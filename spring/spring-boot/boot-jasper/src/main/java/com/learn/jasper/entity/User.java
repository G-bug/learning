/**
 * @author G-bug 2019/9/30
 */
package com.learn.jasper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @auth Administrator
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "t_user")
public class User extends Model<User> {

    // 主键
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    private String name;

    private String password;

    public User() {
    }

    public User(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
