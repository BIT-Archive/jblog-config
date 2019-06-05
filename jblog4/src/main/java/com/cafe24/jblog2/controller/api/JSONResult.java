package com.cafe24.jblog2.controller.api;

public class JSONResult {

	private String result; // success or fail
	private String message; // if fail then set
	private Object data; // if success then set
	
	
	public static JSONResult success (Object data) {
		return new JSONResult("success", null, data);
	}
	
	public static JSONResult fail(String message) {
		return new JSONResult("fail", message, null);
	}
	
	private JSONResult(String result, String message, Object data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}
	
	public String getResult() {
		return result;
	}

	
	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

	
}
