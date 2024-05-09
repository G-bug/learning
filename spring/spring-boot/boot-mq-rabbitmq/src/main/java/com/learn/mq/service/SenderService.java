/**
 * @author G-bug 2019/10/21
 */
package com.learn.mq.service;

import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 消息发送
 * 向交换机发送消息
 */
@Service
public class SenderService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    // fanout(广播) 模式
    public void broadcast(String p) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("FANOUT_EXCHANGE", "", p, correlationData);
    }

    // direct模式
    public void direct(String p) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("DIRECT_EXCHANGE", "DIRECT_ROUTING_KEY", p, correlationData);
    }

    public void broadcastAndPersistent(String p) {
        rabbitTemplate.convertAndSend("FANOUT_EXCHANGE", "", p, mes -> {
            // 消息持久化 设置
            mes.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            return mes;
        });
    }
}
