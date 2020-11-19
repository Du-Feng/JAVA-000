package org.example.lazyv2;

import java.util.ArrayList;
import java.util.List;

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
