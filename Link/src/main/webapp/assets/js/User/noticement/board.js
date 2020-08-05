$(document).ready(function(){
	/*좌측 메인메뉴 애니메이션*/
    $(".main-menu").on("mouseover",function(){
		$("section").css("width","80%");
      	$("section").css("margin-left","18%"); 
   }); 
    $(".main-menu").on("mouseout",function(){
		$("section").css("width","95%");
      	$("section").css("margin-left","6vw"); 
   });
});

function blockmove(block){
	console.log("called" + block)
	location.href="/Link/notice/list?page="+block;
}
function pagecall(serial){
	console.log("called" + serial)
	location.href="/Link/notice/get?n_serial="+serial;
}