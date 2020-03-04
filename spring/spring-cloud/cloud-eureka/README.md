
### 参考
- [eureka集群](http://www.ityouknow.com/springcloud/2017/05/10/springcloud-eureka.html)
- [eureka远程调用](http://www.ityouknow.com/springcloud/2017/05/12/eureka-provider-constomer.html)

### 重点
- Eureka采用C/S架构, Eureka Server 服务注册中心, 其他微服务通过Eureka Client注册到Eureka Server并维持心跳
    - Eureka Server 90s没有收到心跳包则从服务列表清除, Eureka Client 30s发一次心跳包
- 集群 Eureka通过**相互注册, 数据同步**实现集群
    - [注册中心集群架构](https://www.infoq.cn/article/jlDJQ*3wtN2PcqTDyokh)
- `@EnableEurekaServer` 开启Eureka服务端功能
    - Eureka服务端本身也是个客户端,依此实现集群
- `@EnableDiscoveryClient`应用同时变为Eureka Client和Eureka Server实例
    - 在`eureka.instance`中进行配置
- `@EnableFeignClients`启用feign进行远程调用
- `@FeignClient`声明一个Feign客户端,相当是一个webservice客户端

### 问题
- `spring-cloud-starter-eureka-server`已过期,应改为`spring-cloud-starter-netflix-eureka-server`
- `spring-cloud-start-feign`过期,应改为`spring-cloud-starter-openfeign`
- `eureka-consumer`和`eureka-producer` 启动注册成功不没有错,但直接退出了
    - 依赖`spring-boot-starter-web` 或 创建一个用户线程(不需要tomcat容器的话)
    >项目启动注册完成后,又因为主线程结束,节点进行了注销