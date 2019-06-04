package com.cafe24.config.app;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class MyBatisConfig {
	
	
	@Bean
	public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource, ApplicationContext act) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean
			= new SqlSessionFactoryBean();
		
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(act.getResource("classpath:com/cafe24/config/app/mybatis/configuration.xml"));
		
		
		
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
	      return new SqlSessionTemplate(sqlSessionFactory);
	}

	

}