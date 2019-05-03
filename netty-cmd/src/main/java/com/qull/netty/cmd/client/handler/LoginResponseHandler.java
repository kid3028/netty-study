package com.qull.netty.cmd.client.handler;

import com.qull.netty.cmd.entity.LoginRequestPacket;
import com.qull.netty.cmd.entity.LoginResponsePacket;
import com.qull.netty.cmd.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/2 21:33
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(new Random().nextInt(10000));
        loginRequestPacket.setUsername("qull");
        loginRequestPacket.setPassword("pwd");

        // 写数据
        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        if(loginResponsePacket.isSuccess()) {
            System.out.println(String.format("%s : 客户端登录成功", LocalDateTime.now()));
            LoginUtil.markAsLogin(ctx.channel());
            ctx.pipeline().remove(this);
        }else {
            System.out.println(String.format("%s : 客户端登录失败，原因 : %s", LocalDateTime.now(), loginResponsePacket.getReason()));
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端登录成功，移除登录处理器");
    }
}
