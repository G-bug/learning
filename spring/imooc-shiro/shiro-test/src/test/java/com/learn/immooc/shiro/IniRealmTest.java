package com.learn.immooc.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class IniRealmTest {

    @Test
    public void realmTest() {

        IniRealm realm = new IniRealm("classpath:realm.ini");

        // 构建SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(realm);

        // 主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        // 创建Subject
        Subject subject = SecurityUtils.getSubject();

        // 创建Token
        UsernamePasswordToken token = new UsernamePasswordToken("M", "123456");
        // Subject登录
        subject.login(token);

        System.out.println("认证是否通过:" + subject.isAuthenticated());

        subject.checkRole("admin");

        subject.checkPermission("user:delete");
    }

}
