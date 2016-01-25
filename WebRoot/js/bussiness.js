var addokemployeeinfoStr;

$(document).ready(function() {
	
	showmainbussinesstitle();
	showbussinessrunning();
	addokemployeeinfoStr="<option value ='0'>选择员工号</option>";
	$.getJSON("./WGetBussinessAddOkEmployeeInfoServlet",function(outjson){
		
		for(var i=0;i<outjson.EmployeeList.length;i++){
			if(outjson.EmployeeList[i].EmployeeName.length<3){
				outjson.EmployeeList[i].EmployeeName+="&nbsp&nbsp&nbsp";
			}
			addokemployeeinfoStr=addokemployeeinfoStr+"<option value ="+outjson.EmployeeList[i].EmployeeId+">账号:"+outjson.EmployeeList[i].EmployeeAccount+"&nbsp员工名:"+outjson.EmployeeList[i].EmployeeName+"</option>";
			
		}
 	
 	});
	
})

function showback() {
	var seleid=$("#selectbussinessID").val();
	if(seleid==1){
		showbussinessrunning();
	}else if(seleid==2){
		showbussinesswaitdeal();
	}else if(seleid==3){
		showbussinesscomplete();
	}else if(seleid==4){
		showbussinessundo();
	}else if(seleid==5){
		showbussinessbadrecord();
	}else if(seleid==6){
//		showvisitdelay();
	}
	showmainbussinesstitle();
	var op=parseInt(seleid);
	$("#selectbussinessID").find("option").eq(op-1).attr("selected","selected");
}

function showmainbussinesstitle() {
	$(".right-title").html("");
	$(".right-title").append(
			"<img onclick='	showmainbussinesstitle(),showbussinessrunning()' src='./pic/daohang.png'  title='出差管理导航' style=' width:30px;height:30px; cursor:pointer; margin-left:100px;margin-bottom:-64px; margin-top:7px;' />"+
			"<div id=missionhasnodeldivid style='display:none;font-family:Microsoft YaHei;cursor: pointer;position:relative;margin-left:180px; width:120px;height:50px; padding-top:10px;margin-bottom:-64px; margin-top:4px;'><font style='font-size:13px;color:rgb(255,255,255)'>您有未处理的任务</font><img id=clienttitlepoint src='./pic/redpoint.png' style='height:10px;width:10px;padding-bottom:3px; margin-top:-20px;;margin-left:109px;'><img/></div>"+
		 	
			"<div id=usermanagerserch class=zySearch> " +
 			"<input id=clientsearchameid class=search-input  placeholder='全员搜索或请输入员工名' ></input>"+
 			"<div class=search-btn  onclick='searchclient()'>搜索</div>"+
 			"</div>"+
 			"<div id=attenss style='height:25px;width:400px;float:right;margin-top:-15px; margin-left:700px;'>" +
// "<div class=usebut style=' cursor:pointer; position: relative;'
// onclick='updateclient()'>修改</div>"+
				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='seebussiness()'>查看</div>"+
				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='undobussiness()'>撤销</div>"+
				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='addbussiness()'>增加</div>"+
				"<div class=usebut  style=' cursor:pointer; position: relative; margin-left:50px;'> <select id=selectbussinessID onchange='showSelectBussiness()' class=useselect style='height:25px;'><option value='1'>需执行</option><option value='2'>待处理</option><option value='3'>已结束</option><option value='4'>已撤销</option><option value='5'>不良记录</option></select></div> "+
 			"</div>"
 		
	);
	
}

function showSelectBussiness() {
	var seleid=$("#selectbussinessID").val();
	if(seleid==1){
		showbussinessrunning();
	}else if(seleid==2){
		showbussinesswaitdeal();
	}else if(seleid==3){
		showbussinesscomplete();
	}else if(seleid==4){
		showbussinessundo();
	}else if(seleid==5){
		showbussinessbadrecord();
	}else if(seleid==6){
//		showvisitdelay();
	}
	showmainbussinesstitle();
	var op=parseInt(seleid);
	$("#selectbussinessID").find("option").eq(op-1).attr("selected","selected");
}


function checkbussiness() {
	var x=curtab;
	var bussienssid=$(x).find("th").eq(0).html();
	var state=$(x).find("th").eq(9).html();
	
	if(state!="未审核"){
		alert("该出差不需要审核");
 	}else{
 		$(".shadow").show();
 		$(".pagehead font").html("审核出差")
 		$(".showpage").css("width","280px");
 		$(".showpage").css("height","180px");
 		$(".showpage").css("margin-left","600px");
 		$(".pagemain").html("<div class=userset>"+
				"<div><button onclick='checkbussineOK(1)' class=but1 style='width:250px;margin-top:5px'>审核通过</button></div>"+
				"<div><button onclick='checkOKWithBadrecall()' class=but1 style='width:250px;margin-top:5px'>审核通过，但要添加出差不良记录</button></div>"+
				"<div><button onclick='checkbussineOK(2)' class=but1 style='width:250px;margin-top:5px'>审核不通过</button></div>"
				);
 	}
}

function checkOKWithBadrecall() {
	var x=curtab;
	var bussienssid=$(x).find("th").eq(0).html();
	var date=new Date();
	var strdate=date.format("yyyy/MM/dd");
//	$(".shadow").html("");
	$(".shadow").show();
		$(".pagehead font").html("出差不良记录")
		$(".showpage").css("width","280px");
		$(".showpage").css("height","180px");
		$(".showpage").css("margin-left","600px");
		$(".pagemain").html("<div class=usersetss>"+
				"<div><a>出差编号：</a><input type='text' value="+bussienssid+" ></div>"+
				"<div><a>记录时间：</a><input type='text' value="+strdate+"></div>"+
				"<div><a>记录原因：</a><input type='text' /></div>"+
				"<div style='margin-left: 30px;margin-top: 10px;'><button onclick='badrecallok()' class=but1>确定</button><button class=but1 onclick='closepage()'>取消</button></div>"
			);
}

