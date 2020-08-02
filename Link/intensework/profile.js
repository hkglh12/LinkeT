$(document).ready(function(){
    var pw_cfck = false;
	var pwstd = /[\w\!\@\#]{12,16}/;
    /* 좌측 메뉴에 마우스 올릴경우 반응하게하기*/
   $(".main-menu").on("mouseover",function(){
      $(".upper .info").css("margin-left","190px"); 
   });
    $(".main-menu").on("mouseover",function(){
      $(".profile").css("margin-left","25%"); 
   });
    
    $(".main-menu").on("mouseout",function(){
      $(".upper .info").css("margin-left","0px"); 
   });
     $(".main-menu").on("mouseout",function(){
      $(".profile").css("margin","0 auto"); 
   });
    /*비밀번호변경 버튼*/
    $('#pwcgbtn').on("click",function(){
        $("#passwordchange").css("display","block");
        $('body').css("overflow","hidden");
       /* $(".main-menu").css("visibility","hidden");*/
    });
   $('#pwchangecancel').on("click",function(){
        $("#passwordchange").css("display","none"); 
        $('body').css("overflow","visible");
       /* $(".main-menu").css("visibility", "visible");*/
   });
    $('#pwchsbm').on("click", function(){
        console.log($('#new_pw').val());
        console.log($('#old_pw').val());
        var bool = confirm("비밀번호를 변경하시겠습니까?");
        if(bool == true){
            if($('old_pw').val() != "" && $('#new_pw').val()!="" && $('#new_pw_cnfm').val()!=""){
                if(pwstd.test($('#new_pw').val()) == true){
                if($('#new_pw').val() == $('#new_pw_cnfm').val()){
                    var old_pw = (SHA256($("#old_pw").val()));
                    var new_pw = (SHA256($("#new_pw").val()));
                    var param = {"old_pw": old_pw ,"new_pw": new_pw}
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
                          alert("conn");
                        },error:function(data){
                           alert("disconn");
                        }
                    })
                   
                }else{
                    alert('비밀번호와 비밀번호 확인이 일치하지 않아요');
                }
            }else{
                alert('비밀번호 규칙을 만족하지 않았어요');
            }
            }else{
                alert("입력창에 빈칸이 있어요");
            }
        }
    });
});