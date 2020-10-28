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
执行命令行：

        java -XX:+UseSerialGC -Xms128m -Xmx128m -Xlog:gc*:file=gc.serial.128.log:level,tags,time,uptime,pid,tid -Xlog:gc,gc+heap+exit::time,uptime,pid,tid GCLogAnalysis.java

结果如下：


