<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.campsite.model.*"%>

<%
	CampsiteVO campsiteVO = (CampsiteVO) request.getAttribute("campsiteVO"); //CampsiteServlet.java(Concroller), �s�Jreq��campsiteVO����
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title></title>

<!-- Bootstrap �� CSS -->
<link rel="stylesheet" href="./vendors/bootstrap/css/bootstrap.min.css">
<!-- ���J Font Awesome -->
<script src="https://kit.fontawesome.com/846e361093.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="./vendors/bootstrap/css/ReserveCamp.css">
</head>

<body style="background-color: #fbefe7;">
	<nav class="navbar navbar-expand-md navbar-light"
		style="background-color: #fbefe7;">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">GoCamping</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<form class="d-flex">
					<input class="form-control me-2 rounded-pill" type="search"
						placeholder="Search" aria-label="Search">
					<button id="searchIcon" class="btn" type="submit"
						style="padding: 0">
						<i class="bi bi-search"></i>
					</button>
				</form>
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item"><a id="hosting" class="nav-link" href="#"
						style="color: green;">�W�[��a</a></li>
					<li class="nav-item"><a class="nav-link" href="#">�ӫ�</a></li>
					<li class="nav-item"><a class="nav-link" href="#">�׾�</a></li>
					<li class="nav-item"><a class="nav-link" href="#">���U</a></li>
					<li class="nav-item"><a class="nav-link" href="#">�n�J</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							�|���m�W </a>
						<ul class="dropdown-menu dropdown-menu-end"
							aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="#">�|������</a></li>
							<li><a class="dropdown-item" href="#"></a></li>
							<li>
								<hr class="dropdown-divider">
							</li>
							<li><a class="dropdown-item" href="#">�n�X</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container text-center my-3">
		<div class="row mx-auto my-auto justify-content-center">
			<div id="recipeCarousel" class="carousel slide"
				data-bs-ride="carousel">
				<div class="carousel-inner" role="listbox">
					<div class="carousel-item active">
						<div class="col-md-3">
							<div class="card">
								<div class="card-img">
									<img src="//via.placeholder.com/500x400/31f?text=1"
										class="img-fluid">
								</div>
								<div class="card-img-overlay">Slide 1</div>
							</div>
						</div>
					</div>
					<div class="carousel-item">
						<div class="col-md-3">
							<div class="card">
								<div class="card-img">
									<img src="//via.placeholder.com/500x400/e66?text=2"
										class="img-fluid">
								</div>
								<div class="card-img-overlay">Slide 2</div>
							</div>
						</div>
					</div>
					<div class="carousel-item">
						<div class="col-md-3">
							<div class="card">
								<div class="card-img">
									<img src="//via.placeholder.com/500x400/7d2?text=3"
										class="img-fluid">
								</div>
								<div class="card-img-overlay">Slide 3</div>
							</div>
						</div>
					</div>
					<div class="carousel-item">
						<div class="col-md-3">
							<div class="card">
								<div class="card-img">
									<img src="//via.placeholder.com/500x400?text=4"
										class="img-fluid">
								</div>
								<div class="card-img-overlay">Slide 4</div>
							</div>
						</div>
					</div>
					<div class="carousel-item">
						<div class="col-md-3">
							<div class="card">
								<div class="card-img">
									<img src="//via.placeholder.com/500x400/aba?text=5"
										class="img-fluid">
								</div>
								<div class="card-img-overlay">Slide 5</div>
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
					<p class="addr ">�a�}</p>
					<p class="cel ">�q��</p>
				</div>
				<div class="camp-detail">
					<p>��a���e</p>
				</div>
				<div class="camp-comment">
					<p>��a����</p>
				</div>
			</div>
			<div class="col-4 order-menu">
				<form action="">
					<div class="row">
						<div class="col ">
							<p>�����C�ߤ@�H</p>
						</div>
					</div>
					<div class="row">
						<div class="col-6 ">
							<p>�J����</p>
						</div>
						<div class="col-6 ">
							<p>�h�Ф��</p>
						</div>
					</div>
					<div class="row">
						<div class="col ">
							<p>�H��</p>
						</div>
					</div>
					<div class="row">
						<div class="col ">
							<p>�O�_�n�t�M����</p>
						</div>
					</div>
					<div class="row">
						<div class="col ">
							<p>Total</p>
						</div>
					</div>
					<div class="row">
						<div class="col ">
							<button type="submit">�e�X�q��</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- body �������Ҥ��e�A���JBootstrap �� JS -->
	<script src="./vendors/bootstrap/js/bootstrap.bundle.min.js "></script>
	<!-- body �������Ҥ��e�A���JSlider �� JS -->
	<script src="./vendors/bootstrap/js/ReserveCamp.js "></script>
</body>

</html>