package com.code.Thread.timer;

import java.util.concurrent.PriorityBlockingQueue;

public class Worker extends Thread {

    private PriorityBlockingQueue <Task> queue = null;
    private Object mailBox = null;
    public Worker(PriorityBlockingQueue<Task> queue,Object mailBox){
        this.queue = queue;
        this.mailBox = mailBox;
    }
    @Override
    public void run() {
        while(true){
            try {
                Task task =queue.take();
                long curTime = System.currentTimeMillis();
                if(curTime<task.getTime()){
                    queue.put(task);
                    synchronized (mailBox){
                        mailBox.wait(task.getTime()-curTime);
                    }
                }else{
                    task.run();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
