package com.cafe24.jblog2.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog2.service.UserService;
import com.cafe24.jblog2.vo.User;


public class AuthLoginInterceptor extends HandlerInterceptorAdapter{

	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {


		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		User user = new User();
		
		user.setId(id);
		user.setPassword(password);
		
		User authUser = userService.LoginAuth(user);
		
		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath());
		
		return false;
		/** false로 해야 넘어간다 **/
	}

		
	

}
