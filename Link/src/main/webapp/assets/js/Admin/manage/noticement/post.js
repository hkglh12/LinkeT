$(document).ready(function() {
	$('#noticesubmit').on("click",function(){
		var title = $("#n_title");
		var contents = $("#n_contents");
		if(title.val()==""){
			alert("제목을 입력해주세요");
		}else if(contents.val()==""){
			alert("공지 내용을 입력해주세요");
		}else{
			$("#noticeform").submit();
		}
			
	});
	$('#n_contents').summernote({
		lang : 'ko-KR',
		height:450,
		placeholder:"게시글 본문을 입력해주세요",
		focus:true,
		toolbar: [
	       	['style', ['bold', 'italic', 'underline']],
	       	['fontsize', ['fontsize']],
	       	['color', ['color']],
	       	['para', ['ul', 'ol', 'paragraph']]
	   	],
		fontSizes: ['8', '9', '10', '11', '12', '14', '18', '24', '36', '48' , '64', '82', '150']
	});
	$('#u_files').Multifile
		/*좌측 메인메뉴 애니메이션*/
   $(".main-menu").on("mouseover",function(){
      $(".upper .info").css("margin-left","190px"); 
   });
    $(".main-menu").on("mouseover",function(){
		$("section").css("width","80%");
      $("section").css("margin-left","18%"); 
   });
    
    $(".main-menu").on("mouseout",function(){
      $(".upper .info").css("margin-left","0px"); 
   });
     $(".main-menu").on("mouseout",function(){
			$("section").css("width","95%");
      $("section").css("marginLeft","6vw"); 
   });

});
