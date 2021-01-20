package com.code.Thread;

public class Synch {
    private volatile Integer e;
    public Synch(Integer e){
        this.e=e;
    }
    public void add(){
        synchronized (this){
            e++;
        }
    }
    public int getE(){
        return e;
    }
}
