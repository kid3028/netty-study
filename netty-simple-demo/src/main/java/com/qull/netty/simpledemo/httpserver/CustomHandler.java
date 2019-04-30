package com.qull.netty.simpledemo.httpserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description
 * @Author kzh
 * @Date 2019/4/27 10:07
 */
@Slf4j
public class CustomHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject httpObject) throws Exception {
        Channel channel = ctx.channel();
        log.info("remoteAddress:{}", channel.remoteAddress());
        if(httpObject instanceof HttpRequest) {
            HttpRequest message = (HttpRequest) httpObject;
            System.out.println(message.toString());
            ByteBuf byteBuf = Unpooled.copiedBuffer("hello Netty", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain").set(HttpHeaderNames.CONTENT_LENGTH,byteBuf.readableBytes());
            ctx.writeAndFlush(response);
        }

    }
}
