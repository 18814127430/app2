package servlet;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import service.CartService;
import service.CustomerService;
import utils.msg;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import bean.Cart;
import bean.Customer;

public class CartServlet extends ActionSupport {

	private CartService cartService;
	private CustomerService customerService;

	private final int RECORD_SIZE = 10;
	private final int PAGE_SIZE = 10;
	
	private Gson gson = new Gson();
	private Map<String, String> map = new HashMap<String, String>();
	private String json = "";
	private String responsemsg = "";
	private String mapjson = "";

	public void doAdd() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String token = request.getParameter("tk");
		String cartjson = request.getParameter("jn");

		Customer db_customer = customerService.FindByToken(token);
		Cart object = gson.fromJson(cartjson, Cart.class);

		System.out.println("------->token:" + token);
		System.out.println("------->cartjson:" + cartjson);
		System.out.println("------->db_customer:" + db_customer);
		System.out.println("------->object:" + object);

		if (db_customer == null || object == null || !object.getCustomer().getCustomerId().equals(db_customer.getCustomerId())) {
			responsemsg = msg.fail;
			map.put("responsemsg", responsemsg);
			mapjson = gson.toJson(map);

			out.write(mapjson);
			out.flush();
			out.close();
			return;
		}

		Cart db_cart = cartService.Add(object);

		if (db_cart == null) {
			responsemsg = msg.add_fail;
			map.put("responsemsg", responsemsg);
			mapjson = gson.toJson(map);

			out.write(mapjson);
			out.flush();
			out.close();
			return;
		}

		System.out.println("------->db_cart:" + db_cart);

		responsemsg = msg.add_success;
		json = gson.toJson(db_cart);
		map.put("responsemsg", responsemsg);
		map.put("json", json);
		mapjson = gson.toJson(map);

