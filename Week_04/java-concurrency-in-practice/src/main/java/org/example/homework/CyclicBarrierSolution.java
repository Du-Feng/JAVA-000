package org.example.homework;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierSolution {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        FiboCalc calc = new FiboCalc();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1, new Runnable() {
            @Override
            public void run() {
                int result = calc.getValue(); //这是得到的返回值
                // 确保  拿到result 并输出
                System.out.println("异步计算结果为：" + result);
                System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
                // 然后退出main线程
            }
        });
        calc.setCyclicBarrier(cyclicBarrier);
        new Thread(calc).start();
    }

    static class FiboCalc implements Runnable {
        private volatile Integer value = null;
        CyclicBarrier cyclicBarrier;

        public void setCyclicBarrier(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        public Integer getValue() {
            return value;
        }

        @Override
        public void run() {
            try {
                value = sum();
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
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
}
