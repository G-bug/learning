/**
 * @author G-bug 2019/5/21
 */
package com.test.service.impl;

import com.test.dao.impl.AccountDaoImpl;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @auth Administrator
 */

public class TemplateService {

    private AccountDaoImpl accountDaoImpl;
    private TransactionTemplate transactionTemplate;

    public void setAccountDaoImpl(AccountDaoImpl accountDaoImpl) {
        this.accountDaoImpl = accountDaoImpl;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public void transform() {
        /*
        * 根据默认规则，如果在执行回调方法的过程中抛出了未检查异常，或者显式调用了setRollbackOnly() 方法，
        * 则回滚事务；如果事务执行完成或者抛出了 checked 类型的异常，则提交事务
        */
        transactionTemplate.execute((t) -> {
            try {
                accountDaoImpl.addAmount("xh", 20);
                int a = 10 / 0;
                accountDaoImpl.addAmount("xm", -20);

                return null;
            } catch (Exception e) {
                t.setRollbackOnly();
                System.out.println("transaction error");
                return null;
            }
        });
    }
}
