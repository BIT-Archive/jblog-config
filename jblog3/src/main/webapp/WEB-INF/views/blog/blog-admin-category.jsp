<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/blog_header.jsp"/>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/include/admin-menu.jsp">
					<c:param name="menu" value="category"></c:param>
				</c:import>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
					<c:forEach items="${category}" var="category" varStatus="status">
						<tr>
							<td>${category.no }</td>
							<td>${category.name }</td>
							<td>${category.count }</td>
							<td>${category.description }</td>
							<td>
								<img src="${pageContext.request.contextPath}/assets/images/delete.jpg" class="delete_button" id="${category.no}"/>
							</td>
						</tr>
					</c:forEach>
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" id="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" id="desc"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="submit" value="카테고리 추가" id="submit_buttom"></td>
		      		</tr>      		      		
		      	</table> 
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/blog_footer.jsp"/>
	</div>
</body>
<script>

    var delete_button = document.getElementsByClassName("delete_button");
    

     var event_handler = function(event){
        var no = this.id;
        var blog = "${blog.id}";
        
        $.ajax({
            url: "${pageContext.servletContext.contextPath}/" + blog + "/admin/delete?no=" + no,
            type: 'GET',
            cache : false,
            dataType : "json",
            success:function(result, textStatus, xhr, response){

                 console.log(result);
                 document.getElementById(no).parentNode.parentNode.innerHTML ="";
             }
            
        });
    };
     for (var i = 0 ; i < delete_button.length; i++) {
    	 delete_button[i].addEventListener('click' , event_handler , false ); 
    }

</script>
<script>
window.onload = function(){
    var submit_buttom = document.getElementById("submit_buttom");
    

     var event_handler = function(event){
        var description = document.getElementById("desc").value;
        var name = document.getElementById("name").value;
        var blog = "${blog.id}";
        
        $.ajax({
            url: "${pageContext.servletContext.contextPath}/" + blog + "/admin/category_new?name=" + name + "&description="+description,
            type: 'GET',
            cache : false,
            dataType : "json",
            success:function(result, textStatus, xhr, response){

                 console.log(result);
                 
                 document.getElementsByTagName("tbody")[0].innerHTML+=
                	   "<tr><td>"+ result.no 
                	 +"</td><td>"+ result.name
                	 +"</td><td>0</td><td>"+result.description+"</td><td><img src='/jblog2/assets/images/delete.jpg' class='delete_button' id='"+result.no+"'></td></tr>";
             }
            
        });
    };

    submit_buttom.addEventListener('click' , event_handler , false ); 

}
</script>
</html>