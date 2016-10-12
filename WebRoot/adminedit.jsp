<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.Admin"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	Admin admin = (Admin) request.getAttribute("admin");
	Admin user = (Admin)session.getAttribute("user");
	if (user == null) {
		response.getWriter().println("<script>top.location.href='" + basePath+ "';</script>");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改管理员</title>


<link rel="stylesheet" href="<%=basePath%>css/style.css"  type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/select.css"  type="text/css" />
<link rel="stylesheet" href="<%=basePath%>editor/themes/default/default.css" />
<script charset="utf-8" src="<%=basePath%>editor/kindeditor-all.js"></script>
<script charset="utf-8" src="<%=basePath%>editor/lang/zh-CN.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/select-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/laydate.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.md5.js"></script>


<script type="text/javascript">
			KindEditor.ready(function(K) {
				var editor = K.editor({
					cssPath : '<%=basePath%>editor/plugins/code/prettify.css',
					uploadJson : '<%=basePath%>editor/jsp/upload_json.jsp',
					fileManagerJson : '<%=basePath%>editor/jsp/file_manager_json.jsp',
					allowFileManager : true
				});
				K('#image1').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							showRemote : false,
							imageUrl : K('#url1').val(),
							clickFn : function(url, title, width, height, border, align) {
								
								var div = K('#localImage');
								div.html('');
								div.append('<img src="' + url + '" width=150 height=160 style="diplay:none" />');
								
								K('#url1').val(url);
								editor.hideDialog();
							}
						});
					});
				});
			});
</script>


<script type="text/javascript">
	$(document).ready(function(e) {
		$(".select1").uedSelect({
			width : 320
		});
		$(".select2").uedSelect({
			width : 100
		});
		$(".select3").uedSelect({
			width : 100
		});
	});
</script>


<script type="text/javascript">
!function(){
	laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
	laydate({elem: '#demo'});//绑定元素
}();
</script>

<script type="text/javascript">
	/*验证表单*/
	function checkForm() {
		if (document.getElementById("admin.adminAccount").value == "") {
			window.alert('请输入账号!');
			return false;
		}
		if (document.getElementById("admin.adminPassword").value == "") {
			window.alert('请输入密码!');
			return false;
		}
		if (document.getElementById("admin.adminPassword").value != document
				.getElementById("repeatPassword").value) {
			window.alert('两次密码不相同!');
			return false;
		}
		else{
			document.getElementById("admin.adminPassword").value=$.md5(document.getElementById("repeatPassword").value);
		}
		
		if (document.getElementById("admin.adminName").value == "") {
			window.alert('请输入姓名!');
			return false;
		}
		if (document.getElementById("admin.adminPhone").value == "") {
			window.alert('请输入手机号码!');
			return false;
		}
		if (document.getElementById("admin.adminMail").value == "") {
			window.alert('请输入邮箱!');
			return false;
		}
		if (document.getElementById("admin.adminRegion").value == "") {
			window.alert('请输入地区!');
			return false;
		}
		if (document.getElementById("admin.adminClass").value == "") {
			window.alert('请输入权限级别!');
			return false;
		}
		return true;
	}
</script>

</head>

<body>
	<form action="<%=basePath%>admin/admin_doUpdate.action" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1" method="post">
	
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li1><a href="<%=basePath%>mainindex.jsp">首页</a></li1>
				<li1><a href="<%=basePath%>admin.jsp">管理员查找</a></li1>
				<li1><a href="<%=basePath%>admin/admin_doFind.action">管理员列表</a></li1>
				<li1><a href="<%=basePath%>adminadd.jsp">管理员添加</a></li1>
				<li1><a href="<%=basePath%>admin/admin_doView.action?admin.adminId=<%=admin.getAdminId()%>">管理员信息</a></li1>
				<li1><a  style="color:blue;" href="<%=basePath%>admin/admin_doEdit.action?admin.adminId=<%=admin.getAdminId()%>">管理员修改</a></li1>
				<li1><a onClick="history.back(-1)">返回</a></li1>
			</ul>
		</div>
		
		<div class="ibox"></div>
		<div class="ibox"></div>
		
		<div class="MID">
			<div class="MID1">
				<ul class="forminfo">
					<li><input type="hidden" name="admin.adminId" id="admin.adminId" value='<%=admin.getAdminId()%>' /></li>
					<li><input type="hidden" name="admin.adminStartDate" id="admin.adminStartDate" value='<%=admin.getAdminStartDate()%>' /></li>
					<li><input type="hidden" name="admin.adminDates" id="admin.adminDates" value='<%=admin.getAdminDates()%>' /></li>
					<li><label>账号<b>*</b></label> <input type="text" class="dfinput" name="admin.adminAccount" id="admin.adminAccount" value='<%=admin.getAdminAccount()%>' style="width:320px;" /></li>
					<li><label>密码<b>*</b></label> <input type="password" class="dfinput" name="admin.adminPassword" id="admin.adminPassword" value="" placeholder="请填写密码" style="width:320px;" /></li>
					<li><label>确认密码<b>*</b></label> <input type="password" class="dfinput" name="repeatPassword" id="repeatPassword" value="" placeholder="请确认密码" style="width:320px;" /></li>
					<li><label>姓名<b>*</b></label> <input type="text" class="dfinput" name="admin.adminName" id="admin.adminName" value='<%=admin.getAdminName()%>' style="width:320px;" /></li>
					<li><label>电话<b>*</b></label> <input type="text" class="dfinput" name="admin.adminPhone" id="admin.adminPhone" value='<%=admin.getAdminPhone()%>' style="width:320px;" /></li>
					<li><label>邮箱<b>*</b></label> <input type="text" class="dfinput" name="admin.adminMail" id="admin.adminMail" value='<%=admin.getAdminMail()%>' style="width:320px;" /></li>
					<li><label>地区<b>*</b></label> <input type="text" class="dfinput" name="admin.adminRegion" id="admin.adminRegion" value='<%=admin.getAdminRegion()%>' style="width:320px;" /></li>
					
					<%if (user.getAdminClass().equalsIgnoreCase("super")) { %>
					<li><label>管理等级<b>*</b></label>
						<div class="vocation">
							<select class="select1" id="adminClass" name="admin.adminClass">
								<option>general</option>
								<option>super</option>
							</select>
						</div></li>
					<%} %>
					
					<li><label>&nbsp;</label> <input type="submit" name="button" class="btn" value="确认" /> 
					<input name="" type="button" class="btn" value="返回" onClick="history.back(-1)" /></li>
					
				</ul>
			</div>
			
			
			<div class="MID2">
				<ul class="forminfo1">
					<li>
						<div id="localImage">
							<img src="<%=admin.getAdminImg()%>" id="preview" width=150 height=160 style="diplay:none" />
						</div>
					</li>
					<div class="ibox"></div>
					
					<li><input type="hidden" id="url1" name="admin.adminImg" value='<%=admin.getAdminImg()%>' class="dfinput" /></li>
					<li><p><input type="button" id="image1" value="选择图片" class="btn1"  />（网络图片 + 本地上传）</p></li>
				</ul>
			</div>
		</div>
	</form>
</body>
</html>
