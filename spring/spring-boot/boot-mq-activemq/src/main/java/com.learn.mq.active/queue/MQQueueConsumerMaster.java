/**
 * @author G-bug 2019/10/16
 */
package com.learn.mq.active.queue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 消息监听者
 */
@Component
public class MQQueueConsumerMaster {

    // 监听指定的某个队列内的消息, 是否有新增
    @JmsListener(destination = "active.queue")
    public void receiveQueue(String text) {
        System.out.println("Master\t\t" + text);
    }

}
