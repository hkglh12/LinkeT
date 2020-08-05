/*좌측 메인메뉴 애니메이션*/
$(".main-menu").on("mouseover",function(){
	$("section").css("width","80%");
	$("section").css("margin-left","18%"); 
});    
$(".main-menu").on("mouseout",function(){
	$("section").css("width","95%");
    $("section").css("margin-left","6vw"); 
});