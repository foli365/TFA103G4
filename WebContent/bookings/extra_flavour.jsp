<%@page import="com.camporder.model.CampOrderVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.plan.model.*"%>
<%
	PlanService planSvc = new PlanService();
	CampOrderVO campOrderVO = (CampOrderVO) request.getAttribute("campOrderVO");
	List<PlanVO> planList = planSvc.getOnePlan(campOrderVO.getCampId());
	pageContext.setAttribute("list", planList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
<%@ include file="/template/navbarforCampCart.html"%>
<style type="text/css">
i:hover {
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="container my-3">
		<div class="row">
			<div class="col-8"
				style="padding: 35px 50px; width: 800px; border: 1px solid #EBEBEB;">
				<h3>讓露營不再只是露營</h3>
				<P>加選以下的配套活動讓旅程更添風味</P>
				<c:forEach var="campPlanVO" items="${list}">
					<div class="card">
						<div class="card-body">
							<h5 class="card-title">${campPlanVO.planName}</h5>
							<h6 class="card-subtitle mb-2 text-muted">${campPlanVO.planPrice}/每人</h6>
							<p class="card-text">${campPlanVO.planOutline}</p>
							<div class="btn btn-primary add">加至行程</div>
							<div class="adjustPlan" style="display: none;">
								<button class="remove" style="border: 0px; background: white; color: gray">移除</button>
								<i class="fas fa-minus-circle fa-2x minus" style="color: green;"></i>
								<span style="font-size: 2rem" class="total" id="${campPlanVO.planId}">0</span>
								<i class="fas fa-plus-circle fa-2x plus" style="color: green;"></i>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="col-4 d-flex justify-content-center">
				<div class="card" style="width: 16rem;">
					<img src="" class="card-img-top" alt="camp-site-image">
					<div class="card-body">
						<h5 class="card-title">那山那谷</h5>
						<p class="card-text">Some quick example text to build on the
							card title and make up the bulk of the card's content.</p>
					</div>
					<ul class="list-group list-group-flush">
						<li class="list-group-item">入住日期: ${campOrderVO.checkInDate}</li>
						<li class="list-group-item">退住日期: ${campOrderVO.checkOutDate}</li>
						<li class="list-group-item">人數: ${campOrderVO.guestNumber}</li>
						<li class="list-group-item">
							<h5 style="display: inline;">總價:</h5>
							<h5 style="display: inline;">${campOrderVO.orderTotal}</h5>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		$(".card-body").on("click", ".add", function() {
			let add = $(this).closest(".add");
			let adjust = $(add).next(".adjustPlan");
			$(add).toggleClass("d-none")
			$(adjust).show();
		})
		$(".adjustPlan").on("click", ".remove", function() {
			let adjustPlan = $(this).parent();
			let add = adjustPlan.prev(".add")
			$(adjustPlan).hide()
			$(add).toggleClass("d-none")
		})
		$(".adjustPlan").on("click", ".plus", function() {
			let count = 0;
			let adjustPlan = $(this).parent();
			let add = adjustPlan.prev(".add")
			$(adjustPlan).hide()
			$(add).toggleClass("d-none")
		})
	</script>
</body>
</html>