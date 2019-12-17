package com.atguigu.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Auther: 宋金城
 * @Date: 2019/12/16 18:59
 * @Description:
 * 模拟点到场景，等所有人员到齐了再进行学习
 *循环屏障
 * 一个线程没有完成，其他线程都必须是等待状态才能进行下一步操作，并且其他线程都处于等待状态，
 * 不能终止也不能进行下一步，强调互相等待，具有重复性
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(10,()->{
            System.out.println("全员到齐，去吃饭");
        });
        for (int i = 1; i <=9; i++) {
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"到，");
                    cyclicBarrier.await();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            },i+"").start();
        }
    }
}
