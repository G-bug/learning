/**
 * @author G-bug 2019/10/29
 */
package com.learn.swagger.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auth Administrator
 */
// 描述一个API model
@ApiModel(value = "BaseResponse", description = "API接口的返回对象")
public class BaseResponse<T> {

    @ApiModelProperty(value = "是否成功", name = "success", example = "true", required = true)
    private boolean success;

    @ApiModelProperty(value = "返回的详细说明", name = "msg", example = "成功")
    private String msg;

    @ApiModelProperty(value = "返回的数据", name = "data")
    private T data;

    public BaseResponse() {

    }

    public BaseResponse(boolean success, String msg, T data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
