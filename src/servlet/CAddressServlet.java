package servlet;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import service.CAddressService;
import service.CustomerService;
import utils.sendMsg2AliServer;
import utils.msg;
import utils.test;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import androidbeans.a_CAddress;
import androidbeans.a_Goods_1;
import androidbeans.a_OInfo;
import androidbeans.a_CAddress;
import bean.CAddress;
import bean.Customer;
import bean.GBatch;
import bean.Goods;
import bean.OInfo;
import bean.CAddress;

public class CAddressServlet extends ActionSupport {

	private CAddressService caddressService;
	private CustomerService customerService;

	public a_CAddress caddress2a(CAddress db_caddress) {
		a_CAddress a_caddress = new a_CAddress();

		a_caddress.setAddressCity(db_caddress.getAddressCity());
		a_caddress.setAddressDetial(db_caddress.getAddressDetial());
		a_caddress.setAddressId(db_caddress.getAddressId());
		a_caddress.setAddressName(db_caddress.getAddressName());
		a_caddress.setAddressPhone(db_caddress.getAddressPhone());
		a_caddress.setAddressProvince(db_caddress.getAddressProvince());
		a_caddress.setAddressStreet(db_caddress.getAddressStreet());
		a_caddress.setCustomerId(db_caddress.getCustomer().getCustomerId());

		return a_caddress;
	}

	public CAddress a2caddress(a_CAddress a_caddress, Customer db_customer) {

		CAddress db_caddress = new CAddress();
		db_caddress.setAddressId(a_caddress.getAddressId());
		db_caddress.setAddressCity(a_caddress.getAddressCity());
		db_caddress.setAddressDate(test.String2Timestamp(test.GetCurrentTime()));
		db_caddress.setAddressDetial(a_caddress.getAddressDetial());
		db_caddress.setAddressName(a_caddress.getAddressName());
		db_caddress.setAddressPhone(a_caddress.getAddressPhone());
		db_caddress.setAddressProvince(a_caddress.getAddressProvince());
		db_caddress.setAddressStreet(a_caddress.getAddressStreet());
		db_caddress.setCustomer(db_customer);

		return db_caddress;

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
		request.setCharacterEncoding("utf-8");
		String s1 = request.getParameter("jn");
		String s2 = request.getParameter("tk");

		String caddressdata;
		if (s1 == null || s1.equals("")) {
			caddressdata = "";
		} else {
			caddressdata = s1;
		}

		String token;
		if (s2 == null || s2.equals("")) {
			token = "";
		} else {
			token = s2;
		}

		System.out.println("string1:" + s1 + "------------caddressdata:" + caddressdata);
		System.out.println("string2:" + s2 + "------------token:" + token);

		a_CAddress a_caddress = gson.fromJson(caddressdata, a_CAddress.class);

		Customer db_customer = customerService.FindByToken(token);

		System.out.println("------->token:" + token);
		System.out.println("------->caddressdata:" + caddressdata);
		System.out.println("------->db_customer:" + db_customer);
		System.out.println("------->temp_a_caddress:" + a_caddress);

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

		if (a_caddress == null) {
			status = msg.status_1;
			message = msg.caddress_jsonnull;

			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);

			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}

