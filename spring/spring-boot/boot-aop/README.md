### 参考
- [参考项目](https://www.xncoding.com/2017/07/24/spring/sb-aop.html)
- [Advice执行详解](https://juejin.im/post/5da96083e51d4524ba0fd228)
- [PointCut表达式Spring教程](https://docs.spring.io/spring/docs/2.5.x/reference/aop.html#aop-pointcuts-designators)

### 重点
- 创建切面
    - `@Aspect`标记类为切面类
    - `@Point`定义切点方法
        - execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern)throws-pattern?)
        - `within()`某个类(或implements)下的方法,可正则匹配
        - `this()`某个类(或implements)下的方法,必须全名
        - `args()`具有 某个参数的 方法
        - `target()`某个类下的方法
        - `@target()`被 某个注解 修饰 的类 的所有方法
        - `@within()`被 某个注解 修饰 的声明类型的对象的方法
        - `@args()`有 被某个注解修饰的参数 的方法
        - `@annotation()`被 某个注解修饰的 方法
        - `bean()`Spring中的有某个name的bean
- 增强类型
    - `@Before`前置
    - `@After`后置(final增强)
    - `@AfterReturning`正常退出
    - `@AfterThrowing`执行异常
    - `@Around`环绕 参数(ProceedingJoinPoint 调用proceed()方法执行原内容)
- 增强(advice)执行顺序是
    ![单个切面](https://github.com/G-bug/learning/blob/master/img/aop-01.png)
    ![多个切面](https://github.com/G-bug/learning/blob/master/img/aop-02.png)
    
- `CommandLineRunner`接口, 启动服务前预先加载的内容