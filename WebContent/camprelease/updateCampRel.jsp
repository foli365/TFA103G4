<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.camprelease.model.*"%>

<%
CampReleaseVO campreleaseVO = (CampReleaseVO) request.getAttribute("campreleaseVO"); //CampReleaseServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的campreleaseVO, 也包括輸入資料錯誤時的campreleaseVO物件)
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>營地資料更新</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
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

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>營地資料修改</h3>
		 <h4><a href="<%=request.getContextPath()%>/camprelease/Select_Page.jsp"><img src="images/title_camp.png" width="100" height="32" border="0">back home</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" name="form1">
<table>
	<tr>
		<td>營地編號:<font color=red><b>*</b></font></td>
		<td><%=campreleaseVO.getCampId()%></td>
	</tr>
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><%=campreleaseVO.getMemberId()%></td>
	<tr>
		<td>營地名稱:</td>
		<td><input type="TEXT" name="campName" size="45" value="<%=campreleaseVO.getCampName()%>" /></td>
	</tr>
	<tr>
		<td>地址:</td>
		<td><input type="TEXT" name="location" size="45"	value="<%=campreleaseVO.getLocation()%>" /></td>
	</tr>
	<tr>
		<td>經度:</td>
		<td><input type="TEXT" name="latitude" size="45"	value="<%=campreleaseVO.getLatitude()%>" /></td>
	</tr>
	<tr>
		<td>緯度:</td>
		<td><input type="TEXT" name="longtitude" size="45" value="<%=campreleaseVO.getLongtitude()%>" /></td>
	</tr>
	<tr>
		<td>營地介紹:</td>
		<td><input type="TEXT" name="campDescription" size="45" value="<%=campreleaseVO.getCampDescription()%>" /></td>
	</tr>
	<tr>
		<td>價格:</td>
		<td><input type="TEXT" name="campPrice" size="45" value="<%=campreleaseVO.getCampPrice()%>" /></td>
	</tr>
	<tr>
		<td>日期:</td>
		<td><input name="listedTime" id="f_date1" type="text" ></td>
	</tr>
	<tr>
		<td>Pic1:</td>
		<td><input type="file" size="50" name="picture1"
		     value="<%= (campreleaseVO==null)? "" : campreleaseVO.getPicture1()%>" /></td>
	</tr>
	<tr>
		<td>Pic2:</td>
		<td><input type="file" size="50" name="picture2"
		     value="<%= (campreleaseVO==null)? "" : campreleaseVO.getPicture2()%>" /></td>
	</tr>
	<tr>
		<td>Pic3:</td>
		<td><input type="file" size="50" name="picture3"
		     value="<%= (campreleaseVO==null)? "" : campreleaseVO.getPicture3()%>" /></td>
	</tr>
	<tr>
		<td>Pic4:</td>
		<td><input type="file" size="50" name="picture4"
		     value="<%= (campreleaseVO==null)? "" : campreleaseVO.getPicture4()%>" /></td>
	</tr>
	<tr>
		<td>Pic5:</td>
		<td><input type="file" size="50" name="picture5"
		     value="<%= (campreleaseVO==null)? "" : campreleaseVO.getPicture5()%>" /></td>
	</tr>


</table>
<br>

<input type="hidden" name="action" value="update">
<input type="hidden" name="campId" value="<%=campreleaseVO.getCampId()%>">
<input type="hidden" name="memberId" value="<%=campreleaseVO.getMemberId()%>">
<input type="submit" value="送出修改"></FORM>
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
 		   value: '<%=campreleaseVO.getListedTime()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
</script>   
</html>