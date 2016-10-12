package action;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import service.CollectService;
import utils.msg;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.Collect;

public class CollectAction extends ActionSupport {

	private CollectService collectService;// 业务层对象
	private Collect collect;// 待操作的对象
	private String keyword;// 界面层需要查询的属性：关键字
	private int currentPage;// 显示的当前页
	private int customerid;

	public String doView() throws Exception {
		Collect db_collect = collectService.View(collect.getCollectId());
		this.collect = db_collect;
		if (db_collect != null) {
			ActionContext.getContext().put("collect", collect);
			return "collectinfo_view";
		} else {
			ActionContext.getContext().put("Msg", collectService.getMsg());
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
			totalRecord = collectService.Count_CustomerId(customerid, keyword);
		} else {
			totalRecord = collectService.Count_Keyword(keyword);
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
			list = collectService.Find_CustomerId(customerid, keyword, fromIndex, msg.RECORD_SIZE);
		} else {
			list = collectService.Find_Keyword(keyword, fromIndex, msg.RECORD_SIZE);
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
			return "collectlistbycustomer_view";
		}

		return "collectlist_view";

	}

	public CollectService getCollectService() {
		return collectService;
	}

	public void setCollectService(CollectService collectService) {
		this.collectService = collectService;
	}

	public Collect getCollect() {
		return collect;
	}

	public void setCollect(Collect collect) {
		this.collect = collect;
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

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

}
