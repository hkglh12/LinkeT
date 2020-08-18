<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<head>
<meta charset="UTF-8">
	<link href="${pageContext.request.contextPath}/a/css/Admin/root-view.css" rel="stylesheet">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css" integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc" crossorigin="anonymous">
</head>
<div class="area">
   
    
</div>
<nav class="main-menu">
    <ul>
        <li>
            <a href="/Link/admin/manage/log/list">
                <i class="fas fa-broadcast-tower fa-2x"></i>
                <span class="nav-text">
					로그감사
                </span>
            </a>

        </li>
        <li>
            <a href="/Link/admin/manage/user/list?page=1&subject=java">
                <i class="fa fa-home fa-2x"></i>
                <span class="nav-text">
                	사용자 관리
                </span>
            </a>

        </li>
        <li class="has-subnav">
            <a href="/Link/admin/manage/notice/list?page=1">
                <i class="fa fa-laptop fa-2x"></i>
                <span class="nav-text">
                    공지게시판 관리
                    <!--아래에 팀 세개 추가해야함.-->
                </span>
            </a>
        </li>
        <li class="has-subnav">
            <a href="/Link/admin/manage/community/list?page=1&subject=java">
               <i class="fa fa-list fa-2x"></i>
                <span class="nav-text">
                    게시판 관리
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
</html>
<!-- </body>

</html>
 -->