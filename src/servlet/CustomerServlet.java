package servlet;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import service.CAddressService;
import service.CartService;
import service.CollectService;
import service.CustomerService;
import service.OInfoService;
import service.OOrderService;
import utils.alidayuTool;
import utils.msg;
import utils.test;
import utils.sendMsg2AliServer;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import androidbeans.a_CAddress;
import androidbeans.a_Comment;
import androidbeans.a_Customer;
import bean.CAddress;
import bean.Cart;
import bean.Customer;

public class CustomerServlet extends ActionSupport {
	
	private static final Log log = LogFactory.getLog(CustomerServlet.class);
	private CustomerService customerService;
	private CAddressService caddressService;
	private CartService cartService;
	private CollectService collectService;
	private OOrderService oorderService;
	private OInfoService oinfoService;
	
	public a_Customer customer2a(Customer db_customer) {
		a_Customer a_customer = new a_Customer();
		
		a_customer.setCustomerId(db_customer.getCustomerId());
		a_customer.setCustomerToken(db_customer.getCustomerToken());
		
		return a_customer;
	}
	
	public Customer a2customer() {
		
		Customer db_customer = new Customer();
		
		db_customer.setCustomerArray("");
		db_customer.setCustomerCode("");
		db_customer.setCustomerId(null);
		db_customer.setCustomerImg("");
		db_customer.setCustomerPassword("");
		db_customer.setCustomerMail("");
		db_customer.setCustomerPhone("");
		db_customer.setCustomerToken("");
		db_customer.setThirdAccount1("");
		db_customer.setThirdAccount2("");
		db_customer.setThirdType1("");
		db_customer.setThirdType2("");
		
		return db_customer;
	}
	
