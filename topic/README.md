### java的元注解有哪些?

- 元注解(meta-annotation), 即是 用于注解其他注解的注解类

java有四个元注解,位于```java.lang.Annotaion包```

@Target

表明注解的作用范围```... ElementType[] value(); ...```

@Retention

描述被注解的类的保留策略(RetentionPolicy), RUNTIME运行时动态获取注解信息, > CLASS编译时进行预处理,如成辅助代码, > SOURCE只做源码的检查, 如@Override和@SuppressWarnings

@Inherited

表明被该注解所标注的注解标注的类其子类自动具有被@Inherited标注的注解

@Documented
    
描述在javadoc工具生成的帮助文档中是否保留被 该注解 标注的注解 所注解的类 的 文档注释

- 见[掘金](https://juejin.im/post/5d7f34b3f265da03ee6a894c)

### HashMap 和 TreeMap有什么区别

- 数据结构： 
    - HashMap. 用hash表（散列表）实现。hash表用 **数组+链表** 实现，1.8改为链表可转化为红黑树。一个
    数组元素连同对应的链表（或红黑树）称为 桶
        - 元素hash 与 对应桶索引 的计算
            ```java
              public class HashMap {
                  final V putVal(/*...*/) {
                          Node<K,V>[] tab; Node<K,V> p; int n, i;
                          if ((tab = table) == null || (n = tab.length) == 0)
                              n = (tab = resize()).length;
                          // 元素的hash 与 桶总数取余 得 桶索引（同%）
                          if ((p = tab[i = (n - 1) & hash]) == null)
                              tab[i] = newNode(hash, key, value, null);
                          // ...
                          return null;
                      }        
          
                  static final int hash(Object key) {
                     int h;
                     // key可以为空且桶索引为0，用 原hash值 ^ >>>16 来进行扰动让高位参与hash计算
                     return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
                  }
              }
            ```
        - 关键参数
            ```java
              public class HashMap {
                  // 默认 桶的数量
                  static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
                  // 最大桶值
                  static final int MAXIMUM_CAPACITY = 1 << 30;
                  // 默认 负载因子
                  static final float DEFAULT_LOAD_FACTOR = 0.75f;
                  // 链表树化阙值
                  static final int TREEIFY_THRESHOLD = 8;
                  // 链表不树化阙值 （扩容之后要重新计算是否树化）
                  static final int UNTREEIFY_THRESHOLD = 6;
                  // 树化的最小桶值，小于该值则扩容不树化
                  static final int MIN_TREEIFY_CAPACITY = 64;
              }
            ```             
        - 扩容过程
        - 查找过程

### HashSet的底层结构，是实现的哪个类

- HashSet不可重复元素的集合允许null元素，
- 基于HashMap实现，
- 线程不安全，并发应使用```Collections.synchronizedSet(new HashSet(...))```

### ConcurrentHashMap 为什么性能比 HashTable 更好

- 前者是使用cas实现并发，后者使用synchronized
- cas是非阻塞的，相比synchronized并发时线程上下文的开销更小

### Redis的数据类型
- 常见的五种数据类型```String, List, Set, Sorted Set, Hash```
    - String 
    - List 链表结构
    - Set 无序且唯一的字符串集合
    - Sorted Set 有序且唯一的字符串集合，每个元素关联一个source属性
    - Hash 无序的 字符串字段 和 字符串值 映射集
    
### CAS的原理
- Compare and Swap，非阻塞的原子性操作，底层是UnSafe类实现的硬件级别的原子操作

### 有哪些可重入锁
- ReentrantLock

### 线程状态
### 支付中的掉单和防重问题


## 开放问题（需提前准备）

### 自我介绍
- 计算机专业毕业，期间一直从事java web的开发，做过的项目涉及 支付类，ERP的管理系统。

### 讲解一下某个项目

### 为什么离职
- 希望自己能有一个更快的提升，从技术方面的话想接触更具规模的项目，比如用户量更大，问题更加复杂
  和各方面更加完备的团队。

### 现在的公司人员配置，你的角色，薪资配比
- 育谷6人，1美工，4开发，1组长。我负责游戏后台管理系统和支付系统的开发，系统的前后端都是我做。现在公司在寻找新的项目Java都是我负责做。
- 米付4人，1个前端，2个后端，1个组长。负责基本的日常开发任务，后期负责支付系统的开发和整合。
- 安歌7人，1ios，4java，1前端，1负责人。当时开发一个ERP系统基于SSM，我主要负责部分功能模块的开发和Bug修复。
- 底薪加项目提成，每个游戏配套一个管理系统，按2%提成给我，8%提成给游戏开发。后面改为增加基本工资（平均到9k）。