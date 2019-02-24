package com.artisan.example.synContainer;

import java.util.Iterator;
import java.util.Vector;

/**
 * 
 * @author yangshangwei
 * 
 *         当我们使用foreach循环或迭代器去遍历元素的同时又执行删除操作的话，即便在单线程下也会报并发修改异常.
 * 
 *         建议使用for循环
 *
 */
public class VectorExample3 {
	// 使用foreach
	// 在遍历的同时进行了删除的操作，会抛出java.util.ConcurrentModificationException并发修改异常
	private static void test1(Vector<Integer> v1) {
		for (Integer i : v1) {
			if (i.equals(3)) {
				v1.remove(i);
			}
		}
	}

	// 使用iterator
	// 在遍历的同时进行了删除的操作，会抛出java.util.ConcurrentModificationException并发修改异常
	private static void test2(Vector<Integer> v1) {
		Iterator<Integer> iterator = v1.iterator();
		while (iterator.hasNext()) {
			Integer i = iterator.next();
			if (i.equals(3)) {
				iterator.remove();
			}
		}
	}

	// 使用for
	// success
	private static void test3(Vector<Integer> v1) {
		for (int i = 0; i < v1.size(); i++) {
			if (v1.get(i).equals(3)) {
				v1.remove(i);
			}
		}
	}
	

	public static void main(String[] args) {

		Vector<Integer> vector = new Vector<>();
		for (int i = 1; i <= 10; i++) {
			vector.add(i);
		}
		test1(vector);
		test2(vector);
		test3(vector);
	}
}
