/**
 * @author G-bug 2019/9/25
 */
package com.learn.websocket.model;

import java.io.File;

/**
 * @auth Administrator
 */
public class ResponseMessage {

    private String responseMessage;

    public ResponseMessage(String message) {
        this.responseMessage = message;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }

}
