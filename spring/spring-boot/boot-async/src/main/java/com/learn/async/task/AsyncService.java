/**
 * @author G-bug 2019/10/16
 */
package com.learn.async.task;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 调用异步方法的类 和 异步方法所在的类 在同一个类会发生失效
 * 要将异步方法单独放在任务bean中, 再注入Service
 */
@Component
public class AsyncService {

    @Resource
    private AsyncTask task;

    public void taskInvoke() {
        // ....
    }
}
