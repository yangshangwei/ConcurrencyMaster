package com.artisan.example.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AtomicIntegerArrayDemo {

	private static int[] array = new int[] { 11, 22 };

	private static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(array);

	public static void main(String[] args) {

		// 给array[0]的值增加33 ，然后返回增长后的值 44
		log.info("atomicIntegerArray addAndGet ：{}", atomicIntegerArray.addAndGet(0, 33)); // 44
		// 输出 atomicIntegerArray中
		log.info("atomicIntegerArray get ：{}", atomicIntegerArray.get(0)); // 44

		// 数组value通过构造方法传递进去，然后AtomicIntegerArray会将当前数组 复制一份，
		// 所以当AtomicIntegerArray对内部的数组元素进行修改时，不会影响传入的数组
		log.info("array[0]的值： {}", array[0]); // 11

		// 先get 然后再add ,返回add之前的值
		log.info("atomicIntegerArray getAndAdd ：{}", atomicIntegerArray.getAndAdd(0, 33)); // 44
		log.info("atomicIntegerArray .get(0) ：{}", atomicIntegerArray.get(0)); // 44+33
		log.info("array[0]的值： {}", array[0]); // 11

		// 先get ,然后再set,返回set之前的数据
		log.info("atomicIntegerArray getAndSet ：{}", atomicIntegerArray.getAndSet(0, 33));// 77
		log.info("atomicIntegerArray get(0) ：{}", atomicIntegerArray.get(0)); // 33
		log.info("array[0] ：{}", array[0]); // 11 

	}

}
