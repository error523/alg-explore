package com.mitewater.tree;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class PC {

    int MAX = 1024;
    Queue queue = new ArrayBlockingQueue(MAX);

    class P implements Runnable{
        
        @Override
        public void run() {

        }
    }

}
