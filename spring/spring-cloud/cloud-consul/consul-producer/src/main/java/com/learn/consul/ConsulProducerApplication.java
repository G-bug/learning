/**
 * @author G-bug 2019/12/2
 */
package com.learn.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @auth Administrator
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulProducerApplication.class, args);
    }

}
