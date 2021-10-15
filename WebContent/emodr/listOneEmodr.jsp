<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.emodr.model.*"%>
<%@ page import="com.members.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>
<%
	EmodrVO emodrVO = (EmodrVO) request.getAttribute("emodrVO");
%>

<%-- 取出 對應的membersVO物件--%>
<%
	MemberService memberSvc = new MemberService();
	MembersVO membersVO = memberSvc.findByPrimaryKey(emodrVO.getMember_id());
%>

<html>
<head>
<title>訂單資料 - listOneEmodr.jsp</title>

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
	width: 800px;
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
</style>

</head>
<body bgcolor='white'>

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>員工資料 - listOneEmodr.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/emodr/select_page.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>商城訂單編號</th>
			<th>買方會員編號</th>
			<th>訂單日期</th>
			<th>收貨人</th>
			<th>收貨地址</th>
			<th>收貨電話</th>
			<th>總價</th>
			<th>訂單狀態</th>
		</tr>
		<tr>
			<td><%=emodrVO.getEmodr_id()%></td>
			<%-- 			<td><%=emodrVO.getMember_id()%></td> --%>
			<td><%=emodrVO.getMember_id()%>【<%=membersVO.getName()%>】</td>
			<td><%=emodrVO.getEmodr_date()%></td>
			<td><%=emodrVO.getReceipient()%></td>
			<td><%=emodrVO.getAddr()%></td>
			<td><%=emodrVO.getMobile()%></td>
			<td><%=emodrVO.getTotalprice()%></td>
			<td><%=emodrVO.getEmodr_status()%></td>
		</tr>
	</table>

</body>
</html>