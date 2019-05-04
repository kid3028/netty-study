package com.qull.netty.cmd.client.handler;

import com.qull.netty.cmd.entity.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/4 11:01
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket joinGroupResponsePacket) throws Exception {
        if (joinGroupResponsePacket.isSuccess()) {
            System.out.println(String.format("加入群[%s]成功", joinGroupResponsePacket.getGroupId()));
        }else {
            System.out.println(String.format("加入群[%s]失败，原因为:%s", joinGroupResponsePacket.getGroupId(), joinGroupResponsePacket.getReaseon()));
        }
    }
}
