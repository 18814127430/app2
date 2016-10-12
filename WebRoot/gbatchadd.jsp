<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bean.Goods"%>
<%@ page import="bean.GBatch"%>
<%@ page import="bean.Company"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	Object user = session.getAttribute("user");
	int goodsid = 	(Integer)request.getAttribute("goodsid");
	List<Company> companylist = (List<Company>)request.getAttribute("companylist");
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
<title>添加商品批次</title>
<link rel="stylesheet" href="<%=basePath%>css/style.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/select.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>editor/themes/default/default.css" />
<link rel="stylesheet" href="<%=basePath%>editor/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=basePath%>editor/kindeditor-all.js"></script>
<script charset="utf-8" src="<%=basePath%>editor/lang/zh-CN.js"></script>
<script charset="utf-8" src="<%=basePath%>editor/plugins/code/prettify.js"></script>
<script type="text/javascript" src=" <%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src=" <%=basePath%>js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src=" <%=basePath%>js/select-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/laydate.js"></script>

<script type="text/javascript">
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="gbatch.producerSendContent"]', {
				cssPath : '<%=basePath%>editor/plugins/code/prettify.css',
				uploadJson : '<%=basePath%>editor/jsp/upload_json.jsp',
				fileManagerJson : '<%=basePath%>editor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
				}
			});
			
			var editor2 = K.create('textarea[name="gbatch.sellerReceiveContent"]', {
				cssPath : '<%=basePath%>editor/plugins/code/prettify.css',
				uploadJson : '<%=basePath%>editor/jsp/upload_json.jsp',
				fileManagerJson : '<%=basePath%>editor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
				}
			});
			
			prettyPrint();
		});
</script>




