package com.qull.netty.wechat.ws;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author kzh
 * @Date 2019/4/27 15:03
 */
@Component
@Slf4j
public class WSServer {
    private static final WSServer WS_SERVER = new WSServer();
    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;
    private ServerBootstrap serverBootstrap;

    private WSServer() {
        bossGroup = new NioEventLoopGroup();
        workGroup = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).childHandler(new WSServerChannelInitializer());
    }

    public static WSServer getInstance() {
        return WS_SERVER;
    }

    /**
     * 因为该类已经托管于spring容器，所以不需要主动调用shutdown
     */
    public void start() {
        serverBootstrap.bind(8888);
        log.error("=============NettyServer started===============");
    }

    public WSServer readResolve() {
        return WS_SERVER;
    }
}
