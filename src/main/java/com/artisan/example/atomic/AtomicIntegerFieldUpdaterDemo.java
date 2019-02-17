package com.artisan.example.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AtomicIntegerFieldUpdaterDemo {

	// 创建原子更新器，并设置需要更新的对象类和对象的属性
	private static AtomicIntegerFieldUpdater<Artisan2> atomicIntegerFieldUpdaterArtisan = AtomicIntegerFieldUpdater
			.newUpdater(Artisan2.class, "age");

	public static void main(String[] args) {
		Artisan2 artisan = new Artisan2();
		artisan.setAge(20);
		
		log.info("目前atomicIntegerFieldUpdaterArtisan中age的值为:{}",atomicIntegerFieldUpdaterArtisan.get(artisan));
		
		if (atomicIntegerFieldUpdaterArtisan.compareAndSet(artisan, 20, 99)) {
			log.info("artisan的age属性的值期望为20，则将20更新为99，更新成功");
		}
		
		// 获取artisan更新后的age的值
		log.info("目前atomicIntegerFieldUpdaterArtisan中age的值为:{}",atomicIntegerFieldUpdaterArtisan.get(artisan));
		
		
		if (atomicIntegerFieldUpdaterArtisan.compareAndSet(artisan, 20, 99)) {
			log.info("artisan的age属性的值期望为20，则将20更新为99，更新成功");
		}else {
			log.info("artisan的age属性的值期望为20，则将20更新为99，更新失败. 目前age的值为：{}",atomicIntegerFieldUpdaterArtisan.get(artisan));
		}
		
		
	}
}
