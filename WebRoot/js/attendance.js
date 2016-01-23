$(document).ready(function() {
	showmainusertitle();
	showuseattendance();
})


function showmainusertitle() {
	$(".right-title").html("");
	$(".right-title").append(
			"<img onclick='	showmainusertitle(),showuseattendance()' src='./pic/daohang.png'  title='考勤管理导航' style=' width:30px;height:30px; cursor:pointer; margin-left:100px;margin-bottom:-64px; margin-top:7px;' />"+
			"<div id=usermanagerserch class=zySearch> " +
 			"<input id=usernameid class=search-input  placeholder='全员搜索或请输入员工名' ></input>"+
 			"<div class=search-btn  onclick='searchatten()'>搜索</div>"+
 			"</div>"+
 			"<div id=attenss style='height:25px;width:400px;float:right;margin-top:-15px; margin-left:700px;'>" +
// 				"<div class=usebut  style='display:none; cursor:pointer; position: relative;' onclick='setuser()'>修改</div>"+
				"<div class=usebut  style=' cursor:pointer; margin-left:50px;'> <select id=selectID onchange='showSelect()' class=useselect style='height:25px;'><option value='1'>考勤记录</option><option value='2'>考勤统计</option></select></div> "+

 				"<div style='margin-bottom:100px;'><input id=attenid value="+new Date().format("yyyy/MM/dd hh:mm:ss")+"  onclick=SelectDate(this,\'yyyy/MM/dd\') style='margin-top:-19px;color:white;background:rgba(0,0,0,0);border:none;font-size:17px;width:100px;margin-left:140px;'></input></div>"+
// 				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='userdetail()'>查看</div>"+
// 				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='delep()'>删除</div>"+
// 				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='addep()'>增加</div>"+
 			
 			
 			"</div>"
 		
 			
	);
	
}

function showtongjititel() {
	$(".right-title").html("");
	$(".right-title").append(
			"<img onclick='	showmainusertitle(),showuseattendance()' src='./pic/daohang.png'  title='员工管理导航' style=' width:30px;height:30px; cursor:pointer; margin-left:100px;margin-bottom:-64px; margin-top:7px;' />"+
			"<div id=usermanagerserch class=zySearch> " +
 			"<input id=usernameid class=search-input  placeholder='全员搜索或请输入员工名' ></input>"+
 			"<div class=search-btn  onclick='searchatten()'>搜索</div>"+
 			"</div>"+
 			"<div id=attenss style='height:25px;width:400px;float:right;margin-top:-15px; margin-left:700px;'>" +
// 				"<div class=usebut  style='display:none; cursor:pointer; position: relative;' onclick='setuser()'>修改</div>"+
				"<div class=usebut  style=' cursor:pointer; margin-left:50px;'> <select id=selectID onchange='showSelect()' class=useselect style='height:25px;'><option value='1'>考勤记录</option><option value='2' selected='selected'>考勤统计</option></select></div> "+

// 				"<div style='margin-bottom:100px;'><input id=attenid value="+new Date().format("yyyy/MM/dd hh:mm:ss")+"  onclick=SelectDate(this,\'yyyy/MM/dd\') style='margin-top:-19px;color:white;background:rgba(0,0,0,0);border:none;font-size:17px;width:100px;margin-left:140px;'></input></div>"+
// 				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='userdetail()'>查看</div>"+
// 				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='delep()'>删除</div>"+
// 				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='addep()'>增加</div>"+
 				"<div style='margin-left:130px;margin-top:-21px;'><select id=yearseleid onchange='attenstatistical()' class=useselect  style='cursor:pointer; position: relative;margin-left:4px;font-size: 15px;'></select></div>"+
 				"<div style='margin-left:210px;margin-top:-22px;'><select id=monthid class=useselect onchange='attenstatistical()' style='cursor:pointer; position: relative;margin-left:4px;font-size: 15px;'></select></div>"+
 				
 			
 			"</div>"
 		
 			
	);
	
	var str;
	var yearcom=2015;
	for(var i=1;i<=5;i++){
		
		str+="<option  value ='"+yearcom+"'>"+yearcom+"</option>";
		yearcom++;
	}
	$("#yearseleid").append(str);
	var strmonth="<option  value ='全部'>全部</option>";
	var monthcom=1;
	for(var i=1;i<=12;i++){
		strmonth+="<option  value ='"+monthcom+"'>"+monthcom+"</option>";
		monthcom++;
	}
	$("#monthid").append(strmonth);
}

