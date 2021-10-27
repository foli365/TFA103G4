<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("name", session.getAttribute("account"));
%>
<!DOCTYPE html>
<html lang="zh-tw">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<%@ include file="/template/navbar.jsp"%>
<link rel="stylesheet" href="./index.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
</head>

<body>
	<h1 id="title" class="text-center mt-5">Find yourself outside</h1>
	<h3 class="text-center mb-5">
		Discover and book tent camping, RV parks,<br> cabins, treehouses,
		and glamping.
	</h3>

	<div id="decoImg" class="container-sm ">
		<div id="search" class="container">
			<form id="form" action="" class="">
				<div
					class="d-flex container row mt-3 gx-2 align-items-center justify-content-center"
					id="searchSet">
					<div class="col-md-3">
						<input type="text" class="form-control rounded-pill"
							placeholder="想去哪裡?" aria-label="First name">
					</div>
					<div class="col-md-3">
						<input id="date" type="date" class="form-control rounded-pill"
							placeholder="輸入日期" aria-label="Last name">
					</div>
					<div class="col-md-3">
						<select class="form-select rounded-pill" aria-label="Last name">
							<option selected>人數</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5+">5+</option>
						</select>
					</div>
					<div class="col-md-1">
						<button type="submit" class="btn btn-success">
							<i class="bi bi-search"></i>
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="container-sm" id="hot">
		<h1 class="my-5" style="color: #D84627;">熱門營區</h1>
		<div class="row mb-5 mx-0">
			<div class="col-md d-flex justify-content-center gx-0">
				<div class="card" style="width: 100%;">
					<img src="./img/0_Campsites.jpg" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">Card title</h5>
						<p class="card-text">Some quick example text to build on the
							card title and make up the bulk of the card's content.</p>
						<a href="#" class="btn btn-primary">瀏覽營地</a>
					</div>
				</div>
			</div>
			<div class="col-md d-flex justify-content-center">
				<div class="card" style="width: 100%;">
					<img src="./img/shutterstock_625918454.0.webp" class="card-img-top"
						alt="...">
					<div class="card-body">
						<h5 class="card-title">Card title</h5>
						<p class="card-text">Some quick example text to build on the
							card title and make up the bulk of the card's content.</p>
						<a href="#" class="btn btn-primary">瀏覽營地</a>
					</div>
				</div>
			</div>
			<div class="col-md d-flex justify-content-center">
				<div class="card" style="width: 100%;">
					<img src="./img/Leave-No-Trace-campsite-tent.jpg"
						class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">Card title</h5>
						<p class="card-text">Some quick example text to build on the
							card title and make up the bulk of the card's content.</p>
						<a href="#" class="btn btn-primary">瀏覽營地</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<footer
			class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
			<div class="col-md-4 d-flex align-items-center">
				<a href="/"
					class="mb-3 me-2 mb-md-0 text-muted text-decoration-none lh-1">
					<svg class="bi" width="30" height="24">
						<use xlink:href="#bootstrap"></use></svg>
				</a> <span class="text-muted"><a href="<%=request.getContextPath()%>/backendLogin/backendLogin.jsp">© 2021 GoCamping, Inc</a></span>
			</div>

			<ul class="nav col-md-4 justify-content-end list-unstyled d-flex">
				<li class="ms-3"><a class="text-muted" href="#"><svg
							class="bi" width="24" height="24">
							<use xlink:href="#twitter"></use></svg></a></li>
				<li class="ms-3"><a class="text-muted" href="#"><svg
							class="bi" width="24" height="24">
							<use xlink:href="#instagram"></use></svg></a></li>
				<li class="ms-3"><a class="text-muted" href="#"><svg
							class="bi" width="24" height="24">
							<use xlink:href="#facebook"></use></svg></a></li>
			</ul>
		</footer>
	</div>
	<%@ include file="/template/script.html"%>
	<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
	<script src="./js/flatpickr.js"></script>
</body>

</html>