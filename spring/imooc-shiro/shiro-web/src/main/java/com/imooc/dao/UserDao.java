package com.imooc.dao;

import com.imooc.vo.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao {

    User getUserByName(String userName);

    List<String> getRolesByUserName(String userName);
}
