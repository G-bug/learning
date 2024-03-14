/**
 * @author G-bug 2019/10/15
 */
package com.learn.async.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Executor;

/**
 * @auth Administrator
 */
@Configuration
//① 如下开启异步支持 + 实现自己的线程池
//② 用该注解修饰Application启动类 + 使用默认线程池
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(100);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60 * 10);
        executor.setThreadNamePrefix("AsyncThread-");
        executor.initialize(); // 不进行初始化, 报找不到执行器错误
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncExceptionHandler();
    }

    public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
        private final Logger log = LoggerFactory.getLogger(AsyncExceptionHandler.class);

        @Override
        public void handleUncaughtException(Throwable ex, Method method, Object... params) {
            log.error("Async aught exception: method[" + method.getName() + "],params" + Arrays.toString(params));
            if (ex instanceof AsyncException) {
                AsyncException asyncException = (AsyncException) ex;
                log.error("asyncException:" + asyncException.getMessage());
            }
            log.error("Exception:", ex);
        }
    }

    public class AsyncException extends RuntimeException {
        public AsyncException(String msg) {
            super(msg);
        }
    }
}




