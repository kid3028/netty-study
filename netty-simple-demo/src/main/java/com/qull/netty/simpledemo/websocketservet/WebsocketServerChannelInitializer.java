package com.qull.netty.simpledemo.websocketservet;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Description
 * @Author kzh
 * @Date 2019/4/27 11:17
 */
public class WebsocketServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline()
                .addLast("httpCodec", new HttpServerCodec())
                // 对写大数据流的支持
                .addLast(new ChunkedWriteHandler())
                // 对httpMessage进行聚合，聚合成FullHttpMessage或者FullHttpResponse,Netty中的所有编程都会使用到该Handler
                .addLast(new HttpObjectAggregator(1024 * 64))
                // websocket协议支持，并且制定客户端连接访问的路由。会处理握手、ping、pong(心跳)，close。
                // 对于websocket都是以frame进行传输，不同数据类型对应的frame也不同
                .addLast(new WebSocketServerProtocolHandler("/ws"))
                .addLast(new CustomHandler());
    }
}
