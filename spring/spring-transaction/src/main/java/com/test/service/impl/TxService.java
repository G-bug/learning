/**
 * @author G-bug 2019/5/21
 */
package com.test.service.impl;

import com.test.dao.impl.AccountDaoImpl;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auth Administrator
 */

public class TxService {

    private AccountDaoImpl accountDaoImpl;


    public void setAccountDaoImpl(AccountDaoImpl accountDaoImpl) {
        this.accountDaoImpl = accountDaoImpl;
    }

    public void transform() {
        accountDaoImpl.addAmount("xm", 20);
        int a = 10 / 0;
        accountDaoImpl.addAmount("xh", -20);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void transformAn() {
        accountDaoImpl.addAmount("xm", 20);
        int a = 10 / 0;
        accountDaoImpl.addAmount("xh", -20);
    }
}
