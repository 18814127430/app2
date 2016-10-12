package servlet;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import service.CartService;
import service.CompanyService;
import service.GBatchService;
import service.GoodsService;
import service.OInfoService;
import service.OOrderService;
import service.CustomerService;
import utils.serialMaker;
import utils.msg;
import utils.test;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import androidbeans.a_OInfo;
import androidbeans.a_OOrder;
import bean.Cart;
import bean.OOrder;
import bean.GBatch;
import bean.OInfo;
import bean.Customer;
import task.Task_ClearOrder;

/**
 * @author 10919
 * 
 */
public class OOrderServlet extends ActionSupport {
	
	private final Log logger = LogFactory.getLog(getClass());
	private CustomerService customerService;
	private OOrderService oorderService;
	private OInfoService oinfoService;
	private GBatchService gbatchService;
	private CartService cartService;
	private GoodsService goodsService;
	private CompanyService companyService;
	
	public a_OOrder order2a(OOrder db_oorder) {
		a_OOrder a_oorder = new a_OOrder();
		
		a_oorder.setOrderId(db_oorder.getOrderId());
		a_oorder.setCustomerId(db_oorder.getCustomer().getCustomerId());
		a_oorder.setOrderSerial(db_oorder.getOrderSerial());
		a_oorder.setOrderRemark(db_oorder.getOrderRemark());
		
		if (db_oorder.getOrderDate() != null)
			a_oorder.setOrderDate(test.Timestamp2String(db_oorder.getOrderDate()));
		
		a_oorder.setMoneyTotal(db_oorder.getMoneyTotal());
		a_oorder.setMoneyDeliver(db_oorder.getMoneyDeliver());
		a_oorder.setPaySerial(db_oorder.getPaySerial());
		a_oorder.setPayMethod(db_oorder.getPayMethod());
		
		if (db_oorder.getPayDate() != null)
			a_oorder.setPayDate(test.Timestamp2String(db_oorder.getPayDate()));
		
		a_oorder.setAdrName(db_oorder.getAdrName());
		a_oorder.setAdrPhone(db_oorder.getAdrPhone());
		a_oorder.setAdrProvince(db_oorder.getAdrProvince());
		a_oorder.setAdrCity(db_oorder.getAdrCity());
		a_oorder.setAdrStreet(db_oorder.getAdrStreet());
		a_oorder.setAdrDetial(db_oorder.getAdrDetial());
		
		int statusmethod = 3;// 支付方式：1=关闭状态 、2=货到付款、3=线上支付
		int statuspay = 2;// 付款状态：1=关闭状态、2=未付款、3=已付款
		int statusorder = 2;// 订单状态：1=关闭状态、2=未完成、3=已完成、4=未关闭
		int statussend = 2;// 货物状态：1=关闭状态、2=未发货、3=已发货、4=已收货、5=未收货
		int statusrefund = 1;// 退款状态：1=关闭状态 、2=等待退款、3=退款成功、4=允许退款、 5=拒绝退款
		int statuscomment = 1;// 评论状态：1=未评论 、2=已评论
		
		int ordertype = 0;
		//		1.未支付   
		if (db_oorder.getStatusMethod() == 3 && db_oorder.getStatusOrder() == 2 && db_oorder.getStatusPay() == 2) {
			ordertype = 1;
		}
		//		2.未收货
		else if (db_oorder.getStatusMethod() == 3 && db_oorder.getStatusOrder() == 2 && db_oorder.getStatusPay() == 3
				&& db_oorder.getStatusRefund() == 1 && db_oorder.getStatusSend() == 2) {
			ordertype = 2;
		}
		else if (db_oorder.getStatusMethod() == 3 && db_oorder.getStatusOrder() == 2 && db_oorder.getStatusPay() == 3
				&& db_oorder.getStatusRefund() == 1 && db_oorder.getStatusSend() == 3) {
			ordertype = 2;
		}
		//		3.未评论
		else if (db_oorder.getStatusMethod() == 3 && db_oorder.getStatusOrder() == 3 && db_oorder.getStatusPay() == 3
				&& db_oorder.getStatusRefund() == 1 && db_oorder.getStatusSend() == 4
				&& db_oorder.getNumComment() < db_oorder.getNumInfo()) {
			ordertype = 3;
		}
		//      0.全部
		else {
			ordertype = 0;
		}
		
		a_oorder.setOrderType(ordertype);
		
		a_oorder.setDeliverTime(db_oorder.getDeliverTime());
		a_oorder.setBillMemo(db_oorder.getBillMemo());
		
		return a_oorder;
	}
	
