<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("name", session.getAttribute("account"));
%>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<nav class="navbar navbar-expand-md navbar-light sticky-top"
	style="background-color: #fbefe7;">
	<div class="container-fluid">
		<a class="navbar-brand ms-lg-5" href="<%=request.getContextPath()%>/homepage/index.jsp"
			style="font-size: 1.25em;">GoCamping</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<form class="d-flex">
				<input class="form-control me-2 rounded-pill ml-0" type="search"
					placeholder="Search" aria-label="Search">
				<button id="searchIcon" class="btn" type="submit" style="padding: 0">
					<i class="bi bi-search"></i>
				</button>
			</form>
			<ul class="navbar-nav ms-auto mb-2 mb-lg-0 me-xl-5">
				<li class="nav-item"><a id="hosting" class="nav-link" href="<%=request.getContextPath()%>/camprelease/addCampRel.jsp"
					style="color: green;">上架營地</a></li>
				<li class="nav-item"><a class="nav-link" href="#"
					style="color: #E40580;">商城</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/post/index.jsp" style="color: #0B83ED;">論壇</a></li>
				<li class="nav-item" id="register"><a class="nav-link"
					href="<%=request.getContextPath()%>/register_and_login/register.jsp">註冊</a></li>
				<li class="nav-item" id="login"><a class="nav-link"
					href="<%=request.getContextPath()%>/register_and_login/login.jsp">登入</a></li>
				<li class="nav-item dropdown" id="accountName"><a
					style="font-weight: bold;" class="nav-link dropdown-toggle"
					href="#" id="navbarDropdown" role="button"
					data-bs-toggle="dropdown" aria-expanded="false"> ${name} </a>
					<ul class="dropdown-menu dropdown-menu-end "
						aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item"
							href="<%=request.getContextPath()%>/account/account_center.jsp">會員中心</a></li>
						<li><a class="dropdown-item"
							href="<%=request.getContextPath()%>/account/camp_management.jsp">營地管理</a></li>
						<li><a class="dropdown-item"
							href="<%=request.getContextPath()%>/account/edit_profile.jsp">編輯會員資料</a></li>
						<li>
							<hr class="dropdown-divider">
						</li>
						<li><a class="dropdown-item" href="<%=request.getContextPath()%>/account/logout.do">登出</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	<input type="hidden" id="account" value="${name}">
</nav>