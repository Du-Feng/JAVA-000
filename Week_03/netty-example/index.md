# Netty 4.1 Example

根据 netty 4.1 如下官方文档

- [Netty User guide for 4.x](https://netty.io/wiki/user-guide-for-4.x.html)
- [Netty Github 4.1 Branch](https://github.com/netty/netty/)

以及如下两个官方示例程序：

- discard
- echo

学习 netty 的功能和基本使用方法。

# discard
Client连上Server后，Server忽略所有Client发送过来的消息。
1. 启动 DiscardServer
2. 在Window上可以使用如下telnet命令进行测试：

        telnet localhost 8009

# echo
Client 中每输入一个字符，Server 就立即发送相同的字符给Client。
1. 启动 EchoServer
2. 在Window上可以使用如下telnet命令进行测试：

        telnet localhost 8005

# time
当Client连上Server时，Server把系统当前时间发送给Client。
1. 启动 TimeServer

2. 在Windows可以启动 TimeClient 进行测试；在Linux上可以使用如下telnet命令进行测试：

   

        rdate -o 8005 -p localhost







