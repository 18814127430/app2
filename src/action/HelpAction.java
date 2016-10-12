package action;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import service.HelpService;
import utils.msg;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.Help;
import bean.Push;

public class HelpAction extends ActionSupport {

	private HelpService helpService;
	private Help help;
	private String keyword;
	private int currentPage;
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
			ActionContext.getContext().put("Msg", helpService.getMsg());
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
			ActionContext.getContext().put("Msg", helpService.getMsg());
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
			ActionContext.getContext().put("Msg", helpService.getMsg());
			return "systemerror_view";
		}
	}

	public String doFind() throws Exception {
		if (currentPage <= 0)
			currentPage = 1;
		if (keyword == null)
			keyword = "";
		if (keyword == null || keyword.equals(""))
			keyword = "";
		System.out.println("rec+currentPage:" + currentPage);
		System.out.println("rec+keyword:" + keyword);

		int totalRecord = helpService.Count_Keyword(keyword);
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
		if (currentPage > totalPage) {
			System.out.println("currentPage>totalPage");
		} else {
			System.out.println("当前页码：" + currentPage + "页码列表：");
			for (int i = firstPage; i <= lastPage; i++) {
				System.out.print(i);
			}
		}
		int fromIndex = (currentPage - 1) * msg.RECORD_SIZE; // 选择从第几条开始

		System.out.println("当前页码：totalPage" + totalPage);
		System.out.println("当前页码：totalRecord" + totalRecord);
		System.out.println("当前页码：currentPage" + currentPage);
		System.out.println("当前页码：fromIndex" + fromIndex);
		System.out.println("当前页码：firstPage" + firstPage);
		System.out.println("当前页码：lastPage" + lastPage);

		List<?> list = helpService.Find_Keyword(keyword, fromIndex, msg.RECORD_SIZE);// 可优化

		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", msg.PAGE_SIZE);
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
			ActionContext.getContext().put("Msg", helpService.getMsg());
			return "systemerror_view";
		}
	}

	public String doUpdate() throws Exception {
		System.out.println("doUpdate要修改的信息:" + help);
		Help db_help = helpService.Update(help);
		this.help = db_help;
		if (db_help != null) {
			ActionContext.getContext().put("help", help);
			System.out.println("doUpdate修改后信息:" + help);
			return "helpinfo_view";
		} else {
			ActionContext.getContext().put("Msg", helpService.getMsg());
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
