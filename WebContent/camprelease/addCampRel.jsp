<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.camprelease.model.*"%>
<%
CampReleaseVO campreleaseVO = (CampReleaseVO) request.getAttribute("campreleaseVO");

if(session.getAttribute("id") != null){
	Integer id = Integer.parseInt(session.getAttribute("id").toString());
	System.out.println("id: " + id);
	pageContext.setAttribute("id", id);		
	}
	CampReleaseService csvc = new CampReleaseService();
	List<CampReleaseVO> memberCamp = csvc.getAllforMember(1);
	pageContext.setAttribute("memberCamp", memberCamp);

%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>Go camping營地刊登</title>
<link rel='stylesheet' href='<%=request.getContextPath()%>/camprelease/css/bootstrap.min4.1.3.css' />
<link rel="stylesheet" href="<%=request.getContextPath()%>/camprelease/css/stepstyle.css">
<!-- 以下CSS為網頁樣式 -->
<%@ include file="/template/navbar.jsp" %>
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

</style>
</head>

<body>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<header class="header" >
  <h1 class="header__title">Go camping營地刊登</h1><br>
  <table id="table-1">
		 <h4><a href="<%=request.getContextPath()%>/camprelease/Select_Page.jsp"><img src="images/gocamping.jpg" width="500" height="125" border="0"></a></h4>
</table>
</header>
<!-- 過程按鈕 -->
    <div class="container overflow-hidden">
      <div name="action" action="#"  method="#" class="multisteps-form" id="the_form">
        <div class="row">
          <div class="col-12 col-lg-8 ml-auto mr-auto mb-4">
            <div class="multisteps-form__progress">
              <button class="multisteps-form__progress-btn js-active" type="button" title="User Info">營地資訊</button>
              <button class="multisteps-form__progress-btn" type="button" title="Address">地點</button>
              <button class="multisteps-form__progress-btn" type="button" title="Picture">營地圖片</button>
              <button class="multisteps-form__progress-btn" type="button" title="Setting">送出</button>            
            </div>
          </div>
        </div>
