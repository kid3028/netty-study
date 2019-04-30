package com.qull.netty.cmd.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author kzh
 * @Date 2019/4/30 7:43
 */
public class NettyClient {
    private final static int MAX_RETRY = 10;
    public static void main(String[] args) {
        EventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                // 指定线程模型
                .group(workGroup)
                // 指定IO类型为NIO
                .channel(NioSocketChannel.class)
                // IO处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                    }
                });
//        bootstrap.connect("localhost", 8888).addListener(future -> {
//            if(future.isSuccess()) {
//                System.out.println("连接成功");
//            }else {
//                System.out.println("连接失败");
//            }
//        });
        connect(bootstrap,"localhost", 8888, MAX_RETRY);
    }


    public static void connect(final Bootstrap bootstrap, final String host, final int port, final int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if(future.isSuccess()) {
                System.out.println("连接成功");
            }else if(retry == 0) {
                System.out.println("重试次数已用完，放弃连接");
            }else {
                //  第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.out.println(String.format("%s : 连接失败, 第%s次重连...", LocalDateTime.now(), order));
                bootstrap.config().group().schedule(
                        ()-> connect(bootstrap, host, port, retry), delay, TimeUnit.SECONDS);
            }
        });
    }
}
