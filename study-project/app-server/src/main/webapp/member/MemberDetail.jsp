<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
  <title>회원상세</title>
 <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  <script src = "../node_modules/@popperjs/core/dist/umd/popper.js"> </script>
  <script src = "../node_modules/bootstrap/dist/js/bootstrap.js"> </script>
  <style>
  .container {
    xborder : 1px solid red;
    width : 640px;
  }</style>
</head>
<body>
<div class = "container">
<h1>회원 상세(MVC + EL)</h1>

<form action='update'>
  <label for='f-no'>번호</label> 
  <input id='f-no' type='text' name='no' value='${requestScope.member.no}' readonly><br>
  
  
  <div class="mb-3 row">
    <label for='f-name' class="col-sm-2 col-form-label">이름</label>
    <div class="col-sm-6">
      <input id='f-name' type='text' name='name' class = "form-control" value="${member.name}">
  </div>
</div>

<div class="mb-3 row">
    <label for='f-email' class="col-sm-2 col-form-label">이메일</label>
    <div class="col-sm-10">
      <input id='f-email' type='text' name='name' class = "form-control" value="${member.email}">
  </div>
</div>

<div class="mb-3 row">
    <label for='f-password' class="col-sm-2 col-form-label">암호</label>
    <div class="col-sm-6">
      <input id='f-password' type='text' name='name' class = "form-control" >
  </div>
</div>

<div class="mb-3 row">
    <label for='f-photo' class="col-sm-2 col-form-label">사진</label>
    <div class="col-sm-10">
      <input id='f-photo' type='text' name='name' class = "form-control" value="${member.photo}">
  </div>
</div>

<div class="mb-3 row">
    <label for='f-tel' class="col-sm-2 col-form-label">전화</label>
    <div class="col-sm-10">
      <input id='f-tel' type='text' name='name' class = "form-control" value="${member.tel}">
  </div>
</div>


<div class="mb-3 row">
    <label for='f-registeredDate'>등록일</label> 
    <div class="col-sm-10">
    
 <span id='f-registeredDate'>${requestScope.member.registeredDate}</span>
      
  </div>
</div>
  
<button class ="btn btn-primary">변경</button>
 <a href='delete?no=${member.no}' class = "btn btn-primary">삭제</a> 
 <a href='list' class = "btn btn-primary">목록</a>
</form>
</div> <!--  .container -->
</body>
</html>

