package com.qull.netty.wechat;

import com.qull.netty.wechat.ws.WSServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author kzh
 * @Date 2019/4/27 15:14
 */
@Component
@Slf4j
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null) {
            try {
                WSServer.getInstance().start();
            }catch (Exception e) {
                log.error("==============Netty服务启动失败=================");
            }
        }
    }
}
