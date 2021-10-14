<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Campsite: Home</title>

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
   <tr><td><h3>Campsite: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Campsite: Home</p>

<h3>��Ƭd��:</h3>
	
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
  <li><a href='<%=request.getContextPath()%>/campsite/listAllCampsite_byDAO.jsp'>List</a> all Campsite    <h4>(byDAO).         </h4></li>
  
  <li>
    <FORM METHOD="post" ACTION="campsite.do" >
        <b>��J��a�s�� (�p5001):</b>
        <input type="text" name="campId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">                   <h4>(��Ʈ榡����  by Controller).</h4> 
    </FORM>
  </li>
  
  <li>
    <FORM METHOD="post" ACTION="campsite.do" name="form1">
        <b>��J��a�s�� (�p5001):</b>
        <input type="text" name="campId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="button" value="�e�X" onclick="fun1()">  <h4>(��Ʈ榡����  by Java Script).</h4> 
    </FORM>
  </li>

  <jsp:useBean id="dao" scope="page" class="com.campsite.model.CampsiteDAO" />
   
  <li>
     <FORM METHOD="post" ACTION="campsite.do" >
       <b>�����a�s��:</b>
       <select size="1" name="campId">
         <c:forEach var="campsiteVO" items="${dao.all}" > 
          <option value="${campsiteVO.campId}">${campsiteVO.campId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
</ul>

<h3>��a�޲z</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/campsite/addCampsite.jsp'>Add</a> a new Campsite.</li>
</ul>

<script>    
   function fun1(){
      with(document.form1){
         if (campId.value=="") 
             alert("�п�J��a�s��!");
         else if (isNaN(campId.value)) 
             alert("��a�s���榡�����T!");
         else if ((campId.value < 5001) || (campId.value > 5010)) 
             alert("�ж�g����5001�M5010�������ƶq!");
         else
             submit();
      }
   }
</script>

</body>
</html>