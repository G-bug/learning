/**
 * @author G-bug 2019/9/30
 */
package com.learn.cache.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Random;

/**
 * @auth Administrator
 * CacheManager 接口是spring提供的cache统一接口 底层使用Cache接口
 */

@Configuration
@EnableCaching
public class RedisCacheConfig {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Environment env;

    @Bean
    // lettuce连接池
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration();
        redisConf.setHostName(env.getProperty("spring.redis.host"));
        redisConf.setPort(Integer.parseInt(env.getProperty("spring.redis.port")));
        redisConf.setPassword(RedisPassword.of(env.getProperty("spring.redis.password")));
        return new LettuceConnectionFactory(redisConf);
    }

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(200))
                .disableCachingNullValues();
        return cacheConfig;
    }

    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager rcm = RedisCacheManager.builder(redisConnectionFactory())
                .cacheDefaults(cacheConfiguration())
                .transactionAware()
                .build();
        return rcm;
    }

    // 自定义缓存key的实现
    @Bean(name = "myKeyGenerator")
    public KeyGenerator myKeyGenerator() {
        return (o, method, params) -> {
            logger.info("自定义缓存, 使用第一参数作为缓存key, params = " + Arrays.toString(params));
            // 常规不能这样使用
            return params[0];
        };
    }

    /********* subscribe publish ******/

    @Bean
    // 接收发送到channel的消息
    public MessageListener listener() {
        return (message, bytes) -> System.out.println("\n" + message.toString());
    }

    @Bean
    // 根据字符串
    public ChannelTopic channelTopic() {
        return new ChannelTopic("cctv");
    }

    @Bean
    // 基于channel name的匹配模式
    public PatternTopic patternTopic() {
        return new PatternTopic("/cctv/*");
    }

    @Bean
    // sub绑定到pub
    public RedisMessageListenerContainer messageListenerContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory());

        container.addMessageListener(listener(), channelTopic());
        return container;
    }
}
