package action;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import service.CompanyService;
import service.GBatchService;
import service.GoodsService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.Company;
import bean.GBatch;
import bean.Goods;

public class GBatchAction extends ActionSupport {

	private GBatchService gbatchService;// 业务层对象
	private GoodsService goodsService;// 业务层对象
	private CompanyService companyService;// 业务层对象
	private GBatch gbatch;// 待操作的对象
	private String keyword;// 界面层需要查询的属性：关键字
	private int goodsid;
	private int producerid;
	private int sellerid;
	private String gbatchStartDate;
	private int firstPage;// 显示的第一页
	private int lastPage;// 显示的最后一页
	private int currentPage;// 显示的当前页
	private int totalPage;// 总页数
	private int totalRecord;// 总记录数
	private final int RECORD_SIZE = 10;// 每页记录数
	private final int PAGE_SIZE = 10;// 每组的页数
	private String deletelist;

	public String beforedoAdd() throws Exception {
		List companylist = companyService.Find(keyword);
		ActionContext ctx = ActionContext.getContext();
		ctx.put("companylist", companylist);
		ctx.put("goodsid", goodsid);
		return "gbatchadd_view";
	}

	public String doAdd() throws Exception {
		System.out.println(gbatch);
		System.out.println(producerid);
		System.out.println(sellerid);
		System.out.println(goodsid);
		Company producer = companyService.View(producerid);
		Company seller = companyService.View(sellerid);
		Goods goods = goodsService.View(goodsid);
		System.out.println("56565659");
		System.out.println(producer);
		System.out.println(seller);
		System.out.println(goods);

		gbatch.setGoods(goods);
		gbatch.setCompanyByProducerId(producer);
		gbatch.setCompanyBySellerId(seller);

		System.out.println("doAdd要添加的信息:" + gbatch);

		GBatch db_gbatch = gbatchService.Add(gbatch);
		System.out.println("doAdd添加后的信息:" + db_gbatch);

		this.gbatch = db_gbatch;
		if (db_gbatch != null) {
			ActionContext ctx = ActionContext.getContext();
			ctx.put("gbatch", gbatch);
			return "gbatchinfo_view";
		} else {
			ActionContext.getContext().put("errorMsg", gbatchService.getMsg());
			return "systemerror_view";
		}
	}

	public String doDelete() throws Exception {
		GBatch db_gbatch = gbatchService.View(gbatch.getBatchId());
		System.out.println("daDelete要删除的信息:" + db_gbatch);
		if (gbatchService.Delete(db_gbatch)) {
			return (doFind());
		} else {
			ActionContext.getContext().put("errorMsg", gbatchService.getMsg());
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
			GBatch db_gbatch = gbatchService.View(num);
			gbatchService.Delete(db_gbatch);
		}

		return (doFind());
	}

	public String doView() throws Exception {
		GBatch db_gbatch = gbatchService.View(gbatch.getBatchId());
		this.gbatch = db_gbatch;
		if (db_gbatch != null) {
			ActionContext ctx = ActionContext.getContext();
			ctx.put("gbatch", gbatch);
			ctx.put("goodsid", goodsid);
			return "gbatchinfo_view";
		} else {
			ActionContext.getContext().put("errorMsg", gbatchService.getMsg());
			return "systemerror_view";
		}
	}

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

		totalRecord = gbatchService.GetCount(keyword);
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

		List<?> list = gbatchService.Find(keyword, fromIndex, toIndex - fromIndex);// 可优化

		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", PAGE_SIZE);
		ctx.put("keyword", keyword);
		return "gbatchlist_view";
	}

	public String doFindByGoodsId() throws Exception {
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

		totalRecord = gbatchService.GetCountByGoodsId(goodsid, keyword);
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
		List list = gbatchService.FindByGoodsId(goodsid, keyword, fromIndex, toIndex - fromIndex);

		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list);
		ctx.put("goodsid", goodsid);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", PAGE_SIZE);
		ctx.put("keyword", keyword);
		return "gbatchlistbygoods_view";
	}

	public String doEdit() throws Exception {
		List companylist = companyService.Find(keyword);
		System.out.println("doEdit要修改信息的ID：" + gbatch.getBatchId());
		GBatch db_gbatch = gbatchService.View(gbatch.getBatchId());
		this.gbatch = db_gbatch;
		System.out.println("doEdit要修改信息：" + gbatch);
		if (db_gbatch != null) {
			ActionContext ctx = ActionContext.getContext();
			ctx.put("companylist", companylist);
			ctx.put("gbatch", gbatch);
			return "gbatchedit_view";
		} else {
			ActionContext.getContext().put("errorMsg", gbatchService.getMsg());
			return "systemerror_view";
		}
	}

	public String doUpdate() throws Exception {
		System.out.println("doUpdate要修改的信息:" + gbatch);

		System.out.println(producerid);
		System.out.println(sellerid);
		System.out.println(goodsid);
		Company producer = companyService.View(producerid);
		Company seller = companyService.View(sellerid);
		Goods goods = goodsService.View(goodsid);
		System.out.println("565000000000659");
		System.out.println(producer);
		System.out.println(seller);
		System.out.println(goods);

		gbatch.setGoods(goods);
		gbatch.setCompanyByProducerId(producer);
		gbatch.setCompanyBySellerId(seller);

		System.out.println("doAdd要添加的信息:" + gbatch);

		GBatch db_gbatch = gbatchService.Update(gbatch);
		this.gbatch = db_gbatch;
		if (db_gbatch != null) {
			ActionContext ctx = ActionContext.getContext();
			ctx.put("gbatch", gbatch);
			ctx.put("goodsid", goodsid);
			return "gbatchinfo_view";
		} else {
			ActionContext.getContext().put("errorMsg", gbatchService.getMsg());
			return "systemerror_view";
		}
	}

	public GBatchService getGBatchService() {
		return gbatchService;
	}

	public void setGBatchService(GBatchService gbatchService) {
		this.gbatchService = gbatchService;
	}

	public GBatch getGBatch() {
		return gbatch;
	}

	public void setGBatch(GBatch gbatch) {
		this.gbatch = gbatch;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getGBatchStartDate() {
		return gbatchStartDate;
	}

	public void setGBatchStartDate(String gbatchStartDate) {
		this.gbatchStartDate = gbatchStartDate;
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

	public GBatchService getGbatchService() {
		return gbatchService;
	}

	public void setGbatchService(GBatchService gbatchService) {
		this.gbatchService = gbatchService;
	}

	public GBatch getGbatch() {
		return gbatch;
	}

	public void setGbatch(GBatch gbatch) {
		this.gbatch = gbatch;
	}

	public String getGbatchStartDate() {
		return gbatchStartDate;
	}

	public void setGbatchStartDate(String gbatchStartDate) {
		this.gbatchStartDate = gbatchStartDate;
	}

	public GoodsService getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public CompanyService getCompanyService() {
		return companyService;
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}

	public int getGoodsid() {
		return goodsid;
	}

	public int getProducerid() {
		return producerid;
	}

	public void setProducerid(int producerid) {
		this.producerid = producerid;
	}

	public int getSellerid() {
		return sellerid;
	}

	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}

	public String getDeletelist() {
		return deletelist;
	}

	public void setDeletelist(String deletelist) {
		this.deletelist = deletelist;
	}
}
