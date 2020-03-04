/**
 * @author G-bug 2019/10/29
 */
package com.learn.swagger.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auth Administrator
 */
@ApiModel(value = "入网绑定返回信息", description = "入网绑定的返回对象")
public class JoinBindResponse<T> extends BaseResponse<T> {

    @ApiModelProperty(value = "返回码 1:已入网并绑定了网点 2:已入网尚未绑定网点 3:未入网", name = "code", example = "1", required = true)
    private int code;

    @ApiModelProperty(value = "机具状态 1:正常 2:故障 3:维修中 4:已禁用 5:已停用", name = "posState", example = "1", required = true)
    private Integer posState;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Integer getPosState() {
        return posState;
    }

    public void setPosState(Integer posState) {
        this.posState = posState;
    }
}
