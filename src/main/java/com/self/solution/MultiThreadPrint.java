package com.self.solution;

import java.util.concurrent.CountDownLatch;
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
    /**
     * 循环次数
     */
    private int times;
    /**
     * 共享状态
     */
    private int state = 0;

    public MultiThreadPrint(int times) {
        this.times = times;
    }

    public void print() {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Thread(() -> doPrint(0, "1", countDownLatch)).start();
        new Thread(() -> doPrint(1, "2", countDownLatch)).start();
        new Thread(() -> doPrint(2, "3", countDownLatch)).start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doPrint(int index, String printString, CountDownLatch countDownLatch) {
        for (int i = 0; i < times; ) {
            lock.lock();
            try {
                if (state % 3 == index) {
                    System.out.print(printString);
                    state++;
                    i++;
                }
            } finally {
                lock.unlock();
            }
        }
        countDownLatch.countDown();
    }
}
