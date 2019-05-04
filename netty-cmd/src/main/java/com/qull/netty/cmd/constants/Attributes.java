package com.qull.netty.cmd.constants;

import com.qull.netty.cmd.session.Session;
import io.netty.util.AttributeKey;

/**
 * @Description
 * @Author kzh
 * @Date 2019/4/30 22:39
 */
public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
