<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.Admin"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes" />

<html>
<head>
<meta charset="utf-8" />
<title>生鲜农产品管理系统</title>
<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" media="screen"  />
<link rel="stylesheet" type="text/css" href="css/login_style.css" />
<style>
body {
	height: 100%;
	background: #00688B;
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
			dotColor : '#000000',
			lineColor : '#000000'
		});
		//验证码
		createCode();
	});
</script>


<script type="text/javascript">
	function checkForm() {
		var var1 = document.getElementById("name").value;
		var var2 = document.getElementById("password1").value;
		var var3 = $.md5(var2);

		if (var1 == "" || null == var1) {
			window.alert('请输入账号!');
			return false;
		}
		if (var2 == "" || null == var2) {
			window.alert('请输入密码!');
			return false;
		} else {
			document.getElementById("password").value = var3;
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
<!-- <form action="<%=basePath%>admin/admin_checkLogin.action" onsubmit="return checkForm();" id="CheckLoginForm" enctype="multipart/form-data" name="form1" method="post"> -->
	<form action="<%=basePath%>welcome.action" onsubmit="return checkForm();" id="CheckLoginForm" enctype="multipart/form-data" name="form1" method="post">
		<canvas class="pg-canvas" width="808" height="672"></canvas>
		<dl class="admin_login">
			<dt>
				<strong>生鲜农产品管理系统</strong> <em>Agricultural Product Management System</em>
			</dt>


			<dd class="user_icon">
				<input name="name" id="name" type="text" placeholder="账号" class="login_txtbx" property=""</input>
			</dd>

			<dd class="pwd_icon">
				<input id="password1" type="password" placeholder="密码" class="login_txtbx" property=""</input> <input name="password" id="password" type="hidden"</input>
			</dd>

			<dd class="val_icon">
				<div class="checkcode">
					<input type="text" id="J_codetext" placeholder="验证码" maxlength="4" class="login_txtbx" style="width:150px;height: 42px;">
					<canvas class="J_codeimg" id="myCanvas" onclick="createCode()" style="width:150px;height: 42px;"></canvas>
				</div>
			</dd>

			<dd>
				<input type="submit" value="立即登陆" class="submit_btn">
			</dd>
			<dd type="hidden">
				<div class="center">
					<p>
						<a href="#" target="_blank">忘记密码</a>
					</p>
				</div>
			</dd>
		</dl>
		<div class="loginbm">
			生鲜农产品公司
			<a href="#">首页</a>
		</div>
</body>
</html>