function badrecallok() {
	var bussinessid=$(".usersetss").find("div").eq(0).find("input").val();
	var date=$(".usersetss").find("div").eq(1).find("input").val();
	var reson=$(".usersetss").find("div").eq(2).find("input").val();
	$.getJSON("./WCheckBussinessWithBadRecallServlet",{BussinessId:bussinessid,BussinessBadrecordReason:reson,BussinessBadrecordTime:date},function(outjson){
		if(	outjson.check){alert("成功！");closepage();
		showback();
			}else {
				alert("失败！");closepage();
			}
	
	})
}

function checkbussineOK(num) {
	var x=curtab;
	var bussienssid=$(x).find("th").eq(0).html();
	var busstate=0;
	if(num==1){
		busstate=3;
	}else if(num==2){
		busstate=4;
	}
	
	$.getJSON("./WCheckBussinessServlet",{BussinessId:bussienssid,BussinessState:busstate},function(outjson){
		if(outjson.check){
			alert("成功");
		}else {
		
			alert("失败");
			
		}
			showback();
			closepage();
	});
}



function undobussiness() {
	var x=curtab;
	var bussinessId=$(x).find("th").eq(0).html();
	var state=$(x).find("th").eq(9).html();
	if(x==null){
		alert("请选择需要操作的对象");
	}else if(state=="已撤销"){
		alert("该出差已撤销")
	}else{
		
		
		$.getJSON("./WCheckBussinessHavaActivityServlet",{BussinessId:bussinessId},function(outjson){
			if(	outjson.check){
				var date=new Date();
				var strdate=date.format("yyyy/MM/dd");
				$(".shadow").show();
				$(".pagehead font").html("撤销出差")
				$(".showpage").css("width","280px");
				$(".showpage").css("height","240px");
				$(".showpage").css("margin-left","600px");
				$(".pagemain").html("<div class=bussinessundo>"+
							"<div><a>出差编号：</a><input type='text' value="+bussinessId+" ></div>"+
							"<div><a>撤销时间：</a><input type='text' value="+strdate+"></div>"+
							"<div><a>撤销原因：</a><input type='text' /></div>"+
							"<div style='margin-top:10px;margin-left:10px;'>该出差包含出差活动！</div>"+
							"<div><button onclick='undobussinesswithundoactivity()' class=but1 style='width:250px;margin-top:5px'>确定撤销，并撤销出差活动</button></div>"+
							"<div><button onclick='undobussinessunbindactivity()' class=but1 style='width:250px;margin-top:5px'>确定撤销，只撤销出差并解绑出差活动</button></div>"+
							"<div><button onclick='closepage()' class=but1 style='width:250px;margin-top:5px'>取消撤销</button></div>"+
//							"<div style='margin-left: 30px;margin-top: 10px;'><button onclick='undobussinessok()' class=but1>确定</button><button class=but1 onclick='closepage()'>取消</button></div>"+
							"</div>");
				}else {
					var date=new Date();
					var strdate=date.format("yyyy/MM/dd");
					$(".shadow").show();
					$(".pagehead font").html("撤销出差")
					$(".showpage").css("width","280px");
					$(".showpage").css("height","160px");
					$(".showpage").css("margin-left","600px");
					$(".pagemain").html("<div class=bussinessundo>"+
								"<div><a>出差编号：</a><input type='text' value="+bussinessId+" ></div>"+
								"<div><a>撤销时间：</a><input type='text' value="+strdate+"></div>"+
								"<div><a>撤销原因：</a><input type='text' /></div>"+
								"<div style='margin-left: 30px;margin-top: 10px;'><button onclick='undobussinessok()' class=but1>确定</button><button class=but1 onclick='closepage()'>取消</button></div>"+
								"</div>");
				}
		
		})
		
	
	}
}

function undobussinessunbindactivity() {
	
	var bussinessid=$(".bussinessundo").find("div").eq(0).find("input").val();
	var date=$(".bussinessundo").find("div").eq(1).find("input").val();
	var reson=$(".bussinessundo").find("div").eq(2).find("input").val();
	$.getJSON("./WUndoBussinessUnbindActivityServlet",{BussinessId:bussinessid,BussinessUndoReason:reson,BussinessUndoTime:date},function(outjson){
		if(	outjson.check){alert("成功！");closepage();
			}else {
				alert("失败！");closepage();
			}
		showback();
	})
}

function undobussinesswithundoactivity() {
	var bussinessid=$(".bussinessundo").find("div").eq(0).find("input").val();
	var date=$(".bussinessundo").find("div").eq(1).find("input").val();
	var reson=$(".bussinessundo").find("div").eq(2).find("input").val();
	$.getJSON("./WUndoBussinessUndoActivityServlet",{BussinessId:bussinessid,BussinessUndoReason:reson,BussinessUndoTime:date},function(outjson){
		if(	outjson.check){alert("成功！");closepage();
			}else {
				alert("失败！");closepage();
			}
		showback();
	})
}
function undobussinessok( ) {
	var bussinessid=$(".bussinessundo").find("div").eq(0).find("input").val();
	var date=$(".bussinessundo").find("div").eq(1).find("input").val();
	var reson=$(".bussinessundo").find("div").eq(2).find("input").val();
	$.getJSON("./WUndoBussinessServlet",{BussinessId:bussinessid,BussinessUndoReason:reson,BussinessUndoTime:date},function(outjson){
		if(	outjson.check){alert("成功！");closepage();
			}else {
				alert("失败！");closepage();
			}
	showback();
	})
}





