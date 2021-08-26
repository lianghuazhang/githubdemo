package com.example.springbootlogliz01.atomic;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS是什么？ ---》compareAndSet
 *
 * 1、CAS是什么？-->compareAndSet
 *      比较并交换
 *
 */
public class CasDemo {

    public static volatile int testNum=0;

    /**
     * 多线程下用atomicInteger代替
     */
    public static volatile AtomicInteger atomicInteger1 = new AtomicInteger(0);

    public static void main(String[] arg0){
        for(int i=0;i<20;i++){
            new Thread(()->{
                for(int j=0;j<20000;j++){
                    CasDemo.testNum++;
                    atomicInteger1.getAndIncrement();
                }
            }).start();
        }

        while (Thread.activeCount()>2){
            Thread.yield();//线程让步
        }
        System.out.println("testNum="+CasDemo.testNum);
        System.out.println("testNum="+atomicInteger1.get());


        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5,2019)+" current data:"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(2019,2044)+" current data:"+atomicInteger.get());

        CasDemo.test2();
    }

    public static void test2(){

        new Thread(()->{
            try {
                testNum++;
                System.out.println(Thread.currentThread().getName()+" 5秒前执行:"+testNum);
                Thread.sleep(5000l);
                System.out.println(Thread.currentThread().getName()+" 5秒后执行:"+testNum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+" 5秒前执行:"+testNum);
                Thread.sleep(3000l);
                testNum++;
                System.out.println(Thread.currentThread().getName()+" 5秒后执行:"+testNum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        /**
         * 2个线程至少，一个是垃圾回收线程，一个是主线程
         */
        while (Thread.activeCount()>2){
//            System.out.println("当前线程数量为："+Thread.activeCount());
            Thread.yield();//线程让步，让步给其他线程
        }
        System.out.println("线程结束");
    }
}
