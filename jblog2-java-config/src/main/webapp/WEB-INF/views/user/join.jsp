<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<c:import url="/WEB-INF/views/include/menu.jsp"/>
		<form class="join-form" id="join-form" method="post" action="">
			<label class="block-label" for="name">이름</label>
			<input id="name" name="name" type="text" value="">
			
			<spring:hasBindErrors name="user">
						<c:if test="${errors.hasFieldErrors('name') }">
							<p class="error_message">
								<spring:message
									code="${errors.getFieldError( 'name' ).codes[0] }"
									text="${errors.getFieldError( 'name' ).defaultMessage }" />
							</p>
						</c:if>
			</spring:hasBindErrors>
			
			<label class="block-label" for="blog-id">아이디</label>
			<input id="blog-id" name="id" type="text"> 
			<spring:hasBindErrors name="user">
						<c:if test="${errors.hasFieldErrors('id') }">
							<p class="error_message">
								<spring:message
									code="${errors.getFieldError( 'id' ).codes[0] }"
									text="${errors.getFieldError( 'id' ).defaultMessage }" />
							</p>
						</c:if>
			</spring:hasBindErrors>
			
			
			<input id="btn-checkId" type="button" value="id 중복체크">
			<img id="img-checkId" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" />
			<spring:hasBindErrors name="user">
						<c:if test="${errors.hasFieldErrors('password') }">
							<p class="error_message">
								<spring:message
									code="${errors.getFieldError( 'password' ).codes[0] }"
									text="${errors.getFieldError( 'password' ).defaultMessage }" />
							</p>
						</c:if>
			</spring:hasBindErrors>

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form>
	</div>
</body>
<script>
$(function() {
	$('#btn-checkId')
			.click(
					function() {
						$('#blog-id').change(function() {
							$('#btn-checkId').show();
							$('#img-checkId').hide();
						})
						var id = $('#blog-id').val()
						if (id == '') {
							return;
						}

						$.ajax({
									url : "${pageContext.servletContext.contextPath}/user/api/checkId?param="+ id,
									type : "get",
									dataType : "json",
									data : "",
									success : function(response) {
										if (response.result != "success") {
											console.error(response.message);
											return;
										}
										if (response.data == true) {
											alert("이미 존재하는 아이디 입니다.")
											$("#blog-id").focus();

											return;
										}
										if (response.data == false) {
											$('#btn-checkId').hide();
											$('#img-checkId').show();
										}
									}
								})
					})
})

</script>
</html>
