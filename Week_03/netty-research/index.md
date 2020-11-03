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

Server解析request的时序图如下（**注**：这只是粗略步骤，用于解释逻辑）：

- Decode: 接收到request时，
    - Handler的**channelRead**方法进行解码，Decoder的**channelRead**会进一步调用**decode**方法进行解码；
    - 解码完成后，**channelReadComplete**方法会把request传递给下一个handler，直至最后一个handler。
- Encode: 返回response时，
    - Handler的**write**方法加工response，Encoder会进一步调用**decode**加工response。
    - 加工完成后，**flush**会把response传递给下一个handler，直至返回至client端。

<img src="assets/uml/server-sequence-diagram.png" alt="Interpret Request Sequence Diagarm" style="zoom:150%;" />