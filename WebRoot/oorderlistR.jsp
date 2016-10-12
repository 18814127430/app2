<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="bean.OOrder"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path 	= 	request.getContextPath();
	String basePath =	request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	List<OOrder> list1= 	(List<OOrder>)request.getAttribute("list1");
	List<String> list2= 	(List<String>)request.getAttribute("list2");
    int totalPage 	=   (Integer)request.getAttribute("totalPage");  //一共多少页
    int totalRecord =   (Integer)request.getAttribute("totalRecord");  //一共多少记录
    int firstPage 	=  	(Integer)request.getAttribute("firstPage"); //当前页
    int currentPage =  	(Integer)request.getAttribute("currentPage"); //当前页
    int lastPage	=	(Integer)request.getAttribute("lastPage");  //一共多少记录
    int PAGE_SIZE	=	(Integer)request.getAttribute("PAGE_SIZE");  //一共多少记录
    Object user		=	session.getAttribute("user");
    if(user==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "';</script>");
    }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>待退款订单</title>


<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>



</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
		<li1><a href="<%=basePath%>mainindex.jsp">首页</a></li1>
				<li1><a href="<%=basePath%>oorder.jsp">订单查找</a></li1>
				<li1><a href="<%=basePath%>oorder/oorder_doFind.action">订单列表</a></li1>
				<li1><a href="<%=basePath%>oorder/oorder_doFind_S.action">待发货订单</a></li1>
				<li1><a style="color:blue;" href="<%=basePath%>oorder/oorder_doFind_R.action">待退款订单</a></li1>
				<li1><a onClick="history.back(-1)">返回</a></li1>
		</ul>
	</div>

	<div class="formbody" id="formbody">
		<div id="usual1" name="usual1" class="usual">
			<div id="tab1" name="tab1" style="background: none repeat scroll 0% 0% rgb(255, 255, 255);">
				<div class="tabson">
					<%
						int startIndex = (currentPage - 1) * PAGE_SIZE;//计算起始序号
						for (int i = 0; i < list1.size(); i++) {
						int currentIndex = startIndex + i + 1; //当前记录的序号
						OOrder oorder = list1.get(i); //获取到对象
						String imgpath=list2.get(i);
					%>

					<div style="width: 300px;height:630px;border-color: white;border-style: hidden;border-width: 1px;border-radius:18px;margin:20px 20px 20px 20px;float: left;background: none repeat scroll 0% 0% rgb(243, 249, 240);">
						<ul style="float:left;width: 260px;height:600px;margin:10px 10px 10px 10px;">
							<%if(oorder.getStatusOrder()==2&&oorder.getStatusSend()==2&&oorder.getStatusPay()==3) {%>
							<li style="width:260px;margin:5px 30px 5px 30px;"><label style="margin-left:60px;width:240px;font-size: 13px;color: #03408f;font-family: 微软雅黑;">待退款</label></li>
							<%} %>
							<li style="width:260px;margin:5px 10px 5px 10px;clear: both;color: #D6D6D6;">
								<div id="localImage1">
									<img src="<%=imgpath%>" id="preview1" style="width: 240px;height: 200px;border-radius:18px;diplay: none;margin:10px 10px 10px 10px;" />
								</div>
							</li>

							<li style="width:260px;margin:5px 10px 5px 10px;"><label style="margin-left:20px;width:240px;font-size: 13px;color: #03408f;font-family: 微软雅黑;">NO:<%=oorder.getOrderSerial()%></label></li>
							<li style="width:260px;margin:5px 10px 5px 10px;"><label style="margin-left:20px;width:240px;font-size: 13px;color: #03408f;font-family: 微软雅黑;">订单标题:<%=oorder.getOrderSubject()%></label></li>

							<%
								String[] list3 = oorder.getOrderBody().split(",");
								int size = list3.length;
								if(oorder.getOrderBody()==null||oorder.getOrderBody().equals("")){
									size=0;
								}
								for (int j = 0; j < size; j++) {
							%>
							<li style="width:260px;margin:5px 10px 5px 10px;"><label style="margin-left:20px;width:240px;font-size: 13px;color: #03408f;font-family: 微软雅黑;">商品<%=j+1%>:<%=list3[j]%></label></li>
							<%
								}
							%>

							<li style="width:260px;margin:5px 10px 5px 10px;"><label style="margin-left:20px;width:240px;font-size: 13px;color: #03408f;font-family: 微软雅黑;">订单留言:<%=oorder.getOrderRemark()%></label></li>
							<li style="width:260px;margin:5px 10px 5px 10px;"><label style="margin-left:20px;width:240px;font-size: 13px;color: #03408f;font-family: 微软雅黑;">下单时间:<%=oorder.getOrderDate()%></label></li>
							<li style="width:260px;margin:5px 10px 5px 10px;"><label style="margin-left:20px;width:240px;font-size: 13px;color: #03408f;font-family: 微软雅黑;">订单金额:<%=oorder.getMoneyTotal()%></label></li>
							<li style="width:260px;margin:5px 10px 5px 10px;"><label style="margin-left:20px;width:240px;font-size: 13px;color: #03408f;font-family: 微软雅黑;">订单邮费:<%=oorder.getMoneyDeliver()%></label></li>
							<li style="width:260px;margin:5px 10px 5px 10px;"><label style="margin-left:20px;width:240px;font-size: 13px;color: #03408f;font-family: 微软雅黑;">收货姓名:<%=oorder.getAdrName()%></label></li>
							<li style="width:260px;margin:5px 10px 5px 10px;"><label style="margin-left:20px;width:240px;font-size: 13px;color: #03408f;font-family: 微软雅黑;">收货电话:<%=oorder.getAdrPhone()%></label></li>
							<li style="width:260px;margin:5px 10px 5px 10px;"><label style="margin-left:20px;width:240px;font-size: 13px;color: #03408f;font-family: 微软雅黑;">收货省份:<%=oorder.getAdrProvince()%></label></li>
							<li style="width:260px;margin:5px 10px 5px 10px;"><label style="margin-left:20px;width:240px;font-size: 13px;color: #03408f;font-family: 微软雅黑;">收货城市:<%=oorder.getAdrCity()%></label></li>
							<li style="width:260px;margin:5px 10px 5px 10px;"><label style="margin-left:20px;width:240px;font-size: 13px;color: #03408f;font-family: 微软雅黑;">收货街道:<%=oorder.getAdrStreet()%></label></li>
							<li style="width:260px;margin:5px 10px 5px 10px;"><label style="margin-left:20px;width:240px;font-size: 13px;color: #03408f;font-family: 微软雅黑;">收货详细:<%=oorder.getAdrDetial()%></label></li>
							
							<li style="width:260px;margin:5px 10px 5px 10px;"><label style="margin-left:20px;width:240px;font-size: 13px;color: #03408f;font-family: 微软雅黑;">配送时间:<%=oorder.getDeliverTime()%></label></li>
							<li style="width:260px;margin:5px 10px 5px 10px;"><label style="margin-left:20px;width:240px;font-size: 13px;color: #03408f;font-family: 微软雅黑;">发票信息:<%=oorder.getBillMemo()%></label></li>
							
							<li style="width:260px;margin:5px 10px 5px 10px;">
							<label style="margin-left:20px;margin-right:10px;">
							<input onclick="javascript:location.href='<%=basePath%>oorder/oorder_doUpdate_Refund.action?orderid=<%=oorder.getOrderId()%>'" type="button" value="退款" class="btn1" />
							</label>
							<label style="margin-left:10px;margin-right:20px;">
							<input onclick="javascript:location.href='<%=basePath%>oorder/oorder_doView.action?orderid=<%=oorder.getOrderId()%>'" type="button" value="查看" class="btn1" />
							</label>
							</li>
						</ul>
					</div>
					<%
						}
					%>

				</div>
			</div>
		</div>
	</div>
	<div class="rightinfo">
		<div class="ibox"></div>


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
				<li class="paginItem"><a href="<%=basePath%>oorder/oorder_doFind_3.action?currentPage=<%=firstPage-PAGE_SIZE%>">
						<span class="pagepre"></span>
					</a></li>
				<%
					}
				%>

				<%
					for (int i = firstPage; i <=lastPage; i++) {
																																																						if(i==currentPage){
				%>

				<li class="paginItem current"><a href="<%=basePath%>oorder/oorder_doFind_3.action?currentPage=<%=i%>"><%=i%></a></li>

				<%
					continue;}
				%>

				<li class="paginItem"><a href="<%=basePath%>oorder/oorder_doFind_3.action?currentPage=<%=i%>"><%=i%></a></li>

				<%
					}
				%>
				<%
					if(lastPage<totalPage){
				%>
				<li class="paginItem"><a href="<%=basePath%>oorder/oorder_doFind_3.action?currentPage=<%=firstPage+PAGE_SIZE%>">
						<span class="pagenxt"></span>
					</a></li>
				<%
					}
				%>
			</ul>
		</div>
		<div class="ibox"></div>
		<div class="ibox"></div>
		<div class="ibox"></div>
		<div id="buttom" class="ibox" style="float:right;">
			<a class="ibtn" href="#formbody">
				<img src="<%=basePath%>images/iup.png" style="margin-top: 2px; margin-left: -10px;" />返回顶部
			</a>
		</div>
		</form>
		<script type="text/javascript">
			$('.tablelist tbody tr:odd').addClass('odd');
		</script>
</body>
</html>
