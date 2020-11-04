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
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.URI;

public class NettyHttpClient {
    public void execute(RequestInfo requestInfo, SimpleChannelInboundHandler<FullHttpResponse> handler) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            URI uri = requestInfo.uri();
            String host = uri.getHost();
            int port = uri.getPort();

            Bootstrap bootstrap = new Bootstrap()
                    .group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, false)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
                            pipeline.addLast(new HttpResponseDecoder());
                            //客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
                            pipeline.addLast(new HttpRequestEncoder());
                            pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                            pipeline.addLast(handler);
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();

            FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, uri.getRawPath());
            HttpHeaders headers = request.headers();
            headers.set(HttpHeaderNames.HOST, host)
                    .set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE)
                    .set(HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderValues.GZIP);
            requestInfo.headers().forEach(httpHeader -> headers.set(httpHeader.getKey(), httpHeader.getValue()));
            channelFuture.channel().writeAndFlush(request);
            
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
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