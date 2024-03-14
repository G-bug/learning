/**
 * @author G-bug 2019/12/25
 */
package com.gugpay.service.config;

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
 * @author G-bug
 * @Description Rabbit Mq配置
 * @Date 2019/12/25 11:43
 */
@Configuration
public class RabbitMqConfig {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Bean
    public AmqpTemplate amqpTemplate() {

        Logger log = LoggerFactory.getLogger(this.getClass());

        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setEncoding("UTF-8");
        rabbitTemplate.setMandatory(true);

        // 发送到交换器回调
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("发送到exchange成功,id:{}", correlationData.getId());
            } else {
                log.info("发送到exchange失败,原因:{}", cause);
            }
        });

        // 发送到交换器, 但无子任务与交换器绑定
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            log.info("消息{}发送失败,应答码:{}原因:{}交换机:{}路由键:{}", correlationId, replyCode, replyText, exchange, routingKey);
        });

        return rabbitTemplate;
    }

    /**
     * direct exchange
     */

    @Bean("directExchange")
    public Exchange directExchange() {
        return ExchangeBuilder.directExchange("DIRECT_EXCHANGE").durable(true).build();
    }

    @Bean("payOrderQueue")
    public Queue payOrderQueue() {
        // 可持久化队列
        return QueueBuilder.durable("PAY_ORDER_QUEUE").build();
    }

    @Bean
    public Binding directBinding(@Qualifier("payOrderQueue") Queue queue, @Qualifier("directExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("PAY_ORDER_KEY").noargs();
    }

}
