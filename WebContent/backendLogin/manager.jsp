<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.adminList.model.*"%>
<%@ page import="java.util.*"%>
<%
	AdminService dao = new AdminService();
	List<AdminListVO> list = dao.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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
						<li><a href="../backendLogin/member.jsp" class="member_list">會員帳號管理</a></li>
					</ul>
					<ul class="feat-show">
						<li><a href="../backendLogin/manager.jsp" class="manager_list">管理員帳號管理</a></li>
					</ul></li>
				<li><a href="#" class="serv-btn">商品管理 <span
						class="fas fa-caret-down second"></span>
				</a>
					<ul class="serv-show">
						<li><a href="../product/PushProduct.jsp" class="product_up">商品上架</a></li>
						<li><a href="../product/selectAll.jsp" class="product_list">商品資料表</a></li>
					</ul></li>
				<li><a href="#1" class="bom-btn">營地管理 <span
						class="fas fa-caret-down second_1"></span>
				</a>

					<ul class="bom-show">
						<li><a href="#" class="camp_list">營地列表</a></li>
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
		<h2>管理員帳號管理</h2>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<br>
		<div class="searcher">
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/backendLogin/AdminServlet.do">
				<input type="text" class="search" name="adminId"
					placeholder="管理員編號查詢"> <input type="hidden" name="action"
					id="" class="btn_search" value="getOne_For_Display">
				<button type="submit" class="btn btn-outline-success">查詢</button>
				<button type="button" class="btn btn-outline-success"
					onclick="location.href='<%=request.getContextPath()%>/backendLogin/addAdmin.jsp'">新增管理員</button>
				<button type="button" class="btn btn-outline-success" id="export">匯出</button>
			</Form>


			<!-- 			<button type="button" class="btn btn-outline-success" -->
			<!-- 			onclick="location.href='http://localhost:8081/GoCamping/backendLogin/addAdmin.jsp'">新增管理員</button> -->
		</div>
		<table id="myTable" class="tablesorter">
			<thead>
				<tr>
					<th>管理員編號</th>
					<th>管理員密碼</th>
					<th>姓名</th>
					<th>編輯</th>
					<th>刪除</th>
				</tr>
			</thead>
			<%@ include file="page1.file"%>
			<c:forEach var="VO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<tr>

					<td>${VO.adminId}</td>
					<td>${VO.adminPwd}</td>
					<td>${VO.adminName}</td>

					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/backendLogin/AdminServlet.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改"> <input type="hidden"
								name="adminId" value="${VO.adminId}"> <input
								type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/backendLogin/AdminServlet.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="刪除"> <input type="hidden"
								name="adminId" value="${VO.adminId}"> <input
								type="hidden" name="action" value="delete">
						</FORM>
					</td>

				</tr>

			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	</div>
	<script src="../js/jquery.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="../js/jquery.table2excel.js"></script>
	<script src="https://kit.fontawesome.com/a076d05399.js"></script>
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
	<script>
		$(document).ready(function(){			
		$("#export").on("click", function() {
				  $("#myTable").table2excel({
				    // exclude CSS class
				    exclude: ".noExl",
				    name: "Worksheet Name",
				    filename: "管理員清單", //do not include extension
				    fileext: ".xls" // file extension
				  }); 
		});
		});
	</script>
</body>
</html>