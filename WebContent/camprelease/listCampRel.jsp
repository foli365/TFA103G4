<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.camprelease.model.CampReleaseDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.camprelease.model.*"%>
<%@ page import="com.facilities.model.*"%>
<%@ page import="com.favorite.model.*"%>
<%@ page import="com.campAlert.model.*"%>
<%@ page import="com.camporder.model.*"%>
<%@ page import="com.campsitetentstatus.model.*"%>
<%@ page import="com.plan.model.*"%>

<%
// CampReleaseDAO dao = new CampReleaseDAO();
//     List<CampReleaseVO> list = dao.getAll();
//     pageContext.setAttribute("list",list);
    
    CampReleaseService campreleaseSvc = new CampReleaseService();
    List<CampReleaseVO> list = campreleaseSvc.getAll();
    pageContext.setAttribute("list",list);
    
    FacilitiesVO facilitiesVO = (FacilitiesVO) request.getAttribute("facilitiesVO");
%>
<%
//     FacilitiesService facilitiesSvc = new FacilitiesService();
//     List<FacilitiesVO> listfacilities = facilitiesSvc.getAll();
//     pageContext.setAttribute("listfacilities",listfacilities);
//     session.setAttribute("facilitiesId", 1);
//     session.getAttribute("facilitiesId");
        
%>
<%-- <c:if test="${facilitiesId == 1}"> --%>
<%-- <c:set var="states" value="true"></c:set> --%>
<%-- </c:if> --%>

<%-- <jsp:useBean id="planSvc" scope="page" class="com.plan.model.PlanService" /> --%>
<%-- <jsp:useBean id="CampreleaseSvc" scope="page" class="com.camprelease.model.CampReleaseService" /> --%>
<%-- <jsp:useBean id="FacilitiesSvc" scope="page" class="com.facilities.model.FacilitiesService" /> --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>All營地資料</title>
  <link rel='stylesheet' href='<%=request.getContextPath()%>/camprelease/css/jquery.dataTables.min.css' />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/camprelease/css/bootstrap.min5.1.0.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/camprelease/css/icon.css">
    <style>
        div.dataTables_wrapper {
        background-color: whitesmoke;
        width: 800px;
        margin: 0 auto;
    }
    body{
        background-color: #7B7571; 
    }
    
         /* 以下設備 */
/*       .setting-label{ */
/*         position: relative; */
/*         display: inline-block; */
/*         line-height: 1em; */
/*         overflow: hidden; */
/*         margin: 0 5px 5px 0; */
/*         cursor: pointer; */
/*       } */
/*       .setting-label > input{ */
/*         position: absolute; */
/*         top: -20px; */
/*         left: -20px; */
/*       } */
/*       .setting-label > span{ */
/*         position: relative; */
/*         display: block; */
/*         padding: 10px 12px 10px 10px; */
/*         color: #000; */
/*         font-weight: 500; */
/*         background-color: lightgray; */
/*         /* white-space: nowrap; */
/*         border-radius: 2em; */ */
/*         -webkit-border-radius: 2em; */
/*         -moz-border-radius: 2em; */
/*       } */
/*       .setting-label > span > i{ */
/*         opacity: 1; */
/*       } */
/*       .setting-label:hover > span{ */
/*         color:#fff; */
/*         background-color: #F4A249; */
/*       } */
/*       .setting-label:hover >span.male{ */
/*         background-color: #F4A249; */
/*       } */
/*       .setting-label input:checked + span{ */
/*         background-color: #f23557; */
/*         color: white; */
/*       } */
    </style>
    
</head>
    <body>
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
            <th>配套方案</th>
            <th>設施</th>
            <th>營地資料修改</th>
            <th>Plan修改</th>
<!--             <th>刪除</th> -->
        </tr>
    </thead>
    <jsp:useBean id="facilitiesSvc" scope="page" class="com.facilities.model.FacilitiesService" />
    <jsp:useBean id="planSvc" scope="page" class="com.plan.model.PlanService" />
    <jsp:useBean id="campAlertSvc" scope="page" class="com.campAlert.model.CampAlertService" />
    <jsp:useBean id="favoriteSvc" scope="page" class="com.favorite.model.FavoriteService" />
    <jsp:useBean id="campsiteTentStatusSvc" scope="page" class="com.campsitetentstatus.model.CampsiteTentStatusService" />
    <jsp:useBean id="campOrderSvc" scope="page" class="com.camporder.model.CampOrderService" />
    <c:forEach var="campreleaseVO"  items="${list}">
    <tbody>
        <tr>
            <td>【${campreleaseVO.campId}】</td>
			<td>【${campreleaseVO.memberId}】</td>
			<td>【${campreleaseVO.campName}】</td>
			<td>【${campreleaseVO.location}】</td>
			<td>【${campreleaseVO.latitude}】</td>
			<td>【${campreleaseVO.longtitude}】</td>
			<td>【${campreleaseVO.campDescription}】</td> 
			<td>【${campreleaseVO.campPrice}元】</td>
			<td>【<fmt:formatDate value="${campreleaseVO.listedTime}" pattern="yyyy-MM-dd HH:mm:ss"/>】</td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=1" width="100"></td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=2" width="100"></td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=3" width="100"></td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=4" width="100"></td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=5" width="100"></td>
			<td><c:forEach var="planVO" items="${planSvc.all}"><c:if test="${campreleaseVO.campId == planVO.campId}">【${planVO.planName}】【${planVO.planGuestLimit}人】【${planVO.planAgeLimit}歲以下】【${planVO.planPrice}元】<br></c:if></c:forEach></td>
			<td>
			      <div>
                    <label class="setting-label circle-line" for="setting[]"><input type="checkbox" name="bbq" id="bbq" value=""${(facilitiesVO.bbq == 0) ? '1' : 'checked' } disabled="disabled"><span class="material-icons md-18">outdoor_grill</span></label>
                    <label class="setting-label circle-line" for="setting[]"><input type="checkbox" name="wifi" id="wifi" value=""${(facilitiesVO.wifi == 0) ? '1' : 'checked' } disabled="disabled"><span class="material-icons md-18">wifi</span></label>
                    <label class="setting-label circle-line" for="setting[]"><input type="checkbox" name="nosmoke" id="nosmoke" value=""${(facilitiesVO.nosmoke == 0) ? '1' : 'checked' } disabled="disabled"><span class="material-icons md-18">smoke_free</span></label>
                    <label class="setting-label circle-line" for="setting[]"><input type="checkbox" name="pets" id="pets" value=""${(facilitiesVO.pets == 0) ? '1' : 'checked' } disabled="disabled"><span class="material-icons md-18">pets</span></label>
                  </div>
			</td>
            <td>
