<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.facilities.model.*"%>
<%@ page import="java.util.List.*"%>

<%
FacilitiesVO facilitiesVO = (FacilitiesVO) request.getAttribute("facilitiesVO");
%>

<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>  
<title>設備資料更新</title>
<!-- Bootstrap CSS -->
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
      <link rel="stylesheet" href="<%=request.getContextPath()%>/camprelease/css/icon.css">
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
<jsp:useBean id="facilitiesSvc" scope="page" class="com.facilities.model.FacilitiesService" />
<div class="row g-3">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/facilities/facilities.do" name="form1" enctype="multipart/form-data">
          <div class="col-md-8">
            <label for="inputcampId" class="form-label">營地編號<font color=red><b>*</b></font><%=facilitiesVO.getCampId()%></label>
          </div>

<!-- 設備 -->
          <div class="col-md-6">
            <h3>設備</h3>
            <label class="setting-label circle-line" for="setting[]"><input type="checkbox" name="bbq" id="bbq" value=""><span class="material-icons md-18">outdoor_grill</span></label> 
            <label class="setting-label circle-line" for="setting[]"><input type="checkbox" name="wifi" id="wifi" value="" ><span class="material-icons md-18">wifi</span></label> 
            <label class="setting-label circle-line" for="setting[]"><input type="checkbox" name="nosmoke" id="nosmoke" value="" ><span class="material-icons md-18">smoke_free</span></label> 
            <label class="setting-label circle-line" for="setting[]"><input type="checkbox" name="pets" id="pets" value="" ><span class="material-icons md-18">pets</span></label> 
  </div>
  		<div class="col-md-12">
			<input type="hidden" name="action" value="facilities_update"> 
			<input type="hidden" name="facilitiesId" value="<%=facilitiesVO.getFacilitiesId()%>">
			<input type="hidden" name="campId" value="<%=facilitiesVO.getCampId()%>">
			<input type="submit" value="送出修改">
		</div>
  </FORM>
  
  </div>

     <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script> 
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script> 
</body>
</html>