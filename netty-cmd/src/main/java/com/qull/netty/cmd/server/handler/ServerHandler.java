//package com.qull.netty.cmd.server.handler;
//
//import com.qull.netty.cmd.codec.PacketCodec;
//import com.qull.netty.cmd.entity.*;
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//
//import java.time.LocalDateTime;
//
///**
// * @Description
// * @Author kzh
// * @Date 2019/4/30 20:58
// */
//public class ServerHandler extends ChannelInboundHandlerAdapter {
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println(String.format("%s : 客户端开始登录...", LocalDateTime.now()));
//        ByteBuf requestByteBuf = (ByteBuf) msg;
//
//        Packet packet = PacketCodec.INSTANCE.decode(requestByteBuf);
//
//        if(packet instanceof LoginRequestPacket) {
//            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
//
//            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
//            loginResponsePacket.setVersion(packet.getVersion());
//            if(valid(loginRequestPacket)) {
//                loginResponsePacket.setSuccess(true);
//                System.out.println(String.format("%s : 登录成功", LocalDateTime.now()));
//            }else {
//                loginResponsePacket.setReason("账号密码错误");
//                loginResponsePacket.setSuccess(false);
//                System.out.println(String.format("%s : 登录失败", LocalDateTime.now()));
//            }
//
//            ByteBuf byteBuf = PacketCodec.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
//            ctx.channel().writeAndFlush(byteBuf);
//        }else if(packet instanceof MessageRequestPacket) {
//            // 处理消息
//            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
//            System.out.println(String.format("%s : 收到客户端消息 : %s", LocalDateTime.now(), messageRequestPacket.getMessage()));
//
//            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
//            messageResponsePacket.setMessage(String.format("服务端回复【%s】", messageRequestPacket.getMessage()));
//            ByteBuf byteBuf = PacketCodec.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
//            ctx.channel().writeAndFlush(byteBuf);
//        }
//    }
//
//    private boolean valid(LoginRequestPacket loginRequestPacket) {
//        return true;
//    }
//}
