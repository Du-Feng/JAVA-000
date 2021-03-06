# [返回Week_02主页](index.md)

# [更多压力测试工具](StressTestTools.md)

# 作业要求
第 3 课作业实践
2、使用压测工具（wrk或sb），演练gateway-server-0.0.1-SNAPSHOT.jar 示例。

# 简述
我使用的是 Windows 系统，因此使用压测工作sb，演练 gateway-server-0.0.1-SNAPSHOT.jar 如下四个GC：
- **[串行](#串行)**
- **[并行](#并行)**
- **[CMS](#cms)**
- **[G1](#g1)**

我的笔记本是4核、8GB内存，可用内存不到2GB，因此只使用如下几个内存参数进行测试：
- 256MB
- 512MB
- 1GB
- 2GB

使用sb的命令如下：

    sb -u http://localhost:8088/api/hello -c 20 -N 60

# 串行
## SerialGC - 128MB
启动gateway-server-0.0.1-SNAPSHOT.jar的命令如下：

    java -jar -XX:+UseSerialGC -Xms128m -Xmx128m gateway-server-0.0.1-SNAPSHOT.jar

结果如下：
<br/>
![Terminal](course3-question2/serial/serial-128m-sb-terminal.png)
<br/><br/>
![Web](course3-question2/serial/serial-128m-sb-web.png)
<br/><br/>
[Web Live Report](course3-question2/serial/2020-10-28_14-37-56.879899_serial.128m/index.html)

## SerialGC - 256MB
启动gateway-server-0.0.1-SNAPSHOT.jar的命令如下：

    java -jar -XX:+UseSerialGC -Xms256m -Xmx256m gateway-server-0.0.1-SNAPSHOT.jar

结果如下：
<br/>
![Terminal](course3-question2/serial/serial-256m-sb-terminal.png)
<br/><br/>
![Web](course3-question2/serial/serial-256m-sb-web.png)
<br/><br/>
[Web Live Report](course3-question2/serial/2020-10-28_14-44-08.984718_serial.256m/index.html)

## SerialGC - 512MB
启动gateway-server-0.0.1-SNAPSHOT.jar的命令如下：

    java -jar -XX:+UseSerialGC -Xms512m -Xmx512m gateway-server-0.0.1-SNAPSHOT.jar

结果如下：
<br/>
![Terminal](course3-question2/serial/serial-512m-sb-terminal.png)
<br/><br/>
![Web](course3-question2/serial/serial-512m-sb-web.png)
<br/><br/>
[Web Live Report](course3-question2/serial/2020-10-28_14-55-30.510836_serial.512m/index.html)

## SerialGC - 1GB
启动gateway-server-0.0.1-SNAPSHOT.jar的命令如下：

    java -jar -XX:+UseSerialGC -Xms1g -Xmx1g gateway-server-0.0.1-SNAPSHOT.jar

结果如下：
<br/>
![Terminal](course3-question2/serial/serial-1g-sb-terminal.png)
<br/><br/>
![Web](course3-question2/serial/serial-1g-sb-web.png)
<br/><br/>
[Web Live Report](course3-question2/serial/2020-10-28_14-59-56.949790_serial.1g/index.html)

## SerialGC - 2GB
启动gateway-server-0.0.1-SNAPSHOT.jar的命令如下：

    java -jar -XX:+UseSerialGC -Xms2g -Xmx2g gateway-server-0.0.1-SNAPSHOT.jar

结果如下：
<br/>
![Terminal](course3-question2/serial/serial-2g-sb-terminal.png)
<br/><br/>
![Web](course3-question2/serial/serial-2g-sb-web.png)
<br/><br/>
[Web Live Report](course3-question2/serial/2020-10-28_15-03-29.905306_serial.2g/index.html)

## SerialGC - 4GB
启动gateway-server-0.0.1-SNAPSHOT.jar的命令如下：

    java -jar -XX:+UseSerialGC -Xms4g -Xmx4g gateway-server-0.0.1-SNAPSHOT.jar

结果如下：
<br/>
![Terminal](course3-question2/serial/serial-4g-sb-terminal.png)
<br/><br/>
![Web](course3-question2/serial/serial-4g-sb-web.png)
<br/><br/>
[Web Live Report](course3-question2/serial/2020-10-28_15-07-18.070700_serial.4g/index.html)

# 并行
## ParallelGC - 128MB
启动gateway-server-0.0.1-SNAPSHOT.jar的命令如下：

    java -jar -XX:+UseParallelGC -Xms128m -Xmx128m gateway-server-0.0.1-SNAPSHOT.jar

结果如下：
<br/>
![Terminal](course3-question2/parallel/parallel-128m-sb-terminal.png)
<br/><br/>
![Web](course3-question2/parallel/parallel-128m-sb-web.png)
<br/><br/>
[Web Live Report](course3-question2/parallel/2020-10-28_16-48-15.880068_parallel.128m/index.html)

## ParallelGC - 256MB
启动gateway-server-0.0.1-SNAPSHOT.jar的命令如下：

    java -jar -XX:+UseParallelGC -Xms256m -Xmx256m gateway-server-0.0.1-SNAPSHOT.jar

结果如下：
<br/>
![Terminal](course3-question2/parallel/parallel-256m-sb-terminal.png)
<br/><br/>
![Web](course3-question2/parallel/parallel-256m-sb-web.png)
<br/><br/>
[Web Live Report](course3-question2/parallel/2020-10-28_16-54-27.126586_parallel.256m/index.html)

## ParallelGC - 512MB
启动gateway-server-0.0.1-SNAPSHOT.jar的命令如下：

    java -jar -XX:+UseParallelGC -Xms512m -Xmx512m gateway-server-0.0.1-SNAPSHOT.jar

结果如下：
<br/>
![Terminal](course3-question2/parallel/parallel-512m-sb-terminal.png)
<br/><br/>
![Web](course3-question2/parallel/parallel-512m-sb-web.png)
<br/><br/>
[Web Live Report](course3-question2/parallel/2020-10-28_16-58-43.648794_parallel.512m/index.html)

## ParallelGC - 1GB
启动gateway-server-0.0.1-SNAPSHOT.jar的命令如下：

    java -jar -XX:+UseParallelGC -Xms1g -Xmx1g gateway-server-0.0.1-SNAPSHOT.jar

结果如下：
<br/>
![Terminal](course3-question2/parallel/parallel-1g-sb-terminal.png)
<br/><br/>
![Web](course3-question2/parallel/parallel-1g-sb-web.png)
<br/><br/>
[Web Live Report](course3-question2/parallel/2020-10-28_17-03-11.994880_parallel.1g/index.html)

## ParallelGC - 2GB
启动gateway-server-0.0.1-SNAPSHOT.jar的命令如下：

    java -jar -XX:+UseParallelGC -Xms2g -Xmx2g gateway-server-0.0.1-SNAPSHOT.jar

结果如下：
<br/>
![Terminal](course3-question2/parallel/parallel-2g-sb-terminal.png)
<br/><br/>
![Web](course3-question2/parallel/parallel-2g-sb-web.png)
<br/><br/>
[Web Live Report](course3-question2/parallel/2020-10-28_17-06-31.760732_parallel.2g/index.html)

## ParallelGC - 4GB
启动gateway-server-0.0.1-SNAPSHOT.jar的命令如下：

    java -jar -XX:+UseParallelGC -Xms4g -Xmx4g gateway-server-0.0.1-SNAPSHOT.jar

结果如下：
<br/>
![Terminal](course3-question2/parallel/parallel-4g-sb-terminal.png)
<br/><br/>
![Web](course3-question2/parallel/parallel-4g-sb-web.png)
<br/><br/>
[Web Live Report](course3-question2/parallel/2020-10-28_17-09-52.859059_parallel.4g/index.html)

# CMS
## ConcMarkSweepGC - 128MB
启动gateway-server-0.0.1-SNAPSHOT.jar的命令如下：

    java -jar -XX:+UseConcMarkSweepGC -Xms128m -Xmx128m gateway-server-0.0.1-SNAPSHOT.jar

结果如下：
<br/>
![Terminal](course3-question2/cms/cms-128m-sb-terminal.png)
<br/><br/>
![Web](course3-question2/cms/cms-128m-sb-web.png)
<br/><br/>
[Web Live Report](course3-question2/cms/2020-10-28_17-24-14.097825_cms.128m/index.html)

## ConcMarkSweepGC - 256MB
启动gateway-server-0.0.1-SNAPSHOT.jar的命令如下：

    java -jar -XX:+UseConcMarkSweepGC -Xms256m -Xmx256m gateway-server-0.0.1-SNAPSHOT.jar

结果如下：
<br/>
![Terminal](course3-question2/cms/cms-256m-sb-terminal.png)
<br/><br/>
![Web](course3-question2/cms/cms-256m-sb-web.png)
<br/><br/>
[Web Live Report](course3-question2/cms/2020-10-28_17-28-29.571897_cms.256m/index.html)

## ConcMarkSweepGC - 512MB
启动gateway-server-0.0.1-SNAPSHOT.jar的命令如下：

    java -jar -XX:+UseConcMarkSweepGC -Xms512m -Xmx512m gateway-server-0.0.1-SNAPSHOT.jar

结果如下：
<br/>
![Terminal](course3-question2/cms/cms-512m-sb-terminal.png)
<br/><br/>
![Web](course3-question2/cms/cms-512m-sb-web.png)
<br/><br/>
[Web Live Report](course3-question2/cms/2020-10-28_17-31-36.765894_cms.512m/index.html)

## ConcMarkSweepGC - 1GB
启动gateway-server-0.0.1-SNAPSHOT.jar的命令如下：

    java -jar -XX:+UseConcMarkSweepGC -Xms1g -Xmx1g gateway-server-0.0.1-SNAPSHOT.jar

结果如下：
<br/>
![Terminal](course3-question2/cms/cms-1g-sb-terminal.png)
<br/><br/>
![Web](course3-question2/cms/cms-1g-sb-web.png)
<br/><br/>
[Web Live Report](course3-question2/cms/2020-10-28_17-34-52.158236_cms.1g/index.html)

## ConcMarkSweepGC - 2GB
启动gateway-server-0.0.1-SNAPSHOT.jar的命令如下：

    java -jar -XX:+UseConcMarkSweepGC -Xms2g -Xmx2g gateway-server-0.0.1-SNAPSHOT.jar

结果如下：
<br/>
![Terminal](course3-question2/cms/cms-2g-sb-terminal.png)
<br/><br/>
![Web](course3-question2/cms/cms-2g-sb-web.png)
<br/><br/>
[Web Live Report](course3-question2/cms/2020-10-28_17-38-16.857610_cms.2g/index.html)

## ConcMarkSweepGC - 4GB
启动gateway-server-0.0.1-SNAPSHOT.jar的命令如下：

    java -jar -XX:+UseConcMarkSweepGC -Xms4g -Xmx4g gateway-server-0.0.1-SNAPSHOT.jar

结果如下：
<br/>
![Terminal](course3-question2/cms/cms-4g-sb-terminal.png)
<br/><br/>
![Web](course3-question2/cms/cms-4g-sb-web.png)
<br/><br/>
[Web Live Report](course3-question2/cms/2020-10-28_17-43-16.114257_cms.4g/index.html)

# G1
## G1GC - 128MB
启动gateway-server-0.0.1-SNAPSHOT.jar的命令如下：

    java -jar -XX:+UseG1GC -Xms128m -Xmx128m gateway-server-0.0.1-SNAPSHOT.jar

结果如下：
<br/>
![Terminal](course3-question2/g1/g1-128m-sb-terminal.png)
<br/><br/>
![Web](course3-question2/g1/g1-128m-sb-web.png)
<br/><br/>
[Web Live Report](course3-question2/g1/2020-10-28_17-55-03.459551_g1.128m/index.html)

## G1GC - 256MB
启动gateway-server-0.0.1-SNAPSHOT.jar的命令如下：

    java -jar -XX:+UseG1GC -Xms256m -Xmx256m gateway-server-0.0.1-SNAPSHOT.jar

结果如下：
<br/>
![Terminal](course3-question2/g1/g1-256m-sb-terminal.png)
<br/><br/>
![Web](course3-question2/g1/g1-256m-sb-web.png)
<br/><br/>
[Web Live Report](course3-question2/g1/2020-10-28_18-01-20.467665_g1.256m/index.html)

## G1GC - 512MB
启动gateway-server-0.0.1-SNAPSHOT.jar的命令如下：

    java -jar -XX:+UseG1GC -Xms512m -Xmx512m gateway-server-0.0.1-SNAPSHOT.jar

结果如下：
<br/>
![Terminal](course3-question2/g1/g1-512m-sb-terminal.png)
<br/><br/>
![Web](course3-question2/g1/g1-512m-sb-web.png)
<br/><br/>
[Web Live Report](course3-question2/g1/2020-10-28_18-04-44.442805_g1.512m/index.html)

## G1GC - 1GB
启动gateway-server-0.0.1-SNAPSHOT.jar的命令如下：

    java -jar -XX:+UseG1GC -Xms1g -Xmx1g gateway-server-0.0.1-SNAPSHOT.jar

结果如下：
<br/>
![Terminal](course3-question2/g1/g1-1g-sb-terminal.png)
<br/><br/>
![Web](course3-question2/g1/g1-1g-sb-web.png)
<br/><br/>
[Web Live Report](course3-question2/g1/2020-10-28_18-08-07.063920_g1.1g/index.html)

## G1GC - 2GB
启动gateway-server-0.0.1-SNAPSHOT.jar的命令如下：

    java -jar -XX:+UseG1GC -Xms2g -Xmx2g gateway-server-0.0.1-SNAPSHOT.jar

结果如下：
<br/>
![Terminal](course3-question2/g1/g1-2g-sb-terminal.png)
<br/><br/>
![Web](course3-question2/g1/g1-2g-sb-web.png)
<br/><br/>
[Web Live Report](course3-question2/g1/2020-10-28_18-13-22.636939_g1.2g/index.html)

## G1GC - 4GB
启动gateway-server-0.0.1-SNAPSHOT.jar的命令如下：

    java -jar -XX:+UseG1GC -Xms4g -Xmx4g gateway-server-0.0.1-SNAPSHOT.jar

结果如下：
<br/>
![Terminal](course3-question2/g1/g1-4g-sb-terminal.png)
<br/><br/>
![Web](course3-question2/g1/g1-4g-sb-web.png)
<br/><br/>
[Web Live Report](course3-question2/g1/2020-10-28_18-16-03.408513_g1.4g/index.html)