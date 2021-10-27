<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>首頁</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/camprelease/css/bootstrap.min5.1.0.css">

<style>
body {
	background-color: #FFEEE1;
}

.bg-body {
	color: white;
}

.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>

</head>
<body>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<jsp:useBean id="campreleaseSvc" scope="page" class="com.camprelease.model.CampReleaseService" />
	<jsp:useBean id="planSvc" scope="page" class="com.plan.model.PlanService" />
	<jsp:useBean id="facilitiesSvc" scope="page" class="com.facilities.model.FacilitiesService" />

	<main class="container">
		<!-- 標題-->
		<div
			class="d-flex align-items-center p-3 my-3 text-black bg-purple rounded shadow-sm">
			<img class="me-3" src="./svg/triangle-half.svg" alt="" width="48"
				height="38">
			<div class="lh-1">Go Camping</div>
		</div>

		<!-- 新增營地刊登 -->
		<div class="my-3 p-3 rounded shadow-sm">
			<h6 class="border-bottom pb-2 mb-0">營地資料管理</h6>
			<div class="d-flex text-muted pt-3">
				<img class="me-3" src="./svg/tree.svg" alt="" width="48" height="38">
				<p class="pb-3 mb-0 small lh-sm border-bottom">
					<strong class="d-block text-gray-dark">營地上架管理</strong> 
					<a href='<%=request.getContextPath()%>/camprelease/addCampRel.jsp'>Add</a>
					a new Camp.
				</p>
			</div>
		</div>
		<!-- 營地資料查詢相關 -->
		<div class="my-3 p-3 rounded ">
			<h6 class="border-bottom pb-2 mb-0">營地資料查詢(查詢營地刊登相關資料)</h6>
			<div class="d-flex text-muted pt-3">
				<img class="me-3" src="./svg/tree.svg" alt="" width="48" height="38">
				<p class="pb-3 mb-0 small lh-sm border-bottom">
					<strong class="d-block text-gray-dark">由營地名稱查詢</strong>
				</p>
				<ul>
					<li>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do">
							<select size="1" name="campId">
								<c:forEach var="campreleaseVO" items="${campreleaseSvc.all}">
									<option value="${campreleaseVO.campId}">${campreleaseVO.campName}
								</c:forEach>
							</select> <input type="hidden" name="action" value="getOne_For_Display">
							<input type="submit" value="送出">
						</FORM>
					</li>
				</ul>
			</div>
		</div>

		<!-- 配套資料相關 -->
		<div class="my-3 p-3 rounded shadow-sm">
			<h6 class="border-bottom pb-2 mb-0">配套資料管理(查詢配套相關資料)</h6>
			<div class="d-flex text-muted pt-3">
				<img class="me-3" src="./svg/tree.svg" alt="" width="48" height="38">

				<p class="pb-3 mb-0 small lh-sm border-bottom">
					<strong class="d-block text-blue">查詢全部配套相關資料</strong> 
					<a href='listPlan.jsp'>List</a> all Plans.
				</p>
			</div>
			<div class="d-flex text-muted pt-3">
				<img class="me-3" src="./svg/tree.svg" alt="" width="48" height="38">
				<p class="pb-3 mb-0 small lh-sm border-bottom">
					<strong class="d-block text-gray-dark">由配套編號查詢</strong>
				</p>
				<ul>
					<li>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/plan/plan.do">
							<select size="1" name="planId">
								<c:forEach var="planVO" items="${planSvc.all}">
									<option value="${planVO.planId}">${planVO.planId}
								</c:forEach>
							</select> <input type="hidden" name="action"
								value="getOnePlan_For_Display"> <input type="submit"
								value="送出">
						</FORM>
					</li>
				</ul>
			</div>
			<div class="d-flex text-muted pt-3">
				<img class="me-3" src="./svg/tree.svg" alt="" width="48" height="38">
				<p class="pb-3 mb-0 small lh-sm border-bottom">
					<strong class="d-block text-gray-dark">由配套名稱查詢</strong>
				</p>
				<ul>
					<li>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/plan/plan.do">
							<select size="1" name="planId">
								<c:forEach var="planVO" items="${planSvc.all}">
									<option value="${planVO.planId}">${planVO.planName}
								</c:forEach>
							</select> <input type="hidden" name="action"
								value="getOnePlan_For_Display"> <input type="submit"
								value="送出">
						</FORM>
					</li>
				</ul>
			</div>
		</div>
		<!--設施資料相關 -->
		<div class="my-3 p-3 rounded shadow-sm">
			<h6 class="border-bottom pb-2 mb-0">設施資料管理(查詢設施相關資料)</h6>
			<div class="d-flex text-muted pt-3">
				<img class="me-3" src="./svg/tree.svg" alt="" width="48" height="38">

				<p class="pb-3 mb-0 small lh-sm border-bottom">
					<strong class="d-block text-blue">查詢全部設施相關資料</strong> <a
						href='listFac.jsp'>List</a> all Facilities.
				</p>
			</div>
			<div class="d-flex text-muted pt-3">
				<img class="me-3" src="./svg/tree.svg" alt="" width="48" height="38">
				<p class="pb-3 mb-0 small lh-sm border-bottom">
					<strong class="d-block text-gray-dark">由設施編號查詢</strong>
				</p>
				<ul>
					<li>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/facilities/facilities.do">
							<select size="1" name="facilitiesId">
								<c:forEach var="facilitiesVO" items="${facilitiesSvc.all}">
									<option value="${facilitiesVO.facilitiesId}">${facilitiesVO.facilitiesId}
								</c:forEach>
							</select> <input type="hidden" name="action"
								value="getOneFacilities_For_Display"> <input type="submit"
								value="送出">
						</FORM>
					</li>
				</ul>
			</div>
		</div>
	</main>

	<script
		src="<%=request.getContextPath()%>/camprelease/js/popper.min.2.10.2.js"></script>
</body>
</html>