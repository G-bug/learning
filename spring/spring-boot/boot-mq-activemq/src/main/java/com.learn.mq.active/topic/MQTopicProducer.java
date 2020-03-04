/**
 * @author G-bug 2019/10/16
 */
package com.learn.mq.active.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Topic;
import java.util.Date;

/**
 * 消息生产者
 */
@Component
// 开启定时器
@EnableScheduling
public class MQTopicProducer {

    @Autowired
    private JmsMessagingTemplate template;

    @Autowired
    private Topic mineTopic;

    // 定时器
    @Scheduled(fixedDelay = 5000)
    public void send() {
        this.template.convertAndSend(this.mineTopic, "this is mineTopic activeMQ!\t" + new Date());
    }
}
