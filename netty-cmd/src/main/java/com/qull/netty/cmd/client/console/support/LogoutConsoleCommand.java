package com.qull.netty.cmd.client.console.support;

import com.qull.netty.cmd.client.console.ConsoleCommand;
import com.qull.netty.cmd.entity.request.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/3 7:08
 */
public class LogoutConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }
}
