<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String errorMsg = java.net.URLDecoder.decode((String) request
			.getAttribute("Msg"));
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes" />

<head>
<title>systemerror.jsp</title>
<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" media="screen"  />
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>

<script language="javascript">
	$(function() {
		$('.error').css({
			'position' : 'absolute',
			'left' : ($(window).width() - 490) / 2
		});
		$(window).resize(function() {
			$('.error').css({
				'position' : 'absolute',
				'left' : ($(window).width() - 490) / 2
			});
		})
	});
</script>


</head>


<body style="background:#edf6fa;">
	<div class="ibox"></div>
	<div class="ibox"></div>
	<div class="ibox"></div>

	<div class="error">
		<h2>非常遗憾，您访问的页面不存在！</h2>
		<p>
			错误提示信息：<%=errorMsg%></p>
		<div class="reindex">
			<a href="#" onClick="history.back(-1)">返回</a>
		</div>
	</div>
</body>
</html>
