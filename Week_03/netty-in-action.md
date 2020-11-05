# [返回主页](index.md)

# netty 高级

对应极客时间的课程 《[Netty源码剖析与实战](https://time.geekbang.org/course/intro/100036701)》第五章，练习程序的第二版，也是最终版。

# 每课修改代码

## 37 | 调优参数：调整System参数夯实基础

Sever端调优参数：

```java
serverBootstrap.option(NioChannelOption.SO_BACKLOG, 1024);
serverBootstrap.childOption(NioChannelOption.TCP_NODELAY, true);
```

## 38 | 调优参数：权衡Netty核心参数

Client端调优参数：

```java
bootstrap.option(NioChannelOption.CONNECT_TIMEOUT_MILLIS, 10 * 1000);
```

## 39 | 调优参数：图解费脑的三个参数









