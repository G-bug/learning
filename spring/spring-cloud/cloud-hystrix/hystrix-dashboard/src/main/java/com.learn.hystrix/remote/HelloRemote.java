/**
 * @author G-bug 2019/11/8
 */
package com.learn.hystrix.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 如果发生熔断, 返回fallback的类中方法内容
@FeignClient(name = "hystrix-producer", fallback = HelloRemoteImpl.class)
public interface HelloRemote {

    @RequestMapping("/hello")
    public String index(@RequestParam("name") String name);

}
