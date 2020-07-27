$(document).ready( function() {
    /*공지사항 올리는 창 불러오기*/
    $('#upload_notice').on("click", function(){
        if(ownership == true){
            console.log("true");
            $(".curr").animate({
                top:'50em'
            },'slow');
            $(".noticement").removeClass('curr');
            $(".noticeposting").addClass('curr');
            $(".curr").animate({
                top:'0em',
                left:'0em'
            },'slow');
        }else{
            alert("Only team owner can upload noticement");
        }
    })
    /*submit누르면 ajax 통신하고 응답하기*/
  /*  $('notice_submit').on("click",function(){
        var p_title = $('#title');
        var p_content = $('#content');
        var param = {"p_title" : p_title.val(),
                     "p_content" : p_contents.val()
                    };
        $.ajax({
            type:"POST",
            async:true,
            headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            url:"http://localhost:80/LinkeT/createNotice",
            dataType:"json",
            data:JSON.stringify(param),
            success:function(data){
                console.log(data);
            },
            error:function(data){
                condole.log(data);
            }
        });
    $(".curr").animate({
        left:'100em'
    },'slow');
    $(".noticeposting").removeClass('curr');
    $(".curr").attr("left", 0);
    $(".noticement").addClass("curr");
    $(".curr").animate({
        top:'0em',
        left:'0em'
    },'slow');
    });*/
    
    /*소형메뉴 누르면 이동하기*/
    $('nav.menu a').click( function() {
        $(this).parent().find('.current').removeClass('current');
        $(this).addClass('current');
        var tg = $(this).attr("id");
          $(".curr").animate({
             top:'50em' 
          },'slow');
        $(".mainwrapper").find('.curr').removeClass('curr');
        $(".mainwrapper").find('.'+tg).addClass('curr');
          $(".curr").animate({
             top:'0em' 
          }, 'slow');
    });
    /*창 숨기고 열기*/
    $('a[data-connect]').click( function() {
        var i = $(this).find('i');
        i.hasClass('icon-collapse-top') ? i.removeClass('icon-collapse-top').addClass('icon-collapse') : i.removeClass('icon-collapse').addClass('icon-collapse-top');
        $(this).parent().parent().toggleClass('all').next().slideToggle();
    });
      $(window).scroll(function(){
        var w = $(window).width();
        if(w < 768) {
          $('#top-button').hide();
        } else {
          var e = $(window).scrollTop();
          e>150?$('#top-button').fadeIn() :$('#top-button').fadeOut();   
        }
      });
        /*submit버튼관련*/
    $('#notice_submit').on("click", function(){
        var n_title = $("#title");  // val로 접근함
        var n_contents = $("#content");
        
        if(n_title.val()=="" || n_contents.val()==""){
            alert("제목, 혹은 내용이 없습니다.");
        }else{
            var param = {"n_title" : n_title.val(),
                         "n_content" : n_contents.val()
                        };
            $.ajax({
                type:"POST",
                async:true,
                headers:{
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                url:"http://localhost:80/LinkeT/createNotice",
                dataType:"json",
                data:JSON.stringify(param),
                success:function(data){
                    console.log(data);
                },
                error:function(data){
                    condole.log(data);
                }});
            $(".curr").animate({left:'100em'},'slow');
            $(".noticeposting").removeClass('curr');
            $(".curr").attr("left", 0);
            $(".noticement").addClass("curr");
            $(".curr").animate({top:'0em',left:'0em'},'slow');
        }
    });
        
    });

