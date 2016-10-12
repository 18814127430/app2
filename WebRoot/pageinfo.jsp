<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bean.Page"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	Object user = session.getAttribute("user");
	List<Page> list = 	(List<Page>)request.getAttribute("list");
	Page page1=list.get(0);
	Page page2=list.get(1);
	Page page3=list.get(2);
	Page page4=list.get(3);
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
<title>修改轮播图</title>



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



</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li1><a href="<%=basePath%>mainindex.jsp">首页</a></li1>
			<li1><a style="color:blue;" href="<%=basePath%>page/page_doView.action">商品轮播图详情</a></li1>
			<li1><a href="<%=basePath%>page/page_beforedoAdd.action">商品轮播图添加</a></li1>
			<li1><a href="<%=basePath%>pageedit1.jsp">商品轮播图选择</a></li1>
			<li1><a onClick="history.back(-1)">返回</a></li1>
		</ul>
	</div>
	<div class="formbody" id="formbody">

		<div id="usual1" name="usual1" class="usual">

			<div style="margin-left:100px;" class="tabson">

				<div style="width: 440px;height:310px;border-color: green;border-style: inset;border-width: 1px;border-radius:18px;margin:20px 20px 20px 20px;float: left;background: none repeat scroll 0% 0% rgb(243, 249, 240);">
					<ul style="float:left;width: 400px;height:280px;margin:10px 10px 10px 10px;">
						<li style="width:400px;margin:5px 10px 5px 10px;"><label style="margin-left:20px;width:380px;font-size: 13px;color: #03408f;font-family: 微软雅黑;">轮播图1:请选择分辨率约为750*320的图片</label></li>

						<li style="width:400px;margin:5px 10px 5px 10px;clear: both;color: #D6D6D6;">
							<div id="localImage1">
								<img src="<%=page1.getPageImg()%>" style="width: 380px;height: 200px;border-radius:18px;diplay: none;margin:10px 10px 10px 10px;" />
							</div>
						</li>
						<li style="width:280px;margin:10px 70px 10px 70px;"><label style="margin-left:90px;width:280px;">商品名:<%=page1.getGoods().getGoodsName()%></label></li>
						<li style="width:280px;margin:10px 70px 10px 70px;"><label style="margin-left:90px;width:280px;">商品ID:<%=page1.getGoods().getGoodsId()%></label></li>
					</ul>
				</div>
				<div style="width: 440px;height:310px;border-color: green;border-style: inset;border-width: 1px;border-radius:18px;margin:20px 20px 20px 20px;float: left;background: none repeat scroll 0% 0% rgb(243, 249, 240);">
					<ul style="float:left;width: 400px;height:280px;margin:10px 10px 10px 10px;">
						<li style="width:400px;margin:5px 10px 5px 10px;"><label style="margin-left:20px;width:380px;font-size: 13px;color: #03408f;font-family: 微软雅黑;">轮播图2:请选择分辨率约为750*320的图片</label></li>

						<li style="width:400px;margin:5px 10px 5px 10px;clear: both;color: #D6D6D6;">
							<div id="localImage2">
								<img src="<%=page2.getPageImg()%>" style="width: 380px;height: 200px;border-radius:18px;diplay: none;margin:10px 10px 10px 10px;" />
							</div>
						</li>
						<li style="width:280px;margin:10px 70px 10px 70px;"><label style="margin-left:90px;width:280px;">商品名:<%=page2.getGoods().getGoodsName()%></label></li>
						<li style="width:280px;margin:10px 70px 10px 70px;"><label style="margin-left:90px;width:280px;">商品ID:<%=page2.getGoods().getGoodsId()%></label></li>
					</ul>
				</div>
				<div style="width: 440px;height:310px;border-color: green;border-style: inset;border-width: 1px;border-radius:18px;margin:20px 20px 20px 20px;float: left;background: none repeat scroll 0% 0% rgb(243, 249, 240);">
					<ul style="float:left;width: 400px;height:280px;margin:10px 10px 10px 10px;">
						<li style="width:400px;margin:5px 10px 5px 10px;"><label style="margin-left:20px;width:380px;font-size: 13px;color: #03408f;font-family: 微软雅黑;">轮播图3:请选择分辨率约为750*320的图片</label></li>

						<li style="width:400px;margin:5px 10px 5px 10px;clear: both;color: #D6D6D6;">
							<div id="localImage3">
								<img src="<%=page3.getPageImg()%>" style="width: 380px;height: 200px;border-radius:18px;diplay: none;margin:10px 10px 10px 10px;" />
							</div>
						</li>
						<li style="width:280px;margin:10px 70px 10px 70px;"><label style="margin-left:90px;width:280px;">商品名:<%=page3.getGoods().getGoodsName()%></label></li>
						<li style="width:280px;margin:10px 70px 10px 70px;"><label style="margin-left:90px;width:280px;">商品ID:<%=page3.getGoods().getGoodsId()%></label></li>
					</ul>
				</div>
				<div style="width: 440px;height:310px;border-color: green;border-style: inset;border-width: 1px;border-radius:18px;margin:20px 20px 20px 20px;float: left;background: none repeat scroll 0% 0% rgb(243, 249, 240);">
					<ul style="float:left;width: 400px;height:280px;margin:10px 10px 10px 10px;">
						<li style="width:400px;margin:5px 10px 5px 10px;"><label style="margin-left:20px;width:380px;font-size: 13px;color: #03408f;font-family: 微软雅黑;">轮播图4:请选择分辨率约为750*320的图片</label></li>

						<li style="width:400px;margin:5px 10px 5px 10px;clear: both;color: #D6D6D6;">
							<div id="localImage4">
								<img src="<%=page4.getPageImg()%>" style="width: 380px;height: 200px;border-radius:18px;diplay: none;margin:10px 10px 10px 10px;" />
							</div>
						</li>
						<li style="width:280px;margin:10px 70px 10px 70px;"><label style="margin-left:90px;width:280px;">商品名:<%=page4.getGoods().getGoodsName() %></label></li>
						<li style="width:280px;margin:10px 70px 10px 70px;"><label style="margin-left:90px;width:280px;">商品ID:<%=page4.getGoods().getGoodsId()%></label></li>
					</ul>
				</div>
				<div class="ibox1"></div>

			</div>

		</div>

		<div class="ibox"></div>
		<div>
			<li style="position:absolute;right:50%;translateX(-50%);"><label>&nbsp;</label> <a href="<%=basePath%>pageedit1.jsp"><input name="button" type="submit" class="btn" value="修改数据" /></a></li>
		</div>
		<div id="buttom" class="ibox" style="float:right;">
			<a class="ibtn" href="#formbody">
				<img src="<%=basePath%>images/iup.png" style="margin-top: 2px; margin-left: -10px;" />返回顶部
			</a>
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
