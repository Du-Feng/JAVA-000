[toc]

# [返回主页](../index.md)



# 简介

结合秦老师Java并发编程三次课程内容和示例程序、以及极客时间《[Java并发编程实战](https://time.geekbang.org/column/intro/159)》，记录自己学习Java并发编程的历程，作为对两个课程的补充，同时也加深自己的理解。



# 理论知识

## 管程、进程及线程之间的区别

参照 [管程，进程及线程之间的区别](https://blog.csdn.net/qq_35212671/article/details/52713822)

### 进程、线程、管程各自的概念

**进程**：进程是一个具有一定独立功能的程序关于某个数据集合的一次运行活动。它是操作系统动态执行的基本单元，在传统的操作系统中，进程既是基本的分配单元，也是基本的执行单元。

**线程**：线程是进程中的实体，一个进程可以拥有多个线程，一个线程必须有一个父进程。线程不拥有系统资源，只有运行必须的一些数据结构；它与父进程的其它线程共享该进程所拥有的全部资源。线程可以创建和撤消线程，从而实现程序的并发执行。一般，线程具有就绪、阻塞和运行三种基本状态。

**管程**：管程定义了一个数据结构和能为并发进程所执行的一组操作，这组操作能同步进程和改变管程中的数据。



### 进程和线程的关系

简而言之，一个进程至少有一个线程。 线程的划分尺度小于进程，使得多线程程序的并发性高。另外，进程在执行过程中拥有独立的内存单元，而多个线程共享内存，从而极大地提高了程序的运行效率。 线程在执行过程中与进程还是有区别的。每个独立的线程有一个程序运行的入口、顺序执行序列和程序的出口。但是线程不能够独立执行，必须依存在应用程序中，由应用程序提供多个线程执行控制。

从逻辑角度来看，多线程的意义在于一个应用程序中，有多个执行部分可以同时执行。但操作系统并没有将多个线程看做多个独立的应用，来实现进程的调度和管理以及资源分配。这就是进程和线程的重要区别。

进程是具有一定独立功能的程序关于某个数据集合上的一次运行活动，进程是系统进行资源分配和调度的一个独立单位。线程是进程的一个实体，是CPU调度和分派的基本单位，它是比进程更小的能独立运行的基本单位。线程自己基本上不拥有系统资源，只拥有一点在运行中必不可少的资源(如程序计数器，一组寄存器和栈)，但是它可与同属一个进程的其他的线程共享进程所拥有的全部资源。 

一个线程可以创建和撤销另一个线程；同一个进程中的多个线程之间可以并发执行。

### 下面我们分析进程和线程之间的关系：

- 假如我们把整条道路看成是一个“进程”的话，那么由白色虚线分隔开来的各个车道就是进程中的各个“线程”了。
- 这些线程(车道)共享了进程(道路)的公共资源(土地资源)。
- 这些线程(车道)必须依赖于进程(道路)，也就是说，线程不能脱离于进程而存在(就像离开了道路，车道也就没有意义了)。
- 这些线程(车道)之间可以并发执行(各个车道你走你的，我走我的)，也可以互相同步(某些车道在交通灯亮时禁止继续前行或转弯，必须等待其它车道的车辆通行完毕)。
- 这些线程(车道)之间依靠代码逻辑(交通灯)来控制运行，一旦代码逻辑控制有误(死锁，多个线程同时竞争唯一资源)，那么线程将陷入混乱，无序之中。
- 这些线程(车道)之间谁先运行是未知的，只有在线程刚好被分配到CPU时间片(交通灯变化)的那一刻才能知道。

### 管程的引用 

信号量机制的引入解决了进程同步的描述问题，但信号量的大量同步操作分散在各个进程中不便于管理，还有可能导致系统死锁。如：生产者消费者问题中将P、V颠倒可能死锁。

为此Dijkstra于1971年提出：把所有进程对某一种临界资源的同步操作都集中起来，构成一个所谓的秘书进程。凡要访问该临界资源的进程，都需先报告秘书，由秘书来实现诸进程对同一临界资源的互斥使用。

管程由四部分组成：

1. 管程内部的共享变量。
2. 管程内部的条件变量。
3. 管程内部并行执行的进程。
4. 对于局部与管程内部的共享数据设置初始值的语句。

由此可见，管程相当于围墙，它把共享变量和对它进行操作的若干个过程围了起来，所有的进程要访问临界资源时，都必须经过管程才能进入，而管程每次只允许一个进程进入管程，从而实现了进程的互斥。



## 并发(Concurrency)与并发(Parallelism)的区别

请参照：

- [Difference between Concurrency and Parallelism - GeeksforGeeks](https://www.geeksforgeeks.org/difference-between-concurrency-and-parallelism/)
- [Crazy Snail-CSDN博客：并发和并行的区别](https://blog.csdn.net/java_zero2one/article/details/51477791)



## 并发编程领域三个核心问题

参照 [学习攻略 | 如何才能学好并发编程？](https://time.geekbang.org/column/article/83267)。

并发编程领域可以抽象成三个核心问题：分工、同步和互斥。

分工、同步主要强调的是性能；互斥主要是强调安全性，用专业术语叫“线程安全”。

### 分工

**分工指的是如何高效地拆解任务并分配给线程。**

Java SDK 并发包里的 Executor、Fork/Join、Future 本质上都是一种分工方法。除此之外，并发编程领域还总结了一些设计模式，基本上都是和分工方法相关的，例如生产者 - 消费者、Thread-Per-Message、Worker Thread 模式等都是用来指导你如何分工的。

### 同步

**同步指的是线程之间如何协作。**

一个线程执行完了一个任务，如何通知执行后续任务的线程开工。

**工作中遇到的线程协作问题，基本上都可以描述为这样的一个问题：当某个条件不满足时，线程需要等待，当某个条件满足时，线程需要被唤醒执行。**

协作一般是和分工相关的。Java SDK 并发包里的 Executor、Fork/Join、Future 本质上都是分工方法，但同时也能解决线程协作的问题。例如，用 Future 可以发起一个异步调用，当主线程通过 get() 方法取结果时，主线程就会等待，当异步执行的结果返回时，get() 方法就自动返回了。主线程和异步线程之间的协作，Future 工具类已经帮我们解决了。除此之外，Java SDK 里提供的 CountDownLatch、CyclicBarrier、Phaser、Exchanger 也都是用来解决线程协作问题的。



在 Java 并发编程领域，解决协作问题的核心技术是**管程**，上面提到的所有线程协作技术底层都是利用管程解决的。管程是一种解决并发问题的通用模型，除了能解决线程协作问题，还能解决下面我们将要介绍的互斥问题。可以这么说，**管程是解决并发问题的万能钥匙**。所以说，这部分内容的学习，关键是理解管程模型，学好它就可以解决所有问题。其次是了解 Java SDK 并发包提供的几个线程协作的工具类的应用场景，用好它们可以妥妥地提高你的工作效率。

### 互斥

**互斥则是保证同一时刻只允许一个线程访问共享资源。**

实现互斥的核心技术就是锁，Java 语言里 synchronized、SDK 里的各种 Lock 都能解决互斥问题。虽说锁解决了安全性问题，但同时也带来了性能问题，那如何保证安全性的同时又尽量提高性能呢？可以分场景优化，Java SDK 里提供的 ReadWriteLock、StampedLock 就可以优化读多写少场景下锁的性能。还可以使用无锁的数据结构，例如 Java SDK 里提供的原子类都是基于无锁技术实现的。除此之外，还有一些其他的方案，原理是不共享变量或者变量只允许读。这方面，Java 提供了 Thread Local 和 final 关键字，还有一种 Copy-on-write 的模式。



下图是Java并发编程的知识全景图：

![Java Concurrency Roadmap](assets/theory/java-concurrency-roadmap.png)

## 并发编程的三大问题

参照 [01 | 可见性、原子性和有序性问题：并发编程Bug的源头](https://time.geekbang.org/column/article/83682)。

### 可见性

一个线程对共享变量的修改，另外一个线程能够立刻看到，我们称为可见性。

**缓存会导致可见性问题。**

多核 CPU 的缓存与内存关系图:

![Visibility](assets/theory/visibility.png)

### 原子性

原子性是指一个操作是不可中断的，要么全部执行成功要么全部执行失败，有着**同生共死**的感觉。即使在多个线程一起执行的时候，一个操作一旦开始，就不会被其他线程所干扰。我们把一个或者多个操作在 CPU 执行的过程中不被中断的特性称为原子性。

**线程切换带来原子性问题。**

非原子操作的执行路径示意图:

![Atomicity](assets/theory/atomicity.png)

### 有序性

**编译优化带来的有序性问题。**

双重检查创建单例的异常执行路径:

![Ordering](assets/theory/ordering.png)

## Java关键字

参照 [02 | Java内存模型：看Java如何解决可见性和有序性问题](https://time.geekbang.org/column/article/84017)。

在并发编程中分析线程安全的问题时往往需要切入点，那就是两大核心：JMM抽象内存模型以及happens-before规则。

Java 内存模型是个很复杂的规范，可以从不同的视角来解读，站在我们这些程序员的视角，本质上可以理解为，Java 内存模型规范了 JVM 如何提供**按需禁用缓存和编译优化**的方法。具体来说，这些方法包括 **volatile**、**synchronized** 和 **final** 三个关键字，以及六项 **Happens-Before** 规则。

例如，我们声明一个 volatile 变量 volatile int x = 0，它表达的是：告诉编译器，对这个变量的读写，不能使用 CPU 缓存，必须从内存中读取或者写入。



## 用锁的最佳实践

Doug Lea《Java 并发编程：设计原则与模式》一书中，
推荐的三个用锁的最佳实践，它们分别是：

1. 永远只在更新对象的成员变量时加锁
2. 永远只在访问可变的成员变量时加锁
3. 永远不在调用其他对象的方法时加锁



另外，KK总结-最小使用锁：

1. 降低锁范围：锁定代码的范围/作用域
2. 细分锁粒度：将一个大锁，拆分成多个小锁



## Java 线程状态

参照 [09 | Java线程（上）：Java线程的生命周期](https://time.geekbang.org/column/article/86366)

### 通用的线程生命周期

通用的线程生命周期基本上可以用下图这个“五态模型”来描述。这五态分别是：

- 初始状态
- 可运行状态
- 运行状态
- 休眠状态
- 终止状态。

通用线程状态转换图——五态模型:

![Thread Common State](assets/theory/thread-common-state.png)

### Java 中线程的生命周期

Java 语言中线程共有六种状态，分别是：

- NEW（初始化状态）
- RUNNABLE（可运行 / 运行状态）
- BLOCKED（阻塞状态）
- WAITING（无时限等待）
- TIMED_WAITING（有时限等待）
- TERMINATED（终止状态）

Java 中的线程状态转换图:

![Java Thread State](assets/theory/java-thread-state.png)

# 实践

以下是练习代码的重点讲解。

## package org.example.introduction

### DaemonThread

- 默认线程是前台线程，用于完成某个任务。

- 守护线程用于为其它线程提供服务，比如keep alive。可用如下代码指定一个线程为守护线程。

```java
thread.setDaemon(true);
```

- 当所有前台线程结束后，主线程就会结束，守护线程也就会随之结束。

**注**：由于这个练习中只有一个主线程、一个守护线程，也就是说没有任何任务线程，所以当为true时，守护线程不会启动。



### package org.example.introduction.start1

演示启动线程的一个方法：实现接口 **Runnable**，没有任何返回值。

类图如下：

```java
Runner1 runner1 = new Runner1();
Thread thread1 = new Thread(runner1);

Runner2 runner2 = new Runner2();
Thread thread2 = new Thread(runner2);

thread1.start();
thread2.start();
```

Runner1和Runner2都继承自Runnable，只有一个没有任何返回值的Run方法：

```java
public class Runner1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("进入Runner1运行状态——————————" + i);
        }
    }
}
```

接下来演示如何中断线程2：

```
thread2.interrupt();
```

Runner2的run方法中会检查线程是否被中断，然后重置中断状态。

```
boolean result = Thread.currentThread().isInterrupted(); // 检查中断状态，结果为true
boolean result1 = Thread.interrupted(); // 重置状态
boolean result3 = Thread.currentThread().isInterrupted(); // 重新获取中断状态，结果为false
```

### package org.example.introduction.start2

这里演示了三种启动线程的办法：

- 实现  Class **Thread**。其实Thread实现了interface Runnable，它包含了创建线程的方法，可以自启动，但一样没有返回值。
- 实现接口**Runnable**，没有返回值。
- 实现接口**Callable**，有返回值，可以通过**FutureTask**获取到返回值。

类图如下：



### package org.example.introduction.sync

演示synchronized的使用方法：

- 实例方法锁的是 this 代表的对象
- 静态方法锁的是对应的 Class 对象
- synchronized块使用的是 this 对象
- synchronized(obj)使用的是obj对象

**注**：Thread3 中的锁无效。



### package org.example.introduction.collaboration1

演示了如何通过 thread.join() 实现线程间的协作和通信。

执行如下方法时，当前线程会暂停，直至thread1执行完，才会再继续执行当前线程。

```java
thread1.join();
```



### package org.example.introduction.collaboration2

演示了如何通过object.wait() 和 object.notifyAll() 实现线程间的协作和通信。

需要结合 synchronized ,它们的后台锁是 this，即当前对象：

- object.wait() : 放弃锁
- object.notify() : 通知一个等待的线程来抢这个锁
- object.notifyAll() : 通知所有等待的线程来抢这个锁



### package org.example.introduction.lock

演示lock的基本用法。



#### ConditionDemo

演示 lock 和 condition的结合使用方法。



#### ObjectCache

演示了Semaphore的使用方法。



#### package org.example.introduction.lock

演示ReentrantLock 的使用办法，它继承自interface Lock，在同一个线程中，可以多次获取到此锁。



#### package org.example.introduction.lock.reentrantreadwrite

演示ReentrantReadWriteLock 的使用办法，它继承自interface ReadWriteLock，有读、写两把锁。

注：ReadWriteLock 不支持 condition。



#### ReentrantReadWriteLockDemo2

继续演示ReentrantReadWriteLock 的使用办法。



#### StampedLockDemo

我们先来看看在使用上 StampedLock 和上一篇文章讲的 ReadWriteLock 有哪些区别。

ReadWriteLock 支持两种模式：一种是**读锁**，一种是**写锁**。

而 StampedLock 支持三种模式，分别是：**写锁**、**悲观读锁**和**乐观读**。其中，写锁、悲观读锁的语义和 ReadWriteLock 的写锁、读锁的语义非常类似，允许多个线程同时获取悲观读锁，但是只允许一个线程获取写锁，写锁和悲观读锁是互斥的。不同的是：StampedLock 里的写锁和悲观读锁加锁成功之后，都会返回一个 stamp；然后解锁的时候，需要传入这个 stamp。

**注意这里，我们用的是“乐观读”这个词，而不是“乐观读锁”，**是要提醒你，**乐观读这个操作是无锁的**，所以相比较 ReadWriteLock 的读锁，乐观读的性能更好一些。

**StampedLock 的性能之所以比 ReadWriteLock 还要好**，其关键是 StampedLock 支持乐观读的方式。ReadWriteLock 支持多个线程同时读，但是当多个线程同时读的时候，所有的写操作会被阻塞；而 StampedLock 提供的乐观读，是允许一个线程获取写锁的，也就是说不是所有的写操作都被阻塞。

**对于读多写少的场景 StampedLock 性能很好**，简单的应用场景基本上可以替代 ReadWriteLock，**但是 StampedLock 的功能仅仅是 ReadWriteLock 的子集，在使用的时候，还是有几个地方需要注意一下**。

- StampedLock 不支持重入。
- StampedLock 的悲观读锁、写锁都不支持条件变量。
- 如果线程阻塞在 StampedLock 的 readLock() 或者 writeLock() 上时，此时调用该阻塞线程的 interrupt() 方法，会导致 CPU 飙升。所以，使用 StampedLock 一定不要调用中断操作，如果需要支持中断功能，一定使用可中断的悲观读锁 readLockInterruptibly() 和写锁 writeLockInterruptibly()。这个规则一定要记清楚。



#### package org.example.introduction.lock.deadlock

演示了一种死锁。

注：这里使用byte[]作为锁，不推荐这种用法，推荐使用object作为锁。



#### LockSupportDemo

演示LockSupport的使用方法。

- Thread 1 被park后，使用interrupt唤醒t1。
- Thread 1 被park后，使用unpark唤醒t1。



### package org.example.introduction.atomic

#### Count

实现了int的加法，但是线程不安全。

#### SyncCount

利用synchronized实现了线程安全的int加法。



#### AtomicCount和AtomicMain

演示了AtomicInteger的使用方法。

LongDemo

演示了AtomicLong和LongAdder。



## package org.example.advance

### package org.example.advance.future

#### FutureDemo

演示Future的用法。



#### FutureTaskDemo1 & FutureTaskDemo2

演示 FutureTask 的用法。



#### CompletableFutureDemo

##### 创建 CompletableFuture 对象

创建 CompletableFuture 对象主要靠下面代码中展示的这 4 个静态方法，我们先看前两个：runAsync(Runnable runnable)和supplyAsync(Supplier supplier)，它们之间的区别是：Runnable 接口的 run() 方法没有返回值，而 Supplier 接口的 get() 方法是有返回值的。

前两个方法和后两个方法的区别在于：后两个方法可以指定线程池参数。

```java
//使用默认线程池
static CompletableFuture<Void> runAsync(Runnable runnable)
static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
//可以指定线程池  
static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
```



##### 如何理解 CompletionStage 接口

可以站在分工的角度类比一下工作流。任务是有时序关系的，比如有串行关系、并行关系、汇聚关系等。这样说可能有点抽象，这里还举前面烧水泡茶的例子，其中洗水壶和烧开水就是串行关系，洗水壶、烧开水和洗茶壶、洗茶杯这两组任务之间就是并行关系，而烧开水、拿茶叶和泡茶就是汇聚关系。

串行关系：

![CompletionStage Flow](assets/future/CompletionStage-flow-serial.png)



并行关系：

![CompletionStage Flow](assets/future/CompletionStage-flow-parallel.png)



汇聚关系：

![CompletionStage Flow](assets/future/CompletionStage-flow-aggregation.png)



| Type                     | 串行                                                         |
| ------------------------ | ------------------------------------------------------------ |
| 有参数和返回值           | CompletionStage<R> thenApply(fn);<br/>CompletionStage<R> thenApplyAsync(fn); |
| 有参数，但是没有返回值   | CompletionStage<Void> thenAccept(consumer);<br/>CompletionStage<Void> thenAcceptAsync(consumer); |
| 无参数，无返回值         | CompletionStage<Void> thenRun(action);<br/>CompletionStage<Void> thenRunAsync(action); |
| 子流程（有参数和返回值） | CompletionStage<R> thenCompose(fn);<br/>CompletionStage<R> thenComposeAsync(fn); |



| Type                   | AND汇聚                                                      |
| ---------------------- | ------------------------------------------------------------ |
| 有参数和返回值         | CompletionStage<R> thenCombine(other, fn);<br/>CompletionStage<R> thenCombineAsync(other, fn); |
| 有参数，但是没有返回值 | CompletionStage<Void> thenAcceptBoth(other, consumer);<br/>CompletionStage<Void> thenAcceptBothAsync(other, consumer); |
| 无参数，无返回值       | CompletionStage<Void> runAfterBoth(other, action);<br/>CompletionStage<Void> runAfterBothAsync(other, action); |



| Type                   | OR汇聚                                                       |
| ---------------------- | ------------------------------------------------------------ |
| 有参数和返回值         | CompletionStage applyToEither(other, fn);<br/>CompletionStage applyToEitherAsync(other, fn); |
| 有参数，但是没有返回值 | CompletionStage acceptEither(other, consumer);<br/>CompletionStage acceptEitherAsync(other, consumer); |
| 无参数，无返回值       | CompletionStage runAfterEither(other, action);<br/>CompletionStage runAfterEitherAsync(other, action); |



##### 获取 CompletableFuture 结果

上面的组合完毕后，在最后执行join()方法，可以获取结果。



#### package org.example.advance.tool

演示各种工具类的使用方法：

- CountDownLatch
- CyclicBarrier
- Semaphore



#### package org.example.advance.threadpool

演示线程池的使用方法。



#### package org.example.advance.threadlocal

演示ThreadLocal的使用方法。



#### package org.example.advance.collection

演示线程安全collection的使用方法。



#### package org.example.advance.stream

演示线程安全collection的stream用法。