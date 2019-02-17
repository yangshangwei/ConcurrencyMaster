package com.artisan.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SynchronizedStaticClassDemo2 {

	// 修饰一个类
	public  void test() {
		synchronized (SynchronizedStaticClassDemo2.class) {
			for (int i = 0; i < 10; i++) {
				log.info("调用 修饰方法 i = {} ", i);
			}
		}
	}

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newCachedThreadPool();
		
		SynchronizedStaticClassDemo2 demo = new SynchronizedStaticClassDemo2();
		
		// demo调用
		executorService.execute(() ->{
			demo.test();
		});
		
		// demo调用
		executorService.execute(() ->{
			demo.test();
		});
		
		// 最后 关闭线程池
		executorService.shutdown();
	}
}