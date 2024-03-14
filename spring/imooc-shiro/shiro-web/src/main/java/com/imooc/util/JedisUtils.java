package com.imooc.util;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.Set;

@Component
public class JedisUtils {

    // 根据redis连接池获取redis连接
    @Resource
    private JedisPool jedisPool;

    private Jedis getResource() {
        return jedisPool.getResource();
    }

    public byte[] set(byte[] key, byte[] value) {

        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value);
            return value;
        }
    }

    public void expire(byte[] key, int i) {

        try (Jedis jedis = jedisPool.getResource();) {
            jedis.expire(key, i);
        }

    }

    public byte[] get(byte[] key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.get(key);
        }
    }

    public void del(byte[] key) {
        try (Jedis jedis = jedisPool.getResource()){
            jedis.del(key);
        }
    }

    public Set<byte[]> keys(String SHIRO_SESSION_PERFIX) {
        try (Jedis jedis = jedisPool.getResource()){
            return jedis.keys((SHIRO_SESSION_PERFIX + "*").getBytes());
        }
    }
}
