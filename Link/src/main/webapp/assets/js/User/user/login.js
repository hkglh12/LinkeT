
$(document).ready(function(){
/*	if(result=="failed"){
		alert("ID 혹은 PW를 확인해주세요!");	
	}else if(result=="sout"){
		alert("Session이 만료되었습니다. 다시로그인해주세요!");
	}
	*/
	$('#loginbtn').on("click",function(){
		if( !($('#u_id').val()=="")&& !($('#u_pwraw').val()=="")){
			var enc = $("<input>");
			enc.attr("id", "u_pw");
			enc.attr("name", "u_pw");
			enc.attr("type", "hidden");
			enc.val(SHA256($('#u_pwraw').val()));
			enc.appendTo($("#loginform"));
			$("#loginform").submit();
		}else{
			alert("ID, 비밀번호를 입력해주세요");
		}
	});
	
	$("#signup").on("click",function(){
		location.href="/Link/signup";
	})
	
	
	
});

/*
	.jsp 파일 내에 <script> << js
	<script> jqueyr.js </script> < jquery
	
	.js
	var ~ = document.getElemt~
	var ~ = int
	
	.jquery
	var ~ = $(".selector").~
	var ~ = int
	
	.jquery에서 jsp의 변수 접근하는건
	.js파일에서 jquery문법을 사용하여 jsp에 접근
	var ~ = "${variable_name}";
	
	
	.js에서 .jsp로 접근(access get)
	.js에서 .jsp로 값을(Send) 보내는거 X 이건 아는데. << 이러려면 server통신(ajax)

*/