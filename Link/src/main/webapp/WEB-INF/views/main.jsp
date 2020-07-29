<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Link</title>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link href="<c:url value="/a/css/main.css"/>" rel="stylesheet">
    <script src="<c:url value="/a/js/jquery-3.5.1.js"/>"></script>
    <script src="<c:url value="/a/js/main.js"/>"></script>
<!--    <link rel="stylesheet" href="main.css">-->
    <!-- 동일폴더가 아니라 서버 상위 디렉토리로 올라갔다올꺼면 c:url 쓰라고 함 (JSTL)-->
</head>
<body>
<div class="area">
    
</div>
<nav class="main-menu">
    <ul>
        <li>
            <a href="http://localhost:80/Link/">
                <i class="fa fa-home fa-2x"></i>
                <span class="nav-text">
                    Main
                </span>
            </a>

        </li>
        <li>
            <a href="http://localhost:80/Link/">
                <i class="fa fa-home fa-2x"></i>
                <span class="nav-text">
                    My Information
                </span>
            </a>

        </li>
        <li class="has-subnav">
            <a href="http://localhost:80/Link/notice/list">
                <i class="fa fa-laptop fa-2x"></i>
                <span class="nav-text">
                    Noticement
                    <!--아래에 팀 세개 추가해야함.-->
                </span>
            </a>
        </li>
        <li class="has-subnav">
            <a href="http://localhost:80/Link/community/list">
               <i class="fa fa-list fa-2x"></i>
                <span class="nav-text">
                    Community
                </span>
            </a>

        </li>
       
        <li>
            <a href="#">
               <i class="fa fa-info fa-2x"></i>
                <span class="nav-text">
                    Documentation
                </span>
            </a>
        </li>
    </ul>

    <ul class="logout">
        <li>
           <a href="http://localhost:80/Link/usr/logout">
                 <i class="fa fa-power-off fa-2x"></i>
                <span class="nav-text">
                    Logout
                </span>
            </a>
        </li>  
    </ul>
</nav>
<section>
<div class="upper">
        <div class="info"><h1 class="title">WelCome to Linke-T ! We are Linked!</h1></div>
    </div>
</section>
</body>

</html>
