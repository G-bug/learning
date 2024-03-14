/**
 * @author G-bug 2019/11/8
 */
package com.learn.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @auth Administrator
 */
@SpringBootApplication
@EnableDiscoveryClient
public class HystrixProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixProducerApplication.class, args);
    }

}
