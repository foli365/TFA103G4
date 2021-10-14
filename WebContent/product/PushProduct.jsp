<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ page import="com.Product.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	ProductJDBCDAO dao = new ProductJDBCDAO();
	List<ProductVO> list = dao.getAll();
	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="productstyle/jquery.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <link rel="stylesheet" href="productstyle/product.css">
</head>

<body>
    <div class="container_1">
        <nav class="sidebar">
            <div class="min_picture">
                <h1>後臺管理</h1>
            </div>
            <ul>
                <li>
                    <a href="#" class="feat-btn">會員管理
                        <span class="fas fa-caret-down first"></span>
                    </a>
                    <ul class="feat-show">
                        <li><a href="UserTable.jsp" class="member_list">會員資料表</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="serv-btn">商品管理
                        <span class="fas fa-caret-down second"></span> 
                    </a>
                    <ul class="serv-show">
                        <li><a href="#" class="product_up">商品上架</a></li>
                        <li><a href="selectAll.jsp" class="product_list">商品資料表</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#1" class="bom-btn">營地管理
                        <span class="fas fa-caret-down second_1"></span> 
                        </a>

                    <ul class="bom-show">
                        <li><a href="#" class="camp_list">營地訂單</a></li>
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
    <div class="container">
        <div class="title">商品上架</div>
        <form action="" method="#" id="mypro">
            <div class="user-details">
                <div class="input-box">
                    <span class="details">商品名稱</span>
                    <input type="text"  name="pname" >
                </div>
                <div class="input-box">
                    <span class="details">類別</span>
                    <select name="#" class="pouct">

                        <option value="">帳篷</option>
                        <option value="">露營燈</option>
                        <option value="">露營杯</option>
                        <option value="">露營扇</option>
                        <option value="">露營椅</option>
                        <option value="">露營桌</option>
                        <option value="">露營餐具</option>
                        <option value="">露營碗</option>

                    </select>
                </div>
                <div class="input-box">
                    <span class="details_1">商品圖片</span>
                    <input type="file" id="picture">
                    <div class="br">
                        <button class="button" id="choose_file" style="width: 80px; height: 30px;">選圖片
                        </button>
                    </div>
                    <div id="preview">
                        <!-- <span class="text">預覽圖</span> -->
                    </div>
                </div>
                <div class="input-box" id="setcount">
                    <span class="details">價格設定</span>
                    <input type="number">
                    <div class="input-box">
                        <span class="details_p" id="count_p">數量</span>
                        <input type="number" style="width: auto; margin: 3px;">
                    </div>
                </div>
                <div class="puct-box">
                    <span class="details">商品介紹</span>
                    <textarea name="introduce" class="text_t" style="width: 620px; height: 118px;"></textarea>
                </div>

            </div>
            <div class="buttn">
<!--                 <button class="clear" type="button" id="clear_btn" style="width: 80px; height: 30px;">清空</button> -->
                <button class="outin" type="submit" id="output" style="width: 80px; height: 30px;">送出</button>
                <input type="hidden" name="action">
            </div>        
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
    <script src="productstyle/product.js"></script>
    <script>
        window.addEventListener("load", function () {
            var choose_file_e1 = document.getElementById("choose_file");
            choose_file_e1.addEventListener("click", function () {
                var the_file = document.getElementById("picture");
                the_file.click();

            });
            var p_file_p1 = document.getElementById("picture");
            var preview_p1 = document.getElementById("preview");
            var preview_img = function (file) {
                var img_node = document.createElement("img");
                var reader = new FileReader();
                reader.addEventListener("load", function () {

                    var img_node = document.createElement("img");
                    img_node.setAttribute("src", reader.result);
                    img_node.setAttribute("class", "preview_img");
                    preview_p1.innerHTML = '';
                    preview_p1.append(img_node);
                });
                reader.readAsDataURL(file);
            };

            p_file_p1.addEventListener("change", function (e) {
                if (this.files.length > 0) {
                    preview_img(this.files[0]);
                } else {
                    preview_p1.innerHTML = '<span class="text">預覽圖</span>';
                }

            });

        })

        // $('.outin').on("click",function(){
        //     // alert("ccc");
        //     window.location.href='/view/index_view.html';
        // })    

    </script>

</body>

</html>