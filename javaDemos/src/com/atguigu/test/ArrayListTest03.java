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
             *
             *  集合的实际值与期望值不一致
             */
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,6));
                System.out.println(list);},String.valueOf((i))).start();

        }

    }
}
