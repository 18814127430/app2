<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	Object user = session.getAttribute("user");
	int record_1 =  	(Integer)request.getAttribute("record_1");
	int record_2 =  	(Integer)request.getAttribute("record_2");
	int record_3 =  	(Integer)request.getAttribute("record_3");
	int record_4 =  	(Integer)request.getAttribute("record_4");
	int record_5 =  	(Integer)request.getAttribute("record_5");
	int record_6 =  	(Integer)request.getAttribute("record_6");
	if (user == null) {
		response.getWriter().println("<script>top.location.href='" + basePath+ "';</script>");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>systeminfo.jsp</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jsapi.js"></script>
<script type="text/javascript" src="<%=basePath%>js/format+zh_CN,default,corechart.I.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.gvChart-1.0.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.ba-resize.min.js"></script>
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li1><a href="<%=basePath%>mainindex.jsp">首页</a></li1>
			<li1><a style="color:blue;" href="<%=basePath%>system/system_doShow.action">系统信息</a></li1>
			<li1><a onClick="history.back(-1)">返回</a></li1>
		</ul>
	</div>
	<div class="mainbox">
		<div style="width:400px; float:left; border: 1px; border-color: #CBE2E4;display: inline-block; margin: 1ex;">
			<div class="listtitle">
				<a href="<%=basePath%>mainindex.jsp" class="more1">更多</a>
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
				<a href="<%=basePath%>mainindex.jsp" class="more1">更多</a>
				信息统计
			</div>
			<ul class="newlist">
				<li><i>商品类数目：</i><%=record_1 %></li>
				<li><i>管理员数目：</i><%=record_2 %></li>
				<li><i>公司数目：</i><%=record_3 %></li>
				<li><i>顾客数目：</i><%=record_4 %></li>
				<li><i>订单数目：</i><%=record_5 %></li>
				<li><i>商品数目：</i><%=record_6 %></li>
			</ul>
		</div>
	</div>
</body>
</html>
