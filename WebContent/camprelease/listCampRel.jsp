<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.camprelease.model.CampReleaseDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.camprelease.model.*"%>
<%@ page import="com.facilities.model.*"%>
<%@ page import="com.plan.model.*"%>

<%
// CampReleaseDAO dao = new CampReleaseDAO();
//     List<CampReleaseVO> list = dao.getAll();
//     pageContext.setAttribute("list",list);
    
    CampReleaseService campreleaseSvc = new CampReleaseService();
    List<CampReleaseVO> list = campreleaseSvc.getAll();
    pageContext.setAttribute("list",list);
    
    FacilitiesVO facilitiesVO = (FacilitiesVO) request.getAttribute("facilitiesVO");
%>


<%-- <jsp:useBean id="planSvc" scope="page" class="com.plan.model.PlanService" /> --%>
<%-- <jsp:useBean id="CampreleaseSvc" scope="page" class="com.camprelease.model.CampReleaseService" /> --%>
<%-- <jsp:useBean id="FacilitiesSvc" scope="page" class="com.facilities.model.FacilitiesService" /> --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>All營地資料</title>
  <link rel='stylesheet' href='<%=request.getContextPath()%>/camprelease/css/jquery.dataTables.min.css' />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/camprelease/css/bootstrap.min5.1.0.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/camprelease/css/icon.css">
    <style>
        div.dataTables_wrapper {
        background-color: #99BFE6;
        width: 800px;
        margin: 0 auto;
    }
    body{
        background-color: #FFEEE1; 
    }
   
    </style>
    
</head>
    <body>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

	<nav class="navbar navbar-expand-md navbar-light sticky-top"
		style="background-color: #fbefe7">
		<div class="container-fluid">
			<a class="navbar-brand ms-lg-5" href="../Homepage/index.html"
				style="font-size: 1.25em">GoCamping</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<form class="d-flex">
					<input class="form-control me-2 rounded-pill ml-0" type="search"
						placeholder="Search" aria-label="Search" />
					<button id="searchIcon" class="btn" type="submit"
						style="padding: 0">
						<i class="bi bi-search"></i>
					</button>
				</form>
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0 me-xl-5">
					<li class="nav-item"><a id="hosting" class="nav-link" href="<%=request.getContextPath()%>/camprelease/addCampRel.jsp"
						style="color: green">上架營地</a></li>
					<li class="nav-item"><a class="nav-link" href="#"
						style="color: #e40580">商城</a></li>
					<li class="nav-item"><a class="nav-link" href="index.jsp"
						style="color: #0b83ed">論壇</a></li>
					<li class="nav-item"><a class="nav-link"
						href="../RegisterAndLogin/register.html">註冊</a></li>
					<li class="nav-item"><a class="nav-link"
						href="../RegisterAndLogin/login.html">登入</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							會員姓名 </a>
						<ul class="dropdown-menu dropdown-menu-end"
							aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item"
								href="../Account/accountCenter.html">會員中心</a></li>
							<li><a class="dropdown-item"
								href="../Account/editProfile.html">編輯會員資料</a></li>
							<li>
								<hr class="dropdown-divider" />
							</li>
							<li><a class="dropdown-item" href="#">登出</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
        
        <br>
        <br>
        <br>
        <div class="container">
<%--             <a class="btn btn-primary" href="<%=request.getContextPath() %>/camprelease/addCampRel.jsp" role="button"><image src="svg/patch-plus.svg"></image>Add</a> --%>
<table id="example" class="display nowrap" style="width:100%">
    <thead>
        <tr>
            <th>營地編號</th>
            <th>會員編號</th>
            <th>營地名稱</th>
            <th>地點</th>
            <th>經度</th>
            <th>緯度</th>
            <th>營地介紹</th>
            <th>價錢</th>
            <th>日期</th>
            <th>pic1</th>
            <th>pic2</th>
            <th>pic3</th>
            <th>pic4</th>
            <th>pic5</th>
            <th>設施</th>
            <th>營地資料修改</th>
        </tr>
    </thead>
    <jsp:useBean id="facilitiesSvc" scope="page" class="com.facilities.model.FacilitiesService" />
    <jsp:useBean id="planSvc" scope="page" class="com.plan.model.PlanService" />
    <c:forEach var="campreleaseVO"  items="${list}">
    <tbody>
        <tr>
            <td>【${campreleaseVO.campId}】</td>
			<td>【${campreleaseVO.memberId}】</td>
			<td>【${campreleaseVO.campName}】</td>
			<td>【${campreleaseVO.location}】</td>
			<td>【${campreleaseVO.latitude}】</td>
			<td>【${campreleaseVO.longtitude}】</td>
			<td>【${campreleaseVO.campDescription}】</td> 
			<td>【${campreleaseVO.campPrice}元】</td>
			<td>【<fmt:formatDate value="${campreleaseVO.listedTime}" pattern="yyyy-MM-dd HH:mm:ss"/>】</td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=1" width="100"></td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=2" width="100"></td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=3" width="100"></td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=4" width="100"></td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=5" width="100"></td>
			<td>
			</td>
            <td>
                  			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" style="margin-bottom: 0px;"> 
			     <input type="submit" value="營地資料修改">
			     <input type="hidden" name="campId"  value="${campreleaseVO.campId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
              </td>
        </tr>
    </tbody>
        </c:forEach>
    </table>
