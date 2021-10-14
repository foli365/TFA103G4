	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.campAlert.model.*"%>
<%@ page import="java.util.*"%>
<%
	CampAlertDAO dao = new CampAlertDAO();
	List<CampAlertVO> list = dao.getALL();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
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
                <h1>��O�޲z</h1>
            </div>
            <ul>
                   <li>
                    <a href="#" class="feat-btn">�b���޲z
                        <span class="fas fa-caret-down first"></span>
                    </a>
                    <ul class="feat-show">
                        <li><a href="#" class="member_list">�|���b���޲z</a></li>
                    </ul>
                     <ul class="feat-show">
                        <li><a href="#" class="manager_list">�޲z���b���޲z</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="serv-btn">�ӫ~�޲z
                        <span class="fas fa-caret-down second"></span> 
                    </a>
                    <ul class="serv-show">
                        <li><a href="#" class="product_up">�ӫ~�W�[</a></li>
                        <li><a href="#" class="product_list">�ӫ~��ƪ�</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#1" class="bom-btn">��a�޲z
                        <span class="fas fa-caret-down second_1"></span> 
                        </a>

                    <ul class="bom-show">
                        <li><a href="#" class="camp_list">��a�q��</a></li>
                        <li><a href="#" class="alert_managament">���|�޲z</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="mky-btn">�ӫ��޲z
                        <span class="fas fa-caret-down second_2"></span> 
                    </a>
                    <ul class="mky-show">
                        <li><a href="#" class="shopping_list">�ӫ��q��</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
    <div class="rightside">
        <h2>���|�޲z</h2>
        <h3>��a��m�G</h3>
        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
             �п��
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                <li><a class="dropdown-item" href="#">�_��</a></li>
                <li><a class="dropdown-item" href="#">����</a></li>
                <li><a class="dropdown-item" href="#">�n��</a></li>
                <li><a class="dropdown-item" href="#">�F��</a></li>
            </ul>
        </div>
        <div class="searcher">
            <form action="" class="parent">
                <input type="text" class="search" placeholder="��a���|�d��">
                <input type="button" name="" id="" class="btn_search">
            </form>

            <button type="button" class="btn btn-outline-success">�d��</button>
        </div>
        <table class="rwd-table ">
            <tr>
                <th>���|�y����</th>
                <th>���|�H</th>
                <th>��a�s��</th>
                <th>���|�ɶ�</th>
                <th>���|���e</th>
                <th>���|�Ϥ�1</th>
                <th>���|�Ϥ�2</th>
                <th>���|�Ϥ�3</th>
                <th>���|���A</th>
                 <th>�ӿ�H</th>
                <th>�s��</th>
                <th>�R��</th>
            </tr>
            <%@ include file="page1.file" %> 
            	<c:forEach var="VO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr>	
		
			<td>${VO.alertId}</td>
			<td>${VO.memberId}</td>
			<td>${VO.campId}</td>
			<td>${VO.reportTime}</td>
			<td>${VO.content}</td>
			<td><img src="<%=request.getContextPath()%>/backendLogin/CampAlert.do?Id=${VO.alertId}&img=1"></td>
			<td><img src="<%=request.getContextPath()%>/backendLogin/CampAlert.do?Id=${VO.alertId}&img=2"></td>
			<td><img src="<%=request.getContextPath()%>/backendLogin/CampAlert.do?Id=${VO.alertId}&img=3"></td>
			<td>${VO.reportStatus}</td>
			<td>${VO.handeler}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backendLogin" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="alertId"  value="${VO.alertId}">
			     <input type="hidden" name="action"	value="update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backendLogin/CampAlert.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
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