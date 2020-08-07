<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<head>
<meta charset="UTF-8">
	<link href="${pageContext.request.contextPath}/a/css/Admin/root-view.css" rel="stylesheet">
</head>
<div class="area">
   
    
</div>
<nav class="main-menu">
    <ul>
        <li>
            <a href="http://localhost:80/Link/admin">
                <i class="fa fa-home fa-2x"></i>
                <span class="nav-text">
					DASHBOARD
                </span>
            </a>

        </li>
        <li>
            <a href="http://localhost:80/Link/admin/manage/user/list?page=1&subject=java">
                <i class="fa fa-home fa-2x"></i>
                <span class="nav-text">
                	사용자 관리
                </span>
            </a>

        </li>
        <li class="has-subnav">
            <a href="http://localhost:80/Link/admin/manage/notice/list?page=1">
                <i class="fa fa-laptop fa-2x"></i>
                <span class="nav-text">
                    공지게시판 관리
                    <!--아래에 팀 세개 추가해야함.-->
                </span>
            </a>
        </li>
        <li class="has-subnav">
            <a href="http://localhost:80/Link/admin/manage/community/list?page=1&subject=java">
               <i class="fa fa-list fa-2x"></i>
                <span class="nav-text">
                    게시판 관리
                </span>
            </a>
        </li>
<!--        <li class="has-subnav">
            <a href="http://localhost:80/Link/admin/manage/community/list?page=1&subject=jsp">
               <i class="fa fa-list fa-2x"></i>
                <span class="nav-text">
                     JSP 게시판 관리
                </span>
            </a>
        </li>
        <li class="has-subnav">
            <a href="http://localhost:80/Link/admin/manage/community/list?page=1&subject=spring">
               <i class="fa fa-list fa-2x"></i>
                <span class="nav-text">
                    Spring 게시판 관리
                </span>
            </a>
        </li> -->
        
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