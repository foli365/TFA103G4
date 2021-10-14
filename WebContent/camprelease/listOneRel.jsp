<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.camprelease.model.*"%>

<%
CampReleaseVO campreleaseVO = (CampReleaseVO) request.getAttribute("campreleaseVO"); //CampReleaseServlet.java(Concroller), �s�Jreq��campreleaseVO����
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>��ܤ@����a���</title>
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
  <h1 class="header__title">��ܤ@�����</h1><br>
  <table id="table-1">
		 <h4><a href="<%=request.getContextPath()%>/camprelease/Select_Page.jsp"><img src="images/gocamping.jpg" width="500" height="125" border="0"><br>back home</a></h4>
</table>
</header>

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
			<td>�t�M�W��</td>
            <td>�t�M����</td>
            <td>�t�M�f�t�H��</td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=1" width="100">
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=2" width="100">
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=3" width="100">
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=4" width="100">
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=5" width="100">
			<td>�]�I</td>
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
        "searching": false, //�j�M�\��, �w�]�O�}��
        "paging": false, //�����\��, �w�]�O�}��
        "ordering": false, //�Ƨǥ\��, �w�]�O�}��
        "lengthMenu": false,
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
    }
    } );
} );
</script>
</body>
</html>