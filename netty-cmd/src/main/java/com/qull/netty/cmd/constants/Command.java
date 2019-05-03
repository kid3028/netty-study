package com.qull.netty.cmd.constants;

/**
 * @Author kzh
 * @Description
 * @Date 19-4-30 下午5:49
 */
public interface Command {
    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;
}
