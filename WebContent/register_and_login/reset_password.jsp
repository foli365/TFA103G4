<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style type="text/css">
body {
	background: url("/TFA103G4/img/reset.jpg") no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}
.container{
	box-shadow: 0 1px 2px rgb(0 0 0 / 10%), 0 2px 4px rgb(0 0 0 / 10%);
}
</style>
</head>
<body>
	<div style="margin-top:120px; height:260px; width:550px; background-color: #FBEFE7; border-radius: 8px" class="container">
		<h3 class="px-2" style="padding-top: 30px">尋找您的帳號</h3>
		<hr>
		<h5 class="px-2" style="margin-bottom: 20px">請輸入您註冊的電子信箱帳號。</h5>
		<form class="px-2" action="resetPassword.do">
			<input class="form-control" type="email" name="email">
			<button type="button" class="mt-3 float-end btn btn-primary">搜尋</button>
		</form>
	</div>




	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>