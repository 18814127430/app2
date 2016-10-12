package action;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import service.CompanyService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.Company;

public class CompanyAction extends ActionSupport {

	private CompanyService companyService;// 业务层对象
	private Company company;// 待操作的对象
	private String keyword;// 界面层需要查询的属性：关键字
	private String companyStartDate;
	private int firstPage;// 显示的第一页
	private int lastPage;// 显示的最后一页
	private int currentPage;// 显示的当前页
	private int totalPage;// 总页数
	private int totalRecord;// 总记录数
	private final int RECORD_SIZE = 10;// 每页记录数
	private final int PAGE_SIZE = 10;// 每组的页数

	/* 将String时间转换为timeStamp时间 */
	public Timestamp getTimeStamp(String s) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			ts = Timestamp.valueOf(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ts;
	}

	/* 添加信息 */
	public String doAdd() throws Exception {
		System.out.println("doAdd要添加的信息:" + company);
		Company db_company = companyService.Add(company);
		System.out.println("doAdd添加后的信息:" + db_company);
		this.company = db_company;
		if (db_company != null) {
			ActionContext.getContext().put("company", company);
			return "companyinfo_view";
		} else {
			ActionContext.getContext().put("errorMsg", companyService.getMsg());
			return "systemerror_view";
		}
	}

	/* 删除信息 */
	public String doDelete() throws Exception {
		Company db_company = companyService.View(company.getCompanyId());
		System.out.println("daDelete要删除的信息:" + db_company);
		if (companyService.Delete(db_company)) {
			return (doFind());
		} else {
			ActionContext.getContext().put("errorMsg", companyService.getMsg());
			return "systemerror_view";
		}
	}

	/* 查看信息 */
	public String doView() throws Exception {
		Company db_company = companyService.View(company.getCompanyId());
		this.company = db_company;
		if (db_company != null) {
			ActionContext.getContext().put("company", company);
			return "companyinfo_view";
		} else {
			ActionContext.getContext().put("errorMsg", companyService.getMsg());
			return "systemerror_view";
		}
	}

	/* 信息列表 */
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
		List<?> list = companyService.Find(keyword);
		for (int i = 0; i < list.size(); i++) {
		}

		totalRecord = list.size();
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
		int toIndex = Math.min(fromIndex + this.RECORD_SIZE, list.size());// 调用Math.min函数取目的数
		List list1 = list.subList(fromIndex, toIndex);// 从总数列表中截取子列表

		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list1);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", PAGE_SIZE);
		ctx.put("keyword", keyword);
		return "companylist_view";
	}

	/* 修改Company信息 */
	public String doEdit() throws Exception {
		System.out.println("doEdit要修改信息的ID：" + company.getCompanyId());
		Company db_company = companyService.View(company.getCompanyId());
		this.company = db_company;
		System.out.println("doEdit要修改信息：" + company);
		if (db_company != null) {
			ActionContext.getContext().put("company", company);
			return "companyedit_view";
		} else {
			ActionContext.getContext().put("errorMsg", companyService.getMsg());
			return "systemerror_view";
		}
	}

	/* 更新修改Company信息 */
	public String doUpdate() throws Exception {
		System.out.println("doUpdate要修改的信息:" + company);
		Company db_company = companyService.Update(company);
		this.company = db_company;
		if (db_company != null) {
			ActionContext.getContext().put("company", company);
			System.out.println("doUpdate修改后信息:" + company);
			return "companyinfo_view";
		} else {
			ActionContext.getContext().put("errorMsg", companyService.getMsg());
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

	public String getCompanyStartDate() {
		return companyStartDate;
	}

	public void setCompanyStartDate(String companyStartDate) {
		this.companyStartDate = companyStartDate;
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

	public int getRECORD_SIZE() {
		return RECORD_SIZE;
	}

	public int getPAGE_SIZE() {
		return PAGE_SIZE;
	}
}