function attenstatistical () {
	var year=$("#yearseleid").val();
	var month=$("#monthid").val();
	if(month=="全部"){

		$(".group-listattendance").html("");
	 	$(".group-listattendance").append('<div class="group-one" style="height:100%;background-color: rgb(248,254,254);"> '+
	   			'<div class="group-title"><font>考勤统计</font></div>'+
	   			'<table id="tab" style="font-size:13px;" width="100%">'+
	   			
	                '<tbody>'+
	                 '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
	             
	                     '<th>员工帐号</th>'+
	                     '<th>员工名</th>'+
	                     '<th>签到次数</th>'+
	                     '<th>签退次数</th>'+
	                     '<th>签到迟到次数</th>'+
	                     '<th>签退迟到次数</th>'+
	                     '<th>签到漏签次数</th>'+
	                     '<th>签退漏签次数</th>'+
	                 '</tr>'+
	                 '</tbody>'+
	                 
	                  
	                 '<tbody  class="thisgroup">'+
	                 	
	                 '</tbody>'+
	                 '</table>'+
	              
	                 
	                 
	   		'</div>');
	 	
	 	$(".thisgroup").html("");
	 	$.getJSON("./WGetAttendanceStatisticalYearServlet",{year:year},function(outjson){
	 		for ( var i = 0; i < outjson.AttendanceList.length; i++) {
	 		
	 			$(".thisgroup").append('<tr  onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;"  style="-moz-user-select:none;height:20x;font-size:12px;font-weight: normal;background-color: rgb(232,251,250);">'+
	 					'<th style="font-weight: normal ">'+outjson.AttendanceList[i].EmployeeAccount+'</th>'+
	 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].EmployeeName+'</th>'+
	 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].RegisterTimeTimes+'</th>'+
	 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].SignoutTimeTimes+'</th>'+
	 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].RegisterOutTimeTimes+'</th>'+
	 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].SignoutOutTimeTimes+'</th>'+
	 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].RegisterNoTimeTimes+'</th>'+
	 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].SignoutNoTimeTimes+'</th>'+
	 				    
	 			'</tr>');
	 		
	 		}
	 	});
		
	}else if(month!="全部"){

		$(".group-listattendance").html("");
	 	$(".group-listattendance").append('<div class="group-one" style="height:100%;background-color: rgb(248,254,254);"> '+
	   			'<div class="group-title"><font>考勤统计</font></div>'+
	   			'<table id="tab" style="font-size:13px;" width="100%">'+
	   			
	                '<tbody>'+
	                 '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
	             
	                     '<th>员工帐号</th>'+
	                     '<th>员工名</th>'+
	                     '<th>签到次数</th>'+
	                     '<th>签退次数</th>'+
	                     '<th>签到迟到次数</th>'+
	                     '<th>签退迟到次数</th>'+
	                     '<th>签到漏签次数</th>'+
	                     '<th>签退漏签次数</th>'+
	                 '</tr>'+
	                 '</tbody>'+
	                 
	                  
	                 '<tbody  class="thisgroup">'+
	                 	
	                 '</tbody>'+
	                 '</table>'+
	              
	                 
	                 
	   		'</div>');
	 	
	 	$(".thisgroup").html("");
	 	$.getJSON("./WGetAttendanceStatisticalYearMonthServlet",{year:year,month:month},function(outjson){
	 		for ( var i = 0; i < outjson.AttendanceList.length; i++) {
	 		
	 			$(".thisgroup").append('<tr  onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;"  style="-moz-user-select:none;height:20x;font-size:12px;font-weight: normal;background-color: rgb(232,251,250);">'+
	 					'<th style="font-weight: normal ">'+outjson.AttendanceList[i].EmployeeAccount+'</th>'+
	 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].EmployeeName+'</th>'+
	 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].RegisterTimeTimes+'</th>'+
	 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].SignoutTimeTimes+'</th>'+
	 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].RegisterOutTimeTimes+'</th>'+
	 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].SignoutOutTimeTimes+'</th>'+
	 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].RegisterNoTimeTimes+'</th>'+
	 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].SignoutNoTimeTimes+'</th>'+
	 				    
	 			'</tr>');
	 		
	 		}
	 	});
	}
	
}


function showSelect() {
	var select=document.getElementById("selectID");
	 if(select.selectedIndex==0){ 
		showmainusertitle();
		showuseattendance();
	 }else if(select.selectedIndex==1){
		 showtongjititel();
		 attenstatistical();
	 }
}

function searchatten() {
	var usename=$("#usernameid").val();
	var titleval=$("#selectID").val();
	if(usename==""&&titleval==1){
		showattenallbytime();
	}else if(usename!=""&&titleval==1){
		showattenbyname(usename);
	}else if(usename==""&&titleval==2){
		attenstatistical();
	}else if(usename!=""&&titleval==2){
		searchstatic();
		
	}
	
}


