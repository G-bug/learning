/**
 * @author G-bug 2019/11/8
 */
package com.learn.hystrix.remote;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class HelloRemoteImpl implements HelloRemote {

    @Override
    public String index(@RequestParam("name") String name) {
        return "name failed";
    }

}
