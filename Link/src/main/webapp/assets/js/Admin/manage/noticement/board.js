/*리스트 블록이동*/
function blockmove(block){
	console.log("called" + block)
	location.href="/Link/admin/manage/notice/list?page="+block;
}
/*특정 공지사항 호출*/
function pagecall(serial){
	console.log("called" + serial)
	location.href="/Link/admin/manage/notice/get?n_serial="+serial;
}

/*관리자권한 :: 공지사항 글쓰기*/
$("#notice_post").on("click",function(){
	/*location.href="http://localhost:80/Link/notice/form/"*/
	location.href="/Link/admin/manage/notice/form";
	
});
	
