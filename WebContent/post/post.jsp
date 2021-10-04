<%@page import="com.post.model.PostVO"%>
<%@page import="com.post.model.PostService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<% 
	PostService postSvc = new PostService();
 	PostVO postVO = postSvc.findByPostId(Integer.parseInt(request.getParameter("postId")));
 	pageContext.setAttribute("postVO", postVO);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title></title>
</head>
<body>
	<h1>${postVO.title}</h1>
	<h5>${postVO.created}</h5>
	<p>${postVO.article}</p>
</body>
</html>