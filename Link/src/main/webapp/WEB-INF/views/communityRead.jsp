<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="server" value= "${pageContext.request.remoteAddr}"></c:set>
<c:set var="port" value="${pageContext.request.serverPort}"></c:set>
<!DOCTYPE html>
<html>
<head>
    <title>We Linke-t</title>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link href="<c:url value="/a/css/communityRead.css"/>" rel="stylesheet">
    <script src="<c:url value="/a/js/jquery-3.5.1.js"/>"></script>
    <script src="<c:url value="/a/js/communityRead.js"/>"></script>
<!--    <link rel="stylesheet" href="main.css">-->
    <!-- 동일폴더가 아니라 서버 상위 디렉토리로 올라갔다올꺼면 c:url 쓰라고 함 (JSTL)-->
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
<!--     <link rel="stylesheet" href="readnotice.css">
    <script src="jquery-3.5.1.js"></script>
    <script src="readnotice.css"></script> -->
    <script>   
    </script>
</head>
<body>
<div class="area">
    
</div>
<!-- <nav class="main-menu">
    <ul>
        <li>
            <a href="http://localhost:80/Link/">
                <i class="fa fa-home fa-2x"></i>
                <span class="nav-text">
                    Main
                </span>
            </a>

        </li>
        <li>
            <a href="http://localhost:80/Link/">
                <i class="fa fa-home fa-2x"></i>
                <span class="nav-text">
                    My Information
                </span>
            </a>

        </li>
        <li class="has-subnav">
            <a href="http://localhost:80/Link/notice/list">
                <i class="fa fa-laptop fa-2x"></i>
                <span class="nav-text">
                    Noticement
                    아래에 팀 세개 추가해야함.
                </span>
            </a>
        </li>
        <li class="has-subnav">
            <a href="http://localhost:80/Link/community/list">
               <i class="fa fa-list fa-2x"></i>
                <span class="nav-text">
                    Community
                </span>
            </a>

        </li>
       
        <li>
            <a href="#">
               <i class="fa fa-info fa-2x"></i>
                <span class="nav-text">
                    Documentation
                </span>
            </a>
        </li>
    </ul>

    <ul class="logout">
        <li>
           <a href="http://localhost:80/Link/usr/logout">
                 <i class="fa fa-power-off fa-2x"></i>
                <span class="nav-text">
                    Logout
                </span>
            </a>
        </li>  
    </ul>
</nav> -->
<%@ include file="root-view.jsp"%>
 <section>
 <!--내용부 헤더-->
  
  <!--상단부 로고-->
  <header id="atop">
    <div class="row">
     <!--최상단 헤더, 위치안내용으로 쓰임 large-4 column lpad-->
      <div class="main lpad header location">
        <div class="logo">
          <span>CurrentLocation : <c:out value="${server}"></c:out>:<c:out value="${port}"></c:out> </span>
          <span id="main_header_location_t_name"></span>
        </div>
      </div>
      <div class="main header submenu ar">
       <!--소형메뉴 기존 클래스 large-8 column ar lpad-->
        <nav class="menu">
          <a href="#" class="current" id="dashboard">Dashboard</a>
          <a href="#" id="teaminfo">Team information</a>
          <a href="#" id="noticement">Noticement</a>
          <a href="#" id="community">Community</a>
          <!--<a href="#">Calendar</a>-->
          <a href="#" id="shareboard">Share board</a>
          <!--<a href="#">FAQ</a>
          <a href="#">Contact</a>-->
        </nav>     
      </div>
    </div>
  </header>
  <!--내용부 헤더 종료-->
  <!--사용자 계정 맞이-->
  <a href="atop" id="top-button">
    <i class="icon-angle-up"></i>
  </a>
  <div class="row">

    <div class="main lpad greets ar">
      Welcome,
      <a href="#" class="underline"><%-- ${request.getParameter("usrId")} --%></a>
    </div>
  </div> 

  
  <div class="mainwrapper">
  <div id="notiwrapper">
      <div class="forum-category rounded top">
        <div id="noticetitle">
          ${community.title}
        </div>
        <div class="mpad ar">
          <button id="upd"> 수정하기 </button>
          <button id="del">삭제하기</button>
        </div>
      </div>
        <div id="noticeinfos">
            <ul>
                <li>게시글번호 : ${community.serial}</li>
                <li>작성자 : ${community.usrId}</li>
                <li>작성일 : ${community.createDate}</li>
                <li>조회수 : ${community.readCount}</li>
            </ul>
        </div>
        <div id="noticecontent">
          <div id="content">${community.contents}
          </div>
        </div>
        
    <div id="uploadfiles">
     <%-- <c:if test="${fn:length(list) eq 0}"> --%>
     <!-- 올림의 쉬운 이해 -->
     <%-- <fmt:parSeNumber var="pageCount" value="${count/pageSize + (count%pageSize==0 ? 0 : 1)}" intergerOnly="true"/> --%>
     <!-- https://okky.kr/article/187379  -->
     <c:if test="${empty community.uFileList}">
     <label>등록된 파일이 없습니다!</label>
     </c:if>
     <c:if test="${not empty community.uFileList}">
     <c:forEach items="${community.uFileList}" var="i">
     	<li><a href="http://localhost:80/Link/community/download?fileCode=${i.uFileCode}">${i.uFileOriginName}</a></li>
     	<%-- <label class="uFileCode">${i.uFileCode}</label> --%>
     </c:forEach>
     </c:if>
      </div>
        
