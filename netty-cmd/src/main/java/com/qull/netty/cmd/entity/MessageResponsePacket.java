package com.qull.netty.cmd.entity;

import com.qull.netty.cmd.constants.Command;
import lombok.Data;

/**
 * @Description
 * @Author kzh
 * @Date 2019/4/30 22:36
 */
@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
