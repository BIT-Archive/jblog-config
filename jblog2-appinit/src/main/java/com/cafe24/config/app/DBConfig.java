package com.cafe24.config.app;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:com/cafe24/config/app/properties/jdbc.properties")
public class DBConfig {
	
	@Resource
	private Environment env;

	
	@Bean
	public DataSource basicDataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		
		basicDataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		basicDataSource.setUrl(env.getProperty("jdbc.url"));
		basicDataSource.setUsername(env.getProperty("jdbc.userName"));
		basicDataSource.setPassword(env.getProperty("jdbc.password"));
		
		basicDataSource.setInitialSize(env.getProperty("jdbc.initialSize", Integer.class));
		basicDataSource.setMaxActive(env.getProperty("jdbc.maxActive", Integer.class));
		
		return basicDataSource;
	}
	
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	
}