package com.hjy;

public class ThreadLocalTest {
     private static ThreadLocal<String> local= new ThreadLocal<String>();

    public static void main(String[] args) {
        local.set("hjy");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"--"+local.get());
            }
        }).start();

        System.out.println(Thread.currentThread().getName()+"--"+local.get());
    }
}
