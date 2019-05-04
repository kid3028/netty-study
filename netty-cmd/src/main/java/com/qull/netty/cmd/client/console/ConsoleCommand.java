package com.qull.netty.cmd.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/3 7:03
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
