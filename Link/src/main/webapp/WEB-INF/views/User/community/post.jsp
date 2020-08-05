<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="description" content="">
    <meta name="keywords" content="">
	<title>Insert title here</title>
	
    <script src="${pageContext.request.contextPath}/a/js/jquery-3.5.1.js"></script>
    <script src="${pageContext.request.contextPath}/a/js/jquery.MultiFile.js"/></script>
    <script src="${pageContext.request.contextPath}/a/js/User/community/post.js"></script>
    <link href="${pageContext.request.contextPath}/a/css/User/community/post.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/a/css/Commons/column.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/a/css/Commons/posting_structure.css" rel="stylesheet">
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
<jsp:include page="../../root-view.jsp"/>

<section>
	<header id="atop">
	   <div class="row">
	     <div class="main lpad header location">
	       <div class="logo">
	         <span>${subject} : </span>
	         <span id="main_header_location_t_name"> 글 작성</span>
	       </div>
	     </div>
	     <div class="main header submenu ar">
	       <nav class="menu">
	         <a href="#" class="current" id="dashboard"></a>
	       </nav>     
	     </div>
	   </div>
	</header>
	<a href="atop" id="top-button"> <i class="icon-angle-up"></i> </a>
	
	<div class="row">
	    <div class="main lpad greets ar">
	    	Welcome,<a href="#" class="underline">${sessionScope.usrId}</a>
	    </div>
	</div>
	
	<div class="mainwrapper">
  		<div id="notiwrapper">
    		<form action="/Link/community/post" method="post" enctype="multipart/form-data" id="communityform">
    			<input type="hidden" name="c_subject" value="${subject}"> 
      			<div class="forum-category rounded top">
        		<div id="noticetitle">
        			<label> 제목 </label>
	          		<input type="text" placeholder="" name="c_title" id="c_title">
        		</div>
        		<div class="mpad ar">
        			<button id="txsubmit" type="button" onclick="community_submit();"> 게시하기 </button>
        		</div>
        		</div>
        		<div id="noticecontent_wrapper">
       			<label>게시글 내용 입력</label>
       			<div id="noticecontent">
          			<textarea name="c_contents" id="c_contents"></textarea>
	        	</div>
	        	</div>		
	        	<div id="upload_info">
        			<label>최대 업로드 크기는 5MB입니다.</label>
        		</div>
	    		<div id="uploadfiles">
    				<input type="file" class="multi" name="u_files"/>
	      		</div>
    		</form>
  		</div>
  	</div>
</section>
</body>
</html>