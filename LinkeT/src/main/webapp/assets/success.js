$(document).ready(function(){
	console.log("seperated js docready");

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
});