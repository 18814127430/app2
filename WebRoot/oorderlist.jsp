<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="bean.OOrder"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path 	= 	request.getContextPath();
	String basePath =	request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
    List<OOrder> list= 	(List<OOrder>)request.getAttribute("list");
    String keyword	=	(String)request.getAttribute("keyword");  //一共多少记录
    int totalPage 	=   (Integer)request.getAttribute("totalPage");  //一共多少页
    int totalRecord =   (Integer)request.getAttribute("totalRecord");  //一共多少记录
    int firstPage 	=  	(Integer)request.getAttribute("firstPage"); //当前页
    int currentPage =  	(Integer)request.getAttribute("currentPage"); //当前页
    int lastPage	=	(Integer)request.getAttribute("lastPage");  //一共多少记录
    int PAGE_SIZE	=	(Integer)request.getAttribute("PAGE_SIZE");  //一共多少记录
    int type	    =	(Integer)request.getAttribute("type");  //一共多少记录
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
<title>订单列表</title>

<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>

</head>

<body>
	<form action="<%=basePath%>oorder/oorder_doFind.action" name="oorderlistForm" id="oorderlistForm" method="post">
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">

				<li1>
				<a href="<%=basePath%>mainindex.jsp">首页</a></li1>
				<li1>
				<a href="<%=basePath%>oorder.jsp">订单查找</a></li1>
				<li1>
				<a style="color:blue;" href="<%=basePath%>oorder/oorder_doFind.action">订单列表</a></li1>
				<li1>
				<a href="<%=basePath%>oorder/oorder_doFind_3.action">待处理订单</a></li1>
				<li1>
				<a onClick="history.back(-1)">返回</a></li1>
			</ul>
		</div>

		<div class="rightinfo">
			<div class="tools">
				<ul class="toolbar">
					<li onclick="javascript:location.href='<%=basePath%>oorder/oorder_doFind.action?type=<%=1%>'"><span><img src="<%=basePath%>images/t01.png" /></span>待付款</li>
					<li onclick="javascript:location.href='<%=basePath%>oorder/oorder_doFind.action?type=<%=2%>'"><span><img src="<%=basePath%>images/t01.png" /></span>已付款</li>
					<li onclick="javascript:location.href='<%=basePath%>oorder/oorder_doFind.action?type=<%=3%>'"><span><img src="<%=basePath%>images/t01.png" /></span>待发货</li>
					<li onclick="javascript:location.href='<%=basePath%>oorder/oorder_doFind.action?type=<%=4%>'"><span><img src="<%=basePath%>images/t01.png" /></span>已发货</li>
					<li onclick="javascript:location.href='<%=basePath%>oorder/oorder_doFind.action?type=<%=5%>'"><span><img src="<%=basePath%>images/t01.png" /></span>已收货</li>
					<li onclick="javascript:location.href='<%=basePath%>oorder/oorder_doFind.action?type=<%=6%>'"><span><img src="<%=basePath%>images/t01.png" /></span>已完成</li>
					<li onclick="javascript:location.href='<%=basePath%>oorder/oorder_doFind.action?type=<%=7%>'"><span><img src="<%=basePath%>images/t01.png" /></span>待退款</li>
					<li onclick="javascript:location.href='<%=basePath%>oorder/oorder_doFind.action?type=<%=8%>'"><span><img src="<%=basePath%>images/t01.png" /></span>退款成功</li>
				</ul>
				<ul class="toolbar1">
					<li style="width:200px"><input type="text" name="keyword" value="<%=keyword%>" placeholder="请输入关键字" class="findinput" style="width:200px" /></li>
					<input type=hidden name=currentPage value="1" />
					<input type=hidden name=type value="<%=type%>" />
					<li><span onclick="document.getElementById('oorderlistForm').submit();">search<img src="<%=basePath%>images/t06.png" /></span></li>
				</ul>
			</div>

			<table class="tablelist">
				<thead>
					<tr>
						<th style="width:40px">序号</th>
						<th>订单序列</th>
						<th>客户账号</th>
						<th>订单内容</th>
						<th>订单金额</th>
						<th>支付方式</th>
						<th>付款状态</th>
						<th>商品状态</th>
						<th>订单状态</th>
						<th>退单状态</th>
						<th>下单日期</th>
						<th>操作</th>
					</tr>
				</thead>

				<tbody>
					<%
						int startIndex = (currentPage - 1) * PAGE_SIZE;//计算起始序号
									for (int i = 0; i < list.size(); i++) {
									int currentIndex = startIndex + i + 1; //当前记录的序号
									OOrder oorder = list.get(i); //获取到对象
					%>
					<tr onclick="javascript:location.href='<%=basePath%>oorder/oorder_doView.action?orderid=<%=oorder.getOrderId()%>'">
						<td style="width:40px;"><div align="center"><%=currentIndex%></div></td>
						<td style="width:140px;"><%=oorder.getOrderSerial()%></td>
						<td><%=oorder.getCustomer().getCustomerPhone()%></td>
						<td style="width:140px;"><marquee width=140px height="28px" direction="left" behavior="scroll" scrollamount="3" onmouseover="this.stop()" onmouseout="this.start()">
								<a href=""><%=oorder.getOrderBody()%></a>
							</marquee></td>
						<td><%=oorder.getMoneyTotal()%>
							<p>
								运费：<%=oorder.getMoneyDeliver()%></p></td>

						<%
							String status1="";
											if(oorder.getStatusMethod()==2){
												status1="货到付款";
											}else if(oorder.getStatusMethod()==3){
												status1="线上支付";
											}else{
												status1="已关闭";
											}
						%>
						<td><%=status1%></td>

						<%
							String status2="";
											if(oorder.getStatusPay()==2){
												status2="未付款";
											}else if(oorder.getStatusPay()==3){
												status2="已付款";
											}else{
												status2="已关闭";
											}
						%>
						<td><%=status2%></td>

						<%
							String status3="";
											if(oorder.getStatusSend()==2){
												status3="待发货";
											}else if(oorder.getStatusSend()==3){
												status3="已发货";
											}else if(oorder.getStatusSend()==4){
												status3="已收货";
											}else{
												status3="已关闭";
											}
						%>
						<td><%=status3%></td>

						<%
							String status4="";
											if(oorder.getStatusOrder()==2){
												status4="未完成";
											}else if(oorder.getStatusOrder()==3){
												status4="已完成";
											}else{
												status4="已关闭";
											}
						%>
						<td><%=status4%></td>

						<%
							String status5="";
												if (oorder.getStatusRefund() == 2) {
													status5="请求退款";
												}
												else if (oorder.getStatusRefund() == 3) {
													status5="退款成功";
												}
												else if (oorder.getStatusRefund() == 4) {
													status5="同意退款";
												}
												else if (oorder.getStatusRefund() == 5) {
													status5="拒绝退款";
												}
												else {
													status5="已关闭";
												}
						%>
						<td><%=status5%></td>


						<td><%=oorder.getOrderDate()%></td>
						<td><a href="<%=basePath%>oorder/oorder_doView.action?orderid=<%=oorder.getOrderId()%>" style="cursor:hand;" class="tablelink">详情</a></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>

			<div class="pagin">
				<div class="message">
					共
					<i class="blue"><%=totalRecord%></i>
					条记录，当前显示第
					<i class="blue"><%=currentPage%></i>
					/<%=totalPage%>页
				</div>
				<ul class="paginList">
					<%
						if(firstPage-PAGE_SIZE>=1) {
					%>
					<li class="paginItem"><a href="<%=basePath%>oorder/oorder_doFind.action?type=<%=type%>&currentPage=<%=firstPage-PAGE_SIZE%>&keyword=<%=keyword%>">
							<span class="pagepre"></span>
						</a></li>
					<%
						}
					%>

					<%
						for (int i = firstPage; i <=lastPage; i++) {
																																													if(i==currentPage){
					%>

					<li class="paginItem current"><a href="<%=basePath%>oorder/oorder_doFind.action?type=<%=type%>&currentPage=<%=i%>&keyword=<%=keyword%>"><%=i%></a></li>

					<%
						continue;}
					%>

					<li class="paginItem"><a href="<%=basePath%>oorder/oorder_doFind.action?type=<%=type%>&currentPage=<%=i%>&keyword=<%=keyword%>"><%=i%></a></li>

					<%
						}
					%>
					<%
						if(lastPage<totalPage){
					%>
					<li class="paginItem"><a href="<%=basePath%>oorder/oorder_doFind.action?type=<%=type%>&currentPage=<%=firstPage+PAGE_SIZE%>&keyword=<%=keyword%>">
							<span class="pagenxt"></span>
						</a></li>
					<%
						}
					%>
				</ul>
			</div>
		</div>
		<div class="ibox"></div>
		<div class="ibox"></div>
		<div class="ibox"></div>
	</form>
	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
