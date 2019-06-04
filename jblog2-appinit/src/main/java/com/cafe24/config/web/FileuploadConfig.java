package com.cafe24.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@PropertySource("classpath:com/cafe24/config/web/multipart.properties")
public class FileuploadConfig extends WebMvcConfigurerAdapter{

	@Autowired
	private Environment env;
	//
	// Multipart Resolver
	//
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartReslover = new CommonsMultipartResolver();
		
		multipartReslover.setMaxUploadSize(env.getProperty("maxUploadSize", Integer.class));
		multipartReslover.setMaxInMemorySize(env.getProperty("maxInMemorySize", Integer.class));
		multipartReslover.setDefaultEncoding(env.getProperty("defaultEncoding"));
		
		return multipartReslover;
	}
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("file:/mysite-uploads/");
	}
	
	

}