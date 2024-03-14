#### 参考:
- [参考项目](https://www.xncoding.com/2017/07/20/spring/sb-async.html)

#### 重点
- 实现`AsyncConfigurer`接口
    - 重写`getAsyncExecutor()`, 配置线程池执行器 常用`ThreadPoolTaskExectuor`
    - 重写`getAsyncUncaughtExceptionHandler`, 配置异常处理类
- `@EnableAsync` 开启异步支持
- `@Async` 开启方法异步执行
    - 调用和执行方法不能在同一个类,否则不起作用