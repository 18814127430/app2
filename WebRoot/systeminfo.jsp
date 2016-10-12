<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>systeminfo.jsp</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jsapi.js"></script>
<script type="text/javascript" src="js/format+zh_CN,default,corechart.I.js"></script>
<script type="text/javascript" src="js/jquery.gvChart-1.0.1.min.js"></script>
<script type="text/javascript" src="js/jquery.ba-resize.min.js"></script>
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="mainindex.jsp">首页</a></li>
			<li><a href="systeminfo.jsp">系统信息</a></li>
		</ul>
	</div>
	<div class="mainbox">
		<div style="width:400px; float:left; border: 1px; border-color: #CBE2E4;
display: inline-block; margin: 1ex;">
			<div class="listtitle">
				<a href="mainindex.jsp" class="more1">更多</a>
				系统信息
			</div>
			<ul class="newlist">
				<li>系统平台：xxxxxxxxxxx</li>
				<li>系统类型：xxxxxxxxxxx</li>
				<li>系统使用：xxxxxxxxxxx</li>
				<li>公司：xxxxxxxxxxx</li>
				<li>公司类型：xxxxxxxxxxx</li>
				<li>公司联系方式：xxxxxxxxxxx</li>
				<li>特许经营许可证号码：xxxxxxxxxxx</li>
				<li>制作团队：xxxxxxxxxxx</li>
				<li>制作团队联系方式：xxxxxxxxxxx</li>
			</ul>
		</div>
		<div style="width:400px; float:left; border: 1px; border-color: #CBE2E4;display: inline-block; margin: 1ex;">
			<div class="listtitle">
				<a href="mainindex.jsp" class="more1">更多</a>
				信息统计
			</div>
			<ul class="newlist">
				<li><i>管理员数：</i>62</li>
				<li><i>客户数：</i>5546</li>
				<li><i>上线人数：</i>2315</li>
				<li><i>登录次数：</i>1585</li>
				<li><i>评论数：</i>5342</li>
			</ul>
		</div>
	</div>
</body>
</html>
