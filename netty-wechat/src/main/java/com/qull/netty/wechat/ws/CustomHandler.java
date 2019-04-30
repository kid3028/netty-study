package com.qull.netty.wechat.ws;

import com.alibaba.fastjson.JSON;
import com.qull.netty.wechat.entity.DataContent;
import com.qull.netty.wechat.enums.MessageActionEnum;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

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
        DataContent data = JSON.parseObject(text, DataContent.class);
        Integer action = data.getAction();
        if(MessageActionEnum.CONNECT.type.equals(action)) {
            // 连接成功保存用户对应Channel到USER_CHANNEL_MAP
        }else if(MessageActionEnum.CHAT.type.equals(action)) {
            // 丢入线程池，使用用户线程异步处理
            // 保存消息到数据库
            // 获取接受方管道
            // 发送消息
        }else if (MessageActionEnum.SIGNED.type.equals(action)) {
            // 签收
        }else if (MessageActionEnum.KEEPALIVE.type.equals(action)) {

        }else {

        }

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

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        UserChannel.remove(ctx.channel());
        ctx.close();
    }
}
