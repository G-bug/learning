/**
 * @author G-bug 2019/12/5
 */
package com.gugpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author G-bug
 * @Description consul 服务提供者
 * @Date 2019/12/6 14:53
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

}
