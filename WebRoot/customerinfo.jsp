<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.Customer"%>
<%@ page import="bean.CAddress"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	Customer customer = (Customer) request.getAttribute("customer");
	String[] list1 = customer.getCustomerArray().split(",");
	int size = list1.length;
	Object user = session.getAttribute("user");
	if (user == null) {
		response.getWriter().println("<script>window.top.location.href='"+ basePath+ "';</script>");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户信息详情查看</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>



<script type="text/javascript">
	$(document).ready(function() {
		$(".click").click(function() {
			$(".tip").fadeIn(200);
		});

		$(".tiptop a").click(function() {
			$(".tip").fadeOut(200);
		});

		$(".sure").click(function() {
			$(".tip").fadeOut(100);
		});

		$(".cancel").click(function() {
			$(".tip").fadeOut(100);
		});
	});
</script>


</head>

<body>
	<div class="place">
		<span>位置:</span>
		<ul class="placeul">
			<li1><a href="<%=basePath%>customer/customer_doFind.action">顾客列表</a></li1>
			<li1><a style="color:blue;" href="<%=basePath%>customer/customer_doView.action?customerid=<%=customer.getCustomerId()%>">顾客详情</a></li1>
			<li1><a href="<%=basePath%>caddress/caddress_doFind.action?customerid=<%=customer.getCustomerId()%>">地址列表</a></li1>
			<li1><a href="<%=basePath%>oorder/oorder_doFind.action?customerid=<%=customer.getCustomerId()%>">订单列表</a></li1>
			<li1><a href="<%=basePath%>cart/cart_doFind.action?customerid=<%=customer.getCustomerId()%>">购物车列表</a></li1>
			<li1><a href="<%=basePath%>collect/collect_doFind.action?customerid=<%=customer.getCustomerId()%>">收藏列表</a></li1>
			<li1><a href="<%=basePath%>comment/comment_doFind.action?customerid=<%=customer.getCustomerId()%>">评论列表</a></li1>
			<li1><a onClick="history.back(-1)">返回</a></li1>
		</ul>
	</div>

	<div id="tab3" class="tabson">

		<div class="ibox"></div>

		<div class="MID" style="HEIGHT: 220PX;">
			<div class="MID1" style="HEIGHT: 220PX;">
				<ul class="forminfo">
					<li><label>客户账号<b>*</b></label> <input type="text" class="dfinput1" value='<%=customer.getCustomerPhone()%>' readonly="true" style="width:320px;" /></li>
					<li><label>客户电话<b>*</b></label> <input type="text" class="dfinput1" value='<%=customer.getCustomerPhone()%>' readonly="true" style="width:320px;" /></li>
					<li><label>客户邮箱<b>*</b></label> <input type="text" class="dfinput1" value='<%=customer.getCustomerMail()%>' readonly="true" style="width:320px;" /></li>
					<li class="click"><label>登录次数<b>*</b></label> <input type="text" class="dfinput1" value='<%=size%>' readonly="true" style="width:320px;" /></li>
				</ul>
			</div>

			<div class="MID2" style="HEIGHT: 220PX;">
				<ul class="forminfo1">
					<li><span> <img src="<%=customer.getCustomerImg()%>" /></span></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="ibox"></div>
	<div class="ibox"></div>
	<div class="ibox"></div>
	<div class="ibox"></div>
	<div class="tip">
		<div class="tiptop">
			<span>登录详情</span>
			<a></a>
		</div>
		<div class="tiplog">
			<textarea class="textarea" readonly="readonly" style="font-size: 15px;color: #046DA9;font-family:微软雅黑;">
		<%
			for (int j = 0; j < size; j++) {
		%>
		<%=list1[j]%>
		<%
			}
		%>
		</textarea>
		</div>
	</div>
</body>
</html>
