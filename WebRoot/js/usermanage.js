 var arrUserName=new Array();
 var arrClientName=new Array();
 var arrClientSubmitName=new Array();
 
 var tabuser=null;
 var tabclient=null;
 var tabvisitplan=null;
 var tabmission=null;
 var tabbussiness=null;
 var curbut=0;//1.考勤记录，2。员工客户，3.拜访记录，4任务记录，5，出差记录。6，投诉记录
 var usedetailtab=null;
 var searchtype=0;//1.搜员工名2.搜员工分配客户名3.搜员工提交客户名
 var myname="";
 var mydepar=""

	 
$(document).ready(function() {
	showmainusertitle();
	showuserlist();
})


//----------------------------------------------------------标题相关-------------------------------------------------------------
function showuserdetailtitle() {
	$(".right-title").html("");
	
	$(".right-title").append(
			"<img onclick='showuserlist(),showmainusertitle()' src='./pic/daohang.png'  title='员工管理导航' style=' width:30px;height:30px; cursor:pointer; margin-left:100px;margin-bottom:-64px; margin-top:7px;' />"+
			"<div style='font-family:Microsoft YaHei;position:relative; width:100px;height:50px; margin-left:200px;margin-bottom:-54px; margin-top:4px;'><font style='font-size:13px;color:rgb(255,255,255)'>"+myname+"</font></br>	<font style='font-size:13px;color:rgb(255,255,255)'>"+mydepar+"</font></div>"+
			"<div id=usermanagerserch class=zySearch style='margin-left:310px;'> " +
 			"<input id=usetimeid onclick=SelectDate(this,\'yyyy/MM/dd\') class=search-input  placeholder='请输入日期' ></input>"+
 			"<div class=search-btn  onclick='searchtime()'>搜索</div>"+
 			"</div>"+
 			"<div id=butss style='height:25px;width:500px;height:50px;float:right;margin-top:-15px;'>" +
 				"<div id=butss1 class=usebut  style=' cursor:pointer; position: relative;margin-left:50px;margin-right:24px;'><select  id=selectattendance onchange='showSelectAttendance()' class=useselect style='height:25px;'><option value='0'  style='display: none;'>考勤操作</option><option value='1'>考勤记录</option></select></div>"+
 				"<div id=butss3 class=usebut  style=' cursor:pointer; position: relative;margin-right:24px;'> <select  id=selectvisitplan onchange='showSelectVisitPlan()' class=useselect style='height:25px;'><option value='0'  style='display: none;'>拜访操作</option><option value='1'>拜访计划</option><option value='2'>总结记录</option><option value='3'>延期记录</option></select></div>"+
 				"<div id=butss4 class=usebut  style=' cursor:pointer; position: relative;margin-right:24px;'><select  id=selectmission onchange='showSelectMission()' class=useselect style='height:25px;'><option value='0'  style='display: none;'>任务操作</option><option value='1'>任务记录</option><option value='2'>总结记录</option><option value='3'>延期记录</option></select></div>"+
 				"<div id=butss5 class=usebut  style=' cursor:pointer; position: relative;margin-right:24px;'><select  id=selectbussiness onchange='showSelectBussiness()' class=useselect style='height:25px;'><option value='0'  style='display: none;'>出差操作</option><option value='1'>出差记录</option><option value='2'>不良记录</option></select></div>"+
 				"<div id=butss2 class=usebut  style=' cursor:pointer; position: relative;margin-right:24px;'> <select  id=selectclient onchange='showSelectClient()' class=useselect style='height:25px;'><option value='0'  style='display: none;'>客户操作</option><option value='1'>员工客户</option><option value='2'>提交记录</option></select></div>"+
 		 		
 			"</div>"
 		
 			
	);
	$("#selectattendance").find("option").eq(1).attr("selected","selected");
}
function showmainusertitle() {
	$(".right-title").html("");
	$(".right-title").append(
			"<img onclick='showuserlist(),showmainusertitle()' src='./pic/daohang.png'  title='员工管理导航' style=' width:30px;height:30px; cursor:pointer; margin-left:100px;margin-bottom:-64px; margin-top:7px;' />"+
			"<div id=usermanagerserch class=zySearch> " +
 			"<input id=usernameid class=search-input  placeholder='请输入员工名' ></input>"+
 			"<div class=search-btn  onclick='searchuse()'>搜索</div>"+
 			"</div>"+
 			"<div id=butss style='height:25px;width:400px;float:right;margin-top:-15px; margin-left:700px;'>" +
 				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='setuser()'>修改</div>"+
 				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='userdetail()'>查看</div>"+
 				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='delep()'>删除</div>"+
 				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='addep()'>增加</div>"+
 				"<div class=usebut  style=' cursor:pointer; position: relative; margin-left:50px;'> <select id=selectID onchange='showSelect()' class=useselect style='height:25px;'><option value='1'>在职员工</option><option value='2'>已删员工</option></select></div> "+
 				
 			"</div>"
 		
 			
	);
}


function showclienttitle(myselect) {
	$(".right-title").html("");
	
	$(".right-title").append(
			"<img onclick='showuserlist(),showmainusertitle()' src='./pic/daohang.png'  title='员工管理导航' style=' width:30px;height:30px; cursor:pointer; margin-left:100px;margin-bottom:-64px; margin-top:7px;' />"+
			"<div style='font-family:Microsoft YaHei;position:relative; width:100px;height:50px; margin-left:200px;margin-bottom:-54px; margin-top:4px;'><font style='font-size:13px;color:rgb(255,255,255)'>"+myname+"</font></br>	<font style='font-size:13px;color:rgb(255,255,255)'>"+mydepar+"</font></div>"+
			"<div id=usermanagerserch class=zySearch style='margin-left:310px;'> " +
 			"<input id=clienttimeid  class=search-input  placeholder='请输入员工分配的客户名' ></input>"+
 			"<div class=search-btn  onclick='searchclienet()'>搜索</div>"+
 			"</div>"+
 			"<div id=butss style='height:25px;width:500px;height:50px;float:right;margin-top:-15px;'>" +
				"<div id=butss1 class=usebut  style=' cursor:pointer; position: relative;margin-left:50px;margin-right:24px;'><select  id=selectattendance onchange='showSelectAttendance()' class=useselect style='height:25px;'><option value='0'  style='display: none;'>考勤操作</option><option value='1'>考勤记录</option></select></div>"+
				"<div id=butss3 class=usebut  style=' cursor:pointer; position: relative;margin-right:24px;'> <select  id=selectvisitplan onchange='showSelectVisitPlan()' class=useselect style='height:25px;'><option value='0'  style='display: none;'>拜访操作</option><option value='1'>拜访计划</option><option value='2'>总结记录</option><option value='3'>延期记录</option></select></div>"+
				"<div id=butss4 class=usebut  style=' cursor:pointer; position: relative;margin-right:24px;'><select  id=selectmission onchange='showSelectMission()' class=useselect style='height:25px;'><option value='0'  style='display: none;'>任务操作</option><option value='1'>任务记录</option><option value='2'>总结记录</option><option value='3'>延期记录</option></select></div>"+
				"<div id=butss5 class=usebut  style=' cursor:pointer; position: relative;margin-right:24px;'><select  id=selectbussiness onchange='showSelectBussiness()' class=useselect style='height:25px;'><option value='0'  style='display: none;'>出差操作</option><option value='1'>出差记录</option><option value='2'>不良记录</option></select></div>"+
				"<div id=butss2 class=usebut  style=' cursor:pointer; position: relative;margin-right:24px;'> <select  id=selectclient onchange='showSelectClient()' class=useselect style='height:25px;'><option value='0'  style='display: none;'>客户操作</option><option value='1'>员工客户</option><option value='2'>提交记录</option></select></div>"+
		 		
			"</div>"
 		
 			
	);
	$("#selectclient").find("option").eq(myselect).attr("selected","selected");

}


function showclientsubmittitle(myselect) {
	$(".right-title").html("");
	
	$(".right-title").append(
			"<img onclick='showuserlist(),showmainusertitle()' src='./pic/daohang.png'  title='员工管理导航' style=' width:30px;height:30px; cursor:pointer; margin-left:100px;margin-bottom:-64px; margin-top:7px;' />"+
			"<div style='font-family:Microsoft YaHei;position:relative; width:100px;height:50px; margin-left:200px;margin-bottom:-54px; margin-top:4px;'><font style='font-size:13px;color:rgb(255,255,255)'>"+myname+"</font></br>	<font style='font-size:13px;color:rgb(255,255,255)'>"+mydepar+"</font></div>"+
			"<div id=usermanagerserch class=zySearch style='margin-left:310px;'> " +
 			"<input id=clientsubmittimeid  class=search-input  placeholder='请输入员工提交的客户名' ></input>"+
 			"<div class=search-btn  onclick='searchclienetsubmit()'>搜索</div>"+
 			"</div>"+
 			"<div id=butss style='height:25px;width:500px;height:50px;float:right;margin-top:-15px;'>" +
				"<div id=butss1 class=usebut  style=' cursor:pointer; position: relative;margin-left:50px;margin-right:24px;'><select  id=selectattendance onchange='showSelectAttendance()' class=useselect style='height:25px;'><option value='0'  style='display: none;'>考勤操作</option><option value='1'>考勤记录</option></select></div>"+
				"<div id=butss3 class=usebut  style=' cursor:pointer; position: relative;margin-right:24px;'> <select  id=selectvisitplan onchange='showSelectVisitPlan()' class=useselect style='height:25px;'><option value='0'  style='display: none;'>拜访操作</option><option value='1'>拜访计划</option><option value='2'>总结记录</option><option value='3'>延期记录</option></select></div>"+
				"<div id=butss4 class=usebut  style=' cursor:pointer; position: relative;margin-right:24px;'><select  id=selectmission onchange='showSelectMission()' class=useselect style='height:25px;'><option value='0'  style='display: none;'>任务操作</option><option value='1'>任务记录</option><option value='2'>总结记录</option><option value='3'>延期记录</option></select></div>"+
				"<div id=butss5 class=usebut  style=' cursor:pointer; position: relative;margin-right:24px;'><select  id=selectbussiness onchange='showSelectBussiness()' class=useselect style='height:25px;'><option value='0'  style='display: none;'>出差操作</option><option value='1'>出差记录</option><option value='2'>不良记录</option></select></div>"+
				"<div id=butss2 class=usebut  style=' cursor:pointer; position: relative;margin-right:24px;'> <select  id=selectclient onchange='showSelectClient()' class=useselect style='height:25px;'><option value='0'  style='display: none;'>客户操作</option><option value='1'>员工客户</option><option value='2'>提交记录</option></select></div>"+
		 		
			"</div>"
 		
 			
	);
	$("#selectclient").find("option").eq(myselect).attr("selected","selected");

}

//--------------------------------------------------------选择框初始-------------------
function initselect() {
	$("#selectattendance").find("option").eq(0).attr("selected","selected");
	$("#selectvisitplan").find("option").eq(0).attr("selected","selected");
	$("#selectmission").find("option").eq(0).attr("selected","selected");
	$("#selectbussiness").find("option").eq(0).attr("selected","selected");
	$("#selectclient").find("option").eq(0).attr("selected","selected");
}

//--------------------------------------------------搜索相关------------------------------------------------------------------------------
var timeflag1=0;
$(function search () {

		
//	 if(timeflag1==0){
		 setTimeout(search, 1000);
//		 timeflag1=1;
//	 }
	
   var resarray=new Array();
   resarray=arrUserName;
//  alert(arrUserName);
$("#usernameid").autocomplete(resarray,{
        minChars: 0, // 双击空白文本框时显示全部提示数据
        max:100,
        formatItem: function (data, i, total) {
            return  data[0] ; // 改变匹配数据显示的格式
        },
        formatMatch: function (data, i, total) {
            return data[0];
        },
        formatResult: function (data) {
            return data[0];
        }
    }).result(SearchCallback); 
    function SearchCallback(event, data, formatted) {
//$(".tip").show().html("您的选择是：" + (!data ? "空" : formatted));
    }
});

var timeflag2=0;
$(function searchclient () {
	setTimeout(searchclient, 1000);
   var resarray=new Array();
   resarray=arrClientName;
  
$("#clienttimeid").autocomplete(resarray,{
        minChars: 0, // 双击空白文本框时显示全部提示数据
        max:100,
        formatItem: function (data, i, total) {
            return  data[0] ; // 改变匹配数据显示的格式
        },
        formatMatch: function (data, i, total) {
            return data[0];
        },
        formatResult: function (data) {
            return data[0];
        }
    }).result(SearchCallback); 
    function SearchCallback(event, data, formatted) {
//$(".tip").show().html("您的选择是：" + (!data ? "空" : formatted));
    }
});


