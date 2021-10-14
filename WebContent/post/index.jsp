<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.post.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="java.util.*"%>

<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	PostService postSvc = new PostService();
	List<PostVO> list = postSvc.getAll();
	pageContext.setAttribute("list", list);
	Object account = session.getAttribute("account");
	MembersDAO dao = new MembersDAO();
	if (session.getAttribute("id") != null) {
		MembersVO memVO = dao.findByPrimaryKey((Integer) session.getAttribute("id"));
		pageContext.setAttribute("memberVO", memVO);
	} else {
		session.setAttribute("location", request.getRequestURI());
	}
%>
<jsp:useBean id="memSvc" class="com.members.model.MemberService" scope="request"></jsp:useBean>
<!DOCTYPE html>
<html lang="ZH-TW">

<head>
	<meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>論壇</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" />
	<link rel="stylesheet" href="./summernote-0.8.18-dist/summernote-lite.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
	<%@ include file="/template/navbar.jsp" %>
	<style type="text/css">
			#author {
				text-decoration: none;
				color: #6C757D;
			}

			#author:hover {
				text-decoration: underline;
			}

			#post {
				box-shadow: 0px 0px 2px rgba(0, 0, 0, 0.50);
				max-height: 350px;
				position: relative;
				overflow: hidden;
			}

			.read-more {
				position: absolute;
				bottom: 0;
				left: 0;
				width: 100%;
				text-align: center;
				margin: 0;
				padding: 30px 0;
				/* "transparent" only works here because == rgba(0,0,0,0) */
				background-image: linear-gradient(to bottom, transparent, white);
			}

			#post:hover {
				border: 0.5px solid black;
				transition: all 0.2s ease-in;
			}

			#option {
				background-color: white;
				border: 0px;
				color: black;
			}

			#delete:hover {
				background-color: #E9ECEF;
			}

			#edit:hover {
				background-color: #E9ECEF;
			}

			body {
				background-color: #DAE0E6;
			}

			img {
				max-width: 100%;
			}

			button:focus {
				/* border: none !important; */
				outline: none !important;
				box-shadow: none !important;
			}

			textarea:focus {
				border: none !important;
				outline: none !important;
				box-shadow: none !important;
			}

			input:focus {
				outline: none !important;
				box-shadow: none !important;
			}

			.note-editor .dropdown-toggle::after {
				all: unset;
			}

			.note-editor .note-dropdown-menu,
			.note-editor .note-modal-footer {
				box-sizing: content-box;
			}

			.note-editor .note-editable {
				line-height: 0.9;
			}
		</style>
</head>

