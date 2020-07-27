<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
	<!--<link rel="stylesheet" href="profile.css">-->
    <!-- <script src="jquery-3.5.1.js"></script> -->
    <!--<script src="profile.js"></script>-->
    <link href="<c:url value="/assets/profile.css"/>" rel="stylesheet">
    <script src="<c:url value="/assets/jquery/jquery-3.5.1.js"/>"></script>
    <script src="<c:url value="/assets/profile.js"/>"></script>
    <script>
    	var usrTeam1 = "${usrTeam1}";
    	var usrTeam2 = "${usrTeam2}";
    	var usrTeam3 = "${usrTeam3}";
/*    	console.log("usrTeam1 : " + usrTeam1);
    	console.log("usrTeam2 : " + usrTeam2);
    	console.log("usrTeam3 : " + usrTeam3);*/
    	var usrTeam1code = "${usrTeam1code}";
    	var usrTeam2code = "${usrTeam2code}";
    	var usrTeam3code = "${usrTeam3code}";
</script>

</head>
<body>
<div class="area">
    
</div>
<nav class="main-menu">
    <ul>
        <li>
            <a href="http://localhost:80/LinkeT/usrmain">
                <i class="fa fa-home fa-2x"></i>
                <span class="nav-text">
                    Main
                </span>
            </a>

        </li>
        <li>
            <a href="http://localhost:80/LinkeT/me?target='me'">
                <i class="fa fa-home fa-2x"></i>
                <span class="nav-text">
                    My Information
                </span>
            </a>

        </li>
        <li class="has-subnav">
            <a href="#">
                <i class="fa fa-laptop fa-2x"></i>
                <span class="nav-text">
                    My Team
                    <!--아래에 팀 세개 추가해야함.-->
                </span>
            </a>
        </li>
        <li class="has-subnav">
            <a href="#">
               <i class="fa fa-list fa-2x"></i>
                <span class="nav-text">
                    문의하기
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
           <a href="#">
                 <i class="fa fa-power-off fa-2x"></i>
                <span class="nav-text">
                    Logout
                </span>
            </a>
        </li>  
    </ul>
</nav>
    
<section>
   <!--상단 Site navigator-->
<div class="upper">
        <div class="info"><h1 class="title">Your Information</h1></div>
    </div>
    <!--profile 내용부-->
    <div class="profilebg">
    <div class="profile">
    <div class="container">
      
      <div class="row cf">
        <div id="card1" class="card four col">
          <div class="image-wrapper"></div>
          <h3 class="name">${usrId}</h3>
          <div class="info cf">
            <div class="four col">PhoneNumber<span class="number">${usrPhone}</span></div>
            <div class="four col">Email<span class="number">${usrEmail}</span></div>
            <div class="four col">JoinDate<span class="number">${usrSignInDate}</span></div>
          </div>
          <!--<div class="options">
            <ul>
              <li><span class="icon"><i class="fa fa-plus" aria-hidden="true"></i></span>Add to team</li>
              <li><span class="icon"><i class="fa fa-envelope" aria-hidden="true"></i></span>Send a message</li>
            </ul>
          </div>-->
        </div>

        <div id="card2" class="card four col">
          <div class="header">
              <!--<h3 class="name">YourTeamInfo</h3>-->
          </div>
          <div class="wrapper">
            <!--<div class="image-wrapper"></div>-->
            <!--<h3 class="name">YourTeamInfo</h3>-->
            <!--<div class="social cf">
              <div class="four col">
                <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
              </div>
              <div class="four col">
                <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
              </div>
              <div class="four col">
                <a href="#"><i class="fa fa-linkedin" aria-hidden="true"></i></a>
              </div>
            </div>-->
            <div class="info teaminfo">
              <ul>
                  <li id="teamfirst"><label class="cd2 lb teamname"><a class="link" href="#"></a></label>
                  <p class="card2 teamcode" id="c1"></p></li>
                  <li id="teamsec"><label class="cd2 lb teamname"><a class="link" href="#"></a></label>
                  <p class="card2 teamcode" id="c2"></p></li>
                  <li id="teamthird"><label class="cd2 lb teamname"><a class="link" href="#"></a></label>
                  <p class="card2 teamcode" id="c3"></p></li>
              </ul>
            </div>
          </div>
        </div>
</div>
    <!--    <div id="card3" class="card four col">
          <div class="header"></div>
          <div class="wrapper">
            <h2 class="name">John Smith</h2>
          </div>
          <div class="info cf">
            <div class="four col">
              <div class="number">17</div>

            </div>
            <div class="four col">
              <div class="number">239</div>
            </div>
            <div class="four col">
              <div class="number">8</div>
            </div>
          </div>
        </div>-->
      </div>
    </div>
    </div>
    </section>
    </body>

</html>
