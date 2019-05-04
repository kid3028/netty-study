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

    Byte LOGOUT_REQUEST = 5;

    Byte CREATE_GROUP_REQUEST = 6;
    Byte CREATE_GROUP_RESPONSE = 7;

    Byte JOIN_GROUP_REQUEST = 8;
    Byte JOIN_GROUP_RESPONSE = 9;

    Byte LIST_GROUP_MEMBER_REQUEST = 10;
    Byte LIST_GROUP_MEMBER_RESPONSE = 11;

    Byte QUIT_GROUP_REQUEST = 12;
    Byte QUIT_GROUP_RESPONSE = 13;

    Byte GROUP_MESSAGE_REQUEST = 14;
    Byte GROUP_MESSAGE_RESPONSE = 15;
}
