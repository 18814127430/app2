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
<title>推送详情修改</title>


<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/laydate.js"></script>

<script type="text/javascript">
!function(){
	laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
	laydate({elem: '#demo'});//绑定元素
}();
</script>


<script language="javascript">
	function checkForm() {
		if (document.getElementById("push.pushTitle").value == "") {
			window.alert('请输入标题!');
			return false;
		}
		if (document.getElementById("push.pushEditor").value == "") {
			window.alert('请输入编辑者!');
			return false;
		}
		if (document.getElementById("push.pushKeyWord").value == "") {
			window.alert('请输入关键字!');
			return false;
		}
		if (document.getElementById("push.pushUrl").value =="") {
			window.alert('请输入URl!');
			return false;
		}
		if (document.getElementById("push.pushStatus").value == "") {
			window.alert('请选择推送状态!');
			return false;
		}
		if (document.getElementById("push.pushContext").value == "") {
			window.alert('请输入推送内容!');
			return false;
		}
		return true;
	}
</script>
</head>

<body>
	<form action="<%=basePath%>push/push_doUpdate.action" method="post" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1">
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="<%=basePath%>mainindex.jsp">首页</a></li>
			<li><a href="<%=basePath%>push.jsp">信息推送</a></li>
			<li><a href="<%=basePath%>push/push_doFind.action">推送列表</a></li>
			<li><a onClick="history.back(-1)">查看推送</a></li>
			<li><a href="#">推送信息</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>推送内容</span>
		</div>
		<ul class="forminfo">
			<li> <input name="push.pushId" id="push.pushId"	type="hidden" value='<%=push.getPushId()%>'  /> <i></i></li>
			<li><label>添加标题</label> <input name="push.pushTitle" id="push.pushTitle"	 type="text" value='<%=push.getPushTitle()%>'   class="dfinput" /> <i></i></li>
			<li><label>编  辑  者</label> <input name="push.pushEditor"id="push.pushEditor" type="text" value='<%=push.getPushEditor()%>'   class="dfinput" /> <i></i></li>
			<li><label>关  键  字</label> <input name="push.pushKeyWord"id="push.pushKeyWord" type="text" value='<%=push.getPushKeyWord()%>'   class="dfinput" /> <i></i></li>
			<li><label>添加时间</label> <input name="push.pushDate"id="push.pushDate" type="text"  value='<%=push.getPushDate()%>'   class="laydate-icon" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" /> <i></i></li>
			<li><label>推送 URL</label> <input name="push.pushUrl"id="push.pushUrl" type="text" value='<%=push.getPushUrl()%>'   class="dfinput" /> <i></i></li>
			<li><label>推送状态</label> <cite> 
			<input name="push.PushStatus"id="push.PushStatus" type="radio"   value="wait" <%=(push.getPushStatus().equals("wait")?"checked":"")%>  /> 待推送&nbsp;&nbsp;&nbsp;&nbsp; 
			<input name="push.PushStatus"id="push.PushStatus" type="radio"   value="send" <%=(push.getPushStatus().equals("send")?"checked":"")%>  /> 已推送</cite></li>
			<li><label>推送内容</label> <textarea name="push.PushContext"id="push.PushContext"  class="textinput"><%=push.getPushContext()%></textarea></li>
			
			<li><label>&nbsp;</label> 
			<input type="submit" name="button" class="btn" value="确认" /> 
			<input type="button" name="button" class="btn" value="取消" onClick="javascript:location.href='<%=basePath %>push/push_doFind.action'" /></li>
			
			
		</ul>
	</div>
	</form>
</body>
</html>
