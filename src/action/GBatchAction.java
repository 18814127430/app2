package action;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import service.CompanyService;
import service.GBatchService;
import service.GoodsService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.Company;
import bean.GBatch;
import bean.Goods;
import utils.goodsTool;
import utils.msg;
import utils.test;

public class GBatchAction extends ActionSupport {
	
	private GBatchService gbatchService;// 业务层对象
	private GoodsService goodsService;// 业务层对象
	private CompanyService companyService;// 业务层对象
	private GBatch gbatch;// 待操作的对象
	private String keyword;
	private int goodsid;
	private int producerid;
	private int sellerid;
	private int currentPage;// 显示的当前页
	private String deletelist;
	
	public String beforedoAdd() throws Exception {
		List companylist = companyService.Find_All(keyword);
		ActionContext ctx = ActionContext.getContext();
		ctx.put("companylist", companylist);
		ctx.put("goodsid", goodsid);
		return "gbatchadd_view";
	}
	
	public String doAdd() throws Exception {
		System.out.println("rec:" + gbatch);
		System.out.println("rec:" + producerid);
		System.out.println("rec:" + sellerid);
		System.out.println("rec:" + goodsid);
		
		Company producer = companyService.View(producerid);
		Company seller = companyService.View(sellerid);
		Goods goods = goodsService.View(goodsid);
		
		System.out.println("rec:" + producer);
		System.out.println("rec:" + seller);
		System.out.println("rec:" + goods);
		
		gbatch.setGoods(goods);
		gbatch.setCompanyByProducerId(producer);
		gbatch.setCompanyBySellerId(seller);
		
		System.out.println("rec:" + "doAdd要添加的信息:" + gbatch);
		
		GBatch db_gbatch = gbatchService.Add(gbatch);
		
		System.out.println("rec:" + "doAdd添加后的信息:" + db_gbatch);
		
		this.gbatch = db_gbatch;
		if (db_gbatch != null) {
			ActionContext ctx = ActionContext.getContext();
			ctx.put("gbatch", gbatch);
			return "gbatchinfo_view";
		}
		else {
			ActionContext.getContext().put("Msg", gbatchService.getMsg());
			return "systemerror_view";
		}
	}
	
	public String doDelete() throws Exception {
		GBatch db_gbatch = gbatchService.View(gbatch.getBatchId());
		System.out.println("rec:" + "daDelete要删除的信息:" + db_gbatch);
		if (gbatchService.Delete(db_gbatch)) {
			return (doFind());
		}
		else {
			ActionContext.getContext().put("Msg", gbatchService.getMsg());
			return "systemerror_view";
		}
	}
	
