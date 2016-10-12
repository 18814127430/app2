package action;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import service.HelpService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.Help;
import bean.Push;

public class HelpAction extends ActionSupport {

	private HelpService helpService;// 业务层对象
	private Help help;// 待操作的对象
	private String keyword;// 界面层需要查询的属性：关键字
	private String helpStartDate;
	private int firstPage;// 显示的第一页
	private int lastPage;// 显示的最后一页
	private int currentPage;// 显示的当前页
	private int totalPage;// 总页数
	private int totalRecord;// 总记录数
	private final int RECORD_SIZE = 10;// 每页记录数
	private final int PAGE_SIZE = 10;// 每组的页数
	private String deletelist;

	public String doAdd() throws Exception {
		System.out.println("doAdd要添加的信息:" + help);
		Help db_help = helpService.Add(help);
		System.out.println("doAdd添加后的信息:" + db_help);
		this.help = db_help;
		if (db_help != null) {
			ActionContext.getContext().put("help", help);
			return "helpinfo_view";
		} else {
			ActionContext.getContext().put("errorMsg", helpService.getMsg());
			return "systemerror_view";
		}
	}

	/* 删除信息 */
	public String doDelete() throws Exception {
		Help db_help = helpService.View(help.getHelpId());
		System.out.println("daDelete要删除的信息:" + db_help);
		if (helpService.Delete(db_help)) {
			return (doFind());
		} else {
			ActionContext.getContext().put("errorMsg", helpService.getMsg());
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
			Help db_help = helpService.View(num);
			if (db_help != null) {
				helpService.Delete(db_help);
			}
		}

		return (doFind());
	}

	/* 查看信息 */
	public String doView() throws Exception {
		Help db_help = helpService.View(help.getHelpId());
		this.help = db_help;
		if (db_help != null) {
			ActionContext.getContext().put("help", help);
			return "helpinfo_view";
		} else {
			ActionContext.getContext().put("errorMsg", helpService.getMsg());
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

		totalRecord = helpService.GetCount(keyword);
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

		List<?> list = helpService.Find(keyword, fromIndex, toIndex - fromIndex);// 可优化

		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", PAGE_SIZE);
		ctx.put("keyword", keyword);
		return "helplist_view";
	}

	/* 修改Help信息 */
	public String doEdit() throws Exception {
		System.out.println("doEdit要修改信息的ID：" + help.getHelpId());
		Help db_help = helpService.View(help.getHelpId());
		this.help = db_help;
		System.out.println("doEdit要修改信息：" + help);
		if (db_help != null) {
			ActionContext.getContext().put("help", help);
			return "helpedit_view";
		} else {
			ActionContext.getContext().put("errorMsg", helpService.getMsg());
			return "systemerror_view";
		}
	}

	/* 更新修改Help信息 */
	public String doUpdate() throws Exception {
		System.out.println("doUpdate要修改的信息:" + help);
		Help db_help = helpService.Update(help);
		this.help = db_help;
		if (db_help != null) {
			ActionContext.getContext().put("help", help);
			System.out.println("doUpdate修改后信息:" + help);
			return "helpinfo_view";
		} else {
			ActionContext.getContext().put("errorMsg", helpService.getMsg());
			return "systemerror_view";
		}
	}

	public HelpService getHelpService() {
		return helpService;
	}

	public void setHelpService(HelpService helpService) {
		this.helpService = helpService;
	}

	public Help getHelp() {
		return help;
	}

	public void setHelp(Help help) {
		this.help = help;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getHelpStartDate() {
		return helpStartDate;
	}

	public void setHelpStartDate(String helpStartDate) {
		this.helpStartDate = helpStartDate;
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

	public String getDeletelist() {
		return deletelist;
	}

	public void setDeletelist(String deletelist) {
		this.deletelist = deletelist;
	}
}
