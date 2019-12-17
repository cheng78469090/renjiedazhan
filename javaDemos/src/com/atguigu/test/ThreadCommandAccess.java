package com.atguigu.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadCommandAccess {
    private Integer flag=1;
    Lock lock=new ReentrantLock();
    Condition c=lock.newCondition();
        public void print(){
        lock.lock();
        String name = Thread.currentThread().getName();
            int threadI = Integer.parseInt(name);
            try {
            while(flag!=threadI){
                c.await();
            }
            for(int i=1;i<16;++i){
                System.out.println(name+"打印："+i);
                if(threadI==1&&i==5){
                    break;
                }
                if(threadI==2&&i==10){
                    break;
                }
            }
            flag=threadI+1;
            c.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ThreadCommandAccess  t= new ThreadCommandAccess();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                t.print();
            }

        },"1").start();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                t.print();
            }
        },"2").start();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                t.print();
            }
        },"3").start();

    }
}