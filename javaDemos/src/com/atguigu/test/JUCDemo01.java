package com.atguigu.test;

/**
 * @Auther: 宋金城
 * @Date: 2019/12/14 11:11
 * @Description:虚假唤醒
 * 遇到故障
 * 故障原因
 * 解决方案
 * 如何优化
 * /防止多线程通知时，虚假唤醒 bug
 * 用while，不能使用if，while充当循环加判断的功能，
 * 因为当用到if的时候，进入等待后，当再次被唤醒的时候直接进行下一步，没有进行再次判断
 * 而如果使用的是while，进入等待后，当再次被唤醒的时候再次进行判断，然后再进入到下一步。
 */
public class JUCDemo01 {
    public static void main(String[] args) {
        kongtiao kongtiao=new kongtiao();

        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    kongtiao.zeng();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    kongtiao.jian();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    kongtiao.zeng();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();
        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    kongtiao.jian();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"D").start();

    }
}
class kongtiao{

    private int wendu  = 0;

    public synchronized void zeng() throws InterruptedException {
        //判断
        while (wendu!=0){
            this.wait();
        }
        //干活
        ++wendu;
        //通知
        System.out.println("线程："+Thread.currentThread().getName()+"温度："+wendu);
        this.notifyAll();
    }


    /**
     *  //如果给当前的某个方法加锁，相当于给当前实例加锁，
     //使用synchronized如果是给当前方法加锁，就是给当前实例加锁，需要获取当前实例的锁
     所以即使是两个线程，操纵的也是一把锁
     //synchronized如果是静态锁的话就相当与给这个类加锁，进入该同步锁之前需要获得该类的锁，
     *
     */
    public synchronized void jian() throws InterruptedException {
        //判断

       while (wendu==0){
            this.wait();
        }
        //干活
        --wendu;
        //通知
        System.out.println("线程："+Thread.currentThread().getName()+"温度："+wendu);
        this.notifyAll();

    }


}
