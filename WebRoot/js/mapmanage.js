var memberlist=new Array();
var pointerlist=new Array();

var member1={
		name:"小黑",
		lon:121.56,
		lat:29.86
		
}






$(document).ready(function () {

	showmans();

})


function showaddress(x) {

//	var point = new BMap.Point(37.22,121.222);
//	map.centerAndZoom(point, 15);
//	alert(x);
	map.clearOverlays();
	
	$.getJSON("./WGetEmployeeAddressServlet",{EmployeeId:x,days:7},function(outjson){
		var point = new BMap.Point(outjson.AddressList[0].AddressLong,outjson.AddressList[0].AddressLat);
		map.centerAndZoom(point, 15);
		for(var i=0;i<outjson.AddressList.length;i++){
//			str=str+"<option value ="+outjson.EmployeeList[i].EmployeeId+">账号:"+outjson.EmployeeList[i].EmployeeAccount+"&nbsp员工名:"+outjson.EmployeeList[i].EmployeeName+"&nbsp分配客户:"+outjson.EmployeeList[i].ClientNumber+"</option>";
			
//			var infoWindow = new BMap.InfoWindow(outjson.AddressList[i].AddressTime+"---"+i, opts);  // 创建信息窗口对象 
			var point = new BMap.Point(outjson.AddressList[i].AddressLong,outjson.AddressList[i].AddressLat);
			var marker = new BMap.Marker(point);  // 创建标注
			map.addOverlay(marker);              // 将标注添加到地图中
			var content=outjson.AddressList[i].AddressTime+"---"+i;
			addClickHandler(content,marker);
		
		}
 	
 	});

}

function addClickHandler(content,marker){
	marker.addEventListener("click",function(e){
		openInfo(content,e)}
	);
}
function openInfo(content,e){
	var opts = {
			  width : 200,     // 信息窗口宽度
			  height: 100,     // 信息窗口高度
			  title : "员工信息" , // 信息窗口标题
			}
	
	var p = e.target;
	var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
	var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象 
	map.openInfoWindow(infoWindow,point); //开启信息窗口
}

function showmans() {
	$.getJSON("./WGetAllNoDelEmployeeServlet",function(outjson){
		for ( var i = 0; i < outjson.EmployeeList.length; i++) {
			var employeeId=outjson.EmployeeList[i].EmployeeId;
			var name=outjson.EmployeeList[i].EmployeeName;
			$(".membermain").append('<div class="merber-one" onclick="showaddress('+employeeId+')"><img src="./pic/man.png" class="merberoneimg" /><div class="membername">'+name+'</div></div>')
//			var member={
//					name:name,
//					lon:outjson.address[i].addressLong,
//					lat:outjson.address[i].addressLat,
//					
//			}
//			memberlist.push(member);
		}
//		for ( var i = 0; i < memberlist.length; i++) {
//			var opts = {
//					  width : 200,     // 信息窗口宽度
//					  height: 100,     // 信息窗口高度
//					  title : "员工信息" , // 信息窗口标题
//					}
//			var infoWindow = new BMap.InfoWindow(memberlist[i].name, opts);  // 创建信息窗口对象 
//			var point = new BMap.Point(memberlist[i].lon,memberlist[i].lat);
//			pointerlist.push(point);
//			var marker = new BMap.Marker(point);  // 创建标注
//			map.addOverlay(marker);              // 将标注添加到地图中
//			marker.addEventListener("click", function(){          
//				map.openInfoWindow(infoWindow,point); //开启信息窗口
//			});		
//
//		}
//		showaddress(0);
	});
	
}


