package org.example.homework;

import java.util.concurrent.Semaphore;

public class SemaphoreSolution {
    private static final Semaphore semaphore = new Semaphore(1);
    private static final Semaphore notEmpty = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Thread thread = new Thread(() -> {
            try {
                semaphore.acquire();
                value = sum();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
                notEmpty.release();
            }
        });
        thread.start();

        int result = getValue(); //这是得到的返回值

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }

    private static int value;

    private static int getValue() throws InterruptedException {
        int result;
        notEmpty.acquire();
        semaphore.acquire();
        result = value;
        semaphore.release();
        return result;
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
