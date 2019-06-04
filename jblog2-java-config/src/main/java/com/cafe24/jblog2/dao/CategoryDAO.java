package com.cafe24.jblog2.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog2.vo.Category;
import com.cafe24.jblog2.vo.User;

@Repository
public class CategoryDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public List<Category> getCategoryList(String id) {
		return sqlSession.selectList("category.getList", id);
	}

	public List<Long> getNoById(String id) {
		return sqlSession.selectList("category.getNoById",id);
	}

	public void initCategory(User user) {
		sqlSession.insert("category.init", user);
	}

	public List<Category> getCategoryListTable(String id) {
		return sqlSession.selectList("category.getListInfo",id);
	}

	public void deleteCategory(String id, Long no) {
		Category category = new Category();
		category.setId(id);
		category.setNo(no);
		sqlSession.delete("category.deleteCategory", category);
	}

	public void createCategory(String id, String name, String description) {
		Category category = new Category();
		category.setId(id);
		category.setName(name);
		category.setDescription(description);
		sqlSession.insert("category.createCategory", category);
	}

	public Category getLastCategory(String id) {
		return sqlSession.selectOne("category.getLastCategory", id);
	}


}
