package io.github.kimmking.gateway.outbound.netty4;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpVersion;

import java.net.URI;

/**
 * 基于Netty的HttpClient
 */
public class NettyHttpClient {

    public void execute(HttpRequest request, SimpleChannelInboundHandler<FullHttpResponse> handler) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            URI uri = request.uri();
            String host = uri.getHost();
            int port = uri.getPort();
            // 构建HTTP请求
            FullHttpRequest fullHttpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, uri.getRawPath());
            HttpHeaders headers = fullHttpRequest.headers();
            headers.set(HttpHeaderNames.HOST, host)
                    .set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE)
                    .set(HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderValues.GZIP);
            request.headers().forEach(httpHeader -> headers.set(httpHeader.getKey(), httpHeader.getValue()));
            Bootstrap bootstrap = new Bootstrap()
                    .group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, false)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new HttpClientCodec());
                            p.addLast(new HttpObjectAggregator(1024 * 1024));
                            p.addLast(handler);
                        }
                    });
            // Start the client.
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            // Send the HTTP request.
            channelFuture.channel().writeAndFlush(request);
            // Wait for the server to close the connection.
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Shut down executor threads to exit.
            workerGroup.shutdownGracefully();
        }
    }
}

//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelOption;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import io.netty.handler.codec.http.HttpRequestEncoder;
//import io.netty.handler.codec.http.HttpResponseDecoder;
//
//public class NettyHttpClient {
//    public void connect(String host, int port) throws Exception {
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//
//        try {
//            Bootstrap b = new Bootstrap();
//            b.group(workerGroup);
//            b.channel(NioSocketChannel.class);
//            b.option(ChannelOption.SO_KEEPALIVE, true);
//            b.handler(new ChannelInitializer<SocketChannel>() {
//                @Override
//                public void initChannel(SocketChannel ch) throws Exception {
//                    // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
//                    ch.pipeline().addLast(new HttpResponseDecoder());
//                     客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
//                    ch.pipeline().addLast(new HttpRequestEncoder());
//                    ch.pipeline().addLast(new HttpClientOutboundHandler());
//                }
//            });
//
//            // Start the client.
//            ChannelFuture f = b.connect(host, port).sync();
//
//           
//            f.channel().write(request);
//            f.channel().flush();
//            f.channel().closeFuture().sync();
//        } finally {
//            workerGroup.shutdownGracefully();
//        }
//
//    }
//
//    public static void main(String[] args) throws Exception {
//        NettyHttpClient client = new NettyHttpClient();
//        client.connect("127.0.0.1", 8844);
//    }
//}