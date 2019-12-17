package com.atguigu.test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 宋金城
 * @Date: 2019/12/16 19:42
 * @Description:
 * SemaphoreDemo是一种信号计数量，用于管理资源，是synchronized的加强版,
 * 控制线程数的并发数量，多线程枪多资源
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(3);//模拟资源量库，这里定义多少，就只能有多少线程运行
        for (int i = 1; i < 6; i++) {
            final int tem=i;
            new Thread(()->{
                boolean flage=false;
                try {
                    semaphore.acquire();//信号计数量减一,相当于资源库量减一，因为已经有一个线程进入，
                    flage=true;
                    System.out.println("汽车"+(char)(tem+96)+"抢到了汽车");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("汽车"+(char)(tem+96)+"开走了汽车");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //如果线程执行完毕记得释放资源，
                    if (flage){
                        //释放信号，释放一个线程
                           semaphore.release();
                    }
                }
            },"汽车"+(char)(i+96)).start();
        }
    }
}
