<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.campsite.model.*"%>

<%
	CampsiteVO campsiteVO = (CampsiteVO) request.getAttribute("campsiteVO"); //CampsiteServlet.java(Concroller), 存入req的campsiteVO物件
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title></title>

<!-- Bootstrap 的 CSS -->
<link rel="stylesheet" href="./vendors/bootstrap/css/bootstrap.min.css">
<!-- 載入 Font Awesome -->
<script src="https://kit.fontawesome.com/846e361093.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="./vendors/bootstrap/css/ReserveCamp.css">
</head>

<body style="background-color: #fbefe7;">
	<nav class="navbar navbar-expand-md navbar-light"
		style="background-color: #fbefe7;">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">GoCamping</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<form class="d-flex">
					<input class="form-control me-2 rounded-pill" type="search"
						placeholder="Search" aria-label="Search">
					<button id="searchIcon" class="btn" type="submit"
						style="padding: 0">
						<i class="bi bi-search"></i>
					</button>
				</form>
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item"><a id="hosting" class="nav-link" href="#"
						style="color: green;">上架營地</a></li>
					<li class="nav-item"><a class="nav-link" href="#">商城</a></li>
					<li class="nav-item"><a class="nav-link" href="#">論壇</a></li>
					<li class="nav-item"><a class="nav-link" href="#">註冊</a></li>
					<li class="nav-item"><a class="nav-link" href="#">登入</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							會員姓名 </a>
						<ul class="dropdown-menu dropdown-menu-end"
							aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="#">會員中心</a></li>
							<li><a class="dropdown-item" href="#"></a></li>
							<li>
								<hr class="dropdown-divider">
							</li>
							<li><a class="dropdown-item" href="#">登出</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container text-center my-3">
		<div class="row mx-auto my-auto justify-content-center">
			<div id="recipeCarousel" class="carousel slide"
				data-bs-ride="carousel">
				<div class="carousel-inner" role="listbox">
					<div class="carousel-item active">
						<div class="col-md-3">
							<div class="card">
								<div class="card-img">
									<img src="//via.placeholder.com/500x400/31f?text=1"
										class="img-fluid">
								</div>
								<div class="card-img-overlay">Slide 1</div>
							</div>
						</div>
					</div>
					<div class="carousel-item">
						<div class="col-md-3">
							<div class="card">
								<div class="card-img">
									<img src="//via.placeholder.com/500x400/e66?text=2"
										class="img-fluid">
								</div>
								<div class="card-img-overlay">Slide 2</div>
							</div>
						</div>
					</div>
					<div class="carousel-item">
						<div class="col-md-3">
							<div class="card">
								<div class="card-img">
									<img src="//via.placeholder.com/500x400/7d2?text=3"
										class="img-fluid">
								</div>
								<div class="card-img-overlay">Slide 3</div>
							</div>
						</div>
					</div>
					<div class="carousel-item">
						<div class="col-md-3">
							<div class="card">
								<div class="card-img">
									<img src="//via.placeholder.com/500x400?text=4"
										class="img-fluid">
								</div>
								<div class="card-img-overlay">Slide 4</div>
							</div>
						</div>
					</div>
					<div class="carousel-item">
						<div class="col-md-3">
							<div class="card">
								<div class="card-img">
									<img src="//via.placeholder.com/500x400/aba?text=5"
										class="img-fluid">
								</div>
								<div class="card-img-overlay">Slide 5</div>
							</div>
						</div>
					</div>
				</div>
				<a class="carousel-control-prev bg-transparent w-aut"
					href="#recipeCarousel" role="button" data-bs-slide="prev"> <span
					class="carousel-control-prev-icon" aria-hidden="true"></span>
				</a> <a class="carousel-control-next bg-transparent w-aut"
					href="#recipeCarousel" role="button" data-bs-slide="next"> <span
					class="carousel-control-next-icon" aria-hidden="true"></span>
				</a>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-8 camp-content">
				<div class="camp-title">
					<h2 class="camp-name ">${campsiteVO.campName}</h2>
					<p class="addr ">地址</p>
					<p class="cel ">電話</p>
				</div>
				<div class="camp-detail">
					<p>營地內容</p>
				</div>
				<div class="camp-comment">
					<p>營地評論</p>
				</div>
			</div>
			<div class="col-4 order-menu">
				<form action="">
					<div class="row">
						<div class="col ">
							<p>平均每晚一人</p>
						</div>
					</div>
					<div class="row">
						<div class="col-6 ">
							<p>入住日期</p>
						</div>
						<div class="col-6 ">
							<p>退房日期</p>
						</div>
					</div>
					<div class="row">
						<div class="col ">
							<p>人數</p>
						</div>
					</div>
					<div class="row">
						<div class="col ">
							<p>是否要配套活動</p>
						</div>
					</div>
					<div class="row">
						<div class="col ">
							<p>Total</p>
						</div>
					</div>
					<div class="row">
						<div class="col ">
							<button type="submit">送出訂單</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- body 結束標籤之前，載入Bootstrap 的 JS -->
	<script src="./vendors/bootstrap/js/bootstrap.bundle.min.js "></script>
	<!-- body 結束標籤之前，載入Slider 的 JS -->
	<script src="./vendors/bootstrap/js/ReserveCamp.js "></script>
</body>

</html>