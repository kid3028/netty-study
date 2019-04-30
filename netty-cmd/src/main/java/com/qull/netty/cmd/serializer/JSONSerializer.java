package com.qull.netty.cmd.serializer;

import com.alibaba.fastjson.JSON;

/**
 * @Author kzh
 * @Description
 * @Date 19-4-30 下午6:00
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
