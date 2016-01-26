
$(document).ready(function() {
	showmaintransactiontitle();
	showaffiche("#affiche");
})



function showmaintransactiontitle() {
	$(".right-title").html("");
	$(".right-title").append(
			"<img onclick='	showmaintransactiontitle(),showbussinessrunning()' src='./pic/daohang.png'  title='事务管理导航' style=' width:30px;height:30px; cursor:pointer; margin-left:100px;margin-bottom:-64px; margin-top:7px;' />"+
			"<div id=missionhasnodeldivid style='display:none;font-family:Microsoft YaHei;cursor: pointer;position:relative;margin-left:180px; width:120px;height:50px; padding-top:10px;margin-bottom:-64px; margin-top:4px;'><font style='font-size:13px;color:rgb(255,255,255)'>您有未处理的出差</font><img id=clienttitlepoint src='./pic/redpoint.png' style='height:10px;width:10px;padding-bottom:3px; margin-top:-20px;;margin-left:109px;'><img/></div>"+
		 	
			"<div id=usermanagerserch class=zySearch> " +
 			"<input id=clientsearchameid class=search-input  placeholder='全员搜索或请输入员工名' ></input>"+
 			"<div class=search-btn  onclick='searchclient()'>搜索</div>"+
 			"</div>"+
 			"<div id=attenss style='height:25px;width:400px;float:right;margin-top:-15px; margin-left:700px;'>" +
// 				"<div class=usebut style=' cursor:pointer; position: relative;' onclick='updateclient()'>修改</div>"+
//				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='seebussiness()'>查看</div>"+
				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='showaffiche(this)'>通知公告</div>"+
				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='showcomplain(this)'>投诉建议</div>"+
//				"<div class=usebut  style=' cursor:pointer; position: relative; margin-left:50px;'> <select id=selectbussinessID onchange='showSelectBussiness()' class=useselect style='height:25px;'><option value='1'>需执行</option><option value='2'>待处理</option><option value='3'>已结束</option><option value='4'>已撤销</option><option value='5'>不良记录</option></select></div> "+
 			"</div>"
 		
	);
	
}




function changetitle(x) {

	$(".trantitle").css("color","black");
	$(".trantitle").css("background-color","white");
	$(x).css("background-color","black");
	$(x).css("color","white");
}

function showaffiche(x) {
	changetitle(x);
	$(".tran-main").html('<div class="affiche-top">'+
//			'<button style="float:right; "onclick="addaffiche()">新增</button>'+
			'<div class="group-title" <font>通知公告</font>'+
			
			"<div class=usebut  style='float:right; cursor:pointer; margin-top:4px;' onclick='addaffiche()'>新增</div>"+
			'</div>'+
		'</div>'+'<div class="affiche-main">'+	'</div>'
			);
	
	$.getJSON("./WGetAllNoticeServlet",function(outjson){
		for ( var i = 0; i < outjson.NoticeList.length; i++) {
		
			$(".affiche-main").append(
					'<div class="affiche-one">'+
						'<img  src="./pic/ico.png">'+
						'<div class="affiche-oneright">'+
							'<div class="affiche-one-title">'+outjson.NoticeList[i].NoticeTitle+'</div>'+
							'<div class="affiche-one-more">'+outjson.NoticeList[i].NoticeContent+'</div>'+
							'<div style="text-align:right;font-size:12px;">'+outjson.NoticeList[i].NoticeTime+'</div>'+
						'</div>'+
					'</div>'		
			);
		}
	
	});
	
}

function showcomplain(x) {
	changetitle(x);
	$(".tran-main").html(	'<div class="group-title" style="margin-left:0px" <font>投诉建议</font>'+
		'</div>'+'<div class="affiche-main">'+'</div>');
	$.getJSON("./WGetAllSuggestServlet",function(outjson){
		for ( var i = 0; i < outjson.SuggestList.length; i++) {
			$(".affiche-main").append(
					'<div class="affiche-one">'+
						'<img  src="./pic/male.png">'+
						'<div class="affiche-oneright">'+
							'<div class="affiche-one-title">'+outjson.SuggestList[i].EmployeeName+'</div>'+
							'<div class="affiche-one-more">'+outjson.SuggestList[i].SuggestContent+'</div>'+
							'<div style="text-align:right;font-size:12px;">'+outjson.SuggestList[i].SuggestTime+'</div>'+
						'</div>'+
					'</div>'	
				);
		}
	});
		
	
}




function addaffiche() {
	$(".shadow").show();
	$(".pagehead font").html("创建公告")
	$(".showpage").css("width","270px");
	$(".showpage").css("height","250px");
	$(".showpage").css("margin-left","600px");
	$(".pagemain").html('<div style="margin: 10px 0;">标题：<input id="addaffichetitle"/></div>'+
  				'<div >内容：</div>'+
  				'<textarea class="area" id="addaffichemain"></textarea>'+
  				'<button class=but1 style="margin-left:80px;margin-top:10px;" onclick="createaffiche()">确定</button>');
}


function createaffiche() {
	var NoticeTitle=$("#addaffichetitle").val();
	var NoticeContent=$("#addaffichemain").val();
	$.getJSON("./WAddNoticeServlet",{NoticeTitle:NoticeTitle,NoticeContent:NoticeContent},function(json){
		if (json.check) {
			alert("添加成功!");
			showaffiche("#affiche");
			closepage();
		}else {
			alert("添加失败!");
			closepage();
		}
	})	



}
