package com.qull.netty.simpledemo.httpserver;

import com.alibaba.fastjson.JSON;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;



/**
 * @Description
 * @Author kzh
 * @Date 2019/4/27 9:40
 */
@Slf4j
public class HelloHttpServer {

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).childHandler(new HttpServerChannelInitializer());
        try {
            ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.info("服务器启动异常，错误信息:{}", JSON.toJSONString(e));
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
