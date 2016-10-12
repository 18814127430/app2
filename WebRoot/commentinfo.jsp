<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.Comment"%>
<%@ page import="bean.Customer"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
			+ "/";
	Comment comment = (Comment) request.getAttribute("comment");
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
<title>查看评论</title>

<link rel="stylesheet" href="<%=basePath%>css/style.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/select.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>editor/themes/default/default.css" />
<link rel="stylesheet" href="<%=basePath%>editor/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=basePath%>editor/kindeditor-all.js"></script>
<script charset="utf-8" src="<%=basePath%>editor/lang/zh-CN.js"></script>
<script charset="utf-8" src="<%=basePath%>editor/plugins/code/prettify.js"></script>
<script type="text/javascript" src=" <%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src=" <%=basePath%>js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src=" <%=basePath%>js/select-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/laydate.js"></script>



<script type="text/javascript">
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="commentcontent"]', {
				cssPath : '<%=basePath%>editor/plugins/code/prettify.css',
				uploadJson : '<%=basePath%>editor/jsp/upload_json.jsp',
				fileManagerJson : '<%=basePath%>editor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
				}
			});
		prettyPrint();
});
</script>


</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li1>
			<a href="<%=basePath%>mainindex.jsp">首页</a></li1>
			<li1>
			<a href="<%=basePath%>customer/customer_doFind.action">顾客列表</a></li1>
			<li1>
			<a href="<%=basePath%>customer/customer_doView.action?customerid=<%=comment.getOInfo().getOOrder().getCustomer().getCustomerId()%>">顾客详情</a></li1>
			<li1>
			<a href="<%=basePath%>goods/goods_doFind.action">商品列表</a></li1>
			<li1>
			<a href="<%=basePath%>goods/goods_doView.action?goods.goodsId=<%=comment.getOInfo().getGBatch().getGoods().getGoodsId()%>">商品详情</a></li1>
			<li1>
			<a href="<%=basePath%>goods/goods_doEdit.action?goods.goodsId=<%=comment.getOInfo().getGBatch().getGoods().getGoodsId()%>">商品修改</a></li1>
			<li1>
			<a href="<%=basePath%>gbatch/gbatch_doFind.action?goodsid=<%=comment.getOInfo().getGBatch().getGoods().getGoodsId()%>">商品批次</a></li1>
			<li1>
			<a href="<%=basePath%>oinfo/oinfo_doFind.action?goodsid=<%=comment.getOInfo().getGBatch().getGoods().getGoodsId()%>">商品订单</a></li1>
			<li1>
			<a style="color:blue;" href="<%=basePath%>comment/comment_doView.action?commentid=<%=comment.getCommentId()%>">评价详情</a></li1>
			<li1>
			<a onClick="history.back(-1)">返回</a></li1>
		</ul>
	</div>

	<div id="tab1" name="tab1" style="background: none repeat scroll 0% 0% rgb(255, 255, 255);">
		<div class="tabson">
			<div id="formtitle1" class="formtitle">
				<span>订单概览</span>
			</div>
			<div class="tabson1">
				<ul class="forminfo">
					<li><label>订单ID<b>*</b></label><input type="text" value="<%=comment.getOInfo().getOOrder().getOrderId()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<li><label>订单Serial<b>*</b></label><input type="text" value="<%=comment.getOInfo().getOOrder().getOrderSerial()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<li><label>订单商品<b>*</b></label><input type="text" value="<%=comment.getOInfo().getOOrder().getOrderSubject()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<li><label>商品数量<b>*</b></label> <input type="text" value="<%=comment.getOInfo().getOOrder().getOrderBody()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<li><label>商品批次<b>*</b></label><input type="text" value="<%=comment.getOInfo().getOOrder().getOrderRemark()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<li><label>商品价格<b>*</b></label><input type="text" value="<%=comment.getOInfo().getOOrder().getOrderDate()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
				</ul>
			</div>
			<div class="tabson1">
				<ul class="forminfo">
					<%
						String status1 = "";
						if (comment.getOInfo().getOOrder().getStatusMethod() == 2) {
							status1 = "货到付款";
						}
						else if (comment.getOInfo().getOOrder().getStatusMethod() == 3) {
							status1 = "线上支付";
						}
						else {
							status1 = "已关闭";
						}
					%>
					<li><label>支付方式<b>*</b></label><input type="text" value="<%=status1%>" class="dfinput1" readonly="true" style="width:320px;" /></li>


					<%
						String status2 = "";
						if (comment.getOInfo().getOOrder().getStatusPay() == 2) {
							status2 = "未付款";
						}
						else if (comment.getOInfo().getOOrder().getStatusPay() == 3) {
							status2 = "已付款";
						}
						else {
							status2 = "已关闭";
						}
					%>
					<li><label>付款状态<b>*</b></label><input type="text" value="<%=status2%>" class="dfinput1" readonly="true" style="width:320px;" /></li>

					<%
						String status3 = "";
						if (comment.getOInfo().getOOrder().getStatusSend() == 2) {
							status3 = "待发货";
						}
						else if (comment.getOInfo().getOOrder().getStatusSend() == 3) {
							status3 = "已发货";
						}
						else if (comment.getOInfo().getOOrder().getStatusSend() == 4) {
							status3 = "已收货";
						}
						else {
							status3 = "已关闭";
						}
					%>
					<li><label>商品状态<b>*</b></label><input type="text" value="<%=status3%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<%
						String status4 = "";
						if (comment.getOInfo().getOOrder().getStatusOrder() == 2) {
							status4 = "未完成";
						}
						else if (comment.getOInfo().getOOrder().getStatusOrder() == 3) {
							status4 = "已完成";
						}
						else {
							status4 = "已关闭";
						}
					%>
					<li><label>订单状态<b>*</b></label><input type="text" value="<%=status4%>" class="dfinput1" readonly="true" style="width:320px;" /></li>

					<%
						String status5 = "";
						if (comment.getOInfo().getOOrder().getStatusRefund() == 2 && comment.getOInfo().getOOrder().getStatusPay() == 2) {
							status5 = "请求取消订单";
						}
						else if (comment.getOInfo().getOOrder().getStatusRefund() == 2
								&& comment.getOInfo().getOOrder().getStatusPay() == 3) {
							status5 = "请求退款";
						}
						else if (comment.getOInfo().getOOrder().getStatusRefund() == 3
								&& comment.getOInfo().getOOrder().getStatusPay() == 2) {
							status5 = "取消订单成功";
						}
						else if (comment.getOInfo().getOOrder().getStatusRefund() == 3
								&& comment.getOInfo().getOOrder().getStatusPay() == 3) {
							status5 = "退款成功";
						}
						else if (comment.getOInfo().getOOrder().getStatusRefund() == 4
								&& comment.getOInfo().getOOrder().getStatusPay() == 2) {
							status5 = "同意取消订单";
						}
						else if (comment.getOInfo().getOOrder().getStatusRefund() == 4
								&& comment.getOInfo().getOOrder().getStatusPay() == 3) {
							status5 = "同意退款";
						}
						else if (comment.getOInfo().getOOrder().getStatusRefund() == 5
								&& comment.getOInfo().getOOrder().getStatusPay() == 2) {
							status5 = "拒绝取消订单";
						}
						else if (comment.getOInfo().getOOrder().getStatusRefund() == 5
								&& comment.getOInfo().getOOrder().getStatusPay() == 3) {
							status5 = "拒绝退款";
						}
						else {
							status5 = "已关闭";
						}
					%>
					<li><label>退单状态<b>*</b></label><input type="text" value="<%=status5%>" class="dfinput1" readonly="true" style="width:320px;" /></li>

				</ul>
			</div>
		</div>
		<div class="ibox"></div>
		<div class="tabson">
			<div id="formtitle1" class="formtitle">
				<span>评价详情</span>
			</div>
			<div class="tabson1">
				<ul class="forminfo">
					<li><label>顾客账号<b>*</b></label><input type="text" value="<%=comment.getOInfo().getOOrder().getCustomer().getCustomerPhone()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<li><label>评价星数<b>*</b></label><input type="text" value="<%=comment.getCommentStar()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<li><label>评价详情<b>*</b></label><textarea name="commentcontent" readonly="true" style="width:700px;height:250px;visibility:hidden;"><%=comment.getCommentContent()%></textarea></li>
				</ul>
			</div>
			<div class="tabson1">
				<ul class="forminfo">
					<li><label>评价 ID<b>*</b></label><input type="text" value="<%=comment.getCommentId()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<li><label>评价次数<b>*</b></label><input type="text" value="<%=comment.getCommentCount()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<li><label>评价时间<b>*</b></label><input type="text" value="<%=comment.getCommentDate()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
				</ul>
			</div>
		</div>
		<div class="ibox"></div>
		<div class="ibox"></div>
</body>
</html>
