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
	$('#u_files').Multifile;
});
