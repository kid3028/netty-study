package com.qull.netty.cmd.client.console.support;

import com.qull.netty.cmd.client.console.ConsoleCommand;
import com.qull.netty.cmd.entity.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/3 7:29
 */
public class CreateGroupConsoleCommmand implements ConsoleCommand {
    private static final String USER_ID_SPLITER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        System.out.println("[拉人群聊]输入userId列表，userId之间使用英文逗号隔开：");
        String userIds = scanner.next();
        createGroupRequestPacket.setUserList(Arrays.asList(userIds.split(",")));
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
