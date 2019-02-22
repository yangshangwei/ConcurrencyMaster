package com.artisan.example.threadLocal;

import lombok.extern.slf4j.Slf4j;

/**
 * 通常情况下都要具备三个方法  add  get  remove 
 * 特别是remove，否则容易造成内存泄漏
 * @author yangshangwei
 *
 */
@Slf4j
public class RequestHolder {

	private final static ThreadLocal<ArtisanUser>  USER_HOLDER = new ThreadLocal<ArtisanUser>();
	
	public static void addCurrentUser(ArtisanUser artisanUser) {
		//  将当前线程作为key, artisanUser作为value 存入ThreadLocal类的ThreadLocalMap中
		USER_HOLDER.set(artisanUser);
		log.info("将artisanUser:{} 写入到ThreadLocal",artisanUser.toString());
	}
	
	public static ArtisanUser getCurrentUser() {
		//  通过当前线程这个key ,获取存放在当前线程的ThreadLocalMap变量中的value
		ArtisanUser artisanUser = USER_HOLDER.get();
		log.info("从ThreadLocal中获取artisanUser:{}",artisanUser.toString());
		return artisanUser;
		
	}
	
	public static void removeCurrentUser() {
		log.info("从ThreadLocal中移除artisanUser:{}", getCurrentUser());
		//  通过当前线程这个key获取当前线程的ThreadLocalMap，从中移除value
		USER_HOLDER.remove();
	}
	
}
