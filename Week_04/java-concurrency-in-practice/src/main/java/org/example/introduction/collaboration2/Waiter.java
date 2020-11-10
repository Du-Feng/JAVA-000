package org.example.introduction.collaboration2;

class Waiter {
    // 定义生产最大量
    private final int MAX_COUNT = 20;

    int productCount = 0;

    public synchronized void product() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ":::produce:::" + productCount);
            Thread.sleep(10);
            if (productCount >= MAX_COUNT) {
                System.out.println("货舱已满,,.不必再生产");
                wait();
            } else {
                productCount++;
            }

            notifyAll();
        }
    }

    public synchronized void consume() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ":::consume:::" + productCount);
            Thread.sleep(10);
            if (productCount <= 0) {
                System.out.println("货舱已无货...无法消费");
                wait();
            } else {
                productCount--;
            }

            notifyAll();
        }
    }
}
