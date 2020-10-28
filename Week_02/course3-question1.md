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
执行命令行：

        java -XX:+UseSerialGC -Xms128m -Xmx128m -Xlog:gc*:file=gc.serial.128.log:time,uptime,pid,tid -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：




# CMS
执行命令行：

        java -XX:+UseSerialGC -Xms128m -Xmx128m -Xlog:gc*:file=gc.serial.128.log:level,tags,time,uptime,pid,tid -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：




# G1
执行命令行：

        java -XX:+UseSerialGC -Xms128m -Xmx128m -Xlog:gc*:file=gc.serial.128.log:level,tags,time,uptime,pid,tid -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：


