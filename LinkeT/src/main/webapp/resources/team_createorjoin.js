$(document).ready(function(){
    $("#teamcode_join").on("click",function(){
		var usrId="";
        console.log("submitbtnClicked");

		$.ajax({
			type:"GET",
			async:true,
			 headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            url:"http://localhost:80/LinkeT/sresolve",
			dataType:"json",
			success:function(data){
				console.log(data);
				usrId=data.usrId;
			},
			error:function(data){
				console.log(data);
			}
		})

        var tgr = $("#teamcode").val();
        console.log(tgr);
        var param = {"targetTeamCode" : tgr};
        console.log(param);
        $.ajax({
            type:"POST",
            async:true,
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            url:"http://localhost:80/LinkeT/getTeam",
            dataType:"json",
            data: JSON.stringify(param),
            success:function(data){
				if(!(data.hasOwnProperty("fstatus"))){
                console.log("success");
                console.log(data);
                console.log(data.teamOwner);
                console.log($('.join.team.C'));
				$('#teamspacename').text(data.teamName);
                $('.join.team.C').val(data.teamCode);
                $('.join.team.N').val(data.teamName);
                $('.join.team.O').val(data.teamOwner);
                $('.join.team.U').val(usrId);
                $('.join.team.C').attr("readonly",true);
                $('.join.team.N').attr("readonly",true);
                $('.join.team.O').attr("readonly",true);
                $('.join.team.U').attr("readonly",true);
                $('.join.team.G').val("CREW");
                $('.join.team.G').attr("readonly",true);
                $('#success-box').hide();
                $('#error-box').hide();
                $("#join-box").animate({
                    marginLeft:'25%',
                    marginRight:'25%'
                },'fast');
				}else{
					alert("Check your Team Code Again!");
					$("#teamcode").val('');
				}
                /*$('.join.team').attr("textAlign", "center");*/
            },
            error:function(data){
                alert("Check your Team Code Again!");
                
            }
        })
    })
});