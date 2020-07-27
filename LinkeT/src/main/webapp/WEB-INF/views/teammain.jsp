<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>We Linke-t</title>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link href="<c:url value="/assets/css/teammain.css"/>" rel="stylesheet">
    <script src="<c:url value="/assets/jquery/jquery-3.5.1.js"/>"></script>
    <script src="<c:url value="/assets/js/teammain.js"/>"></script>
<!--    <link rel="stylesheet" href="main.css">-->
    <!-- 동일폴더가 아니라 서버 상위 디렉토리로 올라갔다올꺼면 c:url 쓰라고 함 (JSTL)-->
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
<!--    <link rel="stylesheet" href="teammain.css">
    <script src="jquery-3.5.1.js"></script>
    <script src="teammain.js"></script>-->
    <script>
     /*   var t_name = "${teamName}";
        var t_code = "${teamCode}";*/
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
    <!--<div class="large-6 column lpad top-msg breadcrumb">
      <span id="breadcrumb">
        <a href="#"><i class="icon-home"></i></a>
        <a href="#">some category</a>
        <a href="#">some topic</a>
      </span>
    </div> -->
    <div class="main lpad greets ar">
      Welcome,
      <a href="#" class="underline">${u_id}</a>
    </div>
  </div> 
  <!--사용자 계정 맞이-->
  
  <div class="mainwrapper" id="test">
  <!--대쉬보드로 쓰려고-->
  <div class="dashboard mt ">
<!--    <div class="large-12">-->
      <div class="large-12 forum-category rounded top">
        <div class="column lpad categ">
          Dashboard
        </div>
        <div class="mpad ar">
         <!--내용물 가리기 말기 버튼-->
          <a data-connect>
            <i class="icon-collapse-top"></i>
          </a>
        </div>
      </div>
      
      <div class="toggleview">
       <!--컨텐츠 헤더부분-->
        <div class="large-12 forum-head">
         <div class="large-2 column lpad">
            
         </div>
          <div class="large-70 small-8 column lpad">
            Contents
          </div>
          <div class="large-5 column ltpad">
            Total
          </div>
          <div class="large-5 column ltpad">
            This week
          </div>
          <div class="large-15 small-4 column lpad">
            Newest?
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-file"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">Any new noticements?</a>
            </span>
            <span class="overflow-control">
              Team Noticements
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center" id="total">96587</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center" id="counts">678</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-tablet"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">Your Team Crews</a>
            </span>
            <span class="overflow-control">
              Your Team Crews counts
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">25</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">2523</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-ellipsis-horizontal"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">Current Community</a>
            </span>
            <span class="overflow-control">
              You can type as many things as you want but the text will be shorter than bla bla bla
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">355</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">215</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-bug"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">ShareBoard </a>
            </span>
            <span class="overflow-control">
              total for shareboard
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">255</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">285</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-lock"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">Someone got out?</a>
            </span>
            <span class="overflow-control">
              Any crews got out from this team?
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">95</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">234</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
<!--      </div>-->
  </div>
  </div>
  <!--이건 팀정보창, 다르게 구성해야할거야-->
    <div class="teaminfo mt">
<!--    <div class="large-12">-->
      <div class="large-12 forum-category rounded top">
        <div class="column lpad categ">
             teamboard
        </div>
        <div class="mpad ar">
         <!--내용물 가리기 말기 버튼-->
          <a data-connect>
            <i class="icon-collapse-top"></i>
          </a>
        </div>
      </div>
      
      <div class="toggleview">
       <!--컨텐츠 헤더부분-->
        <div class="large-12 forum-head">
         <div class="large-2 column lpad">
             #num
         </div>
          <div class="large-70 small-8 column lpad">
            Forums
          </div>
          <div class="large-5 column ltpad">
            Threads
          </div>
          <div class="large-5 column ltpad">
            Post
          </div>
          <div class="large-15 small-4 column lpad">
            Freshness
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-file"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">Title of the title</a>
            </span>
            <span class="overflow-control">
              Description of the title of the topic(?)
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">96587</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">678</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-tablet"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">Oops.. Foundation is supposed to be responsive</a>
            </span>
            <span class="overflow-control">
              But it's not (yet) working here
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">25</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">2523</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-ellipsis-horizontal"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">text-overflow: ellipsis;</a>
            </span>
            <span class="overflow-control">
              You can type as many things as you want but the text will be shorter than bla bla bla
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">355</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">215</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-bug"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">If you find any bugs is this pen...</a>
            </span>
            <span class="overflow-control">
              ... please tell me in the comments :)
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">255</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">285</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-lock"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">[Locked] Some other topic</a>
            </span>
            <span class="overflow-control">
              This topic is locked by someone
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">95</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">234</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
<!--      </div>-->
  </div>
  </div>
   <!--이건 공지사항-->
    <div class="noticement mt">
