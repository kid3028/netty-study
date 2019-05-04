package com.qull.netty.cmd.client.handler;

import com.qull.netty.cmd.entity.response.LoginResponsePacket;
import com.qull.netty.cmd.session.Session;
import com.qull.netty.cmd.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/2 21:33
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        String userId = loginResponsePacket.getUserId();
        String userName = loginResponsePacket.getUserName();

        if(loginResponsePacket.isSuccess()) {
            System.out.println(String.format("[%s]登录成功，userId为：%s", loginResponsePacket.getUserName(), loginResponsePacket.getUserId()));
            SessionUtil.buildSession(new Session(userId, userName), ctx.channel());
            ctx.pipeline().remove(this);
        }else {
            System.out.println(String.format("[%s]登录失败，原因：%s", loginResponsePacket.getUserName(), loginResponsePacket.getReason()));
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端登录成功，移除登录处理器");
    }
}
