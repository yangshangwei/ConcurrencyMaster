package com.artisan.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.checkerframework.checker.index.qual.LengthOf;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.artisan.example.threadLocal.ArtisanUser;
import com.artisan.example.threadLocal.RequestHolder;

import lombok.extern.slf4j.Slf4j;

/**
 * 实现 Handlerlnterceptor接口,覆盖其对应的方法即完成了拦截器的开发
 * 
 * @author yangshangwei
 *
 */
@Slf4j
public class MyInterceptor implements HandlerInterceptor {

	/**
	 * preHandle在执行Controller之前执行 
	 * 返回true：继续执行处理器逻辑，包含Controller的功能 
	 * 返回false：中断请求
	 * 
	 * 处理器执行前方法
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("MyInterceptor-处理器执行前方法preHandle，返回true则不拦截后续的处理");
		
		// 模拟user存在session中
		ArtisanUser user = new ArtisanUser();
		user.setName("artisan");
		user.setAge(20);
		request.getSession().setAttribute("user", user);
		
		// 将用户信息添加到ThreadLocal中
		RequestHolder.addCurrentUser((ArtisanUser)request.getSession().getAttribute("user"));
		
		return true;
	}

	/**
	 * postHandle在请求执行完之后渲染ModelAndView返回之前执行
	 * 
	 * 处理器处理后方法
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	/**
	 * afterCompletion在整个请求执行完毕后执行,无论是否发生异常都会执行
	 * 
	 * 处理器完成后方法
	 * 
	 * 
	 * 在这个方法中移除当前用户
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("MyInterceptor-处理器完成后方法afterCompletion");
		RequestHolder.removeCurrentUser();
	}

}
