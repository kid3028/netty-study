package com.qull.netty.cmd.entity.request;

import com.qull.netty.cmd.constants.Command;
import com.qull.netty.cmd.entity.Packet;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/3 7:30
 */
@Data
public class CreateGroupRequestPacket extends Packet {
    private List<String> userList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
