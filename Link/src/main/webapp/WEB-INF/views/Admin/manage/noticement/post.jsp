<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Link administrator : 공지사항</title>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
    
    <link href="${pageContext.request.contextPath}/a/css/Admin/manage/noticement/post.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/a/css/Commons/column.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/a/css/Commons/posting_structure.css" rel="stylesheet">
    
    <script src="${pageContext.request.contextPath}/a/js/jquery-3.5.1.js"></script>
   	<script src="${pageContext.request.contextPath}/a/js/jquery.MultiFile.js"></script>
   	<script src="${pageContext.request.contextPath}/a/js/jquery.MultiFile.min.js"></script>
   	<script src="${pageContext.request.contextPath}/a/js/Admin/manage/noticement/post.js"></script>
    <script src="${pageContext.request.contextPath}/a/js/Commons/navReact.js"></script>
    
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
    <!-- Summernote Setting -->
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<!-- include summernote css/js -->
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	<script src="${pageContext.request.contextPath}/a/summernote/summernote-ko-KR.js"></script>
</head>
<body>
	<jsp:include page="../../root-view.jsp"/>
 	<section>
  		<header id="atop">
    		<div class="row">
	      		<div class="main lpad header location">
	        		<div class="logo">
	          			<span>관리자페이지 : </span>
	          			<span id="main_header_location_t_name">공지사항 작성</span>
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
      			<a href="#" class="underline">${sessionScope.usrId}(관리자)</a>
    		</div>
  		</div>
  		<div class="mainwrapper">
  			<div id="notiwrapper">
			     <form id="noticeform" action="/Link/admin/manage/notice/post" method="post" enctype="multipart/form-data">
      				<div class="forum-category rounded top">
				        <div id="noticetitle">
          					<input type="text" placeholder="제목 입력" name="n_title" id="n_title">
        				</div>
        				<div class="mpad ar">
          					<button id="noticesubmit" type="button"> 게시하기 </button>
        				</div>
      				</div>
        			<div id="noticecontent">
          				<textarea name="n_contents" id="n_contents"></textarea>
        			</div>
        			<div id="upload_info">
        				<label>최대 업로드 크기는 5MB입니다.</label>
        			</div>
    				<div id="uploadfiles">
    				<!-- class="multi" -->
    					<input type="file" id="u_files" class="multi" name="u_files"/>
      				</div>

    			</form>
  			</div>
  		</div>
    	</section>
    </body>

</html>     
 