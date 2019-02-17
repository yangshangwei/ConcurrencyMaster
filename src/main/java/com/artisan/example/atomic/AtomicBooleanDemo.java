package com.artisan.example.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AtomicBooleanDemo {
	
	//总请求数
	private static int clientTotal = 5000;
	// 同一时间允许执行的线程数
	private static int threadTotal = 200;
	
	// 标识  ，使用原子包中的AtomicBoolean  初始化为false
	private static AtomicBoolean atomicBooleanFlag = new AtomicBoolean(false);
	
	public static void main(String[] args) throws Exception {
		
		// 线程池
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		// 信号量 同一时间允许threadTotal个请求同时执行 即初始化threadTotal个信号量
		Semaphore semaphore = new Semaphore(threadTotal);
		
		//定义clientTotal个线程需要执行完，主线程才能继续执行
		CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		
		// 循环
		for (int i = 0; i < clientTotal; i++) {
			executorService.execute(() ->{
				try {
					semaphore.acquire();
					doSomething();
					semaphore.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 减一
				countDownLatch.countDown();
			});
		}
		
		// 当全部线程都调用了countDown方法，count的值等于0，然后主线程就能通过await()方法，恢复执行自己的任务。
		countDownLatch.await();
		// 关闭线程池
		executorService.shutdown();
		
		log.info("flag:{}" ,atomicBooleanFlag.get());
		
	}

	
	private static void doSomething() {
		// 如果flag为flase就将其设置为true
		if (atomicBooleanFlag.compareAndSet(false, true)) {
			log.info("doSomething executed");
		}
	}

}
