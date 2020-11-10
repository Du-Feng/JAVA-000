package org.example.introduction.collaboration1;

public class Join {

    public static void main(String[] args) {
        Object oo = new Object();

        JoinedThread thread1 = new JoinedThread("thread1 -- ");
        thread1.setObj(oo);
        thread1.start();

        synchronized (thread1) {
            for (int i = 0; i < 100; i++) {
                if (i == 20) {
                    try {
                        thread1.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " -- " + i);
            }
        }
    }

}
