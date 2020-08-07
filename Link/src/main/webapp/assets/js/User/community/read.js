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
	       	['style', ['style']],
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
	       	['style', ['style']],
	       	['fontsize', ['fontsize']],
	       	['color', ['color']],
	       	['para', ['ul', 'ol', 'paragraph']]
	   	],
		fontSizes: ['8', '9', '10', '11', '12', '14', '18', '24', '36', '48' , '64', '82', '150']
	});

    /*submit버튼관련*/
    $('#notice_submit').on("click", function(){
        var title = $("#title");  // val로 접근함
        var contents = $("#content");    
        if(title.val()=="" || contents.val()==""){
            alert("제목, 혹은 내용이 없습니다.");
        }
    });

	/* 본문 수정 버튼*/	
	$('#upd').on("click",function(){
		var community_serial = $('#c_serial').val();
		location.href="/Link/community/update?c_serial="+community_serial;
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
	    		f.action="/Link/community/delete";
	    		f.method="post";
	    		document.body.appendChild(f);
	    		f.submit();
			}
    	});
	/*댓글 삭제 버튼*/
	$('.comment_del').on("click", function(){
		var result = confirm("정말 삭제하시겠습니까?");
		if(result == true){
			console.log('true');
			console.log($(this).parent());
			$(this).parent().submit();
		}
	});
	$('.comm_submit').on("click", function(){
		var result = confirm("정말 수정할까요?");
		if(result == true){
			$(this).parent().submit();			
		}
	})
    	
});
function gobacklist(){
	location.href="/Link/community/list?subject="+$("#subject").val();
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
							/*var comm = $("<div>");
							comm.attr('class', 'ctx_comment_wrapper');*/
							// 댓글이 공개 || 현재 유저가 글작성자이거나 댓글작성자이면 출력
							if((ci.checkSecret != true) || cur_user == comm_user || cur_user == ci.usrId){ 
								var commenter = $("<div>");
								commenter.attr('class', 'commenter');
								commenter.appendTo(comm);
							
								var image_div=$("<div>").attr('class','image-wrapper').appendTo(commenter);						
								var idlbl = $("<label>").text("유저아이디 : " + ci.usrId).attr('class','comm usrlbl').appendTo(commenter);
								var datelbl = $("<label>").text("게시날짜 : " + moment(ci.createDate).format('YYYY-MM-DD HH:mm:ss')).attr('class','comm usrlbl').appendTo(commenter);
								// 댓글작성자 일때만 삭제, 수정버튼 제공
								if(cur_user == ci.usrId){
									var toglecomm = $("<div>").attr('class', 'toglecomm').appendTo(commenter);
									var togleform = $("<form>").attr('action',"/Link/community/comment/delete").attr('method', 'post').attr('class','delform').appendTo(toglecomm);
									var togle_cserial = $('<input>').attr('type','hidden').attr('value',c_serial).attr('name','c_serial').appendTo(togleform);
									var togle_delserial = $('<input>').attr('type','hidden').attr('value',ci.serial).attr('name','del_serial').appendTo(togleform);
									var comm_delbtn = $("<button>").attr('type','button').attr('class','comment_del').text("댓글 삭제").appendTo(togleform);
									var comm_modifybtn = $("<button>").attr('type','button').attr('class','togleOn').text("댓글 수정").appendTo(togleform);
									if(ci.checkSecret == true){
										var lblred = $("<label>").attr('class', 'col red').text("비밀게시글입니다.").appendTo(toglecomm);
									}
									var toglemodi = $("<div>").attr('class','toglemodi').appendTo(comm);
									var toglemodiform = $("<form>").attr('action', '/Link/community/comment/update').attr('method','post').appendTo(toglemodi);
									var modi_cserial = $("<input>").attr('type','hidden').attr('value',c_serial).attr('name','c_serial').appendTo(toglemodiform);
									var modi_ccserial = $("<input>").attr('type', 'hidden').attr('value',ci.serial).attr('name','cc_serial').appendTo(toglemodiform);
									var modi_contents = $("<textarea>").attr('class','modi_contents').appendTo(toglemodiform);
									var secretcheck = $("<input>").attr('type','checkbox').attr('name','is_secret').attr('value','true').appendTo(toglemodi);
									var submitbtn = $('<button>').attr('type','button').attr('class','comm_submit').appendTo(toglemodi)
									var modi_cancel = $('<button>').attr('type','button').attr('class','togleOff').appendTo(toglemodi);
								}
								var com_contents = $("<div>").attr('class','comment_contents').html(ci.contents).appendTo(comm);	
							}else{
								var secretcomm = $("<div>").attr('class','toglecomm').text("비밀댓글 : 댓글 작성자와 글 작성자만 볼 수 있습니다.").appendTo(comm);
							}
						}
					}
				},error:function(data){
					console.log(data);
				}
			})
		}
	    