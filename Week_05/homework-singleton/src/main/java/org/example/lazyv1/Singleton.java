package org.example.lazyv1;

// 懒汉模式
public class Singleton {
    private static Singleton instance = null; // 不实例化

    //构造函数
    private Singleton() {
    }

    // 通过该函数向整个系统提供实例
    public static Singleton getInstance() {
        if (null == instance) { // 当instance为null时，则实例化对象，否则直接返回对象
            instance = new Singleton(); // 实例化对象
        }
        return instance; // 返回已存在的对象
    }
}
