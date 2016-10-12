package action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import service.CAddressService;
import utils.msg;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.CAddress;
import bean.Customer;

public class CAddressAction extends ActionSupport {

	private CAddressService caddressService;// 业务层对象
	private CAddress caddress;// 待操作的对象
	private String keyword;// 界面层需要查询的属性：关键字
	private int customerid;// 显示的第一页
	private int currentPage;// 显示的当前页

	public String doView() throws Exception {
		CAddress db_caddress = caddressService.View(caddress.getAddressId());
		this.caddress = db_caddress;

		if (db_caddress != null) {
			ActionContext.getContext().put("caddress", caddress);
			System.out.println("------------");
			System.out.println(db_caddress.getCustomer());
			System.out.println(db_caddress);
			System.out.println("------------");
			return "caddressinfo_view";
		} else {
			ActionContext.getContext().put("Msg", caddressService.getMsg());
			return "systemerror_view";
		}
	}

	public String doFind() throws Exception {

		if (customerid <= 0)
			customerid = 0;
		if (currentPage <= 0)
			currentPage = 1;
		if (keyword == null || keyword.equals(""))
			keyword = "";
		System.out.println("rec+customerid:" + customerid);
		System.out.println("rec+currentPage:" + currentPage);
		System.out.println("rec+keyword:" + keyword);

		int totalRecord = 0;
		if (customerid > 0) {
			totalRecord = caddressService.Count_CustomerId(customerid, keyword);
		} else {
			totalRecord = caddressService.Count_Keyword(keyword);
		}

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

		List list = new ArrayList();
		if (customerid > 0) {
			list = caddressService.Find_CustomerId(customerid, keyword, fromIndex, msg.RECORD_SIZE);
		} else {
			list = caddressService.Find_Keyword(keyword, fromIndex, msg.RECORD_SIZE);
		}

		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", msg.PAGE_SIZE);
		ctx.put("keyword", keyword);

		if (customerid > 0) {
			ctx.put("customerid", customerid);
			return "caddresslistbycustomer_view";
		}

		return "caddresslist_view";
	}

	public CAddressService getCAddressService() {
		return caddressService;
	}

	public void setCAddressService(CAddressService caddressService) {
		this.caddressService = caddressService;
	}

	public CAddress getCAddress() {
		return caddress;
	}

	public void setCAddress(CAddress caddress) {
		this.caddress = caddress;
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

	public CAddressService getAddressService() {
		return caddressService;
	}

	public void setAddressService(CAddressService caddressService) {
		this.caddressService = caddressService;
	}

	public CAddressService getCaddressService() {
		return caddressService;
	}

	public void setCaddressService(CAddressService caddressService) {
		this.caddressService = caddressService;
	}

	public CAddress getCaddress() {
		return caddress;
	}

	public void setCaddress(CAddress caddress) {
		this.caddress = caddress;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

}
