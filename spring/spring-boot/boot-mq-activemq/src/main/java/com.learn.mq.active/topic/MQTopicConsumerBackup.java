/**
 * @author G-bug 2019/10/16
 */
package com.learn.mq.active.topic;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @auth Administrator
 */
@Component
public class MQTopicConsumerBackup {

    @JmsListener(destination = "active.topic")
    public void receiveQueue(String text) {
        System.out.println("Backup\t\t" + text);
    }
}
