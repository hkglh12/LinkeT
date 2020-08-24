$(document).ready(function(){
	var namestd = /^[가-힣]{2,4}$/;
    var idcheck = false;
	var idstd = /[\w]{6,12}/gi;
    var phonecheck=false;
	var phonestd = /^\d{3}\d{3,4}\d{4}$/;
	
    var pw_cfck = false;
	var pwstd = /[\w\!\@\#]{12,16}/;
	var pwstd_ck = false;
	
    var emailcheck = false;
    var emailstd = /([\w]+)\@([\w]+)\.([\w]+)/gi;
    //계정명 유효성 검사
    
    $("#phonecheck").on("click", function(){
        if($("#u_phone").val()==""){
            alert("휴대전화번호를 먼저 입력하세요");
        }else{
			var tgr = $("#u_phone").val();
			if(!(phonestd.test(tgr))){
				alert("휴대전화번호 형식이 아닙니다. 확인해주세요");
			}else{
            var param = {"u_phone" : tgr};	
            $.ajax({
                type:"POST",
                async:true,
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                url:"/Link/usr/validate",
                dataType:"json",
            
            	data:JSON.stringify(param),
                success:function(data){
					console.log(data);
                    if(data.result == "accept"){
                        phonecheck = true;
                        alert("사용하실 수 있는 휴대전화번호입니다.")
                    }else{
                        alert("이미 중복된 휴대전화번호가 사용중입니다");
                    }
                },error:function(data){
                    console.log(data);
                }
            })
        }
		}
    });
    
    $("#idcheck").on("click", function(){
        if($("#u_id").val()==""){
            alert("아이디를 입력해주세요");
        }else{
			var tgr = $("#u_id").val();
			var stdtest = false;
			stdtest = idstd.test(tgr);
			if(stdtest == false){
				console.log(tgr);
				console.log(stdtest);
				alert("아이디 입력조건에 부합하지 않습니다.");
			}else{
				console.log(tgr);
				console.log(stdtest);
            var param = {"u_id" : tgr};
            $.ajax({
                type:"POST",
                async:true,
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                url:"/Link/usr/validate",
                dataType:"json",
            	data:JSON.stringify(param),
                success:function(data){
                    if(data.result == "accept"){
                        idcheck = true;
                        alert("사용하실 수 있는 ID입니다.")
     
                    }else{
                        alert("이미 중복된 ID가 사용중입니다");
                    }
                },error:function(data){ }
            })
        }
	}
    });
    
    $("#emailcheck").on("click", function(){
        if($("#u_email").val()==""){
            alert("이메일을 입력해주세요");
        }else{
			
			var tgr = $("#u_email").val();
            if(!(emailstd.test(tgr))){
                alert("이메일 형식이 맞지 않습니다.")
            }else{
                var param = {"u_email" : tgr};
				var parameter = JSON.stringify(param);
                $.ajax({
                    type:"POST",
                    async:true,
                    headers: { 
                        'Accept': 'application/json',
                        'Content-Type': 'application/json' 
                    },
                    url:"/Link/usr/validate",
                    dataType:"json",
                    data : JSON.stringify(param),
                    success:function(data){
                        if(data.result == "accept"){
                            emailcheck = true;
                            alert("사용하실 수 있는 EMAIL입니다.")
           
                        }else{
                            alert("이미 중복된 EMAIL이 사용중입니다");
                        }
                    },error:function(data){
                    }
                })
            }
        }
    });
    
    $("#sign_submit").on("click",function(){
		
        if($("#u_pwraw").val() == $("#pwc").val()){pw_cfck = true;}
		if(pwstd.test( $('#u_pwraw').val() )){
			pwstd_ck=true;
		}
		if($('#u_name').val()==""){
			alert("이름을 입력해주세요");
		}else if(!(namestd.test($('#u_name').val()))){
			alert("입력하신 이름을 다시 확인해주세요");
		}else{
			
        // 전송 or 반려 결정
        if(idcheck == false || emailcheck==false || pw_cfck == false || phonecheck == false || pwstd_ck==false){      
			if(idcheck==false){
                alert("ID 중복검사를 실행해주세요");
            }else if(emailcheck==false){
                alert("Email 중복검사를 실행해주세요");
            }else if(pw_cfck==false){
                alert("비밀번호와 비밀번호확인이 일치하지 않습니다");
            }else if(pwstd_ck==false){
				alert("비밀번호 조건을 충족하지 않았습니다.");
			}else if(phonecheck == false){
				alert("휴대전화번호 유효성 검사를 실시해주세요");
			}
        }else{
			var enc = $("<input>");
			enc.attr("id", "u_pw");
			enc.attr("name", "u_pw");
			enc.attr("type", "hidden");
			enc.val(SHA256($('#u_pwraw').val()));
			enc.appendTo($("#signup_form"));
            $("#signup_form").submit();
        }
}
    });
                  
    
});