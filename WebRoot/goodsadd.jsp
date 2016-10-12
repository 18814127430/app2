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
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	Object user = session.getAttribute("user");
	List<Sort> sortlist = 	(List<Sort>)request.getAttribute("sortlist");
	List<Company> companylist = (List<Company>)request.getAttribute("companylist");
	String time	=	(String)request.getAttribute("time");
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
<title>添加商品</title>



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
			var editor1 = K.create('textarea[name="goods.goodsDescription"]', {
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
			KindEditor.ready(function(K) {
				var editor = K.editor({
					cssPath : '<%=basePath%>editor/plugins/code/prettify.css',
					uploadJson : '<%=basePath%>editor/jsp/upload_json.jsp',
					fileManagerJson : '<%=basePath%>editor/jsp/file_manager_json.jsp',
					allowFileManager : true,
				});
				K('#image1').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							imageUrl : K('#url1').val(),
							clickFn : function(url, title, width, height, border, align) {
								var div = K('#localImage1');
								div.html('');
								div.append('<img src="' + url + '" class="phoinside" />');
								K('#url1').val(url);
								editor.hideDialog();
							}
						});
					});
				});
				K('#image2').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							imageUrl : K('#url2').val(),
							clickFn : function(url, title, width, height, border, align) {
								var div = K('#localImage2');
								div.html('');
								div.append('<img src="' + url + '" class="phoinside" />');
								K('#url2').val(url);
								editor.hideDialog();
							}
						});
					});
				});
				K('#image3').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							imageUrl : K('#url3').val(),
							clickFn : function(url, title, width, height, border, align) {
								var div = K('#localImage3');
								div.html('');
								div.append('<img src="' + url + '" class="phoinside" />');
								K('#url3').val(url);
								editor.hideDialog();
							}
						});
					});
				});
				K('#image4').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							imageUrl : K('#url4').val(),
							clickFn : function(url, title, width, height, border, align) {
								var div = K('#localImage4');
								div.html('');
								div.append('<img src="' + url + '" class="phoinside" />');
								K('#url4').val(url);
								editor.hideDialog();
							}
						});
					});
				});
				K('#image9').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							imageUrl : K('#url9').val(),
							clickFn : function(url, title, width, height, border, align) {
								var div = K('#localImage9');
								div.html('');
								div.append('<img src="' + url + '" class="phoinside" />');
								K('#url9').val(url);
								editor.hideDialog();
							}
						});
					});
				});
				K('#image10').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							imageUrl : K('#url10').val(),
							clickFn : function(url, title, width, height, border, align) {
								var div = K('#localImage10');
								div.html('');
								div.append('<img src="' + url + '" class="phoinside" />');
								K('#url10').val(url);
								editor.hideDialog();
							}
						});
					});
				});
				K('#image11').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							imageUrl : K('#url11').val(),
							clickFn : function(url, title, width, height, border, align) {
								var div = K('#localImage11');
								div.html('');
								div.append('<img src="' + url + '" class="phoinside" />');
								K('#url11').val(url);
								editor.hideDialog();
							}
						});
					});
				});
				K('#image12').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							imageUrl : K('#url12').val(),
							clickFn : function(url, title, width, height, border, align) {
								var div = K('#localImage12');
								div.html('');
								div.append('<img src="' + url + '" class="phoinside" />');
								K('#url12').val(url);
								editor.hideDialog();
							}
						});
					});
				});
				K('#image13').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							imageUrl : K('#url13').val(),
							clickFn : function(url, title, width, height, border, align) {
								var div = K('#localImage13');
								div.html('');
								div.append('<img src="' + url + '" class="phoinside" />');
								K('#url13').val(url);
								editor.hideDialog();
							}
						});
					});
				});
				K('#image14').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							imageUrl : K('#url14').val(),
							clickFn : function(url, title, width, height, border, align) {
								var div = K('#localImage14');
								div.html('');
								div.append('<img src="' + url + '" class="phoinside" />');
								K('#url14').val(url);
								editor.hideDialog();
							}
						});
					});
				});
				K('#image15').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							imageUrl : K('#url15').val(),
							clickFn : function(url, title, width, height, border, align) {
								var div = K('#localImage15');
								div.html('');
								div.append('<img src="' + url + '" class="phoinside" />');
								K('#url15').val(url);
								editor.hideDialog();
							}
						});
					});
				});
				K('#image16').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							imageUrl : K('#url16').val(),
							clickFn : function(url, title, width, height, border, align) {
								var div = K('#localImage16');
								div.html('');
								div.append('<img src="' + url + '" class="phoinside" />');
								K('#url16').val(url);
								editor.hideDialog();
							}
						});
					});
				});
				K('#localImage1').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							showRemote : false,
							imageUrl : K('#url1').val(),
							clickFn : function(url, title, width, height, border, align) {
								var div = K('#localImage1');
								div.html('');
								div.append('<img src="' + url + '" class="phoinside" />');
								K('#url1').val(url);
								editor.hideDialog();
							}
						});
					});
				});
				K('#localImage2').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							showRemote : false,
							imageUrl : K('#url2').val(),
							clickFn : function(url, title, width, height, border, align) {
								var div = K('#localImage2');
								div.html('');
								div.append('<img src="' + url + '" class="phoinside" />');
								K('#url2').val(url);
								editor.hideDialog();
							}
						});
					});
				});
				K('#localImage3').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							showRemote : false,
							imageUrl : K('#url3').val(),
							clickFn : function(url, title, width, height, border, align) {
								var div = K('#localImage3');
								div.html('');
								div.append('<img src="' + url + '" class="phoinside" />');
								K('#url3').val(url);
								editor.hideDialog();
							}
						});
					});
				});
				K('#localImage4').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							showRemote : false,
							imageUrl : K('#url4').val(),
							clickFn : function(url, title, width, height, border, align) {
								var div = K('#localImage4');
								div.html('');
								div.append('<img src="' + url + '" class="phoinside" />');
								K('#url4').val(url);
								editor.hideDialog();
							}
						});
					});
				});
				K('#localImage9').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							showRemote : false,
							imageUrl : K('#url9').val(),
							clickFn : function(url, title, width, height, border, align) {
								var div = K('#localImage9');
								div.html('');
								div.append('<img src="' + url + '" class="phoinside" />');
								K('#url9').val(url);
								editor.hideDialog();
							}
						});
					});
				});
				K('#localImage10').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							showRemote : false,
							imageUrl : K('#url10').val(),
							clickFn : function(url, title, width, height, border, align) {
								var div = K('#localImage10');
								div.html('');
								div.append('<img src="' + url + '" class="phoinside" />');
								K('#url10').val(url);
								editor.hideDialog();
							}
						});
					});
				});
				K('#localImage11').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							showRemote : false,
							imageUrl : K('#url11').val(),
							clickFn : function(url, title, width, height, border, align) {
								var div = K('#localImage11');
								div.html('');
								div.append('<img src="' + url + '" class="phoinside" />');
								K('#url11').val(url);
								editor.hideDialog();
							}
						});
					});
				});
				K('#localImage12').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							showRemote : false,
							imageUrl : K('#url12').val(),
							clickFn : function(url, title, width, height, border, align) {
								var div = K('#localImage12');
								div.html('');
								div.append('<img src="' + url + '" class="phoinside" />');
								K('#url12').val(url);
								editor.hideDialog();
							}
						});
					});
				});
				K('#localImage13').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							showRemote : false,
							imageUrl : K('#url13').val(),
							clickFn : function(url, title, width, height, border, align) {
								var div = K('#localImage13');
								div.html('');
								div.append('<img src="' + url + '" class="phoinside" />');
								K('#url13').val(url);
								editor.hideDialog();
							}
						});
					});
				});
				K('#localImage14').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							showRemote : false,
							imageUrl : K('#url14').val(),
							clickFn : function(url, title, width, height, border, align) {
								var div = K('#localImage14');
								div.html('');
								div.append('<img src="' + url + '" class="phoinside" />');
								K('#url14').val(url);
								editor.hideDialog();
							}
						});
					});
				});
				K('#localImage15').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							showRemote : false,
							imageUrl : K('#url15').val(),
							clickFn : function(url, title, width, height, border, align) {
								var div = K('#localImage15');
								div.html('');
								div.append('<img src="' + url + '" class="phoinside" />');
								K('#url15').val(url);
								editor.hideDialog();
							}
						});
					});
				});
				K('#localImage16').click(function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							showRemote : false,
							imageUrl : K('#url16').val(),
							clickFn : function(url, title, width, height, border, align) {
								var div = K('#localImage16');
								div.html('');
								div.append('<img src="' + url + '" class="phoinside" />');
								K('#url16').val(url);
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
			width : 155
		});
		$(".select3").uedSelect({
			width : 100
		});
	});