<!--    <div class="large-12">-->
      <div class="large-12 forum-category rounded top">
        <div class="column lpad categ">
          noticement
          
          
        </div>
        <div class="mpad ar">
         <!--내용물 가리기 말기 버튼-->
         <label class="noti ar">Only TeamOwner can write Noticements</label>
          <!--<a data-connect>
            <i class="icon-collapse-top"></i>
          </a>-->
        </div>
      </div>
      
      <div class="toggleview">
       <!--컨텐츠 헤더부분-->
        <div class="large-12 forum-head">
         <div class="large-2 column lpad">
             #num
         </div>
          <div class="large-70 small-8 column lpad">
            Forums
          </div>
          <div class="large-5 column ltpad">
            Threads
          </div>
          <div class="large-5 column ltpad">
            Post
          </div>
          <div class="large-15 small-4 column lpad">
            Freshness
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-file"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">Title of the title</a>
            </span>
            <span class="overflow-control">
              Description of the title of the topic(?)
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">96587</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">678</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-tablet"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">Oops.. Foundation is supposed to be responsive</a>
            </span>
            <span class="overflow-control">
              But it's not (yet) working here
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">25</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">2523</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-ellipsis-horizontal"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">text-overflow: ellipsis;</a>
            </span>
            <span class="overflow-control">
              You can type as many things as you want but the text will be shorter than bla bla bla
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">355</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">215</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-bug"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">If you find any bugs is this pen...</a>
            </span>
            <span class="overflow-control">
              ... please tell me in the comments :)
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">255</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">285</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-lock"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">[Locked] Some other topic</a>
            </span>
            <span class="overflow-control">
              This topic is locked by someone
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">95</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">234</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
<!--      </div>-->
  </div>
  </div>
  <!--이건 커뮤니팅=-->
    <div class="community mt">
<!--    <div class="large-12">-->
      <div class="large-12 forum-category rounded top">
        <div class="column lpad categ">
          community
        </div>
        <div class="mpad ar">
         <!--내용물 가리기 말기 버튼-->
          <a data-connect>
            <i class="icon-collapse-top"></i>
          </a>
        </div>
      </div>
      
      <div class="toggleview">
       <!--컨텐츠 헤더부분-->
        <div class="large-12 forum-head">
         <div class="large-2 column lpad">
             #num
         </div>
          <div class="large-70 small-8 column lpad">
            Forums
          </div>
          <div class="large-5 column ltpad">
            Threads
          </div>
          <div class="large-5 column ltpad">
            Post
          </div>
          <div class="large-15 small-4 column lpad">
            Freshness
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-file"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">Title of the title</a>
            </span>
            <span class="overflow-control">
              Description of the title of the topic(?)
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">96587</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">678</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-tablet"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">Oops.. Foundation is supposed to be responsive</a>
            </span>
            <span class="overflow-control">
              But it's not (yet) working here
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">25</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">2523</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-ellipsis-horizontal"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">text-overflow: ellipsis;</a>
            </span>
            <span class="overflow-control">
              You can type as many things as you want but the text will be shorter than bla bla bla
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">355</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">215</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-bug"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">If you find any bugs is this pen...</a>
            </span>
            <span class="overflow-control">
              ... please tell me in the comments :)
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">255</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">285</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-lock"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">[Locked] Some other topic</a>
            </span>
            <span class="overflow-control">
              This topic is locked by someone
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">95</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">234</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
<!--      </div>-->
  </div>
  </div>
   <!--이건 쉐어보드로 쓸꺼야 -->
    <div class="shareboard mt">
<!--    <div class="large-12">-->
      <div class="large-12 forum-category rounded top">
        <div class="column lpad categ">
          shareboard
        </div>
        <div class="mpad ar">
         <!--내용물 가리기 말기 버튼-->
          <a data-connect>
            <i class="icon-collapse-top"></i>
          </a>
        </div>
      </div>
      
      <div class="toggleview">
       <!--컨텐츠 헤더부분-->
        <div class="large-12 forum-head">
         <div class="large-2 column lpad">
             #num
         </div>
          <div class="large-70 small-8 column lpad">
            Forums
          </div>
          <div class="large-5 column ltpad">
            Threads
          </div>
          <div class="large-5 column ltpad">
            Post
          </div>
          <div class="large-15 small-4 column lpad">
            Freshness
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-file"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">Title of the title</a>
            </span>
            <span class="overflow-control">
              Description of the title of the topic(?)
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">96587</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">678</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-tablet"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">Oops.. Foundation is supposed to be responsive</a>
            </span>
            <span class="overflow-control">
              But it's not (yet) working here
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">25</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">2523</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-ellipsis-horizontal"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">text-overflow: ellipsis;</a>
            </span>
            <span class="overflow-control">
              You can type as many things as you want but the text will be shorter than bla bla bla
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">355</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">215</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-bug"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">If you find any bugs is this pen...</a>
            </span>
            <span class="overflow-control">
              ... please tell me in the comments :)
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">255</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">285</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-lock"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">[Locked] Some other topic</a>
            </span>
            <span class="overflow-control">
              This topic is locked by someone
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">95</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center">234</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
<!--      </div>-->
  </div>
  </div>
  <div class="noticeposting mt curr">
     
    <div class="postwrapper">
  <div class="postingbox">


        <form>
        <div class="noti posting title">
         <input type="text" id ="title" placeholder="Noticement Title here">
         </div>
          <div class="noti posting content">
              <textarea id="content" placeholder="notice contents"></textarea>
          </div>
          <div class="fileinput">
              파일등록하는곳이 될겁니다.
          </div>
          <button id="notice_submit">testsdfasd</button>
        </form>
      </div>

  </div>
      </div>        
                            
  </div>
  <!--상부 하부로 나뉜듯-->
