/**
 * @author G-bug 2019/10/15
 */
package com.learn.async.task;

import com.learn.async.config.AsyncConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @auth Administrator
 */
@Component
public class AsyncTask {
    private static final Logger logger = LoggerFactory.getLogger(AsyncTask.class);

    @Async
    // 该注解表示会启动新线程去执行
    public void dealNoReturnTask() {
        logger.info("返回值为void的异步调用开始" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("void 异步结束" + Thread.currentThread().getName());
        // 自定义Exception
        throw new AsyncConfig().new AsyncException("customer exception");
    }

    @Async
    public Future<String> dealHaveReturnTask(int i) {
        logger.info("asyncInvokeReturnFuture, param=" + i);
        Future<String> future;
        try {
            Thread.sleep(1000 * i);
            future = new AsyncResult<>("success:" + i);
        } catch(InterruptedException e) {
            future = new AsyncResult<>("error");
        }
        return future;
    }
}
