<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.campsite.model.*"%>

<%
	CampsiteVO campsiteVO = (CampsiteVO) request.getAttribute("campsiteVO"); //CampsiteServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>��a��ƭק� - update_campsite_input.jsp</title>

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
				<h3>��a��ƭק� - update_campsite_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��ƭק�:</h3>

	<%-- ���~���C --%>
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
				<td>��a�s��:<font color=red><b>*</b></font></td>
				<td><%=campsiteVO.getCampId()%></td>
			</tr>
			<tr>
				<td>�|���s��:</td>
				<td><input type="TEXT" name="memberId" size="45"
					value="<%=campsiteVO.getMemberId()%>" /></td>
			</tr>
			<tr>
				<td>��a�W��:</td>
				<td><input type="TEXT" name="campName" size="45"
					value="<%=campsiteVO.getCampName()%>" /></td>
			</tr>
			<tr>
				<td>�a�}:</td>
				<td><input type="TEXT" name="location" size="45"
					value="<%=campsiteVO.getLocation()%>" /></td>
			</tr>
			<tr>
				<td>�g��:</td>
				<td><input type="TEXT" name="latitude" size="45"
					value="<%=campsiteVO.getLatitude()%>" /></td>
			</tr>
			<tr>
				<td>�n��:</td>
				<td><input type="TEXT" name="longtitude" size="45"
					value="<%=campsiteVO.getLongtitude()%>" /></td>
			</tr>
			<tr>
				<td>��a����:</td>
				<td><input type="TEXT" name="campDescription" size="45"
					value="<%=campsiteVO.getCampDescription()%>" /></td>
			</tr>
			<tr>
				<td>��a����:</td>
				<td><input type="TEXT" name="campPrice" size="45"
					value="<%=campsiteVO.getCampPrice()%>" /></td>
			</tr>
			<tr>
				<td>�H�ƤW��:</td>
				<td><input type="TEXT" name="campLimit" size="45"
					value="<%=campsiteVO.getCampLimit()%>" /></td>
			</tr>
			<tr>
				<td>�W�Ǯɶ�:</td>
				<td><input name="listedTime" id="f_date1" type="text" readonly="true"></td>
			</tr>
			<tr>
				<td>��~���A:</td>
				<td><select size="1" name="siteState">
						<option value="1" ${campsiteVO.getSiteState()==1?'selected':''}>��~��</option>
						<option value="0" ${campsiteVO.getSiteState()==0?'selected':''}>�𮧤�</option>
				</select></td>
			</tr>
			<tr>
				<td>���w�H��:</td>
				<td><input type="TEXT" name="lovedCount" size="45"
					value="<%=campsiteVO.getLovedCount()%>" /></td>
			</tr>
			<tr>
				<td>���|�H��:</td>
				<td><input type="TEXT" name="reportedCount" size="45"
					value="<%=campsiteVO.getReportedCount()%>" /></td>
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
		<br> <input type="hidden" name="action" value="update">
			<input type="hidden" name="campId" value="<%=campsiteVO.getCampId()%>">
			<input type="submit" value="�e�X�ק�">
	</FORM>
</body>


<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

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
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
 		   value: '<%=campsiteVO.getListedTime()%>', // value:   new Date(),
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