<!--  <div class="row mt mb">
    <div class="large-12">
      <div class="large-12 forum-category rounded top">
        <div class="large-8 small-10 column lpad">
          Some category title
        </div>
        <div class="large-4 small-2 column lpad ar">
          <a data-connect="">
            <i class="icon-collapse-top"></i>
          </a>
        </div>
      </div>
      
      <div class="toggleview">
        <div class="large-12 forum-head">
          <div class="large-8 small-8 column lpad">
            Forums
          </div>
          <div class="large-1 column lpad">
            Threads
          </div>
          <div class="large-1 column lpad">
            Post
          </div>
          <div class="large-2 small-4 column lpad">
            Freshness
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-file"></i>
          </div>
          <div class="large-7 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">Title of the title</a>
            </span>
            <span class="overflow-control">
              Description of the title of the topic(?)
            </span>
          </div>
          <div class="large-1 column lpad">
            <span class="center">96587</span>
          </div>
          <div class="large-1 column lpad">
            <span class="center">678</span>
          </div>
          <div class="large-2 small-4 column pad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-tablet"></i>
          </div>
          <div class="large-7 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">Oops.. Foundation is supposed to be responsive</a>
            </span>
            <span class="overflow-control">
              But it's not (yet) working here
            </span>
          </div>
          <div class="large-1 column lpad">
            <span class="center">25</span>
          </div>
          <div class="large-1 column lpad">
            <span class="center">2523</span>
          </div>
          <div class="large-2 small-4 column pad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-ellipsis-horizontal"></i>
          </div>
          <div class="large-7 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">text-overflow: ellipsis;</a>
            </span>
            <span class="overflow-control">
              You can type as many things as you want but the text will be shorter
            </span>
          </div>
          <div class="large-1 column lpad">
            <span class="center">355</span>
          </div>
          <div class="large-1 column lpad">
            <span class="center">215</span>
          </div>
          <div class="large-2 small-4 column pad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-bug"></i>
          </div>
          <div class="large-7 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">If you find any bugs is this pen...</a>
            </span>
            <span class="overflow-control">
              ... please tell me in the comments :)
            </span>
          </div>
          <div class="large-1 column lpad">
            <span class="center">255</span>
          </div>
          <div class="large-1 column lpad">
            <span class="center">285</span>
          </div>
          <div class="large-2 small-4 column pad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-lock"></i>
          </div>
          <div class="large-7 small-8 column lpad">
            <span class="overflow-control">
              <a href="#">[Locked] Some other topic</a>
            </span>
            <span class="overflow-control">
              This topic is locked by someone
            </span>
          </div>
          <div class="large-1 column lpad">
            <span class="center">95</span>
          </div>
          <div class="large-1 column lpad">
            <span class="center">234</span>
          </div>
          <div class="large-2 small-4 column pad">
            <span>
              <a href="#">Some sub-topic</a>
            </span>
            <span>08-29-2013 7:29PM</span>
            <span>by <a href="#">Some user</a></span>
          </div>
        </div>
        
      </div>
    </div>
  </div>-->
  </div>
  
  <!--Alomost Footer-->
  <!--<div class="row mt mb">
    <div class="large-12">
      <div class="large-12 small-12 forum-category rounded top lpad">
        <span>Forum Statistics</span>
      </div>
      <div class="large-12 small-12 normal lpad">
        <h1 class="inset">Who's online</h1>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Incidunt cupiditate culpa id explicabo eum eius corporis minima laudantium dolor aperiam quam cumque sequi aliquam adipisci alias fugiat praesentium quibusdam sapiente.</p>
        <h1 class="inset">Board Statistics</h1>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dicta expedita temporibus dolorum esse modi hic quidem sit voluptas laboriosam veniam corporis accusamus id nam! Commodi sequi molestias quasi dolorum animi.</p>
      </div>
    </div>
  </div>-->
  </section>
</body>

</html>
