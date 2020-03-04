/**
 * @author G-bug 2019/10/22
 */
package com.learn.rabbitmq;

import com.learn.mq.Application;
import com.learn.mq.service.SenderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @auth Administrator
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RabbitMQTest {

    @Autowired
    private SenderService senderService;

    @Test
    public void testMQ() {
        // fanout
        senderService.broadcast("同学集合了!");
        // direct
        senderService.direct("定点消息!");
    }

}
