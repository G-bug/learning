/**
 * @author G-bug 2019/10/16
 */
package com.learn.mq.active.queue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @auth Administrator
 */
@Component
public class MQQueueConsumerBackup {

    @JmsListener(destination = "active.queue")
    public void receiveQueue(String text) {
        System.out.println("Backup\t\t" + text);
    }

}
