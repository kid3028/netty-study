package com.qull.netty.simpledemo.httpserver;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Description
 * @Author kzh
 * @Date 2019/4/27 10:05
 */
public class HttpServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast("httpCodec", new HttpServerCodec()).addLast("customHandler", new CustomHandler());

    }
}
