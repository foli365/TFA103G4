<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="BIG5">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<style type="text/css">
body {
	background: url("<%=request.getContextPath()%>/img/Hawaii.jpg") no-repeat center center
		fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}

#main {
	margin-top: 150px;
	background-color: #fbefe7;
	box-shadow: 0 2px 4px rgb(0 0 0/ 10%), 0 8px 16px rgb(0 0 0/ 10%);
	border-radius: 30px;
	width: 600px;
	height: 70%;
}

h1 {
	margin-top: 15px;
	text-align: center;
	margin-bottom: 10px;
}

h3 {
	text-align: center;
	color: #9c9494;
	margin-bottom: 40px;
}

#name {
	margin-left: auto;
	margin-right: auto;
	width: 60%;
}

#email {
	margin-left: auto;
	margin-right: auto;
	width: 60%;
}

#password {
	margin-left: auto;
	margin-right: auto;
	width: 60%;
}

#submit {
	margin-top: 30px;
	border-radius: 20px;
	margin-bottom: 20px;
}

#phone {
	margin-left: auto;
	margin-right: auto;
	width: 60%;
}

#captcha {
	margin-left: 115px;
	width: 30%;
}
</style>
</head>

<body>
	<div id="main" class="container">
		<h1 style="padding-top: 30px">雲露營</h1>
		<h3>一起去露營吧!</h3>
		<form action="<%=request.getContextPath()%>/account/register.do"
			method="post">
			<div class="mb-3">
				<input type="text" placeholder="姓名" class="form-control" id="name"
					name="name">
			</div>
			<div class="mb-3">
				<input type="email" placeholder="電子郵件" class="form-control"
					id="email" aria-describedby="emailHelp" name="email">
			</div>
			<div class="mb-3">
				<input type="password" placeholder="密碼" class="form-control"
					id="password" name="password">
			</div>
			<div class="mb-3">
				<input type="password" placeholder="密碼確認" class="form-control"
					id="password">
			</div>
			<div class="mb-3">
				<input type="text" placeholder="驗證碼" class="form-control"
					id="captcha">
			</div>
			<div class="d-grid gap-2 col-6 mx-auto">
				<button id="submit" class="btn btn-success" type="submit">註冊</button>
			</div>
			<input type="hidden" value="register" name="action">
		</form>
		<!-- <div style="width: 100%; height: 20px; border-bottom: 1px solid #9C9494; text-align: center">
            <span style="color: #9C9494; font-size: 25px; background-color: #ffffff; padding: 0 10px;">
                或
            </span>
        </div> -->
		<div class="g-signin2" data-onsuccess="onSignIn"></div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
		crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.js"
		integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
		crossorigin="anonymous"></script>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
</body>

</html>