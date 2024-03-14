/**
 * @author G-bug 2019/5/21
 */
package com.start;

import com.test.service.impl.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @auth Administrator
 */
public class TestService {

    @Test
    public void test() {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        // 原始底层API
        ApiService service = context.getBean(ApiService.class);
        service.transform();

        // 模版
        TemplateService templateService = context.getBean(TemplateService.class);
        templateService.transform();

        ///////////////// 声明式 ////////////////////

        InterceptorService interceptorService = (InterceptorService) context.getBean("interceptorService");

        try {
            interceptorService.transform();
        } catch (ArithmeticException e) {
            System.out.println("");
        }

        ProxyService proxyService = (ProxyService) context.getBean("proxyService");

        try {
            proxyService.transform();
        } catch (ArithmeticException e) {
            System.out.println("");
        }

        TxService txService = (TxService) context.getBean("txService");

        try {
            txService.transform();
        } catch (ArithmeticException e) {
            System.out.println("");
        }

        try {
            txService.transformAn();
        } catch (ArithmeticException e) {
            System.out.println("");
        }

    }

}
