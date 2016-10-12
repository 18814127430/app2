<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.CAddress"%>
<%@ page import="bean.Customer"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	CAddress caddress = (CAddress) request.getAttribute("caddress");
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
<title>查看地址</title>


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
			<li1><a href="<%=basePath%>customer/customer_doView.action?customerid=<%=caddress.getCustomer().getCustomerId()%>">顾客信息</a></li1>
			<li1><a href="<%=basePath%>caddress/caddress_doFind.action?customerid=<%=caddress.getCustomer().getCustomerId()%>">地址列表</a></li1>
			<li1><a style="color:blue;" href="<%=basePath%>caddress/caddress_doView.action?caddress.addressId=<%=caddress.getAddressId()%>">地址详情</a></li1>
			<li1><a href="<%=basePath%>oorder/oorder_doFind.action?customerid=<%=caddress.getCustomer().getCustomerId()%>">订单列表</a></li1>
			<li1><a href="<%=basePath%>cart/cart_doFind.action?customerid=<%=caddress.getCustomer().getCustomerId()%>">购物车列表</a></li1>
			<li1><a href="<%=basePath%>collect/collect_doFind.action?customerid=<%=caddress.getCustomer().getCustomerId()%>">收藏列表</a></li1>
			<li1><a href="<%=basePath%>comment/comment_doFind.action?customerid=<%=caddress.getCustomer().getCustomerId()%>">评论列表</a></li1>
			<li1><a onClick="history.back(-1)">返回</a></li1>
		</ul>
	</div>

	<div class="formbody">
		<div class="formtitle">
			<span>查看地址</span>
		</div>

		<ul class="forminfo" >
			<li><label>顾客 ID<b>*</b></label> <input value="<%=caddress.getCustomer().getCustomerId()%>" style="width:320px;padding-left:15px;" readonly type="text" class="dfinput1" /></li>
			<li><label>顾客账号<b>*</b></label> <input value="<%=caddress.getCustomer().getCustomerPhone()%>" style="width:320px;padding-left:15px;" readonly type="text" class="dfinput1" /></li>
			<li><label>顾客邮箱<b>*</b></label> <input value="<%=caddress.getCustomer().getCustomerMail()%>" style="width:320px;padding-left:15px;" readonly type="text" class="dfinput1" /></li>
			<li><label>地址 ID<b>*</b></label> <input value="<%=caddress.getAddressId()%>" style="width:320px;padding-left:15px;" readonly type="text" class="dfinput1" /></li>
			<li><label>收货姓名<b>*</b></label> <input value="<%=caddress.getAddressName()%>" style="width:320px;padding-left:15px;" readonly type="text" class="dfinput1" /></li>
			<li><label>收货电话<b>*</b></label> <input value="<%=caddress.getAddressPhone()%>" style="width:320px;padding-left:15px;" readonly type="text" class="dfinput1" /></li>
			<li><label>收货省份<b>*</b></label> <input value="<%=caddress.getAddressProvince()%>" style="width:320px;padding-left:15px;" readonly type="text" class="dfinput1" /></li>
			<li><label>收货城市<b>*</b></label> <input value="<%=caddress.getAddressCity()%>" style="width:320px;padding-left:15px;" readonly type="text" class="dfinput1" /></li>
			<li><label>收货街道<b>*</b></label> <input value="<%=caddress.getAddressStreet()%>" style="width:320px;padding-left:15px;" readonly type="text" class="dfinput1" /></li>
			<li><label>添加时间<b>*</b></label> <input value="<%=caddress.getAddressDate()%>" style="width:320px;padding-left:15px;" readonly type="text" class="dfinput1" /></li>
			<li><label>地址详情<b>*</b></label> <input value="<%=caddress.getAddressDetial()%>" style="width:320px;padding-left:15px;" readonly type="text" class="dfinput1" /></li>
		</ul>

	</div>
</body>
</html>
