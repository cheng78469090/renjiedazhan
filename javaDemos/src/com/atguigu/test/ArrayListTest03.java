package com.atguigu.test;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Auther: 宋金城
 * @Date: 2019/12/14 16:15
 * @Description:
 * 线程操作资源类
 * 判断干活通知
 * while
 *
 */
public class ArrayListTest03 {
    public static void main(String[] args) {
        List<String> list= new CopyOnWriteArrayList<>();//适用于读多写少的场景
        for (int i = 0; i < 10; i++) {
            /**
             * 功能描述:
             * @date: 2019/12/14 16:52
             *简单来说，就是平时查询的时候，都不需要加锁，随便访问，只有在写入/删除的时候，才会从原来的数据复制一个副本出来
             * ，然后修改这个副本，最后把原数据替换成当前的副本。修改操作的同时，
             * 读操作不会被阻塞，而是继续读取旧的数据。这点要跟读写锁区分一下。
             *  集合的实际值与期望值不一致
             */
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,6));
                System.out.println(list);},String.valueOf((i))).start();

        }

    }
}
