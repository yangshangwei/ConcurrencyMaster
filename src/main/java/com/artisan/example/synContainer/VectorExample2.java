package com.artisan.example.synContainer;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.artisan.anno.NotThreadSafe;

/**
 * 
 * @author yangshangwei
 * 
 *         同步容器也并不一定是绝对线程安全的. 
 *         
 *         例如有两个线程，线程A根据size的值循环执行remove操作，而线程B根据size的值循环执行执行get操作。
 *         
 *         它们都需要调用size获取容器大小，当循环到最后一个元素时，若线程A先remove了线程B需要get的元素，
 *         那么就会报越界错误
 *
 */
@NotThreadSafe
public class VectorExample2 {

	private static Vector<Integer> vector = new Vector<>();

	public static void main(String[] args) {

		while (true) {

			for (int i = 0; i < 10; i++) {
				vector.add(i);
			}

			ExecutorService executorService = Executors.newCachedThreadPool();
			executorService.execute(() -> {
				for (int i = 0; i < vector.size(); i++) {
					vector.remove(i);
				}
			});

			executorService.execute(() -> {
				//当thread2想获取i=9的元素的时候，而thread1刚好将i=9的元素移除了，就会导致数组越界
				for (int i = 0; i < vector.size(); i++) {
					vector.get(i);
				}
			});
		}
	}
}