<script type="text/javascript">
	$(document).ready(function(e) {
		$(".select1").uedSelect({
			width : 320
		});
		$(".select2").uedSelect({
			width : 155
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
		if (document.getElementById("gbatch.numTotal").value == "") {
			window.alert('请输入批次总数量!');
			return false;
		}
		if (isNaN(document.getElementById("gbatch.numTotal").value)) {
			window.alert('批次总数量：请输入数字!');
			return false;
		}
		if (document.getElementById("gbatch.numStock").value == "") {
			window.alert('请输入批次库存!');
			return false;
		}
		if (isNaN(document.getElementById("gbatch.numStock").value)) {
			window.alert('批次库存：请输入数字!');
			return false;
		}
		if (document.getElementById("gbatch.dateKeep1").value == "") {
			window.alert('请输入批次保质期开始时间!');
			return false;
		}
		if (document.getElementById("gbatch.dateKeep2").value == "") {
			window.alert('请输入批次保质期结束时间!');
			return false;
		}
		if (document.getElementById("gbatch.dateSend").value == "") {
			window.alert('请输入生产商发货时间!');
			return false;
		}
		if (document.getElementById("gbatch.dateRec").value == "") {
			window.alert('请输入销售商收货时间!');
			return false;
		}
		if (document.getElementById("gbatch.producerSendContent").value == "") {
			window.alert('请输入批次生产详情!');
			return false;
		}
		if (document.getElementById("gbatch.sellerReceiveContent").value == "") {
			window.alert('请输入批次其它详情!');
			return false;
		}
		if (document.getElementById("producerid").value == "") {
			window.alert('请输入商品生产商!');
			return false;
		}
		if (document.getElementById("sellerid").value == "") {
			window.alert('请输入商品销售商!');
			return false;
		}
		
		return true;
	}
</script>



</head>

<body>
<div class="place">
		<span>位置:</span>
		<ul class="placeul">
		
		<li1><a href="<%=basePath%>mainindex.jsp">首页</a></li1>
			<li1><a href="<%=basePath%>goods.jsp">商品</a></li1>
			<li1><a href="<%=basePath%>sort/sort_doFind.action">商品分类</a></li1>
			<li1><a href="<%=basePath%>goods/goods_doFind.action">商品列表</a></li1>
			<li1><a href="<%=basePath%>goods/goods_doView.action?goods.goodsId=<%=goodsid%>">商品详情</a></li1>
			<li1><a href="<%=basePath%>gbatch/gbatch_doFind.action?goodsid=<%=goodsid%>">批次列表</a></li1>
			<li1><a href="<%=basePath%>oinfo/oinfo_doFind.action?goodsid=<%=goodsid%>">订单列表</a></li1>
			<li1><a  style="color:blue;" href="<%=basePath%>gbatch/gbatch_beforedoAdd.action?goodsid=<%=goodsid%>">添加批次</a></li1>
			<li1><a onClick="history.back(-1)">返回</a></li1>
		</ul>
	</div>

	<div class="formbody">

		<div id="usual1" name="usual1" class="usual">

			<form action="<%=basePath%>gbatch/gbatch_doAdd.action" method="post" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1">
				<div id="tab5" name="tab5" class="tabson">
					<ul class="forminfo">
						<input name="goodsid" id="goodsid" type="hidden" value="<%=goodsid%>" />
						<li><label>该批次数量<b>*</b></label><input name="gbatch.numTotal" id="gbatch.numTotal" type="text" value="" class="dfinput" placeholder="请填写数量" style="width:320px;" /></li>
						<li><label>该批次库存<b>*</b></label><input name="gbatch.numStock" id="gbatch.numStock" type="text" value="" class="dfinput" placeholder="请填写库存" style="width:320px;" /></li>
						<li><label>开始保质期<b>*</b></label><input name="gbatch.dateKeep1" id="gbatch.dateKeep1" type="text" value="" class="laydate-icon" placeholder="请填写保质期" style="width:320px;"
							onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" /></li>
						<li><label>结束保质期<b>*</b></label><input name="gbatch.dateKeep2" id="gbatch.dateKeep2" type="text" value="" class="laydate-icon" placeholder="请填写保质期" style="width:320px;"
							onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" /></li>


						<li><label>生产商<b>*</b></label>
							<div class="usercity">
								<select class="select1" name="producerid" id="producerid">
									<%
										for (int i = 0; i < companylist.size(); i++) {
										Company producer=companylist.get(i);
									%>
									<option value="<%=producer.getCompanyId()%>"><%=producer.getCompanyName()%></option>
									<%
										}
									%>
								</select>
							</div></li>
						<li><label>发货时间<b>*</b></label><input name="gbatch.dateSend" id="gbatch.dateSend" type="text" value="" class="laydate-icon" placeholder="请填写生产商发货时间" style="width:320px;"
							onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" /></li>

						<li><label>销售商<b>*</b></label>
							<div class="usercity">
								<select class="select1" name="sellerid" id="sellerid">
									<%
										for (int i = 0; i < companylist.size(); i++) {
										Company seller=companylist.get(i);
									%>
									<option value="<%=seller.getCompanyId()%>"><%=seller.getCompanyName()%></option>
									<%
										}
									%>
								</select>
							</div></li>
						<li><label>收货时间<b>*</b></label><input name="gbatch.dateRec" id="gbatch.dateRec" type="text" value="" class="laydate-icon" placeholder="请填写销售商收货时间" style="width:320px;"
							onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" /></li>
							
						<li><label>生产详情<b>*</b></label> <textarea id="gbatch.producerSendContent" name="gbatch.producerSendContent" style="width:700px;height:250px;visibility:hidden;"> </textarea></li>
						<li><label>其他详情<b>*</b></label> <textarea id="gbatch.sellerReceiveContent" name="gbatch.sellerReceiveContent" style="width:700px;height:250px;visibility:hidden;"> </textarea></li>
						<li><label>&nbsp;</label><input name="button" type="submit" class="btn" value="数据录入" /></li>
					</ul>
				</div>
			</form>



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
