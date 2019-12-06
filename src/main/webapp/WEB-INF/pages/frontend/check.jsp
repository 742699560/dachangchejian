<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
// 获得项目完全路径（假设你的项目叫myWork，那么获得到的地址就是 http://localhost:8080/myWork/）:
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
// 将 "项目路径basePath" 放入pageContext中
%>

<!DOCTYPE html>
<html  lang="zh-CN">
<head>

    <meta charset="utf-8">
    <title>三河市旅游管理平台</title>
    <base href="<%=basePath%>" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache,must-revalidate" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="Content-Type" content="no-cache, must-revalidate" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="renderer" content="webkit"> 

	<link type="image/png" rel="shortcut icon" href="frontend/images/logo.png"/>
    <link rel="stylesheet" type="text/css" href="frontend/css/global.css"/>
</head>
<body style="width:100%;height:100%;overflow: hidden;position: relative;" >
<div style="width:195px;height:auto;font-size: 18px;text-align:center;color:white;letter-spacing: 3px;cursor: help;position: absolute;top:45%;left:45%;" title="正在维护中">
	<b style="float:left;" class="noSelectTxt">正在维护中</b>
	<b style="float:left;" id="moveDot"></b>
</div>

<script type="text/javascript" src="frontend/js/jquery.min.js"></script>
<script type="text/javascript">

	self.setInterval("clock()",300)
	function clock(){
	  var val = $("#moveDot").text();
	  if(val.length<7){
		  $("#moveDot").text(val+".");
	  }else{
		  $("#moveDot").text("");
	  }
	}
	
	$(document).ready(function(){
		$("body").css("height",$(document).outerHeight(true));
	});
  
</script>
</body>
</html>