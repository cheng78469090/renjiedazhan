package com.atguigu.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: 宋金城
 * @Date: 2019/12/16 09:55
 * @Description:
 */
public class JUCLockTest {
    public static void main(String[] args) {
        jingzhuntongzhi jing=new jingzhuntongzhi();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    jing.print5();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A线程").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    jing.print10();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B线程").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    jing.print15();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C线程").start();

    }
}
class jingzhuntongzhi{
    private int flage=1;
    private Lock lock=new ReentrantLock();
    private Condition A=lock.newCondition();
    private Condition B=lock.newCondition();
    private Condition C=lock.newCondition();
    public void print5() throws InterruptedException {
        lock.lock();
        try {
            while (flage!=1){
                A.await();
            }
            for (int i = 1; i <=5; i++) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
            flage=2;
            B.signal();
        }finally {
            lock.unlock();
        }

    }
    public void print10() throws InterruptedException {
        lock.lock();
        try {
            while (flage!=2){
                B.await();
            }
            for (int i = 1; i <=10; i++) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
            flage=3;
            C.signal();
        }finally {
            lock.unlock();
        }

    }
    public void print15() throws InterruptedException {
        lock.lock();
        try {
            while (flage!=3){
                C.await();
            }
            for (int i = 1; i <=15; i++) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
            flage=1;
            A.signal();
        }finally {
            lock.unlock();
        }

    }




}