function seebussiness() {
	var x=curtab;
	var bussinessid=$(x).find("th").eq(0).html();
	var myname=$(x).find("th").eq(2).html();
	var bussinesssideaddress=$(x).find("th").eq(3).html();
	var bussinesscontent=$(x).find("th").eq(4).html();
	var bussinessregistertime=$(x).find("th").eq(5).html();
	var bussinessintime=$(x).find("th").eq(6).html();
	var bussinessouttime=$(x).find("th").eq(7).html();
	var bussinessreturntime=$(x).find("th").eq(8).html();
	var bussinessstete=$(x).find("th").eq(9).html();
	var bussinessinaddress=$(x).find("th").eq(10).html();
	var bussinessoutaddress=$(x).find("th").eq(11).html();
	
			$(".group-listbussiness").html("");
			$(".group-listbussiness").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
		  			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="showback()"></img><div style="margin-top:-25px;"><font>出差详情</font></div></div>'+
		  			'<div class="ground-one-left" style="float:left;width:50%;height:100%; 	">'+
	   				'<div style="position:relative; height:500px;margin-left:100px;">'+
	   				'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;margin-top:10px;">出差号</font></div>'+	
						'<div id=misconcluid class="main-input" style="margin-left:10px;width:289px;padding-top:7px;margin-top:10px;padding-top:10px;" >'+bussinessid+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">员工名</font></div>'+	
						'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+myname+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">出差目的地</font></div>'+	
						'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+bussinesssideaddress+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">出差内容</font></div>'+	
						'<textarea   class="main-input" style="font-size:15px;font-weight: bold;	margin-left:10px; width:309px;height:100px;margin-top:10px;padding-top:10px;background-color:rgb(248,254,254)">'+bussinesscontent+'</textarea>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">出差登记时间</font></div>'+	
						'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+bussinessregistertime+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">出差抵达地址</font></div>'+	
						'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+bussinessinaddress+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">出差抵达时间</font></div>'+	
						'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+bussinessintime+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">出差离开地址</font></div>'+	
						'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+bussinessoutaddress+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">出差离开时间</font></div>'+	
						'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+bussinessouttime+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">出差归来时间</font></div>'+	
						'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+bussinessreturntime+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">出差状态</font></div>'+	
						'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+bussinessstete+'</div>'+
						
					'</div>'+
	   			'</div>'+
	   			
	   			'<div class="ground-one-right" style="float:left;width:49%;height:1000px;overflow-x:hidden;border-left:2px;border-left-color:black;border-left-style:dashed;">'+
	   				'<div  style="position:relative; height:500px;">'+
	   				
	   				'<div class="group-title" style="margin-top:10px; margin-left:10px;" ><font>任务记录</font>'+
 		  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
// 	 	  			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='undomission()' >撤销</div>"+
// 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='seemission()'>查看</div>"+
// 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='checkmission()'>审核</div>"+
// 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='delmissionout()'>过期处理</div>"+
 					'</div>'+

 		  			'<table id="tab"   style="font-size:13px;" width="100%" >'+
 		               '<tbody  height="500px"; >'+
 		                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
// 		                	'<th>标志</th>'+
 		                    '<th>任务号</th>'+
 		                    '<th>发布时间</th>'+
// 		                    '<th>任务内容</th>'+
 		                    '<th>任务期限</th>'+
 		                    '<th>任务状态</th>'+
 		                '</tr>'+
 		                '</tbody>'+
 		                
 		                 
 		                '<tbody  class="thisgroup">'+
 		                	
 		                '</tbody>'+
 		                '</table>'+
 		             
	   				

 		           	'<div class="group-title" style="margin-top:50px;" ><font>拜访计划</font>'+
 		            		  			"<div id=butvisit style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
// 		            	 	  			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='undovisitplan()' >撤销</div>"+
// 		            	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='seevisitplan()'>查看</div>"+
// 		            	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='checkvisitplan()'>审核</div>"+
// 		            	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='delvisitout()'>过期处理</div>"+
 		            					'</div>'+
 		            		  			'<table id="tabs"   style="font-size:13px;" width="100%" >'+
 		            		               '<tbody >'+
 		            		                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
 		            		                	'<th>标志</th>'+
 		            		                    '<th>拜访计划号</th>'+
// 		            		                    '<th>发布时间</th>'+
// 		            		                    '<th>客户名</th>'+
 		            		                    '<th>开始时间</th>'+
 		            		                    '<th>截止时间</th>'+
 		            		                    '<th>拜访状态</th>'+
 		            		                '</tr>'+
 		            		                '</tbody>'+
 		            		                
 		            		                 
 		            		                '<tbody  class="vthisgroup">'+
 		            		                	
 		            		                '</tbody>'+
 		            		                '</table>'+
 		            		             
				'</div>'+
		  		'</div>');
			
