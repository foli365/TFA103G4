<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <script src="../js/jquery.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/camp.css">
</head>
<body>
<div class="container">
        <nav class="sidebar">
            <div class="min_picture">
                <h1>後臺管理</h1>
            </div>
            <ul>
                  <li>
                    <a href="#" class="feat-btn">帳號管理
                        <span class="fas fa-caret-down first"></span>
                    </a>
                    <ul class="feat-show">
                        <li><a href="#" class="member_list">會員帳號管理</a></li>
                    </ul>
                     <ul class="feat-show">
                        <li><a href="#" class="manager_list">管理員帳號管理</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="serv-btn">商品管理
                        <span class="fas fa-caret-down second"></span> 
                    </a>
                    <ul class="serv-show">
                        <li><a href="http://localhost:8081/git/product/PushProduct.jsp" class="product_up">商品上架</a></li>
                        <li><a href="http://localhost:8081/git/product/selectAll.jsp" class="product_list">商品資料表</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#1" class="bom-btn">營地管理
                        <span class="fas fa-caret-down second_1"></span> 
                        </a>

                    <ul class="bom-show">
                        <li><a href="#" class="camp_list">營地列表</a></li>
                        <li><a href="#" class="alert_managament">檢舉管理</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="mky-btn">商城管理
                        <span class="fas fa-caret-down second_2"></span> 
                    </a>
                    <ul class="mky-show">
                        <li><a href="#" class="shopping_list">商城訂單</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
    <div class="rightside">
        <h2>營地列表</h2>
        <h3>營地位置：</h3>
        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
             請選擇
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                <li><a class="dropdown-item" href="#">北部</a></li>
                <li><a class="dropdown-item" href="#">中部</a></li>
                <li><a class="dropdown-item" href="#">南部</a></li>
                <li><a class="dropdown-item" href="#">東部</a></li>
            </ul>
        </div>
        <div class="searcher">
            <form action="" class="parent">
                <input type="text" class="search" placeholder="營地查詢">
                <input type="button" name="" id="" class="btn_search">
            </form>

            <button type="button" class="btn btn-outline-success">查詢</button>
        </div>
        <table class="rwd-table ">
            <tr>
                <th>編號</th>
                <th>營地名稱</th>
                <th>位置</th>
                <th>地址</th>
                <th>電子信箱</th>
                <th>狀態</th>
                <th>營地營業許可證編號</th>
                <th>編輯</th>
            </tr>
            <tr>
                <td data-th="編號">1</td>
                <td data-th="姓名">張三</td>
                <td data-th="身分證字號">A123456789</td>
                <td data-th="地址">台北市心意區xx路40號x樓</td>
                <td data-th="電子信箱">TIBAME@gmail.com</td>
                <td data-th="身分">一般</td>
                <td data-th="狀態">通過</td>
                <td data-th="編輯"><button type="button" class="btn btn-primary" id="btn_edit">編輯</button>
                    <button type="button" class="btn btn-danger" id="btn_edit">刪除</button></td>
            </tr>
            <tr>
                <td data-th="編號">1</td>
                <td data-th="姓名">張三</td>
                <td data-th="身分證字號">A123456789</td>
                <td data-th="地址">台北市心意區xx路40號x樓</td>
                <td data-th="電子信箱">TIBAME@gmail.com</td>
                <td data-th="身分">營地業主</td>
                <td data-th="狀態">待審</td>
                <td data-th="編輯"><button type="button" class="btn btn-primary" id="btn_edit">編輯</button>
                    <button type="button" class="btn btn-danger" id="btn_edit">刪除</button></td>

            </tr>

        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
    <script src="../js/camp.js"></script>
</body>
</html>