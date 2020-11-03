# [返回主页](../index.md)



# netty 初级

对应极客时间的课程 《[Netty源码剖析与实战](https://time.geekbang.org/course/intro/100036701)》第四章，练习程序的第一版。



# PlantUML

本示例使用 PlantUML 生成图片，请参照 [PlantUML Plugin Installation](PlantUML.md) 在 IntelliJ IDEA 上安装相关插件。



# Message Format

Client 和 Server 交流所使用的 Message 格式如下：

![Message Format](assets/images/data-structure.png)



# Message Class Diagram

Message 的类图如下：

![Message Family Class Diagram](assets/uml/message-family-class-diagram.png)



# Server Sequence Diagram

Server 的 childHandler pipeline 如下：

```java
serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new OrderFrameDecoder());
        pipeline.addLast(new OrderFrameEncoder());

        pipeline.addLast(new OrderProtocolDecoder());
        pipeline.addLast(new OrderProtocolEncoder());

        pipeline.addLast(new LoggingHandler(LogLevel.INFO));

        pipeline.addLast(new OrderServerProcessHandler());
    }
});
```

Server解析request的时序图如下：

