package com.qull.netty.wechat.entity;

import lombok.Data;

/**
 * @Description
 * @Author kzh
 * @Date 2019/4/28 21:51
 */
@Data
public class DataContent {

    private Integer action;

    private ChatMessage message;

    /**
     * 扩展字段
     */
    private String extand;
}