//			
			$.getJSON("./WGetBussinessActivityServlet",{BussinessId:bussinessid},function(outjson){
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
//	 				
	 				var bind="none";
	 				if(outjson.MissionList[i].MissionBussinessBandState==1){
	 					bind="block";
	 				}
	 				
	 				
	 					for ( var j = 0; j < $(".group-one").length; j++) {
	 							$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;	background-color: rgb(232,251,250);">'+
	 									'<th  style="font-weight: normal;display: none; width:60px; height:30px;"></img><img  src="./pic/chuchaibangding.png" style="height:30px;width:30px;cursor: pointer;display: '+bind+';float:right" title="出差绑定"></img></th>'+
	 								    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionId+'</th>'+
	 								    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionPubdate+'</th>'+
	 								    '<th  style="font-weight: normal;display: none; ">'+outjson.MissionList[i].MissionContent+'</th>'+
	 								    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionDeadline+'</th>'+
	 								    '<th  style="font-weight: bold; color:'+co+';">'+state+'</th>'+
	 								    '<th  style="font-weight: normal; display: none;">'+outjson.MissionList[i].MissionBussinessBandState+'</th>'+
//	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanCycleNumber+'</th>'+
//	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanDays+'</th>'+
//	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitBussinessBandState+'</th>'+
//	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].ClientId+'</th>'+
	 									'</tr>')
	 						}
	 					}
				
				
				
				for ( var i = 0; i < outjson.VisitPlanList.length; i++) {
	 				var co="rgb(0,0,0)"
	 				var state="未开始";
	 				if(outjson.VisitPlanList[i].VisitPlanState==0){
	 					state="未开始";
	 				}else if(outjson.VisitPlanList[i].VisitPlanState==1){
	 					state="执行中";
	 				}else if(outjson.VisitPlanList[i].VisitPlanState==2){
	 					state="未审核";
	 					co="rgb(0,162,232)";
	 				}else if(outjson.VisitPlanList[i].VisitPlanState==3){
	 					state="已审核";
	 				}else if(outjson.VisitPlanList[i].VisitPlanState==4){
	 					state="已撤销";
	 				}else if(outjson.VisitPlanList[i].VisitPlanState==5){
	 					state="已过期";
	 					co="rgb(0,162,232)";
	 				}else if(outjson.VisitPlanList[i].VisitPlanState==6){
	 					state="已失败";
	 				}
	 				var cycle="none"
	 				if(outjson.VisitPlanList[i].VisitPlanCycle==1){
	 					cycle="block";
	 				}
	 				
	 				var bind="none";
	 				if(outjson.VisitPlanList[i].VisitBussinessBandState==1){
	 					bind="block";
	 				}
	 				
	 				
	 					for ( var j = 0; j < $(".group-one").length; j++) {
	 							$(".group-one").eq(j).find(".vthisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;	background-color: rgb(232,251,250);">'+
	 									'<th  style="font-weight: normal; width:60px; height:30px;"><img  src="./pic/xunhuan.png" style="height:30px;width:30px;cursor: pointer;display: '+cycle+'; float:left" title="拜访循环"></img><img  src="./pic/chuchaibangding.png" style="height:30px;width:30px;cursor: pointer;display: '+bind+';float:right" title="出差绑定"></img></th>'+
	 								    '<th  style="font-weight: normal; ">'+outjson.VisitPlanList[i].VisitPlanId+'</th>'+
	 								    '<th  style="font-weight: normal;display: none; ">'+outjson.VisitPlanList[i].VisitPlanPubdate+'</th>'+
	 								    '<th  style="font-weight: normal;display: none; ">'+outjson.VisitPlanList[i].ClientName+'</th>'+
	 								    '<th  style="font-weight: normal; ">'+outjson.VisitPlanList[i].VisitPlanStartTime+'</th>'+
	 								    '<th  style="font-weight: normal; ">'+outjson.VisitPlanList[i].VisitPlanEndTime+'</th>'+
	 								    '<th  style="font-weight: bold; color:'+co+';">'+state+'</th>'+
	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanCycle+'</th>'+
	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanCycleType+'</th>'+
	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanCycleNumber+'</th>'+
	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanDays+'</th>'+
	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitBussinessBandState+'</th>'+
	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].ClientId+'</th>'+
	 									'</tr>')
	 						}
	 					}
	 			
			})
			
//		})
	
	
}


function addbussiness() {
	$(".shadow").show();
	$(".pagehead font").html("添加出差")
	$(".showpage").css("width","350px");
	$(".showpage").css("height","180px");
	$(".showpage").css("margin-left","600px");
	$(".pagemain").html(
			'<div style="margin-top: 10px;">选择员工：<select id="movemember"></select></div>'+
			'<div>目的地址：<input style="width:244px" class="inadress" id="inadressid" type="text"/></div>'+
			'<div>出差内容：<input style="width:244px" class="inadress" id="inareaid" type="text"></input></div>'+
			'<div style="margin-left: 70px;margin-top: 10px;"><button class=but1 onclick="moveok()">确定</button><button class=but1 onclick="closepage()">取消</button></div>'
	);
	$("#movemember").append(addokemployeeinfoStr)
	
}

function moveok() {
	var id=$("#movemember").val();
//	alert(mumval);
	var address=$("#inadressid").val();
	var con=$("#inareaid").val();
	$.getJSON("./WAddBussinessServlet",{EmployeeId:id,BussinessSideAddress:address,BussinessContent:con},function(outjson){
		if(outjson.check){
			alert("成功！");
			
		}else {
			alert("失败！");
		}
		
		closepage();
		showback();
		addokemployeeinfoStr="<option value ='0'>选择员工号</option>";
		$.getJSON("./WGetBussinessAddOkEmployeeInfoServlet",function(outjson){
			
			for(var i=0;i<outjson.EmployeeList.length;i++){
				if(outjson.EmployeeList[i].EmployeeName.length<3){
					outjson.EmployeeList[i].EmployeeName+="&nbsp&nbsp&nbsp";
				}
				addokemployeeinfoStr=addokemployeeinfoStr+"<option value ="+outjson.EmployeeList[i].EmployeeId+">账号:"+outjson.EmployeeList[i].EmployeeAccount+"&nbsp员工名:"+outjson.EmployeeList[i].EmployeeName+"</option>";
				
			}
	 	
	 	});
	})
	
	
}

function showbussinesswaitdeal() {
	$(".group-listbussiness").html("");

	$(".group-listbussiness").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
  			'<div class="group-title" ><font>出差记录</font>'+
  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
//  			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='()' >撤销</div>"+
//			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='()'>查看</div>"+
			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='checkbussiness()'>审核</div>"+
			'</div>'+
  			'<table id="tab"   style="font-size:13px;" width="100%">'+
               '<tbody>'+
                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
                    '<th>出差号</th>'+
                    '<th>员工账号</th>'+
                    '<th>员工姓名</th>'+
                    '<th>出差目的地</th>'+
                    '<th>出差内容</th>'+
                    '<th>出差登记时间</th>'+
//                    '<th>出差抵达地址</th>'+
                    '<th>出差抵达时间</th>'+
//                    '<th>出差离开地址</th>'+
                    '<th>出差离开时间</th>'+
                    '<th>出差归来时间</th>'+
                    '<th>出差状态</th>'+
                '</tr>'+
               '</tbody>'+
                
                 
                '<tbody  class="thisgroup">'+
                	
                '</tbody>'+
                '</table>'+
             
                
                
  		'</div>');
	
