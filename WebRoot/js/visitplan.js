var strclient;
$(document).ready(function() {
	showvisitplantitle();
	showvisitplanrunning();
	
	str="<option value ='0'>选择员工号</option>";
	$.getJSON("./WGetClientStatisticalNumberServlet",function(outjson){
		
		for(var i=0;i<outjson.EmployeeList.length;i++){
			if(outjson.EmployeeList[i].EmployeeName.length<3){
				outjson.EmployeeList[i].EmployeeName+="&nbsp&nbsp&nbsp";
			}
			str=str+"<option value ="+outjson.EmployeeList[i].EmployeeId+">账号:"+outjson.EmployeeList[i].EmployeeAccount+"&nbsp员工名:"+outjson.EmployeeList[i].EmployeeName+"&nbsp分配客户:"+outjson.EmployeeList[i].ClientNumber+"</option>";
			
		}
 	
 	});
	
	
	strclient="<option value ='0'>选择客户号</option>";
	$.getJSON("./WGetClientUndistriInfoServlet",function(outjson){
		
		for(var i=0;i<outjson.ClientList.length;i++){
//			if(outjson.EmployeeList[i].EmployeeName.length<3){
//				outjson.EmployeeList[i].EmployeeName+="&nbsp&nbsp&nbsp";
//			}
			strclient=strclient+"<option value ="+outjson.ClientList[i].ClientId+","+outjson.ClientList[i].ClientName+","+outjson.ClientList[i].ClientCompany+">客户号:"+outjson.ClientList[i].ClientId+"&nbsp客户名:"+outjson.ClientList[i].ClientName+"&nbsp公司:"+outjson.ClientList[i].ClientCompany+"</option>";
			
		}
 	
 	});
	
	visitplandiandian();
})


function visitplandiandian() {
	var visitplancount=0;
	if(hasvisitnocheck){
		$("#visithasnodeldivid").css("display","block");
	}else {
		$("#visithasnodeldivid").css("display","none");
	}
	if(visitplancount!=100000){
		setTimeout(visitplandiandian, 5000);
	}
}



function showvisitplantitle() {
	$(".right-title").html("");
	$(".right-title").append(
			"<img onclick='	showvisitplantitle(),showvisitplanrunning()' src='./pic/daohang.png'  title='拜访管理导航' style=' width:30px;height:30px; cursor:pointer; margin-left:100px;margin-bottom:-64px; margin-top:7px;' />"+
			"<div id=visithasnodeldivid style='display:none;font-family:Microsoft YaHei;cursor: pointer;position:relative;margin-left:180px; width:120px;height:50px; padding-top:10px;margin-bottom:-64px; margin-top:4px;'><font style='font-size:13px;color:rgb(255,255,255)'>您有未处理的拜访</font><img id=clienttitlepoint src='./pic/redpoint.png' style='height:10px;width:10px;padding-bottom:3px; margin-top:-20px;;margin-left:109px;'><img/></div>"+
		 	
			"<div id=usermanagerserch class=zySearch> " +
 			"<input id=clientsearchameid class=search-input  placeholder='全员搜索或请输入员工名' ></input>"+
 			"<div class=search-btn  onclick='()'>搜索</div>"+
 			"</div>"+
 			"<div id=attenss style='height:25px;width:400px;float:right;margin-top:-15px; margin-left:700px;'>" +
// 			"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='updateclient()'>修改</div>"+
				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='seevisitplan()'>查看</div>"+
				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='undovisitplan()'>撤销</div>"+
				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='addvisitplan()'>增加</div>"+
				"<div class=usebut  style=' cursor:pointer; position: relative; margin-left:50px;'> <select id=selectvisitID onchange='showSelectVisit()' class=useselect style='height:25px;'><option value='1'>需执行</option><option value='2'>待处理</option><option value='3'>已结束</option><option value='4'>已撤销</option><option value='5'>拜访总结</option><option value='6'>延期记录</option></select></div> "+
 			"</div>"
 		
	);
}


function showSelectVisit() {
	var seleid=$("#selectvisitID").val();
	
	if(seleid==1){
		showvisitplanrunning();
	}else if(seleid==2){
		showvisitplanwaitdeal();
	}else if(seleid==3){
		showvisitplancomplete();
	}else if(seleid==4){
		showvisitplanundo();
	}else if(seleid==5){
		showvisitconclusion();
	}else if(seleid==6){
		showvisitdelay();
	}

	var op=parseInt(seleid);
	$("#selectvisitID").find("option").eq(op-1).attr("selected","selected");

}

function showback() {

	var seleid=$("#selectvisitID").val();
	if(seleid==1){
		showvisitplanrunning();
	}else if(seleid==2){
		showvisitplanwaitdeal();
	}else if(seleid==3){
		showvisitplancomplete();
	}else if(seleid==4){
		showvisitplanundo();
	}else if(seleid==5){
		showvisitconclusion();
	}else if(seleid==6){
		showvisitdelay();
	}
	showvisitplantitle();
	var op=parseInt(seleid);
	$("#selectvisitID").find("option").eq(op-1).attr("selected","selected");


}


function addvisitplan() {


	var x=curtab;
	$(".shadow").show();
	$(".pagehead font").html("增加拜访计划")
	$(".showpage").css("width","280px");
	$(".showpage").css("height","250px");
	$(".showpage").css("margin-left","600px");
	$(".pagemain").html("<div class=userset>"+
				"<div><a>客户号:</a><select id=visclentid style='margin-left: 29px; width:160px;' onchange='showclientselectinfo()'></select></div>"+
  				"<div><a>客户名:</a><input id=dicclientnameid disabled='disabled' type='text' style='margin-left: 29px;'/></div>"+
  				"<div><a>客户公司：</a><input id=disclientcompanyid disabled='disabled' type='text'/></div>"+
  				"<div><a>员工号:</a><select id=disclentid style='margin-left: 29px; width:160px;'></select></div>"+
  				"<div style='margin-left: 30px;margin-top: 10px;'><button onclick='disbutclientOK()' class=but1>确定</button><button class=but1 onclick='closepage()'>取消</button></div>"+
  				"</div>");
	
	$("#disclentid").append(str);
	$("#visclentid").append(strclient);
	
}

function showclientselectinfo() {
	var selestr=$("#visclentid").val();
	var arr = new Array();
	arr = selestr.split(",");
	document.getElementById("dicclientnameid").value=arr[1];
	document.getElementById("disclientcompanyid").value=arr[2];
	
	
}

