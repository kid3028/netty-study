package com.qull.netty.cmd.server.handler;

import com.qull.netty.cmd.entity.request.LoginRequestPacket;
import com.qull.netty.cmd.entity.response.LoginResponsePacket;
import com.qull.netty.cmd.session.Session;
import com.qull.netty.cmd.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/2 21:50
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUserName(loginRequestPacket.getUsername());

        if(valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            String userId = randomUserId();
            loginResponsePacket.setUserId(userId);
            System.out.println(String.format("[%s]登录成功", loginRequestPacket.getUsername()));
            SessionUtil.buildSession(new Session(userId, loginRequestPacket.getUsername()), ctx.channel());
        }else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(String.format("%s : 登录失败", LocalDateTime.now()));
        }

        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    public boolean valid(LoginRequestPacket loginRequestPacket) {return true;}

    public static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }
}
