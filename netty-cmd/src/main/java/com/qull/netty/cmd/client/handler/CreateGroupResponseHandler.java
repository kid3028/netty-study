package com.qull.netty.cmd.client.handler;

import com.qull.netty.cmd.entity.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/3 7:47
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {
        System.out.println(String.format("群创建成功，id为:[%s]", createGroupResponsePacket.getGroupId()));
        String.format("群成员有:%s", createGroupResponsePacket.getUserNameList());
    }
}
