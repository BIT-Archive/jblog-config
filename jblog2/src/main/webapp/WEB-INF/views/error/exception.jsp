<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Ooooooooooooops ! - ${uri }</h1>
	<p> 죄송합니다. 
		<br>
		예외 발생 
		<br>
		===========================
	</p>
		<br>
			<pre style="font-weight:bold;">
		 		${exception }
		 	</pre>
</body>
<script>
	//window.location = "${pageContext.servletContext.contextPath }";
</script>
</html>