package org.example.introduction.lock.deadlock;


public class Count3 {

    private byte[] lock1 = new byte[1];
    private byte[] lock2 = new byte[1];

    public int num = 0;

    public void add() {
        System.out.println(Thread.currentThread().getName() + ":::add method:::try to get lock1");
        synchronized (lock1) {
            try {
                System.out.println(Thread.currentThread().getName() + ":::add method:::got lock1");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":::add method:::try to get lock2");
            synchronized (lock2) {
                num += 1;
            }
            System.out.println(Thread.currentThread().getName() + "_" + num);
        }
    }

    public void lockMethod() {
        System.out.println(Thread.currentThread().getName() + ":::lock method:::try to get lock2");
        synchronized (lock2) {
            try {
                System.out.println(Thread.currentThread().getName() + ":::add method:::got lock2");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":::lock method:::try to get lock1");
            synchronized (lock1) {
                num += 1;
            }
            System.out.println(Thread.currentThread().getName() + "_" + num);
        }
    }
}

