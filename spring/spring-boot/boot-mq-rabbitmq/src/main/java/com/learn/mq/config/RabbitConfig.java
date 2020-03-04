/**
 * @author G-bug 2019/10/21
 */
package com.learn.mq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 重要
 * 交换机及消息绑定
 */
@Configuration
public class RabbitConfig {

    @Resource
    private RabbitTemplate rabbitTemplate;


    @Bean
    // 定制amqp模板  可多个
    public AmqpTemplate amqpTemplate() {

        Logger log = LoggerFactory.getLogger(RabbitTemplate.class);
        // 使用jackson消息转换器
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setEncoding("UTF-8");
        rabbitTemplate.setMandatory(true);

        // ReturnCallback接口 实现
        // 消息发送到RabbitMQ交换器,但无相应子任务队列与交换器绑定时的回调(即消息发送不到任何一个队列中ack)
        // 消息发送失败返回队列 配合yml的 publisher-returns: true
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            log.debug("消息:{}发送失败, 应答码: {} 原因:{}交换机:{}路由键:{}", correlationId, replyCode, replyText, exchange, routingKey);
        });

        // ConfirmCallback接口 实现消息发送到RabbitMQ 交换器后接收ack回调,即消息发送到exchange ack
        // 消息确认, 配合yml的publisher-confirms:true
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.debug("消息发送到exchange成功,id:{}", correlationData.getId());
            } else {
                log.debug("消息发送到exchange失败,原因:{}", cause);
            }
        });

        return rabbitTemplate;
    }

    //-------------- Direct exchange

    // 持久化, 为保证RabbitMQ重启消息也不丢失

    // 声明 direct 交换机, 支持持久化
    @Bean("directExchange")
    public Exchange directExchange() {
        return ExchangeBuilder.directExchange("DIRECT_EXCHANGE").durable(true).build();
    }

    // 声明 支持持久化的队列
    @Bean("directQueue")
    public Queue directQueue() {
        return QueueBuilder.durable("DIRECT_QUEUE").build();
    }

    // 通过绑定键 将 指定队列 绑定到 指定 交换机
    @Bean
    public Binding directBinding(@Qualifier("directQueue") Queue queue, @Qualifier("directExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("DIRECT_ROUTING_KEY").noargs();
    }

    //------------- Fanout Exchange

    // 声明 可持久的fanout(广播模式) exchange
    @Bean("fanoutExchange")
    public FanoutExchange fanoutExchange() {
        return (FanoutExchange) ExchangeBuilder.fanoutExchange("FANOUT_EXCHANGE").durable(true).build();
    }

    // 声明 可持久的fanout队列
    @Bean("fanoutQueueA")
    public Queue fanoutQueueA() {
        return QueueBuilder.durable("FANOUT_QUEUE_A").build();
    }

    @Bean("fanoutQueueB")
    public Queue fanoutQueueB() {
        return QueueBuilder.durable("FANOUT_QUEUE_B").build();
    }

    // 交换机和队列绑定
    @Bean
    public Binding bindingA(@Qualifier("fanoutQueueA") Queue queue, @Qualifier("fanoutExchange") FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean
    public Binding bindingB(@Qualifier("fanoutQueueB") Queue queue, @Qualifier("fanoutExchange") FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

}
