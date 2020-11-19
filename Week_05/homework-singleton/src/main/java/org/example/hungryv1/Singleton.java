package org.example.hungryv1;

public class Singleton {
    private final static Singleton instance = new Singleton(); // 自行创建实例

    // 构造函数
    private Singleton() {
    }

    // 通过该函数向整个系统提供实例
    public static Singleton getInstance() {
        return instance;
    }
}
