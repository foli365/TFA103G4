<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.Product.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO"); 

%>

<html>
<head>
<meta charset="BIG5">
<title>�ӫ~���</title>

<style>
  table#table-3{
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-3 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  img{
  	width: 100px;
  	height: 100px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-3">
	<tr><td>
		 <h3>�ӫ~���</h3>
		 <h4><a href='selectAll.jsp'>�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�ӫ~�s��</th>
		<th>�ӫ~�W��</th>
		<th>����</th>
		<th>�ƶq</th>
		<th>�ӫ~����</th>
		<th>�ӫ~�Ϥ�1</th>
		<th>�ӫ~�Ϥ�2</th>
		<th>�ӫ~�Ϥ�3</th>
	</tr>
	<tr>
		<td><%= productVO.getProductno()%></td>
		<td><%= productVO.getPname()%></td>
		<td><%= productVO.getPrice()%></td>
		<td><%= productVO.getInventory()%></td>
		<td><%= productVO.getDescript()%></td>
		<td><img src="<%=request.getContextPath() %>/PhotoServlet?id=<%= productVO.getProductno()%>&img=1" >
		<td><img src="<%=request.getContextPath() %>/PhotoServlet?id=<%= productVO.getProductno()%>&img=2" >
		<td><img src="<%=request.getContextPath() %>/PhotoServlet?id=<%= productVO.getProductno()%>&img=3" >
	</tr>
</table>

</body>
</html>