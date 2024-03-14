/**
 * @author G-bug 2019/12/3
 */
package com.learn.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auth Administrator
 */
@RestController
public class FallbackController {

    @RequestMapping("/fallback")
    public String forward() {
        return "你垃圾, 被熔断";
    }

}
