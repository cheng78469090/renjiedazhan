package com.atguigu;

/**
 * @Auther: 宋金城
 * @Date: 2019/12/20 10:03
 * @Description:
 */
public class test1219 {
    public static void main(String[] args) {
        //返回的是java虚拟机使用的内存。
       /* long totalMemory = Runtime.getRuntime().totalMemory();
        //返回的是java虚拟机最大可以使用的内存
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("TOTAL_MEMORY(-Xms) = " + totalMemory + "（字节）、" + (totalMemory / (double)1024 / 1024) + "MB");
        System.out.println("MAX_MEMORY(-Xmx) = " + maxMemory + "（字节）、" + (maxMemory / (double)1024 / 1024) + "MB");
        String str = "www.atguigu.com" ;
        while(true)
        {
            byte[] bytes = new byte[1024 * 8];

        }*/
       Thread thread=new Thread();
       thread.start();
       thread.start();


    }
}
