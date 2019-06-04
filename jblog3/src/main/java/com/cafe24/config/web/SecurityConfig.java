package com.cafe24.config.web;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cafe24.jblog2.security.AuthGlobalInterceptor;
import com.cafe24.jblog2.security.AuthLoginInterceptor;
import com.cafe24.jblog2.security.AuthLogoutInterceptor;
import com.cafe24.jblog2.security.AuthUserHandlerMethodArgumentResolver;



@Configuration
public class SecurityConfig extends WebMvcConfigurerAdapter{

	@Bean
	public AuthUserHandlerMethodArgumentResolver authUserHandlerMethodArgumentResolver() {
		return new AuthUserHandlerMethodArgumentResolver();
	}
	
	
	//
	// AddArgumentResolvers
	//
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(authUserHandlerMethodArgumentResolver());
	}

	@Bean
	public AuthGlobalInterceptor authInterceptor() {
		return new AuthGlobalInterceptor();
	}
	
	@Bean
	public AuthLoginInterceptor authLoginInterceptor() {
		return new AuthLoginInterceptor();
	}
	
	@Bean
	public AuthLogoutInterceptor authLoginOutInterceptor() {
		return new AuthLogoutInterceptor();
	}
	
	

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(new AuthLoginInterceptor())
			.addPathPatterns("/user/auth");
		
		registry
		.addInterceptor(new AuthLogoutInterceptor())
		.addPathPatterns("/user/logout");
		
		registry
		.addInterceptor(new AuthGlobalInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns("/user/auth")
		.excludePathPatterns("/user/logout")
		.excludePathPatterns("/assets/**")
		.excludePathPatterns("/images/**")
		.excludePathPatterns("/user/**");
	}

	
}