<!--                   <br>     -->
<!--                   <p> -->
<%--                   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" style="margin-bottom: 0px;">  --%>
<%--                     <a class="btn btn-outline-info" href="<%=request.getContextPath() %>/camprelease/updateCampRel.jsp" role="button"><image src="svg/gear.svg"></image>update</a> --%>
<%--                     			     <input type="hidden" name="campId"  value="${campreleaseVO.campId}">  --%>
<!--  			     <input type="hidden" name="action"	value="getOne_For_Update" > -->
<!--                   </FORM></p> -->
                  			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" style="margin-bottom: 0px;"> 
			     <input type="submit" value="營地資料修改">
			     <input type="hidden" name="campId"  value="${campreleaseVO.campId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
              </td>
                          <td>
                  			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/plan/plan.do" style="margin-bottom: 0px;"> 
			     <input type="submit" value="Plan修改">
			     <input type="hidden" name="planId"  value="${planVO.planId}">
			     <input type="hidden" name="action"	value="getOnePlan_For_Update"></FORM>
              </td>
              
<!--               <td> -->
<!--                   <br> -->
<!--                   <p> -->
<%--                     <a class="btn btn-outline-danger" href="<%=request.getContextPath() %>/camprelease/updateCampRel.jsp" role="button"><image src="svg/trash.svg"></image>delete</a> --%>
<!--                   </p> -->
<%--                   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="campId"  value="${campreleaseVO.campId}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!--               </td> -->
        </tr>
    </tbody>
        </c:forEach>
    </table>
</div>








<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>所有營地資料</h3> -->
<%-- 		 <h4><a href="<%=request.getContextPath() %>/camprelease/Select_Page.jsp"><img src="images/gocamping.jpg" width="100" height="32" border="0">back home</a></h4> --%>
<!-- 	</td></tr> -->
<!-- </table> -->


<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th width="30px">營地編號</th> -->
<!-- 		<th width="30px">會員編號</th> -->
<!-- 		<th width="30px">營地名稱</th> -->
<!-- 		<th width="100px">地點</th> -->
<!-- 		<th width="80px">經度</th> -->
<!-- 		<th width="80px">緯度</th> -->
<!-- 		<th width="100px">營地介紹</th> -->
<!-- 		<th width="30px">價錢</th> -->
<!-- 		<th width="80px">日期</th> -->
<!-- 		<th width="110px">pic1</th> -->
<!-- 		<th width="110px">pic2</th> -->
<!-- 		<th width="110px">pic3</th> -->
<!-- 		<th width="110px">pic4</th> -->
<!-- 		<th width="110px">pic5</th> -->
<!-- 		<th width="40px">修改</th> -->
<!-- 		<th width="40px">刪除</th> -->
<!-- 	</tr> -->
<%-- 		<%@ include file="/camprelease/pages/page1.file" %>  --%>
<%-- 	<c:forEach var="campreleaseVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
		
<!-- 		<tr> -->
<%-- 			<td>${campreleaseVO.campId}</td> --%>
<%-- 			<td>${campreleaseVO.campName}</td> --%>
<%-- 			<td>${campreleaseVO.location}</td> --%>
<%-- 			<td>${campreleaseVO.latitude}</td> --%>
<%-- 			<td>${campreleaseVO.longtitude}</td> --%>
<%-- 			<td>${campreleaseVO.campDescription}</td>  --%>
<%-- 			<td>${campreleaseVO.campPrice}</td> --%>
<%-- 			<td><fmt:formatDate value="${campreleaseVO.listedTime}" --%>
<%-- 						pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
<%-- 			<td><img src="<%=request.getContextPath() %>/CampReleaseGifReader?column=picture1&camp_id=${campreleaseVO.campId}" class="pic"> --%>
<%-- 			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=2" width="100"> --%>
<%-- 			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=3" width="100"> --%>
<%-- 			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=4" width="100"> --%>
<%-- 			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=5" width="100"> --%>
<%-- 			<td>${campreleaseVO.memberId}</td> --%>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="campId"  value="${campreleaseVO.campId}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="campId"  value="${campreleaseVO.campId}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<%-- 	</c:forEach> --%>
<!-- </table> -->
<%-- <%@ include file="/camprelease/pages/page2.file" %> --%>


<script src="<%=request.getContextPath()%>/camprelease/js/jquery_3.3.1.slim.min.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/js/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/js/popper.min.js"></script>
<script>
       $(document).ready(function() {
    $('#example').DataTable( {
        "scrollX": true,
        "searching": true, //搜尋功能, 預設是開啟
        "paging": true, //分頁功能, 預設是開啟
        "ordering": true, //排序功能, 預設是開啟
        "lengthMenu": [3, 8],
//         "aria": {
//             "sortAscending": ": 升冪排列",
//             "sortDescending": ": 降冪排列"
//         }
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