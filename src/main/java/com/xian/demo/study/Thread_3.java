package com.xian.demo.study;
/**
 * join()方法是主线程等待其他线程执行
 * 如果不传参数，就等到其他线程执行完毕之后才执行。
 * 如果传入了参数，就等到指定的时间之后采取执行。
 *
 * join（）方法的底层是调用的wait（） 方法。
 * 由于wait（） 方法会释放锁，所以join（）方法也会释放锁
 *
 */
public class Thread_3 {
    public static void main (String[] args) {
        System.out.println(Thread.currentThread().getName()  + "start");
        Thread_3 thread_3 = new Thread_3();
        Stu stu = thread_3.new Stu();
        stu.start();
        try {
            System.out.println("main join before");
            stu.join(23);
            System.out.println("join after");
        }catch (InterruptedException e){
            System.out.println(e);
        }
        System.out.println("bye");
    }

    class Stu extends Thread {
        @Override
        public void run (){
            System.out.println(Thread.currentThread().getName() + "coming .....start");
            try {
                Thread.sleep(6*1000);
            }catch (InterruptedException e){
                System.out.println("Error" + e);
            }
            System.out.println(Thread.currentThread().getName() + "exit....");
        }
    }
}
