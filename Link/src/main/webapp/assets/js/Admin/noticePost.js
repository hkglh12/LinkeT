$(document).ready( function() {
	$('#n_contents').summernote({
		lang : 'ko-KR',
		height:120,
		plcaeholder:"공지사항 내용을 입력해주세요.",
		focus:true
	});
    
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
	$("#addfile").on("click",function(){
		var multi = document.createElement("input");
		multi.setAttribute("type", "file");
		multi.setAttribute("class", "mulfile");
		
	});
});
