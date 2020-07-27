$(document).ready(function(){

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
    
    if(usrTeam1 == ''){
        $("#teamfirst .link").attr("href","/LinkeT/r/team_createorjoin.html");
        $("#teamfirst .link").text("Create or Join new Team");
        $("#c1").text("");
    }else{
    	$("#teamfirst .link").attr("href","#");
        $("#teamfirst .link").text("${usrTeam1}");
        $("#c1").text("${usrTeam1code}");
    }
    
    
    if(usrTeam2 == ''){
    	$("#teamsec .link").attr("href","/LinkeT/r/team_createorjoin.html");
        $("#teamsec .link").text("Create or Join new Team");
        $("#c2").text("");
    }else{
    	$("#teamsec .link").attr("href","#");
        $("#teamsec .link").text("${usrTeam2}");
        $("#c2").text("${usrTeam2code}");
    }
    
    
    if(usrTeam3 == ''){
    	$("#teamthird .link").attr("href","/LinkeT/r/team_createorjoin.html");
        $("#teamthird .link").text("Create or Join new Team");
        $("#c3").text("");
    }else{
    	$("#teamthird .link").attr("href","#");
        $("#teamthird .link").text("${usrTeam3}");
        $("#c3").text("${usrTeam3code}");
    }
});