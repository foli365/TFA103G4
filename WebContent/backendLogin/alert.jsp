<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.campAlert.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.campsite.model.*"%>
<%@ page import="com.adminList.model.*"%>
<%@ page import="java.util.*"%>
<%
	CampAlertDAO dao = new CampAlertDAO();
	List<CampAlertVO> list = dao.getALL();
	pageContext.setAttribute("list", list);
%>
<%
	MemberService memSvc = new MemberService();
	pageContext.setAttribute("memSvc", memSvc);
%>
<%
	CampsiteService campsiteService = new CampsiteService();
	pageContext.setAttribute("campsiteSvc", campsiteService);
%>
<%
	AdminService adminSvc = new AdminService();
	pageContext.setAttribute("adminSvc", adminSvc);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="../css/alert.css">
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
						<li><a
							href="http://localhost:8081/git/product/PushProduct.jsp"
							class="product_up">商品上架</a></li>
						<li><a href="http://localhost:8081/git/product/selectAll.jsp"
							class="product_list">商品資料表</a></li>
					</ul></li>
				<li><a href="#1" class="bom-btn">營地管理 <span
						class="fas fa-caret-down second_1"></span>
				</a>
					<ul class="bom-show">
						<li><a href="#" class="camp_list">營地列表</a></li>
						<li><a href='campOrder.jsp' class="camp_order">營地訂單</a></li>
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
		<h2>檢舉管理</h2>
		<br>
		<div class="searcher">
		<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/backendLogin/CampAlert.do">
				<input type="text" class="search" name="alertId" placeholder="營地檢舉查詢">
				<input type="hidden" name="action" id="" class="btn_search"
					value="getOne_For_Display">
				<button type="submit" class="btn btn-outline-success">查詢</button>
				<button type="button" class="btn btn-outline-success" id="export4">匯出</button>
			</Form>
		</div>
		<table class="rwd-table " id="myTable">
			<tr>
				<th>檢舉流水號</th>
				<th>檢舉人</th>
				<th>營地名稱</th>
				<th>檢舉時間</th>
				<th>檢舉內容</th>
				<th>檢舉圖片1</th>
				<th>檢舉圖片2</th>
				<th>檢舉圖片3</th>
				<th>檢舉狀態</th>
				<th>承辦人</th>
				<th>同意</th>
				<th>拒絕</th>
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="VO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<tr>
					<td>${VO.alertId}</td>
					<td>${memSvc.findByPrimaryKey(VO.memberId).name}</td>
					<td>${campsiteSvc.getOneCampsite(VO.campId).campName}</td>
					<td>${VO.reportTime}</td>
					<td>${VO.content}</td>
					<td><img
						src="<%=request.getContextPath()%>/backendLogin/CampAlert.do?Id=${VO.alertId}&img=1"></td>
					<td><img
						src="<%=request.getContextPath()%>/backendLogin/CampAlert.do?Id=${VO.alertId}&img=2"></td>
					<td><img
						src="<%=request.getContextPath()%>/backendLogin/CampAlert.do?Id=${VO.alertId}&img=3"></td>
					<td>${VO.reportStatus == 1 ? "檢舉成功":"未審核"}</td>
					<td>${adminSvc.getOneAdminList(VO.handeler).adminName}</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/backendLogin/camplistone.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="同意" class="btn btn-primary"> <input type="hidden"
								name="campId" value="${VO.campId}"> <input type="hidden"
								name="alertId" value="${VO.alertId}"> <input
								type="hidden" name="action" value="addOne">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/backendLogin/CampAlert.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="拒絕" class="btn btn-danger"> <input type="hidden"
								name="Id" value="${VO.alertId}"> <input type="hidden"
								name="action" value="delete">
						</FORM>
					</td>

				</tr>
			</c:forEach>

		</table>
		<%@ include file="page2.file"%>

	</div>
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="../js/jquery.table2excel.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="../js/alert.js"></script>
	<script>
		$(document).ready(function() {
			$("#export4").on("click", function() {
				$("#myTable").table2excel({
					// exclude CSS class
					exclude : ".noExl",
					name : "Worksheet Name",
					filename : "營地檢舉列表", //do not include extension
					fileext : ".xls" // file extension
				});
			});
		});
	</script>
</body>
</html>