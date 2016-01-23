var str;
$(document).ready(function() {
// readtask();
	
// showonelist();
	showmaintasktitle();
	showmissionruning();
	
	
	str="<option value ='0'>选择员工号</option>";
	$.getJSON("./WGetMissionRunningNumberServlet",function(outjson){
		
		for(var i=0;i<outjson.EmployeeList.length;i++){
			if(outjson.EmployeeList[i].EmployeeName.length<3){
				outjson.EmployeeList[i].EmployeeName+="&nbsp&nbsp&nbsp";
			}
			str=str+"<option value ="+outjson.EmployeeList[i].EmployeeId+">账号:"+outjson.EmployeeList[i].EmployeeAccount+"&nbsp员工名:"+outjson.EmployeeList[i].EmployeeName+"&nbsp需执行任务:"+outjson.EmployeeList[i].MissionNumber+"</option>";
			
		}
 	
 	});
	missiondiandian();
})

function missiondiandian() {
	var missioncount=0;
	if(hasmissionnodeal){
		$("#missionhasnodeldivid").css("display","block");
	}else {
		$("#missionhasnodeldivid").css("display","none");
	}
	if(missioncount!=100000){
		setTimeout(missiondiandian, 5000);
	}
}



function showmaintasktitle() {
	$(".right-title").html("");
	$(".right-title").append(
			"<img onclick='	showmaintasktitle(),showmissionruning()' src='./pic/daohang.png'  title='任务管理导航' style=' width:30px;height:30px; cursor:pointer; margin-left:100px;margin-bottom:-64px; margin-top:7px;' />"+
			"<div id=missionhasnodeldivid style='display:none;font-family:Microsoft YaHei;cursor: pointer;position:relative;margin-left:180px; width:120px;height:50px; padding-top:10px;margin-bottom:-64px; margin-top:4px;'><font style='font-size:13px;color:rgb(255,255,255)'>您有未处理的任务</font><img id=clienttitlepoint src='./pic/redpoint.png' style='height:10px;width:10px;padding-bottom:3px; margin-top:-20px;;margin-left:109px;'><img/></div>"+
		 	
			"<div id=usermanagerserch class=zySearch> " +
 			"<input id=clientsearchameid class=search-input  placeholder='全员搜索或请输入员工名' ></input>"+
 			"<div class=search-btn  onclick='searchclient()'>搜索</div>"+
 			"</div>"+
 			"<div id=attenss style='height:25px;width:400px;float:right;margin-top:-15px; margin-left:700px;'>" +
// "<div class=usebut style=' cursor:pointer; position: relative;'
// onclick='updateclient()'>修改</div>"+
				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='seemission()'>查看</div>"+
				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='undomission()'>撤销</div>"+
				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='addmission()'>增加</div>"+
				"<div class=usebut  style=' cursor:pointer; position: relative; margin-left:50px;'> <select id=selectmissionID onchange='showSelectMission()' class=useselect style='height:25px;'><option value='1'>需执行</option><option value='2'>待处理</option><option value='3'>已结束</option><option value='4'>已撤销</option><option value='5'>任务总结</option><option value='6'>延期记录</option></select></div> "+
 			"</div>"
 		
	);
	
}
function showSelectMission() {
	var seleid=$("#selectmissionID").val();
	
	if(seleid==1){
		showmissionruning();
	}else if(seleid==2){
		showmissiondel();
	}else if(seleid==3){
		showmissioncomplete();
	}else if(seleid==4){
		showmissionundo();
	}else if(seleid==5){
		showmissionconclusion();
	}else if(seleid==6){
		showmissiondelay();
	}

	var op=parseInt(seleid);
	$("#selectmissionID").find("option").eq(op-1).attr("selected","selected");

}

function showmissiondelay() {
	$(".task-list").html("");

	$(".task-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
  			'<div class="group-title" ><font>任务记录</font>'+
  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
// "<div class=clientbut style=' cursor:pointer; position: relative;'
// onclick='undomission()' >撤销</div>"+
// "<div class=clientbut style=' cursor:pointer; position: relative;'
// onclick='seemission()'>查看</div>"+
// "<div class=clientbut style=' cursor:pointer; position: relative;'
// onclick='checkmission()'>审核</div>"+
// "<div class=clientbut style=' cursor:pointer; position: relative;'
// onclick='seemissionconclu()'>任务总结</div>"+
			'</div>'+
  			'<table id="tab"   style="font-size:13px;" width="100%">'+
               '<tbody>'+
                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
                    '<th>任务延期号</th>'+
                    '<th>延期时间</th>'+
                    '<th>员工账号</th>'+
                    '<th>员工名</th>'+
                    '<th>延期原因</th>'+
                    '<th>任务内容</th>'+
                '</tr>'+
                '</tbody>'+
                
                 
                '<tbody  class="thisgroup">'+
                	
                '</tbody>'+
                '</table>'+
             
                
                
  		'</div>');
	
// var id=$(x).find("th").eq(6).html();
$.getJSON("./WGetAllMissionUndoServlet",function(outjson){
	for ( var i = 0; i < outjson.MissionList.length; i++) {
			for ( var j = 0; j < $(".group-one").length; j++) {
					$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;	background-color: rgb(232,251,250);">'+
// '<th style="font-weight: normal; width:60px; height:30px;"></img><img
// src="./pic/chuchaibangding.png" style="height:30px;width:30px;cursor:
// pointer;display: '+bind+';float:right" title="出差绑定"></img></th>'+
						    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionUndoId+'</th>'+
						    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionUndoTime+'</th>'+
						    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].EmployeeAccount+'</th>'+
						    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].EmployeeName+'</th>'+
						    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionUndoReason+'</th>'+
						    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionContent+'</th>'+
// '<th style="font-weight: bold; color:'+co+';">'+state+'</th>'+
// '<th style="font-weight: normal; display:
// none;">'+outjson.MissionList[i].MissionBussinessBandState+'</th>'+
// '<th style="font-weight: normal; display:
// none;">'+outjson.MissionList[i].EmployeeId+'</th>'+
// '<th style="font-weight: normal; display:
// none;">'+outjson.VisitPlanList[i].VisitPlanDays+'</th>'+
// '<th style="font-weight: normal; display:
// none;">'+outjson.VisitPlanList[i].VisitBussinessBandState+'</th>'+
// '<th style="font-weight: normal; display:
// none;">'+outjson.VisitPlanList[i].ClientId+'</th>'+
							'</tr>')
				}
			}
	
});
curtab=null;
}






