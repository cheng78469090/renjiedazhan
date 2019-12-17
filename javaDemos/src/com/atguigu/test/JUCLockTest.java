package com.atguigu.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: 宋金城
 * @Date: 2019/12/16 09:55
 * @Description:
 * redis 五大数据类型，持久化，
 * 主从复制原理，
 * mysql调优，执行引擎
 * 索引如何优化
 *
 * 上自习锁门，集齐龙珠
 */
public class JUCLockTest {
    /**
     * 功能描述:
     * @date: 2019/12/16 18:24
     *
     *线程操作资源类，
     * 精准控制要要解开那些锁，那些锁需要等待，类似于呼啦圈，按照顺序来执行
     */
    public static void main(String[] args) {
        jingzhuntongzhi jing=new jingzhuntongzhi();

        new Thread(()->{
            for (int i = 0; i < 3; i++) {
                try {
                    jing.print5();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A线程").start();
        new Thread(()->{
            for (int i = 0; i < 3; i++) {
                try {
                    jing.print10();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B线程").start();
        new Thread(()->{
            for (int i = 0; i < 3; i++) {
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
