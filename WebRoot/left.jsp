<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>左侧菜单</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		//导航切换
		$(".menuson li").click(function() {
			$(".menuson li.active").removeClass("active")
			$(this).addClass("active");
		});

		$('.title').click(function() {
			var $ul = $(this).next('ul');
			$('dd').find('ul').slideUp();
			if ($ul.is(':visible')) {
				$(this).next('ul').slideUp(5);
			} else {
				$(this).next('ul').slideDown(5);
			}
		});
	})
</script>
</head>

<body style="background:#f0f9fd;">
	<div class="lefttop">
		<span></span>APP System
	</div>
	<dl class="leftmenu">
	<dd>
			<div class="title">
				<span><img src="<%=basePath%>images/leftico05.png" /></span>管理员
			</div>
			<ul class="menuson">
				<li><cite></cite> <a href="<%=basePath%>admin.jsp" target="rightFrame">管理员查找</a> <i></i></li>
				<li><cite></cite> <a href="<%=basePath%>admin/admin_doFind.action" target="rightFrame">管理员列表</a> <i></i></li>
				<li><cite></cite> <a href="<%=basePath%>adminadd.jsp" target="rightFrame">管理员添加</a> <i></i></li>
			</ul>
		</dd>
		<dd>
			<div class="title">
				<span><img src="<%=basePath%>images/leftico01.png" /></span>商品管理
			</div>
			<ul class="menuson">
				<li><cite></cite> <a href="<%=basePath%>goods.jsp" target="rightFrame">商品查找</a> <i></i></li>
				<li><cite></cite> <a href="<%=basePath%>sort/sort_doFind.action" target="rightFrame">商品分类</a> <i></i></li>
				<li><cite></cite> <a href="<%=basePath%>goods/goods_beforedoAdd.action" target="rightFrame">商品添加</a> <i></i></li>
			</ul>
		</dd>
		<dd>
			<div class="title">
				<span><img src="<%=basePath%>images/leftico02.png" /></span>订单管理
			</div>
			<ul class="menuson">
				<li><cite></cite> <a href="<%=basePath%>order.jsp" target="rightFrame">订单查找</a> <i></i></li>
				<li><cite></cite> <a href="<%=basePath%>order/order_doFind.action" target="rightFrame">所有订单</a> <i></i></li>
				<li><cite></cite> <a href="<%=basePath%>ordercount.jsp" target="rightFrame">数据统计</a> <i></i></li>
			</ul>
		</dd>

		<dd>
			<div class="title">
				<span><img src="<%=basePath%>images/leftico07.png" /></span>顾客管理
			</div>
			<ul class="menuson">
			<li><cite></cite> <a href="<%=basePath%>customer.jsp" target="rightFrame">顾客查找</a> <i></i></li>
				<li><cite></cite> <a href="<%=basePath%>customer/customer_doFind.action" target="rightFrame">顾客列表</a> <i></i></li>
				<li><cite></cite> <a href="<%=basePath%>caddress/caddress_doFind.action" target="rightFrame">顾客地址列表</a> <i></i></li>
			</ul>
		</dd>
		<dd>
			<div class="title">
				<span><img src="<%=basePath%>images/leftico06.png" /></span>信息推送
			</div>
			<ul class="menuson">
				<li><cite></cite> <a href="<%=basePath%>push.jsp" target="rightFrame">推送查找</a> <i></i></li>
				<li><cite></cite> <a href="<%=basePath%>push/push_doFind.action" target="rightFrame">推送列表</a> <i></i></li>
				<li><cite></cite> <a href="<%=basePath%>pushadd.jsp" target="rightFrame">推送添加</a> <i></i></li>
			</ul>
		</dd>
		<dd>
			<div class="title">
				<span><img src="<%=basePath%>images/leftico03.png" /></span>帮助中心
			</div>
			<ul class="menuson">
				<li><cite></cite> <a href="<%=basePath%>help.jsp" target="rightFrame">帮助查找</a> <i></i></li>
				<li><cite></cite> <a href="<%=basePath%>help/help_doFind.action" target="rightFrame">帮助列表</a> <i></i></li>
				<li><cite></cite> <a href="<%=basePath%>helpadd.jsp" target="rightFrame">帮助添加</a> <i></i></li>
			</ul>
		</dd>
		




		<dd>
			<div class="title">
				<span><img src="<%=basePath%>images/leftico04.png" /></span>系统设置
			</div>
			<ul class="menuson">
				<li><cite></cite> <a href="<%=basePath%>mainindex.jsp" target="rightFrame">首页模版</a> <i></i></li>
				<!--       <li><cite></cite><a href="<%=basePath%>systemtool.jsp" target="rightFrame">常用工具</a><i></i></li> -->
				<li><cite></cite> <a href="<%=basePath%>systemlog.jsp" target="rightFrame">系统日志</a> <i></i></li>
				<!--       <li><cite></cite><a href="<basePathth%>systemerror.jsp" target="rightFrame">错误页</a><i></i></li> -->
			</ul>
		</dd>
	</dl>
</body>
</html>
