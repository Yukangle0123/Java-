package com.code.Thread.timer;

import java.util.concurrent.PriorityBlockingQueue;

public class Timer {
    //由四个部分组成
    //1.有一个类描述“任务”
    //2.有一个阻塞优先队列来管理这些任务
    //3.有一个线程来扫描这个队列，判断是否队首“任务”应该执行了
    //4.有一个接口来让调用者"安排"任务
    PriorityBlockingQueue<Task> queue =new PriorityBlockingQueue<>();
    Object mailBox = new Object();
    public Timer() {
        Worker worker = new Worker(queue,mailBox);
        worker.start();
    }
    public void schedule(Runnable runnable,long time){
        queue.put(new Task(runnable,time));
        synchronized (mailBox){
            mailBox.notify();
        }
    }

    public static void main(String[] args) {
        Timer timer =new Timer();
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hehe");
                timer.schedule(this,2000);
            }
        },2000);
    }
}