</script>


<script type="text/javascript">
	!function() {
		laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
		laydate({
			elem : '#demo'
		});//绑定元素
	}();
</script>


<script type="text/javascript">
	function checkForm() {
		if (document.getElementById("url1").value == "") {
			window.alert('请选择商品图片1!');
			return false;
		}
		if (document.getElementById("url2").value == "") {
			window.alert('请选择商品图片2!');
			return false;
		}
		if (document.getElementById("url3").value == "") {
			window.alert('请选择商品图片3!');
			return false;
		}
		if (document.getElementById("url4").value == "") {
			window.alert('请选择商品图片4!');
			return false;
		}
		if (document.getElementById("url9").value == "") {
			window.alert('请选择商品图片5!');
			return false;
		}
		if (document.getElementById("url10").value == "") {
			window.alert('请选择商品图片6!');
			return false;
		}
		if (document.getElementById("url11").value == "") {
			window.alert('请选择商品图片7!');
			return false;
		}
		if (document.getElementById("url12").value == "") {
			window.alert('请选择商品图片8!');
			return false;
		}
		if (document.getElementById("goods.goodsName").value == "") {
			window.alert('请输入商品名!');
			return false;
		}
		if (document.getElementById("goods.goodsWeight").value == "") {
			window.alert('请输入商品重量!');
			return false;
		}
		if (document.getElementById("goods.goodsRemark").value == "") {
			window.alert('请输入商品备注!');
			return false;
		}
		if (document.getElementById("goods.numTotal").value == "") {
			window.alert('请输入商品数量!');
			return false;
		}
		if (isNaN(document.getElementById("goods.numTotal").value)) {
			window.alert('商品数量：请输入数字!');
			return false;
		}
		if (document.getElementById("goods.numStock").value == "") {
			window.alert('请输入商品库存!');
			return false;
		}
		if (isNaN(document.getElementById("goods.numStock").value)) {
			window.alert('商品库存：请输入数字!');
			return false;
		}
		if (document.getElementById("goods.moneyOld").value == "") {
			window.alert('请输入商品原售价!');
			return false;
		}
		if (isNaN(document.getElementById("goods.moneyOld").value)) {
			window.alert('原售价：请输入数字!');
			return false;
		}
		if (document.getElementById("goods.moneyNew").value == "") {
			window.alert('请输入商品优惠价!');
			return false;
		}
		if (isNaN(document.getElementById("goods.moneyNew").value)) {
			window.alert('优惠价：请输入数字!');
			return false;
		}
		if (document.getElementById("goods.moneyDeliver").value == "") {
			window.alert('请输入商品运费!');
			return false;
		}
		if (isNaN(document.getElementById("goods.moneyDeliver").value)) {
			window.alert('商品运费：请输入数字!');
			return false;
		}
		if (document.getElementById("goods.deliverFree").value == "") {
			window.alert('请选择是否包邮!');
			return false;
		}
		if (document.getElementById("goods.goodsTags").value == "") {
			window.alert('请输入商品标签!');
			return false;
		}
		if (document.getElementById("goods.goodsUnits").value == "") {
			window.alert('请输入商品单位!');
			return false;
		}
		if (document.getElementById("goods.checkDate").value == "") {
			window.alert('请输入商品检验日期!');
			return false;
		}
		if (document.getElementById("goods.checkDepartment").value == "") {
			window.alert('请输入商品检验部门!');
			return false;
		}
		if (document.getElementById("goods.checkResult").value == "") {
			window.alert('请输入商品检验结果!');
			return false;
		}
		if (document.getElementById("goods.checkSerial").value == "") {
			window.alert('请输入商品检验序列号!');
			return false;
		}
		if (document.getElementById("goods.goodsAddDate").value == "") {
			window.alert('请输入商品添加时间!');
			return false;
		}
		if (document.getElementById("sortid2").value == "") {
			window.alert('请输入商品类别!');
			return false;
		}
		if (document.getElementById("sortid").value == "") {
			window.alert('请输入商品类别!');
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
	<form action="<%=basePath%>goods/goods_doAdd.action" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1" method="post">
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
			<li1><a href="<%=basePath%>mainindex.jsp">首页</a></li1>
			<li1><a href="<%=basePath%>goods.jsp">商品查找</a></li1>
			<li1><a href="<%=basePath%>sort/sort_doFind.action">商品分类</a></li1>
			<li1><a href="<%=basePath%>goods/goods_doFind.action">商品列表</a></li1>
			<li1><a href="<%=basePath%>gbatch/gbatch_doFind.action" target="rightFrame">所有批次</a></li1>
			<li1><a style="color:blue;"  href="<%=basePath%>goods/goods_beforedoAdd.action" target="rightFrame">添加商品</a></li1>
			<li1><a onClick="history.back(-1)">返回</a></li1>
			</ul>
		</div>
		<div class="formbody" id="formbody">

			<div id="usual1" name="usual1" class="usual">

				<div class="ibox" style="float:right;">
					<a style="float:right;margin-left:5px;margin-right:5px;" class="ibtn" href="#formtitle6">
						<img src="<%=basePath%>images/idown.png" />去到底部
					</a>
				</div>
				<br>


				<div id="tab1" name="tab1" style="background: none repeat scroll 0% 0% rgb(255, 255, 255);">

					<div class="tabson">
						<div class="formtitle">
							<span>商品基本信息</span>
						</div>

						<div class="tabson1">
							<ul class="forminfo">
								<li><label>商品名称<b>*</b></label><input name="goods.goodsName" id="goods.goodsName" type="text" value="" class="dfinput" placeholder="请填写名称" style="width:320px;" /></li>
								<li><label>商品分类<b>*</b></label>
									<div class="usercity">
										<div class="cityleft">
											<select class="select1" name="sortid" id="sortid">
												<%
													for (int i = 0; i < sortlist.size(); i++) {
														Sort parentsort = sortlist.get(i);
												%>
												<optgroup label="<%=parentsort.getSortName()%>">
													<%
														Set sortSet = parentsort.getSorts();
														Iterator iterator1 = sortSet.iterator();
													    while (iterator1.hasNext()) {
														Sort childrensort = (Sort) iterator1.next();
													%>
													<option value="<%=childrensort.getSortId()%>"><%=childrensort.getSortName()%></option>
													<%
														}
													%>
												</optgroup>
												<%
													}
												%>
											</select>
										</div></li>
								<li><label>关 键 字<b>*</b></label><input name="goods.goodsKeyWord" id="goods.goodsKeyWord" type="text" value="" class="dfinput" placeholder="请填写关键字，以英文逗号分隔，空则自动生成" style="width:320px;" /></li>
								<li><label>商品标签<b>*</b></label><input name="goods.goodsTags" id="goods.goodsTags" type="text" value="精品" class="dfinput" placeholder="请填写如新鲜、精品、优质蔬果等词语，不超过10字" style="width:320px;" /></li>
								<li><label>商品备注<b>*</b></label><input name="goods.goodsRemark" id="goods.goodsRemark" type="text" value="精品" class="dfinput" placeholder="请填写商品备注" style="width:320px;" /></li>
								<li><label>添加时间<b>*</b></label> <input name="goods.goodsAddDate" id="goods.goodsAddDate" type="text" value="<%=time %>" class="laydate-icon" placeholder="请填写商品检验日期" style="width:320px;"
									onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" /></li>
								<li><label>商品数量<b>*</b></label><input name="goods.numTotal" id="goods.numTotal" type="text" value="0" class="dfinput" placeholder="请填写数量" style="width:320px;" /></li>
								<li><label>商品库存<b>*</b></label><input name="goods.numStock" id="goods.numStock" type="text" value="0" class="dfinput" placeholder="请填写库存" style="width:320px;" /></li>
								<li><label>商品重量<b>*</b></label><input name="goods.goodsWeight" id="goods.goodsWeight" type="text" value="120g" class="dfinput" placeholder="请填写重量" style="width:320px;" /></li>
								<li><label>商品单位<b>*</b></label><input name="goods.goodsUnits" id="goods.goodsUnits" type="text" value="盒" class="dfinput" placeholder="请填写商品单位" style="width:320px;" /></li>
								
							</ul>
						</div>
						<div class="tabson1">
							<ul class="forminfo">
								<li><label>原 售 价<b>*</b></label><input name="goods.moneyOld" id="goods.moneyOld" type="text" value="" class="dfinput" placeholder="请填写原售价，可与优惠价相同" style="width:320px;" /></li>
								<li><label>优 惠 价<b>*</b></label><input name="goods.moneyNew" id="goods.moneyNew" type="text" value="" class="dfinput" placeholder="请填写优惠价，可与原售价相同" style="width:320px;" /></li>
								<li><label>商品运费<b>*</b></label><input name="goods.moneyDeliver" id="goods.moneyDeliver" type="text" value="2" class="dfinput" placeholder="请填写运费" style="width:320px;" /></li>
								<li><label>包邮金额<b>*</b></label><input name="goods.moneyLeast" id="goods.moneyLeast" type="text" value="80" class="dfinput" placeholder="请填写最低包邮金额" style="width:320px;" /></li>
								<li><label>小号规格<b>*</b></label><input name="goods.goodsSize2" id="goods.goodsSize2" type="text" value="盒" class="dfinput" placeholder="请填写小号规格" style="width:320px;" /></li>
								<li><label>大号规格<b>*</b></label><input name="goods.goodsSize1" id="goods.goodsSize1" type="text" value="盒" class="dfinput" placeholder="请填写大号规格" style="width:320px;" /></li>
							    <li><label>检验日期<b>*</b></label> <input name="goods.checkDate" id="goods.checkDate" type="text" value="<%=time %>" class="laydate-icon" placeholder="请填写商品检验日期" style="width:320px;"
									onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" /></li>
								<li><label>检验部门<b>*</b></label><input name="goods.checkDepartment" id="goods.checkDepartment" type="text" value="无" class="dfinput" placeholder="请填写商品检验部门" style="width:320px;" /></li>
								<li><label>检验序列号<b>*</b></label><input name="goods.checkSerial" id="goods.checkSerial" type="text" value="无" class="dfinput" placeholder="请填写商品检验序列号" style="width:320px;" /></li>
								<li><label>检验状态<b>*</b></label>
									<div class="vocation">
										<select class="select1" name="goods.checkResult" id="goods.checkResult">
											<option>已检验</option>
											<option>未检验</option>
										</select>
									</div></li>
							</ul>
						</div>
						<!--<div class="tabson1"> 
							<ul class="forminfo" style="width:1000px;">
								<div style="float:left;">
									<label style="width: 85px;line-height: 34px;display: block;float: left; color:#D8D8E1;">商品单位<b color="#d70101;">*</b></label>
								</div>
								<div style="float:left;margin-top:9px;">
									<textarea style="width:884px;height:100px;visibility:hidden;" id="goods.goodsDescription" name="goods.goodsDescription"></textarea>
								</div>
								<div class="ibox1"></div>
							</ul>
						</div>-->
					</div>

					<div class="ibox"></div>
					<div class="tabson">
						<div  class="formtitle">
							<span>移动端app首页商品轮播图片</span>
						</div>
						<div class="pho">
							<ul class="forminfo">
								<li><label style="width:60px;" class="phofont1">图片1<b>*</b></label> <label style="width:160px;" class="phofont1">轮播图</label></li>
								<li><i style="color:#878787;margin-left:-20px;">像素提示：约750*320</i></li>
								<li>
									<div id="localImage1">
										<img src="<%=basePath%>uploads/nopic.png" id="preview1" class="phoinside" />
									</div>
								</li>
								<li><input type="hidden" id="url1" name="goods.img1" value="" /></li>
								<li><p>
										<input type="button" id="image1" value="选择图片" class="btn1" />
										<i style="color:#666666;margin-left:-20px;">（网络+本地上传）</i>
									</p></li>
							</ul>
						</div>
					</div>
					
					<div class="ibox"></div>
					<div class="tabson">
						<div  class="formtitle">
							<span>移动端app首页展示图片</span>
						</div>

						<div class="pho">
							<ul class="forminfo">
								<li><label style="width:60px;" class="phofont1">图片2<b>*</b></label> <label style="width:160px;" class="phofont1">通用展示正方形图片</label></li>
								<li><i style="color:#878787;margin-left:-20px;">像素提示：约750*750</i></li>
								<li>
									<div id="localImage2">
										<img src="<%=basePath%>uploads/nopic.png" id="preview2" class="phoinside" />
									</div>
								</li>
								<li><input type="hidden" id="url2" name="goods.img2" value="" /></li>
								<li><p>
										<input type="button" id="image2" value="选择图片" class="btn1" />
										<i style="color:#666666;margin-left:-20px;">（网络+本地上传）</i>
									</p></li>
							</ul>
						</div>

						<div class="pho">
							<ul class="forminfo">
								<li><label style="width:60px;" class="phofont1">图片3<b>*</b></label> <label style="width:160px;" class="phofont1">分类左中展示图片</label></li>
								<li><i style="color:#878787;margin-left:-20px;">像素提示：约470*590</i></li>
								<li>
									<div id="localImage3">
										<img src="<%=basePath%>uploads/nopic.png" id="preview3" class="phoinside" />
									</div>
								</li>
								<li><input type="hidden" id="url3" name="goods.img3" value="" /></li>
								<li><p>
										<input type="button" id="image3" value="选择图片" class="btn1" />
										<i style="color:#666666;margin-left:-20px;">（网络+本地上传）</i>
									</p></li>
							</ul>
						</div>

						<div class="pho">
							<ul class="forminfo">
								<li><label style="width:60px;" class="phofont1">图片4<b>*</b></label> <label style="width:160px;" class="phofont1">分类右上展示图片</label></li>
								<li><i style="color:#878787;margin-left:-20px;">像素提示：约590*300</i></li>
								<li>
									<div id="localImage4">
										<img src="<%=basePath%>uploads/nopic.png" id="preview4" class="phoinside" />
									</div>
								</li>
								<li><input type="hidden" id="url4" name="goods.img4" value="" /></li>
								<li><p>
										<input type="button" id="image4" value="选择图片" class="btn1" />
										<i style="color:#666666;margin-left:-20px;">（网络+本地上传）</i>
									</p></li>
							</ul>
						</div>
					</div>

					<div class="ibox"></div>
					<div class="tabson">
						<div class="formtitle">
						<span>网页图片信息</span>
						</div>
						<div class="pho">
							<ul class="forminfo">
								<li><label style="width:60px;" class="phofont1">图片5<b>*</b></label> <label style="width:160px;" class="phofont1">(必要)网页显示图片</label></li>
								<li><i style="color:#878787;margin-left:-20px;">像素提示：大于1024*519</i></li>
								<li>
									<div id="localImage9">
										<img src="<%=basePath%>uploads/nopic.png" id="preview9" class="phoinside" />
									</div>
								</li>
								<li><input type="hidden" id="url9" name="goods.img9" value="" /></li>
								<li><p>
										<input type="button" id="image9" value="选择图片" class="btn1" />
										<i style="color:#666666;margin-left:-20px;">（网络+本地上传）</i>
									</p></li>
							</ul>
						</div>

						<div class="pho">
							<ul class="forminfo">
								<li><label style="width:60px;" class="phofont1">图片6<b>*</b></label> <label style="width:160px;" class="phofont1">(必要)网页显示图片</label></li>
								<li><i style="color:#878787;margin-left:-20px;">像素提示：大于1024*519</i></li>
								<li>
									<div id="localImage10">
										<img src="<%=basePath%>uploads/nopic.png" id="preview10" class="phoinside" />
									</div>
								</li>
								<li><input type="hidden" id="url10" name="goods.img10" value="" /></li>
								<li><p>
										<input type="button" id="image10" value="选择图片" class="btn1" />
										<i style="color:#666666;margin-left:-20px;">（网络+本地上传）</i>
									</p></li>
							</ul>
						</div>

						<div class="pho">
							<ul class="forminfo">
								<li><label style="width:60px;" class="phofont1">图片7<b>*</b></label> <label style="width:160px;" class="phofont1">(必要)网页显示图片</label></li>
								<li><i style="color:#878787;margin-left:-20px;">像素提示：大于1024*519</i></li>
								<li>
									<div id="localImage11">
										<img src="<%=basePath%>uploads/nopic.png" id="preview11" class="phoinside" />
									</div>
								</li>
								<li><input type="hidden" id="url11" name="goods.img11" value="" /></li>
								<li><p>
										<input type="button" id="image11" value="选择图片" class="btn1" />
										<i style="color:#666666;margin-left:-20px;">（网络+本地上传）</i>
									</p></li>
							</ul>
						</div>
						
						<div class="pho">
							<ul class="forminfo">
								<li><label style="width:60px;" class="phofont1">图片8<b>*</b></label> <label style="width:160px;" class="phofont1">(必要)网页显示图片</label></li>
								<li><i style="color:#878787;margin-left:-20px;">像素提示：大于1024*519</i></li>
								<li>
									<div id="localImage12">
										<img src="<%=basePath%>uploads/nopic.png" id="preview12" class="phoinside" />
									</div>
								</li>
								<li><input type="hidden" id="url12" name="goods.img12" value="" /></li>
								<li><p>
										<input type="button" id="image12" value="选择图片" class="btn1" />
										<i style="color:#666666;margin-left:-20px;">（网络+本地上传）</i>
									</p></li>
							</ul>
						</div>
						
						<div class="pho">
							<ul class="forminfo">
								<li><label style="width:60px;" class="phofont1">图片9<b>*</b></label> <label style="width:160px;" class="phofont1">网页显示图片</label></li>
								<li><i style="color:#878787;margin-left:-20px;">像素提示：大于1024*519</i></li>
								<li>
									<div id="localImage13">
										<img src="<%=basePath%>uploads/nopic.png" id="preview13" class="phoinside" />
									</div>
								</li>
								<li><input type="hidden" id="url13" name="goods.img13" value="" /></li>
								<li><p>
										<input type="button" id="image13" value="选择图片" class="btn1" />
										<i style="color:#666666;margin-left:-20px;">（网络+本地上传）</i>
									</p></li>
							</ul>
						</div>
						
						<div class="pho">
							<ul class="forminfo">
								<li><label style="width:60px;" class="phofont1">图片10<b>*</b></label> <label style="width:160px;" class="phofont1">网页显示图片</label></li>
								<li><i style="color:#878787;margin-left:-20px;">像素提示：大于1024*519</i></li>
								<li>
									<div id="localImage14">
										<img src="<%=basePath%>uploads/nopic.png" id="preview14" class="phoinside" />
									</div>
								</li>
								<li><input type="hidden" id="url14" name="goods.img14" value="" /></li>
								<li><p>
										<input type="button" id="image14" value="选择图片" class="btn1" />
										<i style="color:#666666;margin-left:-20px;">（网络+本地上传）</i>
									</p></li>
							</ul>
						</div>
						
						<div class="pho">
							<ul class="forminfo">
								<li><label style="width:60px;" class="phofont1">图片11<b>*</b></label> <label style="width:160px;" class="phofont1">网页显示图片</label></li>
								<li><i style="color:#878787;margin-left:-20px;">像素提示：大于1024*519</i></li>
								<li>
									<div id="localImage15">
										<img src="<%=basePath%>uploads/nopic.png" id="preview15" class="phoinside" />
									</div>
								</li>
								<li><input type="hidden" id="url15" name="goods.img15" value="" /></li>
								<li><p>
										<input type="button" id="image15" value="选择图片" class="btn1" />
										<i style="color:#666666;margin-left:-20px;">（网络+本地上传）</i>
									</p></li>
							</ul>
						</div>

						<div class="pho">
							<ul class="forminfo">
								<li><label style="width:60px;" class="phofont1">图片12<b>*</b></label> <label style="width:160px;" class="phofont1">网页显示图片</label></li>
								<li><i style="color:#878787;margin-left:-20px;">像素提示：大于1024*519</i></li>
								<li>
									<div id="localImage16">
										<img src="<%=basePath%>uploads/nopic.png" id="preview16" class="phoinside" />
									</div>
								</li>
								<li><input type="hidden" id="url16" name="goods.img16" value="" /></li>
								<li><p>
										<input type="button" id="image16" value="选择图片" class="btn1" />
										<i style="color:#666666;margin-left:-20px;">（网络+本地上传）</i>
									</p></li>
							</ul>
						</div>
					</div>

					<div class="ibox1"></div>
					<div class="tabson">
						<div class="formtitle">
							<span>网页文字信息</span>
						</div>

<!-- 						<div style="float:left;"> 
							<label style="width: 85px;line-height: 34px;display: block;float: left;">大号标题1<b color="#d70101;">*</b></label>
						</div>
						<div style="float:left;width: 700px;height:50px;border-color: white;border-style:solid;border-width: 1px;border-radius:8px;margin:-5px 10px 20px 10px;float: left;background: none repeat scroll 0% 0% rgb(243, 249, 240);">
							<input name="create1" id="create1" type="text" value="" class="dfinput" placeholder="此处为轮播图片1显示的大号标题文字"
								style="margin:4px 4px 4px 4px;width:692px; height:42px; font-size:13px; font-family:微软雅黑; font-style:normal; color: #03418f;background:#F3FEFC; border-radius:8px; border-color:#CCC;" />
						</div>
						<div style="float:left;">
							<label style="width: 85px;line-height: 34px;display: block;float: left;color:#C0C0C0;">不超过4个汉字</label>
						</div>
						<div class="ibox1"></div>-->


						<div style="float:left;">
							<label style="width: 85px;line-height: 34px;display: block;float: left;">商品文字信息<b color="#d70101;">*</b></label>
						</div>
						<div style="float:left;width: 700px;border-radius:8px;margin:-5px 10px 20px 10px;">
							<textarea style="width:700px;height:100px;visibility:hidden;" id="goods.goodsDescription" name="goods.goodsDescription"> </textarea>
						</div>
						<div style="float:left;">
							<label style="width: 280px;line-height: 34px;display: block;float: left;color:#C0C0C0;">纯文本输入，字数不限</label>
						</div>
						<div class="ibox1"></div>

					</div>

				</div>

				<div class="ibox"></div>
				<div>
					<li style="position:absolute;right:50%;translateX(-50%);"><label>&nbsp;</label> <input name="button" type="submit" class="btn" value="数据录入" /></li>
				</div>
				<div id="formtitle6" class="ibox" style="float:right;">
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
