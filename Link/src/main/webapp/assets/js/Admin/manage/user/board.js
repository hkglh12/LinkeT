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
	var main_category = $("#main_category").val() == null ? "all" : $("#main_category").val();
	var sub_category = $("#sub_category").val();
	var search_target = $("#search_target").val();
	/*if(search_target == null || search_target == ""){
		location.href="/Link/admin/manage/user/list?page="+block+"&main_category="+main_category;
	}else{*/
	location.href="/Link/admin/manage/user/list?page="+block+"&main_category="+main_category+"&sub_category="+sub_category+"&search_target="+search_target;
	/*}*/
}
function pagecall(serial){
	location.href="/Link/admin/manage/community/get?c_serial="+serial;
}
function changeboard(){
	var value = $("#maincategorylist option:selected").val();
	location.href="/Link/admin/manage/user/list?main_category="+value;
}
function userdetail(usr_id){
	console.log(usr_id);
	var frm = $("<form>").attr("action","/Link/admin/manage/user/detail").attr("method","GET").appendTo($("body"));
	$("<input>").attr("type","hidden").attr("name","user_id").attr("value",usr_id).appendTo(frm);
	$("<input>").attr("type","hidden").attr("name","main_category").attr("value",$("#main_category").val()).appendTo(frm);
	$("<input>").attr("type","hidden").attr("name","sub_category").attr("value",$("#sub_category").val()).appendTo(frm);
	$("<input>").attr("type","hidden").attr("name","search_target").attr("value",$("#search_target").val()).appendTo(frm);
	$("<input>").attr("type","hidden").attr("name","page").attr("value",$("#currpage").val()).appendTo(frm);
	frm.submit();
}
function sync_sub_category(){
	$("#sub_category").val($("#subcategorylist option:selected").val());	
}
function bulk_del(){
	var bulk_delform = $("<form>").attr("action","/Link/admin/manage/community/bulkban").attr("method","post");
	$("<input>").attr('name', 'subject').attr('type','hidden').attr('value',$("#subject").val()).appendTo(bulk_delform);
	var lists=$(".bulk_delete_check:checkbox:checked");
	$(".bulk_delete_check:checkbox:checked").each(function(){
		$("<input>").attr('name','bulkdel').attr('value',$(this).val()).attr('type','hidden').appendTo(bulk_delform);
	});
	bulk_delform.appendTo($('body')).submit();
}
function ban_user(){
	var target_list = new Array();
	if($("input[class=target_id]:checked").length>=1){
		var delform = $("<form>").attr('action','/Link/admin/manage/user/ban').attr("method","post").appendTo($("body"));
		$("<input>").attr("type","hidden").attr("name","curr_page").attr("value",$('#currpage').val()).appendTo(delform);
		$("<input>").attr("type","hidden").attr("name","category").attr("value",$('#category').val()).appendTo(delform);
		$("input[class=target_id]:checked").each(function(){
		$("<input>").attr('type','hidden').attr('name','target_list').attr('value',this.value).appendTo(delform);
		});
		delform.submit();
	}else{
		alert("삭제할 대상을 선택해주세요");
	}
	
}