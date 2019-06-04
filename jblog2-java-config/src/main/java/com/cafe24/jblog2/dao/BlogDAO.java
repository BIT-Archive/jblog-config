package com.cafe24.jblog2.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog2.vo.Blog;
import com.cafe24.jblog2.vo.User;

@Repository
public class BlogDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public void create(User user) {
		sqlSession.insert("blog.create", user);
	}

	public Blog getBlog(String id) {
		
		return (Blog) sqlSession.selectList("blog.getblog", id).get(0);
	}

	public void UpdateBlog(Blog blog, String url) {
		blog.setLogo(url);
		sqlSession.update("blog.Updateblog", blog);
	}



}
