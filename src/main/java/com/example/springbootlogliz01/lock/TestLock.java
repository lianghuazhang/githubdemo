package com.example.springbootlogliz01.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁
 * 公平锁和非公平锁
 * 公平：如果已经上锁则按照队列来等
 * 非公平：上来则抢锁，抢不到则进入队列等待。
 *
 * ReentrantLock 默认是非公平锁
 * synchronized 也是一种非公平锁
 */
public class TestLock {
    ReentrantLock rtl = new ReentrantLock(false);
}
