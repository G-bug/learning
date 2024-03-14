### 参考
- [SpringCloud Sleuth](http://www.ityouknow.com/springcloud/2018/02/02/spring-cloud-sleuth-zipkin.html)
- [上篇的参考](http://daixiaoyu.com/distributed-tracing.html)

### 重点
- Sleuth 分布式系统调用跟踪(数据收集,数据存储,数据展示)
- Sleuth 可结合zipkin来存储及展示数据
    - Eureka 服务必须要用`8761`端口开启
    - `zipkin server`包在2.7.x后不推荐自定义`@EnableZipkinServer`(而是使用docker镜像来构建服务), 若使用会报错
    > `There is already an existing meter named 'http_server_requests_seconds' containing tag keys...参考`[github issue](https://github.com/openzipkin/zipkin/issues/2043) 
- 可通过注入`Tracer`Bean进行手动埋点
    ```java
    import org.springframework.cloud.sleuth.Span;
    import org.springframework.cloud.sleuth.Tracer;
    @Autowired
    private Tracer tracer;
      
    // ...
    // 创建一个 span
    final Span span = tracer.createSpan("3rd_service");
    try {
      span.tag(Span.SPAN_LOCAL_COMPONENT_TAG_NAME, "3rd_service");
      span.logEvent(Span.CLIENT_SEND);
      // 调用第三方 API 的代码...
    } finally {
        // 再次埋点,收集异常信息
    }
    ```