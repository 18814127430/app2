<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bean.Admin"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<title>管理员首页</title>

<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>

</head>

<body>
	<form action="<%=basePath%>/admin/admin_doFind.action" name="adminForm" id="adminForm" method="post">

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li1><a href="<%=basePath%>mainindex.jsp">首页</a></li1>
				<li1><a  style="color:blue;" href="<%=basePath%>admin.jsp">管理员查找</a></li1>
				<li1><a href="<%=basePath%>admin/admin_doFind.action">管理员列表</a></li1>
				<li1><a href="<%=basePath%>adminadd.jsp">管理员添加</a></li1>
				<li1><a onClick="history.back(-1)">返回</a></li1>
			</ul>
		</div>

		<div class="mainindex">

			<div class="welinfo">
				<span><img src="<%=basePath%>images/time.png" alt="时间" /></span>
				<b>
					<a href="<%=basePath%>admin/admin_doView.action?admin.adminId=<%=user.getAdminId()%>"><%=user.getAdminName()%></a>
					, 欢迎使用生鲜农产品管理系统
				</b>
			</div>
			<div class="ibox"></div>

			<ul class="seachform">
				<li><label>管理员查询</label> <input type="text" name="keyword" id="keyword" value="" placeholder="请输入关键字" class="scinput" /></li>
				<li><label>&nbsp;</label> <input type="submit" name="button" class="scbtn" value="查询" /></li>
			</ul>

			<div class="xline"></div>

			<ul class="iconlist">
				<li><a href="<%=basePath%>admin/admin_doFind.action">
						<img src="<%=basePath%>images/admin1.png" />
						<p>管理员列表</p>
					</a></li>

				<li><a href="<%=basePath%>adminadd.jsp">
						<img src="<%=basePath%>images/admin3.png" />
						<p>管理员添加</p>
					</a></li>

				<li><a href="<%=basePath%>admin/admin_doView.action?admin.adminId=<%=user.getAdminId()%>">
						<img src="<%=basePath%>images/admin4.png" />
						<p>我的信息</p>
					</a></li>
			</ul>
			<div class="ibox"></div>
			<div class="ibox"></div>
			<div class="ibox"></div>
			<div class="ibox"></div>
			<div class="ibox"></div>
			<div class="ibox"></div>
			<div class="xline"></div>


			<div class="uimakerinfo">
				<b>
					查看生鲜农产品管理系统使用指南和相关信息，联系制作团队：
					<a href="http://www.baidu.com" target="_blank">Alfred</a>
				</b>
			</div>

			<ul class="umlist">
				<li><a href="helplist.jsp">如何发布文章</a></li>
				<li><a href="helplist.jsp">如何访问网站</a></li>
				<li><a href="helplist.jsp">如何管理广告</a></li>
				<li><a href="admminlist.jsp">管理员权限设置</a></li>
				<li><a href="systemerror.jsp">系统设置</a></li>
			</ul>
		</div>
</body>
</html>
