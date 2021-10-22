<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="productSvc" scope="page"
	class="com.Product.model.ProductService" />

<html>
<head>

<title>購物商城</title>

<!-- Bootstrap 的 CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<!-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css"> -->

<!-- 商城 的 CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/EShop.css">
<!-- 商購物車的 CSS -->
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ShoppingCart.css"> --%>


</head>

<body>

	<div>

		<nav class="shopnav">
			<a href="#" class="camppage">回到露營網站首頁</a> <a
				href="<%=request.getContextPath()%>/eshop/pages/Cart.jsp"
				class="shoppingcart">查看購物車</a>
		</nav>

		<div class="top_main">

			<div class="category">
				<ul class="list">
					<li class="item1"><a href="#">帳篷</a></li>
					<li class="item2"><a href="#">餐具123</a></li>
					<li class="item3"><a href="#">睡眠</a></li>
					<li class="item4"><a href="#">桌椅</a></li>
				</ul>
			</div>

			<div class="slideshow">
				<!-- 				這邊放入輪播功能<br> 這邊放入輪播功能<br> 這邊放入輪播功能 -->
				<img src="<%=request.getContextPath()%>/eshop/imgs/welcom2.jpg"
					Style="width: 100%">
			</div>

		</div>
	</div>

	<div class="middle_countainer">
		<h1>熱門商品</h1>
	</div>

	<!-- 以下是格線系統+card的區塊 -->
	<div class="container">

		<div class="row">

			<%
				for (int i = 1; i <= 3; i++) {
			%>

			<c:forEach var="productVO" items="${productSvc.all}">
				<div class="col">
					<form name="shoppingForm"
						action="
						<%=request.getContextPath()%>/eshop/Shopping.html"
						method="POST">
						<div class="card" style="width: 18rem;">
							<img
								src="<%=request.getContextPath()%>/eshop/PictureServlet?id=${productVO.productno}">
							<!-- 							<img -->
							<%-- 								src="<%=request.getContextPath()%>/PhotoServlet?id=${productVO.productno}&img=1"> --%>
							<!-- 							<img -->
							<%-- 								src="<%=request.getContextPath()%>/PhotoServlet?id=${productVO.productno}&img=2"> --%>
							<!-- 							<img -->
							<%-- 								src="<%=request.getContextPath()%>/PhotoServlet?id=${productVO.productno}&img=3"> --%>
							<div class="card-body">
								<h5 class="card-title">${productVO.pname}</h5>
								<p class="card-text">
									<span>NT: ${productVO.price}</span>
								</p>
								<div align="center">
									數量：<input type="text" name="quantity" size="3" value=1>
								</div>
								<br>
								<div align="center">
									<input type="submit" class="btn btn-primary" value="放入購物車">
									<input type="hidden" name="name" value="${productVO.pname}">
									<input type="hidden" name="price" value="${productVO.price}">
									<input type="hidden" name="pic"
										value="<%=request.getContextPath()%>/eshop/PictureServlet?id=${productVO.productno}">
									<input type="hidden" name="action" value="ADD">
								</div>
							</div>
						</div>
					</form>
				</div>
			</c:forEach>
			<%
				}
			%>

		</div>
	</div>

	<!-- 	<p> -->
	<%-- 		<jsp:include page="/eshop/pages/Cart.jsp" flush="true" /> --%>


	<!-- 載入Bootstrap 的 JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
		crossorigin="anonymous"></script>
	<!-- <script src="/bootstrap.bundle.min.js"></script> -->
</body>
</html>