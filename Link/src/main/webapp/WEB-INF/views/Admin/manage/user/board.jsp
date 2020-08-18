<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Link - 사용자관리</title>
    <script src="${pageContext.request.contextPath}/a/js/jquery-3.5.1.js"></script>
    <script src="${pageContext.request.contextPath}/a/js/Admin/manage/user/board.js"></script>
    <script src="${pageContext.request.contextPath}/a/js/Commons/navReact.js"></script>
    <link href="${pageContext.request.contextPath}/a/css/Admin/manage/user/board.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/a/css/Commons/board_structure.css" rel="stylesheet">
    <!-- <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css" integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc" crossorigin="anonymous"> -->
    <script>
    	var result = "${result}";
    	if(result == "true"){
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
          			<span id="main_header_location_t_name"> 사용자관리 </span>
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
        	<c:if test="${main_category eq 'all'}">
          		<label>전체사용자</label>
          	</c:if>
          	<c:if test="${main_category eq 'normal'}">
          		<label>정상사용자</label>
          	</c:if>
          	<c:if test="${main_category eq 'banout'}">
          		<label>탈퇴/강퇴 사용자</label>
          	</c:if>
        	</div>
        	<div id ="search_comm">		
	       	        <select id="maincategorylist" onchange="changeboard();">
	       				<option>--출력타입--</option>
	       				<option value="all">전체 사용자</option>
	       				<option value="normal" >정상 사용자</option>
	       				<option value="banout" >탈퇴/강퇴 사용자</option>
	       				<option value="admin">관리자</option>
	       			</select>
        		<form action="/Link/admin/manage/user/list" method="GET">
        			<select id="subcategorylist" onchange="sync_sub_category();">
        				<option>--검색조건--</option>
        				<option value="id">아이디</option>
        				<option value="name">이름</option>
        				<option value="email">이메일</option>
        			</select>
        			<input type="hidden" value="${main_category}" name="main_category" id="main_category">
        			<input type="hidden" value="${sub_category}" name="sub_category" id="sub_category">
        			<input type="text" id="search_target" name="search_target" value="${search_target}">
        			<button type="submit">검색하기</button>
        		</form>
        	</div>
       		<div class="mpad ar">
       			<c:if test="${category ne'banout'}">
        		<button type="button" id="ban_submit" onclick="ban_user();">강퇴처리하기</button>
        		</c:if>
        	</div>
      </div>
      <div class="toggleview">
      		<div class="large-12 forum-head">
         		<div class="large-4 column lpad">
         			계정<br>상태
         		</div>
         		<div class="large-15 column lpad">
            		ID
          		</div>
          		<div class="large-5 column lpad">
            		이름
          		</div>
          		<div class="large-10 column lpad">
            		전화번호
          		</div>
          		<div class="large-15  column lpad">
            		이메일
          		</div>
          		<div class="large-5 column lpad">
            		작성<br>게시글<br>개수
          		</div>
          		<div class="large-5 column lpad">
            		작성<br>댓글<br>개수
          		</div>
          		<div class="large-5 column lpad">
            		업로드<br>파일<br>개수
          		</div>
          		<div class="large-12c column lpad">
            		가입날짜
          		</div>
          		<div class="large-12c column lpad">
            		탈퇴/강퇴 날짜
          		</div>
          		<div class="large-12c column lpad">
            		강퇴처리자
          		</div>
        	</div>
       		<c:if test="${empty list}">
	        	<div class="large-12 forum-topic">
	          		<div class="large-1 column lpad">
	          		</div>
	          		<div class="large-70 column lpad">
	            		<span class="overflow-upper">
	              			
	            		</span>
	            		<span class="overflow-control">
	              			<a href="#"><c:out value="아직 해당 유저가 없어요!"/></a>
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
			<c:if test="${not empty list}">	
				<form action="/Link/admin/manage/user/ban" id="target_list" name="target_list">
				<input type="hidden" name="curr_page" id="currpage" value="${page}">
				</form>
         		<c:forEach items="${list}" var="user" varStatus = "number">
        			<div class="large-12 forum-topic">
          				<div class="large-4 column lpad">
            				<span class="overflow-upper">
            					<c:choose>
	            					<c:when test="${user.usrOutDate eq null and user.usrKickedDate eq null}">
	              						<c:out value="정상"/>
	              					</c:when>
	              					<c:when test="${user.usrOutDate ne null and user.usrKickedDate eq null }">
	              						<c:out value="탈퇴"/>
	              					</c:when>
	              					<c:when test="${user.usrOutDate eq null and user.usrKickedDate ne null }">
	              						<c:out value="강퇴"/>
	              					</c:when>
              					 </c:choose>
            				</span>
          				</div>
          				<div class="large-15 column lpad cal" onclick='userdetail("${user.usrId}");'>
            				<span class="overflow-control">
              				<a> <c:out value="${user.usrId}"/> </a>
            				</span>
          				</div>
          				<div class="large-5 column lpad">
            				<span class="center" id="total">${user.usrName}</span>
          				</div>
          				<div class="large-10 column lpad">
            				<span class="center" id="counts">${user.usrPhone}</span>
          				</div>
          				<div class="large-15 column lpad">
            				<span class="center" id="counts">${user.usrEmail}</span>
          				</div>
          				<div class="large-5 column lpad">
            				<span class="center" id="counts">${user.communityCount}</span>
          				</div>
          				<div class="large-5 column lpad">
            				<span class="center" id="counts">${user.commentCount}</span>
          				</div>
          				<div class="large-5 column lpad">
            				<span class="center" id="counts">${user.fileCount}</span>
          				</div>
          				<div class="large-12c column lpad">
            				<span><fmt:formatDate value="${user.usrInDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
          				</div>
          				<div class="large-12c column lpad">
          					<c:choose>
	          					<c:when test="${user.usrOutDate eq null and user.usrKickedDate eq null}">
	          						<span> - </span>
	          					</c:when>
								<c:when test="${user.usrOutDate ne null and user.usrKickedDate eq ull }">	          	
            						<span><fmt:formatDate value="${user.usrOutDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
            					</c:when>
            					<c:when test="${user.usrOutDate eq null and user.usrKickedDate ne ull }">	          	
            						<span><fmt:formatDate value="${user.usrKickedDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
            					</c:when>
            				</c:choose>
          				</div>
          				<div class="large-12c column lpad">
          				<c:choose>
          					<c:when test="${user.usrLevel eq 99}">
          						<label>관리자</label>
          					</c:when>
          					<c:when test="${user.usrKickedDate ne null}">
          						<span class="center" id="counts">${user.usrBannedId}</span>
          					</c:when>
          					<c:when test="${user.usrOutDate eq null}">
          						<input type="checkbox" class="target_id" value="${user.usrId}">
          					</c:when>
          				</c:choose>
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
