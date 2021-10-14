<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
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
<title>All��a���</title>
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
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
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
            <th>��a�s��</th>
            <th>��a�W��</th>
            <th>�a�I</th>
            <th>�g��</th>
            <th>�n��</th>
            <th>��a����</th>
            <th>����</th>
            <th>���</th>
            <th>�t�M�W��</th>
            <th>�t�M����</th>
            <th>�t�M�f�t�H��</th>
            <th>pic1</th>
            <th>pic2</th>
            <th>pic3</th>
            <th>pic4</th>
            <th>pic5</th>
            <th>�]�I</th>
            <th>�|���s��</th>
            <th>�ק�</th>
            <th>�R��</th>
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
			<td>�t�M�W��</td>
            <td>�t�M����</td>
            <td>�t�M�f�t�H��</td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=1" width="100">
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=2" width="100">
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=3" width="100">
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=4" width="100">
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=5" width="100">
			<td>�]�I</td>
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
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="campId"  value="${campreleaseVO.campId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
              </td>
              <td>
<!--                   <br> -->
<!--                   <p> -->
<%--                     <a class="btn btn-outline-danger" href="<%=request.getContextPath() %>/camprelease/updateCampRel.jsp" role="button"><image src="svg/trash.svg"></image>delete</a> --%>
<!--                   </p> -->
                  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
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
		 <h3>�Ҧ���a���</h3>
		 <h4><a href="<%=request.getContextPath() %>/camprelease/Select_Page.jsp"><img src="images/gocamping.jpg" width="100" height="32" border="0">back home</a></h4>
	</td></tr>
</table>


<table>
	<tr>
		<th>��a�s��</th>
		<th>��a�W��</th>
		<th>�a�I</th>
		<th>�g��</th>
		<th>�n��</th>
		<th>��a����</th>
		<th>����</th>
		<th>���</th>
		<th>pic1</th>
		<th>pic2</th>
		<th>pic3</th>
		<th>pic4</th>
		<th>pic5</th>
		<th>�|���s��</th>
		<th>�ק�</th>
		<th>�R��</th>
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
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="campId"  value="${campreleaseVO.campId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
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
        "searching": true, //�j�M�\��, �w�]�O�}��
        "paging": true, //�����\��, �w�]�O�}��
        "ordering": true, //�Ƨǥ\��, �w�]�O�}��
        "lengthMenu": [5, 10],
        // "aria": {
        //     "sortAscending": ": �ɾ��ƦC",
        //     "sortDescending": ": �����ƦC"
        // }
        "language": {
        "processing": "�B�z��...",
        "loadingRecords": "���J��...",
        "lengthMenu": "��� _MENU_ �����G",
        "zeroRecords": "�S���ŦX�����G",
        "info": "��ܲ� _START_ �� _END_ �����G�A�@ _TOTAL_ ��",
        "infoEmpty": "��ܲ� 0 �� 0 �����G�A�@ 0 ��",
        "infoFiltered": "(�q _MAX_ �����G���L�o)",
        "infoPostFix": "",
        "search": "�j�M:",
        "paginate": {
            "first": "�Ĥ@��",
            "previous": "�W�@��",
            "next": "�U�@��",
            "last": "�̫�@��"
        },
        "aria": {
            "sortAscending": ": �ɾ��ƦC",
            "sortDescending": ": �����ƦC"
        }
    }
    } );
} );
</script>
</body>
</html>