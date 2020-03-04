/**
 * @author G-bug 2019/11/13
 */
package com.learn.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope // 表明 接到配置中心刷新时, 自动更新类中对应的字段
public class HelloController {

    @Value("${learn.hello}")
    private String hello;

    @GetMapping("/hello")
    public String getHello() {
        return this.hello;
    }

}