function disbutclientOK() {
	var  clientid=$("#visclentid").val();
	var clentname=$("#dicclientnameid").val();
	var employeeid=$("#disclentid").val();
	var date=new Date();
	var pubdate=date.format("yyyy/MM/dd");
	if(employeeid==0){
		alert("请选择员工号");
	}else {
		closepage();
		$(".group-listvisitplan").html("");

		$(".group-listvisitplan").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
	  			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="showback()"></img><div style="margin-top:-25px;"><font>拜访计划详情</font></div></div>'+
	  			'<div class="ground-one-left" style="float:left;width:50%;height:100%; 	">'+
   				'<div style="position:relative; height:500px;margin-left:100px;">'+
//   				'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;margin-top:10px;">员工帐号</font></div>'+	
//					'<div class="main-input" style="margin-left:10px;width:289px;padding-top:7px;margin-top:10px;padding-top:10px;" >'+visitplanid+'</div>'+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户名</font></div>'+	
					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+clentname+'</div>'+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">发布时间</font></div>'+	
					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;" >'+pubdate+'</div>'+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">开始时间</font></div>'+	
					"<input id=visitstartid  onclick=SelectDate(this,\'yyyy/MM/dd\') class='main-input' style='margin-left:10px; width:309px;margin-top:10px;padding-top:-10px;height:50px;padding-top:-10px; font-size:18px;' placeholder='请输入开始日期' ></input>"+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">截止时间</font></div>'+	
					"<input id=visitendid onclick=SelectDate(this,\'yyyy/MM/dd\') class='main-input' style='margin-left:10px; width:309px;margin-top:10px;padding-top:-10px;height:50px;padding-top:-10px; font-size:18px;' placeholder='请输入截止日期' ></input>"+
//					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">拜访状态</font></div>'+	
//					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+state+'</div>'+
//					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">出差绑定号</font></div>'+	
//					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+bbandstate+'</div>'+
					
					'<div style="margin-left:53px; margin-top:50px;"><div class="usebut" onclick="disclietvisitOK()" style="font-size: 14px;width: 76px;height: 35px;line-height: 33px;">提交</div><div class="usebut" style="font-size: 14px;width: 76px;height: 35px;line-height: 33px;margin-left:50px;" onclick="updateclientclean()">取消</div></div>'+
				'</div>'+
   			'</div>'+
   			
   			'<div class="ground-one-right" style="float:left;width:49%;height:100%;border-left:2px;border-left-color:black;border-left-style:dashed;">'+
   			'<div style="position:relative; height:500px;margin-left:100px;">'+
   				'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">循环拜访</font></div>'+	
   				'<div id=cyclebox style="margin-top:10px;width:289px;height:50px;">'+
   					'<div id="cycleboxone" class="visiitbox" style="margin-right:10px;" onclick="iscycle(1)">循环</div>'+
   					'<div id="cycleboxtwo" class="visiitbox" onclick="iscycle(0)">不循环</div>'+
   				'</div>'+
   				
   				'<div style="display: block;">'+
   					'<div style="margin-top:50px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">循环类型</font></div>'+	
   					'<div style="height:30px; width:300px;margin-top:10px;">'+
   						'<div id=cycletypebox  style="margin-top:10px;width:389px;height:50px;">'+
   							'<div class="visiitbox" onclick="iscycletype(0)" style="margin-right:10px;">每日</div>'+
   							'<div class="visiitbox" onclick="iscycletype(1)" style="margin-right:10px;">每周</div>'+
   							'<div class="visiitbox" onclick="iscycletype(2)" style="margin-right:10px;">每月</div>'+
   							'<div class="visiitbox" onclick="iscycletype(3)" style="margin-right:10px;">自定义</div>'+
   						'</div>'+
   					'</div>'+
   					'<div style="margin-top:50px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">循环点</font></div>'+	
   					"<div id=visitdian style='margin-top:10px;width:300px;height:50px;'><select id=visitplanse class=useselect  style='cursor:pointer; position: relative;margin-left:4px;width:240px;height:50px;font-size: 15px;'></select></div>"+
   					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">限定天数</font></div>'+	
   					"<input id=visitponitid class='main-input' style='margin-left:10px; width:309px;margin-top:10px;padding-top:-10px;height:50px;padding-top:-10px; font-size:18px;' placeholder='请输入限定天数' ></input>"+
				'</div>'+
   			'</div>'+
	  		'</div>');
	
		
		

		
	}
}


var cycle=1;
function iscycle(num) {
	
	if(num==1){
		$("#cycleboxone").attr("class","visiitboxtwo");
		$("#cycleboxtwo").attr("class","visiitbox");
	}else if(num==0){
		$("#cycleboxone").attr("class","visiitbox");
		$("#cycleboxtwo").attr("class","visiitboxtwo");
		for(var i=0;i<4;i++){
			$("#cycletypebox").find("div").eq(i).attr("class","visiitbox");
		}
		$("#visitdian").html("");
		$("#visitdian").append("<select id=visitplanse class=useselect  style='cursor:pointer; position: relative;margin-left:4px;width:240px;height:50px;font-size: 15px;'></select>");
	}
	cycle=num;
	
}
var cycletype=0;
function iscycletype(num) {
	if(cycle==0){
		alert("请选择循环拜访为循环")
	}else if(cycle==1){
		for(var i=0;i<4;i++){
			$("#cycletypebox").find("div").eq(i).attr("class","visiitbox");
		}
		$("#cycletypebox").find("div").eq(num).attr("class","visiitboxtwo");
		
		cycletype=num;
		if(cycletype==0){
			$("#visitdian").html("");
			$("#visitdian").append("<select id=visitplanse class=useselect  style='cursor:pointer; position: relative;margin-left:4px;width:240px;height:50px;font-size: 15px;'></select>");
			$("#visitplanse").html("");
			$("#visitplanse").append("<option  value ='1'>1</option>");
		}else if(cycletype==1){
			$("#visitdian").html("");
			$("#visitdian").append("<select id=visitplanse class=useselect  style='cursor:pointer; position: relative;margin-left:4px;width:240px;height:50px;font-size: 15px;'></select>");
			$("#visitplanse").html("");
			$("#visitplanse").append("<option  value ='1'>1</option><option  value ='2'>2</option><option  value ='3'>3</option><option  value ='4'>4</option><option  value ='5'>5</option><option  value ='6'>6</option><option  value ='7'>7</option>");
			$("#visitplanse").find("option").eq(plandays-1).attr("selected","selected");
		}else if(cycletype==2){
			var str;
			for(var i=1;i<=31;i++){
				str+="<option  value ='"+i+"'>"+i+"</option>";
			}
			$("#visitdian").html("");
			$("#visitdian").append("<select id=visitplanse class=useselect  style='cursor:pointer; position: relative;margin-left:4px;width:240px;height:50px;font-size: 15px;'></select>");
			$("#visitplanse").html("");
			$("#visitplanse").append(str);
			$("#visitplanse").find("option").eq(plandays-1).attr("selected","selected");
		}else if(cycletype==3){
			$("#visitdian").html("");
			$("#visitdian").append(	"<input  class='main-input' style='margin-left:10px; width:309px;margin-top:10px;padding-top:-10px;height:50px;padding-top:-10px; font-size:18px;' placeholder='请输入限定天数' ></input>");
		}
		
		if(cycletype==0){
			$("#visitponitid").val(1);
		}
		
	}
}


