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

import net.sf.json.JSONObject;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import service.CAddressService;
import service.CWordsService;
import service.CustomerService;
import utils.msg;
import utils.test;
import utils.SendMsg2AliServer;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.CAddress;
import bean.Customer;

public class CustomerServlet extends ActionSupport {

	private static final Log log = LogFactory.getLog(CustomerServlet.class);
	private CustomerService customerService;// 业务层对象
	private CAddressService caddressService;// 业务层对象
	private CWordsService cwordsService;// 业务层对象
	
	private Gson gson = new Gson();
	private Map<String, String> map = new HashMap<String, String>();
	private String json = "";
	private String responsemsg = "";
	private String mapjson = "";

	public void doLogin() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String phone = request.getParameter("pho");
		String password = request.getParameter("pwd");
		String code = request.getParameter("cod");
		String token = request.getParameter("tk");

		Customer db_customer = new Customer();
		Customer temp_customer = new Customer();
		temp_customer.setCustomerPhone(phone);
		temp_customer.setCustomerCode(code);
		temp_customer.setCustomerToken(token);
		temp_customer.setCustomerPassword(password);

		if (token != null && token != "") {
			db_customer = customerService.LoginByToken(temp_customer);
		}
		if (db_customer == null && password != null && password != "") {
			db_customer = customerService.LoginByPassword(temp_customer);
		}
		if (db_customer == null && code != null && code != "") {
			db_customer = customerService.LoginByCode(temp_customer);
		}
		System.out.println(db_customer);

		json = gson.toJson(db_customer);
		responsemsg = msg.success;
		map.put("json", json);
		map.put("responsemsg", responsemsg);
		mapjson = gson.toJson(map);

		out.write(mapjson);
		out.flush();
		out.close();
		return;
	}

	public void doAdd() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String phone = request.getParameter("pho");
		String code = request.getParameter("cod");

		Customer temp_customer = new Customer();
		temp_customer.setCustomerPhone(phone);

		Customer db_customer = new Customer();
		db_customer = customerService.FindByPhone(temp_customer);

		if (db_customer == null) {
			SendMsg2AliServer SendMsg2AliServer = new SendMsg2AliServer();

			String db_code = RandomStringUtils.randomNumeric(4);// 生成随机数字字符串
			temp_customer.setCustomerCode(db_code);
			customerService.Add(temp_customer);

			SendMsg2AliServer.GetPhoneNum2SendMsg(test.getMsgModelLogin(db_code), phone);

			json = gson.toJson(temp_customer);

		} else {
			if (db_customer.getCustomerCode().endsWith(code)) {

				String db_token = UUID.randomUUID().toString();
				String db_code = RandomStringUtils.randomAlphanumeric(5);// 生成指定长度的字母和数字的随机组合字符串
				db_customer.setCustomerToken(db_token);
				db_customer.setCustomerCode(db_code);
				customerService.Update(db_customer);

				json = gson.toJson(temp_customer);

			} else {
				SendMsg2AliServer SendMsg2AliServer = new SendMsg2AliServer();

				String db_code = RandomStringUtils.randomNumeric(4);
				db_customer.setCustomerCode(db_code);
				customerService.Update(db_customer);

				SendMsg2AliServer.GetPhoneNum2SendMsg(test.getMsgModelLogin(db_code), phone);

				temp_customer.setCustomerCode(db_code);
				json = gson.toJson(temp_customer);
			}
		}
		responsemsg = msg.fail;
		map.put("json", json);
		map.put("responsemsg", responsemsg);
		mapjson = gson.toJson(map);

		out.write(mapjson);
		out.flush();
		out.close();
		return;
	}

	public void doView() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String token = request.getParameter("tk");

		Customer db_customer = customerService.FindByToken(token);
		if (db_customer == null) {
			json = gson.toJson(db_customer);
			responsemsg = msg.fail;
			map.put("responsemsg", responsemsg);
			mapjson = gson.toJson(map);

			out.write(mapjson);
			out.flush();
			out.close();
			return;
		}

		json = gson.toJson(db_customer);
		responsemsg = msg.success;
		map.put("json", json);
		map.put("responsemsg", responsemsg);
		mapjson = gson.toJson(map);

		out.write(mapjson);
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
}
