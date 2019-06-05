package com.cafe24.jblog2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog2.dao.UserDAO;
import com.cafe24.jblog2.vo.User;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;

	public void join(User user) {
		userDAO.join(user);
	}
	
	public List<User> getList(){
		return userDAO.findAll();
	}

	public User LoginAuth(User user) {
		return userDAO.LoginAuth(user);
	}

	public Boolean JudgeDuplicate(String id) {
		return userDAO.JudgeDuplicate(id) != null;
	}

}
