<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.Admin"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	Admin admin = (Admin) request.getAttribute("admin");
	String[] list1 = admin.getAdminDates().split(",");
	int size = list1.length;
	Object user = session.getAttribute("user");
	if (user == null) {
		response.getWriter().print("<script>top.location.href='" + basePath+ "';</script>");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理员信息详情查看</title>


<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$(".click").click(function() {
			$(".tip").fadeIn(200);
		});

		$(".tiptop a").click(function() {
			$(".tip").fadeOut(200);
		});

		$(".sure").click(function() {
			$(".tip").fadeOut(100);
		});

		$(".cancel").click(function() {
			$(".tip").fadeOut(100);
		});
	});
</script>


</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
				<li1><a href="<%=basePath%>mainindex.jsp">首页</a></li1>
				<li1><a href="<%=basePath%>admin.jsp">管理员查找</a></li1>
				<li1><a href="<%=basePath%>admin/admin_doFind.action">管理员列表</a></li1>
				<li1><a href="<%=basePath%>adminadd.jsp">管理员添加</a></li1>
				<li1><a  style="color:blue;" href="<%=basePath%>admin/admin_doView.action?admin.adminId=<%=admin.getAdminId()%>">管理员信息</a></li1>
				<li1><a onClick="history.back(-1)">返回</a></li1>
		</ul>
	</div>
	
	<div class="ibox"></div>
	<div class="ibox"></div>
	
	<div class="MID">
		<div class="MID1">
			<ul class="forminfo">
				<li><label>账号<b>*</b></label> <input type="text" class="dfinput1" value='<%=admin.getAdminAccount()%>' readonly=true style="width:320px;" /></li>
				<li><label>姓名<b>*</b></label> <input type="text" class="dfinput1" value='<%=admin.getAdminName()%>' readonly=true style="width:320px;" /></li>
				<li><label>电话<b>*</b></label> <input type="text" class="dfinput1" value='<%=admin.getAdminPhone()%>' readonly=true style="width:320px;" /></li>
				<li><label>邮箱<b>*</b></label> <input type="text" class="dfinput1" value='<%=admin.getAdminMail()%>' readonly=true style="width:320px;" /></li>
				<li><label>地区<b>*</b></label> <input type="text" class="dfinput1" value='<%=admin.getAdminRegion()%>' readonly=true style="width:320px;" /></li>
				<li class="click"><label>登录次数<b>*</b></label> <input type="text" class="dfinput1" value='<%=size%>' readonly="true" style="width:150px;" /><i>点击查看详情</i></li>
				<li><label>添加时间<b>*</b></label> <input type="text" class="dfinput1" value='<%=admin.getAdminStartDate()%>' readonly=true style="width:320px;" /></li>
				<li><label>等级<b>*</b></label> <input type="text" class="dfinput1" value='<%=admin.getAdminClass()%>' readonly=true style="width:320px;" /></li>
				<li><label>&nbsp;</label> <a href="<%=basePath%>admin/admin_doEdit.action?admin.adminId=<%=admin.getAdminId()%>">
						<input name="" type="button" class="btn" value="修改" />
					</a> <input name="" type="button" class="btn" value="返回" onClick="history.back(-1)" /></li>
			</ul>
		</div>

		<div class="MID2">
			<ul class="forminfo1">
				<li><span> <img src="<%=admin.getAdminImg()%>" /></span></li>
			</ul>
		</div>
	</div>
	
	<div class="ibox"></div>
	
	<div class="tip">
		<div class="tiptop"><span>登录详情</span><a></a></div>
		<div class="tiplog">
		<textarea class="textarea" readonly="readonly" style="font-size: 15px;color: #046DA9;font-family:微软雅黑;">
		<%
			for (int j = 0; j < size; j++) {
		%>
		<%=list1[j]%>&#13;&#10;
		<%
			}
		%>
		</textarea>
		</div>
	</div>
	
</body>
</html>
