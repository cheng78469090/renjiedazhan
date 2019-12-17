package com.atguigu.test;

import java.util.concurrent.*;

/**
 * @Auther: 宋金城
 * @Date: 2019/12/16 20:48
 * @Description:
 * 第一种是继承Thread类，
 * 第二种是实现runable接口
 * 第四种是从线程池中获取
 * 获得线程的第三种方式
 * 实现Callable接口
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //启动方式
        FutureTask futureTask=new FutureTask(new MyCallable());//实现的callable接口需要穿上futureTask的马甲才可以放入到Thread中运行
        new Thread(futureTask).start();
        System.out.println(futureTask.get());



    }
}
/**
 * 功能描述:
 * @date: 2019/12/16 20:52
 *
 *Callable<T>此类并没有继承runable接口，所以不可以直接放入到Thread中，需要另一个FutureTask实现了 Runnable接口的类，
 * 为什么FutureTask可以，因为
 * FutureTask实现了RunnableFuture接口
 * 而RunnableFuture继承了runable与Future
 */
class MyCallable implements Callable<String>{
    /**
     * 功能描述:
     * @date: 2019/12/17 9:07
     *有无返回值，是否抛异常，指定泛型，
     * 适配多态思想，多使用接口
     *
     */
    @Override
    public String call() throws Exception {
        System.out.println("我是第三种获取线程的方式");
        return "哈哈哈哈哈哈哈哈哈哈或或";
    }
}
