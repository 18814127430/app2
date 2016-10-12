package action;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import service.CompanyService;
import utils.msg;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.Company;
import bean.Company;

public class CompanyAction extends ActionSupport {
	
	private CompanyService companyService;
	private Company company;
	private String keyword;
	private int currentPage;
	private String deletelist;
	
	public String doAdd() throws Exception {
		System.out.println("doAdd要添加的信息:" + company);
		Company db_company = companyService.Add(company);
		System.out.println("doAdd添加后的信息:" + db_company);
		this.company = db_company;
		if (db_company != null) {
			ActionContext.getContext().put("company", company);
			return "companyinfo_view";
		}
		else {
			ActionContext.getContext().put("Msg", companyService.getMsg());
			return "systemerror_view";
		}
	}
	
	public String doDelete() throws Exception {
		Company db_company = companyService.View(company.getCompanyId());
		System.out.println("daDelete要删除的信息:" + db_company);
		if (companyService.Delete(db_company)) {
			return (doFind());
		}
		else {
			ActionContext.getContext().put("Msg", companyService.getMsg());
			return "systemerror_view";
		}
	}
	
	public String doDeleteAll() throws Exception {
		
		String[] id = deletelist.split(",");// 用逗号切割
		System.out.println("用逗号切割");
		System.out.println(deletelist);
		System.out.println(currentPage);
		
		for (int i = 0; i < id.length; i++) {
			int num = Integer.parseInt(id[i]);
			Company db_company = companyService.View(num);
			companyService.Delete(db_company);
		}
		
		return (doFind());
	}
	
	public String doView() throws Exception {
		Company db_company = companyService.View(company.getCompanyId());
		this.company = db_company;
		if (db_company != null) {
			ActionContext.getContext().put("company", company);
			return "companyinfo_view";
		}
		else {
			ActionContext.getContext().put("Msg", companyService.getMsg());
			return "systemerror_view";
		}
	}
	
	public String doFind() throws Exception {
		if (currentPage == 0)
			currentPage = 1;
		if (keyword == null)
			keyword = "";
		
		System.out.println(keyword);
		List<?> list = companyService.Find_All(keyword);
		
		int totalRecord = list.size();
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
		}
		else {
			firstPage = (currentPage / msg.PAGE_SIZE) * msg.PAGE_SIZE + 1;
			lastPage = firstPage + msg.PAGE_SIZE - 1;
			if (lastPage > totalPage) {
				lastPage = totalPage;
			}
		}
		if (currentPage > totalPage) {
			System.out.println("currentPage>totalPage");
		}
		else {
			System.out.println("当前页码：" + currentPage + "页码列表：");
			for (int i = firstPage; i <= lastPage; i++) {
				System.out.print(i);
			}
		}
		int fromIndex = (currentPage - 1) * msg.RECORD_SIZE; // 选择从第几条开始
		int toIndex = Math.min(fromIndex + msg.RECORD_SIZE, list.size());// 调用Math.min函数取目的数
		List list1 = list.subList(fromIndex, toIndex);// 从总数列表中截取子列表
		
		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list1);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", msg.PAGE_SIZE);
		ctx.put("keyword", keyword);
		return "companylist_view";
	}
	
	public String doEdit() throws Exception {
		System.out.println("doEdit要修改信息的ID：" + company.getCompanyId());
		Company db_company = companyService.View(company.getCompanyId());
		this.company = db_company;
		System.out.println("doEdit要修改信息：" + company);
		if (db_company != null) {
			ActionContext.getContext().put("company", company);
			return "companyedit_view";
		}
		else {
			ActionContext.getContext().put("Msg", companyService.getMsg());
			return "systemerror_view";
		}
	}
	
	public String doUpdate() throws Exception {
		System.out.println("doUpdate要修改的信息:" + company);
		Company db_company = companyService.Update(company);
		this.company = db_company;
		if (db_company != null) {
			ActionContext.getContext().put("company", company);
			System.out.println("doUpdate修改后信息:" + company);
			return "companyinfo_view";
		}
		else {
			ActionContext.getContext().put("Msg", companyService.getMsg());
			return "systemerror_view";
		}
	}
	
	public CompanyService getCompanyService() {
		return companyService;
	}
	
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
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

	public String getDeletelist() {
		return deletelist;
	}

	public void setDeletelist(String deletelist) {
		this.deletelist = deletelist;
	}
	
}
