$(document).ready(function() {
	/*좌측 메인메뉴 애니메이션*/
	/*$(".main-menu").on("mouseover",function(){
		$("section").css("width","80%");
		$("section").css("margin-left","18%"); 
	});    
	$(".main-menu").on("mouseout",function(){
		$("section").css("width","95%");
	    $("section").css("margin-left","6vw"); 
	});*/
	$('#c_contents').summernote({
		lang : 'ko-KR',
		height:450,
		placeholder:"게시글 본문을 입력해주세요",
		focus:true,
		toolbar: [
	       	['style', ['style']],
	       	['fontsize', ['fontsize']],
	       	['color', ['color']],
	       	['para', ['ul', 'ol', 'paragraph']]
	   	],
		fontSizes: ['8', '9', '10', '11', '12', '14', '18', '24', '36', '48' , '64', '82', '150']
	});

});
function community_submit(){
	var title = $("#c_title");
	var contents = $("#c_contents");
	
	if(title.val()==""){
		alert("제목을 입력해주세요");
	}else if(contents.val()==""){
		alert("본문을 입력해주세요");
	}else{
		$("#communityform").submit();
	}
}
