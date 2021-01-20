package com.code.Thread;

public class BlockQueue {
    private int[]array =new int[1000];
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    public void put(int value) throws InterruptedException {
        //把value放在队尾
        synchronized (this) {
            if(size==array.length){
                wait();
            }
            array[tail] = value;
            tail++;
            if (tail == array.length) {
                tail = 0;
            }
            size++;
            notify();
        }
    }
    public int take() throws InterruptedException {
        int ret=-1;
        synchronized (this) {
            if(size == 0){
                wait();
            }
            ret = array[head];
            head++;
            if (head == array.length) {
                head = 0;
            }
            size--;
            notify();
        }
        return ret;
    }

    public static void main(String[] args) {
        BlockQueue blockQueue = new BlockQueue();
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i<1000 ; i++){
                    try {
                        blockQueue.put(i);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread customer = new Thread(new Runnable() {
            @Override
            public void run() {
                int index=0;
                while(true){
                    try {
                        int take = blockQueue.take();
                        index++;
                        System.out.println("第"+index +"次获取值:"+take);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        producer.start();
        customer.start();
    }
}
