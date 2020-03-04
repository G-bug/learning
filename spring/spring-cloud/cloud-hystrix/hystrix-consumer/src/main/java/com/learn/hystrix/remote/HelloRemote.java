/**
 * @author G-bug 2019/11/8
 */
package com.learn.hystrix.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// contextId 同一个应用中调用同一个服务("hystrix-producer"), 必须加该参数
// 如果发生熔断, 返回fallback的类中方法内容
@FeignClient(name = "hystrix-producer", fallback = HelloRemoteImpl.class, contextId = "id1")
public interface HelloRemote {

    @RequestMapping("/hello")
    public String index(@RequestParam("name") String name);

}
