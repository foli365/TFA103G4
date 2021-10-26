<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.campsitetentstatus.model.CampsiteTentStatusService"%>
<%@ page import="com.campsite.model.*"%>
<%@ page import="com.members.model.*"%>
<%
	CampsiteService campsiteService = new CampsiteService();
	CampsiteVO campsiteVO = campsiteService.getOneCampsite(Integer.parseInt(request.getParameter("campId")));
	pageContext.setAttribute("campsiteVO", campsiteVO);
%>
<%
	if (session.getAttribute("id") == null) {
		session.setAttribute("location", (request.getRequestURI() + "?campId=" + request.getParameter("campId")));
	}
	pageContext.setAttribute("memberId", (Integer) session.getAttribute("id"));
	pageContext.setAttribute("campId", 5001);
	Integer guestCount = null;
	try {
		guestCount = new Integer(request.getParameter("guestCount"));
	} catch (NumberFormatException nfe) {
		guestCount = null;
	}
	CampsiteTentStatusService CTSSvc = new CampsiteTentStatusService();
	pageContext.setAttribute("unavilibleList", CTSSvc.getUnavailibleDatewithGuestNumberOnly(5001, 1));
%>
<%
	MemberService memberService = new MemberService();
	MembersVO membersVO = memberService.findByPrimaryKey(campsiteVO.getMemberId()); //取得Member的電話號碼
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>預定露營地</title>
<%@ include file="/template/navbar.jsp"%>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.css">
<!-- 日期選擇器 的 CSS -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<!-- 載入 CSS -->
<link rel="stylesheet" href="./vendors/bootstrap/css/ReserveCamp.css">
</head>

