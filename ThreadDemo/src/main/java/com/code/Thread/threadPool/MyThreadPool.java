package com.code.Thread.threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyThreadPool {
    //用阻塞队列来管理任务
    BlockingQueue<Runnable> queue =new LinkedBlockingQueue<>();

    //使用List来管理工作线程
    List<Worker> workers =new ArrayList<>();

    public static final int MAXCOUNT =10;

    public void execute(Runnable command) throws InterruptedException {
        if(workers.size()<MAXCOUNT){
            Worker worker =new Worker(queue,workers.size());
            workers.add(worker);
            worker.start();
        }
        queue.put(command);
    }
    public void shutDown() throws InterruptedException {
        for(Worker worker :workers){
            worker.interrupt();
        }
        for(Worker worker :workers){
            worker.join();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThreadPool myThreadPool =new MyThreadPool();
        for(int i = 0; i < 1000; i++){
            Command command =new Command(i);
            myThreadPool.execute(command);
        }
        Thread.sleep(2000);
        myThreadPool.shutDown();
        System.out.println("线程池已被销毁");
    }

}
