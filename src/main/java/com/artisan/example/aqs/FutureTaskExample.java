package com.artisan.example.aqs;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FutureTaskExample {

    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
            	// 耗时任务
                log.info("do something in callable");
                Thread.sleep(5000);
                return "DONE";
            }
        });
        
        
        //创建一个newCachedThreadPool线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // execute futureTask任务
        executorService.execute(futureTask);
        // 主线程模拟一些业务操作,假设耗时一秒
        log.info("do something in main begin");
        Thread.sleep(1000);
        log.info("do something in main finish");

        // 获取刚才提交的线程MyCallable的返回结果
        log.info("获取futureTask的返回结果，如果未返回，主线程将阻塞，处于等待状态");
        String result = futureTask.get();
        log.info("result：{}", result);
        // 关闭线程池
        executorService.shutdown();
    }
}
