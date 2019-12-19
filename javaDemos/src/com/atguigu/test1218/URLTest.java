package com.atguigu.test1218;

/**
 * @Auther: 宋金城
 * @Date: 2019/12/18 11:21
 * @Description:
 */
public class URLTest {
    public static void add(){
        System.out.println();
        add();
    }

    public static void main(String[] args) {
        //add();
        URLTest urlTest=new URLTest();
        System.out.println(urlTest.getClass());
        System.out.println("sssss"+urlTest.getClass().getClassLoader());
        System.out.println(urlTest.getClass().getClassLoader().getParent());
        System.out.println(urlTest.getClass().getClassLoader().getParent().getParent());
        System.out.println(Object.class.getClassLoader());


    }
}
