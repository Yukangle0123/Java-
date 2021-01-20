package com.code.Thread;

import java.util.concurrent.atomic.AtomicInteger;

public class Add extends Thread {
    AtomicInteger e=new AtomicInteger(0);
    Atomic atomic=new Atomic(e);

    @Override
    public void run() {
        atomic.add();
        System.out.println(atomic.getA().get());
    }
}
