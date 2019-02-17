package com.artisan.example.sync;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.artisan.anno.ThreadSafe;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 要求： 10000个请求，同一时间允许500个请求同时执行 即每次允许500个请求同时执行
 * 
 * 实现：使用线程池和信号量和countDownLatch模拟客户端总共发出了1万个请求，每次允许500个请求同时执行，观察一共执行了个多少个请求
 * 
 * @author yangshangwei
 *
 */
@Slf4j
@ThreadSafe
public class CountCaculatorSyncDemo {

	// 请求总数
	private static int clientTotal = 10000;
	// 同一时间执行的请求数
	private static int threadTotal = 500;
	
	// 计数标识
	private static int count = 0;

	public static void main(String[] args) throws InterruptedException {
		
		CountCaculatorSyncDemo caculatorSyncDemo = new CountCaculatorSyncDemo();
		
		// 创建一个可缓存线程池，如果线程池长度超过实际需要，可灵活回收空闲线程，若无可回收的线程，则新建线程。
		ExecutorService executorService = Executors.newCachedThreadPool();

		// 信号量 同一时间允许threadTotal个请求同时执行 即初始化threadTotal个信号量
		final Semaphore semaphore = new Semaphore(threadTotal);
		
		// CountDownLatch是一个同步工具类，它允许一个或多个线程一直等待，直到其他线程的操作执行完后再执行
		// 定义clientTotal个线程需要执行完，主线程才能继续执行
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		// 创建clientTotal个线程，模拟clientTotal个请求
		for (int i = 0; i < clientTotal; i++) {
			// 执行一个线程 ，count加1
			executorService.execute(() -> {
				try {
					// 获取许可，获取许可后执行add方法, 在获得许可之前，一直将线程阻塞
					semaphore.acquire();
					caculatorSyncDemo.add();
					// 释放许可，并将其返回给信号量
					semaphore.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 【在循环内】每当一个线程完成了自己的任务后，计数器的值就会减1，这里指的是初始化CountDownLatch给定的clientTotal减一
				countDownLatch.countDown();
			});
			
		}
		
		// 主线程必须在启动其他线程后立即调用CountDownLatch.await()方法。
		// 这样主线程的操作就会在这个方法上阻塞，直到其他线程完成各自的任务。
		// 当全部线程都调用了countDown方法，count的值等于0，然后主线程就能通过await()方法，恢复执行自己的任务。
		countDownLatch.await();
		
		// 关闭线程池
		executorService.shutdown();
		// 打印总数
		log.info("执行总数：{}", count);
	}
	
	private synchronized void add() {
		count++;
	}

}