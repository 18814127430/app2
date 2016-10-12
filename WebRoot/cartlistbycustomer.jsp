<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bean.Cart"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path 	= 	request.getContextPath();
	String basePath =	request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
    List<Cart> list= 	(List<Cart>)request.getAttribute("list");
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
        response.getWriter().println("<script>top.location.href='" + basePath + "admin/admin_doLogin.action';</script>");
    }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户购物车列表</title>


<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>

</head>

<body>
	<form action="<%=basePath%>cart/cart_doFindByCustomerId.action" name="cartlistForm" id="cartlistForm" method="post">
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="<%=basePath%>mainindex.jsp">首页</a></li>
				<li><a href="<%=basePath%>customer.jsp">顾客</a></li>
				<li><a href="<%=basePath%>customer/customer_doFind.action">顾客列表</a></li>
				<li><a onClick="history.back(-1)">顾客信息</a></li>
				<li><a href="#">顾客购物车列表</a></li>
			</ul>
		</div>

		<div class="rightinfo">
			<div class="tools">

				<ul class="toolbar">
					<li onclick="history.back(-1);"><span><img src="<%=basePath%>images/t08.png" /></span>返回</li>
				</ul>

				<ul class="toolbar1">
					<li><input type="text" name="keyword" value="<%=keyword%>" placeholder="请输入关键字" class="findinput" /></li>
					<input type=hidden name=currentPage value="1" />
					<input type=hidden name=customerid value="<%=customerid%>" />
					<li><span onclick="document.getElementById('cartlistForm').submit();">search<img src="<%=basePath%>images/t06.png" /></span></li>
				</ul>

			</div>

			<table class="tablelist">
				<thead>
					<tr>
						<th style="width:60px;">序号</th>
						<th>购物车ID</th>
						<th>顾客账号</th>
						<th>商品ID</th>
						<th>商品名</th>
						<th>数量</th>
						<th>金额</th>
						<th>创建时间</th>
						<th>操作</th>
					</tr>
				</thead>


				<tbody>
					<%
						int startIndex = (currentPage - 1) * PAGE_SIZE;//计算起始序号
															for (int i = 0; i < list.size(); i++) {
															int currentIndex = startIndex + i + 1; //当前记录的序号
															Cart cart = list.get(i); //获取到对象
					%>
					<tr>
						<td><div align="center"><%=currentIndex%></div></td>
						<td><%=cart.getCartId()%></td>
						<td><%=cart.getCustomer().getCustomerPhone()%></td>
						<td><%=cart.getGoods().getGoodsId()%></td>
						<td><%=cart.getGoods().getGoodsName()%></td>
						<td><%=cart.getMoneyTotal()%><p>
								运费：<%=cart.getMoneyDeliver()%></p></td>
						<td><%=cart.getGoodsNum()%></td>
						<td><%=cart.getCartDate()%></td>
						<td><a href="<%=basePath%>cart/cart_doView.action?cart.cartId=<%=cart.getCartId()%>" style="cursor:hand;" class="tablelink">查看</a></td>
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
					<li class="paginItem"><a href="<%=basePath%>cart/cart_doFindByCustomerId.action?customerid=<%=customerid%>&currentPage=<%=firstPage-PAGE_SIZE%>">
							<span class="pagepre"></span>
						</a></li>
					<%
						}
														for (int i = firstPage; i <=lastPage; i++) {
															if(i==currentPage){
					%>

					<li class="paginItem current"><a href="<%=basePath%>cart/cart_doFindByCustomerId.action?customerid=<%=customerid%>&currentPage=<%=i%>"><%=i%></a></li>
					<%
						continue;}
					%>

					<li class="paginItem"><a href="<%=basePath%>cart/cart_doFindByCustomerId.action?customerid=<%=customerid%>&currentPage=<%=i%>"><%=i%></a></li>
					<%
						}
					%>
					<%
						if(lastPage<totalPage){
					%>
					<li class="paginItem"><a href="<%=basePath%>cart/cart_doFindByCustomerId.action?customerid=<%=customerid%>&currentPage=<%=firstPage+PAGE_SIZE%>">
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
