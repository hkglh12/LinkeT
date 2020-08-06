$(document).ready(function(){
	$("#submitbtn").on("click",function(){
		var result = confirm("정말 반영할까요?");
		if(result == true){
			$("#updform").submit();
		}
	});
	/*좌측 메인메뉴 애니메이션*/
	$(".main-menu").on("mouseover",function(){
		$("section").css("width","80%");
		$("section").css("margin-left","18%"); 
	});    
	$(".main-menu").on("mouseout",function(){
		$("section").css("width","95%");
	    $("section").css("margin-left","6vw"); 
	});
	/*하단 페이지블록 링크*/
	$('#c_contents').summernote({
		lang :'ko-KR',
		height:450,
		focus:true,
		fontSize:20,
		toolbar: [
	       	['style', ['style']],
	       	['fontsize', ['fontsize']],
	       	['color', ['color']],
	       	['para', ['ul', 'ol', 'paragraph']]
	   	],
		fontSizes: ['8', '9', '10', '11', '12', '14', '18', '24', '36', '48' , '64', '82', '150']
	});
});

function delthiscode(qa){
	var inputl = document.createElement("input");
	inputl.setAttribute("type", "hidden");
	inputl.setAttribute("name", "del_target");
	inputl.setAttribute("value", $(qa).parent().find('.tgf_code').val());
	$("#updform").append(inputl);
	$(qa).parent().css("display", "none");
}
