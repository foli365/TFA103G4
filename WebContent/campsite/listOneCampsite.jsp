<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.campsite.model.*"%>

<%
	CampsiteVO campsiteVO = (CampsiteVO) request.getAttribute("campsiteVO"); //CampsiteServlet.java(Concroller), �s�Jreq��campsiteVO����
%>

<html>
<head>
<title>���u��� - listOneCampsite.jsp</title>

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
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	table-layout: fixed;
	word-wrap: break-word;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}

.pic {
	width: 128px;
	height: 90.5px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>���u��� - ListOneCampsite.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th width="30px">��a�s��</th>
			<th width="30px">�|���s��</th>
			<th width="80px">��a�W��</th>
			<th width="120px">�a�}</th>
			<th width="110px">�g��</th>
			<th width="110px">�n��</th>
			<th width="120px">��a����</th>
			<th width="30px">��a����</th>
			<th width="30px">�H�ƤW��</th>
			<th width="80px">�W�Ǯɶ�</th>
			<th width="60px">��~���A</th>
			<th width="30px">���w�H��</th>
			<th width="30px">���|�H��</th>
			<th width="130px">��~����</th>
			<th width="130px">�Ӥ�1</th>
			<th width="40px">�ק�</th>
			<th width="40px">�R��</th>
		</tr>
		<tr>
			<td>${campsiteVO.campId}</td>
			<td>${campsiteVO.memberId}</td>
			<td>${campsiteVO.campName}</td>
			<td>${campsiteVO.location}</td>
			<td>${campsiteVO.latitude}</td>
			<td>${campsiteVO.longtitude}</td>
			<td>${campsiteVO.campDescription}</td>
			<td>${campsiteVO.campPrice}</td>
			<td>${campsiteVO.campLimit}</td>
			<td><fmt:formatDate value="${campsiteVO.listedTime}"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td>${campsiteVO.siteState == 1 ? "��~��":"�𮧤�"}</td>
			<td>${campsiteVO.lovedCount}</td>
			<td>${campsiteVO.reportedCount}</td>
			<td><img
				src="<%=request.getContextPath()%>/CampsiteGifReader?column=camp_license&camp_id=${campsiteVO.campId}"
				class="pic"></td>
			<td><img
				src="<%=request.getContextPath()%>/CampsiteGifReader?column=picture1&camp_id=${campsiteVO.campId}"
				class="pic"></td>
			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/campsite/campsite.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="�ק�"> <input type="hidden"
						name="campId" value="${campsiteVO.campId}"> <input
						type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/campsite/campsite.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="�R��"> <input type="hidden"
						name="campId" value="${campsiteVO.campId}"> <input
						type="hidden" name="action" value="delete">
				</FORM>
			</td>
		</tr>
	</table>

</body>
</html>