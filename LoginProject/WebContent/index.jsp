<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>INDEX</title>
</head>
<body>
<% String id=(String)session.getAttribute("id");
	if(id==null){
%>
<a href="loginForm.html">로그인</a>
<% } else { %>
<a href="logout">로그아웃</a>
<% } %>
</body>
</html>