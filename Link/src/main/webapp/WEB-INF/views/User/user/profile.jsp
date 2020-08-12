<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Link-Profile</title>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
  
  	<link href="${pageContext.request.contextPath}/a/css/User/user/profile.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/a/css/Commons/board_structure.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/a/js/jquery-3.5.1.js"></script>
    <script src="${pageContext.request.contextPath}/a/js/User/user/profile.js"></script>
    <script src="${pageContext.request.contextPath}/a/js/encryptor.js"></script>

</head>
<body>
<div id="passwordchange">
   <div id="pwchangeinputarea">
       <table>
           <tr>
               <td class="te">기존 비밀번호</td>
           </tr>
           <tr>
               <td class="pwchin"><input type="password" name="old_pw" id="old_pw" placeholder="기존에 사용하던 비밀번호를 입력해주세요"></td>
           </tr>
           <tr>
               <td class="te">새로운 비밀번호</td>
           </tr>
           <tr>
               <td class="pwchin"><input type="password" name="new_pw" id="new_pw" placeholder="!,@,#를 1개이상 포함한 영문, 숫자 12~16자리"></td>
           </tr>
           <tr>
               <td class="te">새로운 비밀번호 확인</td>
           </tr>
           <tr>
               <td class="pwchin"><input type="password" id="new_pw_cnfm" placeholder="새로운 비밀번호를 한번더 입력해주세요"></td>
           </tr>
           <tr>
              <td>
               <div class="pwchbtnarea">       <button type="button" id="pwchsbm">변경하기</button>
                   <button type="button" id="pwchangecancel">취소하기</button></div>
               </td>
           </tr>
       </table>

    </div>
</div>
<div id="signoutstart">
   <div id="signoutarea">
       <table>
           <tr>
               <td class="te">비밀번호</td>
           </tr>
           <tr>
               <td class="pwchin"><input type="password" name="u_pw" id="u_pw" placeholder="기존에 사용하던 비밀번호를 입력해주세요"></td>
           </tr>
           <tr>
               <td class="te">탈퇴확인</td>
           </tr>
           
           <tr>
               <td class="pwchin"><input type="text" id="signout_cnfm" placeholder="지금탈퇴 라고 입력해주세요"></td>
           </tr>
           <tr>
              <td>
               <div class="pwchbtnarea"><button type="button" id="signoutsbm">탈퇴하기</button>
                   <button type="button" id="signoutcancel">취소하기</button></div>
               </td>
           </tr>
       </table>

    </div>
</div>
<jsp:include page="../../root-view.jsp"/>

<section>
   <!--상단 Site navigator-->
	<header id="atop">
    	<div class="row">
      		<div class="main lpad header location">
        		<div class="logo">
          			<span>현재위치 : </span>
          			<span id="main_header_location_t_name">사용자 개인정보</span>
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
    <!--profile 내용부-->
    <div class="profilebg">
    <div class="profile">
<!--     <div class="container"> -->
      
      <div class="row cf">
        <div id="card1" class="card four col">
          <div class="image-wrapper"></div>
          
          <div class="info cf">
            <div class="four col">사용자 아이디 <span class="number">${user.usrId}</span></div>
            <div class="four col">PhoneNumber<span class="number">${user.usrPhone}</span></div>
            <div class="four col">Email<span class="number">${user.usrEmail}</span></div>
            <div class="four col">JoinDate<span class="number">${user.usrInDate}</span></div>
              <div class="four col">
              <button type="button" id="pwcgbtn">비밀번호 변경</button>
              <button type="button" id="signoutbtn">탈퇴하기</button>			
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
                      <p class="card2 infoexp" id="c1">작성한 글의 갯수</p><button type="button" class="golistbtn">전체보기 ></button></li>
                      <hr>
                      <li class="cont"><label class="cd2 lb info"><strong>${user.communityCount}</strong> 개 </label>
                  </ul>
                    <ul>
                      <li class="attrs">
                      <p class="card2 infoexp" id="c1">작성한 댓글의 갯수</p><button type="button" class="golistbtn">전체보기 ></button></li>
                      <hr>
                      <li class="cont"><label class="cd2 lb info"><strong>${user.commentCount} </strong>개</label>
                  </ul>
                    <ul>
                      <li class="attrs">
                      <p class="card2 infoexp" id="c1">업로드한 파일 갯수</p></li>
                      <hr>
                      <li class="cont"><label class="cd2 lb info"><strong>${user.fileCount}</strong> 개</label>
                  </ul>
              </div>
          </div>
        </div>
</div>

      </div>
    </div>
<!--     </div> -->
    </section>
    
    </body>

</html>
