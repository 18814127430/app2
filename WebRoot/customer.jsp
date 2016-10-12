<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bean.Admin"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	Admin user		=	(Admin)session.getAttribute("user");
    if(user==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "customer/customer_doLogin.action';</script>");
    }
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>顾客首页</title>
<link href="<%=basePath %>css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
</head>

<body>
<form action="<%=basePath%>/address/address_doFind.action" name="customerForm" id="customerForm" method="post">
<div class="place"> <span>当前位置：</span>
  <ul class="placeul">
    <li><a href="<%=basePath %>mainindex.jsp">首页</a></li>
    <li><a href="">顾客</a></li>
  </ul>
</div>
<div class="mainindex">
  <div class="welinfo"> <span><img src="images/sun.png" alt="天气" /></span> <b>Customer您好，欢迎使用</b> <a href="">信息管理系统</a> </div>
  <ul class="seachform">
    <li>
      <label>顾客综合查询</label>
      <input type="text" name="keyword" id="keyword" value="" placeholder="请输入关键字" class="scinput" />
    </li>
    <li>
      <label>&nbsp;</label>
      <input  type="submit" name="button" class="scbtn" value="查询"/>
    </li>
  </ul>
  
  <div class="xline"></div>
  <ul class="iconlist">
    <li><img src="<%=basePath %>images/customer1.png" />
      <p> <a href="<%=basePath %>customer/customer_doFind.action">顾客列表</a> </p>
    </li>
    <li><img src="<%=basePath %>images/customer3.png" />
      <p> <a href="<%=basePath %>customeradd.jsp">顾客添加</a> </p>
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
    <li><a href="<%=basePath %>helplist.jsp">如何发布文章</a></li>
    <li><a href="<%=basePath %>helplist.jsp">如何访问网站</a></li>
    <li><a href="<%=basePath %>helplist.jsp">如何管理广告</a></li>
    <li><a href="<%=basePath %>customer/customer_doFind.action">管理员权限设置</a></li>
    <li><a href="<%=basePath %>systemerror.jsp">系统设置</a></li>
  </ul>
</div>
</body>
</html>
