<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	Object user = session.getAttribute("user");
	if (user == null) {
		response.getWriter().println("<script>top.location.href='" + basePath+ "admin/admin_doLogin.action';</script>");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>生鲜农产品管理系统</title>
</head>

<frameset rows="88,*" cols="*" frameborder="no" border="0" framespacing="0">

	<frame src="<%=basePath%>top.jsp" name="topFrame" id="topFrame" title="topFrame" scrolling="No" noresize="noresize" />

	<frameset cols="187,*" frameborder="no" border="0" framespacing="0">
		<frame src="<%=basePath%>left.jsp" name="leftFrame" id="leftFrame" title="leftFrame" scrolling="No" noresize="noresize" />
		<frame src="<%=basePath%>mainindex.jsp" name="rightFrame" id="rightFrame" title="rightFrame" />
	</frameset>
	
</frameset>
<noframes>
	<body>
	</body>
</noframes>
</html>
