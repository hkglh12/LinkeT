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
    <link href="<c:url value="/assets/profile.css"/>" rel="stylesheet">
	<!--<link rel="stylesheet" href="profile.css">-->
    <!--<script src="jquery-3.5.1.js"></script>-->
    <script src="<c:url value="/assets/jquery/jquery-3.5.1.js"/>"></script>
</head>
<body>
<div class="area">
    
</div>
<nav class="main-menu">
    <ul>
        <li>
            <a href="http://localhost:80/LinkeT/me?target='me'">
                <i class="fa fa-home fa-2x"></i>
                <span class="nav-text">
                    Dashboard
                </span>
            </a>

        </li>
        <li class="has-subnav">
            <a href="#">
                <i class="fa fa-laptop fa-2x"></i>
                <span class="nav-text">
                    Stars Components
                </span>
            </a>

        </li>
        <li class="has-subnav">
            <a href="#">
               <i class="fa fa-list fa-2x"></i>
                <span class="nav-text">
                    Forms
                </span>
            </a>

        </li>
        <li class="has-subnav">
            <a href="#">
               <i class="fa fa-folder-open fa-2x"></i>
                <span class="nav-text">
                    Pages
                </span>
            </a>

        </li>
        <li>
            <a href="#">
                <i class="fa fa-bar-chart-o fa-2x"></i>
                <span class="nav-text">
                    Graphs and Statistics
                </span>
            </a>
        </li>
        <li>
            <a href="#">
                <i class="fa fa-font fa-2x"></i>
                <span class="nav-text">
                   Quotes
                </span>
            </a>
        </li>
        <li>
           <a href="#">
               <i class="fa fa-table fa-2x"></i>
                <span class="nav-text">
                    Tables
                </span>
            </a>
        </li>
        <li>
           <a href="#">
                <i class="fa fa-map-marker fa-2x"></i>
                <span class="nav-text">
                    Maps
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
<div class="upper">
<div class="info">YourInfos</div>

<div class="profile">

<div class="container">
  <h1 class="title">User Profile Cards</h1>
  <div class="row cf">
    <div id="card1" class="card four col">
      <div class="image-wrapper"></div>
      <h3 class="name">${usrId}</h3>
      <div class="info cf">
        <div class="four col"><span class="number">${usrPhone}</span>PhoneNumber</div>
        <div class="four col"><span class="number">${usrEmail}</span>Email</div>
        <div class="four col"><span class="number">Change</span>PW</div>
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
        <div class="social cf">
          <div class="four col">
            <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
          </div>
          <div class="four col">
            <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
          </div>
          <div class="four col">
            <a href="#"><i class="fa fa-linkedin" aria-hidden="true"></i></a>
          </div>
        </div>
        <div class="info teaminfo">
          <ul>
              <li id="teamfirst"><a class="link" href="localhost:80/LinkeT/r/team_createorjoin.html">testatata${usrTeam1}</a></li>
              <li id="teamsec"><a class="link" href="#">${usrTeam2}</a></li>
              <li id="teamthird"><a class="link" href="#">${usrTeam3}</a></li>
          </ul>
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


</div>
</section>
</body>
<script>
    $(document).ready(function(){
    	console.log($("#teamfirst .link"));
    	console.log($("#teamfirst"));
    	console.log($("#teamfirst .link").attr("href"));
    	console.log($("#teamfirst .link").text());
    	var usrTeam1 = "${usrTeam1}";
    	console.log(usrTeam1);
    	var usrTeam2 = "${usrTeam2}";
    	var usrTeam3 = "${usrTeam3}";
    if(usrTeam1 === ''){
        $("#teamfirst .link").attr("href","/LinkeT/r/team_createorjoin.html");
        $("#teamfirst .link").text("Create or Join new Team");
    }
    if(usrTeam2 === ''){
    	$("#teamsec .link").attr("href","/LinkeT/r/team_createorjoin.html");
        $("#teamsec .link").text("Create or Join new Team");
    }
    if(usrTeam3 === ''){
    	$("#teamthird .link").attr("href","/LinkeT/r/team_createorjoin.html");
        $("#teamthird .link").text("Create or Join new Team");
    }
    });
    console.log("after");
   	console.log($("#teamfirst .link"));
	console.log($("#teamfirst"));
	console.log($("#teamfirst .link").attr("href"));
	console.log($("#teamfirst .link").text());
</script>
</html>
