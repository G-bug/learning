/**
 * @author G-bug 2019/9/30
 */
package com.learn.cache.dao.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.cache.dao.entity.User;


public interface UserMapper extends BaseMapper<User> {

    IPage<User> selectPageUser(Page page);

}
