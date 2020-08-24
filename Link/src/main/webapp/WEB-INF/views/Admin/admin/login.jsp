<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<html>
<head>
    <title>ADMIN Login</title>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link href="${pageContext.request.contextPath}/a/css/Admin/admin/login.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/a/js/jquery-3.5.1.js"></script>
    <script src="${pageContext.request.contextPath}/a/js/Admin/admin/login.js"></script>
    <script src="${pageContext.request.contextPath}/a/js/encryptor.js"></script>
    <%-- Page feedback에 대해 직접 JSP에서 처리 --%>
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
		<%-- Form을 준비하고, 암호화하여 폼에 추가, 동작 시동 --%>
            <form action="/Link/admin/login" method="post" id="loginform">
				<input type="text" name="u_id" placeholder="ADMINISTATOR_ID" id ="u_id"><br>
				<input type="password" name="u_pwraw" id="u_pwraw" placeholder="password" ><br>
                <button type="button" id="loginbtn">Login</button>
            </form>
		</div>

</body>

</html>
