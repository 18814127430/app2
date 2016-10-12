<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.Collect"%>
<%@ page import="bean.Customer"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	Collect collect = (Collect) request.getAttribute("collect");
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
<title>查看收藏</title>


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
			<li1><a href="<%=basePath%>customer/customer_doView.action?customerid=<%=collect.getCustomer().getCustomerId()%>">顾客信息</a></li1>
			<li1><a href="<%=basePath%>caddress/caddress_doFind.action?customerid=<%=collect.getCustomer().getCustomerId()%>">地址列表</a></li1>
			<li1><a href="<%=basePath%>oorder/oorder_doFind.action?customerid=<%=collect.getCustomer().getCustomerId()%>">订单列表</a></li1>
			<li1><a href="<%=basePath%>cart/cart_doFind.action?customerid=<%=collect.getCustomer().getCustomerId()%>">购物车列表</a></li1>
			<li1><a href="<%=basePath%>collect/collect_doFind.action?customerid=<%=collect.getCustomer().getCustomerId()%>">收藏列表</a></li1>
			<li1><a href="<%=basePath%>comment/comment_doFind.action?customerid=<%=collect.getCustomer().getCustomerId()%>">评论列表</a></li1>
			<li1><a style="color:blue;" href="<%=basePath%>collect/collect_doView.action?collect.collectId=<%=collect.getCollectId()%>">收藏详情</a></li1>
			<li1><a onClick="history.back(-1)">返回</a></li1>
		</ul>
	</div>

	<div class="formbody">
		<div class="formtitle">
			<span>收藏内容</span>
		</div>

		<ul class="forminfo">
			<li><label>顾客ID</label> <input value="<%=collect.getCustomer().getCustomerId()%>" style="width:320px;" readonly type="text" class="dfinput1" /></li>
			<li><label>顾客电话</label> <input value="<%=collect.getCustomer().getCustomerPhone()%>" style="width:320px;" readonly type="text" class="dfinput1" /></li>
			<li><label>顾客邮箱</label> <input value="<%=collect.getCustomer().getCustomerMail()%>" style="width:320px;" readonly type="text" class="dfinput1" /></li>
			<li><label>收藏ID</label> <input value="<%=collect.getCollectId()%>" style="width:320px" readonly type="text" class="dfinput1" /></li>
			<li><label>商品ID</label> <input value="<%=collect.getGoods().getGoodsId()%>" style="width:320px;" readonly type="text" class="dfinput1" /></li>
			<li><label>商品名</label> <input value="<%=collect.getGoods().getGoodsName()%>" style="width:320px;" readonly type="text" class="dfinput1" /></li>
			<li><label>收藏日期</label> <input value="<%=collect.getCollectDate()%>" style="width:320px;" readonly type="text" class="dfinput1" /></li>
			<div class="ibox"></div>
			<li><label>&nbsp;</label> <input name="" type="button" class="btn" value="返回" onClick="history.back(-1)" /></li>
		</ul>
	</div>
	<div class="ibox"></div>
</body>
</html>
