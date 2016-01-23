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

	var point = new BMap.Point(memberlist[x].lon,memberlist[x].lat);
	map.centerAndZoom(point, 15);
}

function showmans() {
	$.getJSON("./QueryAllAddressServlet",function(outjson){
		for ( var i = 0; i < outjson.address.length; i++) {
			var employeeId=outjson.address[i].employeeId;
			var name=getnamebyid(employeeId);
			$(".membermain").append('<div class="merber-one" onclick="showaddress('+i+')"><img src="./pic/man.png" class="merberoneimg" /><div class="membername">'+name+'</div></div>')
			var member={
					name:name,
					lon:outjson.address[i].addressLong,
					lat:outjson.address[i].addressLat,
					
			}
			memberlist.push(member);
		}
		for ( var i = 0; i < memberlist.length; i++) {
			var opts = {
					  width : 200,     // 信息窗口宽度
					  height: 100,     // 信息窗口高度
					  title : "员工信息" , // 信息窗口标题
					}
			var infoWindow = new BMap.InfoWindow(memberlist[i].name, opts);  // 创建信息窗口对象 
			var point = new BMap.Point(memberlist[i].lon,memberlist[i].lat);
			pointerlist.push(point);
			var marker = new BMap.Marker(point);  // 创建标注
			map.addOverlay(marker);              // 将标注添加到地图中
			marker.addEventListener("click", function(){          
				map.openInfoWindow(infoWindow,point); //开启信息窗口
			});		

		}
		showaddress(0);
	});
	
}


