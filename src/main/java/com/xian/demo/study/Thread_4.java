package com.xian.demo.study;

public class Thread_4 {

    public static void main (String[] args) {
        System.out.println(Integer.MAX_VALUE);
        Dog dog = new Dog();
        dog.start();
        System.out.println("main start");
        try {
            Thread.sleep(2*1000);
        }catch (InterruptedException e){
            System.out.println(e);
        }
        System.out.println("main alive");
        dog.interrupt();
        System.out.println("interrupt ---");


    }
}
/**
 *1073741824
 * interrupt () 方法只能用来中断处于阻塞状态的线程。
 * 如果要用interrupt()方法来中断正在执行的线程，
 * 需要配合isInterrupted()来中断。
 */

class Dog extends Thread {
    Integer i = 0;
    @Override
    public  void run () {
        while (!isInterrupted() && i<= Integer.MAX_VALUE){
            i++;
        }
        System.out.println(i + "...>");


//        System.out.println(Thread.currentThread().getName() + "start");
//        try {
//            Thread.sleep(5*1000);
//        }catch (InterruptedException e){
//            System.out.println(e + "--->");
//        }
//        System.out.println(Thread.currentThread().getName()  + "end");
    }
}
