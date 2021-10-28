<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<% pageContext.setAttribute("token", request.getParameter("token")); %>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<%@ include file="/template/navbar.jsp"%>
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<style type="text/css">
body {
	background: url("<%=request.getContextPath()%>/img/verified.jpg")
		no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}
</style>
</head>
<body>
	<input type="hidden" id="exist" value="${exist}">
	<input type="hidden" id="invalid" value="${invalid}">
	<input type="hidden" id="success" value="${success}">
	<div
		style="max-width: 550px; margin-top: 100px; margin-bottom: -100px;"
		class="alert alert-danger mx-auto d-none" role="alert" id="existNotice">
		<h4 class="alert-heading">
			<i class="fas fa-exclamation-triangle"></i>&nbsp;${exist}
		</h4>
		<hr>
		�ѰO�K�X�F��?<a
			href="<%=request.getContextPath()%>/register_and_login/search_by_email.jsp"
			class="alert-link">�ѰO�K�X</a>
	</div>
	<div
		style="max-width: 550px; margin-top: 100px; margin-bottom: -100px;"
		class="alert alert-danger mx-auto d-none" role="alert" id="invalidNotice">
		<h4 class="alert-heading">
			<i class="fas fa-exclamation-triangle"></i>&nbsp;${invalid}!
		</h4>
		<hr>
		<span id="afterhere">�Э��s�H�e���ҫH�C</span><a id="resend" href="" class="alert-link">�H�e</a>
	</div>
	<div
		style="max-width: 550px; margin-top: 100px; margin-bottom: -100px;"
		class="d-none alert alert-success mx-auto" role="alert"
		id="successNotice">
		<h4 class="alert-heading">
			<i class="fas fa-check-circle"></i>&nbsp;${success}!
		</h4>
		<hr>
		�N�󤭬�����a�z�^�쭺��
	</div>
	<%@ include file="/template/script.html" %>
	<script type="text/javascript">
	$(document).ready(function() {
		if ($("#invalid").val()) {
			$("#invalidNotice").removeClass("d-none");
		} else if($("#success").val()) {
			$("#successNotice").removeClass("d-none");
		} else if($("#exist").val()) {
			$("#existNotice").removeClass("d-none");
		}
	});
	if ($("#success").val()) {
		window.setTimeout(function() {
			window.location.href = "<%=request.getContextPath()%>/homepage/index.jsp";}
		, 4000)}
	$("#resend").on("click", function() {
		let token = `${token}`;
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/resend",
			contentType: "text",
			data: token,
			success: function(res) {
				$("#resend").remove();
				let small = '<small id="countdown">30</small>';
				$("#afterhere").after(small);
				function countdown() {
					let timer = parseInt($("#countdown").text());
					if (timer == 0) {
						$("#countdown").remove();
						$("#afterhere").after('<a id="resend" href="" class="alert-link">�H�e</a>');
						return;
					}
					let newtime = timer-1;
					$("#countdown").text(newtime);
				}
				setInterval(countdown, 1000);
			}
		})
	})
	
	</script>
</body>
</html>