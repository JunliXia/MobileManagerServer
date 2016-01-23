	var map = new BMap.Map("allmap");          
	var point = new BMap.Point(121.56,29.86); 
$(document).ready(function () {
		
	
		map.centerAndZoom(point, 15);               
		map.addControl(new BMap.NavigationControl());    
		map.addControl(new BMap.ScaleControl());    
		map.addControl(new BMap.OverviewMapControl());    
		map.addControl(new BMap.MapTypeControl());    
		setTimeout(function(){
			map.setZoom(14);   
		}, 2000);  //2秒后放大到14级
		map.enableScrollWheelZoom(true);
		
})
function searchpoint() {
	map.clearOverlays();
	var point=$("#searchpointer").val()+"";
	var local = new BMap.LocalSearch(map, {
		renderOptions:{map: map}
	});
	local.search(point);
}
function showroute(first,end) {
	var driving = new BMap.DrivingRoute(map, {renderOptions: {map: map, panel: "r-result", autoViewport: true}});
	driving.search(first, end);
}

function outroute() {
	$(".route").css("top","-150px");
	$("#r-result").show();
	$(".routet").html("▼");
	$(".routet").attr("onclick","inroute()");
}
function inroute() {
	$(".route").css("top","-21px");
	$("#r-result").hide();
	$(".routet").html("▲");
	$(".routet").attr("onclick","outroute()");
}
function gotoshowroute() {
	var routePolicy = [BMAP_DRIVING_POLICY_LEAST_TIME,BMAP_DRIVING_POLICY_LEAST_DISTANCE,BMAP_DRIVING_POLICY_AVOID_HIGHWAYS];
	var start=$("#fs").val()+"";
	var end=$("#ls").val()+"";
	map.clearOverlays(); 
	var i=$("#driving_way select").val();
	search(start,end,routePolicy[i]); 
	function search(start,end,route){ 
		var driving = new BMap.DrivingRoute(map, {renderOptions:{map: map, panel: "r-result", autoViewport: true},policy: route});
		driving.search(start,end);
	}
}