package com.learn.immooc.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class JdbcRealmTest {

    DruidDataSource dataScore = new DruidDataSource();

    {
        dataScore.setUrl("jdbc:mysql://localhost:3306/shiro-test");
        dataScore.setUsername("root");
        dataScore.setPassword("123456");
    }

    @Test
    public void realmTest() {

        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataScore);
        jdbcRealm.setPermissionsLookupEnabled(true);
        jdbcRealm.setAuthenticationQuery("select password from shiro_users where name = ?");

        // 构建SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);

        // 主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        // 创建Subject
        Subject subject = SecurityUtils.getSubject();

        // 创建Token
        UsernamePasswordToken token = new UsernamePasswordToken("t", "123456");
        // Subject登录
        subject.login(token);

        System.out.println("认证是否通过:" + subject.isAuthenticated());

        /*subject.checkRole("admin");

        subject.checkPermission("user:delete");*/
    }

}
