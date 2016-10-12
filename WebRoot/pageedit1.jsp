<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bean.Page"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
			+ "/";
	Object user = session.getAttribute("user");
	if (user == null) {
		response.getWriter().println("<script>top.location.href='" + basePath + "';</script>");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改轮播图</title>



<link rel="stylesheet" href="<%=basePath%>css/style.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/select.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>editor/themes/default/default.css" />
<link rel="stylesheet" href="<%=basePath%>editor/plugins/code/prettify.css" />



<script charset="utf-8" src="<%=basePath%>editor/kindeditor-all.js"></script>
<script charset="utf-8" src="<%=basePath%>editor/lang/zh-CN.js"></script>
<script charset="utf-8" src="<%=basePath%>editor/plugins/code/prettify.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/select-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/laydate.js"></script>


</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li1> <a href="<%=basePath%>mainindex.jsp">首页</a></li1>
			<li1> <a href="<%=basePath%>page/page_doView.action">商品轮播图详情</a></li1>
			<li1> <a style="color:blue;" href="<%=basePath%>page/page_beforedoAdd.action">商品轮播图生成选择</a></li1>
			<li1> <a href="<%=basePath%>page/page_beforedoAdd.action">商品轮播图添加</a></li1>
			<li1> <a onClick="history.back(-1)">返回</a></li1>
		</ul>
	</div>
	<div class="formbody" id="formbody">
		<div id="usual1" name="usual1" class="usual" style="float:left; margin:15% 30% 15% 30%;">
			<ul class="forminfo" style="float:left;">
				<li>
					<div style="margin:4px 50px 22px 30px">
						<input onclick="javascript:location.href='<%=basePath%>page/page_doEdit1.action'" type="button" id="image1" value="自动生成" style="width: 120px;height: 55px; background:#0D80F2; border-radius:10px;font-size: 20px; font-weight:5550;font-family: 微软雅黑;color: #fff;cursor: pointer;" />
					</div>
				</li>
			</ul>
			<ul class="forminfo" style="float:left;">
				<li>
					<div style="margin:4px 50px 22px 30px">
						<input onclick="javascript:location.href='<%=basePath%>page/page_doEdit2.action'" type="button" id="image1" value="手动添加" style="width: 120px;height: 55px; background:#0D80F2; border-radius:10px;font-size: 20px; font-weight:5550;font-family: 微软雅黑;color: #fff;cursor: pointer;" />
					</div>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>
