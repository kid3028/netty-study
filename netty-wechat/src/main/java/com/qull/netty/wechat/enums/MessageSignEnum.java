package com.qull.netty.wechat.enums;

/**
 * @Description
 * @Author kzh
 * @Date 2019/4/28 22:48
 */
public enum  MessageSignEnum {
    SIGN(1),
    UNSIGN(0)
    ;

    public Integer signStatus;

    MessageSignEnum(Integer signStatus) {
        this.signStatus = signStatus;
    }
}
