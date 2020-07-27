<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="ko">
<head>
    <title>회원가입</title>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link href="<c:url value="/a/css/signup.css"/>" rel="stylesheet">
    <script type="text/javascript" src="<c:url value="/a/js/jquery-3.5.1.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/a/js/signup.js"/>"></script>
</head>
<body>
<div class="outter-wrapper">
   <div class="inner-wrapper">
   <p>Linke-t</p>
   <div class="textinfo">
       <strong>안녕하세요!</strong><br>
       모임, 정보공유공간 Linke-t입니다<br>
        <em>회원정보를 입력해주세요.</em>
   </div>
    <form id="signup_form" action="/Link/usr/join" method="post">
    <table class="main">
        <tr class="inputarea">
            <td class="head">이름</td>
            <td class="body"><input type="text" name="u_name" id="u_name"></td>
        </tr>
        <tr class="inputarea">
            <td class="head">휴대폰 번호</td>
            <td class="body"><input type="text" class="u_phone" name="u_phone" id="u_phone" placeholder="-를 제외하고 입력해주세요.">
            <button type="button" id="phonecheck">중복확인</button>
            </td>
        </tr>
        <tr class="inputarea">
            <td class="head">아이디</td>
            <td class="body"><input type="text" name="u_id" id="u_id" placeholder="영문, 숫자 조합(8~12자)">
            <button type="button" id="idcheck">중복확인</button>
            </td>
        </tr>
        <tr class="inputarea">
            <td class="head">비밀번호</td>
            <td class="body"><input type="password" name="u_pw" id="u_pw" placeholder="영문, 숫자, 특수기호 중 2가지 이상 조합"></td>
        </tr>
        <tr class="inputarea">
            <td class="head">비밀번호 확인</td>
            <td class="body"><input type="password" name="pwc" id="pwc" placeholder="영문, 숫자, 특수기호 중 2가지 이상 조합"></td>
        </tr>
        <tr class="inputarea">
            <td class="head">이메일 주소</td>
            <td class="body"><input type="text" name="u_email" id="u_email" placeholder="이메일 주소를 입력해주세요"> <button type="button" id="emailcheck">중복확인</button></td>
        </tr>
    </table>
      
       
    <div class="btn-area">
    <button type="button" id="sign_submit">회원가입</button>
    </div>
    </form>
</div>
</div>
</body>
</html>
