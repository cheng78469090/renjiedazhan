package com.atguigu.test1217;

/**
 * @Auther: 宋金城
 * @Date: 2019/12/17 09:18
 * @Description:
 */
public class test01 {
    /**
     * 功能描述:
     * @date: 2019/12/17 9:51
     *线程池:
     * 1、含有固定线程数的线程池，或者一池固定数线程
     * 因为有资源耗尽风险所以不适用jdk的，
     * OOM堆内存溢出
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     * 由于对synchronized理解的不到位，由于很多时候，我们多线程都是操作一个synchronized的方法，
     * 当2个线程调用2个不同synchronized的方法的时候（同一实例），认为是没有关系的，
     * 这种想法是存在误区的。直接作用于实例方法:相当于对当前实例加锁，进入同步代码前要获得当前实例的锁。

        如果一个调用synchronized方法。另外一个调用普通方法是没有关系的，2个是不存在等待关系的。

     *
     */

    public static void main(String[] args) {

        try {
            for (int i = 0; i < 30; i++) {
                System.out.println("你好我是try");
            }

        } catch(Exception e) {

        }finally {
            System.out.println("我是finally");
        }
    }

}

