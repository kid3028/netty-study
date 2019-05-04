package com.qull.netty.cmd.server.handler;

import com.qull.netty.cmd.entity.request.MessageRequestPacket;
import com.qull.netty.cmd.entity.response.MessageResponsePacket;
import com.qull.netty.cmd.session.Session;
import com.qull.netty.cmd.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/2 21:58
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        // 1.拿到消息发送方的会话信息
        Session session = SessionUtil.getSession(ctx.channel());

        // 2.通过消息发送方的会话信息构造要发送的消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());

        // 3.拿到消息接收方的channel
        Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());

        // 4.将消息发送给消息接受方
        if(toUserChannel != null && SessionUtil.hasLogin(ctx.channel())) {
            toUserChannel.writeAndFlush(messageResponsePacket);
        }else {
            System.err.println(String.format("[%s]不在线，发送失败", messageRequestPacket.getToUserId()));
        }
    }
}
