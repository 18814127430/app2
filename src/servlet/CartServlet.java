package servlet;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import service.CartService;
import service.CustomerService;
import service.GoodsService;
import utils.msg;
import utils.serialMaker;
import utils.test;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import alipay.util.AlipayCore;
import androidbeans.a_Cart;
import androidbeans.a_Cart;
import androidbeans.a_OInfo;
import androidbeans.a_OOrder;
import bean.Admin;
import bean.Cart;
import bean.Cart;
import bean.Customer;
import bean.GBatch;
import bean.Goods;

public class CartServlet extends ActionSupport {
	
	private CartService cartService;
	private CustomerService customerService;
	private GoodsService goodsService;
	
	public a_Cart cart2a(Cart cart) {
		a_Cart a_cart = new a_Cart();
		
		a_cart.setCartId(cart.getCartId());
		a_cart.setCustomerId(cart.getCustomer().getCustomerId());
		a_cart.setGoodName(cart.getGoods().getGoodsName());
		a_cart.setGoodPrice(cart.getGoods().getMoneyNew());
		a_cart.setGoodsId(cart.getGoods().getGoodsId());
		
		a_cart.setGoodsImg(cart.getGoods().getImg7());//查看购物车  390*390
		
		a_cart.setGoodsNum(cart.getGoodsNum());
		a_cart.setGoodsRemark(cart.getGoods().getGoodsRemark());
		a_cart.setMoneyDeliver(cart.getGoods().getMoneyDeliver());
		
		return a_cart;
	}
	
	public Cart a2cart(a_Cart a_cart, Customer db_customer, Goods db_goods) {
		
		Cart db_cart = new Cart();
		
		db_cart.setCartDate(test.String2Timestamp(test.GetCurrentTime()));
		db_cart.setCartId(a_cart.getCartId());
		db_cart.setCustomer(db_customer);
		db_cart.setGoods(db_goods);
		db_cart.setGoodsNum(a_cart.getGoodsNum());
		
		return db_cart;
		
	}
	
