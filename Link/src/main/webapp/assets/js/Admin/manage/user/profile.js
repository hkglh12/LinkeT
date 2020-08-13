$(document).ready(function(){
	var pwstd = /[\w\!\@\#]{12,16}/;
    /* 좌측 메뉴에 마우스 올릴경우 나머지가 반응하게하기*/
   $(".main-menu").on("mouseover",function(){
      $(".upper .info").css("margin-left","190px"); 
   });
    $(".main-menu").on("mouseover",function(){
      $(".profile").css("margin-left","25%"); 
   });
    
    $(".main-menu").on("mouseout",function(){
      $(".upper .info").css("margin-left","0px"); 
   });
     $(".main-menu").on("mouseout",function(){
      $(".profile").css("margin","0 auto"); 
   });

});

function bann(user_id){
	var result = confirm("정말 강퇴시킬까요?");
	if (result == true){
		var frm = $("<form>").appendTo($("body"));
		$("<input>").attr("type","hidden").attr("name","target_list").attr("value", user_id).appendTo(frm);
		$("<input>").attr("type","hidden").attr("name","user_id").attr("value",usr_id).appendTo(frm);
		$("<input>").attr("type","hidden").attr("name","main_category").attr("value",$("#main_category").val()).appendTo(frm);
		$("<input>").attr("type","hidden").attr("name","sub_category").attr("value",$("#sub_category").val()).appendTo(frm);
		$("<input>").attr("type","hidden").attr("name","search_target").attr("value",$("#search_target").val()).appendTo(frm);
		$("<input>").attr("type","hidden").attr("name","page").attr("value",$("#currpage").val()).appendTo(frm);
		frm.submit();
	}
}
function goback(){
	var main_category = $("#main_category").val() == "" ? "" : $("#main_category").val();
	var sub_category = $("#sub_category").val() == "" ? "" : $("#sub_category").val();
	var search_target = $("#search_target").val() == "" ? "" : $("#search_target").val();
	var page = $("#currpage").val() == "" ? "" : $("#currpage").val();    
	location.href="/Link/admin/manage/user/list?main_category="+main_category+"&sub_category="+sub_category+"&search_target="+search_target+"&page="+page;
}

function direct_search(){
	var search_category = "id";
	var search_target = $("#user_id").val();
	var subject = "direct";
	location.href="/Link/admin/manage/community/directlist?search_category="+search_category+"&search_target="+search_target+"&subject="+subject
}

function direct_comment_search(target){
	location.href="/Link/admin/manage/comment/direct?u_id="+target+"&page=1";
}