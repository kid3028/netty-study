package com.qull.netty.cmd.client.handler;

import com.qull.netty.cmd.entity.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/4 11:05
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket quitGroupResponsePacket) throws Exception {
        if (quitGroupResponsePacket.isSuccess()) {
            System.out.println(String.format("退出群聊[%s]成功", quitGroupResponsePacket.getGroupId()));
        }else {
            System.out.println(String.format("退出群聊[%s]失败", quitGroupResponsePacket.getGroupId()));
        }

    }
}