function disclietvisitOK() {
	var  clientstr=$("#visclentid").val();
	var arr = new Array();
	arr = clientstr.split(",");
	clientid=arr[0];
	var employeeid=$("#disclentid").val();
	var date=new Date();
	var pubdate=date.format("yyyy/MM/dd");
//	cycle
//	cycletype
	var starttime=$("#visitstartid").val();
	var endtime=$("#visitendid").val();
	
	
	var cyclepoint;
	var playdays;
	
	if(cycle==1){
		cyclepoint=$("#visitplanse").val();
		playdays=$("#visitponitid").val();
	}else if(cycle==0){
		cyclepoint=0;
		playdays=0;
	}
	
	
	
//	alert(cycle+"---"+cycletype+"---"+cyclepoint+"---"+playdays);
	$.getJSON("./WDistributionClientServlet",{EmployeeId:employeeid,
		ClientId:clientid,VisitPlanPubdate:pubdate,VisitPlanStartTime:starttime,VisitPlanEndTime:endtime,
		VisitPlanState:0,VisitPlanCycle:cycle,VisitPlanCycleType:cycletype,VisitPlanCycleNumber:cyclepoint,VisitPlanDays:playdays},function(outjson){
 		if(outjson.check){
 			alert("成功！");
 			strclient="<option value ='0'>选择客户号</option>";
 			$.getJSON("./WGetClientUndistriInfoServlet",function(outjson){
 				
 				for(var i=0;i<outjson.ClientList.length;i++){
// 					if(outjson.EmployeeList[i].EmployeeName.length<3){
// 						outjson.EmployeeList[i].EmployeeName+="&nbsp&nbsp&nbsp";
// 					}
 					strclient=strclient+"<option value ="+outjson.ClientList[i].ClientId+","+outjson.ClientList[i].ClientName+","+outjson.ClientList[i].ClientCompany+">客户号:"+outjson.ClientList[i].ClientId+"&nbsp客户名:"+outjson.ClientList[i].ClientName+"&nbsp公司:"+outjson.ClientList[i].ClientCompany+"</option>";
 					
 				}
 		 	
 		 	});
 			
 			
 			
 		}else {
 			alert("失败！");
 		}
// 		var seleed=$("#selectID").val();
// 		if(seleed==1){
//// 			showmainclienttitle();
// 			shownodistribution();
// 		}else if(seleed==2){
// 			showdistribution();
// 		}
 		showback();
// 		var op=parseInt(seleed);
// 		$("#selectID").find("option").eq(op-1).attr("selected","selected");
 		
 	})
}

function undovisitplan() {
	
	
	var x=curtab;
	if(x==null){
		alert("请选择需要操作的对象");
	}else{
		var visitplanid=$(x).find("th").eq(1).html();
		var state=$(x).find("th").eq(8).html();
		var date=new Date();
		var strdate=date.format("yyyy/MM/dd");
		$(".shadow").show();
		$(".pagehead font").html("撤销拜访计划")
		$(".showpage").css("width","280px");
		$(".showpage").css("height","160px");
		$(".showpage").css("margin-left","600px");
		$(".pagemain").html("<div class=visitundo>"+
					"<div><a>拜访计划：</a><input type='text' value="+visitplanid+" ></div>"+
					"<div><a>撤销时间：</a><input type='text' value="+strdate+"></div>"+
					"<div><a>撤销原因：</a><input type='text' /></div>"+
					"<div style='margin-left: 30px;margin-top: 10px;'><button onclick='undovisitplanok()' class=but1>确定</button><button class=but1 onclick='closepage()'>取消</button></div>"+
					"</div>");

	}
	
	
	
}

function undovisitplanok( ) {
	var visitplanid=$(".visitundo").find("div").eq(0).find("input").val();
	var date=$(".visitundo").find("div").eq(1).find("input").val();
	var reson=$(".visitundo").find("div").eq(2).find("input").val();
	$.getJSON("./WUndoVisitPlanServlet",{VisitPlanId:visitplanid,VisitUndoReason:reson,VisitUndoTime:date},function(outjson){
		if(	outjson.check){alert("成功！");closepage();
			}else {
				alert("失败！");closepage();
			}
	
	})
	
	showback();
}

