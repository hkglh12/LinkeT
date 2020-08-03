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
    <link href="${pageContext.request.contextPath}/a/css/User/noticement/read.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/a/js/jquery-3.5.1.js"></script>
    <script src="${pageContext.request.contextPath}/a/js/User/noticement/read.js"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
   	<link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>

<jsp:include page="../../root-view.jsp"/>
 <section>
 <!--내용부 헤더-->

  <!--상단부 로고-->
  <header id="atop">
    <div class="row">
     <!--최상단 헤더, 위치안내용으로 쓰임 large-4 column lpad-->
      <div class="main lpad header location">
        <div class="logo">
          <span> 현재 페이지 : </span>
          <span id="main_header_location_t_name"> 공지사항 </span>
        </div>
      </div>
     	<div class="main header submenu ar">
        		<nav class="menu">
          			<a href="#" class="current" id="dashboard"></a>
		        </nav>    
      		</div>
    </div>
  </header>
  <!--내용부 헤더 종료-->
  <!--사용자 계정 맞이-->
  <div class="row">

    <div class="main lpad greets ar">
      Welcome,
      <a href="#" class="underline">${sessionScope.usrId}</a>
    </div>
  </div> 

  
  <div class="mainwrapper">
  <div id="notiwrapper">
      <div class="forum-category rounded top">
        <label id="lbtitle">제목</label>
        		<div id="noticetitle">
					${noticement.title}
        		</div>
        <div class="mpad ar">

        </div>
      </div>
        <div id="noticeinfos">
            <ul>
                <li>게시글번호 : ${noticement.serial}</li>
                <c:if test="${noticement.modifiedUsr eq null}">
                <li>작성자 : ${noticement.usrId}</li>
                </c:if>
                <c:if test="${noticement.modifiedUsr ne null}">
                <li>작성자 : ${noticement.modifiedUsr}</li>
                </c:if>
                
                <c:if test="${noticement.modifyDate eq null}">
                <li>작성일 : ${noticement.createDate}</li>
                </c:if>
                <c:if test="${noticement.modifyDate ne null}">
               	<li>작성일 : ${noticement.modifyDate}</li>
                </c:if>
                <li>조회수 : ${noticement.readCount}</li>
            </ul>
        </div>
        <div id="noticecontent">
          <div id="content" >
          <c:out value="${noticement.contents}" escapeXml="false" />
          </div>
        </div>
        
    <div id="uploadfiles">
     <%-- <c:if test="${fn:length(list) eq 0}"> --%>
     <c:if test="${empty noticement.uFileList}">
     <label>등록된 파일이 없습니다!</label>
     </c:if>
     <c:if test="${not empty noticement.uFileList}">
     <c:forEach items="${noticement.uFileList}" var="i">
     	<li><a href="http://localhost:80/Link/notice/download?fileCode=${i.uFileCode}">${i.uFileOriginName}
     	<%-- <label class="uFileCode">${i.uFileCode}</label> --%></a>
     </c:forEach>
     </c:if>
      </div>
        
<!--      </div>-->
  </div>
  </div>
  

    
    </section>
    </body>
    <script>
    </script>
</html>     
 