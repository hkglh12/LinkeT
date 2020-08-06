<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Administrator : ${param.subject} 게시판</title>
    <script src="${pageContext.request.contextPath}/a/js/jquery-3.5.1.js"></script>
    <script src="${pageContext.request.contextPath}/a/js/Admin/manage/community/board.js"></script>
    <link href="${pageContext.request.contextPath}/a/css/Admin/manage/community/board.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/a/css/Commons/column.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/a/css/Commons/board_structure.css" rel="stylesheet">
    <script>
		if("${result}" == "true"){
			alert("반영에 성공하였습니다!");
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
          			<span>관리자페이지 : </span>
          			<input type="hidden" id="subject" value="${param.subject}">
          			<span id="main_header_location_t_name"> ${param.subject}</span>
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
      		<a href="#" class="underline">${sessionScope.usrId}</a>
    	</div>
  	</div> 
  	<div class="mainwrapper" id="test">
	<div class="dashboard mt curr">
      	<div class="large-12 forum-category rounded top">
        	<div class="column lta categ">
          		<label>${param.subject} 게시판</label>
        	</div>
        	<div id ="search_comm">
        		<div id="board_select" onchange="changeboard();">
        			<select id="boardlist">
        				<option value="${param.subject}" selected>--게시판선택--</option>
        				<option value="java">java</option>
        				<option value="jsp">jsp</option>
        				<option value="spring">spring</option>
        			</select>
        		</div>
        		<form action="/Link/community/list" method="GET">
        			<select id="search_category" name="search_category">
        				<option value="title" selected>글 제목</option>
        				<option value="id" >작성자</option>
        			</select>
        			<input type="text" id="search_target" name="search_target">
        			<input type="hidden" value="${param.subject}" name="subject" id="subject">
        			<button type="submit">검색하기</button>
        		</form>
        	</div>
       		<div class="mpad ar">
       			<button type="button" id="bulk_delete_submit" onclick="bulk_del();">글 대량삭제</button>
        	</div>
      </div>
      <div class="toggleview">
      		<div class="large-12 forum-head">
         		<div class="large-2 column lpad">
         			삭제
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
	        <input type="hidden" id="search_category_hd" value="${request.getParameter('search_category')}">
	        <input type="hidden" id="search_target_hd" value="${request.getParameter('search_target')}">

       		<c:if test="${empty communitylist}">
	        	<div class="large-12 forum-topic">
	          		<div class="large-1 column lpad">
	            		<i class="icon-file"></i>
	          		</div>
	          		<div class="large-70 small-8 column lpad">
	            		<span class="overflow-upper">
	              			 <c:out value="게시글 없음!"/>
	            		</span>
	            		<span class="overflow-control">
	              			<a href="#"><c:out value="아직 게시된 글이 없어요!"/></a>
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
			<c:if test="${not empty communitylist}">	
         		<c:forEach items="${communitylist}" var="community" varStatus = "number">
        			<div class="large-12 forum-topic cal">
          				<div class="large-1 column lpad">
          					<input type="checkbox" class="bulk_delete_check" value="${community.serial}">
          				</div>
          				<div class="large-70 small-8 column lpad" onclick="pagecall('${community.serial}')">
            				<span class="overflow-upper">
              					 #<c:out value="${total - ((param.page-1) * 10) - number.count+1}"/>
            				</span>
            				<span class="overflow-control">
              				<a>
              					<c:out value="${community.title}    [${community.commentsCount}]"/>
            				</a>
            				</span>
          				</div>
          				<div class="large-5 column ltpad">
            				<span class="center" id="total">${community.fileCount}</span>
          				</div>
          				<div class="large-5 column ltpad">
            				<span class="center" id="counts">${community.readCount}</span>
          				</div>
          				<div class="large-15 small-4 column lpad">
            				<span><fmt:formatDate value="${community.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
            				<span>by <!-- <a href="#"> -->${community.usrId}<!-- </a> --></span>
          				</div>
        		</div>
        	</c:forEach>
     	</c:if>
	</div>
    <div class="page_block">
		<ul class="blocks">
    	<fmt:parseNumber var="block" value = "${(total/10)+ (total%10 == 0 ? 0 : 1)}" integerOnly="true"/>
        	<c:if test="${block gt 0}">
    	      		<c:forEach begin="1" end="${block}" var="i" step="1">
        	  			<c:if test="${i eq param.page}">
         		 			<li><a class="blockcurr" onclick="blockmove('${i}')">${i}</a></li>
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
</html>
