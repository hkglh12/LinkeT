<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
    <title>Link Noticeboard</title>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <script src="${pageContext.request.contextPath}/a/js/jquery-3.5.1.js"></script>
    <script src="${pageContext.request.contextPath}/a/js/User/noticement/board.js"></script>
    <link href="${pageContext.request.contextPath}/a/css/User/noticement/board.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/a/css/Commons/column.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/a/css/Commons/board_structure.css" rel="stylesheet">
	<link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">

</head>
<body>
<jsp:include page="../../root-view.jsp"/>
<section>
	<header id="atop">
    	<div class="row">
      		<div class="main lpad header location">
        		<div class="logo">
          			<span>현재위치 : </span>
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
      		<a href="#" class="underline">${sessionScope.usrId}</a>
    	</div>
  	</div> 
  	<div class="mainwrapper" id="test">
  		<div class="dashboard mt curr">
      		<div class="large-12 forum-category rounded top">
        		<div class="column lpad categ">
          			공지사항
        		</div>
        		<div class="mpad ar"></div>
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
              				<a href="#"></a>
            			</span>
            			<span><a href="#"></a></span>
          			</div>
        		</div>
        	</c:forEach>
        </c:if>
		<c:if test="${not empty noticelist}">
         	<c:forEach items="${noticelist}" var="notice">         
        		<div class="large-12 forum-topic" onclick="pagecall(${notice.serial})">
          			<div class="large-1 column lpad">
            			<i class="icon-file"></i>
          			</div>
          			<div class="large-70 small-8 column lpad">
            			<span class="overflow-upper">#<c:out value="${notice.serial}"/></span>
            			<span class="overflow-control"><a href="#"> <c:out value="${notice.title}"/></a></span>
          			</div>
          			<div class="large-5 column ltpad">
            			<span class="center" id="total">${notice.fileCount}</span>
          			</div>
          			<div class="large-5 column ltpad">
            			<span class="center" id="counts">${notice.readCount}</span>
          			</div>
          			<div class="large-15 small-4 column lpad">
            			<span><fmt:formatDate value="${notice.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
            			<span>by <!-- <a href="#"> -->${notice.usrId} (관리자)<!-- </a> --></span>
          			</div>
        		</div>
        	</c:forEach>
        </c:if>
  	</div>
    <div class="page_block">
    	<ul class="blocks">
      	<!-- //https://yoonka.tistory.com/459 -->
         <!-- https://blog.nerdfactory.ai/2019/05/05/spring-mvc-jstl.html -->
        	<fmt:parseNumber var="comment_block" value="${(commentlength/8) + (commentlength%8 == 0 ? 0 :1)}" integerOnly="true"></fmt:parseNumber>
       <%-- <c:set var="block" value="${total}/8"/> --%>
      		<fmt:parseNumber var="block" value ="${(total/8)+(total%8 == 0 ? 0 : 1)}" integerOnly="true"/>
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
