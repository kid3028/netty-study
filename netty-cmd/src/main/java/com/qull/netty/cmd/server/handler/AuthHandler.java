package com.qull.netty.cmd.server.handler;

import com.qull.netty.cmd.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 身份认证
 * 1.AuthHandler继承自{@link ChannelInboundHandlerAdapter}, 覆盖了{@link ChannelInboundHandlerAdapter#channelRead(ChannelHandlerContext, Object)}方法，表明它可以处理所有类型的数据
 * 2.在{@link ChannelInboundHandlerAdapter#channelRead(ChannelHandlerContext, Object)}方法里面，在决定是否把读到的数据传递到后续指令处理器之前，首先会判断是否登录成功，如果未登录，
 * 直接关闭连接，否则，就把读取到的数据向下传递，传递给需要后续指令处理器
 * @Description
 * @Author kzh
 * @Date 2019/5/3 0:42
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if(!SessionUtil.hasLogin(ctx.channel())) {
            ctx.channel().close();
        }else {
            // 移除登录校验
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if(SessionUtil.hasLogin(ctx.channel())) {
            System.out.println("当前连接登录验证完毕，无需再次验证，AuthHandler被移除");
        }else {
            System.out.println("无登录验证， 强制关闭连接");
        }
    }
}
