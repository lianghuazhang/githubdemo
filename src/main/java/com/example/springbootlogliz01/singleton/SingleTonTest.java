package com.example.springbootlogliz01.singleton;

public class SingleTonTest {

    //多线程情况下，需要加volatile禁止指令重排，存在分配地址之后，还没完全初始化完成时候的其他线程引用导致取值为空的情况（极小概率）
    private static volatile SingleTonTest instance = null;

    private SingleTonTest(){
        System.out.println("SingleTonTest 初始化了！");
    }

    /**
     * 同步块
     * 双端检索机制
     * @return
     */
    public static SingleTonTest getInstance(){
        if(instance == null){
            synchronized (SingleTonTest.class){
                if(instance == null){
                    instance = new SingleTonTest();
                }
            }
        }
        return instance;
    }
}
