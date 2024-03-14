### 参考
- [xxpay项目](https://github.com/jmdhappy/xxpay-master)
- [PageHelper分页](https://pagehelper.github.io/docs/howtouse/)
- [Mybatis-3](https://mybatis.org/mybatis-3/zh/configuration.html)
- [mybatis-generator](https://mybatis.org/generator/configreference)

### 计划
 ~~0. 配置文件迁移到 `gugpay-config`~~
 1. 针对回调处理重新设计
 2. swagger
 3. 日志系统
 4. 加入vue-element-admin
 
 
### 编码过程
- 顺序
    1. gugpay-common
        > 基础包, 工具包
    2. gugpay-config
        > cloud 配置中心
    3. gugpay-service
        > Consul 服务提供者
    4. gugpay-dal
        > 持久层
    5. gugpay-consumer
        > 加入Hystrix(熔断), Feign(远程调用)
        > Consul 服务消费者
    6. gugpay-gateway(与consumer hystrix部分 功能重复)
        > 使用spring-cloud-gateway

### 知识点
- @ConfigurationProperties
    > 从配置文件中得到数据
- @Bean(initMethod="init", destroyMethod="close")
    > Bean 在init/destroy时附加执行的Bean的方法
- 若没有显示的定义`private ... serialVersionUID=xxxL;`
    > Java序列化会根据class文件自动生成serialVersionUID, 若class文件没有改变, 该值不变.
    想实现序列化接口的实体能兼容之前版本, 则自定义该变量 
- @FeignClient类中
    > 请求的参数`@RequestParam`必须添加`value="xx"`, 
    否则启动报错`RequestParam.value() was empty on parameter 0`
- `@FeignClient`与`name(服务提供者)`一一对应,不能重复

### 需个别配置
1. `gugpay-dal`下`datasource`
2. `gugpay-config`下`search-locations`
    > 若找不到则会出现service在rabbitmq中找不到对应正确的queue
3. 各`consul`,`rabbitmq`,`redis`