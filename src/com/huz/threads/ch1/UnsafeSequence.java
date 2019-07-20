package com.huz.threads.ch1;

import java.util.HashSet;
import java.util.Set;

public class UnsafeSequence {
    private int value;
    private static final int iterations=99999;
    private final static int threadCount=3;
    private static Set set1 = new HashSet<>();
    public int getNext(){
        return value++;
    }

    public static void main(String[] args) {
        UnsafeSequence unsafeSequenceObj = new UnsafeSequence();
        for(int i=0;i<threadCount;i++){
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    for (int i=0;i<iterations;i++){
                        int j=unsafeSequenceObj.getNext();
                        //System.out.println(Thread.currentThread().getName()+">>"+j);
                        if(!set1.add(j)){
                            System.out.println(j+" is duplicate count");
                        }
                    }
                }
            };
            Thread t = new Thread(r);
            t.start();
        }
    }
}
