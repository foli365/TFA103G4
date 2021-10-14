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
    <link rel="stylesheet" href="../css/member.css">
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
        <h2>�|���޲z</h2><br>
        <h3>�|�������G</h3>
        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
             �п��
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                <li><a class="dropdown-item" href="#">�@��|��</a></li>
                <li><a class="dropdown-item" href="#">��a�~�D</a></li>
            </ul>
        </div>
        <div class="searcher">
            <form action="" class="parent">
                <input type="text" class="search" placeholder="�|���d��">
                <input type="button" name="" id="" class="btn_search">
            </form>

            <button type="button" class="btn btn-outline-success">�d��</button>
        </div>
        <table class="rwd-table ">
            <tr>
                <th>�s��</th>
                <th>�m�W</th>
                <th>�����Ҧr��</th>
                <th>�a�}</th>
                <th>�q�l�H�c</th>
                <th>����</th>
                <th>���A</th>
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
        <nav aria-label="Page navigation example">
            <ul class="pagination" style="background:bisque ;padding-top: 20px;">
                <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item"><a class="page-link" href="#">Next</a></li>
            </ul>
        </nav>
    </div>
    <div class="bg"></div>
    <div class="pop">
        <h2>�|����ƭק�</h2>
        <div class="formmember">
            <form>
                <p>�|���W�� : <input type="text" name="member_name"></p>
                <p>�|���a�} : <input type="text" name="member_location"></p>
                <p>�����Ҧr��: <input type="text" name="member_id"></p>
                <p>�q�l�H�c: <input type="text" name="member_email"></p>
                <p>����: <input type="text" name="member_idenity"></p>
                <p>���A: <input type="text" name="member_status"></p>
            </form>
            <div class="button">
                <button class="button_editok">�s�觹��</button>
                <button class="button_cancle">����</button>
            </div>
        </div>

    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
    <script src="../js/member.js"></script>

</body>
</html>