function searchstatic() {
	var usename=$("#usernameid").val();
	var year=$("#yearseleid").val();
	var month=$("#monthid").val();
	$(".group-listattendance").html("");
 	$(".group-listattendance").append('<div class="group-one" style="height:100%;background-color: rgb(248,254,254);"> '+
   			'<div class="group-title"><font>考勤统计</font></div>'+
   			'<table id="tab" style="font-size:13px;" width="100%">'+
   			
                '<tbody>'+
                 '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
             
                     '<th>员工帐号</th>'+
                     '<th>员工名</th>'+
                     '<th>签到次数</th>'+
                     '<th>签退次数</th>'+
                     '<th>签到迟到次数</th>'+
                     '<th>签退迟到次数</th>'+
                     '<th>签到漏签次数</th>'+
                     '<th>签退漏签次数</th>'+
                 '</tr>'+
                 '</tbody>'+
                 
                  
                 '<tbody  class="thisgroup">'+
                 	
                 '</tbody>'+
                 '</table>'+
              
                 
                 
   		'</div>');
 	
 	$(".thisgroup").html("");
 	$.getJSON("./WGetAttendanceStatisticalYearMonthEmployeeNameServlet",{year:year,month:month,EmployeeName:usename},function(outjson){
 		for ( var i = 0; i < outjson.AttendanceList.length; i++) {
 		
 			$(".thisgroup").append('<tr  onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;"  style="-moz-user-select:none;height:20x;font-size:12px;font-weight: normal;background-color: rgb(232,251,250);">'+
 					'<th style="font-weight: normal ">'+outjson.AttendanceList[i].EmployeeAccount+'</th>'+
 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].EmployeeName+'</th>'+
 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].RegisterTimeTimes+'</th>'+
 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].SignoutTimeTimes+'</th>'+
 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].RegisterOutTimeTimes+'</th>'+
 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].SignoutOutTimeTimes+'</th>'+
 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].RegisterNoTimeTimes+'</th>'+
 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].SignoutNoTimeTimes+'</th>'+
 				    
 			'</tr>');
 		
 		}
 	});
}

function showattenbyname(name) {
	var time=$("#attenid").val();
 	$(".group-listattendance").html("");
 	$(".group-listattendance").append('<div class="group-one" style="height:100%;background-color: rgb(248,254,254);"> '+
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
 	
 	$(".thisgroup").html("");
 	$.getJSON("./WSearchAttendanceByEmNameServlet",{AttendanceDate:time,EmployeeName:name},function(outjson){
 		for ( var i = 0; i < outjson.AttendanceList.length; i++) {
 			var corone="black";
 			var cortwo="black";
 			if(outjson.AttendanceList[i].AttendanceRegisterTime==""){
 				outjson.AttendanceList[i].AttendanceRegisterTime="空";
 			}
 			if(outjson.AttendanceList[i].AttendanceSignoutTime==""){
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
 					'<th style="font-weight: normal ">'+outjson.AttendanceList[i].EmployeeAccount+'</th>'+
 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].EmployeeName+'</th>'+
 				    '<th style="font-weight: normal">'+time+'</th>'+
 				    '<th style="font-weight: normal;color:'+corone+'">'+outjson.AttendanceList[i].AttendanceRegisterTime+'</th>'+
 				    '<th style="font-weight: normal;color:'+cortwo+'">'+outjson.AttendanceList[i].AttendanceSignoutTime+'</th>'+
 					'</tr>');
 		
 		}
 	});
 	curbut=1;

}


function showattenallbytime() {
//	alert("1");
	var time=$("#attenid").val();
//	var date=new Date();
//	var strdate=date.format("yyyy/MM/dd");
 	$(".group-listattendance").html("");
 	$(".group-listattendance").append('<div class="group-one" style="height:100%;background-color: rgb(248,254,254);"> '+
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
 	
// 	var date=$("#usetimeid").val();
 	$(".thisgroup").html("");
// 	alert(date);
// 	var id=$(x).find("th").eq(6).html();
// 	var name=$(x).find("th").eq(1).html();
// 	var account=$(x).find("th").eq(0).html();
 	$.getJSON("./WGetAllAttendServlet",{AttendanceDate:time},function(outjson){
 		for ( var i = 0; i < outjson.AttendanceList.length; i++) {
 			var corone="black";
 			var cortwo="black";
 			if(outjson.AttendanceList[i].AttendanceRegisterTime==""){
 				outjson.AttendanceList[i].AttendanceRegisterTime="空";
 			}
 			if(outjson.AttendanceList[i].AttendanceSignoutTime==""){
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
 					'<th style="font-weight: normal ">'+outjson.AttendanceList[i].EmployeeAccount+'</th>'+
 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].EmployeeName+'</th>'+
 				    '<th style="font-weight: normal">'+time+'</th>'+
 				    '<th style="font-weight: normal;color:'+corone+'">'+outjson.AttendanceList[i].AttendanceRegisterTime+'</th>'+
 				    '<th style="font-weight: normal;color:'+cortwo+'">'+outjson.AttendanceList[i].AttendanceSignoutTime+'</th>'+
 					'</tr>');
 		
 		}
 	});
 	curbut=1;


}

