/**
 * @author G-bug 2019/11/8
 */
package com.learn.hystrix.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    @Autowired
    HttpServletRequest request;

    @RequestMapping("/hello")
    public String index(@RequestParam("name") String name) throws InterruptedException {

        LoggerFactory.getLogger(this.getClass()).info("请求");

        // 测试zuul retry功能
        // Thread.sleep(1000000);

        return "hello + " + name + request.getLocalPort();
    }
}