	public String doDeleteAll() throws Exception {
		
		String[] id = deletelist.split(",");// 用逗号切割
		System.out.println("rec:" + "用逗号切割");
		System.out.println("rec:" + deletelist);
		System.out.println("rec:" + currentPage);
		
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
		}
		else {
			ActionContext.getContext().put("Msg", gbatchService.getMsg());
			return "systemerror_view";
		}
	}
	
	public void doAutoAdd() throws Exception {
		
		Random r = new Random();
		int numg = goodsService.Count_Sort_Keyword(0, 0, "");
		int numc = companyService.Count_Keyword("");
		for (int i = 0; i < numg; i++) {
			Goods g = goodsService.View(i + 1);
			
			for (int j = 0; j < 4; j++) {
				int idc1 = r.nextInt(numc) + 1;
				int idc2 = r.nextInt(numc) + 1;
				
				Company c1 = companyService.View(idc1);
				Company c2 = companyService.View(idc2);
				
				GBatch gb = new GBatch();
				gb.setCompanyByProducerId(c1);
				gb.setCompanyBySellerId(c2);
				gb.setDateKeep1(test.String2Timestamp(test.GetCurrentTime()));
				gb.setDateKeep2(test.String2Timestamp(test.GetCurrentTime()));
				gb.setDateRec(test.String2Timestamp(test.GetCurrentTime()));
				gb.setDateSend(test.String2Timestamp(test.GetCurrentTime()));
				gb.setGoods(g);
				int stock = r.nextInt(200) + 100;
				gb.setNumStock(stock);
				gb.setNumTotal(stock);
				gb.setProducerSendContent("健康");
				gb.setSellerReceiveContent("健康");
				
				gbatchService.Add(gb);
			}
		}
	}
	
	public String doFind() throws Exception {
		
		if (goodsid <= 0)
			goodsid = 0;
		if (currentPage <= 0)
			currentPage = 1;
		if (keyword == null || keyword.equals(""))
			keyword = "";
		System.out.println("rec:" + "rec+goodsid:" + goodsid);
		System.out.println("rec:" + "rec+currentPage:" + currentPage);
		System.out.println("rec:" + "rec+keyword:" + keyword);
		
		int totalRecord = 0;
		if (goodsid > 0) {
			totalRecord = gbatchService.Count_GoodsId(goodsid, keyword);
		}
		else {
			totalRecord = gbatchService.Count_Keyword(keyword);
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
		}
		else {
			firstPage = (currentPage / msg.PAGE_SIZE) * msg.PAGE_SIZE + 1;
			lastPage = firstPage + msg.PAGE_SIZE - 1;
			if (lastPage > totalPage) {
				lastPage = totalPage;
			}
		}
		
		int fromIndex = (currentPage - 1) * msg.RECORD_SIZE; // 选择从第几条开始
		
		System.out.println("rec:" + "当前页码：totalPage" + totalPage);
		System.out.println("rec:" + "当前页码：totalRecord" + totalRecord);
		System.out.println("rec:" + "当前页码：currentPage" + currentPage);
		System.out.println("rec:" + "当前页码：fromIndex" + fromIndex);
		System.out.println("rec:" + "当前页码：firstPage" + firstPage);
		System.out.println("rec:" + "当前页码：lastPage" + lastPage);
		
		List list = new ArrayList();
		if (goodsid > 0) {
			list = gbatchService.Find_GoodsId(goodsid, keyword, fromIndex, msg.RECORD_SIZE);
		}
		else {
			list = gbatchService.Find_Keyword(keyword, fromIndex, msg.RECORD_SIZE);
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
		
		if (goodsid > 0) {
			ctx.put("goodsid", goodsid);
			return "gbatchlistbygoods_view";
		}
		return "gbatchlist_view";
	}
	
	public String doEdit() throws Exception {
		List companylist = companyService.Find_All(keyword);
		System.out.println("rec:" + "doEdit要修改信息的ID：" + gbatch.getBatchId());
		GBatch db_gbatch = gbatchService.View(gbatch.getBatchId());
		this.gbatch = db_gbatch;
		System.out.println("rec:" + "doEdit要修改信息：" + gbatch);
		if (gbatch != null) {
			ActionContext ctx = ActionContext.getContext();
			ctx.put("companylist", companylist);
			ctx.put("gbatch", gbatch);
			return "gbatchedit_view";
		}
		else {
			ActionContext.getContext().put("Msg", gbatchService.getMsg());
			return "systemerror_view";
		}
	}
	
	public String doUpdate() throws Exception {
		System.out.println("rec:" + "doUpdate要修改的信息:" + gbatch);
		System.out.println("rec:" + producerid);
		System.out.println("rec:" + sellerid);
		System.out.println("rec:" + goodsid);
		
		Company producer = companyService.View(producerid);
		Company seller = companyService.View(sellerid);
		Goods goods = goodsService.View(goodsid);
		
		System.out.println("rec:" + "====================");
		System.out.println("rec:" + producer);
		System.out.println("rec:" + seller);
		System.out.println("rec:" + goods);
		
		gbatch.setGoods(goods);
		gbatch.setCompanyByProducerId(producer);
		gbatch.setCompanyBySellerId(seller);
		
		System.out.println("rec:" + "doUpdate要添加的信息:" + gbatch);
		
		GBatch db_gbatch = gbatchService.Update(gbatch);
		this.gbatch = db_gbatch;
		if (db_gbatch != null) {
			ActionContext ctx = ActionContext.getContext();
			ctx.put("gbatch", gbatch);
			ctx.put("goodsid", goodsid);
			return "gbatchinfo_view";
		}
		else {
			ActionContext.getContext().put("Msg", gbatchService.getMsg());
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
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
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
