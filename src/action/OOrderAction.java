package action;

import java.util.ArrayList;
import java.util.List;
import service.GBatchService;
import service.OInfoService;
import service.OOrderService;
import utils.msg;
import utils.test;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import bean.GBatch;
import bean.OInfo;
import bean.OOrder;

public class OOrderAction extends ActionSupport {
	
	private OOrderService oorderService;
	private OInfoService oinfoService;
	private GBatchService gbatchService;
	private String keyword;
	private int orderid;
	private int oinfoid;
	private int currentPage;
	private String deletelist;
	private int customerid = 0;
	private int type;
	private int type_3;
	
	public String doDelete() throws Exception {
		OOrder db_oorder = oorderService.View_Id(orderid);
		test.a("daDelete要删除的信息:" + db_oorder);
		
		if (!oorderService.Delete(db_oorder)) {
			ActionContext.getContext().put("Msg", oorderService.getMsg());
			return "systemerror_view";
		}
		
		return (doFind());
	}
	
	public String doDeleteAll() throws Exception {
		String[] id = deletelist.split(",");
		
		for (int i = 0; i < id.length; i++) {
			int num = Integer.parseInt(id[i]);
			OOrder db_oorder = oorderService.View_Id(num);
			oorderService.Delete(db_oorder);
		}
		
		return (doFind());
	}
	
