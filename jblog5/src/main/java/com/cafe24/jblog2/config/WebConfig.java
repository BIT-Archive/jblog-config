package com.cafe24.jblog2.config;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.cafe24.jblog2.security.AuthGlobalInterceptor;
import com.cafe24.jblog2.security.AuthLoginInterceptor;
import com.cafe24.jblog2.security.AuthLogoutInterceptor;
import com.cafe24.jblog2.security.AuthUserHandlerMethodArgumentResolver;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;


@Configuration
public class WebConfig extends WebMvcConfigurerAdapter implements WebMvcConfigurer{
	

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		resolver.setExposeContextBeansAsAttributes(true);
		
		
		return resolver;
	}
	
	// Default Servlet handler
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	    configurer.enable();
	}

	
	// Message Converter
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(mappingJackson2HttpMessageConverter());
		converters.add(stringHttpMessageConverter());
	}
	

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


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(authLoginInterceptor())
			.addPathPatterns("/user/auth");
		
		registry
		.addInterceptor(authLoginOutInterceptor())
		.addPathPatterns("/user/logout");
		
		registry
		.addInterceptor(authGlobalInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns("/user/auth")
		.excludePathPatterns("/user/logout")
		.excludePathPatterns("/assets/**");
	}
	
	public AuthLoginInterceptor authLoginInterceptor() {
		return new AuthLoginInterceptor();
	}
	public AuthLogoutInterceptor authLoginOutInterceptor() {
		return new AuthLogoutInterceptor();
	}
	
	
	@Bean
	public AuthGlobalInterceptor authGlobalInterceptor() {
		return new AuthGlobalInterceptor();
	}
	
	
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
				.indentOutput(true)
				.dateFormat(new SimpleDateFormat("yyyy-MM-dd"))
				.modulesToInstall(new ParameterNamesModule() );
		
		
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		
		converter.setSupportedMediaTypes(Arrays.asList(new MediaType("application", "json", Charset.forName("UTF-8"))));
		
		return converter;
	}
	
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(new MediaType("text","html", Charset.forName("UTF-8"))));
		
		return converter;
	}
}
	