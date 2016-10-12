<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.Admin"%>
<%@ page import="utils.MD5Util"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<meta charset="utf-8" />
<title>信息管理系统后台登录</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/login_style.css" />
<style>
body {
	height: 100%;
	background: #16a085;
	overflow: hidden;
}

canvas {
	z-index: -1;
	position: absolute;
}
</style>

<script src="<%=basePath%>js/login_jquery.js"></script>
<script src="<%=basePath%>js/login_verificationNumbers.js"></script>
<script src="<%=basePath%>js/login_Particleground.js"></script>
<script src="<%=basePath%>js/jquery.md5.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//粒子背景特效
		$('body').particleground({
			dotColor : '#5cbdaa',
			lineColor : '#5cbdaa'
		});
		//验证码
		createCode();
	});

	function checkForm() {
		var var1 = document.getElementById("name").value;
		var var2 = document.getElementById("password").value;
		if (var1 == "" || null == var1) {
			window.alert('请输入账号!');
			return false;
		}
		if (var2 == "" || null == var2) {
			window.alert('请输入密码!');
			return false;
		}
		if (!validate()) {
			createCode();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form action="<%=basePath%>admin/admin_checkLogin.action" onsubmit="return checkForm();" id="CheckLoginForm" enctype="multipart/form-data" name="form1">
		<canvas class="pg-canvas" width="808" height="672"></canvas>
		<dl class="admin_login">
			<dt>
				<strong>后台管理系统</strong> <em>Management System</em>
			</dt>


			<dd class="user_icon">
				<input name="name" id="name" type="text" placeholder="账号" class="login_txtbx" property=""</input>
			</dd>

			<dd class="pwd_icon">
				<input name="password" id="password" type="password" placeholder="密码" class="login_txtbx" property=""</input>
			</dd>
			
			<dd class="val_icon">
				<div class="checkcode">
					<input type="text" id="J_codetext" placeholder="验证码" maxlength="4" class="login_txtbx" style="width:150px;height: 42px;">
					<canvas class="J_codeimg" id="myCanvas" onclick="createCode()" style="width:150px;height: 42px;"></canvas>
				</div>
			</dd>

			<dd>
				<input type="submit" name="button" value="立即登陆" class="submit_btn">
			</dd>
			<dd type="hidden">
				<div class="center">
					<p>
						<a href="http://www.baidu.com/" target="_blank">忘记密码</a>
					</p>
				</div>
			</dd>
		</dl>
		<div class="loginbm">
			alfred公司
			<a href="http://www.baidu.com">首页</a>
		</div>
</body>
</html>
