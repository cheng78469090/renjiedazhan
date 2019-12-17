package com.atguigu.test1217;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: 宋金城
 * @Date: 2019/12/17 10:57
 * @Description:
 */
public class TrainTest {
    public static void main(String[] args) {
        /**
         * 功能描述:
         * @date: 2019/12/17 11:31
         *
         *参数是重点，7个参数
         * 练习拒绝策略，四种都要练习
         *模拟集合线程不安全情况
         */
        //ExecutorService servicePool= Executors.newFixedThreadPool(3);//创建一个固定线程数的线程池
        // ExecutorService servicePool= Executors.newSingleThreadExecutor();//创建一个只有一个线程的线程池
      //  ExecutorService servicePool= Executors.newCachedThreadPool();//创建一个有多个线程的线程池，这个线程具体多少系统分配
        Train01 t=new Train01();
      //  Executors.
        ExecutorService threadPool = new ThreadPoolExecutor(
                //当线程池被创建的时候，便进入到运行状态，
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
                );
        try {
            for (int i = 1; i<=30; i++) {
                //开始接受线程任务
                threadPool.execute(()->{ t.maiPiao();
                });
            }
        } catch(Exception e) {
            e.printStackTrace();
        }finally {
            threadPool.shutdown();//调用该方法后，线程池进入关闭状态，不会再接受新的任务，但是还在执行已经提交的任务，
            //当任务执行完毕后，边进入终止状态
        }

    }
}
class Train01{

    private int piaoshu=30;
    private Lock lock=new ReentrantLock();

    public void maiPiao(){
        lock.lock();
        try {
            if (piaoshu>0){
                System.out.println(Thread.currentThread().getName()+"卖出票"+"还剩下:"+(--piaoshu));
            }
        } catch(Exception e) {

        }finally {
            lock.unlock();
        }

    }


}
