<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.Cart"%>
<%@ page import="bean.Customer"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	Cart cart = (Cart) request.getAttribute("cart");
	Object user = session.getAttribute("user");
	if (user == null) {
		response.getWriter().println("<script>window.top.location.href='" + basePath + "admin/admin_doLogin.action';</script>");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看购物车</title>


<link href=" <%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href=" <%=basePath%>css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src=" <%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src=" <%=basePath%>js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src=" <%=basePath%>js/select-ui.min.js"></script>
<script type="text/javascript" src=" <%=basePath%>editor/kindeditor.js"></script>



</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="<%=basePath%>mainindex.jsp">首页</a></li>
			<li><a onClick="history.back(-1)">上一级</a></li>
			<li><a href="#">购物车详情</a></li>
		</ul>
	</div>

	<div class="formbody">
		<div class="formtitle">
			<span>查看购物车</span>
		</div>

		<ul class="forminfo">
			<li><label>顾客ID</label> <input value="<%=cart.getCustomer().getCustomerId()%>" style="width:320px;" readonly type="text" class="dfinput1" /></li>
			<li><label>顾客电话</label> <input value="<%=cart.getCustomer().getCustomerPhone()%>" style="width:320px;" readonly type="text" class="dfinput1" /></li>
			<li><label>顾客邮箱</label> <input value="<%=cart.getCustomer().getCustomerMail()%>" style="width:320px;" readonly type="text" class="dfinput1" /></li>
			<li><label>商品ID</label> <input value="<%=cart.getGoods().getGoodsId()%>" style="width:320px;" readonly type="text" class="dfinput1" /></li>
			<li><label>商品名</label> <input value="<%=cart.getGoods().getGoodsName()%>" style="width:320px;" readonly type="text" class="dfinput1" /></li>
			<li><label>数量</label> <input value="<%=cart.getGoodsNum()%>" style="width:320px;" readonly type="text" class="dfinput1" /></li>
			<li><label>运费</label> <input value="<%=cart.getMoneyDeliver()%>" style="width:320px;" readonly type="text" class="dfinput1" /></li>
			<li><label>总金额</label> <input value="<%=cart.getMoneyTotal()%>" style="width:320px;" readonly type="text" class="dfinput1" /></li>
			<div class="ibox"></div>
			<li><label>&nbsp;</label> <input name="" type="button" class="btn" value="返回" onClick="history.back(-1)" /></li>
		</ul>

	</div>
	<div class="ibox"></div>
	<div class="ibox"></div>
	<div class="ibox"></div>
	<div class="ibox"></div>
</body>
</html>
