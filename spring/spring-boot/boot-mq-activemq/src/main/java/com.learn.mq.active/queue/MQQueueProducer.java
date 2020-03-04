/**
 * @author G-bug 2019/10/16
 */
package com.learn.mq.active.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.Date;

/**
 * @auth Administrator
 */
@Component
public class MQQueueProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue mineQueue;

    @Scheduled(fixedDelay = 7000)
    public void send() {
        this.jmsMessagingTemplate.convertAndSend(this.mineQueue, "this is mineQueue activeMQ!\t" + new Date());
    }

}
