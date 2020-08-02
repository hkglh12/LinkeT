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

	<link href="<c:url value="/a/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
		<!-- Custom styles for this template -->
	<link href="<c:url value="/a/css/User/main/scrolling-nav.css"/>" rel="stylesheet">
	
    <script src="<c:url value="/a/js/jquery-3.5.1.js"/>"></script>
      <!-- Custom JavaScript for this theme -->
  	<script src="<c:url value="/a/js/User/main/scrolling-nav.js"/>"></script>
    <script src="<c:url value="/a/js/User/main/main.js"/>"></script>
  	
  
			

  	<!-- Bootstrap core JavaScript -->
  	<script src="<c:url value="/a/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
  	<!-- Plugin JavaScript -->
  	<script src="<c:url value="/a/bootstrap/jquery-easing/jquery.easing.min.js"/>"></script>


  <script>
  	if("${result}" == "404"){
  		alert("아이디 혹은 비밀번호가 유효하지 않아요") 
  	}
  </script>
</head>

<body id="page-top">
<%-- <c:if test="${result eq 'failed'}">
	<c:out value="<script type='text/javascript'>alert('ID, 비밀번호를 확인해주세요!);</script>"></c:out>
</c:if> --%>
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
          <p class="lead"><strong>프로그래밍</strong>은 어렵죠.<br> 못찾는에러도 한두가지가 아니구요! <br>트러블슈팅이 힘들땐 Link에 물어보세요!</p>
          <ul>
            <li>Link는 Java, JSP, Spring을 사용하는 사람들의 커뮤니티입니다</li>
            <li>프로그래밍 지식을 공유할수도, 꽤 괜찮은 Plugin에 대해 공유할수도 있어요!</li>
            <li>프로그래밍중 막힌부분이 있다면 Link에 올려보세요! 이미 같은 에러를 경험한사람, <br>혹은 그 문제를 알고있는 사람들이 답을 올려주실거에요!</li>
            <li>이제 망설이지말고, 상단 메뉴를 눌러서 Link를 시작해보세요!</li>
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
          <h2>Link에서는 무슨일을 할수있나요?</h2>
          <!-- <p class="lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aut optio velit inventore, expedita quo laboriosam possimus ea consequatur vitae, doloribus consequuntur ex. Nemo assumenda laborum vel, labore ut velit dignissimos.</p> -->
          <ul>
            <li>Java, Jsp, Spring 지식을 공유할 수 있습니다.</li>
            <li>Plug-in 추천을 받을수도 있어요</li>
            <li>어려움을 느낀 부분을 질문하시면, 다른 사용자들이 답변드릴거에요</li>
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
          <p class="lead">우리 서비스에서 불편한점을 찾으셨다구요? 저희는 연락받는걸 좋아해요! <br> 어느정도로 좋아하냐면, 저희는 이런 수정건의 <strong>티켓</strong>이라고 부르고있어요. <br> 더 나은 내일로의 <strong>티겟</strong> 이요! 그러니 망설이지말고 저희에게 "티켓"을 보내주세요!</p>
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
