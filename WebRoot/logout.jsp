<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 
	session.removeAttribute("user");	//移除保存在session中的username属性
	session.invalidate();
	response.getWriter().print("<script>top.location.href='" + basePath+ "';</script>");

// 	response.getWriter().print("<script>top.location.href='" + basePath+ "admin/admin_doLogin.action';</script>");
%>