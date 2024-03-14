/**
 * @author G-bug 2019/9/30
 */
package com.learn.cache.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.cache.dao.entity.User;
import com.learn.cache.dao.repository.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auth Administrator
 */
@Component
public class UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    // 与@Autowired类似 @Resource缺省按bean的byName注入, @Autowired先byType再byName
    private UserMapper userMapper;


    @Cacheable(value = "userCache", key = "#id", unless = "#result == null")
    // value: 指定缓存名称  {cache1, cache2}
    // key: 指定缓存的key,  此处是 id 的值 , 可使用spEL表达式
    // condition: SpEL表达式 用来确定是否缓存
    // unless: 与condition相反, 用来否定缓存 此处 结果(#result)为空时不缓存
    public User getById(int id) {
        logger.info("获取用户start...");
        return userMapper.selectById(id);
    }

    @Cacheable(value = "allUsersCache", unless = "#result.size() == 0")
    public List<User> getAllUsers() {
        logger.info("获取用户列表");
        return userMapper.selectList(null);
    }

    public List<User> getAllUsersAndPage() {
        /* 1. 利用各种WP(Wrapper)
         LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
         IPage<User> users = userMapper.selectPage(new Page<>(2,3), queryWrapper);
         */
        // 2. 自定义sql和方法
        IPage<User> users = userMapper.selectPageUser(new Page(2,3)/* 当前页码 每页数据量*/);
        return users.getRecords();
    }

    @Caching(
            // 可用于类及方法上, 与@Cacheable类同, 不同的是,执行方法前不检测是否已缓存执行结果 总会缓存结果
            put = {@CachePut(value = "userCache", key = "#user.id")},
            // 标记需要清除缓存, 标记类时每个方法执行都会清除缓存
            // allEntries 是否清除该cache下所有元素,true时忽略key属性 清除所有键值对
            // beforeInvocation=true 指定该属性时,spring在执行方法前清除缓存
            evict = {@CacheEvict(value = "allUserCache", allEntries = true)}
    )
    public User createUser(User user) {
        logger.info("创建用户start..., user.id=" + user.getId());
        userMapper.insert(user);
        return user;
    }

    // 该注解组合多个spring cache注解, 三个属性put,evict,cacheable
    @Caching(
            // 更新完即写入缓存
            put = {@CachePut(value = "userCache", key = "#user.id")},
            // 清空 allUserCache
            evict = {@CacheEvict(value = "allUserCache", allEntries = true)}
    )
    public User updateUser(User user) {
        logger.info("更新用户start...");
        userMapper.updateById(user);
        return user;
    }

    @Caching(evict = {@CacheEvict(value = "userCache", key = "#id"), @CacheEvict(value = "userCache", allEntries = true)})
    public void deleteById(int id) {
        logger.info("删除用户start...");
        userMapper.deleteById(id);
    }

}
