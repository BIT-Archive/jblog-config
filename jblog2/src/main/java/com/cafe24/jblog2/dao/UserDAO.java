package com.cafe24.jblog2.dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog2.vo.User;

@Repository
public class UserDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSource datasource;
	

	public void join(User user) {
		sqlSession.insert("user.join", user);
	}


	public List<User> findAll() {
		List<User> list = sqlSession.selectList("user.findAll");
		return list;
	}

	public User LoginAuth(User user) {
		return sqlSession.selectOne("user.LoginAuth", user);
	}


	public User JudgeDuplicate(String id) {
		User user = sqlSession.selectOne("user.JudgeDuplicate", id);
		return user;
	}

}
