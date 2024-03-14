/**
 * @author G-bug 2019/9/29
 */
package com.learn.restful.model;

/**
 * @auth Administrator
 */
public class BaseResponse<T> {

    private boolean success;

    private String msg;

    private T data;

    public BaseResponse() {
    }

    public BaseResponse(Boolean success, String msg, T data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
