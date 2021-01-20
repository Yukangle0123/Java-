package com.code.Thread.timer;

public class Task implements Comparable<Task> {
    private Runnable command;
    private long time;//绝对时间
    //after 指的是多少ms后执行 即相对时间
    public Task(Runnable command, long after) {
        this.command = command;
        this.time = after+System.currentTimeMillis();
    }
    public void run(){
        command.run();
    }

    public long getTime() {
        return time;
    }

    @Override
    public int compareTo(Task o) {
        return (int)(this.time-o.time);
    }
}
