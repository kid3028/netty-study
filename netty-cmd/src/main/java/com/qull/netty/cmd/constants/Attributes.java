package com.qull.netty.cmd.constants;

import io.netty.util.AttributeKey;

/**
 * @Description
 * @Author kzh
 * @Date 2019/4/30 22:39
 */
public interface Attributes {
    //  登录成功标识位
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
