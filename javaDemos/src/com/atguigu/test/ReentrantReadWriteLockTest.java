package com.atguigu.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Auther: 宋金城
 * @Date: 2019/12/16 15:32
 * @Description:
 * 此类用于测试读写锁
 */
public class ReentrantReadWriteLockTest {
    public static void main(String[] args) {
        ReentrantReadTest reentrantReadTest=new ReentrantReadTest();
        for (int i = 1; i <=10; i++) {
            final String tmpl=i+"";
            new Thread(()->{
                reentrantReadTest.setMapVla(tmpl,tmpl);
            },tmpl).start();
        }
        for (int i = 1; i <=10; i++) {
            final String tmpl=i+"";
            new Thread(()->{
                reentrantReadTest.getMapVla(tmpl);
            },tmpl).start();
        }

    }


}

class ReentrantReadTest{
    private volatile Map<String,String> map=new HashMap<>();
    private Lock lock=new ReentrantLock();
    //读写锁
    private ReentrantReadWriteLock rwl=new ReentrantReadWriteLock();
    private Lock w=rwl.writeLock();
    private Lock r=rwl.readLock();
    /**
     * 功能描述:
     * @date: 2019/12/16 18:13
     *小总结：
     * 1.读与读可以共存，所以不涉及安全问题，
     * 2.读与写不可以共存，因为在写的时候数据会被修改，所以不应该再去读或者写
     * 3.写与写不能共存，
     *读写锁的的使用，
     * 保证了线程的安全性，又提高了并发量
     *
     */
    public void setMapVla(String str1,String str2){
        rwl.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始写入");
            map.put(str1,str2);
            System.out.println(Thread.currentThread().getName()+"写入完毕");
        } catch(Exception e) {

        }finally {
            rwl.writeLock().unlock();
        }

    }

    public void getMapVla(String str1){
        rwl.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"读到"+map.get(str1));
            System.out.println(Thread.currentThread().getName()+"读取完毕");

        } catch(Exception e) {

        }finally {
        rwl.readLock().unlock();
        }

    }












    //加lock锁版本
    /**
     * 功能描述:
     * @date: 2019/12/16 18:02
     *加上lock锁后保证了线程的安全性，但是牺牲了读并发量，效率得不到保证
     * 写保证了安全性，但是读并发量大大下降，
     * 我们追求的是保证线程的安全，又要保证尽可能的保证并发
     *
     */
   /* public void setMapVla(String str1,String str2){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始写入");
            map.put(str1,str2);
            System.out.println(Thread.currentThread().getName()+"写入完毕");

        } catch(Exception e) {

        }finally {
            lock.unlock();
        }

    }

    public void getMapVla(String str1){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始读到"+map.get(str1));
            System.out.println(Thread.currentThread().getName()+"读取完毕");
        } catch(Exception e) {

        }finally {
            lock.unlock();
        }

    }*/



    /**
     * 功能描述:
     * @date: 2019/12/16 18:11
     *    //不加锁的情况
    //不加锁导致线程不安全，导致读写不一致，错误写法
     *
     */

   /* public void setMapVla(String str1,String str2){

        System.out.println(Thread.currentThread().getName()+"开始写入");
        map.put(str1,str2);
        System.out.println(Thread.currentThread().getName()+"写入完毕");
    }

    public void getMapVla(String str1){
        System.out.println(Thread.currentThread().getName()+"读到"+map.get(str1));
        System.out.println(Thread.currentThread().getName()+"读取完毕");
    }*/

}
