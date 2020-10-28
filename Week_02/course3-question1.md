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

我使用的 Java 11，自从Java 9，GC日志相关的命令有所变化：

| Java 8 | Java 9 |
| ---- | ---- |
| -Xloggc:gc.demo.log | -Xlog:gc:gc.demo.log |
| -XX:+PrintGCDetails | -Xlog:gc* |
| -XX:+PrintGCDateStamps | -Xlog:gc::time (-Xlog:gc::utctime) |

**Reference:**
- [OpenJDK: JEP 158: Unified JVM Logging]()
- [OpenJDK: JEP 271: Unified GC Logging](https://openjdk.java.net/jeps/271)
- [Oracle: 5 Logging](https://docs.oracle.com/javase/10/jrockit-hotspot/logging.htm#JRHMG121)
- [Unified Logging Of JVM Messages With The -Xlog Option](https://nipafx.dev/java-unified-logging-xlog)

因此，Java 8 的命令：

        java -XX:+UseSerialGC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

在 Java 11 变为：

        java -XX:+UseSerialGC -Xms512m -Xmx512m -Xlog:gc*:file=gc.demo.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis


# 串行

## 128MB
执行命令行：

        java -XX:+UseSerialGC -Xms128m -Xmx128m -Xlog:gc*:file=gc.serial.128m.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：

        PS C:\Data\Code\GitHub\JAVA-000\Week_02> java -XX:+UseSerialGC -Xms128m -Xmx128m -Xlog:gc*:file=gc.serial.128m.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java
        [2020-10-28T09:37:45.137+0800][0.018s][26280][15324] Using Serial
        [2020-10-28T09:37:45.991+0800][0.872s][26280][2160 ] GC(0) Pause Young (Allocation Failure) 34M->9M(123M) 19.907ms
        正在执行...
        [2020-10-28T09:37:46.060+0800][0.941s][26280][2160 ] GC(1) Pause Young (Allocation Failure) 43M->25M(123M) 21.031ms
        [2020-10-28T09:37:46.083+0800][0.964s][26280][2160 ] GC(2) Pause Young (Allocation Failure) 59M->39M(123M) 10.053ms
        [2020-10-28T09:37:46.103+0800][0.984s][26280][2160 ] GC(3) Pause Young (Allocation Failure) 73M->52M(123M) 11.921ms
        [2020-10-28T09:37:46.125+0800][1.006s][26280][2160 ] GC(4) Pause Young (Allocation Failure) 86M->66M(123M) 14.430ms
        [2020-10-28T09:37:46.146+0800][1.027s][26280][2160 ] GC(5) Pause Young (Allocation Failure) 100M->80M(123M) 11.202ms
        [2020-10-28T09:37:46.197+0800][1.078s][26280][2160 ] GC(7) Pause Full (Allocation Failure) 114M->88M(123M) 42.078ms
        [2020-10-28T09:37:46.197+0800][1.078s][26280][2160 ] GC(6) Pause Young (Allocation Failure) 114M->88M(123M) 42.896ms
        [2020-10-28T09:37:46.266+0800][1.147s][26280][2160 ] GC(8) Pause Full (Allocation Failure) 123M->101M(123M) 52.667ms
        [2020-10-28T09:37:46.322+0800][1.203s][26280][2160 ] GC(9) Pause Full (Allocation Failure) 123M->106M(123M) 48.915ms
        [2020-10-28T09:37:46.378+0800][1.259s][26280][2160 ] GC(10) Pause Full (Allocation Failure) 123M->107M(123M) 47.966ms
        [2020-10-28T09:37:46.406+0800][1.287s][26280][2160 ] GC(11) Pause Full (Allocation Failure) 123M->114M(123M) 23.602ms
        [2020-10-28T09:37:46.422+0800][1.303s][26280][2160 ] GC(12) Pause Full (Allocation Failure) 123M->115M(123M) 14.015ms
        [2020-10-28T09:37:46.438+0800][1.319s][26280][2160 ] GC(13) Pause Full (Allocation Failure) 123M->116M(123M) 13.806ms
        [2020-10-28T09:37:46.478+0800][1.359s][26280][2160 ] GC(14) Pause Full (Allocation Failure) 123M->115M(123M) 38.429ms
        [2020-10-28T09:37:46.495+0800][1.376s][26280][2160 ] GC(15) Pause Full (Allocation Failure) 123M->116M(123M) 12.889ms
        [2020-10-28T09:37:46.517+0800][1.398s][26280][2160 ] GC(16) Pause Full (Allocation Failure) 123M->119M(123M) 20.191ms
        [2020-10-28T09:37:46.533+0800][1.414s][26280][2160 ] GC(17) Pause Full (Allocation Failure) 123M->121M(123M) 13.733ms
        [2020-10-28T09:37:46.579+0800][1.460s][26280][2160 ] GC(18) Pause Full (Allocation Failure) 123M->121M(123M) 43.470ms
        [2020-10-28T09:37:46.593+0800][1.474s][26280][2160 ] GC(19) Pause Full (Allocation Failure) 123M->122M(123M) 12.581ms
        [2020-10-28T09:37:46.606+0800][1.487s][26280][2160 ] GC(20) Pause Full (Allocation Failure) 123M->122M(123M) 12.754ms
        [2020-10-28T09:37:46.617+0800][1.498s][26280][2160 ] GC(21) Pause Full (Allocation Failure) 123M->122M(123M) 10.021ms
        [2020-10-28T09:37:46.645+0800][1.526s][26280][2160 ] GC(22) Pause Full (Allocation Failure) 123M->123M(123M) 27.658ms
        [2020-10-28T09:37:46.657+0800][1.538s][26280][2160 ] GC(23) Pause Full (Allocation Failure) 123M->123M(123M) 10.376ms
        [2020-10-28T09:37:46.709+0800][1.590s][26280][2160 ] GC(24) Pause Full (Allocation Failure) 123M->122M(123M) 51.612ms
        [2020-10-28T09:37:46.743+0800][1.624s][26280][2160 ] GC(25) Pause Full (Allocation Failure) 123M->123M(123M) 30.063ms
        [2020-10-28T09:37:46.759+0800][1.640s][26280][2160 ] GC(26) Pause Full (Allocation Failure) 123M->123M(123M) 14.380ms
        Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
                at GCLogAnalysis.generateGarbage(GCLogAnalysis.java:42)
                at GCLogAnalysis.main(GCLogAnalysis.java:25)
        [2020-10-28T09:37:46.762+0800][1.643s][26280][15324] Heap
        [2020-10-28T09:37:46.763+0800][1.644s][26280][15324]  def new generation   total 39296K, used 39169K [0x00000000f8000000, 0x00000000faaa0000, 0x00000000faaa0000)
        [2020-10-28T09:37:46.763+0800][1.644s][26280][15324]   eden space 34944K, 100% used [0x00000000f8000000, 0x00000000fa220000, 0x00000000fa220000)
        [2020-10-28T09:37:46.764+0800][1.645s][26280][15324]   from space 4352K,  97% used [0x00000000fa220000, 0x00000000fa6404f0, 0x00000000fa660000)
        [2020-10-28T09:37:46.764+0800][1.645s][26280][15324]   to   space 4352K,   0% used [0x00000000fa660000, 0x00000000fa660000, 0x00000000faaa0000)
        [2020-10-28T09:37:46.764+0800][1.645s][26280][15324]  tenured generation   total 87424K, used 87299K [0x00000000faaa0000, 0x0000000100000000, 0x0000000100000000)
        [2020-10-28T09:37:46.764+0800][1.645s][26280][15324]    the space 87424K,  99% used [0x00000000faaa0000, 0x00000000fffe0c30, 0x00000000fffe0e00, 0x0000000100000000)
        [2020-10-28T09:37:46.765+0800][1.646s][26280][15324]  Metaspace       used 16362K, capacity 16785K, committed 17024K, reserved 1064960K
        [2020-10-28T09:37:46.765+0800][1.646s][26280][15324]   class space    used 1717K, capacity 1872K, committed 1920K, reserved 1048576K

## 256MB
执行命令行：

        java -XX:+UseSerialGC -Xms256m -Xmx256m -Xlog:gc*:file=gc.serial.256m.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：

        PS C:\Data\Code\GitHub\JAVA-000\Week_02> java -XX:+UseSerialGC -Xms256m -Xmx256m -Xlog:gc*:file=gc.serial.256m.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java
        [2020-10-28T09:40:11.840+0800][0.017s][14280][19200] Using Serial
        正在执行...
        [2020-10-28T09:40:12.712+0800][0.889s][14280][21620] GC(0) Pause Young (Allocation Failure) 68M->20M(247M) 24.039ms
        [2020-10-28T09:40:12.759+0800][0.936s][14280][21620] GC(1) Pause Young (Allocation Failure) 88M->43M(247M) 27.616ms
        [2020-10-28T09:40:12.808+0800][0.985s][14280][21620] GC(2) Pause Young (Allocation Failure) 111M->67M(247M) 27.380ms
        [2020-10-28T09:40:12.849+0800][1.026s][14280][21620] GC(3) Pause Young (Allocation Failure) 135M->96M(247M) 23.710ms
        [2020-10-28T09:40:12.895+0800][1.072s][14280][21620] GC(4) Pause Young (Allocation Failure) 164M->122M(247M) 32.378ms
        [2020-10-28T09:40:12.931+0800][1.108s][14280][21620] GC(5) Pause Young (Allocation Failure) 190M->143M(247M) 20.725ms
        [2020-10-28T09:40:12.967+0800][1.144s][14280][21620] GC(6) Pause Young (Allocation Failure) 212M->165M(247M) 22.051ms
        [2020-10-28T09:40:13.028+0800][1.205s][14280][21620] GC(8) Pause Full (Allocation Failure) 233M->160M(247M) 48.586ms
        [2020-10-28T09:40:13.029+0800][1.206s][14280][21620] GC(7) Pause Young (Allocation Failure) 233M->160M(247M) 49.513ms
        [2020-10-28T09:40:13.099+0800][1.276s][14280][21620] GC(10) Pause Full (Allocation Failure) 228M->176M(247M) 55.047ms
        [2020-10-28T09:40:13.100+0800][1.277s][14280][21620] GC(9) Pause Young (Allocation Failure) 228M->176M(247M) 55.803ms
        [2020-10-28T09:40:13.165+0800][1.342s][14280][21620] GC(11) Pause Full (Allocation Failure) 247M->189M(247M) 52.110ms
        [2020-10-28T09:40:13.235+0800][1.412s][14280][21620] GC(12) Pause Full (Allocation Failure) 247M->192M(247M) 57.725ms
        [2020-10-28T09:40:13.275+0800][1.452s][14280][21620] GC(13) Pause Full (Allocation Failure) 246M->208M(247M) 30.451ms
        [2020-10-28T09:40:13.332+0800][1.509s][14280][21620] GC(14) Pause Full (Allocation Failure) 247M->209M(247M) 48.379ms
        [2020-10-28T09:40:13.395+0800][1.572s][14280][21620] GC(15) Pause Full (Allocation Failure) 247M->212M(247M) 53.448ms
        [2020-10-28T09:40:13.463+0800][1.641s][14280][21620] GC(16) Pause Full (Allocation Failure) 247M->210M(247M) 62.217ms
        [2020-10-28T09:40:13.493+0800][1.671s][14280][21620] GC(17) Pause Full (Allocation Failure) 247M->222M(247M) 21.667ms
        [2020-10-28T09:40:13.530+0800][1.707s][14280][21620] GC(18) Pause Full (Allocation Failure) 246M->227M(247M) 32.669ms
        [2020-10-28T09:40:13.580+0800][1.757s][14280][21620] GC(19) Pause Full (Allocation Failure) 247M->230M(247M) 43.294ms
        [2020-10-28T09:40:13.650+0800][1.828s][14280][21620] GC(20) Pause Full (Allocation Failure) 247M->224M(247M) 64.117ms
        执行结束!共生成对象次数:4648
        [2020-10-28T09:40:13.665+0800][1.842s][14280][19200] Heap
        [2020-10-28T09:40:13.666+0800][1.843s][14280][19200]  def new generation   total 78656K, used 58728K [0x00000000f0000000, 0x00000000f5550000, 0x00000000f5550000)
        [2020-10-28T09:40:13.667+0800][1.844s][14280][19200]   eden space 69952K,  83% used [0x00000000f0000000, 0x00000000f395a278, 0x00000000f4450000)
        [2020-10-28T09:40:13.667+0800][1.845s][14280][19200]   from space 8704K,   0% used [0x00000000f4cd0000, 0x00000000f4cd0000, 0x00000000f5550000)
        [2020-10-28T09:40:13.668+0800][1.845s][14280][19200]   to   space 8704K,   0% used [0x00000000f4450000, 0x00000000f4450000, 0x00000000f4cd0000)
        [2020-10-28T09:40:13.668+0800][1.845s][14280][19200]  tenured generation   total 174784K, used 174580K [0x00000000f5550000, 0x0000000100000000, 0x0000000100000000)
        [2020-10-28T09:40:13.668+0800][1.845s][14280][19200]    the space 174784K,  99% used [0x00000000f5550000, 0x00000000fffcd158, 0x00000000fffcd200, 0x0000000100000000)
        [2020-10-28T09:40:13.668+0800][1.846s][14280][19200]  Metaspace       used 16532K, capacity 16957K, committed 17024K, reserved 1064960K
        [2020-10-28T09:40:13.669+0800][1.846s][14280][19200]   class space    used 1746K, capacity 1890K, committed 1920K, reserved 1048576K

## 512MB
执行命令行：

        java -XX:+UseSerialGC -Xms512m -Xmx512m -Xlog:gc*:file=gc.serial.512m.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：

        PS C:\Data\Code\GitHub\JAVA-000\Week_02> java -XX:+UseSerialGC -Xms512m -Xmx512m -Xlog:gc*:file=gc.serial.512m.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java
        [2020-10-28T09:42:26.188+0800][0.019s][26404][2936] Using Serial
        正在执行...
        [2020-10-28T09:42:27.058+0800][0.889s][26404][18168] GC(0) Pause Young (Allocation Failure) 136M->44M(494M) 39.757ms
        [2020-10-28T09:42:27.130+0800][0.962s][26404][18168] GC(1) Pause Young (Allocation Failure) 180M->85M(494M) 37.985ms
        [2020-10-28T09:42:27.195+0800][1.027s][26404][18168] GC(2) Pause Young (Allocation Failure) 222M->136M(494M) 40.480ms
        [2020-10-28T09:42:27.261+0800][1.093s][26404][18168] GC(3) Pause Young (Allocation Failure) 273M->185M(494M) 39.789ms
        [2020-10-28T09:42:27.326+0800][1.157s][26404][18168] GC(4) Pause Young (Allocation Failure) 322M->234M(494M) 36.408ms
        [2020-10-28T09:42:27.390+0800][1.222s][26404][18168] GC(5) Pause Young (Allocation Failure) 370M->283M(494M) 36.583ms
        [2020-10-28T09:42:27.452+0800][1.284s][26404][18168] GC(6) Pause Young (Allocation Failure) 419M->330M(494M) 37.767ms
        [2020-10-28T09:42:27.542+0800][1.374s][26404][18168] GC(8) Pause Full (Allocation Failure) 467M->259M(494M) 64.313ms
        [2020-10-28T09:42:27.543+0800][1.374s][26404][18168] GC(7) Pause Young (Allocation Failure) 467M->259M(494M) 65.100ms
        [2020-10-28T09:42:27.586+0800][1.417s][26404][18168] GC(9) Pause Young (Allocation Failure) 396M->307M(494M) 14.156ms
        [2020-10-28T09:42:27.681+0800][1.513s][26404][18168] GC(11) Pause Full (Allocation Failure) 444M->278M(494M) 69.301ms
        [2020-10-28T09:42:27.682+0800][1.514s][26404][18168] GC(10) Pause Young (Allocation Failure) 444M->278M(494M) 70.099ms
        [2020-10-28T09:42:27.729+0800][1.560s][26404][18168] GC(12) Pause Young (Allocation Failure) 414M->330M(494M) 18.361ms
        [2020-10-28T09:42:27.854+0800][1.685s][26404][18168] GC(14) Pause Full (Allocation Failure) 466M->304M(494M) 92.983ms
        [2020-10-28T09:42:27.854+0800][1.686s][26404][18168] GC(13) Pause Young (Allocation Failure) 466M->304M(494M) 93.712ms
        [2020-10-28T09:42:28.001+0800][1.832s][26404][18168] GC(16) Pause Full (Allocation Failure) 440M->293M(494M) 106.946ms
        [2020-10-28T09:42:28.001+0800][1.833s][26404][18168] GC(15) Pause Young (Allocation Failure) 440M->293M(494M) 107.619ms
        执行结束!共生成对象次数:8256
        [2020-10-28T09:42:28.025+0800][1.857s][26404][2936 ] Heap
        [2020-10-28T09:42:28.025+0800][1.857s][26404][2936 ]  def new generation   total 157248K, used 7744K [0x00000000e0000000, 0x00000000eaaa0000, 0x00000000eaaa0000)
        [2020-10-28T09:42:28.026+0800][1.857s][26404][2936 ]   eden space 139776K,   5% used [0x00000000e0000000, 0x00000000e0790398, 0x00000000e8880000)
        [2020-10-28T09:42:28.026+0800][1.857s][26404][2936 ]   from space 17472K,   0% used [0x00000000e9990000, 0x00000000e9990000, 0x00000000eaaa0000)
        [2020-10-28T09:42:28.026+0800][1.858s][26404][2936 ]   to   space 17472K,   0% used [0x00000000e8880000, 0x00000000e8880000, 0x00000000e9990000)
        [2020-10-28T09:42:28.026+0800][1.858s][26404][2936 ]  tenured generation   total 349568K, used 300965K [0x00000000eaaa0000, 0x0000000100000000, 0x0000000100000000)
        [2020-10-28T09:42:28.027+0800][1.858s][26404][2936 ]    the space 349568K,  86% used [0x00000000eaaa0000, 0x00000000fd089728, 0x00000000fd089800, 0x0000000100000000)
        [2020-10-28T09:42:28.027+0800][1.858s][26404][2936 ]  Metaspace       used 16515K, capacity 16956K, committed 17024K, reserved 1064960K
        [2020-10-28T09:42:28.027+0800][1.859s][26404][2936 ]   class space    used 1745K, capacity 1890K, committed 1920K, reserved 1048576K

## 1GB
执行命令行：

        java -XX:+UseSerialGC -Xms1g -Xmx1g -Xlog:gc*:file=gc.serial.1g.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：

        PS C:\Data\Code\GitHub\JAVA-000\Week_02> java -XX:+UseSerialGC -Xms1g -Xmx1g -Xlog:gc*:file=gc.serial.1g.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java
        [2020-10-28T09:44:08.964+0800][0.022s][21264][18428] Using Serial
        正在执行...
        [2020-10-28T09:44:09.933+0800][0.991s][21264][2840 ] GC(0) Pause Young (Allocation Failure) 273M->97M(989M) 67.039ms
        [2020-10-28T09:44:10.068+0800][1.127s][21264][2840 ] GC(1) Pause Young (Allocation Failure) 370M->185M(989M) 74.080ms
        [2020-10-28T09:44:10.186+0800][1.244s][21264][2840 ] GC(2) Pause Young (Allocation Failure) 458M->276M(989M) 70.225ms
        [2020-10-28T09:44:10.294+0800][1.353s][21264][2840 ] GC(3) Pause Young (Allocation Failure) 549M->356M(989M) 58.423ms
        [2020-10-28T09:44:10.411+0800][1.470s][21264][2840 ] GC(4) Pause Young (Allocation Failure) 629M->440M(989M) 59.962ms
        [2020-10-28T09:44:10.544+0800][1.603s][21264][2840 ] GC(5) Pause Young (Allocation Failure) 713M->538M(989M) 70.603ms
        [2020-10-28T09:44:10.663+0800][1.721s][21264][2840 ] GC(6) Pause Young (Allocation Failure) 811M->632M(989M) 65.944ms
        [2020-10-28T09:44:10.798+0800][1.856s][21264][2840 ] GC(8) Pause Full (Allocation Failure) 905M->362M(989M) 86.113ms
        [2020-10-28T09:44:10.799+0800][1.857s][21264][2840 ] GC(7) Pause Young (Allocation Failure) 905M->362M(989M) 87.028ms
        执行结束!共生成对象次数:10283
        [2020-10-28T09:44:10.816+0800][1.875s][21264][18428] Heap
        [2020-10-28T09:44:10.817+0800][1.875s][21264][18428]  def new generation   total 314560K, used 16109K [0x00000000c0000000, 0x00000000d5550000, 0x00000000d5550000)
        [2020-10-28T09:44:10.817+0800][1.876s][21264][18428]   eden space 279616K,   5% used [0x00000000c0000000, 0x00000000c0fbb798, 0x00000000d1110000)
        [2020-10-28T09:44:10.818+0800][1.876s][21264][18428]   from space 34944K,   0% used [0x00000000d3330000, 0x00000000d3330000, 0x00000000d5550000)
        [2020-10-28T09:44:10.818+0800][1.877s][21264][18428]   to   space 34944K,   0% used [0x00000000d1110000, 0x00000000d1110000, 0x00000000d3330000)
        [2020-10-28T09:44:10.819+0800][1.877s][21264][18428]  tenured generation   total 699072K, used 370850K [0x00000000d5550000, 0x0000000100000000, 0x0000000100000000)
        [2020-10-28T09:44:10.819+0800][1.877s][21264][18428]    the space 699072K,  53% used [0x00000000d5550000, 0x00000000ebf78a70, 0x00000000ebf78c00, 0x0000000100000000)
        [2020-10-28T09:44:10.819+0800][1.877s][21264][18428]  Metaspace       used 16517K, capacity 16956K, committed 17024K, reserved 1064960K
        [2020-10-28T09:44:10.819+0800][1.878s][21264][18428]   class space    used 1745K, capacity 1890K, committed 1920K, reserved 1048576K

## 2GB
执行命令行：

        java -XX:+UseSerialGC -Xms2g -Xmx2g -Xlog:gc*:file=gc.serial.2g.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：

        PS C:\Data\Code\GitHub\JAVA-000\Week_02> java -XX:+UseSerialGC -Xms1g -Xmx1g -Xlog:gc*:file=gc.serial.1g.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java
        [2020-10-28T09:44:08.964+0800][0.022s][21264][18428] Using Serial
        正在执行...
        [2020-10-28T09:44:09.933+0800][0.991s][21264][2840 ] GC(0) Pause Young (Allocation Failure) 273M->97M(989M) 67.039ms
        [2020-10-28T09:44:10.068+0800][1.127s][21264][2840 ] GC(1) Pause Young (Allocation Failure) 370M->185M(989M) 74.080ms
        [2020-10-28T09:44:10.186+0800][1.244s][21264][2840 ] GC(2) Pause Young (Allocation Failure) 458M->276M(989M) 70.225ms
        [2020-10-28T09:44:10.294+0800][1.353s][21264][2840 ] GC(3) Pause Young (Allocation Failure) 549M->356M(989M) 58.423ms
        [2020-10-28T09:44:10.411+0800][1.470s][21264][2840 ] GC(4) Pause Young (Allocation Failure) 629M->440M(989M) 59.962ms
        [2020-10-28T09:44:10.544+0800][1.603s][21264][2840 ] GC(5) Pause Young (Allocation Failure) 713M->538M(989M) 70.603ms
        [2020-10-28T09:44:10.663+0800][1.721s][21264][2840 ] GC(6) Pause Young (Allocation Failure) 811M->632M(989M) 65.944ms
        [2020-10-28T09:44:10.798+0800][1.856s][21264][2840 ] GC(8) Pause Full (Allocation Failure) 905M->362M(989M) 86.113ms
        [2020-10-28T09:44:10.799+0800][1.857s][21264][2840 ] GC(7) Pause Young (Allocation Failure) 905M->362M(989M) 87.028ms
        执行结束!共生成对象次数:10283
        [2020-10-28T09:44:10.816+0800][1.875s][21264][18428] Heap
        [2020-10-28T09:44:10.817+0800][1.875s][21264][18428]  def new generation   total 314560K, used 16109K [0x00000000c0000000, 0x00000000d5550000, 0x00000000d5550000)
        [2020-10-28T09:44:10.817+0800][1.876s][21264][18428]   eden space 279616K,   5% used [0x00000000c0000000, 0x00000000c0fbb798, 0x00000000d1110000)
        [2020-10-28T09:44:10.818+0800][1.876s][21264][18428]   from space 34944K,   0% used [0x00000000d3330000, 0x00000000d3330000, 0x00000000d5550000)
        [2020-10-28T09:44:10.818+0800][1.877s][21264][18428]   to   space 34944K,   0% used [0x00000000d1110000, 0x00000000d1110000, 0x00000000d3330000)
        [2020-10-28T09:44:10.819+0800][1.877s][21264][18428]  tenured generation   total 699072K, used 370850K [0x00000000d5550000, 0x0000000100000000, 0x0000000100000000)
        [2020-10-28T09:44:10.819+0800][1.877s][21264][18428]    the space 699072K,  53% used [0x00000000d5550000, 0x00000000ebf78a70, 0x00000000ebf78c00, 0x0000000100000000)
        [2020-10-28T09:44:10.819+0800][1.877s][21264][18428]  Metaspace       used 16517K, capacity 16956K, committed 17024K, reserved 1064960K
        [2020-10-28T09:44:10.819+0800][1.878s][21264][18428]   class space    used 1745K, capacity 1890K, committed 1920K, reserved 1048576K

## 4GB
执行命令行：

        java -XX:+UseSerialGC -Xms4g -Xmx4g -Xlog:gc*:file=gc.serial.4g.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：

        PS C:\Data\Code\GitHub\JAVA-000\Week_02> java -XX:+UseSerialGC -Xms4g -Xmx4g -Xlog:gc*:file=gc.serial.4g.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java
        [2020-10-28T09:48:33.653+0800][0.068s][15908][1940] Using Serial
        正在执行...
        [2020-10-28T09:48:35.152+0800][1.567s][15908][10284] GC(0) Pause Young (Allocation Failure) 1092M->246M(3959M) 159.277ms
        [2020-10-28T09:48:35.632+0800][2.047s][15908][10284] GC(1) Pause Young (Allocation Failure) 1338M->402M(3959M) 253.384ms
        执行结束!共生成对象次数:9987
        [2020-10-28T09:48:35.674+0800][2.090s][15908][1940 ] Heap
        [2020-10-28T09:48:35.675+0800][2.090s][15908][1940 ]  def new generation   total 1258304K, used 184408K [0x0000000700000000, 0x0000000755550000, 0x0000000755550000)
        [2020-10-28T09:48:35.676+0800][2.091s][15908][1940 ]   eden space 1118528K,   3% used [0x0000000700000000, 0x0000000702b96100, 0x0000000744450000)
        [2020-10-28T09:48:35.676+0800][2.091s][15908][1940 ]   from space 139776K,  99% used [0x0000000744450000, 0x000000074cccfff8, 0x000000074ccd0000)
        [2020-10-28T09:48:35.676+0800][2.091s][15908][1940 ]   to   space 139776K,   0% used [0x000000074ccd0000, 0x000000074ccd0000, 0x0000000755550000)
        [2020-10-28T09:48:35.676+0800][2.092s][15908][1940 ]  tenured generation   total 2796224K, used 272030K [0x0000000755550000, 0x0000000800000000, 0x0000000800000000)
        [2020-10-28T09:48:35.677+0800][2.092s][15908][1940 ]    the space 2796224K,   9% used [0x0000000755550000, 0x0000000765ef7970, 0x0000000765ef7a00, 0x0000000800000000)
        [2020-10-28T09:48:35.677+0800][2.092s][15908][1940 ]  Metaspace       used 16594K, capacity 16956K, committed 17024K, reserved 1064960K
        [2020-10-28T09:48:35.677+0800][2.092s][15908][1940 ]   class space    used 1746K, capacity 1890K, committed 1920K, reserved 1048576K


# 并行
## 128MB
执行命令行：

        java -XX:+UseParallelGC -Xms128m -Xmx128m -Xlog:gc*:file=gc.parallel.128m.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：

        PS C:\Data\Code\GitHub\JAVA-000\Week_02> java -XX:+UseParallelGC -Xms128m -Xmx128m -Xlog:gc*:file=gc.parallel.128m.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java
        [2020-10-28T10:25:37.525+0800][0.080s][4624][21256] Using Parallel
        [2020-10-28T10:25:39.003+0800][1.554s][4624][1688 ] GC(0) Pause Young (Allocation Failure) 32M->9M(123M) 17.645ms
        正在执行...
        [2020-10-28T10:25:39.171+0800][1.722s][4624][1688 ] GC(1) Pause Young (Allocation Failure) 41M->20M(123M) 18.216ms
        [2020-10-28T10:25:39.210+0800][1.761s][4624][1688 ] GC(2) Pause Young (Allocation Failure) 53M->32M(123M) 15.137ms
        [2020-10-28T10:25:39.242+0800][1.793s][4624][1688 ] GC(3) Pause Young (Allocation Failure) 64M->44M(123M) 20.702ms
        [2020-10-28T10:25:39.271+0800][1.822s][4624][1688 ] GC(4) Pause Young (Allocation Failure) 77M->56M(123M) 14.294ms
        [2020-10-28T10:25:39.298+0800][1.849s][4624][1688 ] GC(5) Pause Young (Allocation Failure) 89M->70M(105M) 15.749ms
        [2020-10-28T10:25:39.312+0800][1.863s][4624][1688 ] GC(6) Pause Young (Allocation Failure) 84M->76M(114M) 11.796ms
        [2020-10-28T10:25:39.381+0800][1.932s][4624][1688 ] GC(7) Pause Full (Ergonomics) 76M->69M(114M) 68.432ms
        [2020-10-28T10:25:39.394+0800][1.945s][4624][1688 ] GC(8) Pause Young (Allocation Failure) 84M->77M(114M) 5.428ms
        [2020-10-28T10:25:39.436+0800][1.987s][4624][1688 ] GC(9) Pause Full (Ergonomics) 77M->76M(114M) 41.332ms
        [2020-10-28T10:25:39.486+0800][2.036s][4624][1688 ] GC(10) Pause Full (Ergonomics) 91M->80M(114M) 42.748ms
        [2020-10-28T10:25:39.532+0800][2.082s][4624][1688 ] GC(11) Pause Full (Ergonomics) 95M->84M(114M) 37.800ms
        [2020-10-28T10:25:39.583+0800][2.133s][4624][1688 ] GC(12) Pause Full (Ergonomics) 99M->87M(114M) 46.610ms
        [2020-10-28T10:25:39.630+0800][2.180s][4624][1688 ] GC(13) Pause Full (Ergonomics) 99M->90M(114M) 43.954ms
        [2020-10-28T10:25:39.671+0800][2.221s][4624][1688 ] GC(14) Pause Full (Ergonomics) 99M->92M(114M) 38.760ms
        [2020-10-28T10:25:39.714+0800][2.264s][4624][1688 ] GC(15) Pause Full (Ergonomics) 99M->94M(114M) 38.665ms
        [2020-10-28T10:25:39.766+0800][2.316s][4624][1688 ] GC(16) Pause Full (Ergonomics) 99M->95M(114M) 49.289ms
        [2020-10-28T10:25:39.813+0800][2.363s][4624][1688 ] GC(17) Pause Full (Ergonomics) 99M->96M(114M) 43.750ms
        [2020-10-28T10:25:39.836+0800][2.386s][4624][1688 ] GC(18) Pause Full (Ergonomics) 99M->96M(114M) 20.530ms
        [2020-10-28T10:25:39.885+0800][2.435s][4624][1688 ] GC(19) Pause Full (Ergonomics) 99M->97M(114M) 47.713ms
        [2020-10-28T10:25:39.912+0800][2.461s][4624][1688 ] GC(20) Pause Full (Ergonomics) 99M->97M(114M) 23.480ms
        [2020-10-28T10:25:39.944+0800][2.493s][4624][1688 ] GC(21) Pause Full (Ergonomics) 99M->97M(114M) 30.054ms
        [2020-10-28T10:25:39.957+0800][2.506s][4624][1688 ] GC(22) Pause Full (Ergonomics) 99M->98M(114M) 10.157ms
        [2020-10-28T10:25:40.006+0800][2.555s][4624][1688 ] GC(23) Pause Full (Ergonomics) 99M->99M(114M) 47.281ms
        [2020-10-28T10:25:40.030+0800][2.580s][4624][1688 ] GC(24) Pause Full (Ergonomics) 99M->99M(114M) 23.234ms
        [2020-10-28T10:25:40.043+0800][2.592s][4624][1688 ] GC(25) Pause Full (Ergonomics) 99M->99M(114M) 11.272ms
        [2020-10-28T10:25:40.055+0800][2.604s][4624][1688 ] GC(26) Pause Full (Ergonomics) 99M->99M(114M) 9.968ms
        [2020-10-28T10:25:40.067+0800][2.616s][4624][1688 ] GC(27) Pause Full (Ergonomics) 99M->99M(114M) 11.391ms
        [2020-10-28T10:25:40.107+0800][2.656s][4624][1688 ] GC(28) Pause Full (Allocation Failure) 99M->99M(114M) 39.112ms
        [2020-10-28T10:25:40.133+0800][2.682s][4624][1688 ] GC(29) Pause Full (Ergonomics) 99M->98M(114M) 24.093ms
        执行结束!共生成对象次数:1331
        [2020-10-28T10:25:40.161+0800][2.710s][4624][21256] Heap
        [2020-10-28T10:25:40.162+0800][2.711s][4624][21256]  PSYoungGen      total 29184K, used 14514K [0x00000000fd580000, 0x0000000100000000, 0x0000000100000000)
        [2020-10-28T10:25:40.163+0800][2.712s][4624][21256]   eden space 14848K, 97% used [0x00000000fd580000,0x00000000fe3acb30,0x00000000fe400000)
        [2020-10-28T10:25:40.164+0800][2.713s][4624][21256]   from space 14336K, 0% used [0x00000000ff200000,0x00000000ff200000,0x0000000100000000)
        [2020-10-28T10:25:40.164+0800][2.713s][4624][21256]   to   space 14336K, 0% used [0x00000000fe400000,0x00000000fe400000,0x00000000ff200000)
        [2020-10-28T10:25:40.165+0800][2.713s][4624][21256]  ParOldGen       total 87552K, used 87408K [0x00000000f8000000, 0x00000000fd580000, 0x00000000fd580000)
        [2020-10-28T10:25:40.165+0800][2.714s][4624][21256]   object space 87552K, 99% used [0x00000000f8000000,0x00000000fd55c0b0,0x00000000fd580000)
        [2020-10-28T10:25:40.165+0800][2.714s][4624][21256]  Metaspace       used 16547K, capacity 16956K, committed 17024K, reserved 1064960K
        [2020-10-28T10:25:40.165+0800][2.714s][4624][21256]   class space    used 1746K, capacity 1890K, committed 1920K, reserved 1048576K

## 256MB
执行命令行：

        java -XX:+UseParallelGC -Xms256m -Xmx256m -Xlog:gc*:file=gc.parallel.256m.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：

        PS C:\Data\Code\GitHub\JAVA-000\Week_02> java -XX:+UseParallelGC -Xms256m -Xmx256m -Xlog:gc*:file=gc.parallel.256m.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java
        [2020-10-28T10:27:35.105+0800][0.027s][19796][5392] Using Parallel
        正在执行...
        [2020-10-28T10:27:35.979+0800][0.900s][19796][2020] GC(0) Pause Young (Allocation Failure) 63M->18M(245M) 26.901ms
        [2020-10-28T10:27:36.039+0800][0.958s][19796][2020] GC(1) Pause Young (Allocation Failure) 82M->43M(245M) 29.201ms
        [2020-10-28T10:27:36.074+0800][0.994s][19796][2020] GC(2) Pause Young (Allocation Failure) 107M->66M(245M) 15.922ms
        [2020-10-28T10:27:36.104+0800][1.023s][19796][2020] GC(3) Pause Young (Allocation Failure) 130M->92M(245M) 16.813ms
        [2020-10-28T10:27:36.129+0800][1.049s][19796][2020] GC(4) Pause Young (Allocation Failure) 156M->117M(245M) 13.687ms
        [2020-10-28T10:27:36.155+0800][1.075s][19796][2020] GC(5) Pause Young (Allocation Failure) 181M->139M(210M) 13.297ms
        [2020-10-28T10:27:36.169+0800][1.089s][19796][2020] GC(6) Pause Young (Allocation Failure) 168M->152M(228M) 8.494ms
        [2020-10-28T10:27:36.186+0800][1.106s][19796][2020] GC(7) Pause Young (Allocation Failure) 180M->165M(228M) 9.934ms
        [2020-10-28T10:27:36.233+0800][1.153s][19796][2020] GC(8) Pause Full (Ergonomics) 165M->137M(228M) 47.389ms
        [2020-10-28T10:27:36.243+0800][1.163s][19796][2020] GC(9) Pause Young (Allocation Failure) 165M->149M(228M) 3.189ms
        [2020-10-28T10:27:36.286+0800][1.206s][19796][2020] GC(10) Pause Full (Ergonomics) 149M->145M(228M) 42.711ms
        [2020-10-28T10:27:36.337+0800][1.257s][19796][2020] GC(11) Pause Full (Ergonomics) 174M->150M(228M) 44.416ms
        [2020-10-28T10:27:36.389+0800][1.309s][19796][2020] GC(12) Pause Full (Ergonomics) 179M->156M(228M) 44.348ms
        [2020-10-28T10:27:36.444+0800][1.363s][19796][2020] GC(13) Pause Full (Ergonomics) 185M->163M(228M) 45.217ms
        [2020-10-28T10:27:36.502+0800][1.421s][19796][2020] GC(14) Pause Full (Ergonomics) 192M->173M(228M) 50.487ms
        [2020-10-28T10:27:36.563+0800][1.482s][19796][2020] GC(15) Pause Full (Ergonomics) 198M->174M(228M) 54.444ms
        [2020-10-28T10:27:36.623+0800][1.542s][19796][2020] GC(16) Pause Full (Ergonomics) 199M->179M(228M) 53.421ms
        [2020-10-28T10:27:36.689+0800][1.608s][19796][2020] GC(17) Pause Full (Ergonomics) 199M->182M(228M) 57.359ms
        [2020-10-28T10:27:36.747+0800][1.666s][19796][2020] GC(18) Pause Full (Ergonomics) 199M->186M(228M) 50.948ms
        [2020-10-28T10:27:36.807+0800][1.726s][19796][2020] GC(19) Pause Full (Ergonomics) 199M->190M(228M) 54.017ms
        [2020-10-28T10:27:36.867+0800][1.786s][19796][2020] GC(20) Pause Full (Ergonomics) 199M->193M(228M) 55.929ms
        [2020-10-28T10:27:36.920+0800][1.838s][19796][2020] GC(21) Pause Full (Ergonomics) 199M->194M(228M) 50.470ms
        [2020-10-28T10:27:36.976+0800][1.894s][19796][2020] GC(22) Pause Full (Ergonomics) 199M->194M(228M) 53.091ms
        执行结束!共生成对象次数:3049
        [2020-10-28T10:27:36.992+0800][1.910s][19796][5392] Heap
        [2020-10-28T10:27:36.992+0800][1.910s][19796][5392]  PSYoungGen      total 58368K, used 25801K [0x00000000fab00000, 0x0000000100000000, 0x0000000100000000)
        [2020-10-28T10:27:36.992+0800][1.910s][19796][5392]   eden space 29696K, 86% used [0x00000000fab00000,0x00000000fc432440,0x00000000fc800000)
        [2020-10-28T10:27:36.992+0800][1.910s][19796][5392]   from space 28672K, 0% used [0x00000000fc800000,0x00000000fc800000,0x00000000fe400000)
        [2020-10-28T10:27:36.993+0800][1.911s][19796][5392]   to   space 28672K, 0% used [0x00000000fe400000,0x00000000fe400000,0x0000000100000000)
        [2020-10-28T10:27:36.993+0800][1.911s][19796][5392]  ParOldGen       total 175104K, used 174964K [0x00000000f0000000, 0x00000000fab00000, 0x00000000fab00000)
        [2020-10-28T10:27:36.993+0800][1.911s][19796][5392]   object space 175104K, 99% used [0x00000000f0000000,0x00000000faadd360,0x00000000fab00000)
        [2020-10-28T10:27:36.993+0800][1.911s][19796][5392]  Metaspace       used 16543K, capacity 16939K, committed 17024K, reserved 1064960K
        [2020-10-28T10:27:36.994+0800][1.912s][19796][5392]   class space    used 1746K, capacity 1890K, committed 1920K, reserved 1048576K

## 512MB
执行命令行：

        java -XX:+UseParallelGC -Xms512m -Xmx512m -Xlog:gc*:file=gc.parallel.512m.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：

        PS C:\Data\Code\GitHub\JAVA-000\Week_02> java -XX:+UseParallelGC -Xms512m -Xmx512m -Xlog:gc*:file=gc.parallel.512m.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java
        [2020-10-28T10:28:56.412+0800][0.018s][9424][21272] Using Parallel
        正在执行...
        [2020-10-28T10:28:57.271+0800][0.876s][9424][16656] GC(0) Pause Young (Allocation Failure) 128M->46M(491M) 19.243ms
        [2020-10-28T10:28:57.327+0800][0.931s][9424][16656] GC(1) Pause Young (Allocation Failure) 174M->90M(491M) 20.143ms
        [2020-10-28T10:28:57.375+0800][0.980s][9424][16656] GC(2) Pause Young (Allocation Failure) 218M->135M(491M) 22.714ms
        [2020-10-28T10:28:57.425+0800][1.029s][9424][16656] GC(3) Pause Young (Allocation Failure) 263M->179M(491M) 24.982ms
        [2020-10-28T10:28:57.482+0800][1.087s][9424][16656] GC(4) Pause Young (Allocation Failure) 308M->224M(491M) 25.585ms
        [2020-10-28T10:28:57.535+0800][1.140s][9424][16656] GC(5) Pause Young (Allocation Failure) 353M->275M(420M) 29.701ms
        [2020-10-28T10:28:57.562+0800][1.166s][9424][16656] GC(6) Pause Young (Allocation Failure) 333M->294M(455M) 13.330ms
        [2020-10-28T10:28:57.589+0800][1.194s][9424][16656] GC(7) Pause Young (Allocation Failure) 351M->313M(455M) 15.451ms
        [2020-10-28T10:28:57.619+0800][1.224s][9424][16656] GC(8) Pause Young (Allocation Failure) 370M->331M(455M) 19.359ms
        [2020-10-28T10:28:57.691+0800][1.295s][9424][16656] GC(9) Pause Full (Ergonomics) 331M->226M(455M) 71.342ms
        [2020-10-28T10:28:57.713+0800][1.317s][9424][16656] GC(10) Pause Young (Allocation Failure) 284M->254M(455M) 5.825ms
        [2020-10-28T10:28:57.740+0800][1.343s][9424][16656] GC(11) Pause Young (Allocation Failure) 311M->277M(455M) 13.726ms
        [2020-10-28T10:28:57.763+0800][1.367s][9424][16656] GC(12) Pause Young (Allocation Failure) 334M->296M(455M) 10.453ms
        [2020-10-28T10:28:57.792+0800][1.396s][9424][16656] GC(13) Pause Young (Allocation Failure) 353M->317M(455M) 15.410ms
        [2020-10-28T10:28:57.861+0800][1.465s][9424][16656] GC(14) Pause Full (Ergonomics) 317M->256M(455M) 69.105ms
        [2020-10-28T10:28:57.889+0800][1.493s][9424][16656] GC(15) Pause Young (Allocation Failure) 313M->280M(455M) 10.166ms
        [2020-10-28T10:28:57.912+0800][1.516s][9424][16656] GC(16) Pause Young (Allocation Failure) 338M->298M(455M) 10.301ms
        [2020-10-28T10:28:57.940+0800][1.543s][9424][16656] GC(17) Pause Young (Allocation Failure) 356M->319M(455M) 13.498ms
        [2020-10-28T10:28:58.016+0800][1.619s][9424][16656] GC(18) Pause Full (Ergonomics) 319M->270M(455M) 75.572ms
        [2020-10-28T10:28:58.042+0800][1.645s][9424][16656] GC(19) Pause Young (Allocation Failure) 328M->289M(455M) 4.813ms
        [2020-10-28T10:28:58.072+0800][1.675s][9424][16656] GC(20) Pause Young (Allocation Failure) 347M->312M(455M) 12.036ms
        [2020-10-28T10:28:58.100+0800][1.703s][9424][16656] GC(21) Pause Young (Allocation Failure) 369M->328M(455M) 9.239ms
        [2020-10-28T10:28:58.181+0800][1.784s][9424][16656] GC(22) Pause Full (Ergonomics) 328M->280M(455M) 80.110ms
        执行结束!共生成对象次数:7202
        [2020-10-28T10:28:58.215+0800][1.818s][9424][21272] Heap
        [2020-10-28T10:28:58.215+0800][1.818s][9424][21272]  PSYoungGen      total 116736K, used 21034K [0x00000000f5580000, 0x0000000100000000, 0x0000000100000000)
        [2020-10-28T10:28:58.215+0800][1.818s][9424][21272]   eden space 58880K, 35% used [0x00000000f5580000,0x00000000f6a0a8e0,0x00000000f8f00000)
        [2020-10-28T10:28:58.215+0800][1.818s][9424][21272]   from space 57856K, 0% used [0x00000000f8f00000,0x00000000f8f00000,0x00000000fc780000)
        [2020-10-28T10:28:58.216+0800][1.819s][9424][21272]   to   space 57856K, 0% used [0x00000000fc780000,0x00000000fc780000,0x0000000100000000)
        [2020-10-28T10:28:58.217+0800][1.820s][9424][21272]  ParOldGen       total 349696K, used 286762K [0x00000000e0000000, 0x00000000f5580000, 0x00000000f5580000)
        [2020-10-28T10:28:58.218+0800][1.821s][9424][21272]   object space 349696K, 82% used [0x00000000e0000000,0x00000000f180a868,0x00000000f5580000)
        [2020-10-28T10:28:58.218+0800][1.821s][9424][21272]  Metaspace       used 16563K, capacity 16955K, committed 17024K, reserved 1064960K
        [2020-10-28T10:28:58.218+0800][1.821s][9424][21272]   class space    used 1747K, capacity 1890K, committed 1920K, reserved 1048576K

## 1GB
执行命令行：

        java -XX:+UseParallelGC -Xms1g -Xmx1g -Xlog:gc*:file=gc.parallel.1g.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：

        PS C:\Data\Code\GitHub\JAVA-000\Week_02> java -XX:+UseParallelGC -Xms1g -Xmx1g -Xlog:gc*:file=gc.parallel.1g.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java
        [2020-10-28T10:30:25.381+0800][0.021s][2580][9320] Using Parallel
        正在执行...
        [2020-10-28T10:30:26.292+0800][0.931s][2580][9584] GC(0) Pause Young (Allocation Failure) 256M->85M(981M) 25.420ms
        [2020-10-28T10:30:26.394+0800][1.033s][2580][9584] GC(1) Pause Young (Allocation Failure) 341M->168M(981M) 40.096ms
        [2020-10-28T10:30:26.490+0800][1.130s][2580][9584] GC(2) Pause Young (Allocation Failure) 424M->248M(981M) 34.579ms
        [2020-10-28T10:30:26.582+0800][1.221s][2580][9584] GC(3) Pause Young (Allocation Failure) 504M->324M(981M) 38.806ms
        [2020-10-28T10:30:26.670+0800][1.309s][2580][9584] GC(4) Pause Young (Allocation Failure) 580M->399M(981M) 37.916ms
        [2020-10-28T10:30:26.787+0800][1.426s][2580][9584] GC(5) Pause Young (Allocation Failure) 655M->474M(839M) 66.444ms
        [2020-10-28T10:30:26.850+0800][1.489s][2580][9584] GC(6) Pause Young (Allocation Failure) 588M->507M(910M) 25.285ms
        [2020-10-28T10:30:26.898+0800][1.537s][2580][9584] GC(7) Pause Young (Allocation Failure) 620M->547M(910M) 26.353ms
        [2020-10-28T10:30:26.990+0800][1.630s][2580][9584] GC(8) Pause Young (Allocation Failure) 661M->578M(910M) 66.811ms
        [2020-10-28T10:30:27.080+0800][1.719s][2580][9584] GC(9) Pause Young (Allocation Failure) 692M->613M(910M) 63.518ms
        [2020-10-28T10:30:27.186+0800][1.825s][2580][9584] GC(10) Pause Young (Allocation Failure) 727M->640M(910M) 80.220ms
        [2020-10-28T10:30:27.367+0800][2.006s][2580][9584] GC(11) Pause Full (Ergonomics) 640M->310M(910M) 178.949ms
        执行结束!共生成对象次数:9811
        [2020-10-28T10:30:27.432+0800][2.071s][2580][9320] Heap
        [2020-10-28T10:30:27.433+0800][2.072s][2580][9320]  PSYoungGen      total 232960K, used 6569K [0x00000000eab00000, 0x0000000100000000, 0x0000000100000000)
        [2020-10-28T10:30:27.433+0800][2.072s][2580][9320]   eden space 116736K, 5% used [0x00000000eab00000,0x00000000eb16a4b8,0x00000000f1d00000)
        [2020-10-28T10:30:27.433+0800][2.072s][2580][9320]   from space 116224K, 0% used [0x00000000f8e80000,0x00000000f8e80000,0x0000000100000000)
        [2020-10-28T10:30:27.433+0800][2.073s][2580][9320]   to   space 116224K, 0% used [0x00000000f1d00000,0x00000000f1d00000,0x00000000f8e80000)
        [2020-10-28T10:30:27.434+0800][2.073s][2580][9320]  ParOldGen       total 699392K, used 317940K [0x00000000c0000000, 0x00000000eab00000, 0x00000000eab00000)
        [2020-10-28T10:30:27.434+0800][2.074s][2580][9320]   object space 699392K, 45% used [0x00000000c0000000,0x00000000d367d3c0,0x00000000eab00000)
        [2020-10-28T10:30:27.436+0800][2.075s][2580][9320]  Metaspace       used 16555K, capacity 16956K, committed 17024K, reserved 1064960K
        [2020-10-28T10:30:27.436+0800][2.076s][2580][9320]   class space    used 1747K, capacity 1890K, committed 1920K, reserved 1048576K

## 2GB
执行命令行：

        java -XX:+UseParallelGC -Xms2g -Xmx2g -Xlog:gc*:file=gc.parallel.2g.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：

        PS C:\Data\Code\GitHub\JAVA-000\Week_02> java -XX:+UseParallelGC -Xms2g -Xmx2g -Xlog:gc*:file=gc.parallel.2g.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java
        [2020-10-28T10:31:37.908+0800][0.026s][15500][19968] Using Parallel
        正在执行...
        [2020-10-28T10:31:39.460+0800][1.578s][15500][21232] GC(0) Pause Young (Allocation Failure) 512M->156M(1963M) 48.106ms
        [2020-10-28T10:31:39.655+0800][1.772s][15500][21232] GC(1) Pause Young (Allocation Failure) 668M->278M(1963M) 60.684ms
        [2020-10-28T10:31:39.851+0800][1.969s][15500][21232] GC(2) Pause Young (Allocation Failure) 790M->401M(1963M) 67.866ms
        [2020-10-28T10:31:40.242+0800][2.360s][15500][21232] GC(3) Pause Young (Allocation Failure) 914M->525M(1963M) 258.485ms
        执行结束!共生成对象次数:9491
        [2020-10-28T10:31:40.492+0800][2.610s][15500][19968] Heap
        [2020-10-28T10:31:40.493+0800][2.610s][15500][19968]  PSYoungGen      total 611840K, used 113182K [0x00000000d5580000, 0x0000000100000000, 0x0000000100000000)
        [2020-10-28T10:31:40.493+0800][2.611s][15500][19968]   eden space 524800K, 4% used [0x00000000d5580000,0x00000000d6f08100,0x00000000f5600000)
        [2020-10-28T10:31:40.493+0800][2.611s][15500][19968]   from space 87040K, 99% used [0x00000000fab00000,0x00000000fffff8a0,0x0000000100000000)
        [2020-10-28T10:31:40.494+0800][2.612s][15500][19968]   to   space 87040K, 0% used [0x00000000f5600000,0x00000000f5600000,0x00000000fab00000)
        [2020-10-28T10:31:40.494+0800][2.612s][15500][19968]  ParOldGen       total 1398272K, used 451036K [0x0000000080000000, 0x00000000d5580000, 0x00000000d5580000)
        [2020-10-28T10:31:40.494+0800][2.612s][15500][19968]   object space 1398272K, 32% used [0x0000000080000000,0x000000009b8773d8,0x00000000d5580000)
        [2020-10-28T10:31:40.495+0800][2.613s][15500][19968]  Metaspace       used 16608K, capacity 16956K, committed 17024K, reserved 1064960K
        [2020-10-28T10:31:40.495+0800][2.613s][15500][19968]   class space    used 1747K, capacity 1890K, committed 1920K, reserved 1048576K

## 4GB
执行命令行：

        java -XX:+UseParallelGC -Xms4g -Xmx4g -Xlog:gc*:file=gc.parallel.4g.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：

        PS C:\Data\Code\GitHub\JAVA-000\Week_02> java -XX:+UseParallelGC -Xms4g -Xmx4g -Xlog:gc*:file=gc.parallel.4g.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java
        [2020-10-28T10:32:29.987+0800][0.037s][11356][20044] Using Parallel
        正在执行...
        [2020-10-28T10:32:31.731+0800][1.781s][11356][9144 ] GC(0) Pause Young (Allocation Failure) 1024M->244M(3925M) 253.284ms
        执行结束!共生成对象次数:5019
        [2020-10-28T10:32:31.943+0800][1.993s][11356][20044] Heap
        [2020-10-28T10:32:31.944+0800][1.994s][11356][20044]  PSYoungGen      total 1223168K, used 288575K [0x00000007aab00000, 0x0000000800000000, 0x0000000800000000)
        [2020-10-28T10:32:31.945+0800][1.994s][11356][20044]   eden space 1048576K, 10% used [0x00000007aab00000,0x00000007b1a520f0,0x00000007eab00000)
        [2020-10-28T10:32:31.945+0800][1.995s][11356][20044]   from space 174592K, 99% used [0x00000007eab00000,0x00000007f557dbf0,0x00000007f5580000)
        [2020-10-28T10:32:31.946+0800][1.995s][11356][20044]   to   space 174592K, 0% used [0x00000007f5580000,0x00000007f5580000,0x0000000800000000)
        [2020-10-28T10:32:31.946+0800][1.996s][11356][20044]  ParOldGen       total 2796544K, used 76293K [0x0000000700000000, 0x00000007aab00000, 0x00000007aab00000)
        [2020-10-28T10:32:31.946+0800][1.996s][11356][20044]   object space 2796544K, 2% used [0x0000000700000000,0x0000000704a81650,0x00000007aab00000)
        [2020-10-28T10:32:31.946+0800][1.996s][11356][20044]  Metaspace       used 16584K, capacity 16955K, committed 17024K, reserved 1064960K
        [2020-10-28T10:32:31.947+0800][1.996s][11356][20044]   class space    used 1747K, capacity 1890K, committed 1920K, reserved 1048576K


# CMS
## 128MB
执行命令行：

        java -XX:+UseConcMarkSweepGC -Xms128m -Xmx128m -Xlog:gc*:file=gc.cms.128m.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：

        PS C:\Data\Code\GitHub\JAVA-000\Week_02> java -XX:+UseConcMarkSweepGC -Xms128m -Xmx128m -Xlog:gc*:file=gc.cms.128m.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java
        Java HotSpot(TM) 64-Bit Server VM warning: Option UseConcMarkSweepGC was deprecated in version 9.0 and will likely be removed in a future release.
        [2020-10-28T10:36:51.524+0800][0.021s][17956][12140] Using Concurrent Mark Sweep
        [2020-10-28T10:36:52.289+0800][0.785s][17956][15824] GC(0) Pause Young (Allocation Failure) 34M->10M(123M) 30.150ms
        正在执行...
        [2020-10-28T10:36:52.386+0800][0.883s][17956][15824] GC(1) Pause Young (Allocation Failure) 44M->27M(123M) 15.556ms
        [2020-10-28T10:36:52.406+0800][0.903s][17956][15824] GC(2) Pause Young (Allocation Failure) 61M->38M(123M) 9.787ms
        [2020-10-28T10:36:52.425+0800][0.921s][17956][15824] GC(3) Pause Young (Allocation Failure) 72M->51M(123M) 11.070ms
        [2020-10-28T10:36:52.425+0800][0.922s][17956][15824] GC(4) Pause Initial Mark 52M->52M(123M) 0.174ms
        [2020-10-28T10:36:52.426+0800][0.922s][17956][21408] GC(4) Concurrent Mark
        [2020-10-28T10:36:52.431+0800][0.928s][17956][21408] GC(4) Concurrent Mark 5.624ms
        [2020-10-28T10:36:52.432+0800][0.929s][17956][21408] GC(4) Concurrent Preclean
        [2020-10-28T10:36:52.434+0800][0.931s][17956][21408] GC(4) Concurrent Preclean 1.458ms
        [2020-10-28T10:36:52.435+0800][0.932s][17956][21408] GC(4) Concurrent Abortable Preclean
        [2020-10-28T10:36:52.444+0800][0.941s][17956][15824] GC(5) Pause Young (Allocation Failure) 85M->61M(123M) 6.725ms
        [2020-10-28T10:36:52.462+0800][0.959s][17956][15824] GC(6) Pause Young (Allocation Failure) 95M->75M(123M) 8.990ms
        [2020-10-28T10:36:52.472+0800][0.969s][17956][21408] GC(4) Concurrent Abortable Preclean 37.167ms
        [2020-10-28T10:36:52.511+0800][1.007s][17956][15824] GC(8) Pause Full (Allocation Failure) 109M->76M(123M) 38.784ms
        [2020-10-28T10:36:52.511+0800][1.008s][17956][15824] GC(7) Pause Young (Allocation Failure) 109M->76M(123M) 39.527ms
        [2020-10-28T10:36:52.568+0800][1.065s][17956][15824] GC(10) Pause Full (Allocation Failure) 110M->88M(123M) 45.271ms
        [2020-10-28T10:36:52.569+0800][1.065s][17956][15824] GC(9) Pause Young (Allocation Failure) 110M->88M(123M) 46.023ms
        [2020-10-28T10:36:52.569+0800][1.066s][17956][15824] GC(11) Pause Initial Mark 89M->89M(123M) 0.208ms
        [2020-10-28T10:36:52.570+0800][1.066s][17956][21408] GC(11) Concurrent Mark
        [2020-10-28T10:36:52.579+0800][1.076s][17956][21408] GC(11) Concurrent Mark 9.765ms
        [2020-10-28T10:36:52.642+0800][1.138s][17956][15824] GC(12) Pause Full (Allocation Failure) 122M->93M(123M) 61.017ms
        [2020-10-28T10:36:52.713+0800][1.209s][17956][15824] GC(13) Pause Full (Allocation Failure) 123M->100M(123M) 63.418ms
        [2020-10-28T10:36:52.714+0800][1.211s][17956][15824] GC(14) Pause Initial Mark 101M->101M(123M) 0.422ms
        [2020-10-28T10:36:52.716+0800][1.212s][17956][21408] GC(14) Concurrent Mark
        [2020-10-28T10:36:52.722+0800][1.218s][17956][21408] GC(14) Concurrent Mark 6.118ms
        [2020-10-28T10:36:52.759+0800][1.255s][17956][15824] GC(15) Pause Full (Allocation Failure) 123M->106M(123M) 36.213ms
        [2020-10-28T10:36:52.798+0800][1.295s][17956][15824] GC(16) Pause Full (Allocation Failure) 123M->113M(123M) 35.940ms
        [2020-10-28T10:36:52.799+0800][1.296s][17956][15824] GC(17) Pause Initial Mark 114M->114M(123M) 0.395ms
        [2020-10-28T10:36:52.800+0800][1.297s][17956][21408] GC(17) Concurrent Mark
        [2020-10-28T10:36:52.817+0800][1.314s][17956][21408] GC(17) Concurrent Mark 17.079ms
        [2020-10-28T10:36:52.858+0800][1.355s][17956][15824] GC(18) Pause Full (Allocation Failure) 123M->115M(123M) 54.860ms
        [2020-10-28T10:36:52.892+0800][1.388s][17956][15824] GC(19) Pause Full (Allocation Failure) 123M->117M(123M) 31.662ms
        [2020-10-28T10:36:52.893+0800][1.390s][17956][15824] GC(20) Pause Initial Mark 119M->119M(123M) 0.233ms
        [2020-10-28T10:36:52.894+0800][1.390s][17956][21408] GC(20) Concurrent Mark
        [2020-10-28T10:36:52.910+0800][1.406s][17956][21408] GC(20) Concurrent Mark 16.030ms
        [2020-10-28T10:36:52.945+0800][1.441s][17956][15824] GC(21) Pause Full (Allocation Failure) 123M->120M(123M) 49.467ms
        [2020-10-28T10:36:52.987+0800][1.483s][17956][15824] GC(22) Pause Full (Allocation Failure) 123M->120M(123M) 41.693ms
        [2020-10-28T10:36:52.989+0800][1.485s][17956][15824] GC(23) Pause Initial Mark 121M->121M(123M) 0.223ms
        [2020-10-28T10:36:52.990+0800][1.486s][17956][21408] GC(23) Concurrent Mark
        [2020-10-28T10:36:53.005+0800][1.501s][17956][21408] GC(23) Concurrent Mark 15.779ms
        [2020-10-28T10:36:53.032+0800][1.528s][17956][15824] GC(24) Pause Full (Allocation Failure) 123M->122M(123M) 41.452ms
        [2020-10-28T10:36:53.059+0800][1.555s][17956][15824] GC(25) Pause Full (Allocation Failure) 123M->122M(123M) 25.927ms
        [2020-10-28T10:36:53.059+0800][1.556s][17956][15824] GC(26) Pause Initial Mark 123M->123M(123M) 0.226ms
        [2020-10-28T10:36:53.060+0800][1.557s][17956][21408] GC(26) Concurrent Mark
        [2020-10-28T10:36:53.081+0800][1.577s][17956][21408] GC(26) Concurrent Mark 20.263ms
        [2020-10-28T10:36:53.109+0800][1.605s][17956][15824] GC(27) Pause Full (Allocation Failure) 123M->123M(123M) 47.101ms
        [2020-10-28T10:36:53.126+0800][1.623s][17956][15824] GC(28) Pause Full (Allocation Failure) 123M->123M(123M) 16.210ms
        [2020-10-28T10:36:53.167+0800][1.664s][17956][15824] GC(29) Pause Full (Allocation Failure) 123M->122M(123M) 40.441ms
        [2020-10-28T10:36:53.169+0800][1.665s][17956][15824] GC(30) Pause Initial Mark 123M->123M(123M) 0.230ms
        [2020-10-28T10:36:53.170+0800][1.666s][17956][21408] GC(30) Concurrent Mark
        [2020-10-28T10:36:53.181+0800][1.678s][17956][21408] GC(30) Concurrent Mark 11.269ms
        [2020-10-28T10:36:53.226+0800][1.722s][17956][15824] GC(31) Pause Full (Allocation Failure) 123M->122M(123M) 55.037ms
        [2020-10-28T10:36:53.241+0800][1.737s][17956][15824] GC(32) Pause Full (Allocation Failure) 123M->122M(123M) 14.059ms
        [2020-10-28T10:36:53.252+0800][1.748s][17956][15824] GC(34) Pause Full (Allocation Failure) 123M->122M(123M) 9.967ms
        [2020-10-28T10:36:53.253+0800][1.749s][17956][15824] GC(33) Pause Initial Mark 123M->123M(123M) 0.239ms
        [2020-10-28T10:36:53.254+0800][1.750s][17956][21408] GC(33) Concurrent Mark
        [2020-10-28T10:36:53.274+0800][1.771s][17956][21408] GC(33) Concurrent Mark 20.832ms
        [2020-10-28T10:36:53.289+0800][1.785s][17956][15824] GC(35) Pause Full (Allocation Failure) 123M->122M(123M) 34.159ms
        [2020-10-28T10:36:53.300+0800][1.796s][17956][15824] GC(36) Pause Full (Allocation Failure) 123M->122M(123M) 9.128ms
        [2020-10-28T10:36:53.302+0800][1.798s][17956][15824] GC(37) Pause Initial Mark 122M->122M(123M) 0.603ms
        [2020-10-28T10:36:53.304+0800][1.800s][17956][21408] GC(37) Concurrent Mark
        [2020-10-28T10:36:53.321+0800][1.817s][17956][21408] GC(37) Concurrent Mark 17.455ms
        [2020-10-28T10:36:53.330+0800][1.827s][17956][15824] GC(38) Pause Full (Allocation Failure) 123M->122M(123M) 25.570ms
        [2020-10-28T10:36:53.345+0800][1.841s][17956][15824] GC(39) Pause Full (Allocation Failure) 123M->123M(123M) 13.507ms
        [2020-10-28T10:36:53.359+0800][1.856s][17956][15824] GC(40) Pause Full (Allocation Failure) 123M->123M(123M) 13.546ms
        Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
                at GCLogAnalysis.generateGarbage(GCLogAnalysis.java:48)
                at GCLogAnalysis.main(GCLogAnalysis.java:25)
        [2020-10-28T10:36:53.363+0800][1.859s][17956][12140] Heap
        [2020-10-28T10:36:53.364+0800][1.860s][17956][12140]  par new generation   total 39296K, used 39118K [0x00000000f8000000, 0x00000000faaa0000, 0x00000000faaa0000)
        [2020-10-28T10:36:53.364+0800][1.861s][17956][12140]   eden space 34944K, 100% used [0x00000000f8000000, 0x00000000fa220000, 0x00000000fa220000)
        [2020-10-28T10:36:53.365+0800][1.861s][17956][12140]   from space 4352K,  95% used [0x00000000fa220000, 0x00000000fa633900, 0x00000000fa660000)
        [2020-10-28T10:36:53.365+0800][1.862s][17956][12140]   to   space 4352K,   0% used [0x00000000fa660000, 0x00000000fa660000, 0x00000000faaa0000)
        [2020-10-28T10:36:53.365+0800][1.862s][17956][12140]  concurrent mark-sweep generation total 87424K, used 87337K [0x00000000faaa0000, 0x0000000100000000, 0x0000000100000000)
        [2020-10-28T10:36:53.366+0800][1.863s][17956][12140]  Metaspace       used 16361K, capacity 16785K, committed 17024K, reserved 1064960K
        [2020-10-28T10:36:53.367+0800][1.863s][17956][12140]   class space    used 1717K, capacity 1872K, committed 1920K, reserved 1048576K

## 256MB
执行命令行：

        java -XX:+UseConcMarkSweepGC -Xms256m -Xmx256m -Xlog:gc*:file=gc.cms.256m.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：

        PS C:\Data\Code\GitHub\JAVA-000\Week_02> java -XX:+UseConcMarkSweepGC -Xms256m -Xmx256m -Xlog:gc*:file=gc.cms.256m.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java
        Java HotSpot(TM) 64-Bit Server VM warning: Option UseConcMarkSweepGC was deprecated in version 9.0 and will likely be removed in a future release.
        [2020-10-28T10:38:33.010+0800][0.021s][20884][19168] Using Concurrent Mark Sweep
        正在执行...
        [2020-10-28T10:38:33.821+0800][0.832s][20884][10400] GC(0) Pause Young (Allocation Failure) 68M->18M(247M) 20.887ms
        [2020-10-28T10:38:33.854+0800][0.865s][20884][10400] GC(1) Pause Young (Allocation Failure) 86M->48M(247M) 15.409ms
        [2020-10-28T10:38:33.886+0800][0.897s][20884][10400] GC(2) Pause Young (Allocation Failure) 116M->73M(247M) 18.684ms
        [2020-10-28T10:38:33.920+0800][0.931s][20884][10400] GC(3) Pause Young (Allocation Failure) 141M->99M(247M) 18.976ms
        [2020-10-28T10:38:33.921+0800][0.932s][20884][10400] GC(4) Pause Initial Mark 101M->101M(247M) 0.206ms
        [2020-10-28T10:38:33.921+0800][0.932s][20884][2212 ] GC(4) Concurrent Mark
        [2020-10-28T10:38:33.930+0800][0.941s][20884][2212 ] GC(4) Concurrent Mark 8.493ms
        [2020-10-28T10:38:33.930+0800][0.941s][20884][2212 ] GC(4) Concurrent Preclean
        [2020-10-28T10:38:33.931+0800][0.942s][20884][2212 ] GC(4) Concurrent Preclean 0.617ms
        [2020-10-28T10:38:33.931+0800][0.942s][20884][2212 ] GC(4) Concurrent Abortable Preclean
        [2020-10-28T10:38:33.987+0800][0.998s][20884][10400] GC(6) Pause Young (Allocation Failure) 194M->153M(247M) 19.938ms
        [2020-10-28T10:38:34.002+0800][1.013s][20884][2212 ] GC(4) Concurrent Abortable Preclean 70.526ms
        [2020-10-28T10:38:34.056+0800][1.067s][20884][10400] GC(8) Pause Full (Allocation Failure) 222M->149M(247M) 54.312ms
        [2020-10-28T10:38:34.057+0800][1.068s][20884][10400] GC(7) Pause Young (Allocation Failure) 222M->149M(247M) 55.417ms
        [2020-10-28T10:38:34.084+0800][1.095s][20884][10400] GC(9) Pause Young (Allocation Failure) 217M->175M(247M) 14.282ms
        [2020-10-28T10:38:34.085+0800][1.096s][20884][10400] GC(10) Pause Initial Mark 176M->176M(247M) 0.248ms
        [2020-10-28T10:38:34.086+0800][1.096s][20884][2212 ] GC(10) Concurrent Mark
        [2020-10-28T10:38:34.095+0800][1.106s][20884][2212 ] GC(10) Concurrent Mark 9.332ms
        [2020-10-28T10:38:34.096+0800][1.106s][20884][2212 ] GC(10) Concurrent Preclean
        [2020-10-28T10:38:34.097+0800][1.108s][20884][2212 ] GC(10) Concurrent Preclean 1.090ms
        [2020-10-28T10:38:34.098+0800][1.108s][20884][2212 ] GC(10) Concurrent Abortable Preclean
        [2020-10-28T10:38:34.098+0800][1.109s][20884][2212 ] GC(10) Concurrent Abortable Preclean 0.601ms
        [2020-10-28T10:38:34.101+0800][1.111s][20884][10400] GC(10) Pause Remark 228M->228M(247M) 1.875ms
        [2020-10-28T10:38:34.101+0800][1.112s][20884][2212 ] GC(10) Concurrent Sweep
        [2020-10-28T10:38:34.104+0800][1.115s][20884][2212 ] GC(10) Concurrent Sweep 3.276ms
        [2020-10-28T10:38:34.164+0800][1.175s][20884][10400] GC(12) Pause Full (Allocation Failure) 235M->178M(247M) 60.120ms
        [2020-10-28T10:38:34.165+0800][1.176s][20884][10400] GC(11) Pause Young (Allocation Failure) 235M->178M(247M) 60.994ms
        [2020-10-28T10:38:34.236+0800][1.247s][20884][10400] GC(13) Pause Full (Allocation Failure) 247M->194M(247M) 56.366ms
        [2020-10-28T10:38:34.237+0800][1.248s][20884][10400] GC(14) Pause Initial Mark 195M->195M(247M) 0.217ms
        [2020-10-28T10:38:34.238+0800][1.248s][20884][2212 ] GC(14) Concurrent Mark
        [2020-10-28T10:38:34.243+0800][1.253s][20884][2212 ] GC(14) Concurrent Mark 4.923ms
        [2020-10-28T10:38:34.244+0800][1.254s][20884][2212 ] GC(14) Concurrent Preclean
        [2020-10-28T10:38:34.249+0800][1.259s][20884][2212 ] GC(14) Concurrent Preclean 5.196ms
        [2020-10-28T10:38:34.315+0800][1.326s][20884][10400] GC(15) Pause Full (Allocation Failure) 246M->208M(247M) 67.371ms
        [2020-10-28T10:38:34.392+0800][1.402s][20884][10400] GC(16) Pause Full (Allocation Failure) 247M->213M(247M) 66.244ms
        [2020-10-28T10:38:34.393+0800][1.403s][20884][10400] GC(17) Pause Initial Mark 214M->214M(247M) 0.259ms
        [2020-10-28T10:38:34.394+0800][1.404s][20884][2212 ] GC(17) Concurrent Mark
        [2020-10-28T10:38:34.399+0800][1.410s][20884][2212 ] GC(17) Concurrent Mark 5.430ms
        [2020-10-28T10:38:34.400+0800][1.411s][20884][2212 ] GC(17) Concurrent Preclean
        [2020-10-28T10:38:34.403+0800][1.414s][20884][2212 ] GC(17) Concurrent Preclean 3.409ms
        [2020-10-28T10:38:34.476+0800][1.487s][20884][10400] GC(18) Pause Full (Allocation Failure) 247M->222M(247M) 75.438ms
        [2020-10-28T10:38:34.555+0800][1.566s][20884][10400] GC(19) Pause Full (Allocation Failure) 247M->227M(247M) 72.675ms
        [2020-10-28T10:38:34.556+0800][1.567s][20884][10400] GC(20) Pause Initial Mark 228M->228M(247M) 0.240ms
        [2020-10-28T10:38:34.557+0800][1.568s][20884][2212 ] GC(20) Concurrent Mark
        [2020-10-28T10:38:34.580+0800][1.591s][20884][2212 ] GC(20) Concurrent Mark 23.561ms
        [2020-10-28T10:38:34.656+0800][1.667s][20884][10400] GC(21) Pause Full (Allocation Failure) 247M->232M(247M) 94.985ms
        [2020-10-28T10:38:34.740+0800][1.751s][20884][10400] GC(22) Pause Full (Allocation Failure) 247M->232M(247M) 78.904ms
        [2020-10-28T10:38:34.742+0800][1.753s][20884][10400] GC(23) Pause Initial Mark 233M->233M(247M) 0.739ms
        [2020-10-28T10:38:34.743+0800][1.754s][20884][2212 ] GC(23) Concurrent Mark
        [2020-10-28T10:38:34.755+0800][1.766s][20884][2212 ] GC(23) Concurrent Mark 11.898ms
        [2020-10-28T10:38:34.835+0800][1.846s][20884][10400] GC(24) Pause Full (Allocation Failure) 247M->233M(247M) 88.109ms
        执行结束!共生成对象次数:3966
        [2020-10-28T10:38:34.850+0800][1.861s][20884][19168] Heap
        [2020-10-28T10:38:34.851+0800][1.862s][20884][19168]  par new generation   total 78656K, used 66467K [0x00000000f0000000, 0x00000000f5550000, 0x00000000f5550000)
        [2020-10-28T10:38:34.851+0800][1.862s][20884][19168]   eden space 69952K,  95% used [0x00000000f0000000, 0x00000000f40e8c60, 0x00000000f4450000)
        [2020-10-28T10:38:34.851+0800][1.863s][20884][19168]   from space 8704K,   0% used [0x00000000f4cd0000, 0x00000000f4cd0000, 0x00000000f5550000)
        [2020-10-28T10:38:34.852+0800][1.863s][20884][19168]   to   space 8704K,   0% used [0x00000000f4450000, 0x00000000f4450000, 0x00000000f4cd0000)
        [2020-10-28T10:38:34.852+0800][1.863s][20884][19168]  concurrent mark-sweep generation total 174784K, used 174738K [0x00000000f5550000, 0x0000000100000000, 0x0000000100000000)
        [2020-10-28T10:38:34.853+0800][1.864s][20884][19168]  Metaspace       used 16541K, capacity 16892K, committed 17024K, reserved 1064960K
        [2020-10-28T10:38:34.853+0800][1.864s][20884][19168]   class space    used 1746K, capacity 1890K, committed 1920K, reserved 1048576K

## 512MB
执行命令行：

        java -XX:+UseConcMarkSweepGC -Xms512m -Xmx512m -Xlog:gc*:file=gc.cms.512m.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：

        PS C:\Data\Code\GitHub\JAVA-000\Week_02> java -XX:+UseConcMarkSweepGC -Xms512m -Xmx512m -Xlog:gc*:file=gc.cms.512m.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java
        Java HotSpot(TM) 64-Bit Server VM warning: Option UseConcMarkSweepGC was deprecated in version 9.0 and will likely be removed in a future release.
        [2020-10-28T10:39:43.782+0800][0.021s][1336][3772] Using Concurrent Mark Sweep
        正在执行...
        [2020-10-28T10:39:44.715+0800][0.953s][1336][20400] GC(0) Pause Young (Allocation Failure) 136M->43M(494M) 25.514ms
        [2020-10-28T10:39:44.803+0800][1.042s][1336][20400] GC(1) Pause Young (Allocation Failure) 179M->90M(494M) 36.167ms
        [2020-10-28T10:39:44.897+0800][1.136s][1336][20400] GC(2) Pause Young (Allocation Failure) 226M->148M(494M) 45.127ms
        [2020-10-28T10:39:44.964+0800][1.202s][1336][20400] GC(3) Pause Young (Allocation Failure) 284M->201M(494M) 41.347ms
        [2020-10-28T10:39:44.965+0800][1.203s][1336][20400] GC(4) Pause Initial Mark 204M->204M(494M) 0.415ms
        [2020-10-28T10:39:44.966+0800][1.204s][1336][11936] GC(4) Concurrent Mark
        [2020-10-28T10:39:44.974+0800][1.213s][1336][11936] GC(4) Concurrent Mark 8.705ms
        [2020-10-28T10:39:44.975+0800][1.214s][1336][11936] GC(4) Concurrent Preclean
        [2020-10-28T10:39:44.976+0800][1.215s][1336][11936] GC(4) Concurrent Preclean 1.147ms
        [2020-10-28T10:39:44.977+0800][1.215s][1336][11936] GC(4) Concurrent Abortable Preclean
        [2020-10-28T10:39:45.037+0800][1.275s][1336][20400] GC(5) Pause Young (Allocation Failure) 337M->248M(494M) 43.307ms
        [2020-10-28T10:39:45.107+0800][1.346s][1336][20400] GC(6) Pause Young (Allocation Failure) 385M->297M(494M) 42.595ms
        [2020-10-28T10:39:45.177+0800][1.415s][1336][20400] GC(7) Pause Young (Allocation Failure) 433M->342M(494M) 37.558ms
        [2020-10-28T10:39:45.177+0800][1.416s][1336][11936] GC(4) Concurrent Abortable Preclean 200.317ms
        [2020-10-28T10:39:45.182+0800][1.421s][1336][20400] GC(4) Pause Remark 348M->348M(494M) 4.713ms
        [2020-10-28T10:39:45.183+0800][1.422s][1336][11936] GC(4) Concurrent Sweep
        [2020-10-28T10:39:45.190+0800][1.429s][1336][11936] GC(4) Concurrent Sweep 6.824ms
        [2020-10-28T10:39:45.191+0800][1.429s][1336][11936] GC(4) Concurrent Reset
        [2020-10-28T10:39:45.193+0800][1.432s][1336][11936] GC(4) Concurrent Reset 2.781ms
        [2020-10-28T10:39:45.301+0800][1.540s][1336][20400] GC(9) Pause Full (Allocation Failure) 452M->255M(494M) 74.113ms
        [2020-10-28T10:39:45.302+0800][1.541s][1336][20400] GC(8) Pause Young (Allocation Failure) 452M->255M(494M) 74.992ms
        [2020-10-28T10:39:45.303+0800][1.542s][1336][20400] GC(10) Pause Initial Mark 259M->259M(494M) 0.517ms
        [2020-10-28T10:39:45.304+0800][1.543s][1336][11936] GC(10) Concurrent Mark
        [2020-10-28T10:39:45.310+0800][1.548s][1336][11936] GC(10) Concurrent Mark 5.730ms
        [2020-10-28T10:39:45.310+0800][1.549s][1336][11936] GC(10) Concurrent Preclean
        [2020-10-28T10:39:45.311+0800][1.550s][1336][11936] GC(10) Concurrent Preclean 0.794ms
        [2020-10-28T10:39:45.312+0800][1.550s][1336][11936] GC(10) Concurrent Abortable Preclean
        [2020-10-28T10:39:45.355+0800][1.594s][1336][20400] GC(11) Pause Young (Allocation Failure) 392M->307M(494M) 23.183ms
        [2020-10-28T10:39:45.410+0800][1.648s][1336][20400] GC(12) Pause Young (Allocation Failure) 443M->355M(494M) 26.994ms
        [2020-10-28T10:39:45.410+0800][1.649s][1336][11936] GC(10) Concurrent Abortable Preclean 98.580ms
        [2020-10-28T10:39:45.414+0800][1.653s][1336][20400] GC(10) Pause Remark 358M->358M(494M) 3.545ms
        [2020-10-28T10:39:45.415+0800][1.653s][1336][11936] GC(10) Concurrent Sweep
        [2020-10-28T10:39:45.420+0800][1.659s][1336][11936] GC(10) Concurrent Sweep 5.232ms
        [2020-10-28T10:39:45.421+0800][1.660s][1336][11936] GC(10) Concurrent Reset
        [2020-10-28T10:39:45.422+0800][1.661s][1336][11936] GC(10) Concurrent Reset 1.262ms
        [2020-10-28T10:39:45.543+0800][1.782s][1336][20400] GC(14) Pause Full (Allocation Failure) 490M->300M(494M) 102.273ms
        [2020-10-28T10:39:45.544+0800][1.782s][1336][20400] GC(13) Pause Young (Allocation Failure) 490M->300M(494M) 103.180ms
        [2020-10-28T10:39:45.546+0800][1.784s][1336][20400] GC(15) Pause Initial Mark 301M->301M(494M) 0.629ms
        [2020-10-28T10:39:45.546+0800][1.785s][1336][11936] GC(15) Concurrent Mark
        [2020-10-28T10:39:45.556+0800][1.795s][1336][11936] GC(15) Concurrent Mark 9.631ms
        [2020-10-28T10:39:45.557+0800][1.796s][1336][11936] GC(15) Concurrent Preclean
        [2020-10-28T10:39:45.560+0800][1.799s][1336][11936] GC(15) Concurrent Preclean 3.157ms
        [2020-10-28T10:39:45.561+0800][1.800s][1336][11936] GC(15) Concurrent Abortable Preclean
        [2020-10-28T10:39:45.603+0800][1.841s][1336][20400] GC(16) Pause Young (Allocation Failure) 436M->352M(494M) 16.781ms
        [2020-10-28T10:39:45.603+0800][1.842s][1336][11936] GC(15) Concurrent Abortable Preclean 41.749ms
        [2020-10-28T10:39:45.607+0800][1.846s][1336][20400] GC(15) Pause Remark 357M->357M(494M) 3.461ms
        [2020-10-28T10:39:45.607+0800][1.846s][1336][11936] GC(15) Concurrent Sweep
        [2020-10-28T10:39:45.609+0800][1.848s][1336][11936] GC(15) Concurrent Sweep 2.174ms
        [2020-10-28T10:39:45.610+0800][1.849s][1336][11936] GC(15) Concurrent Reset
        [2020-10-28T10:39:45.611+0800][1.850s][1336][11936] GC(15) Concurrent Reset 1.123ms
        执行结束!共生成对象次数:7871
        [2020-10-28T10:39:45.640+0800][1.879s][1336][3772 ] Heap
        [2020-10-28T10:39:45.640+0800][1.879s][1336][3772 ]  par new generation   total 157248K, used 83154K [0x00000000e0000000, 0x00000000eaaa0000, 0x00000000eaaa0000)
        [2020-10-28T10:39:45.641+0800][1.879s][1336][3772 ]   eden space 139776K,  46% used [0x00000000e0000000, 0x00000000e4024900, 0x00000000e8880000)
        [2020-10-28T10:39:45.641+0800][1.880s][1336][3772 ]   from space 17472K, 100% used [0x00000000e8880000, 0x00000000e9990000, 0x00000000e9990000)
        [2020-10-28T10:39:45.642+0800][1.881s][1336][3772 ]   to   space 17472K,   0% used [0x00000000e9990000, 0x00000000e9990000, 0x00000000eaaa0000)
        [2020-10-28T10:39:45.643+0800][1.881s][1336][3772 ]  concurrent mark-sweep generation total 349568K, used 341969K [0x00000000eaaa0000, 0x0000000100000000, 0x0000000100000000)
        [2020-10-28T10:39:45.643+0800][1.882s][1336][3772 ]  Metaspace       used 16524K, capacity 16891K, committed 17024K, reserved 1064960K
        [2020-10-28T10:39:45.644+0800][1.883s][1336][3772 ]   class space    used 1746K, capacity 1890K, committed 1920K, reserved 1048576K

## 1GB
执行命令行：

        java -XX:+UseConcMarkSweepGC -Xms1g -Xmx1g -Xlog:gc*:file=gc.cms.1g.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：

        PS C:\Data\Code\GitHub\JAVA-000\Week_02> java -XX:+UseConcMarkSweepGC -Xms1g -Xmx1g -Xlog:gc*:file=gc.cms.1g.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java
        Java HotSpot(TM) 64-Bit Server VM warning: Option UseConcMarkSweepGC was deprecated in version 9.0 and will likely be removed in a future release.
        [2020-10-28T10:40:44.827+0800][0.023s][19168][9728] Using Concurrent Mark Sweep
        正在执行...
        [2020-10-28T10:40:45.916+0800][1.111s][19168][7872] GC(0) Pause Young (Allocation Failure) 273M->93M(989M) 47.456ms
        [2020-10-28T10:40:46.039+0800][1.235s][19168][7872] GC(1) Pause Young (Allocation Failure) 366M->183M(989M) 49.388ms
        [2020-10-28T10:40:46.158+0800][1.353s][19168][7872] GC(2) Pause Young (Allocation Failure) 456M->264M(989M) 63.684ms
        [2020-10-28T10:40:46.278+0800][1.474s][19168][7872] GC(3) Pause Young (Allocation Failure) 537M->354M(989M) 69.460ms
        [2020-10-28T10:40:46.400+0800][1.595s][19168][7872] GC(4) Pause Young (Allocation Failure) 627M->448M(989M) 68.839ms
        [2020-10-28T10:40:46.401+0800][1.596s][19168][7872] GC(5) Pause Initial Mark 449M->449M(989M) 0.238ms
        [2020-10-28T10:40:46.401+0800][1.597s][19168][9188] GC(5) Concurrent Mark
        [2020-10-28T10:40:46.410+0800][1.605s][19168][9188] GC(5) Concurrent Mark 8.653ms
        [2020-10-28T10:40:46.410+0800][1.606s][19168][9188] GC(5) Concurrent Preclean
        [2020-10-28T10:40:46.412+0800][1.607s][19168][9188] GC(5) Concurrent Preclean 1.521ms
        [2020-10-28T10:40:46.412+0800][1.608s][19168][9188] GC(5) Concurrent Abortable Preclean
        [2020-10-28T10:40:46.524+0800][1.720s][19168][7872] GC(6) Pause Young (Allocation Failure) 721M->539M(989M) 63.818ms
        [2020-10-28T10:40:46.637+0800][1.833s][19168][7872] GC(7) Pause Young (Allocation Failure) 812M->623M(989M) 64.267ms
        [2020-10-28T10:40:46.690+0800][1.886s][19168][9188] GC(5) Concurrent Abortable Preclean 278.008ms
        [2020-10-28T10:40:46.694+0800][1.890s][19168][7872] GC(5) Pause Remark 863M->863M(989M) 3.043ms
        [2020-10-28T10:40:46.695+0800][1.890s][19168][9188] GC(5) Concurrent Sweep
        [2020-10-28T10:40:46.699+0800][1.895s][19168][9188] GC(5) Concurrent Sweep 4.086ms
        [2020-10-28T10:40:46.700+0800][1.895s][19168][9188] GC(5) Concurrent Reset
        [2020-10-28T10:40:46.704+0800][1.899s][19168][9188] GC(5) Concurrent Reset 4.248ms
        执行结束!共生成对象次数:10208
        [2020-10-28T10:40:46.708+0800][1.904s][19168][9728] Heap
        [2020-10-28T10:40:46.708+0800][1.904s][19168][9728]  par new generation   total 314560K, used 285839K [0x00000000c0000000, 0x00000000d5550000, 0x00000000d5550000)
        [2020-10-28T10:40:46.709+0800][1.904s][19168][9728]   eden space 279616K,  89% used [0x00000000c0000000, 0x00000000cf503cf0, 0x00000000d1110000)
        [2020-10-28T10:40:46.709+0800][1.904s][19168][9728]   from space 34944K, 100% used [0x00000000d3330000, 0x00000000d5550000, 0x00000000d5550000)
        [2020-10-28T10:40:46.709+0800][1.905s][19168][9728]   to   space 34944K,   0% used [0x00000000d1110000, 0x00000000d1110000, 0x00000000d3330000)
        [2020-10-28T10:40:46.709+0800][1.905s][19168][9728]  concurrent mark-sweep generation total 699072K, used 426507K [0x00000000d5550000, 0x0000000100000000, 0x0000000100000000)
        [2020-10-28T10:40:46.710+0800][1.905s][19168][9728]  Metaspace       used 16566K, capacity 16956K, committed 17024K, reserved 1064960K
        [2020-10-28T10:40:46.710+0800][1.905s][19168][9728]   class space    used 1747K, capacity 1890K, committed 1920K, reserved 1048576K

## 2GB
执行命令行：

        java -XX:+UseConcMarkSweepGC -Xms2g -Xmx2g -Xlog:gc*:file=gc.cms.2g.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：

        PS C:\Data\Code\GitHub\JAVA-000\Week_02> java -XX:+UseConcMarkSweepGC -Xms2g -Xmx2g -Xlog:gc*:file=gc.cms.2g.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java
        Java HotSpot(TM) 64-Bit Server VM warning: Option UseConcMarkSweepGC was deprecated in version 9.0 and will likely be removed in a future release.
        [2020-10-28T10:41:39.436+0800][0.037s][11464][6792] Using Concurrent Mark Sweep
        正在执行...
        [2020-10-28T10:41:40.955+0800][1.556s][11464][10144] GC(0) Pause Young (Allocation Failure) 532M->146M(1981M) 50.348ms
        [2020-10-28T10:41:41.158+0800][1.758s][11464][10144] GC(1) Pause Young (Allocation Failure) 679M->276M(1981M) 64.834ms
        [2020-10-28T10:41:41.488+0800][2.089s][11464][10144] GC(2) Pause Young (Allocation Failure) 809M->396M(1981M) 207.983ms
        执行结束!共生成对象次数:8085
        [2020-10-28T10:41:41.560+0800][2.160s][11464][6792 ] Heap
        [2020-10-28T10:41:41.560+0800][2.161s][11464][6792 ]  par new generation   total 613440K, used 235183K [0x0000000080000000, 0x00000000a9990000, 0x00000000a9990000)
        [2020-10-28T10:41:41.560+0800][2.161s][11464][6792 ]   eden space 545344K,  30% used [0x0000000080000000, 0x000000008a32be40, 0x00000000a1490000)
        [2020-10-28T10:41:41.561+0800][2.162s][11464][6792 ]   from space 68096K, 100% used [0x00000000a5710000, 0x00000000a9990000, 0x00000000a9990000)
        [2020-10-28T10:41:41.561+0800][2.162s][11464][6792 ]   to   space 68096K,   0% used [0x00000000a1490000, 0x00000000a1490000, 0x00000000a5710000)
        [2020-10-28T10:41:41.562+0800][2.162s][11464][6792 ]  concurrent mark-sweep generation total 1415616K, used 337467K [0x00000000a9990000, 0x0000000100000000, 0x0000000100000000)
        [2020-10-28T10:41:41.562+0800][2.163s][11464][6792 ]  Metaspace       used 16565K, capacity 16956K, committed 17024K, reserved 1064960K
        [2020-10-28T10:41:41.563+0800][2.164s][11464][6792 ]   class space    used 1750K, capacity 1890K, committed 1920K, reserved 1048576K

## 4GB
执行命令行：

        java -XX:+UseConcMarkSweepGC -Xms4g -Xmx4g -Xlog:gc*:file=gc.cms.4g.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：

        PS C:\Data\Code\GitHub\JAVA-000\Week_02> java -XX:+UseConcMarkSweepGC -Xms4g -Xmx4g -Xlog:gc*:file=gc.cms.4g.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java
        Java HotSpot(TM) 64-Bit Server VM warning: Option UseConcMarkSweepGC was deprecated in version 9.0 and will likely be removed in a future release.
        [2020-10-28T10:42:27.495+0800][0.037s][9208][19288] Using Concurrent Mark Sweep
        正在执行...
        [2020-10-28T10:42:28.643+0800][1.185s][9208][19872] GC(0) Pause Young (Allocation Failure) 532M->154M(4029M) 47.135ms
        [2020-10-28T10:42:28.824+0800][1.367s][9208][19872] GC(1) Pause Young (Allocation Failure) 687M->288M(4029M) 56.927ms
        [2020-10-28T10:42:29.032+0800][1.575s][9208][19872] GC(2) Pause Young (Allocation Failure) 821M->414M(4029M) 106.071ms
        [2020-10-28T10:42:29.242+0800][1.784s][9208][19872] GC(3) Pause Young (Allocation Failure) 947M->541M(4029M) 106.341ms
        执行结束!共生成对象次数:11813
        [2020-10-28T10:42:29.321+0800][1.863s][9208][19288] Heap
        [2020-10-28T10:42:29.322+0800][1.864s][9208][19288]  par new generation   total 613440K, used 480195K [0x0000000700000000, 0x0000000729990000, 0x0000000729990000)
        [2020-10-28T10:42:29.323+0800][1.865s][9208][19288]   eden space 545344K,  75% used [0x0000000700000000, 0x0000000719270f98, 0x0000000721490000)
        [2020-10-28T10:42:29.323+0800][1.865s][9208][19288]   from space 68096K, 100% used [0x0000000721490000, 0x0000000725710000, 0x0000000725710000)
        [2020-10-28T10:42:29.323+0800][1.866s][9208][19288]   to   space 68096K,   0% used [0x0000000725710000, 0x0000000725710000, 0x0000000729990000)
        [2020-10-28T10:42:29.324+0800][1.866s][9208][19288]  concurrent mark-sweep generation total 3512768K, used 486716K [0x0000000729990000, 0x0000000800000000, 0x0000000800000000)
        [2020-10-28T10:42:29.324+0800][1.866s][9208][19288]  Metaspace       used 16559K, capacity 16956K, committed 17024K, reserved 1064960K
        [2020-10-28T10:42:29.324+0800][1.867s][9208][19288]   class space    used 1744K, capacity 1890K, committed 1920K, reserved 1048576K

# G1
## 128MB
执行命令行：

        java -XX:+UseG1GC -Xms128m -Xmx128m -Xlog:gc*:file=gc.g1.128m.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：

        PS C:\Data\Code\GitHub\JAVA-000\Week_02> java -XX:+UseG1GC -Xms128m -Xmx128m -Xlog:gc*:file=gc.g1.128m.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java
        [2020-10-28T10:45:27.444+0800][0.046s][3212][15140] Using G1
        [2020-10-28T10:45:28.116+0800][0.719s][3212][9748 ] GC(0) Pause Young (Normal) (G1 Evacuation Pause) 26M->9M(128M) 16.566ms
        正在执行...
        [2020-10-28T10:45:28.361+0800][0.963s][3212][9748 ] GC(1) To-space exhausted
        [2020-10-28T10:45:28.361+0800][0.964s][3212][9748 ] GC(1) Pause Young (Normal) (G1 Evacuation Pause) 110M->88M(128M) 11.374ms
        [2020-10-28T10:45:28.370+0800][0.973s][3212][9748 ] GC(2) Pause Young (Concurrent Start) (G1 Humongous Allocation) 89M->88M(128M) 7.400ms
        [2020-10-28T10:45:28.371+0800][0.973s][3212][13364] GC(3) Concurrent Cycle
        [2020-10-28T10:45:28.377+0800][0.980s][3212][9748 ] GC(4) Pause Young (Normal) (G1 Evacuation Pause) 105M->94M(128M) 3.079ms
        [2020-10-28T10:45:28.382+0800][0.984s][3212][9748 ] GC(5) Pause Young (Normal) (G1 Evacuation Pause) 109M->101M(128M) 2.104ms
        [2020-10-28T10:45:28.388+0800][0.990s][3212][9748 ] GC(3) Pause Remark 109M->109M(128M) 2.858ms
        [2020-10-28T10:45:28.392+0800][0.995s][3212][9748 ] GC(6) Pause Young (Normal) (G1 Evacuation Pause) 113M->108M(128M) 2.287ms
        [2020-10-28T10:45:28.396+0800][0.998s][3212][9748 ] GC(7) Pause Young (Normal) (G1 Evacuation Pause) 116M->111M(128M) 1.219ms
        [2020-10-28T10:45:28.398+0800][1.001s][3212][9748 ] GC(3) Pause Cleanup 116M->116M(128M) 0.101ms
        [2020-10-28T10:45:28.399+0800][1.001s][3212][13364] GC(3) Concurrent Cycle 28.250ms
        [2020-10-28T10:45:28.401+0800][1.003s][3212][9748 ] GC(8) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 116M->113M(128M) 1.748ms
        [2020-10-28T10:45:28.406+0800][1.008s][3212][9748 ] GC(9) Pause Young (Mixed) (G1 Evacuation Pause) 121M->102M(128M) 1.343ms
        [2020-10-28T10:45:28.411+0800][1.013s][3212][9748 ] GC(10) Pause Young (Mixed) (G1 Evacuation Pause) 108M->93M(128M) 2.992ms
        [2020-10-28T10:45:28.415+0800][1.017s][3212][9748 ] GC(11) Pause Young (Mixed) (G1 Evacuation Pause) 98M->82M(128M) 2.034ms
        [2020-10-28T10:45:28.420+0800][1.023s][3212][9748 ] GC(12) Pause Young (Mixed) (G1 Evacuation Pause) 90M->85M(128M) 1.655ms
        [2020-10-28T10:45:28.422+0800][1.025s][3212][9748 ] GC(13) Pause Young (Concurrent Start) (G1 Humongous Allocation) 87M->86M(128M) 1.234ms
        [2020-10-28T10:45:28.423+0800][1.025s][3212][13364] GC(14) Concurrent Cycle
        [2020-10-28T10:45:28.431+0800][1.033s][3212][9748 ] GC(15) Pause Young (Normal) (G1 Evacuation Pause) 102M->92M(128M) 1.525ms
        [2020-10-28T10:45:28.437+0800][1.040s][3212][9748 ] GC(16) Pause Young (Normal) (G1 Evacuation Pause) 105M->98M(128M) 1.444ms
        [2020-10-28T10:45:28.443+0800][1.045s][3212][9748 ] GC(14) Pause Remark 104M->103M(128M) 3.528ms
        [2020-10-28T10:45:28.446+0800][1.048s][3212][9748 ] GC(17) Pause Young (Normal) (G1 Evacuation Pause) 109M->101M(128M) 1.425ms
        [2020-10-28T10:45:28.451+0800][1.053s][3212][9748 ] GC(18) Pause Young (Normal) (G1 Evacuation Pause) 107M->104M(128M) 1.222ms
        [2020-10-28T10:45:28.454+0800][1.057s][3212][9748 ] GC(19) Pause Young (Normal) (G1 Evacuation Pause) 114M->110M(128M) 1.110ms
        [2020-10-28T10:45:28.455+0800][1.057s][3212][9748 ] GC(14) Pause Cleanup 110M->110M(128M) 0.082ms
        [2020-10-28T10:45:28.456+0800][1.058s][3212][13364] GC(14) Concurrent Cycle 32.931ms
        [2020-10-28T10:45:28.457+0800][1.060s][3212][9748 ] GC(20) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 115M->112M(128M) 1.145ms
        [2020-10-28T10:45:28.463+0800][1.065s][3212][9748 ] GC(21) Pause Young (Mixed) (G1 Evacuation Pause) 118M->112M(128M) 2.442ms
        [2020-10-28T10:45:28.466+0800][1.068s][3212][9748 ] GC(22) Pause Young (Concurrent Start) (G1 Humongous Allocation) 113M->113M(128M) 1.929ms
        [2020-10-28T10:45:28.466+0800][1.068s][3212][13364] GC(23) Concurrent Cycle
        [2020-10-28T10:45:28.469+0800][1.071s][3212][9748 ] GC(24) Pause Young (Normal) (G1 Evacuation Pause) 123M->114M(128M) 1.090ms
        [2020-10-28T10:45:28.473+0800][1.075s][3212][9748 ] GC(25) Pause Young (Normal) (G1 Evacuation Pause) 121M->117M(128M) 1.059ms
        [2020-10-28T10:45:28.478+0800][1.081s][3212][9748 ] GC(26) Pause Young (Normal) (G1 Evacuation Pause) 123M->119M(128M) 3.629ms
        [2020-10-28T10:45:28.483+0800][1.085s][3212][9748 ] GC(27) Pause Young (Normal) (G1 Evacuation Pause) 124M->121M(128M) 1.142ms
        [2020-10-28T10:45:28.485+0800][1.088s][3212][9748 ] GC(28) To-space exhausted
        [2020-10-28T10:45:28.486+0800][1.088s][3212][9748 ] GC(28) Pause Young (Normal) (G1 Evacuation Pause) 127M->125M(128M) 1.149ms
        [2020-10-28T10:45:28.487+0800][1.090s][3212][9748 ] GC(29) To-space exhausted
        [2020-10-28T10:45:28.488+0800][1.090s][3212][9748 ] GC(29) Pause Young (Normal) (G1 Evacuation Pause) 127M->127M(128M) 1.169ms
        [2020-10-28T10:45:28.521+0800][1.123s][3212][9748 ] GC(30) Pause Full (G1 Evacuation Pause) 127M->106M(128M) 31.849ms
        [2020-10-28T10:45:28.521+0800][1.123s][3212][13364] GC(23) Concurrent Cycle 55.223ms
        [2020-10-28T10:45:28.523+0800][1.125s][3212][9748 ] GC(31) Pause Young (Concurrent Start) (G1 Humongous Allocation) 108M->107M(128M) 0.867ms
        [2020-10-28T10:45:28.523+0800][1.125s][3212][13364] GC(32) Concurrent Cycle
        [2020-10-28T10:45:28.528+0800][1.131s][3212][9748 ] GC(33) Pause Young (Normal) (G1 Evacuation Pause) 114M->110M(128M) 3.686ms
        [2020-10-28T10:45:28.532+0800][1.134s][3212][9748 ] GC(34) To-space exhausted
        [2020-10-28T10:45:28.532+0800][1.135s][3212][9748 ] GC(34) Pause Young (Normal) (G1 Evacuation Pause) 117M->118M(128M) 1.431ms
        [2020-10-28T10:45:28.533+0800][1.136s][3212][9748 ] GC(35) To-space exhausted
        [2020-10-28T10:45:28.534+0800][1.136s][3212][9748 ] GC(35) Pause Young (Normal) (G1 Humongous Allocation) 120M->119M(128M) 0.810ms
        [2020-10-28T10:45:28.535+0800][1.137s][3212][9748 ] GC(36) Pause Young (Normal) (G1 Evacuation Pause) 120M->120M(128M) 0.480ms
        [2020-10-28T10:45:28.557+0800][1.160s][3212][9748 ] GC(37) Pause Full (G1 Evacuation Pause) 120M->113M(128M) 21.784ms
        [2020-10-28T10:45:28.559+0800][1.161s][3212][13364] GC(32) Concurrent Cycle 35.915ms
        [2020-10-28T10:45:28.562+0800][1.164s][3212][9748 ] GC(38) Pause Young (Concurrent Start) (G1 Humongous Allocation) 114M->113M(128M) 2.329ms
        [2020-10-28T10:45:28.563+0800][1.165s][3212][13364] GC(39) Concurrent Cycle
        [2020-10-28T10:45:28.565+0800][1.167s][3212][9748 ] GC(40) To-space exhausted
        [2020-10-28T10:45:28.566+0800][1.168s][3212][9748 ] GC(40) Pause Young (Normal) (G1 Humongous Allocation) 118M->117M(128M) 0.854ms
        [2020-10-28T10:45:28.567+0800][1.169s][3212][9748 ] GC(41) Pause Young (Normal) (G1 Evacuation Pause) 118M->117M(128M) 0.499ms
        [2020-10-28T10:45:28.567+0800][1.170s][3212][9748 ] GC(42) To-space exhausted
        [2020-10-28T10:45:28.568+0800][1.170s][3212][9748 ] GC(42) Pause Young (Normal) (G1 Evacuation Pause) 118M->118M(128M) 0.883ms
        [2020-10-28T10:45:28.593+0800][1.195s][3212][9748 ] GC(43) Pause Full (G1 Evacuation Pause) 118M->115M(128M) 24.341ms
        [2020-10-28T10:45:28.595+0800][1.197s][3212][13364] GC(39) Concurrent Cycle 31.731ms
        [2020-10-28T10:45:28.597+0800][1.199s][3212][9748 ] GC(44) Pause Young (Concurrent Start) (G1 Humongous Allocation) 116M->116M(128M) 1.165ms
        [2020-10-28T10:45:28.598+0800][1.200s][3212][13364] GC(45) Concurrent Cycle
        [2020-10-28T10:45:28.599+0800][1.202s][3212][9748 ] GC(46) To-space exhausted
        [2020-10-28T10:45:28.600+0800][1.202s][3212][9748 ] GC(46) Pause Young (Normal) (G1 Humongous Allocation) 118M->117M(128M) 0.909ms
        [2020-10-28T10:45:28.601+0800][1.203s][3212][9748 ] GC(47) Pause Young (Normal) (G1 Evacuation Pause) 118M->117M(128M) 0.439ms
        [2020-10-28T10:45:28.601+0800][1.204s][3212][9748 ] GC(48) To-space exhausted
        [2020-10-28T10:45:28.602+0800][1.204s][3212][9748 ] GC(48) Pause Young (Normal) (G1 Humongous Allocation) 117M->117M(128M) 0.808ms
        [2020-10-28T10:45:28.622+0800][1.225s][3212][9748 ] GC(49) Pause Full (G1 Humongous Allocation) 117M->116M(128M) 19.937ms
        [2020-10-28T10:45:28.623+0800][1.225s][3212][13364] GC(45) Concurrent Cycle 25.512ms
        [2020-10-28T10:45:28.624+0800][1.227s][3212][9748 ] GC(50) Pause Young (Concurrent Start) (G1 Humongous Allocation) 118M->116M(128M) 1.091ms
        [2020-10-28T10:45:28.625+0800][1.227s][3212][13364] GC(51) Concurrent Cycle
        [2020-10-28T10:45:28.628+0800][1.231s][3212][9748 ] GC(52) To-space exhausted
        [2020-10-28T10:45:28.629+0800][1.232s][3212][9748 ] GC(52) Pause Young (Normal) (G1 Humongous Allocation) 118M->117M(128M) 2.603ms
        [2020-10-28T10:45:28.630+0800][1.233s][3212][9748 ] GC(53) Pause Young (Normal) (G1 Evacuation Pause) 118M->117M(128M) 0.483ms
        [2020-10-28T10:45:28.631+0800][1.233s][3212][9748 ] GC(54) To-space exhausted
        [2020-10-28T10:45:28.632+0800][1.234s][3212][9748 ] GC(54) Pause Young (Normal) (G1 Humongous Allocation) 117M->117M(128M) 0.785ms
        [2020-10-28T10:45:28.651+0800][1.253s][3212][9748 ] GC(55) Pause Full (G1 Humongous Allocation) 117M->115M(128M) 18.837ms
        [2020-10-28T10:45:28.652+0800][1.255s][3212][13364] GC(51) Concurrent Cycle 27.546ms
        [2020-10-28T10:45:28.654+0800][1.256s][3212][9748 ] GC(56) Pause Young (Concurrent Start) (G1 Humongous Allocation) 116M->115M(128M) 1.037ms
        [2020-10-28T10:45:28.654+0800][1.256s][3212][13364] GC(57) Concurrent Cycle
        [2020-10-28T10:45:28.655+0800][1.257s][3212][9748 ] GC(58) To-space exhausted
        [2020-10-28T10:45:28.656+0800][1.258s][3212][9748 ] GC(58) Pause Young (Normal) (G1 Evacuation Pause) 118M->118M(128M) 1.048ms
        [2020-10-28T10:45:28.679+0800][1.281s][3212][9748 ] GC(59) Pause Full (G1 Evacuation Pause) 118M->117M(128M) 22.459ms
        [2020-10-28T10:45:28.680+0800][1.283s][3212][13364] GC(57) Concurrent Cycle 26.309ms
        [2020-10-28T10:45:28.683+0800][1.286s][3212][9748 ] GC(60) To-space exhausted
        [2020-10-28T10:45:28.684+0800][1.286s][3212][9748 ] GC(60) Pause Young (Concurrent Start) (G1 Humongous Allocation) 118M->119M(128M) 2.702ms
        [2020-10-28T10:45:28.685+0800][1.287s][3212][13364] GC(61) Concurrent Cycle
        [2020-10-28T10:45:28.686+0800][1.288s][3212][9748 ] GC(62) To-space exhausted
        [2020-10-28T10:45:28.686+0800][1.288s][3212][9748 ] GC(62) Pause Young (Normal) (G1 Evacuation Pause) 120M->120M(128M) 1.012ms
        [2020-10-28T10:45:28.702+0800][1.305s][3212][9748 ] GC(63) Pause Full (G1 Evacuation Pause) 120M->119M(128M) 15.893ms
        [2020-10-28T10:45:28.703+0800][1.305s][3212][13364] GC(61) Concurrent Cycle 18.591ms
        [2020-10-28T10:45:28.704+0800][1.306s][3212][9748 ] GC(64) Pause Young (Concurrent Start) (G1 Humongous Allocation) 119M->119M(128M) 0.672ms
        [2020-10-28T10:45:28.704+0800][1.307s][3212][13364] GC(65) Concurrent Cycle
        [2020-10-28T10:45:28.705+0800][1.307s][3212][9748 ] GC(66) To-space exhausted
        [2020-10-28T10:45:28.705+0800][1.308s][3212][9748 ] GC(66) Pause Young (Normal) (G1 Evacuation Pause) 120M->120M(128M) 0.816ms
        [2020-10-28T10:45:28.718+0800][1.321s][3212][9748 ] GC(67) Pause Full (G1 Evacuation Pause) 120M->120M(128M) 12.380ms
        [2020-10-28T10:45:28.719+0800][1.321s][3212][13364] GC(65) Concurrent Cycle 14.569ms
        [2020-10-28T10:45:28.719+0800][1.322s][3212][9748 ] GC(68) To-space exhausted
        [2020-10-28T10:45:28.720+0800][1.322s][3212][9748 ] GC(68) Pause Young (Normal) (G1 Evacuation Pause) 121M->121M(128M) 0.984ms
        [2020-10-28T10:45:28.739+0800][1.342s][3212][9748 ] GC(69) Pause Full (G1 Evacuation Pause) 121M->118M(128M) 18.735ms
        [2020-10-28T10:45:28.741+0800][1.343s][3212][9748 ] GC(70) To-space exhausted
        [2020-10-28T10:45:28.742+0800][1.344s][3212][9748 ] GC(70) Pause Young (Concurrent Start) (G1 Humongous Allocation) 120M->120M(128M) 1.710ms
        [2020-10-28T10:45:28.742+0800][1.345s][3212][13364] GC(71) Concurrent Cycle
        [2020-10-28T10:45:28.743+0800][1.346s][3212][9748 ] GC(72) Pause Young (Normal) (G1 Humongous Allocation) 120M->120M(128M) 0.821ms
        [2020-10-28T10:45:28.766+0800][1.368s][3212][9748 ] GC(73) Pause Full (G1 Humongous Allocation) 120M->118M(128M) 22.218ms
        [2020-10-28T10:45:28.767+0800][1.370s][3212][13364] GC(71) Concurrent Cycle 25.105ms
        [2020-10-28T10:45:28.770+0800][1.372s][3212][9748 ] GC(74) To-space exhausted
        [2020-10-28T10:45:28.770+0800][1.373s][3212][9748 ] GC(74) Pause Young (Normal) (G1 Evacuation Pause) 121M->120M(128M) 1.353ms
        [2020-10-28T10:45:28.771+0800][1.374s][3212][9748 ] GC(75) To-space exhausted
        [2020-10-28T10:45:28.772+0800][1.374s][3212][9748 ] GC(75) Pause Young (Concurrent Start) (G1 Evacuation Pause) 120M->120M(128M) 1.073ms
        [2020-10-28T10:45:28.772+0800][1.375s][3212][13364] GC(77) Concurrent Cycle
        [2020-10-28T10:45:28.788+0800][1.391s][3212][9748 ] GC(76) Pause Full (G1 Evacuation Pause) 120M->118M(128M) 15.716ms
        [2020-10-28T10:45:28.789+0800][1.391s][3212][13364] GC(77) Concurrent Cycle 16.353ms
        [2020-10-28T10:45:28.789+0800][1.392s][3212][9748 ] GC(78) To-space exhausted
        [2020-10-28T10:45:28.790+0800][1.392s][3212][9748 ] GC(78) Pause Young (Normal) (G1 Evacuation Pause) 119M->119M(128M) 0.849ms
        [2020-10-28T10:45:28.803+0800][1.405s][3212][9748 ] GC(79) Pause Full (G1 Evacuation Pause) 119M->118M(128M) 12.828ms
        [2020-10-28T10:45:28.804+0800][1.407s][3212][9748 ] GC(80) To-space exhausted
        [2020-10-28T10:45:28.805+0800][1.407s][3212][9748 ] GC(80) Pause Young (Concurrent Start) (G1 Humongous Allocation) 120M->120M(128M) 1.001ms
        [2020-10-28T10:45:28.805+0800][1.408s][3212][13364] GC(81) Concurrent Cycle
        [2020-10-28T10:45:28.806+0800][1.408s][3212][9748 ] GC(82) Pause Young (Normal) (G1 Humongous Allocation) 120M->120M(128M) 0.753ms
        [2020-10-28T10:45:28.830+0800][1.432s][3212][9748 ] GC(83) Pause Full (G1 Humongous Allocation) 120M->118M(128M) 23.355ms
        [2020-10-28T10:45:28.831+0800][1.434s][3212][13364] GC(81) Concurrent Cycle 26.175ms
        [2020-10-28T10:45:28.834+0800][1.436s][3212][9748 ] GC(84) To-space exhausted
        [2020-10-28T10:45:28.834+0800][1.437s][3212][9748 ] GC(84) Pause Young (Normal) (G1 Evacuation Pause) 120M->119M(128M) 1.842ms
        [2020-10-28T10:45:28.836+0800][1.438s][3212][9748 ] GC(85) To-space exhausted
        [2020-10-28T10:45:28.837+0800][1.439s][3212][9748 ] GC(85) Pause Young (Concurrent Start) (G1 Evacuation Pause) 119M->119M(128M) 1.487ms
        [2020-10-28T10:45:28.837+0800][1.439s][3212][13364] GC(87) Concurrent Cycle
        [2020-10-28T10:45:28.851+0800][1.453s][3212][9748 ] GC(86) Pause Full (G1 Evacuation Pause) 119M->118M(128M) 13.596ms
        [2020-10-28T10:45:28.852+0800][1.454s][3212][13364] GC(87) Concurrent Cycle 14.447ms
        [2020-10-28T10:45:28.853+0800][1.455s][3212][9748 ] GC(88) To-space exhausted
        [2020-10-28T10:45:28.853+0800][1.456s][3212][9748 ] GC(88) Pause Young (Normal) (G1 Evacuation Pause) 120M->120M(128M) 0.916ms
        [2020-10-28T10:45:28.866+0800][1.468s][3212][9748 ] GC(89) Pause Full (G1 Evacuation Pause) 120M->119M(128M) 11.817ms
        [2020-10-28T10:45:28.867+0800][1.470s][3212][9748 ] GC(90) To-space exhausted
        [2020-10-28T10:45:28.868+0800][1.470s][3212][9748 ] GC(90) Pause Young (Concurrent Start) (G1 Evacuation Pause) 120M->120M(128M) 1.172ms
        [2020-10-28T10:45:28.868+0800][1.471s][3212][13364] GC(92) Concurrent Cycle
        [2020-10-28T10:45:28.877+0800][1.480s][3212][9748 ] GC(91) Pause Full (G1 Evacuation Pause) 120M->119M(128M) 8.942ms
        [2020-10-28T10:45:28.878+0800][1.481s][3212][13364] GC(92) Concurrent Cycle 10.023ms
        [2020-10-28T10:45:28.879+0800][1.482s][3212][9748 ] GC(93) To-space exhausted
        [2020-10-28T10:45:28.880+0800][1.483s][3212][9748 ] GC(93) Pause Young (Normal) (G1 Evacuation Pause) 120M->120M(128M) 1.534ms
        [2020-10-28T10:45:28.888+0800][1.490s][3212][9748 ] GC(94) Pause Full (G1 Evacuation Pause) 120M->119M(128M) 7.269ms
        [2020-10-28T10:45:28.890+0800][1.492s][3212][9748 ] GC(95) Pause Young (Concurrent Start) (G1 Humongous Allocation) 119M->119M(128M) 0.671ms
        [2020-10-28T10:45:28.890+0800][1.493s][3212][13364] GC(96) Concurrent Cycle
        [2020-10-28T10:45:28.892+0800][1.494s][3212][9748 ] GC(97) To-space exhausted
        [2020-10-28T10:45:28.893+0800][1.495s][3212][9748 ] GC(97) Pause Young (Normal) (G1 Evacuation Pause) 120M->119M(128M) 1.720ms
        [2020-10-28T10:45:28.894+0800][1.497s][3212][9748 ] GC(98) To-space exhausted
        [2020-10-28T10:45:28.895+0800][1.497s][3212][9748 ] GC(98) Pause Young (Normal) (G1 Humongous Allocation) 120M->120M(128M) 1.217ms
        [2020-10-28T10:45:28.904+0800][1.506s][3212][9748 ] GC(99) Pause Full (G1 Humongous Allocation) 120M->119M(128M) 8.721ms
        [2020-10-28T10:45:28.905+0800][1.508s][3212][9748 ] GC(100) Pause Young (Normal) (G1 Evacuation Pause) 120M->120M(128M) 0.477ms
        [2020-10-28T10:45:28.918+0800][1.520s][3212][9748 ] GC(101) Pause Full (G1 Evacuation Pause) 120M->120M(128M) 11.975ms
        [2020-10-28T10:45:28.931+0800][1.534s][3212][9748 ] GC(102) Pause Full (G1 Evacuation Pause) 120M->120M(128M) 12.915ms
        [2020-10-28T10:45:28.933+0800][1.535s][3212][13364] GC(96) Concurrent Cycle 42.322ms
        [2020-10-28T10:45:28.934+0800][1.536s][3212][9748 ] GC(103) To-space exhausted
        [2020-10-28T10:45:28.934+0800][1.537s][3212][9748 ] GC(103) Pause Young (Concurrent Start) (G1 Humongous Allocation) 121M->121M(128M) 1.369ms
        [2020-10-28T10:45:28.935+0800][1.537s][3212][13364] GC(104) Concurrent Cycle
        [2020-10-28T10:45:28.935+0800][1.538s][3212][9748 ] GC(105) Pause Young (Normal) (G1 Humongous Allocation) 121M->121M(128M) 0.752ms
        [2020-10-28T10:45:28.945+0800][1.547s][3212][9748 ] GC(106) Pause Full (G1 Humongous Allocation) 121M->120M(128M) 9.333ms
        [2020-10-28T10:45:28.947+0800][1.549s][3212][13364] GC(104) Concurrent Cycle 12.186ms
        [2020-10-28T10:45:28.948+0800][1.550s][3212][9748 ] GC(107) Pause Young (Normal) (G1 Evacuation Pause) 121M->120M(128M) 1.117ms
        [2020-10-28T10:45:28.950+0800][1.552s][3212][9748 ] GC(108) To-space exhausted
        [2020-10-28T10:45:28.950+0800][1.553s][3212][9748 ] GC(108) Pause Young (Concurrent Start) (G1 Evacuation Pause) 121M->121M(128M) 1.217ms
        [2020-10-28T10:45:28.951+0800][1.553s][3212][13364] GC(110) Concurrent Cycle
        [2020-10-28T10:45:28.963+0800][1.566s][3212][9748 ] GC(109) Pause Full (G1 Evacuation Pause) 121M->120M(128M) 12.516ms
        [2020-10-28T10:45:28.964+0800][1.566s][3212][13364] GC(110) Concurrent Cycle 13.352ms
        [2020-10-28T10:45:28.964+0800][1.567s][3212][9748 ] GC(111) To-space exhausted
        [2020-10-28T10:45:28.965+0800][1.568s][3212][9748 ] GC(111) Pause Young (Normal) (G1 Evacuation Pause) 121M->121M(128M) 0.999ms
        [2020-10-28T10:45:28.979+0800][1.582s][3212][9748 ] GC(112) Pause Full (G1 Evacuation Pause) 121M->119M(128M) 13.475ms
        [2020-10-28T10:45:28.981+0800][1.583s][3212][9748 ] GC(113) Pause Young (Concurrent Start) (G1 Humongous Allocation) 120M->119M(128M) 0.743ms
        [2020-10-28T10:45:28.981+0800][1.584s][3212][13364] GC(114) Concurrent Cycle
        [2020-10-28T10:45:28.982+0800][1.585s][3212][9748 ] GC(115) To-space exhausted
        [2020-10-28T10:45:28.983+0800][1.586s][3212][9748 ] GC(115) Pause Young (Normal) (G1 Evacuation Pause) 120M->119M(128M) 1.786ms
        [2020-10-28T10:45:28.985+0800][1.588s][3212][9748 ] GC(116) To-space exhausted
        [2020-10-28T10:45:28.986+0800][1.589s][3212][9748 ] GC(116) Pause Young (Normal) (G1 Humongous Allocation) 120M->120M(128M) 1.595ms
        [2020-10-28T10:45:28.996+0800][1.598s][3212][9748 ] GC(117) Pause Full (G1 Humongous Allocation) 120M->119M(128M) 9.047ms
        [2020-10-28T10:45:28.997+0800][1.600s][3212][9748 ] GC(118) Pause Young (Normal) (G1 Evacuation Pause) 120M->119M(128M) 0.588ms
        [2020-10-28T10:45:28.998+0800][1.600s][3212][13364] GC(114) Concurrent Cycle 16.545ms
        [2020-10-28T10:45:28.998+0800][1.601s][3212][9748 ] GC(119) To-space exhausted
        [2020-10-28T10:45:29.000+0800][1.602s][3212][9748 ] GC(119) Pause Young (Normal) (G1 Evacuation Pause) 120M->120M(128M) 1.627ms
        [2020-10-28T10:45:29.009+0800][1.611s][3212][9748 ] GC(120) Pause Full (G1 Evacuation Pause) 120M->119M(128M) 8.304ms
        [2020-10-28T10:45:29.011+0800][1.613s][3212][9748 ] GC(121) Pause Young (Concurrent Start) (G1 Humongous Allocation) 120M->119M(128M) 0.710ms
        [2020-10-28T10:45:29.011+0800][1.613s][3212][13364] GC(122) Concurrent Cycle
        [2020-10-28T10:45:29.012+0800][1.615s][3212][9748 ] GC(123) Pause Young (Normal) (G1 Evacuation Pause) 120M->119M(128M) 0.932ms
        [2020-10-28T10:45:29.013+0800][1.615s][3212][9748 ] GC(124) To-space exhausted
        [2020-10-28T10:45:29.014+0800][1.616s][3212][9748 ] GC(124) Pause Young (Normal) (G1 Evacuation Pause) 120M->120M(128M) 0.994ms
        [2020-10-28T10:45:29.023+0800][1.625s][3212][9748 ] GC(125) Pause Full (G1 Evacuation Pause) 120M->120M(128M) 8.846ms
        [2020-10-28T10:45:29.024+0800][1.626s][3212][13364] GC(122) Concurrent Cycle 12.755ms
        [2020-10-28T10:45:29.024+0800][1.627s][3212][9748 ] GC(126) Pause Young (Normal) (G1 Humongous Allocation) 120M->120M(128M) 0.485ms
        [2020-10-28T10:45:29.027+0800][1.629s][3212][9748 ] GC(127) Pause Young (Concurrent Start) (G1 Evacuation Pause) 121M->121M(128M) 1.266ms
        [2020-10-28T10:45:29.027+0800][1.630s][3212][13364] GC(129) Concurrent Cycle
        [2020-10-28T10:45:29.038+0800][1.640s][3212][9748 ] GC(128) Pause Full (G1 Evacuation Pause) 121M->121M(128M) 10.394ms
        [2020-10-28T10:45:29.050+0800][1.653s][3212][9748 ] GC(130) Pause Full (G1 Evacuation Pause) 121M->121M(128M) 11.936ms
        [2020-10-28T10:45:29.051+0800][1.654s][3212][13364] GC(129) Concurrent Cycle 24.029ms
        [2020-10-28T10:45:29.052+0800][1.654s][3212][9748 ] GC(131) Pause Young (Normal) (G1 Evacuation Pause) 121M->121M(128M) 0.614ms
        [2020-10-28T10:45:29.063+0800][1.665s][3212][9748 ] GC(132) Pause Full (G1 Evacuation Pause) 121M->121M(128M) 10.656ms
        [2020-10-28T10:45:29.082+0800][1.685s][3212][9748 ] GC(133) Pause Full (G1 Evacuation Pause) 121M->121M(128M) 17.891ms
        [2020-10-28T10:45:29.084+0800][1.687s][3212][9748 ] GC(134) Pause Young (Concurrent Start) (G1 Evacuation Pause) 121M->121M(128M) 0.723ms
        [2020-10-28T10:45:29.085+0800][1.687s][3212][13364] GC(136) Concurrent Cycle
        [2020-10-28T10:45:29.096+0800][1.699s][3212][9748 ] GC(135) Pause Full (G1 Evacuation Pause) 121M->4M(128M) 11.293ms
        [2020-10-28T10:45:29.098+0800][1.700s][3212][13364] GC(136) Concurrent Cycle 12.947ms
        Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
                at GCLogAnalysis.generateGarbage(GCLogAnalysis.java:45)
                at GCLogAnalysis.main(GCLogAnalysis.java:25)
        [2020-10-28T10:45:29.101+0800][1.704s][3212][15140] Heap
        [2020-10-28T10:45:29.101+0800][1.704s][3212][15140]  garbage-first heap   total 131072K, used 4588K [0x00000000f8000000, 0x0000000100000000)
        [2020-10-28T10:45:29.102+0800][1.704s][3212][15140]   region size 1024K, 1 young (1024K), 0 survivors (0K)
        [2020-10-28T10:45:29.102+0800][1.704s][3212][15140]  Metaspace       used 16359K, capacity 16784K, committed 17024K, reserved 1064960K
        [2020-10-28T10:45:29.102+0800][1.705s][3212][15140]   class space    used 1719K, capacity 1872K, committed 1920K, reserved 1048576K

## 256MB
执行命令行：

        java -XX:+UseG1GC -Xms256m -Xmx256m -Xlog:gc*:file=gc.g1.256m.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：

        PS C:\Data\Code\GitHub\JAVA-000\Week_02> java -XX:+UseG1GC -Xms256m -Xmx256m -Xlog:gc*:file=gc.g1.256m.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java
        [2020-10-28T10:47:00.219+0800][0.026s][14104][14604] Using G1
        [2020-10-28T10:47:00.917+0800][0.724s][14104][6784 ] GC(0) Pause Young (Normal) (G1 Evacuation Pause) 26M->9M(256M) 13.263ms
        正在执行...
        [2020-10-28T10:47:01.156+0800][0.963s][14104][6784 ] GC(1) Pause Young (Normal) (G1 Evacuation Pause) 160M->68M(256M) 12.653ms
        [2020-10-28T10:47:01.206+0800][1.013s][14104][6784 ] GC(2) Pause Young (Normal) (G1 Evacuation Pause) 129M->96M(256M) 37.680ms
        [2020-10-28T10:47:01.224+0800][1.031s][14104][6784 ] GC(3) Pause Young (Concurrent Start) (G1 Humongous Allocation) 153M->117M(256M) 4.437ms
        [2020-10-28T10:47:01.225+0800][1.032s][14104][12216] GC(4) Concurrent Cycle
        [2020-10-28T10:47:01.237+0800][1.044s][14104][6784 ] GC(4) Pause Remark 151M->151M(256M) 2.357ms
        [2020-10-28T10:47:01.245+0800][1.052s][14104][6784 ] GC(4) Pause Cleanup 176M->176M(256M) 0.188ms
        [2020-10-28T10:47:01.246+0800][1.053s][14104][12216] GC(4) Concurrent Cycle 21.479ms
        [2020-10-28T10:47:01.256+0800][1.062s][14104][6784 ] GC(5) Pause Young (Concurrent Start) (G1 Humongous Allocation) 180M->150M(256M) 6.889ms
        [2020-10-28T10:47:01.256+0800][1.063s][14104][12216] GC(6) Concurrent Cycle
        [2020-10-28T10:47:01.274+0800][1.081s][14104][6784 ] GC(6) Pause Remark 196M->196M(256M) 1.770ms
        [2020-10-28T10:47:01.282+0800][1.089s][14104][6784 ] GC(7) Pause Young (Normal) (G1 Evacuation Pause) 209M->178M(256M) 5.215ms
        [2020-10-28T10:47:01.288+0800][1.094s][14104][6784 ] GC(6) Pause Cleanup 189M->189M(256M) 0.129ms
        [2020-10-28T10:47:01.289+0800][1.096s][14104][12216] GC(6) Concurrent Cycle 33.280ms
        [2020-10-28T10:47:01.301+0800][1.108s][14104][6784 ] GC(8) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 219M->191M(256M) 5.604ms
        [2020-10-28T10:47:01.310+0800][1.117s][14104][6784 ] GC(9) Pause Young (Mixed) (G1 Evacuation Pause) 205M->195M(256M) 4.021ms
        [2020-10-28T10:47:01.312+0800][1.119s][14104][6784 ] GC(10) Pause Young (Concurrent Start) (G1 Humongous Allocation) 197M->196M(256M) 1.391ms
        [2020-10-28T10:47:01.313+0800][1.120s][14104][12216] GC(11) Concurrent Cycle
        [2020-10-28T10:47:01.321+0800][1.128s][14104][6784 ] GC(12) Pause Young (Normal) (G1 Evacuation Pause) 221M->202M(256M) 1.836ms
        [2020-10-28T10:47:01.329+0800][1.136s][14104][6784 ] GC(13) Pause Young (Normal) (G1 Evacuation Pause) 221M->208M(256M) 1.955ms
        [2020-10-28T10:47:01.333+0800][1.140s][14104][6784 ] GC(11) Pause Remark 210M->210M(256M) 2.586ms
        [2020-10-28T10:47:01.339+0800][1.146s][14104][6784 ] GC(14) Pause Young (Normal) (G1 Evacuation Pause) 228M->215M(256M) 2.068ms
        [2020-10-28T10:47:01.342+0800][1.149s][14104][6784 ] GC(11) Pause Cleanup 223M->223M(256M) 0.142ms
        [2020-10-28T10:47:01.343+0800][1.150s][14104][12216] GC(11) Concurrent Cycle 30.278ms
        [2020-10-28T10:47:01.347+0800][1.154s][14104][6784 ] GC(15) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 234M->224M(256M) 2.108ms
        [2020-10-28T10:47:01.356+0800][1.163s][14104][6784 ] GC(16) To-space exhausted
        [2020-10-28T10:47:01.357+0800][1.164s][14104][6784 ] GC(16) Pause Young (Mixed) (G1 Evacuation Pause) 237M->231M(256M) 4.984ms
        [2020-10-28T10:47:01.361+0800][1.168s][14104][6784 ] GC(17) To-space exhausted
        [2020-10-28T10:47:01.362+0800][1.169s][14104][6784 ] GC(17) Pause Young (Mixed) (G1 Evacuation Pause) 254M->246M(256M) 1.341ms
        [2020-10-28T10:47:01.367+0800][1.173s][14104][6784 ] GC(18) Pause Young (Concurrent Start) (G1 Humongous Allocation) 252M->248M(256M) 1.261ms
        [2020-10-28T10:47:01.367+0800][1.174s][14104][12216] GC(19) Concurrent Cycle
        [2020-10-28T10:47:01.368+0800][1.175s][14104][6784 ] GC(20) To-space exhausted
        [2020-10-28T10:47:01.369+0800][1.176s][14104][6784 ] GC(20) Pause Young (Normal) (G1 Evacuation Pause) 255M->253M(256M) 1.188ms
        [2020-10-28T10:47:01.371+0800][1.178s][14104][6784 ] GC(21) To-space exhausted
        [2020-10-28T10:47:01.372+0800][1.179s][14104][6784 ] GC(21) Pause Young (Normal) (G1 Evacuation Pause) 254M->254M(256M) 1.644ms
        [2020-10-28T10:47:01.373+0800][1.180s][14104][6784 ] GC(22) To-space exhausted
        [2020-10-28T10:47:01.373+0800][1.180s][14104][6784 ] GC(22) Pause Young (Normal) (G1 Evacuation Pause) 255M->255M(256M) 0.771ms
        [2020-10-28T10:47:01.412+0800][1.219s][14104][6784 ] GC(23) Pause Full (G1 Evacuation Pause) 255M->195M(256M) 37.985ms
        [2020-10-28T10:47:01.412+0800][1.219s][14104][12216] GC(19) Concurrent Cycle 45.581ms
        [2020-10-28T10:47:01.415+0800][1.222s][14104][6784 ] GC(24) Pause Young (Concurrent Start) (G1 Humongous Allocation) 196M->196M(256M) 2.493ms
        [2020-10-28T10:47:01.416+0800][1.223s][14104][12216] GC(25) Concurrent Cycle
        [2020-10-28T10:47:01.423+0800][1.229s][14104][6784 ] GC(26) Pause Young (Normal) (G1 Evacuation Pause) 216M->204M(256M) 1.349ms
        [2020-10-28T10:47:01.427+0800][1.234s][14104][6784 ] GC(25) Pause Remark 216M->216M(256M) 1.556ms
        [2020-10-28T10:47:01.430+0800][1.237s][14104][6784 ] GC(27) Pause Young (Normal) (G1 Evacuation Pause) 217M->210M(256M) 1.970ms
        [2020-10-28T10:47:01.434+0800][1.241s][14104][6784 ] GC(25) Pause Cleanup 221M->221M(256M) 0.205ms
        [2020-10-28T10:47:01.436+0800][1.243s][14104][12216] GC(25) Concurrent Cycle 20.466ms
        [2020-10-28T10:47:01.439+0800][1.246s][14104][6784 ] GC(28) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 229M->216M(256M) 1.727ms
        [2020-10-28T10:47:01.443+0800][1.250s][14104][6784 ] GC(29) Pause Young (Mixed) (G1 Evacuation Pause) 228M->218M(256M) 1.795ms
        [2020-10-28T10:47:01.445+0800][1.252s][14104][6784 ] GC(30) Pause Young (Concurrent Start) (G1 Humongous Allocation) 220M->219M(256M) 1.080ms
        [2020-10-28T10:47:01.446+0800][1.253s][14104][12216] GC(31) Concurrent Cycle
        [2020-10-28T10:47:01.454+0800][1.261s][14104][6784 ] GC(32) To-space exhausted
        [2020-10-28T10:47:01.455+0800][1.262s][14104][6784 ] GC(32) Pause Young (Normal) (G1 Evacuation Pause) 238M->230M(256M) 2.496ms
        [2020-10-28T10:47:01.459+0800][1.266s][14104][6784 ] GC(31) Pause Remark 239M->239M(256M) 1.408ms
        [2020-10-28T10:47:01.460+0800][1.267s][14104][6784 ] GC(33) To-space exhausted
        [2020-10-28T10:47:01.461+0800][1.267s][14104][6784 ] GC(33) Pause Young (Normal) (G1 Evacuation Pause) 239M->235M(256M) 0.921ms
        [2020-10-28T10:47:01.462+0800][1.269s][14104][6784 ] GC(34) To-space exhausted
        [2020-10-28T10:47:01.463+0800][1.270s][14104][6784 ] GC(34) Pause Young (Normal) (G1 Humongous Allocation) 238M->238M(256M) 1.172ms
        [2020-10-28T10:47:01.505+0800][1.312s][14104][6784 ] GC(35) Pause Full (G1 Humongous Allocation) 238M->215M(256M) 41.103ms
        [2020-10-28T10:47:01.505+0800][1.313s][14104][12216] GC(31) Concurrent Cycle 60.397ms
        [2020-10-28T10:47:01.508+0800][1.316s][14104][6784 ] GC(36) Pause Young (Concurrent Start) (G1 Humongous Allocation) 219M->216M(256M) 0.980ms
        [2020-10-28T10:47:01.509+0800][1.316s][14104][12216] GC(37) Concurrent Cycle
        [2020-10-28T10:47:01.513+0800][1.320s][14104][6784 ] GC(38) To-space exhausted
        [2020-10-28T10:47:01.513+0800][1.321s][14104][6784 ] GC(38) Pause Young (Normal) (G1 Evacuation Pause) 234M->233M(256M) 1.537ms
        [2020-10-28T10:47:01.518+0800][1.325s][14104][6784 ] GC(39) To-space exhausted
        [2020-10-28T10:47:01.518+0800][1.326s][14104][6784 ] GC(39) Pause Young (Normal) (G1 Humongous Allocation) 236M->236M(256M) 1.074ms
        [2020-10-28T10:47:01.548+0800][1.355s][14104][6784 ] GC(40) Pause Full (G1 Humongous Allocation) 236M->225M(256M) 29.061ms
        [2020-10-28T10:47:01.549+0800][1.357s][14104][12216] GC(37) Concurrent Cycle 40.346ms
        [2020-10-28T10:47:01.553+0800][1.361s][14104][6784 ] GC(41) Pause Young (Concurrent Start) (G1 Humongous Allocation) 228M->226M(256M) 1.709ms
        [2020-10-28T10:47:01.553+0800][1.361s][14104][12216] GC(42) Concurrent Cycle
        [2020-10-28T10:47:01.556+0800][1.363s][14104][6784 ] GC(43) To-space exhausted
        [2020-10-28T10:47:01.556+0800][1.364s][14104][6784 ] GC(43) Pause Young (Normal) (G1 Humongous Allocation) 238M->236M(256M) 0.997ms
        [2020-10-28T10:47:01.557+0800][1.365s][14104][6784 ] GC(44) To-space exhausted
        [2020-10-28T10:47:01.558+0800][1.365s][14104][6784 ] GC(44) Pause Young (Normal) (G1 Humongous Allocation) 237M->237M(256M) 0.824ms
        [2020-10-28T10:47:01.589+0800][1.397s][14104][6784 ] GC(45) Pause Full (G1 Humongous Allocation) 237M->230M(256M) 31.083ms
        [2020-10-28T10:47:01.590+0800][1.398s][14104][12216] GC(42) Concurrent Cycle 36.557ms
        [2020-10-28T10:47:01.591+0800][1.398s][14104][6784 ] GC(46) Pause Young (Concurrent Start) (G1 Humongous Allocation) 232M->232M(256M) 0.754ms
        [2020-10-28T10:47:01.591+0800][1.399s][14104][12216] GC(47) Concurrent Cycle
        [2020-10-28T10:47:01.592+0800][1.400s][14104][6784 ] GC(48) To-space exhausted
        [2020-10-28T10:47:01.593+0800][1.401s][14104][6784 ] GC(48) Pause Young (Normal) (G1 Humongous Allocation) 237M->235M(256M) 0.945ms
        [2020-10-28T10:47:01.594+0800][1.402s][14104][6784 ] GC(49) Pause Young (Normal) (G1 Evacuation Pause) 237M->236M(256M) 0.521ms
        [2020-10-28T10:47:01.595+0800][1.403s][14104][6784 ] GC(50) To-space exhausted
        [2020-10-28T10:47:01.596+0800][1.403s][14104][6784 ] GC(50) Pause Young (Normal) (G1 Humongous Allocation) 237M->237M(256M) 1.098ms
        [2020-10-28T10:47:01.629+0800][1.436s][14104][6784 ] GC(51) Pause Full (G1 Humongous Allocation) 237M->233M(256M) 31.306ms
        [2020-10-28T10:47:01.630+0800][1.437s][14104][12216] GC(47) Concurrent Cycle 38.051ms
        [2020-10-28T10:47:01.632+0800][1.439s][14104][6784 ] GC(52) Pause Young (Concurrent Start) (G1 Humongous Allocation) 235M->233M(256M) 1.743ms
        [2020-10-28T10:47:01.633+0800][1.440s][14104][12216] GC(53) Concurrent Cycle
        [2020-10-28T10:47:01.635+0800][1.442s][14104][6784 ] GC(54) To-space exhausted
        [2020-10-28T10:47:01.636+0800][1.443s][14104][6784 ] GC(54) Pause Young (Normal) (G1 Evacuation Pause) 238M->237M(256M) 1.084ms
        [2020-10-28T10:47:01.637+0800][1.444s][14104][6784 ] GC(55) To-space exhausted
        [2020-10-28T10:47:01.638+0800][1.445s][14104][6784 ] GC(55) Pause Young (Normal) (G1 Evacuation Pause) 238M->238M(256M) 1.474ms
        [2020-10-28T10:47:01.659+0800][1.466s][14104][6784 ] GC(56) Pause Full (G1 Evacuation Pause) 238M->235M(256M) 20.076ms
        [2020-10-28T10:47:01.660+0800][1.467s][14104][12216] GC(53) Concurrent Cycle 27.631ms
        [2020-10-28T10:47:01.663+0800][1.470s][14104][6784 ] GC(57) Pause Young (Concurrent Start) (G1 Humongous Allocation) 236M->236M(256M) 2.044ms
        [2020-10-28T10:47:01.664+0800][1.471s][14104][12216] GC(58) Concurrent Cycle
        [2020-10-28T10:47:01.665+0800][1.472s][14104][6784 ] GC(59) To-space exhausted
        [2020-10-28T10:47:01.665+0800][1.473s][14104][6784 ] GC(59) Pause Young (Normal) (G1 Evacuation Pause) 237M->236M(256M) 1.142ms
        [2020-10-28T10:47:01.667+0800][1.474s][14104][6784 ] GC(60) To-space exhausted
        [2020-10-28T10:47:01.668+0800][1.475s][14104][6784 ] GC(60) Pause Young (Normal) (G1 Evacuation Pause) 237M->237M(256M) 0.936ms
        [2020-10-28T10:47:01.689+0800][1.497s][14104][6784 ] GC(61) Pause Full (G1 Evacuation Pause) 237M->236M(256M) 21.199ms
        [2020-10-28T10:47:01.690+0800][1.497s][14104][12216] GC(58) Concurrent Cycle 26.277ms
        [2020-10-28T10:47:01.691+0800][1.498s][14104][6784 ] GC(62) Pause Young (Concurrent Start) (G1 Humongous Allocation) 239M->237M(256M) 0.701ms
        [2020-10-28T10:47:01.692+0800][1.499s][14104][12216] GC(63) Concurrent Cycle
        [2020-10-28T10:47:01.693+0800][1.500s][14104][6784 ] GC(64) To-space exhausted
        [2020-10-28T10:47:01.693+0800][1.500s][14104][6784 ] GC(64) Pause Young (Normal) (G1 Humongous Allocation) 239M->238M(256M) 1.046ms
        [2020-10-28T10:47:01.694+0800][1.501s][14104][6784 ] GC(65) To-space exhausted
        [2020-10-28T10:47:01.695+0800][1.502s][14104][6784 ] GC(65) Pause Young (Normal) (G1 Evacuation Pause) 240M->240M(256M) 0.809ms
        [2020-10-28T10:47:01.725+0800][1.532s][14104][6784 ] GC(66) Pause Full (G1 Evacuation Pause) 240M->237M(256M) 30.218ms
        [2020-10-28T10:47:01.726+0800][1.533s][14104][12216] GC(63) Concurrent Cycle 34.464ms
        [2020-10-28T10:47:01.727+0800][1.534s][14104][6784 ] GC(67) To-space exhausted
        [2020-10-28T10:47:01.727+0800][1.534s][14104][6784 ] GC(67) Pause Young (Concurrent Start) (G1 Humongous Allocation) 240M->238M(256M) 1.018ms
        [2020-10-28T10:47:01.728+0800][1.535s][14104][12216] GC(68) Concurrent Cycle
        [2020-10-28T10:47:01.728+0800][1.535s][14104][6784 ] GC(69) To-space exhausted
        [2020-10-28T10:47:01.729+0800][1.536s][14104][6784 ] GC(69) Pause Young (Normal) (G1 Evacuation Pause) 240M->239M(256M) 1.010ms
        [2020-10-28T10:47:01.730+0800][1.537s][14104][6784 ] GC(70) To-space exhausted
        [2020-10-28T10:47:01.731+0800][1.538s][14104][6784 ] GC(70) Pause Young (Normal) (G1 Evacuation Pause) 240M->240M(256M) 1.241ms
        [2020-10-28T10:47:01.751+0800][1.558s][14104][6784 ] GC(71) Pause Full (G1 Evacuation Pause) 240M->238M(256M) 17.805ms
        [2020-10-28T10:47:01.752+0800][1.559s][14104][12216] GC(68) Concurrent Cycle 24.171ms
        [2020-10-28T10:47:01.754+0800][1.561s][14104][6784 ] GC(72) Pause Young (Concurrent Start) (G1 Humongous Allocation) 239M->239M(256M) 1.883ms
        [2020-10-28T10:47:01.754+0800][1.561s][14104][12216] GC(73) Concurrent Cycle
        [2020-10-28T10:47:01.755+0800][1.562s][14104][6784 ] GC(74) To-space exhausted
        [2020-10-28T10:47:01.756+0800][1.563s][14104][6784 ] GC(74) Pause Young (Normal) (G1 Evacuation Pause) 240M->240M(256M) 0.879ms
        [2020-10-28T10:47:01.783+0800][1.590s][14104][6784 ] GC(75) Pause Full (G1 Evacuation Pause) 240M->239M(256M) 27.114ms
        [2020-10-28T10:47:01.785+0800][1.592s][14104][12216] GC(73) Concurrent Cycle 30.381ms
        [2020-10-28T10:47:01.787+0800][1.594s][14104][6784 ] GC(76) To-space exhausted
        [2020-10-28T10:47:01.787+0800][1.594s][14104][6784 ] GC(76) Pause Young (Normal) (G1 Evacuation Pause) 242M->242M(256M) 1.654ms
        [2020-10-28T10:47:01.807+0800][1.614s][14104][6784 ] GC(77) Pause Full (G1 Evacuation Pause) 242M->239M(256M) 19.685ms
        [2020-10-28T10:47:01.809+0800][1.616s][14104][6784 ] GC(78) Pause Young (Concurrent Start) (G1 Humongous Allocation) 239M->239M(256M) 0.662ms
        [2020-10-28T10:47:01.809+0800][1.616s][14104][12216] GC(79) Concurrent Cycle
        [2020-10-28T10:47:01.810+0800][1.617s][14104][6784 ] GC(80) To-space exhausted
        [2020-10-28T10:47:01.810+0800][1.617s][14104][6784 ] GC(80) Pause Young (Normal) (G1 Evacuation Pause) 241M->241M(256M) 0.893ms
        [2020-10-28T10:47:01.827+0800][1.634s][14104][6784 ] GC(81) Pause Full (G1 Evacuation Pause) 241M->241M(256M) 15.743ms
        [2020-10-28T10:47:01.827+0800][1.634s][14104][12216] GC(79) Concurrent Cycle 18.438ms
        [2020-10-28T10:47:01.828+0800][1.635s][14104][6784 ] GC(82) To-space exhausted
        [2020-10-28T10:47:01.829+0800][1.636s][14104][6784 ] GC(82) Pause Young (Concurrent Start) (G1 Humongous Allocation) 241M->241M(256M) 1.121ms
        [2020-10-28T10:47:01.829+0800][1.636s][14104][12216] GC(83) Concurrent Cycle
        [2020-10-28T10:47:01.831+0800][1.638s][14104][6784 ] GC(84) Pause Young (Normal) (G1 Humongous Allocation) 241M->241M(256M) 1.844ms
        [2020-10-28T10:47:01.850+0800][1.657s][14104][6784 ] GC(85) Pause Full (G1 Humongous Allocation) 241M->241M(256M) 18.165ms
        [2020-10-28T10:47:01.852+0800][1.659s][14104][12216] GC(83) Concurrent Cycle 22.429ms
        [2020-10-28T10:47:01.853+0800][1.660s][14104][6784 ] GC(86) Pause Young (Normal) (G1 Evacuation Pause) 242M->242M(256M) 1.005ms
        [2020-10-28T10:47:01.863+0800][1.670s][14104][6784 ] GC(87) Pause Full (G1 Evacuation Pause) 242M->241M(256M) 9.591ms
        [2020-10-28T10:47:01.868+0800][1.675s][14104][6784 ] GC(88) To-space exhausted
        [2020-10-28T10:47:01.869+0800][1.676s][14104][6784 ] GC(88) Pause Young (Concurrent Start) (G1 Humongous Allocation) 242M->242M(256M) 3.972ms
        [2020-10-28T10:47:01.869+0800][1.677s][14104][12216] GC(89) Concurrent Cycle
        [2020-10-28T10:47:01.872+0800][1.680s][14104][6784 ] GC(90) Pause Young (Normal) (G1 Humongous Allocation) 242M->242M(256M) 0.732ms
        [2020-10-28T10:47:01.889+0800][1.696s][14104][6784 ] GC(91) Pause Full (G1 Humongous Allocation) 242M->242M(256M) 14.491ms
        [2020-10-28T10:47:01.890+0800][1.697s][14104][6784 ] GC(92) Pause Young (Normal) (G1 Evacuation Pause) 243M->242M(256M) 0.481ms
        [2020-10-28T10:47:01.890+0800][1.697s][14104][12216] GC(89) Concurrent Cycle 20.729ms
        [2020-10-28T10:47:01.891+0800][1.698s][14104][6784 ] GC(93) To-space exhausted
        [2020-10-28T10:47:01.891+0800][1.698s][14104][6784 ] GC(93) Pause Young (Concurrent Start) (G1 Humongous Allocation) 242M->242M(256M) 0.972ms
        [2020-10-28T10:47:01.892+0800][1.699s][14104][12216] GC(94) Concurrent Cycle
        [2020-10-28T10:47:01.893+0800][1.700s][14104][6784 ] GC(95) Pause Young (Normal) (G1 Humongous Allocation) 242M->242M(256M) 0.867ms
        [2020-10-28T10:47:01.908+0800][1.715s][14104][6784 ] GC(96) Pause Full (G1 Humongous Allocation) 242M->242M(256M) 15.229ms
        [2020-10-28T10:47:01.910+0800][1.717s][14104][6784 ] GC(97) Pause Young (Normal) (G1 Evacuation Pause) 243M->242M(256M) 0.562ms
        [2020-10-28T10:47:01.910+0800][1.718s][14104][12216] GC(94) Concurrent Cycle 18.866ms
        [2020-10-28T10:47:01.911+0800][1.718s][14104][6784 ] GC(98) To-space exhausted
        [2020-10-28T10:47:01.912+0800][1.719s][14104][6784 ] GC(98) Pause Young (Normal) (G1 Evacuation Pause) 243M->243M(256M) 0.996ms
        [2020-10-28T10:47:01.923+0800][1.730s][14104][6784 ] GC(99) Pause Full (G1 Evacuation Pause) 243M->243M(256M) 11.152ms
        [2020-10-28T10:47:01.926+0800][1.733s][14104][6784 ] GC(100) To-space exhausted
        [2020-10-28T10:47:01.927+0800][1.734s][14104][6784 ] GC(100) Pause Young (Concurrent Start) (G1 Humongous Allocation) 243M->243M(256M) 1.262ms
        [2020-10-28T10:47:01.927+0800][1.734s][14104][12216] GC(101) Concurrent Cycle
        [2020-10-28T10:47:01.928+0800][1.735s][14104][6784 ] GC(102) Pause Young (Normal) (G1 Humongous Allocation) 243M->243M(256M) 0.897ms
        [2020-10-28T10:47:01.945+0800][1.752s][14104][6784 ] GC(103) Pause Full (G1 Humongous Allocation) 243M->242M(256M) 16.039ms
        [2020-10-28T10:47:01.946+0800][1.753s][14104][6784 ] GC(104) Pause Young (Normal) (G1 Evacuation Pause) 243M->242M(256M) 0.519ms
        [2020-10-28T10:47:01.947+0800][1.754s][14104][12216] GC(101) Concurrent Cycle 19.754ms
        [2020-10-28T10:47:01.947+0800][1.755s][14104][6784 ] GC(105) To-space exhausted
        [2020-10-28T10:47:01.948+0800][1.755s][14104][6784 ] GC(105) Pause Young (Normal) (G1 Evacuation Pause) 243M->243M(256M) 1.126ms
        [2020-10-28T10:47:01.960+0800][1.767s][14104][6784 ] GC(106) Pause Full (G1 Evacuation Pause) 243M->242M(256M) 11.045ms
        [2020-10-28T10:47:01.961+0800][1.768s][14104][6784 ] GC(107) To-space exhausted
        [2020-10-28T10:47:01.961+0800][1.768s][14104][6784 ] GC(107) Pause Young (Concurrent Start) (G1 Evacuation Pause) 243M->243M(256M) 0.996ms
        [2020-10-28T10:47:01.962+0800][1.769s][14104][12216] GC(109) Concurrent Cycle
        [2020-10-28T10:47:01.983+0800][1.790s][14104][6784 ] GC(108) Pause Full (G1 Evacuation Pause) 243M->242M(256M) 21.167ms
        [2020-10-28T10:47:01.984+0800][1.791s][14104][12216] GC(109) Concurrent Cycle 22.144ms
        [2020-10-28T10:47:01.985+0800][1.792s][14104][6784 ] GC(110) To-space exhausted
        [2020-10-28T10:47:01.986+0800][1.793s][14104][6784 ] GC(110) Pause Young (Normal) (G1 Evacuation Pause) 243M->243M(256M) 1.785ms
        [2020-10-28T10:47:02.011+0800][1.819s][14104][6784 ] GC(111) Pause Full (G1 Evacuation Pause) 243M->241M(256M) 24.548ms
        [2020-10-28T10:47:02.015+0800][1.822s][14104][6784 ] GC(112) To-space exhausted
        [2020-10-28T10:47:02.016+0800][1.823s][14104][6784 ] GC(112) Pause Young (Concurrent Start) (G1 Evacuation Pause) 242M->242M(256M) 3.044ms
        [2020-10-28T10:47:02.016+0800][1.823s][14104][12216] GC(114) Concurrent Cycle
        [2020-10-28T10:47:02.031+0800][1.838s][14104][6784 ] GC(113) Pause Full (G1 Evacuation Pause) 242M->241M(256M) 14.095ms
        [2020-10-28T10:47:02.032+0800][1.839s][14104][12216] GC(114) Concurrent Cycle 15.961ms
        [2020-10-28T10:47:02.034+0800][1.841s][14104][6784 ] GC(115) To-space exhausted
        [2020-10-28T10:47:02.036+0800][1.843s][14104][6784 ] GC(115) Pause Young (Concurrent Start) (G1 Humongous Allocation) 241M->241M(256M) 2.890ms
        [2020-10-28T10:47:02.037+0800][1.844s][14104][12216] GC(116) Concurrent Cycle
        [2020-10-28T10:47:02.038+0800][1.845s][14104][6784 ] GC(117) Pause Young (Normal) (G1 Humongous Allocation) 241M->241M(256M) 1.074ms
        [2020-10-28T10:47:02.050+0800][1.857s][14104][6784 ] GC(118) Pause Full (G1 Humongous Allocation) 241M->241M(256M) 11.293ms
        [2020-10-28T10:47:02.051+0800][1.858s][14104][12216] GC(116) Concurrent Cycle 14.653ms
        [2020-10-28T10:47:02.053+0800][1.860s][14104][6784 ] GC(119) To-space exhausted
        [2020-10-28T10:47:02.054+0800][1.862s][14104][6784 ] GC(119) Pause Young (Concurrent Start) (G1 Humongous Allocation) 243M->243M(256M) 2.694ms
        [2020-10-28T10:47:02.055+0800][1.862s][14104][12216] GC(120) Concurrent Cycle
        [2020-10-28T10:47:02.056+0800][1.863s][14104][6784 ] GC(121) Pause Young (Normal) (G1 Humongous Allocation) 243M->243M(256M) 0.779ms
        [2020-10-28T10:47:02.071+0800][1.878s][14104][6784 ] GC(122) Pause Full (G1 Humongous Allocation) 243M->242M(256M) 14.511ms
        [2020-10-28T10:47:02.073+0800][1.880s][14104][12216] GC(120) Concurrent Cycle 17.841ms
        执行结束!共生成对象次数:3084
        [2020-10-28T10:47:02.095+0800][1.902s][14104][14604] Heap
        [2020-10-28T10:47:02.095+0800][1.903s][14104][14604]  garbage-first heap   total 262144K, used 249375K [0x00000000f0000000, 0x0000000100000000)
        [2020-10-28T10:47:02.096+0800][1.903s][14104][14604]   region size 1024K, 1 young (1024K), 0 survivors (0K)
        [2020-10-28T10:47:02.096+0800][1.903s][14104][14604]  Metaspace       used 16552K, capacity 16955K, committed 17024K, reserved 1064960K
        [2020-10-28T10:47:02.096+0800][1.903s][14104][14604]   class space    used 1750K, capacity 1890K, committed 1920K, reserved 1048576K

## 512MB
执行命令行：

        java -XX:+UseG1GC -Xms512m -Xmx512m -Xlog:gc*:file=gc.g1.512m.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：

        PS C:\Data\Code\GitHub\JAVA-000\Week_02> java -XX:+UseG1GC -Xms512m -Xmx512m -Xlog:gc*:file=gc.g1.512m.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java
        [2020-10-28T10:48:27.457+0800][0.030s][9376][6804] Using G1
        [2020-10-28T10:48:28.136+0800][0.709s][9376][12344] GC(0) Pause Young (Normal) (G1 Evacuation Pause) 27M->9M(512M) 18.645ms
        正在执行...
        [2020-10-28T10:48:28.291+0800][0.864s][9376][12344] GC(1) Pause Young (Normal) (G1 Evacuation Pause) 38M->20M(512M) 8.722ms
        [2020-10-28T10:48:28.328+0800][0.901s][9376][12344] GC(2) Pause Young (Normal) (G1 Evacuation Pause) 68M->41M(512M) 5.157ms
        [2020-10-28T10:48:28.360+0800][0.933s][9376][12344] GC(3) Pause Young (Normal) (G1 Evacuation Pause) 111M->67M(512M) 5.397ms
        [2020-10-28T10:48:28.396+0800][0.969s][9376][12344] GC(4) Pause Young (Normal) (G1 Evacuation Pause) 159M->109M(512M) 8.230ms
        [2020-10-28T10:48:28.444+0800][1.017s][9376][12344] GC(5) Pause Young (Normal) (G1 Evacuation Pause) 235M->152M(512M) 13.402ms
        [2020-10-28T10:48:28.533+0800][1.106s][9376][12344] GC(6) Pause Young (Normal) (G1 Evacuation Pause) 316M->217M(512M) 15.066ms
        [2020-10-28T10:48:28.559+0800][1.132s][9376][12344] GC(7) Pause Young (Concurrent Start) (G1 Humongous Allocation) 294M->239M(512M) 9.652ms
        [2020-10-28T10:48:28.560+0800][1.133s][9376][11736] GC(8) Concurrent Cycle
        [2020-10-28T10:48:28.584+0800][1.157s][9376][12344] GC(8) Pause Remark 281M->281M(512M) 5.883ms
        [2020-10-28T10:48:28.593+0800][1.166s][9376][12344] GC(8) Pause Cleanup 301M->301M(512M) 0.186ms
        [2020-10-28T10:48:28.595+0800][1.168s][9376][11736] GC(8) Concurrent Cycle 34.371ms
        [2020-10-28T10:48:28.657+0800][1.231s][9376][12344] GC(9) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 410M->285M(512M) 13.048ms
        [2020-10-28T10:48:28.668+0800][1.242s][9376][12344] GC(10) Pause Young (Mixed) (G1 Evacuation Pause) 302M->278M(512M) 6.107ms
        [2020-10-28T10:48:28.674+0800][1.247s][9376][12344] GC(11) Pause Young (Concurrent Start) (G1 Humongous Allocation) 279M->279M(512M) 4.503ms
        [2020-10-28T10:48:28.674+0800][1.248s][9376][11736] GC(12) Concurrent Cycle
        [2020-10-28T10:48:28.681+0800][1.255s][9376][12344] GC(12) Pause Remark 305M->303M(512M) 1.457ms
        [2020-10-28T10:48:28.685+0800][1.259s][9376][12344] GC(12) Pause Cleanup 323M->323M(512M) 0.527ms
        [2020-10-28T10:48:28.690+0800][1.264s][9376][11736] GC(12) Concurrent Cycle 16.332ms
        [2020-10-28T10:48:28.709+0800][1.282s][9376][12344] GC(13) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 398M->313M(512M) 9.172ms
        [2020-10-28T10:48:28.722+0800][1.295s][9376][12344] GC(14) Pause Young (Mixed) (G1 Evacuation Pause) 336M->296M(512M) 8.226ms
        [2020-10-28T10:48:28.724+0800][1.298s][9376][12344] GC(15) Pause Young (Concurrent Start) (G1 Humongous Allocation) 299M->296M(512M) 1.331ms
        [2020-10-28T10:48:28.724+0800][1.298s][9376][11736] GC(16) Concurrent Cycle
        [2020-10-28T10:48:28.732+0800][1.305s][9376][12344] GC(16) Pause Remark 333M->333M(512M) 1.501ms
        [2020-10-28T10:48:28.736+0800][1.310s][9376][12344] GC(16) Pause Cleanup 345M->345M(512M) 0.155ms
        [2020-10-28T10:48:28.737+0800][1.311s][9376][11736] GC(16) Concurrent Cycle 12.673ms
        [2020-10-28T10:48:28.751+0800][1.325s][9376][12344] GC(17) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 400M->334M(512M) 6.924ms
        [2020-10-28T10:48:28.765+0800][1.338s][9376][12344] GC(18) Pause Young (Mixed) (G1 Evacuation Pause) 362M->319M(512M) 7.502ms
        [2020-10-28T10:48:28.777+0800][1.351s][9376][12344] GC(19) Pause Young (Concurrent Start) (G1 Humongous Allocation) 369M->334M(512M) 3.783ms
        [2020-10-28T10:48:28.777+0800][1.351s][9376][11736] GC(20) Concurrent Cycle
        [2020-10-28T10:48:28.788+0800][1.362s][9376][12344] GC(20) Pause Remark 379M->379M(512M) 3.469ms
        [2020-10-28T10:48:28.792+0800][1.366s][9376][12344] GC(20) Pause Cleanup 391M->391M(512M) 0.151ms
        [2020-10-28T10:48:28.794+0800][1.367s][9376][11736] GC(20) Concurrent Cycle 16.243ms
        [2020-10-28T10:48:28.807+0800][1.381s][9376][12344] GC(21) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 429M->362M(512M) 8.337ms
        [2020-10-28T10:48:28.819+0800][1.393s][9376][12344] GC(22) Pause Young (Mixed) (G1 Evacuation Pause) 387M->338M(512M) 8.185ms
        [2020-10-28T10:48:28.832+0800][1.406s][9376][12344] GC(23) Pause Young (Mixed) (G1 Evacuation Pause) 384M->357M(512M) 3.570ms
        [2020-10-28T10:48:28.834+0800][1.407s][9376][12344] GC(24) Pause Young (Concurrent Start) (G1 Humongous Allocation) 357M->357M(512M) 1.464ms
        [2020-10-28T10:48:28.834+0800][1.408s][9376][11736] GC(25) Concurrent Cycle
        [2020-10-28T10:48:28.844+0800][1.418s][9376][12344] GC(25) Pause Remark 397M->397M(512M) 1.453ms
        [2020-10-28T10:48:28.848+0800][1.422s][9376][12344] GC(25) Pause Cleanup 417M->417M(512M) 0.248ms
        [2020-10-28T10:48:28.849+0800][1.423s][9376][11736] GC(25) Concurrent Cycle 15.449ms
        [2020-10-28T10:48:28.857+0800][1.431s][9376][12344] GC(26) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 423M->377M(512M) 6.825ms
        [2020-10-28T10:48:28.871+0800][1.445s][9376][12344] GC(27) Pause Young (Mixed) (G1 Evacuation Pause) 409M->356M(512M) 8.564ms
        [2020-10-28T10:48:28.876+0800][1.450s][9376][12344] GC(28) Pause Young (Concurrent Start) (G1 Humongous Allocation) 363M->358M(512M) 2.022ms
        [2020-10-28T10:48:28.877+0800][1.450s][9376][11736] GC(29) Concurrent Cycle
        [2020-10-28T10:48:28.884+0800][1.458s][9376][12344] GC(29) Pause Remark 399M->399M(512M) 1.705ms
        [2020-10-28T10:48:28.891+0800][1.465s][9376][12344] GC(29) Pause Cleanup 418M->418M(512M) 0.157ms
        [2020-10-28T10:48:28.897+0800][1.470s][9376][12344] GC(30) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 421M->383M(512M) 4.300ms
        [2020-10-28T10:48:28.897+0800][1.471s][9376][11736] GC(29) Concurrent Cycle 20.476ms
        [2020-10-28T10:48:28.913+0800][1.487s][9376][12344] GC(31) Pause Young (Mixed) (G1 Evacuation Pause) 407M->368M(512M) 11.458ms
        [2020-10-28T10:48:28.917+0800][1.491s][9376][12344] GC(32) Pause Young (Concurrent Start) (G1 Humongous Allocation) 374M->371M(512M) 2.160ms
        [2020-10-28T10:48:28.917+0800][1.491s][9376][11736] GC(33) Concurrent Cycle
        [2020-10-28T10:48:28.926+0800][1.500s][9376][12344] GC(33) Pause Remark 408M->408M(512M) 1.400ms
        [2020-10-28T10:48:28.934+0800][1.508s][9376][12344] GC(34) Pause Young (Normal) (G1 Evacuation Pause) 426M->389M(512M) 4.272ms
        [2020-10-28T10:48:28.936+0800][1.510s][9376][12344] GC(33) Pause Cleanup 391M->391M(512M) 0.476ms
        [2020-10-28T10:48:28.939+0800][1.513s][9376][11736] GC(33) Concurrent Cycle 21.965ms
        [2020-10-28T10:48:28.949+0800][1.523s][9376][12344] GC(35) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 449M->409M(512M) 4.661ms
        [2020-10-28T10:48:28.965+0800][1.538s][9376][12344] GC(36) Pause Young (Mixed) (G1 Evacuation Pause) 432M->394M(512M) 7.114ms
        [2020-10-28T10:48:28.967+0800][1.541s][9376][12344] GC(37) Pause Young (Concurrent Start) (G1 Humongous Allocation) 394M->394M(512M) 1.587ms
        [2020-10-28T10:48:28.968+0800][1.541s][9376][11736] GC(38) Concurrent Cycle
        [2020-10-28T10:48:28.982+0800][1.555s][9376][12344] GC(39) Pause Young (Normal) (G1 Evacuation Pause) 433M->407M(512M) 3.127ms
        [2020-10-28T10:48:28.989+0800][1.562s][9376][12344] GC(38) Pause Remark 416M->416M(512M) 4.079ms
        [2020-10-28T10:48:28.994+0800][1.568s][9376][12344] GC(38) Pause Cleanup 435M->435M(512M) 0.182ms
        [2020-10-28T10:48:28.998+0800][1.571s][9376][12344] GC(40) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 436M->412M(512M) 2.475ms
        [2020-10-28T10:48:28.999+0800][1.572s][9376][11736] GC(38) Concurrent Cycle 30.980ms
        [2020-10-28T10:48:29.014+0800][1.587s][9376][12344] GC(41) Pause Young (Mixed) (G1 Evacuation Pause) 448M->396M(512M) 7.810ms
        [2020-10-28T10:48:29.018+0800][1.592s][9376][12344] GC(42) Pause Young (Concurrent Start) (G1 Humongous Allocation) 398M->397M(512M) 3.719ms
        [2020-10-28T10:48:29.019+0800][1.592s][9376][11736] GC(43) Concurrent Cycle
        [2020-10-28T10:48:29.026+0800][1.600s][9376][12344] GC(43) Pause Remark 440M->440M(512M) 1.741ms
        [2020-10-28T10:48:29.029+0800][1.603s][9376][12344] GC(44) Pause Young (Normal) (G1 Evacuation Pause) 441M->405M(512M) 2.424ms
        [2020-10-28T10:48:29.033+0800][1.607s][9376][12344] GC(43) Pause Cleanup 423M->423M(512M) 0.327ms
        [2020-10-28T10:48:29.036+0800][1.610s][9376][11736] GC(43) Concurrent Cycle 17.654ms
        [2020-10-28T10:48:29.046+0800][1.620s][9376][12344] GC(45) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 440M->417M(512M) 3.028ms
        [2020-10-28T10:48:29.065+0800][1.639s][9376][12344] GC(46) Pause Young (Mixed) (G1 Evacuation Pause) 450M->410M(512M) 13.463ms
        [2020-10-28T10:48:29.071+0800][1.645s][9376][12344] GC(47) Pause Young (Concurrent Start) (G1 Humongous Allocation) 411M->410M(512M) 2.792ms
        [2020-10-28T10:48:29.071+0800][1.645s][9376][11736] GC(48) Concurrent Cycle
        [2020-10-28T10:48:29.081+0800][1.655s][9376][12344] GC(49) Pause Young (Normal) (G1 Evacuation Pause) 443M->420M(512M) 2.171ms
        [2020-10-28T10:48:29.084+0800][1.658s][9376][12344] GC(48) Pause Remark 426M->426M(512M) 1.868ms
        [2020-10-28T10:48:29.095+0800][1.669s][9376][12344] GC(48) Pause Cleanup 450M->450M(512M) 0.931ms
        [2020-10-28T10:48:29.097+0800][1.671s][9376][11736] GC(48) Concurrent Cycle 25.337ms
        [2020-10-28T10:48:29.100+0800][1.674s][9376][12344] GC(50) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 454M->429M(512M) 3.531ms
        [2020-10-28T10:48:29.121+0800][1.695s][9376][12344] GC(51) Pause Young (Mixed) (G1 Evacuation Pause) 462M->418M(512M) 11.314ms
        [2020-10-28T10:48:29.127+0800][1.700s][9376][12344] GC(52) Pause Young (Concurrent Start) (G1 Humongous Allocation) 418M->417M(512M) 4.402ms
        [2020-10-28T10:48:29.127+0800][1.701s][9376][11736] GC(53) Concurrent Cycle
        [2020-10-28T10:48:29.144+0800][1.718s][9376][12344] GC(54) Pause Young (Normal) (G1 Evacuation Pause) 455M->427M(512M) 4.283ms
        [2020-10-28T10:48:29.147+0800][1.720s][9376][12344] GC(53) Pause Remark 430M->430M(512M) 1.639ms
        [2020-10-28T10:48:29.151+0800][1.724s][9376][12344] GC(53) Pause Cleanup 450M->450M(512M) 0.235ms
        [2020-10-28T10:48:29.153+0800][1.726s][9376][11736] GC(53) Concurrent Cycle 25.419ms
        [2020-10-28T10:48:29.162+0800][1.736s][9376][12344] GC(55) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 456M->435M(512M) 8.076ms
        [2020-10-28T10:48:29.181+0800][1.755s][9376][12344] GC(56) To-space exhausted
        [2020-10-28T10:48:29.182+0800][1.756s][9376][12344] GC(56) Pause Young (Mixed) (G1 Evacuation Pause) 475M->444M(512M) 13.147ms
        [2020-10-28T10:48:29.189+0800][1.763s][9376][12344] GC(57) Pause Young (Concurrent Start) (G1 Humongous Allocation) 448M->444M(512M) 5.568ms
        [2020-10-28T10:48:29.190+0800][1.764s][9376][11736] GC(58) Concurrent Cycle
        [2020-10-28T10:48:29.201+0800][1.775s][9376][12344] GC(59) Pause Young (Normal) (G1 Evacuation Pause) 481M->454M(512M) 3.117ms
        [2020-10-28T10:48:29.208+0800][1.781s][9376][12344] GC(58) Pause Remark 459M->458M(512M) 3.598ms
        [2020-10-28T10:48:29.212+0800][1.786s][9376][12344] GC(58) Pause Cleanup 479M->479M(512M) 0.181ms
        [2020-10-28T10:48:29.217+0800][1.791s][9376][12344] GC(60) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 483M->463M(512M) 3.145ms
        [2020-10-28T10:48:29.217+0800][1.791s][9376][11736] GC(58) Concurrent Cycle 27.191ms
        [2020-10-28T10:48:29.237+0800][1.811s][9376][12344] GC(61) To-space exhausted
        [2020-10-28T10:48:29.239+0800][1.812s][9376][12344] GC(61) Pause Young (Mixed) (G1 Evacuation Pause) 498M->482M(512M) 10.466ms
        [2020-10-28T10:48:29.247+0800][1.821s][9376][12344] GC(62) To-space exhausted
        [2020-10-28T10:48:29.248+0800][1.822s][9376][12344] GC(62) Pause Young (Mixed) (G1 Evacuation Pause) 511M->499M(512M) 1.424ms
        [2020-10-28T10:48:29.249+0800][1.823s][9376][12344] GC(63) Pause Young (Concurrent Start) (G1 Humongous Allocation) 499M->499M(512M) 0.719ms
        [2020-10-28T10:48:29.249+0800][1.823s][9376][11736] GC(64) Concurrent Cycle
        [2020-10-28T10:48:29.255+0800][1.829s][9376][12344] GC(65) To-space exhausted
        [2020-10-28T10:48:29.256+0800][1.829s][9376][12344] GC(65) Pause Young (Normal) (G1 Humongous Allocation) 511M->505M(512M) 2.438ms
        [2020-10-28T10:48:29.258+0800][1.832s][9376][12344] GC(66) To-space exhausted
        [2020-10-28T10:48:29.258+0800][1.832s][9376][12344] GC(66) Pause Young (Normal) (G1 Evacuation Pause) 511M->511M(512M) 0.991ms
        [2020-10-28T10:48:29.316+0800][1.890s][9376][12344] GC(67) Pause Full (G1 Evacuation Pause) 511M->378M(512M) 57.114ms
        [2020-10-28T10:48:29.317+0800][1.891s][9376][11736] GC(64) Concurrent Cycle 68.302ms
        执行结束!共生成对象次数:9028
        [2020-10-28T10:48:29.339+0800][1.913s][9376][6804 ] Heap
        [2020-10-28T10:48:29.340+0800][1.914s][9376][6804 ]  garbage-first heap   total 524288K, used 387733K [0x00000000e0000000, 0x0000000100000000)
        [2020-10-28T10:48:29.340+0800][1.914s][9376][6804 ]   region size 1024K, 1 young (1024K), 0 survivors (0K)
        [2020-10-28T10:48:29.341+0800][1.914s][9376][6804 ]  Metaspace       used 16552K, capacity 16956K, committed 17024K, reserved 1064960K
        [2020-10-28T10:48:29.341+0800][1.915s][9376][6804 ]   class space    used 1746K, capacity 1890K, committed 1920K, reserved 1048576K

## 1GB
执行命令行：

        java -XX:+UseG1GC -Xms1g -Xmx1g -Xlog:gc*:file=gc.g1.1g.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：
        
        PS C:\Data\Code\GitHub\JAVA-000\Week_02> java -XX:+UseG1GC -Xms1g -Xmx1g -Xlog:gc*:file=gc.g1.1g.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java
        [2020-10-28T10:49:28.540+0800][0.044s][9644][13168] Using G1
        正在执行...
        [2020-10-28T10:49:29.384+0800][0.887s][9644][8044 ] GC(0) Pause Young (Normal) (G1 Evacuation Pause) 64M->18M(1024M) 11.011ms
        [2020-10-28T10:49:29.418+0800][0.921s][9644][8044 ] GC(1) Pause Young (Normal) (G1 Evacuation Pause) 85M->44M(1024M) 8.966ms
        [2020-10-28T10:49:29.442+0800][0.945s][9644][8044 ] GC(2) Pause Young (Normal) (G1 Evacuation Pause) 115M->70M(1024M) 4.773ms
        [2020-10-28T10:49:29.475+0800][0.979s][9644][8044 ] GC(3) Pause Young (Normal) (G1 Evacuation Pause) 169M->106M(1024M) 5.675ms
        [2020-10-28T10:49:29.501+0800][1.004s][9644][8044 ] GC(4) Pause Young (Normal) (G1 Evacuation Pause) 191M->139M(1024M) 9.266ms
        [2020-10-28T10:49:29.534+0800][1.037s][9644][8044 ] GC(5) Pause Young (Normal) (G1 Evacuation Pause) 239M->177M(1024M) 10.535ms
        [2020-10-28T10:49:29.571+0800][1.075s][9644][8044 ] GC(6) Pause Young (Normal) (G1 Evacuation Pause) 296M->233M(1024M) 12.142ms
        [2020-10-28T10:49:29.621+0800][1.125s][9644][8044 ] GC(7) Pause Young (Normal) (G1 Evacuation Pause) 382M->289M(1024M) 11.475ms
        [2020-10-28T10:49:29.677+0800][1.181s][9644][8044 ] GC(8) Pause Young (Normal) (G1 Evacuation Pause) 462M->350M(1024M) 12.849ms
        [2020-10-28T10:49:29.726+0800][1.230s][9644][8044 ] GC(9) Pause Young (Normal) (G1 Evacuation Pause) 529M->394M(1024M) 15.239ms
        [2020-10-28T10:49:29.788+0800][1.291s][9644][8044 ] GC(10) Pause Young (Concurrent Start) (G1 Humongous Allocation) 608M->438M(1024M) 15.601ms
        [2020-10-28T10:49:29.789+0800][1.292s][9644][21200] GC(11) Concurrent Cycle
        [2020-10-28T10:49:29.802+0800][1.306s][9644][8044 ] GC(11) Pause Remark 481M->472M(1024M) 2.238ms
        [2020-10-28T10:49:29.817+0800][1.321s][9644][8044 ] GC(11) Pause Cleanup 516M->516M(1024M) 0.591ms
        [2020-10-28T10:49:29.824+0800][1.327s][9644][21200] GC(11) Concurrent Cycle 35.215ms
        [2020-10-28T10:49:29.973+0800][1.477s][9644][8044 ] GC(12) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 853M->529M(1024M) 24.793ms
        [2020-10-28T10:49:29.989+0800][1.493s][9644][8044 ] GC(13) Pause Young (Mixed) (G1 Evacuation Pause) 546M->457M(1024M) 11.259ms
        [2020-10-28T10:49:30.000+0800][1.504s][9644][8044 ] GC(14) Pause Young (Concurrent Start) (G1 Humongous Allocation) 487M->469M(1024M) 4.948ms
        [2020-10-28T10:49:30.001+0800][1.504s][9644][21200] GC(15) Concurrent Cycle
        [2020-10-28T10:49:30.010+0800][1.514s][9644][8044 ] GC(15) Pause Remark 514M->508M(1024M) 1.968ms
        [2020-10-28T10:49:30.017+0800][1.521s][9644][8044 ] GC(15) Pause Cleanup 531M->531M(1024M) 0.581ms
        [2020-10-28T10:49:30.020+0800][1.524s][9644][21200] GC(15) Concurrent Cycle 19.620ms
        [2020-10-28T10:49:30.098+0800][1.602s][9644][8044 ] GC(16) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 857M->543M(1024M) 23.171ms
        [2020-10-28T10:49:30.118+0800][1.621s][9644][8044 ] GC(17) Pause Young (Mixed) (G1 Evacuation Pause) 571M->477M(1024M) 14.773ms
        [2020-10-28T10:49:30.120+0800][1.624s][9644][8044 ] GC(18) Pause Young (Concurrent Start) (G1 Humongous Allocation) 477M->477M(1024M) 1.915ms
        [2020-10-28T10:49:30.120+0800][1.624s][9644][21200] GC(19) Concurrent Cycle
        [2020-10-28T10:49:30.133+0800][1.636s][9644][8044 ] GC(19) Pause Remark 530M->529M(1024M) 1.604ms
        [2020-10-28T10:49:30.137+0800][1.640s][9644][8044 ] GC(19) Pause Cleanup 550M->550M(1024M) 0.314ms
        [2020-10-28T10:49:30.139+0800][1.642s][9644][21200] GC(19) Concurrent Cycle 18.575ms
        [2020-10-28T10:49:30.193+0800][1.697s][9644][8044 ] GC(20) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 778M->548M(1024M) 17.419ms
        [2020-10-28T10:49:30.214+0800][1.717s][9644][8044 ] GC(21) Pause Young (Mixed) (G1 Evacuation Pause) 590M->488M(1024M) 14.169ms
        [2020-10-28T10:49:30.274+0800][1.778s][9644][8044 ] GC(22) Pause Young (Concurrent Start) (G1 Humongous Allocation) 778M->553M(1024M) 14.236ms
        [2020-10-28T10:49:30.275+0800][1.778s][9644][21200] GC(23) Concurrent Cycle
        [2020-10-28T10:49:30.286+0800][1.790s][9644][8044 ] GC(23) Pause Remark 600M->589M(1024M) 1.913ms
        [2020-10-28T10:49:30.291+0800][1.795s][9644][8044 ] GC(23) Pause Cleanup 615M->615M(1024M) 0.317ms
        [2020-10-28T10:49:30.297+0800][1.801s][9644][21200] GC(23) Concurrent Cycle 22.621ms
        [2020-10-28T10:49:30.355+0800][1.858s][9644][8044 ] GC(24) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 820M->611M(1024M) 21.038ms
        执行结束!共生成对象次数:12649
        [2020-10-28T10:49:30.372+0800][1.875s][9644][13168] Heap
        [2020-10-28T10:49:30.373+0800][1.876s][9644][13168]  garbage-first heap   total 1048576K, used 626663K [0x00000000c0000000, 0x0000000100000000)
        [2020-10-28T10:49:30.373+0800][1.876s][9644][13168]   region size 1024K, 26 young (26624K), 25 survivors (25600K)
        [2020-10-28T10:49:30.373+0800][1.877s][9644][13168]  Metaspace       used 16560K, capacity 16956K, committed 17024K, reserved 1064960K
        [2020-10-28T10:49:30.373+0800][1.877s][9644][13168]   class space    used 1747K, capacity 1890K, committed 1920K, reserved 1048576K
        

## 2GB
执行命令行：

        java -XX:+UseG1GC -Xms2g -Xmx2g -Xlog:gc*:file=gc.g1.2g.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：
<details><summary>Click to expand</summary><p><code>

        PS C:\Data\Code\GitHub\JAVA-000\Week_02> java -XX:+UseG1GC -Xms2g -Xmx2g -Xlog:gc*:file=gc.g1.2g.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java
        [2020-10-28T10:50:17.928+0800][0.071s][1964][3712] Using G1
        正在执行...
        [2020-10-28T10:50:18.791+0800][0.934s][1964][9088] GC(0) Pause Young (Normal) (G1 Evacuation Pause) 142M->52M(2048M) 11.123ms
        [2020-10-28T10:50:18.840+0800][0.983s][1964][9088] GC(1) Pause Young (Normal) (G1 Evacuation Pause) 187M->107M(2048M) 13.263ms
        [2020-10-28T10:50:18.883+0800][1.026s][1964][9088] GC(2) Pause Young (Normal) (G1 Evacuation Pause) 245M->158M(2048M) 13.929ms
        [2020-10-28T10:50:18.922+0800][1.065s][1964][9088] GC(3) Pause Young (Normal) (G1 Evacuation Pause) 296M->202M(2048M) 11.991ms
        [2020-10-28T10:50:18.963+0800][1.106s][1964][9088] GC(4) Pause Young (Normal) (G1 Evacuation Pause) 343M->251M(2048M) 12.320ms
        [2020-10-28T10:50:19.010+0800][1.153s][1964][9088] GC(5) Pause Young (Normal) (G1 Evacuation Pause) 392M->302M(2048M) 13.887ms
        [2020-10-28T10:50:19.062+0800][1.205s][1964][9088] GC(6) Pause Young (Normal) (G1 Evacuation Pause) 473M->352M(2048M) 14.122ms
        [2020-10-28T10:50:19.114+0800][1.257s][1964][9088] GC(7) Pause Young (Normal) (G1 Evacuation Pause) 540M->403M(2048M) 14.664ms
        [2020-10-28T10:50:19.176+0800][1.319s][1964][9088] GC(8) Pause Young (Normal) (G1 Evacuation Pause) 629M->476M(2048M) 12.897ms
        [2020-10-28T10:50:19.253+0800][1.396s][1964][9088] GC(9) Pause Young (Normal) (G1 Evacuation Pause) 713M->533M(2048M) 14.820ms
        [2020-10-28T10:50:19.376+0800][1.519s][1964][9088] GC(10) Pause Young (Normal) (G1 Evacuation Pause) 899M->603M(2048M) 20.406ms
        [2020-10-28T10:50:19.514+0800][1.657s][1964][9088] GC(11) Pause Young (Normal) (G1 Evacuation Pause) 1042M->695M(2048M) 34.230ms
        [2020-10-28T10:50:19.675+0800][1.818s][1964][9088] GC(12) Pause Young (Normal) (G1 Evacuation Pause) 1213M->792M(2048M) 41.601ms
        执行结束!共生成对象次数:12978
        [2020-10-28T10:50:19.725+0800][1.868s][1964][3712] Heap
        [2020-10-28T10:50:19.725+0800][1.868s][1964][3712]  garbage-first heap   total 2097152K, used 994657K [0x0000000080000000, 0x0000000100000000)
        [2020-10-28T10:50:19.725+0800][1.869s][1964][3712]   region size 1024K, 165 young (168960K), 47 survivors (48128K)
        [2020-10-28T10:50:19.726+0800][1.869s][1964][3712]  Metaspace       used 16580K, capacity 16955K, committed 17024K, reserved 1064960K
        [2020-10-28T10:50:19.726+0800][1.869s][1964][3712]   class space    used 1746K, capacity 1890K, committed 1920K, reserved 1048576K
        
</code></p></details>

## 4GB
执行命令行：

        java -XX:+UseG1GC -Xms4g -Xmx4g -Xlog:gc*:file=gc.g1.4g.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：
<details><summary>Click to expand</summary><code>
        
        PS C:\Data\Code\GitHub\JAVA-000\Week_02> java -XX:+UseG1GC -Xms4g -Xmx4g -Xlog:gc*:file=gc.g1.4g.log:time,uptime,pid,tid,level,tags -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java
        [2020-10-28T10:51:02.984+0800][0.116s][16528][7964] Using G1
        正在执行...
        [2020-10-28T10:51:03.886+0800][1.019s][16528][11296] GC(0) Pause Young (Normal) (G1 Evacuation Pause) 203M->72M(4096M) 23.980ms
        [2020-10-28T10:51:03.949+0800][1.082s][16528][11296] GC(1) Pause Young (Normal) (G1 Evacuation Pause) 250M->137M(4096M) 21.356ms
        [2020-10-28T10:51:04.005+0800][1.138s][16528][11296] GC(2) Pause Young (Normal) (G1 Evacuation Pause) 315M->206M(4096M) 25.575ms
        [2020-10-28T10:51:04.061+0800][1.193s][16528][11296] GC(3) Pause Young (Normal) (G1 Evacuation Pause) 384M->279M(4096M) 21.981ms
        [2020-10-28T10:51:04.114+0800][1.247s][16528][11296] GC(4) Pause Young (Normal) (G1 Evacuation Pause) 456M->355M(4096M) 23.564ms
        [2020-10-28T10:51:04.170+0800][1.302s][16528][11296] GC(5) Pause Young (Normal) (G1 Evacuation Pause) 533M->422M(4096M) 24.201ms
        [2020-10-28T10:51:04.222+0800][1.355s][16528][11296] GC(6) Pause Young (Normal) (G1 Evacuation Pause) 600M->485M(4096M) 19.828ms
        [2020-10-28T10:51:04.277+0800][1.409s][16528][11296] GC(7) Pause Young (Normal) (G1 Evacuation Pause) 663M->550M(4096M) 21.831ms
        [2020-10-28T10:51:04.344+0800][1.477s][16528][11296] GC(8) Pause Young (Normal) (G1 Evacuation Pause) 764M->631M(4096M) 22.350ms
        [2020-10-28T10:51:04.413+0800][1.546s][16528][11296] GC(9) Pause Young (Normal) (G1 Evacuation Pause) 870M->723M(4096M) 27.559ms
        [2020-10-28T10:51:04.524+0800][1.657s][16528][11296] GC(10) Pause Young (Normal) (G1 Evacuation Pause) 1001M->815M(4096M) 30.164ms
        [2020-10-28T10:51:04.642+0800][1.774s][16528][11296] GC(11) Pause Young (Normal) (G1 Evacuation Pause) 1147M->918M(4096M) 41.258ms
        执行结束!共生成对象次数:13950
        [2020-10-28T10:51:04.765+0800][1.897s][16528][7964 ] Heap
        [2020-10-28T10:51:04.765+0800][1.898s][16528][7964 ]  garbage-first heap   total 4194304K, used 1401110K [0x0000000700000000, 0x0000000800000000)
        [2020-10-28T10:51:04.766+0800][1.899s][16528][7964 ]   region size 2048K, 251 young (514048K), 24 survivors (49152K)
        [2020-10-28T10:51:04.766+0800][1.899s][16528][7964 ]  Metaspace       used 16549K, capacity 16956K, committed 17024K, reserved 1064960K
        [2020-10-28T10:51:04.767+0800][1.899s][16528][7964 ]   class space    used 1748K, capacity 1890K, committed 1920K, reserved 1048576K
        

</code></details>


