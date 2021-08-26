package com.example.springbootlogliz01.setMapList;

import jdk.management.resource.internal.inst.ThreadRMHooks;

import java.util.*;

public class TestSetMapList {

    /***
     * hashSet底层就是hashMap
     * @param arg0
     */
    public static void main(String[] arg0){

    }

    public static void testSet(){
        Set set = new HashSet<>();
        for(int i=0;i<30;i++){
            new Thread(()->{
                set.add(Thread.currentThread().getName());
                System.out.println(set);
            },String.valueOf(i)).start();
        }
        while(Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(set.toString());
    }

    /**
     * 故障现象
     *      java.util.ConcurrentModificationException，多线程下并发异常
     * 导致原因
     *
     * 解决方案
     *
     * 优化建议（同样的错误不犯第2次）
     */
    public static void testList(){
        //java.util.ConcurrentModificationException，多线程下并发异常
//        List list = new Vector();//第一种
        //多线程下需要注意
        List list = Collections.synchronizedList(new ArrayList<>());




    }

}
