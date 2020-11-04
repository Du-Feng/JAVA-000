package io.github.kimmking.gateway.inbound;

import io.github.kimmking.gateway.NettyServerApplication;
import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.github.kimmking.gateway.filter.HttpRequestHeaderAppenderFilter;
import io.github.kimmking.gateway.outbound.HttpOutBoundHandler;
import io.github.kimmking.gateway.outbound.httpclient4.HttpClientOutboundHandler;
import io.github.kimmking.gateway.outbound.netty4.NettyHttpClientOutboundHandler;
import io.github.kimmking.gateway.outbound.okhttp.OkHttpOutboundHandler;
import io.github.kimmking.gateway.router.HttpEndpointRouter;
import io.github.kimmking.gateway.router.RandomEndpointRouter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HttpInboundHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);
    private final List<String> proxyServers;
    private HttpOutBoundHandler handler;
    private final HttpRequestFilter filter;
    private final HttpEndpointRouter router;

    public HttpInboundHandler(List<String> proxyServers) {
        this.proxyServers = proxyServers;
        filter = new HttpRequestHeaderAppenderFilter();
        router = new RandomEndpointRouter();

        String outBoundHandlerType = System.getProperty("outBoundHandlerType", NettyServerApplication.HTTPCLIENT_MODE);
        if (outBoundHandlerType.equalsIgnoreCase(NettyServerApplication.HTTPCLIENT_MODE)) {
            handler = new HttpClientOutboundHandler(router.route(proxyServers));
        } else if (outBoundHandlerType.equalsIgnoreCase(NettyServerApplication.OKHTTP_MODE))  {
            handler = new OkHttpOutboundHandler(router.route(proxyServers));
        } else {
            handler = new NettyHttpClientOutboundHandler(router.route(proxyServers));
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            logger.info("channelRead流量接口请求开始");
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
//            String uri = fullRequest.uri();
//            logger.info("接收到的请求url为{}", uri);
//            if (uri.contains("/test")) {
//                handlerTest(fullRequest, ctx);
//            }
            filter.filter(fullRequest, ctx);
            handler.handle(fullRequest, ctx);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

//    private void handlerTest(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
//        FullHttpResponse response = null;
//        try {
//            String value = "hello,kimmking";
//            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
//            response.headers().set("Content-Type", "application/json");
//            response.headers().setInt("Content-Length", response.content().readableBytes());
//
//        } catch (Exception e) {
//            logger.error("处理测试接口出错", e);
//            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
//        } finally {
//            if (fullRequest != null) {
//                if (!HttpUtil.isKeepAlive(fullRequest)) {
//                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
//                } else {
//                    response.headers().set(CONNECTION, KEEP_ALIVE);
//                    ctx.write(response);
//                }
//            }
//        }
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//        cause.printStackTrace();
//        ctx.close();
//    }

}
