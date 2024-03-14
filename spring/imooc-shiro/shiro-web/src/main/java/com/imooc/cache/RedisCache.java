package com.imooc.cache;

import com.imooc.util.JedisUtils;
import com.sun.xml.internal.ws.developer.Serialization;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

@Component
public class RedisCache<K, V> implements Cache<K, V> {

    @Resource
    private JedisUtils jedisUtils;

    private final String CACHE_PERFIX = "redis_cache:";

    private byte[] getKey(K k) {



        if (k instanceof String) {
            return (CACHE_PERFIX + k).getBytes();
        }
        return SerializationUtils.serialize(k);
    }

    @Override
    public V get(K key) throws CacheException {
        // 此处在实际项目中可以加入本地的二级缓存
        System.out.println("从cache中获取授权信息");
        byte[] v = jedisUtils.get(getKey(key));
        if (v != null) {
            return (V) SerializationUtils.deserialize(v);
        }
        return null;
    }

    @Override
    public V put(K key, V value) throws CacheException {
        byte[] k = getKey(key);
        byte[] v = SerializationUtils.serialize(value);
        jedisUtils.set(k, v);
        jedisUtils.expire(k, 600);
        return value;
    }

    @Override
    public V remove(K key) throws CacheException {
        byte[] k = getKey(key);
        jedisUtils.del(k);
        return get(key);
    }

    @Override
    public void clear() throws CacheException {
        // 因为redis不止存储session的缓存所以不用重写此方法
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