		out.write(mapjson);
		out.flush();
		out.close();
		return;
	}

	public void doUpdate() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String token = request.getParameter("tk");
		String cartjson = request.getParameter("jn");

		Customer db_customer = customerService.FindByToken(token);
		Cart object = gson.fromJson(cartjson, Cart.class);

		System.out.println("------->token:" + token);
		System.out.println("------->cartjson:" + cartjson);
		System.out.println("------->db_customer:" + db_customer);
		System.out.println("------->object:" + object);

		if (db_customer == null || object == null || !object.getCustomer().getCustomerId().equals(db_customer.getCustomerId())) {
			responsemsg = msg.fail;
			map.put("responsemsg", responsemsg);
			mapjson = gson.toJson(map);

			out.write(mapjson);
			out.flush();
			out.close();
			return;
		}

		Cart db_cart = cartService.Update(object);

		if (db_cart == null) {
			responsemsg = msg.update_fail;
			map.put("responsemsg", responsemsg);
			mapjson = gson.toJson(map);

			out.write(mapjson);
			out.flush();
			out.close();
			return;
		}

		System.out.println("------->db_cart:" + db_cart);

		responsemsg = msg.update_success;
		json = gson.toJson(db_cart);
		map.put("responsemsg", responsemsg);
		map.put("json", json);
		mapjson = gson.toJson(map);

		out.write(mapjson);
		out.flush();
		out.close();
		return;
	}

	public void doDelete() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String token = request.getParameter("tk");
		int cartid = Integer.parseInt(request.getParameter("id"));

		Customer db_customer = customerService.FindByToken(token);
		Cart object = cartService.View(cartid);

		System.out.println("------->token:" + token);
		System.out.println("------->cartid:" + cartid);
		System.out.println("------->db_customer:" + db_customer);
		System.out.println("------->object:" + object);

		if (db_customer == null || object == null || !object.getCustomer().getCustomerId().equals(db_customer.getCustomerId())) {
			responsemsg = msg.fail;
			map.put("responsemsg", responsemsg);
			mapjson = gson.toJson(map);

			out.write(mapjson);
			out.flush();
			out.close();
			return;
		}

		if (!cartService.Delete(object)) {
			responsemsg = msg.delete_fail;
			map.put("responsemsg", responsemsg);
			mapjson = gson.toJson(map);

			out.write(mapjson);
			out.flush();
			out.close();
			return;
		}

		responsemsg = msg.delete_success;
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
		int cartid = Integer.parseInt(request.getParameter("id"));

		Customer db_customer = customerService.FindByToken(token);
		Cart object = cartService.View(cartid);

		System.out.println("------->token:" + token);
		System.out.println("------->cartid:" + cartid);
		System.out.println("------->db_customer:" + db_customer);
		System.out.println("------->object:" + object);

		if (db_customer == null || object == null || !object.getCustomer().getCustomerId().equals(db_customer.getCustomerId())) {
			responsemsg = msg.fail;
			map.put("responsemsg", responsemsg);
			mapjson = gson.toJson(map);

			out.write(mapjson);
			out.flush();
			out.close();
			return;
		}

		json = gson.toJson(object);
		responsemsg = msg.success;
		map.put("json", json);
		map.put("responsemsg", responsemsg);
		mapjson = gson.toJson(map);

		out.write(mapjson);
		out.flush();
		out.close();
		return;
	}

	public void doFindByCustomer() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		int currentPage = Integer.parseInt(request.getParameter("page"));
		String token = request.getParameter("tk");
		String keyword = request.getParameter("kw");

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

		if (keyword == null || keyword == "")
			keyword = "";

		int firstPage = 1;
		int lastPage = 1;
		int totalRecord = cartService.GetCountByCustomerId(db_customer.getCustomerId(), keyword);
		int totalPage = totalRecord / this.RECORD_SIZE + 1;
		if ((totalRecord % this.RECORD_SIZE == 0) && (totalRecord > this.RECORD_SIZE)) {
			totalPage--;
		}
		if (totalPage < PAGE_SIZE) {
			firstPage = 1;
			lastPage = totalPage;
		} else {
			firstPage = (currentPage / PAGE_SIZE) * PAGE_SIZE + 1;
			lastPage = firstPage + PAGE_SIZE - 1;
			if (lastPage > totalPage) {
				lastPage = totalPage;
			}
		}

		int fromIndex = (currentPage - 1) * this.RECORD_SIZE; // 选择从第几条开始
		int toIndex = Math.min(fromIndex + this.RECORD_SIZE, totalRecord);// 调用Math.min函数取目的数

		System.out.println("当前页码：totalPage" + totalPage);
		System.out.println("当前页码：totalRecord" + totalRecord);
		System.out.println("当前页码：currentPage" + currentPage);
		System.out.println("当前页码：fromIndex" + fromIndex);
		System.out.println("当前页码：toIndex" + toIndex);
		System.out.println("当前页码：firstPage" + firstPage);
		System.out.println("当前页码：lastPage" + lastPage);

		List<?> list = cartService.FindByCustomerId(db_customer.getCustomerId(), keyword, fromIndex, toIndex - fromIndex);// 可优化

		if (list.size() == 0) {
			responsemsg = msg.fail;
			map.put("responsemsg", responsemsg);
			mapjson = gson.toJson(map);

			out.write(mapjson);
			out.flush();
			out.close();
			return;
		}

		json = gson.toJson(list);
		responsemsg = msg.fail;
		map.put("json", json);
		map.put("responsemsg", responsemsg);
		map.put("totalPage", Integer.toString(totalPage));
		map.put("totalRecord", Integer.toString(totalRecord));
		map.put("currentPage", Integer.toString(currentPage));
		map.put("fromIndex", Integer.toString(fromIndex));
		map.put("toIndex", Integer.toString(toIndex));
		map.put("firstPage", Integer.toString(firstPage));
		map.put("lastPage", Integer.toString(lastPage));
		mapjson = gson.toJson(map);

		out.write(mapjson);
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
}
