<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bean.CAddress"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path 	= 	request.getContextPath();
	String basePath =	request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
    List<CAddress> list= 	(List<CAddress>)request.getAttribute("list");
    String keyword	=	(String)request.getAttribute("keyword");  //一共多少记录
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
<title>顾客地址列表</title>


<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>


</head>

<body>
	<form action="<%=basePath%>caddress/caddress_doFind.action" name="caddresslistForm" id="caddresslistForm" method="post">
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="<%=basePath%>mainindex.jsp">首页</a></li>
				<li><a href="<%=basePath%>customer.jsp">顾客</a></li>
				<li><a href="#">地址列表</a></li>
			</ul>
		</div>
		
		<div class="rightinfo">
			<div class="tools">
				<ul class="toolbar">
					<li onclick="history.back(-1);"><span><img src="<%=basePath%>images/t08.png" /></span>返回</li>
				</ul>
				<ul class="toolbar1">
					<li><input type="text" name="keyword" value="<%=keyword%>" placeholder="请输入关键字" class="findinput" /></li>
					<li><input type=hidden name=currentPage value="1" /></li>
					<li><span onclick="document.getElementById('caddresslistForm').submit();">search<img src="<%=basePath%>images/t06.png" /></span></li>
				</ul>
			</div>

			<table class="tablelist">
				<thead>
					<tr>
						<th style="width:60px;">序号</th>
						<th>客户账号</th>
						<th>收货省份</th>
						<th>收货城市</th>
						<th>收货街道</th>
						<th>地址详情</th>
						<th>收货人</th>
						<th>联系电话</th>
						<th>操作</th>
					</tr>
				</thead>


				<tbody>
					<%
						int startIndex = (currentPage - 1) * PAGE_SIZE;//计算起始序号
															for (int i = 0; i < list.size(); i++) {
															int currentIndex = startIndex + i + 1; //当前记录的序号
															CAddress caddress = list.get(i); //获取到对象
					%>
					<tr>
						<td><div align="center"><%=currentIndex%></div></td>
						<td><%=caddress.getCustomer().getCustomerPhone()%></td>
						<td><%=caddress.getAddressProvince()%></td>
						<td><%=caddress.getAddressCity()%></td>
						<td><%=caddress.getAddressStreet()%></td>
						<td><%=caddress.getAddressDetial()%></td>
						<td><%=caddress.getAddressName()%></td>
						<td><%=caddress.getAddressPhone()%></td>
						<td><a href="<%=basePath%>caddress/caddress_doView.action?caddress.addressId=<%=caddress.getAddressId()%>" style="cursor:hand;" class="tablelink">查看</a></td>
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
					<li class="paginItem"><a href="<%=basePath%>caddress/caddress_doFind.action?currentPage=<%=firstPage-PAGE_SIZE%>">
							<span class="pagepre"></span>
						</a></li>
					<%
						}
					%>

					<%
						for (int i = firstPage; i <=lastPage; i++) {
															if(i==currentPage){
					%>

					<li class="paginItem current"><a href="<%=basePath%>caddress/caddress_doFind.action?currentPage=<%=i%>"><%=i%></a></li>

					<%
						continue;}
					%>

					<li class="paginItem"><a href="<%=basePath%>caddress/caddress_doFind.action?currentPage=<%=i%>"><%=i%></a></li>

					<%
						}
					%>
					<%
						if(lastPage<totalPage){
					%>
					<li class="paginItem"><a href="<%=basePath%>caddress/caddress_doFind.action?currentPage=<%=firstPage+PAGE_SIZE%>">
							<span class="pagenxt"></span>
						</a></li>
					<%
						}
					%>
				</ul>
			</div>
		</div>
		<script type="text/javascript">
			$('.tablelist tbody tr:odd').addClass('odd');
		</script>
	</form>

</body>
</html>
