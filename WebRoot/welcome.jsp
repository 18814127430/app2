<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.Admin"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
			+ "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes" />
<!-- <meta http-equiv='refresh' content='0;url=welcome.action'> -->
<html>
<head>
<meta charset="utf-8" />
<title>生鲜农产品管理系统</title>
<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" media="screen" />
</head>

<body class="mainBody" onload="init();">
</body>
<body>
	<jsp:forward page="welcome.action"></jsp:forward>
</body>
</html>
