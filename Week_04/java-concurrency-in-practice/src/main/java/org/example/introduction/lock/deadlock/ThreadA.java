package org.example.introduction.lock.deadlock;

public class ThreadA extends Thread {
    private Count count3;

    public ThreadA(Count count3) {
        this.count3 = count3;
    }

    public void run() {
        count3.add();
    }

}
