<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.camprelease.model.*"%>

<%
CampReleaseService campreleaseSvc = new CampReleaseService();
    List<CampReleaseVO> list = campreleaseSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>All營地資料</title>
  <link rel='stylesheet' href='<%=request.getContextPath()%>/camprelease/css/jquery.dataTables.min.css' />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/camprelease/css/bootstrap.min5.1.0.css">

    <style>
        div.dataTables_wrapper {
        background-color: whitesmoke;
        width: 800px;
        margin: 0 auto;
    }
    body{
        background-color: #FFEEE1;
    }
    </style>
    
</head>
    <body style="background-color: #7B7571;">
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
        
        <br>
        <br>
        <br>
        <div class="container">
            <a class="btn btn-primary" href="<%=request.getContextPath() %>/camprelease/addCampRel.jsp" role="button"><image src="svg/patch-plus.svg"></image>Add</a>
<table id="example" class="display nowrap" style="width:100%">
    <thead>
        <tr>
            <th>營地編號</th>
            <th>營地名稱</th>
            <th>地點</th>
            <th>經度</th>
            <th>緯度</th>
            <th>營地介紹</th>
            <th>價錢</th>
            <th>日期</th>
            <th>配套名稱</th>
            <th>配套價格</th>
            <th>配套搭配人數</th>
            <th>pic1</th>
            <th>pic2</th>
            <th>pic3</th>
            <th>pic4</th>
            <th>pic5</th>
            <th>設施</th>
            <th>會員編號</th>
            <th>修改</th>
            <th>刪除</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="campreleaseVO" items="${list}">
        <tr>
            <td>${campreleaseVO.campId}</td>
			<td>${campreleaseVO.campName}</td>
			<td>${campreleaseVO.location}</td>
			<td>${campreleaseVO.latitude}</td>
			<td>${campreleaseVO.longtitude}</td>
			<td>${campreleaseVO.campDescription}</td> 
			<td>${campreleaseVO.campPrice}</td>
			<td>${campreleaseVO.listedTime}</td>
			<td>配套名稱</td>
            <td>配套價格</td>
            <td>配套搭配人數</td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=1" width="100">
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=2" width="100">
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=3" width="100">
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=4" width="100">
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=5" width="100">
			<td>設施</td>
			<td>${campreleaseVO.memberId}</td>
            <td>
<!--                   <br>     -->
<!--                   <p> -->
<%--                   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" style="margin-bottom: 0px;">  --%>
<%--                     <a class="btn btn-outline-info" href="<%=request.getContextPath() %>/camprelease/updateCampRel.jsp" role="button"><image src="svg/gear.svg"></image>update</a> --%>
<%--                     			     <input type="hidden" name="campId"  value="${campreleaseVO.campId}">  --%>
<!--  			     <input type="hidden" name="action"	value="getOne_For_Update" > -->
<!--                   </FORM></p> -->
                  			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" style="margin-bottom: 0px;"> 
			     <input type="submit" value="修改">
			     <input type="hidden" name="campId"  value="${campreleaseVO.campId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
              </td>
              <td>
<!--                   <br> -->
<!--                   <p> -->
<%--                     <a class="btn btn-outline-danger" href="<%=request.getContextPath() %>/camprelease/updateCampRel.jsp" role="button"><image src="svg/trash.svg"></image>delete</a> --%>
<!--                   </p> -->
                  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="campId"  value="${campreleaseVO.campId}">
			     <input type="hidden" name="action" value="delete"></FORM>
              </td>
        </tr>
        </c:forEach>
    </tbody>
    </table>
</div>








<table id="table-1">
	<tr><td>
		 <h3>所有營地資料</h3>
		 <h4><a href="<%=request.getContextPath() %>/camprelease/Select_Page.jsp"><img src="images/gocamping.jpg" width="100" height="32" border="0">back home</a></h4>
	</td></tr>
</table>


<table>
	<tr>
		<th>營地編號</th>
		<th>營地名稱</th>
		<th>地點</th>
		<th>經度</th>
		<th>緯度</th>
		<th>營地介紹</th>
		<th>價錢</th>
		<th>日期</th>
		<th>pic1</th>
		<th>pic2</th>
		<th>pic3</th>
		<th>pic4</th>
		<th>pic5</th>
		<th>會員編號</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
		<%@ include file="/camprelease/pages/page1.file" %> 
	<c:forEach var="campreleaseVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${campreleaseVO.campId}</td>
			<td>${campreleaseVO.campName}</td>
			<td>${campreleaseVO.location}</td>
			<td>${campreleaseVO.latitude}</td>
			<td>${campreleaseVO.longtitude}</td>
			<td>${campreleaseVO.campDescription}</td> 
			<td>${campreleaseVO.campPrice}</td>
			<td>${campreleaseVO.listedTime}</td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=1" width="100">
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=2" width="100">
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=3" width="100">
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=4" width="100">
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=5" width="100">
			<td>${campreleaseVO.memberId}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="campId"  value="${campreleaseVO.campId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="campId"  value="${campreleaseVO.campId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="/camprelease/pages/page2.file" %>


<script src="<%=request.getContextPath()%>/camprelease/js/jquery_3.5.1.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/js/jquery.dataTables.min.js"></script>
<script>
       $(document).ready(function() {
    $('#example').DataTable( {
        "scrollX": true,
        "searching": true, //搜尋功能, 預設是開啟
        "paging": true, //分頁功能, 預設是開啟
        "ordering": true, //排序功能, 預設是開啟
        "lengthMenu": [5, 10],
        // "aria": {
        //     "sortAscending": ": 升冪排列",
        //     "sortDescending": ": 降冪排列"
        // }
        "language": {
        "processing": "處理中...",
        "loadingRecords": "載入中...",
        "lengthMenu": "顯示 _MENU_ 項結果",
        "zeroRecords": "沒有符合的結果",
        "info": "顯示第 _START_ 至 _END_ 項結果，共 _TOTAL_ 項",
        "infoEmpty": "顯示第 0 至 0 項結果，共 0 項",
        "infoFiltered": "(從 _MAX_ 項結果中過濾)",
        "infoPostFix": "",
        "search": "搜尋:",
        "paginate": {
            "first": "第一頁",
            "previous": "上一頁",
            "next": "下一頁",
            "last": "最後一頁"
        },
        "aria": {
            "sortAscending": ": 升冪排列",
            "sortDescending": ": 降冪排列"
        }
    }
    } );
} );
</script>
</body>
</html>