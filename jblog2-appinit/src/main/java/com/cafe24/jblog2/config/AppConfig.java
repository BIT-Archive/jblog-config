package com.cafe24.jblog2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.cafe24.config.app.DBConfig;
import com.cafe24.config.app.MyBatisConfig;

@Configuration
@EnableAspectJAutoProxy
@EnableWebMvc
@ComponentScan({"com.cafe24.jblog2.dao", "com.cafe24.jblog2.service", "com.cafe24.jblog2.aop"})
@Import({DBConfig.class, MyBatisConfig.class})
public class AppConfig {

}