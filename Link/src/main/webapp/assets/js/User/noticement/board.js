function blockmove(block){
	console.log("called" + block)
	location.href="/Link/notice/list?page="+block;
}
function pagecall(serial){
	console.log("called" + serial)
	location.href="/Link/notice/get?n_serial="+serial;
}
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
	/*관리자권한 :: 공지사항 글쓰기*/
	$("#notice_post").on("click",function(){
		/*location.href="http://localhost:80/Link/notice/form/"*/
		location.href="/Link/admin/manage/notice/form";
		
	})
	
	/*하단 페이지블록 링크*/
	
});