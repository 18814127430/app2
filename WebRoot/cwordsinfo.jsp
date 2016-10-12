<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.CWords"%>
<%@ page import="bean.Customer"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	CWords cwords = (CWords) request.getAttribute("cwords");
	Object user = session.getAttribute("user");
	if (user == null) {
		response.getWriter().println("<script>window.top.location.href='" + basePath + "admin/admin_doLogin.action';</script>");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看留言</title>


<link href=" <%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href=" <%=basePath%>css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src=" <%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src=" <%=basePath%>js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src=" <%=basePath%>js/select-ui.min.js"></script>
<script type="text/javascript" src=" <%=basePath%>editor/kindeditor.js"></script>



</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href=" <%=basePath%>mainindex.jsp">首页</a></li>
			<li><a onClick="history.back(-1)">上一级</a></li>
			<li><a href="">查看留言</a></li>
		</ul>
	</div>

	<div class="formbody">
		<div class="formtitle">
			<span>查看留言</span>
		</div>

		<ul class="forminfo">
			<li><label>顾客ID</label> <input value="<%=cwords.getCustomer().getCustomerId()%>" style="width:320px;" readonly type="text" class="dfinput1" /></li>
			<li><label>顾客电话</label> <input value="<%=cwords.getCustomer().getCustomerPhone()%>" style="width:320px;" readonly type="text" class="dfinput1" /></li>
			<li><label>顾客邮箱</label> <input value="<%=cwords.getCustomer().getCustomerMail()%>" style="width:320px;" readonly type="text" class="dfinput1" /></li>
			<li><label>留言ID</label> <input value="<%=cwords.getWordsId()%>" style="width:320px" readonly type="text" class="dfinput1" /></li>
			<li><label>管理员</label> <input value="<%=cwords.getAdmin().getAdminAccount()%>" style="width:320px;" readonly type="text" class="dfinput1" /></li>
			<li><label>留言状态</label> <input value="<%=cwords.getWordsStatus()%>" style="width:320px;" readonly type="text" class="dfinput1" /></li>
			<li><label>添加时间</label> <input value="<%=cwords.getWordsDate()%>" style="width:320px;" readonly type="text" class="dfinput1" /></li>
			<li><label>留言详情</label> <textarea class="textinput" readonly style="margin-left:0px;height:80px;"><%=cwords.getWordsContent()%></textarea></li>
			<div class="ibox"></div>
			<li><label>&nbsp;</label> <input name="" type="button" class="btn" value="返回" onClick="history.back(-1)" /></li>
		</ul>

	</div>
	<div class="ibox"></div>
	<div class="ibox"></div>
	<div class="ibox"></div>
	<div class="ibox"></div>
</body>
</html>
