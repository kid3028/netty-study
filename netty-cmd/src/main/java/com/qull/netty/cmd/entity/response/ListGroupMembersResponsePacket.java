package com.qull.netty.cmd.entity.response;

import com.qull.netty.cmd.constants.Command;
import com.qull.netty.cmd.entity.Packet;
import com.qull.netty.cmd.session.Session;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/4 8:25
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {
    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBER_RESPONSE;
    }
}
