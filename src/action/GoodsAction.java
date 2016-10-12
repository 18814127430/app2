package action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import service.CompanyService;
import service.GoodsService;
import service.SortService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.Goods;
import bean.Sort;
import utils.goodsTool;
import utils.msg;
import utils.test;

public class GoodsAction extends ActionSupport {
	
	private GoodsService goodsService;// 业务层对象
	private CompanyService companyService;// 业务层对象
	private SortService sortService;// 业务层对象
	private Goods goods;
	private String keyword;
	private int sortid;
	private int producerid;
	private int sellerid;
	private int currentPage;
	private String deletelist;
	private String oldhtmlurl;
	
	public String beforedoAdd() throws Exception {
		
		List<?> list = sortService.Find_All();
		List parentlist = new ArrayList();
		List sortlist = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Sort sort = (Sort) list.get(i);
			if (sort.getSortClass() == 1) {
				parentlist.add(sort);
			}
		}
		for (int i = 0; i < parentlist.size(); i++) {
			Sort sort = (Sort) parentlist.get(i);
			List childlist = sortService.Find_SortId(sort.getSortId());
			Set<Sort> set = new HashSet<Sort>(childlist);
			sort.setSorts(set);
			sortlist.add(sort);
		}
		
		for (int i = 0; i < sortlist.size(); i++) {
			Sort sort = (Sort) sortlist.get(i);
			test.a("father:" + sort.getSortName());
			
			Set sortSet = sort.getSorts();
			Iterator iterator1 = sortSet.iterator();
			int j = 0;
			while (iterator1.hasNext()) {
				Sort sort1 = (Sort) iterator1.next();
				j++;
				test.a("               children: " + sort1.getSortName());
			}
		}
		
		List companylist = companyService.Find_All(keyword);
		