function undomission() {
	var x=curtab;
	if(x==null){
		alert("请选择需要操作的对象");
	}else{
		var missionId=$(x).find("th").eq(1).html();
		var date=new Date();
		var strdate=date.format("yyyy/MM/dd");
		$(".shadow").show();
		$(".pagehead font").html("撤销任务")
		$(".showpage").css("width","280px");
		$(".showpage").css("height","160px");
		$(".showpage").css("margin-left","600px");
		$(".pagemain").html("<div class=missionundo>"+
					"<div><a>任务编号：</a><input type='text' value="+missionId+" ></div>"+
					"<div><a>撤销时间：</a><input type='text' value="+strdate+"></div>"+
					"<div><a>撤销原因：</a><input type='text' /></div>"+
					"<div style='margin-left: 30px;margin-top: 10px;'><button onclick='undovmissionok()' class=but1>确定</button><button class=but1 onclick='closepage()'>取消</button></div>"+
					"</div>");
	}
}

function undovmissionok( ) {
	var missionid=$(".missionundo").find("div").eq(0).find("input").val();
	var date=$(".missionundo").find("div").eq(1).find("input").val();
	var reson=$(".missionundo").find("div").eq(2).find("input").val();
// alert(missionid);
	$.getJSON("./WUndoMissionServclet",{MissionId:missionid,MissionUndoReason:reson,MissionUndoTime:date},function(outjson){
		if(	outjson.check){alert("成功！");closepage();
		
		}else {
				alert("失败！");closepage();
		}
		
		var seleed=$("#selectmissionID").val();
		if(seleed==1){
			showmissionruning();
		}
// else if(seleed==2){
// showdistribution();
// }else if(seleed==3){
// shownocheck();
// }else if(seleed==4){
// showdelclient();
// }
		showmaintasktitle();
		var op=parseInt(seleed);
		$("#selectmissionID").find("option").eq(op-1).attr("selected","selected");

	
	})
}

function seemission() {
	var x=curtab;
	var seleid=$("#selectmissionID").val();
	var missionid;
	var pubdate;
	var content;
	var endtime;
	var state;
	
// if(seleid==1){
		missionid=$(x).find("th").eq(1).html();
		pubdate=$(x).find("th").eq(2).html();
		content=$(x).find("th").eq(5).html();
		endtime=$(x).find("th").eq(6).html();
		state=$(x).find("th").eq(7).html();
// }
		var seleed=$("#selectmissionID").val();

// var bbandstate=$(x).find("th").eq(6).html();
	if(x==null){
		alert("请选择需要操作的对象");
	
	}else if(seleed==6){
		alert("无法查看");
		
	}else{
	$(".task-list").html("");

		$(".task-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
	  			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="showback()"></img><div style="margin-top:-25px;"><font>任务记录详情</font></div></div>'+
	  			'<div class="ground-one-left" style="float:left;width:50%;height:100%; 	">'+
   				'<div style="position:relative; height:500px;margin-left:100px;">'+
   				'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;margin-top:10px;">任务号</font></div>'+	
					'<div class="main-input" style="margin-left:10px;width:289px;padding-top:7px;margin-top:10px;padding-top:10px;" >'+missionid+'</div>'+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">发布时间</font></div>'+	
					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+pubdate+'</div>'+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">任务内容</font></div>'+	
					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+content+'</div>'+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">任务期限</font></div>'+	
					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+endtime+'</div>'+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">任务状态</font></div>'+	
					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+state+'</div>'+
// '<div style="margin-top:10px;"><font style="font-size:15px;font-weight:
// bold;margin-left:10px;">出差绑定号</font></div>'+
// '<div class="main-input" style="margin-left:10px;
// width:289px;margin-top:10px;padding-top:10px;">'+bbandstate+'</div>'+
					
					
				'</div>'+
   			'</div>'+
   			
   			'<div class="ground-one-right" style="float:left;width:49%;height:100%;border-left:2px;border-left-color:black;border-left-style:dashed;">'+
   				'<img src="./pic/missionbackgro.jpg"  style="width:100%;height:100%"> '+ 
	  		'</div>');
		
	
		
	}
}


function addmission() {
	
	
	var date=new Date();
	var strdate=date.format("yyyy/MM/dd");
	$(".task-list").html("");

		$(".task-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
	  			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="showback()"></img><div style="margin-top:-25px;"><font>任务记录详情</font></div></div>'+
	  			'<div class="ground-one-left" style="float:left;width:50%;height:100%; 	">'+
   				'<div style="position:relative; height:500px;margin-left:100px;">'+
// '<div style="margin-top:10px;"><font style="font-size:15px;font-weight:
// bold;margin-left:10px;margin-top:10px;">任务号</font></div>'+
// '<div class="main-input"
// style="margin-left:10px;width:289px;padding-top:7px;margin-top:10px;padding-top:10px;"
// >'+missionid+'</div>'+
   				"<div id=visitdian style='border:1px solid black;border-radius:1px;margin-top:10px;width:310px;height:50px;margin-left:10px;'><select id=missionnumid class=useselect  style='cursor:pointer; position: relative;margin-left:4px;width:300px;height:50px;font-size: 15px;background-color:rgb(255,255,255);color:rgb(0,0,0);'></select></div>"+
   				'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">发布时间</font></div>'+	
					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+strdate+'</div>'+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">任务内容</font></div>'+	
					'<input id=missioncontentid  class="main-input" style="margin-left:10px;margin-top:0px;margin-top:5px; width:309px;height:42px;">'+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">任务期限</font></div>'+	
					'<input id=endtime onclick=SelectDate(this,\'yyyy/MM/dd\') class="main-input" style="margin-left:10px;margin-top:0px;margin-top:5px; width:309px;height:42px;">'+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">任务状态</font></div>'+	
					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">未开始</div>'+
// '<div style="margin-top:10px;"><font style="font-size:15px;font-weight:
// bold;margin-left:10px;">出差绑定号</font></div>'+
// '<div class="main-input" style="margin-left:10px;
// width:289px;margin-top:10px;padding-top:10px;">'+bbandstate+'</div>'+
					
				'</div>'+
				'<div style="margin-left:113px;margin-top:-70px;"><div class="usebut" onclick="addmissionok()" style="font-size: 14px;width: 76px;height: 35px;line-height: 33px;">提交</div><div class="usebut" style="font-size: 14px;width: 76px;height: 35px;line-height: 33px;" onclick="showback()">取消</div></div>'+
   			'</div>'+
   			
   			'<div class="ground-one-right" style="float:left;width:49%;height:100%;border-left:2px;border-left-color:black;border-left-style:dashed;">'+
   				'<img src="./pic/missionbackgro.jpg"  style="width:100%;height:100%"> '+ 
	  		'</div>');
		
		$("#missionnumid").append(str);
		
}


