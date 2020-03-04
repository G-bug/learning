/**
 * @author G-bug 2019/9/27
 */
package com.learn.websocket.interceptor;


import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * @auth Administrator
 */
@Component
// 通道拦截
public class WsChannelInterceptor implements ChannelInterceptor {

    // 接收消息
    @Override
    public boolean preReceive(MessageChannel channel) {
        System.out.println("preReceive 接收消息");
        return true;
    }

    @Override
    // 发消息
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        System.out.println("preSend 发送消息");

        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();

        if (StompCommand.SUBSCRIBE.equals(command)) {
            // 订阅一个地址发一次
            System.out.println("订阅的目的地" + accessor.getDestination());
            return message;
        }

        return message;
    }

    @Override
    // 发送之后
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        SimpMessagingTemplate messageTemplate = new SimpMessagingTemplate(channel);
        System.out.println("afterSendCompletion 发送之后");

        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();

        if (StompCommand.SUBSCRIBE.equals(command)) {
            System.out.println("订阅已发送");
        }

        if (StompCommand.DISCONNECT.equals(command)) {
            System.out.println("已断开");
            messageTemplate.convertAndSend("/topic/getResponse", "{'msg':'断开成功'}");
        }
    }

}