function seevisitplan() {
	var x=curtab;
	var visitplanid=$(x).find("th").eq(1).html();
	var pubdate=$(x).find("th").eq(2).html();
	var clientname=$(x).find("th").eq(5).html();
	var starttime=$(x).find("th").eq(6).html();
	var endtime=$(x).find("th").eq(7).html();
	var state=$(x).find("th").eq(8).html();
	var cycle=$(x).find("th").eq(9).html();
	var cycletype=$(x).find("th").eq(10).html();
	var cyclenumber=$(x).find("th").eq(9).html();
	var plandays=$(x).find("th").eq(11).html();
	var bbandstate=$(x).find("th").eq(12).html();
	var cliendid=$(x).find("th").eq(13).html();
	
	
	var cycleonechocce="visiitbox";
	var cycletwochocce="visiitbox";
	if(cycle==0){
		cycletwochocce="visiitboxtwo";
	}else if(cycle==1){
		cycleonechocce="visiitboxtwo";
	}
	
	
	if(x==null){
		alert("请选择需要操作的对象");
	}else{
	$(".group-listvisitplan").html("");

		$(".group-listvisitplan").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
	  			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="showback()"></img><div style="margin-top:-25px;"><font>拜访计划详情</font></div></div>'+
	  			'<div class="ground-one-left" style="float:left;width:50%;height:100%; 	">'+
   				'<div style="position:relative; height:500px;margin-left:100px;">'+
   				'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;margin-top:10px;">拜访计划号</font></div>'+	
					'<div class="main-input" style="margin-left:10px;width:289px;padding-top:7px;margin-top:10px;padding-top:10px;" >'+visitplanid+'</div>'+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户名</font></div>'+	
					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+clientname+'</div>'+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">发布时间</font></div>'+	
					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+pubdate+'</div>'+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">开始时间</font></div>'+	
					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+starttime+'</div>'+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">截止时间</font></div>'+	
					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+endtime+'</div>'+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">拜访状态</font></div>'+	
					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+state+'</div>'+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">出差绑定号</font></div>'+	
					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+bbandstate+'</div>'+
					
					
				'</div>'+
   			'</div>'+
   			
   			'<div class="ground-one-right" style="float:left;width:49%;height:100%;border-left:2px;border-left-color:black;border-left-style:dashed;">'+
   			'<div style="position:relative; height:500px;margin-left:100px;">'+
   				'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">循环拜访</font></div>'+	
   				'<div style="margin-top:10px;width:289px;height:50px;">'+
   					'<div class="'+cycleonechocce+'" style="margin-right:10px;">循环</div>'+
   					'<div class="'+cycletwochocce+'">不循环</div>'+
   				'</div>'+
   				
   				'<div style="display: block;">'+
   					'<div style="margin-top:50px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">循环类型</font></div>'+	
   					'<div style="height:30px; width:300px;margin-top:10px;">'+
   						'<div id=cycletypebox  style="margin-top:10px;width:389px;height:50px;">'+
   							'<div class="visiitbox"  style="margin-right:10px;">每日</div>'+
   							'<div class="visiitbox"  style="margin-right:10px;">每周</div>'+
   							'<div class="visiitbox"  style="margin-right:10px;">每月</div>'+
   							'<div class="visiitbox"  style="margin-right:10px;">自定义</div>'+
   						'</div>'+
   					'</div>'+
   					'<div style="margin-top:50px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">循环点</font></div>'+	
   					"<div id=visitdian style='margin-top:10px;width:300px;height:50px;'><select id=visitplanse class=useselect  style='cursor:pointer; position: relative;margin-left:4px;width:240px;height:50px;font-size: 15px;'></select></div>"+
   					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">限定天数</font></div>'+	
   					'<div id=plandays class="main-input" style="margin-left:3px; width:220px;margin-top:10px;padding-top:10px;">'+plandays+'</div>'+
				'</div>'+
   			'</div>'+
	  		'</div>');
		
		if(cycle==1){
			$("#cycletypebox").find("div").eq(cycletype).attr("class","visiitboxtwo");
			if(cycletype==0){
				$("#visitplanse").append("<option  value ='1'>1</option>");
			}else if(cycletype==1){
				$("#visitplanse").append("<option  value ='1'>1</option><option  value ='2'>2</option><option  value ='3'>3</option><option  value ='4'>4</option><option  value ='5'>5</option><option  value ='6'>6</option><option  value ='7'>7</option>");
				$("#visitplanse").find("option").eq(cyclenumber-1).attr("selected","selected");
			}else if(cycletype==2){
				var str;
				for(var i=1;i<=31;i++){
					str+="<option  value ='"+i+"'>"+i+"</option>";
				}
				$("#visitplanse").append(str);
				$("#visitplanse").find("option").eq(cyclenumber-1).attr("selected","selected");
			}else if(cycletype==3){
				$("#visitdian").html("");
				$("#visitdian").append(	'<div  class="main-input" style="margin-left:3px; width:220px;margin-top:10px;padding-top:10px;">'+plandays+'</div>');
			}
		
		}else if(cycle==0){
			$("#visitplanse").css("background-color","rgb(120,121,123)");
			$("#plandays").html(0);
			
		}
	}
}


function checkvisitplan() {
	var x=curtab;
	var clientname=$(x).find("th").eq(5).html();
	var state=$(x).find("th").eq(8).html();
	var visitplanid=$(x).find("th").eq(1).html();
	var myname=$(x).find("th").eq(4).html();
	if(state!="未审核"){
		alert("该拜访计划不需要审核");
	}else{
		$.getJSON("./WGetVisitConclusionServlet",{VisitPlanId:visitplanid},function(outjson){
			$(".group-listvisitplan").html("");
			var paths=outjson.VisitAccessoryPath.split("/");
			$(".group-listvisitplan").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
		  			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="showvisitplan()"></img><div style="margin-top:-25px;"><font>总结详情</font></div></div>'+
		  			'<div class="ground-one-left" style="float:left;width:50%;height:100%; 	">'+
	   				'<div style="position:relative; height:500px;margin-left:100px;">'+
	   				'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;margin-top:10px;">拜访总结号</font></div>'+	
						'<div id=vitconcluid class="main-input" style="margin-left:10px;width:289px;padding-top:7px;margin-top:10px;padding-top:10px;" >'+outjson.VisitConclusionId+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户名</font></div>'+	
						'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+clientname+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">员工名</font></div>'+	
						'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+myname+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">提交时间</font></div>'+	
						'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+outjson.VisitSubmitTime+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">拜访总结</font></div>'+	
						'<textarea   class="main-input" style="font-size:15px;font-weight: bold;	margin-left:10px; width:309px;height:100px;margin-top:10px;padding-top:10px;background-color:rgb(248,254,254)">'+outjson.VisitSummary+'</textarea>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户需求</font></div>'+	
						'<textarea   class="main-input" style="font-size:15px;font-weight: bold;	margin-left:10px; width:309px;height:100px;margin-top:10px;padding-top:10px;background-color:rgb(248,254,254)">'+outjson.VisitCommand+'</textarea>'+
					
					'</div>'+
	   			'</div>'+
	   			
	   			'<div class="ground-one-right" style="float:left;width:49%;height:100%;border-left:2px;border-left-color:black;border-left-style:dashed;">'+
	   				'<div style="position:relative; height:500px;margin-left:100px;">'+
	   					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">现场图片</font></div>'+	
	   					'<div  style="margin-left:10px;height:400px; width:289px;margin-top:10px;background-color:rgb(41,44,47);"><img style="width:100%;height:100%;" src="/upload/'+paths[2]+'"/> </div>'+
					'</div>'+
					'<div style="margin-left:113px;"><div class="usebut"  style="font-size: 14px;width: 76px;height: 35px;line-height: 33px;margin-left:50px;" onclick="visitconclusioncheck(1)">审核通过</div><div class="usebut" style="margin-left:40px;font-size: 14px;width: 76px;height: 35px;line-height: 33px;" onclick="visitconclusioncheck(2)">审核不通过</div></div>'+
				'</div>'+
		  		'</div>');
		})
	
	}
	
	
}

