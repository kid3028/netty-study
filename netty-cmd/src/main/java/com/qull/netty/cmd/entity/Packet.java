package com.qull.netty.cmd.entity;

import lombok.Data;

/**
 * 客户端与服务端通信的Java对象
 * @Author kzh
 * @Description
 * @Date 19-4-30 下午5:45
 */
@Data
public abstract class Packet {
    /**
     * 协议版本号
     */
    private Byte version = 1;

    /**
     * 获取指令
     * @return
     */
    public abstract Byte getCommand();
}
