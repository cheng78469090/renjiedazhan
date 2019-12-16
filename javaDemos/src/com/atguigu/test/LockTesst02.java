package com.atguigu.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: 宋金城
 * @Date: 2019/12/15 13:33
 * @Description:lock的唤醒与等待使用
 * 使用if会造成虚假唤醒的bug，这里需要使用while，
 * condition需要配合Lock锁对象使用，它是一个接口，需要Lock对象返回condition的实现类
 * java的任何的一个对象都有一组监视器，所以在object中有wait与notify的方法，配合synchronize一起使用
 *
 */
public class LockTesst02 {
    public static void main(String[] args) {

        air_conditioner air=new air_conditioner();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    air.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    air.reduce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    air.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    air.reduce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();

    }



}

class air_conditioner{
    private int temperature=0;
    Lock lock=new ReentrantLock();

    /**
     * 功能描述:
     * condition可以与锁对象相结合
     * @date: 2019/12/15 14:06
     * 虚假唤醒理解：
     * 例如有四个线程，其中两个线程是做蛋糕，两个线程吃蛋糕，逻辑是，当蛋糕没有的时候做蛋糕，有蛋糕时吃蛋糕，
     * 保持做一个吃一个。当做蛋糕的线程进入if条件判断是否有蛋糕，当发现有蛋糕的时候进入等待并且释放锁，等待吃蛋糕线程吃蛋糕
     * 但是这个时候另一个做蛋糕的线程抢到了线程，也进行判断发现也有蛋糕，于是在这里进入等待，等待吃蛋糕线程，当吃蛋糕进行
     * 吃蛋糕后，唤醒了正在等待的做蛋糕线程，于是这两个做蛋糕的线程全部被唤醒，没有再进行判断是否有蛋糕，直接进行做蛋糕，
     * 这样就直接做了两个蛋糕，造成了虚假唤醒的bug，这个bug的解决方法就是不适用if判断而使用while进行判断
     *
     *
     *
     */
    Condition condition=lock.newCondition();

    public void increase() throws InterruptedException {
        //判断.
        lock.lock();
        try {
            if (temperature!=0){//会导致虚假唤醒
                //不等于零代表该减温度了，所以进入等待状态
                condition.await();
            }
            //干活
            ++temperature;
            System.out.println(Thread.currentThread().getName()+"调了温度,当前温度"+temperature);
            //唤醒，唤醒使用signalALL

            condition.signalAll();

        } finally {
            lock.unlock();
        }

    }
    public void reduce() throws InterruptedException {
        lock.lock();
        try {
            if (temperature==0){
                condition.await();
            }
            --temperature;
            System.out.println(Thread.currentThread().getName()+"调了温度,当前温度"+temperature);
            condition.signalAll();

        } catch(Exception e) {

        }finally {
            lock.unlock();
        }


    }
}