<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="bean.OOrder"%>
<%@ page import="bean.OInfo"%>
<%@ page import="bean.Customer"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path 	= 	request.getContextPath();
	String basePath =	request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
    List<OInfo> list= 	(List<OInfo>)request.getAttribute("list");
    OInfo info=(OInfo)list.get(0);
    Object user		=	session.getAttribute("user");
    if(user==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "';</script>");
    }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>orderinfo.jsp</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/select-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>editor/kindeditor.js"></script>
<script type="text/javascript">
	KE.show({
		id : 'content7',
		cssPath : './index.css'
	});
</script>
<script type="text/javascript">
	$(document).ready(function(e) {
		$(".select1").uedSelect({
			width : 320
		});
		$(".select2").uedSelect({
			width : 100
		});
		$(".select3").uedSelect({
			width : 100
		});
	});
</script>
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
		<li1><a href="<%=basePath%>mainindex.jsp">首页</a></li1>
				<li1><a href="<%=basePath%>oorder.jsp">订单查找</a></li1>
				<li1><a href="<%=basePath%>oorder/oorder_doFind.action">订单列表</a></li1>
				<li1><a href="<%=basePath%>oorder/oorder_doFind_3.action">待处理订单</a></li1>
			<li1><a  style="color:blue;" href="<%=basePath%>oorder/oorder_doView.action?orderid=<%=info.getOOrder().getOrderId()%>">订单详情</a></li1>
			<li1><a onClick="history.back(-1)">返回</a></li1>
		</ul>
	</div>

	<div class="formbody" id="formbody">

		<div id="usual1" name="usual1" class="usual">

			<div class="ibox" style="float:right;">
				<a style="float:right;margin-left:5px;margin-right:5px;" class="ibtn" href="#formtitle1">
					<img src="<%=basePath%>images/idown.png" " />订单概览
				</a>
				<a style="float:right;margin-left:5px;margin-right:5px;" class="ibtn" href="#formtitle2">
					<img src="<%=basePath%>images/idown.png" " />订单详情
				</a>
			</div>
			<br>


			<div id="tab1" name="tab1" style="background: none repeat scroll 0% 0% rgb(255, 255, 255);">

				<div class="tabson">
					<div id="formtitle1" class="formtitle">
						<span>订单概览</span>
					</div>

					<div class="tabson1">
						<ul class="forminfo">
							<li><label>订单ID<b>*</b></label><input type="text" value="<%=info.getOOrder().getOrderId()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>订单Serial<b>*</b></label><input type="text" value="<%=info.getOOrder().getOrderSerial()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>订单标题<b>*</b></label><input type="text" value="<%=info.getOOrder().getOrderSubject()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>订单内容<b>*</b></label> <input type="text" value="<%=info.getOOrder().getOrderBody()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>订单备注<b>*</b></label><input type="text" value="<%=info.getOOrder().getOrderRemark()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>下单时间<b>*</b></label><input type="text" value="<%=info.getOOrder().getOrderDate()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>总金额<b>*</b></label><input type="text" value="<%=info.getOOrder().getMoneyTotal()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>配送费<b>*</b></label><input type="text" value="<%=info.getOOrder().getMoneyDeliver()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>支付Serial<b>*</b></label><input type="text" value="<%=info.getOOrder().getPaySerial()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>支付平台<b>*</b></label><input type="text" value="<%=info.getOOrder().getPayMethod()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>支付时间<b>*</b></label><input type="text" value="<%=info.getOOrder().getPayDate()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
						</ul>
					</div>
					<div class="tabson1">
						<ul class="forminfo">
							<li><label>顾客ID<b>*</b></label><input type="text" value="<%=info.getOOrder().getCustomer().getCustomerId()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>顾客电话<b>*</b></label><input type="text" value="<%=info.getOOrder().getCustomer().getCustomerPhone()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>收货电话<b>*</b></label><input type="text" value="<%=info.getOOrder().getAdrPhone()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>收货姓名<b>*</b></label><input type="text" value="<%=info.getOOrder().getAdrName()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>收货省份<b>*</b></label><input type="text" value="<%=info.getOOrder().getAdrProvince()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>收货城市<b>*</b></label><input type="text" value="<%=info.getOOrder().getAdrCity()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>收货街道<b>*</b></label><input type="text" value="<%=info.getOOrder().getAdrStreet()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>地址详情<b>*</b></label><input type="text" value="<%=info.getOOrder().getAdrDetial()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							
							<li><label>配送时间<b>*</b></label><input type="text" value="<%=info.getOOrder().getDeliverTime()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>发票信息<b>*</b></label><input type="text" value="<%=info.getOOrder().getBillMemo()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							
							<li><label>订单数目<b>*</b></label><input type="text" value="<%=info.getOOrder().getNumInfo()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>已评数目<b>*</b></label><input type="text" value="<%=info.getOOrder().getNumComment()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>

						</ul>
					</div>
				</div>

				<div class="ibox"></div>
				
				<div class="tabson">
					<div id="formtitle1" class="formtitle">
						<span>订单状态</span>
					</div>

					<div class="tabson1">
						<ul class="forminfo">
							<%
								String status1="";
								if (info.getOOrder().getStatusMethod() == 2) {
									status1="货到付款";
								}
								else if (info.getOOrder().getStatusMethod() == 3) {
									status1="线上支付";
								}
								else {
									status1="已关闭";
								}
							%>
							<li><label>支付方式<b>*</b></label><input type="text" value="<%=status1 %>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							

							<%
								String status2="";
								if (info.getOOrder().getStatusPay() == 2) {
									status2="未付款";
								}
								else if (info.getOOrder().getStatusPay() == 3) {
									status2="已付款";
								}
								else {
									status2="已关闭";
								}
							%>
							<li><label>付款状态<b>*</b></label><input type="text" value="<%=status2 %>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							
							<%
								String status3="";
								if (info.getOOrder().getStatusSend() == 2) {
									status3="待发货";
								}
								else if (info.getOOrder().getStatusSend() == 3) {
									status3="已发货";
								}
								else if (info.getOOrder().getStatusSend() == 4) {
									status3="已收货";
								}
								else {
									status3="已关闭";
								}
							%>
							<li><label>商品状态<b>*</b></label><input type="text" value="<%=status3 %>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							
						</ul>
					</div>
					<div class="tabson1">
						<ul class="forminfo">
							<%
								String status4="";
								if (info.getOOrder().getStatusOrder() == 2) {
									status4="未完成";
								}
								else if (info.getOOrder().getStatusOrder() == 3) {
									status4="已完成";
								}
								else {
									status4="已关闭";
								}
							%>
							<li><label>订单状态<b>*</b></label><input type="text" value="<%=status4 %>" class="dfinput1" readonly="true" style="width:320px;" /></li>

							<%
								String status5="";
								if (info.getOOrder().getStatusRefund() == 2&&info.getOOrder().getStatusPay() == 2) {
									status5="请求取消订单";
								}
								else if (info.getOOrder().getStatusRefund() == 2&&info.getOOrder().getStatusPay() == 3) {
									status5="请求退款";
								}
								else if (info.getOOrder().getStatusRefund() == 3&&info.getOOrder().getStatusPay() == 2) {
									status5="取消订单成功";
								}
								else if (info.getOOrder().getStatusRefund() == 3&&info.getOOrder().getStatusPay() == 3) {
									status5="退款成功";
								}
								else if (info.getOOrder().getStatusRefund() == 4&&info.getOOrder().getStatusPay() == 2) {
									status5="同意取消订单";
								}
								else if (info.getOOrder().getStatusRefund() == 4&&info.getOOrder().getStatusPay() == 3) {
									status5="同意退款";
								}
								else if (info.getOOrder().getStatusRefund() == 5&&info.getOOrder().getStatusPay() == 2) {
									status5="拒绝取消订单";
								}
								else if (info.getOOrder().getStatusRefund() == 5&&info.getOOrder().getStatusPay() == 3) {
									status5="拒绝退款";
								}
								else {
									status5="已关闭";
								}
							%>
							<li><label>退单状态<b>*</b></label><input type="text" value="<%=status5 %>" class="dfinput1" readonly="true" style="width:320px;" /></li>

						</ul>
					</div>
				</div>

				<div class="ibox"></div>

				<div class="tabson">
					<div id="formtitle2" class="formtitle">
						<span>订单详情</span>
					</div>

					<%
						for (int i = 0; i < list.size(); i++) {
							OInfo oinfo = list.get(i); //获取到对象
					%>
					<div class="formbody" style="float:left;border:1px;margin-bottom: 35px;margin-left: 35px;">
						<ul class="forminfo">
							<li><label>订单ID<b>*</b></label> <input type="text" class="dfinput1" value='<%=oinfo.getOinfoId()%>' readonly /></li>
							<li><label>订单Serial<b>*</b></label> <input type="text" class="dfinput1" value='<%=oinfo.getOinfoSerial()%>' readonly /></li>
							<li><label>商品名称<b>*</b></label> <input type="text" class="dfinput1" value='<%=oinfo.getGBatch().getGoods().getGoodsName()%>' readonly /></li>
							<li><label>商品批次<b>*</b></label> <input type="text" class="dfinput1" value='<%=oinfo.getGBatch().getBatchId()%>' readonly /></li>
							<li><label>商品数量<b>*</b></label> <input type="text" class="dfinput1" value='<%=oinfo.getGoodsNum()%>' readonly /></li>
							<li><label>商品单价<b>*</b></label> <input type="text" class="dfinput1" value='<%=oinfo.getGoodsPrice()%>' readonly /></li>


							<%
								String infostatus1="";
								if (oinfo.getStatusSend() == 2) {
									infostatus1="待发货";
								}
								else if (oinfo.getStatusSend() == 3) {
										infostatus1="已发货";
								}
								else if (oinfo.getStatusSend() == 4) {
										infostatus1="已收货";
								}
								else {
										infostatus1="已关闭";
								}
							%>
<!-- 							<li><label>商品状态<b>*</b></label><input type="text" value="<%=infostatus1 %>" class="dfinput1" readonly="true" style="width:320px;" /></li> -->
							

							<%
								String infostatus2="";
								if (oinfo.getStatusRefund() == 2&&oinfo.getOOrder().getStatusPay() == 2) {
									infostatus2="请求取消订单";
								}
								else if (oinfo.getStatusRefund() == 2&&oinfo.getOOrder().getStatusPay() == 3) {
									infostatus2="请求退款";
								}
								else if (oinfo.getStatusRefund() == 3&&oinfo.getOOrder().getStatusPay() == 2) {
									infostatus2="取消订单成功";
								}
								else if (oinfo.getStatusRefund() == 3&&oinfo.getOOrder().getStatusPay() == 3) {
									infostatus2="退款成功";
								}
								else if (oinfo.getStatusRefund() == 4&&oinfo.getOOrder().getStatusPay() == 2) {
									infostatus2="同意取消订单";
								}
								else if (oinfo.getStatusRefund() == 4&&oinfo.getOOrder().getStatusPay() == 3) {
									infostatus2="同意退款";
								}
								else if (oinfo.getStatusRefund() == 5&&oinfo.getOOrder().getStatusPay() == 2) {
									infostatus2="拒绝取消订单";
								}
								else if (oinfo.getStatusRefund() == 5&&oinfo.getOOrder().getStatusPay() == 3) {
									infostatus2="拒绝退款";
								}
								else {
										infostatus2="已关闭";
								}
							%>
<!-- 							<li><label>退款状态<b>*</b></label><input type="text" value="<%=infostatus2 %>" class="dfinput1" readonly="true" style="width:320px;" /></li> -->
							
							<%
								String infostatus3="";
								if (oinfo.getStatusComment() == 1) {
									infostatus3="未评论";
								}
								else {
									infostatus3="已评论";
								}
							%>
							<li><label>评论状态<b>*</b></label><input type="text" value="<%=infostatus3 %>" class="dfinput1" readonly="true" style="width:320px;" /></li>

						</ul>
					</div>
					<%
						}
					%>
					<div class="ibox"></div>

				</div>

				<div class="ibox"></div>
				<div>
					<%
						if (info.getOOrder().getStatusMethod() == 2 && info.getOOrder().getStatusSend() == 2) {
					%>
					<li style="position:absolute;right:50%;translateX(-50%);"><label>&nbsp;</label> <input onClick="javascript:location.href='<%=basePath%>oorder/oorder_doUpdate_Send.action?orderid=<%=info.getOOrder().getOrderId()%>'" name="button" type="submit" class="btn" value="发货" /></li>
					<%
						}
						else if (info.getOOrder().getStatusMethod() == 3 && info.getOOrder().getStatusPay() == 3 && info.getOOrder().getStatusSend() == 2) {
					%>
					<li style="position:absolute;right:50%;translateX(-50%);"><label>&nbsp;</label> <input onClick="javascript:location.href='<%=basePath%>oorder/oorder_doUpdate_Send.action?orderid=<%=info.getOOrder().getOrderId()%>'" name="button" type="submit" class="btn" value="发货" /></li>
					<%
						}
						else if (info.getOOrder().getStatusPay() == 2 && info.getOOrder().getStatusSend() == 2
								&& info.getOOrder().getStatusRefund() == 2) {
					%>
					<li style="position:absolute;right:50%;translateX(-50%);"><label>&nbsp;</label> <input onClick="javascript:location.href='<%=basePath%>oorder/oorder_doUpdate_Cancel.action?orderid=<%=info.getOOrder().getOrderId()%>'" name="button" type="submit" class="btn" value="同意取消订单" /></li>
					<%
						}
						else if (info.getOOrder().getStatusMethod() == 3 && info.getOOrder().getStatusPay() == 3
								&& info.getOOrder().getStatusSend() == 2 && info.getOOrder().getStatusRefund() == 2) {
					%>
					<li style="position:absolute;right:50%;translateX(-50%);"><label>&nbsp;</label> <input onClick="javascript:location.href='<%=basePath%>oorder/oorder_doUpdate_Refund.action?orderid=<%=info.getOOrder().getOrderId()%>'" name="button" type="submit" class="btn" value="同意退款" /></li>
					<%
						}
						else if (info.getOOrder().getStatusMethod() == 2 && info.getOOrder().getStatusSend() == 3) {
					%>
					<li style="position:absolute;right:50%;translateX(-50%);"><label>&nbsp;</label> <input onClick="javascript:location.href='<%=basePath%>oorder/oorder_doUpdate_Rec.action?orderid=<%=info.getOOrder().getOrderId()%>'" name="button" type="submit" class="btn" value="顾客已收货" /></li>
					<%
						}
						else if (info.getOOrder().getStatusMethod() == 3 && info.getOOrder().getStatusPay() == 3 && info.getOOrder().getStatusSend() == 3) {
					%>
					<li style="position:absolute;right:50%;translateX(-50%);"><label>&nbsp;</label> <input onClick="javascript:location.href='<%=basePath%>oorder/oorder_doUpdate_Rec.action?orderid=<%=info.getOOrder().getOrderId()%>'" name="button" type="submit" class="btn" value="顾客已收货" /></li>
					<%
						}
					%>
				</div>
				<div id="buttom" class="ibox" style="float:right;">
					<a class="ibtn" href="#formbody">
						<img src="<%=basePath%>images/iup.png" style="margin-top: 2px; margin-left: -10px;" />返回顶部
					</a>
				</div>
			</div>
		</div>
	</div>
	<div class="ibox"></div>
	<div class="ibox"></div>
	<div class="ibox"></div>
</body>
</html>
