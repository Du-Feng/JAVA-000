package io.github.kimmking.gateway.outbound.netty4;

import io.github.kimmking.gateway.outbound.HttpOutBoundHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class NettyHttpClientOutboundHandler implements HttpOutBoundHandler {
    private static final Logger logger = LoggerFactory.getLogger(NettyHttpClientOutboundHandler.class);

    private final NettyHttpClient client;

    private final String backendUrl;

    public NettyHttpClientOutboundHandler(String backendUrl) {
        Objects.requireNonNull(backendUrl, "BackendUrl can not be null!");
        logger.info("NettyHttpClientOutboundHandler is created for backendUrl {}", backendUrl);
        this.client = new NettyHttpClient();
        this.backendUrl = backendUrl;
    }

    @Override
    public void handle(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        final String url = this.backendUrl + fullRequest.uri();
        fetchGet(fullRequest, ctx, url);
    }

    private void fetchGet(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final String url) {
        HttpGet.Builder builder = new HttpGet.Builder().uri(url);
        fullRequest.headers().forEach(header -> builder.header(header.getKey(), header.getValue()));
        client.execute(builder.build(), new SimpleChannelInboundHandler<FullHttpResponse>() {
            @Override
            protected void channelRead0(ChannelHandlerContext channelHandlerContext, final FullHttpResponse msg) throws Exception {
                handleResponse(fullRequest, ctx, msg);
            }
        });
    }

    private void handleResponse(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx,
                                final FullHttpResponse endpointResponse) {
        byte[] bytes = endpointResponse.content().toString(CharsetUtil.UTF_8).getBytes();
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(bytes));
        response.headers().set("Content-Type", "application/json");
        response.headers().setInt("Content-Length", Integer.parseInt(endpointResponse.headers().get("Content-Length")));
        if (fullRequest != null) {
            if (!HttpUtil.isKeepAlive(fullRequest)) {
                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            } else {
                ctx.write(response);
            }
        }
        ctx.flush();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}