//var id=$(x).find("th").eq(6).html();
$.getJSON("./WGetAllBussinessWaitDealServlet",function(outjson){
	for ( var i = 0; i < outjson.BussinessList.length; i++) {
		var co="rgb(0,0,0)"
		var state="未登记";
		if(outjson.BussinessList[i].BussinessState==0){
			state="未登记";
		}else if(outjson.BussinessList[i].BussinessState==1){
			state="执行中";
		}else if(outjson.BussinessList[i].BussinessState==2){
			state="未审核";
			co="rgb(0,162,232)";
		}else if(outjson.BussinessList[i].BussinessState==3){
			state="已通过";
		}else if(outjson.BussinessList[i].BussinessState==4){
			state="未通过";
		}else if(outjson.BussinessList[i].BussinessState==5){
			state="已撤销";
		}
//		
		
			for ( var j = 0; j < $(".group-one").length; j++) {
					$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;	background-color: rgb(232,251,250);">'+
//							'<th  style="font-weight: normal; width:60px; height:30px;"></img><img  src="./pic/chuchaibangding.png" style="height:30px;width:30px;cursor: pointer;display: '+bind+';float:right" title="出差绑定"></img></th>'+
						    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessId+'</th>'+
						    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].EmployeeAccount+'</th>'+
						    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].EmployeeName+'</th>'+
						    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessSideAddress+'</th>'+
						    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessContent+'</th>'+
						    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessRegisterTime+'</th>'+
						    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessInTime+'</th>'+
						   	'<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessOutTime+'</th>'+
						   	'<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessReturnTime+'</th>'+
						    '<th  style="font-weight: bold; color:'+co+';">'+state+'</th>'+
						    '<th  style="font-weight: normal;  display: none;">'+outjson.BussinessList[i].BussinessInAddress+'</th>'+
						    '<th  style="font-weight: normal;  display: none;">'+outjson.BussinessList[i].BussinessOutAddress+'</th>'+
						    '<th  style="font-weight: normal;  display: none;">'+outjson.BussinessList[i].EmployeeId+'</th>'+
//						    '<th  style="font-weight: normal; display: none;">'+outjson.BussinessList[i].MissionBussinessBandState+'</th>'+
//						    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanCycleNumber+'</th>'+
//						    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanDays+'</th>'+
//						    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitBussinessBandState+'</th>'+
//						    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].ClientId+'</th>'+
							'</tr>')
				}
			}
	
});
curtab=null;
}


function bianbussiness() {
	if(curtab==null){
		alert("请选择对象")
	}else{
		var x=curtab;
		var id=$(x).find("th").eq(12).html();
		$.getJSON("./WGetEmployeeMissionUnBindServlet",{EmployeeId:id},function(outjson){
			missionstr=null;
			for(var i=0;i<outjson.MissionList.length;i++){
				missionstr=missionstr+"<option value ="+outjson.MissionList[i].MissionId+">任务号:"+outjson.MissionList[i].MissionId+"&nbsp任务内容:"+outjson.MissionList[i].MissionContent+"</option>";
			}
	 	
	 	});
		
		$.getJSON("./WGetEmployeeVisitPlanUnBindServlet",{EmployeeId:id},function(outjson){
			visitplanstr=null;
			for(var i=0;i<outjson.VisitPlanList.length;i++){
				visitplanstr=visitplanstr+"<option value ="+outjson.VisitPlanList[i].VisitPlanId+">拜访计划号:"+outjson.VisitPlanList[i].VisitPlanId+"&nbsp发布时间:"+outjson.VisitPlanList[i].VisitPlanPubdate+"</option>";
			}
	 	
	 	});
		$(".shadow").show();
		$(".pagehead font").html("绑定出差活动")
		$(".showpage").css("width","350px");
		$(".showpage").css("height","90px");
		$(".showpage").css("margin-left","600px");
		$(".pagemain").html(
//				'<div style="margin-top: 10px;">选择员工：<select id="movemember"></select></div>'+
//				'<div>目的地址：<input style="width:244px" class="inadress" id="inadressid" type="text"/></div>'+
//				'<div>出差内容：<input style="width:244px" class="inadress" id="inareaid" type="text"></input></div>'+
				'<div style="margin-left: 70px;margin-top: 10px;"><button style="width:100px;" class=but1 onclick="bindmission()">绑定员工任务</button><button style="width:100px;" class=but1 onclick="bindvisitplan()">绑定员工拜访</button></div>'
		);
	}
	
	
	
//	$("#movemember").append(addokemployeeinfoStr)
	
}

function bindmission() {
	
	$(".shadow").show();
	$(".pagehead font").html("选择绑定任务")
	$(".showpage").css("width","350px");
	$(".showpage").css("height","150px");
	$(".showpage").css("margin-left","600px");
	$(".pagemain").html(
			'<div style="margin-top: 10px;">选择任务：<select id="missstr"></select></div>'+
//			'<div>目的地址：<input style="width:244px" class="inadress" id="inadressid" type="text"/></div>'+
//			'<div>出差内容：<input style="width:244px" class="inadress" id="inareaid" type="text"></input></div>'+
			'<div style="margin-left: 70px;margin-top: 10px;"><button class=but1 onclick="bindmissionok()">确定</button><button class=but1 onclick="closepage()">取消</button></div>'
	);
	
	$("#missstr").append(missionstr);
	missionstr=null;
}
function bindvisitplan(){
	$(".shadow").show();
	$(".pagehead font").html("选择绑定拜访计划")
	$(".showpage").css("width","350px");
	$(".showpage").css("height","150px");
	$(".showpage").css("margin-left","600px");
	$(".pagemain").html(
			'<div style="margin-top: 10px;">选择拜访计划：<select id="visitstr"></select></div>'+
//			'<div>目的地址：<input style="width:244px" class="inadress" id="inadressid" type="text"/></div>'+
//			'<div>出差内容：<input style="width:244px" class="inadress" id="inareaid" type="text"></input></div>'+
			'<div style="margin-left: 70px;margin-top: 10px;"><button class=but1 onclick="bindvisitplanok()">确定</button><button class=but1 onclick="closepage()">取消</button></div>'
	);
	
	$("#visitstr").append(visitplanstr);
	visitplanstr=null;
}