<c:forEach var="campreleaseVO" items="${memberCamp}">
				<!-- 新增資訊 -->
				<div class="row">
					<div class="col-12 col-lg-8 m-auto">
						<div class="multisteps-form__form">
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do"
								name="form1" enctype="multipart/form-data">
								<div class="multisteps-form__panel shadow p-4 rounded bg-white js-active" data-animation="scaleIn">
									<h3 class="multisteps-form__title">營地資訊</h3>
									<div class="multisteps-form__content">
										<div class="form-row mt-4">
											<div class="col-12 col-sm-6">
												<label for="inputName" class="col-form-label">營地名稱</label> 
												<input type="text" name="campName" size="45" class="multisteps-form__input form-control" placeholder="輸入營地名稱" value="">
											</div>
										</div>
										<div class="form-row mt-4">
											<label for="inputcampDescription" class="form-label">營地介紹</label>
											<textarea class="form-control" name="campDescription"
												rows="3" placeholder="請輸入...."></textarea>
										</div>
										<div class="form-row mt-4">
											<div class="col-12 col-sm-6">
												<label for="inputprice" class="col-form-label">價格</label> 
												<input type="text" class="multisteps-form__input form-control" name="campPrice" id="c_price" placeholder="請輸入價格" value="">
											</div>
										</div>
										<div class="form-row mt-4">
											<div class="col-12 col-sm-6">
												<label for="inputLimit" class="col-form-label">營地人數限制</label>
												<input type="text" class="multisteps-form__input form-control" name="campLimit" id="c_limit" placeholder="請輸入人數" value="">
											</div>
										</div>
										<div class="form-row mt-4">
											<div class="col-12 col-sm-6">
												<label for="inputprice" class="col-form-label">日期</label> <input type="text" class="multisteps-form__input form-control" name="listedTime" size="45" id="f_date1" value="">
											</div>
										</div>
										<div class="button-row d-flex mt-4">
											<button class="btn btn-primary ml-auto js-btn-next" type="button" title="Next">Next</button>
										</div>
									</div>
								</div>
								<!-- 地點去抓經緯度 -->
								<div class="multisteps-form__panel shadow p-4 rounded bg-white"
									data-animation="scaleIn">
									<h3 class="multisteps-form__title">地點</h3>
									<div class="multisteps-form__content">
										<div class="form-row mt-4">
											<div class="col">
												<div id="webbulutumap" style="height: 280px;"></div>
												<br>
												<br> 
												<input type="text" name="location" value="" size="40" class="wpcf7-form-control wpcf7-text wpcf7-validates-as-required"
													id="address" aria-required="true" aria-invalid="false" placeholder="Street Address" /><br>
												<br> <input type="text" name="latitude" value="" placeholder="latitude" id="latitude" /><br>
												<br> <input type="text" name="longtitude" value="" placeholder="longitude" id="longitude" /><br>
												<br> <a href="#" id="find-address" title="Find Address" class="button">Find Address</a>
											</div>
										</div>
										<div class="button-row d-flex mt-4">
											<button class="btn btn-primary js-btn-prev" type="button" title="Prev">Prev</button>
											<button class="btn btn-primary ml-auto js-btn-next" type="button" title="Next">Next</button>
										</div>
									</div>
								</div>
								<!-- 營地圖片新增 -->
								<div class="multisteps-form__panel shadow p-4 rounded bg-white"
									data-animation="scaleIn">
									<h3 class="multisteps-form__title">請上傳營地圖片</h3>
									<div class="multisteps-form__content">
										<div class="form-row mt-4">
											<h5>
												<label>Pic1: <input type="file" accept="image/*" name="picture1"></label>
											</h5>
											<h5>
												<label>Pic2: <input type="file" accept="image/*" name="picture2"></label>
											</h5>
											<h5>
												<label>Pic3: <input type="file" accept="image/*" name="picture3"></label>
											</h5>
											<h5>
												<label>Pic4: <input type="file" accept="image/*" name="picture4"></label>
											</h5>
											<h5>
												<label>Pic5: <input type="file" accept="image/*" name="picture5"></label>
											</h5>
										</div>
										<div class="button-row d-flex mt-4">
											<button class="btn btn-primary js-btn-prev" type="button" title="Prev">Prev</button>
											<button class="btn btn-primary ml-auto js-btn-next" type="button" title="Next">Next</button>
										</div>
									</div>
								</div>
								<div class="multisteps-form__panel shadow p-4 rounded bg-white" data-animation="scaleIn">
									<div class="button-row d-flex mt-4">

										<button class="btn btn-primary js-btn-prev" type="button" title="Prev">Prev</button>
										<button class="btn ml-auto" type="reset" title="Reset">Reset</button>

										<div>
											<input type="hidden" name="action" value="insert">
											<button class="btn btn-success ml-auto" type="submit">Send</button>
											<input type="hidden" name="memberId" value="${campreleaseVO.memberId}">
										    <input type="hidden" name="campId" value="${campreleaseVO.campId}">                       
										</div>
									</div>
								</div>
							</FORM>
						</div>
					</div>
				</div>
			</c:forEach>
          </div>
        </div>

<script src="<%=request.getContextPath()%>/camprelease/js/jquery_3.3.1.slim.min.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/js/bootstrap.min4.1.3.js"></script>

<script src="<%=request.getContextPath()%>/camprelease/vendors/jquery/jquery-3.6.0.min.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/js/camp.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/js/stepfunction.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/js/photoUpload.js"></script>

<script src="<%=request.getContextPath()%>/https://maps.googleapis.com/maps/api/js?key=AIzaSyBsqt74NPCV93dg4iOpJtLL0RDvMSfsnYM"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBsqt74NPCV93dg4iOpJtLL0RDvMSfsnYM"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
    var map;
    var marker;
    var myLatlng = new google.maps.LatLng('25.0511536', '121.5675248');
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
    var apiKey = 'AIzaSyBsqt74NPCV93dg4iOpJtLL0RDvMSfsnYM';
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
        document.getElementById("longitude").value = longitude;
        document.getElementById("latitude").value = latitude;
        // geocoder is asynchronous, do this in the callback function
        longitude = $("input#longitude").val();
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
                        $('#longitude').val(marker.getPosition().lng());
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
   step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
   format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
   value: '<%=listedTime%>', // value:   new Date(),
   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
   //startDate:	            '2017/07/10',  // 起始日
   //minDate:               '-1970-01-01', // 去除今日(不含)之前
   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
});

</script>
<%@ include file="/template/script.html" %>
</body>
</html>