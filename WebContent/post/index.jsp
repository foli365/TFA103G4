<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.post.model.*"%>
<%@ page import="java.util.*"%>

<%
	PostService postSvc = new PostService();
	List<PostVO> list = postSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="ZH-TW">

<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>論壇</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet" href="./index.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" />
<link rel="stylesheet"
	href="./summernote-0.8.18-dist/summernote-lite.css">
</head>

<body>
	<nav class="navbar navbar-expand-md navbar-light sticky-top"
		style="background-color: #fbefe7">
		<div class="container-fluid">
			<a class="navbar-brand ms-lg-5" href="../Homepage/index.html"
				style="font-size: 1.25em">GoCamping</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<form class="d-flex">
					<input class="form-control me-2 rounded-pill ml-0" type="search"
						placeholder="Search" aria-label="Search" />
					<button id="searchIcon" class="btn" type="submit"
						style="padding: 0">
						<i class="bi bi-search"></i>
					</button>
				</form>
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0 me-xl-5">
					<li class="nav-item"><a id="hosting" class="nav-link" href="#"
						style="color: green">上架營地</a></li>
					<li class="nav-item"><a class="nav-link" href="#"
						style="color: #e40580">商城</a></li>
					<li class="nav-item"><a class="nav-link" href="#"
						style="color: #0b83ed">論壇</a></li>
					<li class="nav-item"><a class="nav-link"
						href="../RegisterAndLogin/register.html">註冊</a></li>
					<li class="nav-item"><a class="nav-link"
						href="../RegisterAndLogin/login.html">登入</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							會員姓名 </a>
						<ul class="dropdown-menu dropdown-menu-end"
							aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item"
								href="../Account/accountCenter.html">會員中心</a></li>
							<li><a class="dropdown-item"
								href="../Account/editProfile.html">編輯會員資料</a></li>
							<li>
								<hr class="dropdown-divider" />
							</li>
							<li><a class="dropdown-item" href="#">登出</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container mt-3" style="max-width: 1600px">
		<div class="row justify-content-center">
			<div class="col" style="max-width: 800px">
				<div class="row mb-3 p-1"
					style="border: 1px solid #d3d4d6; background-color: white; border-radius: 5px;">
					<div class="col-1">
						<img
							style="max-width: 50px; border-radius: 50%; display: inline-block;"
							src="./img/avatar.jpg" alt="" />
					</div>

					<div class="col-11 my-auto">
						<button type="button" class="btn btn-light" style="width: 100%"
							data-bs-toggle="modal" data-bs-target="#popUp">
							<input id="createArtical" type="text" placeholder="發表文章"
								class="form-control"/>
						</button>
					</div>
				</div>
				<div class="row mb-3 p-1 justify-content-center"
					style="border: 1px solid #d3d4d6; background-color: white; border-radius: 5px;">
					<div class="col" id="filter">
						<button type="button" style="border: 0px;"
							class="btn btn-outline-secondary p-2">熱門文章</button>
						<button type="button" style="border: 0px;"
							class="btn btn-outline-secondary p-2">最新文章</button>
					</div>
				</div>
				<div class="modal fade" id="popUp" data-bs-backdrop="static"
					data-bs-keyboard="false" tabindex="-1"
					aria-labelledby="staticBackdropLabel" aria-hidden="true">
					<div class="modal-dialog modal-xl">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="staticBackdropLabel">建立文章</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<FORM method="POST"
									ACTION="<%=request.getContextPath()%>/post/post.do">
									<div class="mb-1">
										<input name="title" type="text" placeholder="文章標題"
											class="form-control" id="recipient-name" />
									</div>
									<div class="mb-3">
										<textarea name="article" id="summernote" cols="30" rows="30"></textarea>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">取消</button>
										<button type="submit" class="btn btn-primary">完成</button>
										<input type="hidden" name="action" value="insert">
									</div>
								</FORM>
							</div>
						</div>
					</div>
				</div>
				<c:forEach var="postVO" items="${list}">
					<p>${postVO.title}</p>
				</c:forEach>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script src="./summernote-0.8.18-dist/summernote-lite.js"></script>
	<script src="./summernote-0.8.18-dist/lang/summernote-zh-TW.js"></script>
	<script>
	$(document).ready(function () {
		$('#summernote').summernote({
			spellCheck: false,
			toolbar: [
				['style', ['style']],
				['font', ['bold', 'italic', 'clear']],
				['fontsize', ['fontsize']],
				['color', ['color']],
				['para', ['ul', 'ol', 'paragraph']],
				['insert', ['picture']],
				['height', ['height']]
			],
			lang: 'zh-TW',
			lineHeights: ['0.4', '0.5', '0.6', '0.8', '1.0', '1.2', '1.4', '1.5', '2.0', '3.0'],
			placeholder: "寫些什麼吧"
		});
		$('div.note-editable').height(500);
	});
	</script>
</body>

</html>