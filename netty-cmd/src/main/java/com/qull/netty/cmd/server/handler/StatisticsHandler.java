package com.qull.netty.cmd.server.handler;

import com.qull.netty.cmd.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/3 0:15
 */
public class StatisticsHandler extends ChannelInboundHandlerAdapter {

    private static int ONLINE_CLIENT = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ONLINE_CLIENT++;
//        System.out.println(String.format("%s : 当前在线客户端数量为%s", LocalDateTime.now(), ONLINE_CLIENT));
        ctx.executor().scheduleAtFixedRate(() -> System.out.println(String.format("%s : 当前在线客户端数量为%s, USER_ID_CHANNEL_MAP.size : %s", LocalDateTime.now(), ONLINE_CLIENT, SessionUtil.USER_ID_CHANNEL_MAP.size())),
                0, 5, TimeUnit.SECONDS);
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ONLINE_CLIENT--;
        System.out.println(String.format("%s : 当前在线客户端数量为%s", LocalDateTime.now(), ONLINE_CLIENT));
        super.channelInactive(ctx);
    }
}
