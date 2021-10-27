<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>單筆訂單資料 - listOneEmodr.jsp</title>

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
a:hover{
	color:red;
	font-size: 20px;
}
</style>

<style>
table {
/* 	width: 800px; */
	width: 100%;
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
	<table id="table-1">
		<tr>
			<td>
				<h3>查詢結果</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/emodr/listAllEmodr.jsp">回前一頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>訂單編號</th>
			<th>買方編號與名字</th>
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
			<td><%= (emodrVO.getEmodr_status()== true)? "成立": "不成立"%></td>
		</tr>
	</table>

</body>
</html>