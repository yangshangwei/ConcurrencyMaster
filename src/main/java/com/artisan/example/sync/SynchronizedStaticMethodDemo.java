package com.artisan.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SynchronizedStaticMethodDemo {

	// 修饰静态方法
	public synchronized static void test() {
		for (int i = 0; i < 10; i++) {
			log.info("调用 修饰方法 i = {} ", i);
		}
	}

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newCachedThreadPool();
		
		SynchronizedStaticMethodDemo demo1 = new SynchronizedStaticMethodDemo();
		SynchronizedStaticMethodDemo demo2 = new SynchronizedStaticMethodDemo();
		
		// demo1调用
		executorService.execute(() ->{
			// 其实直接调用test方法即可，这里仅仅是为了演示不同对象调用 静态同步方法
			demo1.test();
		});
		
		// demo2调用
		executorService.execute(() ->{
			// 其实直接调用test方法即可，这里仅仅是为了演示不同对象调用 静态同步方法
			demo2.test();
		});
		
		// 最后 关闭线程池
		executorService.shutdown();
	}
}