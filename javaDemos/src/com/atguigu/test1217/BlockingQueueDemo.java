package com.atguigu.test1217;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 宋金城
 * @Date: 2019/12/17 18:08
 * @Description:抛出异常当阻塞队列满时，再往队列里add插入元素会抛IllegalStateException:Queue full当阻塞队列空时，
 * 再往队列里remove移除元素会抛NoSuchElementException特殊值插入方法，
 * 成功ture失败false移除方法，成功返回出队列的元素，
 * 队列里没有就返回null一直阻塞当阻塞队列满时，
 * 生产者线程继续往队列里put元素，
 * 队列会一直阻塞生产者线程直到put数据or响应中断退出当阻塞队列空时，
 * 消费者线程试图从队列里take元素，队列会一直阻塞消费者线程直到队列可用超时退出当阻塞队列满时，
 * 队列会阻塞生产者线程一定时间，超过限时后生产者线程会退出
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 功能描述:
         * @date: 2019/12/17 19:13
         *在使用队列接口的时候使用，阻塞队列接口
         *
         */
        BlockingQueue<Integer> queue=new ArrayBlockingQueue<Integer>(2);
        queue.offer(1,1L, TimeUnit.SECONDS);
        queue.poll(1L,TimeUnit.SECONDS);
        queue.offer(2,1L, TimeUnit.SECONDS);
        queue.poll(3L,TimeUnit.SECONDS);
        System.out.println(queue.element());


        //  BlockingQueue<Integer> queue1=new LinkedBlockingQueue<>(3);
        /*queue.put(1);
        queue.put(12);
        System.out.println("进入阻塞");
        queue.put(3);
        queue.take();
        System.out.println(queue.element());*/




      /*  System.out.println(queue.element());
        queue.remove();
        System.out.println(queue.element());
        queue.remove();
        System.out.println(queue.element());
*/

    }
}
