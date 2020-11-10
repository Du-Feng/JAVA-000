package org.example.introduction.sync;


public class Thread2 {
    public void countDown1() {
        synchronized (this) {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void countDown2() {
        int i = 5;
        while (i-- > 0) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final Thread2 runner = new Thread2();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                runner.countDown1();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                runner.countDown2();
            }
        }, "t2");
        t2.start();
        t1.start();
    }
}

