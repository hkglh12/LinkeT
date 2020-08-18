$(document).ready(function(){
	/*좌측 메인메뉴 애니메이션*/
	//게시글 작성하기
	$("#community_post").on("click",function(){
		location.href="/Link/community/form?subject="+$("#subject").val();
	});

});
// 페이지 이동
function blockmove(block){
	var sbj= $('#subject').val();
	if (sbj!="direct"){
		var stc=$('.search_category_hd').val() == undefined ? "" : $('.search_category_hd').val();
		var stg=$('.search_target_hd').val() == undefined ? "" : $('.search_target_hd').val();	
		location.href="/Link/community/list?page="+block+"&search_category="+stc+"&search_target="+stg+"&subject="+sbj;
	}else{
		var stclength = $(".search_category_hd").length;
		baselink = "/Link/community/directlist?page="+block+"&subject="+sbj;
		for(var i=0; i<stclength; i++){
			baselink += "&search_category=" + $(".search_category_hd").eq(i).val();
			baselink += "&search_target="+ $(".search_target_hd").eq(i).val();  
		}
		location.href=baselink;
	}
}
// 게시글 호출
function pagecall(serial){
	location.href="/Link/community/get?c_serial="+serial;
}
