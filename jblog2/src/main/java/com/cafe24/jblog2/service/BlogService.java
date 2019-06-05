package com.cafe24.jblog2.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog2.dao.BlogDAO;
import com.cafe24.jblog2.dao.CategoryDAO;
import com.cafe24.jblog2.dao.PostDAO;
import com.cafe24.jblog2.vo.Blog;
import com.cafe24.jblog2.vo.Category;
import com.cafe24.jblog2.vo.Post;
import com.cafe24.jblog2.vo.User;

@Service
public class BlogService {

	private static final String SAVE_PATH = "/mysite-uploads";
	private static final String URL = "/images";
	
	@Autowired
	private BlogDAO blogDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private PostDAO postDAO;
	
	public void create(User user) {
		blogDAO.create(user);
	}

	public Blog getBlog(String id) {
		
		return blogDAO.getBlog(id);
	}

	public List<Category> getCategoryList(String id) {
		
		return categoryDAO.getCategoryList(id);
	}
	
	public List<Post> getPostList(String id, Optional<Long> category_no, Optional<Long> post_no) {
		
		if(post_no.isPresent()) {
			return postDAO.getPost(post_no.get());
		}else if (category_no.isPresent()==true && post_no.isPresent()==false) {
			return postDAO.getPostList(category_no.get());
		}else {
			return postDAO.recentPost(id);
		}

	}
	
	public List<Post> recentPost(String id){
		return postDAO.recentPost(id);
	}

	public List<Post> getPost(Long post_no) {
		return postDAO.getPost(post_no);
	}

	
	public List<Long> findNoById(String id) {
		return categoryDAO.getNoById(id);
	}
	
	
	public void initCategory(User user) {
		categoryDAO.initCategory(user);
	}

	public String getUrl(MultipartFile multipartFile) {
		String url = "";

		try {
			
		String originalFilename = multipartFile.getOriginalFilename();
		
		String extName = originalFilename.substring(originalFilename.lastIndexOf('.')+1);
		
		String saveFileName = generateSaveFileName(extName);
		
		byte fileData[] = multipartFile.getBytes();
		

		OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
		os.write(fileData);
		os.close();
		
		url = URL + "/" + saveFileName;
		
		} catch (IOException e) {
			throw new RuntimeException("FileUpload Error : " + e);
		}
		return url;
	}



	private String generateSaveFileName(String extName) {
		String fileName = "";
		Calendar calendar = Calendar.getInstance();
		
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("."+extName);
		
		return fileName;
	}

	public void UpdateBlog(Blog blog, String url) {
		blogDAO.UpdateBlog(blog,url);
	}

	public void insertPost(Post post) {
		postDAO.insertPost(post);
		
	}

	public List<Category> getCategoryListTable(String id) {
		return categoryDAO.getCategoryListTable(id);
	}

	public void deleteCategory(String id, Long no) {
		categoryDAO.deleteCategory(id, no);
	}

	public void deletePostByCategory(Long no) {
		postDAO.deletePostByCategory(no);
	}

	public void createCategory(String id, String name, String description) {
		categoryDAO.createCategory(id, name, description);
	}

	public Category getLastCategory(String id) {
		return categoryDAO.getLastCategory(id);
	}

}
