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
	location.href="/Link/admin/manage/community/list?page="+block+"&search_category="+stc+"&search_target="+stg+"&subject="+$('#subject').val();
	}
}
function pagecall(serial){
	location.href="/Link/admin/manage/community/get?c_serial="+serial;
}
function changeboard(){
	var value = $("#boardlist option:selected").val();
	location.href="/Link/admin/manage/community/list?page=1&subject="+value;
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