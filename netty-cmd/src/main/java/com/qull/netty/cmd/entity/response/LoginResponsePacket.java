package com.qull.netty.cmd.entity.response;

import com.qull.netty.cmd.constants.Command;
import com.qull.netty.cmd.entity.Packet;
import lombok.Data;

/**
 * @Description
 * @Author kzh
 * @Date 2019/4/30 21:35
 */
@Data
public class LoginResponsePacket extends Packet {
    private String userId;

    private String userName;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
