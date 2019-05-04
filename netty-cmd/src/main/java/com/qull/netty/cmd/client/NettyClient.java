package com.qull.netty.cmd.client;

import com.qull.netty.cmd.client.console.support.ConsoleCommandManager;
import com.qull.netty.cmd.client.console.support.LoginConsoleCommand;
import com.qull.netty.cmd.client.handler.*;
import com.qull.netty.cmd.codec.PacketDecoder;
import com.qull.netty.cmd.codec.PacketEncoder;
import com.qull.netty.cmd.codec.Spliter;
import com.qull.netty.cmd.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author kzh
 * @Date 2019/4/30 7:43
 */
public class NettyClient {
    private final static int MAX_RETRY = 10;
    public static void main(String[] args) {
        EventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                // 指定线程模型
                .group(workGroup)
                // 指定IO类型为NIO
                .channel(NioSocketChannel.class)
                // IO处理逻辑
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline()
//                                .addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4))
                                .addLast(new Spliter())
                                .addLast(new PacketDecoder())
                                .addLast(new LoginResponseHandler())
                                .addLast(new MessageResponseHandler())
                                .addLast(new CreateGroupResponseHandler())
                                .addLast(new JoinGroupResponseHandler())
                                .addLast(new QuitGroupResponseHandler())
                                .addLast(new GroupMessageResponseHandler())
                                .addLast(new ListGroupMembersResponseHandler())
                                .addLast(new PacketEncoder());
                    }
                });
//        bootstrap.connect("localhost", 8888).addListener(future -> {
//            if(future.isSuccess()) {
//                System.out.println("连接成功");
//            }else {
//                System.out.println("连接失败");
//            }
//        });
        connect(bootstrap,"localhost", 9999, MAX_RETRY);
    }


    public static void connect(final Bootstrap bootstrap, final String host, final int port, final int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if(future.isSuccess()) {
                System.out.println("连接成功");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            }else if(retry == 0) {
                System.out.println("重试次数已用完，放弃连接");
            }else {
                //  第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.out.println(String.format("%s : 连接失败, 第%s次重连...", LocalDateTime.now(), order));
                bootstrap.config().group().schedule(
                        ()-> connect(bootstrap, host, port, retry), delay, TimeUnit.SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
//        Scanner sc = new Scanner(System.in);
//        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
//        new Thread(() -> {
//            while (!Thread.interrupted()) {
//                if(!SessionUtil.hasLogin(channel)) {
//                    System.out.println("输入用户名登录:");
//                    String userName = sc.nextLine();
//                    loginRequestPacket.setUsername(userName);
//                    loginRequestPacket.setPassword("pwd");
//                    channel.writeAndFlush(loginRequestPacket);
//                    waitForLoginResponse();
//                }else {
//                    String message = sc.next();
//                    if(StringUtils.isEmpty(message)) {
//                        continue;
//                    }
//                    if("exit".equalsIgnoreCase(message)) {
//                        channel.writeAndFlush(new LogoutRequestPacket());
//                        waitForLoginResponse();
//                        channel.close();
//                        System.exit(0);
//                    }else {
//                        String toUserId = sc.next();
//                        channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
//                    }
//                }
//            }
//        }).start();

        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        Scanner scanner = new Scanner(System.in);
        new Thread(() ->{
            while (!Thread.interrupted()) {
                if(!SessionUtil.hasLogin(channel)) {
                    loginConsoleCommand.exec(scanner, channel);
                }else {
                    consoleCommandManager.exec(scanner, channel);
                }
            }
        }).start();
    }

    public static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }}
