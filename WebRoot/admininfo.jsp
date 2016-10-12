<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.Admin"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	Admin admin = (Admin) request.getAttribute("admin");
	Object user = session.getAttribute("user");
	if (user == null) {
		response.getWriter().println("<script>window.top.location.href='" + basePath + "admin/admin_doLogin.action';</script>");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理员信息详情查看</title>


<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="<%=basePath%>mainindex.jsp">首页</a></li>
			<li><a href="<%=basePath%>admin.jsp">管理员</a></li>
			<li><a onClick="history.back(-1)">管理员列表</a></li>
			<li><a href="#">管理员信息</a></li>
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
</body>
</html>
