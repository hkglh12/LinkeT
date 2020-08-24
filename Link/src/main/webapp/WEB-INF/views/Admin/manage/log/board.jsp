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
    <script src="${pageContext.request.contextPath}/a/js/Admin/manage/log/board.js"></script>
    <script src="${pageContext.request.contextPath}/a/js/Commons/navReact.js"></script>
    <link href="${pageContext.request.contextPath}/a/css/Admin/manage/log/board.css" rel="stylesheet">
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
          			<span id="main_header_location_t_name"> 로그감사 </span>
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
          		<c:if test="${(search_category eq null) and (search_target eq null)}">
          			<label>${param.subject} 게시판</label>
	          	</c:if>
	          	<c:if test="${(search_category ne null) and (search_category ne null)}">
          			<label>검색결과</label>
          		</c:if>
        	</div>
        	<div id ="search_comm">
        		<div id="board_select" onchange="changeboard();">
        		<!-- 날짜로 바뀔예정이에여 -->
        		<%-- <input type="hidden" name="start_date" id="start_date" value="${start_date}">
        		<input type="hidden" name="end_date" id="end_date" value="${end_date}"> --%>
        		
        		</div>
        		<!-- 검색조건으로 변경 -->
        		<input type="hidden" id="search_category" name = "search_category" value="${search_category}">
        		<form action="/Link/admin/manage/log/list" method="GET">
        		검색 기간 : <input type="date" name="start_date" id="start_date" value="${start_date}"> ~ <input type="date" id="end_date" name="end_date" value="${end_date}">
        			<select id="search_category" name="search_category">
        				<option value="" selected>검색조건</option>
        				<option value="u_id">접근자 ID</option>
        				<option value="targetservice" >대상 URI</option>
        				<option value="ip_addr" >대상 IP</option>
        			</select>
        			<input type="text" id="search_target" name="search_target" value="${search_target}">
        			<button type="submit">검색하기</button>
        		</form>
        	</div>
       		<div class="mpad ar">
       		
        	</div>
      </div>
      <div class="toggleview">
      		<div class="large-12 forum-head">
         		<div class="large-15 column lpad">
         			발생시각
         		</div>
         		<div class="large-30 column ltpad text-14">
            		접속대상
          		</div>
         		<div class="large-8 small-4 column lpad">
            		결과
          		</div>
          		<div class="large-9 small-4 column lpad">
            		소요시간 (ms)
          		</div>
          		<div class="large-15 column ltpad text-10">
            		User Id
          		</div>
          		<div class="large-15 small-8 column lpad">
            		Ip address
          		</div>
          		<div class="large-8 small-4 column lpad">
            		HTTP method
          		</div>

        	</div>
	        <input type="hidden" id="search_category_hd" value="${search_category}">
	        <input type="hidden" id="search_target_hd" value="${search_target}">

       		<c:if test="${empty list}">
	        	<div class="large-12 forum-topic">
	          		<div class="large-1 column lpad">
	            		<i class="icon-file"></i>
	          		</div>
	          		<div class="large-70 small-8 column lpad">
	            		<span class="overflow-upper">

	            		</span>
	            		<span class="overflow-control">
	              			<a href="#"><c:out value="아직 수집된 로그가 없어요!"/></a>
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
         		<c:forEach items="${list}" var="log" varStatus = "number">
        			<div class="large-12 forum-topic">
          				<div class="large-15 column lpad">
          					<span class="center occurTime"> ${log.occurTime}</span>
          				</div>
          				<div class="large-30 small-8 column ltgpad">
            				<span class="overflow-control">${log.targetService}</span>
          				</div>
          				<div class="large-8 column lpad">
            				<span class="center">${log.resultStatus}</span>
          				</div>
          				<div class="large-9 column lpad">
            				<span class="center" >${log.requiredTime}</span>
          				</div>
          				<div class="large-15 small-4 column lpad">
            				<span class="center" >${log.usrId}</span>
          				</div>
		   				<div class="large-15 small-4 column lpad">
            				<span class="center" >${log.ipAddr}</span>
          				</div>
           				<div class="large-8 small-4 column lpad">
            				<span class="center" >${log.method}</span>
          				</div>
        		</div>
        	</c:forEach>
     	</c:if>
	</div>
	<input type="hidden" name="page" id="page" value="${page}">
    <div class="page_block">
    <%-- total은 model의 attribute --%>
        <fmt:parseNumber var="total_page" value = "${(total/20)+ (total%20 == 0 ? 0 : 1)}" integerOnly="true"/>
    	<fmt:parseNumber var="now_block" value="${(page / 10)}" integerOnly = "true"/>
		<ul class="blocks">
			<li><a onclick="masive_left_move('${page}');"> 좌 </a></li>
	        	<c:if test="${total_page gt 0}">
	    	      		<c:forEach begin="${(now_block*10)+1}" end="${(now_block*10)+10}" var="i" step="1">
	    	      		<c:if test="${i le total_page }">
	        	  			<c:if test="${i eq param.page}">
	         		 			<li><a class="blockcurr" onclick="blockmove('${i}')">${i}</a></li>
	         	 			</c:if>
		         	 		<c:if test="${i ne param.page}">
			         	 		<li><a onclick="blockmove('${i}')">${i}</a></li>
		         	 		</c:if>
		         	 	</c:if>
	          		</c:forEach>
	          	</c:if>
	          	<li><a onclick="masive_right_move('${page}', '${total_page}');">우</a></li>
	  		</ul>
  	</div>
	</div>
</div>
</section>
</body>
</html>
