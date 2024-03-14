/**
 * @author G-bug 2019/5/21
 */
package com.test.dao.impl;

import com.test.dao.AccountDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @auth Administrator
 */
public class AccountDaoImpl implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public void addAmount(String src, int amount) {
        String sql = "update account set amount = amount + ? where name = ?";
        jdbcTemplate.update(sql, amount, src);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
