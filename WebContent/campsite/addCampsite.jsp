<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.campsite.model.*"%>

<%
	CampsiteVO campsiteVO = (CampsiteVO) request.getAttribute("campsiteVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>��a��Ʒs�W - addCampsite.jsp</title>

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
		<tr>
			<td>
				<h3>��a��Ʒs�W - addCampsite.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="<%=request.getContextPath()%>/campsite/select_page.jsp"><img
						src="images/tomcat.png" width="100" height="100" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��Ʒs�W:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="campsite.do" name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>�|���s��:</td>
				<td><input type="TEXT" name="memberId" size="45"
					value="<%=(campsiteVO == null) ? "4" : campsiteVO.getMemberId()%>" /></td>
			</tr>
			<tr>
				<td>��a�W��:</td>
				<td><input type="TEXT" name="campName" size="45"
					value="<%=(campsiteVO == null) ? "TibameTest" : campsiteVO.getCampName()%>" /></td>
			</tr>
			<tr>
				<td>�a�}:</td>
				<td><input type="TEXT" name="location" size="45"
					value="<%=(campsiteVO == null) ? "�x�_�����s�ϫn�ʪF���T�q219��5��" : campsiteVO.getLocation()%>" /></td>
			</tr>
			<tr>
				<td>�g��:</td>
				<td><input type="TEXT" name="latitude" size="45"
					value="<%=(campsiteVO == null) ? "111.2222" : campsiteVO.getLatitude()%>" /></td>
			</tr>
			<tr>
				<td>�n��:</td>
				<td><input type="TEXT" name="longtitude" size="45"
					value="<%=(campsiteVO == null) ? "222.1111" : campsiteVO.getLongtitude()%>" /></td>
			</tr>
			<tr>
				<td>��a����:</td>
				<td><input type="TEXT" name="campDescription" size="45"
					value="<%=(campsiteVO == null) ? "�o�̬OTibameTest" : campsiteVO.getCampDescription()%>" /></td>
			</tr>
			<tr>
				<td>��a����:</td>
				<td><input type="TEXT" name="campPrice" size="45"
					value="<%=(campsiteVO == null) ? "100" : campsiteVO.getCampPrice()%>" /></td>
			</tr>
			<tr>
				<td>�H�ƤW��:</td>
				<td><input type="TEXT" name="campLimit" size="45"
					value="<%=(campsiteVO == null) ? "100" : campsiteVO.getCampLimit()%>" /></td>
			</tr>
			<tr>
				<td>�W�Ǯɶ�:</td>
				<td><input name="listedTime" id="f_date1" type="text" readonly="true"></td>
			</tr>
			<!-- 			<tr> -->
			<!-- 				<td>��~���A:</td> -->
			<!-- 				<td><input type="TEXT" name="siteState" size="45" -->
			<%-- 					value="<%=(campsiteVO == null) ? "0" : campsiteVO.getSiteState()%>" /></td> --%>
			<!-- 			</tr> -->
			<tr>
				<td>��~���A:</td>
				<td><select size="1" name="siteState">
						<option value="1" selected>��~��</option>
						<option value="0">�𮧤�</option>
				</select></td>
			</tr>
			<tr>
				<td>���w�H��:</td>
				<td><input type="TEXT" name="lovedCount" size="45"
					value="<%=(campsiteVO == null) ? "100" : campsiteVO.getLovedCount()%>" /></td>
			</tr>
			<tr>
				<td>���|�H��:</td>
				<td><input type="TEXT" name="reportedCount" size="45"
					value="<%=(campsiteVO == null) ? "1" : campsiteVO.getReportedCount()%>" /></td>
			</tr>
			<tr>
				<td>��~����:</td>
				<td><input type="file" name="campLicense" /></td>
			</tr>
			<tr>
				<td>�Ӥ�1:</td>
				<td><input type="file" name="picture1" /></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="�e�X�s�W">
	</FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<%
	// 	java.sql.Date listedTime = null;
	// 	java.util.Date utilListedTime = null;
	// 	try {
	// 		utilListedTime = new java.util.Date(campsiteVO.getListedTime().getTime());
	// 		listedTime = new java.sql.Date(utilListedTime.getTime());
	// 	} catch (Exception e) {
	// 		listedTime = new java.sql.Date(System.currentTimeMillis());
	// 	}
%>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
	$.datetimepicker.setLocale('zh');
	$('#f_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (�o�Otimepicker���w�]���j60����)
		format : 'Y-m-d H:i:s', //format:'Y-m-d H:i:s',
		value : new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
	//startDate:	            '2017/07/10',  // �_�l��
	//minDate:               '-1970-01-01', // �h������(���t)���e
	//maxDate:               '+1970-01-01'  // �h������(���t)����
	});

	// ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

	//      1.�H�U���Y�@�Ѥ��e������L�k���
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.�H�U���Y�@�Ѥ��᪺����L�k���
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
</html>