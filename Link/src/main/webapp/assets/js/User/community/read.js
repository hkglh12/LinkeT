$(document).ready(function() {
	$('.togleOn').on("click",function(){
		var root = $(this).parent().parent().parent().parent();
			if(root.find('.toglemodi').css("display")=="none"){
				root.find('.toglemodi').css("display", "block");
				console.log(root.find('.comment_contents').text());
				root.find('.modi_contents').summernote("code",root.find('.comment_contents').text());
				root.find('.comment_contents').css("display","none");
			}
		});
		
	$('.togleOff').on("click",function(){
		if($(this).parent().parent().parent().children('.toglecomm').css("display")=="none"){ 
			$(this).parent().parent().css("display","none");
			$(this).parent().parent().parent().children('.toglecomm').css("display","block");
			}	
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
	})
    	
});

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
					console.log(data);
					$('.registered.comment').html("");

					

					if(data.list.length==0){
						var non = $("<div>");
						non.attr('class', 'ctx_commnet_wrapper');
						non.html('<label> 아직 등록된 댓글이 없습니다.</label>');
						$('.registered.comment').append(non);
					}
					
					var regcomm = $('.registered.comment');
					for(var i=0; i<data.list.length; i++){
						var ci = data.list[i];
						console.log("ci : " + ci.serial);
						
						var comm = $("<div>");
						comm.attr('class', 'ctx_comment_wrapper');
						
						var idlbl = $("<label>");
						idlbl.text("작성자 : " + ci.usrId);
						idlbl.appendTo(comm);
						
						var contmark = $("<mark>");
						contmark.text(ci.contents);
						contmark.appendTo(comm);
						
						var tgcomm = $("<div>");
						tgcomm.attr("class", "toglecomm");
						tgcomm.appendTo(comm);
						
							var tgcommdelform = $("<form>");
							tgcommdelform.attr("action", "/Link/community/comment/delete");
							tgcommdelform.attr("method", "POST");
							tgcommdelform.appendTo(tgcomm);
							
								var ctyserialin = $("<input>");
								ctyserialin.attr("type", "hidden");
								ctyserialin.attr("value", ci.communitySerial);
								ctyserialin.attr("name", "c_serial");
								ctyserialin.appendTo(tgcommdelform);
								
								var commin = $("<input>");
								commin.attr("type","hidden");
								commin.attr("value",ci.serial);
								commin.attr("name","del_serial");
								commin.appendTo(tgcommdelform);
								
								var sbmitbtn = $("<button>");
								sbmitbtn.attr("type","submit");
								sbmitbtn.text("삭제하기");
								sbmitbtn.appendTo(tgcommdelform);
							
						var modibtn = $("<button>");
						modibtn.attr("type", "button");
						modibtn.attr("class", "togleOn");
						modibtn.text("수정하기");
						modibtn.appendTo(comm);
						
						var tgmodi = $("<div>");
						tgmodi.attr("class", "toglemodi");
						tgmodi.appendTo(comm);
						
							var tgcommmodiform = $("<form>");
							tgcommmodiform.attr("action", "/Link/community/comment/update");
							tgcommmodiform.attr("method", "POST");
							tgcommmodiform.appendTo(tgmodi);
								
								var cmtyserialin = $("<input>");
								cmtyserialin.attr("type", "hidden");
								cmtyserialin.attr("value", ci.communitySerial);
								cmtyserialin.attr("name", "c_serial");
								cmtyserialin.appendTo(tgcommmodiform);
								
								var commserial = $("<input>");
								commserial.attr("type", "hidden");
								commserial.attr("value", ci.serial);
								commserial.attr("name", "cc_serial");
								commserial.appendTo(tgcommmodiform);
								
								var issecretin = $("<input>");
								issecretin.attr("type", "checkbox");
								issecretin.attr("name", "is_secret");
								issecretin.attr("value", "true");
								issecretin.appendTo(tgcommmodiform);
								
								var textara = $("<textarea>");
								textara.attr("name", "modi_contents");
								textara.appendTo(tgcommmodiform);
								
								var sbbtn = $("<button>");
								sbbtn.attr("type", "submit");
								sbbtn.attr("class", "comm_submit");
								sbbtn.text("수정해서 보내버리기");
								sbbtn.appendTo(tgcommmodiform);
								
								
								var cancelbtn = $("<button>");
								cancelbtn.attr("type", "button");
								cancelbtn.attr("class", "togleOff");
								cancelbtn.attr("text", "취소하기");
								cancelbtn.appendTo(tgcommmodiform);
	
							comm.appendTo(regcomm);
					}
				},error:function(data){
					console.log(data);
				}
			})
		}
	    