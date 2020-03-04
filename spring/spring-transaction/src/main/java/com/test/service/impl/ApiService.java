/**
 * @author G-bug 2019/5/21
 */
package com.test.service.impl;

import com.test.dao.impl.AccountDaoImpl;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @auth Administrator
 */

public class ApiService {

    private AccountDaoImpl accountDaoImpl;
    private TransactionDefinition txDefinition;
    private DataSourceTransactionManager txManager;

    public void setAccountDaoImpl(AccountDaoImpl accountDaoImpl) {
        this.accountDaoImpl = accountDaoImpl;
    }

    public void setTxDefinition(DefaultTransactionDefinition txDefinition) {
        this.txDefinition = txDefinition;
    }

    public void setTransactionManager(DataSourceTransactionManager transactionManager) {
        this.txManager = transactionManager;
    }

    public void transform() {
        // 启动事务
        TransactionStatus txStatus = txManager.getTransaction(txDefinition);
        try {
            accountDaoImpl.addAmount("xm", -20);

            int a = 10 / 0;

            accountDaoImpl.addAmount("xh", 20);
            // 提交
            txManager.commit(txStatus);
        } catch (Exception e) {
            // 回滚
            txManager.rollback(txStatus);
            System.out.println("transaction error");
        }
    }



}
