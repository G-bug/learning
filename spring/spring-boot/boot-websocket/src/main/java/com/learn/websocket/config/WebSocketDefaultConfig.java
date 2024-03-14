/**
 * @author G-bug 2019/9/25
 */
package com.learn.websocket.config;

import com.learn.websocket.interceptor.WsHandshakeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

import com.learn.websocket.handle.WsHandler;

/**
 * @auth Administrator
 */
@Configuration
@EnableWebSocket
// 原始的websocket方式, 处理 ws:// 的连接
public class WebSocketDefaultConfig implements WebSocketConfigurer {

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WsHandler(), "/app") // 对 /app 的请求进行处理
                .addInterceptors(new WsHandshakeInterceptor()) // 握手拦截
                .setAllowedOrigins("*");
    }

}
