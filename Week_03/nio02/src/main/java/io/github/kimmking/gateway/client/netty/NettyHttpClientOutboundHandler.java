package io.github.kimmking.gateway.client.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyHttpClientOutboundHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(NettyHttpClientOutboundHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx)
            throws Exception {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        logger.info("Read: ", msg);
    }
}