$(document).ready(function(){
	var pwstd = /[\w\!\@\#]{12,16}/;
 	/*좌측 메인메뉴 애니메이션*/
    $(".main-menu").on("mouseover",function(){
		$("section").css("width","80%");
      	$("section").css("margin-left","18%"); 
   }); 
    $(".main-menu").on("mouseout",function(){
		$("section").css("width","95%");
      	$("section").css("margin-left","6vw"); 
   });
    /*비밀번호변경 버튼*/
    $('#pwcgbtn').on("click",function(){
        $("#passwordchange").css("display","block");
        $('body').css("overflow","hidden");

    });
	/*비밀번호 변경 취소버튼*/
   $('#pwchangecancel').on("click",function(){
        $("#passwordchange").css("display","none"); 
        $('body').css("overflow","visible");
		$('#old_pw').val("");
		$('#new_pw').val("");
		$('#new_pw_cnfm').val("");
   });
	
	//비밀번호 변경하기
    $('#pwchsbm').on("click", function(){
        var bool = confirm("비밀번호를 변경하시겠습니까?");
        if(bool == true){
	 		var old_pw = $('#old_pw');
			var new_pw = $('#new_pw');
			var new_pw_cnfm = $('#new_pw_cnfm');
			//빈칸점검
            if(old_pw.val() != "" && new_pw.val()!="" && new_pw_cnfm.val()!=""){
				//비밀번호 규칙점검
                if(pwstd.test(new_pw.val()) == true){
				// 비밀번호 , 비밀번호 확인 점검
                if(new_pw.val() == new_pw_cnfm.val()){
                    var old_pw_enc = (SHA256(old_pw.val()));
                    var new_pw_enc = (SHA256(new_pw.val()));
                    var param = {"old_pw": old_pw_enc ,"new_pw": new_pw_enc};
                    $.ajax({
                        type:"POST",
                        async:true,
                        headers: { 
                            'Accept': 'application/json',
                            'Content-Type': 'application/json' 
                        },
                        url:"/Link/usr/update",
                        dataType:"json",
                        data:JSON.stringify(param),
                        success:function(data){
                          if(data.result =="404"){
							alert("기존 비밀번호가 일치하지 않아요");
							}else if(data.result=="200"){
								alert("변경에 성공하였습니다!");
							}else{
								alert("오류로 인해 성공하지 못했어요.\r\n 관리자에게 문의해주세요");
							}
							$("#passwordchange").css("display","none"); 
        					$('body').css("overflow","visible");
							old_pw.val("");
							new_pw.val("");
							new_pw_cnfm.val("");
                        },error:function(data){
                         	alert("오류가 발생하였습니다.\r\n 관리자에게 문의해주세요");
							$("#passwordchange").css("display","none"); 
        					$('body').css("overflow","visible");
							old_pw.val("");
							new_pw.val("");
							new_pw_cnfm("");
                        }
                    })
                }else{
                    alert('비밀번호와 비밀번호 확인이 일치하지 않아요');
                }
            }else{
                alert('비밀번호 규칙을 만족하지 않았어요');
            }
            }else{
                alert("입력창을 모두 채우지 않으셨어요");
            }
        }
    });
	/*탈퇴하기 버튼*/
	$('#signoutbtn').on("click",function(){
		$('#signoutstart').css("display","block");
		$('body').css("overflow","hidden");
	});
	/*탈퇴하기 취소버튼*/
	$('#signoutcancel').on("click",function(){
		$('#signoutstart').css('display',"none");
		$('body').css("overflow","visible");
		$('#u_pw').val("");
		$('#signout_cnfm').val("");
	});
	
	$('#signoutsbm').on('click',function(){
		var pwin = $('#u_pw');
		var cnfm = $('#signout_cnfm');
		if(cnfm.val()=="" || cnfm.val() != "지금탈퇴"){
			alert("지금탈퇴 정확히 입력해주세요");
		}else{
			var u_pw = (SHA256(pwin.val())); 
			var param = {"u_pw" : u_pw};
			
			 $.ajax({
                type:"POST",
                async:true,
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                url:"/Link/usr/signout",
                dataType:"json",
                data:JSON.stringify(param),
                success:function(data){
					console.log(data.result);
                 	if(data.result =="404"){
						alert("비밀번호가 일치하지 않아요.");
						pwin.val("");
						cnfm.val("");
						$("#signoutstart").css("display","none"); 
        				$('body').css("overflow","visible");
					}else if(data.result=="200"){
						alert("탈퇴에 성공했습니다.\r\n그동안 사용해주셔서 감사합니다.");
						location.href="/Link/";
					}else{
						alert("오류가 발생하였습니다.\r\n 관리자에게 문의해주세요");
						pwin.val("");
						cnfm.val("");
						$("#signoutstart").css("display","none"); 
        				$('body').css("overflow","visible");
					}
                },error:function(data){
               		alert("오류가 발생하였습니다.\r\n 관리자에게 문의해주세요");
                }
			});
		}
	});
});

function go_my_community(target){
	location.href="/Link/community/list?search_category=id&search_target="+target+"&subject=direct"
}
function go_my_comments(target){
	location.href="/Link/comment/direct?u_id="+target+"&page=1";
}