		if (a_caddress.getCustomerId().intValue() != db_customer.getCustomerId().intValue()) {
			message = msg.caddress_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);

			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}

		CAddress db_caddress = a2caddress(a_caddress, db_customer);

		db_caddress = caddressService.Add(db_caddress);
		System.out.println("------->caddress:" + db_caddress);

		if (db_caddress == null) {
			message = msg.caddress_fail;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);

			mapdata = gson.toJson(map);

			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}

		a_caddress = caddress2a(db_caddress);

		message = msg.caddress_success;
		status = msg.status_0;
		data = gson.toJson(a_caddress);

		map.put("status", status);
		map.put("message", message);
		map.put("data", data);
		mapdata = gson.toJson(map);

		out.write(mapdata);

		test.a(data);
		test.a(mapdata);
		test.a(request.getCharacterEncoding());
		test.a(response.getCharacterEncoding());
		test.a(response.getWriter());
		test.a(response.toString());
		test.a(out);
		test.a(out.toString());

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
		String s1 = request.getParameter("jn");
		String s2 = request.getParameter("tk");

		String caddressdata;
		if (s1 == null || s1.equals("")) {
			caddressdata = "";
		} else {
			caddressdata = s1;
		}

		String token;
		if (s2 == null || s2.equals("")) {
			token = "";
		} else {
			token = s2;
		}

		System.out.println("string1:" + s1 + "------------caddressdata:" + caddressdata);
		System.out.println("string2:" + s2 + "------------token:" + token);

		a_CAddress a_caddress = gson.fromJson(caddressdata, a_CAddress.class);

		Customer db_customer = customerService.FindByToken(token);

		System.out.println("------->token:" + token);
		System.out.println("------->caddressdata:" + caddressdata);
		System.out.println("------->db_customer:" + db_customer);
		System.out.println("------->temp_a_caddress:" + a_caddress);

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

		if (a_caddress == null) {
			status = msg.status_1;
			message = msg.caddress_jsonnull;

			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);

			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}

		if (a_caddress.getAddressId() == null) {
			status = msg.status_1;
			message = msg.caddress_illegalinput;

			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);

			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}

		if (a_caddress.getCustomerId().intValue() != db_customer.getCustomerId().intValue()) {
			message = msg.caddress_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);

			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}

		test.a("-1-");
		CAddress db_caddress = a2caddress(a_caddress, db_customer);
		test.a("-1-");
		test.a(db_caddress);

		db_caddress = caddressService.Update(db_caddress);
		System.out.println("------->caddress:" + db_caddress);

		if (db_caddress == null) {
			message = msg.caddress_fail;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);

			mapdata = gson.toJson(map);

			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}

		a_caddress = caddress2a(db_caddress);

		message = msg.caddress_success;
		status = msg.status_0;
		data = gson.toJson(a_caddress);

		map.put("status", status);
		map.put("message", message);
		map.put("data", data);
		mapdata = gson.toJson(map);

		out.write(mapdata);
		out.flush();
		out.close();
		return;
	}

	public void doDelete() throws Exception {

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

		int caddressid;
		if (s1 == null || s1.equals("") || !test.isInteger(s1)) {
			message = msg.caddress_illegalinput;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);

			out.write(mapdata);
			out.flush();
			out.close();
			return;
		} else {
			caddressid = Integer.parseInt(s1);
		}

		String token;
		if (s2 == null || s2.equals("")) {
			token = "";
		} else {
			token = s2;
		}

		System.out.println("string1:" + s1 + "------------caddressid:" + caddressid);
		System.out.println("string2:" + s2 + "------------token:" + token);

		Customer db_customer = customerService.FindByToken(token);
		CAddress caddress = caddressService.View(caddressid);

		System.out.println("------->token:" + token);
		System.out.println("------->caddressid:" + caddressid);
		System.out.println("------->db_customer:" + db_customer);
		System.out.println("------->object:" + caddress);

		if (db_customer == null) {
			message = msg.customer_customernull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);

			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}

		if (caddress == null) {
			message = msg.caddress_addressnull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);

			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}

		if (caddress.getCustomer().getCustomerId().intValue() != db_customer.getCustomerId().intValue()) {
			message = msg.caddress_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);

			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}

		if (!caddressService.Delete(caddress)) {
			message = msg.caddress_fail;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);

			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}

		message = msg.caddress_success;
		status = msg.status_1;
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

		int caddressid;
		if (s1 == null || s1.equals("") || !test.isInteger(s1)) {
			message = msg.caddress_illegalinput;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);

			out.write(mapdata);
			out.flush();
			out.close();
			return;
		} else {
			caddressid = Integer.parseInt(s1);
		}

		String token;
		if (s2 == null || s2.equals("")) {
			token = "";
		} else {
			token = s2;
		}

		System.out.println("string1:" + s1 + "------------caddressid:" + caddressid);
		System.out.println("string2:" + s2 + "------------token:" + token);

		Customer db_customer = customerService.FindByToken(token);
		CAddress db_caddress = caddressService.View(caddressid);

		System.out.println("------->token:" + token);
		System.out.println("------->caddressid:" + caddressid);
		System.out.println("------->db_customer:" + db_customer);
		System.out.println("------->caddress:" + db_caddress);

		if (db_customer == null) {
			message = msg.customer_customernull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);

			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}

		if (db_caddress == null) {
			message = msg.caddress_fail;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);

			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}

		if (db_caddress.getCustomer().getCustomerId().intValue() != db_customer.getCustomerId().intValue()) {
			message = msg.caddress_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);

			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}

		message = msg.caddress_success;
		status = msg.status_0;
		map.put("status", status);
		map.put("message", message);

		a_CAddress a_caddress = caddress2a(db_caddress);

		data = gson.toJson(a_caddress);
		map.put("data", data);
		mapdata = gson.toJson(map);

		out.write(mapdata);
		out.flush();
		out.close();

		map = gson.fromJson(mapdata, java.util.HashMap.class);
		data = (String) map.get("data");
		message = (String) map.get("message");

		System.out.println("------->get data:" + data);
		System.out.println("------->get message:" + message);

		a_caddress = gson.fromJson(data, a_CAddress.class);
		System.out.println("--------get a_caddress:" + a_caddress);

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
		} else {
			keyword = s1;
		}

		int currentPage;
		if (s2 == null || s2.equals("") || !test.isInteger(s2)) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(s2);
		}

		String token;
		if (s3 == null || s3.equals("")) {
			token = "";
		} else {
			token = s3;
		}

		System.out.println("string1:" + s1 + "------------keyword:" + keyword);
		System.out.println("string2:" + s2 + "------------currentPage:" + currentPage);
		System.out.println("string3:" + s3 + "------------token:" + token);

		Customer db_customer = customerService.FindByToken(token);

		if (db_customer == null) {
			message = msg.customer_customernull;
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
		int totalRecord = caddressService.Count_CustomerId(db_customer.getCustomerId(), keyword);
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
		} else {
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

		List<?> list = caddressService.Find_CustomerId(db_customer.getCustomerId(), keyword, fromIndex, msg.RECORD_SIZE);// 可优化

		if (list.size() == 0) {
			message = msg.caddress_fail;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);

			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}

		List list1 = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			CAddress caddress = (CAddress) list.get(i);

			a_CAddress a_caddress = caddress2a(caddress);

			list1.add(a_caddress);
		}

		message = msg.caddress_success;
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

	public CAddressService getCaddressService() {
		return caddressService;
	}

	public void setCaddressService(CAddressService caddressService) {
		this.caddressService = caddressService;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
}
