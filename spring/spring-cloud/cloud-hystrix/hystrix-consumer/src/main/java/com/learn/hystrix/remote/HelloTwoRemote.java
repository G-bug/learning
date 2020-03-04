/**
 * @author G-bug 2019/11/8
 */
package com.learn.hystrix.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "hystrix-producer", contextId = "id2")
public interface HelloTwoRemote {

    @RequestMapping("/hello")
    public String index(@RequestParam("name") String name);

}
