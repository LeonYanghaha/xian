package com.xian.demo.study;


public class Thread_1 {

    private int i = 10;
    private Object object = new Object();

    public static void main(String[]args){
        Thread_1 thread_1 = new Thread_1();
        Stu thread1 = thread_1.new Stu();
        Stu thread2 = thread_1.new Stu();
        thread1.start();
        thread2.start();

    }

    class Stu extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                i++;
                System.out.println("i:"+i);
                try {
                    System.out.println("线程"+Thread.currentThread().getName()+"进入睡眠状态");
                    Thread.currentThread().sleep(10000);
                } catch (InterruptedException e) {
                    // TODO: handle exception
                }
                System.out.println("线程"+Thread.currentThread().getName()+"睡眠结束");
                i++;
                System.out.println("i:"+i);
            }
        }
    }
}



