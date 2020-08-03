$(document).ready(function() {
	$('#n_contents').summernote({
		lang : 'ko-KR',
		height:450,
		placeholder:"공지사항 내용을 입력해주세요.",
		focus:true
	});
	$('#noticesubmit').on("click",function(){
		if($('#n_title').val() != ""){
			if($('n_contents').val()!=""){
				$('#updform').submit();
			}else{
				alert('공지 내용을 입력해주세요');
			}
		}else{
			alert("공지 제목을 입력해주세요");
		}
	})
});
function delconfirm(qa){
	var result = confirm("정말 지우시겠어요? 되돌릴 수 없어요");
	if(result==true){
		delthiscode(qa);
	}
}
function delthiscode(qa){
    	console.log(qa);
    	console.log($(qa).parent().find('input').val());
    	var inputl = document.createElement("input");
		inputl.setAttribute("type", "hidden");
		inputl.setAttribute("name", "del_targets");
		inputl.setAttribute("value", $(qa).parent().find('.tgf_code').val());
    	$("#updform").append(inputl);
    	$(qa).parent().css("display", "none");
    }
