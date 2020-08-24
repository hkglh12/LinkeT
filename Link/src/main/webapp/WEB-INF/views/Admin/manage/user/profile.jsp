 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Administrator - User Profile</title>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
  
 	<%-- 또다른 실행방법. pageContext- 로 가져오는 방법과 c:url로 가져오는 방법이 있다
 	차이점은 JSP의 내장객체를 사용하는가, 혹은 JSTL의 C: 태그를 사용하는가 차이. --%>
    <link href="<c:url value="/a/css/Admin/manage/user/profile.css"/>" rel="stylesheet">
    <script src="<c:url value="/a/js/jquery-3.5.1.js"/>"></script>
    <script src="<c:url value="/a/js/Admin/manage/user/profile.js"/>"></script>
    <script src="<c:url value="/a/js/encryptor.js"/>"></script>

</head>
<body>

<jsp:include page="../../root-view.jsp"/>

<section>
<%-- 강퇴처리 후 리턴할 위치를 저장 --%>
	<input type="hidden" name="curr_page" id="currpage" value="${page}">
	<input type="hidden" value="${main_category}" name="main_category" id="main_category">
	<input type="hidden" value="${sub_category}" name="sub_category" id="sub_category">
	<input type="hidden" id="search_target" name="search_target" value="${search_target}">
   <!--상단 Site navigator-->
<div class="upper">
        <div class="info"><h1 class="title">유저 개인정보 보기</h1></div>
    </div>
    <!--profile 내용부-->
    <div class="profilebg">
    <div class="profile">
    <div class="container">
      
      <div class="row cf">
        <div id="card1" class="card four col">
          <div class="image-wrapper"></div>
          <input type="hidden" value="${user.usrId}" id="user_id">
          <h3 class="name">${user.usrId}</h3>
          <div class="info cf">
            <div class="four col">PhoneNumber<span class="number">${user.usrPhone}</span></div>
            <div class="four col">Email<span class="number">${user.usrEmail}</span></div>
            <div class="four col">JoinDate<span class="number"><fmt:formatDate value="${user.usrInDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></div>
              <div class="four col">
              <%-- 이전 위치로 돌아가기 --%>
            	<button type="button" id="gobacklist" onclick="goback();">돌아가기</button>
            	<%-- 관리자 / 이미 탈퇴된사람?  --%>
            	<c:if test="${ user.usrLevel ne 99 and user.usrOutDate eq null and user.usrKickedDate eq null}">
              <button type="button" id="bann" onclick="bann('${user.usrId}');">강퇴시키기</button>
              </c:if>
              </div>
              <div class="four col">
              	<c:choose>
              		<c:when test="${user.usrKickedDate ne null}">
              			<span class="number redlbl"><c:out value="상태 : 강퇴"></c:out></span>
              			<span class="number"><c:out value="처리 : ${user.usrBannedId}"></c:out></span>
              			<span class="number redlbl"><c:out value="일시 : "></c:out><fmt:formatDate value="${user.usrKickedDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
              		</c:when>
              		<c:when test="${user.usrOutDate ne null}">
              			<span class="number"><c:out value="상태 : 탈퇴"></c:out></span>
              			<span class="number"><c:out value="탈퇴날짜 : "></c:out><fmt:formatDate value="${user.usrOutDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
              		</c:when>
              		<c:when test="${user.usrKickedDate eq null and user.usrOutDate eq null}">
              			<span class="number"><c:out value="상태 : 정상"></c:out></span>
              		</c:when>
              	</c:choose>
              </div>
          </div>
  
        </div>

        <div id="card2" class="card four col">
          <div class="header">

          </div>
          <div class="wrapper">
              <div id="attributions">
                  <ul>
                      <li class="attrs">
                      <p class="card2 infoexp" id="c1">작성한 글의 갯수</p></li>
                      <hr>
                      <li class="cont"><label class="cd2 lb info"><strong>${user.communityCount}</strong> 개 </label>
                  </ul>
                  <ul>
                      <li class="attrs">
                      <p class="card2 infoexp" id="c1">최근 작성한 게시글 </p><button class="godetail" onclick="direct_search();">전체보기&gt;</button></li>
                      <hr>
                      <li class="cont"><label class="cd2 lb info"><fmt:formatDate value="${last_community.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/> : ${last_community.title}</label>
                      
                  </ul>
                    <ul>
                      <li class="attrs">
                      <p class="card2 infoexp" id="c1">작성한 댓글의 갯수</p></li>
                      <hr>
                      <li class="cont"><label class="cd2 lb info"><strong>${user.commentCount} </strong>개</label>
                  </ul>
                  <ul>
                      <li class="attrs">
                      <p class="card2 infoexp" id="c1">최근 작성한 댓글</p> <button class="godetail" onclick="direct_comment_search('${user.usrId}');">전체보기&gt;</button></li>
                  
                      <hr>
                      <li class="cont"><label class="cd2 lb info"><fmt:formatDate value="${last_comment.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/> : ${last_comment.contents}</label> </label>
                  </ul>
              </div>
          </div>
        </div>
</div>

      </div>
    </div>
    </div>
    </section>
    
    </body>

</html>
