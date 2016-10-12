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
import service.CustomerService;
import utils.test;
import utils.sendMsg2AliServer;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.CAddress;
import bean.Customer;
import utils.msg;

public class CustomerAction extends ActionSupport {

	private static final Log log = LogFactory.getLog(CustomerAction.class);
	private CustomerService customerService;
	private CAddressService caddressService;
	private String keyword;
	private int currentPage;
	private int customerid;

	public String doView() throws Exception {
		Customer db_customer = customerService.View(customerid);

		if (db_customer != null) {
			System.out.println("======>db_customer:" + db_customer);
			Gson gson = new Gson();
			String json = gson.toJson(db_customer);
			System.out.println("======>json:" + json);
			Customer customer = gson.fromJson(json, db_customer.getClass());
			System.out.println("======>customer:" + customer);

			List<CAddress> list = caddressService.Find_CustomerId(customerid, "", 0, 100);
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

			ActionContext.getContext().put("customer", db_customer);
			return "customerinfo_view";
		} else {
			ActionContext.getContext().put("Msg", customerService.getMsg());
			return "systemerror_view";
		}
	}

	public String doFind() throws Exception {
		if (currentPage == 0)
			currentPage = 1;
		if (keyword == null)
			keyword = "";

		System.out.println("rec+currentPage:" + currentPage);
		System.out.println("rec+keyword:" + keyword);

		int totalRecord = customerService.Count_Keyword(keyword);

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
		} else {
			firstPage = (currentPage / msg.PAGE_SIZE) * msg.PAGE_SIZE + 1;
			lastPage = firstPage + msg.PAGE_SIZE - 1;
			if (lastPage > totalPage) {
				lastPage = totalPage;
			}
		}

		int fromIndex = (currentPage - 1) * msg.RECORD_SIZE; // 选择从第几条开始

		System.out.println("当前页码：totalPage" + totalPage);
		System.out.println("当前页码：totalRecord" + totalRecord);
		System.out.println("当前页码：currentPage" + currentPage);
		System.out.println("当前页码：fromIndex" + fromIndex);
		System.out.println("当前页码：firstPage" + firstPage);
		System.out.println("当前页码：lastPage" + lastPage);

		List<?> list = customerService.Find_Keyword(keyword, fromIndex, msg.RECORD_SIZE);// 可优化

		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", msg.PAGE_SIZE);
		ctx.put("keyword", keyword);

		return "customerlist_view";
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
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

	public static Log getLog() {
		return log;
	}

	public CAddressService getCaddressService() {
		return caddressService;
	}

	public void setCaddressService(CAddressService caddressService) {
		this.caddressService = caddressService;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

}
