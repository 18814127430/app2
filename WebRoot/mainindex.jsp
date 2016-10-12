<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bean.Admin"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	Admin user = (Admin) session.getAttribute("user");
	if (user == null) {
		response.getWriter().println("<script>top.location.href='" + basePath + "admin/admin_doLogin.action';</script>");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">

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
			<li><a href="<%=basePath%>mainindex.jsp">首页</a></li>
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
			<li><img src="<%=basePath%>images/find0.png" />
				<p>
					<a href="<%=basePath%>goods.jsp">商品查找</a>
				</p></li>
			<li><img src="<%=basePath%>images/find0.png" />
				<p>
					<a href="<%=basePath%>order.jsp">订单查找</a>
				</p></li>
			<li><img src="<%=basePath%>images/find0.png" />
				<p>
					<a href="<%=basePath%>customer.jsp">顾客查找</a>
				</p></li>
			<li><img src="<%=basePath%>images/find0.png" />
				<p>
					<a href="<%=basePath%>push.jsp">推送查找</a>
				</p></li>
			<li><img src="<%=basePath%>images/find0.png" />
				<p>
					<a href="<%=basePath%>help.jsp">帮助查找</a>
				</p></li>

			<li><img src="<%=basePath%>images/find0.png" />
				<p>
					<a href="<%=basePath%>admin.jsp">管理员查找</a>
				</p></li>
		</ul>
		
		<ul class="iconlist">
			<li><img src="<%=basePath%>images/goods1.png" />
				<p>
					<a href="<%=basePath%>sort/sort_doFind.action">商品分类</a>
				</p></li>
		</ul>

		<div class="ibox">
			<a class="ibtn">
				<img src="<%=basePath%>images/iadd.png" />暂时不只是添加快捷功能
			</a>
		</div>
		
		<div class="box"></div>
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
