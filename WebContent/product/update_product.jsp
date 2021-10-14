<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.Product.model.*"%>

<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO"); 
%>

<html>
<head>
<title>�ӫ~�ק�</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-2 h4 {
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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='burlywood'>

<table id="table-2">
	<tr><td>
		 <h3>�ӫ~��ƭק� </h3>
		 <h4><a href='selectAll.jsp'>�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="product.do" name="pform" enctype= "multipart/form-data">
<table>
	<tr>
		<td>�ӫ~�s��:<font color=red><b>*</b></font></td>
		<td><%=productVO.getProductno()%></td>
	</tr>
	<tr>
		<td>�ӫ~�W��:</td>
		<td><input type="TEXT" name="pname" size="45" value="<%=productVO.getPname()%>" /></td>
	</tr>
	
	<tr>
		<td>����:</td>
		<td><input type="TEXT" name="price" size="45" value="<%=productVO.getPrice()%>" /></td>
	</tr>
	
	<tr>
		<td>�ƶq:</td>
		<td><input type="TEXT" name="inventory" size="45" value="<%=productVO.getInventory()%>" /></td>
	</tr>
	<tr>
		<td>�ӫ~����:</td>
		<td><input type="TEXT" name="descript" size="45" value="<%=productVO.getDescript()%>" /></td>
	</tr>
	
	<tr>
		<td>�ӫ~�Ϥ�1:</td>
		<td><input type="file" name="img1"
			 value="<%= (productVO==null)? "" : productVO.getPicture1()%>" /></td>
	</tr>
	
	<tr>
		<td>�ӫ~�Ϥ�2:</td>
		<td><input type="file" name="img2"
			 value="<%= (productVO==null)? "" : productVO.getPicture2()%>" /></td>
	</tr>
	
	<tr>
		<td>�ӫ~�Ϥ�3:</td>
		<td><input type="file" name="img3"
			 value="<%= (productVO==null)? "" : productVO.getPicture3()%>" /></td>
	</tr>

	<jsp:useBean id="productSvc" scope="page" class="com.Product.model.ProductService" />
	
	<tr>
		<td>���O:<font color=red><b>*</b></font></td>
		<td><select size="1" name="psort">
			<c:forEach var="productVO" items="${productSvc.all}">
				<option value="${productVO.psort}">${productVO.psort}</option>>
			</c:forEach>
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="productno" value="<%=productVO.getProductno()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>

</html>