<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.* , com.eshop.model.*, com.emodr.model.*,com.members.model.*"%>

<%
// 	Integer memberId = new Integer(1); //此行new程式為測試用，正式上線時請註解這行!!!!
	Integer memberId = (Integer) session.getAttribute("id"); // 正式上線時請打開!!! 接來自loginhandler.java的session.setAttribute("id",)
	MemberService memberSvc = new MemberService();
	MembersVO memberVO = memberSvc.findByPrimaryKey(memberId);
	pageContext.setAttribute("memberVO", memberVO);
%>
<%
	EmodrVO emodrVO = (EmodrVO) request.getAttribute("emodrVO");
    
%>

<html>
<head>
<title>結帳</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/eshop/css_eshop/ShoppingCart.css">
</head>
<body>
	<font size="+3">結帳</font>
	<hr>
	<p>
	<table id="table-1" style="margin: auto;">
		<tr>
			<th width="500">商品名稱</th>
			<th width="100">價格</th>
			<th width="120">數量</th>
			<th width="120"></th>
		</tr>

		<%
			@SuppressWarnings("unchecked")
			Vector<Merchandise> buylist = (Vector<Merchandise>) session.getAttribute("shoppingcart");
			String amount = (String) request.getAttribute("amount");
			if (amount==null){
				Double emodrVOPrice = emodrVO.getTotalprice();
				amount= String.valueOf(emodrVOPrice);
			}
		%>
		<%
			for (int i = 0; i < buylist.size(); i++) {
				Merchandise order = buylist.get(i);
				String name = order.getName();
				Double price = order.getPrice();
				Integer quantity = order.getQuantity();
		%>
		<tr>
			<td width="500"><%=name%></td>
			<td width="100"><%=price%></td>
			<td width="120"><%=quantity%></td>
			<td width="120"></td>
		</tr>
		<%
			}
		%>


		<tr>
			<td colspan="6" style="text-align: right;">
			<font size="+2"; style="color:red;">總金額： $<%=amount %> </font>
			</td>
		</tr>
	</table>


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
		ACTION="<%=request.getContextPath()%>/emodr/emodr.do" name="ckform" >
		<table style="margin: auto;">
			<tr>
				<td>購買人:</td>
				<td><%=memberVO.getName()%><input type="hidden" name="member_id" value="<%=memberId%>"></td>
			</tr>
			<tr>
				<td><input type="hidden" name="emodr_date" value="2021-11-09"></td>
			</tr>
			<tr>
				<td>收貨人:</td>
				<td><input type="TEXT" name="receipient" size="45" id="receip" value="" /></td>

			</tr>
			<tr>
				<td>收貨地址:</td>
				<td><input type="TEXT" name="addr" size="45" id="address" value="" /></td>
			</tr>
			<tr>
				<td>收貨電話:</td>
				<td><input type="TEXT" name="mobile" size="45" id="mobi" value="" /></td>
			</tr>
			<tr>
				<td><input type="hidden" name="totalprice" value="<%=amount%>"></td>
			</tr>
			<tr>
				<td><input type="hidden" name="emodr_status" value="True"></td>
			</tr>

		</table> 
		<input type="button" id="quick_fill_btn" value="快速填單">
		<input type="hidden" name="action" value="insert"> 
		<input type="submit" class="buttonok" value="成立訂單" >
		
	</FORM>
		<br><br><br>
		<a href="<%=request.getContextPath()%>/eshop/pages/EShop.jsp"><font size="+1">繼 續 購 物</font></a>
				
<!-- jQuery程式區============================================================================================================== -->
	<script src="<%=request.getContextPath()%>/eshop/js_eshop/jquery.js"></script>
	
	<script>
		$(document).ready(function(){
			$("#quick_fill_btn").click(function(){
				$("#receip").val("林斯濱");
				$("#address").val("台北市中山區南京東路三段219號5樓");
				$("#mobi").val("02-27120589");				
			});	
		});		
	</script>

</body>
</html>