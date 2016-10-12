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
List<Sort> sortlist = 	(List<Sort>)request.getAttribute("sortlist");
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




</head>

<body>
	<div class="place">
		<span>位置:</span>
		<ul class="placeul">
			<li1><a href="<%=basePath%>sort/sort_doFind.action">商品分类</a></li1>
			<li1><a href="<%=basePath%>goods/goods_doFind.action">商品列表</a></li1>
			<li1><a style="color:blue;" href="<%=basePath%>goods/goods_doView.action?goods.goodsId=<%=goods.getGoodsId()%>">商品详情</a></li1>
			<li1><a href="<%=basePath%>goods/goods_doEdit.action?goods.goodsId=<%=goods.getGoodsId()%>">商品修改</a></li1>
			<li1><a href="<%=basePath%>gbatch/gbatch_doFind.action?goodsid=<%=goods.getGoodsId()%>">批次列表</a></li1>
			<li1><a href="<%=basePath%>oinfo/oinfo_doFind.action?goodsid=<%=goods.getGoodsId()%>">商品订单</a></li1>
			<li1><a href="<%=basePath%>comment/comment_doFind.action?goodsid=<%=goods.getGoodsId()%>">商品评论</a></li1>
			<li1><a href="<%=basePath%>gbatch/gbatch_beforedoAdd.action?goodsid=<%=goods.getGoodsId()%>">添加批次</a></li1>
			<li1><a href="<%=basePath%>goods/goods_beforedoAdd.action" target="rightFrame">添加商品</a></li1>
			<li1><a onClick="history.back(-1)">返回</a></li1>
		</ul>
	</div>
	
	
	<div class="formbody" id="formbody">

		<div id="usual1" name="usual1" class="usual">

			<div class="ibox" style="float:right;">
				<a style="float:right;margin-left:5px;margin-right:5px;" class="ibtn" href="<%=goods.getGoodsHtmlUrl()%>" target="_blank">
					<img src="<%=basePath%>images/iright.png" />查看该商品的HTML页面
				</a>
				<a style="float:right;margin-left:5px;margin-right:5px;" class="ibtn" href="#formtitle5">
					<img src="<%=basePath%>images/idown.png" " />去到底部
				</a>
			</div>
			<br>

			<div id="tab1" name="tab1" style="background: none repeat scroll 0% 0% rgb(255, 255, 255);">

				<div class="tabson">
					<div id="formtitle1" class="formtitle">
						<span>商品基本信息</span>
					</div>

					<div class="tabson1">
						<ul class="forminfo">
							<input name="goods.goodsId" id="goods.goodsId" type="hidden" value="<%=goods.getGoodsId()%>"</input>
							<li><label>商品名称<b>*</b></label><input type="text" value="<%=goods.getGoodsName()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>商品分类<b>*</b></label> <input type="text" value="<%=goods.getSort().getSort().getSortName()%>" class="dfinput1" readonly="true" style="width:80px;" /> <input type="text" value="<%=goods.getSort().getSortName()%>"
								class="dfinput1" readonly="true" style="width:80px;" /></li>
							<li><label>关 键 字<b>*</b></label><input type="text" value="<%=goods.getGoodsKeyWord()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>添加时间<b>*</b></label><input type="text" value="<%=goods.getGoodsAddDate()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>商品标签<b>*</b></label><input type="text" value="<%=goods.getGoodsTags()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>商品备注<b>*</b></label><input type="text" value="<%=goods.getGoodsRemark()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>商品重量<b>*</b></label><input type="text" value="<%=goods.getGoodsWeight()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>商品数量<b>*</b></label><input type="text" value="<%=goods.getNumTotal()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>商品库存<b>*</b></label><input type="text" value="<%=goods.getNumStock()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>商品单位<b>*</b></label><input type="text" value="<%=goods.getGoodsUnits()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
						</ul>
					</div>
					<div class="tabson1">
						<ul class="forminfo">
							<li><label>原 售 价<b>*</b></label><input type="text" value="<%=goods.getMoneyOld()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>优 惠 价<b>*</b></label><input type="text" value="<%=goods.getMoneyNew()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>包邮金额<b>*</b></label><input type="text" value="<%=goods.getMoneyLeast()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>邮 费<b>*</b></label><input type="text" value="<%=goods.getMoneyDeliver()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>检验日期<b>*</b></label><input type="text" value="<%=goods.getCheckDate()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>检验部门<b>*</b></label><input type="text" value="<%=goods.getCheckDepartment()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>检验序列<b>*</b></label><input type="text" value="<%=goods.getCheckSerial()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>检验状态<b>*</b></label><input type="text" value="<%=goods.getCheckResult()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>小号规格<b>*</b></label><input type="text" value="<%=goods.getGoodsSize2()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>大号规格<b>*</b></label><input type="text" value="<%=goods.getGoodsSize1()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							
						</ul>
					</div>
				</div>
				
				<div class="ibox1"></div>
				<div class="tabson">
					<div id="formtitle1" class="formtitle">
						<span>商品相关统计</span>
					</div>

					<div class="tabson1">
						<ul class="forminfo">
							<li><label>订 单 量<b>*</b></label><input type="text" value="<%=goods.getCountOrder()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>收 藏 量<b>*</b></label><input type="text" value="<%=goods.getCountCollect()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>购 物 车<b>*</b></label><input type="text" value="<%=goods.getCountCart()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
						</ul>
					</div>
					<div class="tabson1">
						<ul class="forminfo">
							<li><label>1星 评 价<b>*</b></label><input type="text" value="<%=goods.getCommentStar1()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>2星 评 价<b>*</b></label><input type="text" value="<%=goods.getCommentStar2()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>3星 评 价<b>*</b></label><input type="text" value="<%=goods.getCommentStar3()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>4星 评 价<b>*</b></label><input type="text" value="<%=goods.getCommentStar4()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
							<li><label>5星 评 价<b>*</b></label><input type="text" value="<%=goods.getCommentStar5()%>" class="dfinput1" readonly="true" style="width:320px;" /></li>
						</ul>
					</div>
				</div>
				
				<div class="ibox1"></div>
				<div class="tabson">
					<div id="formtitle2" class="formtitle">
						<span>移动端app首页商品轮播图片</span>
					</div>
					<div class="pho">
						<ul class="forminfo">
							<li><label style="width:60px;" class="phofont1">图片1<b>*</b></label> <label style="width:160px;" class="phofont1">轮播图</label></li>
							<li><i style="color:#878787;margin-left:-20px;">像素提示：约750*320</i></li>
							<li>
								<div id="localImage1">
									<img src="<%=goods.getImg1()%>" id="preview1" class="phoinside" />
								</div>
							</li>
						</ul>
					</div>
				</div>

				<div class="ibox"></div>
				<div class="tabson">
					<div id="formtitle2" class="formtitle">
						<span>移动端app首页展示图片</span>
					</div>
					<div class="pho">
						<ul class="forminfo">
							<li><label style="width:60px;" class="phofont1">图片2<b>*</b></label> <label style="width:160px;" class="phofont1">新品上市及热销榜单图片</label></li>
							<li><i style="color:#878787;margin-left:-20px;">像素提示：约120*120</i></li>
							<li>
								<div id="localImage2">
									<img src="<%=goods.getImg2()%>" id="preview2" class="phoinside" />
								</div>
							</li>
						</ul>
					</div>

					<div class="pho">
						<ul class="forminfo">
							<li><label style="width:60px;" class="phofont1">图片3<b>*</b></label> <label style="width:160px;" class="phofont1">分类左中展示图片</label></li>
							<li><i style="color:#878787;margin-left:-20px;">像素提示：约470*590</i></li>
							<li>
								<div id="localImage3">
									<img src="<%=goods.getImg3()%>" id="preview3" class="phoinside" />
								</div>
							</li>
						</ul>
					</div>

					<div class="pho">
						<ul class="forminfo">
							<li><label style="width:60px;" class="phofont1">图片4<b>*</b></label> <label style="width:160px;" class="phofont1">分类右上展示图片</label></li>
							<li><i style="color:#878787;margin-left:-20px;">像素提示：约590*300</i></li>
							<li>
								<div id="localImage4">
									<img src="<%=goods.getImg4()%>" id="preview4" class="phoinside" />
								</div>
							</li>
						</ul>
					</div>

					<div class="pho">
						<ul class="forminfo">
							<li><label style="width:60px;" class="phofont1">图片5<b>*</b></label> <label style="width:160px;" class="phofont1">分类右下展示图片</label></li>
							<li><i style="color:#878787;margin-left:-20px;">像素提示：约590*300</i></li>
							<li>
								<div id="localImage5">
									<img src="<%=goods.getImg5()%>" id="preview5" class="phoinside" />
								</div>
							</li>
						</ul>
					</div>
				</div>

				<div class="ibox"></div>
				<div class="tabson">
					<div id="formtitle2" class="formtitle">
						<span>移动端app非首页展示图片</span>
					</div>
					<div class="pho">
						<ul class="forminfo">
							<li><label style="width:60px;" class="phofont1">图片6<b>*</b></label> <label style="width:160px;" class="phofont1">详情页展示图片</label></li>
							<li><i style="color:#878787;margin-left:-20px;">像素提示：约750*750</i></li>
							<li>
								<div id="localImage6">
									<img src="<%=goods.getImg6()%>" id="preview6" class="phoinside" />
								</div>
							</li>
						</ul>
					</div>

					<div class="pho">
						<ul class="forminfo">
							<li><label style="width:60px;" class="phofont1">图片7<b>*</b></label> <label style="width:160px;" class="phofont1">搜索二级页面展示图片</label></li>
							<li><i style="color:#878787;margin-left:-20px;">像素提示：约390*390</i></li>
							<li>
								<div id="localImage7">
									<img src="<%=goods.getImg7()%>" id="preview7" class="phoinside" />
								</div>
							</li>
						</ul>
					</div>

					<div class="pho">
						<ul class="forminfo">
							<li><label style="width:60px;" class="phofont1">图片8<b>*</b></label> <label style="width:160px;" class="phofont1">购物车(收藏)展示图片</label></li>
							<li><i style="color:#878787;margin-left:-20px;">像素提示：约200*200</i></li>
							<li>
								<div id="localImage8">
									<img src="<%=goods.getImg8()%>" id="preview8" class="phoinside" />
								</div>
							</li>
						</ul>
					</div>
				</div>
				
				<div class="ibox"></div>
				<div class="tabson">
					<div id="formtitle3" class="formtitle">
						<span>网页图片信息</span>
					</div>
					<div class="pho">
						<ul class="forminfo">
							<li><label style="width:60px;" class="phofont1">图片9<b>*</b></label> <label style="width:160px;" class="phofont1">(必要)网页显示图片</label></li>
							<li><i style="color:#878787;margin-left:-20px;">像素提示：大于1024*519</i></li>
							<li>
								<div id="localImage9">
									<img src="<%=goods.getImg9()%>" id="preview9" class="phoinside" />
								</div>
							</li>
						</ul>
					</div>

					<div class="pho">
						<ul class="forminfo">
							<li><label style="width:60px;" class="phofont1">图片10<b>*</b></label> <label style="width:160px;" class="phofont1">(必要)网页显示图片</label></li>
							<li><i style="color:#878787;margin-left:-20px;">像素提示：大于1024*519</i></li>
							<li>
								<div id="localImage10">
									<img src="<%=goods.getImg10()%>" id="preview10" class="phoinside" />
								</div>
							</li>
						</ul>
					</div>

					<div class="pho">
						<ul class="forminfo">
							<li><label style="width:60px;" class="phofont1">图片11<b>*</b></label> <label style="width:160px;" class="phofont1">(必要)网页显示图片</label></li>
							<li><i style="color:#878787;margin-left:-20px;">像素提示：大于1024*519</i></li>
							<li>
								<div id="localImage11">
									<img src="<%=goods.getImg11()%>" id="preview11" class="phoinside" />
								</div>
							</li>
						</ul>
					</div>

					<div class="pho">
						<ul class="forminfo">
							<li><label style="width:60px;" class="phofont1">图片12<b>*</b></label> <label style="width:160px;" class="phofont1">(必要)网页显示图片</label></li>
							<li><i style="color:#878787;margin-left:-20px;">像素提示：大于1024*519</i></li>
							<li>
								<div id="localImage12">
									<img src="<%=goods.getImg12()%>" id="preview12" class="phoinside" />
								</div>
							</li>
						</ul>
					</div>

					<%if(goods.getImg13()!=null&&!goods.getImg13().equals("")) {%>
					<div class="pho">
						<ul class="forminfo">
							<li><label style="width:60px;" class="phofont1">图片13<b>*</b></label> <label style="width:160px;" class="phofont1">网页显示图片</label></li>
							<li><i style="color:#878787;margin-left:-20px;">像素提示：大于1024*519</i></li>
							<li>
								<div id="localImage13">
									<img src="<%=goods.getImg13()%>" id="preview13" class="phoinside" />
								</div>
							</li>
						</ul>
					</div>

					<%}if(goods.getImg14()!=null&&!goods.getImg14().equals("")) {%>
					<div class="pho">
						<ul class="forminfo">
							<li><label style="width:60px;" class="phofont1">图片14<b>*</b></label> <label style="width:160px;" class="phofont1">网页显示图片</label></li>
							<li><i style="color:#878787;margin-left:-20px;">像素提示：大于1024*519</i></li>
							<li>
								<div id="localImage14">
									<img src="<%=goods.getImg14()%>" id="preview14" class="phoinside" />
								</div>
							</li>
						</ul>
					</div>
					
					<%}if(goods.getImg15()!=null&&!goods.getImg15().equals("")) {%>
					<div class="pho">
						<ul class="forminfo">
							<li><label style="width:60px;" class="phofont1">图片15<b>*</b></label> <label style="width:160px;" class="phofont1">网页显示图片</label></li>
							<li><i style="color:#878787;margin-left:-20px;">像素提示：大于1024*519</i></li>
							<li>
								<div id="localImage15">
									<img src="<%=goods.getImg15()%>" id="preview15" class="phoinside" />
								</div>
							</li>
						</ul>
					</div>
					
					<%}if(goods.getImg16()!=null&&!goods.getImg16().equals("")) {%>
					<div class="pho">
						<ul class="forminfo">
							<li><label style="width:60px;" class="phofont1">图片16<b>*</b></label> <label style="width:160px;" class="phofont1">网页显示图片</label></li>
							<li><i style="color:#878787;margin-left:-20px;">像素提示：大于1024*519</i></li>
							<li>
								<div id="localImage16">
									<img src="<%=goods.getImg16()%>" id="preview16" class="phoinside" />
								</div>
							</li>
						</ul>
					</div>
					<%}%>
				</div>

				<div class="ibox1"></div>
				<div class="tabson">
					<div id="formtitle4" class="formtitle">
						<span>网页文字信息</span>
					</div>


					<div style="float:left;">
						<label style="width: 85px;line-height: 34px;display: block;float: left;">商品文字信息<b color="#d70101;">*</b></label>
					</div>
					<div style="float:left;width: 700px;border-radius:8px;margin:-5px 10px 20px 10px;">
						<textarea readonly="true" style="width:700px;height:100px;visibility:hidden;" name="create5"><%=goods.getGoodsDescription()%> </textarea>
					</div>
					<div style="float:left;">
						<label style="width: 280px;line-height: 34px;display: block;float: left;color:#C0C0C0;">纯文本输入，字数不限</label>
					</div>
					<div class="ibox1"></div>

				</div>

			</div>

			<div class="ibox"></div>
			<div>
				<li style="position:absolute;right:50%;translateX(-50%);"><label>&nbsp;</label> <input name="button" type="button" class="btn" value="数据修改"
					onClick="javascript:location.href='<%=basePath%>goods/goods_doEdit.action?goods.goodsId=<%=goods.getGoodsId()%>'" /> <label>&nbsp;</label> <input name="button" type="button" class="btn" value="返回上级" onClick="history.back(-1)" /></li>
			</div>
			<div id="formtitle5" class="ibox" style="float:right;">
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
