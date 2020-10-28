# [返回](../index.md)

# 作业
## 第1课作业实践
1. 自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和 for，然后自己分析一下对应的字节码，有问题群里讨论。
   <br/>[答案](bytecode/index.md)
2. 自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内
容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件群里提供。
   <br/>[答案](class-loader/index.md)
3. 画一张图，展示 Xmx、Xms、Xmn、Meta、DirectMemory、Xss 这些内存参数的
关系。
   <br/>[答案](JVMMemoryModel.md)
4. 检查一下自己维护的业务系统的 JVM 参数配置，用 jstat 和 jstack、jmap 查看一下详情，并且自己独立分析一下大概情况，思考有没有不合理的地方，如何改进。
注意：如果没有线上系统，可以自己 run 一个 web/java 项目。
<br/> 此题没有做。

## 第 2 节课作业实践
1. 本机使用 G1 GC 启动一个程序，仿照课上案例分析一下 JVM 情况
   <br/>[G1 GC.docx](G1 GC.docx)