	public void doAddMore() throws Exception {
		
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
		
		String cartdata;
		if (s1 == null || s1.equals("")) {
			message = msg.cart_jsonnull;
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
			cartdata = s1;
		}
		
		String token;
		if (s2 == null || s2.equals("")) {
			message = msg.cart_jsonnull;
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
			token = s2;
		}
		
		System.out.println("string1:" + s1 + "------------cartdata:" + cartdata);
		System.out.println("string2:" + s2 + "------------token:" + token);
		
		Customer db_customer = customerService.FindByToken(token);
		if (db_customer == null) {
			message = msg.cart_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		a_Cart a_cart = gson.fromJson(cartdata, a_Cart.class);
		
		System.out.println("------->token:" + token);
		System.out.println("------->cartdata:" + cartdata);
		System.out.println("------->db_customer:" + db_customer);
		System.out.println("------->a_cart:" + a_cart);
		
		if (a_cart == null) {
			message = msg.cart_jsonnull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		Goods db_goods = goodsService.View(a_cart.getGoodsId());
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
		
		if (a_cart.getCustomerId().intValue() != db_customer.getCustomerId().intValue()) {
			message = msg.cart_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		Cart db_cart = a2cart(a_cart, db_customer, db_goods);
		
		db_cart = cartService.AddMore(db_cart);
		
		System.out.println("------->db_cart:" + db_cart);
		
		if (db_cart == null) {
			message = msg.cart_fail;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		a_cart = cart2a(db_cart);
		
		message = msg.cart_success;
		status = msg.status_0;
		map.put("status", status);
		map.put("message", message);
		
		data = gson.toJson(a_cart);
		map.put("data", data);
		mapdata = gson.toJson(map);
		
		out.write(mapdata);
		out.flush();
		out.close();
		return;
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
		
		String listdata;
		if (s1 == null || s1.equals("")) {
			message = msg.cart_jsonnull;
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
			listdata = s1;
			AlipayCore.logResult(s1, "servlet_cartservlet_doAdd_listdata");
		}
		
		String token;
		if (s2 == null || s2.equals("")) {
			message = msg.cart_jsonnull;
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
			token = s2;
		}
		
		System.out.println("string1:" + s1 + "------------listdata:" + listdata);
		System.out.println("string2:" + s2 + "------------token:" + token);
		
		Customer db_customer = customerService.FindByToken(token);
		if (db_customer == null) {
			message = msg.cart_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		List list = gson.fromJson(listdata, java.util.ArrayList.class);
		test.a("-----------------------------------------------------");
		for (int i = 0; i < list.size(); i++) {
			test.a(list.get(i));
		}
		test.a("-----------------------------------------------------");
		List list1 = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			String s = (String) list.get(i);
			a_Cart a_cart = gson.fromJson(s, a_Cart.class);
			
			System.out.println("------->token:" + token);
			System.out.println("------->s:" + s);
			System.out.println("------->db_customer:" + db_customer);
			System.out.println("------->a_cart:" + a_cart);
			
			if (a_cart == null) {
				message = msg.cart_jsonnull;
				status = msg.status_1;
				map.put("status", status);
				map.put("message", message);
				mapdata = gson.toJson(map);
				
				out.write(mapdata);
				out.flush();
				out.close();
				return;
			}
			
			Goods db_goods = goodsService.View(a_cart.getGoodsId());
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
			
			if (a_cart.getCustomerId().intValue() != db_customer.getCustomerId().intValue()) {
				message = msg.cart_customerwrong;
				status = msg.status_1;
				map.put("status", status);
				map.put("message", message);
				mapdata = gson.toJson(map);
				
				out.write(mapdata);
				out.flush();
				out.close();
				return;
			}
			
			Cart db_cart = a2cart(a_cart, db_customer, db_goods);
			
			if (db_cart.getGoodsNum() == 0) {
				cartService.Delete(db_cart);
			}
			else {
				db_cart = cartService.Add(db_cart);
			}
			
			System.out.println("------->db_cart:" + db_cart);
			
			if (db_cart == null) {
				message = msg.cart_fail;
				status = msg.status_1;
				map.put("status", status);
				map.put("message", message);
				mapdata = gson.toJson(map);
				
				out.write(mapdata);
				out.flush();
				out.close();
				return;
			}
			
			a_cart = cart2a(db_cart);
			
			data = gson.toJson(a_cart);
			list1.add(data);
		}
		
		if (list.size() != list1.size()) {
			message = msg.cart_fail;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		message = msg.cart_success;
		status = msg.status_0;
		map.put("status", status);
		map.put("message", message);
		
		data = gson.toJson(list1);
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
		
		int cartid;
		if (s1 == null || s1.equals("") || !test.isInteger(s1)) {
			message = msg.cart_illegalinput;
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
			cartid = Integer.parseInt(s1);
		}
		
		String token;
		if (s2 == null || s2.equals("")) {
			token = "";
		}
		else {
			token = s2;
		}
		
		System.out.println("string1:" + s1 + "------------cartid:" + cartid);
		System.out.println("string2:" + s2 + "------------token:" + token);
		
		Customer db_customer = customerService.FindByToken(token);
		if (db_customer == null) {
			message = msg.cart_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		Cart db_cart = cartService.View(cartid);
		System.out.println("------->token:" + token);
		System.out.println("------->cartid:" + cartid);
		System.out.println("------->db_customer:" + db_customer);
		System.out.println("------->object:" + db_cart);
		
		if (db_cart == null) {
			message = msg.cart_cartnull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		if (db_cart.getCustomer().getCustomerId().intValue() != db_customer.getCustomerId().intValue()) {
			message = msg.cart_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		if (!cartService.Delete(db_cart)) {
			message = msg.cart_fail;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
		}
		
		message = msg.cart_success;
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
			message = msg.cart_illegalinput;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		String[] cartid = s1.replace(" ", "").split(",");
		
		String token;
		if (s2 == null || s2.equals("")) {
			token = "";
		}
		else {
			token = s2;
		}
		
		System.out.println("string1:" + s1 + "------------cartid:" + cartid);
		System.out.println("string2:" + s2 + "------------token:" + token);
		
		Customer db_customer = customerService.FindByToken(token);
		if (db_customer == null) {
			message = msg.cart_customerwrong;
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
		for (int i = 0; i < cartid.length; i++) {
			
			test.a(cartid[i]);
			
			if (cartid[i].equals(""))
				continue;
			
			if (!test.isInteger(cartid[i])) {
				message = msg.cart_illegalinput;
				status = msg.status_1;
				map.put("status", status);
				map.put("message", message);
				mapdata = gson.toJson(map);
				
				out.write(mapdata);
				out.flush();
				out.close();
				return;
			}
			
			int num = Integer.parseInt(cartid[i]);
			Cart db_cart = cartService.View(num);
			
			if (db_cart == null) {
				message = msg.cart_cartnull;
				status = msg.status_1;
				map.put("status", status);
				map.put("message", message);
				mapdata = gson.toJson(map);
				
				out.write(mapdata);
				out.flush();
				out.close();
				return;
			}
			
			if (db_cart.getCustomer().getCustomerId().intValue() != db_customer.getCustomerId().intValue()) {
				message = msg.cart_customerwrong;
				status = msg.status_1;
				map.put("status", status);
				map.put("message", message);
				mapdata = gson.toJson(map);
				
				out.write(mapdata);
				out.flush();
				out.close();
				return;
			}
			
			if (!cartService.Delete(db_cart)) {
				message = msg.cart_fail;
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
		
		test.a(count + "----" + cartid.length);
		
		if (count != cartid.length) {
			message = msg.cart_delete;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		message = msg.cart_success;
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
		
		int cartid;
		if (s1 == null || s1.equals("") || !test.isInteger(s1)) {
			message = msg.cart_illegalinput;
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
			cartid = Integer.parseInt(s1);
		}
		
		String token;
		if (s2 == null || s2.equals("")) {
			token = "";
		}
		else {
			token = s2;
		}
		
		System.out.println("string1:" + s1 + "------------cartid:" + cartid);
		System.out.println("string2:" + s2 + "------------token:" + token);
		
		Customer db_customer = customerService.FindByToken(token);
		if (db_customer == null) {
			message = msg.cart_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		Cart db_cart = cartService.View(cartid);
		System.out.println("------->token:" + token);
		System.out.println("------->cartid:" + cartid);
		System.out.println("------->db_customer:" + db_customer);
		System.out.println("------->object:" + db_cart);
		
		if (db_cart == null) {
			message = msg.cart_cartnull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		if (db_cart.getCustomer().getCustomerId().intValue() != db_customer.getCustomerId().intValue()) {
			message = msg.cart_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		message = msg.cart_success;
		status = msg.status_0;
		map.put("status", status);
		map.put("message", message);
		
		a_Cart a_cart = cart2a(db_cart);
		
		data = gson.toJson(a_cart);
		test.a(data);
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
			message = msg.cart_customerwrong;
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
		int totalRecord = cartService.Count_CustomerId(db_customer.getCustomerId(), keyword);
		int totalPage = totalRecord / (4 * msg.RECORD_SIZE) + 1;
		if ((totalRecord % (4 * msg.RECORD_SIZE) == 0) && (totalRecord > (4 * msg.RECORD_SIZE))) {
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
		
		int fromIndex = (currentPage - 1) * (4 * msg.RECORD_SIZE); // 选择从第几条开始
		int toIndex = Math.min(fromIndex + (4 * msg.RECORD_SIZE), totalRecord);// 调用Math.min函数取目的数
		
		System.out.println("当前页码：totalPage" + totalPage);
		System.out.println("当前页码：totalRecord" + totalRecord);
		System.out.println("当前页码：currentPage" + currentPage);
		System.out.println("当前页码：fromIndex" + fromIndex);
		System.out.println("当前页码：toIndex" + toIndex);
		System.out.println("当前页码：firstPage" + firstPage);
		System.out.println("当前页码：lastPage" + lastPage);
		
		List<?> list = cartService.Find_CustomerId(db_customer.getCustomerId(), keyword, fromIndex, (4 * msg.RECORD_SIZE));// 可优化
		
		int total = 0;
		List list1 = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Cart db_cart = (Cart) list.get(i);
			
			total = total + db_cart.getGoodsNum();
			
			a_Cart a_cart = cart2a(db_cart);
			
			list1.add(a_cart);
		}
		
		if (list.size() == 0) {
			message = msg.cart_cartnull;
			status = msg.status_0;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		message = msg.cart_success;
		status = msg.status_0;
		map.put("status", status);
		map.put("message", message);
		data = gson.toJson(list1);
		map.put("data", data);
		map.put("page", totalPage);
		map.put("total", total);
		
		mapdata = gson.toJson(map);
		
		out.write(mapdata);
		out.flush();
		out.close();
		return;
	}
	
	public void doFind2() throws Exception {
		
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
			message = msg.cart_customerwrong;
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
		int totalRecord = cartService.Count_CustomerId(db_customer.getCustomerId(), keyword);
		int totalPage = totalRecord / (4 * msg.RECORD_SIZE) + 1;
		if ((totalRecord % (4 * msg.RECORD_SIZE) == 0) && (totalRecord > (4 * msg.RECORD_SIZE))) {
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
		
		int fromIndex = (currentPage - 1) * (4 * msg.RECORD_SIZE); // 选择从第几条开始
		int toIndex = Math.min(fromIndex + (4 * msg.RECORD_SIZE), totalRecord);// 调用Math.min函数取目的数
		
		System.out.println("当前页码：totalPage" + totalPage);
		System.out.println("当前页码：totalRecord" + totalRecord);
		System.out.println("当前页码：currentPage" + currentPage);
		System.out.println("当前页码：fromIndex" + fromIndex);
		System.out.println("当前页码：toIndex" + toIndex);
		System.out.println("当前页码：firstPage" + firstPage);
		System.out.println("当前页码：lastPage" + lastPage);
		
		List<?> list = cartService.Find_CustomerId(db_customer.getCustomerId(), keyword, fromIndex, (4 * msg.RECORD_SIZE));// 可优化
		
		List list1 = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Cart db_cart = (Cart) list.get(i);
			
			a_Cart a_cart = cart2a(db_cart);
			a_cart.setCartId(null);
			String s = gson.toJson(a_cart);
			
			list1.add(s);
		}
		
		if (list.size() == 0) {
			message = msg.cart_cartnull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		message = msg.cart_success;
		status = msg.status_0;
		map.put("status", status);
		map.put("message", message);
		data = gson.toJson(list1);
		System.out.println(data);
		map.put("data", data);
		map.put("page", totalPage);
		
		mapdata = gson.toJson(map);
		
		out.write(mapdata);
		out.flush();
		out.close();
		return;
	}
	
	public CartService getCartService() {
		return cartService;
	}
	
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
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
