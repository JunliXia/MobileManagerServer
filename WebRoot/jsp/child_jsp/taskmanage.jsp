<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Taskheadmanage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
     <link rel="stylesheet" href="css/taskmanage.css" type="text/css"></link>
  	 <script type="text/javascript" src="js/taskmanage.js"></script>
  	 <script type="text/javascript" src="js/common/date.js"></script>
  </head>
  
  <body><!--
   <div class="Taskhead_list">          
  			<div class="Taskhead_one" onclick="showonelist()"><img src="./pic/taskone.png"><div class="Taskhead">待接受任务</div></div>
  			<div class="Taskhead_one"onclick="showtwolist()"><img src="./pic/tasktwo.png"><div class="Taskhead">执行中任务</div></div>
  			<div class="Taskhead_one"onclick="showthreelist()"><img src="./pic/taskthree.png"><div class="Taskhead">待处理任务</div></div>
  			<div class="Taskhead_one"onclick="showfourlist()"><img src="./pic/taskfour.png"><div class="Taskhead">已结束任务</div></div>
  			<div class="Taskhead_two"onclick="showfivelist()"><img src="./pic/laji.png"></div>
  		</div>
  		-->
  		<div class="task-list">
		
  	</div>
  </body>
</html>
