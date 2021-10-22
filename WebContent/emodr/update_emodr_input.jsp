<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emodr.model.*"%>

<%
  EmodrVO emodrVO = (EmodrVO) request.getAttribute("emodrVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>訂單資料修改 - update_emodr_input.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
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
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>訂單資料修改 - update_emodr_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="emodr.do" name="form1">
<table>
	<tr>
		<td>訂單編號:<font color=red><b>*</b></font></td>
		<td><%=emodrVO.getEmodr_id()%></td>
	</tr>
	
	<jsp:useBean id="membersSvcrex" scope="page" class="com.members.model.MemberService" />
	<tr>
		<td>買方會員:<font color=red><b>*</b></font></td>
		<td><select size="1" name="member_id">
			<c:forEach var="membersVO" items="${membersSvcrex.all}">
				<option value="${membersVO.memberId}" ${(emodrVO.member_id==membersVO.memberId)?'selected':'' } >${membersVO.name}
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>訂單日期:</td>
		<td><input type="TEXT" name="emodr_date" size="45"	value="<%=emodrVO.getEmodr_date()%>" /></td>
	</tr>
	<tr>
		<td>收貨人:</td>
		<td><input type="TEXT" name="receipient" size="45"	value="<%=emodrVO.getReceipient()%>" /></td>
	</tr>
	<tr>
		<td>收貨地址:</td>
		<td><input type="TEXT" name="addr" size="45"	value="<%=emodrVO.getAddr()%>" /></td>
	</tr>
	<tr>
		<td>收貨電話:</td>
		<td><input type="TEXT" name="mobile" size="45" value="<%=emodrVO.getMobile()%>" /></td>
	</tr>
	<tr>
		<td>總價:</td>
		<td><input type="TEXT" name="totalprice" size="45" value="<%=emodrVO.getTotalprice()%>" /></td>
	</tr>
	<tr>
		<td>訂單狀態:</td>
		<td><input type="TEXT" name="emodr_status" size="45" value="<%=emodrVO.getEmodr_status()%>" /></td>
	</tr>



</table>
<br>
<input type="hidden" name="action" value="update">  <!-- 送出update字串讓servlet去接，告訴servlet要做的事是update -->
<input type="hidden" name="emodr_id" value="<%=emodrVO.getEmodr_id()%>"> <!-- 送出<%=emodrVO.getEmodr_id()%>讓servlet去接，告訴servlet emodr_id字串-->
<input type="submit" value="送出修改">
</FORM>
</body>

</html>