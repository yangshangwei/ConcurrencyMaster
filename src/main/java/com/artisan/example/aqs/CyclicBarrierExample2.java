package com.artisan.example.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CyclicBarrierExample2 {
	
	// 屏障拦截的线程数量
    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executor.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
                    log.error("exception", e);
                }
            });
        }
        executor.shutdown();
    }

    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        try {
        	//每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞.
        	// 等待2S.为了使在发生异常的时候，不影响其他线程，一定要catch
            // 由于设置了超时时间后阻塞的线程可能会被中断，抛出BarrierException异常，
        	// 如果想继续往下执行，需要加上try-catch
            barrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | TimeoutException | BrokenBarrierException e) {
            // isBroken方法用来知道阻塞的线程是否被中断
            log.warn("exception occurred {} {}. isBroken : {}", e.getClass().getName(), e.getMessage(), barrier.isBroken());
        }
        log.info("{} continue", threadNum);
    }
}