function visitconclusioncheck(x) {
	var vit=curtab;
	var visitplanid=$(vit).find("th").eq(1).html();
	var vitcon=$("#vitconcluid").html();
	$.getJSON("./WCheckVisitConclusionServlet",{VisitPlanId:visitplanid,VisitConclusionId:vitcon,VisitCheck:x,},function(outjson){
		if(outjson.check){
			alert("成功");
		}else {
			alert("失败");
		}
		showback();
	});

}

function delvisitout() {
	var vit=curtab;
	var visitplanid=$(vit).find("th").eq(1).html();
	var state=$(vit).find("th").eq(8).html();
	
	if(state!="已过期"){
		alert("该拜访计划不需要过期处理");
 	}else{
 		$(".shadow").show();
 		$(".pagehead font").html("过期处理")
 		$(".showpage").css("width","280px");
 		$(".showpage").css("height","100px");
 		$(".showpage").css("margin-left","600px");
 		$(".pagemain").html("<div class=userset>"+
 	  				"<div style='margin-left: 30px;margin-top: 10px;'><button onclick='delvisitoutOK(1)'  class=but1>延期处理</button><button class=but1  onclick='delvisitoutOK(2)'  >判定失败</button></div>");
 	}
}
function delvisitoutOK(x) {
	var vit=curtab;
	var visitplanid=$(vit).find("th").eq(1).html();
	var date=new Date();
	var strdate=date.format("yyyy/MM/dd");
	var myname=$(vit).find("th").eq(4).html();
	var userid=$(vit).find("th").eq(15).html();
	
	if(x==1){
		closepage();
		
		$(".shadow").show();
		$(".pagehead font").html("延期处理")
		$(".showpage").css("width","280px");
		$(".showpage").css("height","200px");
		$(".showpage").css("margin-left","600px");
		$(".pagemain").html("<div class=userset>"+
					"<div><a>拜访计划 : </a>&nbsp"+visitplanid+"</div>"+
	  				"<div><a>员工名称 : </a>&nbsp"+myname+"</div>"+
	  				"<div><a>延期时间：</a>"+strdate+"</div>"+
	  				"<div><a>延期期限：</a><input id=deltimeid  onclick=SelectDate(this,\'yyyy/MM/dd\') style='font-size:17px;width:100px;'></input></div>"+
	  				"<div style='display:none'>"+userid+"</div>"+
	  				"<div style='margin-left: 30px;margin-top: 10px;'><button onclick='delvisitoutOKOK()' class=but1>确定</button><button class=but1 onclick='closeshadow()'>取消</button></div>"+
	  				"</div>");
	}else if(x==2){
		$.getJSON("./WAbandonVisitPlanServlet",{VisitPlanId:visitplanid},function(outjson){
			if(outjson.check){
				alert("成功");
				closepage();
			}else {
				alert("失败");
				closepage();
			}
				
		});
		showback();
	}
	
}
function delvisitoutOKOK() {
	var vit=curtab;
	var visitplanid=$(vit).find("th").eq(1).html();
//	var user=usedetailtab;
	var userid=$(vit).find("th").eq(15).html();
	var endtime=$("#deltimeid").val();
	var date=new Date();
	var strdate=date.format("yyyy/MM/dd");
//	alert(userid+"--"+visitplanid);
	if(!duibi(strdate, endtime)){
		alert("延期期限不能小于延期时间")
	}else{
		$.getJSON("./WDelayVisitPlanServlet",{EmployeeId:userid,VisitPlanId:visitplanid,VisitPlanEndTime:endtime},function(outjson){
			if(outjson.check){
//				showvisitplan();
				alert("成功");
				closepage();
			}else {
//				showvisitplan();
				alert("失败");
				closepage();
			}
				
		});
		showback();
	}
}
function duibi(a, b) {
    var arr = a.split("/");
    var starttime = new Date(arr[0], arr[1], arr[2]);
    var starttimes = starttime.getTime();

    var arrs = b.split("/");
    var lktime = new Date(arrs[0], arrs[1], arrs[2]);
    var lktimes = lktime.getTime();

    if (starttimes >= lktimes) {

//        alert('开始时间大于离开时间，请检查');
        return false;
    }
    else
        return true;

}


function seevisitcounclusion() {
	
	var x=curtab;
	var clientname=$(x).find("th").eq(10).html();
	var state=$(x).find("th").eq(7).html();
	var vistconid=$(x).find("th").eq(0).html();
	var myname=$(x).find("th").eq(9).html();
	
		$.getJSON("./WGetVisitConclusionDetailServlet",{VisitConclusionId:vistconid},function(outjson){
			$(".group-listvisitplan").html("");
			var paths=outjson.VisitAccessoryPath.split("\\");
			$(".group-listvisitplan").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
		  			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="showback()"></img><div style="margin-top:-25px;"><font>总结详情</font></div></div>'+
		  			'<div class="ground-one-left" style="float:left;width:50%;height:100%; 	">'+
	   				'<div style="position:relative; height:500px;margin-left:100px;">'+
	   				'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;margin-top:10px;">拜访总结号</font></div>'+	
						'<div id=vitconcluid class="main-input" style="margin-left:10px;width:289px;padding-top:7px;margin-top:10px;padding-top:10px;" >'+outjson.VisitConclusionId+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户名</font></div>'+	
						'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+clientname+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">员工名</font></div>'+	
						'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+myname+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">提交时间</font></div>'+	
						'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+outjson.VisitSubmitTime+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">拜访总结</font></div>'+	
						'<textarea   class="main-input" style="font-size:15px;font-weight: bold;	margin-left:10px; width:309px;height:100px;margin-top:10px;padding-top:10px;background-color:rgb(248,254,254)">'+outjson.VisitSummary+'</textarea>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户需求</font></div>'+	
						'<textarea   class="main-input" style="font-size:15px;font-weight: bold;	margin-left:10px; width:309px;height:100px;margin-top:10px;padding-top:10px;background-color:rgb(248,254,254)">'+outjson.VisitCommand+'</textarea>'+
					
					'</div>'+
	   			'</div>'+
	   			
	   			'<div class="ground-one-right" style="float:left;width:49%;height:100%;border-left:2px;border-left-color:black;border-left-style:dashed;">'+
	   				'<div style="position:relative; height:500px;margin-left:100px;">'+
	   					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">现场图片</font></div>'+	
	   					'<div  style="margin-left:10px;height:400px; width:289px;margin-top:10px;background-color:rgb(41,44,47);"><img style="width:100%;height:100%;" src="/upload/'+paths[2]+'"/> </div>'+
					'</div>'+
//					'<div style="margin-left:113px;"><div class="usebut"  style="font-size: 14px;width: 76px;height: 35px;line-height: 33px;margin-left:50px;" onclick="visitconclusioncheck(1)">审核通过</div><div class="usebut" style="margin-left:40px;font-size: 14px;width: 76px;height: 35px;line-height: 33px;" onclick="visitconclusioncheck(2)">审核不通过</div></div>'+
				'</div>'+
		  		'</div>');
			
		})

	
	
}

