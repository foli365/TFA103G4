<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.admin.model.*"%>
<%@ page import="java.util.*"%>
<%
	AdminListVO VO = (AdminListVO) request.getAttribute("adminListVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查詢單筆管理員</title>
<script src="../js/jquery.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<link rel="stylesheet" href="../css/manager.css">
</head>
<body>
	<div class="container">
		<nav class="sidebar">
			<div class="min_picture">
				<h1>後臺管理</h1>
			</div>
			<ul>
				<li><a href="#" class="feat-btn">帳號管理 <span
						class="fas fa-caret-down first"></span>
				</a>
					<ul class="feat-show">
						<li><a href="#" class="member_list">會員帳號管理</a></li>
					</ul>
					<ul class="feat-show">
						<li><a href="#" class="manager_list">管理員帳號管理</a></li>
					</ul></li>
				<li><a href="#" class="serv-btn">商品管理 <span
						class="fas fa-caret-down second"></span>
				</a>
					<ul class="serv-show">
						<li><a href="http://localhost:8081/git/product/PushProduct.jsp" class="product_up">商品上架</a></li>
						<li><a href="http://localhost:8081/git/product/selectAll.jsp" class="product_list">商品資料表</a></li>
					</ul></li>
				<li><a href="#1" class="bom-btn">營地管理 <span
						class="fas fa-caret-down second_1"></span>
				</a>

					<ul class="bom-show">
						<li><a href="#" class="camp_list">營地訂單</a></li>
						<li><a href="#" class="alert_managament">檢舉管理</a></li>
					</ul></li>
				<li><a href="#" class="mky-btn">商城管理 <span
						class="fas fa-caret-down second_2"></span>
				</a>
					<ul class="mky-show">
						<li><a href="#" class="shopping_list">商城訂單</a></li>
					</ul></li>
			</ul>
		</nav>
	</div>
	<div class="rightside">
		<h2>管理員帳號單筆查詢</h2>
		<br>
		<h3><a href='manager.jsp'>回管理員帳號管理</a></h3>
		<div class="searcher">
			<form action="" class="parent">
				<input type="text" class="search" placeholder="管理員查詢"> <input
					type="button" name="" id="" class="btn_search">
			</form>
			<button type="submit" class="btn btn-outline-success">查詢</button>
		</div>
		<table id="myTable" class="tablesorter">
			<thead>
				<tr>
					<th>管理員編號</th>
					<th>管理員密碼</th>
					<th>姓名</th>
				</tr>
			</thead>
				<tr>

					<td><%=VO.getAdminId()%></td>
					<td><%=VO.getAdminPwd()%></td>
					<td><%=VO.getAdminName()%></td>
				</tr>
		</table>
	</div>

	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.30.5/css/theme.blue.min.css"></link>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.30.5/js/jquery.tablesorter.min.js"></script>
	<script>
		$("#myTable").tablesorter({
			theme : "blue",
			widgets : [ 'zebra' ]
		});
	</script>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
		crossorigin="anonymous"></script>
	<script src="../js/manager.js"></script>
</body>
</html>