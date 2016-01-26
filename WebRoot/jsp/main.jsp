<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="css/main.css" type="text/css"></link>
	 <link href="css/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
     <script type="text/javascript" src="js/common/jq.js"></script>
     <script src="js/common/jquery.autocomplete.min.js" type="text/javascript"></script>
      
     <script type="text/javascript" src="js/common/file.js"></script>
  	 <script type="text/javascript" src="js/main.js"></script>
  	 <script type="text/javascript" src="js/common/date.js"></script>
  	 <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=xTGYDhxlbHjfvpB4CsrtxvsA"></script>
  </head>
  
  <body>
    <div class="front"><img src="./pic/tuichu.jpg" onclick="loginout()"  style="height:35px;width: 35px;position: relative;right: 30px;top: 10px;"/></div>
  	<div class="all">
  		<div class="left">
  			<div class="left_head">系统导航</div>
  			<div class="left_list">
  				<div class="one" id="userbit" onclick="changecolors(this),touser()">员工管理</div>
  				<div class="one" onclick="changecolors(this),toattendance()">考勤管理</div>
  				<div class="one" onclick="changecolors(this),toclient()">客户管理<img id=clientpoint src="./pic/redpoint.png" style="height:10px;width:10px; margin-left:30px;	"><img/></div>
  				<div class="one" onclick="changecolors(this),totask()">任务管理<img id=missionpoint src="./pic/redpoint.png" style="height:10px;width:10px; margin-left:30px;	"><img/></div>
  				<div class="one" onclick="changecolors(this),tovisitplan()">拜访管理<img id=visitpoint src="./pic/redpoint.png" style="height:10px;width:10px; margin-left:30px;	"><img/></div>
  				<div class="one" onclick="changecolors(this),tobussiness()">出差管理<img id=bussinesspoint src="./pic/redpoint.png" style="height:10px;width:10px; margin-left:30px;	"><img/></div>
  				<div class="one" onclick="changecolors(this),tomap()">地图相关</div>
  				<div class="one" onclick="changecolors(this),totransaction()">事务查看</div>
<!--  				<div class="one" onclick="changecolors(this),totransaction()">事务查看<img id=transpoint src="./pic/redpoint.png" style="height:10px;width:10px; margin-left:30px;	"><img/></div>-->
  			</div>
  		</div>
  		<div class="right-title"></div>
  		<div class="right">
  			<div class="usermanage"></div>
  			<div class="client"></div>
  			<div class="visitplan"></div>
  			<div class="bussiness"></div>
  			<div class="attendance"></div>
  			<div class="mapmanage"></div>
  			<div class="transactionmanage"></div>
  			<div class="taskmanage"></div>
  		</div>
  	</div>
  	<div class="shadow" style="display: none;">
  		<div class="showpage">
  			<div class="pagehead"><font></font><div class="closepage" onclick="closepage()"></div></div>
  			<div class="pagemain" style="padding: 5px;">
  				
  			</div>
  		</div>
  	</div>
  </body>
</html>
