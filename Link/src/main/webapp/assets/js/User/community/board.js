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
	
	$("#community_post").on("click",function(){
		var sbj = $("#subject").val();
	    location.href="http://localhost:80/Link/community/form?subject="+sbj;
	});
});

function blockmove(block){
	console.log("called");
	var stc=$('#search_category_hd').val();
	console.log(stc)
	var stg=$('#search_target_hd').val();
	console.log(stg)
	if(stc != null && stg != null){
	location.href="/Link/community/list?page="+block+"&search_category="+stc+"&search_target="+stg+"&subject="+$('#subject').val();
	}
}
function pagecall(serial){
	location.href="/Link/community/get?c_serial="+serial;
}