$(function searchclienetsubmits () {
	setTimeout(searchclienetsubmits, 1000);
   var resarray=new Array();
   resarray=arrClientSubmitName;
  
$("#clientsubmittimeid").autocomplete(resarray,{
        minChars: 0, // 双击空白文本框时显示全部提示数据
        max:100,
        formatItem: function (data, i, total) {
            return  data[0] ; // 改变匹配数据显示的格式
        },
        formatMatch: function (data, i, total) {
            return data[0];
        },
        formatResult: function (data) {
            return data[0];
        }
    }).result(SearchCallback); 
    function SearchCallback(event, data, formatted) {
//$(".tip").show().html("您的选择是：" + (!data ? "空" : formatted));
    }
});

function searchtime() {
		var searchtype=curbut;
		var time=document.getElementById("usetimeid").value;
		var x=usedetailtab;
		var id=$(x).find("th").eq(6).html();
		var name=$(x).find("th").eq(1).html();
		var account=$(x).find("th").eq(0).html();
		
		
		if(curbut==1&&time!=""){
			employeedateattendance(id,name,time,account)
		}else if(curbut==1&&time==""){
			showuseattendance(x);
		}else if(curbut==2&&time!=""){
		
		}else if(cutbut==2&&time==""){
		
		}
		
	}


//-----------------------------------------------------------------出差相关----------------------------------------------------------
function showSelectBussiness() {
	var select=document.getElementById("selectbussiness");
	$("#selectattendance").find("option").eq(0).attr("selected","selected");
	$("#selectvisitplan").find("option").eq(0).attr("selected","selected");
	$("#selectmission").find("option").eq(0).attr("selected","selected");
//	$("#selectbussiness").find("option").eq(0).attr("selected","selected");
	$("#selectclient").find("option").eq(0).attr("selected","selected");
	 if(select.selectedIndex==1){ 
		 showbussiness();
	 }else if(select.selectedIndex==2){
		 showbussinessbadrecore();
	 }
}

function undobussiness() {
	var x=tabbussiness;
	var bussinessId=$(x).find("th").eq(0).html();
	var state=$(x).find("th").eq(7).html();
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
		showbussiness()();
			}else {
				alert("失败！");closepage();
			}
	
	})
}

function undobussinesswithundoactivity() {
	var bussinessid=$(".bussinessundo").find("div").eq(0).find("input").val();
	var date=$(".bussinessundo").find("div").eq(1).find("input").val();
	var reson=$(".bussinessundo").find("div").eq(2).find("input").val();
	$.getJSON("./WUndoBussinessUndoActivityServlet",{BussinessId:bussinessid,BussinessUndoReason:reson,BussinessUndoTime:date},function(outjson){
		if(	outjson.check){alert("成功！");closepage();
		showbussiness()();
			}else {
				alert("失败！");closepage();
			}
	
	})
}
function undobussinessok( ) {
	var bussinessid=$(".bussinessundo").find("div").eq(0).find("input").val();
	var date=$(".bussinessundo").find("div").eq(1).find("input").val();
	var reson=$(".bussinessundo").find("div").eq(2).find("input").val();
	$.getJSON("./WUndoBussinessServlet",{BussinessId:bussinessid,BussinessUndoReason:reson,BussinessUndoTime:date},function(outjson){
		if(	outjson.check){alert("成功！");closepage();
		showbussiness()();
			}else {
				alert("失败！");closepage();
			}
	
	})
}


function seebussiness() {
	var x=tabbussiness;
	var bussinessid=$(x).find("th").eq(0).html();
	var bussinesssideaddress=$(x).find("th").eq(1).html();
	var bussinesscontent=$(x).find("th").eq(2).html();
	var bussinessregistertime=$(x).find("th").eq(3).html();
	var bussinessintime=$(x).find("th").eq(4).html();
	var bussinessouttime=$(x).find("th").eq(5).html();
	var bussinessreturntime=$(x).find("th").eq(6).html();
	var bussinessstete=$(x).find("th").eq(7).html();
	var bussinessinaddress=$(x).find("th").eq(8).html();
	var bussinessoutaddress=$(x).find("th").eq(9).html();
	
	
	

	
	
//	var clientname=$(x).find("th").eq(3).html();
//	var state=$(x).find("th").eq(4).html();
//	var missionconclusionId=$(x).find("th").eq(0).html();
//		$.getJSON("./WGetMissionConclusionServlet",{MissionConclusionId:missionconclusionId},function(outjson){
			$(".group-list").html("");
//			var paths=outjson.MissionAccessoryPath.split("/");
			$(".group-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
		  			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="showbussiness()"></img><div style="margin-top:-25px;"><font>出差详情</font></div></div>'+
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


function checkbussiness() {
	var x=tabbussiness;
	var bussienssid=$(x).find("th").eq(0).html();
	var state=$(x).find("th").eq(7).html();
	
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
	var x=tabbussiness;
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
		showbussiness();
			}else {
				alert("失败！");closepage();
			}
	
	})
}

function checkbussineOK(num) {
	var x=tabbussiness;
	var bussienssid=$(x).find("th").eq(0).html();
	var busstate=0;
	if(num==1){
		busstate=3;
	}else if(num==2){
		busstate=4;
	}
	
	$.getJSON("./WCheckBussinessServlet",{BussinessId:bussienssid,BussinessState:busstate},function(outjson){
		if(outjson.check){
			showbussiness();
			alert("成功");
			closepage();
		}else {
			showbussiness();
			alert("失败");
			closepage();
		}
			
	});
}


function showbussiness() {
		var x=usedetailtab;
		$(".group-list").html("");

	 			$(".group-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
	 		  			'<div class="group-title" ><font>出差记录</font>'+
	 		  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
	 	 	  			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='undobussiness()' >撤销</div>"+
	 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='seebussiness()'>查看</div>"+
	 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='checkbussiness()'>审核</div>"+
	 					'</div>'+
	 		  			'<table id="tab"   style="font-size:13px;" width="100%">'+
	 		               '<tbody>'+
	 		                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
	 		                    '<th>出差号</th>'+
	 		                    '<th>出差目的地</th>'+
	 		                    '<th>出差内容</th>'+
	 		                    '<th>出差登记时间</th>'+
//	 		                    '<th>出差抵达地址</th>'+
	 		                    '<th>出差抵达时间</th>'+
//	 		                    '<th>出差离开地址</th>'+
	 		                    '<th>出差离开时间</th>'+
	 		                    '<th>出差归来时间</th>'+
	 		                    '<th>出差状态</th>'+
	 		                '</tr>'+
	 		               '</tbody>'+
	 		                
	 		                 
	 		                '<tbody  class="thisgroup">'+
	 		                	
	 		                '</tbody>'+
	 		                '</table>'+
	 		             
	 		                
	 		                
	 		  		'</div>');
	 			
	 		var id=$(x).find("th").eq(6).html();
	 		$.getJSON("./WGetAllEmployeeBussinessServlet",{EmployeeId:id},function(outjson){
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
//	 									'<th  style="font-weight: normal; width:60px; height:30px;"></img><img  src="./pic/chuchaibangding.png" style="height:30px;width:30px;cursor: pointer;display: '+bind+';float:right" title="出差绑定"></img></th>'+
	 								    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessId+'</th>'+
	 								    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessSideAddress+'</th>'+
	 								    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessContent+'</th>'+
	 								    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessRegisterTime+'</th>'+
	 								    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessInTime+'</th>'+
	 								   	'<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessOutTime+'</th>'+
	 								   	'<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessReturnTime+'</th>'+
	 								    '<th  style="font-weight: bold; color:'+co+';">'+state+'</th>'+
	 								    '<th  style="font-weight: normal;  display: none;">'+outjson.BussinessList[i].BussinessInAddress+'</th>'+
	 								    '<th  style="font-weight: normal;  display: none;">'+outjson.BussinessList[i].BussinessOutAddress+'</th>'+
//	 								    '<th  style="font-weight: normal; display: none;">'+outjson.BussinessList[i].MissionBussinessBandState+'</th>'+
//	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanCycleNumber+'</th>'+
//	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanDays+'</th>'+
//	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitBussinessBandState+'</th>'+
//	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].ClientId+'</th>'+
	 									'</tr>')
	 						}
	 					}
	 			
	 		});
	 		tabbussiness=null;
}

function showbussinessbadrecore() {
	
	var x=usedetailtab;
	$(".group-list").html("");

 			$(".group-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
 		  			'<div class="group-title" ><font>出差不良记录</font>'+
 		  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
// 	 	  			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='undobussiness()' >撤销</div>"+
// 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='seebussiness()'>查看</div>"+
// 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='checkbussiness()'>审核</div>"+
 					'</div>'+
 		  			'<table id="tab"   style="font-size:13px;" width="100%">'+
 		               '<tbody>'+
 		                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
 		                    '<th>出差不良记录号</th>'+
 		                    '<th>出差号</th>'+
 		                    '<th>不良记录时间</th>'+
 		                    '<th>不良记录原因</th>'+
 		                '</tr>'+
 		               '</tbody>'+
 		                
 		                 
 		                '<tbody  class="thisgroup">'+
 		                	
 		                '</tbody>'+
 		                '</table>'+
 		             
 		                
 		                
 		  		'</div>');
 			
 		var id=$(x).find("th").eq(6).html();
 		$.getJSON("./WGetEmployeeBussinessBadRecallServlet",{EmployeeId:id},function(outjson){
 			for ( var i = 0; i < outjson.BussinessBadrecordList.length; i++) {
 				
 					for ( var j = 0; j < $(".group-one").length; j++) {
 							$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;	background-color: rgb(232,251,250);">'+
 								    '<th  style="font-weight: normal; ">'+outjson.BussinessBadrecordList[i].BussinessBadrecordId+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.BussinessBadrecordList[i].BussinessId+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.BussinessBadrecordList[i].BussinessBadrecordTime+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.BussinessBadrecordList[i].BussinessBadrecordReason+'</th>'+
// 								    '<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessInTime+'</th>'+
// 								   	'<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessOutTime+'</th>'+
// 								   	'<th  style="font-weight: normal; ">'+outjson.BussinessList[i].BussinessReturnTime+'</th>'+
// 								    '<th  style="font-weight: bold; color:'+co+';">'+state+'</th>'+
// 								    '<th  style="font-weight: normal;  display: none;">'+outjson.BussinessList[i].BussinessInAddress+'</th>'+
// 								    '<th  style="font-weight: normal;  display: none;">'+outjson.BussinessList[i].BussinessOutAddress+'</th>'+
 									'</tr>')
 						}
 					}
 			
 		});
 		tabbussiness=null;
	
	
	
}


//-----------------------------------------------------------------任务相关----------------------------------------------------------
function showSelectMission() {
//	var x=usedetailtab;
	var select=document.getElementById("selectmission");
	$("#selectattendance").find("option").eq(0).attr("selected","selected");
	$("#selectvisitplan").find("option").eq(0).attr("selected","selected");
//	$("#selectmission").find("option").eq(0).attr("selected","selected");
	$("#selectbussiness").find("option").eq(0).attr("selected","selected");
	$("#selectclient").find("option").eq(0).attr("selected","selected");
	 if(select.selectedIndex==1){ 
		 showmission();
	 }else if(select.selectedIndex==2){
		 showmissionconclusion();
	 }else if(select.selectedIndex==3){
		 showmissiondelay();
	 }
}
function undomission() {
	var x=tabmission;
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
//	alert(missionid);
	$.getJSON("./WUndoMissionServclet",{MissionId:missionid,MissionUndoReason:reson,MissionUndoTime:date},function(outjson){
		if(	outjson.check){alert("成功！");closepage();
		showmission();
			}else {
				alert("失败！");closepage();
			}
	
	})
}

function seemission() {
	var x=tabmission;
	var missionid=$(x).find("th").eq(1).html();
	var pubdate=$(x).find("th").eq(2).html();
	var content=$(x).find("th").eq(3).html();
	var endtime=$(x).find("th").eq(4).html();
	var state=$(x).find("th").eq(5).html();
	var bbandstate=$(x).find("th").eq(6).html();
	
	
	
	if(x==null){
		alert("请选择需要操作的对象");
	}else{
	$(".group-list").html("");

		$(".group-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
	  			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="showmission()"></img><div style="margin-top:-25px;"><font>任务记录详情</font></div></div>'+
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
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">出差绑定号</font></div>'+	
					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+bbandstate+'</div>'+
					
					
				'</div>'+
   			'</div>'+
   			
   			'<div class="ground-one-right" style="float:left;width:49%;height:100%;border-left:2px;border-left-color:black;border-left-style:dashed;">'+
   				'<img src="./pic/missionbackgro.jpg"  style="width:100%;height:100%"> '+ 
	  		'</div>');
		
	
		
	}
}



