package com.learn.immooc.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * shiro 授权
 */
public class AuthenticationTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser() {
        simpleAccountRealm.addAccount("Bob", "123456", "admin");
    }

    @Test
    public void testAuthentication() {

        // 构建SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        // 设置Realm
        defaultSecurityManager.setRealm(simpleAccountRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        // 通过SecurityUtils获取主体
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("Bob", "123456");
        subject.login(token);

        System.out.println("认证登录:" + subject.isAuthenticated());

        subject.checkRole("admin");

        subject.logout();
    }

}
