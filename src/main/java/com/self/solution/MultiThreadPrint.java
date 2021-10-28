package com.self.solution;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 要求三个线程，Thread a, b, c
 * Thread a 只能输出1
 * Thread b 只能输出2
 * Thread c 只能输出3
 * <p>
 * 要求：顺序输出 1 2 3 1 2 3 ..., 10次 1 2 3
 *
 * @author yuanxin
 * @date 2021-10-28
 */
public class MultiThreadPrint {
    private Lock lock = new ReentrantLock();
    private int state = 0;
    private int count = 0;

    public void solution(int count) {
        this.count = count;
        ThreadFactory threadFactory = new ThreadFactory() {
            private AtomicInteger count = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new OrderThread(count.incrementAndGet());
            }
        };
        ExecutorService executorService = new ThreadPoolExecutor(3, 3,
                0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(), threadFactory);
        executorService.execute(() -> print());
        executorService.execute(() -> print());
        executorService.execute(() -> print());
    }

    class OrderThread extends Thread {
        private int index;
        private String outStr;

        public OrderThread(int index) {
            this.index = index;
            this.outStr = String.valueOf(index + 1);
        }
    }
    private void print() {
        for (int i = 0; i < count; ) {
            lock.lock();
            try {
                if (state % 3 == index) {
                    System.out.print(outStr);
                    state++;
                    i++;
                }
            } finally {
                lock.unlock();
            }
        }
    }

}
