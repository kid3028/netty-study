package com.qull.netty.cmd.client.handler;

import com.qull.netty.cmd.entity.response.GroupMessageResponsePacket;
import com.qull.netty.cmd.session.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/4 14:35
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket groupMessageResponsePacket) throws Exception {
        String fromGroupId = groupMessageResponsePacket.getFromGroupId();
        Session fromUser = groupMessageResponsePacket.getFromUser();
        System.out.println(String.format("收到群[%s]中[%s]发来的消息:%s", fromGroupId, fromUser, groupMessageResponsePacket.getMessage()));
    }
}
