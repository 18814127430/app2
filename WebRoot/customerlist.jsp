<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="bean.Customer"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path 	= 	request.getContextPath();
	String basePath =	request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
    List<Customer> list= 	(List<Customer>)request.getAttribute("list");
    String keyword	=	(String)request.getAttribute("keyword");  //一共多少记录
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
<title>客户列表</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript">
	function checkDelete() {
		return confirm("确定要删除？");
	}
</script>
</head>

<body>
	<form action="<%=basePath%>customer/customer_doFind.action" name="customerlistForm" id="customerlistForm" method="post">
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
			<li1><a href="<%=basePath%>mainindex.jsp">首页</a></li1>
			<li1><a href="<%=basePath%>customer.jsp">顾客查找</a></li1>
			<li1><a  style="color:blue;" href="<%=basePath%>customer/customer_doFind.action">顾客列表</a></li1>
			<li1><a href="<%=basePath%>caddress/caddress_doFind.action">所有地址</a></li1>
			<li1><a onClick="history.back(-1)">返回</a></li1>
			</ul>
		</div>
		<div class="rightinfo">
			<div class="tools">
				<ul class="toolbar1">
					<li><input type="text" name="keyword" value="<%=keyword%>" placeholder="请输入关键字" class="findinput" /></li>
					<li><input type=hidden name=currentPage value="1" /></li>
					<li><span onclick="document.getElementById('customerlistForm').submit();">search<img src="<%=basePath%>images/t06.png" /></span></li>
				</ul>
			</div>




			<table class="tablelist">
				<thead>
					<tr>
						<th style="width:60px;">序号</th>
						<th>头像</th>
						<th>账号</th>
						<th>邮箱</th>
						<th>登录次数</th>
						<th>操作</th>
					</tr>
				</thead>


				<tbody>
					<%
						int startIndex = (currentPage - 1) * PAGE_SIZE;//计算起始序号
									for (int i = 0; i < list.size(); i++) {
									int currentIndex = startIndex + i + 1; //当前记录的序号
									Customer customer = list.get(i); //获取到对象
									
									String[] list1= customer.getCustomerArray().trim().split(",");
									int size=list1.length;
					%>
					<tr onclick="javascript:location.href='<%=basePath%>customer/customer_doView.action?customerid=<%=customer.getCustomerId()%>'">
					<td><div align="center"><%=currentIndex%></div></td>
					<td class="imgtd"><a href="" target="rightFrame">
							<img src="<%=customer.getCustomerImg()%>" height=30px border="0">
						</a></td>
					<td><%=customer.getCustomerPhone()%></td>
					<td><%=customer.getCustomerMail()%></td>
					<td><%=size%></td>
					<td><a href="<%=basePath%>customer/customer_doView.action?customerid=<%=customer.getCustomerId()%>" style="cursor:hand;" class="tablelink">查看</a></td>
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
					<li class="paginItem"><a href="<%=basePath%>customer/customer_doFind.action?currentPage=<%=firstPage-PAGE_SIZE%>&keyword=<%=keyword%>">
							<span class="pagepre"></span>
						</a></li>
					<%
						}
					%>

					<%
						for (int i = firstPage; i <=lastPage; i++) {
						if(i==currentPage){
					%>

					<li class="paginItem current"><a href="<%=basePath%>customer/customer_doFind.action?currentPage=<%=i%>&keyword=<%=keyword%>"><%=i%></a></li>

					<%
						continue;}
					%>

					<li class="paginItem"><a href="<%=basePath%>customer/customer_doFind.action?currentPage=<%=i%>&keyword=<%=keyword%>"><%=i%></a></li>

					<%
						}
						if(lastPage<totalPage){
					%>
					<li class="paginItem"><a href="<%=basePath%>customer/customer_doFind.action?currentPage=<%=firstPage+PAGE_SIZE%>&keyword=<%=keyword%>">
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
