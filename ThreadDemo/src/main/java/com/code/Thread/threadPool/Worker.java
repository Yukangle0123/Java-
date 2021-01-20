package com.code.Thread.threadPool;

import java.util.concurrent.BlockingQueue;

public class Worker extends Thread{
    BlockingQueue<Runnable> queue =null;
    int id ;

    public Worker(BlockingQueue<Runnable> queue,int id) {
        this.queue = queue;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while(!Thread.currentThread().isInterrupted()){
                Runnable command = queue.take();
                System.out.println("Thread " + id + "  Running...");
                command.run();
            }
        } catch (InterruptedException e) {
            System.out.println("线程结束");
        }
    }
}
