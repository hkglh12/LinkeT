<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Link-Profile</title>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
  
    <link href="<c:url value="/a/css/Admin/manage/user/profile.css"/>" rel="stylesheet">
    <script src="<c:url value="/a/js/jquery-3.5.1.js"/>"></script>
    <script src="<c:url value="/a/js/Admin/manage/userprofile.js"/>"></script>
    <script src="<c:url value="/a/js/encryptor.js"/>"></script>

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
<div class="area">
    
</div>
<jsp:include page="../../root-view.jsp"/>

<section>
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
          <h3 class="name">${user.usrId}</h3>
          <div class="info cf">
            <div class="four col">PhoneNumber<span class="number">${user.usrPhone}</span></div>
            <div class="four col">Email<span class="number">${user.usrEmail}</span></div>
            <div class="four col">JoinDate<span class="number">${user.usrInDate}</span></div>
              <div class="four col">
              <button type="button" id="bann">강퇴시키기</button>
			
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
                      <p class="card2 infoexp" id="c1">최근 작성한 게시글 </p><button class="godetail">전체보기&gt;</button></li>
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
                      <p class="card2 infoexp" id="c1">최근 작성한 댓글</p> <button class="godetail">전체보기&gt;</button></li>
                  
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
