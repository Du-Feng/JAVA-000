# 演示 for 循环和静态方法调用的字节码
**注：本篇未完成，只弄明白了 for 循环，尚未掌握静态方法的调用，尤其是未能明白dup指令**
<br/>请先阅读：
- [入门简介](index.md)
- [四则运算](pages/ArithmeticOperation.md)
- [if 判断](pages/condition.md)

# Source Code

    package org.example.loop;
    
    public class ForLoopTest {
        private static int[] numbers = {1, 6, 8};
    
        public static void main(String[] args) {
            MovingAverage ma = new MovingAverage();
            for (int number : numbers) {
                ma.submit(number);
            }
            double avg = ma.getAvg();
        }
    }
    
# bytecode 字节码
执行如下命令：

    javap -c -v -p ForLoopTest.class 

得到字节码文件：
    
    Classfile /C:/Data/Code/GitHub/GeekbangJavaTrainingCamp/Week_01/Course_01/bytecode/target/classes/org/example/loop/ForLoopTest.class
      Last modified Oct 20, 2020; size 817 bytes
      MD5 checksum 97d55e36844eaf624fd166f6346bd2e9
      Compiled from "ForLoopTest.java"
    public class org.example.loop.ForLoopTest
      minor version: 0
      major version: 51
      flags: (0x0021) ACC_PUBLIC, ACC_SUPER
      this_class: #7                          // org/example/loop/ForLoopTest
      super_class: #8                         // java/lang/Object
      interfaces: 0, fields: 1, methods: 3, attributes: 1
    Constant pool:
       #1 = Methodref          #8.#34         // java/lang/Object."<init>":()V
       #2 = Class              #35            // org/example/loop/MovingAverage
       #3 = Methodref          #2.#34         // org/example/loop/MovingAverage."<init>":()V
       #4 = Fieldref           #7.#36         // org/example/loop/ForLoopTest.numbers:[I
       #5 = Methodref          #2.#37         // org/example/loop/MovingAverage.submit:(D)V
       #6 = Methodref          #2.#38         // org/example/loop/MovingAverage.getAvg:()D
       #7 = Class              #39            // org/example/loop/ForLoopTest
       #8 = Class              #40            // java/lang/Object
       #9 = Utf8               numbers
      #10 = Utf8               [I
      #11 = Utf8               <init>
      #12 = Utf8               ()V
      #13 = Utf8               Code
      #14 = Utf8               LineNumberTable
      #15 = Utf8               LocalVariableTable
      #16 = Utf8               this
      #17 = Utf8               Lorg/example/loop/ForLoopTest;
      #18 = Utf8               main
      #19 = Utf8               ([Ljava/lang/String;)V
      #20 = Utf8               number
      #21 = Utf8               I
      #22 = Utf8               args
      #23 = Utf8               [Ljava/lang/String;
      #24 = Utf8               ma
      #25 = Utf8               Lorg/example/loop/MovingAverage;
      #26 = Utf8               avg
      #27 = Utf8               D
      #28 = Utf8               StackMapTable
      #29 = Class              #23            // "[Ljava/lang/String;"
      #30 = Class              #10            // "[I"
      #31 = Utf8               <clinit>
      #32 = Utf8               SourceFile
      #33 = Utf8               ForLoopTest.java
      #34 = NameAndType        #11:#12        // "<init>":()V
      #35 = Utf8               org/example/loop/MovingAverage
      #36 = NameAndType        #9:#10         // numbers:[I
      #37 = NameAndType        #41:#42        // submit:(D)V
      #38 = NameAndType        #43:#44        // getAvg:()D
      #39 = Utf8               org/example/loop/ForLoopTest
      #40 = Utf8               java/lang/Object
      #41 = Utf8               submit
      #42 = Utf8               (D)V
      #43 = Utf8               getAvg
      #44 = Utf8               ()D
    {
      private static int[] numbers;
        descriptor: [I
        flags: (0x000a) ACC_PRIVATE, ACC_STATIC
    
      public org.example.loop.ForLoopTest();
        descriptor: ()V
        flags: (0x0001) ACC_PUBLIC
        Code:
          stack=1, locals=1, args_size=1
             0: aload_0
             1: invokespecial #1                  // Method java/lang/Object."<init>":()V
             4: return
          LineNumberTable:
            line 3: 0
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
                0       5     0  this   Lorg/example/loop/ForLoopTest;
    
      public static void main(java.lang.String[]);
        descriptor: ([Ljava/lang/String;)V
        flags: (0x0009) ACC_PUBLIC, ACC_STATIC
        Code:
          stack=3, locals=6, args_size=1
             0: new           #2                  // class org/example/loop/MovingAverage
             3: dup
             4: invokespecial #3                  // Method org/example/loop/MovingAverage."<init>":()V
             7: astore_1
             8: getstatic     #4                  // Field numbers:[I
            11: astore_2
            12: aload_2
            13: arraylength
            14: istore_3
            15: iconst_0
            16: istore        4
            18: iload         4
            20: iload_3
            21: if_icmpge     43
            24: aload_2
            25: iload         4
            27: iaload
            28: istore        5
            30: aload_1
            31: iload         5
            33: i2d
            34: invokevirtual #5                  // Method org/example/loop/MovingAverage.submit:(D)V
            37: iinc          4, 1
            40: goto          18
            43: aload_1
            44: invokevirtual #6                  // Method org/example/loop/MovingAverage.getAvg:()D
            47: dstore_2
            48: return
          LineNumberTable:
            line 7: 0
            line 8: 8
            line 9: 30
            line 8: 37
            line 11: 43
            line 12: 48
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
               30       7     5 number   I
                0      49     0  args   [Ljava/lang/String;
                8      41     1    ma   Lorg/example/loop/MovingAverage;
               48       1     2   avg   D
          StackMapTable: number_of_entries = 2
            frame_type = 255 /* full_frame */
              offset_delta = 18
              locals = [ class "[Ljava/lang/String;", class org/example/loop/MovingAverage, class "[I", int, int ]
              stack = []
            frame_type = 248 /* chop */
              offset_delta = 24
    
      static {};
        descriptor: ()V
        flags: (0x0008) ACC_STATIC
        Code:
          stack=4, locals=0, args_size=0
             0: iconst_3
             1: newarray       int
             3: dup
             4: iconst_0
             5: iconst_1
             6: iastore
             7: dup
             8: iconst_1
             9: bipush        6
            11: iastore
            12: dup
            13: iconst_2
            14: bipush        8
            16: iastore
            17: putstatic     #4                  // Field numbers:[I
            20: return
          LineNumberTable:
            line 4: 0
    }
    SourceFile: "ForLoopTest.java"

# 解析
## array 初始化

      static {};
        descriptor: ()V
        flags: (0x0008) ACC_STATIC
        Code:
          stack=4, locals=0, args_size=0
             0: iconst_3
             1: newarray       int
             3: dup
             4: iconst_0
             5: iconst_1
             6: iastore
             7: dup
             8: iconst_1
             9: bipush        6
            11: iastore
            12: dup
            13: iconst_2
            14: bipush        8
            16: iastore
            17: putstatic     #4                  // Field numbers:[I
            20: return
          LineNumberTable:
            line 4: 0

- **0: iconst_3** 把常量3放入operand stack，此时operand stack中只有一个元素3。
- **1: newarray       int** 初始化一个int类型的数据，长度为operand stack的第一个元素，此处为3。
3从operand stack中弹出，同时新数组起始地址放入至operand stack。此时operand stack中只有一个元素：即数组的起始地址。
- **3: dup** 复制operand stack的第一个元素，同时放入operand stack中。此时operand stack中有两个元素：3和3。
- **4: iconst_0** 把常量0放入operand stack中。
- **5: iconst_1** 把常量1放入operand stack中。此是operand stack有四个元素：0、1、3、3。
- **6: iastore** 数组的第0个位置放入int 1。此时operand stack中有两个元素：3和3。
- **7: dup**
- **8: iconst_1** 把常量1放入operand stack中。
- **9: bipush        6** 把常量6放入operand stack中。此是operand stack有两个元素：1和6。
- **11: iastore** 数组的第1个位置放入int 6。
- **12: dup**
- **13: iconst_2** 把常量2放入operand stack中。
- **14: bipush        8** 把常量8放入operand stack中。
- **16: iastore** 数组的第2个位置放入int 8。
- **17: putstatic     #4** 把结果保存为static field **#4 = Fieldref           #7.#36**，最终解析为org/example/loop/ForLoopTest.numbers:[I
- **20: return** 执行结束。

##　main method

      public static void main(java.lang.String[]);
        descriptor: ([Ljava/lang/String;)V
        flags: (0x0009) ACC_PUBLIC, ACC_STATIC
        Code:
          stack=3, locals=6, args_size=1
             0: new           #2                  // class org/example/loop/MovingAverage
             3: dup
             4: invokespecial #3                  // Method org/example/loop/MovingAverage."<init>":()V
             7: astore_1
             8: getstatic     #4                  // Field numbers:[I
            11: astore_2
            12: aload_2
            13: arraylength
            14: istore_3
            15: iconst_0
            16: istore        4
            18: iload         4
            20: iload_3
            21: if_icmpge     43
            24: aload_2
            25: iload         4
            27: iaload
            28: istore        5
            30: aload_1
            31: iload         5
            33: i2d
            34: invokevirtual #5                  // Method org/example/loop/MovingAverage.submit:(D)V
            37: iinc          4, 1
            40: goto          18
            43: aload_1
            44: invokevirtual #6                  // Method org/example/loop/MovingAverage.getAvg:()D
            47: dstore_2
            48: return
          LineNumberTable:
            line 7: 0
            line 8: 8
            line 9: 30
            line 8: 37
            line 11: 43
            line 12: 48
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
               30       7     5 number   I
                0      49     0  args   [Ljava/lang/String;
                8      41     1    ma   Lorg/example/loop/MovingAverage;
               48       1     2   avg   D
          StackMapTable: number_of_entries = 2
            frame_type = 255 /* full_frame */
              offset_delta = 18
              locals = [ class "[Ljava/lang/String;", class org/example/loop/MovingAverage, class "[I", int, int ]
              stack = []
            frame_type = 248 /* chop */
              offset_delta = 24

- **0: new           #2** 新建**Constant pool #2 = Class              #35**，最终解析为org/example/loop/MovingAverage的一个实例对象。
- **3: dup**
- **4: invokespecial #3** 调用**Constant pool #3**方法，最终解析为Class org/example/loop/MovingAverage 的构造函数。
- **7: astore_1** 把operand stack中的第一个元素存入LocalVariableTable的Slot为1变量，即ma中。
- **8: getstatic     #4**　把static 变量加载进operand stack，最终解析为把 org/example/loop/ForLoopTest.numbers 加载进operand stack。
- **11: astore_2** 把operand stack中的第一个元素存入LocalVariableTable的Slot为2变量，即avg中。
- **12: aload_2** 把LocalVariableTable的Slot为2变量，即avg加载进operand stack。
- **13: arraylength** 获取数据的长度
- **14: istore_3** 
- **15: iconst_0** 把常量0放入operand stack中。
- **16: istore        4** 把operand stack的第一个元素
- **18: iload         4** 把LocalVariableTable的Slot为4变量加载进operand stack。
- **20: iload_3** 把LocalVariableTable的Slot为3变量加载进operand stack。
- **21: if_icmpge     43** 如果 >= ，则跳转至**43: aload_1**，即结束循环。
- **24: aload_2**
- **25: iload         4**
- **27: iaload**
- **28: istore        5**
- **30: aload_1**
- **31: iload         5**
- **33: i2d**
- **34: invokevirtual #5**
- **37: iinc          4, 1**
- **40: goto          18** 跳转至**18: iload         4**，即开始下一次循环。
- **43: aload_1** 把LocalVariableTable的Slot为1变量加载进operand stack。
- **44: invokevirtual #6** 调用方法org/example/loop/MovingAverage.getAvg:()D
- **47: dstore_2** 把operand stack的第一个元素保存至LocalVariableTable的Slot为2变量中。
- **48: return** 退出方法，不返回任何值。