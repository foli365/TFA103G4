<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.Product.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
 	ProductService proSvc = new ProductService();
 	List <ProductVO> list = proSvc.getAll();
 	pageContext.setAttribute("list", list);
%>

<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>�ӫ~��ƪ�</title>
    <script src="productstyle/jquery.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <link rel="stylesheet" href="productstyle/table.css">
    
    <style>
    	table {
			width: 450px;
			background-color: white;
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
</head>

<body>
    <div class="container">
        <nav class="sidebar">
            <div class="min_picture">
                <h1>��O�޲z</h1>
            </div>
            <ul>
                <li>
                    <a href="#" class="feat-btn">�|���޲z
                        <span class="fas fa-caret-down first"></span>
                    </a>
                    <ul class="feat-show">
                        <li><a href="UserTable.jsp" class="member_list">�|����ƪ�</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="serv-btn">�ӫ~�޲z
                        <span class="fas fa-caret-down second"></span> 
                    </a>
                    <ul class="serv-show">
                        <li><a href="PushProduct.jsp" class="product_up">�ӫ~�W�[</a></li>
                        <li><a href="#" class="product_list">�ӫ~��ƪ�</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#1" class="bom-btn">��a�޲z
                        <span class="fas fa-caret-down second_1"></span> 
                        </a>

                    <ul class="bom-show">
                        <li><a href="#" class="camp_list">��a�q��</a></li>
                        <li><a href="#" class="alert_managament">���|�޲z</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="mky-btn">�ӫ��޲z
                        <span class="fas fa-caret-down second_2"></span> 
                    </a>
                    <ul class="mky-show">
                        <li><a href="#" class="shopping_list">�ӫ��q��</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
    <div class="rightside" id="rig-1">
        <h2>�ӫ~��ƪ�</h2><br>
        <h3>�ӫ~�����G</h3>     
        <div class="dropdown">
          <div class="btninsert">
        <button type="button" class="btny" id="btninsert">�s�W�ӫ~</button>
          </div>        		
            <div class="wrap">
            <div class="search">
            <FORM METHOD="post" ACTION="product.do" >
            <input class="search-bar" type="text"  name="productno" placeholder="��J�W��">
       		<input type="hidden" name="action" value="search">        	
           <button type="submit" class="search-btn"><i class="fas fa-search"></i></button>
           </div>
        </div>
        <br>
        <br>
 
        <table class="rwd-table">
            <tr>
                <th>�ӫ~�s��</th>
				<th>�ӫ~�W��</th>
				<th>���O</th>
				<th>����</th>
				<th>�ӫ~�ƶq</th>
				<th>�ӫ~����</th>
				<th>���A</th>
				<th>�ӫ~�Ϥ�1</th>
				<th>�ӫ~�Ϥ�2</th>
				<th>�ӫ~�Ϥ�3</th>
				<th>�ק�</th>
				<th>�R��</th>
            </tr>
            
        <%@ include file="page1.file" %> 
		<c:forEach var="message" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
            <tr>
               <td>${message.productno}</td>
				<td>${message.pname}</td>
				<td>${message.psort}</td>
				<td>${message.price}</td>
				<td>${message.inventory}</td>
				<td>${message.descript}</td>
				<td>${message.situation}</td>
				<td><img src="<%=request.getContextPath() %>/PhotoServlet?id=${message.productno}&img=1">
				<td><img src="<%=request.getContextPath() %>/PhotoServlet?id=${message.productno}&img=2">
				<td><img src="<%=request.getContextPath() %>/PhotoServlet?id=${message.productno}&img=3">
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" style="margin-bottom: 0px;">
					<input type="submit" class= "btnupdate" value="�ק�">
					<input type="hidden" name="productno"  value="${message.productno}">
			     	<input type="hidden" name="action"  value="getUpdate"></FORM>
				<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" style="margin-bottom: 0px;">
					<input type="submit" class= "btndanger" value="�R��">
					<input type="hidden" name="productno"  value="${message.productno}">
			     	<input type="hidden" name="action"  value="delete"></FORM>
            </tr>
          </c:forEach> 		
        </table>
       <%@ include file="page2.file" %> 
    </div>
    <div class="bg" id="bgblack"></div>
    <div class="pop">
    <div class="formmember">
        
<FORM METHOD= post ACTION="<%=request.getContextPath()%>/product/product.do" class="alert">
	<table class="rwd-side">
	<tr>
		<td>�ӫ~�W��:</td>
		<td><input type="TEXT" name="pname" size="40" 
			 value="<%= (productVO==null)? "" : productVO.getPname()%>" /></td>
	</tr>
	<tr>
		<td>���O:</td>
		<td><input type="TEXT" name="psort" size="40"
			 value="<%= (productVO==null)? "" : productVO.getPsort()%>" /></td>
	</tr>
	<tr>
		<td>����:</td>
		<td><input type="TEXT" name="price" size="40" 
			 value= "<%= (productVO==null)? "" : productVO.getPrice()%>" /></td>
	</tr>
	<tr>
		<td>�ƶq:</td>
		<td><input type="TEXT" name="inventory" size="40"
			 value="<%= (productVO==null)? "" : productVO.getInventory()%>" /></td>
	</tr>
	<tr>
		<td>�ӫ~����:</td>
		<td><input type="TEXTAREA" name="descript" size="40"
			 value="<%= (productVO==null)? "" : productVO.getDescript()%>" /></td>
	</tr>
	
	<tr>
		<td>�ӫ~�Ϥ�1:</td>
		<td><input type="file" name="img1"
			 value="<%= (productVO==null)? "" : productVO.getPicture1()%>" /></td>
	</tr>
	
	<tr>
		<td>�ӫ~�Ϥ�2:</td>
		<td><input type="file" name="img2"
			 value="<%= (productVO==null)? "" : productVO.getPicture2()%>" /></td>
	</tr>
	
	<tr>
		<td>�ӫ~�Ϥ�3:</td>
		<td><input type="file" name="img3"
			 value="<%= (productVO==null)? "" : productVO.getPicture3()%>" /></td>
	</tr>


	</table>
<br>
	<div class="button">
	<input type="hidden" name="action" value="insert">
	<input type="submit" value="�e�X�s�W">
	<button class="button_editok">����</button></FORM>
	</div>

			
                
            
            	            
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
<script src="productstyle/table.js"></script>

</body>

</html>