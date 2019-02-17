package com.artisan.example.atomic;

import java.util.concurrent.atomic.AtomicReference;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AtomicReferenceDemo {

	private static AtomicReference<Artisan> atomicReferenceArtisan = new AtomicReference<Artisan>();

	public static void main(String[] args) {

		Artisan expectedArtisan = new Artisan();
		expectedArtisan.setName("小工匠");
		expectedArtisan.setAge(20);

		// 将expectedArtisan设置到atomicReferenceArtisan
		atomicReferenceArtisan.set(expectedArtisan);

		Artisan updateArtisan = new Artisan();
		updateArtisan.setName("小工匠Update");
		updateArtisan.setAge(99);

		// compareAndSet方法进行原子更新操作,如果是expectedArtisan，则更新为updateArtisan

		boolean mark2 = atomicReferenceArtisan.compareAndSet(new Artisan(), updateArtisan);
		log.info("更新是否成功：{}", mark2);// false

		boolean mark = atomicReferenceArtisan.compareAndSet(expectedArtisan, updateArtisan);
		log.info("更新是否成功：{}", mark); // true

		log.info("atomicReferenceArtisan name:{}", atomicReferenceArtisan.get().getName());
		log.info("atomicReferenceArtisan age：{}", atomicReferenceArtisan.get().getAge());

	}

}
