<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<head>
<meta charset="UTF-8">
	<link href="${pageContext.request.contextPath}/a/css/User/root-view.css" rel="stylesheet">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css" integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc" crossorigin="anonymous">
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
                <i class="far fa-address-card fa-2x"></i>
                <span class="nav-text">
                    내 정보보기
                </span>
            </a>

        </li>
        <li class="has-subnav">
            <a href="/Link/notice/list?page=1">
                <i class="fas fa-exclamation-circle fa-2x"></i>
                <span class="nav-text">
                    공지사항
                </span>
            </a>
        </li>
        <li class="has-subnav">
            <a href="/Link/community/list?subject=java&page=1">
               <i class="fa fa-list fa-2x"></i>
                <span class="nav-text">
                    JAVA 게시판
                </span>
            </a>

        </li>
       	<li class="has-subnav">
            <a href="/Link/community/list?subject=jsp&page=1">
               <i class="fa fa-list fa-2x"></i>
                <span class="nav-text">
                    JSP 게시판
                </span>
            </a>

        </li>
        <li class="has-subnav">
            <a href="/Link/community/list?subject=spring&page=1">
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
</body>
</hteml>