package com.code.Thread;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {
    private AtomicInteger a;
    public Atomic(AtomicInteger a){
        this.a=a;
    }
    public void add(){
        a.getAndIncrement();
    }
    public AtomicInteger getA(){
        return a;
    }
}
