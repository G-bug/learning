### 参考
- [网关组件](http://www.ityouknow.com/springcloud/2018/12/12/spring-cloud-gateway-start.html)
- [官网文档](https://cloud.spring.io/spring-cloud-gateway/reference/html/)

### 重点
- GateWay是SpringCloud自己的网关组件, 使用非阻塞IO, 支持WebSockets, 基于Spring5, SpringBoot2.0等
- 是利用Jetty和Webflux实现, 不用加入web模块
- 概念
    - Route(路由), 由ID,目标URI, 一组断言和过渡器定义, 断言为真则路由匹配.
    - Predicate(断言), Java8的Predicate, 输入类型是ServerWebExchange, 可匹配HTTP请求中的任何内容
    - Filter, GateWayFilter的实例
- 特征
    - 集成Hystrix
    - 集成SpringCloudDiscoveryClient
    - 动态路由
    - 限流
    - 路径重写(RewritePath)
- GateWay注册到服务中心后, 网关会自动代理所有注册中心的服务
- GateWay Filter的生命周期只有`pre`和`post`
    - GlobalFilter 会应用到所有路由上
    - GateWayFilter 应用到单一路由上
 