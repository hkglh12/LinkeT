$(document).ready(function(){
});

// 사용자 리스트 페이지 이동
function blockmove(block){
	var main_category = $("#main_category").val() == null ? "all" : $("#main_category").val();
	var sub_category = $("#sub_category").val();
	var search_target = $("#search_target").val();
	location.href="/Link/admin/manage/user/list?page="+block+"&main_category="+main_category+"&sub_category="+sub_category+"&search_target="+search_target;
}

// 리스트 보드 타입을 변경 (전체사용자, 정상사용자, 탈퇴사용자, 관리자)
function changeboard(){
	var value = $("#maincategorylist option:selected").val();
	location.href="/Link/admin/manage/user/list?main_category="+value;
}
// 특정 유저의 상세정보를 리콜
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
// 유저 리스트 검색조건을 변경하는 순간 해당 form 내의 hidden type을 동기화 (form action으로 동작함)
function sync_sub_category(){
	$("#sub_category").val($("#subcategorylist option:selected").val());	
}
// 동시삭제, 체크한것을 모두 들고와야하고 form을 하나로 처리하기위해서 jquery selector로 수집
// >> 개선 : ban_user, 삭제 후 redirect에 필요한 데이터를 함께 전송
/*function bulk_del(){
	var bulk_delform = $("<form>").attr("action","/Link/admin/manage/community/bulkban").attr("method","post");
	$("<input>").attr('name', 'subject').attr('type','hidden').attr('value',$("#subject").val()).appendTo(bulk_delform);
	var lists=$(".bulk_delete_check:checkbox:checked");
	$(".bulk_delete_check:checkbox:checked").each(function(){
		$("<input>").attr('name','bulkdel').attr('value',$(this).val()).attr('type','hidden').appendTo(bulk_delform);
	});
	bulk_delform.appendTo($('body')).submit();
}*/
// 개별 유저 삭제 >> 개선 동시삭제 및 개별삭제 허용
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