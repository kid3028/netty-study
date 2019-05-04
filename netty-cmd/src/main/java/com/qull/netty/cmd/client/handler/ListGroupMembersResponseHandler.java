package com.qull.netty.cmd.client.handler;

import com.qull.netty.cmd.entity.response.ListGroupMembersResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/4 11:07
 */
public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersResponsePacket listGroupMembersResponsePacket) throws Exception {
        System.out.println(String.format("群[%s]下群成员为：%s", listGroupMembersResponsePacket.getGroupId(), listGroupMembersResponsePacket.getSessionList()));
    }
}
