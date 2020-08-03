<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
    <title>Link administrator : 공지사항</title>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link href="${pageContext.request.contextPath}/a/css/Admin/manage/noticement/board.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/a/js/jquery-3.5.1.js"></script>
    
	<link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet"> 
	<script>

	var result = "${result}";
	console.log(result);
		if(result == "true"){
			alert("반영을 완료했습니다.");
		}
	
	</script>   
</head>
<body>
<!-- 좌측 네비게이터 -->
<jsp:include page="../../root-view.jsp"/>
<section>
	<header id="atop">
		<div class="row">
      		<div class="main lpad header location">
        		<div class="logo">
          			<span> 관리자페이지 : </span>
          			<span id="main_header_location_t_name">공지 목록</span>
        		</div>
      		</div>
      <!-- CSS로부터 공간확보를 위해 유지 -->
			<div class="main header submenu ar">
				<nav class="menu">
	        		 <a href="#" class="current" id="dashboard"></a>
	       		</nav>     
	    	</div>
    	</div>
    </header>
<!--------------------------------------상단부 현재위치표시----------------------------->
  	<div class="row">
    	<div class="main lpad greets ar">
      		Welcome,
      		<a href="#" class="underline">${usrId} (관리자)</a>
    	</div>
  	</div> 
<!-- ------------------------------------사용자 정보 노출 -------------------------------->
  	<div class="mainwrapper" id="test">
  		<div class="dashboard mt curr">
      		<div class="large-12 forum-category rounded top">
      			<div class="column lpad categ">
      				공지사항
        		</div>
      			<div class="mpad ar">
        			<button type="button" id="notice_post">공지글 게시하기</button>
        		</div>
      		</div>
		<div class="toggleview">
<!----------------------------------------컨텐츠 헤더----------------------------------------->
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
<!----------------------------------------------Contents -------------------------------------->
		<c:if test="${empty noticelist}">
        <c:forEach var='i' begin='0' end='7'>
       		<div class="large-12 forum-topic">
          		<div class="large-1 column lpad">
           			<c:if test="${i eq 0}">
            			<i class="icon-file"></i>
            		</c:if>
          		</div>
          		<div class="large-70 small-8 column lpad">
            		<span class="overflow-control">
            			<c:if test="${i eq 0}">
              				<c:out value="공지사항 없음!"/>
            			</c:if>
            		</span>
            		<span class="overflow-control">
            			<c:if test="${i eq 0}">
              				<c:out value="아직 아무 공지사항도 입력된적이 없어요!"/>
              			</c:if>
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
              			<!-- <a href="#"></a> -->
            		</span>
            		<span></span>
            		<span><a href="#"></a></span>
          		</div>
        	</div>
        </c:forEach>
        </c:if>
<!-------------------------------------- 결과로 받아온 리스트가 비어있지 않을경우 ---------------->
		<c:if test="${not empty noticelist}">
        	<c:forEach items="${noticelist}" var="notice"> 
        		<div class="large-12 forum-topic" onclick="pagecall(${notice.serial})">
          			<div class="large-1 column lpad">
            			<i class="icon-file"></i>
          			</div>
	          		<div class="large-70 small-8 column lpad">
	            		<span class="overflow-control">
	              			 #<c:out value="${notice.serial}"/>
	            		</span>
	            		<span class="overflow-control">
	              			<a href="#"> <c:out value="${notice.title}"/> </a>
	            		</span>
	          		</div>
	          		<div class="large-5 column ltpad">
	            		<span class="center" id="total">${notice.fileCount}</span>
	          		</div>
	          		<div class="large-5 column ltpad">
	           			<span class="center" id="counts">${notice.readCount}</span>
	          		</div>
	          		<div class="large-15 small-4 column lpad">
	            		<!-- <span>
	              			공지사항
	            		</span> -->
	            		<span>${notice.createDate}</span>
	           		 	<span>by <!-- <a href="#"> -->${notice.usrId}<!-- </a> --></span>
	          		</div>
        		</div>
        </c:forEach>
        </c:if>
  	</div>
    <div class="page_block">
    	<ul class="blocks">
	      	<!-- //https://yoonka.tistory.com/459 -->
	        <!-- https://blog.nerdfactory.ai/2019/05/05/spring-mvc-jstl.html -->
	        <fmt:parseNumber var="comment_block" value="${(commentlength/10) + (commentlength%10 == 0 ? 0 :1)}" integerOnly="true"></fmt:parseNumber>
         	<fmt:parseNumber var="block" value ="${(total/8)+(total%8 == 0 ? 0 : 1)}" integerOnly="true"/>
      		<c:if test="${block gt 0}">
          		<c:forEach begin="1" end="${block}" var="i" step="1">
          			<c:if test="${i eq param.page}">
         	 		<li ><a class="blockcurr" onclick="blockmove('${i}')">${i}</a></li>
         	 		</c:if>
         	 		<c:if test="${i ne param.page}">
         	 		<li><a onclick="blockmove('${i}')">${i}</a></li>
         	 		</c:if>
          		</c:forEach>
          	</c:if>
      	</ul>
  	</div>
	</div>
	</div>
</section>
</body>
<script src="${pageContext.request.contextPath}/a/js/Admin/manage/noticement/board.js"></script>
</html>

<!-- JSP하면서 참조한 사이트들

https://okky.kr/article/407048
https://sbs20011.tistory.com/entry/20140728
https://javawin.tistory.com/17
https://okky.kr/article/56708
https://whdvy777.tistory.com/entry/JSTL-Core-%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC-forEach-%EB%B0%B0%EC%97%B4%EC%B6%9C%EB%A0%A5-items
https://m.blog.naver.com/PostView.nhn?blogId=hulint&logNo=80190571641&proxyReferer=https:%2F%2Fwww.google.com%2F
  https://offbyone.tistory.com/366
  https://atoz-develop.tistory.com/entry/JSP-JSTL-%EC%82%AC%EC%9A%A9-%EB%B0%A9%EB%B2%95-%EC%A3%BC%EC%9A%94-%ED%83%9C%EA%B7%B8-%EB%AC%B8%EB%B2%95-%EC%A0%95%EB%A6%AC
  
  http://blog.daum.net/question0921/736
  https://blog.nerdfactory.ai/2019/05/05/spring-mvc-jstl.html
  https://yoonka.tistory.com/459
  https://epthffh.tistory.com/entry/Javascript-%EC%97%90%EC%84%9C-JSTL-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0-%EC%99%80-%EC%A3%BC%EC%9D%98%EC%82%AC%ED%95%AD
  -->
