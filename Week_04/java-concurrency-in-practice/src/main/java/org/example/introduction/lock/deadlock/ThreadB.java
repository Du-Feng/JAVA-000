package org.example.introduction.lock.deadlock;

public class ThreadB extends Thread {
    private Count count3;

    public ThreadB(Count count3) {
        this.count3 = count3;
    }

    public void run() {
        count3.lockMethod();
    }

}
