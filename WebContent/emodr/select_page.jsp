<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.members.model.*"%>

<html>
<head>
<title>Emodr: ����</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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

</head>
<body bgcolor='white'>
	<p>This is the Home page for Emodr: ����</p>

	<h3>��Ƭd��:</h3>
	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}}">
		<font style="color: red">��J�����~! �Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<%-- ================================================================== --%>

	<ul>
		<%-- �C�X�Ҧ��q���T�ø����listAllEmodr.jsp ��������ܩҦ��q���T --%>
		<li><a
			href='<%=request.getContextPath()%>/emodr/listAllEmodr.jsp'>List</a>
			�Ҧ��q��<br> <br></li>

		<%-- ��J�q��ߤ@��pk�ȨñN���ƭȶǤJ��@controller��servlet.java�@�ƭȮ榡���� --%>
		<li>
			<form method="post"
				action="<%=request.getContextPath()%>/emodr/emodr.do">
				<b>�п�J�q��s��(�Ҧp1):</b> <input type="text" name="emodr_id"> <input
					type="hidden" name="action" value="getOne_For_Display"> <input
					type="submit" value="�e�X">
			</form>
		</li>

		<%-- �Q��jsp��useBean+el�y�k�ӧ���A�w�����q����ߤ@��pk����ܦb�U�Ԧ����A���ϥΪ̿�ܭn�d���@���A���ƭȶǤJ��@controller��servlet.java�@�ƭȮ榡���� --%>
		<jsp:useBean id="emodrSvc" scope="page"
			class="com.emodr.model.EmodrService" />
		<li>
			<form method="post"
				action="<%=request.getContextPath()%>/emodr/emodr.do">
				<b>�п�ܭq��s��:</b> <select size="1" name="emodr_id">
					<c:forEach var="emodrVO" items="${emodrSvc.all}">
						<option value="${emodrVO.emodr_id}">${emodrVO.emodr_id}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="�e�X">
			</form>
		</li>

		<%-- �U���o�qcode���\���W���@�ˡA�t�O�b��U�Ԧ������ܪ��令�η|���W����� --%>
		<jsp:useBean id="membersSvc" scope="page"
			class="com.members.model.MemberService" />
		<li>
			<form method="post"
				action="<%=request.getContextPath()%>/emodr/emodr.do">
				<b>�п�ܦ��f�H:</b> <select size="1" name="emodr_id">
					<c:forEach var="emodrVO" items="${emodrSvc.all}">
						<option value="${emodrVO.emodr_id}">
							<c:forEach var="membersVO" items="${membersSvc.all}">
								<c:if test="${emodrVO.member_id==membersVO.memberId}">
									${membersVO.name}
							    </c:if>
							</c:forEach>
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="�e�X">
			</form>
		</li>
	</ul>
	<%-- ================================================================== --%>
	<%-- �s�W�q��\��--%>
	<h3>�q��޲z_�s�W�q��\��</h3>

	<ul>
		<li><a href='<%=request.getContextPath()%>/emodr/addEmp.jsp'>Add</a>
			�s���q��</li>
	</ul>


</body>
</html>