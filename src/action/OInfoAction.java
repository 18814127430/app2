package action;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import service.OInfoService;
import utils.msg;
import utils.test;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.OInfo;
import bean.OOrder;

public class OInfoAction extends ActionSupport {
	
	private OInfoService oinfoService;
	private OInfo oinfo;
	private String keyword;
	private int currentPage;
	private int orderid;
	private int goodsid;
	private int customerid;
	private int batchid;
	private int type;
	private String deletelist;
	
	public String doFind() throws Exception {
		
		if (currentPage <= 0)
			currentPage = 1;
		if (keyword == null || keyword.equals(""))
			keyword = "";
		if (orderid <= 0)
			orderid = 0;
		if (goodsid <= 0)
			goodsid = 0;
		if (batchid <= 0)
			batchid = 0;
		if (customerid <= 0)
			customerid = 0;
		
		// 支付方式：1=关闭状态 、2=货到付款、3=线上支付
		int statusmethod = 0;
		// 付款状态：1=关闭状态、2=未付款、3=已付款
		int statuspay = 0;
		// 订单状态：1=关闭状态、2=未完成、3=已完成、4=未关闭
		int statusorder = 0;
		// 货物状态：1=关闭状态、2=未发货、3=已发货、4=已收货
		int statussend = 0;
		// 退款状态：1=关闭状态 、2=等待退款、3=退款成功、4=允许退款、 5=拒绝退款
		int statusrefund = 0;
		// 评论状态：1=未评论 、2=已评论
		int statuscomment = 0;
		
		if (type == 1) {//未付款
			statuspay = 2;// 付款状态：1=关闭状态、2=未付款、3=已付款
		}
		else if (type == 2) {//已付款
			statuspay = 3;// 付款状态：1=关闭状态、2=未付款、3=已付款
		}
		else if (type == 3) {//待发货
			statussend = 2;// 货物状态：1=关闭状态、2=未发货、3=已发货、4=已收货
		}
		else if (type == 4) {//已发货
			statussend = 3;// 货物状态：1=关闭状态、2=未发货、3=已发货、4=已收货
		}
		else if (type == 5) {//已收货
			statussend = 4;// 货物状态：1=关闭状态、2=未发货、3=已发货、4=已收货
		}
		else if (type == 6) {//已完成
			statusorder = 3;// 订单状态：1=关闭状态、2=未完成、3=已完成、4=未关闭
		}
		else if (type == 7) {//未评价
			statuscomment = 1;// 评论状态：1=未评论 、2=已评论
		}
		else if (type == 8) {//已评价
			statuscomment = 2;// 评论状态：1=未评论 、2=已评论
		}
		else {//总订单
			statusmethod = 0;// 支付方式：1=关闭状态 、2=货到付款、3=线上支付
			statuspay = 0;// 付款状态：1=关闭状态、2=未付款、3=已付款
			statusorder = 0;// 订单状态：1=关闭状态、2=未完成、3=已完成、4=未关闭
			statussend = 0;// 货物状态：1=关闭状态、2=未发货、3=已发货、4=已收货
			statusrefund = 0;// 退款状态：1=关闭状态 、2=等待退款、3=退款成功、4=允许退款、 5=拒绝退款
			statuscomment = 0;// 评论状态：1=未评论 、2=已评论
		}
		
		test.a("currentPage:" + currentPage);
		test.a("keyword:" + keyword);
		test.a("orderid:" + orderid);
		test.a("goodsid:" + goodsid);
		test.a("batchid:" + batchid);
		test.a("keyword:" + keyword);
		test.a("customerid:" + customerid);
		test.a("type:" + type);
		test.a("statusmethod:" + statusmethod);
		test.a("statuspay:" + statuspay);
		test.a("statusorder:" + statusorder);
		test.a("statussend:" + statussend);
		test.a("statusrefund:" + statusrefund);
		test.a("statuscomment:" + statuscomment);
		
		int totalRecord = oinfoService.Count(orderid, customerid, goodsid, batchid, statussend, statusrefund, statusorder,
				statuspay, statuspay, statusmethod, keyword);
		
		int totalPage = totalRecord / msg.RECORD_SIZE + 1;
		if ((totalRecord % msg.RECORD_SIZE == 0) && (totalRecord > msg.RECORD_SIZE)) {
			totalPage--;
		}
		if (currentPage > totalPage) {
			currentPage = totalPage;
		}
		
		int firstPage = 1;
		int lastPage = 1;
		if (totalPage < msg.PAGE_SIZE) {
			firstPage = 1;
			lastPage = totalPage;
		}
		else {
			firstPage = (currentPage / msg.PAGE_SIZE) * msg.PAGE_SIZE + 1;
			lastPage = firstPage + msg.PAGE_SIZE - 1;
			if (lastPage > totalPage) {
				lastPage = totalPage;
			}
		}
		int fromIndex = (currentPage - 1) * msg.RECORD_SIZE; // 选择从第几条开始
		int toIndex = Math.min(fromIndex + msg.RECORD_SIZE, totalRecord);// 调用Math.min函数取目的数
		
		test.a("totalPage:" + totalPage);
		test.a("totalRecord:" + totalRecord);
		test.a("currentPage:" + currentPage);
		test.a("fromIndex:" + fromIndex);
		test.a("toIndex:" + toIndex);
		test.a("firstPage:" + firstPage);
		test.a("lastPage:" + lastPage);
		
		List list = oinfoService.Find(orderid, customerid, goodsid, batchid, statussend, statusrefund, statusorder,
				statuspay, statuspay, statusmethod, keyword, fromIndex, msg.RECORD_SIZE);
		
		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", msg.PAGE_SIZE);
		ctx.put("keyword", keyword);
		
		if (goodsid > 0) {
			ctx.put("goodsid", goodsid);
			return "oinfolistbygoods_view";
		}
		else if (batchid > 0) {
			ctx.put("batchid", batchid);
			return "oinfolistbybatch_view";
		}
		else {
			return "oinfolist_view";
		}
		
	}
	
	public OInfoService getOInfoService() {
		return oinfoService;
	}
	
	public void setOInfoService(OInfoService oinfoService) {
		this.oinfoService = oinfoService;
	}
	
	public OInfo getOInfo() {
		return oinfo;
	}
	
	public void setOInfo(OInfo oinfo) {
		this.oinfo = oinfo;
	}
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public OInfoService getOinfoService() {
		return oinfoService;
	}
	
	public void setOinfoService(OInfoService oinfoService) {
		this.oinfoService = oinfoService;
	}
	
	public OInfo getOinfo() {
		return oinfo;
	}
	
	public void setOinfo(OInfo oinfo) {
		this.oinfo = oinfo;
	}
	
	public int getGoodsid() {
		return goodsid;
	}
	
	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}
	
	public String getDeletelist() {
		return deletelist;
	}
	
	public void setDeletelist(String deletelist) {
		this.deletelist = deletelist;
	}
	
	public int getOrderid() {
		return orderid;
	}
	
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	
	public int getBatchid() {
		return batchid;
	}
	
	public void setBatchid(int batchid) {
		this.batchid = batchid;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getCustomerid() {
		return customerid;
	}
	
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
}
