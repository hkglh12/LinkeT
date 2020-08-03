$(".main-menu").on("mouseover",function(){
	$("section").css("width","80%");
  	$("section").css("margin-left","18%"); 
});
    
$(".main-menu").on("mouseout",function(){
	$("section").css("width","95%");
	$("section").css("margin-left","6vw"); 
});

function updaterequest(target){
	location.href="/Link/admin/manage/notice/update?n_serial="+target;
}
function deleterequest(target){
		var f = document.createElement("form");
    	var inputl = document.createElement("input");
    	inputl.setAttribute("type", "hidden");
    	inputl.setAttribute("name", "n_serial");
    	inputl.setAttribute("value",target);
    	f.appendChild(inputl);
    	f.action="/Link/admin/manage/notice/delete";
    	f.method="post";
    	document.body.appendChild(f);
    	f.submit();
}