<body style="background-color: #DAE0E6;">
	<div class="container mt-3" style="max-width: 1600px">
		<div class="row justify-content-center">
			<div class="col" style="max-width: 800px">
				<div class="row mb-3 p-1 justify-content-center"
					style="border: 1px solid #d3d4d6; background-color: white; border-radius: 5px;">
					<div class="col-1"
						style="background-size: cover; max-width: 50px; max-height: 50px; border-radius: 50%; display: inline-block; background-image: url('data:image/jpg;base64,${memberVO.base64Image}')">
					</div>
					<div class="col-11 my-auto">
						<button type="button" id="createPost" class="btn btn-light" style="width: 100%">
							<input id="createArtical" type="text" placeholder="發表文章" class="form-control" />
						</button>
					</div>
				</div>
				<div class="row mb-3 p-1 justify-content-center"
					style="border: 1px solid #d3d4d6; background-color: white; border-radius: 5px;">
					<div class="col" id="filter">
						<button type="button" style="border: 0px;" class="btn btn-outline-secondary p-2">熱門文章</button>
						<button type="button" style="border: 0px;" class="btn btn-outline-secondary p-2">最新文章</button>
					</div>
				</div>
				<div class="modal fade" id="popUp" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
					aria-labelledby="staticBackdropLabel" aria-hidden="true">
					<div class="modal-dialog modal-xl">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="staticBackdropLabel">建立文章</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<FORM method="POST" ACTION="<%=request.getContextPath()%>/post/post.do">
									<div class="mb-1">
										<input name="title" type="text" placeholder="文章標題" class="form-control"
											id="recipient-name" />
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
				<div class="modal fade" id="loginFilter" tabindex="-1" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">提醒</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">在您發表文章前，請先登入</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
								<a href="<%=request.getContextPath()%>/register_and_login/login.jsp"
									class="btn btn-primary">登入</a>
							</div>
						</div>
					</div>
				</div>
				<c:forEach var="postVO" items="${list}">
					<div id="post" class="row card mb-3" style="max-width: 800px">
						<div class="row g-0">
							<div class="col">
								<div class="card-body">
									<div class="row">
										<div class="col-11">
											<p class="card-text">
												<small class="text-muted">發布於 ${postVO.passed} - <a id="author"
														href="">${memSvc.findByPrimaryKey(postVO.authorId).name}</a></small>
											</p>
											<h4 class="card-title">
												<a href="<%=request.getContextPath()%>/post/post.jsp?postId=${postVO.postId}"
													style="text-decoration: none; color: inherit;">${postVO.title}</a>
											</h4>
										</div>
										<div class="col-1 text-end">
											<div class="btn-group dropend">
												<button id="option" class="btn btn-secondary btn-sm" type="button"
													data-bs-toggle="dropdown" aria-expanded="false">
													<i class="fas fa-ellipsis-v"></i>
												</button>
												<ul class="dropdown-menu">
													<li class="dropdown-item text-end">
														<button data-bs-toggle="modal"
															data-bs-target="#popEdit${postVO.postId}">編輯</button>
													</li>
													<li class="dropdown-item text-end">
														<form method="post"
															action="<%=request.getContextPath()%>/post/post.do">
															<input id="delete"
																style="background-color: white; border: 0px"
																type="submit" value="刪除"> <input type="hidden"
																name="action" value="delete"> <input type="hidden"
																name="postId" value="${postVO.postId}">
														</form>
													</li>
												</ul>
											</div>
										</div>
									</div>
									<p class="card-text">${postVO.article}</p>
									<p class="read-more"></p>
								</div>
							</div>
						</div>
					</div>
					<div class="modal fade" id="popEdit${postVO.postId}" data-bs-backdrop="static"
						data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
						<div class="modal-dialog modal-xl">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="staticBackdropLabel">編輯文章</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">
									<FORM method="POST" ACTION="<%=request.getContextPath()%>/post/post.do">
										<div class="mb-1">
											<input name="title" type="text" placeholder="文章標題" class="form-control"
												id="recipient-name" value="${postVO.title}" />
										</div>
										<div class="mb-3">
											<textarea name="article" class="summernote" id="summernote2" cols="30"
												rows="30">${postVO.article}</textarea>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">取消</button>
											<button type="submit" class="btn btn-primary">確認</button>
											<input type="hidden" name="action" value="update"> <input type="hidden"
												name="postId" value="${postVO.postId}">
										</div>
									</FORM>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<input type="hidden" id="name" value="${memberVO.name}">
	</div>
	<%@ include file="/template/script.html" %>
		<script src="./summernote-0.8.18-dist/summernote-lite.js"></script>
		<script src="./summernote-0.8.18-dist/lang/summernote-zh-TW.js"></script>
		<script>
			// 		function uploadImage(image) {
			// 			var data = new FormData();
			// 			data.append("img", image);
			// 			$.ajax({
			// 				//上傳路徑
			// 				url : 'uploadServlet3.do',
			// 				cache : false,
			// 				contentType : false,
			// 				processData : false,
			// 				data : data,
			// 				type : "post",
			// 				success : function(url) {
			// 					var image = $('<img>').attr('src', url);
			// 					//會來後再編輯點插入該圖片，因為回傳是網址只要處理src 即可
			// 					$('.summernote').summernote("insertNode", image[0]);
			// 				},
			// 				error : function(data) {
			// 					console.log(data);
			// 				}
			// 			});
			// 		}
			$("#createPost").click(function () {
				console.log($("#name").val())
				if ($("#name").val() == '') {
					$("#loginFilter").modal("show");
				} else {
					$("#popUp").modal("show");
				}
			})
			$(document).ready(function () {
				$('#summernote').summernote({
					spellCheck: false,
					toolbar: [
						['style', ['style']],
						['font', ['bold', 'italic', 'clear']],
						['fontsize', ['fontsize']],
						['color', ['color']],
						['para', ['ul', 'ol', 'paragraph']],
						['insert', ['picture'], ['elfinder']],
						['height', ['height']],
						['view', ['fullscreen', 'codeview', 'help']]],
					lang: 'zh-TW',
					lineHeights: ['0.4', '0.5',
						'0.6', '0.8', '1.0',
						'1.2', '1.4', '1.5',
						'2.0', '3.0'],
					placeholder: "寫些什麼吧",
					// 				callbacks: {
					// 					onImageUpload: function (image) {
					// 						uploadImage(image[0]);
					// 					}
					// 				}
				});
				$('.summernote').summernote({
					spellCheck: false,
					toolbar: [
						['style', ['style']],
						['font', ['bold', 'italic', 'clear']],
						['fontsize', ['fontsize']],
						['color', ['color']],
						['para', ['ul', 'ol', 'paragraph']],
						['insert', ['picture'], ['elfinder']],
						['height', ['height']],
						['view', ['fullscreen', 'codeview', 'help']]],
					lang: 'zh-TW',
					lineHeights: ['0.4', '0.5',
						'0.6', '0.8', '1.0',
						'1.2', '1.4', '1.5',
						'2.0', '3.0'],
					placeholder: "寫些什麼吧",
					// 				callbacks: {
					// 					onImageUpload: function (image) {
					// 						uploadImage(image[0]);
					// 					}
					// 				}
				});
				$('div.note-editable').height(500);
			});
		</script>
</body>

</html>