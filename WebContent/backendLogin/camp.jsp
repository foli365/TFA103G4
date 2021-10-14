<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
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
                <h1>��O�޲z</h1>
            </div>
            <ul>
                  <li>
                    <a href="#" class="feat-btn">�b���޲z
                        <span class="fas fa-caret-down first"></span>
                    </a>
                    <ul class="feat-show">
                        <li><a href="#" class="member_list">�|���b���޲z</a></li>
                    </ul>
                     <ul class="feat-show">
                        <li><a href="#" class="manager_list">�޲z���b���޲z</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="serv-btn">�ӫ~�޲z
                        <span class="fas fa-caret-down second"></span> 
                    </a>
                    <ul class="serv-show">
                        <li><a href="#" class="product_up">�ӫ~�W�[</a></li>
                        <li><a href="#" class="product_list">�ӫ~��ƪ�</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#1" class="bom-btn">��a�޲z
                        <span class="fas fa-caret-down second_1"></span> 
                        </a>

                    <ul class="bom-show">
                        <li><a href="#" class="camp_list">��a�q��</a></li>
                        <li><a href="#" class="alert_managament">���|�޲z</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="mky-btn">�ӫ��޲z
                        <span class="fas fa-caret-down second_2"></span> 
                    </a>
                    <ul class="mky-show">
                        <li><a href="#" class="shopping_list">�ӫ��q��</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
    <div class="rightside">
        <h2>��a�C��</h2>
        <h3>��a��m�G</h3>
        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
             �п��
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                <li><a class="dropdown-item" href="#">�_��</a></li>
                <li><a class="dropdown-item" href="#">����</a></li>
                <li><a class="dropdown-item" href="#">�n��</a></li>
                <li><a class="dropdown-item" href="#">�F��</a></li>
            </ul>
        </div>
        <div class="searcher">
            <form action="" class="parent">
                <input type="text" class="search" placeholder="��a�d��">
                <input type="button" name="" id="" class="btn_search">
            </form>

            <button type="button" class="btn btn-outline-success">�d��</button>
        </div>
        <table class="rwd-table ">
            <tr>
                <th>�s��</th>
                <th>��a�W��</th>
                <th>��m</th>
                <th>�a�}</th>
                <th>�q�l�H�c</th>
                <th>���A</th>
                <th>��a��~�\�i�ҽs��</th>
                <th>�s��</th>
            </tr>
            <tr>
                <td data-th="�s��">1</td>
                <td data-th="�m�W">�i�T</td>
                <td data-th="�����Ҧr��">A123456789</td>
                <td data-th="�a�}">�x�_���߷N��xx��40��x��</td>
                <td data-th="�q�l�H�c">TIBAME@gmail.com</td>
                <td data-th="����">�@��</td>
                <td data-th="���A">�q�L</td>
                <td data-th="�s��"><button type="button" class="btn btn-primary" id="btn_edit">�s��</button>
                    <button type="button" class="btn btn-danger" id="btn_edit">�R��</button></td>
            </tr>
            <tr>
                <td data-th="�s��">1</td>
                <td data-th="�m�W">�i�T</td>
                <td data-th="�����Ҧr��">A123456789</td>
                <td data-th="�a�}">�x�_���߷N��xx��40��x��</td>
                <td data-th="�q�l�H�c">TIBAME@gmail.com</td>
                <td data-th="����">��a�~�D</td>
                <td data-th="���A">�ݼf</td>
                <td data-th="�s��"><button type="button" class="btn btn-primary" id="btn_edit">�s��</button>
                    <button type="button" class="btn btn-danger" id="btn_edit">�R��</button></td>

            </tr>

        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
    <script src="../js/camp.js"></script>
</body>
</html>