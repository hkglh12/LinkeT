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
	
    <script src="<c:url value="/a/js/jquery-3.5.1.js"/>"></script>
    <script src="<c:url value="/a/js/jquery.MultiFile.js"/>"></script>
    
    
    
   <%--  <link href="<c:url value="/a/css/main.css"/>" rel="stylesheet">
    <script src="<c:url value="/a/js/main.js"/>"></script> --%>
    <link href="<c:url value="/a/css/communityPost.css"/>" rel="stylesheet">
    <script src="<c:url value="/a/js/communityPost.js"/>"></script>

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
<jsp:include page="root-view.jsp"/>

<section>
	<header id="atop">
	   <div class="row">
	     <div class="main lpad header location">
	       <div class="logo">
	         <span>CurrentLocation : </span>
	         <span id="main_header_location_t_name">/community/posting</span>
	       </div>
	     </div>
	     <div class="main header submenu ar">
	       <nav class="menu">
	         <a href="#" class="current" id="dashboard">Dashboard</a>
	         <a href="#" id="teaminfo">Team information</a>
	         <a href="#" id="noticement">Noticement</a>
	         <a href="#" id="community">Community</a>
	         <a href="#" id="shareboard">Share board</a>
	       </nav>     
	     </div>
	   </div>
	</header>
	<a href="atop" id="top-button"> <i class="icon-angle-up"></i> </a>
	
	<div class="row">
	    <div class="main lpad greets ar">
	    	Welcome,<a href="#" class="underline">${sesison.getAttribute("usrId")}</a>
	    </div>
	</div>
	
	<div class="mainwrapper">
  	<div id="notiwrapper">
    	<form action="/Link/community/post" method="post" enctype="multipart/form-data">
      		<div class="forum-category rounded top mgx">
        	<div id="noticetitle">
          		<input type="text" placeholder="제목 입력" name="c_title" id="c_title">
        	</div>
        	<div class="mpad ar">
          		<button id="txsubmit" type="submit"> 게시하기 </button>
        	</div>
	       		<div id="noticecontent">
	          		<textarea name="c_contents" id="c_contents"></textarea>
	        	</div>
			</div>
    	<div id="uploadfiles">
    		<input type="file" class="multi" name="u_files"/>
      	</div>
    	</form>

  	</div>
  </div>
</section>
</body>
<script>
	$('#c_contents').summernote();

</script>
</html>