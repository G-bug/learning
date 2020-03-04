/**
 * @author G-bug 2019/10/21
 */
package com.learn.mq.receiver;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 消息监听器
 * 监听队列消息并做相应处理, 并通过  Ack机制确认处理(basicAck方法)  完成
 */
@Component
public class Receiver {

    private static final Logger log = LoggerFactory.getLogger(Receiver.class);

    // fanout 广播队列监听
    @RabbitListener(queues = {"FANOUT_QUEUE_A"})
    public void on(Message message, Channel channel) throws IOException {
        // 消息确认
        // 第一个参数是每个消息在channel上的唯一tag
        // 第二个参数是批量确认, true则该tag之前的全部消息进行确认
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        log.debug("FANOUT_QUEUE_A" + new String(message.getBody()));
    }

    @RabbitListener(queues = {"FANOUT_QUEUE_B"})
    public void on1(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        log.debug("FANOUT_QUEUE_B" + new String(message.getBody()));
    }

    // direct 模式监听
    @RabbitListener(queues = {"DIRECT_QUEUE"})
    public void on2(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        log.debug("DIRECT" + new String(message.getBody()));
    }
}
