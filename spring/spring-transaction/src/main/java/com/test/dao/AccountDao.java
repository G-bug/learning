/**
 * @author G-bug 2019/5/21
 */
package com.test.dao;

import org.springframework.stereotype.Component;

/**
 * @auth Administrator
 */
public interface AccountDao {

    void addAmount(String src, int amount);
}
