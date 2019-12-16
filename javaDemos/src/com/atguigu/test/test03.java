package com.atguigu.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: 宋金城
 * @Date: 2019/12/13 17:49
 * @Description:
 */
public class test03 {
    public static void main(String[] args) throws InterruptedException {

        trains train=new trains();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 35; i++) {
//                    train.sale();
//                }
//
//            }
//        }, "窗口一").start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 35; i++) {
//                    train.sale();
//                }
//            }
//        }, "窗口二").start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 35; i++) {
//                    train.sale();
//                }
//            }
//        }, "窗口三").start();
        Object o = new Object();
        Thread thread = new Thread();
        /**
         * 功能描述:
         * @date: 2019/12/14 8:56
         *线程 操作 资源类(前提：高内聚低耦合)，高内聚的封装思想
         *juc
         * 使用lambda表达式
         * 条件：
         * 函数式接口@FunctionalInterface
         * 1.这个接口有且仅有一个方法，
         * 2.拷贝小括号，写死右箭头，落地大括号
         *3.参数多个可以省略参数类型
         * 4.接口里可以有多个默认方法，default
         * 5.接口里允许有静态方法
         */

        new Thread(()->{for (int i = 0; i < 35; i++) train.sale();},"窗口A").start();
        new Thread(()->{for (int i = 0; i < 35; i++) train.sale();},"窗口B").start();
        new Thread(()->{for (int i = 0; i < 35; i++) train.sale();},"窗口C").start();
        Object object=new Object();
        //因为锁对象可以是任意类型的，所以wait等待方法和notify唤醒方法定义在object类中，
        //在调用wait方法和notify方法的时候必须是同一个对象调用的，而这个对象是任意类型的
        object.wait();


    }

}

class trains{
    private int piaoshu=30;

    public void sale(){
        //需要上锁,这种上锁方式增加了高并发的细粒度，避免给方法加锁导致整个方法都上锁，限制了高并发
        Lock lock=new ReentrantLock();//Lock属于可中断锁
        //ReentrantLock()默认属于属于非公平锁，但是可以设置为公平锁，属于可重入锁
        //synchronized属于不可中断锁，属于非公平锁，无法保证所有线程获得锁是按照顺序来的，这就导致了有一部分线程永远无法获得锁
        lock.lock();
        try {
            if (piaoshu>=0) {
                System.out.println("第" + Thread.currentThread().getName() + "窗口,还剩" + (piaoshu--) + "张票");
            }
        }finally {
            lock.unlock();
        }

    }
}