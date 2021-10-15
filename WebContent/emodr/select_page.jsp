<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.members.model.*"%>

<html>
<head>
<title>Emodr: 首頁</title>

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
	<p>This is the Home page for Emodr: 首頁</p>

	<h3>資料查詢:</h3>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}}">
		<font style="color: red">輸入有錯誤! 請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<%-- ================================================================== --%>

	<ul>
		<%-- 列出所有訂單資訊並跳轉到listAllEmodr.jsp 頁面來顯示所有訂單資訊 --%>
		<li><a
			href='<%=request.getContextPath()%>/emodr/listAllEmodr.jsp'>List</a>
			所有訂單<br> <br></li>

		<%-- 輸入訂單唯一鍵pk值並將此數值傳入當作controller的servlet.java作數值格式驗證 --%>
		<li>
			<form method="post"
				action="<%=request.getContextPath()%>/emodr/emodr.do">
				<b>請輸入訂單編號(例如1):</b> <input type="text" name="emodr_id"> <input
					type="hidden" name="action" value="getOne_For_Display"> <input
					type="submit" value="送出">
			</form>
		</li>

		<%-- 利用jsp的useBean+el語法來抓資瞭庫中的訂單表格唯一鍵pk值顯示在下拉式表單，讓使用者選擇要查哪一筆，此數值傳入當作controller的servlet.java作數值格式驗證 --%>
		<jsp:useBean id="emodrSvc" scope="page"
			class="com.emodr.model.EmodrService" />
		<li>
			<form method="post"
				action="<%=request.getContextPath()%>/emodr/emodr.do">
				<b>請選擇訂單編號:</b> <select size="1" name="emodr_id">
					<c:forEach var="emodrVO" items="${emodrSvc.all}">
						<option value="${emodrVO.emodr_id}">${emodrVO.emodr_id}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</form>
		</li>

		<%-- 下面這段code的功能跟上面一樣，差別在於下拉式選單顯示的改成用會員名來顯示 --%>
		<jsp:useBean id="membersSvc" scope="page"
			class="com.members.model.MemberService" />
		<li>
			<form method="post"
				action="<%=request.getContextPath()%>/emodr/emodr.do">
				<b>請選擇收貨人:</b> <select size="1" name="emodr_id">
					<c:forEach var="emodrVO" items="${emodrSvc.all}">
						<option value="${emodrVO.emodr_id}">
							<c:forEach var="membersVO" items="${membersSvc.all}">
								<c:if test="${emodrVO.member_id==membersVO.memberId}">
									${membersVO.name}
							    </c:if>
							</c:forEach>
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</form>
		</li>
	</ul>
	<%-- ================================================================== --%>
	<%-- 新增訂單功能--%>
	<h3>訂單管理_新增訂單功能</h3>

	<ul>
		<li><a href='<%=request.getContextPath()%>/emodr/addEmp.jsp'>Add</a>
			新的訂單</li>
	</ul>


</body>
</html>