/**
 * @author G-bug 2019/5/21
 */
package com.test.service.impl;

import com.test.dao.impl.AccountDaoImpl;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.File;

/**
 * @auth Administrator
 */

public class InterceptorService {

    private AccountDaoImpl accountDaoImpl;

    public void setAccountDaoImpl(AccountDaoImpl accountDaoImpl) {
        this.accountDaoImpl = accountDaoImpl;
    }

    public void transform() throws ArithmeticException {
        accountDaoImpl.addAmount("xm", 20);
        int a = 10 / 0;
        accountDaoImpl.addAmount("xh", -20);
    }
}
