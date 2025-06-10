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
		
		ir.addPathPatterns("/usr/member/myPage");
		ir.addPathPatterns("/usr/member/checkPw");
		ir.addPathPatterns("/usr/member/doCheckPw");
		ir.addPathPatterns("/usr/article/doLogout");
		ir.addPathPatterns("/usr/member/modify");
		ir.addPathPatterns("/usr/member/doModify");
		
		ir.addPathPatterns("/usr/reply/doWrite");
		
		ir.addPathPatterns("/usr/reaction/doLike");
		ir.addPathPatterns("/usr/reaction/doDislike");
		
		
		ir = registry.addInterceptor(needLogoutInterCeptor);
		ir.addPathPatterns("/usr/member/login");
		ir.addPathPatterns("/usr/member/doLogin");
		ir.addPathPatterns("/usr/member/join");
		ir.addPathPatterns("/usr/member/doJoin");
		ir.addPathPatterns("/usr/member/findLoginId");
		ir.addPathPatterns("/usr/member/doFindLoginId");
		ir.addPathPatterns("/usr/member/findLoginPw");
		ir.addPathPatterns("/usr/member/doFindLoginPw");
		
	}
}
