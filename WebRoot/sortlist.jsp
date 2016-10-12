<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="bean.Sort"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path 	= 	request.getContextPath();
	String basePath =	request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	List<Sort> list1 = 	(List<Sort>)request.getAttribute("list1");
	List<Sort> list2 = 	(List<Sort>)request.getAttribute("list2");
	String keyword	=	(String)request.getAttribute("keyword");  //一共多少记
    Object user		=	session.getAttribute("user");
    if(user==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "admin/admin_doLogin.action';</script>");
    }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品分类</title>


<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>


<script language="javascript">
	$(function() {
		//导航切换
		$(".imglist li").click(function() {
			$(".imglist li.selected").removeClass("selected")
			$(this).addClass("selected");
		})
	})
</script>
</head>

<body>
	<form action="<%=basePath%>goods/goods_doFind.action" name="sortlistForm" id="sortlistForm" method="post">
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="<%=basePath%>mainindex.jsp">首页</a></li>
				<li><a href="<%=basePath%>goods.jsp">分类列表</a></li>
				<li><a href="#">分类列表</a></li>
			</ul>
		</div>
		<div class="rightinfo">
			<div class="tools">
				<ul class="toolbar">
					<li onclick="javascript:location.href='<%=basePath%>sort/sort_beforedoAdd.action'"><span><img src="<%=basePath%>images/t01.png" /></span>添加</li>
					<li onclick="history.back(-1);"><span><img src="<%=basePath%>images/t08.png" /></span>返回</li>
				</ul>
				<ul class="toolbar1">
					<li><input type="text" name="keyword" value="<%=keyword%>" placeholder="请输入关键字" class="findinput" /></li>
					<li><span onclick="document.getElementById('sortlistForm').submit();">search<img src="<%=basePath%>images/t06.png" /></span></li>
				</ul>
			</div>

			<%
				for (int i = 0; i < list1.size(); i++) {
					Sort sort1 = (Sort) list1.get(i); //获取到对象
			%>
			<div class="xline"></div>
			<ul class="imglist">
				<div class="welinfo">
					<span><img src="<%=basePath%>images/time.png" alt="时间" /></span>
					<i><%=sort1.getSortName()%></i>
				</div>

				<%
					for (int j = 0; j < list2.size(); j++) {
							Sort sort2 = (Sort) list2.get(j); //获取到对象
							if (sort1.getSortId() == sort2.getSort().getSortId()) {
				%>
				<a href="<%=basePath%>goods/goods_doFindBySortId.action?sortid=<%=sort2.getSortId()%>" target="rightFrame">
					<li class="selected"><span><img src="<%=sort2.getSortImgPath()%>" width=98 height=66 /></span>
						<h2><%=sort2.getSortName()%></h2>

						<p>
							<a href="<%=basePath%>sort/sort_doEdit.action?sort.sortId=<%=sort2.getSortId()%>">编辑</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="#">删除</a>
						</p></li>
				</a>
				<%
					}
						}
				%>
			</ul>
			<%
				}
			%>


		</div>
		<div class="box"></div>
		<div class="box"></div>
		<div class="box"></div>
		<div class="box"></div>
	</form>
</body>
</html>
