package com.code.Thread;

public class ThreadStateTransfer {
    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        Thread t = new Thread(() -> {
            synchronized (object) {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 1000_0000; i++) {}
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(t.getState());;
        t.start();
        System.out.println(t.getState());
        Thread.sleep(10);
        synchronized (object) {
            for (int i = 0; i < 10; i++) {
                System.out.println(t.getState());
            }
            object.notify();
        }
        while (t.isAlive()) {
            System.out.println(t.getState());
        }
    }
    private static class Counter {
        private long n = 0;
        public void increment() {
            n++;
        }
        public void decrement() {
            n--;
        }
        public long value() {
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
        });
        thread.start();
        for (int i = 0; i < COUNT; i++) {
            counter.decrement();
        }
        thread.join();

        System.out.println(counter.value());
    }
}
