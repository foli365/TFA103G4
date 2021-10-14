<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ page import="com.camprelease.model.*"%> --%>
<%-- <%@ page import="java.util.*" %> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="theme-color" content="#7952b3">
<title>營地新增首頁</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/camprelease/css/bootstrap.min5.1.0.css">

  <style>
    body{
        background-color: #FFEEE1;
    }
    .bg-body{
    color: white;
    
    }
    .bd-placeholder-img {
      font-size: 1.125rem;
      text-anchor: middle;
      -webkit-user-select: none;
      -moz-user-select: none;
      user-select: none;
    }

    @media (min-width: 768px) {
      .bd-placeholder-img-lg {
        font-size: 3.5rem;
      }
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
<jsp:useBean id="campreleaseSvc" scope="page" class="com.camprelease.model.CampReleaseService" />

<main class="container">
        <div class="d-flex align-items-center p-3 my-3 text-white bg-purple rounded shadow-sm">
          <img class="me-3" src="./svg/triangle-half.svg" alt="" width="48" height="38">
          <div class="lh-1">

          </div>
        </div>

        <div class="my-3 p-3 bg-body rounded shadow-sm">
            <h6 class="border-bottom pb-2 mb-0">營地資料查詢</h6>
            <div class="d-flex text-muted pt-3">
                <img class="me-3" src="./svg/tree.svg" alt="" width="48" height="38">
        
              <p class="pb-3 mb-0 small lh-sm border-bottom">
                <strong class="d-block text-blue">查詢全部資料</strong>
                <a href='listCampRel.jsp'>List</a> all Camps.
              </p>
            </div>
            <div class="d-flex text-muted pt-3">
                <img class="me-3" src="./svg/tree.svg" alt="" width="48" height="38">        
              <p class="pb-3 mb-0 small lh-sm border-bottom">
                <strong class="d-block text-gray-dark">由營地編號查詢</strong>
              </p>
              <ul>
                  <li>
              <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" >
                <select size="1" name="campId">          
                  <c:forEach var="campreleaseVO" items="${campreleaseSvc.all}" > 
                   <option value="${campreleaseVO.campId}">${campreleaseVO.campId}
                  </c:forEach>   
                </select>
                <input type="hidden" name="action" value="getOne_For_Display">
                <input type="submit" value="送出">
             </FORM>
                  </li>
            </ul>
            </div>
            <div class="d-flex text-muted pt-3">
                <img class="me-3" src="./svg/tree.svg" alt="" width="48" height="38" >        
              <p class="pb-3 mb-0 small lh-sm border-bottom">
                <strong class="d-block text-gray-dark">由營地名稱查詢</strong>
              </p>
              <ul>
                  <li>
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" >
                    <select size="1" name="campId">
                      <c:forEach var="campreleaseVO" items="${campreleaseSvc.all}" > 
                       <option value="${campreleaseVO.campId}">${campreleaseVO.campName}
                      </c:forEach>   
                    </select>
                    <input type="hidden" name="action" value="getOne_For_Display">
                    <input type="submit" value="送出">
                  </FORM>
                  </li>
               </ul>
            </div>
          </div>
        
          <div class="my-3 p-3 bg-body rounded shadow-sm">
            <h6 class="border-bottom pb-2 mb-0">營地資料管理</h6>
            <div class="d-flex text-muted pt-3">
                <img class="me-3" src="./svg/tree.svg" alt="" width="48" height="38">        
              <p class="pb-3 mb-0 small lh-sm border-bottom">
                <strong class="d-block text-gray-dark">營地上架管理</strong>
                <a href='<%=request.getContextPath()%>/camprelease/addCampRel.jsp'>Add</a> a new Camp.
              </p>
            </div>
          </div>
        </main>







<ul>
  <li><a href='listCampRel.jsp'>List</a> all Camps.  <br><br></li>
  

<%--   <jsp:useBean id="campreleaseSvc" scope="page" class="com.camprelease.model.CampReleaseService" /> --%>
<%--   <c:forEach var="campaddVO" items="${list}"></c:forEach>  --%>

  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" >
       <b>選擇營地編號:</b>
       <select size="1" name="campId">          
         <c:forEach var="campreleaseVO" items="${campreleaseSvc.all}" > 
          <option value="${campreleaseVO.campId}">${campreleaseVO.campId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>

  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" >
       <b>選擇營地名稱:</b>
       <select size="1" name="campId">
         <c:forEach var="campreleaseVO" items="${campreleaseSvc.all}" > 
          <option value="${campreleaseVO.campId}">${campreleaseVO.campName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>

</ul>


<h3>營地資料管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/camprelease/addCampRel.jsp'>Add</a> a new Camp.</li>
</ul>


<script src="<%=request.getContextPath()%>/camprelease/js/popper.min.2.10.2.js"></script>
</body>
</html>