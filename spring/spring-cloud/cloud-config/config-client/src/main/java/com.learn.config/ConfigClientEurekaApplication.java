/**
 * @author G-bug 2019/11/12
 */
package com.learn.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/*
* bus-refresh测试多开客户端
*/
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigClientEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientEurekaApplication.class, args);
    }

}
