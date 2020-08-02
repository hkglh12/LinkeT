<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Welcome-Link</title>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
<!--     <link rel="stylesheet" href="Login.css"> -->
    <!--<script src ="Login.js"></script>-->
    <link href="<c:url value="/a/css/User/user/login.css"/>" rel="stylesheet">
    <script type="text/javascript" src="<c:url value="/a/js/jquery-3.5.1.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/a/js/User/user/login.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/a/js/encryptor.js"/>"></script>
    <!-- <script src="login.js"></script> -->
<!-- 	<script>
		var result = "${result}";
		console.log(result);
	</script> -->
</head>
<body>
	<c:if test = "${result ne null}">
		<c:if test = "${result eq 'failed'}">
			<c:out value="<script type='text/javascript'>alert('ID 혹은 비밀번호를 확인해주세요');</script>"></c:out>
		</c:if>
		<c:if test = "${result eq 'success'}">
			<c:out value="<script type='text/javascript'>alert('가입에 성공하였습니다.');</script>"></c:out>
		</c:if>
	</c:if>
    <div class="body"></div>
		<div class="grad"></div>
		<div class="innerwrapper"></div>
		<div class="header">
			<div><span>L</span>ink</div>
		</div>
		<br>
		<div class="login">
            <form action="/Link/usr/login" method="post" id="loginform">
				<input type="text" name="u_id" placeholder="userid" id ="u_id"><br>
				<input type="password" name="u_pwraw" id="u_pwraw" placeholder="password" ><br>
                <button type="button" id="loginbtn">Login</button>
            <button type="button" id="signup">SignUp</button>
            </form>
		</div>

</body>

</html>
