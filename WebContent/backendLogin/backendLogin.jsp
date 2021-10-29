<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta>
<title>Insert title here</title>
  <script src="../js/jquery.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/homesign.css">
</head>
<body>
  <div class="logo">
        <a href="#">
            <img src="../images/200x1280.png" style="width: 1280px; height: 200px;">
        </a>
    </div>
    <div class="divform">
        	<form action="<%=request.getContextPath()%>/backendLogin/backendLogin.do" method="post">
            <p>請輸入管理員帳號: <input type="text" name="adminId"></p>
            <p>請輸入管理員密碼: <input type="PASSWORD" name="adminPwd"></p>
            
        <div class="button">
            <button  type="submit" class="button_submit">送出</button>
            <a href="<%=request.getContextPath()%>/homepage/index.jsp">回到首頁</a>
            
        </div>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>

    <script src="../js/homesign.js"></script>
</body>
</html>