# 演示加减乘除四则运算的字节码
请先阅读：
- [入门简介](index.md)

# Useful Links
- [Table 2.11.1-A. Type support in the Java Virtual Machine instruction set](https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-2.html#jvms-2.11.1-220) 查看Java 11完整的指令集
- [Chapter 6. The Java Virtual Machine Instruction Set](https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-6.html) 查看Java 11每个指令的详细解释
- [Chapter 7. Opcode Mnemonics by Opcode](https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-7.html) 查看Java 11完整的助记符：

# Source Code

    package org.example.operation;
    
    public class ArithmeticOperation {
        public static void main(String[] args) {
            int a = 0;
            int b = -1;
            int c = -2;
            int d = 6;
            int e = 7;
            int f = 12345678;
            int g = 987654321;
            int h = a + d;
            int i = e + f;
            int j = g - f;
            int k = d * e;
            int l = f / e;
        }
    }

# bytecode
使用如下命令：

    javap -c -v ArithmeticOperation.class

得到的字节码文件如下：

    Classfile /C:/Data/Code/GitHub/GeekbangJavaTrainingCamp/Week_01/Course_01/bytecode/target/classes/org/example/operation/ArithmeticOperation.class
      Last modified Oct 20, 2020; size 731 bytes
      MD5 checksum 928a5f565c07247f4e352107af9b745e
      Compiled from "ArithmeticOperation.java"
    public class org.example.operation.ArithmeticOperation
      minor version: 0
      major version: 51
      flags: (0x0021) ACC_PUBLIC, ACC_SUPER
      this_class: #4                          // org/example/operation/ArithmeticOperation
      super_class: #5                         // java/lang/Object
      interfaces: 0, fields: 0, methods: 2, attributes: 1
    Constant pool:
       #1 = Methodref          #5.#32         // java/lang/Object."<init>":()V
       #2 = Integer            12345678
       #3 = Integer            987654321
       #4 = Class              #33            // org/example/operation/ArithmeticOperation
       #5 = Class              #34            // java/lang/Object
       #6 = Utf8               <init>
       #7 = Utf8               ()V
       #8 = Utf8               Code
       #9 = Utf8               LineNumberTable
      #10 = Utf8               LocalVariableTable
      #11 = Utf8               this
      #12 = Utf8               Lorg/example/operation/ArithmeticOperation;
      #13 = Utf8               main
      #14 = Utf8               ([Ljava/lang/String;)V
      #15 = Utf8               args
      #16 = Utf8               [Ljava/lang/String;
      #17 = Utf8               a
      #18 = Utf8               I
      #19 = Utf8               b
      #20 = Utf8               c
      #21 = Utf8               d
      #22 = Utf8               e
      #23 = Utf8               f
      #24 = Utf8               g
      #25 = Utf8               h
      #26 = Utf8               i
      #27 = Utf8               j
      #28 = Utf8               k
      #29 = Utf8               l
      #30 = Utf8               SourceFile
      #31 = Utf8               ArithmeticOperation.java
      #32 = NameAndType        #6:#7          // "<init>":()V
      #33 = Utf8               org/example/operation/ArithmeticOperation
      #34 = Utf8               java/lang/Object
    {
      public org.example.operation.ArithmeticOperation();
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
                0       5     0  this   Lorg/example/operation/ArithmeticOperation;
    
      public static void main(java.lang.String[]);
        descriptor: ([Ljava/lang/String;)V
        flags: (0x0009) ACC_PUBLIC, ACC_STATIC
        Code:
          stack=2, locals=13, args_size=1
             0: iconst_0
             1: istore_1
             2: iconst_m1
             3: istore_2
             4: bipush        -2
             6: istore_3
             7: bipush        6
             9: istore        4
            11: bipush        7
            13: istore        5
            15: ldc           #2                  // int 12345678
            17: istore        6
            19: ldc           #3                  // int 987654321
            21: istore        7
            23: iload_1
            24: iload         4
            26: iadd
            27: istore        8
            29: iload         5
            31: iload         6
            33: iadd
            34: istore        9
            36: iload         7
            38: iload         6
            40: isub
            41: istore        10
            43: iload         4
            45: iload         5
            47: imul
            48: istore        11
            50: iload         6
            52: iload         5
            54: idiv
            55: istore        12
            57: return
          LineNumberTable:
            line 5: 0
            line 6: 2
            line 7: 4
            line 8: 7
            line 9: 11
            line 10: 15
            line 11: 19
            line 12: 23
            line 13: 29
            line 14: 36
            line 15: 43
            line 16: 50
            line 17: 57
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
                0      58     0  args   [Ljava/lang/String;
                2      56     1     a   I
                4      54     2     b   I
                7      51     3     c   I
               11      47     4     d   I
               15      43     5     e   I
               19      39     6     f   I
               23      35     7     g   I
               29      29     8     h   I
               36      22     9     i   I
               43      15    10     j   I
               50       8    11     k   I
               57       1    12     l   I
    }
    SourceFile: "ArithmeticOperation.java"

# 要点解析
## Constant pool
在这个简单的示例中，Class的Constant pool中有此class的两个CONSTANT_Integer，后面的指令会用到这两个Integer。
请参照 [4.4. The Constant Pool](https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-4.html#jvms-4.4) 查看Constant Pool的详细信息。

    Constant pool:
       #1 = Methodref          #5.#32         // java/lang/Object."<init>":()V
       #2 = Integer            12345678
       #3 = Integer            987654321
       
## method 结构概述
以main方法为例，介绍下一个方法的几个重要组成部分：
- descriptor: 表示此method的参数只有一个，为String Array，返回值为空Void。
请参照 [Table 4.3-A. Interpretation of field descriptors](https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-4.html#jvms-4.3.2-200) 查看类型的完整信息。
- flags: 表示此method为public、static
- Code: 是此method的核心，里面包含了运行指令。
  - stack: 此method运行时的operand stack最大深度为2。赋值运算只涉及一个操作数，加减乘除涉及两个操作数。
  - locals: 包含参数在内，此方法有13个local variable（局部变量）。
- LineNumberTable: debugger 用于定位代码行数，这里不介绍。
- LocalVariableTable: 13个局部变量的数组信息。
  - Slot: 变量在数组中的索引，第0个是方法的参数args。
  - Name: 变量的名字
  - Signature: 变量的类型。请参照 [Table 4.3-A. Interpretation of field descriptors](https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-4.html#jvms-4.3.2-200) 查看类型的完整信息。

      public static void main(java.lang.String[]);
        descriptor: ([Ljava/lang/String;)V
        flags: (0x0009) ACC_PUBLIC, ACC_STATIC
        Code:
          stack=2, locals=13, args_size=1
             0: iconst_0
             1: istore_1
             ...
          LineNumberTable:
            line 5: 0
            line 6: 2
            ...
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
                0      58     0  args   [Ljava/lang/String;
                2      56     1     a   I
                4      54     2     b   I
                7      51     3     c   I
               11      47     4     d   I
               15      43     5     e   I
               19      39     6     f   I
               23      35     7     g   I
               29      29     8     h   I
               36      22     9     i   I
               43      15    10     j   I
               50       8    11     k   I
               57       1    12     l   I

## main code
接下来详细讲解下 main 方法的 Code 指令：
- 初始化时operand stack 是空的。
- **0: iconst_0** 把常量0放入operand stack中，此时operand stack中有一个元素：0。
- **1: istore_1** 把operand stack的第一个元素0取出来放到 LocalVariableTable Slot 为1的变量中，即 a = 0。此时operand stack恢复为空。
- ** 2: iconst_m1 把常量 -1 放入到operand stack中，此时operand stack中有一个元素：-1。
- **3: istore_2** 把operand stack的第一个元素-1取出来放到 LocalVariableTable Slot 为2的变量中，即 b = -1。此时operand stack恢复为空。
- **4: bipush        -2** 把常量-2放入operand stack中，此时operand stack中有一个元素：-2。
- **6: istore_3** 把operand stack的第一个元素-2取出来放到 LocalVariableTable Slot 为3的变量中，即 c = -2。此时operand stack恢复为空。
- **7: bipush        6** 把常量 6 放入到operand stack中，此时operand stack中有一个元素：6。
- **9: istore        4** 把operand stack的第一个元素6取出来放到 LocalVariableTable Slot 为4的变量中，即 d = 6。此时operand stack恢复为空。
- **11: bipush        7** 把常量 7 放入到operand stack中，此时operand stack中有一个元素：7。
- **13: istore        5** 把operand stack的第一个元素7取出来放到 LocalVariableTable Slot 为5的变量中，即 e = 7。此时operand stack恢复为空。
- **15: ldc           #2                  // int 12345678** 把常量 Constant pool #2 中保存的数字 12345678 放入到operand stack中，此时operand stack中有一个元素：12345678。
- **17: istore        6** 把operand stack的第一个元素12345678取出来放到 LocalVariableTable Slot 为6的变量中，即 f = 12345678。此时operand stack恢复为空。
- **19: ldc           #3                  // int 987654321** 把常量 Constant pool #3 中保存的数字 987654321 放入到operand stack中，此时operand stack中有一个元素：987654321。
- **21: istore        7** 把operand stack的第一个元素987654321取出来放到 LocalVariableTable Slot 为6的变量中，即 g = 987654321。此时operand stack恢复为空。
- **23: iload_1** 把 LocalVariableTable Slot 为1的变量即 a 加载进operand stack 中，此时operand stack中有一个元素 0。
- **24: iload         4** 把 LocalVariableTable Slot 为4的变量即 d 加载进operand stack 中，此时operand stack中有两个元素：6 和 0。
- **26: iadd** 把operand stack 中的前两个元素相加，6 + 0 = 6，此时operand stack中只有一个元素，即相加所得的结果：6。
- **27: istore        8** 把operand stack的第一个元素6取出来放到 LocalVariableTable Slot 为8的变量中，即 h = 8。此时operand stack恢复为空。
- **29: iload         5** 把 LocalVariableTable Slot 为5的变量即 e 加载进operand stack 中，此时operand stack中有一个元素 7。
- **31: iload         6** 把 LocalVariableTable Slot 为6的变量即 f 加载进operand stack 中，此时operand stack中有两个元素：12345678 和 7。
- **33: iadd** 把operand stack 中的前两个元素相加，12345678 + 7 = 12345685，此时operand stack中只有一个元素，即相加所得的结果：12345685。
- **34: istore        9** 把operand stack的第一个元素12345685取出来放到 LocalVariableTable Slot 为9的变量中，即 i = 12345685。此时operand stack恢复为空。
- **36: iload         7** 把 LocalVariableTable Slot 为7的变量即 g 加载进operand stack 中，此时operand stack中有一个元素 987654321。
- **38: iload         6** 把 LocalVariableTable Slot 为6的变量即 f 加载进operand stack 中，此时operand stack中有两个元素：7 和 987654321。
- **40: isub** 把operand stack 中的前两个元素相减，987654321 - 7 = 987654314。此时operand stack中只有一个元素，即相减所得的结果：987654314。
- **41: istore        10** 把operand stack的第一个元素12345685取出来放到 LocalVariableTable Slot 为10的变量中，即 j = 987654314。此时operand stack恢复为空。
- **43: iload         4** 把 LocalVariableTable Slot 为4的变量即 d 加载进operand stack 中，此时operand stack中有一个元素 6。
- **45: iload         5** 把 LocalVariableTable Slot 为5的变量即 e 加载进operand stack 中，此时operand stack中有两个元素：7 和 6。
- **47: imul** 把operand stack 中的前两个元素相乘，7 * 6 = 42。此时operand stack中只有一个元素，即相乘所得的结果：42。
- **48: istore        11** 把operand stack的第一个元素42取出来放到 LocalVariableTable Slot 为11的变量中，即 k = 42。此时operand stack恢复为空。
- **50: iload         6** 把 LocalVariableTable Slot 为6的变量即 f 加载进operand stack 中，此时operand stack中有一个元素 12345678。
- **52: iload         5** 把 LocalVariableTable Slot 为5的变量即 e 加载进operand stack 中，此时operand stack中有两个元素：7 和 12345678。
- **54: idiv** 把operand stack 中的前两个元素相除，12345678 / 7 = 42。此时operand stack中只有一个元素，即相乘所得的结果：1763668。
- **55: istore        12** 把operand stack的第一个元素1763668取出来放到 LocalVariableTable Slot 为12的变量中，即 l = 1763668。此时operand stack恢复为空。
- **57: return** 没有返回值，退出方法。

下面介绍两个小知识点，以加深对指令的理解，请参照 [Chapter 7. Opcode Mnemonics by Opcode](https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-7.html) 查看Java 11完整的助记符：
- iconst_0 vs bipush        6
  - **iconst_0** 表示把常量0放入进operand stack中。它本身是一个完整的指令，在class文件中只占用一个byte。
  **iconst_<n>**指令一共有7个：iconst_m1, iconst_0, iconst_1, iconst_2, iconst_3, iconst_4, iconst_5。
  - **bipush        6** 表示把常量6放入进operand stack中。它在class文件中占用两个byte，**bipush**是一个指令，它占用第一个byte，而后面的数字6占用第二个byte。
 - istore_1 vs istore        4
   - **istore_1** 表示把operand stack的第一个元素取出来，放到 LocalVariableTable Slot 为1的变量中。它在class文件中只占用一个byte。
   **istore_<n>**指令一共有4个：istore_0, istore_1, istore_2, istore_3。
   - **istore        4** 表示把operand stack的第一个元素取出来，放到 LocalVariableTable Slot 为4的变量中。它在class文件中占用两个byte。第一个byte为**istore**指令，第二个byte为数字4。
  