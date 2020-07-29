<!--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>-->
<!DOCTYPE html>
<html>
<head>
    <title>We Linke-t</title>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link href="<c:url value="/a/css/noticePost.css"/>" rel="stylesheet">
    <script src="<c:url value="/a/js/jquery-3.5.1.js"/>"></script>
    <script src="<c:url value="/a/js/jquery.MultiFile.js"/>"></script>
    <script src="<c:url value="/a/js/noticePost.js"/>"></script>
    <!-- 동일폴더가 아니라 서버 상위 디렉토리로 올라갔다올꺼면 c:url 쓰라고 함 (JSTL)-->
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
  
<!--     <script src="jquery-3.5.1.js"></script>
    <script src="postnotice.js"></script>
    <script src="jquery.MultiFile.js"></script> -->
    <script>
        console.log("${t_owner}");
        console.log("${u_id}");        
        var ownership = "${t_owner}" == "${u_id}" ? true : false;
        console.log(ownership);
    </script>
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
 <!--내용부 헤더-->
  <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
  <!--상단부 로고-->
  <header id="atop">
    <div class="row">
     <!--최상단 헤더, 위치안내용으로 쓰임 large-4 column lpad-->
      <div class="main lpad header location">
        <div class="logo">
          <span>CurrentLocation : </span>
          <span id="main_header_location_t_name">/notice/post</span>
        </div>
      </div>
      <div class="main header submenu ar">
       <!--소형메뉴 기존 클래스 large-8 column ar lpad-->
        <nav class="menu">
          <a href="#" class="current" id="dashboard">Dashboard</a>
          <a href="#" id="teaminfo">Team information</a>
          <a href="#" id="noticement">Noticement</a>
          <a href="#" id="community">Community</a>
          <!--<a href="#">Calendar</a>-->
          <a href="#" id="shareboard">Share board</a>
          <!--<a href="#">FAQ</a>
          <a href="#">Contact</a>-->
        </nav>     
      </div>
    </div>
  </header>
  <!--내용부 헤더 종료-->
  <!--사용자 계정 맞이-->
  <a href="atop" id="top-button">
    <i class="icon-angle-up"></i>
  </a>
  <div class="row">

    <div class="main lpad greets ar">
      Welcome,
      <a href="#" class="underline">${request.getParameter("usrId")}</a>
    </div>
  </div> 

  
  <div class="mainwrapper">
  <div id="notiwrapper">
     <form action="/Link/notice/post" method="post" enctype="multipart/form-data">
      <div class="forum-category rounded top">
        <div id="noticetitle">
          <input type="text" placeholder="제목 입력" name="n_title">
        </div>
        <div class="mpad ar">
          <button id="submit" type="submit"> 게시하기 </button>
        </div>
      </div>
        
        <div id="noticecontent">
          <textarea name="n_contents" id="content"></textarea>
        </div>
        
    <div id="uploadfiles">
    <input type="file" class="multi" name="u_files"/>
    <!-- <label>파일 업로드는 1회 2개로 제한됩니다.</label> -->
    <!-- <button type="button" id="addfile">파일추가하기</button><br/> -->
    <!-- 	<input mutiple="multiple" class="mulfile" type="file" name="u_files"/><br/>
    	<input mutiple="multiple" class="mulfile" type="file" name="u_files"/> -->
     <!-- AJAX해야함 -->
     <!-- https://ktko.tistory.com/entry/Spring-%EB%8B%A8%EC%9D%BC%ED%8C%8C%EC%9D%BC-%EB%8B%A4%EC%A4%91%ED%8C%8C%EC%9D%BC-%EC%97%85%EB%A1%9C%EB%93%9C%ED%95%98%EA%B8%B0 -->
      </div>
    </form>
<!--      </div>-->
  </div>
  </div>
  

    
    </section>
    </body>
</html>     
 