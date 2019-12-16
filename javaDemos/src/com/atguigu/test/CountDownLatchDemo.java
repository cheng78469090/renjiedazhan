package com.atguigu.test;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: 宋金城
 * @Date: 2019/12/16 18:29
 * @Description:
 * 此类用于练习CountDownLatch
 * CountDownLatch是用于当多个线程时，其他线程等待一个线程完成后再执行的其操作
 */
public class CountDownLatchDemo {
    private static final int NUMBER=10;
    //模拟主线程等待其他线程执行完毕后再执行其操作
    public static void main(String[] args) throws InterruptedException {
        //创建一个同步帮助工具
        CountDownLatch cdl=new CountDownLatch(NUMBER);
        for (int i = 1; i <=NUMBER; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"号同学"+"在上自习");
                cdl.countDown();
            },i+"").start();

        }
        cdl.await();//当所有的countDown执行完成，或者计数器为零的时候，将唤醒等待的方法
        System.out.println("班长锁门走人");

        /**
         * 功能描述:需求是等所有同学上完自习班长锁门走人
         * @date: 2019/12/16 18:39
         *
         *不使用这个CountDownLatch同步帮助的结果，会造成还有同学在上自习，班长就锁门走人
         * 该最后一个执行的线程还没有等待到其他线程执行完成就提前执行了
         */
       /* for (int i = 1; i <=10; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"号同学"+"在上自习");
            },i+"").start();
        }
        System.out.println("班长锁门走人");*/
    }
}