function addmissionok() {
	var employeeid=$("#missionnumid").val();
	var date=new Date();
	var pubdate=date.format("yyyy/MM/dd");
	var content=$("#missioncontentid").val();
	var endtime=$("#endtime").val();
	$.getJSON("./WAddMissionServlet",{EmployeeId:employeeid,MissionPubdate:pubdate,MissionContent:content,MissionDeadline:endtime},function(outjson){
	if(	outjson.check){alert("成功！");closepage();
		
		}else {
				alert("失败！");closepage();
		}
		showback();
		var seleed=$("#selectmissionID").val();
		if(seleid==1){
			showmissionruning();
		}else if(seleid==2){
			showmissiondel();
		}else if(seleid==3){
			showmissioncomplete();
		}else if(seleid==4){
			showmissionundo();
		}else if(seleid==5){
			showmissionconclusion();
		}
		showmaintasktitle();
		var op=parseInt(seleed);
		$("#selectmissionID").find("option").eq(op-1).attr("selected","selected");

		
	});
}


function showback() {

	var seleid=$("#selectmissionID").val();
	if(seleid==1){
		showmissionruning();
	}else if(seleid==2){
		showmissiondel();
	}else if(seleid==3){
		showmissioncomplete();
	}else if(seleid==4){
		showmissionundo();
	}else if(seleid==5){
		showmissionconclusion();
	}
	showmaintasktitle();
	var op=parseInt(seleid);
	$("#selectmissionID").find("option").eq(op-1).attr("selected","selected");


}
function showmissioncomplete() {
	$(".task-list").html("");

		$(".task-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
	  			'<div class="group-title" ><font>任务记录</font>'+
	  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
// "<div class=clientbut style=' cursor:pointer; position: relative;'
// onclick='undomission()' >撤销</div>"+
// "<div class=clientbut style=' cursor:pointer; position: relative;'
// onclick='seemission()'>查看</div>"+
// "<div class=clientbut style=' cursor:pointer; position: relative;'
// onclick='checkmission()'>审核</div>"+
				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='seemissionconclu()'>任务总结</div>"+
				'</div>'+
	  			'<table id="tab"   style="font-size:13px;" width="100%">'+
	               '<tbody>'+
	                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
	                	'<th>标志</th>'+
	                    '<th>任务号</th>'+
	                    '<th>发布时间</th>'+
	                    '<th>员工账号</th>'+
	                    '<th>员工名</th>'+
	                    '<th>任务内容</th>'+
	                    '<th>任务期限</th>'+
	                    '<th>任务状态</th>'+
	                '</tr>'+
	                '</tbody>'+
	                
	                 
	                '<tbody  class="thisgroup">'+
	                	
	                '</tbody>'+
	                '</table>'+
	             
	                
	                
	  		'</div>');
		
// var id=$(x).find("th").eq(6).html();
	$.getJSON("./WGetAllMissionCompleteServlet",function(outjson){
		for ( var i = 0; i < outjson.MissionList.length; i++) {
			var co="rgb(0,0,0)"
			var state="未接受";
			if(outjson.MissionList[i].MissionState==0){
				state="未接受";
			}else if(outjson.MissionList[i].MissionState==1){
				state="执行中";
			}else if(outjson.MissionList[i].MissionState==2){
				state="未审核";
				co="rgb(0,162,232)";
			}else if(outjson.MissionList[i].MissionState==3){
				state="已审核";
			}else if(outjson.MissionList[i].MissionState==4){
				state="已撤销";
			}else if(outjson.MissionList[i].MissionState==5){
				state="已过期";
				co="rgb(0,162,232)";
			}else if(outjson.MissionList[i].MissionState==6){
				state="已失败";
			}
// //
			var bind="none";
			if(outjson.MissionList[i].MissionBussinessBandState==1){
				bind="block";
			}
			
			
				for ( var j = 0; j < $(".group-one").length; j++) {
						$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;	background-color: rgb(232,251,250);">'+
								'<th  style="font-weight: normal; width:60px; height:30px;"></img><img  src="./pic/chuchaibangding.png" style="height:30px;width:30px;cursor: pointer;display: '+bind+';float:right" title="出差绑定"></img></th>'+
							    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionId+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionPubdate+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].EmployeeAccount+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].EmployeeName+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionContent+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionDeadline+'</th>'+
							    '<th  style="font-weight: bold; color:'+co+';">'+state+'</th>'+
							    '<th  style="font-weight: normal; display: none;">'+outjson.MissionList[i].MissionBussinessBandState+'</th>'+
							    '<th  style="font-weight: normal; display: none;">'+outjson.MissionList[i].EmployeeId+'</th>'+
// '<th style="font-weight: normal; display:
// none;">'+outjson.VisitPlanList[i].VisitPlanDays+'</th>'+
// '<th style="font-weight: normal; display:
// none;">'+outjson.VisitPlanList[i].VisitBussinessBandState+'</th>'+
// '<th style="font-weight: normal; display:
// none;">'+outjson.VisitPlanList[i].ClientId+'</th>'+
								'</tr>')
					}
				}
		
	});
	curtab=null;
}

