package com.qull.netty.cmd.entity.response;

import com.qull.netty.cmd.constants.Command;
import com.qull.netty.cmd.entity.Packet;
import lombok.Data;

/**
 * @Description
 * @Author kzh
 * @Date 2019/4/30 22:36
 */
@Data
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String fromUserName;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
