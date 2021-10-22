<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.camprelease.model.*"%>
<%@ page import="com.facilities.model.*"%>
<%@ page import="com.plan.model.*"%>

<%
CampReleaseVO campreleaseVO = (CampReleaseVO) request.getAttribute("campreleaseVO"); //CampReleaseServlet.java(Concroller), �s�Jreq��campreleaseVO����
FacilitiesVO facilitiesVO = (FacilitiesVO) request.getAttribute("facilitiesVO");
%>
<%
PlanVO planVO = (PlanVO) request.getAttribute("planVO");
%>
<%
// CampReleaseService campreleaseSvc = new CampReleaseService();
// List<CampReleaseVO> list = campreleaseSvc.getAll();
// pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>��ܤ@����a���</title>
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

      /* �H�U�]�� */
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
  <h1 class="header__title">��ܤ@�����</h1><br>
  <table id="table-1">
		 <h4><a href="<%=request.getContextPath()%>/camprelease/Select_Page.jsp"><img src="images/gocamping.jpg" width="500" height="125" border="0"><br>back home</a></h4>
</table>
</header>

<table id="example" class="display nowrap" style="width:100%">
    <thead>
	<tr>
		<th>��a�s��</th>
		<th>�|���s��</th>
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
		<th>�t�M</th>
		<th>�]�I</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	</thead>
	<tbody>
	<jsp:useBean id="planSvc" scope="page" class="com.plan.model.PlanService" />
	<tr>
			 <td>�i${campreleaseVO.campId}�j</td>
			<td>�i${campreleaseVO.memberId}�j</td>
			<td>�i${campreleaseVO.campName}�j</td>
			<td>�i${campreleaseVO.location}�j</td>
			<td>�i${campreleaseVO.latitude}�j</td>
			<td>�i${campreleaseVO.longtitude}�j</td>
			<td>�i${campreleaseVO.campDescription}�j</td> 
			<td>�i${campreleaseVO.campPrice}���j</td>
		    <td>�i<fmt:formatDate value="${campreleaseVO.listedTime}"
					pattern="yyyy-MM-dd HH:mm:ss" />�j</td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=1" width="100"></td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=2" width="100"></td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=3" width="100"></td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=4" width="100"></td>
			<td><img src="<%=request.getContextPath() %>/CampReleasePhotoServlet?id=${campreleaseVO.campId}&img=5" width="100"></td>
            <td>�i${planVO.planName}�j�i${planVO.planGuestLimit}�H�j�i${planVO.planAgeLimit}���H�U�j�i${planVO.planPrice}���j<br></td>
			<td>			      
			      <div>
                    <label class="setting-label circle-line" for="setting[]"><input type="checkbox" name="bbq" id="bbq" value="1" ${facilitiesSvc.findByCampId(facilitiesVO.getCampId()).bbq == '1' ? 'checked' : ''}><span class="material-icons md-18">outdoor_grill</span></label>
                    <label class="setting-label circle-line" for="setting[]"><input type="checkbox" name="wifi" id="wifi" value="1" ${facilitiesSvc.findByCampId(facilitiesVO.getCampId()).wifi == '1' ? 'checked' : ''}><span class="material-icons md-18">wifi</span></label>
                    <label class="setting-label circle-line" for="setting[]"><input type="checkbox" name="nosmoke" id="nosmoke" value="1" ${facilitiesSvc.findByCampId(facilitiesVO.getCampId()).nosmoke == '1' ? 'checked' : ''}><span class="material-icons md-18">smoke_free</span></label>
                    <label class="setting-label circle-line" for="setting[]"><input type="checkbox" name="pets" id="pets" value="1" ${facilitiesSvc.findByCampId(facilitiesVO.getCampId()).pets == '1' ? 'checked' : ''}><span class="material-icons md-18">pets</span></label>
                  </div>
            </td>
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