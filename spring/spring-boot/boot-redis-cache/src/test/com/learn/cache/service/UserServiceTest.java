package com.learn.cache.service;

import com.learn.cache.Application;
import com.learn.cache.dao.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @auth Administrator
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testCache() {
        int id = new Random().nextInt(1000);
        User user = new User(id, "admin", "admin");
        userService.createUser(user);

        int id2 = new Random().nextInt(1000);
        User user2 = new User(id2, "xiong", "neng");
        userService.createUser(user2);

        List<User> list = userService.getAllUsers();
        assertEquals(list.size(), 2);

        User user3 = userService.getById(id);
        assertEquals(user3.getPassword(), "admin");

        User user4 = userService.getById(id);
        assertEquals(user4.getPassword(), "admin");

        user4.setPassword("123456");
        userService.updateUser(user4);

        User user5 = userService.getById(id);
        assertEquals(user5.getPassword(), "123456");

        userService.deleteById(id);
        assertNull(userService.getById(id));

    }

    @Test
    public void testRedisTemplate() {
        // 字符串(数组结构,可追加)
        stringRedisTemplate.opsForValue().set("str", "generator");
        stringRedisTemplate.opsForValue().append("str", "exclude");
        System.out.println(stringRedisTemplate.opsForValue().get("str"));
        // 无序集(内部使用hash表)
        redisTemplate.opsForSet().add("set", "evict", "completed", "shortcut");
        System.out.println(redisTemplate.opsForSet().members("set"));
        // Hash(key-value)
        redisTemplate.opsForHash().put("hash", "information", "disabled");
        redisTemplate.opsForHash().put("hash", "available", "through");
        System.out.println(redisTemplate.opsForHash().get("hash", "available"));
        // XXOperations, 视图操作接口类
        ValueOperations<String, User> value = redisTemplate.opsForValue();
        value.set("view-user", userService.getById(2));
        System.out.println(value.get("view-user").getPassword());
    }

    @Autowired
    private ChannelTopic topic;

    @Test
    public void testRedisSubPub() {
        System.out.println(Math.round(-1.6));
        stringRedisTemplate.convertAndSend(topic.getTopic(), "this is cctv");
    }

}