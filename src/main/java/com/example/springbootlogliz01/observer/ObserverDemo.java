package com.example.springbootlogliz01.observer;

import java.util.Observable;

public class ObserverDemo extends Observable {

    public static void main(String[] arg0){
        ObserverDemo demo = new ObserverDemo();

        demo.addObserver((o,args) -> System.out.println("发生改变"));
        demo.addObserver((o,args) -> System.out.println("手动被观察者 通知，准备改变"));
        demo.addObserver((o,args) -> System.out.println("准备改变"));
        demo.setChanged();//数据变化
        demo.notifyObservers();//通知
    }
}
