<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
    <title>ADMIN Login</title>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link href="<c:url value="/a/css/Admin/admin/login.css"/>" rel="stylesheet">
    <script type="text/javascript" src="<c:url value="/a/js/jquery-3.5.1.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/a/js/Admin/admin/login.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/a/js/encryptor.js"/>"></script>
	<script>
		$(function(){
			var response = "${result}";
			if(response=="failed"){
				alert("ID, 비밀번호를 확인해주세요");
			}else if(response=="denied"){
				alert("접근제한된 페이지입니다.");
			}
		})
	</script>
</head>
<body>
    <div class="body"></div>
		<div class="grad"></div>
		<div class="innerwrapper"></div>
		<div class="header">
			<div><span>ADMIN<br></span><span>L</span>ink</div>
		</div>
		<br>
		<div class="login">
            <form action="/Link/admin/login" method="post" id="loginform">
				<input type="text" name="u_id" placeholder="ADMINISTATOR_ID" id ="u_id"><br>
				<input type="password" name="u_pwraw" id="u_pwraw" placeholder="password" ><br>
                <button type="button" id="loginbtn">Login</button>
            </form>
		</div>

</body>

</html>
