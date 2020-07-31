<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>We Linke-t</title>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link href="<c:url value="/a/css/noticeRead.css"/>" rel="stylesheet">
    <script src="<c:url value="/a/js/jquery-3.5.1.js"/>"></script>
    <script src="<c:url value="/a/js/noticeRead.js"/>"></script>
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
  <!-- Summernote Setting -->
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<!-- include summernote css/js -->
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
    <script src="<c:url value="/a/summernote/summernote-ko-KR.js"/>"></script>
</head>
<body>
<div class="area">
    
</div>
<!-- <nav class="main-menu">
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
                    아래에 팀 세개 추가해야함.
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
</nav> -->
<%@ include file="root-view.jsp"%>
 <section>
 <!--내용부 헤더-->
  <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
  <!--상단부 로고-->
  <header id="atop">
    <div class="row">
     <!--최상단 헤더, 위치안내용으로 쓰임 large-4 column lpad-->
      <div class="main lpad header location">
        <div class="logo">
          <span>CurrentLocation :</span>
          <span id="main_header_location_t_name"> /Notice</span>
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
      <a href="#" class="underline">${session.getAttribute("usrId")}</a>
    </div>
  </div> 

  
  <div class="mainwrapper">
  <div id="notiwrapper">
      <div class="forum-category rounded top">
        <div id="noticetitle">
          ${noticement.title}
        </div>
        <div class="mpad ar">
          <button id="upd"> 수정하기 </button>
          <button id="del">삭제하기</button>
        </div>
      </div>
        <div id="noticeinfos">
            <ul>
                <li>게시글번호 : ${noticement.serial}</li>
                <li>작성자 : ${noticement.usrId}</li>
                <li>작성일 : ${noticement.createDate}</li>
                <li>조회수 : ${noticement.readCount}</li>
            </ul>
        </div>
        <div id="noticecontent">
          <div id="content" >
          <c:out value="${noticement.contents}" escapeXml="false" />
          </div>
        </div>
        
    <div id="uploadfiles">
     <%-- <c:if test="${fn:length(list) eq 0}"> --%>
     <c:if test="${empty noticement.uFileList}">
     <label>등록된 파일이 없습니다!</label>
     </c:if>
     <c:if test="${not empty noticement.uFileList}">
     <c:forEach items="${noticement.uFileList}" var="i">
     	<li><a href="http://localhost:80/Link/notice/download?fileCode=${i.uFileCode}">${i.uFileOriginName}
     	<%-- <label class="uFileCode">${i.uFileCode}</label> --%></a>
     </c:forEach>
     </c:if>
      </div>
        
<!--      </div>-->
  </div>
  </div>
  

    
    </section>
    </body>
    <script>
    	console.log()
    	
/*   		$('#content').val("${noticement.contents}"); */
    	$('#upd').on("click",function(){
    		location.href="/Link/notice/update?n_serial="+${noticement.serial};
    	});
    	$("#del").on("click",function(){
    		var f = document.createElement("form");
    		var inputl = document.createElement("input");
    		inputl.setAttribute("type", "hidden");
    		inputl.setAttribute("name", "n_serial");
    		inputl.setAttribute("value","${noticement.serial}");
    		
    		f.appendChild(inputl);
    		f.action="http://localhost:80/Link/notice/delete";
    		f.method="post";
    		document.body.appendChild(f);
    		f.submit();
    	})
    </script>
</html>     
 