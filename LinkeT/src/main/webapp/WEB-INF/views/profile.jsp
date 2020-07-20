<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Untitled Document</title>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link href="<c:url value="/assets/profile.css"/>" rel="stylesheet">
</head>
<body>
<div class="area">
    
</div>
<nav class="main-menu">
    <ul>
        <li>
            <a href="http://localhost:8080/LinkeT/me?target='me'">
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
${usrId};
${usrPw};
${usrPhone};
${usrEmail};
${usrTeam1};
${usrTeam2};
${usrTeam3};
<div class="container">
  <h1 class="title">User Profile Cards</h1>
  <div class="row cf">
    <div id="card1" class="card four col">
      <div class="image-wrapper"></div>
      <h3 class="name">Amy Smith</h3>
      <div class="info cf">
        <div class="four col"><span class="number">100</span>Posts</div>
        <div class="four col"><span class="number">28</span>Tasks</div>
        <div class="four col"><span class="number">179</span>Likes</div>
      </div>
      <div class="options">
        <ul>
          <li><span class="icon"><i class="fa fa-plus" aria-hidden="true"></i></span>Add to team</li>
          <li><span class="icon"><i class="fa fa-envelope" aria-hidden="true"></i></span>Send a message</li>
        </ul>
      </div>
    </div>

    <div id="card2" class="card four col">
      <div class="header"></div>
      <div class="wrapper">
        <div class="image-wrapper"></div>
        <h3 class="name">Jack Smith</h3>
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
        <div class="info">
          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptatem aspernatur, mollitia suscipit perspiciatis minima reprehenderit quasi consectetur.</p>
        </div>
      </div>
    </div>

    <div id="card3" class="card four col">
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
    </div>
  </div>
</div>
</div>
</div>
</section>
</body>

</html>
