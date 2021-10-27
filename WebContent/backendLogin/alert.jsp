	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/alert.css">
</head>
<body>

 <div class="container">
        <nav class="sidebar">
            <div class="min_picture">
                <h1>後臺管理</h1>
            </div>
            <ul>
                   <li>
                    <a href="#" class="feat-btn">帳號管理
                        <span class="fas fa-caret-down first"></span>
                    </a>
                    <ul class="feat-show">
                        <li><a href="#" class="member_list">會員帳號管理</a></li>
                    </ul>
                     <ul class="feat-show">
                        <li><a href="#" class="manager_list">管理員帳號管理</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="serv-btn">商品管理
                        <span class="fas fa-caret-down second"></span> 
                    </a>
                    <ul class="serv-show">
                        <li><a href="http://localhost:8081/git/product/PushProduct.jsp" class="product_up">商品上架</a></li>
                        <li><a href="http://localhost:8081/git/product/selectAll.jsp" class="product_list">商品資料表</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#1" class="bom-btn">營地管理
                        <span class="fas fa-caret-down second_1"></span> 
                        </a>

                    <ul class="bom-show">
                        <li><a href="#" class="camp_list">營地列表</a></li>
                          <li><a href='campOrder.jsp' class="camp_order">營地訂單</a></li>
                        <li><a href="#" class="alert_managament">檢舉管理</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="mky-btn">商城管理
                        <span class="fas fa-caret-down second_2"></span> 
                    </a>
                    <ul class="mky-show">
                        <li><a href="#" class="shopping_list">商城訂單</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
    <div class="rightside">
        <h2>檢舉管理</h2>
        <h3>營地位置：</h3>
        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
             請選擇
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                <li><a class="dropdown-item" href="#">北部</a></li>
                <li><a class="dropdown-item" href="#">中部</a></li>
                <li><a class="dropdown-item" href="#">南部</a></li>
                <li><a class="dropdown-item" href="#">東部</a></li>
            </ul>
        </div>
        <div class="searcher">
            <form action="" class="parent">
                <input type="text" class="search" placeholder="營地檢舉查詢">
                <input type="button" name="" id="" class="btn_search">
            </form>

            <button type="button" class="btn btn-outline-success">查詢</button>
        </div>
        <table class="rwd-table ">
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
                <th>編輯</th>
                <th>刪除</th>
            </tr>
            <%@ include file="page1.file" %> 
            	<c:forEach var="VO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr>	
		
			<td>${VO.alertId}</td>
			<td>${memSvc.findByPrimaryKey(VO.memberId).name}</td>
			<td>${campsiteSvc.getOneCampsite(VO.campId).campName}</td>
			<td>${VO.reportTime}</td>
			<td>${VO.content}</td>
			<td><img src="<%=request.getContextPath()%>/backendLogin/CampAlert.do?Id=${VO.alertId}&img=1"></td>
			<td><img src="<%=request.getContextPath()%>/backendLogin/CampAlert.do?Id=${VO.alertId}&img=2"></td>
			<td><img src="<%=request.getContextPath()%>/backendLogin/CampAlert.do?Id=${VO.alertId}&img=3"></td>
			<td>${VO.reportStatus}</td>
			<td>${adminSvc.getOneAdminList(VO.handeler).adminName}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backendLogin" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="alertId"  value="${VO.alertId}">
			     <input type="hidden" name="action"	value="update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backendLogin/CampAlert.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="alertId"  value="${VO.alertId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			
			</tr>
		</c:forEach>	

        </table>
       <%@ include file="page2.file" %>
       
    </div>
     
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
    <script src="../js/alert.js"></script>
</body>
</html>