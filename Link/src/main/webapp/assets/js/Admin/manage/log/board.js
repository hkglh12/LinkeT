$(document).ready(function(){

});

function masive_left_move(page){
	var target = (Number(page)/10)-1;
	if(target <0){
		target = 1;
		blockmove(Math.floor(1));
	}else{
		blockmove(Math.floor(target*10));
	}
}
function masive_right_move(now, total_page){
	var target = (Number(now)/10) + 1;
	if(target > (total_page/10)){
		target = Number(total_page)/10;
	}
	blockmove(Math.floor(target*10));
}
function blockmove(block){
	var stdd = $("#start_date").val();
	var endd = $("#end_date").val();
	var search_category = $("#search_category").val();
	var search_target = $("#search_target").val();
	
	location.href="/Link/admin/manage/log/list?page="+block+"&start_date="+stdd+"&end_date="+endd+"&search_category="+search_category+"&search_target="+search_target;
}
function pagecall(serial){
	location.href="/Link/admin/manage/community/get?c_serial="+serial;
}
function changeboard(){
	var value = $("#boardlist option:selected").val();
	location.href="/Link/admin/manage/community/list?page=1&subject="+value;
}
