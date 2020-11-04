package io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRequestHeaderAppenderFilter implements HttpRequestFilter {
    private static Logger logger = LoggerFactory.getLogger(HttpRequestHeaderAppenderFilter.class);
    private static final String HEADER = "nio";

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set(HEADER, "du feng");
        logger.info("Added header:nio = " + fullRequest.headers().get(HEADER));
    }
}
