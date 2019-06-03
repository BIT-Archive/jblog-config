package com.cafe24.jblog2.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.jblog2.controller.api.JSONResult;
import com.cafe24.jblog2.service.BlogService;
import com.cafe24.jblog2.service.UserService;
import com.cafe24.jblog2.vo.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join_GET() {
		return "user/join";
	}

	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join_POST(@ModelAttribute @Valid User user,
							BindingResult result,
							Model model) {
		
		if(result.hasErrors()) {
			
		
			model.addAllAttributes(result.getModel());
		
		
			return "user/join";
		}
		
		userService.join(user);
		blogService.create(user);
		blogService.initCategory(user);
		
		
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login_GET() {
		
		
		return "user/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login_POST(@ModelAttribute User user,
								HttpSession session) {
		
		User authUser = userService.LoginAuth(user);
		
		if( authUser == null) {
			return "user/login";
		}
		
		session.setAttribute("authUser", authUser);
		return "redirect:/";
		
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/api/checkId")
	@ResponseBody
	public JSONResult checkEmail(@RequestParam(value="param", required=true) String id) {
		
		Boolean judge = userService.JudgeDuplicate(id);
		
		return JSONResult.success(judge);
	}
	
	
}
