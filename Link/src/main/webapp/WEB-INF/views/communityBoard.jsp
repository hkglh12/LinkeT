<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link href="<c:url value="/a/css/communityBoard.css"/>" rel="stylesheet">
    <script src="<c:url value="/a/js/jquery-3.5.1.js"/>"></script>
    <script src="<c:url value="/a/js/communityBoard.js"/>"></script>

</head>
<body>
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
          <span>CurrentLocation : </span>
          <span id="main_header_location_t_name">/Link/noticement</span>
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
      <a href="#" class="underline">${session.getAttribute("usrId")}</a>
    </div>
  </div> 
  <!--사용자 계정 맞이-->
           <!-- https://www.baeldung.com/jstl  -->
  <div class="mainwrapper" id="test">
  <!--대쉬보드로 쓰려고-->
  <div class="dashboard mt curr">
<!--    <div class="large-12">-->
      <div class="large-12 forum-category rounded top">
        <div class="column lpad categ">
          Dashboard
        </div>
        <div>
        	검색기능도 제공해야지
        </div>
        <div class="mpad ar">
        <button type="btn" id="community_post"> 자유게시글 올리기</button>
         <!--내용물 가리기 말기 버튼-->
          <!-- <a data-connect>
            <i class="icon-collapse-top"></i>
          </a> -->
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
            File Count
          </div>
          <div class="large-5 column ltpad">
            Read Count
          </div>
          <div class="large-15 small-4 column lpad">
            Posting Information
          </div>
        </div>
         
        
         <!-- 모델에서 넘긴건 바로없이 접근가능 
         단, 이터레이팅 변수는 여기서 선언한것이므로 -->
        <!-- 결과로 받아온 리스트가 비어있을경우 -->
         <c:if test="${empty communitylist}">
         <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-file"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="#"> <c:out value="게시글 없음!"/></a>
            </span>
            <span class="overflow-control">
              <c:out value="아직 아무 게시글 입력된적이 없어요!"/>
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center" id="total"></span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center" id="counts"></span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#"></a>
            </span>
            <span></span>
            <span><a href="#"></a></span>
          </div>
        </div>
         </c:if>
         <!-- 결과로 받아온 리스트가 비어있지 않을경우 -->
		<c:if test="${not empty communitylist}">
         <c:forEach items="${communitylist}" var="community">
         
        <div class="large-12 forum-topic">
          <div class="large-1 column lpad">
            <i class="icon-file"></i>
          </div>
          <div class="large-70 small-8 column lpad">
            <span class="overflow-control">
              <a href="javascript:pagecall(${community.serial})"> #<c:out value="${community.serial}"/></a>
            </span>
            <span class="overflow-control">
              <c:out value="${community.title}"/>
            </span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center" id="total">${community.fileCount}</span>
          </div>
          <div class="large-5 column ltpad">
            <span class="center" id="counts">${community.readCount}</span>
          </div>
          <div class="large-15 small-4 column lpad">
            <span>
              <a href="#">Noticement</a>
            </span>
            <span>${community.createDate}</span>
            <span>by <!-- <a href="#"> -->${community.usrId};<!-- </a> --></span>
          </div>
        </div>
        </c:forEach>
        </c:if>
        
  </div>
    <div class="page_block">
      <ul class="blocks">
      	<!-- //https://yoonka.tistory.com/459 -->
         <!-- https://blog.nerdfactory.ai/2019/05/05/spring-mvc-jstl.html -->
       <%-- <c:set var="block" value="${total}/8"/> --%>
      <fmt:parseNumber var="block" value = "${(total/8)+ (total%8 == 0 ? 0 : 1)}" integerOnly="true"/>
          <c:if test="${block gt 0}">
          <c:forEach begin="1" end="${block}" var="i" step="1">
          <li><a href="javascript:blockmove('${i}')">${i}</a></li>
          </c:forEach>
          </c:if>
      </ul>
  </div>
  </div>

  </div>
  </section>
</body>
    <script>
    $("#community_post").on("click",function(){
    	console.log("post called");
    	location.href="http://localhost:80/Link/community/form";
    });
	    function blockmove(block){
			console.log("called" + block)
			location.href="/Link/community/list?page="+block;
		}
	    function pagecall(serial){
	    	console.log("called" + serial)
	    	location.href="/Link/community/get?c_serial="+serial;
	    }
    </script>
</html>
