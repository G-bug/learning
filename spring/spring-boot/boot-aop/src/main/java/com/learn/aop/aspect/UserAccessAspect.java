/**
 * @author G-bug 2019/9/24
 */
package com.learn.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @auth Administrator
 */
@Component
@Aspect
public class UserAccessAspect {

    @Pointcut(value = "@annotation(com.learn.aop.aspect.UserAccess)")
    public void access() {

    }

    @Before("access()")
    public void deBefore(JoinPoint point) throws Throwable {
        System.out.println("前置");
    }

    @Around("@annotation(userAccess)")
    public Object aroundd(ProceedingJoinPoint point, UserAccess userAccess) {
        System.out.println("环绕" + userAccess.desc());
        try {
            return point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
