/**
 * @author G-bug 2019/9/26
 */
package com.learn.websocket.model;

/**
 * @auth Administrator
 */
public class WsParam<T> {

    private String method;
    private T param;

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public T getParam() {
        return this.param;
    }

    public void setParam(T param) {
        this.param = param;
    }

}
