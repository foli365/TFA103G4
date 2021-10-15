<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.emodr.model.*"%>
<%@ page import="com.members.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%-- ���X Concroller EmpServlet.java�w�s�Jrequest��EmpVO����--%>
<%
	EmodrVO emodrVO = (EmodrVO) request.getAttribute("emodrVO");
%>

<%-- ���X ������membersVO����--%>
<%
	MemberService memberSvc = new MemberService();
	MembersVO membersVO = memberSvc.findByPrimaryKey(emodrVO.getMember_id());
%>

<html>
<head>
<title>�q���� - listOneEmodr.jsp</title>

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

	<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>���u��� - listOneEmodr.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/emodr/select_page.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>�ӫ��q��s��</th>
			<th>�R��|���s��</th>
			<th>�q����</th>
			<th>���f�H</th>
			<th>���f�a�}</th>
			<th>���f�q��</th>
			<th>�`��</th>
			<th>�q�檬�A</th>
		</tr>
		<tr>
			<td><%=emodrVO.getEmodr_id()%></td>
			<%-- 			<td><%=emodrVO.getMember_id()%></td> --%>
			<td><%=emodrVO.getMember_id()%>�i<%=membersVO.getName()%>�j</td>
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