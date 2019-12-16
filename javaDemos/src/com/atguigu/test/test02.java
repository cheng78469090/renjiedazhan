package com.atguigu.test;

public class test02 {
    public static void main(String[] args) {
        titick titick=new titick();

    /**
     * 功能描述: 
     * @date: 2019/12/13 17:41
     *
     *
     */
     try {

     } catch(Exception e) {

     } finally {

     }
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 35; i++) {

                    titick.sale();
                }
            }
        }, "窗口1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 35; i++) {

                    titick.sale();
                }
            }
        }, "窗口2").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 35; i++) {

                    titick.sale();
                }
            }
        }, "窗口3").start();

    }



}
class titick{
    private int piaoshu=30;
    //并发性下降，表锁，整个方法都会被枷锁，
    public synchronized void sale(){
        if (piaoshu>=0){
            System.out.println("第"+Thread.currentThread().getName()+"卖出了,还剩下"+(piaoshu--)+"张");
        }
    }

}
