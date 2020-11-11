package org.example.introduction.lock.reentrant;

public class ReentrantLockDemo {
    public static void main(String[] args) {
        final ReentrantCount count = new ReentrantCount();

        for (int i = 0; i < 2; i++) {
            new Thread() {
                public void run() {
                    count.get();
                }
            }.start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread() {
                public void run() {
                    count.put();
                }
            }.start();
        }
    }
}