function checkvisitconclusion() {
	var x=curtab;
	var clientname=$(x).find("th").eq(10).html();
	var state=$(x).find("th").eq(7).html();
	var visitplanid=$(x).find("th").eq(4).html();
	var myname=$(x).find("th").eq(9).html();
	if(state!="未审核"){
		alert("该拜访计划不需要审核");
	}else{
		$.getJSON("./WGetVisitConclusionServlet",{VisitPlanId:visitplanid},function(outjson){
			$(".group-listvisitplan").html("");
			var paths=outjson.VisitAccessoryPath.split("\\");
			$(".group-listvisitplan").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
		  			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="showvisitplan()"></img><div style="margin-top:-25px;"><font>总结详情</font></div></div>'+
		  			'<div class="ground-one-left" style="float:left;width:50%;height:100%; 	">'+
	   				'<div style="position:relative; height:500px;margin-left:100px;">'+
	   				'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;margin-top:10px;">拜访总结号</font></div>'+	
						'<div id=vitconcluid class="main-input" style="margin-left:10px;width:289px;padding-top:7px;margin-top:10px;padding-top:10px;" >'+outjson.VisitConclusionId+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户名</font></div>'+	
						'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+clientname+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">员工名</font></div>'+	
						'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+myname+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">提交时间</font></div>'+	
						'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+outjson.VisitSubmitTime+'</div>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">拜访总结</font></div>'+	
						'<textarea   class="main-input" style="font-size:15px;font-weight: bold;	margin-left:10px; width:309px;height:100px;margin-top:10px;padding-top:10px;background-color:rgb(248,254,254)">'+outjson.VisitSummary+'</textarea>'+
						'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户需求</font></div>'+	
						'<textarea   class="main-input" style="font-size:15px;font-weight: bold;	margin-left:10px; width:309px;height:100px;margin-top:10px;padding-top:10px;background-color:rgb(248,254,254)">'+outjson.VisitCommand+'</textarea>'+
					
					'</div>'+
	   			'</div>'+
	   			
	   			'<div class="ground-one-right" style="float:left;width:49%;height:100%;border-left:2px;border-left-color:black;border-left-style:dashed;">'+
	   				'<div style="position:relative; height:500px;margin-left:100px;">'+
	   					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">现场图片</font></div>'+	
	   					'<div  style="margin-left:10px;height:400px; width:289px;margin-top:10px;background-color:rgb(41,44,47);"><img style="width:100%;height:100%;" src="/upload/'+paths[2]+'"/> </div>'+
					'</div>'+
					'<div style="margin-left:113px;"><div class="usebut"  style="font-size: 14px;width: 76px;height: 35px;line-height: 33px;margin-left:50px;" onclick="visitconclusioncheckbyconclusion(1)">审核通过</div><div class="usebut" style="margin-left:40px;font-size: 14px;width: 76px;height: 35px;line-height: 33px;" onclick="visitconclusioncheckbyconclusion(2)">审核不通过</div></div>'+
				'</div>'+
		  		'</div>');
			
		})
	}
}


function visitconclusioncheckbyconclusion(x) {
	var vit=curtab;
	var visitplanid=$(vit).find("th").eq(4).html();
	var vitcon=$("#vitconcluid").html();
	$.getJSON("./WCheckVisitConclusionServlet",{VisitPlanId:visitplanid,VisitConclusionId:vitcon,VisitCheck:x,},function(outjson){
		if(outjson.check){
			alert("成功");
		}else {
			alert("失败");
		}
		showback();
	});

}

function showvisitconclusion() {
//	var x=usedetailtab;
	$(".group-listvisitplan").html("");

 			$(".group-listvisitplan").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
 		  			'<div class="group-title" ><font>拜访总结记录</font>'+
 		  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='seevisitcounclusion()'>查看</div>"+
 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='checkvisitconclusion()'>审核</div>"+
 					'</div>'+
 		  			'<table id="tab"   style="font-size:13px;" width="100%">'+
 		               '<tbody>'+
 		                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
 		                    '<th>拜访总结号</th>'+
 		                    '<th>提交时间</th>'+
 		                    '<th>员工账号</th>'+
 		                    '<th>客户号</th>'+
 		                    '<th>拜访计划号</th>'+
 		                    '<th>客户总结</th>'+
 		                    '<th>客户需求</th>'+
 		                    '<th>审核结果</th>'+
 		                '</tr>'+
 		                '</tbody>'+
 		                
 		                 
 		                '<tbody  class="thisgroup">'+
 		                	
 		                '</tbody>'+
 		                '</table>'+
 		             
 		                
 		                
 		  		'</div>');
 			
