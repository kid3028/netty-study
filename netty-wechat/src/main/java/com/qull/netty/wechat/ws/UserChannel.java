package com.qull.netty.wechat.ws;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description
 * @Author kzh
 * @Date 2019/4/28 22:27
 */
public class UserChannel {

    private static final ConcurrentHashMap<Long, Channel> USER_CHANNEL_MAP = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Long> CHANNEL_USER_MAP = new ConcurrentHashMap<>();

    public static void put(Long userId, Channel channel) {
        USER_CHANNEL_MAP.putIfAbsent(userId, channel);
        CHANNEL_USER_MAP.putIfAbsent(channel.id().asLongText(), userId);
    }

    public static Channel get(Long userId) {
        return USER_CHANNEL_MAP.get(userId);
    }

    public static void remove(Channel channel) {
        Long userId = CHANNEL_USER_MAP.get(channel.id().asLongText());
        USER_CHANNEL_MAP.remove(userId);
        CHANNEL_USER_MAP.remove(channel.id().asLongText());
    }
}
