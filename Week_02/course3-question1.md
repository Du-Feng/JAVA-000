# [返回](index.md)

# 作业要求
第 3 课作业实践
1、使用 GCLogAnalysis.java 自己演练一遍串行/并行/CMS/G1的案例。

# 简述
使用 GCLogAnalysis.java 演练如下四个GC：
- 串行
- 并行
- CMS
- G1

分别使用如下6种size的内存：
- 128MB
- 256MB
- 512MB
- 1GB
- 2GB
- 4GB

我使用的 Java 11，GC日志相关的命令有所变化：
Java 8 | Java 11
------ | -------
-Xloggc:gc.demo.log | -Xlog:gc:gc.demo.log
-XX:+PrintGCDetails | -Xlog:gc*
-XX:+PrintGCDateStamps | -Xlog:gc::time (-Xlog:gc::utctime)

因此，Java 8 的命令：

        java -XX:+UseSerialGC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

在 Java 11 变为：

        java -XX:+UseSerialGC -Xms512m -Xmx512m -Xlog:gc:gc.demo.log -Xlog:gc* -Xlog:gc::time GCLogAnalysis

# 串行

# 并行

# CMS

# G1

# GC总结