// 		var id=$(x).find("th").eq(6).html();
 		$.getJSON("./WGetAllVisitConclusionServlet",function(outjson){
 			for ( var i = 0; i < outjson.VisitConclusionList.length; i++) {
 				
 						var co="rgb(0,0,0)"
 		 				var state="未审核";
 		 				if(outjson.VisitConclusionList[i].VisitCheck==0){
 		 					state="未审核";
 		 					co="rgb(0,162,232)";
 		 				}else if(outjson.VisitConclusionList[i].VisitCheck==1){
 		 					state="已通过";
 		 				}else if(outjson.VisitConclusionList[i].VisitCheck==2){
 		 					state="未通过";
 		 				}
 	 					for ( var j = 0; j < $(".group-one").length; j++) {
 	 							$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;	background-color: rgb(232,251,250);">'+
 	 								    '<th  style="font-weight: normal; ">'+outjson.VisitConclusionList[i].VisitConclusionId+'</th>'+
 	 								    '<th  style="font-weight: normal; ">'+outjson.VisitConclusionList[i].VisitSubmitTime+'</th>'+
 	 								    '<th  style="font-weight: normal; ">'+outjson.VisitConclusionList[i].EmployeeAccount+'</th>'+
 	 								    '<th  style="font-weight: normal; ">'+outjson.VisitConclusionList[i].ClientId+'</th>'+
 	 								    '<th  style="font-weight: normal; ">'+outjson.VisitConclusionList[i].VisitPlanId+'</th>'+
 	 								    '<th  style="font-weight: normal; ">'+outjson.VisitConclusionList[i].VisitSummary+'</th>'+
 	 								    '<th  style="font-weight: normal; ">'+outjson.VisitConclusionList[i].VisitCommand+'</th>'+
 	 								    '<th  style="font-weight: bold; color:'+co+';">'+state+'</th>'+
 	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitConclusionList[i].EmployeeId+'</th>'+
 	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitConclusionList[i].EmployeeName+'</th>'+
 	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitConclusionList[i].ClientName+'</th>'+
// 	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanCycleType+'</th>'+
// 	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanCycleNumber+'</th>'+
// 	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanDays+'</th>'+
// 	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitBussinessBandState+'</th>'+
// 	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].ClientId+'</th>'+
 	 									'</tr>')
 	 						}
 	 					}
 		
 				
 				
 			
 		});
 		curtab=null;
}



function showvisitdelay() {
	$(".group-listvisitplan").html("");

		$(".group-listvisitplan").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
	  			'<div class="group-title" ><font>拜访延期记录</font>'+
	  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
//				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='seevisitconclusion()'>查看</div>"+
//				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='checkvisitconclusion()'>审核</div>"+
				'</div>'+
	  			'<table id="tab"   style="font-size:13px;" width="100%">'+
	               '<tbody>'+
	                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
	                    '<th>拜访延期号</th>'+
	                    '<th>拜访延期时间</th>'+
	                    '<th>拜访延期期限</th>'+
	                    '<th>客户号</th>'+
	                    '<th>拜访计划号</th>'+
	                    '<th>员工账号号</th>'+
	                '</tr>'+
	                '</tbody>'+
	                
	                 
	                '<tbody  class="thisgroup">'+
	                	
	                '</tbody>'+
	                '</table>'+
	             
	                
	                
	  		'</div>');
		
//	var id=$(x).find("th").eq(6).html();
	$.getJSON("./WGetAllVisitDealyInfoServlet",function(outjson){
		for ( var i = 0; i < outjson.VisitDelayList.length; i++) {
					for ( var j = 0; j < $(".group-one").length; j++) {
							$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;	background-color: rgb(232,251,250);">'+
								    '<th  style="font-weight: normal; ">'+outjson.VisitDelayList[i].VisitDelayId+'</th>'+
								    '<th  style="font-weight: normal; ">'+outjson.VisitDelayList[i].VisitDelayTime+'</th>'+
								    '<th  style="font-weight: normal; ">'+outjson.VisitDelayList[i].VisitDelayDeadline+'</th>'+
								    '<th  style="font-weight: normal; ">'+outjson.VisitDelayList[i].ClientId+'</th>'+
								    '<th  style="font-weight: normal; ">'+outjson.VisitDelayList[i].VisitPlanId+'</th>'+
								    '<th  style="font-weight: normal; ">'+outjson.VisitDelayList[i].EmployeeAccount+'</th>'+
//								    '<th  style="font-weight: bold; color:'+co+';">'+state+'</th>'+
								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitDelayList[i].EmployeeId+'</th>'+
//								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanCycleType+'</th>'+
//								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanCycleNumber+'</th>'+
//								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanDays+'</th>'+
//								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitBussinessBandState+'</th>'+
//								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].ClientId+'</th>'+
									'</tr>')
						}
					}
	
			
			
		
	});
	curtab=null;
}






function showvisitplanundo() {
	
	$(".group-listvisitplan").html("");

		$(".group-listvisitplan").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
	  			'<div class="group-title" ><font>拜访计划</font>'+
	  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
//	  			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='undovisitplan()' >撤销</div>"+
//				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='seevisitconclusion()'>查看总结</div>"+
//				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='checkvisitplan()'>审核</div>"+
//				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='delvisitout()'>过期处理</div>"+
				'</div>'+
	  			'<table id="tab"   style="font-size:13px;" width="100%">'+
	               '<tbody>'+
	                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
//	                	'<th>标志</th>'+
	                    '<th>拜访撤销号</th>'+
	                    '<th>撤销时间</th>'+
	                    '<th>拜访计划号</th>'+
	                    '<th>员工账号</th>'+
	                    '<th>员工名</th>'+
	                    '<th>客户账号</th>'+
	                    '<th>客户名</th>'+
	                    '<th>撤销原因</th>'+
	                '</tr>'+
	                '</tbody>'+
	                
	                 
	                '<tbody  class="thisgroup">'+
	                	
	                '</tbody>'+
	                '</table>'+
	             
	                
	                
	  		'</div>');
		
//	var id=$(x).find("th").eq(6).html();
	$.getJSON("./WGetAllVisitUndoServlet",function(outjson){
		for ( var i = 0; i < outjson.VisitUndoList.length; i++) {
			
			
				for ( var j = 0; j < $(".group-one").length; j++) {
						$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;	background-color: rgb(232,251,250);">'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitUndoList[i].VisitUndoId+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitUndoList[i].VisitUndoTime+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitUndoList[i].VisitPlanId+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitUndoList[i].EmployeeAccount+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitUndoList[i].EmployeeName+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitUndoList[i].ClientId+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitUndoList[i].ClientName+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitUndoList[i].VisitUndoReason+'</th>'+
							    '<th  style="font-weight: normal; display: none;">'+outjson.VisitUndoList[i].EmployeeId+'</th>'+
								'</tr>')
					}
				}
		
	});
	curtab=null;
}








