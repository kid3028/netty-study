package com.qull.netty.cmd.entity.request;

import com.qull.netty.cmd.constants.Command;
import com.qull.netty.cmd.entity.Packet;
import lombok.Data;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/4 8:16
 */
@Data
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBER_REQUEST;
    }
}
