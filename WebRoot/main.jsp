<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" media="screen"  />
<title>生鲜农产品管理系统</title>
</head>

<frameset rows="60,*" cols="*" frameborder="no" border="0" framespacing="0">

	<frame src="<%=basePath%>top.jsp" name="topFrame" id="topFrame" title="topFrame" scrolling="No" noresize="noresize" />

	<frameset cols="140,*" frameborder="no" border="0" framespacing="0">
		<frame src="left.jsp" name="leftFrame" id="leftFrame" title="leftFrame" scrolling="No" noresize="noresize" />
		<frame src="<%=basePath%>mainindex.jsp" name="rightFrame" id="rightFrame" title="rightFrame" />
	</frameset>
	
</frameset>
<noframes>
	<body>
	</body>
</noframes>
</html>
