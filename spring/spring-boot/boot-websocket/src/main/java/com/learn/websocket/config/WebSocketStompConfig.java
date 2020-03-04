/**
 * @author G-bug 2019/9/25
 */
package com.learn.websocket.config;

import com.learn.websocket.interceptor.WsChannelInterceptor;
import com.learn.websocket.interceptor.WsHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @auth Administrator
 */
@Configuration
@EnableWebSocketMessageBroker // Broker代理方式 开启使用stomp
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {

    // 注册 STOMP 协议的节点, 映射的URL地址
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stomp) {
        stomp.addEndpoint("/simple")
                .setAllowedOrigins("*")
                .addInterceptors(new WsHandshakeInterceptor())
                .withSockJS(); // 指定使用SockJS
    }

    // 配置消息代理
    @Override
    public void configureMessageBroker(MessageBrokerRegistry reg) {
        // 实时推送的代理是 /topic
        reg.enableSimpleBroker("/topic");
    }

    // 设置通道拦截
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new WsChannelInterceptor());
    }

}
