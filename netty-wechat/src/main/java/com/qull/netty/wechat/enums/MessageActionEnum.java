package com.qull.netty.wechat.enums;

import lombok.AllArgsConstructor;

/**
 * @Description
 * @Author kzh
 * @Date 2019/4/28 21:53
 */
@AllArgsConstructor
public enum MessageActionEnum {

    CONNECT(1, "第一次或重连初始化连接"),
    CHAT(2, "聊天消息"),
    SIGNED(3, "消息签收"),
    KEEPALIVE(4, "客户端保持心跳 "),
    PULL_FRIEND(5, "拉取好友列表")
    ;

    public final Integer type;

    public final String content;


}
