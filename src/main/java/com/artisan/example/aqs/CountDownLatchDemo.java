package com.artisan.example.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CountDownLatchDemo {

	public static void main(String[] args) throws InterruptedException {

//		ExecutorService executorService = Executors.newCachedThreadPool();
		CountDownLatch countDownLatch = new CountDownLatch(2);
//		线程池的写法 （推荐）
//		executorService.execute(() -> {
//			try {
//				// 休眠1S，模拟业务耗时
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			countDownLatch.countDown();
//			log.info("parse sheetA");
//		});
//
//		executorService.execute(() -> {
//			try {
//				// 休眠1S，模拟业务耗时
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			log.info("parse sheetB");
//			countDownLatch.countDown();
//
//		});

		
		Thread t1 = new Thread(() -> {
			try {
				log.info(Thread.currentThread().getName() + " parse sheetA start");
				// 休眠1S，模拟业务耗时
				Thread.sleep(1000);
				// 调用CountDownLatch的countDown方法时，计数器N就会减1，CountDownLatch的await方法
				// 会阻塞当前线程，直到计数器N变成零
				countDownLatch.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.info(Thread.currentThread().getName() + " parse sheetA finish");
		}, "t1");

		Thread t2 = new Thread(() -> {
			try {
				log.info(Thread.currentThread().getName() + " parse sheetB start");
				// 休眠1S，模拟业务耗时
				Thread.sleep(1000);
				// 调用CountDownLatch的countDown方法时，计数器N就会减1，CountDownLatch的await方法
				// 会阻塞当前线程，直到计数器N变成零
				countDownLatch.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.info(Thread.currentThread().getName() + " parse sheetB finish");
		}, "t2");
		
		
		// 开启线程
		t1.start();
		t2.start();
		
		// 主线程调用await方法 ，等待t1 t2 完成后继续操作。 即计数器N变为0。 等于0时候，调用await方法时不会
		// 阻塞当前线程
		countDownLatch.await();
		
		log.info("t1 t2 完成，继续其他操作");

	}

}
