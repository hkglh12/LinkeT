
<!-- 모든 페이지에서 include방식으로 동작하게 변경하였기에, 상단부를 삭제합니다. -->
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

</head>
<body>

<nav class="main-menu">
    <ul>
        <li>
            <a href="/Link/">
                <i class="fa fa-home fa-2x"></i>
                <span class="nav-text">
                    메인으로 돌아가기
                </span>
            </a>

        </li>
        <li>
            <a href="/Link/usr/me">
                <i class="fa fa-home fa-2x"></i>
                <span class="nav-text">
                    내 정보보기
                </span>
            </a>

        </li>
        <li class="has-subnav">
            <a href="/Link/notice/list">
                <i class="fa fa-laptop fa-2x"></i>
                <span class="nav-text">
                    공지사항 게시판
                    <!--아래에 팀 세개 추가해야함.-->
                </span>
            </a>
        </li>
        <li class="has-subnav">
            <a href="/Link/community/list?subject=java">
               <i class="fa fa-list fa-2x"></i>
                <span class="nav-text">
                    JAVA 게시판
                </span>
            </a>

        </li>
       	<li class="has-subnav">
            <a href="/Link/community/list?subject=jsp">
               <i class="fa fa-list fa-2x"></i>
                <span class="nav-text">
                    JSP 게시판
                </span>
            </a>

        </li>
        <li class="has-subnav">
            <a href="/Link/community/list?subject=spring">
               <i class="fa fa-list fa-2x"></i>
                <span class="nav-text">
                    Spring 게시판
                </span>
            </a>

        </li>
        
    </ul>

    <ul class="logout">
        <li>
           <a href="/Link/usr/logout">
                 <i class="fa fa-power-off fa-2x"></i>
                <span class="nav-text">
                    Logout
                </span>
            </a>
        </li>  
    </ul>
</nav>

<!-- </body>

</html>
 -->