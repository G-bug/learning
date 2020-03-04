/**
 * @author G-bug 2019/12/2
 */
package com.learn.consul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancedRetryFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @auth Administrator
 */
@RestController
public class HelloController {

    @Autowired
    HttpServletRequest request;

    @RequestMapping("/hello")
    public String hello(String foo) {
        return "hello world " + foo + "," + request.getLocalPort();
    }

    @RequestMapping("/retry")
    public ResponseEntity retry(String foo) {
        System.out.println("收到请求");
        return new ResponseEntity(HttpStatus.GATEWAY_TIMEOUT);
    }

    @RequestMapping("/hystrix")
    public String hystrix() throws InterruptedException {
        Thread.sleep(100000);
        return "null";
    }

    @RequestMapping("/rate")
    public String rate() {
        return "null";
    }

}
