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
	Goods goods=(Goods)request.getAttribute("goods");
	Sort sort=(Sort)request.getAttribute("sort");
	List<Sort> sortlist = 	(List<Sort>)request.getAttribute("sortlist");
	List<Company> companylist = (List<Company>)request.getAttribute("companylist");
	if (user == null) {
		response.getWriter().println("<script>top.location.href='" + basePath+ "admin/admin_doLogin.action';</script>");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看商品</title>


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


</head>

<body>

	<div class="formbody">

		<div id="usual1" name="usual1" class="usual">

			<div class="itab">
				<ul>
					<li><a href="<%=basePath%>sort/sort_doFind.action">商品分类</a></li>
					<li><a href="#tab1" class="selected">商品查看</a></li>
					<li><a href="#tab2">图片查看</a></li>
					<li><a href="<%=basePath%>gbatch/gbatch_doFindByGoodsId.action?goodsid=<%=goods.getGoodsId()%>">批次列表</a></li>
					<li><a href="<%=basePath%>oinfo/oinfo_doFindByGoodsId.action?goodsid=<%=goods.getGoodsId()%>">订单列表</a></li>
					<li><a href="<%=basePath%>gbatch/gbatch_beforedoAdd.action?goodsid=<%=goods.getGoodsId()%>">添加批次</a></li>
				</ul>
			</div>

			<div id="tab1" name="tab1" class="tabson">
				<ul class="forminfo">
					<li><label>商品名称<b>*</b></label><input type="text" value="<%=goods.getGoodsName()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<li><label>关 键 字<b>*</b></label><input type="text" value="<%=goods.getGoodsKeyWord()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<li><label>商品重量<b>*</b></label><input type="text" value="<%=goods.getGoodsWeight()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<li><label>商品数量<b>*</b></label><input type="text" value="<%=goods.getGoodsNumTotal()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<li><label>商品库存<b>*</b></label><input type="text" value="<%=goods.getGoodsNumStock()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<li><label>商品备注<b>*</b></label><input type="text" value="<%=goods.getGoodsRemark()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<li><label>进 货 价<b>*</b></label><input type="text" value="<%=goods.getGoodsMoneyEntry()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<li><label>商品售价<b>*</b></label><input type="text" value="<%=goods.getGoodsMoneyRetail()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<li><label>商品运费<b>*</b></label><input type="text" value="<%=goods.getGoodsMoneyDeliver()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<li><label>商品规格<b>*</b></label><input type="text" value="<%=goods.getGoodsSize2()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<li><label>商品规格<b>*</b></label><input type="text" value="<%=goods.getGoodsSize1()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<li><label>商品单位<b>*</b></label><input type="text" value="<%=goods.getGoodsUnits()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<li><label>检验日期<b>*</b></label><input type="text" value="<%=goods.getGoodsCheckDate()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<li><label>检验部门<b>*</b></label><input type="text" value="<%=goods.getGoodsCheckDepartment()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<li><label>序 列 号<b>*</b></label><input type="text" value="<%=goods.getGoodsCheckSerial()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
					<li><label>检验状态<b>*</b></label><input type="text" value="<%=goods.getGoodsCheckResult()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>

					<li><label>商品分类<b>*</b></label> <input type="text" value="<%=sort.getSort().getSortName()%>" class="dfinput1" readonly="true" style="width:80px;" /> <input type="text" value="<%=goods.getSort().getSortName()%>" class="dfinput1"
						readonly="true" style="width:80px;" /></li>

					<li><label>商品介绍<b>*</b></label> <textarea name="goods.goodsDescription" style="width:700px;height:250px;visibility:hidden;"><%=goods.getGoodsDescription()%></textarea></li>

					<li><label>&nbsp;</label> <input name="button" type="button" class="btn" value="数据修改" onClick="javascript:location.href='<%=basePath%>goods/goods_doEdit.action?goods.goodsId=<%=goods.getGoodsId()%>'" /> <label>&nbsp;</label> <input
						name="button" type="button" class="btn" value="返回上级" onClick="history.back(-1)" /></li>
				</ul>
			</div>
			</form>




			<div id="tab2" name="tab2" class="tabson">
					<ul class="forminfo">
					
					<li><label>商品图片1<b>*</b></label><input type="text" value="通用图片" class="dfinput1" readonly="true" /></li>
					<li><span> <img src="<%=goods.getImg1()%>" width=150 height=160 style="diplay:none" /></span></li>
					<div class="xline"></div>
					
					<div class="ibox1"></div>
					<li><label>商品图片2<b>*</b></label><input type="text" value="通用图片" class="dfinput1" readonly="true" /></li>
					<li><span> <img src="<%=goods.getImg2()%>" width=150 height=160 style="diplay:none"  /></span></li>
					<div class="xline"></div>
					
					<div class="ibox1"></div>
					<li><label>商品图片3<b>*</b></label><input type="text" value="通用图片" class="dfinput1" readonly="true" /></li>
					<li><span> <img src="<%=goods.getImg3()%>" width=150 height=160 style="diplay:none"  /></span></li>
					<div class="xline"></div>
					
					<div class="ibox1"></div>
					<li><label>商品图片4<b>*</b></label><input type="text" value="通用图片" class="dfinput1" readonly="true" /></li>
					<li><span> <img src="<%=goods.getImg4()%>" width=150 height=160 style="diplay:none" /></span></li>
					<div class="xline"></div>
					
					<div class="ibox1"></div>
					<li><label>商品图片5<b>*</b></label><input type="text" value="通用图片" class="dfinput1" readonly="true" /></li>
					<li><span> <img src="<%=goods.getImg5()%>" width=150 height=160 style="diplay:none"  /></span></li>
					<div class="xline"></div>
					
					<div class="ibox1"></div>
					<li><label>商品图片6<b>*</b></label><input type="text" value="通用图片" class="dfinput1" readonly="true" /></li>
					<li><span> <img src="<%=goods.getImg6()%>" width=150 height=160 style="diplay:none"  /></span></li>
					<div class="xline"></div>
					
					<div class="ibox1"></div>
					<li><label>商品图片7<b>*</b></label><input type="text" value="通用图片" class="dfinput1" readonly="true" /></li>
					<li><span> <img src="<%=goods.getImg7()%>" width=150 height=160 style="diplay:none"  /></span></li>
					<div class="xline"></div>
					
					<div class="ibox1"></div>
					<li><label>商品图片8<b>*</b></label><input type="text" value="通用图片" class="dfinput1" readonly="true" /></li>
					<li><span> <img src="<%=goods.getImg8()%>" width=150 height=160 style="diplay:none"  /></span></li>
					<div class="xline"></div>
					
					<div class="ibox1"></div>
					<li><label>商品图片9<b>*</b></label><input type="text" value="通用图片" class="dfinput1" readonly="true" /></li>
					<li><span> <img src="<%=goods.getImg9()%>" width=150 height=160 style="diplay:none" /></span></li>
					<div class="xline"></div>
					
					<div class="ibox"></div>
					<li><label>&nbsp;</label> <input name="button" type="button" class="btn" value="数据修改" onClick="javascript:location.href='<%=basePath%>goods/goods_doEdit.action?goods.goodsId=<%=goods.getGoodsId()%>'" /> <label>&nbsp;</label> <input
						name="button" type="button" class="btn" value="返回上级" onClick="history.back(-1)" /></li>
						
					</ul>
				</div>
				
				<div class="ibox"></div>
				<div class="ibox"></div>
				<div class="ibox"></div>

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
