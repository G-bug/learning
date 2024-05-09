### 重点
#### Rabbit 实现了 AMQP(高级消息队列协议 Advanced Message Queuing Protocol)

- RabbitMQ是消息的中间节点,负责消息的缓存和分发消息

- Broker 消息队列服务器的实体
- Exchange 消息路由器(交换机), 指定消息按什么规则,路由到哪个队列
  - durable=true 表示队列持久化 持久化到磁盘
      - Direct 先匹配 再投送, 消息routing_key匹配时才会投递到绑定的队列, 最简单类型
      - Topic 根据通配符进行转发, Direct灵活版
        >可路由到多个队列 × 表示匹配一个单词 # 表示匹配0/多个单词
      - Headers 自定义匹配规则类型, 设定一组键值对(headers属性) 不使用
      - Fanout 消息广播模式, 将消息发给绑定给它的 **全部队列**, 忽略routing_key
- Queue 消息队列, 存储消息
  - durable=true 表示队列持久化 持久化到磁盘
- Binding 绑定, 把Exchange和Queue按路由规则绑定
- RoutingKey 路由关键字, Exchange根据该关键字进行消息投递
- Producter 消息生产者, 产生消息
  - setDeliveryMode(MessageDeliveryMode.PERSISTENT); 持久化消息
- Consumer 消息消费者, 接收消息
- Channel 消息通道, 每个Channel代表一个会话

### 重发
- 队列ttl+dlx
- ttl设置了队列内消息的过期时间, 或basicNack了: channel.basicNack
- dlx死信
  - 为一个普通队列声明dlx交换机，过期或队列满时将消息转发到dlx交换机
  - dlx队列来处理发到dlx交换机的消息
  - 一个系统一套dlx 用routing-key区分业务

### 重试
- rabbitTemplate设置 重试次数和间隔 最大重试次数

 ### Ack(acknowledgments消息确认)机制
 > 为了防止Consumer处理消息失败, 而RabbitMQ已将该message删除, 导致数据丢失的问题, 采用ack机制,
 若Consumer退出了但没有发送ack给RabbitMQ, 则将message发送到下一个Consumer
 - RabbitMQ仅通过Consumer连接中断确认message没有正确处理,若Consumer退出时忘记发ack,会导致RabbitMQ内存占用增加而"内存泄漏"
 
### 注意
- application.yml配置中默认端口应是5672, 不是web页面的15672端口
- RabbitMQ Server会和Active冲突, 启动前关闭Active

#### 参考
- [参考项目](https://www.xncoding.com/2017/08/06/spring/sb-rabbitmq.html)