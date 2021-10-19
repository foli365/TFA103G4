<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.adminList.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="java.util.*"%>
<%
	MembersVO membersVO =(MembersVO) request.getAttribute("membersVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta>
<title>Insert title here</title>
</head>
<body>
	<div class="logo">
		<a href="#"> <img src="../images/200x1280.png"
			style="width: 100%; height: 200px;">
		</a>
	</div>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/backendLogin/updatemember.do"
		name="form1">
		<table>
			<tr>
				<td>會員編號:<font color=red><b>*</b></font></td>
				<td><%=membersVO.getMemberId() %></td>
			</tr>
			<tr>
				<td>會員姓名:</td>
				<td><%=membersVO.getName() %></td>
			</tr>
			<tr>
				<td>會員信箱:</td>
				<td><input type="TEXT" name="adminName" size="45"
					value="<%=membersVO.getEmail()%>" /></td>
						
			</tr>
			<tr>
				<td>會員地址:</td>
				<td><input type="TEXT" name="adminName" size="45"
					value="<%=membersVO.getAddress() %>" /></td>
						
			</tr>
			<tr>
				<td>會員連絡電話:</td>
				<td><input type="TEXT" name="adminName" size="45"
					value="<%=membersVO.getPhone() %>" /></td>
						
			</tr>
			<tr>
				<td>會員身分:</td>
				<td><input type="TEXT" name="adminName" size="45"
					value="<%=membersVO.getMemberStatus() %>" /></td>
						
			</tr>
		</table>
		<br> <input type="hidden" name="id"
			value="<%=membersVO.getMemberId()%>"> <input type="hidden"
			name="action" value="update"> <input type="submit"
			value="送出新修改">
	</FORM>
</body>
<script src="../js/jquery.js"></script>
<script>
$(function(){
    $("#pwd2").blur(function(){
        if($("#pwd1").val()!=$("#pwd2").val()){

            alert("两次输入的密码不一致");

        }
    })
     

})
</script>
</body>
</html>