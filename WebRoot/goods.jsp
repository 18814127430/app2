<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>goods.jsp</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
</head>

<body>
<div class="place"> <span>当前位置：</span>
  <ul class="placeul">
    <li><a href="mainindex.jsp">首页</a></li>
    <li><a href="goods.jsp">产品</a></li>
  </ul>
</div>
<div class="mainindex">
  <div class="welinfo"> <span><img src="images/sun.png" alt="天气" /></span> <b>Admin您好，欢迎使用</b> <a href="http://www.baidu.com/">信息管理系统</a> </div>
  <ul class="seachform">
    <li>
      <label>产品查询</label>
      <input name="" type="text"  id="id1" value="" placeholder="请输入产品名或者编号" class="scinput" />
    </li>
    <li>
      <label>&nbsp;</label>
      <input id="id1" type="button" class="scbtn" value="查询"/>
    </li>
  </ul>
  <div class="xline"></div>
  <ul class="iconlist">
    <li><img src="images/goods1.png" />
      <p> <a href="goodslist.jsp">产品列表</a> </p>
    </li>
    <li><img src="images/goods3.png" />
      <p> <a href="goodsadd.jsp">产品添加</a> </p>
    </li>
  </ul>
  <div class="ibox"></div>
  <div class="ibox"></div>
  <div class="ibox"></div>
  <div class="ibox"></div>
  <div class="ibox"></div>
  <div class="ibox"></div>
  <div class="ibox"></div>
  <div class="xline"></div>
  <div class="uimakerinfo"> <b>查看APP网站使用指南和相关信息，联系制作团队，您可以点击来源：<a href="http://www.baidu.com" target="_blank">Alfred</a></b> </div>
  <ul class="umlist">
    <li><a href="helplist.jsp">如何发布文章</a></li>
    <li><a href="helplist.jsp">如何访问网站</a></li>
    <li><a href="helplist.jsp">如何管理广告</a></li>
    <li><a href="admminlist.jsp">管理员权限设置</a></li>
    <li><a href="systemerror.jsp">系统设置</a></li>
  </ul>
</div>
</body>
</html>
