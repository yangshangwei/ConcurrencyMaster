package com.artisan.example.commonUnsafe;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.artisan.anno.ThreadSafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class JDK8TimeDemo {

	// 请求总数
	public static int clientTotal = 5000;

	// 同时并发执行的线程数
	public static int threadTotal = 200;

	// JDK8的时间处理类 全局变量 线程安全
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

	public static void main(String[] args) throws Exception {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		for (int i = 0; i < clientTotal; i++) {
			final int count = i;
			executorService.execute(() -> {
				try {
					semaphore.acquire();
					update(count);
					semaphore.release();
				} catch (Exception e) {
					log.error("exception", e);
				} finally {
					countDownLatch.countDown();
				}

			});
		}
		countDownLatch.await();
		log.info("finish");
		executorService.shutdown();
	}

	private static void update(int threadNum) {
		log.info("{}, {}", threadNum, LocalDate.parse("20190223", dtf));
	}
}
