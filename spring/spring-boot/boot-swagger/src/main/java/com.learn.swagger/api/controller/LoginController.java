/**
 * @author G-bug 2019/10/30
 */
package com.learn.swagger.api.controller;

import com.learn.swagger.api.model.BaseResponse;
import com.learn.swagger.api.model.LoginParam;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @auth Administrator
 */
@Api(value = "登录请求接口类", tags = "登录", description = "用户请求登录获取token")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "请求已完成"),
        @ApiResponse(code = 201, message = "资源成功被创建"),
        @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
        @ApiResponse(code = 401, message = "未授权客户机访问数据"),
        @ApiResponse(code = 403, message = "服务器接受请求，但是拒绝处理"),
        @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
        @ApiResponse(code = 500, message = "服务器出现异常")
})
@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @ApiOperation(value = "登录认证接口", notes = "接口或websocket连接均先到此获取token", produces = "application/json")
    @PostMapping("/login")
    public BaseResponse<String> login(@RequestHeader(name = "Content-Type", defaultValue = "application/json") String contentType, @ApiParam(value = "登录参数") @RequestBody LoginParam loginParam) {
        logger.info("用户请求登录获取Token");
        return new BaseResponse<>(true, "Login success", "JWT");
    }

    @ApiOperation(value = "推送消息登录认证接口", notes = "加密后的密码", produces = "application/json")
    @PostMapping("/notifyLogin")
    public BaseResponse<String> notifyLogin(@RequestHeader(name = "Content-Type", defaultValue = "application/json") String contentType, @ApiParam(value = "登录参数") @RequestBody LoginParam loginParam) {
        logger.info("登录用户推送请求登录获取Token");
        return new BaseResponse<>(true, "Login success", "JWT");
    }

    @GetMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ApiIgnore
    public BaseResponse unauthorized() {
        return new BaseResponse<>(false, "Unauthorized", null);
    }
}
