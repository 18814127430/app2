<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bean.Collect"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path 	= 	request.getContextPath();
	String basePath =	request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
    List<Collect> list= 	(List<Collect>)request.getAttribute("list");
    String keyword	=	(String)request.getAttribute("keyword");  //一共多少记录
    int customerid	=	(Integer)request.getAttribute("customerid");  //一共多少记录
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
<title>收藏列表</title>


<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>


</head>

<body>
	<form action="<%=basePath%>collect/collect_doFindByCustomerId.action" name="collectlistForm" id="collectlistForm" method="post">
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
			<li1><a href="<%=basePath%>mainindex.jsp">首页</a></li1>
			<li1><a href="<%=basePath%>customer/customer_doFind.action">顾客列表</a></li1>
			<li1><a href="<%=basePath%>customer/customer_doView.action?customerid=<%=customerid%>">顾客信息</a></li1>
			<li1><a href="<%=basePath%>caddress/caddress_doFind.action?customerid=<%=customerid%>">地址列表</a></li1>
			<li1><a href="<%=basePath%>oorder/oorder_doFind.action?customerid=<%=customerid%>">订单列表</a></li1>
			<li1><a href="<%=basePath%>cart/cart_doFind.action?customerid=<%=customerid%>">购物车列表</a></li1>
			<li1><a style="color:blue;" href="<%=basePath%>collect/collect_doFind.action?customerid=<%=customerid%>">收藏列表</a></li1>
			<li1><a href="<%=basePath%>comment/comment_doFind.action?customerid=<%=customerid%>">评论列表</a></li1>
			<li1><a onClick="history.back(-1)">返回</a></li1>
			</ul>
		</div>

		<div class="rightinfo">
			<div class="tools">
			
				<ul class="toolbar1">
					<li><input type="text" name="keyword" value="<%=keyword%>" placeholder="请输入关键字" class="findinput" /></li>
					<input type=hidden name=currentPage value="1" />
					<input type=hidden name=customerid value="<%=customerid%>" />
					<li><span onclick="document.getElementById('collectlistForm').submit();">search<img src="<%=basePath%>images/t06.png" /></span></li>
				</ul>
				
			</div>

			<table class="tablelist">
				<thead>
					<tr>
						<th style="width:60px;">序号</th>
						<th>收藏ID</th>
						<th>客户账号</th>
						<th>商品ID</th>
						<th>商品名</th>
						<th>创建时间</th>
						<th>操作</th>
					</tr>
				</thead>


				<tbody>
					<%
						int startIndex = (currentPage - 1) * PAGE_SIZE;//计算起始序号
												for (int i = 0; i < list.size(); i++) {
												int currentIndex = startIndex + i + 1; //当前记录的序号
												Collect collect = list.get(i); //获取到对象
					%>
					<tr onclick="javascript:location.href='<%=basePath%>collect/collect_doView.action?collect.collectId=<%=collect.getCollectId()%>'">
						<td><div align="center"><%=currentIndex%></div></td>
						<td><%=collect.getCollectId()%></td>
						<td><%=collect.getCustomer().getCustomerPhone()%></td>
						<td><%=collect.getGoods().getGoodsId()%></td>
						<td><%=collect.getGoods().getGoodsName()%></td>
						<td><%=collect.getCollectDate()%></td>
						<td><a href="<%=basePath%>collect/collect_doView.action?collect.collectId=<%=collect.getCollectId()%>" style="cursor:hand;" class="tablelink">查看</a></td>
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
					<li class="paginItem"><a href="<%=basePath%>collect/collect_doFindByCustomerId.action?customerid=<%=customerid%>&currentPage=<%=firstPage-PAGE_SIZE%>&keyword=<%=keyword%>">
							<span class="pagepre"></span>
						</a></li>
					<%
						}
											for (int i = firstPage; i <=lastPage; i++) {
												if(i==currentPage){
					%>

					<li class="paginItem current"><a href="<%=basePath%>collect/collect_doFindByCustomerId.action?customerid=<%=customerid%>&currentPage=<%=i%>&keyword=<%=keyword%>"><%=i%></a></li>
					<%
						continue;}
					%>

					<li class="paginItem"><a href="<%=basePath%>collect/collect_doFindByCustomerId.action?customerid=<%=customerid%>&currentPage=<%=i%>&keyword=<%=keyword%>"><%=i%></a></li>
					<%
						}
					%>
					<%
						if(lastPage<totalPage){
					%>
					<li class="paginItem"><a href="<%=basePath%>collect/collect_doFindByCustomerId.action?customerid=<%=customerid%>&currentPage=<%=firstPage+PAGE_SIZE%>&keyword=<%=keyword%>">
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
