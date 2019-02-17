package com.artisan.example.volat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.artisan.anno.NotThreadSafe;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 演示使用volatile尝试解决并发问题，失败，因为volatile仅能保证变量的可见性，不能保证操作原子性
 * 
 * 要求： 10000个请求，同一时间允许500个请求同时执行 即每次允许500个请求同时执行
 * 
 * 实现：使用线程池和信号量和countDownLatch模拟客户端总共发出了1万个请求，每次允许500个请求同时执行，观察一共执行了个多少个请求
 * 
 * @author yangshangwei
 *
 */
@Slf4j
@NotThreadSafe
public class CountCaculatorSyncDemo {

	// 请求总数
	private static int clientTotal = 10000;
	// 同一时间执行的请求数
	private static   int threadTotal = 500;
	
	// 计数标识
	private static volatile int count = 0;

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
		// volatile 修饰了count ,仅能保证主内存中的数据是最新的，但count++操作不是原子操作，原子性不能保证，所以volatile不适合计数的场景
		// 1、count 从主存中取出count的值
        // 2、+1  在工作内存中执行+1操作
        // 3、count 将count的值写回主存
        //即使将count用volatile修饰，每次从主存中取到的都是最新的值，可是当多个线程同时取到最新的值，执行+1操作，当刷新到主存中的时候会覆盖结果，从而丢失一些+1操作
		count++;
	}

}