	public void doLoginByToken() throws Exception {
		
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
		
		Customer temp_customer = new Customer();
		temp_customer.setCustomerToken(token);
		test.a(temp_customer);
		
		Customer db_customer = customerService.LoginByToken(temp_customer);
		System.out.println("------------>customer0:" + db_customer);
		
		if (db_customer == null) {
			status = msg.status_1;
			message = msg.customer_fail;
			
			map.put("status", status);
			map.put("message", message);
			
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		status = msg.status_0;
		message = msg.customer_success;
		
		map.put("status", status);
		map.put("message", message);
		
		if (db_customer.getCustomerArray().equals("") || db_customer.getCustomerArray() == null) {
			db_customer.setCustomerArray(test.GetCurrentTime());
		}
		else {
			db_customer.setCustomerArray(test.GetCurrentTime() + "," + db_customer.getCustomerArray());
		}
		
		db_customer = customerService.Update(db_customer);
		System.out.println("-------------->customer1:" + db_customer);
		
		a_Customer a_customer = new a_Customer();
		a_customer.setCustomerId(db_customer.getCustomerId());
		a_customer.setCustomerToken(db_customer.getCustomerToken());
		
		data = gson.toJson(a_customer);
		map.put("data", data);
		
		int num1 = 0;// 购物车数目
		int length = cartService.Count_CustomerId(db_customer.getCustomerId(), "");
		List list = cartService.Find_CustomerId(db_customer.getCustomerId(), "", 0, length);
		for (int i = 0; i < list.size(); i++) {
			Cart cart = (Cart) list.get(i);
			num1 = cart.getGoodsNum() + num1;
		}
		
		int num2 = oorderService.Count(db_customer.getCustomerId(), 1, 0, 0, 0, 0, 0, "");// 未支付
		int num3 = oorderService.Count(db_customer.getCustomerId(), 0, 0, 0, 1, 0, 0, "");// 待收货
		int num4 = oorderService.Count(db_customer.getCustomerId(), 0, 0, 0, 0, 0, 1, "");// 待评价
		
		map.put("num1", num1);
		map.put("num2", num2);
		map.put("num3", num3);
		map.put("num4", num4);
		
		mapdata = gson.toJson(map);
		
		out.write(mapdata);
		out.flush();
		out.close();
		return;
	}
	
	public void doLoginByPassword() throws Exception {
		
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
		String phone = request.getParameter("pho");
		String password = request.getParameter("pwd");
		
		Customer temp_customer = new Customer();
		temp_customer.setCustomerPhone(phone);
		temp_customer.setCustomerPassword(password);
		System.out.println(temp_customer);
		
		Customer db_customer = customerService.LoginByPassword(temp_customer);
		System.out.println(db_customer);
		
		if (db_customer == null) {
			status = msg.status_1;
			message = msg.customer_fail;
			
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		String db_token = UUID.randomUUID().toString();
		
		if (db_customer.getCustomerArray().equals("") || db_customer.getCustomerArray() == null) {
			db_customer.setCustomerArray(test.GetCurrentTime());
		}
		else {
			db_customer.setCustomerArray(test.GetCurrentTime() + "," + db_customer.getCustomerArray());
		}
		//		db_customer.setCustomerToken(db_token);
		db_customer = customerService.Update(db_customer);
		
		status = msg.status_0;
		message = msg.customer_success;
		
		map.put("status", status);
		map.put("message", message);
		
		a_Customer a_customer = new a_Customer();
		a_customer.setCustomerId(db_customer.getCustomerId());
		a_customer.setCustomerToken(db_customer.getCustomerToken());
		
		data = gson.toJson(a_customer);
		map.put("data", data);
		
		int num1 = 0;// 购物车数目
		int length = cartService.Count_CustomerId(db_customer.getCustomerId(), "");
		List list = cartService.Find_CustomerId(db_customer.getCustomerId(), "", 0, length);
		for (int i = 0; i < list.size(); i++) {
			Cart cart = (Cart) list.get(i);
			num1 = cart.getGoodsNum() + num1;
		}
		
		int num2 = oorderService.Count(db_customer.getCustomerId(), 1, 0, 0, 0, 0, 0, "");// 未支付
		int num3 = oorderService.Count(db_customer.getCustomerId(), 0, 0, 0, 1, 0, 0, "");// 待收货
		int num4 = oorderService.Count(db_customer.getCustomerId(), 0, 0, 0, 0, 0, 1, "");// 待评价
		
		map.put("num1", num1);
		map.put("num2", num2);
		map.put("num3", num3);
		map.put("num4", num4);
		
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
		String password = request.getParameter("pwd");
		String phone = request.getParameter("pho");
		String code = request.getParameter("cod");
		
		if (phone == null || phone.equals("")) {
			
			status = msg.status_1;
			message = msg.customer_illegalinput;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		Customer temp_customer = new Customer();
		temp_customer.setCustomerPhone(phone);
		temp_customer.setCustomerImg(msg.defaultImg_customer);
		temp_customer.setCustomerArray(test.GetCurrentTime());
		temp_customer.setCustomerCode("");
		temp_customer.setCustomerImg("");
		temp_customer.setCustomerPassword("");
		temp_customer.setCustomerMail("");
		temp_customer.setCustomerToken("");
		temp_customer.setThirdAccount1("");
		temp_customer.setThirdAccount2("");
		temp_customer.setThirdType1("");
		temp_customer.setThirdType2("");
		test.a(temp_customer);
		
		Customer db_customer = customerService.FindByPhone(temp_customer);
		System.out.println("-------------->customer0:" + db_customer);
		
		if (db_customer == null || code == null || code.equals("")) {
			String db_code = RandomStringUtils.randomNumeric(4);// 生成随机数字字符串
			temp_customer.setCustomerCode(db_code);
			db_customer = customerService.Add(temp_customer);
			System.out.println("-------------->customer1:" + db_customer);
			
			sendMsg2AliServer sendmsg2aliserver = new sendMsg2AliServer();
			sendmsg2aliserver.GetPhoneNum2SendMsg(alidayuTool.getAliMsg_1(db_code), phone);
			
			status = msg.status_0;
			message = msg.customer_codewaiting;
			data = gson.toJson(db_customer.getCustomerCode());
			
			map.put("status", status);
			map.put("message", message);
			map.put("data", data);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		if (code != null && !code.equals("") && !db_customer.getCustomerCode().equals(code)) {
			
			String db_code = RandomStringUtils.randomNumeric(4);
			db_customer.setCustomerCode(db_code);
			db_customer = customerService.Update(db_customer);
			System.out.println("-------------->customer3:" + db_customer);
			
			sendMsg2AliServer sendmsg2aliserver = new sendMsg2AliServer();
			sendmsg2aliserver.GetPhoneNum2SendMsg(alidayuTool.getAliMsg_1(db_code), phone);
			
			status = msg.status_1;
			message = msg.customer_codewrong;
			data = gson.toJson(db_customer.getCustomerCode());
			
			map.put("status", status);
			map.put("message", message);
			map.put("data", data);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		if (password == null || password.equals("")) {
			
			String db_code = RandomStringUtils.randomNumeric(4);
			db_customer.setCustomerCode(db_code);
			db_customer = customerService.Update(db_customer);
			System.out.println("-------------->customer3:" + db_customer);
			
			sendMsg2AliServer sendmsg2aliserver = new sendMsg2AliServer();
			sendmsg2aliserver.GetPhoneNum2SendMsg(alidayuTool.getAliMsg_1(db_code), phone);
			
			status = msg.status_1;
			message = msg.customer_illegalinput;
			data = gson.toJson(db_customer.getCustomerCode());
			
			map.put("status", status);
			map.put("message", message);
			map.put("data", data);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		String db_token = UUID.randomUUID().toString();
		String db_code = RandomStringUtils.randomAlphanumeric(5);
		db_customer.setCustomerToken(db_token);
		db_customer.setCustomerCode(db_code);
		db_customer.setCustomerPassword(password);
		db_customer = customerService.Update(db_customer);
		System.out.println("-------------->customer2:" + db_customer);
		
		a_Customer a_customer = new a_Customer();
		a_customer.setCustomerId(db_customer.getCustomerId());
		a_customer.setCustomerToken(db_customer.getCustomerToken());
		
		status = msg.status_0;
		message = msg.customer_success;
		data = gson.toJson(a_customer);
		
		map.put("status", status);
		map.put("message", message);
		map.put("data", data);
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
		String token = request.getParameter("tk");
		
		Customer db_customer = customerService.FindByToken(token);
		System.out.println(db_customer);
		
		if (db_customer == null) {
			status = msg.status_1;
			message = msg.customer_fail;
			
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		status = msg.status_0;
		message = msg.customer_success;
		
		map.put("status", status);
		map.put("message", message);
		
		a_Customer a_customer = new a_Customer(db_customer.getCustomerId(), db_customer.getCustomerToken());
		data = gson.toJson(a_customer);
		map.put("data", data);
		
		mapdata = gson.toJson(map);
		
		out.write(mapdata);
		out.flush();
		out.close();
		return;
	}
	
	public void doForgetPassword() throws Exception {
		
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
		String phone = request.getParameter("pho");
		String code = request.getParameter("cod");
		String password = request.getParameter("pwd");
		
		Customer temp_customer = new Customer();
		temp_customer.setCustomerPhone(phone);
		
		Customer db_customer = customerService.FindByPhone(temp_customer);
		System.out.println("-------------->customer0:" + db_customer);
		
		if (db_customer == null) {
			String db_code = RandomStringUtils.randomNumeric(4);// 生成随机数字字符串
			temp_customer.setCustomerCode(db_code);
			db_customer = customerService.Add(temp_customer);
			System.out.println("-------------->customer1:" + db_customer);
			
			sendMsg2AliServer sendmsg2aliserver = new sendMsg2AliServer();
			sendmsg2aliserver.GetPhoneNum2SendMsg(alidayuTool.getAliMsg_1(db_code), phone);
			
			status = msg.status_1;
			message = msg.customer_customernull;
			data = gson.toJson(db_customer.getCustomerCode());
			
		}
		else {
			if (db_customer.getCustomerCode().equals(code)) {
				
				String db_token = UUID.randomUUID().toString();
				String db_code = RandomStringUtils.randomAlphanumeric(5);
				db_customer.setCustomerToken(db_token);
				db_customer.setCustomerCode(db_code);
				db_customer.setCustomerPassword(password);
				db_customer = customerService.Update(db_customer);
				System.out.println("-------------->customer2:" + db_customer);
				
				a_Customer a_customer = new a_Customer(db_customer.getCustomerId(), db_customer.getCustomerToken());
				status = msg.status_0;
				message = msg.customer_success;
				data = gson.toJson(a_customer);
				
			}
			else {
				String db_code = RandomStringUtils.randomNumeric(4);
				db_customer.setCustomerCode(db_code);
				db_customer = customerService.Update(db_customer);
				System.out.println("-------------->customer3:" + db_customer);
				
				sendMsg2AliServer sendmsg2aliserver = new sendMsg2AliServer();
				sendmsg2aliserver.GetPhoneNum2SendMsg(alidayuTool.getAliMsg_1(db_code), phone);
				
				status = msg.status_0;
				message = msg.customer_fail;
				data = gson.toJson(db_customer.getCustomerCode());
			}
		}
		
		map.put("status", status);
		map.put("message", message);
		map.put("data", data);
		mapdata = gson.toJson(map);
		
		out.write(mapdata);
		out.flush();
		out.close();
		return;
	}
	
	public void doUpdate() throws Exception {
		
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
		String customerdata = request.getParameter("jn");
		
		a_Customer a_temp_customer = gson.fromJson(customerdata, a_Customer.class);
		
		Customer db_customer = customerService.View(a_temp_customer.getCustomerId());
		db_customer.setCustomerToken(a_temp_customer.getCustomerToken());
		
		db_customer = customerService.Update(db_customer);
		System.out.println("------->customer:" + db_customer);
		
		if (db_customer == null) {
			status = msg.status_1;
			message = msg.customer_customernull;
			
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		status = msg.status_0;
		message = msg.customer_success;
		
		map.put("status", status);
		map.put("message", message);
		
		a_Customer a_customer = new a_Customer(db_customer.getCustomerId(), db_customer.getCustomerToken());
		data = gson.toJson(a_customer);
		map.put("data", data);
		
		mapdata = gson.toJson(map);
		
		out.write(mapdata);
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
	
	public CAddressService getCaddressService() {
		return caddressService;
	}
	
	public void setCaddressService(CAddressService caddressService) {
		this.caddressService = caddressService;
	}
	
	public CartService getCartService() {
		return cartService;
	}
	
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	
	public CollectService getCollectService() {
		return collectService;
	}
	
	public void setCollectService(CollectService collectService) {
		this.collectService = collectService;
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
}
