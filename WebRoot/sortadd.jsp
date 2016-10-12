<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="bean.Sort"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	List<Sort> list = 	(List<Sort>)request.getAttribute("list");
	Object user = session.getAttribute("user");
	if (user == null) {
		response.getWriter().println("<script>top.location.href='" + basePath+ "admin/admin_doLogin.action';</script>");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加类别</title>


<link rel="stylesheet" href="<%=basePath%>css/style.css"  type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/select.css"  type="text/css" />
<link rel="stylesheet" href="<%=basePath%>editor/themes/default/default.css" />
<script charset="utf-8" src="<%=basePath%>editor/kindeditor-all.js"></script>
<script charset="utf-8" src="<%=basePath%>editor/lang/zh-CN.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/select-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/laydate.js"></script>


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


<script language="javascript">
	/*验证表单*/
	function checkForm() {
		if (document.getElementById("sort.sortName").value == "") {
			window.alert('请输入类别名!');
			return false;
		}
		if (document.getElementById("parentid").value == "") {
			window.alert('请输入父类名!');
			return false;
		}
		return true;
	}
</script>



</head>

<body>
	<form action="<%=basePath%>sort/sort_doAdd.action" method="post" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1">
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="<%=basePath%>mainindex.jsp">首页</a></li>
				<li><a onClick="history.back(-1)">类别列表</a></li>
				<li><a href="#">类别添加</a></li>
			</ul>
		</div>
		<div class="ibox"></div>
		<div class="MID">
			<div class="MID1">
				<div class="ibox"></div>
				<div class="ibox"></div>
				<ul class="forminfo">
					<li><label>类别名<b>*</b></label> <input type="text" class="dfinput" name="sort.sortName" id="sort.sortName" value="" style="width:320px;" /></li>
					<li><label>父类名<b>*</b></label>
						<div class="vocation">
							<select class="select1" id="parentid" name="parentid">

								<%
									for (int i = 0; i < list.size(); i++) {
										Sort sort1 = (Sort) list.get(i);
								%>
								<option value="<%=sort1.getSortId()%>"><%=sort1.getSortName()%></option>
								<%
									}
								%>
							</select>
						</div></li>
					<li><label>&nbsp;</label> <input type="submit" name="button" class="btn" value="确认" /> <a href="<%=basePath%>sort/sort_doFind.action" /><input type="button" name="button" class="btn" value="取消" /> </a></li>
				</ul>
			</div>
			
			
			<div class="MID2">
				<ul class="forminfo1">
					<li><label1>提示：请选择分辨率为86*66的图片</label1></li>
					<li>
						<div id="localImage">
							<img src="/app2/uploads/nopic.png" id="preview" width=150 height=160 style="diplay:none" />
						</div>
					</li>
					<div class="ibox"></div>

					<li><input type="hidden" id="url1" name="sort.sortImgPath" value=""></li>
					<li><p>
							<input type="button" id="image1" value="选择图片" class="btn1" />（网络图片 + 本地上传）
						</p></li>

				</ul>
			</div>
		</div>
	</form>
</body>
</html>
