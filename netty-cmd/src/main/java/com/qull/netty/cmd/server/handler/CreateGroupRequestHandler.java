package com.qull.netty.cmd.server.handler;

import com.qull.netty.cmd.entity.request.CreateGroupRequestPacket;
import com.qull.netty.cmd.entity.response.CreateGroupResponsePacket;
import com.qull.netty.cmd.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/3 7:52
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> userList = createGroupRequestPacket.getUserList();
        List<String> userNameList = new ArrayList<>();

        // 1.创建一个channel分组
        DefaultChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        // 2.筛选出待加入群聊的用户的channel和userName
        for (String userId : userList) {
            Channel channel = SessionUtil.getChannel(userId);
            if(channel != null) {
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        }

        // 3.创建群聊创建结果的响应
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setGroupId(UUID.randomUUID().toString().split("-")[0]);
        createGroupResponsePacket.setUserNameList(userNameList);

        // 4.给每个客户单发送群通知
        channelGroup.writeAndFlush(createGroupResponsePacket);

        System.out.println(String.format("群创建成功，id为[%s]", createGroupResponsePacket.getGroupId()));
        System.out.println(String.format("群里面有:%s", userNameList));

        // 5.保存群组相关的信息
        SessionUtil.bindChannelGroup(createGroupResponsePacket.getGroupId(), channelGroup);
    }
}


