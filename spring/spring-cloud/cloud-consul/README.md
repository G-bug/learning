### 参考
- [注册中心consul](http://www.ityouknow.com/springcloud/2018/07/20/spring-cloud-consul.html)

### 重点
- 功能同Eureka, 实现服务治理, 功能齐全不依赖其他工具
    - consul保证强一致性, 牺牲可用性, Eureka保证可用性牺牲一致性
    - consul是go语言写的, eureka跑在servlet容器中
