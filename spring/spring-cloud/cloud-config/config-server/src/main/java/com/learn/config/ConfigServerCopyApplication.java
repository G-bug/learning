/**
 * @author G-bug 2019/11/12
 */
package com.learn.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
/*
* 配合port 开启多个服务提供者
*/
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigServerCopyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerCopyApplication.class, args);
    }

}
