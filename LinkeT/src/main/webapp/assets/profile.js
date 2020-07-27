$(document).ready(function(){
    console.log(usrTeam1);
    console.log(usrTeam2);
    console.log(usrTeam3);
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
    	/* $("#teamfirst .link").attr("href","/LinkeT/teammain"); */
        $("#teamfirst .link").text(usrTeam1);
        $("#teamfirst .link").on("click",function(){
            var codet = $(this).parent().parent().find(".teamcode").text();
			var f = document.createElement("form");
			inputl = document.createElement("input");
            inputl.setAttribute("type","hidden");
            inputl.setAttribute("name","teamcode");
			inputl.setAttribute("value",codet);
			f.appendChild(inputl);
            
			f.action="http://localhost:80/LinkeT/teammain"
			f.method="post";
			document.body.appendChild(f);
			f.submit();
        });
        $("#c1").text(usrTeam1code);
    }
    
    
    if(usrTeam2 == ''){
    	$("#teamsec .link").attr("href","/LinkeT/r/team_createorjoin.html");
        $("#teamsec .link").text("Create or Join new Team");
        $("#c2").text("");
    }else{
        
    	$("#teamsec .link").on("click",function(){
           var codet = $(this).parent().parent().find(".teamcode").text();
			var f = document.createElement("form");
			inputl = document.createElement("input");
            inputl.setAttribute("type","hidden");
            inputl.setAttribute("name","teamcode");
			inputl.setAttribute("value",codet);
			f.appendChild(inputl);
            
			f.action="http://localhost:80/LinkeT/teammain"
			f.method="post";
			document.body.appendChild(f);
			f.submit();
        });
        $("#teamsec .link").text(usrTeam2);
        $("#c2").text(usrTeam2code);
    }
    
    
    if(usrTeam3 == ''){
    	$("#teamthird .link").attr("href","/LinkeT/r/team_createorjoin.html");
        $("#teamthird .link").text("Create or Join new Team");
        $("#c3").text("");
    }else{
    	$("#teamthird .link").on("click",function(){
            var codet = $(this).parent().parent().find(".teamcode").text();
			var f = document.createElement("form");
			inputl = document.createElement("input");
            inputl.setAttribute("type","hidden");
            inputl.setAttribute("name","teamcode");
			inputl.setAttribute("value",codet);
			f.appendChild(inputl);
            
			f.action="http://localhost:80/LinkeT/teammain"
			f.method="post";
			document.body.appendChild(f);
			f.submit();
        });
        $("#teamthird .link").text(usrTeam3);
        $("#c3").text(usrTeam3code);
    } 
	function move_teammain(){
	   console.log($(this).parent().parent().find(".teamcode").text());
	}
});