function checkmission() {
	var x=tabmission;
//	var clientname=$(x).find("th").eq(3).html();
	var state=$(x).find("th").eq(5).html();
	var missionid=$(x).find("th").eq(1).html();
	if(state!="未审核"){
		alert("该任务不需要审核");
	}else{
		$.getJSON("./WGetEmployeeMissionConclusionServlet",{MissionId:missionid},function(outjson){
			$(".group-list").html("");
			var paths=outjson.MissionAccessoryPath.split("/");
			$(".group-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
		  			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="showmission()"></img><div style="margin-top:-25px;"><font>总结详情</font></div></div>'+
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
					'<div style="margin-left:113px;"><div class="usebut"  style="font-size: 14px;width: 76px;height: 35px;line-height: 33px;margin-left:50px;" onclick="missioncheck(1)">审核通过</div><div class="usebut" style="margin-left:40px;font-size: 14px;width: 76px;height: 35px;line-height: 33px;" onclick="missioncheck(2)">审核不通过</div></div>'+
				'</div>'+
		  		'</div>');
			
		})
	}
	
	
}

function missioncheck(x) {
	var mis=tabmission;
	var missionid= $(mis).find("th").eq(1).html();
	var missioncon=$("#misconcluid").html();
	$.getJSON("./WCheckMissionConclusionServlet",{MissionId:missionid,MissionConclusionId:missioncon,MissionCheck:x,},function(outjson){
		if(outjson.check){
			showmission();
			alert("成功");
		}else {
			showmission();
			alert("失败");
		}
			
	});
}

function delmissionout() {
	var mis=tabmission;
	var missionid=$(mis).find("th").eq(1).html();
	var state=$(mis).find("th").eq(5).html();
	
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
	var mis=tabvisitplan;
	var missionid=$(mis).find("th").eq(1).html();
	var date=new Date();
	var strdate=date.format("yyyy/MM/dd");
	var user=usedetailtab;
	var userid=$(user).find("th").eq(6).html();
	if(x==1){
		closepage();
		
		$(".shadow").show();
		$(".pagehead font").html("延期处理")
		$(".showpage").css("width","280px");
		$(".showpage").css("height","200px");
		$(".showpage").css("margin-left","600px");
		$(".pagemain").html("<div class=userset>"+
					"<div><a>拜访计划 : </a>&nbsp"+missionid+"</div>"+
	  				"<div><a>员工名称 : </a>&nbsp"+myname+"</div>"+
	  				"<div><a>延期时间：</a>"+strdate+"</div>"+
	  				"<div><a>延期期限：</a><input id=deltimeid  onclick=SelectDate(this,\'yyyy/MM/dd\') style='font-size:17px;width:100px;'></input></div>"+
	  				"<div style='display:none'>"+userid+"</div>"+
	  				"<div style='margin-left: 30px;margin-top: 10px;'><button onclick='delmissionoutOKOK()' class=but1>确定</button><button class=but1 onclick='closeshadow()'>取消</button></div>"+
	  				"</div>");
	}else if(x==2){
		$.getJSON("./WAbandonMissionServlet",{MissionId:missionid},function(outjson){
			if(outjson.check){
				showmission();
				alert("成功");
				closepage();
			}else {
				showmission();
				alert("失败");
				closepage();
			}
				
		});
	}
	
}
function delmissionoutOKOK() {
	var mis=tabmission;
	var missionId=$(mis).find("th").eq(1).html();
	var endtime=$("#deltimeid").val();
	var date=new Date();
	var strdate=date.format("yyyy/MM/dd");
	if(!duibi(strdate, endtime)){
		alert("延期期限不能小于延期时间")
	}else{
		$.getJSON("./WDelayMissionServlet",{MissionId:missionId,MissionDelayDeadline:endtime,MissionDelayTime:strdate},function(outjson){
			if(outjson.check){
				showmission();
				alert("成功");
				closepage();
			}else {
				showmission();
				alert("失败");
				closepage();
			}
				
		});
	}
}


function showmission() {
	var x=usedetailtab;
	$(".group-list").html("");

 			$(".group-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
 		  			'<div class="group-title" ><font>任务记录</font>'+
 		  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
 	 	  			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='undomission()' >撤销</div>"+
 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='seemission()'>查看</div>"+
 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='checkmission()'>审核</div>"+
 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='delmissionout()'>过期处理</div>"+
 					'</div>'+
 		  			'<table id="tab"   style="font-size:13px;" width="100%">'+
 		               '<tbody>'+
 		                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
 		                	'<th>标志</th>'+
 		                    '<th>任务号</th>'+
 		                    '<th>发布时间</th>'+
 		                    '<th>任务内容</th>'+
 		                    '<th>任务期限</th>'+
 		                    '<th>任务状态</th>'+
 		                '</tr>'+
 		                '</tbody>'+
 		                
 		                 
 		                '<tbody  class="thisgroup">'+
 		                	
 		                '</tbody>'+
 		                '</table>'+
 		             
 		                
 		                
 		  		'</div>');
 			
 		var id=$(x).find("th").eq(6).html();
 		$.getJSON("./WGetAllEmployeeMissionServlet",{EmployeeId:id},function(outjson){
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
 									'<th  style="font-weight: normal; width:60px; height:30px;"></img><img  src="./pic/chuchaibangding.png" style="height:30px;width:30px;cursor: pointer;display: '+bind+';float:right" title="出差绑定"></img></th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionId+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionPubdate+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionContent+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.MissionList[i].MissionDeadline+'</th>'+
 								    '<th  style="font-weight: bold; color:'+co+';">'+state+'</th>'+
 								    '<th  style="font-weight: normal; display: none;">'+outjson.MissionList[i].MissionBussinessBandState+'</th>'+
// 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanCycleNumber+'</th>'+
// 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanDays+'</th>'+
// 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitBussinessBandState+'</th>'+
// 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].ClientId+'</th>'+
 									'</tr>')
 						}
 					}
 			
 		});
 		tabmission=null;
}
function seemissionconclusion() {
	var x=tabmission;
//	var clientname=$(x).find("th").eq(3).html();
	var state=$(x).find("th").eq(4).html();
	var missionconclusionId=$(x).find("th").eq(0).html();
//	if(state!="未审核"){
//		alert("该任务不需要审核");
//	}else{
		$.getJSON("./WGetMissionConclusionServlet",{MissionConclusionId:missionconclusionId},function(outjson){
			$(".group-list").html("");
			var paths=outjson.MissionAccessoryPath.split("/");
			$(".group-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
		  			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="showmissionconclusion()"></img><div style="margin-top:-25px;"><font>总结详情</font></div></div>'+
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
//					'<div style="margin-left:113px;"><div class="usebut"  style="font-size: 14px;width: 76px;height: 35px;line-height: 33px;margin-left:50px;" onclick="missioncheck(1)">审核通过</div><div class="usebut" style="margin-left:40px;font-size: 14px;width: 76px;height: 35px;line-height: 33px;" onclick="missioncheck(2)">审核不通过</div></div>'+
				'</div>'+
		  		'</div>');
			
		})
//	}
	
	
}


function checkmissionconclusion() {
	var x=tabmission;
//	var clientname=$(x).find("th").eq(3).html();
	var state=$(x).find("th").eq(4).html();
	var missionconclusionId=$(x).find("th").eq(0).html();
	if(state!="未审核"){
		alert("该任务不需要审核");
	}else{
		$.getJSON("./WGetMissionConclusionServlet",{MissionConclusionId:missionconclusionId},function(outjson){
			$(".group-list").html("");
			var paths=outjson.MissionAccessoryPath.split("/");
			$(".group-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
		  			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="showmissionconclusion()"></img><div style="margin-top:-25px;"><font>总结详情</font></div></div>'+
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
					'<div style="margin-left:113px;"><div class="usebut"  style="font-size: 14px;width: 76px;height: 35px;line-height: 33px;margin-left:50px;" onclick="missionconclusioncheck(1)">审核通过</div><div class="usebut" style="margin-left:40px;font-size: 14px;width: 76px;height: 35px;line-height: 33px;" onclick="missionconclusioncheck(2)">审核不通过</div></div>'+
				'</div>'+
		  		'</div>');
			
		})
	}
	
	
}


function missionconclusioncheck(x) {
	var mis=tabmission;
	var missionid= $(mis).find("th").eq(5).html();
	var missionconclusionid=$(mis).find("th").eq(0).html();
//	var missioncon=$("#misconcluid").html();
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

function showmissionconclusion() {
	var x=usedetailtab;
	$(".group-list").html("");

 			$(".group-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
 		  			'<div class="group-title" ><font>任务记录</font>'+
 		  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
// 	 	  			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='undomission()' >撤销</div>"+
 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='seemissionconclusion()'>查看</div>"+
 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='checkmissionconclusion()'>审核</div>"+
// 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='delmissionout()'>过期处理</div>"+
 					'</div>'+
 		  			'<table id="tab"   style="font-size:13px;" width="100%">'+
 		               '<tbody>'+
 		                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
// 		                	'<th>标志</th>'+
 		                    '<th>任务总结号</th>'+
 		                    '<th>提交时间</th>'+
 		                    '<th>任务内容</th>'+
 		                    '<th>任务总结</th>'+
 		                    '<th>审核结果</th>'+
 		                '</tr>'+
 		                '</tbody>'+
 		                
 		                 
 		                '<tbody  class="thisgroup">'+
 		                	
 		                '</tbody>'+
 		                '</table>'+
 		             
 		                
 		                
 		  		'</div>');
 			
 		var id=$(x).find("th").eq(6).html();
 		$.getJSON("./WGetAllEmployeeMissionConclusionServlet",{EmployeeId:id},function(outjson){
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
 								    '<th  style="font-weight: normal; ">'+outjson.MissionConclusionList[i].MissionContent+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.MissionConclusionList[i].MissionSummary+'</th>'+
 								    '<th  style="font-weight: bold; color:'+co+';">'+state+'</th>'+
 								   '<th  style="font-weight: normal;display:none; ">'+outjson.MissionConclusionList[i].MissionId+'</th>'+
 									'</tr>')
 						}
 					}
 			
 		});
 		tabmission=null;
}




function showmissiondelay() {
	
	var x=usedetailtab;
	$(".group-list").html("");

 			$(".group-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
 		  			'<div class="group-title" ><font>任务延期记录</font>'+
 		  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
// 	 	  			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='undomission()' >撤销</div>"+
// 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='seemissionconclusion()'>查看</div>"+
// 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='checkmissionconclusion()'>审核</div>"+
// 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='delmissionout()'>过期处理</div>"+
 					'</div>'+
 		  			'<table id="tab"   style="font-size:13px;" width="100%">'+
 		               '<tbody>'+
 		                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
 		                    '<th>任务延期号</th>'+
 		                    '<th>任务号</th>'+
 		                    '<th>延期时间</th>'+
 		                    '<th>延期期限</th>'+
 		                '</tr>'+
 		                '</tbody>'+
 		                
 		                 
 		                '<tbody  class="thisgroup">'+
 		                	
 		                '</tbody>'+
 		                '</table>'+
 		             
 		                
 		                
 		  		'</div>');
 			
 		var id=$(x).find("th").eq(6).html();
 		$.getJSON("./WGetEmployeeMissionDelayServlet",{EmployeeId:id},function(outjson){
 			for ( var i = 0; i < outjson.MissionDelayList.length; i++) {
 				
 					for ( var j = 0; j < $(".group-one").length; j++) {
 							$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;	background-color: rgb(232,251,250);">'+
 								    '<th  style="font-weight: normal; ">'+outjson.MissionDelayList[i].MissionDelayId+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.MissionDelayList[i].MissionId+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.MissionDelayList[i].MissionDelayTime+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.MissionDelayList[i].MissionDelayDeadline+'</th>'+
// 								    '<th  style="font-weight: bold; color:'+co+';">'+state+'</th>'+
// 								    '<th  style="font-weight: normal;display:none; ">'+outjson.MissionConclusionList[i].MissionId+'</th>'+
 									'</tr>')
 						}
 					}
 			
 		});
 		tabmission=null;
}

//------------------------------------------------------------------拜访相关--------------------------------------------------------
var visitplanselect=0;//如果是0就是拜访操作，1是考勤操作
function showSelectVisitPlan() {
//	var x=usedetailtab;
	var select=document.getElementById("selectvisitplan");
	$("#selectattendance").find("option").eq(0).attr("selected","selected");
//	$("#selectvisitplan").find("option").eq(0).attr("selected","selected");
	$("#selectmission").find("option").eq(0).attr("selected","selected");
	$("#selectbussiness").find("option").eq(0).attr("selected","selected");
	$("#selectclient").find("option").eq(0).attr("selected","selected");
	 if(select.selectedIndex==1){ 
		showvisitplan();
	 }else if(select.selectedIndex==2){
		 showvisitconclusion();
	 }else if(select.selectedIndex==3){
		 showvisitdelay();
	 }
}


function undovisitplan() {
	
	
	var x=tabvisitplan;
	if(x==null){
		alert("请选择需要操作的对象");
	}else{
		var visitplanid=$(x).find("th").eq(1).html();
		var state=$(x).find("th").eq(6).html();
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
		showvisitplan();
			}else {
				alert("失败！");closepage();
			}
	
	})
}