function showvisitplanwaitdeal() {
//	WGetAllVisitPlanWaitDealServlet

	$(".group-listvisitplan").html("");
		$(".group-listvisitplan").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
	  			'<div class="group-title" ><font>拜访计划</font>'+
	  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
//	  			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='undovisitplan()' >撤销</div>"+
//				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='seevisitplan()'>查看</div>"+
				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='checkvisitplan()'>审核</div>"+
				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='delvisitout()'>过期处理</div>"+
				'</div>'+
	  			'<table id="tab"   style="font-size:13px;" width="100%">'+
	               '<tbody>'+
	                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
	                	'<th>标志</th>'+
	                    '<th>拜访计划号</th>'+
	                    '<th>发布时间</th>'+
	                    '<th>员工账号</th>'+
	                    '<th>员工名</th>'+
	                    '<th>客户名</th>'+
	                    '<th>开始时间</th>'+
	                    '<th>截止时间</th>'+
	                    '<th>拜访状态</th>'+
	                '</tr>'+
	                '</tbody>'+
	                
	                 
	                '<tbody  class="thisgroup">'+
	                	
	                '</tbody>'+
	                '</table>'+
	             
	                
	                
	  		'</div>');
		
//	var id=$(x).find("th").eq(6).html();
	$.getJSON("./WGetAllVisitPlanWaitDealServlet",function(outjson){
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
						$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;	background-color: rgb(232,251,250);">'+
								'<th  style="font-weight: normal; width:60px; height:30px;"><img  src="./pic/xunhuan.png" style="height:30px;width:30px;cursor: pointer;display: '+cycle+'; float:left" title="拜访循环"></img><img  src="./pic/chuchaibangding.png" style="height:30px;width:30px;cursor: pointer;display: '+bind+';float:right" title="出差绑定"></img></th>'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitPlanList[i].VisitPlanId+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitPlanList[i].VisitPlanPubdate+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitPlanList[i].EmployeeAccount+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitPlanList[i].EmployeeName+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitPlanList[i].ClientName+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitPlanList[i].VisitPlanStartTime+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitPlanList[i].VisitPlanEndTime+'</th>'+
							    '<th  style="font-weight: bold; color:'+co+';">'+state+'</th>'+
							    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanCycle+'</th>'+
							    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanCycleType+'</th>'+
							    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanCycleNumber+'</th>'+
							    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanDays+'</th>'+
							    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitBussinessBandState+'</th>'+
							    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].ClientId+'</th>'+
							    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].EmployeeId+'</th>'+
								'</tr>')
					}
				}
		
	});
	curtab=null;
	
}





function showvisitplancomplete() {
	
	$(".group-listvisitplan").html("");

		$(".group-listvisitplan").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
	  			'<div class="group-title" ><font>拜访计划</font>'+
	  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
//	  			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='undovisitplan()' >撤销</div>"+
//				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='seevisitconclusion()'>查看总结</div>"+
//				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='checkvisitplan()'>审核</div>"+
//				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='delvisitout()'>过期处理</div>"+
				'</div>'+
	  			'<table id="tab"   style="font-size:13px;" width="100%">'+
	               '<tbody>'+
	                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
	                	'<th>标志</th>'+
	                    '<th>拜访计划号</th>'+
	                    '<th>发布时间</th>'+
	                    '<th>员工账号</th>'+
	                    '<th>员工名</th>'+
	                    '<th>客户名</th>'+
	                    '<th>开始时间</th>'+
	                    '<th>截止时间</th>'+
	                    '<th>拜访状态</th>'+
	                '</tr>'+
	                '</tbody>'+
	                
	                 
	                '<tbody  class="thisgroup">'+
	                	
	                '</tbody>'+
	                '</table>'+
	             
	                
	                
	  		'</div>');
		
//	var id=$(x).find("th").eq(6).html();
	$.getJSON("./WGetAllVisitPlanCompleteServlet",function(outjson){
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
						$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;	background-color: rgb(232,251,250);">'+
								'<th  style="font-weight: normal; width:60px; height:30px;"><img  src="./pic/xunhuan.png" style="height:30px;width:30px;cursor: pointer;display: '+cycle+'; float:left" title="拜访循环"></img><img  src="./pic/chuchaibangding.png" style="height:30px;width:30px;cursor: pointer;display: '+bind+';float:right" title="出差绑定"></img></th>'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitPlanList[i].VisitPlanId+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitPlanList[i].VisitPlanPubdate+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitPlanList[i].EmployeeAccount+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitPlanList[i].EmployeeName+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitPlanList[i].ClientName+'</th>'+
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
		
	});
	curtab=null;
}




function showvisitplanrunning() {
	
	$(".group-listvisitplan").html("");

		$(".group-listvisitplan").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
	  			'<div class="group-title" ><font>拜访计划</font>'+
	  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
//	  			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='undovisitplan()' >撤销</div>"+
//				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='seevisitplan()'>查看</div>"+
//				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='checkvisitplan()'>审核</div>"+
//				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='delvisitout()'>过期处理</div>"+
				'</div>'+
	  			'<table id="tab"   style="font-size:13px;" width="100%">'+
	               '<tbody>'+
	                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
	                	'<th>标志</th>'+
	                    '<th>拜访计划号</th>'+
	                    '<th>发布时间</th>'+
	                    '<th>员工账号</th>'+
	                    '<th>员工名</th>'+
	                    '<th>客户名</th>'+
	                    '<th>开始时间</th>'+
	                    '<th>截止时间</th>'+
	                    '<th>拜访状态</th>'+
	                '</tr>'+
	                '</tbody>'+
	                
	                 
	                '<tbody  class="thisgroup">'+
	                	
	                '</tbody>'+
	                '</table>'+
	             
	                
	                
	  		'</div>');
		
//	var id=$(x).find("th").eq(6).html();
	$.getJSON("./WGetAllVisitPlanRunningServlet",function(outjson){
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
						$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;	background-color: rgb(232,251,250);">'+
								'<th  style="font-weight: normal; width:60px; height:30px;"><img  src="./pic/xunhuan.png" style="height:30px;width:30px;cursor: pointer;display: '+cycle+'; float:left" title="拜访循环"></img><img  src="./pic/chuchaibangding.png" style="height:30px;width:30px;cursor: pointer;display: '+bind+';float:right" title="出差绑定"></img></th>'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitPlanList[i].VisitPlanId+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitPlanList[i].VisitPlanPubdate+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitPlanList[i].EmployeeAccount+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitPlanList[i].EmployeeName+'</th>'+
							    '<th  style="font-weight: normal; ">'+outjson.VisitPlanList[i].ClientName+'</th>'+
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


