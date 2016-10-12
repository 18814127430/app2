<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.Push"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	Push push = (Push) request.getAttribute("push");
	Object user = session.getAttribute("user");
	if (user == null) {
		response.getWriter().println("<script>window.top.location.href='" + basePath + "admin/admin_doLogin.action';</script>");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>推送详情查看</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="<%=basePath%>mainindex.jsp">首页</a></li>
			<li><a href="<%=basePath%>push.jsp">信息推送</a></li>
			<li><a onClick="history.back(-1)">信息推送列表</a></li>
			<li><a href="#">查看推送信息</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>推送内容</span>
		</div>
		<ul class="forminfo">
			<li><label> 推送 ID</label> <input type="text" value='<%=push.getPushId()%>' readonly="true" class="dfinput" /> <i></i></li>
			<li><label>添加标题</label> <input type="text" value='<%=push.getPushTitle()%>' readonly="true" class="dfinput" /> <i></i></li>
			<li><label>编  辑  者</label> <input type="text" value='<%=push.getPushEditor()%>' readonly="true" class="dfinput" /> <i></i></li>
			<li><label>关  键  字</label> <input type="text" value='<%=push.getPushKeyWord()%>' readonly="true" class="dfinput" /> <i></i></li>
			<li><label>添加时间</label> <input type="text" value='<%=push.getPushDate()%>' readonly="true" class="dfinput" /> <i></i></li>
			<li><label>推送 URL</label> <input type="text" value='<%=push.getPushUrl()%>' readonly="true" class="dfinput" /> <i></i></li>
			
			<li><label>推送状态</label> <cite> 
			<input name="push.PushStatus"id="push.PushStatus" type="radio" disabled value="wait" <%=(push.getPushStatus().equals("wait")?"checked":"")%> /> 待推送&nbsp;&nbsp;&nbsp;&nbsp; 
			<input name="push.PushStatus"id="push.PushStatus" type="radio" disabled value="send" <%=(push.getPushStatus().equals("send")?"checked":"")%> /> 已推送</cite></li>
			
			<li><label>推送内容</label> <textarea readonly="true" class="textinput"><%=push.getPushContext()%></textarea></li>
			
			<li><label>&nbsp;</label>
			<input name="" type="button" class="btn" value="修改" onClick="javascript:location.href='<%=basePath%>push/push_doEdit.action?push.pushId=<%=push.getPushId()%>'"/>
			<input name="" type="button" class="btn" value="返回" onClick="history.back(-1)" /></li>
		</ul>
	</div>
</body>
</html>
