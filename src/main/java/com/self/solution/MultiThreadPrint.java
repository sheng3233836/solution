package com.self.solution;

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
    /**
     * 完成状态
     */
    private boolean finish = false;

    public MultiThreadPrint(int times) {
        this.times = times;
    }

    public void print() {
        new Thread(() -> doPrint(0, "1")).start();
        new Thread(() -> doPrint(1, "2")).start();
        new Thread(() -> doPrint(2, "3")).start();
    }

    private void doPrint(int index, String printString) {
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
        // 最后线程
        if (index == 2) {
            finish = true;
        }
    }

    public boolean isFinish() {
        return finish;
    }
}
