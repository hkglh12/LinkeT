$(document).ready( function() {
    $('')
    
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

    console.log();
    
      
  });
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
        var title = $("#title");  // val로 접근함
        var contents = $("#content");
        
        if(title.val()=="" || contents.val()==""){
            alert("제목, 혹은 내용이 없습니다.");
        }else{
            
        }
    });
});
