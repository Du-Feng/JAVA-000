package org.example.introduction.lock.deadlock;

public class LockMain {

    public static void main(String[] args) {
        Count count3 = new Count();

        ThreadA threadA = new ThreadA(count3);
        threadA.setName("线程A");
        threadA.start();

        ThreadB threadB = new ThreadB(count3);
        threadB.setName("线程B");
        threadB.start();
    }
}
