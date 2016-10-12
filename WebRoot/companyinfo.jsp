<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bean.Company"%>
<%@ page import="bean.Sort"%>
<%@ page import="bean.Company"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
Object user = session.getAttribute("user");
Company company=(Company)request.getAttribute("company");
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
<title>添加公司</title>



<link rel="stylesheet" href="../css/style.css" type="text/css" />
<link rel="stylesheet" href="../css/select.css" type="text/css" />
<link rel="stylesheet" href="../editor/themes/default/default.css" />
<link rel="stylesheet" href="../editor/plugins/code/prettify.css" />



<script charset="utf-8" src="../editor/kindeditor-all.js"></script>
<script charset="utf-8" src="../editor/lang/zh-CN.js"></script>
<script charset="utf-8" src="../editor/plugins/code/prettify.js"></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="../js/select-ui.min.js"></script>
<script type="text/javascript" src="../js/laydate.js"></script>



<script type="text/javascript">
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="create5"]', {
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






</head>

<body>
<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li1><a href="<%=basePath%>mainindex.jsp">首页</a></li1>
				<li1><a href="<%=basePath%>company.jsp">公司查找</a></li1>
				<li1><a href="<%=basePath%>company/company_doFind.action">公司列表</a></li1>
				<li1><a href="<%=basePath%>companyadd.jsp">公司添加</a></li1>
				<li1><a  style="color:blue;" href="<%=basePath%>company/company_doView.action?company.companyId=<%=company.getCompanyId()%>">公司详情</a></li1>
				<li1><a onClick="history.back(-1)">返回</a></li1>
			</ul>
		</div>
		
	<div class="formbody" id="formbody">

		<div id="usual1" name="usual1" class="usual">

			<div id="tab1" name="tab1" style="background: none repeat scroll 0% 0% rgb(255, 255, 255);">

				<div class="tabson">
					<div id="formtitle1" class="formtitle">
						<span>公司基本信息</span>
					</div>

					<div class="tabson1">
						<ul class="forminfo">
							<li><label>公司名称<b>*</b></label><input type="text" value="<%=company.getCompanyName()%>" class="dfinput1" style="width:320px;" /></li>
							<li><label>公司类型<b>*</b></label><input type="text" value="<%=company.getCompanyType()%>" class="dfinput1" style="width:320px;" /></li>
							<li><label>公司电话<b>*</b></label><input type="text" value="<%=company.getCompanyPhone()%>" class="dfinput1" style="width:320px;" /></li>
							<li><label>公司邮编<b>*</b></label><input type="text" value="<%=company.getCompanyPostCode()%>" class="dfinput1" style="width:320px;" /></li>
							<li><label>公司传真<b>*</b></label><input type="text" value="<%=company.getCompanyFax()%>" class="dfinput1" style="width:320px;" /></li>
							<li><label>公司链接<b>*</b></label><input type="text" value="<%=company.getCompanyUrl()%>" class="dfinput1" style="width:320px;" /></li>

						</ul>
					</div>
					<div class="tabson1">
						<ul class="forminfo">
							<li><label>公司省份<b>*</b></label><input type="text" value="<%=company.getCompanyProvince()%>" class="dfinput1" style="width:320px;" /></li>
							<li><label>公司城市<b>*</b></label><input type="text" value="<%=company.getCompanyCity()%>" class="dfinput1" style="width:320px;" /></li>
							<li><label>公司街道<b>*</b></label><input type="text" value="<%=company.getCompanyStress()%>" class="dfinput1" style="width:320px;" /></li>
							<li><label>详细地址<b>*</b></label><input type="text" value="<%=company.getCompanyDetial()%>" class="dfinput1" style="width:320px;" /></li>
						</ul>
					</div>
				</div>


				<div class="ibox1"></div>
				<div class="tabson">
					<div id="formtitle4" class="formtitle">
						<span>公司文字信息</span>
					</div>


					<div style="float:left;">
						<label style="width: 85px;line-height: 34px;display: block;float: left;">公司文字信息<b color="#d70101;">*</b></label>
					</div>
					<div style="float:left;width: 700px;border-radius:8px;margin:-5px 10px 20px 10px;">
						<textarea readonly="true" style="width:700px;height:100px;visibility:hidden;" name="create5"><%=company.getCompanyIntroduction()%> </textarea>
					</div>
					<div style="float:left;">
						<label style="width: 280px;line-height: 34px;display: block;float: left;color:#C0C0C0;">富文本输入，字数不限</label>
					</div>
					<div class="ibox1"></div>

				</div>

			</div>

			<div class="ibox"></div>
			<div>
				<li style="position:absolute;right:50%;translateX(-50%);"><label>&nbsp;</label> <input name="button" type="button" class="btn" value="数据修改"
					onClick="javascript:location.href='<%=basePath%>company/company_doEdit.action?company.companyId=<%=company.getCompanyId()%>'" /> <label>&nbsp;</label> <input name="button" type="button" class="btn" value="返回上级" onClick="history.back(-1)" /></li>
			</div>
			<div id="buttom" class="ibox" style="float:right;">
				<a class="ibtn" href="#formbody">
					<img src="<%=basePath%>images/iup.png" style="margin-top: 2px; margin-left: -10px;" />返回顶部
				</a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$("#usual1 ul").idTabs();
	</script>
	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
