package com.cafe24.jblog2.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog2.dao.UserDAO;
import com.cafe24.jblog2.vo.User;

public class AuthGlobalInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserDAO userDAO;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (handler instanceof HandlerMethod == false) {
			return true;
		}

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		if(auth == null) {
			return true;
		}
		
		HttpSession session = request.getSession();
		
		if(session == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		User authUser = (User) session.getAttribute("authUser");
		if( authUser == null ) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
	

		return true;
	}

}
