<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.camprelease.model.*"%>

<%
CampReleaseVO campreleaseVO = (CampReleaseVO) request.getAttribute("campreleaseVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>Go camping��a�Z�n</title>
<%--   <link rel="stylesheet" href="<%=request.getContextPath()%>/camprelease/css/index.css" /> --%>
  <link rel='stylesheet' href='<%=request.getContextPath()%>/camprelease/css/bootstrap.min4.1.3.css' />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/camprelease/css/stepstyle.css">
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
<%-- <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" name="form1"> --%>
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
<%--         <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" name="form1"> --%>
        <div class="row">
          <div class="col-12 col-lg-8 m-auto">
            <form class="multisteps-form__form">
              <div class="multisteps-form__panel shadow p-4 rounded bg-white js-active" data-animation="scaleIn">
                <h3 class="multisteps-form__title">��a��T</h3>
                <div class="multisteps-form__content"> 
                  <div class="form-row mt-4">
                    <div class="col-12 col-sm-6">
                      <label for="inputName" class="col-form-label">��a�W��</label>
                      <input class="multisteps-form__input form-control" type="TEXT" name="campName" size="45" id="c_name" value="<%= (campreleaseVO==null)? "�Ѥ���a��" : campreleaseVO.getCampName()%>"/>
                    </div>
                  </div>
                  <div class="form-row mt-4">
                      <label for="inputintr" class="col-form-label">��a����</label>
                        <textarea class="multisteps-form__textarea form-control" type="TEXT" id="c_intr" value="<%= (campreleaseVO==null)? "�o�O��a��,�i�H�S��o��" : campreleaseVO.getCampDescription()%>" ></textarea>

                  </div>
                  <div class="form-row mt-4">
                    <div class="col-12 col-sm-6">
                      <label for="inputprice" class="col-form-label">����</label>
                        <input class="multisteps-form__input form-control" type="TEXT" id="c_price" value="<%= (campreleaseVO==null)? "1000" : campreleaseVO.getCampPrice()%>" />
                    </div>
                  </div>
                  <div class="form-row mt-4">
                    <div class="col-12 col-sm-6">
                  <label for="inputchoose" class="col-form-label">����</label>
                    <select class="multisteps-form__select form-control" id="c_choose">
                      <option selected="selected">����...</option>
                      <option>�s�W</option>
                      <option>����</option>
                      <option>�˪L</option>
                    </select>
                    </div>
                  </div>

                  <div class="button-row d-flex mt-4">
                    <button class="btn btn-primary ml-auto js-btn-next" type="button" title="Next">Next</button>
                  </div>
                </div>
              </div>

              <div class="multisteps-form__panel shadow p-4 rounded bg-white" data-animation="scaleIn">
                <h3 class="multisteps-form__title">�a�I</h3>
                <div class="multisteps-form__content">
<!--                     �a�ϧ�g�n�I�u�� -->
                  <div class="form-row mt-4">
                    <div class="col">
                      <div id="floating-panel">
                        <input id="address" type="textbox" value="Sydney, NSW" />
                        <input id="submit" type="button" value="Geocode" />
                      </div>
                      <div id="map"></div>
                    </div>
                  </div>
                  <div class="button-row d-flex mt-4">
                    <button class="btn btn-primary js-btn-prev" type="button" title="Prev">Prev</button>
                    <button class="btn btn-primary ml-auto js-btn-next" type="button" title="Next">Next</button>
                  </div>
                </div>
              </div>

              <div class="multisteps-form__panel shadow p-4 rounded bg-white" data-animation="scaleIn">
                <h3 class="multisteps-form__title">�ФW����a�Ϥ�</h3>
                <div class="multisteps-form__content">
                  <div class="form-row mt-4">
                    <div class="col" id="preview">
                      <canvas id="can1"></canvas>
                        <p>filename:
                           <input type="file" multiple="false" accept="image/*" id="finput" onchange=upload()>
                        </p>
                    </div>
<table>
	<tr>
		<td>Pic1:</td>
		<td><input type="file" size="50" name="picture1"
		     value="<%= (campreleaseVO==null)? "" : campreleaseVO.getPicture1()%>" /></td>
	</tr>
	<tr>
		<td>Pic2:</td>
		<td><input type="file" size="50" name="picture2"
		     value="<%= (campreleaseVO==null)? "" : campreleaseVO.getPicture2()%>" /></td>
	</tr>
	<tr>
		<td>Pic3:</td>
		<td><input type="file" size="50" name="picture3"
		     value="<%= (campreleaseVO==null)? "" : campreleaseVO.getPicture3()%>" /></td>
	</tr>
	<tr>
		<td>Pic4:</td>
		<td><input type="file" size="50" name="picture4"
		     value="<%= (campreleaseVO==null)? "" : campreleaseVO.getPicture4()%>" /></td>
	</tr>
	<tr>
		<td>Pic5:</td>
		<td><input type="file" size="50" name="picture5"
		     value="<%= (campreleaseVO==null)? "" : campreleaseVO.getPicture5()%>" /></td>
	</tr>
	</table>
                  </div>
                  <div class="button-row d-flex mt-4">
                    <button class="btn btn-primary js-btn-prev" type="button" title="Prev">Prev</button>
                    <button class="btn btn-primary ml-auto js-btn-next" type="button" title="Next">Next</button>
                  </div>
                </div>
              </div>

              <div class="multisteps-form__panel shadow p-4 rounded bg-white" data-animation="scaleIn">
                <h3 class="multisteps-form__title">�t�M��{</h3>
                <div class="multisteps-form__content">
                  <div class="form-row mt-4">
                    <div class="col-12 col-sm-6">
                      <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">+�}�l�s�W��{</button>
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

              <div class="multisteps-form__panel shadow p-4 rounded bg-white" data-animation="scaleIn">
                <h3 class="multisteps-form__title">�]�ƻP�A��</h3>
                <div class="multisteps-form__content">
                  <div class="ws-nowrap camp5">
                    <label class="setting-label circle-line" for="setting1"><input type="checkbox" name="setting[]" id="setting1" value=""><span class="material-icons md-18">outdoor_grill �N�װ�</span></label><br>
                    <label class="setting-label circle-line" for="setting2"><input type="checkbox" name="setting[]" id="setting2" value=""><span class="material-icons md-18">pool �a��</span></label><br>
                    <label class="setting-label circle-line" for="setting3"><input type="checkbox" name="setting[]" id="setting3" value=""><span class="material-icons md-18">wifi  ����</span></label><br>
                    <label class="setting-label circle-line" for="setting4"><input type="checkbox" name="setting[]" id="setting4" value=""><span class="material-icons md-18">smoke_free �T��</span></label><br>
                    <label class="setting-label circle-line" for="setting5"><input type="checkbox" name="setting[]" id="setting5" value=""><span class="material-icons md-18">pets �d��</span></label><br>
                    <label class="setting-label circle-line" for="setting6"><input type="checkbox" name="setting[]" id="setting6" value=""><span class="material-icons md-18">shower �O�D</span></label><br>
                  </div>
                  <div class="button-row d-flex mt-4">
                    <button class="btn btn-primary js-btn-prev" type="button" title="Prev">Prev</button>
                    <button class="btn ml-auto" type="reset" title="Reset">Reset</button>
                    <button class="btn btn-success ml-auto" type="submit" title="Send" id="btn_submit">Send</button>
                    <input type="hidden" name="action" value="insert">
                    <input type="submit" value="�e�X�s�W">
                  </div>
                </div>
              </div>
 
            </form>
          </div>
        </div>
<!--         </FORM> -->
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
<!--     </FORM> -->

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" name="form1">
<table>
	<tr>
		<td>��a�W��:</td>
		<td><input type="TEXT" name="campName" size="45" 
			 value="<%= (campreleaseVO==null)? "�Ѥ���a��" : campreleaseVO.getCampName()%>" /></td>
	</tr>
	<tr>
		<td>�a�I:</td>
		<td><input type="TEXT" name="location" size="45"
			 value="<%= (campreleaseVO==null)? "�x�_���j�w��23��" : campreleaseVO.getLocation()%>" /></td>
	</tr>
	<tr>
		<td>�g��:</td>
		<td><input type="TEXT" name="latitude" size="45"
			 value="<%= (campreleaseVO==null)? "23.567" : campreleaseVO.getLatitude()%>" /></td>
	</tr>
	<tr>
		<td>�n��:</td>
		<td><input type="TEXT" name="longtitude" size="45"
			 value="<%= (campreleaseVO==null)? "50.2321" : campreleaseVO.getLongtitude()%>" /></td>
	</tr>
	<tr>
		<td>��a����:</td>
		<td><input type="TEXT" name="campDescription" size="45"
		     value="<%= (campreleaseVO==null)? "�o�O��a��,�i�H�S��o��" : campreleaseVO.getCampDescription()%>" /></td>
	</tr>
	<tr>
		<td>����:</td>
		<td><input type="TEXT" name="campPrice" size="45"
		     value="<%= (campreleaseVO==null)? "1000" : campreleaseVO.getCampPrice()%>" /></td>
	</tr>
	<tr>
		<td>���:</td>
		<td><input type="TEXT" name="listedTime" size="45" id="f_date1"></td>
	</tr>
	<tr>
		<td>Pic1:</td>
		<td><input type="file" size="50" name="picture1"
		     value="<%= (campreleaseVO==null)? "" : campreleaseVO.getPicture1()%>" /></td>
	</tr>
	<tr>
		<td>Pic2:</td>
		<td><input type="file" size="50" name="picture2"
		     value="<%= (campreleaseVO==null)? "" : campreleaseVO.getPicture2()%>" /></td>
	</tr>
	<tr>
		<td>Pic3:</td>
		<td><input type="file" size="50" name="picture3"
		     value="<%= (campreleaseVO==null)? "" : campreleaseVO.getPicture3()%>" /></td>
	</tr>
	<tr>
		<td>Pic4:</td>
		<td><input type="file" size="50" name="picture4"
		     value="<%= (campreleaseVO==null)? "" : campreleaseVO.getPicture4()%>" /></td>
	</tr>
	<tr>
		<td>Pic5:</td>
		<td><input type="file" size="50" name="picture5"
		     value="<%= (campreleaseVO==null)? "" : campreleaseVO.getPicture5()%>" /></td>
	</tr>
	<tr>
		<td>�|���s��:<font color=red><b>*</b></font></td>
		<td><select size="1" name="memberId">
<%-- 		<c:forEach var="campreleaseVO" items="${deptSvc.all}"> --%>
				<option value="${campreleaseVO.memberId}" >
<%-- 			</c:forEach> --%>
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W">
</FORM>
<script src="<%=request.getContextPath()%>/camprelease/js/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/js/bootstrap.min.4.1.3.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/vendors/jquery/jquery-3.6.0.min.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/js/camp.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/js/stepfunction.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/js/planAlert.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/js/photoUpload.js"></script>
</body>
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
<script src="<%=request.getContextPath()%>/camprelease/js/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/js/bootstrap.min4.1.3.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/vendors/jquery/jquery-3.6.0.min.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/js/camp.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/js/stepfunction.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/js/planAlert.js"></script>
<script src="<%=request.getContextPath()%>/camprelease/js/photoUpload.js"></script>
</html>