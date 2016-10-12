package action;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import service.LogsService;
import utils.msg;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.Logs;

public class LogsAction extends ActionSupport {

	private LogsService logsService;
	private Logs logs;
	private String keyword;
	private String logsStartDate;
	private int currentPage;

	public String doAdd() throws Exception {
		System.out.println("doAdd要添加的信息:" + logs);
		Logs db_logs = logsService.Add(logs);
		System.out.println("doAdd添加后的信息:" + db_logs);
		this.logs = db_logs;
		if (db_logs != null) {
			ActionContext.getContext().put("logs", logs);
			return "logsinfo_view";
		} else {
			ActionContext.getContext().put("Msg", logsService.getMsg());
			return "systemerror_view";
		}
	}

	/* 删除信息 */
	public String doDelete() throws Exception {
		Logs db_logs = logsService.View(logs.getLogsId());
		System.out.println("daDelete要删除的信息:" + db_logs);
		if (logsService.Delete(db_logs)) {
			return (doFind());
		} else {
			ActionContext.getContext().put("Msg", logsService.getMsg());
			return "systemerror_view";
		}
	}

	/* 查看信息 */
	public String doView() throws Exception {
		Logs db_logs = logsService.View(logs.getLogsId());
		this.logs = db_logs;
		if (db_logs != null) {
			ActionContext.getContext().put("logs", logs);
			return "logsinfo_view";
		} else {
			ActionContext.getContext().put("Msg", logsService.getMsg());
			return "systemerror_view";
		}
	}

	/* 信息列表 */
	public String doFind() throws Exception {
		if (currentPage <= 0)
			currentPage = 1;
		if (keyword == null)
			keyword = "";
		if (keyword == null || keyword.equals(""))
			keyword = "";
		System.out.println("rec+currentPage:" + currentPage);
		System.out.println("rec+keyword:" + keyword);

		int totalRecord = logsService.Count_Keyword(keyword);
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
		int toIndex = Math.min(fromIndex + msg.RECORD_SIZE, totalRecord);// 调用Math.min函数取目的数

		System.out.println("当前页码：totalPage" + totalPage);
		System.out.println("当前页码：totalRecord" + totalRecord);
		System.out.println("当前页码：currentPage" + currentPage);
		System.out.println("当前页码：fromIndex" + fromIndex);
		System.out.println("当前页码：toIndex" + toIndex);
		System.out.println("当前页码：firstPage" + firstPage);
		System.out.println("当前页码：lastPage" + lastPage);

		List<?> list = logsService.Find_Keyword(keyword, fromIndex, msg.RECORD_SIZE);// 可优化

		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", msg.PAGE_SIZE);
		ctx.put("keyword", keyword);
		return "logslist_view";
	}

	/* 修改Logs信息 */
	public String doEdit() throws Exception {
		System.out.println("doEdit要修改信息的ID：" + logs.getLogsId());
		Logs db_logs = logsService.View(logs.getLogsId());
		this.logs = db_logs;
		System.out.println("doEdit要修改信息：" + logs);
		if (db_logs != null) {
			ActionContext.getContext().put("logs", logs);
			return "logsedit_view";
		} else {
			ActionContext.getContext().put("Msg", logsService.getMsg());
			return "systemerror_view";
		}
	}

	/* 更新修改Logs信息 */
	public String doUpdate() throws Exception {
		System.out.println("doUpdate要修改的信息:" + logs);
		Logs db_logs = logsService.Update(logs);
		this.logs = db_logs;
		if (db_logs != null) {
			ActionContext.getContext().put("logs", logs);
			System.out.println("doUpdate修改后信息:" + logs);
			return "logsinfo_view";
		} else {
			ActionContext.getContext().put("Msg", logsService.getMsg());
			return "systemerror_view";
		}
	}

	public LogsService getLogsService() {
		return logsService;
	}

	public void setLogsService(LogsService logsService) {
		this.logsService = logsService;
	}

	public Logs getLogs() {
		return logs;
	}

	public void setLogs(Logs logs) {
		this.logs = logs;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getLogsStartDate() {
		return logsStartDate;
	}

	public void setLogsStartDate(String logsStartDate) {
		this.logsStartDate = logsStartDate;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

}
