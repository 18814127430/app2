<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bean.Admin"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	Admin user = (Admin) session.getAttribute("user");
	if (user == null) {
		response.getWriter().println("<script>top.location.href='" + basePath + "';</script>");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>生鲜农产品管理系统首页</title>

<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>


</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li1> <a style="color:blue;" href="<%=basePath%>mainindex.jsp">首页</a></li1>
			<li1> <a href="<%=basePath%>customer.jsp">顾客查找</a></li1>
			<li1> <a href="<%=basePath%>goods.jsp">商品查找</a></li1>
			<li1> <a href="<%=basePath%>company.jsp">公司查找</a></li1>
			<li1> <a href="<%=basePath%>admin.jsp">管理员</a></li1>
			<li1> <a href="<%=basePath%>oorder.jsp">订单查找</a></li1>
			<li1> <a href="<%=basePath%>push.jsp">推送查找</a></li1>
			<li1> <a href="<%=basePath%>help.jsp">帮助查找</a></li1>
			<li1> <a onClick="history.back(-1)">返回</a></li1>
		</ul>
	</div>

	<div class="mainindex">

		<div class="welinfo">
			<span><img src="<%=basePath%>images/time.png" alt="时间" /></span>
			<b>
				<a href="<%=basePath%>admin/admin_doView.action?admin.adminId=<%=user.getAdminId()%>"><%=user.getAdminName()%></a>
				, 欢迎使用生鲜农产品管理系统 , 请选择操作
			</b>
		</div>

		<div class="box"></div>

		<ul class="iconlist">
			<li><a href="<%=basePath%>goods.jsp"><img src="<%=basePath%>images/find0.png" /><p>商品查找</p></a></li>
			<li><a href="<%=basePath%>oorder.jsp"><img src="<%=basePath%>images/find0.png" /><p>订单查找</p></a></li>
			<li><a href="<%=basePath%>customer.jsp"><img src="<%=basePath%>images/find0.png" /><p>顾客查找</p></a></li>
			<li><a href="<%=basePath%>push.jsp"><img src="<%=basePath%>images/find0.png" /><p>推送查找</p></a></li>
			<li><a href="<%=basePath%>help.jsp"><img src="<%=basePath%>images/find0.png" /><p>帮助查找</p></a></li>
			<li><a href="<%=basePath%>admin.jsp"><img src="<%=basePath%>images/find0.png" /><p>管理员查找</p></a></li>
		</ul>
		
		<ul class="iconlist">
			<li><a href="<%=basePath%>sort/sort_doFind.action"><img src="<%=basePath%>images/goods1.png" /><p>商品分类</p></a></li>
		</ul>

		<div class="ibox"><a class="ibtn"><img src="<%=basePath%>images/iadd.png" />暂时不只是添加快捷功能</a></div>

		<div class="box"></div>
		<div class="xline"></div>

		<div class="uimakerinfo">
		<b>查看生鲜农产品管理系统使用指南和相关信息，联系制作团队：<a href="#" target="_blank">Alfred</a>
		<img  style="CURSOR: pointer;" onclick="javascript:window.open('http://b.qq.com/webc.htm?new=0&sid=1091948334&o=www.easyfresh.com&q=7', '_blank', 'height=502, width=644,toolbar=no,scrollbars=no,menubar=no,status=no');"  border="0" SRC=http://wpa.qq.com/pa?p=1:1091948334:1 alt="Alfred">
		</b>
		</div>

		<ul class="umlist">
			<li><a href="<%=basePath%>helplist.jsp">如何发布文章</a></li>
			<li><a href="<%=basePath%>helplist.jsp">如何访问网站</a></li>
			<li><a href="<%=basePath%>helplist.jsp">如何管理广告</a></li>
			<li><a href="<%=basePath%>adminlist.jsp">管理员权限设置</a></li>
			<li><a href="<%=basePath%>systemerror.jsp">系统设置</a></li>
		</ul>

	</div>
</body>
</html>
