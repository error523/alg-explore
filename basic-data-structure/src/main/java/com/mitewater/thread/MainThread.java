package com.mitewater.thread;

import cn.hutool.core.thread.ThreadUtil;


public class MainThread {

    public static void main(String[] args) {


        ThreadUtil.execAsync(()->{
            for(int i=0;i<10;i++){
                try {
                    PubQueue.pubQueue.offer("" + i);
                    Thread.sleep(200);
                    System.out.println("add queue : " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        ThreadUtil.execAsync(()->{
            int fair = 0;
            while(true) {
                System.out.println("锁的公平 ： " + fair);
                System.out.println("生产进入锁");
                PubQueue.lock.lock();
                try{
                    System.out.println("判断队列是否为空");
                    while(!PubQueue.pubQueue.isEmpty()){
                        System.out.println("队列不为空");
                        System.out.println("current queue size is : "+PubQueue.pubQueue.size());
                        System.out.println("等待");
                        PubQueue.isFinish.await();
                        System.out.println("结束等待");
                        fair ++;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    System.out.println("生产进入锁");
                    PubQueue.lock.unlock();
                }
                System.out.println("循环");
            }

        });

        ThreadUtil.execAsync(()->{
           for(int i=0;i<10;i++){
               System.out.println("消费进入锁");
               PubQueue.lock.lock();
               try{
                   String c = PubQueue.pubQueue.poll();
                   System.out.println("get c is " + c);
                   System.out.println("get size is : " + PubQueue.pubQueue.size());
                   Thread.sleep(1000);
                   PubQueue.isFinish.signalAll();
               }catch (Exception e){
                   e.printStackTrace();
               }finally {
                   System.out.println("消费释放锁");
                   PubQueue.lock.unlock();
               }
           }
        });

        while (true){}

    }

}
