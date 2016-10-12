package action;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import service.PushService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.Goods;
import bean.Push;

public class PushAction extends ActionSupport {

	private PushService pushService;// 业务层对象
	private Push push;// 待操作的对象
	private String keyword;// 界面层需要查询的属性：关键字
	private String StartDate;
	private int firstPage;// 显示的第一页
	private int lastPage;// 显示的最后一页
	private int currentPage;// 显示的当前页
	private int totalPage;// 总页数
	private int totalRecord;// 总记录数
	private final int RECORD_SIZE = 10;// 每页记录数
	private final int PAGE_SIZE = 10;// 每组的页数
	private String deletelist;

	public String doAdd() throws Exception {
		push.setPushDate(StartDate);
		System.out.println("doAdd要添加的信息:" + push);
		Push db_push = pushService.Add(push);
		System.out.println("doAdd添加后的信息:" + db_push);
		this.push = db_push;
		if (db_push != null) {
			ActionContext.getContext().put("push", push);
			return "pushinfo_view";
		} else {
			ActionContext.getContext().put("errorMsg", pushService.getMsg());
			return "systemerror_view";
		}
	}

	/* 删除信息 */
	public String doDelete() throws Exception {
		Push db_push = pushService.View(push.getPushId());
		System.out.println("daDelete要删除的信息:" + db_push);
		if (pushService.Delete(db_push)) {
			return (doFind());
		} else {
			ActionContext.getContext().put("errorMsg", pushService.getMsg());
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
			Push db_push = pushService.View(num);
			if (db_push != null) {
				pushService.Delete(db_push);
			}
		}

		return (doFind());
	}

	/* 查看信息 */
	public String doView() throws Exception {
		Push db_push = pushService.View(push.getPushId());
		System.out.println("doView要查看的信息1:" + db_push);
		this.push = db_push;
		System.out.println("doView要查看的信息2:" + push);
		if (db_push != null) {
			ActionContext.getContext().put("push", push);
			return "pushinfo_view";
		} else {
			ActionContext.getContext().put("errorMsg", pushService.getMsg());
			return "systemerror_view";
		}
	}

	/* 信息列表 */
	public String doFind() throws Exception {
		System.out.println(keyword);

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

		totalRecord = pushService.GetCount(keyword);
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

		List<?> list = pushService.Find(keyword, fromIndex, toIndex - fromIndex);// 可优化

		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", PAGE_SIZE);
		ctx.put("keyword", keyword);
		return "pushlist_view";
	}

	/* 修改Push信息 */
	public String doEdit() throws Exception {
		System.out.println("doEdit要修改信息的ID：" + push.getPushId());
		Push db_push = pushService.View(push.getPushId());
		this.push = db_push;
		System.out.println("doEdit要修改信息：" + push);
		if (db_push != null) {
			ActionContext.getContext().put("push", push);
			return "pushedit_view";
		} else {
			ActionContext.getContext().put("errorMsg", pushService.getMsg());
			return "systemerror_view";
		}
	}

	/* 更新修改Push信息 */
	public String doUpdate() throws Exception {
		System.out.println("doUpdate要修改的信息:" + push);
		Push db_push = pushService.Update(push);
		this.push = db_push;
		if (db_push != null) {
			ActionContext.getContext().put("push", push);
			System.out.println("doUpdate修改后信息:" + push);
			return "pushinfo_view";
		} else {
			ActionContext.getContext().put("errorMsg", pushService.getMsg());
			return "systemerror_view";
		}
	}

	public PushService getPushService() {
		return pushService;
	}

	public void setPushService(PushService pushService) {
		this.pushService = pushService;
	}

	public Push getPush() {
		return push;
	}

	public void setPush(Push push) {
		this.push = push;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getStartDate() {
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = startDate;
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
