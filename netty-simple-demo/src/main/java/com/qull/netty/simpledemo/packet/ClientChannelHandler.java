package com.qull.netty.simpledemo.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/2 23:05
 */
public class ClientChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 100; i++) {
            String msg = "这里是Netty拆包粘包教程，欢迎查看demo";
            ByteBuf byteBuf = Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8);
            ctx.channel().writeAndFlush(byteBuf);
        }
    }
}
