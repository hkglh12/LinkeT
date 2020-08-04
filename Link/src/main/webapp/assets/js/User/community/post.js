$(document).ready(function() {
		$('#c_contents').summernote({
		lang : 'ko-KR',
		height:300,
		placeholder:"댓글 내용을 입력해주세요.",
		focus:true,
		toolbar: [
	       	['style', ['bold', 'italic', 'underline']],
	       	['fontsize', ['fontsize']],
	       	['color', ['color']],
	       	['para', ['ul', 'ol', 'paragraph']]
	   	],
		fontSizes: ['8', '9', '10', '11', '12', '14', '18', '24', '36', '48' , '64', '82', '150']
	});
  });
