<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="bean.OInfo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path 	= 	request.getContextPath();
	String basePath =	request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
    List<OInfo> list= 	(List<OInfo>)request.getAttribute("list");
    String keyword	=	(String)request.getAttribute("keyword");  //一共多少记录
    int goodsid	=	(Integer)request.getAttribute("goodsid");  //一共多少记录
    int totalPage 	=   (Integer)request.getAttribute("totalPage");  //一共多少页
    int totalRecord =   (Integer)request.getAttribute("totalRecord");  //一共多少记录
    int firstPage 	=  	(Integer)request.getAttribute("firstPage"); //当前页
    int currentPage =  	(Integer)request.getAttribute("currentPage"); //当前页
    int lastPage	=	(Integer)request.getAttribute("lastPage");  //一共多少记录
    int PAGE_SIZE	=	(Integer)request.getAttribute("PAGE_SIZE");  //一共多少记录
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
	<form action="<%=basePath%>oinfo/oinfo_doFind.action" name="oinfolistForm" id="oinfolistForm" method="post">
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
			<li1><a href="<%=basePath%>mainindex.jsp">首页</a></li1>
			<li1><a href="<%=basePath%>goods.jsp">商品查找</a></li1>
			<li1><a href="<%=basePath%>sort/sort_doFind.action">商品分类</a></li1>
			<li1><a href="<%=basePath%>goods/goods_doFind.action">商品列表</a></li1>
			<li1><a href="<%=basePath%>goods/goods_doView.action?goods.goodsId=<%=goodsid%>">商品详情</a></li1>
			<li1><a href="<%=basePath%>gbatch/gbatch_doFind.action?goodsid=<%=goodsid%>">批次列表</a></li1>
			<li1><a  style="color:blue;" href="<%=basePath%>oinfo/oinfo_doFind.action?goodsid=<%=goodsid%>">子订单列表</a></li1>
			<li1><a href="<%=basePath%>gbatch/gbatch_beforedoAdd.action?goodsid=<%=goodsid%>">添加批次</a></li1>
			<li1><a href="<%=basePath%>gbatch/gbatch_doView.action?gbatch.batchId=<%=goodsid%>">批次详情</a></li1>
			<li1><a onClick="history.back(-1)">返回</a></li1>
			</ul>
		</div>

		<div class="rightinfo">
			<div class="tools">
				<ul class="toolbar1">
					<li><input type="text" name="keyword" value="<%=keyword%>" placeholder="请输入关键字" class="findinput" /></li>
					<input type=hidden name=currentPage value="1" />
					<input type=hidden name=goodsid value="<%=goodsid%>" />
					<li><span onclick="document.getElementById('oinfolistForm').submit();">search<img src="<%=basePath%>images/t06.png" /></span></li>
				</ul>
			</div>


			<table class="tablelist">
				<thead>
					<tr>
						<th style="width:60px">序号</th>
						<th>订单ID<i class="sort"></th>
						<th>客户账号</th>
						<th>订单序列号</th>
						<th>订单总金额</th>
						<th>订单状态</th>
						<th>商品名</th>
						<th>商品批次</th>
						<th>该商品单价</th>
						<th>该商品数量</th>
						<th>评论状态</th>
						<th>操作</th>
					</tr>
				</thead>

				<tbody>
					<%
						int startIndex = (currentPage - 1) * PAGE_SIZE;//计算起始序号
											for (int i = 0; i < list.size(); i++) {
											int currentIndex = startIndex + i + 1; //当前记录的序号
											OInfo oinfo = list.get(i); //获取到对象
					%>
					<tr onclick="javascript:location.href='<%=basePath%>oorder/oorder_doView.action?oorder.orderId=<%=oinfo.getOOrder().getOrderId()%>'">
						<td><div align="center"><%=currentIndex%></div></td>
						<td><%=oinfo.getOinfoId()%></td>
						<td><%=oinfo.getOOrder().getCustomer().getCustomerPhone()%></td>
						<td><%=oinfo.getOOrder().getOrderSerial()%></td>
						<td><%=oinfo.getOOrder().getMoneyTotal()%></td>
						
						<%
							String status4="";
							if(oinfo.getOOrder().getStatusOrder()==2){
								status4="未完成";
							}else if(oinfo.getOOrder().getStatusOrder()==3){
								status4="已完成";
							}else{
								status4="已关闭";
							}
						%>
						<td><%=status4 %></td>
						
						<td><%=oinfo.getGBatch().getGoods().getGoodsName()%></td>
						<td><%=oinfo.getGBatch().getBatchId()%></td>
						<td><%=oinfo.getGBatch().getGoods().getMoneyNew()%></td>
						<td><%=oinfo.getGoodsNum()%></td>
						
						<%
							String status5="";
							if(oinfo.getStatusComment()==1){
								status5="未评论";
							}else if(oinfo.getStatusComment()==2){
								status5="已评论";
							}else{
								status5="已关闭";
							}
						%>
						<td><%=status5 %></td>
						
						<td><a href="<%=basePath%>oorder/oorder_doView.action?orderid=<%=oinfo.getOOrder().getOrderId()%>" style="cursor:hand;" class="tablelink">详情</a> </td>
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
					<li class="paginItem"><a href="<%=basePath%>oinfo/oinfo_doFind.action?goodsid=<%=goodsid%>&currentPage=<%=firstPage-PAGE_SIZE%>&keyword=<%=keyword%>">
							<span class="pagepre"></span>
						</a></li>
					<%
						}
					%>

					<%
						for (int i = firstPage; i <=lastPage; i++) {
						if(i==currentPage){
					%>

					<li class="paginItem current"><a href="<%=basePath%>oinfo/oinfo_doFind.action?goodsid=<%=goodsid%>&currentPage=<%=i%>&keyword=<%=keyword%>"><%=i%></a></li>

					<%
						continue;}
					%>

					<li class="paginItem"><a href="<%=basePath%>oinfo/oinfo_doFind.action?goodsid=<%=goodsid%>&currentPage=<%=i%>&keyword=<%=keyword%>"><%=i%></a></li>

					<%
						}
					%>
					<%
						if(lastPage<totalPage){
					%>
					<li class="paginItem"><a href="<%=basePath%>oinfo/oinfo_doFind.action?goodsid=<%=goodsid%>&currentPage=<%=firstPage+PAGE_SIZE%>&keyword=<%=keyword%>">
							<span class="pagenxt"></span>
						</a></li>
					<%
						}
					%>
				</ul>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
