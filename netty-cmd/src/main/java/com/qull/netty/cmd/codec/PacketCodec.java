package com.qull.netty.cmd.codec;

import com.qull.netty.cmd.constants.Command;
import com.qull.netty.cmd.entity.*;
import com.qull.netty.cmd.serializer.JSONSerializer;
import com.qull.netty.cmd.serializer.Serializer;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author kzh
 * @Date 2019/4/30 20:15
 */
public class PacketCodec {

    public static PacketCodec INSTANCE = new PacketCodec();

    public  static final int MAGIC_NUMBER = 0x12345678;
    private final Map<Byte, Class<? extends Packet>> PACKET_TYPE_MAP;
    private final Map<Byte, Serializer> SERIALIZER_MAP;

    private PacketCodec() {
        PACKET_TYPE_MAP = new HashMap<>();
        PACKET_TYPE_MAP.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        PACKET_TYPE_MAP.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        PACKET_TYPE_MAP.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        PACKET_TYPE_MAP.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);

        SERIALIZER_MAP = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        SERIALIZER_MAP.put(serializer.getSerializerAlgorithm(), serializer);
    }

    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        // 2.序列化对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        //  3.实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        // 1.跳过magic_number 一个int长度是4byte
        byteBuf.skipBytes(4);

        // 2.跳过版本号
        byteBuf.skipBytes(1);

        // 3.获取序列化算法
        byte serializerAlgorithm = byteBuf.readByte();

        // 4.获取指令
        byte command = byteBuf.readByte();

        // 5.获取数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializerAlgorithm);

        if(requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }
        return null;
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return PACKET_TYPE_MAP.get(command);
    }

    private Serializer getSerializer(byte serializerAlgorithm) {
        return SERIALIZER_MAP.get(serializerAlgorithm);
    }
}