function bindmissionok() {
	
	var x=curtab;
	var busid=$(x).find("th").eq(0).html();
	var id=$("#missstr").val();
	$.getJSON("./WBindBussinessServlet",{BussinessObjectId:id,BussinessActivityType:0,BussinessId:busid},function(outjson){
		if(outjson.check){
			alert("成功！");
			
		}else {
			alert("失败！");
		}
		
		closepage();
	});
}


function bindvisitplanok() {
	var x=curtab;
	var busid=$(x).find("th").eq(0).html();
	var id=$("#visitstr").val();
	$.getJSON("./WBindBussinessServlet",{BussinessObjectId:id,BussinessActivityType:1,BussinessId:busid},function(outjson){
		if(outjson.check){
			alert("成功！");
			
		}else {
			alert("失败！");
		}
		
		closepage();
	});
}



function unbianbussiness() {
	if(curtab==null){
		alert("请选择对象")
	}else{
		var x=curtab;
		var id=$(x).find("th").eq(12).html();
		$.getJSON("./WGetEmployeeMissionBindServlet",{EmployeeId:id},function(outjson){
			missionstr=null;
			for(var i=0;i<outjson.MissionList.length;i++){
				missionstr=missionstr+"<option value ="+outjson.MissionList[i].MissionId+">任务号:"+outjson.MissionList[i].MissionId+"&nbsp任务内容:"+outjson.MissionList[i].MissionContent+"</option>";
			}
	 	
	 	});
		
		$.getJSON("./WGetEmployeeVisitPlanBindServlet",{EmployeeId:id},function(outjson){
			visitplanstr=null;
			for(var i=0;i<outjson.VisitPlanList.length;i++){
				visitplanstr=visitplanstr+"<option value ="+outjson.VisitPlanList[i].VisitPlanId+">拜访计划号:"+outjson.VisitPlanList[i].VisitPlanId+"&nbsp发布时间:"+outjson.VisitPlanList[i].VisitPlanPubdate+"</option>";
			}
	 	
	 	});
		$(".shadow").show();
		$(".pagehead font").html("解绑出差活动")
		$(".showpage").css("width","350px");
		$(".showpage").css("height","90px");
		$(".showpage").css("margin-left","600px");
		$(".pagemain").html(
//				'<div style="margin-top: 10px;">选择员工：<select id="movemember"></select></div>'+
//				'<div>目的地址：<input style="width:244px" class="inadress" id="inadressid" type="text"/></div>'+
//				'<div>出差内容：<input style="width:244px" class="inadress" id="inareaid" type="text"></input></div>'+
				'<div style="margin-left: 70px;margin-top: 10px;"><button style="width:100px;" class=but1 onclick="unbindmission()">解绑员工任务</button><button style="width:100px;" class=but1 onclick="unbindvisitplan()">解绑员工拜访</button></div>'
		);
	}
	
	
	
//	$("#movemember").append(addokemployeeinfoStr)
	
}

var missionstr;
function unbindmission() {
	
	$(".shadow").show();
	$(".pagehead font").html("选择解绑任务")
	$(".showpage").css("width","350px");
	$(".showpage").css("height","150px");
	$(".showpage").css("margin-left","600px");
	$(".pagemain").html(
			'<div style="margin-top: 10px;">选择任务：<select id="missstr"></select></div>'+
//			'<div>目的地址：<input style="width:244px" class="inadress" id="inadressid" type="text"/></div>'+
//			'<div>出差内容：<input style="width:244px" class="inadress" id="inareaid" type="text"></input></div>'+
			'<div style="margin-left: 70px;margin-top: 10px;"><button class=but1 onclick="unbindmissionok()">确定</button><button class=but1 onclick="closepage()">取消</button></div>'
	);
	
	$("#missstr").append(missionstr);
	missionstr=null;
}


var visitplanstr;
function unbindvisitplan(){
	$(".shadow").show();
	$(".pagehead font").html("选择解绑拜访计划")
	$(".showpage").css("width","350px");
	$(".showpage").css("height","150px");
	$(".showpage").css("margin-left","600px");
	$(".pagemain").html(
			'<div style="margin-top: 10px;">选择拜访计划：<select id="visitstr"></select></div>'+
//			'<div>目的地址：<input style="width:244px" class="inadress" id="inadressid" type="text"/></div>'+
//			'<div>出差内容：<input style="width:244px" class="inadress" id="inareaid" type="text"></input></div>'+
			'<div style="margin-left: 70px;margin-top: 10px;"><button class=but1 onclick="unbindvisitplanok()">确定</button><button class=but1 onclick="closepage()">取消</button></div>'
	);
	
	$("#visitstr").append(visitplanstr);
	visitplanstr=null;
}


function unbindmissionok() {
	var id=$("#missstr").val();
	$.getJSON("./WUnBindBussinessServlet",{BussinessObjectId:id,BussinessActivityType:0},function(outjson){
		if(outjson.check){
			alert("成功！");
			
		}else {
			alert("失败！");
		}
		
		closepage();
	});
}

function unbindvisitplanok() {
	var id=$("#visitstr").val();
	$.getJSON("./WUnBindBussinessServlet",{BussinessObjectId:id,BussinessActivityType:1},function(outjson){
		if(outjson.check){
			alert("成功！");
			
		}else {
			alert("失败！");
		}
		
		closepage();
	});
}


