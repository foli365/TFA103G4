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
	System.out.println(planList);
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
	<input type="hidden" value="${campOrderVO.guestNumber}" id="maxPerson">
	<input type="hidden" value="${campOrderVO.orderTotal}" id="totalPrice">
	<div class="container my-3">
		<div class="row">
			<div class="col-6"
				style="padding: 35px 50px; border: 1px solid #EBEBEB;">
				<h3>讓露營不再只是露營</h3>
				<P>加選以下的配套活動讓旅程更添風味</P>
				<c:forEach var="campPlanVO" items="${list}">
					<div class="card">
						<div class="card-body">
							<h5 class="card-title">${campPlanVO.planName}</h5>
							<h6 class="card-subtitle mb-2 text-muted">${campPlanVO.planPrice}<span>/每人</span></h6>
							<input type="hidden" id="plan${campPlanVO.planId}planPrice" value="${campPlanVO.planPrice}">
							<p class="card-text">${campPlanVO.planOutline}</p>
							<div class="btn btn-success add">加至行程</div>
							<div class="adjustPlan" style="display: none;">
								<button class="remove" style="border: 0px; background: white; color: gray">移除</button>
								<i class="btn fas fa-minus-circle fa-2x minus" style="color: green;"></i>
								<span style="font-size: 2rem" class="total">0</span>
								<i class="btn fas fa-plus-circle fa-2x plus" style="color: green;"></i>
								<input type="hidden" class="planId" value="${campPlanVO.planId}">
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="col-6 d-flex justify-content-center">
				<div class="card" style="width: 16rem;">
					<img src="" class="card-img-top" alt="camp-site-image">
					<div class="card-body">
						<h5 class="card-title">那山那谷</h5>
						<p class="card-text">Some quick example text to build on the
							card title and make up the bulk of the card's content.</p>
					</div>
					<ul class="list-group list-group-flush">
						<li class="list-group-item">入住日期: <span class="float-end">${campOrderVO.checkInDate}</span></li>
						<li class="list-group-item">退住日期: <span class="float-end">${campOrderVO.checkOutDate}</span></li>
						<li id="afterHere" class="list-group-item">人數: <span class="float-end">${campOrderVO.guestNumber}</span></li>
						<li class="list-group-item">
							<h5 style="display: inline;">總價:</h5>
							<h5 style="display: inline;"><span class="float-end total">${campOrderVO.orderTotal}</span></h5>
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
	let max = $("#maxPerson").val();
	let totalPrice = $("#totalPrice").val();
	console.log($("#totalPrice").val());
		$(".card-body").on("click", ".add", function() {
			let add = $(this).closest(".add");
			let adjust = $(add).next(".adjustPlan");
			$(add).toggleClass("d-none")
			$(adjust).show();
		})
		$(".adjustPlan").on("click", ".remove", function() {
			let adjustPlan = $(this).parent();
			let add = adjustPlan.prev(".add");
			let planId = $(this).siblings(".planId").val();
			$(this).siblings(".total").text("0");
			$(adjustPlan).hide();
			$(add).toggleClass("d-none");
			$("#" + planId).parent().remove();
		})
		$(".adjustPlan").on("click", ".plus", function() {
			let planId = $(this).siblings(".planId").val();
			let value = parseInt($(this).prev().text())
			let plus1 = value + 1;
			if(plus1 > max){
				plus1 = max;
			}
			$(this).prev().text(plus1);
			let planName = $(this).parent().siblings('.card-title').text();
			let planPrice = $("#plan" + planId +"planPrice").val();
			let total = planPrice * plus1;
			if(plus1 === 1){				
				let li = '<li class="list-group-item">' + planName + '<small id="'+planId+'">/1位</small><span class="float-end '+ planId +'">' + total + '</span></li>';
				$("#afterHere").after(li);
				$(".float-end .total").text(totalPrice + total)
			} else if (plus1 > 1) {
				$("#" + planId).text("/" + plus1 + "位");
				$(".float-end" +"." +  planId).text(total);
			}
		})
		$(".adjustPlan").on("click", ".minus", function() {
			let planId = $(this).siblings(".planId").val();
			let value = parseInt($(this).next().text());
			let planPrice = $("#plan" + planId +"planPrice").val();
			let minus1 = value - 1;
			let total = planPrice * minus1;
			if(minus1 > 0){				
				$(this).next().text(minus1);
				$("#" + planId).text("/" + minus1 + "位");
				$(".float-end" +"." +  planId).text(total);
				$(".float-end .total").text(totalPrice - total)
			} else if (minus1 === 0){
				$(this).next().text(minus1);
				$("#" + planId).parent().remove();
			}
		})
	</script>
</body>
</html>