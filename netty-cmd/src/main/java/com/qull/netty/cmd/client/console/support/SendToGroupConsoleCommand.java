package com.qull.netty.cmd.client.console.support;

import com.qull.netty.cmd.client.console.ConsoleCommand;
import com.qull.netty.cmd.entity.request.GroupMessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/4 14:28
 */
public class SendToGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("发送群消息:");
        System.out.println("输入群id:");
        String toGroupId = scanner.next();
        System.out.println("输入消息内容:");
        String message = scanner.next();
        channel.writeAndFlush(new GroupMessageRequestPacket(toGroupId, message));
    }
}
