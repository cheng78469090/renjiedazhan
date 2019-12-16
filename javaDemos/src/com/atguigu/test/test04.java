package com.atguigu.test;

/**
 * @Auther: 宋金城
 * @Date: 2019/12/13 18:41
 * @Description:
 */
public class test04 {
    public static void main(String[] args) {
        //Thread.currentThread().setPriority(8);
        System.out.println("主线程的优先级"+Thread.currentThread().getPriority());
        new MyThread01().start();
        new Thread(()->{for (int i = 0; i < 30; i++) System.out.println("这是接口线程"+i);
     //   Thread.currentThread().setPriority(6);
            Thread.yield();
            System.out.println("接口线程的优先级别"+Thread.currentThread().getPriority());
        }).start();
        Thread thread=new Thread();

        for (int i = 0; i < 30; i++) {
            System.out.println("这是主线程"+i);
        }

    }
}
class MyThread01 extends Thread{
    @Override
    public void run() {
      //  Thread.currentThread().setPriority(3);
       /* try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        System.out.println("普通线程的优先级："+Thread.currentThread().getPriority());
        for (int i = 0; i < 30; i++) {

            System.out.println("这是普通线程"+i);
        }
    }
}
