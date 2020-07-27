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
    <link href="<c:url value="/a/css/updateform.css"/>" rel="stylesheet">
    <script src="<c:url value="/a/jquery/jquery-3.5.1.js"/>"></script>
    <script src="<c:url value="/a/js/updateform.js"/>"></script>
<!--    <link rel="stylesheet" href="main.css">-->
    <!-- 동일폴더가 아니라 서버 상위 디렉토리로 올라갔다올꺼면 c:url 쓰라고 함 (JSTL)-->
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
<!--    <link rel="stylesheet" href="updateform.css">
    <script src="jquery-3.5.1.js"></script>
    <script src="updateform.js"></script>-->
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
            <a href="http://localhost:80/LinkeT/usrmain">
                <i class="fa fa-home fa-2x"></i>
                <span class="nav-text">
                    Main
                </span>
            </a>

        </li>
        <li>
            <a href="http://localhost:80/LinkeT/me?target='me'">
                <i class="fa fa-home fa-2x"></i>
                <span class="nav-text">
                    My Information
                </span>
            </a>

        </li>
        <li class="has-subnav">
            <a href="#">
                <i class="fa fa-laptop fa-2x"></i>
                <span class="nav-text">
                    My Team
                    <!--아래에 팀 세개 추가해야함.-->
                </span>
            </a>
        </li>
        <li class="has-subnav">
            <a href="#">
               <i class="fa fa-list fa-2x"></i>
                <span class="nav-text">
                    문의하기
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
           <a href="#">
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
          <span id="main_header_location_t_name">${t_name}</span>
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
      <a href="#" class="underline">${u_id}</a>
    </div>
  </div> 

  
  <div class="mainwrapper">
  <div id="notiwrapper">
     <form action="/Link/notice/update" method="post">
     <input type="text" value=${n_serial} hidden=true>
      <div class="forum-category rounded top">
        <div id="noticetitle">
          <input type="text" value="${n_title}" name="n_title">
        </div>
        <div class="mpad ar">
          <button id="submit" type="submit"> 갱신하기 </button>
        </div>
      </div>
        
        <div id="noticecontent">
          <textarea name="n_contents" id="content">${n_contents}</textarea>
        </div>
        
    <div id="uploadfiles">
     파일업로드기능은 이곳에
      </div>
    </form>
<!--      </div>-->
  </div>
  </div>
  

    
    </section>
    </body>
</html>     
 