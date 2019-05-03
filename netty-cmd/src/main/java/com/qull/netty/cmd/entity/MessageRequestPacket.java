package com.qull.netty.cmd.entity;

import com.qull.netty.cmd.constants.Command;
import lombok.Data;

/**
 * @Description
 * @Author kzh
 * @Date 2019/4/30 22:32
 */
@Data
public class MessageRequestPacket extends Packet{

    private String message;

    public MessageRequestPacket() {}

    public MessageRequestPacket(String message) {
        this.message = message;
    }


    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
