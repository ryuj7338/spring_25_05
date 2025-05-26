package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.example.demo.interceptor.BeforeActionInterceptor;
import com.example.demo.interceptor.NeedLoginInterceptor;
import com.example.demo.interceptor.NeedLogoutInterceptor;

@Configuration
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {

	@Autowired
	BeforeActionInterceptor beforeActionInterCeptor;

	@Autowired
	NeedLoginInterceptor needLoginInterCeptor;

	@Autowired
	NeedLogoutInterceptor needLogoutInterCeptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(beforeActionInterCeptor).addPathPatterns("/**");
//
//		registry.addInterceptor(needLoginInterCeptor).addPathPatterns("/usr/article/write")
//				.addPathPatterns("/usr/article/doWrite").addPathPatterns("/usr/article/modify")
//				.addPathPatterns("/usr/article/doModify").addPathPatterns("/usr/article/doDelete")
//				.addPathPatterns("/usr/member/doLogout");
//
//		registry.addInterceptor(needLogoutInterCeptor).addPathPatterns("/usr/member/login")
//				.addPathPatterns("/usr/member/doLogin").addPathPatterns("/usr/member/join")
//				.addPathPatterns("/usr/member/doJoin");
		InterceptorRegistration ir;
		
		ir = registry.addInterceptor(beforeActionInterCeptor);
		ir.addPathPatterns("/**");
		ir.addPathPatterns("/favicon.ico");
		ir.addPathPatterns("/resource/**");
		ir.addPathPatterns("/error");
		
		ir = registry.addInterceptor(needLoginInterCeptor);
		ir.addPathPatterns("/usr/article/write");
		ir.addPathPatterns("/usr/article/doWrite");
		ir.addPathPatterns("/usr/article/modify");
		ir.addPathPatterns("/usr/article/doModify");
		ir.addPathPatterns("/usr/article/doDelete");
		ir.addPathPatterns("/usr/article/doLogout");
		ir.addPathPatterns("/usr/reply/doWrite");
		
		ir.addPathPatterns("/usr/reaction/doLike");
		ir.addPathPatterns("/usr/reaction/doDislike");
		
		
		ir = registry.addInterceptor(needLogoutInterCeptor);
		ir.addPathPatterns("/usr/member/login");
		ir.addPathPatterns("/usr/member/doLogin");
		ir.addPathPatterns("/usr/member/join");
		ir.addPathPatterns("/usr/member/doJoin");
		
	}
}