</div>








<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>所有營地資料</h3> -->
<%-- 		 <h4><a href="<%=request.getContextPath() %>/camprelease/Select_Page.jsp"><img src="images/gocamping.jpg" width="100" height="32" border="0">back home</a></h4> --%>
<!-- 	</td></tr> -->
<!-- </table> -->


<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th width="30px">營地編號</th> -->
<!-- 		<th width="30px">會員編號</th> -->
<!-- 		<th width="30px">營地名稱</th> -->
<!-- 		<th width="100px">地點</th> -->
<!-- 		<th width="80px">經度</th> -->
<!-- 		<th width="80px">緯度</th> -->
<!-- 		<th width="100px">營地介紹</th> -->
<!-- 		<th width="30px">價錢</th> -->
<!-- 		<th width="80px">日期</th> -->
<!-- 		<th width="110px">pic1</th> -->
<!-- 		<th width="110px">pic2</th> -->
<!-- 		<th width="110px">pic3</th> -->
<!-- 		<th width="110px">pic4</th> -->
<!-- 		<th width="110px">pic5</th> -->
<!-- 		<th width="40px">修改</th> -->
<!-- 		<th width="40px">刪除</th> -->
<!-- 	</tr> -->
<%-- 		<%@ include file="/camprelease/pages/page1.file" %>  --%>
<%-- 	<c:forEach var="campreleaseVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
		
<!-- 		<tr> -->
<%-- 			<td>${campreleaseVO.campId}</td> --%>
<%-- 			<td>${campreleaseVO.campName}</td> --%>
<%-- 			<td>${campreleaseVO.location}</td> --%>
<%-- 			<td>${campreleaseVO.latitude}</td> --%>
<%-- 			<td>${campreleaseVO.longtitude}</td> --%>
<%-- 			<td>${campreleaseVO.campDescription}</td>  --%>
<%-- 			<td>${campreleaseVO.campPrice}</td> --%>
<%-- 			<td><fmt:formatDate value="${campreleaseVO.listedTime}" --%>
<%-- 						pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
<%-- 			<td><img src="<%=request.getContextPath() %>/CampReleaseGifReader?column=picture1&camp_id=${campreleaseVO.campId}" class="pic"> --%>
<%-- 			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=2" width="100"> --%>
<%-- 			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=3" width="100"> --%>
<%-- 			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=4" width="100"> --%>
<%-- 			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=5" width="100"> --%>
<%-- 			<td>${campreleaseVO.memberId}</td> --%>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="campId"  value="${campreleaseVO.campId}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="campId"  value="${campreleaseVO.campId}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<%-- 	</c:forEach> --%>
<!-- </table> -->
<%-- <%@ include file="/camprelease/pages/page2.file" %> --%>


<script src="<%=request.getContextPath()%>/camprelease/js/jquery_3.3.1.slim.min.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/js/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/js/popper.min.js"></script>
<script>
       $(document).ready(function() {
    $('#example').DataTable( {
        "scrollX": true,
        "searching": false, //搜尋功能, 預設是開啟
        "paging": true, //分頁功能, 預設是開啟
        "ordering": true, //排序功能, 預設是開啟
        "lengthMenu": [3, 8],
//         "aria": {
//             "sortAscending": ": 升冪排列",
//             "sortDescending": ": 降冪排列"
//         }
        "language": {
        "processing": "處理中...",
        "loadingRecords": "載入中...",
        "lengthMenu": "顯示 _MENU_ 項結果",
        "zeroRecords": "沒有符合的結果",
        "info": "顯示第 _START_ 至 _END_ 項結果，共 _TOTAL_ 項",
        "infoEmpty": "顯示第 0 至 0 項結果，共 0 項",
        "infoFiltered": "(從 _MAX_ 項結果中過濾)",
        "infoPostFix": "",
        "search": "搜尋:",
        "paginate": {
            "first": "第一頁",
            "previous": "上一頁",
            "next": "下一頁",
            "last": "最後一頁"
        },
        "aria": {
            "sortAscending": ": 升冪排列",
            "sortDescending": ": 降冪排列"
        }
    }
    } );
} );
</script>
</body>
</html>