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
	GBatch gbatch = 	(GBatch)request.getAttribute("gbatch");
	List<Company> companylist = (List<Company>)request.getAttribute("companylist");
	if (user == null) {
		response.getWriter().println("<script>top.location.href='" + basePath+ "admin/admin_doLogin.action';</script>");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改商品批次</title>


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
		if (document.getElementById("gbatch.batchNumTotal").value == "") {
			window.alert('请输入批次总数量!');
			return false;
		}
		if (document.getElementById("gbatch.batchNumStock").value == "") {
			window.alert('请输入批次库存!');
			return false;
		}
		if (document.getElementById("gbatch.batchDateKeep").value == "") {
			window.alert('请输入批次保质期!');
			return false;
		}
		if (document.getElementById("gbatch.producerSendDate").value == "") {
			window.alert('请输入批次生产时间!');
			return false;
		}
		if (document.getElementById("gbatch.producerSendContent").value == "") {
			window.alert('请输入批次生产详情!');
			return false;
		}if (document.getElementById("gbatch.sellerReceiveDate").value == "") {
			window.alert('请输入批次入店时间!');
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
	<div class="formbody">

		<div id="usual1" name="usual1" class="usual">

			<div class="itab">
				<ul>
					<li><a href="<%=basePath%>goods/goods_doView.action?goods.goodsId=<%=gbatch.getGoods().getGoodsId()%>">商品查看</a></li>
					<li><a href="<%=basePath%>gbatch/gbatch_doFindByGoodsId.action?goodsid=<%=gbatch.getGoods().getGoodsId()%>">商品批次列表</a></li>
					<li><a href="#tab4" class="selected">批次详情修改</a></li>
					<li><a href="<%=basePath%>gbatch/gbatch_beforedoAdd.action?goodsid=<%=gbatch.getGoods().getGoodsId()%>" >添加批次</a></li>
				</ul>
			</div>

			<form action="<%=basePath%>gbatch/gbatch_doUpdate.action" method="post" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1">
				<div id="tab5" name="tab5" class="tabson">
					<ul class="forminfo">
						<input name="goodsid" id="goodsid" type="hidden" value="<%=gbatch.getGoods().getGoodsId()%>" />
						<input name="gbatch.batchId" id="gbatch.batchId" type="hidden" value="<%=gbatch.getBatchId()%>" />
						<li><label>数量<b>*</b></label><input name="gbatch.batchNumTotal" id="gbatch.batchNumTotal" type="text" value="<%=gbatch.getBatchNumTotal()%>" class="dfinput" placeholder="请填写数量" style="width:320px;" /></li>
						<li><label>库存<b>*</b></label><input name="gbatch.batchNumStock" id="gbatch.batchNumStock" type="text" value="<%=gbatch.getBatchNumStock()%>" class="dfinput" placeholder="请填写库存" style="width:320px;" /></li>
						<li><label>保质期<b>*</b></label><input name="gbatch.batchDateKeep" id="gbatch.batchDateKeep" type="text" value="<%=gbatch.getBatchDateKeep()%>" class="dfinput" placeholder="请填写保质期" style="width:320px;" /></li>
						<li><label>生产时间<b>*</b></label><input name="gbatch.producerSendDate" id="gbatch.producerSendDate" type="text" value="<%=gbatch.getProducerSendDate()%>" class="laydate-icon" placeholder="请填写生产时间" style="width:320px;"
							onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" /></li>
						<li><label>入货时间<b>*</b></label><input name="gbatch.sellerReceiveDate" id="gbatch.sellerReceiveDate" type="text" value="<%=gbatch.getSellerReceiveDate()%>" class="laydate-icon" placeholder="请填写入店时间" style="width:320px;"
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

						<li><label>生产详情<b>*</b></label> <textarea id="gbatch.producerSendContent" name="gbatch.producerSendContent" style="width:700px;height:250px;visibility:hidden;"><%=gbatch.getProducerSendContent()%></textarea></li>
						<li><label>其他详情<b>*</b></label> <textarea id="gbatch.sellerReceiveContent" name="gbatch.sellerReceiveContent" style="width:700px;height:250px;visibility:hidden;"><%=gbatch.getSellerReceiveContent()%></textarea></li>
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
