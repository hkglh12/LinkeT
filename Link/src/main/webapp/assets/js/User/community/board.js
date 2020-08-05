$(document).ready(function(){
$("#community_post").on("click",function(){
    	location.href="http://localhost:80/Link/community/form";
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