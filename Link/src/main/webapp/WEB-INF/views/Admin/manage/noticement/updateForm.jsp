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
    <link href="${pageContext.request.contextPath}/a/css/Admin/manage/noticement/updateForm.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/a/js/jquery-3.5.1.js"></script>
    <script src="${pageContext.request.contextPath}/a/js/jquery.MultiFile.js"></script>
    <script src="${pageContext.request.contextPath}/a/js/Admin/manage/noticement/updateForm.js"></script>
    <script src="https://kit.fontawesome.com/2313945ac3.js" crossorigin="anonymous"></script>
    <%-- <script src="${pageContext.request.contextPath}/a/summernote/summernote-ko-KR.js"></script> --%>
    <%--
    <script src="<c:url value="/a/summernote/summernote-ko-KR.js"/>"></script>
   	<script src="<c:url value="/a/js/jquery-3.5.1.js"/>"></script>
    <script src="<c:url value="/a/js/jquery.MultiFile.js"/>"></script>
    <script src="<c:url value="/a/js/noticeUpdateForm.js"/>"></script> --%>

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
          				<span>관리자페이지 : </span>
          				<span id="main_header_location_t_name">공지사항 수정</span>
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
     			<form action="/Link/admin/manage/notice/update" method="post" enctype="multipart/form-data" id="updform">
      				<div class="forum-category rounded top">
       					<div id="noticetitle">
       						<input type="hidden" value="${noticement.serial}" name="n_serial">
       						<input type="text" value="${noticement.title}" name="n_title">
       					</div>
       					<div class="mpad ar">
          					<button id="noticesubmit" type="submit"> 갱신하기 </button>
        				</div>
      				</div>
        			<div id="noticecontent">
          				<textarea name="n_contents" id="n_contents">${noticement.contents}</textarea>
        			</div>
        			<div class="upload_info">
        				<label class="text_info">기존에 등록해두었던 파일 리스트입니다.</label>
        			</div>
    				<div id="uploadfiles">
     					<div id="previous_upload">
     						<c:if test="${empty noticement.uFileList}">
     							
     						</c:if>
     						<c:if test="${not empty noticement.uFileList}">
     						<ul>
     							<c:forEach items="${noticement.uFileList}" var="i">
     								<li><input type="text" class="file_name" name="previous" value="${i.uFileOriginName}" readonly><button type="button" class="del_files" onclick="delconfirm(this);"><i class="fas fa-times"></i></button>
     								<input type="hidden" class ="tgf_code" value="${i.uFileCode}"></li>
     							</c:forEach>
     						</ul>
          					</c:if>
          				</div>
      				</div>
      				<div class="new_upload">
      					<label class="text_info">새로 등록하실 파일을 선택해주세요<br>
      					최대 파일 크기는 5MB입니다.</label>
      					<div id="new_upload_files">
							<input type="file" class="multi" name="u_files"/>
						</div>
					</div>
    			</form>
  			</div>
  		</div>
   	</section>
</body>
</html>     
 