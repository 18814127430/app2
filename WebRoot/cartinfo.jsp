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
		response.getWriter().println("<script>window.top.location.href='" + basePath + "';</script>");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes" />

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
			<li1><a href="<%=basePath%>mainindex.jsp">首页</a></li1>
			<li1><a href="<%=basePath%>customer/customer_doFind.action">顾客列表</a></li1>
			<li1><a href="<%=basePath%>customer/customer_doView.action?customerid=<%=cart.getCustomer().getCustomerId()%>">顾客信息</a></li1>
			<li1><a href="<%=basePath%>caddress/caddress_doFind.action?customerid=<%=cart.getCustomer().getCustomerId()%>">地址列表</a></li1>
			<li1><a  style="color:blue;" href="<%=basePath%>cart/cart_doView.action?cart.cartId=<%=cart.getCartId()%>">购物车详情</a></li1>
			<li1><a href="<%=basePath%>oorder/oorder_doFind.action?customerid=<%=cart.getCustomer().getCustomerId()%>">订单列表</a></li1>
			<li1><a href="<%=basePath%>cart/cart_doFind.action?customerid=<%=cart.getCustomer().getCustomerId()%>">购物车列表</a></li1>
			<li1><a href="<%=basePath%>collect/collect_doFind.action?customerid=<%=cart.getCustomer().getCustomerId()%>">收藏列表</a></li1>
			<li1><a href="<%=basePath%>comment/comment_doFind.action?customerid=<%=cart.getCustomer().getCustomerId()%>">评论列表</a></li1>
			<li1><a onClick="history.back(-1)">返回</a></li1>
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
			<li><label>单价</label> <input value="<%=cart.getGoods().getMoneyNew()%>" style="width:320px;" readonly type="text" class="dfinput1" /></li>
			<li><label>总金额</label> <input value="<%=cart.getGoodsNum() * cart.getGoods().getMoneyNew()%>" style="width:320px;" readonly type="text" class="dfinput1" /></li>
			<div class="ibox"></div>
			<li><label>&nbsp;</label> <input name="" type="button" class="btn" value="返回" onClick="history.back(-1)" /></li>
		</ul>

	</div>
	<div class="ibox"></div>
</body>
</html>