function showbussinessrunning() {
	$(".group-listbussiness").html("");

		$(".group-listbussiness").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
	  			'<div class="group-title" ><font>出差记录</font>'+
	  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
//	  			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='()' >撤销</div>"+
				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='unbianbussiness()'>解绑活动</div>"+
				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='bianbussiness()'>绑定活动</div>"+
				'</div>'+
	  			'<table id="tab"   style="font-size:13px;" width="100%">'+
	               '<tbody>'+
	                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
	                    '<th>出差号</th>'+
	                    '<th>员工账号</th>'+
	                    '<th>员工姓名</th>'+
	                    '<th>出差目的地</th>'+
	                    '<th>出差内容</th>'+
	                    '<th>出差登记时间</th>'+
//	                    '<th>出差抵达地址</th>'+
	                    '<th>出差抵达时间</th>'+
//	                    '<th>出差离开地址</th>'+
	                    '<th>出差离开时间</th>'+
	                    '<th>出差归来时间</th>'+
	                    '<th>出差状态</th>'+
	                '</tr>'+
	               '</tbody>'+
	                
	                 
	                '<tbody  class="thisgroup">'+
	                	
	                '</tbody>'+
	                '</table>'+
	             
	                
	                
	  		'</div>');
		
//	var id=$(x).find("th").eq(6).html();
	$.getJSON("./WGetAllBussinessRuningServlet",function(outjson){
		for ( var i = 0; i < outjson.BussinessList.length; i++) {
			var co="rgb(0,0,0)"
			var state="未登记";
			if(outjson.BussinessList[i].BussinessState==0){
				state="未登记";
			}else if(outjson.BussinessList[i].BussinessState==1){
				state="执行中";
			}else if(outjson.BussinessList[i].BussinessState==2){
				state="未审核";
				co="rgb(0,162,232)";
			}else if(outjson.BussinessList[i].BussinessState==3){
				state="已通过";
			}else if(outjson.BussinessList[i].BussinessState==4){
				state="未通过";
			}else if(outjson.BussinessList[i].BussinessState==5){
				state="已撤销";
			}
//			
			
				for ( var j = 0; j < $(".group-one").length; j++) {
						$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;	background-color: rgb(232,251,250);">'+
//								'<th  style="font-weight: normal; width:60px; height:30px;"></img><img  src="./pic/chuchaibangding.png" style="height:30px;width:30px;cursor: pointer;display: '+bind+';float:right" title="出差绑定"></img></th>'+
							    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessId+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].EmployeeAccount+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].EmployeeName+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessSideAddress+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessContent+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessRegisterTime+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessInTime+'</th>'+
							   	'<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessOutTime+'</th>'+
							   	'<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessReturnTime+'</th>'+
							    '<th  style="font-weight: bold; color:'+co+';">'+state+'</th>'+
							    '<th  style="font-weight: normal;  display: none;">'+outjson.BussinessList[i].BussinessInAddress+'</th>'+
							    '<th  style="font-weight: normal;  display: none;">'+outjson.BussinessList[i].BussinessOutAddress+'</th>'+
							    '<th  style="font-weight: normal;  display: none;">'+outjson.BussinessList[i].EmployeeId+'</th>'+
//							    '<th  style="font-weight: normal; display: none;">'+outjson.BussinessList[i].MissionBussinessBandState+'</th>'+
//							    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanCycleNumber+'</th>'+
//							    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanDays+'</th>'+
//							    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitBussinessBandState+'</th>'+
//							    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].ClientId+'</th>'+
								'</tr>')
					}
				}
		
	});
	curtab=null;
}










function showbussinesscomplete() {
	$(".group-listbussiness").html("");

		$(".group-listbussiness").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
	  			'<div class="group-title" ><font>出差记录</font>'+
	  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
//	  			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='()' >撤销</div>"+
//				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='()'>查看</div>"+
//				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='()'>审核</div>"+
				'</div>'+
	  			'<table id="tab"   style="font-size:13px;" width="100%">'+
	               '<tbody>'+
	                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
	                    '<th>出差号</th>'+
	                    '<th>员工账号</th>'+
	                    '<th>员工姓名</th>'+
	                    '<th>出差目的地</th>'+
	                    '<th>出差内容</th>'+
	                    '<th>出差登记时间</th>'+
//	                    '<th>出差抵达地址</th>'+
	                    '<th>出差抵达时间</th>'+
//	                    '<th>出差离开地址</th>'+
	                    '<th>出差离开时间</th>'+
	                    '<th>出差归来时间</th>'+
	                    '<th>出差状态</th>'+
	                '</tr>'+
	               '</tbody>'+
	                
	                 
	                '<tbody  class="thisgroup">'+
	                	
	                '</tbody>'+
	                '</table>'+
	             
	                
	                
	  		'</div>');
		
//	var id=$(x).find("th").eq(6).html();
	$.getJSON("./WGetAllBussinessCompleteServlet",function(outjson){
		for ( var i = 0; i < outjson.BussinessList.length; i++) {
			var co="rgb(0,0,0)"
			var state="未登记";
			if(outjson.BussinessList[i].BussinessState==0){
				state="未登记";
			}else if(outjson.BussinessList[i].BussinessState==1){
				state="执行中";
			}else if(outjson.BussinessList[i].BussinessState==2){
				state="未审核";
				co="rgb(0,162,232)";
			}else if(outjson.BussinessList[i].BussinessState==3){
				state="已通过";
			}else if(outjson.BussinessList[i].BussinessState==4){
				state="未通过";
			}else if(outjson.BussinessList[i].BussinessState==5){
				state="已撤销";
			}
//			
			
				for ( var j = 0; j < $(".group-one").length; j++) {
						$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;	background-color: rgb(232,251,250);">'+
//								'<th  style="font-weight: normal; width:60px; height:30px;"></img><img  src="./pic/chuchaibangding.png" style="height:30px;width:30px;cursor: pointer;display: '+bind+';float:right" title="出差绑定"></img></th>'+
							    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessId+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].EmployeeAccount+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].EmployeeName+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessSideAddress+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessContent+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessRegisterTime+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessInTime+'</th>'+
							   	'<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessOutTime+'</th>'+
							   	'<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessReturnTime+'</th>'+
							    '<th  style="font-weight: bold; color:'+co+';">'+state+'</th>'+
							    '<th  style="font-weight: normal;  display: none;">'+outjson.BussinessList[i].BussinessInAddress+'</th>'+
							    '<th  style="font-weight: normal;  display: none;">'+outjson.BussinessList[i].BussinessOutAddress+'</th>'+
							    '<th  style="font-weight: normal;  display: none;">'+outjson.BussinessList[i].EmployeeId+'</th>'+
//							    '<th  style="font-weight: normal; display: none;">'+outjson.BussinessList[i].MissionBussinessBandState+'</th>'+
//							    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanCycleNumber+'</th>'+
//							    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanDays+'</th>'+
//							    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitBussinessBandState+'</th>'+
//							    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].ClientId+'</th>'+
								'</tr>')
					}
				}
		
	});
	curtab=null;
}


