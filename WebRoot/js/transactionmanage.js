
$(document).ready(function() {
	showaffiche("#affiche");
})

function changetitle(x) {

	$(".trantitle").css("color","black");
	$(".trantitle").css("background-color","white");
	$(x).css("background-color","black");
	$(x).css("color","white");
}

function showaffiche(x) {
	changetitle(x);
	$(".tran-main").html('<div class="affiche-top">'+
			'<button style="float:right; "onclick="addaffiche()">新增</button>'+
		'</div>'+'<div class="affiche-main">'+	'</div>'
			);
	
	$.getJSON("./QueryAllNoticeServlet",function(outjson){
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
	$(".tran-main").html('<div class="affiche-top"style="color:white;text-align:center;">'+
			'建议列表'+
		'</div>'+'<div class="affiche-main">'+'</div>');
	$.getJSON("./QueryAllSuggestServlet",function(outjson){
		for ( var i = 0; i < outjson.SuggestList.length; i++) {
			var name=getnamebyid(outjson.SuggestList[i].EmployeeId);
			$(".affiche-main").append(
					'<div class="affiche-one">'+
						'<img  src="./pic/male.png">'+
						'<div class="affiche-oneright">'+
							'<div class="affiche-one-title">'+name+'</div>'+
							'<div class="affiche-one-more">'+outjson.SuggestList[i].SuggestContent+'</div>'+
							'<div style="text-align:right;font-size:12px;">2012-2-2</div>'+
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
	$.getJSON("./AddNoticeServlet",{NoticeTitle:NoticeTitle,NoticeContent:NoticeContent},function(json){
		if (json.sign) {
			alert("添加成功!");
			showaffiche("#affiche");
			closepage();
		}else {
			alert("添加失败!");
			closepage();
		}
	})	



}
