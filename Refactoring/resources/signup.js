$(document).ready(function(){
    var idcheck = false;
    var pw_cfck = false;
    var emailcheck = false;
    var invitationck = false;
    var emailstd = /([\w]+)\@([\w]+)\.([\w]+)/gi;
    //계정명 유효성 검사
    
    
    /*test*/
    $("#emailcheck").on("click", function(){
        if($("#u_email").val()==""){
            alert("이메일을 입력해주세요");
        }else{
			var tgr = $("#u_email").val();
			console.log(tgr);
			console.log(emailstd);
            if(emailstd.test(tgr)){
                console.log(emailstd.test(tgr));
				console.log(tgr);
                var param = {"u_email" : tgr};
				console.log(param);
            }else{
				
               alert("이메일 형식이 맞지 않습니다.")
                console.log(tgr);
				console.log(emailstd.test(tgr));
            }
        }
    });
    
    /*
    $("#idcheck").on("click", function(){
        if($("u_id").val=""){
            alert("아이디를 입력해주세요");
        }else{
            var param = {"u_id" : $("u_id").val};
            $.ajax({
                type:"POST",
                async:true,
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                url:"http://localhost:80/LinkeT/validate",
                dataType:"json",
                data : JSON.stringfy(param),
                success:function(data){
                    if(data.result == "accept"){
                        idcheck = true;
                        alert("사용하실 수 있는 ID입니다.")
                    }else{
                        alert("이미 중복된 ID가 사용중입니다");
                    }
                },error:function(data){
                    console.log(data);
                }
            })
        }
    });
    
    $("#email_check").on("click", function(){
        if($("u_email").val=""){
            alert("이메일을 입력해주세요");
        }else{
            if(emailstd.test($("u_email").val)){
                alert("이메일 형식이 맞지 않습니다.")
                console.log(emailstd.test($("u_email").val));
            }else{
                var param = {"u_email" : $("u_email").val};
                $.ajax({
                    type:"POST",
                    async:true,
                    headers: { 
                        'Accept': 'application/json',
                        'Content-Type': 'application/json' 
                    },
                    url:"http://localhost:80/LinkeT/validate",
                    dataType:"json",
                    data : JSON.stringfy(param),
                    success:function(data){
                        if(data.result == "accept"){
                            emailcheck = true;
                            alert("사용하실 수 있는 EMAIL입니다.")
                        }else{
                            alert("이미 중복된 EMAIL이 사용중입니다");
                        }
                    },error:function(data){
                        console.log(data);
                    }
                })
            }
        }
    });
    
    //InvitatinoCode 유효성 검사
    $("#invicheck").on("click", function(){
        if($("#invcode").val=""){
            alert("Team 초대 코드를 입력해주세요");
        }else{
            var param = {"targetTeamCode" : $("#invcode").val};
            $.ajax({
                type:"POST",
                async:true,
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                url:"http://localhost:80/LinkeT/getTeam",
                dataType:"json",
                data : JSON.stringfy(param),
                success:function(data){
                    var result = data.TeamName;
                    if(result != "" || result != undeifined || result != null){
                        alert("유효한 Team Code입니다!\r\n Team Name : " + result)
                    }else{
                        alert("유효하지 않은 team code입니다. 다시한번 확인해주세요");
                        $("invcode").val="";
                    }
                },error:function(data){
                    console.log(data);
                }
            });
        }
        
    });
    
    $(".sign_submit").on("click",function(){
        //비밀번호 유효성 검사
        if($("#pw").val == $("#pwc").val){
            pw_cfck = true;
        }
        
        // 전송 or 반려 결정
        if(idcheck == false || emailcheck==false || pw_cfck == false || invitationck == false){
            if(idcheck==false){
                alert("ID 중복검사를 실행해주세요");
            }else if(emailcheck==false){
                alert("Email 중복검사를 실행해주세요");
            }else if(pw_cfck==false){
                alert("비밀번호와 비밀번호확인이 일치하지 않습니다");
            }
        }else{
            $(".signup_form").submit();
        }
    });
       */           
    
});