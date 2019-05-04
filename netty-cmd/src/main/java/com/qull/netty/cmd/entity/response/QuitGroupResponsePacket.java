package com.qull.netty.cmd.entity.response;

import com.qull.netty.cmd.constants.Command;
import com.qull.netty.cmd.entity.Packet;
import lombok.Data;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/4 8:26
 */
@Data
public class QuitGroupResponsePacket extends Packet {
    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_RESPONSE;
    }
}
