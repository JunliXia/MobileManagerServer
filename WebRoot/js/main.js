$(document).ready(function() {
	
	changecolors("#userbit");
	touser();
	
	clientpoint();
	missionpoint();
	visitpoint();
	bussinesspoint();
}) ;


var hasbussinessnocheck=false;
function bussinesspoint() {
	var bussinessclient=0;
	$.getJSON("./WCheckHasNoDealBussinessServlet",function(outjson){
		if(outjson.check){
			hasbussinessnocheck=true;
			$("#bussinesspoint").css("display","block");
			$("#bussinesspoint").css("margin-left","90px");
			$("#bussinesspoint").css("margin-top","-12px");

		}else{
			hasbussinessnocheck=false;
			$("#bussinesspoint").css("display","none");
		}
	
	});
	if(bussinessclient!=100000){
		setTimeout(bussinesspoint, 5000);
	}

	
}


var hasvisitnocheck=false;
function visitpoint() {
	var visitclient=0;
	
	$.getJSON("./WCheckHasNoDealVisitPlanServlet",function(outjson){
		if(outjson.check){
			hasvisitnocheck=true;
			$("#visitpoint").css("display","block");
			$("#visitpoint").css("margin-left","90px");
			$("#visitpoint").css("margin-top","-12px");

		}else{
			hasvisitnocheck=false;
			$("#visitpoint").css("display","none");
		}
	
	});
	if(visitclient!=100000){
		setTimeout(visitpoint, 5000);
	}

	
}



var hasclientnocheck=false;
function clientpoint() {
	var clientcount=0;
//	clientcount++;
	$.getJSON("./WCheckHasNoCheckClientServlet",function(outjson){
			if(outjson.check){
				hasclientnocheck=true;
				$("#clientpoint").css("display","block");
				$("#clientpoint").css("margin-left","90px");
				$("#clientpoint").css("margin-top","-12px");

			}else{
				hasclientnocheck=false;
				$("#clientpoint").css("display","none");
			}
		
		});
	if(clientcount!=100000){
		setTimeout(clientpoint, 5000);
	}

}


var hasmissionnodeal=false;
function missionpoint() {
	var missioncount=0;
//	clientcount++;
	$.getJSON("./WCheckHasNoDealMissionServlet",function(outjson){
			if(outjson.check){
				hasmissionnodeal=true;
				$("#missionpoint").css("display","block");
				$("#missionpoint").css("margin-left","90px");
				$("#missionpoint").css("margin-top","-12px");

			}else{
				hasmissionnodeal=false;
				$("#missionpoint").css("display","none");
			}
		
		});
	if(missioncount!=100000){
		setTimeout(missionpoint, 5500);
	}

}



function loginout() {
	 if (confirm("确定退出吗？")){	window.location.href="jsp/login.jsp"}
	 else{}
}


function changecolors(x) {
	$(".left_list div").attr("class","one");
	  $(x).attr("class","two");
}

function tobussiness() {
	$(".usermanage").hide();
	$(".mapmanage").hide();
	$(".transactionmanage").hide();
	$(".taskmanage").hide();
	$(".attendance").hide();
	$(".client").hide();
	$(".visitplan").hide();
	$(".bussiness").hide();
	$(".bussiness").html("");
	if($(".bussiness").html()==""){
	$(".bussiness").load("jsp/child_jsp/bussiness.jsp");}
	else{
	}
	$(".bussiness").show();
}

function tovisitplan() {
	$(".usermanage").hide();
	$(".mapmanage").hide();
	$(".transactionmanage").hide();
	$(".taskmanage").hide();
	$(".attendance").hide();
	$(".client").hide();
	$(".bussiness").hide();
	$(".visitplan").hide();
	$(".visitplan").html("");
	if($(".visitplan").html()==""){
	$(".visitplan").load("jsp/child_jsp/visitplan.jsp");}
	else{
	}
	$(".visitplan").show();
}

function toclient(){
	$(".usermanage").hide();
	$(".mapmanage").hide();
	$(".transactionmanage").hide();
	$(".taskmanage").hide();
	$(".attendance").hide();
	$(".client").hide();
	$(".visitplan").hide();
	$(".bussiness").hide();
	$(".client").html("");
	if($(".client").html()==""){
	$(".client").load("jsp/child_jsp/client.jsp");}
	else{
		
	}
	$(".client").show();
}

function toattendance() {
	$(".usermanage").hide();
	$(".mapmanage").hide();
	$(".transactionmanage").hide();
	$(".taskmanage").hide();
	$(".attendance").hide();
	$(".client").hide();
	$(".visitplan").hide();
	$(".bussiness").hide();
	$(".attendance").html("");
	if($(".attendance").html()==""){
	$(".attendance").load("jsp/child_jsp/attendance.jsp");}
	else{
		
	}
	$(".attendance").show();
}

function touser() {
	$(".usermanage").hide();
	$(".mapmanage").hide();
	$(".transactionmanage").hide();
	$(".taskmanage").hide();
	$(".attendance").hide();
	$(".client").hide();
	$(".visitplan").hide();
	$(".bussiness").hide();
	$(".usermanage").html("");
	if($(".usermanage").html()==""){
	$(".usermanage").load("jsp/child_jsp/usermanage.jsp");}
	else{
		
	}
	$(".usermanage").show();
}

function tomap() {
	$(".usermanage").hide();
	$(".mapmanage").hide();
	$(".transactionmanage").hide();
	$(".taskmanage").hide();
	$(".attendance").hide();
	$(".client").hide();
	$(".visitplan").hide();
	$(".bussiness").hide();
	$(".mapmanage").html("");
	if($(".mapmanage").html()==""){
	$(".mapmanage").load("jsp/child_jsp/mapmanage.jsp");}
	else{
		
	}
	$(".mapmanage").show();
}
function totransaction() {
	$(".usermanage").hide();
	$(".mapmanage").hide();
	$(".transactionmanage").hide();
	$(".taskmanage").hide();
	$(".attendance").hide();
	$(".client").hide();
	$(".bussiness").hide();
	$(".visitplan").hide();
	$(".transactionmanage").html("");
	if($(".transactionmanage").html()==""){
	$(".transactionmanage").load("jsp/child_jsp/transactionmanage.jsp");
	}else{
		
	}
	$(".transactionmanage").show();
}
function totask() {
	$(".usermanage").hide();
	$(".mapmanage").hide();
	$(".transactionmanage").hide();
	$(".taskmanage").hide();
	$(".attendance").hide();
	$(".client").hide();
	$(".visitplan").hide();
	$(".bussiness").hide();
	$(".taskmanage").html("");
	if($(".taskmanage").html()==""){
	$(".taskmanage").load("jsp/child_jsp/taskmanage.jsp");
	}
	else{
		
	}
	$(".taskmanage").show();
}



function closepage() {
	$(".shadow").hide();
}