<!--      </div>-->
  </div>
  </div>
  
	<c:set var="commentlength" value="${total_comment}"></c:set>
    <div class="comment part">
    	<div class="upload commet">
    		<form action="/Link/community/comment/post" method="post">
    		<input name="c_serial" type="hidden" value="${community.serial}">
    		<textarea name="cc_contents">
    		
    		</textarea>
    		<input type="checkbox" name="is_secret" value="true"> 비밀로하기
    		<button type="submit"> 보내버리기</button>
    		</form>
    	</div>
    	<div class="registered comment">
    	    <c:if test="${empty community.comments}">
    	    <div class="ctx_comment_wrapper">
     		<label>아직 등록된 댓글이 없습니다</label>
     		</div>
     		</c:if>
     		
    		<c:if test="${not empty community.comments}">
     		<c:forEach items="${community.comments}" var="i">
     		<!-- form은 pageContext 접근 가능 -->
     		<div class="ctx_comment_wrapper">
     		<label>${i.usrId}</label>
     		<mark>${i.contents}</mark>
     		<div class="toglecomm">
	     		<form action="/Link/community/comment/delete" method="post">
	     			<input type="hidden" value="${community.serial}" name="c_serial">
	     			<input type="hidden" value="${i.serial}" name="del_serial">
	     			<button type="submit">삭제하기</button>
	     		</form>
     		<button type="button" class="togleOn">수정</button>
     		</div>
     		
     		<div class="toglemodi">
     			<form action="/Link/community/comment/update" method="post">
     				<input type="hidden" value="${community.serial}" name="c_serial">
	     			<input type="hidden" value="${i.serial}" name="cc_serial">
	     			<input type="checkbox" name="is_secret" value="true"> 비밀로하기
	     			<button type="submit" class="comm_submit">수정해서보내버리기</button>
	     			<button type="button" class="togleOff">취소하기</button>
	     			<textarea name="modi_contents">
	     			</textarea>
     			</form>
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
	          <li><a href="javascript:commentblockmove('${i}')">${i}</a></li>
          </c:forEach>
          </c:if>
    	</ul>
    </div>
    </section>
    </body>
    <script>
	    function commentblockmove(page_num){
	    	console.log(page_num);
			var param = {
					"c_serial" : "${community.serial}",
					"page_num" : page_num
					};
			console.log(param);
			$.ajax({
				type:"POST",
				async:true,
				headers:{
					'Accept': 'application/json',
                    'Content-Type': 'application/json' 
				},
				url:"/Link/community/comment/list",
				dataType:"json",
				data:JSON.stringify(param),
				success:function(data){
					console.log(data);
					$('.registered.comment').html("");

					

					if(data.list.length==0){
						var non = $("<div>");
						non.attr('class', 'ctx_commnet_wrapper');
						non.html('<label> 아직 등록된 댓글이 없습니다.</label>');
						$('.registered.comment').append(non);
					}
					
					var regcomm = $('.registered.comment');
					for(var i=0; i<data.list.length; i++){
						var ci = data.list[i];
						console.log("ci : " + ci.serial);
						
						var comm = $("<div>");
						comm.attr('class', 'ctx_comment_wrapper');
						
						var idlbl = $("<label>");
						idlbl.text("작성자 : " + ci.usrId);
						idlbl.appendTo(comm);
						
						var contmark = $("<mark>");
						contmark.text(ci.contents);
						contmark.appendTo(comm);
						
						var tgcomm = $("<div>");
						tgcomm.attr("class", "toglecomm");
						tgcomm.appendTo(comm);
						
							var tgcommdelform = $("<form>");
							tgcommdelform.attr("action", "/Link/community/comment/delete");
							tgcommdelform.attr("method", "POST");
							tgcommdelform.appendTo(tgcomm);
							
								var ctyserialin = $("<input>");
								ctyserialin.attr("type", "hidden");
								ctyserialin.attr("value", ci.communitySerial);
								ctyserialin.attr("name", "c_serial");
								ctyserialin.appendTo(tgcommdelform);
								
								var commin = $("<input>");
								commin.attr("type","hidden");
								commin.attr("value",ci.serial);
								commin.attr("name","del_serial");
								commin.appendTo(tgcommdelform);
								
								var sbmitbtn = $("<button>");
								sbmitbtn.attr("type","submit");
								sbmitbtn.text("삭제하기");
								sbmitbtn.appendTo(tgcommdelform);
							
						var modibtn = $("<button>");
						modibtn.attr("type", "button");
						modibtn.attr("class", "togleOn");
						modibtn.text("수정하기");
						modibtn.appendTo(comm);
						
						var tgmodi = $("<div>");
						tgmodi.attr("class", "toglemodi");
						tgmodi.appendTo(comm);
						
							var tgcommmodiform = $("<form>");
							tgcommmodiform.attr("action", "/Link/community/comment/update");
							tgcommmodiform.attr("method", "POST");
							tgcommmodiform.appendTo(tgmodi);
								
								var cmtyserialin = $("<input>");
								cmtyserialin.attr("type", "hidden");
								cmtyserialin.attr("value", ci.communitySerial);
								cmtyserialin.attr("name", "c_serial");
								cmtyserialin.appendTo(tgcommmodiform);
								
								var commserial = $("<input>");
								commserial.attr("type", "hidden");
								commserial.attr("value", ci.serial);
								commserial.attr("name", "cc_serial");
								commserial.appendTo(tgcommmodiform);
								
								var issecretin = $("<input>");
								issecretin.attr("type", "checkbox");
								issecretin.attr("name", "is_secret");
								issecretin.attr("value", "true");
								issecretin.appendTo(tgcommmodiform);
								
								var textara = $("<textarea>");
								textara.attr("name", "modi_contents");
								textara.appendTo(tgcommmodiform);
								
								var sbbtn = $("<button>");
								sbbtn.attr("type", "submit");
								sbbtn.attr("class", "comm_submit");
								sbbtn.text("수정해서 보내버리기");
								sbbtn.appendTo(tgcommmodiform);
								
								
								var cancelbtn = $("<button>");
								cancelbtn.attr("type", "button");
								cancelbtn.attr("class", "togleOff");
								cancelbtn.attr("text", "취소하기");
								cancelbtn.appendTo(tgcommmodiform);
	
							comm.appendTo(regcomm);
					}
				},error:function(data){
					console.log(data);
				}
			})
		}
	    
		$('.togleOn').on("click",function(){
			 if($(this).parent().parent().children('.toglemodi').css("display")=="none"){
				console.log("true");
				$(this).parent().css("display","none");
				$(this).parent().parent().children('.toglemodi').css("display","block");
			}; 
		});
	
		$('.togleOff').on("click",function(){

			console.log($(this).parent().parent().parent().children('.toglecomm').css("display"));
			
			if($(this).parent().parent().parent().children('.toglecomm').css("display")=="none"){ 
				$(this).parent().parent().css("display","none");
				$(this).parent().parent().parent().children('.toglecomm').css("display","block");
			}
	});
    	$('#upd').on("click",function(){
    		location.href="/Link/community/update?c_serial="+${community.serial};
    	});
    	
    	$("#del").on("click",function(){
    		var f = document.createElement("form");
    		var inputl = document.createElement("input");
    		inputl.setAttribute("type", "hidden");
    		inputl.setAttribute("name", "c_serial");
    		inputl.setAttribute("value","${community.serial}");
    		
    		f.appendChild(inputl);
    		f.action="/Link/community/delete";
    		f.method="post";
    		document.body.appendChild(f);
    		f.submit();
    	});
    	
    </script>
</html>     
 