<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="bean.OInfo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path 	= 	request.getContextPath();
	String basePath =	request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	OInfo  oinfo	= 	(OInfo)request.getAttribute("oinfo");
    String keyword	=	(String)request.getAttribute("keyword");  //一共多少记录
    int totalPage 	=   (Integer)request.getAttribute("totalPage");  //一共多少页
    int totalRecord =   (Integer)request.getAttribute("totalRecord");  //一共多少记录
    int firstPage 	=  	(Integer)request.getAttribute("firstPage"); //当前页
    int currentPage =  	(Integer)request.getAttribute("currentPage"); //当前页
    int lastPage	=	(Integer)request.getAttribute("lastPage");  //一共多少记录
    int PAGE_SIZE	=	(Integer)request.getAttribute("PAGE_SIZE");  //一共多少记录
    Object user		=	session.getAttribute("user");
    if(user==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "admin/admin_doLogin.action';</script>");
    }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>orderinfo.jsp</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/select-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>editor/kindeditor.js"></script>
<script type="text/javascript">
	KE.show({
		id : 'content7',
		cssPath : './index.css'
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
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="<%=basePath%>mainindex.jsp">首页</a></li>
			<li><a href="<%=basePath%>order/order_doFind.action">订单列表</a></li>
		</ul>
	</div>

	<div class="ibox"></div>
	<div class="ibox"></div>
	<div class="formbody" style="float:left;border:1px;margin-bottom: 35px;margin-left: 35px;">
		<ul class="forminfo">
			<li><label>订222编号<b>*</b></label> <input type="text" class="dfinput" value='<%=oinfo.getOrder().getCustomer().getCustomerId()%>' readonly /></li>
			<li><label>订单编号<b>*</b></label> <input type="text" class="dfinput" value='<%=oinfo.getOrder().getOrderId()%>' readonly /></li>
			<li><label>商 品 名<b>*</b></label> <input type="text" class="dfinput" value='<%=oinfo.getGoods().getGoodsName()%>' readonly /></li>
			<li><label>商品批次<b>*</b></label> <input type="text" class="dfinput" value='<%=oinfo.getGBatch().getBatchId()%>' readonly /></li>
			<li><label>商品单价<b>*</b></label> <input type="text" class="dfinput" value='<%=oinfo.getGoods().getGoodsMoneyRetail()%>' readonly /></li>
			<li><label>商品数量<b>*</b></label> <input type="text" class="dfinput" value='<%=oinfo.getOcNum()%>' readonly /></li>
			<li><label>商品运费<b>*</b></label> <input type="text" class="dfinput" value='<%=oinfo.getOcMoneyDeliver()%>' readonly /></li>
			<li><label>商品金额<b>*</b></label> <input type="text" class="dfinput" value='<%=oinfo.getOcMoneyTotal()%>' readonly /></li>
			<li><label>订单日期<b>*</b></label> <input type="text" class="dfinput" value='<%=oinfo.getOcDate()%>' readonly /></li>
		</ul>
	</div>

</body>
</html>
