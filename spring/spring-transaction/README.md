### 参考
- [IBM全面分析Spring事务](https://www.ibm.com/developerworks/cn/education/opensource/os-cn-spring-trans/index.html)
- [git项目JavaGuide](https://juejin.im/post/5b00c52ef265da0b95276091)
- [自己的事务总结](https://juejin.im/post/5d4be7e06fb9a06b1417d0a0)

### Spring事务管理
>按照给定的事务规则来执行提交或回滚操作
   - “给定事务规则”：TransactionDefinition
   - "按照...来执行提交或回滚操作"：PlatFormTransactionManager

### 5种隔离级别(DEFAULT、RU、RC、RR、SERIALIZABLE)
>隔离级别是针对**事务并发**导致的4大问题的处理方式
- 脏读：A读到B修改 但未提交的数据
- 不可重复读：A读到X，但B修改了X，A重读X出现不一致
- 幻读：A读了Y条数据，但B增删了Y中的部分数据，A再操作时出现幻数据
- 丢失修改：A读到X，B读到X，A先修改，B再修改导致A的修改丢失
    - TransactionDefinition.ISOLATION_DEFAULT
        >mysql默认RR，Oracle默认RC
    - TransactionDefinition.ISOLATION_READ_UNCOMMITTED
        >读取-未提交的数据，不解决任何问题
    - TransactionDefinition.ISOLATION_READ_COMMITTED
        >读取-提交的数据，在B的修改未提交之前，A读到的仍是原数据，
        **解决脏读**
    - TransactionDefinition.ISOLATION_REPEATABLE_READ
        >重复-读取，即使B的修改已提交，A再读还是原数据，多次读取结果一致，
        **解决脏读、不可重复读**
    - TransactionDefinition.ISOLATION_SERIALIZABLE
        >串行执行，没有并发，没有问题，但性能低
---
### 7种传播行为
>一个被事务修饰的方法，嵌套进另一个方法事务如何传播
- 同一事务：其中有一个回滚则全部回滚，即使异常被捕获仍回滚
- 父、子事务：父子事务一起提交，父事务回滚子事务一定回滚，子事务回滚不影响其他子事务和父事务
- 独立事务：回滚互不影响
    - TransactionDefinition.PROPAGATION_REQUIRED
        >必有事务，外围有则加入形成**同一个事务**，没有则新开启
    - TransactionDefinition.PROPAGATION_REQUIRES_NEW
        >必有新事务，不管外围均开启新事务，互为**独立事务**，
        且挂起外围，新事务执行完再执行外围事务
    - TransactionDefinition.PROPAGATION_SUPPORTS
        >支持，外围有则按REQUIRED，没有则非事务执行
    - TransactionDefinition.PROPAGATION_NOT_SUPPORTED
        >不支持，非事务执行，外围有事务则将其挂起，执行自己先
    - TransactionDefinition.PROPAGATION_MANDATORY
        >强制加入父事务，没有父事务则抛出异常
    - TransactionDefinition.PROPAGATION_NEVER
        >从不使用事务，外围有事务则抛出异常
    - TransactionDefinition.PROPAGATION_NESTED
        >嵌套save point的概念，外围无事务，则同REQUIRED开启新事务且相互独立。
        外围有事务，则变为其**子事务**，嵌套事务不能自行提交，父事务提交才能提交