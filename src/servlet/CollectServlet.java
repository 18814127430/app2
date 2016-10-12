package servlet;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import service.CollectService;
import service.CustomerService;
import service.GoodsService;
import utils.msg;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import androidbeans.a_Collect;
import bean.Collect;
import bean.Customer;
import bean.Goods;
import utils.test;

public class CollectServlet extends ActionSupport {
	
	private CollectService collectService;
	private CustomerService customerService;
	private GoodsService goodsService;
	
	public a_Collect collect2a(Collect db_collect) {
		a_Collect a_collect = new a_Collect();
		
		a_collect.setCollectId(db_collect.getCollectId());
		a_collect.setCustomerId(db_collect.getCustomer().getCustomerId());
		
		a_collect.setGoodImg(db_collect.getGoods().getImg8());//收藏:pic_item_good 200*200
		
		a_collect.setGoodName(db_collect.getGoods().getGoodsName());
		a_collect.setGoodPrice(db_collect.getGoods().getMoneyNew());
		a_collect.setGoodsId(db_collect.getGoods().getGoodsId());
		a_collect.setGoodWord(db_collect.getGoods().getGoodsDescription());
		
		return a_collect;
	}
	
	public Collect a2collect(a_Collect a_collect, Customer db_customer, Goods db_goods) {
		
		Collect db_collect = new Collect();
		
		db_collect.setCollectDate(test.String2Timestamp(test.GetCurrentTime()));
		db_collect.setCollectId(a_collect.getCollectId());
		db_collect.setCustomer(db_customer);
		db_collect.setGoods(db_goods);
		
		return db_collect;
		
	}
	