function seemissionconclu() {
		var x=curtab;
// var clientname=$(x).find("th").eq(3).html();
		var emplyname=$(x).find("th").eq(4).html();
		var state=$(x).find("th").eq(7).html();
		var missionconclusionId=$(x).find("th").eq(1).html();
		if(state!="已审核"){
			alert("该任务已失败");
		}else{
			$.getJSON("./WGetMissionConclusionServlet",{MissionConclusionId:missionconclusionId},function(outjson){
				$(".task-list").html("");
				var paths=outjson.MissionAccessoryPath.split("/");
				$(".task-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
			  			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="showback()"></img><div style="margin-top:-25px;"><font>总结详情</font></div></div>'+
			  			'<div class="ground-one-left" style="float:left;width:50%;height:100%; 	">'+
		   				'<div style="position:relative; height:500px;margin-left:100px;">'+
		   				'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;margin-top:10px;">任务总结号</font></div>'+	
							'<div id=misconcluid class="main-input" style="margin-left:10px;width:289px;padding-top:7px;margin-top:10px;padding-top:10px;" >'+outjson.MissionConclusionId+'</div>'+
							'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">员工名</font></div>'+	
							'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+emplyname+'</div>'+
							'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">提交时间</font></div>'+	
							'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+outjson.MissionSubmitTime+'</div>'+
							'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">任务总结</font></div>'+	
							'<textarea   class="main-input" style="font-size:15px;font-weight: bold;	margin-left:10px; width:309px;height:100px;margin-top:10px;padding-top:10px;background-color:rgb(248,254,254)">'+outjson.MissionSummary+'</textarea>'+
						'</div>'+
		   			'</div>'+
		   			
		   			'<div class="ground-one-right" style="float:left;width:49%;height:100%;border-left:2px;border-left-color:black;border-left-style:dashed;">'+
		   				'<div style="position:relative; height:500px;margin-left:100px;">'+
		   					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">现场图片</font></div>'+	
		   					'<div  style="margin-left:10px;height:400px; width:289px;margin-top:10px;background-color:rgb(41,44,47);"><img style="width:100%;height:100%;" src="/upload/'+paths[2]+'"/> </div>'+
						'</div>'+
// '<div style="margin-left:113px;"><div class="usebut" style="font-size:
// 14px;width: 76px;height: 35px;line-height: 33px;margin-left:50px;"
// onclick="missioncheck(1)">审核通过</div><div class="usebut"
// style="margin-left:40px;font-size: 14px;width: 76px;height: 35px;line-height:
// 33px;" onclick="missioncheck(2)">审核不通过</div></div>'+
					'</div>'+
			  		'</div>');
				
			})
		}
		
		

}



function checkmissionconclusion() {
	var x=curtab;
// var clientname=$(x).find("th").eq(3).html();
	var state=$(x).find("th").eq(6).html();
	var emplyname=$(x).find("th").eq(3).html();
	var missionconclusionId=$(x).find("th").eq(0).html();
	if(state!="未审核"){
		alert("该任务不需要审核");
	}else{
		$.getJSON("./WGetMissionConclusionServlet",{MissionConclusionId:missionconclusionId},function(outjson){
			$(".task-list").html("");
			var paths=outjson.MissionAccessoryPath.split("/");
			$(".task-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
		  			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="showback()"></img><div style="margin-top:-25px;"><font>总结详情</font></div></div>'+
		  			'<div class="ground-one-left" style="float:left;width:50%;height:100%; 	">'+
	   				'<div style="position:relative; height:500px;margin-left:100px;">'+
	   				'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;margin-top:10px;">任务总结号</font></div>'+	
						'<div id=misconcluid class="main-input" style="margin-left:10px;width:289px;padding-top:7px;margin-top:10px;padding-top:10px;" >'+outjson.MissionConclusionId+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">员工名</font></div>'+	
						'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+emplyname+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">提交时间</font></div>'+	
						'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+outjson.MissionSubmitTime+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">任务总结</font></div>'+	
						'<textarea   class="main-input" style="font-size:15px;font-weight: bold;	margin-left:10px; width:309px;height:100px;margin-top:10px;padding-top:10px;background-color:rgb(248,254,254)">'+outjson.MissionSummary+'</textarea>'+
					'</div>'+
	   			'</div>'+
	   			
	   			'<div class="ground-one-right" style="float:left;width:49%;height:100%;border-left:2px;border-left-color:black;border-left-style:dashed;">'+
	   				'<div style="position:relative; height:500px;margin-left:100px;">'+
	   					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">现场图片</font></div>'+	
	   					'<div  style="margin-left:10px;height:400px; width:289px;margin-top:10px;background-color:rgb(41,44,47);"><img style="width:100%;height:100%;" src="/upload/'+paths[2]+'"/> </div>'+
					'</div>'+
					'<div style="margin-left:113px;"><div class="usebut"  style="font-size: 14px;width: 76px;height: 35px;line-height: 33px;margin-left:50px;" onclick="missionconclusioncheck(1)">审核通过</div><div class="usebut" style="margin-left:40px;font-size: 14px;width: 76px;height: 35px;line-height: 33px;" onclick="missionconclusioncheck(2)">审核不通过</div></div>'+
				'</div>'+
		  		'</div>');
			
		})
	}
	
	
}


function missionconclusioncheck(x) {
	var mis=curtab;
	var missionid= $(mis).find("th").eq(7).html();
	var missionconclusionid=$(mis).find("th").eq(0).html();
// var missioncon=$("#misconcluid").html();
	$.getJSON("./WCheckMissionConclusionServlet",{MissionId:missionid,MissionConclusionId:missionconclusionid,MissionCheck:x,},function(outjson){
		if(outjson.check){
			showmissionconclusion();
			alert("成功");
		}else {
			showmissionconclusion();
			alert("失败");
		}
			
	});
}


