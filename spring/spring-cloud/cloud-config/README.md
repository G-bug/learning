### 参考
- [cloud配置中心](http://www.ityouknow.com/springcloud/2017/05/23/springcloud-config-svn-refresh.html)

### 重点
- **配置中心**是集中管理各类服务等的配置文件的, **客户端**通过请求获取**服务端**加载的配置
- Spring Cloud Config 服务端负责将配置发布为REST接口,以供客户端(ConfigClient)访问
- Config Client通过POST触发各自的`/refresh`
    - 利用Actuator来进行`/refresh`监控
    - `@RefreshScope`表明在客户端`/refresh`时会更新 标注类 下的变量值
    
- 配置中心的**服务化和高可用**是通过,将server端 在eureka中 注册 供client来 调用实现的

- 总线(spring cloud bus)的本质是利用MQ的广播机制实现在分布式系统中消息传播
- 解决 多Config Client 自动刷新的场景
    1. Client增加Bus,任一个Client刷新, 其他客户端接收Bus消息,请求Server实现自动刷新
    2. Server端增加Bus, Server负责与Bus通信, 再利用bus完成所有客户端的刷新
- 灰度更新某客户端: `/bus/refresh?destination=[application.name]:[server.port]`
    >测试未成功
- bus跟踪`/httptrace`(Spring Boot 2.*)