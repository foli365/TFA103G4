<%@page import="java.util.concurrent.TimeUnit"%>
<%@page import="com.post.model.PostVO"%>
<%@page import="com.post.model.PostService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%
	PostService postSvc = new PostService();
	PostVO postVO = postSvc.findByPostId(Integer.parseInt(request.getParameter("postId")));
	pageContext.setAttribute("postId", Integer.parseInt(request.getParameter("postId")));
	pageContext.setAttribute("postVO", postVO);
%>
<jsp:useBean id="memSvc" class="com.members.model.MemberService"
	scope="request"></jsp:useBean>
<jsp:useBean id="commentSvc"
	class="com.postComment.model.PostCommentService" scope="page"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<link rel="stylesheet" href="post.css" />
<style>
button:focus {
	/* border: none !important; */
	outline: none !important;
	box-shadow: none !important;
}
#userImage {
	background-size: cover;
	max-width: 50px;
	height: 50px;
	max-height: 50px;
	border-radius: 50%;
	display: inline-block;
	background-image:
		url('data:image/jpg;base64,${memSvc.findByPrimaryKey(postVO.authorId).base64Image}');
	max-height: 50px;
}
#option {
	background-color: white;
	border: 0px;
	color: #6E6865;
	font-size: 10px;
	margin-top: 15px;
	margin-left: 15px;
}
</style>
<title>${postVO.title}</title>
</head>
<body style="background-color: #484442">
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
	<div class="container" id="main"
		style="padding-bottom: 25px; margin-top: 50px; max-width: 1100px; background-color: white; border-radius: 10px">
		<div class="container">
			<div class="px-3">
				<div class="row">
				<h1 class="col-11" style="padding-top: 30px;">${postVO.title}</h1>
				<div class="col-1 text-end">
					<div class="btn">
						<button id="option" style="border: 0px;" class="btn btn-outline-secondary btn-sm" type="button"
							data-bs-toggle="dropdown" aria-expanded="false">
							<i class="fas fa-ellipsis-v fa-2x"></i>
						</button>
						<ul class="dropdown-menu" style="width: 60px">
							<li class="dropdown-item text-end">
								<button data-bs-toggle="modal"s
									data-bs-target="#popEdit${postVO.postId}" style="background-color: white; border: 0px">編輯</button>
							</li>
							<li class="dropdown-item text-end">
								<form method="post"
									action="<%=request.getContextPath()%>/post/post.do">
									<input id="delete" style="background-color: white; border: 0px"
										type="submit" value="刪除"> <input type="hidden"
										name="action" value="delete"> <input type="hidden"
										name="postId" value="${postVO.postId}">
								</form>
							</li>
						</ul>
					</div>
				</div>
				</div>
				<div class="row mb-3 px-2 py-2">
					<div class="col-1" id="userImage"></div>
					<div class="col-2">
						<small>${memSvc.findByPrimaryKey(postVO.authorId).name}<br>
							${postVO.passed}
						</small>
					</div>
				</div>
				${postVO.article}
				<div class="col" id="filter" style="margin-bottom: 20px">
					<button type="button" style="border: 0px;"
						class="btn btn-outline-secondary p-2">
						<i class="far fa-comment-dots" style="margin-right: 5px;"></i>${commentSvc.findByPostId(postVO.postId).size()}
					</button>
					<button type="button" style="border: 0px;"
						class="btn btn-outline-secondary p-2">
						<i class="far fa-flag" style="margin-right: 5px;"></i>檢舉
					</button>
				</div>
				<form class="pt-2"
					action="<%=request.getContextPath()%>/post/comment.do"
					method="post">
					<textarea style="resize: none;" placeholder="快來發表你的評論吧~"
						class="form-control" name="comment"
						id="exampleFormControlTextarea1" rows="4"></textarea>
					<button type="submit" class="btn btn-outline-secondary float-end"
						style="margin-top: 15px">發送</button>
					<input type="hidden" name="action" value="insert"> <input
						type="hidden" name="postId" value="${postId}">
				</form>
				<div style="margin-top: 90px">
					<hr>
				</div>
				<div class="container">
					<c:forEach var="commentVO"
						items="${commentSvc.findByPostId(postVO.postId)}">
						<div class="row mb-3 py-2">
							<div class="col-1" id="userImage"></div>
							<div class="col-2">
								<small>${memSvc.findByPrimaryKey(commentVO.memberId).name}<br>
									${commentVO.passed}
								</small>
							</div>
						</div>
						<div>${commentVO.content}</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<div></div>

	</div>




	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>