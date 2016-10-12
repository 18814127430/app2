<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bean.Goods"%>
<%@ page import="bean.Sort"%>
<%@ page import="bean.Company"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Object user = session.getAttribute("user");
	if (user == null) {
		response.getWriter().println(
				"<script>top.location.href='" + basePath
						+ "';</script>");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加公司</title>



<link rel="stylesheet" href="<%=basePath%>css/style.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/select.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>editor/themes/default/default.css" />
<link rel="stylesheet" href="<%=basePath%>editor/plugins/code/prettify.css" />



<script charset="utf-8" src="<%=basePath%>editor/kindeditor-all.js"></script>
<script charset="utf-8" src="<%=basePath%>editor/lang/zh-CN.js"></script>
<script charset="utf-8" src="<%=basePath%>editor/plugins/code/prettify.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/select-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/laydate.js"></script>



<script type="text/javascript">
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="company.companyIntroduction"]', {
				cssPath : '<%=basePath%>editor/plugins/code/prettify.css',
				uploadJson : '<%=basePath%>editor/jsp/upload_json.jsp',
				fileManagerJson : '<%=basePath%>editor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['form1'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['form1'].submit();
					});
				}
			});
			prettyPrint();
		});
</script>



<script type="text/javascript">
	function checkForm() {
		if (document.getElementById("company.companyIntroduction").value == "") {
			window.alert('请输入公司文字信息!');
			return false;
		}
		if (document.getElementById("company.companyName").value == "") {
			window.alert('请输入公司名!');
			return false;
		}
		if (document.getElementById("company.companyType").value == "") {
			window.alert('请输入公司类型!');
			return false;
		}
		if (document.getElementById("company.companyStress").value == "") {
			window.alert('请输入公司街道!');
			return false;
		}
		if (document.getElementById("company.companyPostCode").value == "") {
			window.alert('请输入公司邮编!');
			return false;
		}
		if (document.getElementById("company.companyFax").value == "") {
			window.alert('请输入公司传真!');
			return false;
		}
		if (document.getElementById("company.companyUrl").value == "") {
			window.alert('请输入公司链接!');
			return false;
		}
		if (document.getElementById("company.companyPhone").value == "") {
			window.alert('请输入公司电话!');
			return false;
		}
		if (document.getElementById("company.companyCity").value == "") {
			window.alert('请输入公司城市!');
			return false;
		}
		if (document.getElementById("company.companyProvince").value == "") {
			window.alert('请输入公司省份!');
			return false;
		}
		if (document.getElementById("company.companyDetial").value == "") {
			window.alert('请输入公司地址详情!');
			return false;
		}
		return true;
	}
</script>





</head>

<body>
	<form action="<%=basePath%>company/company_doAdd.action" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1" method="post">
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li1><a href="<%=basePath%>mainindex.jsp">首页</a></li1>
				<li1><a href="<%=basePath%>company.jsp">公司查找</a></li1>
				<li1><a href="<%=basePath%>company/company_doFind.action">公司列表</a></li1>
				<li1><a  style="color:blue;" href="<%=basePath%>companyadd.jsp">公司添加</a></li1>
				<li1><a onClick="history.back(-1)">返回</a></li1>
			</ul>
		</div>
		<div class="formbody" id="formbody">

			<div id="usual1" name="usual1" class="usual">

				<div class="ibox" style="float:right;">
					<a style="float:right;margin-left:5px;margin-right:5px;" class="ibtn" href="#formtitle1">
						<img src="<%=basePath%>images/idown.png" " />公司基本信息
					</a>
					<a style="float:right;margin-left:5px;margin-right:5px;" class="ibtn" href="#formtitle4">
						<img src="<%=basePath%>images/idown.png" " />公司网页详情
					</a>
				</div>
				<br>


				<div id="tab1" name="tab1" style="background: none repeat scroll 0% 0% rgb(255, 255, 255);">

					<div class="tabson">
						<div id="formtitle1" class="formtitle">
							<span>公司基本信息</span>
						</div>

						<div class="tabson1">
							<ul class="forminfo">
								<li><label>公司名称<b>*</b></label><input name="company.companyName" id="company.companyName" type="text" value="" class="dfinput" placeholder="请填写名称" style="width:320px;" /></li>
								<li><label>公司类型<b>*</b></label><input name="company.companyType" id="company.companyType" type="text" value="" class="dfinput" placeholder="请填写" style="width:320px;" /></li>
								<li><label>公司电话<b>*</b></label><input name="company.companyPhone" id="company.companyPhone" type="text" value="" class="dfinput" placeholder="请填写" style="width:320px;" /></li>
								<li><label>公司邮编<b>*</b></label><input name="company.companyPostCode" id="company.companyPostCode" type="text" value="" class="dfinput" placeholder="请填写" style="width:320px;" /></li>
								<li><label>公司传真<b>*</b></label><input name="company.companyFax" id="company.companyFax" type="text" value="" class="dfinput" placeholder="请填写" style="width:320px;" /></li>
								<li><label>公司链接<b>*</b></label><input name="company.companyUrl" id="company.companyUrl" type="text" value="" class="dfinput" placeholder="请填写" style="width:320px;" /></li>

							</ul>
						</div>
						<div class="tabson1">
							<ul class="forminfo">
								<li><label>公司省份<b>*</b></label><input name="company.companyProvince" id="company.companyProvince" type="text" value="" class="dfinput" placeholder="请填写" style="width:320px;" /></li>
								<li><label>公司城市<b>*</b></label><input name="company.companyCity" id="company.companyCity" type="text" value="" class="dfinput" placeholder="请填写" style="width:320px;" /></li>
								<li><label>公司街道<b>*</b></label><input name="company.companyStress" id="company.companyStress" type="text" value="" class="dfinput" placeholder="请填写" style="width:320px;" /></li>
								<li><label>详细地址<b>*</b></label><input name="company.companyDetial" id="company.companyDetial" type="text" value="" class="dfinput" placeholder="请填写" style="width:320px;" /></li>
							</ul>
						</div>
					</div>

					<div class="ibox"></div>

					<div class="tabson">
						<div id="formtitle4" class="formtitle">
							<span>公司文字信息</span>
						</div>

						<div style="float:left;">
							<label style="width: 85px;line-height: 34px;display: block;float: left;">公司介绍<b color="#d70101;">*</b></label>
						</div>
						<div style="float:left;width: 700px;border-radius:8px;margin:-5px 10px 20px 10px;">
							<textarea style="width:700px;height:100px;visibility:hidden;" id="company.companyIntroduction" name="company.companyIntroduction"> </textarea>
						</div>
						<div style="float:left;">
							<label style="width: 280px;line-height: 34px;display: block;float: left;color:#C0C0C0;">富文本输入，字数不限</label>
						</div>
						<div class="ibox1"></div>

					</div>

				</div>

				<div class="ibox"></div>
				<div>
					<li style="position:absolute;right:50%;translateX(-50%);"><label>&nbsp;</label> <input name="button" type="submit" class="btn" value="数据录入" /></li>
				</div>
				<div id="buttom" class="ibox" style="float:right;">
					<a class="ibtn" href="#formbody">
						<img src="<%=basePath%>images/iup.png" style="margin-top: 2px; margin-left: -10px;" />返回顶部
					</a>
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		$("#usual1 ul").idTabs();
	</script>
	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
