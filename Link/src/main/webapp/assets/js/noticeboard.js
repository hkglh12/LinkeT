$(document).ready(function(){


	/*좌측 메인메뉴 애니메이션*/
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
	/*관리자권한 :: 공지사항 글쓰기*/
	$("#notice_post").on("click",function(){
		location.href="http://localhost:80/Link/h/postnotice.html"
		
	})
	
	/*하단 페이지블록 링크*/
	
});