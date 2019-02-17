package com.artisan.example.atomic;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AtomicIntegerDemo {
	
	// 实例化一个初始值为5的AtomicInteger
	private static AtomicInteger value = new AtomicInteger(5);
	
	public static void main(String[] args) {
		// 以原子方式将输入的数值与value 相加，并返回结果 
		log.info("value的值：{}" ,value.addAndGet(3));
		
		// 获取value的值
		log.info("value的值：{}",value.get());

		// 如果输入的数值等于预期值，则以原子方式将该值设置为输入的值
		log.info("执行结果：{}" ,value.compareAndSet(8, 11)); // 因为经过了addAndGet，操作之前value的值为8，这里会将value更新成11，返回true
		log.info("执行结果：{}" ,value.compareAndSet(5, 11)); // 因为经过了addAndGet，操作之前value的值为8，并不等于5，因此不会更新为11，返回false
		
		
		log.info("value的值：{}" ,value.get());
		
		
		log.info("value 增长前的值：{}",value.getAndIncrement());
		log.info("value的值：{}" ,value.get());
		
		log.info("value 增长后的值：{}",value.incrementAndGet());
		log.info("value的值：{}" ,value.get());
		
		
		//  最终会设置成newValue，使用lazySet设置值后，可能导致其他线程在之后的一小段时间内还是可以读到旧的值
		value.lazySet(99);
		log.info("lazyset value: {}",value.get());
		
	}
}
