<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <c:set var="server" value= "${pageContext.request.remoteAddr}"></c:set>
<c:set var="port" value="${pageContext.request.serverPort}"></c:set> --%>
<!DOCTYPE html>
<html>
<head>
    <title>관리자 : 게시글</title>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
    

    <script src="${pageContext.request.contextPath}/a/js/jquery-3.5.1.js"></script>
    <script src="${pageContext.request.contextPath}/a/js/Admin/manage/community/read.js"></script>
   	<link href="${pageContext.request.contextPath}/a/css/Commons/column.css" rel="stylesheet">  
   	<link href="${pageContext.request.contextPath}/a/css/Commons/board_structure.css" rel="stylesheet">
   	<link href="${pageContext.request.contextPath}/a/css/Admin/manage/community/read.css" rel="stylesheet">
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
        
        <!-- Summernote Setting -->
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<!-- include summernote css/js -->
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	<script src="${pageContext.request.contextPath}/a/summernote/summernote-ko-KR.js"></script>
	<!-- AJAX Read Timestamp 관리 -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
	<script>
		var result = "${result}";
		if(result == "true"){
			alert("작업을 성공하였습니다!");
		}
	</script>
</head>
<body>
<jsp:include page="../../root-view.jsp"/>
	<section>
  		<header id="atop">
    		<div class="row">
      			<div class="main lpad header location">
        			<div class="logo">
          				<span>관리자 : 게시글 조회</span>
          				<span id="main_header_location_t_name"></span>
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
      			Welcome,<a href="#" class="underline">${sessionScope.usrId}</a>
   			</div>	
  		</div>
  		<div class="mainwrapper">
  			<div id="notiwrapper">
      			<div class="forum-category rounded top">
        			<div id="noticetitle">

        			<label class="title_upper">제목</label><br>
          			<label class="title_lower">${community.title}</label>
        			</div>
        			<input type="hidden" id="subject" value="${community.subject}">
	        		<div class="mmpad ar">
	        			<button id="goback" onclick="gobacklist();">목록으로 돌아가기</button>
	          			<button id="del">게시글 강제삭제</button>
	        		</div>
      			</div>
        		<div id="noticeinfos">
            		<ul>
		                <li>게시글번호 : ${community.serial}</li>
		                <li>작성자 : ${community.usrId}</li>
		                <li>작성일 : <fmt:formatDate value="${community.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
		                <li>조회수 : ${community.readCount}</li>
		            </ul>
        		</div>
        		<div id="noticecontent">
        			<input type="hidden" id="c_serial" value="${community.serial}">
        			<label>게시글 내용</label>
          			<div id="content">${community.contents}</div>
        		</div>

    			<div id="uploadfiles_wrapper">
    			<label>첨부된 파일</label>
     <%-- <c:if test="${fn:length(list) eq 0}"> --%>
     <!-- 올림의 쉬운 이해 -->
     <%-- <fmt:parSeNumber var="pageCount" value="${count/pageSize + (count%pageSize==0 ? 0 : 1)}" intergerOnly="true"/> --%>
     <!-- https://okky.kr/article/187379  -->
     			<div id="uploadfiles">
     				<ul>
     				<c:if test="${empty community.uFileList}">
     					<label>첨부된 파일이 없습니다!</label>
     				</c:if>
     						
     				<c:if test="${not empty community.uFileList}">
     					<c:forEach items="${community.uFileList}" var="i">
     						<li><a href="http://localhost:80/Link/community/download?fileCode=${i.uFileCode}">${i.uFileOriginName}</a></li>
    <%-- <label class="uFileCode">${i.uFileCode}</label> --%>
     					</c:forEach>
     				</c:if>
     				</ul>
      			</div>
      			</div>
  			</div>
  		</div>

	<c:set var="commentlength" value="${total_comment}"></c:set>
	<div id="comment_start">
		댓글목록
		<input type="hidden" id="current_userid" value="${sessionScope.usrId}">
		<input type="hidden" id="community_userid" value="${community.usrId}">
	</div>
    	<div class="comment part">
    		
    		<div class="registered comment">
    	    	<c:if test="${empty community.comments}">
    	    		<div class="ctx_comment_wrapper">
     					<label>아직 등록된 댓글이 없습니다</label>
     				</div>
     			</c:if>
    			<c:if test="${not empty community.comments}">
     				<c:forEach items="${community.comments}" var="i">
	     					<!-- 구분선 -->
	     					<div class="ctx_comment_wrapper">
	     						<div class="commenter">
          							<div class="image-wrapper"></div>
		     						<label class="comm usrlbl">유저아이디 : ${i.usrId}</label>
		     						<label class="comm usrlbl">게시날짜 : <fmt:formatDate value="${i.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></label>
	     							
	     							<div class="toglecomm">

			     						<form action="/Link/admin/manage/comment/ban" method="post" class="delform">
			     							<input type="hidden" value="${community.serial}" name="c_serial">
			     							<input type="hidden" value="${i.serial}" name="del_serial"> 
			     						</form>

		     						<button type="button" class="comment_del">댓글 강제삭제</button>
		     						</div>
		     						<c:if test="${i.checkSecret eq true}">
	     							<label class="col red usrlbl">비밀게시글입니다.</label>
	     							</c:if>
	     						</div>

	     						<div class="comment_contents">
		     							${i.contents}
		     					</div>
	     					</div>
		     		</c:forEach>
    			</c:if>
    		</div>
    	</div>
    	<div class="comment_page_block">
    		<ul class="comment_blocks">
    			<fmt:parseNumber var="comment_block" value="${(commentlength/10) + (commentlength%10 == 0 ? 0 :1)}" integerOnly="true"></fmt:parseNumber>
    		<%-- <fmt:parseNumber var="comment_blocka" value = "${(commentlength/10)+1}" integerOnly="true"/> --%>
    			<c:if test="${comment_block gt 0}">
	        		<c:forEach begin="1" end="${comment_block}" var="i" step="1">
	          			<li><a onclick="commentblockmove('${i}')">${i}</a></li>
          			</c:forEach>
          		</c:if>
    		</ul>
    	</div>	
    </section>
</body>
<script>

</script>
</html>     
 