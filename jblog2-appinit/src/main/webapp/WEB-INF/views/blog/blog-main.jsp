<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/blog_header.jsp"/>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					
					<c:if test="${post[0] ne null }">
						<h4>${post[0].title }</h4>
					
					<p>
						${post[0].contents }
					<p>
					</c:if>
				</div>
				<ul class="blog-list">
					<c:forEach items="${post }" var="post" varStatus="status" begin="1">
						<li><a href="${pageContext.request.contextPath}/${blog.id}/${post.category_no}/${post.no}">${post.title}</a> <span>${post.reg_date }</span>	</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blog.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				
				<c:forEach items="${category }" var="post" varStatus="status">
						<li><a href="${pageContext.request.contextPath}/${blog.id}/${category[status.index].no}">${category[status.index].name}</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>