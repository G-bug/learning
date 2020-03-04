/**
 * @author G-bug 2019/10/16
 */
package com.learn.mq.active;

import com.learn.mq.active.sample.JmsConsumer;
import com.learn.mq.active.sample.JmsProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @auth Administrator
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        JmsProducer.run();
        JmsConsumer.run();

        SpringApplication.run(Application.class, args);
    }

}
