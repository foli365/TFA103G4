<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.Product.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO"); 

%>

<html>
<head>
<meta charset="BIG5">
<title>商品資料</title>
	<script src="productstyle/jquery.js"></script>
	<script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <link rel="stylesheet" href="productstyle/table.css">
 <style>
    	table {
			width: 450px;
			margin-top: 1px;
			margin-bottom: 1px;
  		}
  		table, th, td {
			border: 1px solid blue;
    
  		}
  		th, td {
    		padding: 1px;
  		}
   
 </style>

<style>
	body {
	background-color: bisque;
	}
  table {
	width: 600px;
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
  img{
  	width: 100px;
  	height: 100px;
  }
</style>

</head>
<body>
 <div class="container">
        <nav class="sidebar">
            <div class="min_picture">
                <h1>後臺管理</h1>
            </div>
            <ul>
                <li>
                    <a href="#" class="feat-btn">會員管理
                        <span class="fas fa-caret-down first"></span>
                    </a>
                    <ul class="feat-show">
                        <li><a href="UserTable.jsp" class="member_list">會員資料表</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="serv-btn">商品管理
                        <span class="fas fa-caret-down second"></span> 
                    </a>
                    <ul class="serv-show">
                        <li><a href="PushProduct.jsp" class="product_up">商品上架</a></li>
                        <li><a href="#" class="product_list">商品資料表</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#1" class="bom-btn">營地管理
                        <span class="fas fa-caret-down second_1"></span> 
                        </a>

                    <ul class="bom-show">
                        <li><a href="#" class="camp_list">營地訂單</a></li>
                        <li><a href="#" class="alert_managament">檢舉管理</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="mky-btn">商城管理
                        <span class="fas fa-caret-down second_2"></span> 
                    </a>
                    <ul class="mky-show">
                        <li><a href="#" class="shopping_list">商城訂單</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
    <div class="rightside" id="rig-1">
        <h2>商品資料表</h2><br>
        <h3>商品類型：</h3>     
        <div class="dropdown">       		
            <div class="wrap">
            <div class="search">
            <FORM METHOD="post" ACTION="product.do" >
            <input class="search-bar" type="text"  name="productno" placeholder="輸入名稱">
       		<input type="hidden" name="action" value="search">        	
           <button type="submit" class="search-btn"><i class="fas fa-search"></i></button>
           </div>
        </div>
        <br>
        <br>
 
       



 <table class="rwd-table">
            <tr>
                <th>商品編號</th>
				<th>商品名稱</th>
				<th>類別</th>
				<th>價格</th>
				<th>商品數量</th>
				<th>商品介紹</th>
				<th>狀態</th>
				<th>商品圖片1</th>
				<th>商品圖片2</th>
				<th>商品圖片3</th>
				
            </tr>
	<tr>
		<td><%= productVO.getProductno()%></td>
		<td><%= productVO.getPname()%></td>
		<td><%= productVO.getPsort()%></td>
		<td><%= productVO.getPrice()%></td>
		<td><%= productVO.getInventory()%></td>
		<td><%= productVO.getDescript()%></td>
		<td><%= productVO.getSituation()%></td>
		<td><img src="<%=request.getContextPath() %>/PhotoServlet?id=<%= productVO.getProductno()%>&img=1" >
		<td><img src="<%=request.getContextPath() %>/PhotoServlet?id=<%= productVO.getProductno()%>&img=2" >
		<td><img src="<%=request.getContextPath() %>/PhotoServlet?id=<%= productVO.getProductno()%>&img=3" >
	</tr>
</table>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
<script src="productstyle/table.js"></script>
</body>
</html>