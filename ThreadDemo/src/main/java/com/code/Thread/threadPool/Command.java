package com.code.Thread.threadPool;

public class Command implements Runnable{
    public int id;

    public Command(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("正在执行任务: " + id);
    }
}
