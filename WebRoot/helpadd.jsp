<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.Help"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	Object user = session.getAttribute("user");
	if (user == null) {
		response.getWriter().println("<script>window.top.location.href='" + basePath + "admin/admin_doLogin.action';</script>");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加帮助文档</title>

<link href=" <%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href=" <%=basePath%>css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src=" <%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src=" <%=basePath%>js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src=" <%=basePath%>js/select-ui.min.js"></script>
<script type="text/javascript" src=" <%=basePath%>editor/kindeditor.js"></script>
<script type="text/javascript" src="<%=basePath%>js/laydate.js"></script>


<script type="text/javascript">
!function(){
	laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
	laydate({elem: '#demo'});//绑定元素
}();

//日期范围限制
var start = {
    elem: '#start',
    format: 'YYYY-MM-DD',
    min: laydate.now(), //设定最小日期为当前日期
    max: '2099-06-16', //最大日期
    istime: true,
    istoday: false,
    choose: function(datas){
         end.min = datas; //开始日选好后，重置结束日的最小日期
         end.start = datas //将结束日的初始值设定为开始日
    }
};

var end = {
    elem: '#end',
    format: 'YYYY-MM-DD',
    min: laydate.now(),
    max: '2099-06-16',
    istime: true,
    istoday: false,
    choose: function(datas){
        start.max = datas; //结束日选好后，充值开始日的最大日期
    }
};
laydate(start);
laydate(end);

//自定义日期格式
laydate({
    elem: '#test1',
    format: 'YYYY年MM月DD日',
    festival: true, //显示节日
    choose: function(datas){ //选择日期完毕的回调
        alert('得到：'+datas);
    }
});

//日期范围限定在昨天到明天
laydate({
    elem: '#hello3',
    min: laydate.now(-1), //-1代表昨天，-2代表前天，以此类推
    max: laydate.now(+1) //+1代表明天，+2代表后天，以此类推
});
</script>



<script language="javascript">
	/*验证表单*/
	function checkForm() {
		if (document.getElementById("help.helpTitle").value == "") {
			window.alert("请输入文档标题!");
			return false;
		}
		if (document.getElementById("help.helpKeyWord").value == "") {
			window.alert("请输入文档关键字!");
			return false;
		}
		if (document.getElementById("help.helpDate").value == "") {
			window.alert("请输入文档时间!");
			return false;
		}
		if (document.getElementById("help.helpContext").value == "") {
			window.alert("请输入文档内容!");
			return false;
		}
		return true;
	}
</script>



</head>

<body>
	<form action="<%=basePath%>help/help_doAdd.action" method="post" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1">
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href=" <%=basePath%>mainindex.jsp">首页</a></li>
				<li><a href=" <%=basePath%>help/help_doFind.action">帮助文档列表</a></li>
				<li><a href="">帮助文档添加</a></li>
			</ul>
		</div>

		<div class="formbody">
			<div class="formtitle">
				<span>帮助文档内容</span>
			</div>
			<ul class="forminfo">
				<li><label>文 档 标 题</label> <input name="help.helpTitle" id="help.helpTitle" value="" style="width:320px;" type="text" class="dfinput" /> <i>标题不能超过150个字符</i></li>
				<li><label>文档关键字</label> <input name="help.helpKeyWord" id="help.helpKeyWord" value="" style="width:320px;" type="text" class="dfinput" /> <i>多个关键字用,隔开</i></li>
				<li><label>添 加 时 间</label> <input name="help.helpDate" id="help.helpDate"  value="" style="width:320px;" type="text" class="laydate-icon" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" /></li>	
				<li><label>文 档 内 容</label> <textarea name="help.helpContext" id="help.helpContext"  class="textinput" style="margin-left:0px;background: #eef6f5;"></textarea></li>
				
				<div class="ibox"></div>
				
				<li><label>&nbsp;</label> 
				<input type="submit" name="button" class="btn" value="确认" /> 
				<a href="<%=basePath%>help/help_doFind.action" /><input type="button" name="button" class="btn" value="取消" /> </a></li>
			</ul>
		</div>
	</form>
</body>
</html>
