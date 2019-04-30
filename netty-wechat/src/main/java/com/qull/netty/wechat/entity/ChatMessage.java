package com.qull.netty.wechat.entity;

import lombok.Data;

/**
 * @Description
 * @Author kzh
 * @Date 2019/4/28 21:49
 */
@Data
public class ChatMessage {

    private Long id;

    private Long sendUserId;

    private Long receiveUserId;

    private String content;
}
