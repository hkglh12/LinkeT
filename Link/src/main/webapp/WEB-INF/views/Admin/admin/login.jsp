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

</head>
<body>
	<c:if test = "${result ne null}">
		<c:if test = "${result eq 'failed'}">
			<c:out value="<script type='text/javascript'>alert('ID 혹은 비밀번호를 확인해주세요');</script>"></c:out>
		</c:if>
		<c:if test = "${result eq 'success'}">
			<c:out value="<script type='text/javascript'>alert('가입에 성공하였습니다.');</script>"></c:out>
		</c:if>
		<c:if test = "${result eq 'notvalid'}">
			<c:out value="<script type='text/javascript'>alert('관리자 계정만 접근 가능합니다.');</script>"></c:out>
			</c:if>
	</c:if>
    <div class="body"></div>
		<div class="grad"></div>
		<div class="innerwrapper"></div>
		<div class="header">
			<div><span>ADMIN</span>Link</div>
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
