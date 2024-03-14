/**
 * @author G-bug 2019/9/26
 */
package com.learn.websocket.model;

/**
 * @auth Administrator
 */
public class WsResponse<T> {

    private T result;

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }

}
