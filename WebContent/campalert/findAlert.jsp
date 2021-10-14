<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.campAlert.model.*"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<style>
  table{
 	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
   table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 10px;
    text-align: center;
  }
  img{
  width:100px;
  height:100px;	
  }
  
</style>
</head>
<body>
<%
	CampAlertDAO dao = new CampAlertDAO();
	 List<CampAlertVO> list = dao.getALL();
	  pageContext.setAttribute("list",list);
%>
<table>
	<tr>
		<th>���|�y����</th>
		<th>���|�H</th>
		<th>��a�s��</th>
		<th>���|�ɶ�</th>
		<th>���|���e</th>
		<th>�Ϥ�1</th>
		<th>�Ϥ�2</th>
		<th>�Ϥ�3</th>
		<th>���|���A</th>
		<th>�ӿ�H�s��</th>
		<th>�s��</th>
		<th>�R��</th>
		
	</tr>
	
		<c:forEach var="message" items="${list}">
			<tr>	
		
			<td>${message.alertId}</td>
			<td>${message.memberId}</td>
			<td>${message.campId}</td>
			<td>${message.report_Time}</td>
			<td>${message.content}</td>
			<td><img src="CampAlertServlet?id=${message.alert_Id}&img=1"></td>
			<td><img src="CampAlertServlet?id=${message.alert_Id}&img=2"></td>
			<td><img src="CampAlertServlet?id=${message.alert_Id}&img=3"></td>
			<td>${message.report_Status}</td>
			<td>${message.handeler}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backendLogin" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="alertId"  value="${empVO.empno}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backendLogin" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="alertId"  value="${empVO.empno}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			
			</tr>
		</c:forEach>	
		
	</table>	


</body>
</html>