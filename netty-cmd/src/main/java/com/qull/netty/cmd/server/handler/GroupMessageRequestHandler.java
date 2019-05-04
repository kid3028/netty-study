package com.qull.netty.cmd.server.handler;

import com.qull.netty.cmd.entity.request.GroupMessageRequestPacket;
import com.qull.netty.cmd.entity.response.GroupMessageResponsePacket;
import com.qull.netty.cmd.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/4 14:41
 */
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket groupMessageRequestPacket) throws Exception {
        // 1.拿到groupId构造群聊消息的响应
        String toGroupId = groupMessageRequestPacket.getToGroupId();
        GroupMessageResponsePacket groupMessageResponsePacket = new GroupMessageResponsePacket();
        groupMessageResponsePacket.setFromGroupId(toGroupId);
        groupMessageResponsePacket.setMessage(groupMessageRequestPacket.getMessage());
        groupMessageResponsePacket.setFromUser(SessionUtil.getSession(ctx.channel()));

        // 2.拿到群聊对应的channelGroup,写到每个客户端
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(toGroupId);
        channelGroup.writeAndFlush(groupMessageResponsePacket);

    }
}
