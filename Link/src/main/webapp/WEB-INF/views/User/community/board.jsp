<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Link - ${param.subject} 게시판</title>
    <script src="${pageContext.request.contextPath}/a/js/jquery-3.5.1.js"></script>
    <script src="${pageContext.request.contextPath}/a/js/User/community/board.js"></script>
    <link href="${pageContext.request.contextPath}/a/css/User/community/board.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/a/css/Commons/column.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/a/css/Commons/board_structure.css" rel="stylesheet">
    <!-- <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css" integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc" crossorigin="anonymous"> -->
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
          			<span>현재위치 : </span>
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
        		<button type="button" id="community_post"> 글 게시하기</button>
        	</div>
      </div>
      <div class="toggleview">
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
        			<div class="large-12 forum-topic cal" onclick="pagecall('${community.serial}')">
          				<div class="large-1 column lpad">
            				<span class="overflow-upper">
              					 #<c:out value="${total - ((param.page-1) * 8) - number.count+1}"/>
            				</span>
            				
          				</div>
          				<div class="large-70 small-8 column lpad">
            				
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
            				<span>${community.createDate}</span>
            				<span>by <!-- <a href="#"> -->${community.usrId}<!-- </a> --></span>
          				</div>
        		</div>
        	</c:forEach>
     	</c:if>
	</div>
    <div class="page_block">
		<ul class="blocks">
    	<fmt:parseNumber var="block" value = "${(total/8)+ (total%8 == 0 ? 0 : 1)}" integerOnly="true"/>
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
