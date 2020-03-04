/**
 * @author G-bug 2019/9/24
 */
package com.learn.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @auth Administrator
 * CommandLineRunner
 * */
@Component
// order 越小优先级越高
@Order(1)
// 实现 CommandLineRunner 接口,启动服务前预先加载的内容
public class AfterStarterRunner implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void run(String... args) {
        logger.debug("log记录debug");
        logger.info("log记录info");
    }
}
