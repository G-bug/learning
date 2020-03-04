
- 在yml文件中增加Server配置 即启用ActiveMQ Server(本地服务)

### 参考
- [参考项目及讲解](https://github.com/timebusker/spring-boot/blob/master/spring-boot-18-MQ/README.md)

### 重点

- Queue传递模型(Point-To-point PTP 点对点)
    - 一个生产者向一个消费者发送消息
    - 同一队列可关联多个消息生产者各和消息消费者,但一条消息仅能传递给一个消息消费者
    - JMS消息服务器按"先来者优先"确定哪个消息消费者接收下条消息
    - 同懒汉模型或轮询
    - 消息不是自动推给消费者, 而是由消费者从队列中请求获得
  
- Topic传递模型(publish/subscribe, pub/sub)
    - 一条消息发送给多个消费者
    - JMS一直保留消息,直至所有主题订阅者都收到消息为止
    - 同推送模型
    - 消息自动广播, 消费者无须主动请求来获取
    
    
    
    