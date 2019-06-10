package com.xian.demo.study;

public class Thread_2 extends Thread{

    private int i =10;
    private Object object = new Object();

    public static void main (String[] args) {

        Thread_2 thread_2 = new Thread_2();
        thread_2.start();

        Thread_2 thread_21 = new Thread_2();
        thread_21.start();


    }
    @Override
    public void run () {

        synchronized (object){
            System.out.println("coming-->" + i + Thread.currentThread().getName());
            i++;
            try {
                Thread.sleep(3 * 1000);
            }catch (InterruptedException e){
                System.out.println(e);

            }
            System.out.println("exit--->" + i + Thread.currentThread().getName());
        }
    }
}

//class Cat extends Thread {
//
//    public int i = 11;
//    public Object object = new Object();
//    public void run(){
//
//        synchronized (object){
//            System.out.println("coming-->" + i + Thread.currentThread().getName());
//            try {
//                Thread.sleep(3);
//                i++;
//            } catch (InterruptedException e){
//                System.out.println(e);
//            }
//            System.out.println("exit------->"+ i + Thread.currentThread().getName());
//        }
//    }
//}