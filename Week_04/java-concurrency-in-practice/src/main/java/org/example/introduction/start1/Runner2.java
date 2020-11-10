package org.example.introduction.start1;

public class Runner2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("进入Runner2运行状态——————————" + i);
        }

        boolean result = Thread.currentThread().isInterrupted(); // 检查中断状态，结果为true
        boolean result1 = Thread.interrupted(); // 重置状态
        boolean result3 = Thread.currentThread().isInterrupted(); // 重新获取中断状态，结果为false

        System.out.println("Runner2.run isInterrupted before ===>" + result);
        System.out.println("Runner2.run interrupted ===>" + result1);
        System.out.println("Runner2.run isInterrupted after ===>" + result3);
    }
}
