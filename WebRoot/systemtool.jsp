<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>systemtool.jsp</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="mainindex.jsp">首页</a></li>
			<li><a href="systemtool.jsp">系统工具箱</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>常用功能</span>
		</div>
		<div class="toolsli">
			<ul class="toollist">
				<li><a href="#">
						<img src="<%=basePath%>images/i01.png" />
					</a>
					<h2>文档打印</h2></li>
				<li><a href="#">
						<img src="<%=basePath%>images/i03.png" />
					</a>
					<h2>安全管理</h2></li>
				<li><a href="#">
						<img src="<%=basePath%>images/i05.png" />
					</a>
					<h2>信息统计</h2></li>
			</ul>
			<span class="tooladd"><img src="<%=basePath%>images/add.png" title="添加" /></span>
		</div>
		<div class="formtitle">
			<span>数据分析</span>
		</div>
		<div class="toolsli">
			<ul class="toollist">
				<li><a href="#">
						<img src="<%=basePath%>images/i06.png" />
					</a>
					<h2>费用汇总</h2></li>
				<li><a href="#">
						<img src="<%=basePath%>images/i07.png" />
					</a>
					<h2>合同利润</h2></li>
				<li><a href="#">
						<img src="<%=basePath%>images/i08.png" />
					</a>
					<h2>收支明细</h2></li>
				<li><a href="#">
						<img src="<%=basePath%>images/i09.png" />
					</a>
					<h2>通知公告</h2></li>
			</ul>
			<span class="tooladd"><img src="<%=basePath%>images/add.png" title="添加" /></span>
		</div>
	</div>
</body>
</html>