	public OOrder a2order(a_OOrder a_oorder, Customer db_customer) {
		
		serialMaker serialMaker = new serialMaker();
		String serial = serialMaker.maker();
		
		OOrder db_oorder = new OOrder();
		
		db_oorder.setAdrCity(a_oorder.getAdrCity());
		db_oorder.setAdrDetial(a_oorder.getAdrDetial());
		db_oorder.setAdrName(a_oorder.getAdrName());
		db_oorder.setAdrPhone(a_oorder.getAdrPhone());
		db_oorder.setAdrProvince(a_oorder.getAdrProvince());
		db_oorder.setAdrStreet(a_oorder.getAdrStreet());
		
		db_oorder.setMoneyDeliver(0.0);
		db_oorder.setMoneyTotal(0.0);
		
		db_oorder.setOrderId(null);
		db_oorder.setCustomer(db_customer);
		db_oorder.setOrderSerial(serial);
		db_oorder.setOrderSubject("");
		db_oorder.setOrderBody("");
		db_oorder.setOrderRemark(a_oorder.getOrderRemark());
		db_oorder.setOrderDate(test.String2Timestamp(test.GetCurrentTime()));
		
		db_oorder.setPayDate(null);
		db_oorder.setPayMethod(a_oorder.getPayMethod());
		db_oorder.setPaySerial(null);
		
		// 支付方式：1=关闭状态 、2=货到付款、3=线上支付
		int statusmethod = 3;
		// 付款状态：1=关闭状态、2=未付款、3=已付款
		int statuspay = 2;
		// 订单状态：1=关闭状态、2=未完成、3=已完成、4=未关闭
		int statusorder = 2;
		// 货物状态：1=关闭状态、2=未发货、3=已发货、4=已收货、5=未收货
		int statussend = 2;
		// 退款状态：1=关闭状态 、2=等待退款、3=退款成功、4=允许退款、 5=拒绝退款
		int statusrefund = 1;
		// 评论状态：1=未评论 、2=已评论
		int statuscomment = 1;
		
		db_oorder.setStatusMethod(statusmethod);
		db_oorder.setStatusPay(statuspay);
		db_oorder.setStatusOrder(statusorder);
		db_oorder.setStatusSend(statussend);
		db_oorder.setStatusRefund(statusrefund);
		
		db_oorder.setNumInfo(0);
		db_oorder.setNumComment(0);
		
		db_oorder.setDeliverTime(a_oorder.getDeliverTime());
		db_oorder.setBillMemo(a_oorder.getBillMemo());
		
		return db_oorder;
	}
	
	public a_OInfo oinfo2a(OInfo db_oinfo) {
		a_OInfo a_oinfo = new a_OInfo();
		
		a_oinfo.setOinfoId(db_oinfo.getOinfoId());
		a_oinfo.setOinfoSerial(db_oinfo.getOinfoSerial());
		
		a_oinfo.setBatchId(db_oinfo.getGBatch().getBatchId());
		a_oinfo.setGoodsId(db_oinfo.getGBatch().getGoods().getGoodsId());
		a_oinfo.setOrderId(db_oinfo.getOOrder().getOrderId());
		
		a_oinfo.setGoodsName(db_oinfo.getGBatch().getGoods().getGoodsName());
		a_oinfo.setGoodsNum(db_oinfo.getGoodsNum());
		
		a_oinfo.setGoodsPrice(db_oinfo.getGoodsPrice());
		a_oinfo.setDeliverFree(null);
		a_oinfo.setDeliverMoney(null);
		a_oinfo.setTotalMoney(null);
		
		a_oinfo.setStatusComment(db_oinfo.getStatusComment());
		
		a_oinfo.setPic_item_good(db_oinfo.getGBatch().getGoods().getImg8());//pic_item_good 200*200
		
		return a_oinfo;
	}
	
	public OInfo a2oinfo(a_OInfo a_oinfo, GBatch db_gbatch, OOrder db_oorder) {
		
		serialMaker serialMaker = new serialMaker();
		String serial = serialMaker.maker();
		
		OInfo db_oinfo = new OInfo();
		
		db_oinfo.setOinfoId(a_oinfo.getOinfoId());
		db_oinfo.setGBatch(db_gbatch);
		db_oinfo.setOOrder(db_oorder);
		
		db_oinfo.setOinfoSerial(serial);
		
		db_oinfo.setGoodsNum(a_oinfo.getGoodsNum());
		db_oinfo.setGoodsPrice(db_gbatch.getGoods().getMoneyNew());
		
		db_oinfo.setStatusRefund(0);
		db_oinfo.setStatusSend(0);
		db_oinfo.setStatusComment(1);
		
		return db_oinfo;
	}
	