function showbussinessundo() {
	$(".group-listbussiness").html("");

	$(".group-listbussiness").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
  			'<div class="group-title" ><font>出差撤销记录</font>'+
  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
//  			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='()' >撤销</div>"+
//			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='()'>查看</div>"+
//			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='()'>审核</div>"+
			'</div>'+
  			'<table id="tab"   style="font-size:13px;" width="100%">'+
               '<tbody>'+
                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
                    '<th>出差撤销号</th>'+
                    '<th>出差撤销时间</th>'+
                    '<th>出差号</th>'+
                    '<th>员工账号</th>'+
                    '<th>员工姓名</th>'+
                    '<th>出差撤销原因</th>'+
                '</tr>'+
               '</tbody>'+
                
                 
                '<tbody  class="thisgroup">'+
                	
                '</tbody>'+
                '</table>'+
             
                
                
  		'</div>');
	
//var id=$(x).find("th").eq(6).html();
$.getJSON("./WGetAllBussinessUndoServlet",function(outjson){
	for ( var i = 0; i < outjson.BussinessUndoList.length; i++) {
			for ( var j = 0; j < $(".group-one").length; j++) {
					$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;	background-color: rgb(232,251,250);">'+
//							'<th  style="font-weight: normal; width:60px; height:30px;"></img><img  src="./pic/chuchaibangding.png" style="height:30px;width:30px;cursor: pointer;display: '+bind+';float:right" title="出差绑定"></img></th>'+
						    '<th  style="font-weight: normal; ">'+outjson.BussinessUndoList[i].BussinessUndoId+'</th>'+
						    '<th  style="font-weight: normal; ">'+outjson.BussinessUndoList[i].BussinessUndoTime+'</th>'+
						    '<th  style="font-weight: normal; ">'+outjson.BussinessUndoList[i].BussinessId+'</th>'+
						    '<th  style="font-weight: normal; ">'+outjson.BussinessUndoList[i].EmployeeAccount+'</th>'+
						    '<th  style="font-weight: normal; ">'+outjson.BussinessUndoList[i].EmployeeName+'</th>'+
						    '<th  style="font-weight: normal; ">'+outjson.BussinessUndoList[i].BussinessUndoReason+'</th>'+
						    '<th  style="font-weight: normal;  display: none;">'+outjson.BussinessUndoList[i].EmployeeId+'</th>'+
//						    '<th  style="font-weight: normal; display: none;">'+outjson.BussinessList[i].MissionBussinessBandState+'</th>'+
//						    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanCycleNumber+'</th>'+
//						    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanDays+'</th>'+
//						    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitBussinessBandState+'</th>'+
//						    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].ClientId+'</th>'+
							'</tr>')
				}
			}
	
});
curtab=null;
}


function showbussinessbadrecord() {
	$(".group-listbussiness").html("");

	$(".group-listbussiness").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
  			'<div class="group-title" ><font>出差不良记录</font>'+
  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
//  			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='()' >撤销</div>"+
//			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='()'>查看</div>"+
//			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='()'>审核</div>"+
			'</div>'+
  			'<table id="tab"   style="font-size:13px;" width="100%">'+
               '<tbody>'+
                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
                    '<th>出差不良记录号</th>'+
                    '<th>出差不良记录时间</th>'+
                    '<th>出差号</th>'+
                    '<th>员工账号</th>'+
                    '<th>员工姓名</th>'+
                    '<th>出差不良记录原因</th>'+
                '</tr>'+
               '</tbody>'+
                
                 
                '<tbody  class="thisgroup">'+
                	
                '</tbody>'+
                '</table>'+
             
                
                
  		'</div>');
	
//var id=$(x).find("th").eq(6).html();
$.getJSON("./WGetAllBussinessBadInfoServlet",function(outjson){
	for ( var i = 0; i < outjson.BussinessBadrecordList.length; i++) {
			for ( var j = 0; j < $(".group-one").length; j++) {
					$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;	background-color: rgb(232,251,250);">'+
//							'<th  style="font-weight: normal; width:60px; height:30px;"></img><img  src="./pic/chuchaibangding.png" style="height:30px;width:30px;cursor: pointer;display: '+bind+';float:right" title="出差绑定"></img></th>'+
						    '<th  style="font-weight: normal; ">'+outjson.BussinessBadrecordList[i].BussinessBadrecordId+'</th>'+
						    '<th  style="font-weight: normal; ">'+outjson.BussinessBadrecordList[i].BussinessBadrecordTime+'</th>'+
						    '<th  style="font-weight: normal; ">'+outjson.BussinessBadrecordList[i].BussinessId+'</th>'+
						    '<th  style="font-weight: normal; ">'+outjson.BussinessBadrecordList[i].EmployeeAccount+'</th>'+
						    '<th  style="font-weight: normal; ">'+outjson.BussinessBadrecordList[i].EmployeeName+'</th>'+
						    '<th  style="font-weight: normal; ">'+outjson.BussinessBadrecordList[i].BussinessBadrecordReason+'</th>'+
						    '<th  style="font-weight: normal;  display: none;">'+outjson.BussinessBadrecordList[i].EmployeeId+'</th>'+
//						    '<th  style="font-weight: normal; display: none;">'+outjson.BussinessList[i].MissionBussinessBandState+'</th>'+
//						    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanCycleNumber+'</th>'+
//						    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanDays+'</th>'+
//						    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitBussinessBandState+'</th>'+
//						    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].ClientId+'</th>'+
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


