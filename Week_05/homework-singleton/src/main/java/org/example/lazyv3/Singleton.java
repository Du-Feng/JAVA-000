package org.example.lazyv3;

import java.util.ArrayList;
import java.util.List;

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
