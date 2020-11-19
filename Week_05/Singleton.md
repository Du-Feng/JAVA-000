# 声明

此文不是原创，原文来自极客时间 Java性能调优实战的 [27 | 单例模式：如何创建单一对象优化系统性能？](https://time.geekbang.org/column/article/109564)

通过此笔记，加深自己的理解。



# 单例模式总结

单例的实现方式其实有很多，但总结起来就两种：饿汉模式和懒汉模式。

## 饿汉模式

示例代码：

```java
public class HungrySingleton {
    private final static HungrySingleton instance = new HungrySingleton(); // 自行创建实例

    // 构造函数
    private HungrySingleton() {
    }

    // 通过该函数向整个系统提供实例
    public static HungrySingleton getInstance() {
        return instance;
    }
}
```

这种实现单例的代码中，使用了 static 修饰了成员变量 instance，所以该变量会在类初始化的过程中被收集进类构造器即 方法中。在多线程场景下，JVM 会保证只有一个线程能执行该类的 方法，其它线程将会被阻塞等待。等到唯一的一次 方法执行完成，其它线程将不会再执行 方法，转而执行自己的代码。也就是说，static 修饰了成员变量 instance，在多线程的情况下能保证只实例化一次。这种方式实现的单例模式，在类初始化阶段就已经在堆内存中开辟了一块内存，用于存放实例化对象，所以也称为**饿汉模式**。

**优点**：可以保证多线程情况下实例的唯一性，而且 getInstance 直接返回唯一实例，性能非常高。

**缺点**：如果应用中不使用实例，就是导致内存的浪费。尤其在类成员变量比较多，或变量比较大的情况下，这种模式可能会在没有使用类对象的情况下，一直占用堆内存。试想下，如果一个第三方开源框架中的类都是基于饿汉模式实现的单例，这将会初始化所有单例类，无疑是灾难性的。



## 懒汉模式

### 懒汉模式单线程版

懒汉模式就是为了避免直接加载类对象时提前创建对象的一种单例设计模式。该模式使用懒加载方式，只有当系统使用到类对象时，才会将实例加载到堆内存中。通过以下代码，可以简单地了解下懒加载的实现方式：

```java
// 懒汉模式
public class LazySingleton {
    private static LazySingleton instance = null; // 不实例化

    //构造函数
    private LazySingleton() {
    }

    // 通过该函数向整个系统提供实例
    public static LazySingleton getInstance() {
        if (null == instance) { // 当instance为null时，则实例化对象，否则直接返回对象
            instance = new LazySingleton(); // 实例化对象
        }
        return instance; // 返回已存在的对象
    }
}
```

以上代码在单线程下运行是没有问题的，但要运行在多线程下，就会出现实例化多个类对象的情况。



### 懒汉模式多线程版

**优点**：Singleton 实例在第一次使用的时候才会加载，如果不使用，不会浪费不必要的内存。

**缺点**：在第一次使用的时候获取实例较慢。同步锁会增加锁竞争，带来系统性能开销，从而导致系统性能下降，因此这种方式也会降低单例模式的性能。



**注意**：如果不加 volatile 关键字，双重检查+synchronized 可能会失效，原因是JVM会指定重排序的优化机制，volatile 禁止指令重排序。

```java
// 懒汉模式 + synchronized + volatile static field + double-check
public final class Singleton {
    private volatile static Singleton instance = null; // 不实例化
    public List<String> list = null; // list属性

    // 构造函数
    private Singleton() {
        list = new ArrayList<String>();
    }

    public static Singleton getInstance() { // 加同步锁，通过该函数向整个系统提供实例
        if (null == instance) { // 第一次判断，当instance为null时，则实例化对象，否则直接返回对象
            synchronized (Singleton.class) { // 同步锁
                if (null == instance) { // 第二次判断
                    instance = new Singleton(); // 实例化对象
                }
            }
        }
        return instance; // 返回已存在的对象
    }
}
```

以上这种同步锁 +Double-Check 的实现方式相对来说，复杂且加了同步锁，那有没有稍微简单一点儿的可以实现线程安全的懒加载方式呢？



### 懒汉模式内部类版

属于懒汉模式，优缺点与懒汉模式相同，但是不需要同步，减少了同步开销。

在饿汉模式中，使用了 static 修饰了成员变量 instance，所以该变量会在类初始化的过程中被收集进类构造器即 方法中。在多线程场景下，JVM 会保证只有一个线程能执行该类的 方法，其它线程将会被阻塞等待。这种方式可以保证内存的可见性、顺序性以及原子性。如果我们在 Singleton 类中创建一个内部类来实现成员变量的初始化，则可以避免多线程下重复创建对象的情况发生。这种方式，只有在第一次调用 getInstance() 方法时，才会加载 InnerSingleton 类，而只有在加载 InnerSingleton 类之后，才会实例化创建对象。具体实现如下：

```java
// 懒汉模式 内部类实现
public final class Singleton {
    public List<String> list = null; // list属性

    // 构造函数
    private Singleton() {
        list = new ArrayList<String>();
    }

    // 内部类实现
    public static class InnerSingleton {
        private static Singleton instance = new Singleton(); // 自行创建实例
    }

    public static Singleton getInstance() {
        return InnerSingleton.instance; // 返回内部类中的静态变量
    }
}
```