function showmissiondel() {
// var x=usedetailtab;
	$(".task-list").html("");

 			$(".task-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
 		  			'<div class="group-title" ><font>任务记录</font>'+
 		  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
// "<div class=clientbut style=' cursor:pointer; position: relative;'
// onclick='undomission()' >撤销</div>"+
// "<div class=clientbut style=' cursor:pointer; position: relative;'
// onclick='seemission()'>查看</div>"+
 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='checkmission()'>审核</div>"+
 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='delmissionout()'>过期处理</div>"+
 					'</div>'+
 		  			'<table id="tab"   style="font-size:13px;" width="100%">'+
 		               '<tbody>'+
 		                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
 		                	'<th>标志</th>'+
 		                    '<th>任务号</th>'+
 		                    '<th>发布时间</th>'+
 		                    '<th>员工账号</th>'+
 		                    '<th>员工名</th>'+
 		                    '<th>任务内容</th>'+
 		                    '<th>任务期限</th>'+
 		                    '<th>任务状态</th>'+
 		                '</tr>'+
 		                '</tbody>'+
 		                
 		                 
 		                '<tbody  class="thisgroup">'+
 		                	
 		                '</tbody>'+
 		                '</table>'+
 		             
 		                
 		                
 		  		'</div>');
 			
// var id=$(x).find("th").eq(6).html();
 		$.getJSON("./WGetAllMissionDealServlet",function(outjson){
 			for ( var i = 0; i < outjson.MissionList.length; i++) {
 				var co="rgb(0,0,0)"
 				var state="未接受";
 				if(outjson.MissionList[i].MissionState==0){
 					state="未接受";
 				}else if(outjson.MissionList[i].MissionState==1){
 					state="执行中";
 				}else if(outjson.MissionList[i].MissionState==2){
 					state="未审核";
 					co="rgb(0,162,232)";
 				}else if(outjson.MissionList[i].MissionState==3){
 					state="已审核";
 				}else if(outjson.MissionList[i].MissionState==4){
 					state="已撤销";
 				}else if(outjson.MissionList[i].MissionState==5){
 					state="已过期";
 					co="rgb(0,162,232)";
 				}else if(outjson.MissionList[i].MissionState==6){
 					state="已失败";
 				}
// //
 				var bind="none";
 				if(outjson.MissionList[i].MissionBussinessBandState==1){
 					bind="block";
 				}
 				
 				
 					for ( var j = 0; j < $(".group-one").length; j++) {
 							$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;	background-color: rgb(232,251,250);">'+
 									'<th  style="font-weight: normal; width:60px; height:30px;"></img><img  src="./pic/chuchaibangding.png" style="height:30px;width:30px;cursor: pointer;display: '+bind+';float:right" title="出差绑定"></img></th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionId+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionPubdate+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].EmployeeAccount+'</th>'+
								    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].EmployeeName+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionContent+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionDeadline+'</th>'+
 								    '<th  style="font-weight: bold; color:'+co+';">'+state+'</th>'+
 								    '<th  style="font-weight: normal; display: none;">'+outjson.MissionList[i].MissionBussinessBandState+'</th>'+
 								    '<th  style="font-weight: normal; display: none;">'+outjson.MissionList[i].EmployeeId+'</th>'+
// '<th style="font-weight: normal; display:
// none;">'+outjson.VisitPlanList[i].VisitPlanDays+'</th>'+
// '<th style="font-weight: normal; display:
// none;">'+outjson.VisitPlanList[i].VisitBussinessBandState+'</th>'+
// '<th style="font-weight: normal; display:
// none;">'+outjson.VisitPlanList[i].ClientId+'</th>'+
 									'</tr>')
 						}
 					}
 			
 		});
 		curtab=null;
}

function checkmission() {
	var x=curtab;
	var emplyname=$(x).find("th").eq(4).html();
	var state=$(x).find("th").eq(7).html();
	var missionid=$(x).find("th").eq(1).html();
	if(state!="未审核"){
		alert("该任务不需要审核");
	}else{
		$.getJSON("./WGetEmployeeMissionConclusionServlet",{MissionId:missionid},function(outjson){
			$(".task-list").html("");
			var paths=outjson.MissionAccessoryPath.split("/");
			$(".task-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
		  			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="showback()"></img><div style="margin-top:-25px;"><font>总结详情</font></div></div>'+
		  			'<div class="ground-one-left" style="float:left;width:50%;height:100%; 	">'+
	   				'<div style="position:relative; height:500px;margin-left:100px;">'+
	   				'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;margin-top:10px;">任务总结号</font></div>'+	
						'<div id=misconcluid class="main-input" style="margin-left:10px;width:289px;padding-top:7px;margin-top:10px;padding-top:10px;" >'+outjson.MissionConclusionId+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">员工名</font></div>'+	
						'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+emplyname+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">提交时间</font></div>'+	
						'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+outjson.MissionSubmitTime+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">任务总结</font></div>'+	
						'<textarea   class="main-input" style="font-size:15px;font-weight: bold;	margin-left:10px; width:309px;height:100px;margin-top:10px;padding-top:10px;background-color:rgb(248,254,254)">'+outjson.MissionSummary+'</textarea>'+
					'</div>'+
	   			'</div>'+
	   			
	   			'<div class="ground-one-right" style="float:left;width:49%;height:100%;border-left:2px;border-left-color:black;border-left-style:dashed;">'+
	   				'<div style="position:relative; height:500px;margin-left:100px;">'+
	   					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">现场图片</font></div>'+	
	   					'<div  style="margin-left:10px;height:400px; width:289px;margin-top:10px;background-color:rgb(41,44,47);"><img style="width:100%;height:100%;" src="/upload/'+paths[2]+'"/> </div>'+
					'</div>'+
					'<div style="margin-left:113px;"><div class="usebut"  style="font-size: 14px;width: 76px;height: 35px;line-height: 33px;margin-left:50px;" onclick="missioncheck(1)">审核通过</div><div class="usebut" style="margin-left:40px;font-size: 14px;width: 76px;height: 35px;line-height: 33px;" onclick="missioncheck(2)">审核不通过</div></div>'+
				'</div>'+
		  		'</div>');
			
		})
	}
	
	
}

function missioncheck(x) {
	var mis=curtab;
	var missionid= $(mis).find("th").eq(1).html();
	var missioncon=$("#misconcluid").html();
	$.getJSON("./WCheckMissionConclusionServlet",{MissionId:missionid,MissionConclusionId:missioncon,MissionCheck:x,},function(outjson){
		if(outjson.check){
// showmission();
			alert("成功");
		}else {
// showmission();
			alert("失败");
		}
			
	});
	showmissiondel();
}

