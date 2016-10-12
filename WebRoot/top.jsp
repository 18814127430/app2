<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bean.Admin"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Admin user = (Admin) session.getAttribute("user");
	if (user == null) {
		response.getWriter().println(
				"<script>top.location.href='" + basePath
						+ "';</script>");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>top.jsp</title>


<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%=basePath%>js/jquery.js"></script>


<script type="text/javascript">
	$(function() {
		//顶部导航切换
		$(".nav li a").click(function() {
			$(".nav li a.selected").removeClass("selected")
			$(this).addClass("selected");
		})
	})
</script>


</head>

<body style="background:url(images/topbg.gif) repeat-x;">

	<div class="topleft" >
		<a style="display: none" href="mainindex.jsp" target="rightFrame">
			<img src="<%=basePath%>images/logo.png" title="系统首页" />
		</a>
	</div>

	<ul class="nav">
		<li><a href="<%=basePath%>mainindex.jsp" target="rightFrame">
				<img src="<%=basePath%>images/icon01.png" title="首页" />
				<h2>首页</h2>
			</a></li>

		<li><a href="<%=basePath%>customer/customer_doFind.action" target="rightFrame">
				<img src="<%=basePath%>images/icon09.png" title="顾客列表" />
				<h2>顾客列表</h2>
			</a></li>
			
		<li><a href="<%=basePath%>company/company_doFind.action" target="rightFrame">
				<img src="<%=basePath%>images/icon10.png" title="公司列表" />
				<h2>公司列表</h2>
			</a></li>
			
		<li><a href="<%=basePath%>pageedit1.jsp" target="rightFrame">
				<img src="<%=basePath%>images/icon11.png" title="轮播图更新" />
				<h2>轮播图更新</h2>
			</a></li>
			
		<li><a href="<%=basePath%>goods/goods_doFind.action" target="rightFrame">
				<img src="<%=basePath%>images/icon13.png" title="所有商品" />
				<h2>所有商品</h2>
			</a></li>
		
		<li><a href="<%=basePath%>sort/sort_doFind.action" target="rightFrame">
				<img src="<%=basePath%>images/icon12.png" title="商品分类" />
				<h2>商品分类</h2>
			</a></li>

		<li><a href="<%=basePath%>goods/goods_beforedoAdd.action" target="rightFrame">
				<img src="<%=basePath%>images/icon02.png" title="添加商品" />
				<h2>添加商品</h2>
			</a></li>

		<li><a href="<%=basePath%>admin/admin_beforedoAdd.action" target="rightFrame">
				<img src="<%=basePath%>images/icon03.png" title="添加管理员" />
				<h2>添加管理员</h2>
			</a></li>

		<li><a href="<%=basePath%>oorder/oorder_doFind.action" target="rightFrame">
				<img src="<%=basePath%>images/icon15.png" title="所有订单" />
				<h2>所有订单</h2>
			</a></li>
			
		<li><a href="<%=basePath%>oorder/oorder_doFind_S.action" target="rightFrame">
				<img src="<%=basePath%>images/icon16.png" title="待发货订单" />
				<h2>待发货订单</h2>
			</a></li>
			
		<li><a href="<%=basePath%>oorder/oorder_doFind_R.action" target="rightFrame">
				<img src="<%=basePath%>images/icon14.png" title="待退款订单" />
				<h2>待退款订单</h2>
			</a></li>

<!-- 		<li><a href="<%=basePath%>pushadd.jsp" target="rightFrame"> -->
<!-- 				<img src="<%=basePath%>images/icon08.png" title="添加推送" /> -->
<!-- 				<h2>添加推送</h2> -->
<!-- 			</a></li> -->
	</ul>

	<div class="topright">

		<ul>
			<li><span><img src="<%=basePath%>images/help.png" class="helpimg" /></span> <a href="<%=basePath%>help/help_doFind.action" target="rightFrame">帮助</a></li>
			<li><a href="<%=basePath%>system/system_doShow.action" target="rightFrame">关于</a></li>
			<li><a href="<%=basePath%>logout.jsp" target="_parent">退出</a></li>
		</ul>

		<div class="user">
			<span>管理员：<a href="<%=basePath%>admin/admin_doView.action?admin.adminId=<%=user.getAdminId()%>" target="rightFrame" style="color:white"><%=user.getAdminName()%></a></span>
		</div>
	</div>
</body>
</html>
