package org.example.homework;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownLatchSolution {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        AtomicInteger fiboResult = new AtomicInteger();

        Thread thread = new Thread(() -> {
            fiboResult.set(sum()); //这是得到的返回值
            countDownLatch.countDown();
        });
        thread.start();

        countDownLatch.await();
        int result = fiboResult.get();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + fiboResult);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }
}