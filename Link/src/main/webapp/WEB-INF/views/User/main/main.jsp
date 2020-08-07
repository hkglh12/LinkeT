<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Link</title>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<script src="${pageContext.request.contextPath}/a/js/jquery-3.5.1.js"></script>
	<%-- <script src="${pageContext.request.contextPath}/js/User/main/scrolling-nav.js"></script> --%>
	<script src="${pageContext.request.contextPath}/a/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/a/bootstrap/jquery-easing/jquery.easing.min.js"></script>
	<%-- <script src="${pageContext.request.contextPath}/js/User/main/main.js"></script> --%>
	<link href="${pageContext.request.contextPath}/a/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/a/css/User/main/scrolling-nav.css" rel="stylesheet">
	
</head>

<body id="page-top">
  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <div class="container">
      <a class="navbar-brand js-scroll-trigger" href="#page-top">Link? We linked!</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
        	<c:if test="${sessionScope.usrId eq null }">
        	<li class="nav-item">
            	<a class="nav-link js-scroll-trigger" href="/Link/usr/login/form">Login</a>
          	</li>
          	</c:if>
          	<c:if test="${sessionScope.usrId ne null }">
        	<li class="nav-item">
            	<a class="nav-link js-scroll-trigger" href="/Link/usr/logout">Logout</a>
          	</li>
          	</c:if>
          	<c:if test="${sessionScope.usrId ne null }">
          	<li class="nav-item">
            	<a class="nav-link js-scroll-trigger" href="/Link/usr/me">MyPage</a>
          	</li>
          	</c:if>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="/Link/notice/list">공지사항</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="/Link/community/list?subject=java">Java</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="/Link/community/list?subject=jsp">JSP</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="/Link/community/list?subject=spring">Spring</a>
          </li>
          
        </ul>
      </div>
    </div>
  </nav>

  <header class="bg-primary text-white">
    <div class="container text-center">
      <c:if test="${sessionScope.usrId eq null }">
      <h1>Welcome to "Link"</h1>
      <p class="lead">We Love Programmers</p>
      </c:if>
      <c:if test="${sessionScope.usrId ne null }">
      <h1>안녕하세요, <strong> &lt; ${sessionScope.usrId} &gt; !</strong></h1>
      <p class="lead">오늘 하루도 즐겁게 코딩해볼까요?</p>
      </c:if>
    </div>
  </header>

  <section id="about">
    <div class="container">
      <div class="row">
        <div class="col-lg-8 mx-auto">
          <h2>About Link</h2>
          <p class="lead"><h3>Together we can "Program"</h3>
          <br>트러블슈팅이 힘들땐 Link에 물어보세요!</p>
          <ul>
            <li>Link는 Java, JSP, Spring을 사용하는 사람들의 커뮤니티입니다</li>
            <li>프로그래밍 지식을 공유할수도, 꽤 괜찮은 Plugin에 대해 공유할수도 있어요!</li>
            <li>프로그래밍중 막힌부분이 있다면 Link에 올려보세요!</li>
            <li>망설이지말고, 상단 메뉴를 눌러서 Link를 시작해보세요!</li>
     <!--        <li>Minimal custom CSS so you are free to explore your own unique design options</li> -->
          </ul>
        </div>
      </div>
    </div>
  </section>

  <section id="services" class="bg-light">
    <div class="container">
      <div class="row">
        <div class="col-lg-8 mx-auto">
          <h2>What can we do on Link?</h2>
          <h3>"Link" 서비스에서는 이런 일을 할 수 있어요.</h3>
          <!-- <p class="lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aut optio velit inventore, expedita quo laboriosam possimus ea consequatur vitae, doloribus consequuntur ex. Nemo assumenda laborum vel, labore ut velit dignissimos.</p> -->
          <ul>
            <li>Java, Jsp, Spring 지식을 공유할 수 있습니다.</li>
            <li>Plug-in 추천을 받을수도 있어요</li>
            <li>기술 동향에 대해 의논할 수 있어요</li>
            <li>전공 기초지식에 대해 이야기 할 수도 있어요</li>
            <li>어려움을 느낀 부분을 질문하시면, 다른 사용자들이 답변드릴거에요</li>
            <li>"Link" 서비스는 사용자가 만들어가는 커뮤니티입니다. 자유로워요</li>
          </ul>
        </div>
      </div>
    </div>
  </section>

  <section id="contact">
    <div class="container">
      <div class="row">
        <div class="col-lg-8 mx-auto">
          <h2>Contact us</h2>
          <h3>Hey, check this out!</h3>
          <p class="lead">저희는 불편 문의사항을 받는 것을 좋아합니다.<br> 더 나은 내일로의 <b>티켓</b> 이라고 부르고있어요 <br> 불편한점을 겪으셨다면 저희 관리자에게 <b>티켓</b> 을 보내주세요!</p>
          <p class="lead"><br> <strong> hkglh12@gmail.com </strong></p>
        </div>
      </div>
    </div>
  </section>

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Link</p>
    </div>
    <!-- /.container -->
  </footer>

<!-- bootstrap 출처 : https://startbootstrap.com/previews/scrolling-nav/ -->


</body>

</html>