function delmissionout() {
	var mis=curtab;
	var missionid=$(mis).find("th").eq(1).html();
	var state=$(mis).find("th").eq(7).html();
	
	if(state!="已过期"){
		alert("该任务不需要过期处理");
 	}else{
 		$(".shadow").show();
 		$(".pagehead font").html("过期处理")
 		$(".showpage").css("width","280px");
 		$(".showpage").css("height","100px");
 		$(".showpage").css("margin-left","600px");
 		$(".pagemain").html("<div class=userset>"+
 	  				"<div style='margin-left: 30px;margin-top: 10px;'><button onclick='delmissionoutOK(1)'  class=but1>延期处理</button><button class=but1  onclick='delmissionoutOK(2)'  >判定失败</button></div>");
 	}
}
function delmissionoutOK(x) {
	var mis=curtab;
	var missionid=$(mis).find("th").eq(1).html();
	var date=new Date();
	var strdate=date.format("yyyy/MM/dd");
	var emplyname=$(mis).find("th").eq(4).html();
	var userid=$(mis).find("th").eq(9).html();
	if(x==1){
		closepage();
		
		$(".shadow").show();
		$(".pagehead font").html("延期处理")
		$(".showpage").css("width","280px");
		$(".showpage").css("height","200px");
		$(".showpage").css("margin-left","600px");
		$(".pagemain").html("<div class=userset>"+
					"<div><a>拜访计划 : </a>&nbsp"+missionid+"</div>"+
	  				"<div><a>员工名称 : </a>&nbsp"+emplyname+"</div>"+
	  				"<div><a>延期时间：</a>"+strdate+"</div>"+
	  				"<div><a>延期期限：</a><input id=deltimeid  onclick=SelectDate(this,\'yyyy/MM/dd\') style='font-size:17px;width:100px;'></input></div>"+
	  				"<div style='display:none'>"+userid+"</div>"+
	  				"<div style='margin-left: 30px;margin-top: 10px;'><button onclick='delmissionoutOKOK()' class=but1>确定</button><button class=but1 onclick='closeshadow()'>取消</button></div>"+
	  				"</div>");
	}else if(x==2){
		$.getJSON("./WAbandonMissionServlet",{MissionId:missionid},function(outjson){
			if(outjson.check){
// showmission();
				alert("成功");
				closepage();
			}else {
// showmission();
				alert("失败");
				closepage();
			}
				
		});
		showmissiondel();
	}
	
}
function delmissionoutOKOK() {
	var mis=curtab;
	var missionId=$(mis).find("th").eq(1).html();
	var endtime=$("#deltimeid").val();
	var date=new Date();
	var strdate=date.format("yyyy/MM/dd");
	if(!duibi(strdate, endtime)){
		alert("延期期限不能小于延期时间")
	}else{
		$.getJSON("./WDelayMissionServlet",{MissionId:missionId,MissionDelayDeadline:endtime,MissionDelayTime:strdate},function(outjson){
			if(outjson.check){
// showmission();
				alert("成功");
				closepage();
			}else {
// showmission();
				alert("失败");
				closepage();
			}
				
		});
		showmissiondel();
	}
}

function showmissioncomple() {
	$(".task-list").html("");

		$(".task-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
	  			'<div class="group-title" ><font>任务记录</font>'+
	  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
// "<div class=clientbut style=' cursor:pointer; position: relative;'
// onclick='undomission()' >撤销</div>"+
// "<div class=clientbut style=' cursor:pointer; position: relative;'
// onclick='seemission()'>查看</div>"+
// "<div class=clientbut style=' cursor:pointer; position: relative;'
// onclick='checkmission()'>审核</div>"+
				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='()'>任务总结</div>"+
				'</div>'+
	  			'<table id="tab"   style="font-size:13px;" width="100%">'+
	               '<tbody>'+
	                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
	                	'<th>标志</th>'+
	                    '<th>任务号</th>'+
	                    '<th>发布时间</th>'+
	                    '<th>员工账号</th>'+
	                    '<th>员工名</th>'+
	                    '<th>任务内容</th>'+
	                    '<th>任务期限</th>'+
	                    '<th>任务状态</th>'+
	                '</tr>'+
	                '</tbody>'+
	                
	                 
	                '<tbody  class="thisgroup">'+
	                	
	                '</tbody>'+
	                '</table>'+
	             
	                
	                
	  		'</div>');
		
// var id=$(x).find("th").eq(6).html();
	$.getJSON("./WGetAllMissionRuningServlet",function(outjson){
		for ( var i = 0; i < outjson.MissionList.length; i++) {
			var co="rgb(0,0,0)"
			var state="未接受";
			if(outjson.MissionList[i].MissionState==0){
				state="未接受";
			}else if(outjson.MissionList[i].MissionState==1){
				state="执行中";
			}else if(outjson.MissionList[i].MissionState==2){
				state="未审核";
				co="rgb(0,162,232)";
			}else if(outjson.MissionList[i].MissionState==3){
				state="已审核";
			}else if(outjson.MissionList[i].MissionState==4){
				state="已撤销";
			}else if(outjson.MissionList[i].MissionState==5){
				state="已过期";
				co="rgb(0,162,232)";
			}else if(outjson.MissionList[i].MissionState==6){
				state="已失败";
			}
// //
			var bind="none";
			if(outjson.MissionList[i].MissionBussinessBandState==1){
				bind="block";
			}
			
			
				for ( var j = 0; j < $(".group-one").length; j++) {
						$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;	background-color: rgb(232,251,250);">'+
								'<th  style="font-weight: normal; width:60px; height:30px;"></img><img  src="./pic/chuchaibangding.png" style="height:30px;width:30px;cursor: pointer;display: '+bind+';float:right" title="出差绑定"></img></th>'+
							    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionId+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionPubdate+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].EmployeeAccount+'</th>'+
						    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].EmployeeName+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionContent+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionDeadline+'</th>'+
							    '<th  style="font-weight: bold; color:'+co+';">'+state+'</th>'+
							    '<th  style="font-weight: normal; display: none;">'+outjson.MissionList[i].MissionBussinessBandState+'</th>'+
// '<th style="font-weight: normal; display:
// none;">'+outjson.VisitPlanList[i].VisitPlanCycleNumber+'</th>'+
// '<th style="font-weight: normal; display:
// none;">'+outjson.VisitPlanList[i].VisitPlanDays+'</th>'+
// '<th style="font-weight: normal; display:
// none;">'+outjson.VisitPlanList[i].VisitBussinessBandState+'</th>'+
// '<th style="font-weight: normal; display:
// none;">'+outjson.VisitPlanList[i].ClientId+'</th>'+
								'</tr>')
					}
				}
		
	});
	curtab=null;
}


