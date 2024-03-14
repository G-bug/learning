package com.test.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 实现了CyclicBarrier的复用功能
 */
public class SemaphoreTest {

    private static volatile Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread() + "A task over");
                semaphore.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread() + "B task over");
                semaphore.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 等待A B执行结束 主线程阻塞 等semaphore计数器变为2
        semaphore.acquire(2);

        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread() + "C task over");
                semaphore.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        semaphore.acquire(1);

        System.out.print("task is over");

        // 关闭线程池
        executorService.shutdown();

    }

}
