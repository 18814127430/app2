<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>systemlog.jsp</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".click").click(function() {
			$(".tip").fadeIn(200);
		});

		$(".tiptop a").click(function() {
			$(".tip").fadeOut(200);
		});

		$(".sure").click(function() {
			$(".tip").fadeOut(100);
		});

		$(".cancel").click(function() {
			$(".tip").fadeOut(100);
		});

		$(".tablelink1").click(function() {
			$(".tip").fadeIn(200);
		});

	});
</script>
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="mainindex.jsp">首页</a></li>
			<li><a href="systemlog.jsp">系统日志信息</a></li>
		</ul>
	</div>
	<div class="rightinfo">
		<div class="tools">
			<ul class="toolbar">
				<li class="click"><span><img src="images/t07.png" /></span>打印</li>
			</ul>
			<ul class="toolbar1">
				<li><input id="id1" value="" placeholder="请输入关键字" type="text" class="findinput" /></li>
				<li class="click"><span></span><span><img src="images/t06.png" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></li>
			</ul>
		</div>
		<table class="tablelist">
			<thead>
				<tr>
					<th width="6%"><input name="" type="checkbox" value="" checked="checked" /></th>
					<th width="16%">时间<i class="sort">
							<img src="images/px.gif" />
						</i></th>
					<th width="12%">使用者</th>
					<th width="42%">事件</th>
					<th width="13%">使用者等级</th>
					<th width="11%">操作</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input name="" type="checkbox" value="" /></td>
					<td>2013-09-09 15:05</td>
					<td>alj</td>
					<td>录入数据</td>
					<td>general</td>
					<td><a href="#" class="tablelink1"> 查看详情</a></td>
				</tr>
				<tr>
					<td><input name="" type="checkbox" value="" /></td>
					<td>2013-09-09 15:05</td>
					<td>alj</td>
					<td>录入数据</td>
					<td>general</td>
					<td><a href="#" class="tablelink1"> 查看详情</a></td>
				</tr>
				<tr>
					<td><input name="" type="checkbox" value="" /></td>
					<td>2013-09-09 15:05</td>
					<td>alj</td>
					<td>录入数据</td>
					<td>general</td>
					<td><a href="#" class="tablelink1"> 查看详情</a></td>
				</tr>
				<tr>
					<td><input name="" type="checkbox" value="" /></td>
					<td>2013-09-09 15:05</td>
					<td>alj</td>
					<td>录入数据</td>
					<td>general</td>
					<td><a href="#" class="tablelink1"> 查看详情</a></td>
				</tr>
				<tr>
					<td><input name="" type="checkbox" value="" /></td>
					<td>2013-09-09 15:05</td>
					<td>alj</td>
					<td>录入数据</td>
					<td>general</td>
					<td><a href="#" class="tablelink1"> 查看详情</a></td>
				</tr>
				<tr>
					<td><input name="" type="checkbox" value="" /></td>
					<td>2013-09-09 15:05</td>
					<td>alj</td>
					<td>录入数据</td>
					<td>general</td>
					<td><a href="#" class="tablelink1"> 查看详情</a></td>
				</tr>
				<tr>
					<td><input name="" type="checkbox" value="" /></td>
					<td>2013-09-09 15:05</td>
					<td>alj</td>
					<td>录入数据</td>
					<td>general</td>
					<td><a href="#" class="tablelink1"> 查看详情</a></td>
				</tr>
				<tr>
					<td><input name="" type="checkbox" value="" /></td>
					<td>2013-09-09 15:05</td>
					<td>alj</td>
					<td>录入数据</td>
					<td>general</td>
					<td><a href="#" class="tablelink1"> 查看详情</a></td>
				</tr>
				<tr>
					<td><input name="" type="checkbox" value="" /></td>
					<td>2013-09-09 15:05</td>
					<td>alj</td>
					<td>录入数据</td>
					<td>general</td>
					<td><a href="#" class="tablelink1"> 查看详情</a></td>
				</tr>
				<tr>
					<td><input name="" type="checkbox" value="" /></td>
					<td>2013-09-09 15:05</td>
					<td>alj</td>
					<td>录入数据</td>
					<td>general</td>
					<td><a href="#" class="tablelink1"> 查看详情</a></td>
				</tr>
				<tr>
					<td><input name="" type="checkbox" value="" /></td>
					<td>2013-09-09 15:05</td>
					<td>alj</td>
					<td>录入数据</td>
					<td>general</td>
					<td><a href="#" class="tablelink1"> 查看详情</a></td>
				</tr>
				<tr>
					<td><input name="" type="checkbox" value="" /></td>
					<td>2013-09-09 15:05</td>
					<td>alj</td>
					<td>录入数据</td>
					<td>general</td>
					<td><a href="#" class="tablelink1"> 查看详情</a></td>
				</tr>
				<tr>
					<td><input name="" type="checkbox" value="" /></td>
					<td>2013-09-09 15:05</td>
					<td>alj</td>
					<td>录入数据</td>
					<td>general</td>
					<td><a href="#" class="tablelink1"> 查看详情</a></td>
				</tr>
			</tbody>
		</table>
		<div class="pagin">
			<div class="message">
				共
				<i class="blue">1256</i>
				条记录，当前显示第&nbsp;
				<i class="blue">2&nbsp;</i>
				页
			</div>
			<ul class="paginList">
				<li class="paginItem"><a href="javascript:;">
						<span class="pagepre"></span>
					</a></li>
				<li class="paginItem"><a href="javascript:;">1</a></li>
				<li class="paginItem current"><a href="javascript:;">2</a></li>
				<li class="paginItem"><a href="javascript:;">3</a></li>
				<li class="paginItem"><a href="javascript:;">4</a></li>
				<li class="paginItem"><a href="javascript:;">5</a></li>
				<li class="paginItem more"><a href="javascript:;">...</a></li>
				<li class="paginItem"><a href="javascript:;">10</a></li>
				<li class="paginItem"><a href="javascript:;">
						<span class="pagenxt"></span>
					</a></li>
			</ul>
		</div>
		<div class="tip">
			<div class="tiptop">
				<span>日志详情</span>
				<a></a>
			</div>
			<div class="tiplog">
				<textarea class="textarea" readonly="readonly">[2016-04-30 16:52:31;750,     0]ERROR(Start_Server.java:91) - This is error message.
[2016-04-30 16:52:45;684,     0]ERROR(Start_Server.java:91) - This is error message.
[2016-04-30 16:52:56;693,     0]ERROR(Start_Server.java:91) - This is error message.</textarea>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>