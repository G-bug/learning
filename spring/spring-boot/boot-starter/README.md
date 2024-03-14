### 重点

- 增加spring.factories文件,设置配置类

1. spring boot 启动时扫描项目所依赖的jar包, 寻找包含 spring.factories 文件的jar包
2. 根据 spring.factories 配置加载AutoConfigure类
3. 根据@Conditional注解的条件, 进行自动配置将Bean 注入 spring context

- 创建完成后 `mvn install`,其他项目引用即可

### 参考
[参考项目](https://www.xncoding.com/2017/07/22/spring/sb-starter.html)