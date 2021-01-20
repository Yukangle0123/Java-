package com.code.Thread;

public class ThreadDemo {
    private static class Counter {
        private long n = 0;
        public synchronized void increment() {
            n++;
        }
        public synchronized void decrement() {
            n--;
        }
        public synchronized long value() {
            return n;
        }
    }
    public static void main1(String[] args) throws InterruptedException {
        final int COUNT = 1000_0000;
        Counter counter = new Counter();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                counter.increment();
            }
        }, "李四");
        thread.start();
        for (int i = 0; i < COUNT; i++) {
            counter.decrement();
        }
        thread.join();
// 期望最终结果应该是 0
        System.out.println(counter.value());
    }
    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        synchronized (object) {
            System.out.println("等待中...");
            object.wait();
            System.out.println("等待已过...");
        }
        System.out.println("main方法结束...");
    }
}
