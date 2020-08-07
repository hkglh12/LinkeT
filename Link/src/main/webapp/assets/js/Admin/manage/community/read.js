$(document).ready(function() {
	$('.togleOn').on("click",function(){
		var root = $(this).parent().parent().parent().parent();
		root.find('.toglemodi').css("display", "block");
		root.find('.modi_contents').summernote("code",root.find('.comment_contents').text());
		root.find('.comment_contents').css("display","none");
	});	
	$('.togleOff').on("click",function(){
		$(this).parent().parent().css("display","none");
		$(this).parent().parent().parent().find('.comment_contents').css("display","block");
	});	
	$('#cc_contents').summernote({
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
	$('.modi_contents').summernote({
		lang : 'ko-KR',
		width:1000,
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
/*
    /*submit버튼관련*/
    $('#notice_submit').on("click", function(){
        var title = $("#title");  // val로 접근함
        var contents = $("#content");    
        if(title.val()=="" || contents.val()==""){
            alert("제목, 혹은 내용이 없습니다.");
        }
    });
	$("#del").on("click",function(){
		var result = confirm("정말 삭제하시겠어요?");
			if(result == true){ 
    		var f = document.createElement("form");
    		var inputl = document.createElement("input");
    		inputl.setAttribute("type", "hidden");
    		inputl.setAttribute("name", "c_serial");
    		inputl.setAttribute("value",$('#c_serial').val());
    		f.appendChild(inputl);
			var subj = $("<input>").attr("type", "hidden").attr("name", "subject");
			subj.attr("value", $("#subject").val());
			subj.appendTo(f);
    		f.action="/Link/admin/manage/community/ban";
    		f.method="post";
    		document.body.appendChild(f);
    		f.submit();
		}
	});
	/*댓글 삭제 버튼*/
	$('.comment_del').on("click", function(){
		var result = confirm("정말 삭제하시겠습니까?");
		if(result == true){

			$(this).parent().find('.delform').submit();
		}
	});
});
function gobacklist(){
	location.href="/Link/admin/manage/community/list?subject="+$("#subject").val();
}
function commentblockmove(page_num){
	var c_serial = $('#c_serial').val();
	var param = {
			"c_serial" : c_serial,
			"page_num" : page_num
			};
	$.ajax({
		type:"POST",
		async:true,
		headers:{
			'Accept': 'application/json',
            'Content-Type': 'application/json' 
		},
		url:"/Link/community/comment/list",
		dataType:"json",
		data:JSON.stringify(param),
		success:function(data){
			$('.registered.comment').html("");
			if(data.list.length==0){
				var non = $("<div>");
				non.attr('class', 'ctx_commnet_wrapper');
				non.html('<label> 아직 등록된 댓글이 없습니다.</label>');
				$('.registered.comment').append(non);
			}else{
				var regcomm = $('.registered.comment');
				var cur_user = $("#current_userid").val();
				var comm_user = $("community_userid").val();
				for(var i=0; i<data.list.length; i++){
					var ci = data.list[i];			
					var comm = $("<div>").attr('class', 'ctx_comment_wrapper').appendTo(regcomm);
					var commenter = $("<div>");
					commenter.attr('class', 'commenter');
					commenter.appendTo(comm);
					var image_div=$("<div>").attr('class','image-wrapper').appendTo(commenter);						
					var idlbl = $("<label>").text("유저아이디 : " + ci.usrId).attr('class','comm usrlbl').appendTo(commenter);
					var datelbl = $("<label>").text("게시날짜 : " + moment(ci.createDate).format('YYYY-MM-DD HH:mm:ss')).attr('class','comm usrlbl').appendTo(commenter);
					var toglecomm = $("<div>").attr('class', 'toglecomm').appendTo(commenter);
					var togleform = $("<form>").attr('action',"/Link/community/comment/ban").attr('method', 'post').attr('class','delform').appendTo(toglecomm);
					var togle_cserial = $('<input>').attr('type','hidden').attr('value',c_serial).attr('name','c_serial').appendTo(togleform);
					var togle_delserial = $('<input>').attr('type','hidden').attr('value',ci.serial).attr('name','del_serial').appendTo(togleform);
					var comm_delbtn = $("<button>").attr('type','button').attr('class','comment_del').text("댓글 강제삭제").appendTo(togleform);
					if(ci.checkSecret == true){
						var lblred = $("<label>").attr('class', 'col red').text("비밀게시글입니다.").appendTo(toglecomm);
					}
					var com_contents = $("<div>").attr('class','comment_contents').html(ci.contents).appendTo(comm);	
				}
			}
		},error:function(data){
			console.log(data);
		}
	});
}