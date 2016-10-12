package action;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import service.AdminService;
import service.CompanyService;
import service.CustomerService;
import service.GoodsService;
import service.OOrderService;
import service.SortService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.Goods;
import bean.Sort;
import bean.Admin;
import bean.Company;
import bean.Customer;
import bean.OOrder;
import utils.test;

public class SystemAction extends ActionSupport {
	
	private SortService sortService;
	private AdminService adminService;
	private CompanyService companyService;
	private CustomerService customerService;
	private OOrderService oorderService;
	private GoodsService goodsService;
	private String keyword = "";
	private int currentPage;
	
	public String doShow() throws Exception {
		
		int record_sort = sortService.Count_All();
		int record_admin = adminService.Count_Keyword("");
		int record_company = companyService.Count_Keyword("");
		int record_customer = customerService.Count_Keyword("");
		int record_order = oorderService.Count(0, 0, 0, 0, 0, 0, 0, "");
		int record_goods = goodsService.Count_Sort_Keyword(0, 0, "");
		
		ActionContext ctx = ActionContext.getContext();
		ctx.put("record_1", record_sort);
		ctx.put("record_2", record_admin);
		ctx.put("record_3", record_company);
		ctx.put("record_4", record_customer);
		ctx.put("record_5", record_order);
		ctx.put("record_6", record_goods);
		
		return "systeminfo_view";
	}
	
	public SortService getSortService() {
		return sortService;
	}
	
	public void setSortService(SortService sortService) {
		this.sortService = sortService;
	}
	
	public AdminService getAdminService() {
		return adminService;
	}
	
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	public CompanyService getCompanyService() {
		return companyService;
	}
	
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	public CustomerService getCustomerService() {
		return customerService;
	}
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	public OOrderService getOorderService() {
		return oorderService;
	}
	
	public void setOorderService(OOrderService oorderService) {
		this.oorderService = oorderService;
	}
	
	public GoodsService getGoodsService() {
		return goodsService;
	}
	
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
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
	
}
