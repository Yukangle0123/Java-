package java40_1125;

import java.util.concurrent.PriorityBlockingQueue;

public class Timer {
    static class Task implements Comparable<Task>{
        Runnable task=null;
        long time;
        Task(Runnable task,int after){
            this.task=task;
            this.time=System.currentTimeMillis()+after;
        }
        public void run(){
            task.run();
        }

        @Override
        public int compareTo(Task o) {
            return (int)(this.time-o.time);
        }
    }
    //使用一个线程循环判断阻塞优先队列中的task
    static class Worker extends Thread{
        public Object mailBox=null;
        public PriorityBlockingQueue<Task> queue=null;
        Worker(PriorityBlockingQueue queue,Object mailBox){
            this.queue=queue;
            this.mailBox=mailBox;
        }
        @Override
        public void run() {
            while (true) {
                try {
                    Task task = queue.take();
                    long curTime=System.currentTimeMillis();
                    if(task.time>curTime){
                        queue.put(task);
                        synchronized (mailBox){
                            mailBox.wait(task.time-curTime);
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
    PriorityBlockingQueue <Task>queue=new PriorityBlockingQueue<>();
    Object mailBox=new Object();
    public void schedule(Runnable command,int after){
        Task task=new Task(command,after);
        queue.put(task);
        synchronized (mailBox){
            mailBox.notify();
        }
    }
    Timer(){
        Worker worker=new Worker(queue,mailBox);
        worker.start();
    }

    public static void main(String[] args) {
        Timer timer=new Timer();
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hehe");
                timer.schedule(this,2000);
            }
        },2000);
    }


}
