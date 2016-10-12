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
	String keyword	=	(String)request.getAttribute("keyword");  //一共多少记录
	int totalPage 	=   (Integer)request.getAttribute("totalPage");  //一共多少页
	int totalRecord =   (Integer)request.getAttribute("totalRecord");  //一共多少记录
	int firstPage 	=  	(Integer)request.getAttribute("firstPage"); //当前页
	int currentPage =  	(Integer)request.getAttribute("currentPage"); //当前页
	int lastPage	=	(Integer)request.getAttribute("lastPage");  //一共多少记录
	int PAGE_SIZE	=	(Integer)request.getAttribute("PAGE_SIZE");  //一共多少记录
	int  goodsid	=	(Integer)request.getAttribute("goodsid");
	List<GBatch> list = 	(List<GBatch>)request.getAttribute("list");
	if (user == null) {
		response.getWriter().println("<script>top.location.href='" + basePath+ "admin/admin_doLogin.action';</script>");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看商品批次列表</title>


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
	function checkDelete() {
		return confirm("确定要删除？");
	}
</script>


<script type="text/javascript">
	function edit() {
		var check = document.getElementsByName("check");
		var len = check.length;
		var editid = 0;
		var flag=0;
		for (var i = 0; i < len; i++) {
			if (check[i].checked) {
				editid= check[i].value;
				flag++;
			}
		}
 		if(flag==0){
 			window.alert("请选择要修改的对象");
		}else if(flag==1){
			if(confirm("确定要修改？")){
 				window.location.href="<%=basePath%>gbatch/gbatch_doEdit.action?gbatch.batchId="+editid+"&goodsid=<%=goodsid%>";
			}
		}
 		else{
 			window.alert("一次只能修改一个对象");
		}
	}
</script>


<script type="text/javascript">
	function deleteAll() {
		var check = document.getElementsByName("check");
		var len = check.length;
		var deletelist = "";
		for (var i = 0; i < len; i++) {
			if (check[i].checked) {
				deletelist += check[i].value + ",";
			}
		}
 		if(deletelist==""){
 			window.alert("请选择要删除的对象？");
		}
 		else{
 			if(confirm("确定要删除？")){
 				window.location.href="<%=basePath%>gbatch/gbatch_doDeleteAll.action?deletelist="+deletelist+"&currentPage=<%=currentPage%>&goodsid=<%=goodsid%>
	";
			}
		}
	}
</script>



<script language="JavaScript" type="text/javascript">
	var flag = 1;
	function selectAll() {
		var check = document.getElementsByName("check");
		if (flag == 1) {
			for (var i = 0; i < check.length; i++)
				check[i].checked = true;
			flag = 0;
		} else {
			for (var i = 0; i < check.length; i++)
				check[i].checked = false;
			flag = 1;
		}
	}
</script>


</head>

<body>
	<form action="<%=basePath%>gbatch/gbatch_doFindByGoodsId.action?goodsid=<%=goodsid%>" name="gbatchlistForm" id="gbatchlistForm" method="post">
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="<%=basePath%>mainindex.jsp">首页</a></li>
				<li><a href="<%=basePath%>gbatch/gbatch_doFind.action" target="rightFrame">商品批次列表</a></li>
				<li><a href="" target="rightFrame">商品批次查看</a></li>
			</ul>
		</div>



		<div class="rightinfo">
			<div class="tools">
				<ul class="toolbar">
					<li onclick="javascript:edit();"><span><img src="<%=basePath%>images/t02.png" /></span>修改</li>
					<li onclick="javascript:deleteAll();"><span><img src="<%=basePath%>images/t03.png" /></span>删除</li>
					<li onclick="history.back(-1);"><span><img src="<%=basePath%>images/t03.png" /></span>返回</li>
				</ul>

				<ul class="toolbar1">
					<li><input type="text" name="keyword" value="<%=keyword%>" placeholder="请输入关键字" class="findinput" /></li>
					<input type=hidden name=currentPage value="1" />
					<input type=hidden name=goodsid value="<%=goodsid %>" />
					<li><span onclick="document.getElementById('gbatchlistForm').submit();">search<img src="<%=basePath%>images/t06.png" /></span></li>
				</ul>
			</div>


			<table class="tablelist">
				<thead>
					<tr>
						<th style="width:40px"><input name="checkall" type="checkbox" value="" onClick="selectAll();" /></th>
						<th style="width:60px">序号</th>
						<th>批次编号<i class="sort">
								<img src="<%=basePath%>images/px.gif" />
							</i></th>
						<th>生产商</th>
						<th>销售商</th>
						<th>库存</th>
						<th>操作</th>
					</tr>
				</thead>

				<tbody>

					<%
						int startIndex = (currentPage - 1) * PAGE_SIZE;//计算起始序号
						for (int i = 0; i < list.size(); i++) {
						int currentIndex = startIndex + i + 1; //当前记录的序号
						GBatch gbatch = list.get(i); //获取到对象
					%>
					<tr>
						<td><input name="check" type="checkbox" value="<%=gbatch.getBatchId()%>"></input></td>
						<td><div align="center"><%=currentIndex%></div></td>
						<td><%=gbatch.getBatchId()%></td>
						<td><%=gbatch.getCompanyByProducerId().getCompanyName()%></td>
						<td><%=gbatch.getCompanyBySellerId().getCompanyName()%></td>
						<td><%=gbatch.getBatchNumStock()%>
							<p>
								该批数量：<%=gbatch.getBatchNumTotal()%></p></td>
						<td><a href="<%=basePath%>gbatch/gbatch_doView.action?gbatch.batchId=<%=gbatch.getBatchId()%>" style="cursor:hand;" class="tablelink">详情</a> <a
								href="<%=basePath%>gbatch/gbatch_doDelete.action?gbatch.batchId=<%=gbatch.getBatchId()%>&currentPage=<%=currentPage%>" onClick="return checkDelete()" style="cursor:hand;" class="tablelink1">删除</a></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>


			<div class="pagin">
				<div class="message">
					共
					<i class="blue"><%=totalRecord%></i>
					条记录，当前显示第
					<i class="blue"><%=currentPage%></i>
					/<%=totalPage%>页
				</div>
				<ul class="paginList">
					<%
						if(firstPage-PAGE_SIZE>=1) {
					%>
					<li class="paginItem"><a href="<%=basePath%>gbatch/gbatch_doFindByGoodsId.action?goodsid=<%=goodsid%>&currentPage=<%=firstPage-PAGE_SIZE%>">
							<span class="pagepre"></span>
						</a></li>
					<%
						}
					%>

					<%
						for (int i = firstPage; i <=lastPage; i++) {
																				if(i==currentPage){
					%>

					<li class="paginItem current"><a href="<%=basePath%>gbatch/gbatch_doFindByGoodsId.action?goodsid=<%=goodsid%>&currentPage=<%=i%>"><%=i%></a></li>

					<%
						continue;}
					%>

					<li class="paginItem"><a href="<%=basePath%>gbatch/gbatch_doFindByGoodsId.action?goodsid=<%=goodsid%>&currentPage=<%=i%>"><%=i%></a></li>

					<%
						}
					%>
					<%
						if(lastPage<totalPage){
					%>
					<li class="paginItem"><a href="<%=basePath%>gbatch/gbatch_doFindByGoodsId.action?goodsid=<%=goodsid%>&currentPage=<%=firstPage+PAGE_SIZE%>">
							<span class="pagenxt"></span>
						</a></li>
					<%
						}
					%>
				</ul>
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
