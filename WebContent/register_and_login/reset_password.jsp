<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div
		style="margin-top: 120px;height: 260px; max-width: 550px; background-color: #FBEFE7; border-radius: 8px"
		class="container">
		<h3 class="px-2" style="padding-top: 30px">重設密碼</h3>
		<hr>
		<form class="px-2"
			action="<%=request.getContextPath()%>/resetPassword.do" method="post">
			<label for=""></label>
			<input class="form-control" type="password" name="password" id="password"> <small
				style="color: red;">${noEmail}</small>
			<input class="form-control" type="password" id="passwordConfirm">
			<button type="submit" class="mt-3 float-end btn btn-primary">重設密碼</button>
		</form>
	</div>
<%@ include file="/template/script.html" %>
</body>
</html>