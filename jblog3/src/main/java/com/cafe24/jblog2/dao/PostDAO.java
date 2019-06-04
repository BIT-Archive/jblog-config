package com.cafe24.jblog2.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog2.vo.Post;

@Repository
public class PostDAO {
	
	@Autowired
	private SqlSession sqlSession;

	public List<Post> getPostList(Long category_no) {
		return sqlSession.selectList("post.getList", category_no);
	}

	public List<Post> recentPost(String id) {
		return sqlSession.selectList("post.recentList", id);
	}

	public List<Post> getPost(Long post_no) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("post.getPost", post_no);
	}

	public void insertPost(Post post) {
		sqlSession.insert("post.insertPost", post);
	}

	public void deletePostByCategory(Long no) {
		sqlSession.delete("post.deletePostByCategory", no);
	}

	

}
