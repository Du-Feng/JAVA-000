package org.example.introduction.lock;

import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {
    private int x, y;

    public StampedLockDemo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    final StampedLock sl = new StampedLock();

    public static void main(String[] args) {
        StampedLockDemo demo = new StampedLockDemo(4, 7);
        System.out.println("距离：" + demo.distanceFromOrigin());
    }

    //计算到原点的距离
    int distanceFromOrigin() {
        // 乐观读
        long stamp = sl.tryOptimisticRead();
        // 读入局部变量，
        // 读的过程数据可能被修改
        int curX = x, curY = y;
        //判断执行读操作期间，
        //是否存在写操作，如果存在，
        //则sl.validate返回false
        if (!sl.validate(stamp)) {
            // 升级为悲观读锁
            stamp = sl.readLock();
            try {
                curX = x;
                curY = y;
            } finally {
                //释放悲观读锁
                sl.unlockRead(stamp);
            }
        }
        return (int) Math.sqrt(curX * curX + curY * curY);
    }
}
