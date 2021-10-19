<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.camprelease.model.*"%>
<%@ page import="com.facilities.model.*"%>

<%
CampReleaseVO campreleaseVO = (CampReleaseVO) request.getAttribute("campreleaseVO");
FacilitiesVO facilitiesVO = (FacilitiesVO) request.getAttribute("facilitiesVO");

%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>Go camping��a�Z�n</title>
  <link rel='stylesheet' href='<%=request.getContextPath()%>/camprelease/css/bootstrap.min4.1.3.css' />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/camprelease/css/stepstyle.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/camprelease/css/icon.css">
<!-- �H�UCSS�������˦� -->
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

.imgto{
  position: relative;
}

.preview_box img {
  padding: 0 22px;
  width: 300px;
}

canvas{
  height: 200px;
  border-style: solid;
  border-width: 3px;
}

imput{
  font-size: 14pt;
}

/* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
       #map {
        height: 100%;
      }
      
      /* Optional: Makes the sample page fill the window. */
      /* html,
      body {
        height: 100%;
        margin: 0;
        padding: 0;
      } */
      
      #floating-panel {
        position: absolute;
        top: 10px;
        left: 25%;
        z-index: 5;
        background-color: #fff;
        padding: 5px;
        border: 1px solid #999;
        text-align: center;
        font-family: "Roboto", "sans-serif";
        line-height: 30px;
        padding-left: 10px;
      }

      .camp5{
        margin-left: 15px !important;
      }
      .ws-nowrap{
        white-space: nowrap;
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
</head>

<body>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<header class="header" >
  <h1 class="header__title">Go camping��a�Z�n</h1><br>
  <table id="table-1">
		 <h4><a href="<%=request.getContextPath()%>/camprelease/Select_Page.jsp"><img src="images/gocamping.jpg" width="500" height="125" border="0"><br>back home</a></h4>
</table>
</header>

<!-- �L�{���s -->
    <div class="container overflow-hidden">
      <div name="action" action="#"  method="#" class="multisteps-form" id="the_form">
        <div class="row">
          <div class="col-12 col-lg-8 ml-auto mr-auto mb-4">
            <div class="multisteps-form__progress">
              <button class="multisteps-form__progress-btn js-active" type="button" title="User Info">��a��T</button>
              <button class="multisteps-form__progress-btn" type="button" title="Address">�a�I</button>
              <button class="multisteps-form__progress-btn" type="button" title="Picture">��a�Ϥ�</button>
              <button class="multisteps-form__progress-btn" type="button" title="Order tirp">�t�M��{</button>
              <button class="multisteps-form__progress-btn" type="button" title="Setting">�]�ƻP�A��</button>            
            </div>
          </div>
        </div>

<!-- �s�W��T -->
<jsp:useBean id="campreleaseSvc" scope="page" class="com.camprelease.model.CampReleaseService" />
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" name="form1" enctype="multipart/form-data">
        <div class="row">
          <div class="col-12 col-lg-8 m-auto">
            <div class="multisteps-form__form">
              <div class="multisteps-form__panel shadow p-4 rounded bg-white js-active" data-animation="scaleIn">
                <h3 class="multisteps-form__title">��a��T</h3>
                <div class="multisteps-form__content">
                  <div class="form-row mt-4">
                    <div class="col-12 col-sm-6">
                      <label class="col-form-label">��ܷ|���s��<font color=red><b>*</b></font>
                        <select size="1" name="memberId">
		                <c:forEach var="campreleaseVO" items="${campreleaseSvc.all}">
		                <option value="${campreleaseVO.memberId}">${campreleaseVO.memberId}</c:forEach>
		                </select>
		              </label>
                    </div>
                  </div>
                  <div class="form-row mt-4">
                    <div class="col-12 col-sm-6">
                      <label for="inputName" class="col-form-label">��a�W��
                      <input type="text" name="campName" size="45" class="multisteps-form__input form-control" value="<%=(campreleaseVO == null) ? "�w���S��a" : campreleaseVO.getCampName()%>"></label>
                    </div>
                  </div>
                  <div class="form-row mt-4">
                      <label for="inputintr" class="col-form-label">��a����</label>
                        <textarea class="multisteps-form__textarea form-control" id="intr" name="campDescription" 
                        ></textarea>
                  </div>
                  <div class="form-row mt-4">
                    <div class="col-12 col-sm-6">
                      <label for="inputprice" class="col-form-label">����
                        <input type="text" class="multisteps-form__input form-control" name="campPrice" id="c_price" 
                        value="<%=(campreleaseVO == null) ? "500" : campreleaseVO.getCampPrice()%>"></label>
                    </div>
                  </div>
                  <div class="form-row mt-4">
                    <div class="col-12 col-sm-6">
                      <label for="inputprice" class="col-form-label">���
                        <input type="text" class="multisteps-form__input form-control" name="ListedTime" size="45" id="f_date1"
                        value="<%=(campreleaseVO == null) ? "2021-12-05 12:45:03" : campreleaseVO.getListedTime()%>"></label>
                    </div>
                  </div>
<!--                   ��������ݭn���n�[ -->
<!--                   <div class="form-row mt-4"> -->
<!--                     <div class="col-12 col-sm-6"> -->
<!--                       <label for="inputchoose" class="col-form-label">���� -->
<!--                       <select class="multisteps-form__select form-control" name="campChoose" id="c_choose"> -->
<!--                           <option disabled required>����..</option> -->
<!--                           <option value="0" -->
<%--                           ${(campreleaseVO.campChoose==0)? "selected":""}>�s�W</option> --%>
<!--                           <option selected value="1" -->
<%--                           ${(campreleaseVO.campChoose==1)? "selected":""}>����</option> --%>
<!--                           <option value="2" -->
<%--                           ${(campreleaseVO.campChoose==2)? "selected":""}>�˪L</option> --%>
<!--                       </select> -->
<!--                       </label> -->
<!--                     </div> -->
<!--                   </div> -->
                  <div class="button-row d-flex mt-4">
                    <button class="btn btn-primary ml-auto js-btn-next" type="button" title="Next">Next</button>
                  </div>
                </div>
              </div>
<!-- �a�I�h��g�n�� -->
              <div class="multisteps-form__panel shadow p-4 rounded bg-white" data-animation="scaleIn">
                <h3 class="multisteps-form__title">�a�I</h3>
                <div class="multisteps-form__content">
                  <div class="form-row mt-4">
                    <div class="col">
                      <div id="webbulutumap" style="height: 280px;"></div>
                        <input type="text" name="location" value="" size="40" class="wpcf7-form-control wpcf7-text wpcf7-validates-as-required" id="address" aria-required="true" aria-invalid="false" placeholder="Street Address"/>
                        <input type="text" name="latitude" value="" placeholder="latitude" id="latitude"/>
                        <input type="text" name="longtitude" value="" placeholder="longtitude" id="longtitude"/>
                        <a href="#" id="find-address" title="Find Address" class="button">Find Address</a>
                    </div>
                  </div>
                  <div class="button-row d-flex mt-4">
                    <button class="btn btn-primary js-btn-prev" type="button" title="Prev">Prev</button>
                    <button class="btn btn-primary ml-auto js-btn-next" type="button" title="Next">Next</button>
                  </div>
                </div>
              </div>
<!-- ��a�Ϥ��s�W -->
              <div class="multisteps-form__panel shadow p-4 rounded bg-white" data-animation="scaleIn">
                <h3 class="multisteps-form__title">�ФW����a�Ϥ�</h3>
                <div class="multisteps-form__content">
                  <div class="form-row mt-4">
<!--                     <div class="col" id="preview"> -->
<%--                       <canvas id="can1"></canvas> --%>
<!--                         <p>filename: -->
<!--                            <input type="file" multiple="false" accept="image/*" id="finput" onchange=upload()> -->
<!--                         </p> -->
<!--                     </div> -->

										<h5>
											<label>Pic1: <input type="file" accept="image/*"
												name="picture1"></label>
										</h5>
										<h5>
											<label>Pic2: <input type="file" accept="image/*"
												name="picture2"></label>
										</h5>
										<h5>
											<label>Pic3: <input type="file" accept="image/*"
												name="picture3"></label>
										</h5>
										<h5>
											<label>Pic4: <input type="file" accept="image/*"
												name="picture4"></label>
										</h5>
										<h5>
											<label>Pic5: <input type="file" accept="image/*"
												name="picture5"></label>
										</h5>
									</div>
                  <div class="button-row d-flex mt-4">
                    <button class="btn btn-primary js-btn-prev" type="button" title="Prev">Prev</button>
                    <button class="btn btn-primary ml-auto js-btn-next" type="button" title="Next">Next</button>
                  </div>
                </div>
              </div>
<!-- �t�M��{�s�W -->
              <div class="multisteps-form__panel shadow p-4 rounded bg-white" data-animation="scaleIn">
                <h3 class="multisteps-form__title">�t�M��{</h3>
                <div class="multisteps-form__content">
                  <div class="form-row mt-4">
                    <div class="col-12 col-sm-6">
                      <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#plans" data-whatever="@mdo">+�}�l�s�W��{</button>
                  </div>
                  
                  </div>
                  <div class="row">
                    <div class="button-row d-flex mt-4 col-12">
                      <button class="btn btn-primary js-btn-prev" type="button" title="Prev">Prev</button>
                      <button class="btn btn-primary ml-auto js-btn-next" type="button" title="Next">Next</button>
                    </div>
                  </div>
                </div>
              </div>
<!-- �]�I�A��ICON -->
              <div class="multisteps-form__panel shadow p-4 rounded bg-white" data-animation="scaleIn">
                <h3 class="multisteps-form__title">�]�ƻP�A��</h3>
                <div class="multisteps-form__content">
                  <div class="ws-nowrap camp5">
                    <label class="setting-label circle-line" for="bbq"><input type="checkbox" name="bbq" id="bbq" value="1" ${facilitiesSvc.getBbq(facilitiesVO.getFacilitiesId()).bbq == '1' ? 'checked' : ''}><span class="material-icons md-18">outdoor_grill</span></label>
                    <label class="setting-label circle-line" for="wifi"><input type="checkbox" name="wifi" id="wifi" value="1" ${facilitiesSvc.getWifi(facilitiesVO.getFacilitiesId()).wifi == '1' ? 'checked' : ''}><span class="material-icons md-18">wifi</span></label>
                    <label class="setting-label circle-line" for="nosmoke"><input type="checkbox" name="nosmoke" id="nosmoke" value="1" ${facilitiesSvc.getNosmoke(facilitiesVO.getFacilitiesId()).nosmoke == '1' ? 'checked' : ''}><span class="material-icons md-18">smoke_free</span></label>
                    <label class="setting-label circle-line" for="pets"><input type="checkbox" name="pets" id="pets" value="1" ${facilitiesSvc.getPets(facilitiesVO.getFacilitiesId()).pets == '1' ? 'checked' : ''}><span class="material-icons md-18">pets</span></label>
                  </div>
                  <div class="button-row d-flex mt-4">
                    
                       <button class="btn btn-primary js-btn-prev" type="button" title="Prev">Prev</button>
                       <button class="btn ml-auto" type="reset" title="Reset">Reset</button>

                    <div>
                       <input type="hidden" name="action" value="insert">
                       <button class="btn btn-success ml-auto" type="submit">Send</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </FORM>
        </div>
      </div>
      
     <!-- �t�M�u�X���� -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">�зs�W�t�M��{</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <form>
              <div class="form-group">
                <label for="recipient-name" class="col-form-label">��{�W��</label>
                <input type="text" class="form-control" id="recipient-name">
              </div>
              <div class="form-group">
                <label for="recipient-people" class="col-form-label">�H��</label>
                <input type="text" class="form-control" id="recipient-people">
              </div>
              <div class="form-group">
                <label for="recipient-price" class="col-form-label">����</label>
                <input type="text" class="form-control" id="recipient-price">
              </div>
              <div class="form-group">
                <label for="recipient-price" class="col-form-label">�~�֭���</label>
                <input type="text" class="form-control" id="recipient-age">
              </div>
              <div class="form-group">
                <label for="message-text" class="col-form-label">Message:</label>
                <textarea class="form-control" id="message-text"></textarea>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Send</button>
          </div>
        </div>
      </div>
    </div>
    
<!-- �Ѯv�d�� -->
<%-- ���~��C --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">�Эץ��H�U���~:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>
<%-- <jsp:useBean id="campreleaseSvc" scope="page" class="com.camprelease.model.CampReleaseService" /> --%>
<%-- <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" name="form1" enctype="multipart/form-data"> --%>
<!-- <table> -->
<!-- <tr> -->
<!-- 		<td>��ܷ|���s��:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="memberId"> -->
<%-- 		<c:forEach var="campreleaseVO" items="${campreleaseSvc.all}"> --%>
<%-- 		<option value="${campreleaseVO.memberId}">${campreleaseVO.memberId}</c:forEach> --%>
<!-- 		</select> -->
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>��a�W��:</td> -->
<!-- 		<td><input type="TEXT" name="campName" size="45"  -->
<%-- 			 value="<%= (campreleaseVO==null)? "�Ѥ���a��" : campreleaseVO.getCampName()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>�a�I:</td> -->
<!-- 		<td><input type="TEXT" name="location" size="45" -->
<%-- 			 value="<%= (campreleaseVO==null)? "�x�_���j�w��23��" : campreleaseVO.getLocation()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>�g��:</td> -->
<!-- 		<td><input type="TEXT" name="latitude" size="45" -->
<%-- 			 value="<%= (campreleaseVO==null)? "23.567" : campreleaseVO.getLatitude()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>�n��:</td> -->
<!-- 		<td><input type="TEXT" name="longtitude" size="45" -->
<%-- 			 value="<%= (campreleaseVO==null)? "50.2321" : campreleaseVO.getLongtitude()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>��a����:</td> -->
<!-- 		<td><input type="TEXT" name="campDescription" size="45" -->
<%-- 		     value="<%= (campreleaseVO==null)? "�o�O��a��,�i�H�S��o��" : campreleaseVO.getCampDescription()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>����:</td> -->
<!-- 		<td><input type="TEXT" name="campPrice" size="45" -->
<%-- 		     value="<%= (campreleaseVO==null)? "1000" : campreleaseVO.getCampPrice()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>���:</td> -->
<!-- 		<td><input type="TEXT" name="listedTime" size="45" id="f_date1"></td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>Pic1:</td> -->
<!-- 		<td><input type="file" size="50" name="picture1"/></td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>Pic2:</td> -->
<!-- 		<td><input type="file" size="50" name="picture2"/></td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>Pic3:</td> -->
<!-- 		<td><input type="file" size="50" name="picture3"/></td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>Pic4:</td> -->
<!-- 		<td><input type="file" size="50" name="picture4"/></td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>Pic5:</td> -->
<!-- 		<td><input type="file" size="50" name="picture5"/></td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>�|���s��:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="memberId"> -->
<%-- 		<c:forEach var="campreleaseVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${campreleaseVO.memberId}" > --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

<!-- </table> -->
<!-- <br> -->
<!-- <input type="hidden" name="action" value="insert"> -->
<!-- <input type="submit" value="�e�X�s�W"> -->
<!-- </FORM> -->
<script src="<%=request.getContextPath()%>/camprelease/js/jquery_3.3.1.slim.min.js"></script>
<%-- <script src="<%=request.getContextPath()%>/camprelease/js/popper.min.js"></script> --%>
<script src="<%=request.getContextPath()%>/camprelease/js/bootstrap.min4.1.3.js"></script>

<script src="<%=request.getContextPath()%>/camprelease/vendors/jquery/jquery-3.6.0.min.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/js/camp.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/js/stepfunction.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/js/planAlert.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/js/photoUpload.js"></script>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA1SwBl3CYCg1oon98Lyge8VLpxdcx-RZU"></script>
<!-- <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script> -->
<script type="text/javascript">
    var map;
    var marker;
    var myLatlng = new google.maps.LatLng('25.055998', '121.539728');
    var geocoder = new google.maps.Geocoder();
    var infowindow = new google.maps.InfoWindow();
    function initialize() {
        var mapOptions = {
        zoom: 10,
        center: myLatlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        map = new google.maps.Map(document.getElementById("webbulutumap"), mapOptions);
        marker = new google.maps.Marker({
            map: map,
            position: myLatlng,
            draggable: true
        });
        google.maps.event.addListener(marker, 'dragend', function() {
            geocoder.geocode({'latLng': marker.getPosition()}, function(results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    if (results[0]) {
                        var address_components = results[0].address_components;
                        var components={};
                        jQuery.each(address_components, function(k,v1) {jQuery.each(v1.types, function(k2, v2){components[v2]=v1.long_name});});
                        $('#latitude').val(marker.getPosition().lat());
                        $('#longitude').val(marker.getPosition().lng());
                        infowindow.setContent(results[0].formatted_address);
                        infowindow.open(map, marker);
                    }
                }
            });
        });
    }
    google.maps.event.addDomListener(window, 'load', initialize);
</script>
<script>
  $("#find-address").click(function(){
    var apiKey = 'AIzaSyA1SwBl3CYCg1oon98Lyge8VLpxdcx-RZU';
    var  address =  $('#address').val();
    var addressClean = address.replace(/\s+/g, '+');
    var geocoder = new google.maps.Geocoder();
    geocoder.geocode({
      address: addressClean 
    }, function(results, status) {
      console.log(status);
      if (status == 'OK') {
        longitude = results[0].geometry.location.lng();
        latitude = results[0].geometry.location.lat();
        document.getElementById("longtitude").value = longitude;
        document.getElementById("latitude").value = latitude;
        // geocoder is asynchronous, do this in the callback function
        longitude = $("input#longtitude").val();
        latitude = $("input#latitude").val();
        if (longitude && latitude) {
          longitude = parseFloat(longitude);
          latitude = parseFloat(latitude);
          initMap(longitude, latitude);
        }
      } else alert("geocode failed")
    });
    function initMap(longitude, latitude) {
    var myLatlng = new google.maps.LatLng(latitude, longitude);
    var mapOptions = {
      zoom: 12,
      center: myLatlng,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    var map = new google.maps.Map(document.getElementById("webbulutumap"), mapOptions);
    var marker = new google.maps.Marker({
      position: myLatlng,
      map: map,
      draggable: true,
      title: "Where's your garden?"
    });
    google.maps.event.addListener(marker, 'dragend', function() {
            geocoder.geocode({'latLng': marker.getPosition()}, function(results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    if (results[0]) {
                        var address_components = results[0].address_components;
                        var components={};
                        jQuery.each(address_components, function(k,v1) {jQuery.each(v1.types, function(k2, v2){components[v2]=v1.long_name});});

                        $('#latitude').val(marker.getPosition().lat());
                        $('#longtitude').val(marker.getPosition().lng());
                        infowindow.setContent(results[0].formatted_address);
                        infowindow.open(map, marker);
                    }
                }
            });
        });
  };
}) 
</script>
<!-------------------datetimepicker------------------------->
<%
  java.sql.Timestamp listedTime = null;
try{
	listedTime = campreleaseVO.getListedTime();
} catch (Exception e) {
	listedTime = new java.sql.Timestamp(System.currentTimeMillis());
}
%>
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
   timepicker:true,       //timepicker:true,
   step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
   format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
   value: '<%=listedTime%>', // value:   new Date(),
   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
   //startDate:	            '2017/07/10',  // �_�l��
   //minDate:               '-1970-01-01', // �h������(���t)���e
   //maxDate:               '+1970-01-01'  // �h������(���t)����
});

</script>
</body>
</html>