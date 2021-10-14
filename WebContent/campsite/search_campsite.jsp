<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.campsite.model.*"%>

<%
	List<CampsiteVO> campsiteList = (List<CampsiteVO>) request.getAttribute("campsiteList"); //CampsiteServlet.java(Concroller), 存入req的List<CampsiteVO>物件
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title></title>

<!-- Bootstrap 的 CSS -->
<link rel="stylesheet" href="./vendors/bootstrap/css/bootstrap.min.css">
<!-- 日期選擇器 的 CSS & JS -->
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<!-- 載入 Font Awesome -->
<script src="https://kit.fontawesome.com/846e361093.js"
	crossorigin="anonymous"></script>
<!-- 載入 CSS & JS -->
<script src="./vendors/bootstrap/js/SearchCamp.js"></script>
<link rel="stylesheet" href="./vendors/bootstrap/css/SearchCamp.css">
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

	<div class="container">
		<div class="row">
			<form method="post" action="campsite.do">
				<div class="col-12 search-bar" style="background-color: #fbefe7">
					<div class="row">
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<ul>
								<%-- 								<c:forEach var="message" items="${errorMsgs}"> --%>
								<%-- 									<li style="color: red; font-weight: bold;">${message}</li> --%>
								<%-- 								</c:forEach> --%>
								<script>
									alert(`
										<c:forEach var="message" items="${errorMsgs}">
											${message}\r
										</c:forEach>
									`.replaceAll('\t', '').replaceAll('\n', ''))
								</script>
							</ul>
						</c:if>
						<div class="col-4">
							<!-- Actual search box -->
							<div class="form-group has-search">
								<p class="fw-bold">目的地?</p>
								<i class="fas fa-location-arrow form-control-feedback"></i> <input
									type="text" name="campName" class="form-control has-icon"
									placeholder="請輸入營地...">
							</div>
						</div>
						<div class="col-3">
							<div class="form-group has-search">
								<p class="fw-bold">選擇日期</p>
								<i class="fas fa-calendar-alt form-control-feedback"></i> <input
									type="text" id="date" class="form-control choose-date has-icon"
									name="datefilter" value="" placeholder="請選擇日期..." />
							</div>
						</div>
						<div class="col-2">
							<div class="form-group has-search">
								<p class="fw-bold">顧客人數</p>
								<i class="fas fa-user-friends form-control-feedback"></i> <input
									type="text" class="form-control has-icon"
									placeholder="請輸入人數...">
							</div>
						</div>
						<div class="col-2">
							<div class="has-search">
								<p class="fw-bold">價格範圍</p>
								<i class="fas fa-wallet form-control-feedback"></i> <select
									class="form-select has-icon"
									aria-label="Default select example">
									<option selected>請選擇價格</option>
									<option value="1">1000~2000</option>
									<option value="2">2001~3000</option>
									<option value="3">3001~4000</option>
								</select>
							</div>
						</div>
						<input type="hidden" name="action" value="getSearchCampsite">
						<div>
							<button type="submit" class="search-icon">
								<i class="fas fa-search"></i>
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="row row-2">
			<div class="col-5 map">
				<div id="map"></div>

				<!-- Async script executes immediately and must be after any DOM elements used in callback. -->
				<script
					src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB41DRUbKWJHPxaFjMAwdrzWzbVKartNGg&callback=initMap&libraries=&v=weekly&channel=2"
					async></script>
			</div>
			<div class="col-7 camp-list">
				<div class="list-group">
					<c:forEach var="campsiteVO" items="${campsiteList}">
						<a href="#"
							class="list-group-item list-group-item-action flex-column align-items-start">
							<div class="row">
								<div class="col-6">
									<div class="d-flex w-100 justify-content-between camp-name">
										<h5>${campsiteVO.campName}</h5>
									</div>
									<div class="camp-addr">
										<p>地址: ${campsiteVO.location}</p>
									</div>
									<div class="camp-content">
										<p>${campsiteVO.campDescription}</p>
									</div>
								</div>
								<div class="col-6">
									<img
										src="<%=request.getContextPath()%>/CampsiteGifReader?column=picture1&camp_id=${campsiteVO.campId}"
										class="rounded float-right camp-picture" alt="...">
								</div>
							</div>
						</a>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>

	<!-- body 結束標籤之前，載入Bootstrap 的 JS -->
	<script src="./vendors/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>

</html>