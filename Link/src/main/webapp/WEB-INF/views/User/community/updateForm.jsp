<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Link - 게시글 수정</title>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link href="${pageContext.request.contextPath}/a/css/Commons/posting_structure.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/a/css/Commons/column.css" rel="stylesheet">
   	<link href="${pageContext.request.contextPath}/a/css/User/community/updateForm.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css" integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc" crossorigin="anonymous">
    <script src="${pageContext.request.contextPath}/a/js/jquery-3.5.1.js"></script>
    <script src="${pageContext.request.contextPath}/a/js/User/community/updateForm.js"></script>
    <script src="${pageContext.request.contextPath}/a/js/jquery.MultiFile.js"></script>
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
 <!-- Summernote Setting -->
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<!-- include summernote css/js -->
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

</head>
<body>
<jsp:include page="../../root-view.jsp"/>
<section>
	<header id="atop">
    	<div class="row">
      		<div class="main lpad header location">
        		<div class="logo">
          			<span>${community.subject}</span>
          			<span id="main_header_location_t_name"> : 수정</span>
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
      		Welcome, <a href="#" class="underline">${sessionScope.usrId}</a>
    	</div>
  	</div>
  	<div class="mainwrapper">
  		<div id="notiwrapper">
     		<form action="/Link/community/update" method="post" enctype="multipart/form-data" id="updform">
     			<input type="hidden" value="${community.serial}">
      				<div class="forum-category rounded top">
        				<div id="noticetitle">
        				    <label> 제목 </label>
        				    <input type="hidden" value="${community.subject}" name="subject" id="subject">
        					<input type="hidden" value="${community.serial}" name="c_serial">
          					<input type="text" value="${community.title}" name="c_title" id="c_title">
        				</div>
        				<div class="mpad ar">
          					<button id="submitbtn" type="button"> 갱신하기 </button>
        				</div>	
      				</div>
      				<div id="noticecontent_wrapper">
       				<label>게시글 수정</label>
        			<div id="noticecontent">
          				<textarea name="c_contents" id="c_contents">${community.contents}</textarea>
        			</div>
        			</div>
    				<div id="uploadfiles">
    					<div id="upload_info">
        					<label>기존에 첨부되어있던 파일입니다.</label>
		        		</div>
     					<div id="previousuploaded">
     						<c:if test="${empty community.uFileList}">
     							<label>첨부된 파일이 없습니다!</label>
     						</c:if>
     						<c:if test="${not empty community.uFileList}">
     						<ul>
     							<c:forEach items="${community.uFileList}" var="i">
     								<li><button type="button" class="delfile" onclick="delthiscode(this);"><i class="fas fa-times"></i></button><input type="text" name="previous" value="${i.uFileOriginName}" readonly>
     								<input type="hidden" class ="tgf_code" value="${i.uFileCode}"></li>
     							</c:forEach>
     							</ul>
          					</c:if>
          				</div>
          				<div id="upload_info">
        					<label>신규 업로드할 파일을 선택해주세요. 최대 업로드 크기는 5MB입니다.</label>
        				</div>
     					<div id="newlyuploaded">
							<input type="file" class="multi" name="u_files"/>
						</div>
      				</div>
    			</form>
  			</div>
  		</div>
    </section>
</body>
</html>     
 