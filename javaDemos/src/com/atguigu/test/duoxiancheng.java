package com.atguigu.test;

public class duoxiancheng {
        public static void main(String arg[]){


            MyThread huochepiao1=new MyThread("窗口1");
            MyThread huochepiao2=new MyThread("窗口2");
            MyThread huochepiao3=new MyThread("窗口3");
            huochepiao1.start();
            huochepiao2.start();
            huochepiao3.start();
//        Thread thread01=new Thread(huochepiao,"窗口一");
//        Thread thread02=new Thread(huochepiao,"窗口二");
//        Thread thread03=new Thread(huochepiao,"窗口三");
//        thread01.start();
//        thread02.start();
//        thread03.start();
        }

}

class MyThread extends  Thread{
    public MyThread() {
    }

    public MyThread(String name) {
        super(name);
    }

    static int piaoshu=30;
    @Override
    public void run() {
       while (true){

           if (piaoshu>=0){

               String name=Thread.currentThread().getName();
               System.out.println("第"+name+"正在卖票，票数还剩下"+ piaoshu--);

           }
           //获取线程名称

       }
    }
}
