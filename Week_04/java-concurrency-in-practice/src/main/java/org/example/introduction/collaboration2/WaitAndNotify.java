package org.example.introduction.collaboration2;

public class WaitAndNotify {
    public static void main(String[] args) {
        Waiter methodClass = new Waiter();

        Thread t1 = new Thread(() -> {
            try {
                methodClass.product();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "producer");

        Thread t2 = new Thread(() -> {
            try {
                methodClass.consume();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "consumer1");

        Thread t3 = new Thread(() -> {
            try {
                methodClass.consume();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "consumer2");

        t1.start();
        t2.start();
        t3.start();

    }
}