	public String doView() throws Exception {
		test.a(orderid);
		
		int length = oinfoService.Count(orderid, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		List list = oinfoService.Find(orderid, 0, 0, 0, 0, 0, 0, 0, 0, 0, "", 0, length);
		
		if (list.size() <= 0) {
			ActionContext.getContext().put("Msg", oorderService.getMsg());
			return "systemerror_view";
		}
		ActionContext.getContext().put("list", list);
		return "oorderinfo_view";
	}
	
	public String doUpdate_Send() throws Exception {
		
		// 支付方式：1=关闭状态 、2=货到付款、3=线上支付
		int statusmethod = 0;
		// 付款状态：1=关闭状态、2=未付款、3=已付款
		int statuspay = 0;
		// 订单状态：1=关闭状态、2=未完成、3=已完成、4=未关闭
		int statusorder = 0;
		// 货物状态：1=关闭状态、2=未发货、3=已发货、4=已收货
		int statussend = 3;
		// 退款状态：1=关闭状态 、2=等待退款、3=退款成功、4=允许退款、 5=拒绝退款
		int statusrefund = 0;
		// 评论状态：1=未评论 、2=已评论
		int statuscomment = 0;
		
		OOrder db_oorder = oorderService.View_Id(orderid);
		
		if (db_oorder.getStatusMethod() == 3 && db_oorder.getStatusOrder() == 2 && db_oorder.getStatusPay() == 3
				&& db_oorder.getStatusSend() == 2 && db_oorder.getStatusRefund() == 1) {
			
			db_oorder.setStatusSend(statussend);
			db_oorder = oorderService.Update(db_oorder);
			test.a(db_oorder);
			
			if (db_oorder != null) {
				List list1 = new ArrayList();
				int length = oinfoService.Count(orderid, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
				List list = oinfoService.Find(orderid, 0, 0, 0, 0, 0, 0, 0, 0, 0, "", 0, length);
				
				for (int i = 0; i < list.size(); i++) {
					OInfo oinfo = (OInfo) list.get(i);
					
					oinfo.setStatusSend(statussend);
					
					OInfo db_oinfo = oinfoService.Update(oinfo);
					
					test.a(db_oinfo);
					
					list1.add(db_oinfo);
				}
				
				ActionContext.getContext().put("list", list1);
				return "oorderinfo_view";
			}
			else {
				ActionContext.getContext().put("Msg", oorderService.getMsg());
				return "systemerror_view";
			}
		}
		//非法操作
		else {
			ActionContext.getContext().put("Msg", msg.order_illegalinput);
			return "systemerror_view";
		}
	}
	
	public String doUpdate_Refund() throws Exception {
		
		// 支付方式：1=关闭状态 、2=货到付款、3=线上支付
		int statusmethod = 0;
		// 付款状态：1=关闭状态、2=未付款、3=已付款
		int statuspay = 1;
		// 订单状态：1=关闭状态、2=未完成、3=已完成、4=未关闭
		int statusorder = 1;
		// 货物状态：1=关闭状态、2=未发货、3=已发货、4=已收货
		int statussend = 1;
		// 退款状态：1=关闭状态 、2=等待退款、3=退款成功、4=允许退款、 5=拒绝退款
		int statusrefund = 3;
		// 评论状态：1=未评论 、2=已评论
		int statuscomment = 1;
		
		OOrder db_oorder = oorderService.View_Id(orderid);
		
		//未发货
		if (db_oorder.getStatusMethod() == 3 && db_oorder.getStatusOrder() == 2 && db_oorder.getStatusPay() == 3
				&& db_oorder.getStatusSend() == 2 && db_oorder.getStatusRefund() == 2) {
			
			db_oorder.setStatusPay(statuspay);
			db_oorder.setStatusOrder(statusorder);
			db_oorder.setStatusSend(statussend);
			db_oorder.setStatusRefund(statusrefund);
			db_oorder = oorderService.Update(db_oorder);
			test.a(db_oorder);
			
			if (db_oorder != null) {
				List list1 = new ArrayList();
				int length = oinfoService.Count(orderid, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
				List list = oinfoService.Find(orderid, 0, 0, 0, 0, 0, 0, 0, 0, 0, "", 0, length);
				
				for (int i = 0; i < list.size(); i++) {
					OInfo oinfo = (OInfo) list.get(i);
					
					oinfo.setStatusSend(statussend);
					oinfo.setStatusRefund(statusrefund);
					oinfo.setStatusComment(statuscomment);
					
					OInfo db_oinfo = oinfoService.Update(oinfo);
					
					test.a(db_oinfo);
					
					GBatch db_gabtch = oinfo.getGBatch();
					db_gabtch.setNumStock(db_gabtch.getNumStock() + oinfo.getGoodsNum());
					db_gabtch = gbatchService.Update(db_gabtch);
					test.a(db_gabtch);
					
					list1.add(db_oinfo);
				}
				
				ActionContext.getContext().put("list", list1);
				return "oorderinfo_view";
			}
			else {
				ActionContext.getContext().put("Msg", oorderService.getMsg());
				return "systemerror_view";
			}
		}
		//已发货，未收货
		else if (db_oorder.getStatusMethod() == 3 && db_oorder.getStatusOrder() == 2 && db_oorder.getStatusPay() == 3
				&& db_oorder.getStatusSend() == 3 && db_oorder.getStatusRefund() == 2) {
			
			db_oorder.setStatusPay(statuspay);
			db_oorder.setStatusOrder(statusorder);
			db_oorder.setStatusSend(statussend);
			db_oorder.setStatusRefund(statusrefund);
			db_oorder = oorderService.Update(db_oorder);
			test.a(db_oorder);
			
			if (db_oorder != null) {
				List list1 = new ArrayList();
				int length = oinfoService.Count(orderid, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
				List list = oinfoService.Find(orderid, 0, 0, 0, 0, 0, 0, 0, 0, 0, "", 0, length);
				
				for (int i = 0; i < list.size(); i++) {
					OInfo oinfo = (OInfo) list.get(i);
					
					oinfo.setStatusSend(statussend);
					oinfo.setStatusRefund(statusrefund);
					oinfo.setStatusComment(statuscomment);
					
					OInfo db_oinfo = oinfoService.Update(oinfo);
					
					test.a(db_oinfo);
					
					GBatch db_gabtch = oinfo.getGBatch();
					db_gabtch.setNumStock(db_gabtch.getNumStock() + oinfo.getGoodsNum());
					db_gabtch = gbatchService.Update(db_gabtch);
					test.a(db_gabtch);
					
					list1.add(db_oinfo);
				}
				
				ActionContext.getContext().put("list", list1);
				return "oorderinfo_view";
			}
			else {
				ActionContext.getContext().put("Msg", oorderService.getMsg());
				return "systemerror_view";
			}
		}
		//非法操作
		else {
			ActionContext.getContext().put("Msg", msg.order_illegalinput);
			return "systemerror_view";
		}
		
	}
	
	public String doUpdate_Rec() throws Exception {
		
		// 支付方式：1=关闭状态 、2=货到付款、3=线上支付
		int statusmethod = 0;
		// 付款状态：1=关闭状态、2=未付款、3=已付款
		int statuspay = 0;
		// 订单状态：1=关闭状态、2=未完成、3=已完成、4=未关闭
		int statusorder = 0;
		// 货物状态：1=关闭状态、2=未发货、3=已发货、4=已收货
		int statussend = 4;
		// 退款状态：1=关闭状态 、2=等待退款、3=退款成功、4=允许退款、 5=拒绝退款
		int statusrefund = 0;
		// 评论状态：1=未评论 、2=已评论
		int statuscomment = 0;
		
		OOrder db_oorder = oorderService.View_Id(orderid);
		
		if (db_oorder.getStatusMethod() == 3 && db_oorder.getStatusOrder() == 2 && db_oorder.getStatusPay() == 3
				&& db_oorder.getStatusSend() == 3 && db_oorder.getStatusRefund() == 1) {
			
			db_oorder.setStatusSend(statussend);
			db_oorder = oorderService.Update(db_oorder);
			test.a(db_oorder);
			
			if (db_oorder != null) {
				List list1 = new ArrayList();
				int length = oinfoService.Count(orderid, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
				List list = oinfoService.Find(orderid, 0, 0, 0, 0, 0, 0, 0, 0, 0, "", 0, length);
				
				for (int i = 0; i < list.size(); i++) {
					OInfo oinfo = (OInfo) list.get(i);
					test.a(oinfo);
					
					oinfo.setStatusSend(statussend);
					
					OInfo db_oinfo = oinfoService.Update(oinfo);
					test.a(db_oinfo);
					list1.add(db_oinfo);
				}
				ActionContext.getContext().put("list", list1);
				return "oorderinfo_view";
			}
			else {
				ActionContext.getContext().put("Msg", oorderService.getMsg());
				return "systemerror_view";
			}
		}
		else if (db_oorder.getStatusMethod() == 3 && db_oorder.getStatusOrder() == 3 && db_oorder.getStatusPay() == 3
				&& db_oorder.getStatusSend() == 3 && db_oorder.getStatusRefund() == 1) {
			
			db_oorder.setStatusSend(statussend);
			db_oorder = oorderService.Update(db_oorder);
			test.a(db_oorder);
			
			if (db_oorder != null) {
				List list1 = new ArrayList();
				int length = oinfoService.Count(orderid, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
				List list = oinfoService.Find(orderid, 0, 0, 0, 0, 0, 0, 0, 0, 0, "", 0, length);
				
				for (int i = 0; i < list.size(); i++) {
					OInfo oinfo = (OInfo) list.get(i);
					test.a(oinfo);
					
					oinfo.setStatusSend(statussend);
					
					OInfo db_oinfo = oinfoService.Update(oinfo);
					test.a(db_oinfo);
					list1.add(db_oinfo);
				}
				ActionContext.getContext().put("list", list1);
				return "oorderinfo_view";
			}
			else {
				ActionContext.getContext().put("Msg", oorderService.getMsg());
				return "systemerror_view";
			}
		}
		else {
			ActionContext.getContext().put("Msg", msg.order_illegalinput);
			return "systemerror_view";
		}
		
	}
	
	public String doFind() throws Exception {
		if (currentPage <= 0)
			currentPage = 1;
		if (customerid <= 0)
			customerid = 0;
		if (keyword == null || keyword.equals(""))
			keyword = "";
		
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
		else if (type == 7) {//待退款
			statusmethod = 3;
			statuspay = 3;
			statussend = 2;
			statusrefund = 2;// 退款状态：1=关闭状态 、2=等待退款、3=退款成功、4=允许退款、 5=拒绝退款
		}
		else if (type == 8) {//退款成功
			statuspay = 1;// 付款状态：1=关闭状态、2=未付款、3=已付款
			statusorder = 1;// 订单状态：1=关闭状态、2=未完成、3=已完成、4=未关闭
			statussend = 1;// 货物状态：1=关闭状态、2=未发货、3=已发货、4=已收货
			statusrefund = 3;// 退款状态：1=关闭状态 、2=等待退款、3=退款成功、4=允许退款、 5=拒绝退款
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
		test.a("customerid:" + customerid);
		test.a("type:" + type);
		test.a("statusmethod:" + statusmethod);
		test.a("statuspay:" + statuspay);
		test.a("statusorder:" + statusorder);
		test.a("statussend:" + statussend);
		test.a("statusrefund:" + statusrefund);
		test.a("statuscomment:" + statuscomment);
		
		int totalRecord = 0;
		totalRecord = oorderService.Count(customerid, statuspay, statusmethod, statusorder, statussend, statusrefund,
				statuscomment, keyword);
		
		int totalPage = totalRecord / msg.RECORD_SIZE + 1;
		if ((totalRecord % msg.RECORD_SIZE == 0) && (totalRecord > msg.RECORD_SIZE)) {
			totalPage--;
		}
		currentPage = Math.min(currentPage, totalPage);
		
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
		
		test.a("当前页码：totalPage" + totalPage);
		test.a("当前页码：totalRecord" + totalRecord);
		test.a("当前页码：currentPage" + currentPage);
		test.a("当前页码：fromIndex" + fromIndex);
		test.a("当前页码：firstPage" + firstPage);
		test.a("当前页码：lastPage" + lastPage);
		
		List list = oorderService.Find_Desc(customerid, statuspay, statusmethod, statusorder, statussend, statusrefund,
				statuscomment, keyword, fromIndex, msg.RECORD_SIZE);
		
		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", msg.PAGE_SIZE);
		ctx.put("customerid", customerid);
		ctx.put("keyword", keyword);
		ctx.put("type", type);
		
		if (customerid > 0) {
			ctx.put("customerid", customerid);
			return "oorderlistbycustomer_view";
		}
		
		return "oorderlist_view";
		
	}
	
	public String doFind_S() throws Exception {
		if (currentPage <= 0)
			currentPage = 1;
		if (keyword == null || keyword.equals(""))
			keyword = "";
		
		// 支付方式：1=关闭状态 、2=货到付款、3=线上支付
		int statusmethod = 3;
		// 付款状态：1=关闭状态、2=未付款、3=已付款
		int statuspay = 3;
		// 订单状态：1=关闭状态、2=未完成、3=已完成、4=未关闭
		int statusorder = 4;
		// 货物状态：1=关闭状态、2=未发货、3=已发货、4=已收货
		int statussend = 2;
		// 退款状态：1=关闭状态 、2=等待退款、3=退款成功、4=允许退款、 5=拒绝退款
		int statusrefund = 1;
		// 评论状态：1=未评论 、2=已评论
		int statuscomment = 0;
		
		test.a("currentPage:" + currentPage);
		test.a("keyword:" + keyword);
		test.a("customerid:" + customerid);
		test.a("type:" + type);
		test.a("statusmethod:" + statusmethod);
		test.a("statuspay:" + statuspay);
		test.a("statusorder:" + statusorder);
		test.a("statussend:" + statussend);
		test.a("statusrefund:" + statusrefund);
		test.a("statuscomment:" + statuscomment);
		
		int totalRecord = 0;
		totalRecord = oorderService.Count(customerid, statuspay, statusmethod, statusorder, statussend, statusrefund,
				statuscomment, keyword);
		
		int totalPage = totalRecord / (msg.RECORD_SIZE - 1) + 1;
		if ((totalRecord % (msg.RECORD_SIZE - 1) == 0) && (totalRecord > (msg.RECORD_SIZE - 1))) {
			totalPage--;
		}
		currentPage = Math.min(currentPage, totalPage);
		
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
		int fromIndex = (currentPage - 1) * (msg.RECORD_SIZE - 1); // 选择从第几条开始
		
		test.a("当前页码：totalPage" + totalPage);
		test.a("当前页码：totalRecord" + totalRecord);
		test.a("当前页码：currentPage" + currentPage);
		test.a("当前页码：fromIndex" + fromIndex);
		test.a("当前页码：firstPage" + firstPage);
		test.a("当前页码：lastPage" + lastPage);
		
		List list1 = oorderService.Find_Desc(customerid, statuspay, statusmethod, statusorder, statussend, statusrefund,
				statuscomment, keyword, fromIndex, (msg.RECORD_SIZE - 1));
		
		List list2 = new ArrayList();
		for (int i = 0; i < list1.size(); i++) {
			OOrder order = (OOrder) list1.get(i);
			List list = oinfoService.Find(order.getOrderId(), 0, 0, 0, 0, 0, 0, 0, 0, 0, "", 0, 1);
			OInfo info = (OInfo) list.get(0);
			String imgpath = info.getGBatch().getGoods().getImg1();
			list2.add(imgpath);
		}
		
		ActionContext ctx = ActionContext.getContext();
		ctx.put("list1", list1);
		ctx.put("list2", list2);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", msg.PAGE_SIZE);
		
		return "oorderlistS_view";
		
	}
	
	public String doFind_R() throws Exception {
		if (currentPage <= 0)
			currentPage = 1;
		if (keyword == null || keyword.equals(""))
			keyword = "";
		
		// 支付方式：1=关闭状态 、2=货到付款、3=线上支付
		int statusmethod = 3;
		// 付款状态：1=关闭状态、2=未付款、3=已付款
		int statuspay = 3;
		// 订单状态：1=关闭状态、2=未完成、3=已完成、4=未关闭
		int statusorder = 2;
		// 货物状态：1=关闭状态、2=未发货、3=已发货、4=已收货
		int statussend = 2;
		// 退款状态：1=关闭状态 、2=等待退款、3=退款成功、4=允许退款、 5=拒绝退款
		int statusrefund = 2;
		// 评论状态：1=未评论 、2=已评论
		int statuscomment = 0;
		
		test.a("currentPage:" + currentPage);
		test.a("keyword:" + keyword);
		test.a("customerid:" + customerid);
		test.a("type:" + type);
		test.a("statusmethod:" + statusmethod);
		test.a("statuspay:" + statuspay);
		test.a("statusorder:" + statusorder);
		test.a("statussend:" + statussend);
		test.a("statusrefund:" + statusrefund);
		test.a("statuscomment:" + statuscomment);
		
		int totalRecord = 0;
		totalRecord = oorderService.Count(customerid, statuspay, statusmethod, statusorder, statussend, statusrefund,
				statuscomment, keyword);
		
		int totalPage = totalRecord / (msg.RECORD_SIZE - 1) + 1;
		if ((totalRecord % (msg.RECORD_SIZE - 1) == 0) && (totalRecord > (msg.RECORD_SIZE - 1))) {
			totalPage--;
		}
		currentPage = Math.min(currentPage, totalPage);
		
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
		int fromIndex = (currentPage - 1) * (msg.RECORD_SIZE - 1); // 选择从第几条开始
		
		test.a("当前页码：totalPage" + totalPage);
		test.a("当前页码：totalRecord" + totalRecord);
		test.a("当前页码：currentPage" + currentPage);
		test.a("当前页码：fromIndex" + fromIndex);
		test.a("当前页码：firstPage" + firstPage);
		test.a("当前页码：lastPage" + lastPage);
		
		List list1 = oorderService.Find_Desc(customerid, statuspay, statusmethod, statusorder, statussend, statusrefund,
				statuscomment, keyword, fromIndex, (msg.RECORD_SIZE - 1));
		
		List list2 = new ArrayList();
		for (int i = 0; i < list1.size(); i++) {
			OOrder order = (OOrder) list1.get(i);
			List list = oinfoService.Find(order.getOrderId(), 0, 0, 0, 0, 0, 0, 0, 0, 0, "", 0, 1);
			OInfo info = (OInfo) list.get(0);
			String imgpath = info.getGBatch().getGoods().getImg1();
			list2.add(imgpath);
		}
		
		ActionContext ctx = ActionContext.getContext();
		ctx.put("list1", list1);
		ctx.put("list2", list2);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", msg.PAGE_SIZE);
		
		return "oorderlistR_view";
		
	}
	
	public OOrderService getOorderService() {
		return oorderService;
	}
	
	public void setOorderService(OOrderService oorderService) {
		this.oorderService = oorderService;
	}
	
	public OInfoService getOinfoService() {
		return oinfoService;
	}
	
	public void setOinfoService(OInfoService oinfoService) {
		this.oinfoService = oinfoService;
	}
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public int getOinfoid() {
		return oinfoid;
	}
	
	public void setOinfoid(int oinfoid) {
		this.oinfoid = oinfoid;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public String getDeletelist() {
		return deletelist;
	}
	
	public void setDeletelist(String deletelist) {
		this.deletelist = deletelist;
	}
	
	public int getCustomerid() {
		return customerid;
	}
	
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getOrderid() {
		return orderid;
	}
	
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	
	public int getType_3() {
		return type_3;
	}
	
	public void setType_3(int type_3) {
		this.type_3 = type_3;
	}
	
	public GBatchService getGbatchService() {
		return gbatchService;
	}
	
	public void setGbatchService(GBatchService gbatchService) {
		this.gbatchService = gbatchService;
	}
	
}
