<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>搜索插件</title>
        <link href="http://www.imooc.com/data/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="http://www.imooc.com/data/jquery-1.8.2.min.js"></script>
        <script src="http://www.imooc.com/data/jquery.autocomplete.js" type="text/javascript"></script>
    </head>
    
    <body>
        <div id="divtest">
            <div class="title">
                <span class="fl">搜索插件</span>
            </div>
            <div class="content">
                <span class="fl">用户名</span><br />
                <input id="txtSearch" name="txtSearch" type="text" />
                <div class="tip">
                </div>
            </div>
        </div>
        
        <script type="text/javascript">
            $(function () {
                var arrUserName = ["王五", "刘明", "李小四", "刘促明", "李渊", "张小三", "王小明"];
            $("#txtSearch").autocomplete(arrUserName,{
                    minChars: 0, //双击空白文本框时显示全部提示数据
                    formatItem: function (data, i, total) {
                        return "<I>" + data[0] + "</I>"; //改变匹配数据显示的格式
                    },
                    formatMatch: function (data, i, total) {
                        return data[0];
                    },
                    formatResult: function (data) {
                        return data[0];
                    }
                }).result(SearchCallback); 
                function SearchCallback(event, data, formatted) {
                    $(".tip").show().html("您的选择是：" + (!data ? "空" : formatted));
                }
            });
        </script>
    </body>
</html>