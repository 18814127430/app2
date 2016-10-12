package action;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
import utils.test;
import utils.SendMsg2AliServer;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.CAddress;
import bean.Customer;

public class CustomerAction extends ActionSupport {

	private static final Log log = LogFactory.getLog(CustomerAction.class);
	private CustomerService customerService;// 业务层对象
	private CAddressService caddressService;// 业务层对象
	private CWordsService cwordsService;// 业务层对象
	private Customer customer;// 待操作的对象
	private String keyword;// 界面层需要查询的属性：关键字
	private int firstPage;// 显示的第一页
	private int lastPage;// 显示的最后一页
	private int currentPage;// 显示的当前页
	private int totalPage;// 总页数
	private int totalRecord;// 总记录数
	private final int RECORD_SIZE = 10;// 每页记录数
	private final int PAGE_SIZE = 10;// 每组的页数

	private String phone;
	private String password;
	private String code;
	private String token;

	public void doLogin() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String code = request.getParameter("code");
		String token = request.getParameter("token");

		Customer db_customer = new Customer();
		Customer temp_customer = new Customer();
		temp_customer.setCustomerPhone(phone);

		if (token != null && token != "") {
			temp_customer.setCustomerToken(token);
			db_customer = customerService.LoginByToken(temp_customer);
		}
		if (db_customer == null && password != null && password != "") {
			temp_customer.setCustomerPassword(password);
			db_customer = customerService.LoginByPassword(temp_customer);
		}
		if (db_customer == null && code != null && code != "") {
			temp_customer.setCustomerCode(code);
			db_customer = customerService.LoginByCode(temp_customer);
		}
		System.out.println(db_customer);

		JSONObject json = JSONObject.fromObject(db_customer);
		out.write(json.toString());
		out.flush();
		out.close();
	}

	public void doAdd() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String phone = request.getParameter("phone");
		String code = request.getParameter("code");

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

			JSONObject json = JSONObject.fromObject(temp_customer);
			out.write(json.toString());

			log.info(temp_customer.toString());
			System.out.println(json.toString());

		} else {
			if (db_customer.getCustomerCode().endsWith(code)) {

				String db_token = UUID.randomUUID().toString();
				String db_code = RandomStringUtils.randomAlphanumeric(5);// 生成指定长度的字母和数字的随机组合字符串
				db_customer.setCustomerToken(db_token);
				db_customer.setCustomerCode(db_code);
				customerService.Update(db_customer);

				JSONObject json = JSONObject.fromObject(db_customer);
				out.write(json.toString());

				System.out.println(db_customer);
				log.info(json.toString());

			} else {
				SendMsg2AliServer SendMsg2AliServer = new SendMsg2AliServer();

				String db_code = RandomStringUtils.randomNumeric(4);
				db_customer.setCustomerCode(db_code);
				customerService.Update(db_customer);

				SendMsg2AliServer.GetPhoneNum2SendMsg(test.getMsgModelLogin(db_code), phone);

				temp_customer.setCustomerCode(db_code);
				JSONObject json = JSONObject.fromObject(temp_customer);
				out.write(json.toString());

				System.out.println(temp_customer);
				log.info(json.toString());
			}
		}
		out.flush();
		out.close();
	}

	public String doView() throws Exception {
		Customer db_customer = customerService.View(customer.getCustomerId());

		if (db_customer != null) {
			System.out.println("000000000000" + "+" + db_customer.getCustomerId());
			CAddress adr = new CAddress();
			adr.setAddressCity("lllllllllll");
			adr.setAddressPhone("llllllllllllll");
			adr.setAddressName("llllllllllllll");
			adr.setAddressDate("");
			adr.setAddressProvince("");
			adr.setAddressStatus("");
			adr.setAddressStreet("");
			adr.setAddressDetial("llllllllllllll");
			adr.setCustomer(db_customer);
			caddressService.Add(adr);
			System.out.println("777777777777" + "+" + adr);
			List<CAddress> list = caddressService.FindByCustomerId(db_customer.getCustomerId(), "", 0, 100);
			for (int i = 0; i < list.size(); i++) {
				CAddress ca = list.get(i);
				System.out.println("**********CAddress:" + ca);
				System.out.println("***Customer:" + ca.getCustomer());
			}
			Set<CAddress> set = new HashSet<CAddress>(list);
			db_customer.setCAddresses(set);

			Set AddressSet = db_customer.getCAddresses();
			Iterator iterator1 = AddressSet.iterator();
			while (iterator1.hasNext()) {
				CAddress address = (CAddress) iterator1.next();
				System.out.println("Customeraddres: " + address);
			}

			this.customer = db_customer;
			ActionContext.getContext().put("customer", customer);
			return "customerinfo_view";
		} else {
			ActionContext.getContext().put("errorMsg", customerService.getMsg());
			return "systemerror_view";
		}
	}

	public String doFind() throws Exception {
		if (totalRecord == 0)
			totalRecord = 1;
		if (totalPage == 0)
			totalPage = 1;
		if (firstPage == 0)
			firstPage = 1;
		if (currentPage == 0)
			currentPage = 1;
		if (lastPage == 0)
			lastPage = 1;
		if (keyword == null)
			keyword = "";

		System.out.println(keyword);

		totalRecord = customerService.GetCount(keyword);
		System.out.println("59594646" + totalRecord);

		totalPage = totalRecord / this.RECORD_SIZE + 1;
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
		if (currentPage > totalPage) {
			System.out.println("currentPage>totalPage");
		} else {
			System.out.println("当前页码：" + currentPage + "页码列表：");
			for (int i = firstPage; i <= lastPage; i++) {
				System.out.print(i);
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

		List<?> list = customerService.Find(keyword, fromIndex, toIndex - fromIndex);// 可优化

		// Iterator iterator = list1.iterator();
		// while (iterator.hasNext()) {
		// Customer customer = (Customer) iterator.next();
		// System.out.println("Customer: " + customer);
		//
		// Set AddressSet = customer.getCAddresses();
		// Iterator iterator1 = AddressSet.iterator();
		// while (iterator1.hasNext()) {
		// CAddress address = (CAddress) iterator1.next();
		// System.out.println("Customeraddress: " + address);
		// }
		//
		// }

		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", PAGE_SIZE);
		ctx.put("keyword", keyword);

		return "customerlist_view";
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public static Log getLog() {
		return log;
	}

	public int getRECORD_SIZE() {
		return RECORD_SIZE;
	}

	public int getPAGE_SIZE() {
		return PAGE_SIZE;
	}

	public CAddressService getCaddressService() {
		return caddressService;
	}

	public void setCaddressService(CAddressService caddressService) {
		this.caddressService = caddressService;
	}

	public CWordsService getCwordsService() {
		return cwordsService;
	}

	public void setCwordsService(CWordsService cwordsService) {
		this.cwordsService = cwordsService;
	}

}
