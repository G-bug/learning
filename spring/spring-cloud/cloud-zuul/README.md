### 参考
- [GateWay Zuul](http://www.ityouknow.com/springcloud/2017/06/01/gateway-service-zuul.html)

### 重点
- Zuul中的`zuul.routes.***`后为自定义
- 通过Eureka配合`service-id`实现服务化
- Zuul会代理所有注册的服务,路由规则为
    > `http://zuulclienthost:port/eureka-service-id/**`
- Zuul Filter是其核心, 实现对外服务的控制, 可结合shiro/oauth2.0等做鉴权/验证, 
- filter分四个生命周期
    - PRE, 请求被路由之前
    - ROUTING, 构建发给微服务的请求
    - POST, 可为响应添加HTTP Header,将响应从微服务发到客户端
    - ERROR, 其他阶段出现错误执行该过渡器
- Zuul 可提供路由熔断服务
    - 实现Spring重新实现的`FallbackProvider`
    - 只支持到服务级别的熔断, 不支持某个URL
- Zuul 可提供重试功能
    - 开启retry
    >同时使用熔断和重试是有矛盾的, 重试会导致可能将多个请求堆积到一个实例, 此时熔断便没有了意义
    可以接受短时间的熔断则可以不用重试
- 在Zuul前端配合Nginx实现其高可用