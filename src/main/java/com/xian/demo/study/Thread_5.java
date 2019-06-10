package com.xian.demo.study;

public class Thread_5 {
    public static void main (String[] args) {

        Stu stu = new Stu();
        Thread thread = new Thread(stu);



        Thread thread1 = new Thread(stu);
        thread1.start();
        thread.start();

//        while (1==1){
//            System.out.println(Thread.currentThread().getName());
//            try {
//                Thread.sleep(1*1000);
//            }catch (InterruptedException e){
//                System.out.println(e);
//            }
//        }

    }
}


class Stu implements Runnable {

    Integer i = 0;
    private Object object = new Object();

    public void  run () {
        synchronized (object){
            while (true){
                System.out.println(Thread.currentThread().getName()  + "--->" + i);
                i++;
                try {
                    Thread.sleep(1*1000);
                }catch (InterruptedException e){

                }
            }
        }
    }
}