# [返回](../index.md)

# 演示 if 条件判断和静态方法的字节码
请先阅读：
- [入门简介](index.md)
- [四则运算](pages/ArithmeticOperation.md)

# Source Code

    package org.example.condition;
    
    public class ConditionDemo {
        public static void main(String[] args) {
            demoA(true);
            demoA(false);
            demoB(-2);
            demoB(1);
            demoB(3);
            demoB(1024);
        }
    
        private static void demoA(boolean tag) {
            if (tag) {
                System.out.println("This is ture");
            } else {
                System.out.println("This is false");
            }
        }
    
        private static void demoB(int num) {
            if (num <= 0) {
                System.out.println("This is 1");
            } else if (num > 0 && num < 3) {
                System.out.println("This is 2");
            } else if (num == 3) {
                System.out.println("This is 3");
            } else {
                System.out.println("This is something else");
            }
        }
    }


# Byte Code
运行如下命令：

    javap -c -p -v ConditionDemo.class

得到的字节码为：

    Classfile /C:/Data/Code/GitHub/GeekbangJavaTrainingCamp/Week_01/Course_01/bytecode/target/classes/org/example/condition/ConditionDemo.class
      Last modified Oct 20, 2020; size 1062 bytes
      MD5 checksum 63dd9a9fecccbd7dfb0fe1618f961105
      Compiled from "ConditionDemo.java"
    public class org.example.condition.ConditionDemo
      minor version: 0
      major version: 51
      flags: (0x0021) ACC_PUBLIC, ACC_SUPER
      this_class: #12                         // org/example/condition/ConditionDemo
      super_class: #13                        // java/lang/Object
      interfaces: 0, fields: 0, methods: 4, attributes: 1
    Constant pool:
       #1 = Methodref          #13.#36        // java/lang/Object."<init>":()V
       #2 = Methodref          #12.#37        // org/example/condition/ConditionDemo.demoA:(Z)V
       #3 = Methodref          #12.#38        // org/example/condition/ConditionDemo.demoB:(I)V
       #4 = Fieldref           #39.#40        // java/lang/System.out:Ljava/io/PrintStream;
       #5 = String             #41            // This is ture
       #6 = Methodref          #42.#43        // java/io/PrintStream.println:(Ljava/lang/String;)V
       #7 = String             #44            // This is false
       #8 = String             #45            // This is 1
       #9 = String             #46            // This is 2
      #10 = String             #47            // This is 3
      #11 = String             #48            // This is something else
      #12 = Class              #49            // org/example/condition/ConditionDemo
      #13 = Class              #50            // java/lang/Object
      #14 = Utf8               <init>
      #15 = Utf8               ()V
      #16 = Utf8               Code
      #17 = Utf8               LineNumberTable
      #18 = Utf8               LocalVariableTable
      #19 = Utf8               this
      #20 = Utf8               Lorg/example/condition/ConditionDemo;
      #21 = Utf8               main
      #22 = Utf8               ([Ljava/lang/String;)V
      #23 = Utf8               args
      #24 = Utf8               [Ljava/lang/String;
      #25 = Utf8               demoA
      #26 = Utf8               (Z)V
      #27 = Utf8               tag
      #28 = Utf8               Z
      #29 = Utf8               StackMapTable
      #30 = Utf8               demoB
      #31 = Utf8               (I)V
      #32 = Utf8               num
      #33 = Utf8               I
      #34 = Utf8               SourceFile
      #35 = Utf8               ConditionDemo.java
      #36 = NameAndType        #14:#15        // "<init>":()V
      #37 = NameAndType        #25:#26        // demoA:(Z)V
      #38 = NameAndType        #30:#31        // demoB:(I)V
      #39 = Class              #51            // java/lang/System
      #40 = NameAndType        #52:#53        // out:Ljava/io/PrintStream;
      #41 = Utf8               This is ture
      #42 = Class              #54            // java/io/PrintStream
      #43 = NameAndType        #55:#56        // println:(Ljava/lang/String;)V
      #44 = Utf8               This is false
      #45 = Utf8               This is 1
      #46 = Utf8               This is 2
      #47 = Utf8               This is 3
      #48 = Utf8               This is something else
      #49 = Utf8               org/example/condition/ConditionDemo
      #50 = Utf8               java/lang/Object
      #51 = Utf8               java/lang/System
      #52 = Utf8               out
      #53 = Utf8               Ljava/io/PrintStream;
      #54 = Utf8               java/io/PrintStream
      #55 = Utf8               println
      #56 = Utf8               (Ljava/lang/String;)V
    {
      public org.example.condition.ConditionDemo();
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
                0       5     0  this   Lorg/example/condition/ConditionDemo;
    
      public static void main(java.lang.String[]);
        descriptor: ([Ljava/lang/String;)V
        flags: (0x0009) ACC_PUBLIC, ACC_STATIC
        Code:
          stack=1, locals=1, args_size=1
             0: iconst_1
             1: invokestatic  #2                  // Method demoA:(Z)V
             4: iconst_0
             5: invokestatic  #2                  // Method demoA:(Z)V
             8: bipush        -2
            10: invokestatic  #3                  // Method demoB:(I)V
            13: iconst_1
            14: invokestatic  #3                  // Method demoB:(I)V
            17: iconst_3
            18: invokestatic  #3                  // Method demoB:(I)V
            21: sipush        1024
            24: invokestatic  #3                  // Method demoB:(I)V
            27: return
          LineNumberTable:
            line 5: 0
            line 6: 4
            line 7: 8
            line 8: 13
            line 9: 17
            line 10: 21
            line 11: 27
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
                0      28     0  args   [Ljava/lang/String;
    
      private static void demoA(boolean);
        descriptor: (Z)V
        flags: (0x000a) ACC_PRIVATE, ACC_STATIC
        Code:
          stack=2, locals=1, args_size=1
             0: iload_0
             1: ifeq          15
             4: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
             7: ldc           #5                  // String This is ture
             9: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
            12: goto          23
            15: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
            18: ldc           #7                  // String This is false
            20: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
            23: return
          LineNumberTable:
            line 14: 0
            line 15: 4
            line 17: 15
            line 19: 23
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
                0      24     0   tag   Z
          StackMapTable: number_of_entries = 2
            frame_type = 15 /* same */
            frame_type = 7 /* same */
    
      private static void demoB(int);
        descriptor: (I)V
        flags: (0x000a) ACC_PRIVATE, ACC_STATIC
        Code:
          stack=2, locals=1, args_size=1
             0: iload_0
             1: ifgt          15
             4: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
             7: ldc           #8                  // String This is 1
             9: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
            12: goto          59
            15: iload_0
            16: ifle          35
            19: iload_0
            20: iconst_3
            21: if_icmpge     35
            24: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
            27: ldc           #9                  // String This is 2
            29: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
            32: goto          59
            35: iload_0
            36: iconst_3
            37: if_icmpne     51
            40: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
            43: ldc           #10                 // String This is 3
            45: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
            48: goto          59
            51: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
            54: ldc           #11                 // String This is something else
            56: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
            59: return
          LineNumberTable:
            line 22: 0
            line 23: 4
            line 24: 15
            line 25: 24
            line 26: 35
            line 27: 40
            line 29: 51
            line 31: 59
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
                0      60     0   num   I
          StackMapTable: number_of_entries = 4
            frame_type = 15 /* same */
            frame_type = 19 /* same */
            frame_type = 15 /* same */
            frame_type = 7 /* same */
    }
    SourceFile: "ConditionDemo.java"

# 解析

## class
类加载时执行如下代码：

      public org.example.condition.ConditionDemo();
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
                0       5     0  this   Lorg/example/condition/ConditionDemo;

- **0: aload_0** 加载LocalVariableTable中slot为0的引用变量，即this指针。
- **1: invokespecial #1                  // Method java/lang/Object."<init>":()V** 调用默认的构造函数。
其中 **#1** 表示指向 **Class Constant pool** 的 **#1 = Methodref          #13.#36        // java/lang/Object."<init>":()V**，解析为默认的构造函数。
- **4: return** 执行完毕

## main method
main 方法的字节码如下：

      public static void main(java.lang.String[]);
        descriptor: ([Ljava/lang/String;)V
        flags: (0x0009) ACC_PUBLIC, ACC_STATIC
        Code:
          stack=1, locals=1, args_size=1
             0: iconst_1
             1: invokestatic  #2                  // Method demoA:(Z)V
             4: iconst_0
             5: invokestatic  #2                  // Method demoA:(Z)V
             8: bipush        -2
            10: invokestatic  #3                  // Method demoB:(I)V
            13: iconst_1
            14: invokestatic  #3                  // Method demoB:(I)V
            17: iconst_3
            18: invokestatic  #3                  // Method demoB:(I)V
            21: sipush        1024
            24: invokestatic  #3                  // Method demoB:(I)V
            27: return
          LineNumberTable:
            line 5: 0
            line 6: 4
            line 7: 8
            line 8: 13
            line 9: 17
            line 10: 21
            line 11: 27
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
                0      28     0  args   [Ljava/lang/String;

- **0: iconst_1** 把常量1放入operand stack中，此时operand stack中只有一个元素1。**注意**，boolean是当作int处理的，true为1。
- **1: invokestatic  #2                  // Method demoA:(Z)V** 调用静态方法 demoA，operand stack的第一个元素1为其参数，此时operand stack变为空。
其中 **#2** 表示指向 **Clas Constant pool** 的 **#2 = Methodref          #12.#37        // org/example/condition/ConditionDemo.demoA:(Z)V**，即demoA。
- **4: iconst_0** 把常量0放入operand stack中，此时operand stack中只有一个元素0。**注意**，boolean是当作int处理的，false为0。
- **5: invokestatic  #2                  // Method demoA:(Z)V** 调用静态方法 demoA，operand stack的第一个元素0为其参数，此时operand stack变为空。
- **8: bipush        -2** 把常量-2放入operand stack中，此时operand stack只有一个元素-2.
- **10: invokestatic  #3                  // Method demoB:(I)V** 调用静态方法 demoB，operand stack的第一个元素-2为其参数，此时operand stack变为空。
- **13: iconst_1** 把常量1放入operand stack中，此时operand stack只有一个元素1
- **14: invokestatic  #3                  // Method demoB:(I)V** 调用静态方法 demoB，operand stack的第一个元素1为其参数，此时operand stack变为空。
- **17: iconst_3** 把常量3放入operand stack中，此时operand stack只有一个元素3
- **18  #3                  // Method demoB:(I)V** 调用静态方法 demoB，operand stack的第一个元素3为其参数，此时operand stack变为空。
- **21: sipush        1024** 把常量1024放入operand stack中，此时operand stack只有一个元素1024
- **24  #3                  // Method demoB:(I)V** 调用静态方法 demoB，operand stack的第一个元素1024为其参数，此时operand stack变为空。
- **27: return** 执行完毕，退出方法，不返回任何值。

## demoA
bool的if else 是简单拆解为 ifeq 和 goto 跳转。
demoA 方法的字节码如下：

      private static void demoA(boolean);
        descriptor: (Z)V
        flags: (0x000a) ACC_PRIVATE, ACC_STATIC
        Code:
          stack=2, locals=1, args_size=1
             0: iload_0
             1: ifeq          15
             4: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
             7: ldc           #5                  // String This is ture
             9: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
            12: goto          23
            15: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
            18: ldc           #7                  // String This is false
            20: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
            23: return
          LineNumberTable:
            line 14: 0
            line 15: 4
            line 17: 15
            line 19: 23
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
                0      24     0   tag   Z
          StackMapTable: number_of_entries = 2
            frame_type = 15 /* same */
            frame_type = 7 /* same */
            
- **0: iload_0** 把LocalVariableTable中Slot为0的变量，加载进operand stack，即把此方法的参数tag加载进operand stack。此时operand stack只有一个元素tag。
- **1: ifeq          15** 取出operand stack的第一个元素，如果为0，则跳转去执行**15: getstatic     #4**。此时operand stack变为空。
**注意**，ifeq是判断是否为0。
- **4: getstatic     #4** 获取静态变量：class Constant pool的**#4 = Fieldref           #39.#40**，
最终解析为：java/lang/System.out:Ljava/io/PrintStream。
放入于operand stack。此时operand stack中只有此一个元素，java/io/PrintStream地址。
- **7: ldc           #5**
把class Constant pool的**#5 = String             #41**放入至operand stack中，
最终是把**#41 = Utf8               This is ture**放入至operand stack中。
此时operand stack有两个元素：字符串This is ture，java/io/PrintStream地址。
- **9: invokevirtual #6** 调用方法：#6 = Methodref          #42.#43        // java/io/PrintStream.println:(Ljava/lang/String;)V
参数为operand stack的第一个元素：字符串This is ture。operand stack变为空。
- **12: goto          23** 跳转去执行**23: return**，即退出方法。
- **15: getstatic     #4** 把java/io/PrintStream地址放入operand stack中。此时operand stack只有此一个元素。
- **18: ldc           #7**
把class Constant pool的 ** #7 = String             #44 ** 放入至operand stack中，
最终是把** #44 = Utf8               This is false **放入至operand stack中。
此时operand stack有两个元素：字符串This is false，java/io/PrintStream地址。
- **20: invokevirtual #6** 调用方法 java/io/PrintStream.println:(Ljava/lang/String;)V 。此时operand stack变为空。
- **23: return** 退出函数，没有任何返回值。


## demoB
这里演示一个int的 if, else if, else if, else。
demoB 方法的字节码如下：

      private static void demoB(int);
        descriptor: (I)V
        flags: (0x000a) ACC_PRIVATE, ACC_STATIC
        Code:
          stack=2, locals=1, args_size=1
             0: iload_0
             1: ifgt          15
             4: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
             7: ldc           #8                  // String This is 1
             9: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
            12: goto          59
            15: iload_0
            16: ifle          35
            19: iload_0
            20: iconst_3
            21: if_icmpge     35
            24: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
            27: ldc           #9                  // String This is 2
            29: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
            32: goto          59
            35: iload_0
            36: iconst_3
            37: if_icmpne     51
            40: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
            43: ldc           #10                 // String This is 3
            45: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
            48: goto          59
            51: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
            54: ldc           #11                 // String This is something else
            56: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
            59: return
          LineNumberTable:
            line 22: 0
            line 23: 4
            line 24: 15
            line 25: 24
            line 26: 35
            line 27: 40
            line 29: 51
            line 31: 59
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
                0      60     0   num   I
          StackMapTable: number_of_entries = 4
            frame_type = 15 /* same */
            frame_type = 19 /* same */
            frame_type = 15 /* same */
            frame_type = 7 /* same */
            
- **0: iload_0** 把LocalVariableTable的Slot为0的变量，即此方法的参数num，放入operand stack中。此时operand stack中只有一个元素：num。
- **1: ifgt          15** 如果operand stack的第一个元素num大于0，则跳转去执行**15: iload_0**，即执行第一个 else if代码块。
- **4: getstatic     #4** 把静态变量放入operand stack，此时operand stack中有一个元素。
- **7: ldc           #8** 把字体串**This is 1**放入operand stack。此时operand stack中有两个元素。
- **9: invokevirtual #6** 调用方法Method java/io/PrintStream.println:(Ljava/lang/String;)V，此时operand stack变为空。
- **12: goto          59** 跳转去执行**59: return**，即结束if代码块、退出方法。
- **15: iload_0** 此为第一个else if。
把LocalVariableTable的Slot为0的变量，即此方法的参数num，放入operand stack中。此时operand stack中只有一个元素：num。
- **16: ifle          35** 如果operand stack的第一个元素num <= 0，则跳转执行**35: iload_0**，即执行第二个else if代码块。此时operand stack变为空。
- **19: iload_0** 把LocalVariableTable的Slot为0的变量，即此方法的参数num，放入operand stack中。此时operand stack中只有一个元素：num。
- **20: iconst_3** 把常量3放入operand stack中，此时operand stack中有两个元素。
- **21: if_icmpge     35** 如果 num >= 3，则跳转执行**35: iload_0**，即else代码块。此是operand stack变为空。
**注意**，这里作了优化，没有再去检查 num > 0。
- **24: getstatic     #4** 把静态变量放入operand stack，此时operand stack中有一个元素。
- **27: ldc           #9** 把字体串**This is 2**放入operand stack。此时operand stack中有两个元素。
- **29: invokevirtual #6** 调用方法Method java/io/PrintStream.println:(Ljava/lang/String;)V，此时operand stack变为空。
- **32: goto          59** 跳转去执行**59: return**，即结束第一个else if代码块、退出方法。
- **35: iload_0** 此为第二个else if。
把LocalVariableTable的Slot为0的变量，即此方法的参数num，放入operand stack中。此时operand stack中只有一个元素：num。
- **36: iconst_3** 把常量3放入operand stack中，此时operand stack中有两个元素。
- **37: if_icmpne     51** 如果operand stack中前两个元素不相等，则跳转执行*851: getstatic     #4**，即执行else代码块。此时operand stack变为空。
- **40: getstatic     #4** 把静态变量放入operand stack，此时operand stack中有一个元素。
- **43: ldc           #10** 把字体串**This is 3**放入operand stack。此时operand stack中有两个元素。
- **45: invokevirtual #6** 调用方法Method java/io/PrintStream.println:(Ljava/lang/String;)V，此时operand stack变为空。
- **48: goto          59** 跳转去执行**59: return**，即结束第二个else if代码块、退出方法。
- **51: getstatic     #4** 把静态变量放入operand stack，此时operand stack中有一个元素。
- **54: ldc           #11** 把字体串**This is something else**放入operand stack。此时operand stack中有两个元素。
- **56: invokevirtual #6** 调用方法Method java/io/PrintStream.println:(Ljava/lang/String;)V，此时operand stack变为空。
- **59: return** 退出方法，不返回任何值。
