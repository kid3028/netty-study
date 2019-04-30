package com.qull.netty.wechat.ws;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description
 * @Author kzh
 * @Date 2019/4/29 14:05
 */
@Slf4j
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 判断当前的Event是够是IdleStateEvent(用于触发用户事件，包含读空闲、写空闲、读写空闲)
        if(evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if(IdleState.READER_IDLE.equals(idleStateEvent.state())) {
                log.info("======= 进入读空闲 =========");
            }else if(IdleState.WRITER_IDLE.equals(idleStateEvent.state())) {
                log.info("======== 进入写空闲 ========");
            }else if(IdleState.ALL_IDLE.equals(idleStateEvent.state())) {
                log.error("======== 进入读写空闲 =======");
                Channel channel = ctx.channel();
                UserChannel.remove(channel);
                ctx.close();
            }
        }

    }
}
