package com.qull.netty.cmd.entity.request;

import com.qull.netty.cmd.constants.Command;
import com.qull.netty.cmd.entity.Packet;
import lombok.Data;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/3 5:23
 */
@Data
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}
