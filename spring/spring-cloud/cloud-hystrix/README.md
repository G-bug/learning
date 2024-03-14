### 参考
- [Hystrix熔断器](http://www.ityouknow.com/springcloud/2017/05/16/springcloud-hystrix.html)

### 重点
- 熔断器(CircuitBreaker)作用于服务消费(调用)端
- 断路器机制
    > Hystrix Command请求服务失败数量超过一定数量(默认50%), 断路器切换为OPEN(开路), 所有请求直接失败不会发送出去
    该状态维持一定时间(默认5秒),自动切换到半开路状态, 
    等待并判断下次请求返回情况, 成功则切回CLOSED(闭路), 否则切回OPEN
- fallback
    > 请求失败时的返回值, 默认值或缓存中的值
- 资源隔离
    > 在Hystrix中可以利用线程池来将不同服务隔离, 同时会带来性能有所下降
- Turbine
    >Hystrix Dashboard只能展示单个服务的应用,
    汇总系统内的全部服务就要用到Turbine