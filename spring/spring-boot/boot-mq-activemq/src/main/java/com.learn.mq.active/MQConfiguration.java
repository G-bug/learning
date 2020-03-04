/**
 * @author G-bug 2019/10/16
 */
package com.learn.mq.active;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * 消息传送者
 * 即队列
 */
@Configuration
public class MQConfiguration {

    @Bean("mineQueue")
    public Queue queue() {
        return new ActiveMQQueue("active.queue");
    }

    @Bean("mineTopic")
    public Topic topic() {
        return new ActiveMQTopic("active.topic");
    }

}
