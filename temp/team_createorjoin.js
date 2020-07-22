$(document).ready(function(){
    $("#teamcode_join").on("click",function(){
        console.log("submitbtnClicked");
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
                console.log("success")
                console.log(data);
                var JsonData = JSON.parse(data);
                console.log(JsonData);
                $('.join.team.C').text()
            },
            error:function(data){
                alert("Check your Team Code Again!");
                
            }
        })
    }),
    

    $("#join-box").ready(function(){
            $("#join-box").animate({
                marginLeft:'25%',
                marginRight:'25%'
            },'fast');
        })
}
);