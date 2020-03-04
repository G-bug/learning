/**
 * @author G-bug 2019/11/8
 */
package com.learn.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @auth Administrator
 */
@SpringBootApplication
@EnableEurekaServer
public class HystrixEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixEurekaApplication.class, args);
    }

}
