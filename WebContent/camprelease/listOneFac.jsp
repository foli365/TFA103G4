<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.facilities.model.*"%>

<%
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0);

	FacilitiesVO facilitiesVO = (FacilitiesVO) request.getAttribute("facilitiesVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>顯示一個設施資料</title>
<link rel='stylesheet' href='<%=request.getContextPath()%>/camprelease/css/jquery.dataTables.min.css' />
<link rel="stylesheet" href="<%=request.getContextPath()%>/camprelease/css/bootstrap.min5.1.0.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/camprelease/css/icon.css">
</head>
<style>
body {
	background-color: #FFEEE1;
	background-position: center;
	background-size: cover;
	font-family: sans-serif;
	margin-top: 40px;
	font-family: arial;
	margin: 30px;
}

.header {
	text-align: center;
}

.setting-label {
	position: relative;
	display: inline-block;
	line-height: 1em;
	overflow: hidden;
	margin: 0 5px 5px 0;
	cursor: pointer;
}

.setting-label>input {
	position: absolute;
	top: -20px;
	left: -20px;
}

.setting-label>span {
	position: relative;
	display: block;
	padding: 10px 12px 10px 10px;
	color: #000;
	font-weight: 500;
	background-color: lightgray;
	white-space: nowrap;
	border-radius: 2em;
	-webkit-border-radius: 2em;
	-moz-border-radius: 2em;
}

.setting-label>span>i {
	opacity: 1;
}

.setting-label:hover>span {
	color: #fff;
	background-color: #F4A249;
}

.setting-label:hover>span.male {
	background-color: #F4A249;
}

.setting-label input:checked+span {
	background-color: #f23557;
	color: white;
}
</style>
<body>
<header class="header" >
  <table id="table-1">
		 <a href="<%=request.getContextPath()%>/camprelease/Select_Page.jsp">back home</a>
</table>
</header>

	<header class="header">
		<h1 class="header__title">顯示一筆資料</h1>
		<br>
		<table id="table-1">
			<h4>
				<a href="<%=request.getContextPath()%>/camprelease/Select_Page.jsp">
				<img src="camprelease/images/gocamping.jpg" width="500" height="125"
					border="0"><br>back home</a>
			</h4>
		</table>
	</header>

	<table id="example" class="display nowrap" style="width: 100%">
		<thead>
			<tr>
				<th>設施編號</th>
				<th>營地編號</th>
				<th>烤肉區</th>
				<th>WIFI網路</th>
				<th>禁菸</th>
				<th>寵物</th>
				<th>設施修改</th>
				<th>刪除</th>
			</tr>
		</thead>
		<tbody>
			<jsp:useBean id="facilitiesSvc" scope="page" class="com.facilities.model.FacilitiesService" />
			<tr>
				<td>【${facilitiesVO.facilitiesId}】</td>
				<td>【${facilitiesVO.campId}】</td>
				
				<td><label class="setting-label" for="setting[]">
				<input type="checkbox" name="bbq" id="bbq" value="1"${(facilitiesVO.bbq == 0) ? '1' : 'checked' } disabled="disabled">
				<span class="material-icons md-18">outdoor_grill</span></label></td>
				
				<td><label class="setting-label" for="setting[]">
				<input type="checkbox" name="wifi" id="wifi" value="1"${(facilitiesVO.wifi == 0) ? '1' : 'checked' } disabled="disabled">
				<span class="material-icons md-18">wifi</span></label></td>
				
				<td><label class="setting-label" for="setting[]">
				<input type="checkbox" name="nosmoke" id="nosmoke" value="1"${(facilitiesVO.nosmoke == 0) ? '1' : 'checked' } disabled="disabled">
				<span class="material-icons md-18">smoke_free</span></label></td>
				
				<td><label class="setting-label" for="setting[]">
				<input type="checkbox" name="pets" id="pets" value="1"${(facilitiesVO.pets == 0) ? '1' : 'checked' }  disabled="disabled">
				<span class="material-icons md-18">pets</span></label></td>
				
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/facilities/facilities.do" style="margin-bottom: 0px;">
						<input type="submit" value="設施修改"> 
						<input type="hidden" name="facilitiesId" value="${facilitiesVO.facilitiesId}"> 
						<input type="hidden" name="campId" value="${facilitiesVO.campId}"> 
						<input type="hidden" name="action" value="getOneFacilities_For_Update">
					</FORM>
				</td>

				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/facilities/facilities.do" style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden" name="facilitiesId" value="${facilitiesVO.facilitiesId}"> 
						<input type="hidden" name="action" value="delete_facilities">
					</FORM>
				</td>
			</tr>
		</tbody>
	</table>

	<script
		src="<%=request.getContextPath()%>/camprelease/js/jquery_3.5.1.js"></script>
	<script
		src="<%=request.getContextPath()%>/camprelease/js/jquery.dataTables.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#example').DataTable({
				"scrollX" : true,
				"searching" : false, //搜尋功能, 預設是開啟
				"paging" : false, //分頁功能, 預設是開啟
				"ordering" : false, //排序功能, 預設是開啟
				"lengthMenu" : false,
				// "aria": {
				//     "sortAscending": ": 升冪排列",
				//     "sortDescending": ": 降冪排列"
				// }
				"language" : {
					"processing" : "處理中...",
					"loadingRecords" : "載入中...",
					"lengthMenu" : "顯示 _MENU_ 項結果",
					"zeroRecords" : "沒有符合的結果",
					"info" : "顯示第 _START_ 至 _END_ 項結果，共 _TOTAL_ 項",
					"infoEmpty" : "顯示第 0 至 0 項結果，共 0 項",
					"infoFiltered" : "(從 _MAX_ 項結果中過濾)",
					"infoPostFix" : "",
					"search" : "搜尋:",
					"paginate" : {
						"first" : "第一頁",
						"previous" : "上一頁",
						"next" : "下一頁",
						"last" : "最後一頁"
					},
				}
			});
		});
	</script>
</body>
</html>