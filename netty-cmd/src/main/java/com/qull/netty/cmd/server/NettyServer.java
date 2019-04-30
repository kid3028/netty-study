package com.qull.netty.cmd.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description
 * @Author kzh
 * @Date 2019/4/29 22:50
 */
@Slf4j
public class NettyServer {

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel ch) throws Exception {
                    }
                });
//        serverBootstrap.bind(8888);
//        serverBootstrap.bind(8888).addListener(new GenericFutureListener<Future<? super Void>>() {
//            @Override
//            public void operationComplete(Future<? super Void> future) throws Exception {
//                if(future.isSuccess()) {
//                    System.out.println("端口绑定成功");
//                }else {
//                    System.out.println("端口绑定失败");
//                }
//            }
//        });

        bind(serverBootstrap, 1000);
    }

    public static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()) {
                    System.out.println(String.format("端口[%s]绑定成功", port));
                }else {
                    System.out.println(String.format("端口[%s]绑定失败", port));
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }
}