<body style="background-color: #fbefe7;">

	<div class="container text-center my-3">
		<div class="row mx-auto my-auto justify-content-center">
			<div id="recipeCarousel" class="carousel slide"
				data-bs-ride="carousel">
				<div class="carousel-inner" role="listbox">
					<div class="carousel-item active">
						<div class="col-md-3">
							<div class="card">
								<div class="card-img">
									<img
										src="<%=request.getContextPath()%>/CampsiteGifReader?column=picture1&camp_id=${campsiteVO.campId}"
										class="img-fluid">
								</div>
							</div>
						</div>
					</div>
					<div class="carousel-item">
						<div class="col-md-3">
							<div class="card">
								<div class="card-img">
									<img
										src="<%=request.getContextPath()%>/CampsiteGifReader?column=picture2&camp_id=${campsiteVO.campId}"
										class="img-fluid">
								</div>
							</div>
						</div>
					</div>
					<div class="carousel-item">
						<div class="col-md-3">
							<div class="card">
								<div class="card-img">
									<img
										src="<%=request.getContextPath()%>/CampsiteGifReader?column=picture3&camp_id=${campsiteVO.campId}"
										class="img-fluid">
								</div>
							</div>
						</div>
					</div>
					<div class="carousel-item">
						<div class="col-md-3">
							<div class="card">
								<div class="card-img">
									<img
										src="<%=request.getContextPath()%>/CampsiteGifReader?column=picture4&camp_id=${campsiteVO.campId}"
										class="img-fluid">
								</div>
							</div>
						</div>
					</div>
					<div class="carousel-item">
						<div class="col-md-3">
							<div class="card">
								<div class="card-img">
									<img
										src="<%=request.getContextPath()%>/CampsiteGifReader?column=picture5&camp_id=${campsiteVO.campId}"
										class="img-fluid">
								</div>
							</div>
						</div>
					</div>
				</div>
				<a class="carousel-control-prev bg-transparent w-aut"
					href="#recipeCarousel" role="button" data-bs-slide="prev"> <span
					class="carousel-control-prev-icon" aria-hidden="true"></span>
				</a> <a class="carousel-control-next bg-transparent w-aut"
					href="#recipeCarousel" role="button" data-bs-slide="next"> <span
					class="carousel-control-next-icon" aria-hidden="true"></span>
				</a>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-8 camp-content">
				<div class="camp-title">
					<h2 class="camp-name ">${campsiteVO.campName}</h2>
					<p class="addr ">地址: ${campsiteVO.location}</p>
					<p class="cel ">
						電話:
						<%=membersVO.getPhone()%></p>
				</div>
				<div class="camp-detail">
					<p>${campsiteVO.campDescription}</p>
				</div>
				<div class="camp-comment">
					<p>營地評論</p>
				</div>
			</div>
			<div class="col-4 order-menu">
				<form action="<%=request.getContextPath()%>/campOrder.do"
					method="post">
					<h2 class="camp-price">$${campsiteVO.campPrice}</h2>
					<small class="avg-person">(平均每人一晚)</small>
					<hr>
					<p class="customer-num">人數:</p>
					<input type="text" class="form-control" name="headCount"
						id="headCount" value="" autocomplete="off">
					<hr>
					<p class="check-in">入住日期:</p>
					<input class="flatpickr flatpickr-input active" id="selectDate"
						name="date" type="text" placeholder="請選擇範圍..."
						data-id="rangePlugin" readonly="readonly" autocomplete="off">
					<input type="hidden" id="from" name="from" value=""> <input
						type="hidden" id="to" name="to" value="">
					<hr>
					<h3 style="padding-bottom: 20px;">
						總價: <span id="price"></span>
					</h3>
					<small style="color: red">${missing}</small> <small
						style="color: red">${noSession}</small> <small style="color: red">${noSpace}</small>
					<small style="color: red">${repeat}</small>
					<hr>
					<div class="d-flex justify-content-center">
						<button style="margin-bottom: 20px;" type="submit"
							class="btn btn-success btn-lg" id="book">預定</button>
						<input type="hidden" name="action" value="book"> <input
							type="hidden" name="memberId" id="memberId" value="${memberId}">
						<input type="hidden" name="campId" value="${campId}"> <input
							type="hidden" id="orderDate" name="orderDate" value=""> <input
							type="hidden" id="deadline" name="deadline" value=""> <input
							type="hidden" id="price" name="price" value="">
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal fade" id="loginFilter" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">提醒</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">在您預定營地前，請先登入</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">取消</button>
					<a
						href="<%=request.getContextPath()%>/register_and_login/login.jsp"
						class="btn btn-primary">登入</a>
				</div>
			</div>
		</div>
	</div>
	<!-- 載入Slider 的 JS -->
	<script>
	let items = document.querySelectorAll('.carousel .carousel-item')

	items.forEach((el) => {
	    const minPerSlide = 3
	    let next = el.nextElementSibling
	    for (var i = 1; i < minPerSlide; i++) {
	        if (!next) {
	            // wrap carousel by using first child
	            next = items[0]
	        }
	        let cloneChild = next.cloneNode(true)
	        el.appendChild(cloneChild.children[0])
	        next = next.nextElementSibling
	    }
	})

	window.onload =
	    function() {
	        var omDiv = document.getElementsByClassName("order-menu")[0],
	            H = -50,
	            Y = omDiv
	        while (Y) {
	            H += Y.offsetTop;
	            Y = Y.offsetParent;
	        }
	        window.onscroll = function() {
	            var s = document.body.scrollTop || document.documentElement.scrollTop
	            if (s > H) {
	                omDiv.style = "position:fixed;top:55px;right:113px"
	            } else {
	                omDiv.style = ""
	            }
	        }
	    }
	</script>
	<%@ include file="/template/script.html"%>
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.js"></script>
	<script type="text/javascript">
	$("#book").click(function (e) {
		if ($("#memberId").val() == '') {
			event.preventDefault();
			$("#loginFilter").modal("show");
		}
	})
		$(document).ready(function() {
			var today = new Date();
			var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
			var deadline = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+(today.getDate()+2);
			var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
			console.log(date);
			$("#orderDate").val(date+' '+time);
			$("#deadline").val(deadline+' '+time);
		})
		let daysBetween;
		const flatpickr = $("#selectDate").flatpickr(
				{
					mode: "range",
					altInput: true,
					altFormat: "F j",
					dateFormat: "Y-m-d",
					minDate: "today",
					maxDate: new Date().fp_incr(60), // 30 days from now
					showMonths : 2,
					disable: ${unavilibleList},
					locale: {
						weekdays: {
							shorthand: [ "日", "一", "二", "三", "四", "五",
									"六" ],
							longhand: [ "日", "一", "二", "三", "四",
									"五", "六", ],
						},
						months: {
							shorthand: [ "1月", "2月", "3月", "4月", "5月", "6月",
									"7月", "8月", "9月", "10月", "11月", "12月", ],
							longhand: [ "1月", "2月", "3月", "4月", "5月", "6月",
									"7月", "8月", "9月", "10月", "11月", "12月", ],
						},
						rangeSeparator: " 至 ",
						weekAbbreviation: "週",
						scrollTitle: "滾動切換",
						toggleTitle: "點擊切換 12/24 小時時制",
					},
					onChange: function(dates) {
				        if (dates.length == 2) {
				            let start = flatpickr.formatDate(dates[0],"Y-m-d");
				            let end = flatpickr.formatDate(dates[1], "Y-m-d");
				            $("#from").val(start);
				            $("#to").val(end);				            
				            if (start && end) {
				                console.log({ start, end });
				              }
				            let newStart = new Date(start).getTime();
				            let newend = new Date(end).getTime();
				            daysBetween = eval((newend - newStart)/86400/1000);
				            console.log(daysBetween);
				            if (daysBetween != null && $("#headCount").val() > 0) {
				    			let price = daysBetween * $("#headCount").val() * ${campsiteVO.campPrice};
				    			$("#price").text(price);
				    			$("[name='price']").val(price);
				    			$("#headCounts").val($("#headCount").val());
				    		}
				        }
				    }
				});
		$("#headCount").change(function() {	
		if (daysBetween != null && $("#headCount").val() > 0) {
			let price = daysBetween * $("#headCount").val() * ${campsiteVO.campPrice};
			$("#price").text(price);
			$("[name='price']").val(price);
			}
		let campId = $("[name='campId']").val();
		let totalGuest = $("#headCount").val();
		let json = [campId, totalGuest];
		console.log(json);
		$.ajax({
            type: "post",
            url: "<%=request.getContextPath()%>/availibleDate",
            contentType: "application/json",
            data: JSON.stringify(json),
            success: function (response) {
            	var array = JSON.parse(response);
            	flatpickr.clear();
            	$("#price").text("");
            	flatpickr.set("disable", array);
             },
        });
		})
	</script>
	<!-- 日期選擇器 的 JS -->
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
	<!-- 載入 Font Awesome -->
	<script src="https://kit.fontawesome.com/846e361093.js"
		crossorigin="anonymous"></script>
	<!-- 載入 JS -->
	<script
		src="<%=request.getContextPath()%>/campsite/vendors/bootstrap/js/ReserveCamp.js"></script>
</body>

</html>