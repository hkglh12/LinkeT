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
	var stdd = $("#start_date").val();
	var endd = $("#end_date").val();
	var search_category = $("#search_category").val();
	var search_target = $("#search_target").val();
	
	location.href="/Link/admin/manage/log/list?page="+block+"&start_date"+stdd+"&end_date"+endd+"&search_category="+search_category+"&search_target="+search_target;
}
function pagecall(serial){
	location.href="/Link/admin/manage/community/get?c_serial="+serial;
}
function changeboard(){
	var value = $("#boardlist option:selected").val();
	location.href="/Link/admin/manage/community/list?page=1&subject="+value;
}
