package com.qull.netty.cmd.client.handler;

import com.qull.netty.cmd.entity.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/2 21:39
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        System.out.println(String.format("%s : 收到服务端的消息 : %s", LocalDateTime.now(), msg.getMessage()));
    }
}
