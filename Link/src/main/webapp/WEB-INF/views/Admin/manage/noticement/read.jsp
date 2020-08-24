<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Link administrator : 공지사항</title>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link href="${pageContext.request.contextPath}/a/css/Admin/manage/noticement/read.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/a/css/Commons/posting_structure.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/a/css/Commons/column.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/a/js/Commons/navReact.js"></script>
    <script src="${pageContext.request.contextPath}/a/js/jquery-3.5.1.js"></script>
	<script src="${pageContext.request.contextPath}/a/js/Admin/manage/noticement/read.js"></script>
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
  <!-- Summernote Setting -->
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<!-- include summernote css/js -->
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
  <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../../root-view.jsp"></jsp:include>
<section>
  	<header id="atop">
    	<div class="row">
     	<!--최상단 헤더, 위치안내용으로 쓰임 large-4 column lpad-->
      		<div class="main lpad header location">
        		<div class="logo">
          			<span>관리자페이지 :</span>
          				<span id="main_header_location_t_name">공지사항</span>
        		</div>
      		</div>
      		<div class="main header submenu ar">
        		<nav class="menu">
          			<a href="#" class="current" id="dashboard"></a>
		        </nav>    
      		</div>
    	</div>
  	</header>
  	<div class="row">
    	<div class="main lpad greets ar">
      		Welcome,
      		<a href="#" class="underline">${sessionScope.usrId} (관리자)</a>
    	</div>
  	</div> 
  	<div class="mainwrapper">
  		<div id="notiwrapper">
      		<div class="forum-category rounded top">
      			<label id="lbtitle">제목</label>
        		<div id="noticetitle">
					${noticement.title}
        		</div>
        		<div class="mpad">
        			<div class="al">
        				
        			</div>
	        		<div class="ar">
	        			<button id="goback" onclick="golist();">목록으로 돌아가기</button>
	        			<%-- form 없이 순수 js만으로 "수집"해서 전달해보기 --%>
	          			<button id="upd" onclick="updaterequest('${noticement.serial}');"> 수정하기 </button>
	          			<button id="del" onclick="deleterequest('${noticement.serial}');">삭제하기</button>
	        		</div>
        		</div>
      		</div>
        	<div id="noticeinfos">
            	<ul> 
            		<li class="origin"><strong class="bb">기본 게시글정보</strong><li>
	                <li class="origin">게시글번호 : ${noticement.serial}</li>
	                <li class="origin">작성자 : ${noticement.usrId}</li>
	                <li class="origin">작성일 : ${noticement.createDate}</li>
	                <li class="origin">조회수 : ${noticement.readCount}</li>
            	</ul>
            	
            	<ul id="modilog">
					<li><strong class="br">수정기록</strong></li>
            		
            		<c:if test="${noticement.modifiedUsr ne null}">
	                <li>최종수정자 : ${noticement.modifiedUsr}</li>
	                </c:if>
	                
	                <c:if test="${noticement.modifiedUsr eq null}">
	                <li>최종수정자 : 수정된 기록이 없습니다.</li>
	                </c:if>
	                
	                <c:if test="${noticement.modifyDate ne null}">
	                <li>최종수정일 : ${noticement.modifyDate}</li>
	                </c:if>
	                
	                <c:if test="${noticement.modifyDate eq null}">
	                <li>최종수정일 : 수정된 기록이 없습니다.</li>
	                </c:if>
	                
            	</ul>
        	</div>
        	<div id="noticecontent">
          		<div id="content" >
          			<c:out value="${noticement.contents}" escapeXml="false" />
          		</div>
        	</div>
        	
    		<div class="filelist">
    			
     			<c:if test="${empty noticement.uFileList}">
     				<label>등록된 파일이 없습니다!</label>
     			</c:if>
     			<c:if test="${not empty noticement.uFileList}">
     			<label>등록된 파일</label>
     			<ul>
     				<c:forEach items="${noticement.uFileList}" var="i">
     					<li><a href="http://localhost:80/Link/notice/download?fileCode=${i.uFileCode}">${i.uFileOriginName}</a></li>
     				</c:forEach>
     			</ul>
     			</c:if>
      		</div>
  		</div>
  	</div>
</section>
</body>
<script>

    </script>
</html>     
 