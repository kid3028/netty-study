package com.qull.netty.cmd.serializer;

/**
 * @Author kzh
 * @Description
 * @Date 19-4-30 下午5:55
 */
public interface Serializer {
    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * Java对象转化为二进制
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 二进制转化为Java对象
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();
}
