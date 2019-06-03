package com.cafe24.jblog2.controller;

import java.net.URLEncoder;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog2.security.Auth;
import com.cafe24.jblog2.service.BlogService;
import com.cafe24.jblog2.vo.Blog;
import com.cafe24.jblog2.vo.Category;
import com.cafe24.jblog2.vo.Post;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	

	
	
	@RequestMapping(value= {"", "/{category_no}", "/{category_no}/{post_no}"})
	public String index_id(@PathVariable String id,
							@PathVariable Optional<Long> category_no,
							@PathVariable Optional<Long> post_no,
								Model model) {
		
		model.addAttribute("blog", blogService.getBlog(id));
		model.addAttribute("category", blogService.getCategoryList(id));
		model.addAttribute("post",blogService.getPostList(id, category_no, post_no));

		return "blog/blog-main";
	}

	@Auth
	@RequestMapping(value="/admin/basic", method=RequestMethod.GET)
	public String admin_get(@PathVariable("id") String id,
							HttpSession session,
							Model model) {
		
		model.addAttribute("blog", blogService.getBlog(id));
		
		return "blog/blog-admin-basic";
	}
	
	@Auth
	@RequestMapping(value="/admin/basic", method=RequestMethod.POST)
	public String admin_post(@PathVariable("id") String id,
							HttpSession session,
							@ModelAttribute Blog blog,
							 @RequestParam(value="file") MultipartFile multipartFile,
							Model model) {
		
		String url = blogService.getUrl(multipartFile);
		
		blogService.UpdateBlog(blog, url);
		
		return "redirect:/"+ URLEncoder.encode(id);
	}
	
	@Auth
	@RequestMapping(value="/admin/write", method=RequestMethod.GET)
	public String write_get(@PathVariable("id") String id,
						Model model,
						HttpSession session) {
		
		model.addAttribute("blog", blogService.getBlog(id));
		model.addAttribute("category", blogService.getCategoryList(id));
		
		
		return "blog/blog-admin-write";
	}
	
	@Auth
	@RequestMapping(value="/admin/write", method=RequestMethod.POST)
	public String write_get(@PathVariable("id") String id,
						Model model,
						HttpSession session,
						@ModelAttribute Post post) {
		
		blogService.insertPost(post);
		
		
		return "redirect:/"+ URLEncoder.encode(id);
	}
	
	@Auth
	@RequestMapping(value="/admin/category")
	public String category(@PathVariable("id") String id,
						Model model,
						HttpSession session) {
		
		model.addAttribute("blog", blogService.getBlog(id));
		model.addAttribute("category", blogService.getCategoryListTable(id));
 
		return "blog/blog-admin-category";
	}
	
	@Auth
	@RequestMapping(value="/admin/delete")
	public @ResponseBody String delete_ajax(@PathVariable("id") String id,
						Model model,
						HttpSession session,
						@RequestParam(value="no", required=true) Long no) {
		
		
		blogService.deletePostByCategory(no);
		blogService.deleteCategory(id, no);
 
		return "삭제 성공!";
	}
	
	@Auth
	@RequestMapping(value="/admin/category_new")
	public @ResponseBody Category delete_ajax(@PathVariable("id") String id,
						Model model,
						HttpSession session,
						@RequestParam(value="name", required=true) String name,
						@RequestParam(value="description", required=true) String description) {
		

		blogService.createCategory(id, name, description);
		
		Category category = blogService.getLastCategory(id);
		
		return category;
	}
	

}
