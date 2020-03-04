参考:
- [参考项目](https://www.xncoding.com/2017/07/28/spring/sb-cache.html)
- [spring-boot集成redis做cache](https://www.concretepage.com/spring-boot/spring-boot-redis-cache)

重点
- `@EnableCaching`开启缓存
- RedisCache配置
    - `CacheManager` 是Spring提供的统一Cache管理接口,底层实现`Cache`接口
    - `LettuceConnectionFactory` 连接池处理多个redis实例
    - `RedisCacheConfiguration`
- `@Cacheable`
    - `value {..,..,}` 缓存名称(组)
    - `key` 缓存key
    - `condition` 正向条件
    - `unless` 反向条件
 ![redis-cache](https://github.com/G-bug/learning/blob/master/img/redis-01.png)
- `@Caching` 组合多个cache用注解
    - `put{ @CachePut[] }` 不检测加入
    - `evict{ @CacheEvict[] }` 清除
    - `cacheable{ @Cacheable[] }` 检测后不重复才 加入
