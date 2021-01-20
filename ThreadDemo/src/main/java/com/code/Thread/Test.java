package com.code.Thread;

public class Test {
    public static void main(String[] args) {
        Object o = new Object();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 100 ; i++){
                    try {
                        synchronized (o){
                            o.wait();
                        }
                        System.out.println("正在打印："+i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
        t.interrupt();

    }
}