function showuseattendance() {
	var date=new Date();
	var strdate=date.format("yyyy/MM/dd");
 	$(".group-listattendance").html("");
 	$(".group-listattendance").append('<div class="group-one" style="height:100%;background-color: rgb(248,254,254);"> '+
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
// 	var id=$(x).find("th").eq(6).html();
// 	var name=$(x).find("th").eq(1).html();
// 	var account=$(x).find("th").eq(0).html();
 	$.getJSON("./WGetAllAttendServlet",{AttendanceDate:strdate},function(outjson){
 		for ( var i = 0; i < outjson.AttendanceList.length; i++) {
 			var corone="black";
 			var cortwo="black";
 			if(outjson.AttendanceList[i].AttendanceRegisterTime==""){
 				outjson.AttendanceList[i].AttendanceRegisterTime="空";
 			}
 			if(outjson.AttendanceList[i].AttendanceSignoutTime==""){
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
 					'<th style="font-weight: normal ">'+outjson.AttendanceList[i].EmployeeAccount+'</th>'+
 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].EmployeeName+'</th>'+
 				    '<th style="font-weight: normal">'+strdate+'</th>'+
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

// function employeedateattendance(id,name,time,account) {
// 	$(".group-listattendance").html("");
// 	$(".group-listattendance").append('<div class="group-one" style="height:100%;background-color: rgb(248,254,254);"> '+
//   			'<div class="group-title"><font>考勤记录</font></div>'+
//   			'<table id="tab" style="font-size:13px;" width="100%">'+
//   			
//                '<tbody>'+
//                 '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60);">'+
//             
//                     '<th>员工号</th>'+
//              
//                     '<th>员工名</th>'+
//                     '<th>考勤日期</th>'+
//                     '<th>签到时间</th>'+
//                     '<th>签退时间</th>'+
//
//                 '</tr>'+
//                 '</tbody>'+
//                 
//                  
//                 '<tbody  class="thisgroup">'+
//                 	
//                 '</tbody>'+
//                 '</table>'+
//              
//                 
//                 
//   		'</div>');
// 	
// 	var date=$("#usetimeid").val();
// 	$(".thisgroup").html("");
// 	$.getJSON("./WGetEmployeeDateAttendanceServlet",{EmployeeId:id,AttendanceDate:time},function(outjson){
// 		for ( var i = 0; i < outjson.AttendanceList.length; i++) {
// 			var corone="black";
// 			var cortwo="black";
// 			if(outjson.AttendanceList[i].AttendanceRegisterTime==null){
// 				outjson.AttendanceList[i].AttendanceRegisterTime="空";
// 				corone="rgb(0,0,0)";
// 			}
// 			if(outjson.AttendanceList[i].AttendanceSignoutTime==null){ 
// 				outjson.AttendanceList[i].AttendanceSignoutTime="空";
// 				cortwo="rgb(0,0,0)";
// 			}
// 			
// 			if(!timeregicomp(outjson.AttendanceList[i].AttendanceRegisterTime)){
// 				corone="rgb(237,28,36)";
// 			}
// 			if(!timesignout(outjson.AttendanceList[i].AttendanceSignoutTime)){ 
//// 				outjson.AttendanceList[i].AttendanceSignoutTime="空";
// 				cortwo="rgb(237,28,36)";
// 			}
// 			
// 			$(".thisgroup").append('<tr  onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;"  style="-moz-user-select:none;height:20x;font-size:12px;font-weight: normal;background-color: rgb(232,251,250);">'+
// 					'<th style="font-weight: normal ">'+account+'</th>'+
// 				    '<th style="font-weight: normal">'+name+'</th>'+
// 				    '<th style="font-weight: normal">'+outjson.AttendanceList[i].AttendanceDate+'</th>'+
// 				    '<th style="font-weight: normal color:'+corone+'">'+outjson.AttendanceList[i].AttendanceRegisterTime+'</th>'+
// 				    '<th style="font-weight: normal color:'+cortwo+'">'+outjson.AttendanceList[i].AttendanceSignoutTime+'</th>'+
// 					'</tr>');
// 		
// 		}
// 	});
// }








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
