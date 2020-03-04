/**
 * @author G-bug 2019/9/24
 */
package com.learn.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @auth Administrator
 */
@Component
@Aspect
public class LogAspect {

    @Pointcut("execution(public * com.learn.aop.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        System.out.print("前置");
        System.out.println("URL:" + request.getRequestURL().toString());
        System.out.println("HTTP_METHOD:" + request.getMethod());
        System.out.println("IP:" + request.getRemoteAddr());
        System.out.println("CLASS_METHOD:" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        System.out.println("ARGS:" + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        System.out.println("正常退出:" + ret);
    }

    @AfterThrowing( throwing = "e", pointcut =  "webLog()")
    public void throwsing(JoinPoint point, Exception e) {
        System.out.println("异常执行");
    }

    // final增强, 异常或正常 均会执行
    @After("webLog()")
    public void after(JoinPoint point) {
        System.out.println("后置,异常或正常");
    }

    // 相当于MethodInterceptor
    @Around("webLog()")
    public Object arounding(ProceedingJoinPoint point) {

        System.out.println("环绕");

        try {
            // 执行被切方法
            Object o = point.proceed();
            System.out.println("方法返回:" + o);
            return o;
        } catch(Throwable e) {
            e.printStackTrace();
            return 3;
        }

    }
}

