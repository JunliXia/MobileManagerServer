<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'transactionmanage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	 <link rel="stylesheet" href="css/transactionmanage.css" type="text/css"></link>
  	 <script type="text/javascript" src="js/transactionmanage.js"></script>
  
  </head>
  
  <body>
	<div class="tran">
	<div class="tran-head">
		<div style="float: left;">事务管理</div>
		<div id="complain" class="trantitle" onclick="showcomplain(this)">投诉建议</div>
		<div id="affiche" class="trantitle" onclick="showaffiche(this)">通知公告</div>
	</div>
	<div class="tran-main">
		
		
	</div>
	</div>
  </body>
</html>
