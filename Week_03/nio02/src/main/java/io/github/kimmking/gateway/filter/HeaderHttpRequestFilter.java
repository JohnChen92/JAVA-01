package io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeaderHttpRequestFilter implements HttpRequestFilter {

    private static Logger logger = LoggerFactory.getLogger(HeaderHttpRequestFilter.class);


    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        logger.info("filter process!" + fullRequest.headers().toString());
        fullRequest.headers().set("filterAdd", "test");
    }
}
