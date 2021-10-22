<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
 
 <title>購物商城</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ShoppingCart.css">
</head>

<body>

<hr>

<jsp:useBean id="productSvc" scope="page" class="com.Product.model.ProductService" />

<table id="table-1">
  <tr> 
    	<th>商品照片</th>
    	<th>商品名稱</th>
    	<th>商品價格</th>
    	<th>購買數量</th>
    	<th></th>
<!--     <th width="120"><img src="images/shopping-cart.png" width="45px" height="35px"></th> -->
  </tr>
</table>
 
 <!--  
       第一種action寫法: <form name="shoppingForm" action="Shopping.html" method="POST">
       第二種action寫法: <form name="shoppingForm" action="/IBM_MVC/Shopping.html" method="POST">
       第三種action寫法: <form name="shoppingForm" action="<%=request.getContextPath()%>/Shopping.html" method="POST">
 -->
 <!-- 
       當某網頁可能成為被forward的網頁時, 此網頁內的所有html連結 , 如果採用相對路徑寫法時, 因為會被加上原先forward者的路徑
       在更複雜的MVC架構中, 上面第三種寫法, 先以request.getContextPath()方法, 先取得環境(Servlet Context)目錄路徑的寫法,
       將是最佳解決方案
 -->
<c:forEach var="productVO" items="${productSvc.all}">

  <form name="shoppingForm" action="Shopping.html" method="POST">
    <table><tr> 
      <td width="200"><div align="center">${productVO.picture1}</div></td>
      <td width="100"><div align="center">${productVO.pname}</div></td>
      <td width="100"><div align="center">${productVO.price}</div></td>
      <td width="120"><div align="center">數量：<input type="text" name="quantity" size="3" value=1></div></td>
      <td width="120"><div align="center">     <input type="submit" class="button" value="放入購物車"> </div></td>
    </tr></table>
      <input type="hidden" name="name" value="不一樣的養生法">
      <input type="hidden" name="author" value="吳永志">
      <input type="hidden" name="publisher" value="基峰">
      <input type="hidden" name="price" value="600">
      <input type="hidden" name="action" value="ADD">
  </form>
</c:forEach> 
<!-- <p>  -->
<%--   <jsp:include page="<%=request.getContextPath()%>/Cart.jsp" flush="true" /> --%>
</body>
</html>