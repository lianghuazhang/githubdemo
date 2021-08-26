package com.example.springbootlogliz01.singleton;

public class SingleTonTest2 {

    //多线程情况下，需要加volatile禁止指令重排
    private static volatile SingleTonTest instance = null;

    private SingleTonTest2(){
        System.out.println("SingleTonTest 初始化了！");
    }



}
