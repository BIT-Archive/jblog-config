package com.cafe24.jblog2.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class Post {
	
	
	private long no;
	
	@NotEmpty
	private String title;
	
	private String contents;
	
	private String reg_date;
	
	private long category_no;
	
	private String id;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public long getCategory_no() {
		return category_no;
	}
	public void setCategory_no(long category_no) {
		this.category_no = category_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	

}
