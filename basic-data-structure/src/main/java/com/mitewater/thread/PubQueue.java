package com.mitewater.thread;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PubQueue {

    public static Queue<String> pubQueue = new ArrayDeque<>();

    // 锁在公平的情况下尽量按执行代码先后顺序获取锁
    // 锁在不公平的情况下不会按执行代码的先后顺序获取锁
    // 这里的场景需要一个公平锁
    public static Lock lock = new ReentrantLock(true);

    public static Condition isFinish = lock.newCondition();



}
