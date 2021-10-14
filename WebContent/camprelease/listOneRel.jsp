<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.camprelease.model.*"%>

<%
CampReleaseVO campreleaseVO = (CampReleaseVO) request.getAttribute("campreleaseVO"); //CampReleaseServlet.java(Concroller), 存入req的campreleaseVO物件
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>顯示一個營地資料</title>
  <link rel='stylesheet' href='<%=request.getContextPath()%>/camprelease/css/jquery.dataTables.min.css' />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/camprelease/css/bootstrap.min5.1.0.css">
</head>
<style>

body{
  background-color: #FFEEE1;
  background-position: center;
  background-size: cover;
  font-family: sans-serif;
  margin-top: 40px;
  font-family: arial;
  margin: 30px;
}
.header{
text-align: center;
}

</style>
<body>

<header class="header" >
  <h1 class="header__title">顯示一筆資料</h1><br>
  <table id="table-1">
		 <h4><a href="<%=request.getContextPath()%>/camprelease/Select_Page.jsp"><img src="images/gocamping.jpg" width="500" height="125" border="0"><br>back home</a></h4>
</table>
</header>

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
	</tr>
	</thead>
	<tbody>
	<tr>
			<td><%=campreleaseVO.getCampId()%></td>
			<td><%=campreleaseVO.getCampName()%></td>
			<td><%=campreleaseVO.getLocation()%></td>
			<td><%=campreleaseVO.getLatitude()%></td>
			<td><%=campreleaseVO.getLongtitude()%></td>
			<td><%=campreleaseVO.getCampDescription()%></td> 
			<td><%=campreleaseVO.getCampPrice()%></td>
			<td><%=campreleaseVO.getListedTime()%></td>
			<td>配套名稱</td>
            <td>配套價格</td>
            <td>配套搭配人數</td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=1" width="100">
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=2" width="100">
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=3" width="100">
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=4" width="100">
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=5" width="100">
			<td>設施</td>
			<td><%=campreleaseVO.getMemberId()%></td>
	</tr>
	</tbody>
</table>


<script src="<%=request.getContextPath()%>/camprelease/js/jquery_3.5.1.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/js/jquery.dataTables.min.js"></script>
<script>
       $(document).ready(function() {
    $('#example').DataTable( {
        "scrollX": true,
        "searching": false, //搜尋功能, 預設是開啟
        "paging": false, //分頁功能, 預設是開啟
        "ordering": false, //排序功能, 預設是開啟
        "lengthMenu": false,
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
    }
    } );
} );
</script>
</body>
</html>