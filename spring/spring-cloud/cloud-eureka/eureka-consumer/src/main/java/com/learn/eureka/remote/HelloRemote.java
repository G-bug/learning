/**
 * @author G-bug 2019/11/6
 */
package com.learn.eureka.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "rdgdfgdgdgdgdeureka-producer") // 远程服务名 即:spring...name
public interface HelloRemote {

    @RequestMapping(value = "/hello")
    // 方法参数与远程调用的controller方法参数保持一致
    public String hello(@RequestParam("name") String name);

}
