package com.atguigu.test;

/**
 * @Auther: 宋金城
 * @Date: 2019/12/15 13:19
 * @Description:
 */
public class LockTest01 {
    /**
     * 功能描述:
     * @date: 2019/12/15 13:20
     *线程  操作  资源类
     *
     */
    public static void main(String[] args) {
        dangao dangao = new dangao();
        //创建线程
        new Thread(()->{ for (int i = 0; i < 30; i++) dangao.chidangao(); },"张三").start();
        new Thread(()->{ for (int i = 0; i < 30; i++) dangao.chidangao(); },"李四").start();
        new Thread(()->{ for (int i = 0; i < 30; i++) dangao.chidangao(); },"王五").start();
        new Thread(()->{ for (int i = 0; i < 30; i++) dangao.chidangao(); },"zhu").start();
        new Thread(()->{ for (int i = 0; i < 30; i++) dangao.chidangao(); },"A").start();
        new Thread(()->{ for (int i = 0; i < 30; i++) dangao.chidangao(); },"B").start();
        new Thread(()->{ for (int i = 0; i < 30; i++) dangao.chidangao(); },"C").start();
        new Thread(()->{ for (int i = 0; i < 30; i++) dangao.chidangao(); },"D").start();
        new Thread(()->{ for (int i = 0; i < 30; i++) dangao.chidangao(); },"E").start();
        new Thread(()->{ for (int i = 0; i < 30; i++) dangao.chidangao(); },"G").start();
        new Thread(()->{ for (int i = 0; i < 30; i++) dangao.chidangao(); },"F").start();
        new Thread(()->{ for (int i = 0; i < 30; i++) dangao.chidangao(); },"W").start();

    }
}
class dangao{
    private int shuliang=30;

    public void chidangao(){

        if (shuliang>0){
            System.out.println(Thread.currentThread().getName()+"吃蛋糕,蛋糕还剩下："+(--shuliang)+"块");
        }

    }
}

