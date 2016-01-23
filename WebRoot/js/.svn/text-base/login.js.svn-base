function Login(){
	var AdministratorAccount=$(".name input").val();
	var AdministratorPassword=$(".pass input").val();
	
	
	$.getJSON("./WLoginServlet",{
		AdministratorAccount:AdministratorAccount,
		AdministratorPassword:AdministratorPassword,
	},function(outjson){
		if(outjson.check==true){
			window.location.href="jsp/main.jsp"

		}else if(outjson.check==false){
		    alert("账号密码不正确");
		}
	})
}