function showmissionconclusion() {
	$(".task-list").html("");

 			$(".task-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
 		  			'<div class="group-title" ><font>任务记录</font>'+
 		  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
// "<div class=clientbut style=' cursor:pointer; position: relative;'
// onclick='undomission()' >撤销</div>"+
 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='seemissionconclusion()'>查看</div>"+
 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='checkmissionconclusion()'>审核</div>"+
// "<div class=clientbut style=' cursor:pointer; position: relative;'
// onclick='delmissionout()'>过期处理</div>"+
 					'</div>'+
 		  			'<table id="tab"   style="font-size:13px;" width="100%">'+
 		               '<tbody>'+
 		                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
// '<th>标志</th>'+
 		                    '<th>任务总结号</th>'+
 		                    '<th>提交时间</th>'+
 		                    '<th>员工账号</th>'+
 		                    '<th>员工名</th>'+
 		                    '<th>任务内容</th>'+
 		                    '<th>任务总结</th>'+
 		                    '<th>审核结果</th>'+
 		                '</tr>'+
 		                '</tbody>'+
 		                
 		                 
 		                '<tbody  class="thisgroup">'+
 		                	
 		                '</tbody>'+
 		                '</table>'+
 		             
 		                
 		                
 		  		'</div>');
 			
 		$.getJSON("./WGetAllMissionConclusionWithEmployeeInfoServlet",function(outjson){
 			for ( var i = 0; i < outjson.MissionConclusionList.length; i++) {
 				var co="rgb(0,0,0)"
 				var state="未审核";
 				if(outjson.MissionConclusionList[i].MissionCheck==0){
 					state="未审核";
 					co="rgb(0,162,232)";
 				}else if(outjson.MissionConclusionList[i].MissionCheck==1){
 					state="已通过";
 				}else if(outjson.MissionConclusionList[i].MissionCheck==2){
 					state="未通过";
 					
 				}
 				
 				
 					for ( var j = 0; j < $(".group-one").length; j++) {
 							$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;	background-color: rgb(232,251,250);">'+
 								    '<th  style="font-weight: normal; ">'+outjson.MissionConclusionList[i].MissionConclusionId+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.MissionConclusionList[i].MissionSubmitTime+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.MissionConclusionList[i].EmployeeAccount+'</th>'+
								    '<th  style="font-weight: normal; ">'+outjson.MissionConclusionList[i].EmployeeName+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.MissionConclusionList[i].MissionContent+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.MissionConclusionList[i].MissionSummary+'</th>'+
 								    '<th  style="font-weight: bold; color:'+co+';">'+state+'</th>'+
 								   '<th  style="font-weight: normal;display:none; ">'+outjson.MissionConclusionList[i].MissionId+'</th>'+
 									'</tr>')
 						}
 					}
 			
 		});
 		curtab=null;
}

function seemissionconclusion() {
	var x=curtab;
// var state=$(x).find("th").eq(4).html();
	var myname=$(x).find("th").eq(3).html();
	var missionconclusionId=$(x).find("th").eq(0).html();
	if(x==null){
		alert("请选择查看对象");
	}else{
		$.getJSON("./WGetMissionConclusionServlet",{MissionConclusionId:missionconclusionId},function(outjson){
			$(".task-list").html("");
			var paths=outjson.MissionAccessoryPath.split("/");
			$(".task-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
		  			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="showback()"></img><div style="margin-top:-25px;"><font>总结详情</font></div></div>'+
		  			'<div class="ground-one-left" style="float:left;width:50%;height:100%; 	">'+
	   				'<div style="position:relative; height:500px;margin-left:100px;">'+
	   				'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;margin-top:10px;">任务总结号</font></div>'+	
						'<div id=misconcluid class="main-input" style="margin-left:10px;width:289px;padding-top:7px;margin-top:10px;padding-top:10px;" >'+outjson.MissionConclusionId+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">员工名</font></div>'+	
						'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+myname+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">提交时间</font></div>'+	
						'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+outjson.MissionSubmitTime+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">任务总结</font></div>'+	
						'<textarea   class="main-input" style="font-size:15px;font-weight: bold;	margin-left:10px; width:309px;height:100px;margin-top:10px;padding-top:10px;background-color:rgb(248,254,254)">'+outjson.MissionSummary+'</textarea>'+
					'</div>'+
	   			'</div>'+
	   			
	   			'<div class="ground-one-right" style="float:left;width:49%;height:100%;border-left:2px;border-left-color:black;border-left-style:dashed;">'+
	   				'<div style="position:relative; height:500px;margin-left:100px;">'+
	   					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">现场图片</font></div>'+	
	   					'<div  style="margin-left:10px;height:400px; width:289px;margin-top:10px;background-color:rgb(41,44,47);"><img style="width:100%;height:100%;" src="/upload/'+paths[2]+'"/> </div>'+
					'</div>'+
// '<div style="margin-left:113px;"><div class="usebut" style="font-size:
// 14px;width: 76px;height: 35px;line-height: 33px;margin-left:50px;"
// onclick="missioncheck(1)">审核通过</div><div class="usebut"
// style="margin-left:40px;font-size: 14px;width: 76px;height: 35px;line-height:
// 33px;" onclick="missioncheck(2)">审核不通过</div></div>'+
				'</div>'+
		  		'</div>');
			
		})
	}
	
	
}


function showmissionundo() {
	$(".task-list").html("");

		$(".task-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
	  			'<div class="group-title" ><font>任务记录</font>'+
	  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
// "<div class=clientbut style=' cursor:pointer; position: relative;'
// onclick='undomission()' >撤销</div>"+
// "<div class=clientbut style=' cursor:pointer; position: relative;'
// onclick='seemission()'>查看</div>"+
// "<div class=clientbut style=' cursor:pointer; position: relative;'
// onclick='checkmission()'>审核</div>"+
// "<div class=clientbut style=' cursor:pointer; position: relative;'
// onclick='delmissionout()'>过期处理</div>"+
				'</div>'+
	  			'<table id="tab"   style="font-size:13px;" width="100%">'+
	               '<tbody>'+
	                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
// '<th>标志</th>'+
	                    '<th>任务撤销号</th>'+
	                    '<th>撤销时间</th>'+
	                    '<th>员工账号</th>'+
	                    '<th>员工名</th>'+
	                    '<th>任务内容</th>'+
	                    '<th>撤销原因</th>'+
// '<th>任务状态</th>'+
	                '</tr>'+
	                '</tbody>'+
	                
	                 
	                '<tbody  class="thisgroup">'+
	                	
	                '</tbody>'+
	                '</table>'+
	             
	                
	                
	  		'</div>');
		
// var id=$(x).find("th").eq(6).html();
	$.getJSON("./WGetAllMissionUndoServlet",function(outjson){
		for ( var i = 0; i < outjson.MissionList.length; i++) {
// var co="rgb(0,0,0)"
// var state="未接受";
// if(outjson.MissionList[i].MissionState==0){
// state="未接受";
// }else if(outjson.MissionList[i].MissionState==1){
// state="执行中";
// }else if(outjson.MissionList[i].MissionState==2){
// state="未审核";
// co="rgb(0,162,232)";
// }else if(outjson.MissionList[i].MissionState==3){
// state="已审核";
// }else if(outjson.MissionList[i].MissionState==4){
// state="已撤销";
// }else if(outjson.MissionList[i].MissionState==5){
// state="已过期";
// co="rgb(0,162,232)";
// }else if(outjson.MissionList[i].MissionState==6){
// state="已失败";
// }
// //
// var bind="none";
// if(outjson.MissionList[i].MissionBussinessBandState==1){
// bind="block";
// }

			
				for ( var j = 0; j < $(".group-one").length; j++) {
						$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;	background-color: rgb(232,251,250);">'+
// '<th style="font-weight: normal; width:60px; height:30px;"></img><img
// src="./pic/chuchaibangding.png" style="height:30px;width:30px;cursor:
// pointer;display: '+bind+';float:right" title="出差绑定"></img></th>'+
							    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionUndoId+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionUndoTime+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].EmployeeAccount+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].EmployeeName+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionContent+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionUndoReason+'</th>'+
// '<th style="font-weight: bold; color:'+co+';">'+state+'</th>'+
							    '<th  style="font-weight: normal; display: none;">'+outjson.MissionList[i].MissionBussinessBandState+'</th>'+
// '<th style="font-weight: normal; display:
// none;">'+outjson.VisitPlanList[i].VisitPlanCycleNumber+'</th>'+
// '<th style="font-weight: normal; display:
// none;">'+outjson.VisitPlanList[i].VisitPlanDays+'</th>'+
// '<th style="font-weight: normal; display:
// none;">'+outjson.VisitPlanList[i].VisitBussinessBandState+'</th>'+
// '<th style="font-weight: normal; display:
// none;">'+outjson.VisitPlanList[i].ClientId+'</th>'+
								'</tr>')
					}
				}
		
	});
	curtab=null;
}


