/**
 * @author G-bug 2019/9/25
 */
package com.learn.websocket.handle;

import com.fasterxml.jackson.core.type.TypeReference;
import com.learn.websocket.commons.JacksonUtil;
import com.learn.websocket.model.WsParam;
import com.learn.websocket.model.WsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @auth Administrator
 */
public class WsHandler extends TextWebSocketHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("handleTextMessage 开始");
        String msg = message.getPayload();
        logger.info("msg=" + msg);

        WsParam<String> wsParam = JacksonUtil.json2Bean(msg, new TypeReference<WsParam<String>>() {
        });

        if ("list".equals(wsParam.getMethod())) {
            logger.info("call list method...");
            WsResponse<String> response = new WsResponse();
            response.setResult("hello list");
            sendMessageToUser(session, new TextMessage(JacksonUtil.bean2Json(response)));
        }

        logger.info("handleTextMessage end");

        //sendMessagesToUsers(msg);
        //sendMessageToUser(userId, msg);

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("Connected ..." + session.getId());
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        sessions.remove(session);
        logger.info(String.format("session %s closed because of %s", session.getId(), status.getReason()));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        logger.error("error occured at sender" + session, throwable);
    }


    private void sendMessageToUser(WebSocketSession user, TextMessage message) {
        try {
            if (user.isOpen()) {
                user.sendMessage(message);
            }
        } catch (IOException e) {
            logger.error("发送指定用户出错");
        }
    }

    public void sendMessagesToUsers(TextMessage message) {
        for (WebSocketSession user : sessions) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
