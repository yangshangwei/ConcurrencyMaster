package com.artisan.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class FutureExample {

    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
        	// 耗时任务
            log.info("do something in callable start");
            Thread.sleep(5000);
            log.info("do something in callable  end");
            return "DONE";
        }
    }

    public static void main(String[] args) throws Exception {
    	// 创建一个newCachedThreadPool线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // submit任务
        Future<String> future = executorService.submit(new MyCallable());
        // 主线程模拟一些业务操作,假设耗时一秒
        log.info("do something in main begin");
        Thread.sleep(1000);
        log.info("do something in main finish");

        // 获取刚才提交的线程MyCallable的返回结果
        log.info("获取MyCallable的返回结果，如果未返回，主线程将阻塞，处于等待状态");
        String result = future.get();
        log.info("result：{}", result);
        
        // 关闭线程池
        executorService.shutdown();
    }
}
