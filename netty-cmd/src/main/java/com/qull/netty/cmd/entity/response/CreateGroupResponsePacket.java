package com.qull.netty.cmd.entity.response;

import com.qull.netty.cmd.constants.Command;
import com.qull.netty.cmd.entity.Packet;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/3 7:47
 */
@Data
public class CreateGroupResponsePacket extends Packet {
    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