		ActionContext ctx = ActionContext.getContext();
		ctx.put("sortlist", sortlist);
		ctx.put("companylist", companylist);
		ctx.put("time", test.GetCurrentTime());
		return "goodsadd_view";
	}
	
	public String doAdd() throws Exception {
		test.a("doAdd要添加的sortid信息:" + sortid);
		test.a("doAdd要添加的producerid信息:" + producerid);
		test.a("doAdd要添加的sellerid信息:" + sellerid);
		test.a("doAdd要添加的getImg1信息:" + goods.getImg1());
		test.a("doAdd要添加的getImg2信息:" + goods.getImg2());
		test.a("doAdd要添加的getImg3信息:" + goods.getImg3());
		test.a("doAdd要添加的getImg4信息:" + goods.getImg4());
		test.a("doAdd要添加的getImg5信息:" + goods.getImg5());
		test.a("doAdd要添加的getImg6信息:" + goods.getImg6());
		test.a("doAdd要添加的getImg7信息:" + goods.getImg7());
		test.a("doAdd要添加的getImg8信息:" + goods.getImg8());
		test.a("doAdd要添加的getImg9信息:" + goods.getImg9());
		test.a("doAdd要添加的getImg10信息:" + goods.getImg10());
		test.a("doAdd要添加的getImg11信息:" + goods.getImg11());
		test.a("doAdd要添加的getImg12信息:" + goods.getImg12());
		test.a("doAdd要添加的getImg13信息:" + goods.getImg13());
		test.a("doAdd要添加的getImg14信息:" + goods.getImg14());
		test.a("doAdd要添加的getImg15信息:" + goods.getImg15());
		test.a("doAdd要添加的getImg16信息:" + goods.getImg16());
		test.a("doAdd要添加的HtmlUrl信息:" + goods.getGoodsHtmlUrl());
		
		String savePath = ServletActionContext.getServletContext().getRealPath("/") + "attached/";
		
		goods = task.Task_SetGoodsImgSize.SetImgSize_Add(goods);
		test.a("doAdd要添加的getImg1信息:" + goods.getImg1());
		test.a("doAdd要添加的getImg2信息:" + goods.getImg2());
		test.a("doAdd要添加的getImg3信息:" + goods.getImg3());
		test.a("doAdd要添加的getImg4信息:" + goods.getImg4());
		test.a("doAdd要添加的getImg5信息:" + goods.getImg5());
		test.a("doAdd要添加的getImg6信息:" + goods.getImg6());
		test.a("doAdd要添加的getImg7信息:" + goods.getImg7());
		test.a("doAdd要添加的getImg8信息:" + goods.getImg8());
		test.a("doAdd要添加的getImg9信息:" + goods.getImg9());
		test.a("doAdd要添加的getImg10信息:" + goods.getImg10());
		test.a("doAdd要添加的getImg11信息:" + goods.getImg11());
		test.a("doAdd要添加的getImg12信息:" + goods.getImg12());
		test.a("doAdd要添加的getImg13信息:" + goods.getImg13());
		test.a("doAdd要添加的getImg14信息:" + goods.getImg14());
		test.a("doAdd要添加的getImg15信息:" + goods.getImg15());
		test.a("doAdd要添加的getImg16信息:" + goods.getImg16());
		test.a("doAdd要添加的HtmlUrl信息:" + goods.getGoodsHtmlUrl());
		
		Sort sort = sortService.View(sortid);
		goods.setSort(sort);
		goods.setCountCart(0);
		goods.setCountCollect(0);
		goods.setCommentStar1(0);
		goods.setCommentStar2(0);
		goods.setCommentStar3(0);
		goods.setCommentStar4(0);
		goods.setCommentStar5(0);
		goods.setCountOrder(0);
		
		//商品关键字
		if (goods.getGoodsKeyWord() == null || goods.getGoodsKeyWord().equals(""))
			goods.setGoodsKeyWord(goods.getGoodsName() + "," + goods.getSort().getSortName());
		else
			goods.setGoodsKeyWord(goods.getGoodsName() + "," + goods.getSort().getSortName() + ","
					+ goods.getGoodsKeyWord());
		
		//最低包邮金额
		if (goods.getMoneyLeast() == null || goods.getMoneyLeast().equals(""))
			goods.setMoneyLeast(msg.goodsmoneyleast);
		
		//运费
		if (goods.getMoneyDeliver() == null || goods.getMoneyDeliver().equals(""))
			goods.setMoneyDeliver(msg.goodsmoneydeliver);
		
		//商品标签
		if (goods.getGoodsTags().length() > 20) {
			String tags = goods.getGoodsTags().substring(0, 19);
			goods.setGoodsTags(tags);
		}
		
		String saveUrl = ServletActionContext.getRequest().getContextPath() + "/attached/";
		String htmlurl = goodsTool.createHtml(goods);
		goods.setGoodsHtmlUrl(saveUrl + htmlurl);
		
		Goods db_goods = goodsService.Add(goods);
		test.a("doAdd要添加的getImg1信息:" + goods.getImg1());
		test.a("doAdd要添加的getImg2信息:" + goods.getImg2());
		test.a("doAdd要添加的getImg3信息:" + goods.getImg3());
		test.a("doAdd要添加的getImg4信息:" + goods.getImg4());
		test.a("doAdd要添加的getImg5信息:" + goods.getImg5());
		test.a("doAdd要添加的getImg6信息:" + goods.getImg6());
		test.a("doAdd要添加的getImg7信息:" + goods.getImg7());
		test.a("doAdd要添加的getImg8信息:" + goods.getImg8());
		test.a("doAdd要添加的getImg9信息:" + goods.getImg9());
		test.a("doAdd要添加的getImg10信息:" + goods.getImg10());
		test.a("doAdd要添加的getImg11信息:" + goods.getImg11());
		test.a("doAdd要添加的getImg12信息:" + goods.getImg12());
		test.a("doAdd要添加的getImg13信息:" + goods.getImg13());
		test.a("doAdd要添加的getImg14信息:" + goods.getImg14());
		test.a("doAdd要添加的getImg15信息:" + goods.getImg15());
		test.a("doAdd要添加的getImg16信息:" + goods.getImg16());
		test.a("doAdd要添加的HtmlUrl信息:" + goods.getGoodsHtmlUrl());
		
		if (db_goods == null) {
			goodsTool.DeleteImgHtml_All(goods);
			ActionContext.getContext().put("Msg", goodsService.getMsg());
			return "systemerror_view";
		}
		
		ActionContext.getContext().put("goods", db_goods);
		return "goodsinfo_view";
	}
	
	public String doDelete() throws Exception {
		Goods db_goods = goodsService.View(goods.getGoodsId());
		Goods goods = db_goods;
		if (goodsService.Delete(db_goods)) {
			goodsTool.DeleteImgHtml_All(goods);
			return (doFind());
		}
		else {
			ActionContext.getContext().put("Msg", goodsService.getMsg());
			return "systemerror_view";
		}
	}
	
	public String doDeleteAll() throws Exception {
		
		String[] id = deletelist.split(",");// 用逗号切割
		test.a("用逗号切割");
		test.a(deletelist);
		test.a(currentPage);
		
		for (int i = 0; i < id.length; i++) {
			int num = Integer.parseInt(id[i]);
			Goods db_goods = goodsService.View(num);
			goodsService.Delete(db_goods);
		}
		
		return (doFind());
	}
	
	public String doView() throws Exception {
		test.a("getGoodsId:" + goods.getGoodsId());
		Goods db_goods = goodsService.View(goods.getGoodsId());
		this.goods = db_goods;///////????????????????????!!!!!!
		if (db_goods != null) {
			ActionContext ctx = ActionContext.getContext();
			ctx.put("goods", goods);
			return "goodsinfo_view";
		}
		else {
			ActionContext.getContext().put("Msg", goodsService.getMsg());
			return "systemerror_view";
		}
	}
	
	public void doFormat() throws Exception {
		int a = goodsService.Count_Sort_Keyword(0, 0, "");
		List list10 = goodsService.Find_Sort_Desc(0, "", 0, a);
		for (int i = 0; i < list10.size(); i++) {
			Goods good1 = (Goods) list10.get(i);
			good1 = goodsTool.formatPath(good1);
			goodsService.Update(good1);
		}
	}
	
	public String doFind() throws Exception {
		
		if (sortid < 10 || sortid > 26)
			sortid = 0;
		if (currentPage <= 0)
			currentPage = 1;
		if (keyword == null || keyword.equals(""))
			keyword = "";
		test.a("rec+sortid:" + sortid);
		test.a("rec+currentPage:" + currentPage);
		test.a("rec+keyword:" + keyword);
		
		int totalRecord = 0;
		
		totalRecord = goodsService.Count_Sort_Keyword(0, sortid, keyword);
		
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
		
		test.a("当前页码：totalPage" + totalPage);
		test.a("当前页码：totalRecord" + totalRecord);
		test.a("当前页码：currentPage" + currentPage);
		test.a("当前页码：fromIndex" + fromIndex);
		test.a("当前页码：firstPage" + firstPage);
		test.a("当前页码：lastPage" + lastPage);
		
		List list = new ArrayList();
		if (sortid == 0) {
			list = goodsService.Find_Desc(keyword, fromIndex, msg.RECORD_SIZE);// 可优化
		}
		else {
			list = goodsService.Find_Sort_Desc(sortid, keyword, fromIndex, msg.RECORD_SIZE);
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
		
		if (sortid > 0) {
			ctx.put("sortid", sortid);
			return "goodslistbysort_view";
		}
		return "goodslist_view";
	}
	
	public String doEdit() throws Exception {
		List list = sortService.Find_All();
		List parentlist = new ArrayList();
		List sortlist = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Sort sort = (Sort) list.get(i);
			if (sort.getSortClass() == 1) {
				parentlist.add(sort);
				test.a(sort);
			}
		}
		for (int i = 0; i < parentlist.size(); i++) {
			Sort sort = (Sort) parentlist.get(i);
			List childlist = sortService.Find_SortId(sort.getSortId());
			Set<Sort> set = new HashSet<Sort>(childlist);
			sort.setSorts(set);
			sortlist.add(sort);
			test.a(sort);
		}
		
		List companylist = companyService.Find_All("");
		
		test.a("doEdit要修改信息的ID：" + goods.getGoodsId());
		Goods db_goods = goodsService.View(goods.getGoodsId());
		this.goods = db_goods;
		if (db_goods != null) {
			ActionContext ctx = ActionContext.getContext();
			ctx.put("sortlist", sortlist);
			ctx.put("companylist", companylist);
			ctx.put("goods", goods);
			
			return "goodsedit_view";
		}
		else {
			ActionContext.getContext().put("Msg", goodsService.getMsg());
			return "systemerror_view";
		}
	}
	
	public String doUpdate() throws Exception {
		Goods old_goods = goodsService.View(goods.getGoodsId());
		
		test.a("doUpdate要添加的sortid信息:" + sortid);
		test.a("doUpdate要添加的producerid信息:" + producerid);
		test.a("doUpdate要添加的sellerid信息:" + sellerid);
		test.a("doUpdate要添加的getImg1信息:" + goods.getImg1());
		test.a("doUpdate要添加的getImg2信息:" + goods.getImg2());
		test.a("doUpdate要添加的getImg3信息:" + goods.getImg3());
		test.a("doUpdate要添加的getImg4信息:" + goods.getImg4());
		test.a("doUpdate要添加的getImg5信息:" + goods.getImg5());
		test.a("doUpdate要添加的getImg6信息:" + goods.getImg6());
		test.a("doUpdate要添加的getImg7信息:" + goods.getImg7());
		test.a("doUpdate要添加的getImg8信息:" + goods.getImg8());
		test.a("doUpdate要添加的getImg9信息:" + goods.getImg9());
		test.a("doUpdate要添加的getImg10信息:" + goods.getImg10());
		test.a("doUpdate要添加的getImg11信息:" + goods.getImg11());
		test.a("doUpdate要添加的getImg12信息:" + goods.getImg12());
		test.a("doUpdate要添加的getImg13信息:" + goods.getImg13());
		test.a("doUpdate要添加的getImg14信息:" + goods.getImg14());
		test.a("doUpdate要添加的getImg15信息:" + goods.getImg15());
		test.a("doUpdate要添加的getImg16信息:" + goods.getImg16());
		test.a("doUpdate要添加的HtmlUrl信息:" + goods.getGoodsHtmlUrl());
		
		goods = task.Task_SetGoodsImgSize.SetImgSize_Update(old_goods, goods);
		test.a("doUpdate要添加的getImg1信息:" + goods.getImg1());
		test.a("doUpdate要添加的getImg2信息:" + goods.getImg2());
		test.a("doUpdate要添加的getImg3信息:" + goods.getImg3());
		test.a("doUpdate要添加的getImg4信息:" + goods.getImg4());
		test.a("doUpdate要添加的getImg5信息:" + goods.getImg5());
		test.a("doUpdate要添加的getImg6信息:" + goods.getImg6());
		test.a("doUpdate要添加的getImg7信息:" + goods.getImg7());
		test.a("doUpdate要添加的getImg8信息:" + goods.getImg8());
		test.a("doUpdate要添加的getImg9信息:" + goods.getImg9());
		test.a("doUpdate要添加的getImg10信息:" + goods.getImg10());
		test.a("doUpdate要添加的getImg11信息:" + goods.getImg11());
		test.a("doUpdate要添加的getImg12信息:" + goods.getImg12());
		test.a("doUpdate要添加的getImg13信息:" + goods.getImg13());
		test.a("doUpdate要添加的getImg14信息:" + goods.getImg14());
		test.a("doUpdate要添加的getImg15信息:" + goods.getImg15());
		test.a("doUpdate要添加的getImg16信息:" + goods.getImg16());
		test.a("doUpdate要添加的HtmlUrl信息:" + goods.getGoodsHtmlUrl());
		
		Sort sort = sortService.View(sortid);
		goods.setSort(sort);
		
		//商品关键字
		if (goods.getGoodsKeyWord() == null || goods.getGoodsKeyWord().equals(""))
			goods.setGoodsKeyWord(goods.getGoodsName() + "," + goods.getSort().getSortName());
		
		//最低包邮金额
		if (goods.getMoneyLeast() == null || goods.getMoneyLeast().equals(""))
			goods.setMoneyLeast(msg.goodsmoneyleast);
		
		//运费
		if (goods.getMoneyDeliver() == null || goods.getMoneyDeliver().equals(""))
			goods.setMoneyDeliver(msg.goodsmoneydeliver);
		
		//商品标签
		if (goods.getGoodsTags().length() > 20) {
			String tags = goods.getGoodsTags().substring(0, 19);
			goods.setGoodsTags(tags);
		}
		
		String saveUrl = ServletActionContext.getRequest().getContextPath() + "/attached/";
		String htmlurl = goodsTool.createHtml(goods);
		goods.setGoodsHtmlUrl(saveUrl + htmlurl);
		
		Goods db_goods = goodsService.Update(goods);
		test.a("doUpdate要添加的getImg1信息:" + goods.getImg1());
		test.a("doUpdate要添加的getImg2信息:" + goods.getImg2());
		test.a("doUpdate要添加的getImg3信息:" + goods.getImg3());
		test.a("doUpdate要添加的getImg4信息:" + goods.getImg4());
		test.a("doUpdate要添加的getImg5信息:" + goods.getImg5());
		test.a("doUpdate要添加的getImg6信息:" + goods.getImg6());
		test.a("doUpdate要添加的getImg7信息:" + goods.getImg7());
		test.a("doUpdate要添加的getImg8信息:" + goods.getImg8());
		test.a("doUpdate要添加的getImg9信息:" + goods.getImg9());
		test.a("doUpdate要添加的getImg10信息:" + goods.getImg10());
		test.a("doUpdate要添加的getImg11信息:" + goods.getImg11());
		test.a("doUpdate要添加的getImg12信息:" + goods.getImg12());
		test.a("doUpdate要添加的getImg13信息:" + goods.getImg13());
		test.a("doUpdate要添加的getImg14信息:" + goods.getImg14());
		test.a("doUpdate要添加的getImg15信息:" + goods.getImg15());
		test.a("doUpdate要添加的getImg16信息:" + goods.getImg16());
		test.a("doUpdate要添加的HtmlUrl信息:" + goods.getGoodsHtmlUrl());
		
		if (db_goods == null) {
			goodsTool.DeleteImgHtml_All(goods);
			ActionContext.getContext().put("Msg", goodsService.getMsg());
			return "systemerror_view";
		}
		
		goodsTool.DeleteImgHtml_Some(old_goods, db_goods);
		
		ActionContext ctx = ActionContext.getContext();
		ctx.put("goods", db_goods);
		return "goodsinfo_view";
	}
	
	public GoodsService getGoodsService() {
		return goodsService;
	}
	
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	
	public Goods getGoods() {
		return goods;
	}
	
	public void setGoods(Goods goods) {
		this.goods = goods;
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
	
	public int getSortid() {
		return sortid;
	}
	
	public void setSortid(int sortid) {
		this.sortid = sortid;
	}
	
	public CompanyService getCompanyService() {
		return companyService;
	}
	
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	public SortService getSortService() {
		return sortService;
	}
	
	public void setSortService(SortService sortService) {
		this.sortService = sortService;
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
	
	public String getOldhtmlurl() {
		return oldhtmlurl;
	}
	
	public void setOldhtmlurl(String oldhtmlurl) {
		this.oldhtmlurl = oldhtmlurl;
	}
	
}