function seevisitplan() {
	var x=tabvisitplan;
	var visitplanid=$(x).find("th").eq(1).html();
	var pubdate=$(x).find("th").eq(2).html();
	var clientname=$(x).find("th").eq(3).html();
	var starttime=$(x).find("th").eq(4).html();
	var endtime=$(x).find("th").eq(5).html();
	var state=$(x).find("th").eq(6).html();
	var cycle=$(x).find("th").eq(7).html();
	var cycletype=$(x).find("th").eq(8).html();
	var cyclenumber=$(x).find("th").eq(9).html();
	var plandays=$(x).find("th").eq(10).html();
	var bbandstate=$(x).find("th").eq(11).html();
	var cliendid=$(x).find("th").eq(12).html();
	
	
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
	$(".group-list").html("");

		$(".group-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
	  			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="showvisitplan()"></img><div style="margin-top:-25px;"><font>拜访计划详情</font></div></div>'+
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
	var x=tabvisitplan;
	var clientname=$(x).find("th").eq(3).html();
	var state=$(x).find("th").eq(6).html();
	var visitplanid=$(x).find("th").eq(1).html();
	if(state!="未审核"){
		alert("该拜访计划不需要审核");
	}else{
		$.getJSON("./WGetVisitConclusionServlet",{VisitPlanId:visitplanid},function(outjson){
			$(".group-list").html("");
			var paths=outjson.VisitAccessoryPath.split("\\");
			$(".group-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
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
	var vit=tabvisitplan;
	var visitplanid=$(vit).find("th").eq(1).html();
	var vitcon=$("#vitconcluid").html();
	$.getJSON("./WCheckVisitConclusionServlet",{VisitPlanId:visitplanid,VisitConclusionId:vitcon,VisitCheck:x,},function(outjson){
		if(outjson.check){
			showvisitplan();
			alert("成功");
		}else {
			showvisitplan();
			alert("失败");
		}
			
	});
}

function delvisitout() {
	var vit=tabvisitplan;
	var visitplanid=$(vit).find("th").eq(1).html();
	var state=$(vit).find("th").eq(6).html();
	
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
	var vit=tabvisitplan;
	var visitplanid=$(vit).find("th").eq(1).html();
	var date=new Date();
	var strdate=date.format("yyyy/MM/dd");
	var user=usedetailtab;
	var userid=$(user).find("th").eq(6).html();
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
				showvisitplan();
				alert("成功");
				closepage();
			}else {
				showvisitplan();
				alert("失败");
				closepage();
			}
				
		});
	}
	
}
function delvisitoutOKOK() {
	var vit=tabvisitplan;
	var visitplanid=$(vit).find("th").eq(1).html();
	var user=usedetailtab;
	var userid=$(user).find("th").eq(6).html();
	var endtime=$("#deltimeid").val();
	var date=new Date();
	var strdate=date.format("yyyy/MM/dd");
	if(!duibi(strdate, endtime)){
		alert("延期期限不能小于延期时间")
	}else{
		$.getJSON("./WDelayVisitPlanServlet",{EmployeeId:userid,VisitPlanId:visitplanid,VisitPlanEndTime:endtime},function(outjson){
			if(outjson.check){
				showvisitplan();
				alert("成功");
				closepage();
			}else {
				showvisitplan();
				alert("失败");
				closepage();
			}
				
		});
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

function showvisitplan() {
	var x=usedetailtab;
	$(".group-list").html("");

 			$(".group-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
 		  			'<div class="group-title" ><font>拜访计划</font>'+
 		  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
 	 	  			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='undovisitplan()' >撤销</div>"+
 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='seevisitplan()'>查看</div>"+
 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='checkvisitplan()'>审核</div>"+
 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='delvisitout()'>过期处理</div>"+
 					'</div>'+
 		  			'<table id="tab"   style="font-size:13px;" width="100%">'+
 		               '<tbody>'+
 		                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
 		                	'<th>标志</th>'+
 		                    '<th>拜访计划号</th>'+
 		                    '<th>发布时间</th>'+
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
 			
 		var id=$(x).find("th").eq(6).html();
 		$.getJSON("./WGetEmployeeVisitPlanServlet",{EmployeeId:id},function(outjson){
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
 		tabvisitplan=null;
}



function seevisitconclusion() {
	var x=tabvisitplan;
	var clientname=$(x).find("th").eq(2).html();
	var state=$(x).find("th").eq(5).html();
	var visitconclusionid=$(x).find("th").eq(0).html();
		$.getJSON("./WGetVisitConclusionDetailServlet",{VisitConclusionId:visitconclusionid},function(outjson){
			$(".group-list").html("");
			var paths=outjson.VisitAccessoryPath.split("\\");
			$(".group-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
		  			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="showvisitconclusion()"></img><div style="margin-top:-25px;"><font>总结详情</font></div></div>'+
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
	var vit=tabvisitplan;
	var state=$(vit).find("th").eq(5).html();
	
	if(state!="未审核"){
		alert("该拜访总结不需要审核");
 	}else{
 		$(".shadow").show();
 		$(".pagehead font").html("审核拜访总结")
 		$(".showpage").css("width","280px");
 		$(".showpage").css("height","100px");
 		$(".showpage").css("margin-left","600px");
 		$(".pagemain").html("<div class=userset>"+
 	  				"<div style='margin-left: 30px;margin-top: 10px;'><button onclick='delvisitconclusionOK(1)'  class=but1>通过</button><button class=but1  onclick='delvisitconclusionOK(2)'  >不通过</button></div>");
 	}
}
function delvisitconclusionOK(x) {
//	alert(x);
	var vit=tabvisitplan;
	var state=$(vit).find("th").eq(5).html();
	var visitplanid=$(vit).find("th").eq(6).html();
	var visitconclusionid=$(vit).find("th").eq(0).html();
	$.getJSON("./WCheckVisitConclusionServlet",{VisitPlanId:visitplanid,VisitConclusionId:visitconclusionid,VisitCheck:x,},function(outjson){
		if(outjson.check){
			showvisitplan();
			alert("成功");
			closepage();
		}else {
			showvisitplan();
			alert("失败");
			closepage();
		}
			
	});
}


function showvisitconclusion() {
	var x=usedetailtab;
	$(".group-list").html("");

 			$(".group-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
 		  			'<div class="group-title" ><font>拜访总结记录</font>'+
 		  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='seevisitconclusion()'>查看</div>"+
 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='checkvisitconclusion()'>审核</div>"+
 					'</div>'+
 		  			'<table id="tab"   style="font-size:13px;" width="100%">'+
 		               '<tbody>'+
 		                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
 		                    '<th>拜访总结号</th>'+
 		                    '<th>提交时间</th>'+
 		                    '<th>客户名</th>'+
 		                    '<th>客户总结</th>'+
 		                    '<th>客户需求</th>'+
 		                    '<th>审核结果</th>'+
 		                '</tr>'+
 		                '</tbody>'+
 		                
 		                 
 		                '<tbody  class="thisgroup">'+
 		                	
 		                '</tbody>'+
 		                '</table>'+
 		             
 		                
 		                
 		  		'</div>');
 			
 		var id=$(x).find("th").eq(6).html();
 		$.getJSON("./WGetAllEmployeeVisitConclusionServlet",{EmployeeId:id},function(outjson){
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
 	 								    '<th  style="font-weight: normal; ">'+outjson.VisitConclusionList[i].ClientName+'</th>'+
 	 								    '<th  style="font-weight: normal; ">'+outjson.VisitConclusionList[i].VisitSummary+'</th>'+
 	 								    '<th  style="font-weight: normal; ">'+outjson.VisitConclusionList[i].VisitCommand+'</th>'+
 	 								    '<th  style="font-weight: bold; color:'+co+';">'+state+'</th>'+
 	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitConclusionList[i].VisitPlanId+'</th>'+
// 	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanCycleType+'</th>'+
// 	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanCycleNumber+'</th>'+
// 	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanDays+'</th>'+
// 	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitBussinessBandState+'</th>'+
// 	 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].ClientId+'</th>'+
 	 									'</tr>')
 	 						}
 	 					}
 		
 				
 				
 			
 		});
 		tabvisitplan=null;
}


function showvisitdelay() {
	var x=usedetailtab;
	$(".group-list").html("");

 			$(".group-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
 		  			'<div class="group-title" ><font>拜访延期记录</font>'+
 		  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
// 	 	  			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='undovisitplan()' >撤销</div>"+
// 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='seevisitplan()'>查看</div>"+
// 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='checkvisitplan()'>审核</div>"+
// 	  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='delvisitout()'>过期处理</div>"+
 					'</div>'+
 		  			'<table id="tab"   style="font-size:13px;" width="100%">'+
 		               '<tbody>'+
 		                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
 		                    '<th>拜访延期号</th>'+
 		                    '<th>拜访计划号</th>'+
 		                    '<th>延期时间</th>'+
 		                    '<th>延期期限</th>'+
 		                '</tr>'+
 		                '</tbody>'+
 		                
 		                 
 		                '<tbody  class="thisgroup">'+
 		                	
 		                '</tbody>'+
 		                '</table>'+
 		             
 		                
 		                
 		  		'</div>');
 			
 		var id=$(x).find("th").eq(6).html();
 		$.getJSON("./WGetEmployeeDelayServlet",{EmployeeId:id},function(outjson){
 			for ( var i = 0; i < outjson.VisitDelayList.length; i++) {
 					for ( var j = 0; j < $(".group-one").length; j++) {
 							$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;	background-color: rgb(232,251,250);">'+
 								    '<th  style="font-weight: normal; ">'+outjson.VisitDelayList[i].VisitDelayId+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.VisitDelayList[i].VisitPlanId+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.VisitDelayList[i].VisitDelayTime+'</th>'+
 								    '<th  style="font-weight: normal; ">'+outjson.VisitDelayList[i].VisitDelayDeadline+'</th>'+
 								 
// 								    '<th  style="font-weight: normal; ">'+outjson.VisitPlanList[i].VisitPlanEndTime+'</th>'+
// 								    '<th  style="font-weight: bold; color:'+co+';">'+state+'</th>'+
// 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanCycle+'</th>'+
// 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanCycleType+'</th>'+
// 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanCycleNumber+'</th>'+
// 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitPlanDays+'</th>'+
// 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].VisitBussinessBandState+'</th>'+
// 								    '<th  style="font-weight: normal; display: none;">'+outjson.VisitPlanList[i].ClientId+'</th>'+
 									'</tr>')
 						}
 					}
 			
 		});
 		tabvisitplan=null;
}

//-----------------------------------------------------用户小操作相关----------------------------------------------
function showSelect() {
	 if(document.getElementById("selectID").selectedIndex==0){ 

		 showuserlist();
	  
	 }else if(document.getElementById("selectID").selectedIndex==1){
		 showdel();
	 }
}

	
function userdetailclick(num) {
	var x=usedetailtab;
	changgebutsscolcr(num);
	if(num==1){
		showuserdetailtitle();
		showuseattendance(x);
		$("#butss1").attr("class","usebuttwo");
	}
	else if(num==2){
		showuserdetailtitle();
		
//		curbut=2;
	}
}     



//----------------------------------------------------------用户主界面大界面（主界面，在职，删除，更多，搜索）---------------------------------------------------

     function showuserlist() {
     	
     	$(".group-list").html("");
     	var array=new Array();
     	array.push("行政部");
     	array.push("销售部");
     	array.push("售后部");
     	array.push("配送部");
     	array.push("巡店部");

     	for ( var i = 0; i < array.length; i++) {
     			$(".group-list").append('<div class="group-one" style="overflow-x:hidden;background-color: rgb(248,254,254);"> '+
     		  			'<div class="group-title"><font>'+array[i]+'</font><img class="grouptitleimg" src="./pic/zaozuo.jpg" onclick="delgroup('+i+',this)"   style="float:right;margin-right: 20px;font-size: 12px;cursor:pointer"/><font style="cursor: pointer; float:right;margin-right:15px;font-size:13px;margin-top:3px;color:rgb(11,116,244);  font-weight:bold;"  onclick="moreuse(this)">更多</font></div>'+
     		  			'<table id="tab"   style="font-size:13px;" width="100%">'+
     		               '<tbody>'+
     		                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
     		            
     		                    '<th>工号</th>'+
     		             
     		                    '<th>员工名</th>'+
     		                    '<th>员工电话</th>'+
     		                    '<th>员工性别</th>'+
     		             
     		                    '<th>员工职务</th>'+
     		                 
     		                '</tr>'+
     		                '</tbody>'+
     		                
     		                 
     		                '<tbody  class="thisgroup">'+
     		                	
     		                '</tbody>'+
     		                '</table>'+
     		             
     		                
     		                
     		  		'</div>');
     			
     			
     		}
     		for ( var i = 0; i < 5; i++) {
     			$(".grouptitleimg").eq(i).hide();
     		}
     		
     		
     		$.getJSON("./EnterCommunitionServlet",{operation:0},function(outjson){
     			for ( var i = 0; i < outjson.EmployeeList.length; i++) {
     // alert(outjson.EmployeeList.length)
     					var EmployeeDepartment=outjson.EmployeeList[i].EmployeeDepartment;
     					for ( var j = 0; j < $(".group-one").length; j++) {
     						if (EmployeeDepartment==$(".group-one").eq(j).find("font").html()) {
     							$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;	background-color: rgb(232,251,250);">'+
     									'<th  style="font-weight: normal; width:200px">'+outjson.EmployeeList[i].EmployeeAccount+' </th>'+
     								    '<th  style="font-weight: normal; width:175px">'+outjson.EmployeeList[i].EmployeeName+'</th>'+
     								    '<th  style="font-weight: normal; width:260px">'+outjson.EmployeeList[i].EmployeePhone+'</th>'+
     								    '<th  style="font-weight: normal; width:130px">'+outjson.EmployeeList[i].EmployeeSex+'</th>'+
     								    '<th  style="font-weight: normal ;width:180px">'+outjson.EmployeeList[i].EmployeeJob+'</th>'+
     								    '<th  style="font-weight: normal; display:none;">'+outjson.EmployeeList[i].EmployeePassword+'</th>'+
     								    '<th  style="font-weight: normal; display:none;">'+outjson.EmployeeList[i].EmployeeId+'</th>'+
     									'</tr>')
     						}
     					}
     			}
     			
     		});
    	$.getJSON("./GetAllEmployeeNameServlet",function(outjson){
   			 arrUserName = outjson.EmployeeName;
   			 timeflag1=0;
   		});
   		
     }
     
     function moreuse(x) {
 		var xx=$(x).parent().find("font").eq(0).html();
 		$(".group-list").html("");
 		
  			$(".group-list").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
  		  			'<div class="group-title"><font>'+xx+'</font><img class="grouptitleimg" src="./pic/zaozuo.jpg" onclick="delgroup('+i+',this)"   style="float:right;margin-right: 20px;font-size: 12px;cursor:pointer"/></div>'+
  		  			'<table id="tab" style="font-size:13px;" width="100%">'+
  		               '<tbody>'+
  		                '<tr style="height:100%;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
  		            
  		                    '<th>工号</th>'+
  		             
  		                    '<th>员工名</th>'+
  		                    '<th>员工电话</th>'+
  		                    '<th>员工性别</th>'+
  		             
  		                    '<th>员工职务</th>'+
  		                 
  		                '</tr>'+
  		                '</tbody>'+
  		                
  		                 
  		                '<tbody  class="thisgroup">'+
  		                	
  		                '</tbody>'+
  		                '</table>'+
  		             
  		                
  		                
  		  		'</div>');
  			
  			
  		for ( var i = 0; i < 5; i++) {
  			$(".grouptitleimg").eq(i).hide();
  		}
  		
  		
  		$.getJSON("./WGetDepartmentEmployeeServlet",{EmployeeDepartment:xx},function(outjson){
  			for ( var i = 0; i < outjson.EmployeeList.length; i++) {
  					for ( var j = 0; j < $(".group-one").length; j++) {
  							$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;background-color: rgb(232,251,250);">'+
  									'<th  style="font-weight: normal; width:200px">'+outjson.EmployeeList[i].EmployeeAccount+' </th>'+
  								    '<th  style="font-weight: normal; width:175px">'+outjson.EmployeeList[i].EmployeeName+'</th>'+
  								    '<th  style="font-weight: normal; width:260px">'+outjson.EmployeeList[i].EmployeePhone+'</th>'+
  								    '<th  style="font-weight: normal; width:130px">'+outjson.EmployeeList[i].EmployeeSex+'</th>'+
  								    '<th  style="font-weight: normal ;width:180px">'+outjson.EmployeeList[i].EmployeeJob+'</th>'+
  								    '<th  style="font-weight: normal; display:none;">'+outjson.EmployeeList[i].EmployeePassword+'</th>'+
  								    '<th  style="font-weight: normal; display:none;">'+outjson.EmployeeList[i].EmployeeId+'</th>'+
  									'</tr>')
  					}
  			}
  			
  		});
 		
 	}
      
     
     function showdel(){
    		$(".group-list").html("");

    		$(".group-list").append('<div class="group-oneone" style="background-color: rgb(248,254,254);"> '+
    	  			'<div class="group-title"><font>已删员工列表</font></div>'+
    	  			'<table  style="font-size:13px;overflow-x:hidden;  " width="100%" >'+
    	               '<tbody>'+
    	                '<tr  style="height:100%;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
    	            
    	                    '<th>工号</th>'+
    	             
    	                    '<th>员工名</th>'+
    	                    '<th>员工电话</th>'+
    	                    '<th>员工性别</th>'+
    	             
    	                    '<th>员工职务</th>'+
    	                 
    	                '</tr>'+
    	                '</tbody>'+
    	                
    	                 
    	                '<tbody  class="thisgroup">'+
    	                	
    	                '</tbody>'+
    	                '</table>'+
    	             
    	                
    	                
    	  		'</div>');
    		
    		
    		$.getJSON("./WGetAllDelEmployeeServlet",function(outjson){
    			for ( var i = 0; i < outjson.EmployeeList.length; i++) {
    				for ( var j = 0; j < $(".group-oneone").length; j++) {
    					$(".group-oneone").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;background-color: rgb(232,251,250);">'+
    							'<th style="font-weight: normal; width:200px">'+outjson.EmployeeList[i].EmployeeAccount+' </th>'+
    						    '<th style="font-weight: normal; width:175px">'+outjson.EmployeeList[i].EmployeeName+'</th>'+
    						    '<th style="font-weight: normal; width:260px">'+outjson.EmployeeList[i].EmployeePhone+'</th>'+
    						    '<th style="font-weight: normal; width:130px">'+outjson.EmployeeList[i].EmployeeSex+'</th>'+
    						    '<th style="font-weight: normal ;width:180px">'+outjson.EmployeeList[i].EmployeeJob+'</th>'+
    						    '<th style="font-weight: normal; display:none;">'+outjson.EmployeeList[i].EmployeePassword+'</th>'+
    						    '<th style="font-weight: normal; display:none;">'+outjson.EmployeeList[i].EmployeeId+'</th>'+
    							'</tr>')
    				}
    			
    			}
    		});
    		
    	}





     function searchuse() {
     	var x=usedetailtab;
     	var name=document.getElementById("usernameid").value;
     	if(name==""){
     		showuserlist();
     	}else{

     		$(".group-list").html("");
     	    
     	    			$(".group-list").append('<div class="group-one" style="height:800px;background-color: rgb(248,254,254); "> '+
     	    		  			'<div class="group-title"><font>搜索结果</font><img class="grouptitleimg" src="./pic/zaozuo.jpg"   style="float:right;margin-right: 20px;font-size: 12px;cursor:pointer;"/></div>'+
     	    		  			'<table id="tab" style="font-size:13px;overflow-x:hidden;"  width="100%">'+
     	    		               '<tbody>'+
     	    		                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
     	    		            
     	    		                    '<th>工号</th>'+
     	    		             
     	    		                    '<th>员工名</th>'+
     	    		                    '<th>员工电话</th>'+
     	    		                    '<th>员工性别</th>'+
     	    		             
     	    		                    '<th>员工职务</th>'+
     	    		                 
     	    		                '</tr>'+
     	    		                '</tbody>'+
     	    		                
     	    		                 
     	    		                '<tbody  class="thisgroup">'+
     	    		                	
     	    		                '</tbody>'+
     	    		                '</table>'+
     	    		             
     	    		                
     	    		                
     	    		  		'</div>');
     	    			
     	    			
     	    		for ( var i = 0; i < 5; i++) {
     	    			$(".grouptitleimg").eq(i).hide();
     	    		}
     	    		
     	    		
     	    		$.getJSON("./WSearchEmployeeServlet",{EmployeeName:name},function(outjson){
     	    			for ( var i = 0; i < outjson.EmployeeList.length; i++) {
     	    					var EmployeeDepartment=outjson.EmployeeList[i].EmployeeDepartment;
     	    					for ( var j = 0; j < $(".group-one").length; j++) {
     	    							$(".group-one").eq(j).find(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;-moz-user-select:none;background-color: rgb(232,251,250);">'+
     	    									'<th  style="font-weight: normal; width:200px">'+outjson.EmployeeList[i].EmployeeAccount+' </th>'+
     	    								    '<th  style="font-weight: normal; width:175px">'+outjson.EmployeeList[i].EmployeeName+'</th>'+
     	    								    '<th  style="font-weight: normal; width:260px">'+outjson.EmployeeList[i].EmployeePhone+'</th>'+
     	    								    '<th  style="font-weight: normal; width:130px">'+outjson.EmployeeList[i].EmployeeSex+'</th>'+
     	    								    '<th  style="font-weight: normal ;width:180px">'+outjson.EmployeeList[i].EmployeeJob+'</th>'+
     	    								    '<th  style="font-weight: normal; display:none;">'+outjson.EmployeeList[i].EmployeePassword+'</th>'+
     	    								    '<th  style="font-weight: normal; display:none;">'+outjson.EmployeeList[i].EmployeeId+'</th>'+
     	    									'</tr>')
     	    						}
     	    			}
     	    			
     	    		});
     	}
         	
     }
//--------------------------------------------------用户管理主界面弹出框（既四小按钮）--------------------------------------------------------
     function userdetail() {
    		
    		var x=tabuser;
    		usedetailtab=tabuser;
    		if(x==null){
    			alert("请选中需要操作的员工");
    		}else{
    			var id=$(x).find("th").eq(6).html();
    			var name=$(x).find("th").eq(1).html();
    			var depar=$(x).find("th").eq(4).html();
    			myname=name;
    			mydepar=depar;
    			showuserdetailtitle();
    			showuseattendance(tabuser);
    			
    			$.getJSON("./WGetEmployeeClientNameServlet",{EmployeeId:id},function(outjson){
    				arrClientName = outjson.ClientName;
    			});
    		}
    		
    		
    		
    		x=null;
    		tabuser=null;
    	}

     
//用户删除
function delep() {
	var x=tabuser;
	var id=$(x).find("th").eq(6).html();
	if(x==null){
		alert("请选中需要操作的员工");
	}else{
		 if (confirm("确定删除该员工吗？")){
				$.getJSON("./WDelEmployeeServlet",{EmployeeId:id},function(outjson){
					if(outjson.check){
						alert("成功！");
						closepage();
						showuserlist();
					}else {
						alert("失败！");closepage();
					}
				})
				
			  }else{
			  }
	}
}

function addep() {
	$(".shadow").show();
	$(".pagehead font").html("添加员工")
	$(".showpage").css("width","280px");
	$(".showpage").css("height","250px");
	$(".showpage").css("margin-left","600px");
	$(".pagemain").html("<div class=usersetadd>"+
				"<div><a>员工账号   :</a><input  type='text'   style='margin-left: 7px;'/></div>"+
				"<div><a>员工名&nbsp&nbsp&nbsp :</a><input  type='text'style='margin-left: 10px;' /></div>"+
				"<div><a>员工电话：</a><input type='text' /></div>"+
				"<div><a>员工密码：</a><input type='text' /></div>"+
				"<div><a>员工性别：</a><select style='width:100px;'><option value ='男'>男</option><option value ='女'>女</option></select></div>"+
				"<div><a>员工职务：</a><select style='width:100px;'><option value ='销售员'>销售员</option><option value ='售后员'>售后员</option><option value ='配送员'>配送员</option><option value ='巡店员'>巡店员</option><option value ='督察员'>督察员</option></select></div>"+
				"<div style='margin-left: 30px;margin-top: 10px;'><button onclick='addepok()' class=but1>确定</button><button class=but1 onclick='closepage()'>取消</button></div>"+
				"</div>");

}

function addepok( ) {
	var account=$(".usersetadd").find("div").eq(0).find("input").val();
	var name=$(".usersetadd").find("div").eq(1).find("input").val();
	var phone=$(".usersetadd").find("div").eq(2).find("input").val();
	var pass=$(".usersetadd").find("div").eq(3).find("input").val();
	var sex=$(".usersetadd").find("div").eq(4).find("select").val();
	var job=$(".usersetadd").find("div").eq(5).find("select").val();
	var depart;
	var type=0;
	if(job=="销售员"){
		type=0;
		depart="销售部"
	}else if(job=="售后员"){
		type=2;
		depart="售后部"
	}else if(job=="配送员"){
		type=1;
		depart="配送部"
	}else if(job=="巡店员"){
		type=3;
		depart="巡店部"
	}else if(job=="督察员"){
		type=5;
		depart="行政部"
	}
	$.getJSON("./WAddEmployeeServlet",{EmployeeAccount:account,EmployeePassword:pass,EmployeeName:name,EmployeePhone:phone,EmployeeSex:sex,EmployeeDepartment:depart,EmployeeJob:job,EmployeeType:type},function(outjson){
		if(	outjson.check){alert("成功！");closepage();
		showuserlist();
			}else {
				alert("失败！");closepage();
			}
	
	})
}


function setuser() {
	var x=tabuser;
	if(x==null){
		alert("请选中需要操作的员工");
	}else{
		$(".shadow").show();
		$(".pagehead font").html("修改员工")
		$(".showpage").css("width","280px");
		$(".showpage").css("height","250px");
		$(".showpage").css("margin-left","600px");
		$(".pagemain").html("<div class=userset>"+
	  				"<div><a>员工名:</a><input id="+$(x).find("th").eq(0).html()+" type='text' style='margin-left: 29px;'value='"+$(x).find("th").eq(1).html()+"' /></div>"+
	  				"<div><a>员工电话：</a><input type='text' value='"+$(x).find("th").eq(2).html()+"'/></div>"+
	  				"<div><a>员工密码：</a><input type='text' value='"+$(x).find("th").eq(5).html()+"'/></div>"+
	  				"<div><a>员工性别：</a><select><option value ='男'>男</option><option value ='女'>女</option></select></div>"+
	  				"<div><a>员工职务：</a><select><option value ='销售员'>销售员</option><option value ='售后员'>售后员</option><option value ='配送员'>配送员</option><option value ='巡店员'>巡店员</option><option value ='督察员'>督察员</option></select></div>"+
	  				"<div style='margin-left: 30px;margin-top: 10px;'><button onclick='updateuserok("+$(x).find("th").eq(6).html()+")' class=but1>确定</button><button class=but1 onclick='closeshadow()'>取消</button></div>"+
	  				"</div>");
		var sex=$(x).find("th").eq(3).html();
		
		if(sex=="男"){
			$(".userset").find("div").eq(3).find("select").find("option").eq(0).attr("selected","selected");
		}else if(sex=="女"){
			$(".userset").find("div").eq(3).find("select").find("option").eq(1).attr("selected","selected");
		}
		
		var job=$(x).find("th").eq(4).html();
		if(job=="销售员"){
			$(".userset").find("div").eq(4).find("select").find("option").eq(0).attr("selected","selected");
		}else if(job=="售后员"){
			$(".userset").find("div").eq(4).find("select").find("option").eq(1).attr("selected","selected");
		}else if(job=="配送员"){
			$(".userset").find("div").eq(4).find("select").find("option").eq(2).attr("selected","selected");
		}else if(job=="巡店员"){
			$(".userset").find("div").eq(4).find("select").find("option").eq(3).attr("selected","selected");
		}else if(job=="督察员"){
			$(".userset").find("div").eq(4).find("select").find("option").eq(4).attr("selected","selected");
		}
	}
	
	
}

function updateuserok(x) {
	var name=$(".userset").find("div").eq(0).find("input").val();
	
	var phone=$(".userset").find("div").eq(1).find("input").val();
	var pass=$(".userset").find("div").eq(2).find("input").val();
	var sex=$(".userset").find("div").eq(3).find("select").val();
	var job=$(".userset").find("div").eq(4).find("select").val();
	var depart;
	var type=0;
	if(job=="销售员"){
		type=0;
		depart="销售部"
	}else if(job=="售后员"){
		type=2;
		depart="售后部"
	}else if(job=="配送员"){
		type=1;
		depart="配送部"
	}else if(job=="巡店员"){
		type=3;
		depart="巡店部"
	}else if(job=="督察员"){
		type=5;
		depart="行政部"
	}
	
	$.getJSON("./WUpdateEmployeeServlet",{
		EmployeeId:x,
		EmployeePassword:pass,
		EmployeeName:name,
		EmployeePhone:phone,
		EmployeeSex:sex,
		EmployeeDepartment:depart,
		EmployeeJob:job,
		EmployeeType:type},function(outjson){
		if(	outjson.check){alert("成功！");closepage();
		showuserlist();
			}else {
				alert("失败！");closepage();
			}
	
	})
}



//------------------------------------------------------------列表鼠标四个事件-------------------------------------------------------------
   
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
 	        tabuser=null;
 	        tabclient=null;
 	        tabvisitplan=null;
 	        tabmission=null;
 	        tabbussiness=null;
  	 }else{
  		 var x=69;
  		 var y=205;
	  	     var z=195;
	    	 tab.style.backgroundColor = setColor(x, y, z);
	    	 
	         if(curtab != null) curtab.style.backgroundColor = setColor(232,251,250);
	         curtab = tab;
	         tabuser=tab;
	         tabclient=tab;
	         tabvisitplan=tab;
	         tabmission=tab;
	         tabbussiness=tab;
  	 }
  	
   }
   
   function do_blcclick(tab) {
  	 tab.style.backgroundColor = setColor(255, 255, 255);
  	 tabuser=null;
  	 curtab=null;
  	 tabclient=null;
  	 tabvisitplan=null;
  	 tabmission=null;
     tabbussiness=null;
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



//--------------------------------------------------------------------------------------------------------------------


 //-----------------------------------------------------------客户相关---------------------------------------------------

 function showuserdetailclient(x) {
 	$(".group-list").html("");
 			$(".group-list").append('<div class="group-one" style="height:100%;background-color: rgb(248,254,254);"> '+
 	  			'<div class="group-title"><font>客户分配列表</font>'+ 
 	  			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
 	  			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='cancelDisbut()' >取消分配</div>"+
  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='updateclient()'>修改</div>"+
  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='seeclient()'>查看</div>"+
  				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='delclient()'>删除</div>"+
  			"</div>"+
 	  			'</div>'+
 	  			'<table id="tabclient" style="font-size:13px;" width="100%">'+
 	               '<tbody>'+
 	                '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60;">'+
 	                    '<th>客户名</th>'+
 	             
 	                    '<th>客户电话</th>'+
 	                    '<th>客户区域</th>'+
 	                    '<th>客户地址</th>'+
 	                    '<th>客户公司</th>'+
 	                '</tr>'+
 	                '</tbody>'+
 	                
 	                 
 	                '<tbody  class="thisgroup">'+
 	                	
 	                '</tbody>'+
 	                '</table>'+
 	             
 	                
 	                
 	  		'</div>');
 			
 			var id=$(x).find("th").eq(6).html();
 			$.getJSON("./WGetAllEmployeeClientServlet",{EmployeeId:id},function(outjson){
 				for ( var i = 0; i < outjson.ClientList.length; i++) {
 					var clientt="未分配";
 					if (outjson.ClientList[i].ClientState==0) {
 						clientt="未分配"
 					}else if(outjson.ClientList[i].ClientState==1){
 						clientt="已分配"
 					}else if(outjson.ClientList[i].ClientState==2){
 						clientt="未审核"
 					}else if(outjson.ClientList[i].ClientState==3){
 						clientt="已删除"
 					}
 					$(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;background-color: rgb(232,251,250);-moz-user-select:none;" ClientId='+outjson.ClientList[i].ClientId+'>'+
 							'<th style="font-weight: normal">'+outjson.ClientList[i].ClientName+'</th>'+
 						    '<th style="font-weight: normal">'+outjson.ClientList[i].ClientPhone+'</th>'+
 						    '<th style="font-weight: normal">'+outjson.ClientList[i].ClientArea+'</th>'+
 						    '<th style="font-weight: normal">'+outjson.ClientList[i].ClientAddress+'</th>'+
 						    '<th style="font-weight: normal">'+outjson.ClientList[i].ClientCompany+'</th>'+
 							'<th style="font-weight: normal; display:none;">'+outjson.ClientList[i].ClientId+'</th>'+
 							'</tr>')
 				}
 			});	
 			
 			tabclient=null;
 			curbut=2;
 }

 function searchclienet() {
 	var name=document.getElementById("clienttimeid").value;
 	
 	if(name==""){
 		var x=usedetailtab;
 		clientselect=1;
 		showclienttitle(clientselect);
 		showuserdetailclient(x);
 		curbut=2;
 	}else{
 		$(".group-list").html("");
 		$(".group-list").append('<div class="group-one" style="height:100%;background-color: rgb(248,254,254);"> '+
 				'<div class="group-title"><font>客户分配列表</font>'+ 
 				"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
 				"<div class=clientbut  style=' cursor:pointer; position: relative;'onclick='cancelDisbut()' >取消分配</div>"+
 				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='updateclient()'>修改</div>"+
 				"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='seeclient()'>查看</div>"+
 				"<div class=clientbut  style=' cursor:pointer; position: relative;'	onclick='delclient()'>删除</div>"+
 			"</div>"+
 				'</div>'+
 				'<table id="tabclient" style="font-size:13px;" width="100%">'+
 	           '<tbody>'+
 	            '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60;">'+
 	                '<th>客户名</th>'+
 	         
 	                '<th>客户电话</th>'+
 	                '<th>客户区域</th>'+
 	                '<th>客户地址</th>'+
 	                '<th>客户公司</th>'+
 	            '</tr>'+
 	            '</tbody>'+
 	            
 	             
 	            '<tbody  class="thisgroup">'+
 	            	
 	            '</tbody>'+
 	            '</table>'+
 	         
 	            
 	            
 			'</div>');
 		
// 		var id=$(x).find("th").eq(6).html();
 		$.getJSON("./WSearchClientServlet",{ClientName:name},function(outjson){
 			for ( var i = 0; i < outjson.ClientList.length; i++) {
 				var clientt="未分配";
 				if (outjson.ClientList[i].ClientState==0) {
 					clientt="未分配"
 				}else if(outjson.ClientList[i].ClientState==1){
 					clientt="已分配"
 				}else if(outjson.ClientList[i].ClientState==2){
 					clientt="未审核"
 				}else if(outjson.ClientList[i].ClientState==3){
 					clientt="已删除"
 				}
 				$(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;background-color: rgb(232,251,250);-moz-user-select:none;" ClientId='+outjson.ClientList[i].ClientId+'>'+
 						'<th style="font-weight: normal">'+outjson.ClientList[i].ClientName+'</th>'+
 					    '<th style="font-weight: normal">'+outjson.ClientList[i].ClientPhone+'</th>'+
 					    '<th style="font-weight: normal">'+outjson.ClientList[i].ClientArea+'</th>'+
 					    '<th style="font-weight: normal">'+outjson.ClientList[i].ClientAddress+'</th>'+
 					    '<th style="font-weight: normal">'+outjson.ClientList[i].ClientCompany+'</th>'+
 						'<th style="font-weight: normal; display:none;">'+outjson.ClientList[i].ClientId+'</th>'+
 						'</tr>')
 			}
 		});	
 		
 		tabclient=null;
 		curbut=2;
 	}
 	
 	
 	
 }


 function searchclienetsubmit() {
 	var name=document.getElementById("clientsubmittimeid").value;
 	
 	if(name==""){
 		var x=usedetailtab;
 		clientselect=2;
 		showclientsubmittitle(clientselect);
 		showuserdetailclientsubmit(x);
 		curbut=2;
 	}else{
 		$(".group-list").html("");
 		$(".group-list").append('<div class="group-one" style="height:100%;background-color: rgb(248,254,254);"> '+
 				'<div class="group-title"><font>客户提交列表</font>'+ 
 				"<div id=butclient style='height:25px;width:300px;float:right;margin-top:-20px; margin-left:900px;'>" +
 				"<div class=clientbut  style=' cursor:pointer; position: relative;'onclick='seeclient()'>查看</div>"+
 				"<div class=clientbut  style=' cursor:pointer; position: relative;'onclick='clientcheck()'>审核</div>"+
 			"</div>"+
 				'</div>'+
 				'<table id="tabclient" style="font-size:13px;" width="100%">'+
 	           '<tbody>'+
 	            '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60;">'+
 	                '<th>客户名</th>'+
 	         
 	                '<th>客户电话</th>'+
 	                '<th>客户区域</th>'+
 	                '<th>客户地址</th>'+
 	                '<th>客户公司</th>'+
 	            	'<th>提交时间</th>'+
 	                '<th>提交状态</th>'+
 	            '</tr>'+
 	            '</tbody>'+
 	            
 	             
 	            '<tbody  class="thisgroup">'+
 	            	
 	            '</tbody>'+
 	            '</table>'+
 	         
 	            
 	            
 			'</div>');
 		var x=usedetailtab;
// 		var id=$(x).find("th").eq(6).html();
 		var id=$(x).find("th").eq(6).html();
 		var zero=0;
 		$.getJSON("./GetClientSubmitServlet",{EmployeeId:id,operation:zero},function(outjson){
 		
 		
 				for ( var i = 0; i < outjson.ClientSubmitList.length; i++) {
 					if(name==outjson.ClientSubmitList[i].ClientName){
 					var clientt="未审核";
 					var co="rgb(0,0,0)"
 					if (outjson.ClientSubmitList[i].ClientSubmitState==0) {
 						clientt="未审核"
 						co="rgb(0,162,232)";
 					}else if(outjson.ClientSubmitList[i].ClientSubmitState==1){
 						clientt="已通过"
 						
 					}else if(outjson.ClientSubmitList[i].ClientSubmitState==2){
 						clientt="未通过"
 					}
 				
 					$(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;background-color: rgb(232,251,250);-moz-user-select:none;" ClientId='+outjson.ClientSubmitList[i].ClientId+'>'+
 							'<th style="font-weight: normal">'+outjson.ClientSubmitList[i].ClientName+'</th>'+
 						    '<th style="font-weight: normal">'+outjson.ClientSubmitList[i].ClientPhone+'</th>'+
 						    '<th style="font-weight: normal">'+outjson.ClientSubmitList[i].ClientArea+'</th>'+
 						    '<th style="font-weight: normal">'+outjson.ClientSubmitList[i].ClientAddress+'</th>'+
 						    '<th style="font-weight: normal">'+outjson.ClientSubmitList[i].ClientCompany+'</th>'+
 							'<th style="font-weight: normal; display:none;">'+outjson.ClientSubmitList[i].ClientId+'</th>'+
 							'<th style="font-weight: normal">'+outjson.ClientSubmitList[i].ClientSubmitTime+'</th>'+
 							'<th style="font-weight: bold;color:'+co+'">'+clientt+'</th>'+
 							'</tr>')
 				}
 			}
 			});	
 			
 			
 		tabclient=null;
 		curbut=2;
 	}
 	
 }

 function showuserdetailclientsubmit(x) {
 	
 	showclientsubmittitle(2);
 	$(".group-list").html("");
 	$(".group-list").append('<div class="group-one" style="height:100%;background-color: rgb(248,254,254);"> '+
 			'<div class="group-title"><font>客户提交列表</font>'+ 
 			"<div id=butclient style='height:25px;width:300px;float:right;margin-top:-20px; margin-left:900px;'>" +
 			"<div class=clientbut  style=' cursor:pointer; position: relative;'onclick='seeclient()'>查看</div>"+
 			"<div class=clientbut  style=' cursor:pointer; position: relative;'onclick='clientcheck()'>审核</div>"+
 		"</div>"+
 			'</div>'+
 			'<table id="tabclient" style="font-size:13px;" width="100%">'+
            '<tbody>'+
             '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60;">'+
                 '<th>客户名</th>'+
                 '<th>客户电话</th>'+
                 '<th>客户区域</th>'+
                 '<th>客户地址</th>'+
                 '<th>客户公司</th>'+
               	'<th>提交时间</th>'+
                 '<th>提交状态</th>'+
             '</tr>'+
             '</tbody>'+
             
              
             '<tbody  class="thisgroup">'+
             	
             '</tbody>'+
             '</table>'+
          
             
             
 		'</div>');
 	
 	var id=$(x).find("th").eq(6).html();
 	var zero=0;
 	$.getJSON("./GetClientSubmitServlet",{EmployeeId:id,operation:zero},function(outjson){
 		arrClientSubmitName=new Array();
 		for ( var i = 0; i < outjson.ClientSubmitList.length; i++) {
 			arrClientSubmitName.push(outjson.ClientSubmitList[i].ClientName);
 			
 			var clientt="未审核";
 			var co="rgb(0,0,0)"
 			if (outjson.ClientSubmitList[i].ClientSubmitState==0) {
 				clientt="未审核"
 				co="rgb(0,162,232)";
 			}else if(outjson.ClientSubmitList[i].ClientSubmitState==1){
 				clientt="已通过"
 				
 			}else if(outjson.ClientSubmitList[i].ClientSubmitState==2){
 				clientt="未通过"
 			}
 		
 			$(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;background-color: rgb(232,251,250);-moz-user-select:none;" ClientId='+outjson.ClientSubmitList[i].ClientId+'>'+
 				
 					'<th style="font-weight: normal">'+outjson.ClientSubmitList[i].ClientName+'</th>'+
 				    '<th style="font-weight: normal">'+outjson.ClientSubmitList[i].ClientPhone+'</th>'+
 				    '<th style="font-weight: normal">'+outjson.ClientSubmitList[i].ClientArea+'</th>'+
 				    '<th style="font-weight: normal">'+outjson.ClientSubmitList[i].ClientAddress+'</th>'+
 				    '<th style="font-weight: normal">'+outjson.ClientSubmitList[i].ClientCompany+'</th>'+
 					'<th style="font-weight: normal; display:none;">'+outjson.ClientSubmitList[i].ClientId+'</th>'+
 					'<th style="font-weight: normal">'+outjson.ClientSubmitList[i].ClientSubmitTime+'</th>'+
 					'<th style="font-weight: bold;color:'+co+'">'+clientt+'</th>'+
 					'</tr>')
 		}
 	});	
 	
 	tabclient=null;
 	curbut=2;
 	
 }




 function updateclient() {

 	var x=tabclient;
 	var clientname=$(x).find("th").eq(0).html();
 	var clientnumber=$(x).find("th").eq(1).html();
 	var clientarea=$(x).find("th").eq(2).html();
 	var clientaddress=$(x).find("th").eq(3).html();
 	var clientcompany=$(x).find("th").eq(4).html();
 	var clientid=$(x).find("th").eq(5).html();
 	if(x==null){
 		alert("请选中需要操作的客户")
 	}else{
 		$(".group-list").html("");
 		$(".group-list").append('<div class="group-one" style="height:100%;background-color: rgb(248,254,254);"> '+
   			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="updateclientclean()"></img><div style="margin-top:-25px;"><font>修改客户</font></div></div>'+
   			'<div class="ground-one-left" style="float:left;width:50%;height:100%;">'+
   				'<div style="position:relative; height:500px;margin-left:100px;">'+
   					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;margin-top:10px;">客户号</font></div>'+	
   					'<div class="main-input" style="margin-left:10px;margin-top:5px;margin-top:5px;padding-top:7px;" >'+clientid+'</div>'+
   					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户名</font></div>'+	
   					'<input  class="main-input" style="margin-left:10px;margin-top:0px;margin-top:5px; width:289px"value='+clientname+' ">'+
   					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户公司</font></div>'+	
   					'<input  class="main-input" style="margin-left:10px;margin-top:0px;margin-top:5px; width:289px"value='+clientcompany+' >'+
   					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户号码</font></div>'+	
   					'<input  class="main-input" style="margin-left:10px;margin-top:0px;margin-top:5px; width:289px" value='+clientnumber+'>'+
   					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户区域</font></div>'+	
   					'<input id=updateclientarea class="main-input" style="margin-left:10px;margin-top:0px;margin-top:5px; width:289px" value='+clientarea+'>'+
   					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户地址</font></div>'+	
   					'<input id=updateclientaddress class="main-input" style="margin-left:10px;margin-top:0px;margin-top:5px; width:289px" value='+clientaddress+'>'+
   					
   				'</div>'+
   				'<div style="margin-left:113px;"><div class="usebut" onclick="changecustomerok(this)" style="font-size: 14px;width: 76px;height: 35px;line-height: 33px;">提交</div><div class="usebut" style="font-size: 14px;width: 76px;height: 35px;line-height: 33px;" onclick="updateclientclean()">取消</div></div>'+
   			'</div>'+
   			
   			
   			
   			'<div class="ground-one-right" style="float:left;width:50%;height:100%;">'+
   				'<div id=updateclientcontainer  style="width:100%;height:95%;"></div>'+
   			'</div>'
 		
 		
 		
 		
 		
 		);
 		
 		withmap(clientaddress,clientarea);
 	}
 	
 	
 }
 function seeclient() {


 		var x=tabclient;
 		var clientname=$(x).find("th").eq(0).html();
 		var clientnumber=$(x).find("th").eq(1).html();
 		var clientarea=$(x).find("th").eq(2).html();
 		var clientaddress=$(x).find("th").eq(3).html();
 		var clientcompany=$(x).find("th").eq(4).html();
 		var clientid=$(x).find("th").eq(5).html();
 		if(x==null){
 			alert("请选中需要操作的客户")
 		}else{
 			$(".group-list").html("");
 			$(".group-list").append('<div class="group-one" style="height:100%;background-color: rgb(248,254,254);"> '+
 	  			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="updateclientclean()"></img><div style="margin-top:-25px;"><font>客户详情</font></div></div>'+
 	  			'<div class="ground-one-left" style="float:left;width:50%;height:100%;">'+
 	  				'<div style="position:relative; height:500px;margin-left:100px;">'+
 	  					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;margin-top:10px;">客户号</font></div>'+	
 	  					'<div class="main-input" style="margin-left:10px;width:289px;padding-top:7px;margin-top:10px;padding-top:10px;" >'+clientid+'</div>'+
 	  					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户名</font></div>'+	
 	  					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+clientname+'</div>'+
 	  					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户公司</font></div>'+	
 	  					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+clientcompany+'</div>'+
 	  					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户号码</font></div>'+	
 	  					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+clientnumber+'</div>'+
 	  					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户区域</font></div>'+	
 	  					'<div id=updateclientarea class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+clientarea+'</div>'+
 	  					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户地址</font></div>'+	
 	  					'<div id=updateclientaddress class="main-input" style="margin-left:10px;width:289px;margin-top:10px;padding-top:10px;">'+clientaddress+'</div>'+
 	  					
 	  				'</div>'+
// 	  				'<div style="margin-left:113px;"><div class="usebut" onclick="changecustomerok(this)" style="font-size: 14px;width: 76px;height: 35px;line-height: 33px;">提交</div><div class="usebut" style="font-size: 14px;width: 76px;height: 35px;line-height: 33px;" onclick="updateclientclean()">取消</div></div>'+
 	  			'</div>'+
 	  			
 	  			
 	  			
 	  			'<div class="ground-one-right" style="float:left;width:50%;height:100%;">'+
 	  				'<div id=updateclientcontainer  style="width:100%;height:95%;"></div>'+
 	  			'</div>'
 			
 			
 			
 			
 			);
 			
 			withmapsee(clientaddress,clientarea);
 		}
 		
 		
 }


 function clientcheck() {
 	var x=tabclient;
 	var clientsubmitstate=$(x).find("th").eq(7).html();
 	var clienid=$(x).find("th").eq(5).html();
 	if(x==null){
 		alert("请选择需要操作的客户")
 	}else{
 		if(clientsubmitstate=="未审核"){
 			$(".shadow").show();
 			$(".pagehead font").html("审核客户")
 			$(".showpage").css("width","280px");
 			$(".showpage").css("height","100px");
 			$(".showpage").css("margin-left","600px");
 			$(".pagemain").html("<div class=userset>"+
 		  				"<div style='margin-left: 30px;margin-top: 10px;'><button onclick='checkclienet("+clienid+",1)'  class=but1>审核通过</button><button class=but1  onclick='checkclienet("+clienid+",2)'  >审核不通过</button></div>");
 		}else {
 			alert("客户已审核");
 		}
 	}
// 	tabclient=null;
 	
 }

 function checkclienet(id,type) {
 	var x=usedetailtab;
 	
 	$.getJSON("./WCheckClientServlet",{ClientId:id,ClientSubmitState:type},function(outjson){
 			if(outjson.check){
 				alert("成功！");
 				

 			}else{
 				alert("失败！");
 			}
 			 closepage();
 			 clientselect=2;
 			 showclienttitle(clientselect);
 			 showuserdetailclientsubmit(x);
 			 curbut=2;
 		});
 }


 function updateclientclean() {
 	var x=usedetailtab;
 	clientselect=1;
 	showclienttitle(clientselect);
 	showuserdetailclient(x);
 	curbut=2;
 }

 function changecustomerok(x) {
 	var clientid=$(x).parent().parent().find("div").eq(0).find("div").eq(1).html();
 	var clientname=$(x).parent().parent().find("div").eq(0).find("input").eq(0).val();
 	var clientcompany=$(x).parent().parent().find("div").eq(0).find("input").eq(1).val();
 	var clientnumber=$(x).parent().parent().find("div").eq(0).find("input").eq(2).val();
 	var clientarea=$(x).parent().parent().find("div").eq(0).find("input").eq(3).val();
 	var clientaddress=$(x).parent().parent().find("div").eq(0).find("input").eq(4).val();
 	$.getJSON("./WUpdateClientServlet",{ClientName:clientname,ClientId:clientid,ClientCompany:clientcompany,ClientPhone:clientnumber,ClientArea:clientarea,ClientAddress:clientaddress},function(outjson){
 		if(outjson.check){
 			alert("成功！");
// 			userdetailclick(2);
 			var x=usedetailtab;
 			clientselect=1;
 			showclienttitle(clientselect);
 			showuserdetailclient(x);
 			curbut=2;
 		}else {
 			alert("失败！");
// 			userdetailclick(2);
 			var x=usedetailtab;
 			clientselect=1;
 			showclienttitle(clientselect);
 			showuserdetailclient(x);
 			curbut=2;
 		}
 	})
 }

 var clientselect=0;//如果是0就是客户操作，1是员工客户，2是提交客户
 function showSelectClient() {
 	var x=usedetailtab;
 	var select=document.getElementById("selectclient");
 	 if(select.selectedIndex==1){ 
 		  clientselect=1;
 		 showclienttitle(clientselect);
 		 showuserdetailclient(x);
 		 curbut=2;
 	 }else if(select.selectedIndex==2){
 		 clientselect=2;
 		 showclienttitle(clientselect);
 		 showuserdetailclientsubmit(x);
 		 curbut=2;

 	 }
 }


 function cancelDisbut() {
 	
 	
 	var x=tabclient;
 	var clientsubmitstate=$(x).find("th").eq(7).html();
 	var clienid=$(x).find("th").eq(5).html();
 	if(x==null){
 		alert("请选择需要操作的客户")
 	}else{
 		$(".shadow").show();
 		$(".pagehead font").html("取消分配")
 		$(".showpage").css("width","280px");
 		$(".showpage").css("height","100px");
 		$(".showpage").css("margin-left","600px");
 		$(".pagemain").html("<div class=userset>"+
 	  				"<div style='margin-left: 30px;margin-top: 10px;'><button onclick='cancelDisbutOK("+clienid+")'  class=but1>确定</button><button class=but1  onclick='closepage()'  >取消</button></div>");
 	}
 }

 function cancelDisbutOK(clientid) {
 	var x=usedetailtab;
 	$.getJSON("./WCancleClientDistuServlet",{ClientId:clientid},function(outjson){
 		if(outjson.check){
 			alert("成功！");
 			

 		}else{
 			alert("失败！");
 		}
 		 closepage();
 		 clientselect=1;
 		 showclienttitle(clientselect);
 		 showuserdetailclient(x);
 		 curbut=2;
 	});
 }

 function delclient() {
 	var x=tabclient;
 	var clientsubmitstate=$(x).find("th").eq(7).html();
 	var clienid=$(x).find("th").eq(5).html();
 	if(x==null){
 		alert("请选择需要操作的客户")
 	}else{
 		$(".shadow").show();
 		$(".pagehead font").html("删除客户")
 		$(".showpage").css("width","280px");
 		$(".showpage").css("height","100px");
 		$(".showpage").css("margin-left","600px");
 		$(".pagemain").html("<div class=userset>"+
 	  				"<div style='margin-left: 30px;margin-top: 10px;'><button onclick='delclientOK("+clienid+")'  class=but1>确定</button><button class=but1  onclick='closepage()'  >取消</button></div>");
 	}
 }


 function delclientOK(clientid) {
 	var x=usedetailtab;
 	$.getJSON("./WDelClientServlet",{ClientId:clientid},function(outjson){
 		if(outjson.check){
 			alert("成功！");
 			

 		}else{
 			alert("失败！");
 		}
 		 closepage();
 		 clientselect=1;
 		 showclienttitle(clientselect);
 		 showuserdetailclient(x);
 		 curbut=2;
 	});
 }

 
//--------------------------------------------------考勤相关-------------------------------------------------------------
 function showuseattendance(x) {
 	$(".group-list").html("");
 	$(".group-list").append('<div class="group-one" style="height:100%;background-color: rgb(248,254,254);"> '+
   			'<div class="group-title"><font>考勤记录</font></div>'+
   			'<table id="tab" style="font-size:13px;" width="100%">'+
   			
                '<tbody>'+
                 '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
             
                     '<th>员工号</th>'+
              
                     '<th>员工名</th>'+
                     '<th>考勤日期</th>'+
                     '<th>签到时间</th>'+
                     '<th>签退时间</th>'+

                 '</tr>'+
                 '</tbody>'+
                 
                  
                 '<tbody  class="thisgroup">'+
                 	
                 '</tbody>'+
                 '</table>'+
              
                 
                 
   		'</div>');
 	
 	var date=$("#usetimeid").val();
 	$(".thisgroup").html("");
// 	alert(date);
 	var id=$(x).find("th").eq(6).html();
 	var name=$(x).find("th").eq(1).html();
 	var account=$(x).find("th").eq(0).html();
 	$.getJSON("./WGetEmployeeAllAttendanceServlet",{EmployeeId:id},function(outjson){
 		for ( var i = 0; i < outjson.AttendanceList.length; i++) {
 			var corone="black";
 			var cortwo="black";
 			if(outjson.AttendanceList[i].AttendanceRegisterTime==null){
 				outjson.AttendanceList[i].AttendanceRegisterTime="空";
 			}
 			if(outjson.AttendanceList[i].AttendanceSignoutTime==null){
 				outjson.AttendanceList[i].AttendanceSignoutTime="空";
 			}
 			if(!timeregicomp(outjson.AttendanceList[i].AttendanceRegisterTime)){
 				corone="rgb(237,28,36)";
 			}
 			if(!timesignout(outjson.AttendanceList[i].AttendanceSignoutTime)){ 
// 				outjson.AttendanceList[i].AttendanceSignoutTime="空";
 				cortwo="rgb(237,28,36)";
 			}
 			$(".thisgroup").append('<tr  onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;"  style="-moz-user-select:none;height:20x;font-size:12px;font-weight: normal;background-color: rgb(232,251,250);">'+
 					'<th style="font-weight: normal ">'+account+'</th>'+
 				    '<th style="font-weight: normal">'+name+'</th>'+
 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].AttendanceDate+'</th>'+
 				    '<th style="font-weight: normal;color:'+corone+'">'+outjson.AttendanceList[i].AttendanceRegisterTime+'</th>'+
 				    '<th style="font-weight: normal;color:'+cortwo+'">'+outjson.AttendanceList[i].AttendanceSignoutTime+'</th>'+
 					'</tr>');
 		
 		}
 	});
 	curbut=1;
 }
 
 function timeregicomp(regintime) {
	var timeone="08:30";
	var timetwo="09:00";
//	var timethree="16:30";
//	var timefour="17:00";
	var ones=timeone.split(":");
	var twos=timetwo.split(":");
	var regintimes=regintime.split(":");
	if(parseInt(regintimes[0])==parseInt(ones[0])&&parseInt(regintimes[0])<parseInt(twos[0])&&parseInt(regintimes[1])>=30){
		return true;
	}else {
		return false;
	}
	
}
 
 
 function timesignout(signouttime) {
		var timethree="16:30";
		var timefour="17:00";
		var threes=timethree.split(":");
		var fours=timefour.split(":");
		var outtimes=signouttime.split(":");
		if(parseInt(outtimes[0])==parseInt(threes[0])&&parseInt(outtimes[0])<parseInt(fours[0])&&parseInt(outtimes[1])>=30){
			return true;
		}else {
			return false;
		}
}

 function employeedateattendance(id,name,time,account) {
 	$(".group-list").html("");
 	$(".group-list").append('<div class="group-one" style="height:100%;background-color: rgb(248,254,254);"> '+
   			'<div class="group-title"><font>考勤记录</font></div>'+
   			'<table id="tab" style="font-size:13px;" width="100%">'+
   			
                '<tbody>'+
                 '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
             
                     '<th>员工号</th>'+
              
                     '<th>员工名</th>'+
                     '<th>考勤日期</th>'+
                     '<th>签到时间</th>'+
                     '<th>签退时间</th>'+

                 '</tr>'+
                 '</tbody>'+
                 
                  
                 '<tbody  class="thisgroup">'+
                 	
                 '</tbody>'+
                 '</table>'+
              
                 
                 
   		'</div>');
 	
 	var date=$("#usetimeid").val();
 	$(".thisgroup").html("");
 	$.getJSON("./WGetEmployeeDateAttendanceServlet",{EmployeeId:id,AttendanceDate:time},function(outjson){
 		for ( var i = 0; i < outjson.AttendanceList.length; i++) {
 			var corone="black";
 			var cortwo="black";
 			if(outjson.AttendanceList[i].AttendanceRegisterTime==null){
 				outjson.AttendanceList[i].AttendanceRegisterTime="空";
 				corone="rgb(0,0,0)";
 			}
 			if(outjson.AttendanceList[i].AttendanceSignoutTime==null){ 
 				outjson.AttendanceList[i].AttendanceSignoutTime="空";
 				cortwo="rgb(0,0,0)";
 			}
 			
 			if(!timeregicomp(outjson.AttendanceList[i].AttendanceRegisterTime)){
 				corone="rgb(237,28,36)";
 			}
 			if(!timesignout(outjson.AttendanceList[i].AttendanceSignoutTime)){ 
// 				outjson.AttendanceList[i].AttendanceSignoutTime="空";
 				cortwo="rgb(237,28,36)";
 			}
 			
 			$(".thisgroup").append('<tr  onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;"  style="-moz-user-select:none;height:20x;font-size:12px;font-weight: normal;background-color: rgb(232,251,250);">'+
 					'<th style="font-weight: normal ">'+account+'</th>'+
 				    '<th style="font-weight: normal">'+name+'</th>'+
 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].AttendanceDate+'</th>'+
 				    '<th style="font-weight: normal color:'+corone+'">'+outjson.AttendanceList[i].AttendanceRegisterTime+'</th>'+
 				    '<th style="font-weight: normal color:'+cortwo+'">'+outjson.AttendanceList[i].AttendanceSignoutTime+'</th>'+
 					'</tr>');
 		
 		}
 	});
 }

 var attendanceselect=0;//如果是0就是客户操作，1是考勤操作
 function showSelectAttendance() {
 	var x=usedetailtab;
 	var select=document.getElementById("selectattendance");
 	 if(select.selectedIndex==1){ 
 		showuserdetailtitle();
 		showuseattendance(x);
 		
 	 }
 }

 
 //---------------------------------------------------------------地图相关----------------------------------------------

 function withmap(address,area) {
 	var map = new BMap.Map("updateclientcontainer"); // 创建地图实例  
 	map.centerAndZoom(new BMap.Point(121.56, 29.86), 10);  // 初始化地图,设置中心点坐标和地图级别
 	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
 	map.setCurrentCity("宁波");          // 设置地图显示的城市 此项是必须设置的
 	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
 	
 	
 	var myGeo = new BMap.Geocoder();
 	// 将地址解析结果显示在地图上,并调整地图视野
 	myGeo.getPoint(address, function(point){
 		if (point) {
 			map.centerAndZoom(point, 16);
 			map.addOverlay(new BMap.Marker(point));
 		}else{
 			alert("您选择地址没有解析到结果!");
 		}
 	}, area);
 	
 	var geoc = new BMap.Geocoder();    
 	map.addEventListener("click", function(e){      
 		map.clearOverlays();        
 		var pt = e.point;
 		
 		
 		geoc.getLocation(pt, function(rs){
 			var addComp = rs.addressComponents;
 			var address=addComp.province  + addComp.city  + addComp.district + addComp.street  + addComp.streetNumber;
 			
 			document.getElementById("updateclientarea").value=addComp.province;
 			document.getElementById("updateclientaddress").value=address; 
 			
 			var marker = new BMap.Marker(new BMap.Point(e.point.lng, e.point.lat));
 			map.addOverlay(marker);    
 		});     
 	});
 	
 	
 }
 
 
 function withmapsee(address,area) {
	 	var map = new BMap.Map("updateclientcontainer"); // 创建地图实例  
	 	map.centerAndZoom(new BMap.Point(121.56, 29.86), 10);  // 初始化地图,设置中心点坐标和地图级别
	 	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	 	map.setCurrentCity("宁波");          // 设置地图显示的城市 此项是必须设置的
	 	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	 	
	 	
	 	var myGeo = new BMap.Geocoder();
	 	// 将地址解析结果显示在地图上,并调整地图视野
	 	myGeo.getPoint(address, function(point){
	 		if (point) {
	 			map.centerAndZoom(point, 16);
	 			map.addOverlay(new BMap.Marker(point));
	 		}else{
	 			alert("您选择地址没有解析到结果!");
	 		}
	 	}, area);
	 	
	 	
}

 //-------------------------------------------------------------------
   function closeshadow() {
   	$(".shadow").html("");
   	$(".shadow").hide();
   }


   Date.prototype.format = function (format) {
              var args = {
                  "M+": this.getMonth() + 1,
                  "d+": this.getDate(),
                  "h+": this.getHours(),
                  "m+": this.getMinutes(),
                  "s+": this.getSeconds(),
                  "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
                  "S": this.getMilliseconds()
              };
              if (/(y+)/.test(format))
                  format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
              for (var i in args) {
                  var n = args[i];
                  if (new RegExp("(" + i + ")").test(format))
                      format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n : ("00" + n).substr(("" + n).length));
              }
              return format;
          };

   function changgebutsscolcr(x) {
   	var array=new Array("usebut","usebut","usebut","usebut","usebut","usebut");
   	array[x-1]="usebuttwo"
   	for(var i=0;i<array.length;i++){
   		$("#butss").find("div").eq(i).attr("class",array[i]);
   	}
   	
   }