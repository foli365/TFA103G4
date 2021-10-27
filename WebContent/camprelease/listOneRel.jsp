<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.camprelease.model.*"%>
<%@ page import="com.facilities.model.*"%>
<%@ page import="com.plan.model.*"%>

<%
// CampReleaseVO campreleaseVO = (CampReleaseVO) request.getAttribute("campreleaseVO"); //CampReleaseServlet.java(Concroller), 存入req的campreleaseVO物件
// PlanService planSvc = new PlanService();
// CampReleaseVO campreleaseVO = (CampReleaseVO) session.getAttribute("campreleaseVO");
// Integer campId = campreleaseVO.getCampId();
// List<PlanVO> list = planSvc.getByCampId(campId);
// pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>顯示一個營地資料</title>
  <link rel='stylesheet' href='<%=request.getContextPath()%>/camprelease/css/jquery.dataTables.min.css' />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/camprelease/css/bootstrap.min5.1.0.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/camprelease/css/icon.css">
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

      /* 以下設備 */
      .setting-label{
        position: relative;
        display: inline-block;
        line-height: 1em;
        overflow: hidden;
        margin: 0 5px 5px 0;
        cursor: pointer;
      }
      .setting-label > input{
        position: absolute;
        top: -20px;
        left: -20px;
      }
      .setting-label > span{
        position: relative;
        display: block;
        padding: 10px 12px 10px 10px;
        color: #000;
        font-weight: 500;
        background-color: lightgray;
        /* white-space: nowrap;
        border-radius: 2em; */
        -webkit-border-radius: 2em;
        -moz-border-radius: 2em;
      }
      .setting-label > span > i{
        opacity: 1;
      }
      .setting-label:hover > span{
        color:#fff;
        background-color: #F4A249;
      }
      .setting-label:hover >span.male{
        background-color: #F4A249;
      }
      .setting-label input:checked + span{
        background-color: #f23557;
        color: white;
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
		<th>會員編號</th>
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
		<th>配套</th>
		<th>設施</th>
	</tr>
	</thead>
	<tbody>
	<jsp:useBean id="facilitiesSvc" scope="page" class="com.facilities.model.FacilitiesService" />
<%-- 	<jsp:useBean id="planSvc" scope="page" class="com.plan.model.PlanService" /> --%>
	<tr>
			 <td>【${campreleaseVO.campId}】</td>
			<td>【${campreleaseVO.memberId}】</td>
			<td>【${campreleaseVO.campName}】</td>
			<td>【${campreleaseVO.location}】</td>
			<td>【${campreleaseVO.latitude}】</td>
			<td>【${campreleaseVO.longtitude}】</td>
			<td>【${campreleaseVO.campDescription}】</td> 
			<td>【${campreleaseVO.campPrice}元】</td>
		    <td>【<fmt:formatDate value="${campreleaseVO.listedTime}"
					pattern="yyyy-MM-dd HH:mm:ss" />】</td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=1" width="100"></td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=2" width="100"></td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=3" width="100"></td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=4" width="100"></td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=5" width="100"></td>
            <td>【${planVO.planName}】【${planVO.planGuestLimit}人】【${planVO.planAgeLimit}歲以下】【${planVO.planPrice}元】<br></td>
			<td>			      
			      <div>
<%--                     <label class="setting-label circle-line" for="setting[]"><input type="checkbox" name="bbq" id="bbq" value="1" ${facilitiesSvc.getByCampId(facilitiesVO.getCampId()).bbq == '1' ? 'checked' : ''}><span class="material-icons md-18">outdoor_grill</span></label> --%>
<%--                     <label class="setting-label circle-line" for="setting[]"><input type="checkbox" name="wifi" id="wifi" value="1" ${facilitiesSvc.getByCampId(facilitiesVO.getCampId()).wifi == '1' ? 'checked' : ''}><span class="material-icons md-18">wifi</span></label> --%>
<%--                     <label class="setting-label circle-line" for="setting[]"><input type="checkbox" name="nosmoke" id="nosmoke" value="1" ${facilitiesSvc.getByCampId(facilitiesVO.getCampId()).nosmoke == '1' ? 'checked' : ''}><span class="material-icons md-18">smoke_free</span></label> --%>
<%--                     <label class="setting-label circle-line" for="setting[]"><input type="checkbox" name="pets" id="pets" value="1" ${facilitiesSvc.getByCampId(facilitiesVO.getCampId()).pets == '1' ? 'checked' : ''}><span class="material-icons md-18">pets</span></label> --%>
                  </div>
            </td>
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