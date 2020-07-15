/**
 * @author G-bug 2019/9/30
 */
package com.learn.jasper.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.jasper.entity.User;
import com.learn.jasper.mapper.UserMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auth Administrator
 */
@Component
public class UserService {


    @Resource
    private UserMapper userMapper;

    public User getById(int id) {
        return userMapper.selectById(id);
    }

    public List<User> getAllUsers() {
        return userMapper.selectList(null);
    }

    public List<User> getAllUsersAndPage() {
        IPage<User> users = userMapper.selectPageUser(new Page(2,3)/* 当前页码 每页数据量*/);
        return users.getRecords();
    }

    public User createUser(User user) {
        userMapper.insert(user);
        return user;
    }

    public User updateUser(User user) {
        userMapper.updateById(user);
        return user;
    }

    public void deleteById(int id) {
        userMapper.deleteById(id);
    }

}