function showmissionruning() {
// var x=usedetailtab;
	$(".task-list").html("");

 			$(".task-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
 		  			'<div class="group-title" ><font>任务记录</font>'+
 		  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
// "<div class=clientbut style=' cursor:pointer; position: relative;'
// onclick='undomission()' >撤销</div>"+
// "<div class=clientbut style=' cursor:pointer; position: relative;'
// onclick='seemission()'>查看</div>"+
// "<div class=clientbut style=' cursor:pointer; position: relative;'
// onclick='checkmission()'>审核</div>"+
// "<div class=clientbut style=' cursor:pointer; position: relative;'
// onclick='delmissionout()'>过期处理</div>"+
 					'</div>'+
 		  			'<table id="tab"   style="font-size:13px;" width="100%">'+
 		               '<tbody>'+
 		                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
 		                	'<th>标志</th>'+
 		                    '<th>任务号</th>'+
 		                    '<th>发布时间</th>'+
 		                    '<th>员工账号</th>'+
 		                    '<th>员工名</th>'+
 		                    '<th>任务内容</th>'+
 		                    '<th>任务期限</th>'+
 		                    '<th>任务状态</th>'+
 		                '</tr>'+
 		                '</tbody>'+
 		                
 		                 
 		                '<tbody  class="thisgroup">'+
 		                	
 		                '</tbody>'+
 		                '</table>'+
 		             
 		                
 		                
 		  		'</div>');
 			
// var id=$(x).find("th").eq(6).html();
 		$.getJSON("./WGetAllMissionRuningServlet",function(outjson){
 			for ( var i = 0; i < outjson.MissionList.length; i++) {
 				var co="rgb(0,0,0)"
 				var state="未接受";
 				if(outjson.MissionList[i].MissionState==0){
 					state="未接受";
 				}else if(outjson.MissionList[i].MissionState==1){
 					state="执行中";
 				}else if(outjson.MissionList[i].MissionState==2){
 					state="未审核";
 					co="rgb(0,162,232)";
 				}else if(outjson.MissionList[i].MissionState==3){
 					state="已审核";
 				}else if(outjson.MissionList[i].MissionState==4){
 					state="已撤销";
 				}else if(outjson.MissionList[i].MissionState==5){
 					state="已过期";
 					co="rgb(0,162,232)";
 				}else if(outjson.MissionList[i].MissionState==6){
 					state="已失败";
 				}
// //
 				var bind="none";
 				if(outjson.MissionList[i].MissionBussinessBandState==1){
 					bind="block";
 				}
 				
 				
 					for ( var j = 0; j < $(".group-one").length; j++) {
 							$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;	background-color: rgb(232,251,250);">'+
 									'<th  style="font-weight: normal; width:60px; height:30px;"></img><img  src="./pic/chuchaibangding.png" style="height:30px;width:30px;cursor: pointer;display: '+bind+';float:right" title="出差绑定"></img></th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionId+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionPubdate+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].EmployeeAccount+'</th>'+
								    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].EmployeeName+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionContent+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionDeadline+'</th>'+
 								    '<th  style="font-weight: bold; color:'+co+';">'+state+'</th>'+
 								    '<th  style="font-weight: normal; display: none;">'+outjson.MissionList[i].MissionBussinessBandState+'</th>'+
// '<th style="font-weight: normal; display:
// none;">'+outjson.VisitPlanList[i].VisitPlanCycleNumber+'</th>'+
// '<th style="font-weight: normal; display:
// none;">'+outjson.VisitPlanList[i].VisitPlanDays+'</th>'+
// '<th style="font-weight: normal; display:
// none;">'+outjson.VisitPlanList[i].VisitBussinessBandState+'</th>'+
// '<th style="font-weight: normal; display:
// none;">'+outjson.VisitPlanList[i].ClientId+'</th>'+
 									'</tr>')
 						}
 					}
 			
 		});
 		curtab=null;
}






function changeTrColorone(obj){    

	    var _table=obj.parentNode;

	    for (var i=0;i<_table.rows.length;i++){


	    }    
	    var x=69;
	    var y=205;
	    var z=195;
	    obj.style.backgroundColor=setColor(x, y, z);

	}


var curtab = null;
function do_onclick(tab){
	 if(curtab==tab){
		tab.style.backgroundColor =setColor(232,251,250);
		curtab = null;
	 }else{
		 var x=69;
		 var y=205;
  	     var z=195;
    	 tab.style.backgroundColor = setColor(x, y, z);
    	 
         if(curtab != null) curtab.style.backgroundColor = setColor(232,251,250);
         curtab = tab;
	 }
	
}

function do_blcclick(tab) {
	 tab.style.backgroundColor = setColor(255, 255, 255);
	 curtab=null;
}

function setColor(x,y,z){
		return "#"  + x.toString(16) + y.toString(16) + z.toString(16);
		}
function changeTrColortwo(obj) {
	  var _table=obj.parentNode;

    for (var i=0;i<_table.rows.length;i++){

        _table.rows[i].style.backgroundColor=setColor(232,251,250);

    }    
	var x=69;
    var y=205;
    var z=195;
    obj.style.backgroundColor=setColor(232,251,250);
  if(curtab != null) curtab.style.backgroundColor = setColor(x, y, z);
}