	/**
	 * 添加订单
	 * 
	 * @throws Exception
	 */
	public synchronized void doAdd() throws Exception {
		
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		String status = "";
		String message = "";
		String data = "";
		int page = 0;
		String mapdata = "";
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/data; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String token = request.getParameter("tk");
		String listdata = request.getParameter("jn");
		String cid = request.getParameter("cid");
		
		if (listdata == null || listdata.equals("")) {
			message = msg.order_illegalinput;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		Customer db_customer = customerService.FindByToken(token);
		if (db_customer == null) {
			message = msg.order_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		System.out.println("------->token:" + token);
		System.out.println("------->listdata:" + listdata);
		System.out.println("------->db_customer:" + db_customer);
		
		double moneytotal = 0.0;
		double moneyleast = 0.0;
		double moneydeliver = 0.0;
		String body = "";
		String subject = "";
		OOrder db_oorder = new OOrder();
		OInfo db_oinfo = new OInfo();
		List list1 = new ArrayList();
		List list = gson.fromJson(listdata, java.util.ArrayList.class);
		
		for (int i = 0; i < list.size(); i++) {
			
			if (i == 0) {
				String s = (String) list.get(i);
				a_OOrder a_oorder = gson.fromJson(s, a_OOrder.class);
				
				db_oorder = a2order(a_oorder, db_customer);
				test.a("原始db_oorder：" + db_oorder);
				db_oorder = oorderService.Add(db_oorder);
				test.a("添加db_oorder:" + db_oorder);
				if (db_oorder == null) {
					message = msg.order_ordernull;
					status = msg.status_1;
					map.put("status", status);
					map.put("message", message);
					mapdata = gson.toJson(map);
					
					out.write(mapdata);
					out.flush();
					out.close();
					return;
				}
				
				a_oorder = order2a(db_oorder);
				list1.add(a_oorder);
				continue;
			}
			
			String s = (String) list.get(i);
			a_OInfo a_oinfo = gson.fromJson(s, a_OInfo.class);
			
			if (a_oinfo == null) {
				break;
			}
			
			GBatch db_gbatch = gbatchService.findByQuatifyDateKeep(a_oinfo.getGoodsNum(), a_oinfo.getGoodsId());
			test.a("选中db_gbatch:" + db_gbatch);
			if (db_gbatch == null) {
				message = msg.gbatch_gbatchnull;
				status = msg.status_1;
				map.put("status", status);
				map.put("message", message);
				mapdata = gson.toJson(map);
				
				out.write(mapdata);
				out.flush();
				out.close();
				return;
			}
			
			db_oinfo = a2oinfo(a_oinfo, db_gbatch, db_oorder);
			test.a("原始db_oinfo：" + db_oinfo);
			db_oinfo = oinfoService.Add(db_oinfo);
			test.a("添加db_oinfo:" + db_oinfo);
			
			if (db_oinfo == null) {
				break;
			}
			
			a_oinfo = oinfo2a(db_oinfo);
			//			list1.add(a_oinfo);
			
			moneyleast = Math.max(moneyleast, db_gbatch.getGoods().getMoneyLeast());
			moneydeliver = Math.max(moneydeliver, db_gbatch.getGoods().getMoneyDeliver());
			moneytotal = moneytotal + db_oinfo.getGoodsNum() * db_gbatch.getGoods().getMoneyNew();
			
			if (subject.length() <= 250 && subject.equals("")) {
				subject = "(商品)" + a_oinfo.getGoodsName() + "等";
			}
			if (body.length() <= 1000) {
				if (body.equals("")) {
					body = a_oinfo.getGoodsName() + "x" + a_oinfo.getGoodsNum().toString();
				}
				else {
					body = body + "," + a_oinfo.getGoodsName() + "x" + a_oinfo.getGoodsNum().toString();
				}
			}
		}
		
		if (list.size() != list1.size() || list.size() == 1) {
			oorderService.Delete(db_oorder);
			
			message = msg.order_ordernull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		if (moneytotal >= moneyleast) {
			db_oorder.setMoneyTotal(moneytotal);
			db_oorder.setMoneyDeliver(0.0);
		}
		else {
			db_oorder.setMoneyTotal(moneytotal + moneydeliver);
			db_oorder.setMoneyDeliver(moneydeliver);
		}
		
		db_oorder.setNumInfo(list1.size() - 1);
		db_oorder.setNumComment(0);
		
		db_oorder.setOrderSubject(subject);
		db_oorder.setOrderBody(body);
		
		test.a("原始db_oorder：" + db_oorder);
		db_oorder = oorderService.Update(db_oorder);
		test.a("更新db_oorder:" + db_oorder);
		
		a_OOrder a_oorder = order2a(db_oorder);
		list.set(0, a_oorder);
		
		message = msg.order_success;
		status = msg.status_0;
		map.put("status", status);
		map.put("message", message);
		
		data = gson.toJson(list1);
		map.put("data", data);
		
		map.put("out_trade_no", db_oorder.getOrderSerial());
		map.put("subject", db_oorder.getOrderSubject());
		map.put("total_fee", db_oorder.getMoneyTotal());
		map.put("body", db_oorder.getOrderBody());
		map.put("it_b_pay", msg.it_b_pay);
		
		mapdata = gson.toJson(map);
		
		out.write(mapdata);
		out.flush();
		out.close();
		
		if (cid == null || cid.equals("")) {
			return;
		}
		String[] cartid = cid.replace(" ", "").split(",");
		for (int i = 0; i < cartid.length; i++) {
			
			if (cartid[i].equals("") || !test.isInteger(cartid[i])) {
				continue;
			}
			
			int num = Integer.parseInt(cartid[i]);
			Cart db_cart = cartService.View(num);
			
			if (db_cart == null) {
				continue;
			}
			
			if (db_cart.getCustomer().getCustomerId().intValue() != db_customer.getCustomerId().intValue()) {
				continue;
			}
			
			if (!cartService.Delete(db_cart)) {
				continue;
			}
		}
		
		logger.info("下单成功！");
		return;
	}
	
	/**
	 * 取消订单或申请未收到货退款
	 * 
	 * @throws Exception
	 */
	public void doCancel() throws Exception {
		
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		String status = "";
		String message = "";
		String mapdata = "";
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/data; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String s1 = request.getParameter("tk");
		String s2 = request.getParameter("id");
		
		if (s1 == null || s1.equals("") || s2 == null || s2.equals("") || !test.isInteger(s2)) {
			message = msg.order_illegalinput;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		String token = s1;
		int id = Integer.parseInt(s2);
		
		Customer db_customer = customerService.FindByToken(token);
		if (db_customer == null) {
			message = msg.order_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		OOrder db_oorder = oorderService.View_Id(id);
		
		if (db_oorder == null) {
			message = msg.order_ordernull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		if (db_oorder.getCustomer().getCustomerId().intValue() != db_customer.getCustomerId().intValue()) {
			message = msg.order_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		System.out.println("------->token:" + token);
		System.out.println("------->orderid:" + id);
		System.out.println("------->db_customer:" + db_customer);
		System.out.println("------->db_oorder:" + db_oorder);
		
		// 线上支付、未付款 ===> 删除订单
		if (db_oorder.getStatusMethod() == 3 && db_oorder.getStatusPay() == 2) {
			oorderService.Delete(db_oorder);
			
			for (int i = Task_ClearOrder.list.size() - 1; i >= 0; i--) {
				int orderid = (Integer) Task_ClearOrder.list.get(i);
				if (orderid == db_oorder.getOrderId()) {
					Task_ClearOrder.list.remove(i);
				}
			}
		}
		
		// 线上支付、已付款、未发货 ===> 删除订单
		else if (db_oorder.getStatusMethod() == 3 && db_oorder.getStatusPay() == 2 && db_oorder.getStatusSend() == 2) {
			oorderService.Delete(db_oorder);
			
			for (int i = Task_ClearOrder.list.size() - 1; i >= 0; i--) {
				int orderid = (Integer) Task_ClearOrder.list.get(i);
				if (orderid == db_oorder.getOrderId()) {
					Task_ClearOrder.list.remove(i);
				}
			}
		}
		
		// 线上支付、已付款、已发货 ===> 退款 ===> 删除订单
		else if (db_oorder.getStatusMethod() == 3 && db_oorder.getStatusPay() == 2 && db_oorder.getStatusSend() == 2) {
			/* 申请退款 */
			oorderService.Delete(db_oorder);
			
			for (int i = Task_ClearOrder.list.size() - 1; i >= 0; i--) {
				int orderid = (Integer) Task_ClearOrder.list.get(i);
				if (orderid == db_oorder.getOrderId()) {
					Task_ClearOrder.list.remove(i);
				}
			}
		}
		
		// 其他情况、取消失败
		else {
			message = msg.order_fail;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		message = msg.order_success;
		status = msg.status_0;
		map.put("status", status);
		map.put("message", message);
		mapdata = gson.toJson(map);
		
		out.write(mapdata);
		out.flush();
		out.close();
		return;
	}
	
	/**
	 * 确认收货
	 * 
	 * @throws Exception
	 */
	public void doConfirm() throws Exception {
		
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		String status = "";
		String message = "";
		String data = "";
		String mapdata = "";
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/data; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String s1 = request.getParameter("tk");
		String s2 = request.getParameter("id");
		
		if (s1 == null || s1.equals("") || s2 == null || s2.equals("") || !test.isInteger(s2)) {
			message = msg.order_illegalinput;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		String token = s1;
		int id = Integer.parseInt(s2);
		
		Customer db_customer = customerService.FindByToken(token);
		if (db_customer == null) {
			message = msg.order_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		OOrder db_oorder = oorderService.View_Id(id);
		
		if (db_oorder == null) {
			message = msg.order_ordernull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		if (db_oorder.getCustomer().getCustomerId().intValue() != db_customer.getCustomerId().intValue()) {
			message = msg.order_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		System.out.println("------->token:" + token);
		System.out.println("------->orderid:" + id);
		System.out.println("------->db_customer:" + db_customer);
		System.out.println("------->db_oorder:" + db_oorder);
		
		if (db_oorder.getStatusMethod() == 3 && db_oorder.getStatusPay() == 3 && db_oorder.getStatusOrder() == 2
				&& db_oorder.getStatusSend() != 1) {
			
			List list1 = new ArrayList();
			
			// 支付方式：1=关闭状态 、2=货到付款、3=线上支付
			int statusmethod = 3;
			// 付款状态：1=关闭状态、2=未付款、3=已付款
			int statuspay = 3;
			// 订单状态：1=关闭状态、2=未完成、3=已完成、4=未关闭
			int statusorder = 3;
			// 货物状态：1=关闭状态、2=未发货、3=已发货、4=已收货、5=未收货
			int statussend = 4;
			// 退款状态：1=关闭状态 、2=等待退款、3=退款成功、4=允许退款、 5=拒绝退款
			int statusrefund = 1;
			// 评论状态：1=未评论 、2=已评论
			int statuscomment = 0;
			
			db_oorder.setStatusOrder(statusorder);
			db_oorder.setStatusSend(statussend);
			
			db_oorder = oorderService.Update(db_oorder);
			
			if (db_oorder == null) {
				message = msg.order_ordernull;
				status = msg.status_1;
				map.put("status", status);
				map.put("message", message);
				mapdata = gson.toJson(map);
				
				out.write(mapdata);
				out.flush();
				out.close();
				return;
			}
			
			a_OOrder a_oorder = order2a(db_oorder);
			list1.add(a_oorder);
			
			int length = oinfoService.Count(db_oorder.getOrderId(), 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
			List list = oinfoService.Find(db_oorder.getOrderId(), 0, 0, 0, 0, 0, 0, 0, 0, 0, "", 0, length);
			
			for (int i = 0; i < list.size(); i++) {
				OInfo oinfo = (OInfo) list.get(i);
				
				oinfo.setStatusSend(statussend);
				
				OInfo db_oinfo = oinfoService.Update(oinfo);
				
				a_OInfo a_oinfo = oinfo2a(db_oinfo);
				
				list1.add(a_oinfo);
			}
			
			message = msg.order_success;
			status = msg.status_0;
			map.put("status", status);
			map.put("message", message);
			
			data = gson.toJson(list1);
			map.put("data", data);
			
			//			map.put("out_trade_no", db_oorder.getOrderSerial());
			//			map.put("subject", db_oorder.getOrderSubject());
			//			map.put("total_fee", db_oorder.getMoneyTotal());
			//			map.put("body", db_oorder.getOrderBody());
			//			map.put("it_b_pay", msg.it_b_pay);
			
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			
			for (int i = Task_ClearOrder.list.size() - 1; i >= 0; i--) {
				int orderid = (Integer) Task_ClearOrder.list.get(i);
				if (orderid == db_oorder.getOrderId()) {
					Task_ClearOrder.list.remove(i);
				}
			}
			
			logger.info("确认收货成功！");
			return;
		}
		else {
			message = msg.order_fail;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
	}
	
	/**
	 * 查看订单
	 * 
	 * @throws Exception
	 */
	public void doView() throws Exception {
		
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		String status = "";
		String message = "";
		String data = "";
		int page = 0;
		String mapdata = "";
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/data; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String s1 = request.getParameter("tk");
		String s2 = request.getParameter("typ");
		String s3 = request.getParameter("id");
		
		if (s1 == null || s1.equals("") || s2 == null || s2.equals("") || !test.isInteger(s2) || s3 == null
				|| s3.equals("") || !test.isInteger(s3)) {
			message = msg.order_illegalinput;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		int type = Integer.parseInt(s2);
		if (type < 1 || type > 2) {
			message = msg.order_illegalinput;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		String token = s1;
		Customer db_customer = customerService.FindByToken(token);
		if (db_customer == null) {
			message = msg.order_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		int id = Integer.parseInt(s3);
		
		System.out.println("string1:" + s1 + "------------token:" + token);
		System.out.println("string2:" + s2 + "------------type:" + type);
		System.out.println("string3:" + s3 + "------------id:" + id);
		
		if (type == 1) {
			OOrder db_oorder = oorderService.View_Id(id);
			
			if (db_oorder == null) {
				message = msg.order_ordernull;
				status = msg.status_1;
				map.put("status", status);
				map.put("message", message);
				mapdata = gson.toJson(map);
				
				out.write(mapdata);
				out.flush();
				out.close();
				return;
			}
			
			if (db_oorder.getCustomer().getCustomerId().intValue() != db_customer.getCustomerId().intValue()) {
				message = msg.order_customerwrong;
				status = msg.status_1;
				map.put("status", status);
				map.put("message", message);
				mapdata = gson.toJson(map);
				
				out.write(mapdata);
				out.flush();
				out.close();
				return;
			}
			
			System.out.println("------->token:" + token);
			System.out.println("------->orderid:" + id);
			System.out.println("------->db_customer:" + db_customer);
			System.out.println("------->object:" + db_oorder);
			
			message = msg.order_success;
			status = msg.status_0;
			map.put("status", status);
			map.put("message", message);
			
			a_OOrder a_oorder = order2a(db_oorder);
			
			data = gson.toJson(a_oorder);
			map.put("data", data);
			
			map.put("out_trade_no", db_oorder.getOrderSerial());
			map.put("subject", db_oorder.getOrderSubject());
			map.put("total_fee", db_oorder.getMoneyTotal());
			map.put("body", db_oorder.getOrderBody());
			map.put("it_b_pay", msg.it_b_pay);
			
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			
			out.flush();
			out.close();
			return;
		}
		else {
			OInfo db_oinfo = oinfoService.View_Id(id);
			
			System.out.println("------->token:" + token);
			System.out.println("------->id:" + id);
			System.out.println("------->db_customer:" + db_customer);
			System.out.println("------->db_oinfo:" + db_oinfo);
			
			if (db_oinfo == null) {
				message = msg.oinfo_oinfonull;
				status = msg.status_1;
				map.put("status", status);
				map.put("message", message);
				mapdata = gson.toJson(map);
				
				out.write(mapdata);
				out.flush();
				out.close();
				return;
			}
			
			if (db_oinfo.getOOrder().getCustomer().getCustomerId().intValue() != db_customer.getCustomerId().intValue()) {
				message = msg.oinfo_customerwrong;
				status = msg.status_1;
				map.put("status", status);
				map.put("message", message);
				mapdata = gson.toJson(map);
				
				out.write(mapdata);
				out.flush();
				out.close();
				return;
			}
			
			message = msg.oinfo_success;
			status = msg.status_0;
			map.put("status", status);
			map.put("message", message);
			
			a_OInfo a_oinfo = oinfo2a(db_oinfo);
			
			data = gson.toJson(a_oinfo);
			map.put("data", data);
			
			map.put("out_trade_no", db_oinfo.getOOrder().getOrderSerial());
			map.put("subject", db_oinfo.getOOrder().getOrderSubject());
			map.put("total_fee", db_oinfo.getOOrder().getMoneyTotal());
			map.put("body", db_oinfo.getOOrder().getOrderBody());
			map.put("it_b_pay", msg.it_b_pay);
			
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
	}
	
	/**
	 * 查看订单列表
	 * 
	 * @throws Exception
	 */
	public void doFind_Type() throws Exception {
		
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		String status = "";
		String message = "";
		String data = "";
		int page = 0;
		String mapdata = "";
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/data; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String s1 = request.getParameter("tk");
		String s2 = request.getParameter("typ");
		String s3 = request.getParameter("page");
		String s4 = request.getParameter("kw");
		
		if (s1 == null || s1.equals("") || s2 == null || s2.equals("") || !test.isInteger(s2) || s3 == null
				|| s3.equals("") || !test.isInteger(s3)) {
			message = msg.order_illegalinput;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		String token = s1;
		int type = Integer.parseInt(s2);
		int currentPage = Integer.parseInt(s3);
		
		String keyword = "";
		if (s4 == null || s4.equals("")) {
			keyword = "";
		}
		else {
			keyword = s4;
		}
		
		Customer db_customer = customerService.FindByToken(token);
		if (db_customer == null) {
			message = msg.order_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		// 支付方式：1=关闭状态 、2=货到付款、3=线上支付
		int statusmethod = 0;
		// 付款状态：1=关闭状态、2=未付款、3=已付款
		int statuspay = 0;
		// 订单状态：1=关闭状态、2=未完成、3=已完成、4=未关闭
		int statusorder = 0;
		// 货物状态：1=关闭状态、2=未发货、3=已发货、4=已收货、5=未收货
		int statussend = 0;
		// 退款状态：1=关闭状态 、2=等待退款、3=退款成功、4=允许退款、 5=拒绝退款
		int statusrefund = 0;
		// 评论状态：1=未评论 、2=已评论
		int statuscomment = 0;
		
		if (type == 1) {//未付款
			statusmethod = 3;// 支付方式：1=关闭状态 、2=货到付款、3=线上支付
			statuspay = 2;// 付款状态：1=关闭状态、2=未付款、3=已付款
		}
		else if (type == 2) {//未收货
			statusmethod = 3;// 支付方式：1=关闭状态 、2=货到付款、3=线上支付
			statuspay = 3;// 付款状态：1=关闭状态、2=未付款、3=已付款
			statusorder = 2;// 订单状态：1=关闭状态、2=未完成、3=已完成、4=未关闭
			statussend = 5;// 货物状态：1=关闭状态、2=未发货、3=已发货、4=已收货、5=未收货、5=未关闭
			statusrefund = 1;// 退款状态：1=关闭状态 、2=等待退款、3=退款成功、4=允许退款、 5=拒绝退款
		}
		else if (type == 3) {//未评论
			statusmethod = 3;// 支付方式：1=关闭状态 、2=货到付款、3=线上支付
			statuspay = 3;// 付款状态：1=关闭状态、2=未付款、3=已付款
			statusorder = 3;// 订单状态：1=关闭状态、2=未完成、3=已完成、4=未关闭
			statussend = 4;// 货物状态：1=关闭状态、2=未发货、3=已发货、4=已收货、5=未收货
			statusrefund = 1;// 退款状态：1=关闭状态 、2=等待退款、3=退款成功、4=允许退款、 5=拒绝退款
			statuscomment = 1;// 评论状态：1=未评论 、2=已评论
		}
		else if (type == 4) {//全部订单
			statusmethod = 0;// 支付方式：1=关闭状态 、2=货到付款、3=线上支付
			statuspay = 0;// 付款状态：1=关闭状态、2=未付款、3=已付款
			statusorder = 0;// 订单状态：1=关闭状态、2=未完成、3=已完成、4=未关闭
			statussend = 0;// 货物状态：1=关闭状态、2=未发货、3=已发货、4=已收货、5=未收货
			statusrefund = 0;// 退款状态：1=关闭状态 、2=等待退款、3=退款成功、4=允许退款、 5=拒绝退款
			statuscomment = 0;// 评论状态：1=未评论 、2=已评论
		}
		
		int totalRecord = oorderService.Count(db_customer.getCustomerId(), statuspay, statusmethod, statusorder,
				statussend, statusrefund, statuscomment, keyword);
		int totalPage = totalRecord / (3 * msg.RECORD_SIZE) + 1;
		if ((totalRecord % (3 * msg.RECORD_SIZE) == 0) && (totalRecord > (3 * msg.RECORD_SIZE))) {
			totalPage--;
		}
		if (currentPage > totalPage || currentPage <= 0) {
			currentPage = 1;
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
		int fromIndex = (currentPage - 1) * (3 * msg.RECORD_SIZE); // 选择从第几条开始
		
		System.out.println("当前页码：totalPage" + totalPage);
		System.out.println("当前页码：totalRecord" + totalRecord);
		System.out.println("当前页码：currentPage" + currentPage);
		System.out.println("当前页码：fromIndex" + fromIndex);
		System.out.println("当前页码：firstPage" + firstPage);
		System.out.println("当前页码：lastPage" + lastPage);
		
		List list = new ArrayList();
		List list1 = new ArrayList();
		list = oorderService.Find_Desc(db_customer.getCustomerId(), statuspay, statusmethod, statusorder, statussend,
				statusrefund, statuscomment, keyword, fromIndex, (3 * msg.RECORD_SIZE));
		for (int i = 0; i < list.size(); i++) {
			OOrder db_oorder = (OOrder) list.get(i);
			a_OOrder a_oorder = order2a(db_oorder);
			list1.add(a_oorder);
		}
		
		if (list.size() < 0 || list.size() != list1.size()) {
			message = msg.order_ordernull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		message = msg.order_success;
		status = msg.status_0;
		map.put("status", status);
		map.put("message", message);
		
		data = gson.toJson(list1);
		map.put("data", data);
		map.put("page", totalPage);
		mapdata = gson.toJson(map);
		
		out.write(mapdata);
		out.flush();
		out.close();
		return;
	}
	
	/**
	 * 查看订单列表
	 * 
	 * @throws Exception
	 */
	public void doFind() throws Exception {
		
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		String status = "";
		String message = "";
		String data = "";
		int page = 0;
		String mapdata = "";
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/data; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String s1 = request.getParameter("tk");
		String s2 = request.getParameter("id");
		String s3 = request.getParameter("page");
		String s4 = request.getParameter("kw");
		
		if (s3 == null || s3.equals("") || !test.isInteger(s3)) {
			message = msg.order_illegalinput;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		int currentPage = Integer.parseInt(s3);
		
		String keyword = "";
		if (s4 == null || s4.equals("")) {
			keyword = "";
		}
		else {
			keyword = s4;
		}
		
		if (s1 == null || s1.equals("")) {
			message = msg.order_illegalinput;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		String token = s1;
		
		Customer db_customer = customerService.FindByToken(token);
		if (db_customer == null) {
			message = msg.order_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		int orderid = 0;
		if (s2 == null || s2.equals("") || !test.isInteger(s2)) {
			orderid = 0;
		}
		else {
			orderid = Integer.parseInt(s2);
		}
		
		int totalRecord = 0;
		if (orderid == 0) {//查看母订单
			totalRecord = oorderService.Count(db_customer.getCustomerId(), 0, 0, 0, 0, 0, 0, keyword);
		}
		else {//查看子订单
			totalRecord = oinfoService.Count(orderid, 0, 0, 0, 0, 0, 0, 0, 0, 0, keyword);
		}
		int totalPage = totalRecord / (3 * msg.RECORD_SIZE) + 1;
		if ((totalRecord % (3 * msg.RECORD_SIZE) == 0) && (totalRecord > (3 * msg.RECORD_SIZE))) {
			totalPage--;
		}
		if (currentPage > totalPage || currentPage <= 0) {
			currentPage = 1;
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
		int fromIndex = (currentPage - 1) * (3 * msg.RECORD_SIZE); // 选择从第几条开始
		
		System.out.println("当前页码：totalPage" + totalPage);
		System.out.println("当前页码：totalRecord" + totalRecord);
		System.out.println("当前页码：currentPage" + currentPage);
		System.out.println("当前页码：fromIndex" + fromIndex);
		System.out.println("当前页码：firstPage" + firstPage);
		System.out.println("当前页码：lastPage" + lastPage);
		
		List list = new ArrayList();
		List list1 = new ArrayList();
		if (orderid == 0) {
			list = oorderService.Find_Asc(db_customer.getCustomerId(), 0, 0, 0, 0, 0, 0, keyword, fromIndex,
					(3 * msg.RECORD_SIZE));
			for (int i = 0; i < list.size(); i++) {
				OOrder db_oorder = (OOrder) list.get(i);
				a_OOrder a_oorder = order2a(db_oorder);
				list1.add(a_oorder);
			}
		}
		else {
			list = oinfoService.Find(orderid, 0, 0, 0, 0, 0, 0, 0, 0, 0, keyword, fromIndex, (3 * msg.RECORD_SIZE));
			for (int i = 0; i < list.size(); i++) {
				OInfo db_oinfo = (OInfo) list.get(i);
				a_OInfo a_oinfo = oinfo2a(db_oinfo);
				list1.add(a_oinfo);
			}
		}
		
		if (list.size() != list1.size()) {
			message = msg.order_ordernull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		message = msg.order_success;
		status = msg.status_0;
		map.put("status", status);
		map.put("message", message);
		
		data = gson.toJson(list1);
		map.put("data", data);
		map.put("page", totalPage);
		mapdata = gson.toJson(map);
		
		out.write(mapdata);
		out.flush();
		out.close();
		return;
	}
	
	/**
	 * 测试用例
	 * 
	 * @throws Exception
	 */
	public void doFind1() throws Exception {
		
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		String status = "";
		String message = "";
		String data = "";
		int page = 0;
		String mapdata = "";
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/data; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String s1 = request.getParameter("tk");
		String s2 = request.getParameter("id");
		String s3 = request.getParameter("page");
		String s4 = request.getParameter("kw");
		
		if (s3 == null || s3.equals("") || !test.isInteger(s3)) {
			message = msg.order_illegalinput;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		int currentPage = Integer.parseInt(s3);
		
		String keyword = "";
		if (s4 == null || s4.equals("")) {
			keyword = "";
		}
		else {
			keyword = s4;
		}
		
		if (s1 == null || s1.equals("")) {
			message = msg.order_illegalinput;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		String token = s1;
		
		Customer db_customer = customerService.FindByToken(token);
		if (db_customer == null) {
			message = msg.order_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		int orderid = 0;
		if (s2 == null || s2.equals("") || !test.isInteger(s2)) {
			orderid = 0;
		}
		else {
			orderid = Integer.parseInt(s2);
		}
		
		int totalRecord = 0;
		if (orderid == 0) {
			totalRecord = oorderService.Count(db_customer.getCustomerId(), 0, 0, 0, 0, 0, 0, keyword);
		}
		else {
			totalRecord = oinfoService.Count(orderid, 0, 0, 0, 0, 0, 0, 0, 0, 0, keyword);
		}
		int totalPage = totalRecord / (3 * msg.RECORD_SIZE) + 1;
		if ((totalRecord % (3 * msg.RECORD_SIZE) == 0) && (totalRecord > (3 * msg.RECORD_SIZE))) {
			totalPage--;
		}
		if (currentPage > totalPage || currentPage <= 0) {
			currentPage = 1;
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
		int fromIndex = (currentPage - 1) * (3 * msg.RECORD_SIZE); // 选择从第几条开始
		
		System.out.println("当前页码：totalPage" + totalPage);
		System.out.println("当前页码：totalRecord" + totalRecord);
		System.out.println("当前页码：currentPage" + currentPage);
		System.out.println("当前页码：fromIndex" + fromIndex);
		System.out.println("当前页码：firstPage" + firstPage);
		System.out.println("当前页码：lastPage" + lastPage);
		
		List list = new ArrayList();
		List list1 = new ArrayList();
		if (orderid == 0) {
			list = oorderService.Find_Asc(db_customer.getCustomerId(), 0, 0, 0, 0, 0, 0, keyword, fromIndex,
					(3 * msg.RECORD_SIZE));
			for (int i = 0; i < list.size(); i++) {
				OOrder db_oorder = (OOrder) list.get(i);
				
				a_OOrder a_oorder = order2a(db_oorder);
				a_oorder.setOrderId(null);
				
				String s = gson.toJson(a_oorder);
				
				list1.add(s);
			}
		}
		else {
			list = oinfoService.Find(orderid, 0, 0, 0, 0, 0, 0, 0, 0, 0, keyword, fromIndex, (3 * msg.RECORD_SIZE));
			for (int i = 0; i < list.size(); i++) {
				if (i == 0) {
					OInfo db_oinfo = (OInfo) list.get(i);
					OOrder db_oorder = db_oinfo.getOOrder();
					a_OOrder a_oorder = order2a(db_oorder);
					a_oorder.setOrderId(null);
					
					String s = gson.toJson(a_oorder);
					
					list1.add(s);
				}
				OInfo db_oinfo = (OInfo) list.get(i);
				a_OInfo a_oinfo = oinfo2a(db_oinfo);
				a_oinfo.setOinfoId(null);
				a_oinfo.setOrderId(null);
				a_oinfo.setBatchId(null);
				String s = gson.toJson(a_oinfo);
				
				list1.add(s);
			}
		}
		
		message = msg.order_success;
		status = msg.status_1;
		map.put("status", status);
		map.put("message", message);
		
		data = gson.toJson(list1);
		test.a(data);
		
		map.put("data", data);
		map.put("page", totalPage);
		map.put("tage", Integer.toString(totalPage));
		mapdata = gson.toJson(map);
		System.out.println(data);
		out.write(data);
		out.flush();
		out.close();
		
		return;
	}
	
	public CustomerService getCustomerService() {
		return customerService;
	}
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	public OInfoService getOinfoService() {
		return oinfoService;
	}
	
	public void setOinfoService(OInfoService oinfoService) {
		this.oinfoService = oinfoService;
	}
	
	public GBatchService getGbatchService() {
		return gbatchService;
	}
	
	public void setGbatchService(GBatchService gbatchService) {
		this.gbatchService = gbatchService;
	}
	
	public GoodsService getGoodsService() {
		return goodsService;
	}
	
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	
	public CompanyService getCompanyService() {
		return companyService;
	}
	
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	public OOrderService getOorderService() {
		return oorderService;
	}
	
	public void setOorderService(OOrderService oorderService) {
		this.oorderService = oorderService;
	}
	
	public CartService getCartService() {
		return cartService;
	}
	
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	
	public Log getLogger() {
		return logger;
	}
	
}
