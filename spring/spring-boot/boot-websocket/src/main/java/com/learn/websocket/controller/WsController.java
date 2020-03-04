/**
 * @author G-bug 2019/9/25
 */
package com.learn.websocket.controller;

import com.learn.websocket.model.RequestMessage;
import com.learn.websocket.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auth Administrator
 */
@Controller
public class WsController {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WsController(SimpMessagingTemplate message) {
        this.messagingTemplate = message;
    }

    @MessageMapping("/welcome") // 类似 @RequestMapping 响应 /welcome 请求
    @SendTo("/topic/say") // @SendTo->有消息推送时,对订阅了 /topic/say 的浏览器发送消息
    public ResponseMessage say(RequestMessage message) {
        System.out.println("msgName=" + message.getName());
        return new ResponseMessage("welcome, " + message.getName() + "!");
    }

    @Scheduled(fixedRate = 5000) // 定时消息
    public void callback() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //messagingTemplate.convertAndSend("/topic/callback", "定时" + df.format(new Date()));
    }

}
