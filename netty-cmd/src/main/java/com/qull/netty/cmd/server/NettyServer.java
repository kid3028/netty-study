package com.qull.netty.cmd.server;

import com.qull.netty.cmd.codec.PacketDecoder;
import com.qull.netty.cmd.codec.PacketEncoder;
import com.qull.netty.cmd.codec.Spliter;
import com.qull.netty.cmd.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
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
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline()
//                                .addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4))
                                .addLast(new Spliter())
                                .addLast(new StatisticsHandler())
                                .addLast(new PacketDecoder())
                                .addLast(new LoginRequestHandler())
                                .addLast(new AuthHandler())
                                .addLast(new LogoutRequestHandler())
                                .addLast(new CreateGroupRequestHandler())
                                .addLast(new JoinGroupRequestHandler())
                                .addLast(new ListGroupMembersRequestHandler())
                                .addLast(new QuitGroupRequestHandler())
                                .addLast(new GroupMessageRequestHandler())
                                .addLast(new MessageRequestHandler())
                                .addLast(new PacketEncoder());
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

        bind(serverBootstrap, 9999);
    }

    public static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println(String.format("端口[%s]绑定成功", port));
                } else {
                    System.out.println(String.format("端口[%s]绑定失败", port));
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }
}
