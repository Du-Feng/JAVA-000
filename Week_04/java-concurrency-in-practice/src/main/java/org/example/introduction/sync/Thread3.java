package org.example.introduction.sync;

public class Thread3 {
    class Inner {
        private void countDown1() {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : Inner.m4t1()=" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void countDown2() {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : Inner.m4t2()=" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void outerCountDown1(Inner inner) {
        synchronized (inner) { //使用对象锁
            inner.countDown1();
        }
    }

    private void outerCountDown2(Inner inner) {
        inner.countDown2();
    }

    public static void main(String[] args) {
        final Thread3 runner = new Thread3();
        final Inner inner = runner.new Inner();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                runner.outerCountDown1(inner);
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                runner.outerCountDown2(inner);
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}

