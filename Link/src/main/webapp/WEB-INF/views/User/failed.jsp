<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>We Linke-T!</title>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link href="<c:url value="/assets/failed.css"/>" rel="stylesheet"/>
    <script src="<c:url value="/assets/jquery/jquery-3.5.1.js"/>"></script>
    <script src="<c:url value="/assets/failed.js"/>"></script>
    <script>
    /*  JSP는 Server-side Lan. JS는 Client-side.
    동작순서는 JSP > JSTL > HTML > JS. 따라서 JS에서는 JSP 변수에
    접근이  가능하지만, JSP는 JS의 변수정보를 알 수 없음.
    전달하려면 AJAX통신 or form에 숨겨놔야함.
    또한 동일 파일내에서 var 선언으로 JS 객체 선언을 해두고 ,JSP변수를
    저장해두어야만 접근이 가능
    REFER: https://coderanch.com/t/287106/java/jsp-variables-javascript
    내가 이해한 방향은 JSP가 Compile되면서 JS 선언 공간을 인식,
    이후 JSP가 선언이되면서 그 JS 선언공간에 값 할당.
    만약 선언하지 않는다면 JSP의 결과물로 그저 "HTML" 파일이 완성되어
    "객체처리화"가 되지 아니함을 의미.*/
		var ct = "${contents}";
		var val = "${value}";
		console.log(ct);
		console.log(val);
	    /* $(document).ready(function(){
	    	console.log("docready");
	    	
	    	var ct = "${contents}";
	    	var val = "${value}";
	    	console.log(ct);
	    	console.log(val);
	    	if(ct == "join"){
	    		if(val == "true"){
	    			$("#btn_go").html("가입성공!");
	    			$("#btn_go").on("click",(function(){
	    				location.href="http://localhost:80/LinkeT/";
	    			}));
	    		}else{
	    			$("#btn_go").text("가입실패!");
	    			$("#btn_go").on("click",(function(){
	    				location.href="http://localhost:80/LinkeT/";
	    			}));
	    		}
	    	}
	    }); */
    </script>
<!--     <script>
console.log("docready");
	
	var ct = "${contents}";
	var val = "${value}";
	
	if(ct == "join"){
		if(val == "true"){
			$("#btn_go").text("가입성공!");
			$("#btn_go").on("click",(function(){
				location.href="/";
			}));
		}else{
			$("#btn_go").text("가입실패!");
			$("#btn_go").on("click",(function(){
				location.href="/";
			}
		}
	}
    
    </script> -->
<!--    <link rel="stylesheet" href="success.css">  -->
</head>
 <body>
   <div class="wrapper">
    <div class="upper info">
       <h2>Failed</h2>
       <button type="button" id="btn_go">ad</button>
    </div>
    <div class="container clearfix">
      <div class="pa mackbook hover">
        <div class="screen">
          <div class="user_pic"></div>
          <div class="password"></div>
          <div class="icons clearfix">
            <div class="icon"></div>
            <div class="icon"></div>
            <div class="icon"></div>
          </div>
        </div>
        <div class="base pr">
          <div class="connector"></div>
          <div class="keypad">
            <div class="clearfix">
              <div class="ftl key key2"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key2"></div>
            </div>
            <div class="clearfix pad-lr-10">
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
            </div>
            <div class="clearfix">
              <div class="ftl key key2"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key2"></div>
            </div>
            <div class="clearfix pad-lr-10">
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key3"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
              <div class="ftl key key1"></div>
            </div>
          </div>
          <div class="touchpad"></div>
          <div class="pa shadow"></div>
        </div>
      </div>

      <div class="pa mouse hover">
        <div class="pa scroller"></div>
      </div>

      <div class="pa phone hover">
        <div class="speaker"></div>
        <div class="screen">
          <div class="screen_data"></div>
        </div>
        <div class="button"></div>
        <div class="pa volume_rockers"></div>
      </div>

      <div class="pa notes hover">
        <div class="note pr"></div>
      </div>

      <div class="pa pen hover">
        <div class="pen-nip">
          <div class="pen-tip"></div>
        </div>
        <div class="pa pen-bottom"></div>
      </div>

      <div class="pa handwatch hover">
        <div class="pr">
          <div class="belt"></div>
          <div class="pa dial">
            <div class="pa sun-hand"></div>
            <div class="pa hand1"></div>
            <div class="pa hand2"></div>
            <div class="pa button1"></div>
            <div class="pa button2"></div>
          </div>
        </div>
      </div>

      <div class="pa passbook hover">
        <div class="pr symbol">
          <div class="pa h_line"></div>
          <div class="pa v_line"></div>
          <div class="pa inner_circle"></div>
        </div>
        <div class="details1"></div>
        <div class="details2"></div>
        <div class="details3"></div>
      </div>

      <div class="pa diary hover">
        <div class="main">
          <div class="cover">
            <div class="pa less"></div>
          </div>
        </div>
      </div>

      <div class="pa pencil hover">
        <div class="pa pencil-bottom"></div>
        <div class="pencil-nip">
          <div class="pencil-tip"></div>
        </div>
      </div>

      <div class="pa lock hover">
        <div class="handle"></div>
        <div class="pr locker">
          <div class="pa key_hole"></div>
        </div>
      </div>

      <div class="pa mug hover">
        <div class="pr mug_head">
          <div class="pa coffee"></div>
          <div class="pa ear"></div>
        </div>
      </div>
    </div>
    </div>
  </body>
</html>