	public void doAdd() throws Exception {
		
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
		String s1 = request.getParameter("jn");
		String s2 = request.getParameter("tk");
		
		String collectdata;
		if (s1 == null || s1.equals("")) {
			collectdata = "";
		}
		else {
			collectdata = s1;
		}
		
		String token;
		if (s2 == null || s2.equals("")) {
			token = "";
		}
		else {
			token = s2;
		}
		
		System.out.println("string1:" + s1 + "------------collectdata:" + collectdata);
		System.out.println("string2:" + s2 + "------------token:" + token);
		
		Customer db_customer = customerService.FindByToken(token);
		if (db_customer == null) {
			message = msg.collect_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		a_Collect a_collect = gson.fromJson(collectdata, a_Collect.class);
		System.out.println("------->token:" + token);
		System.out.println("------->collectdata:" + collectdata);
		System.out.println("------->db_customer:" + db_customer);
		System.out.println("------->object:" + a_collect);
		
		if (a_collect == null) {
			message = msg.collect_jsonnull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		Goods db_goods = goodsService.View(a_collect.getGoodsId());
		if (db_goods == null) {
			message = msg.goods_goodsnull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		if (a_collect.getCustomerId().intValue() != db_customer.getCustomerId().intValue()) {
			message = msg.collect_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		Collect db_collect = a2collect(a_collect, db_customer, db_goods);
		
		System.out.println("------->db_collect:" + db_collect);
		
		db_collect = collectService.Add(db_collect);
		System.out.println("------->db_collect:" + db_collect);
		
		if (db_collect == null) {
			message = msg.collect_fail;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			
			mapdata = gson.toJson(map);
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		message = msg.collect_success;
		status = msg.status_0;
		map.put("status", status);
		map.put("message", message);
		
		a_collect = collect2a(db_collect);
		
		data = gson.toJson(a_collect);
		map.put("data", data);
		mapdata = gson.toJson(map);
		
		out.write(mapdata);
		out.flush();
		out.close();
		return;
	}
	
	public void doDelete1() throws Exception {
		
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
		String s1 = request.getParameter("id");
		String s2 = request.getParameter("tk");
		
		int collectid;
		if (s1 == null || s1.equals("") || !test.isInteger(s1)) {
			message = msg.collect_illegalinput;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		else {
			collectid = Integer.parseInt(s1);
		}
		
		String token;
		if (s2 == null || s2.equals("")) {
			token = "";
		}
		else {
			token = s2;
		}
		
		System.out.println("string1:" + s1 + "------------collectid:" + collectid);
		System.out.println("string2:" + s2 + "------------token:" + token);
		
		Customer db_customer = customerService.FindByToken(token);
		if (db_customer == null) {
			message = msg.collect_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		Collect db_collect = collectService.View(collectid);
		System.out.println("------->token:" + token);
		System.out.println("------->collectid:" + collectid);
		System.out.println("------->db_customer:" + db_customer);
		System.out.println("------->object:" + db_collect);
		
		if (db_collect == null) {
			message = msg.collect_collectnull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		if (db_collect.getCustomer().getCustomerId().intValue() != db_customer.getCustomerId().intValue()) {
			message = msg.collect_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		if (!collectService.Delete(db_collect)) {
			message = msg.collect_fail;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		message = msg.collect_success;
		status = msg.status_0;
		map.put("status", status);
		map.put("message", message);
		mapdata = gson.toJson(map);
		
		out.write(mapdata);
		out.flush();
		out.close();
		return;
	}
	
	public void doDelete2() throws Exception {
		
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
		String s1 = request.getParameter("il");
		String s2 = request.getParameter("tk");
		
		if (s1 == null || s1.equals("")) {
			message = msg.collect_illegalinput;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		String[] collectid = s1.replace(" ", "").split(",");
		
		String token;
		if (s2 == null || s2.equals("")) {
			token = "";
		}
		else {
			token = s2;
		}
		
		System.out.println("string1:" + s1 + "------------collectid:" + collectid);
		System.out.println("string2:" + s2 + "------------token:" + token);
		
		Customer db_customer = customerService.FindByToken(token);
		if (db_customer == null) {
			message = msg.collect_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		int count = 0;
		for (int i = 0; i < collectid.length; i++) {
			
			test.a(collectid[i]);
			
			if (collectid[i].equals(""))
				continue;
			
			if (!test.isInteger(collectid[i])) {
				message = msg.collect_illegalinput;
				status = msg.status_1;
				map.put("status", status);
				map.put("message", message);
				mapdata = gson.toJson(map);
				
				out.write(mapdata);
				out.flush();
				out.close();
				return;
			}
			
			int num = Integer.parseInt(collectid[i]);
			Collect db_collect = collectService.View(num);
			
			if (db_collect == null) {
				message = msg.collect_collectnull;
				status = msg.status_1;
				map.put("status", status);
				map.put("message", message);
				mapdata = gson.toJson(map);
				
				out.write(mapdata);
				out.flush();
				out.close();
				return;
			}
			
			if (db_collect.getCustomer().getCustomerId().intValue() != db_customer.getCustomerId().intValue()) {
				message = msg.collect_customerwrong;
				status = msg.status_1;
				map.put("status", status);
				map.put("message", message);
				mapdata = gson.toJson(map);
				
				out.write(mapdata);
				out.flush();
				out.close();
				return;
			}
			
			if (!collectService.Delete(db_collect)) {
				message = msg.collect_fail;
				status = msg.status_1;
				map.put("status", status);
				map.put("message", message);
				out.write(mapdata);
				out.flush();
				out.close();
				return;
			}
			count++;
		}
		
		test.a(count + "----" + collectid.length);
		
		if (count != collectid.length) {
			message = msg.collect_delete;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		message = msg.collect_success;
		status = msg.status_0;
		map.put("status", status);
		map.put("message", message);
		mapdata = gson.toJson(map);
		
		out.write(mapdata);
		out.flush();
		out.close();
		return;
	}
	
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
		String s1 = request.getParameter("id");
		String s2 = request.getParameter("tk");
		
		int collectid;
		if (s1 == null || s1.equals("") || !test.isInteger(s1)) {
			message = msg.collect_illegalinput;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		else {
			collectid = Integer.parseInt(s1);
		}
		
		String token;
		if (s2 == null || s2.equals("")) {
			token = "";
		}
		else {
			token = s2;
		}
		
		System.out.println("string1:" + s1 + "------------collectid:" + collectid);
		System.out.println("string2:" + s2 + "------------token:" + token);
		
		Customer db_customer = customerService.FindByToken(token);
		if (db_customer == null) {
			message = msg.collect_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		Collect db_collect = collectService.View(collectid);
		System.out.println("------->token:" + token);
		System.out.println("------->collectid:" + collectid);
		System.out.println("------->db_customer:" + db_customer);
		System.out.println("------->object:" + db_collect);
		
		if (db_collect == null) {
			message = msg.collect_collectnull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		if (db_collect.getCustomer().getCustomerId().intValue() != db_customer.getCustomerId().intValue()) {
			message = msg.collect_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		message = msg.collect_success;
		status = msg.status_0;
		map.put("status", status);
		map.put("message", message);
		
		a_Collect a_collect = collect2a(db_collect);
		
		data = gson.toJson(a_collect);
		map.put("data", data);
		mapdata = gson.toJson(map);
		
		out.write(mapdata);
		out.flush();
		out.close();
		return;
	}
	
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
		String s1 = request.getParameter("kw");
		String s2 = request.getParameter("page");
		String s3 = request.getParameter("tk");
		
		String keyword;
		if (s1 == null || s1.equals("")) {
			keyword = "";
		}
		else {
			keyword = s1;
		}
		
		int currentPage;
		if (s2 == null || s2.equals("") || !test.isInteger(s2)) {
			currentPage = 1;
		}
		else {
			currentPage = Integer.parseInt(s2);
		}
		
		String token;
		if (s3 == null || s3.equals("")) {
			token = "";
		}
		else {
			token = s3;
		}
		
		System.out.println("string1:" + s1 + "------------keyword:" + keyword);
		System.out.println("string2:" + s2 + "------------currentPage:" + currentPage);
		System.out.println("string3:" + s3 + "------------token:" + token);
		
		Customer db_customer = customerService.FindByToken(token);
		if (db_customer == null) {
			message = msg.collect_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		if (keyword == null || keyword == "")
			keyword = "";
		
		int firstPage = 1;
		int lastPage = 1;
		int totalRecord = collectService.Count_CustomerId(db_customer.getCustomerId(), keyword);
		int totalPage = totalRecord / msg.RECORD_SIZE + 1;
		if ((totalRecord % msg.RECORD_SIZE == 0) && (totalRecord > msg.RECORD_SIZE)) {
			totalPage--;
		}
		if (currentPage > totalPage) {
			currentPage = 1;
		}
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
		
		System.out.println("当前页码：totalPage" + totalPage);
		System.out.println("当前页码：totalRecord" + totalRecord);
		System.out.println("当前页码：currentPage" + currentPage);
		System.out.println("当前页码：fromIndex" + fromIndex);
		System.out.println("当前页码：toIndex" + toIndex);
		System.out.println("当前页码：firstPage" + firstPage);
		System.out.println("当前页码：lastPage" + lastPage);
		
		List<?> list = collectService.Find_CustomerId(db_customer.getCustomerId(), keyword, fromIndex, msg.RECORD_SIZE);// 可优化
		List list1 = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Collect db_collect = (Collect) list.get(i);
			
			a_Collect a_collect = collect2a(db_collect);
			
			list1.add(a_collect);
		}
		
		if (list.size() == 0) {
			message = msg.collect_collectnull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		message = msg.collect_success;
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
	
	public CollectService getCollectService() {
		return collectService;
	}
	
	public void setCollectService(CollectService collectService) {
		this.collectService = collectService;
	}
	
	public CustomerService getCustomerService() {
		return customerService;
	}
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	public GoodsService getGoodsService() {
		return goodsService;
	}
	
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
}
