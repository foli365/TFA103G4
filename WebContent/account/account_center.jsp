<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>


<!DOCTYPE html>
<html lang="zh-tw">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<%@ include file="/template/navbar.jsp"%>
<link rel="stylesheet" href="./style.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
</head>

<body>
	<div class="container mt-5">
		<div class="row">
			<div class="col">
				<ul class="nav nav-tabs mb-4" id="pills-tab" role="tablist">
					<li class="nav-item mx-4" role="presentation">
						<button class="nav-link active" id="pills-home-tab"
							data-bs-toggle="pill" data-bs-target="#pills-home" type="button"
							role="tab" aria-controls="pills-home" aria-selected="true">
							<h3>行程</h3>
						</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link" id="pills-profile-tab"
							data-bs-toggle="pill" data-bs-target="#pills-profile"
							type="button" role="tab" aria-controls="pills-profile"
							aria-selected="false">
							<h3>購物訂單</h3>
						</button>
					</li>
					<li class="nav-item mx-4" role="presentation">
						<button class="nav-link" id="pills-contact-tab"
							data-bs-toggle="pill" data-bs-target="#pills-contact"
							type="button" role="tab" aria-controls="pills-contact"
							aria-selected="false">
							<h3>儲存營地</h3>
						</button>
					</li>
				</ul>
				<div class="tab-content" id="pills-tabContent">
					<div class="tab-pane fade show active" id="pills-home"
						role="tabpanel" aria-labelledby="pills-home-tab">
						<div class="container">
							<div class="row">
								<div class="col mb-4">
									<div class="card mx-auto" style="width: 40rem;">
										<img src="./img/campsite/1485516773runke_ground17-180816.jpg"
											class="card-img-top" alt="...">
										<div class="card-body">
											<div class="row align-items-center">
												<div class="col">
													<h5 class="card-title mb-2">
														<a href="">南灣風情</a>
													</h5>
												</div>
												<div class="col d-flex justify-content-end">
													<span class="badge rounded-pill bg-info">已預訂行程</span>
												</div>
											</div>
											<p class="card-text">
												<a href="">屏東縣恆春鎮南灣路102號</a>
											</p>
										</div>
										<ul class="list-group list-group-flush">
											<li class="list-group-item">預訂期間: 11/25至11/31</li>
											<li class="list-group-item">人數: 4</li>
											<li class="list-group-item">總價: 2000元</li>
										</ul>
										<div class="card-body">
											<button type="button" class="btn btn-primary">修改訂單</button>
											<button type="button" class="btn btn-danger">取消訂單</button>
										</div>
									</div>
								</div>
								<div class="col mb-4">
									<div class="card mx-auto" style="width: 40rem;">
										<img src="./img/campsite/741692.jpg" class="card-img-top"
											alt="...">
										<div class="card-body">
											<div class="row align-items-center">
												<div class="col">
													<h5 class="card-title mb-2">
														<a href="">拉拉山營區</a>
													</h5>
												</div>
												<div class="col d-flex justify-content-end">
													<span class="badge rounded-pill bg-secondary">過往行程</span>
												</div>
											</div>
											<p class="card-text">
												<a href="">336桃園市復興區華陵村11鄰180-11號</a>
											</p>
										</div>
										<ul class="list-group list-group-flush">
											<li class="list-group-item">預訂期間: 8/17至8/20</li>
											<li class="list-group-item">人數: 3</li>
											<li class="list-group-item">總價: 1500元</li>
										</ul>
										<div class="card-body">
											<button type="button" class="btn btn-success">給予評價</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade" id="pills-profile" role="tabpanel"
						aria-labelledby="pills-profile-tab">
						<div class="container">
							<table class="table table-hover">
								<thead>
									<tr>
										<th scope="col">#</th>
										<th scope="col">購買時間</th>
										<th scope="col" style="width: 30%">商品名稱</th>
										<th scope="col">數量</th>
										<th scope="col">商品總價</th>
										<th scope="col">訂單管理</th>
										<th scope="col">訂單狀態</th>
										<th scope="col">評價</th>
									</tr>
								</thead>
								<tbody class="">
									<tr>
										<th scope="row">1</th>
										<td>08/19</td>
										<td>NatureHike加厚休閒野餐墊</td>
										<td>1</td>
										<td>849</td>
										<td><a href="">查看訂單明細</a></td>
										<td>已送達</td>
										<td></td>
									</tr>
									<tr>
										<th scope="row">2</th>
										<td>08/22</td>
										<td>MACHFALLY 多功能充電式露營探照燈</td>
										<td>2</td>
										<td>2600</td>
										<td><a href="">查看訂單明細</a></td>
										<td>配送中</td>
										<td></td>
									</tr>
								</tbody>

							</table>
						</div>
					</div>
					<div class="tab-pane fade" id="pills-contact" role="tabpanel"
						aria-labelledby="pills-contact-tab">
						<div class="container">
							<div id="carouselExampleDark"
								class="carousel carousel-dark slide" data-bs-ride="carousel">
								<div class="carousel-inner">
									<div class="carousel-item active" data-bs-interval="10000">
										<div class="card mx-auto" style="width: 40rem;">
											<img src="./img/campsite/EVFKVCY3BYZU2SCFWTUVA3OBMY.jpg"
												class="card-img-top" alt="...">
											<div class="card-body">
												<div class="row align-items-center">
													<div class="col">
														<h5 class="card-title mb-2">
															<a href="">比撒日灣山中漫城露營區</a>
														</h5>
													</div>

												</div>
												<p class="card-text">
													<a href="">556南投縣信義鄉開高巷101-9號</a>
												</p>
											</div>
											<ul class="list-group list-group-flush">

											</ul>
											<div class="card-body">
												<button type="button" class="btn btn-success">立即預定</button>
												<button type="button" class="btn btn-danger">移除</button>
											</div>
										</div>

									</div>
									<div class="carousel-item" data-bs-interval="10000">
										<div class="card mx-auto" style="width: 40rem;">
											<img src="./img/campsite/984404_15071414010032265180.jpg"
												class="card-img-top" alt="...">
											<div class="card-body">
												<div class="row align-items-center">
													<div class="col">
														<h5 class="card-title mb-2">
															<a href="">大雪山喜樂之地露營區</a>
														</h5>
													</div>

												</div>
												<p class="card-text">
													<a href="">423台中市東勢區東坑路978-6號</a>
												</p>
											</div>
											<ul class="list-group list-group-flush">

											</ul>
											<div class="card-body">
												<button type="button" class="btn btn-success">立即預定</button>
												<button type="button" class="btn btn-danger">移除</button>
											</div>
										</div>

									</div>
									<div class="carousel-item" data-bs-interval="10000">
										<div class="card mx-auto" style="width: 40rem;">
											<img style="height: 370px;"
												src="./img/campsite/Three+Hares+Campsite-5.jpg"
												class="card-img-top" alt="...">
											<div class="card-body">
												<div class="row align-items-center">
													<div class="col">
														<h5 class="card-title mb-2">
															<a href="">鹿野高台大草原露營區</a>
														</h5>
													</div>
												</div>
												<p class="card-text">
													<a href="">台東縣鹿野鄉永安村高台路42巷133號</a>
												</p>
											</div>
											<ul class="list-group list-group-flush">

											</ul>
											<div class="card-body">
												<button type="button" class="btn btn-success">立即預定</button>
												<button type="button" class="btn btn-danger">移除</button>
											</div>
										</div>
									</div>
								</div>
								<button class="carousel-control-prev" type="button"
									data-bs-target="#carouselExampleDark" data-bs-slide="prev">
									<span class="carousel-control-prev-icon" aria-hidden="true"></span>
									<span class="visually-hidden">Previous</span>
								</button>
								<button class="carousel-control-next" type="button"
									data-bs-target="#carouselExampleDark" data-bs-slide="next">
									<span class="carousel-control-next-icon" aria-hidden="true"></span>
									<span class="visually-hidden">Next</span>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/template/script.html"%>

</body>
</html>