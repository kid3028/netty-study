package com.qull.netty.cmd.entity;

import com.qull.netty.cmd.constants.Command;
import lombok.Data;

/**
 * @Author kzh
 * @Description
 * @Date 19-4-30 下午5:49
 */
@Data
public class LoginRequestPacket extends Packet {

    private Integer userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
