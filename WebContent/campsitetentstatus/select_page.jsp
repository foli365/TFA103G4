<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>CampsiteTentStatus: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>CampsiteTentStatus: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for CampsiteTentStatus: Home</p>

<h3>��a�b�O���A��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath()%>/campsitetentstatus/listAllCampsiteTentStatus.jsp'>List</a> all CampsiteTentStatus    <h4>(byDAO).         </h4></li>
  
  <li>
    <FORM METHOD="post" ACTION="campsitetentstatus.do" >
        <b>��J��a�s�� (�p5001):</b>
        <input type="text" name="campId">
        <input type="hidden" name="action" value="getAllOfOne">
        <input type="submit" value="�e�X">                   <h4>(��Ʈ榡����  by Controller).</h4> 
    </FORM>
  </li>

<%--   <jsp:useBean id="dao" scope="page" class="com.campsitetentstatus.model.CampsiteTentStatusDAO" /> --%>
   
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="campsitetentstatus.do" > -->
<!--        <b>�����a�s��:</b> -->
<!--        <select size="1" name="campId"> -->
<%--          <c:forEach var="campsiteVO" items="${dao.all}" >  --%>
<%--           <option value="${campsiteVO.campId}">${campsiteVO.campId} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getAllOfOne"> -->
<!--        <input type="submit" value="�e�X"> -->
<!--     </FORM> -->
<!--   </li> -->
</ul>

<h3>��a�b�O���A�޲z</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/campsitetentstatus/addCampsiteTentStatus.jsp'>Add</a> a new CampsiteTentStatus.</li>
</ul>

</body>
</html>