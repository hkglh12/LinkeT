$(document).ready(function(){
	// 로그인 버튼 Onclick 부여
	// 암호화해야하므로 직접 form action으로 전송 불가
	$('#loginbtn').on("click",function(){
		if(!($('#u_id').val()=="")&& !($('#u_pwraw').val()=="")){
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
});
