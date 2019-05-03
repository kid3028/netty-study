package com.qull.netty.cmd.server.handler;

import com.qull.netty.cmd.entity.MessageRequestPacket;
import com.qull.netty.cmd.entity.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/2 21:58
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        System.out.println(String.format("%s : 收到客户端消息 : %s", LocalDateTime.now(), msg.getMessage()));

        messageResponsePacket.setMessage(String.format("服务端回复【%s】", msg.getMessage()));
        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
