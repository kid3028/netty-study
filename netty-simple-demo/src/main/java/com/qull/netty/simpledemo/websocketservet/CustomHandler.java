package com.qull.netty.simpledemo.websocketservet;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * @Description
 * @Author kzh
 * @Date 2019/4/27 11:31
 */
@Slf4j
public class CustomHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static ChannelGroup CHANNEL_GROUP = new DefaultChannelGroup("clientChannelGroup", GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String text = msg.text();
        log.info("received client message:{}", text);

        // 因为这里传输的载体是TextWebSocketFrame，所以这里我们也需要使用TextWebSocketFrame作为一个载体，再将消息刷出去
//        CHANNEL_GROUP.writeAndFlush(text);

        CHANNEL_GROUP.writeAndFlush(new TextWebSocketFrame("[服务器在]:" + LocalDateTime.now() + "接受到消息，消息为:" + text ));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        CHANNEL_GROUP.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // 当触发了handlerRemove时，ChannelGroup会自动移除对应客户端的channel，所以此处可以省略
//        CHANNEL_GROUP.remove(ctx.channel());
        log.info("========channel {} 被移除========", ctx.channel().id().asShortText());
    }
}
