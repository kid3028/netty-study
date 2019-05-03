package com.qull.netty.cmd.server.handler;

import com.qull.netty.cmd.entity.LoginRequestPacket;
import com.qull.netty.cmd.entity.LoginResponsePacket;
import com.qull.netty.cmd.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/2 21:50
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        System.out.println(String.format("%s : 收到客户端登录请求...", LocalDateTime.now()));

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        if(valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            System.out.println(String.format("%s : 登录成功！", LocalDateTime.now()));
            LoginUtil.markAsLogin(ctx.channel());
            ctx.pipeline().remove(this);
        }else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账号密码错误");
            System.out.println(String.format("%s : 登录失败", LocalDateTime.now()));
        }
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端登录成功，移除登录处理器");
    }
}
